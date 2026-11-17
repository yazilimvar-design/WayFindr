package com.example.wayfindr.dash.nav

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Handler
import android.os.Looper
import android.os.Messenger
import android.util.Log
import android.widget.Toast
import com.example.wayfindr.BuildConfig
import com.example.wayfindr.dash.DashConfig
import com.example.wayfindr.dash.GoogleKey
import com.example.wayfindr.dash.RoutingSource
import com.example.wayfindr.data.SharedLocation
import com.google.android.libraries.mapsplatform.turnbyturn.TurnByTurnManager
import com.google.android.libraries.mapsplatform.turnbyturn.model.NavInfo
import com.google.android.libraries.mapsplatform.turnbyturn.model.Maneuver as NavManeuver
import com.google.android.libraries.navigation.NavigationApi
import com.google.android.libraries.navigation.NavigationUpdatesOptions
import com.google.android.libraries.navigation.Navigator
import com.google.android.libraries.navigation.RoadSnappedLocationProvider
import com.google.android.libraries.navigation.RoutingOptions
import com.google.android.libraries.navigation.Waypoint

/**
 * Google Navigation SDK guidance for the dash: while active, the rider position comes from the
 * SDK's road-snapped location and the next turn / distances / ETA from its NavInfo, and the SDK
 * does the rerouting. It never replaces the normal pipeline: DashViewModel keeps its own route
 * and only reads from here while [active]; any failure just stops this and the normal OSM /
 * Routes API navigation carries on.
 *
 * Billing: [start] (→ Navigator.setDestinations) is only called when the rider starts navigation.
 */
class GoogleNav(private val app: Application, private val listener: Listener) {
    interface Listener {
        fun onSnappedLocation(loc: Location)
        /** New / rerouted route: polyline, total metres and seconds from the rider's position (0 = unknown). */
        fun onRoute(points: List<GeoPoint>, meters: Double, seconds: Double)
        fun onArrival(finalDestination: Boolean)
        fun onFailed(reason: String)
    }

    private var navigator: Navigator? = null
    private var snapped: RoadSnappedLocationProvider? = null
    // Bumped by every start/stop; a callback from an older session drops itself.
    private var generation = 0

    /** Guidance is running for the current trip (DashViewModel reads [navInfo] only then). */
    @Volatile var active = false
        private set

    private val snapListener = object : RoadSnappedLocationProvider.LocationListener {
        override fun onLocationChanged(location: Location) { if (active) listener.onSnappedLocation(location) }
    }
    private val arrivalListener = Navigator.ArrivalListener { e ->
        if (!e.isFinalDestination) runCatching { navigator?.continueToNextDestination() }
        listener.onArrival(e.isFinalDestination)
        // Not from inside the SDK's own listener loop.
        if (e.isFinalDestination) Handler(Looper.getMainLooper()).post { stop() }
    }
    private val routeListener = Navigator.RouteChangedListener { publishRoute() }

    /** Starts guidance to [stops] (intermediates first, final last) if the SDK can be used now. */
    fun start(stops: List<SharedLocation>) {
        stop()
        if (!canUse(app) || !NavigationApi.areTermsAccepted(app)) return
        val waypoints = stops.mapNotNull { s ->
            if (s.lat == null || s.lng == null) null
            else runCatching { Waypoint.builder().setTitle(s.name).setLatLng(s.lat, s.lng).build() }.getOrNull()
        }
        if (waypoints.isEmpty()) return
        val gen = ++generation
        try {
            setKeyOnce()
            NavigationApi.getNavigator(app, object : NavigationApi.NavigatorListener {
                override fun onNavigatorReady(nav: Navigator) {
                    if (gen != generation) return
                    navigator = nav
                    runCatching { route(nav, waypoints, gen) }.onFailure { fail(gen, "setup: ${it.message}") }
                }
                override fun onError(errorCode: Int) = fail(gen, "navigator error $errorCode")
            })
        } catch (t: Throwable) {
            fail(gen, "init: ${t.message}")
        }
    }

    private fun route(nav: Navigator, waypoints: List<Waypoint>, gen: Int) {
        // The app's own VoiceManager speaks the turns (fed from NavInfo); the SDK stays silent.
        nav.setAudioGuidance(Navigator.AudioGuidance.SILENT)
        nav.addArrivalListener(arrivalListener)
        nav.addRouteChangedListener(routeListener)
        // Google's own limit data: it won't give the number, only "you are over it" (shown by flashing the sign).
        runCatching {
            nav.setSpeedingListener { _, sev ->
                NavPlus.gSpeeding = sev != com.google.android.libraries.navigation.SpeedAlertSeverity.NONE
                NavPlus.gSpeedingAt = android.os.SystemClock.elapsedRealtime()
            }
        }
        nav.registerServiceForNavUpdates(
            app.packageName, NavInfoService::class.java.name,
            NavigationUpdatesOptions.builder().setNumNextStepsToPreview(1).build(),
        )
        // shortcut: DRIVING, not TWO_WHEELER (that mode is only offered in some countries).
        // Scenic: avoid highways/tolls for the whole trip, SDK reroutes included (RoutingOptions).
        val scenic = DashConfig.get(app).scenicRoute.value
        nav.setDestinations(waypoints, RoutingOptions().travelMode(RoutingOptions.TravelMode.DRIVING)
            .avoidHighways(scenic).avoidTolls(scenic))
            .setOnResultListener { status ->
                if (gen != generation) return@setOnResultListener
                if (status != Navigator.RouteStatus.OK) { fail(gen, "route $status"); return@setOnResultListener }
                runCatching {
                    nav.startGuidance()
                    snapped = NavigationApi.getRoadSnappedLocationProvider(app)?.also { it.addLocationListener(snapListener) }
                    active = true
                    publishRoute()
                    Log.i(TAG, "guidance started (${waypoints.size} stops)")
                }.onFailure { fail(gen, "guidance: ${it.message}") }
            }
    }

    private fun publishRoute() {
        val nav = navigator ?: return
        if (!active) return
        runCatching {
            val points = nav.routeSegments.flatMap { seg -> seg.latLngs.map { GeoPoint(it.latitude, it.longitude) } }
            val td = nav.currentTimeAndDistance
            if (points.size >= 2) listener.onRoute(points, td?.meters?.toDouble() ?: 0.0, td?.seconds?.toDouble() ?: 0.0)
        }.onFailure { Log.w(TAG, "route read failed: ${it.message}") }
    }

    private fun fail(gen: Int, reason: String) {
        if (gen != generation) return
        Log.w(TAG, "fallback: $reason")
        stop()
        listener.onFailed(reason)
    }

    /** Stop guidance and drop the destinations (trip ended, new trip, or failure). */
    fun stop() {
        generation++
        active = false
        navInfo = null
        NavPlus.gSpeeding = false
        snapped?.let { s -> runCatching { s.removeLocationListener(snapListener) } }
        snapped = null
        navigator?.let { nav ->
            runCatching {
                nav.removeArrivalListener(arrivalListener)
                nav.removeRouteChangedListener(routeListener)
                nav.setSpeedingListener(null)
                nav.unregisterServiceForNavUpdates()
                nav.stopGuidance()
                nav.clearDestinations()
            }
        }
    }

    /** Release the SDK's navigator (view model cleared). */
    fun cleanup() {
        stop()
        navigator?.let { runCatching { it.cleanup() } }
        navigator = null
    }

    /** Receives NavInfo from the SDK (registerServiceForNavUpdates). */
    class NavInfoService : Service() {
        private val tbt = TurnByTurnManager.createInstance()
        private val messenger = Messenger(Handler(Looper.getMainLooper()) { msg ->
            if (msg.what == TurnByTurnManager.MSG_NAV_INFO) {
                navInfo = runCatching { tbt.readNavInfoFromBundle(msg.data) }.getOrNull()
            }
            true
        })
        override fun onBind(intent: Intent?) = messenger.binder
    }

    companion object {
        private const val TAG = "GoogleNav"
        private const val PREFS = "google_nav"
        private const val KEY_TERMS_DECLINED = "terms_declined"

        /** Latest turn-by-turn data; null when no guidance is running. */
        @Volatile var navInfo: NavInfo? = null

        private var keySet = false
        private fun setKeyOnce() {
            if (keySet) return
            keySet = true
            // The manifest carries the build key; a key the rider entered in Settings replaces it.
            val key = GPlus.key()
            if (key != BuildConfig.MAPS_API_KEY) NavigationApi.setApiKey(key)
        }

        /** Real key, Google routing chosen, and Google Play services installed. */
        fun canUse(ctx: Context): Boolean =
            GoogleKey.present &&
                DashConfig.get(ctx).routingSource.value == RoutingSource.GOOGLE &&
                runCatching { ctx.packageManager.getApplicationInfo("com.google.android.gms", 0).enabled }.getOrDefault(false)

        /**
         * Runs [then] after making sure Google's Navigation terms were answered: shows the terms
         * dialog once when the SDK would be used and they were never accepted or declined.
         */
        fun ensureTerms(activity: Activity?, then: () -> Unit) {
            val prefs = activity?.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            if (activity == null || prefs == null || prefs.getBoolean(KEY_TERMS_DECLINED, false) ||
                !canUse(activity) || runCatching { NavigationApi.areTermsAccepted(activity.application) }.getOrDefault(true)
            ) { then(); return }
            try {
                NavigationApi.showTermsAndConditionsDialog(
                    activity, activity.applicationInfo.loadLabel(activity.packageManager).toString(),
                    "Google navigasyon şartları",
                ) { accepted ->
                    if (!accepted) {
                        prefs.edit().putBoolean(KEY_TERMS_DECLINED, true).apply()
                        Toast.makeText(activity, "Şartlar kabul edilmedi; standart navigasyon kullanılıyor.", Toast.LENGTH_SHORT).show()
                    }
                    then()
                }
            } catch (t: Throwable) {
                Log.w(TAG, "terms dialog failed: ${t.message}")
                then()
            }
        }

        /** [step] as the app's Maneuver (null = destination next); [stepKey] tags the route version. */
        fun maneuverOf(step: com.google.android.libraries.mapsplatform.turnbyturn.model.StepInfo?, cumulativeMeters: Double, stepKey: Int): Maneuver? {
            step ?: return null
            val name = googleName(step.maneuver) ?: return null
            // TurnAnnouncer keys turns by location; the step number (+ route version) stands in for it.
            return Maneuver(
                ManeuverType.fromGoogle(name), step.fullInstructionText ?: "",
                GeoPoint((step.stepNumber ?: 0).toDouble(), stepKey.toDouble()), cumulativeMeters, name,
            )
        }

        /** Nav SDK maneuver → Routes API maneuver name (drives DashGlyph); null = arriving. */
        fun googleName(m: Int): String? = when (m) {
            NavManeuver.DESTINATION, NavManeuver.DESTINATION_LEFT, NavManeuver.DESTINATION_RIGHT -> null
            NavManeuver.DEPART -> "DEPART"
            NavManeuver.TURN_LEFT -> "TURN_LEFT"
            NavManeuver.TURN_RIGHT -> "TURN_RIGHT"
            NavManeuver.TURN_KEEP_LEFT, NavManeuver.TURN_SLIGHT_LEFT -> "TURN_SLIGHT_LEFT"
            NavManeuver.TURN_KEEP_RIGHT, NavManeuver.TURN_SLIGHT_RIGHT -> "TURN_SLIGHT_RIGHT"
            NavManeuver.TURN_SHARP_LEFT -> "TURN_SHARP_LEFT"
            NavManeuver.TURN_SHARP_RIGHT -> "TURN_SHARP_RIGHT"
            NavManeuver.TURN_U_TURN_CLOCKWISE -> "UTURN_RIGHT"
            NavManeuver.TURN_U_TURN_COUNTERCLOCKWISE -> "UTURN_LEFT"
            NavManeuver.MERGE_UNSPECIFIED, NavManeuver.MERGE_LEFT, NavManeuver.MERGE_RIGHT -> "MERGE"
            NavManeuver.FORK_LEFT -> "FORK_LEFT"
            NavManeuver.FORK_RIGHT -> "FORK_RIGHT"
            // Ramps: the SDK numbers left variants even (on-ramp) / odd (off-ramp).
            in NavManeuver.ON_RAMP_LEFT..NavManeuver.ON_RAMP_U_TURN_COUNTERCLOCKWISE ->
                if (m % 2 == 0) "RAMP_LEFT" else "RAMP_RIGHT"
            in NavManeuver.OFF_RAMP_LEFT..NavManeuver.OFF_RAMP_U_TURN_COUNTERCLOCKWISE ->
                if (m % 2 == 1) "RAMP_LEFT" else "RAMP_RIGHT"
            in NavManeuver.ROUNDABOUT_CLOCKWISE..NavManeuver.ROUNDABOUT_EXIT_COUNTERCLOCKWISE ->
                if (m % 2 == 1) "ROUNDABOUT_CLOCKWISE" else "ROUNDABOUT_COUNTERCLOCKWISE"
            NavManeuver.FERRY_BOAT -> "FERRY"
            NavManeuver.FERRY_TRAIN -> "FERRY_TRAIN"
            else -> "STRAIGHT"
        }
    }
}
