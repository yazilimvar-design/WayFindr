package com.example.wayfindr.viewmodel

import android.app.Application
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.PowerManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wayfindr.dash.DarkSchedule
import com.example.wayfindr.dash.DashKeepAliveService
import com.example.wayfindr.dash.DashSession
import com.example.wayfindr.dash.DashState
import com.example.wayfindr.dash.DashWifiManager
import com.example.wayfindr.dash.WifiConnStatus
import com.example.wayfindr.dash.map.LocationTracker
import com.example.wayfindr.dash.map.MapRenderer
import com.example.wayfindr.dash.map.Mercator
import com.example.wayfindr.dash.map.TileProvider
import com.example.wayfindr.dash.map.OffRouteConfirmation
import com.example.wayfindr.dash.map.WeakGps
import com.example.wayfindr.dash.nav.GeoPoint
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.dash.nav.Route
import com.example.wayfindr.dash.nav.Router
import com.example.wayfindr.dash.nav.TrafficSwitch
import com.example.wayfindr.dash.nav.TrafficInterval
import com.example.wayfindr.dash.protocol.DashCommands
import com.example.wayfindr.dash.video.DashEncoder
import com.example.wayfindr.dash.video.NalProcessor
import com.example.wayfindr.dash.video.RtpPacketizer
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

enum class ConnStage { OFFLINE, WIFI, AUTH, STREAMING, ERROR }

/**
 * GPS health shown to the rider so a bad signal degrades *visibly* instead of the marker just
 * freezing. GOOD = fresh, accurate. WEAK = imprecise, slow, or briefly stale (urban canyon /
 * next to a building) → marker holds, dash warns. LOST = no accepted fix for 12 s → grey
 * marker and "GPS lost".
 */
enum class GpsStatus { GOOD, WEAK, LOST }

data class DashUiState(
    val stage: ConnStage = ConnStage.OFFLINE,
    val frameCount: Int = 0,
    val lastButton: String? = null,
    val ssid: String = "",            // empty until a dash is discovered/paired (see DashConfig)
    val wifiPassword: String = "12345678",  // CR Console factory passphrase; rider-overridable
    val destinationName: String? = null,
    val errorMessage: String? = null,
    val retryAttempt: Int = 0,        // >0 while auto-retrying the flaky auth handshake
    val mapZoom: Int = 19,
    val remainingKm: Double? = null,
    val etaMinutes: Int? = null,
    val maneuver: String? = null,
    val hasGps: Boolean = false,
    val gpsStatus: GpsStatus = GpsStatus.LOST,
    val hasRoute: Boolean = false,
    val offRoute: Boolean = false,
    val recalculating: Boolean = false, // off-route long enough / actively rerouting → ETA frozen, pill shows "Recalculating"
    val navPaused: Boolean = false,     // "Navigasyonu duraklat": route kept, guidance suspended
    val headingUp: Boolean = true,
    val mapDark: Boolean = false,       // effective (auto schedule or manual)
    val mapDarkAuto: Boolean = true,
    val followMode: Boolean = true,
    val thermal: String = "OK",
    val needsWifiOn: Boolean = false,   // Wi‑Fi radio is off → UI prompts to enable it
    // For the in-app Google Map view (mirrors the dash viewport)
    val riderLat: Double? = null,
    val riderLng: Double? = null,
    val riderBearing: Float = 0f,
    val camLat: Double? = null,
    val camLng: Double? = null,
    val camBearing: Float = 0f,
    val destLatLng: Pair<Double, Double>? = null,
    val routePoints: List<GeoPoint> = emptyList(),
    val routeTraffic: List<TrafficInterval> = emptyList(),
)

class DashViewModel(app: Application) : AndroidViewModel(app) {
    private val _ui = MutableStateFlow(DashUiState())
    val ui = _ui.asStateFlow()

    // The EXACT frame being H.264-encoded to the dash, published for the in-app "Dash view"
    // so the phone preview is pixel-identical to the bike screen (same renderer, not a second
    // map engine). A fresh immutable snapshot per redraw — the encoder's own bitmap is mutated
    // on a background thread and recycled on teardown, so handing that out would tear/crash.
    private val _previewFrame = MutableStateFlow<Bitmap?>(null)
    val previewFrame = _previewFrame.asStateFlow()

    private val session     = DashSession(viewModelScope).also { s ->
        s.phoneStatusProvider = { com.example.wayfindr.dash.PhoneStatus.read(getApplication()) }
    }
    /** Live dash → app data fields, for the "Dash data" tab. */
    val dashFields get() = session.dashFields
    private val wifiManager = DashWifiManager(app, viewModelScope)
    private val dashConfig  = com.example.wayfindr.dash.DashConfig.get(app)
    private val voice        = com.example.wayfindr.dash.nav.VoiceManager.get(app)
    private val curveWarner  = com.example.wayfindr.dash.nav.CurveWarner()
    private val routeWeather = com.example.wayfindr.dash.nav.RouteWeather()
    private var alertsRoute: Route? = null
    private val repo         = com.example.wayfindr.data.SyncRepository.get(app)
    private val recorder     = com.example.wayfindr.data.RideRecorder()
    private var recordJob: Job? = null
    private val tiles       = TileProvider(app, viewModelScope)
    private val location    = LocationTracker(app)
    // Google Navigation SDK (when usable): road-snapped position + turn-by-turn for the dash. The
    // normal route pipeline below always runs; tick() only reads the SDK's data while it is active.
    private val googleNav = com.example.wayfindr.dash.nav.GoogleNav(app, object : com.example.wayfindr.dash.nav.GoogleNav.Listener {
        override fun onSnappedLocation(loc: android.location.Location) {
            location.snappedFeed = true
            location.feedSnapped(loc)
        }
        override fun onRoute(points: List<GeoPoint>, meters: Double, seconds: Double) {
            if (!hasDestination()) return
            // The SDK's route (and each of its reroutes) replaces ours; drop any request of ours still running.
            routeRequestGeneration++
            routeRequestJob?.cancel()
            routeRequestJob = null
            rerouting = false
            trafficRefreshInFlight = false
            waitingForFreshRouteOrigin = false
            val cumulative = DoubleArray(points.size)
            for (i in 1 until points.size) cumulative[i] = cumulative[i - 1] + GeoPoint.distMeters(points[i - 1], points[i])
            val r = Route(points, emptyList(), meters.takeIf { it > 0.0 } ?: cumulative.last(), seconds, cumulative, source = com.example.wayfindr.dash.RoutingSource.GOOGLE)
            val prev = route; val prevProgress = progressM
            route = r
            progressM = 0.0; progressFixNs = 0L
            routeLegOffset = activeStopIndex
            navSdkRouteVersion++
            voice.resetTrip()
            tiles.prefetchRoute(points)
            // The SDK itself moves to a faster route when traffic changes; only announce a clear gain.
            val leftS = prev?.let { TrafficSwitch.remainingSeconds(it.totalSeconds, it.totalMeters, prevProgress) } ?: 0.0
            val gainS = leftS - r.totalSeconds
            val now = System.currentTimeMillis()
            // The first SDK route replaces our own estimate, so it is never "a switch".
            if (navSdkRouteVersion > 1 && TrafficSwitch.worthSwitching(leftS, r.totalSeconds, now, lastTrafficSwitchAt)) {
                lastTrafficSwitchAt = now
                voice.alert(TrafficSwitch.message(gainS))
            }
            _ui.update { it.copy(errorMessage = null, hasRoute = true, routePoints = points, routeTraffic = emptyList()) }
            com.example.wayfindr.data.RideDiagnostics.log("navsdk", "route ${points.size} pts, ${meters.toInt()} m")
        }
        override fun onArrival(finalDestination: Boolean) {
            if (!finalDestination && activeStopIndex < routeStops.lastIndex) advanceToNextStop(activeStopIndex)
            if (finalDestination) location.snappedFeed = false
        }
        override fun onFailed(reason: String) {
            location.snappedFeed = false
            com.example.wayfindr.data.RideDiagnostics.log("navsdk", "fallback: $reason")
            android.widget.Toast.makeText(getApplication(), "Google navigasyonu kullanılamadı; standart rota kullanılıyor.", android.widget.Toast.LENGTH_SHORT).show()
        }
    })
    // Bumped per Navigation SDK route, so a rerouted step number counts as a new turn for voice.
    @Volatile private var navSdkRouteVersion = 0
    private val mapRenderer = MapRenderer(tiles)
    private val mediaInfo   = com.example.wayfindr.media.MediaInfoProvider(app)
    private val callController = com.example.wayfindr.media.CallController(app)
    private var mediaObserveJob: Job? = null
    private val powerManager = app.getSystemService(Application.POWER_SERVICE) as PowerManager

    private var encoder: DashEncoder? = null
    private var streamJob: Job? = null
    private var tickerJob: Job? = null
    // Even presentation clock (ms) feeding the RTP 90 kHz timestamp — advances by exactly one
    // frame interval per encoded frame so the dash gets an evenly-spaced timeline (NOT wall clock,
    // which inherited the loop's jitter). Paired with absolute-grid send pacing below.
    @Volatile private var videoPtsMs = 0L
    // Set on a joystick zoom; cleared when the zoom's keyframe leaves the phone, so the ride log
    // shows how much of the press→dash lag is ours vs. the dash's own decode/display.
    @Volatile private var zoomPressedAtMs = 0L
    // Picker input time, so the ride log shows how long the list change took to leave the phone.
    @Volatile private var pickerInputAtMs = 0L
    private var lastLoneDownMs = 0L
    // "DOWN again" hint on the dash, shown while a lone DOWN waits for its partner.
    @Volatile private var downHintUntilMs = 0L
    @Volatile private var musicHintUntilMs = 0L   // after a lone ▼ while music plays: ▶ next / ◀ previous
    // Short confirmation drawn on the dash (e.g. after "Cancel next stop") until noticeUntilMs.
    @Volatile private var notice: List<String>? = null
    @Volatile private var noticeUntilMs = 0L

    // Joystick-UP navigation prompt (no call ringing, route set): LEFT pause/resume, RIGHT end, DOWN cancel.
    @Volatile private var navPromptOpen = false
    @Volatile private var debugLastStops: List<com.example.wayfindr.data.SharedLocation>? = null   // CI redo_dest
    private var navPromptJob: Job? = null

    private fun showNotice(vararg lines: String) {
        notice = lines.toList()
        noticeUntilMs = System.currentTimeMillis() + NOTICE_MS
        lastSignature = ""
        wakeFrameLoop()
    }

    // ── On-dash saved-destination picker (joystick-driven, free-roam only) ──
    // LEFT = up, RIGHT = down, long-press RIGHT = open (free roam) / select (picker open).
    @Volatile private var pickerItems: List<com.example.wayfindr.data.SavedLocation>? = null
    @Volatile private var pickerIndex = 0
    /**
     * Page of the on-dash menu: ROOT = (with a route) pause/resume, end, skip next stop; then
     * "Uyarılar ›", the quick actions and saved destinations; SOURCE = the alert switches.
     */
    private enum class MenuPage { ROOT, SOURCE }
    @Volatile private var menuPage = MenuPage.ROOT
    @Volatile private var pickerLastInputMs = 0L
    // State just before the last RIGHT tap, so a gesture that follows it (second tap of a
    // double-tap, or a long press right after a tap) can undo that tap's zoom / list move.
    private val pickerGesture by lazy { PickerGesture(BTN_JOY_RIGHT, BTN_JOY_LEFT, BTN_JOY_DOWN, DOUBLE_TAP_MS, DOUBLE_DOWN_MS) }
    private var zoomBeforeRightTap = 0
    private var sendPaceMs = 0.0    // EMA of the real frame-send interval — exposes cadence jitter
    private var sendPaceMaxMs = 0L  // worst interval since the last diag line
    private var tickMsAvg = 0.0     // EMA of tick (nav + render) time per frame
    private var encodeMsAvg = 0.0   // EMA of encoder feed + drain time per frame
    private var lastPreviewMs = 0L
    private var diagDecoded = 0L    // session.framesDecoded at the last diag line
    // Backstop so a dropped/never-reached connection can't drain the battery forever: if we don't
    // reach STREAMING within this window, give up completely (stop the Wi‑Fi reconnect loop, the
    // foreground service, GPS and the encoder). Cancelled the moment streaming starts.
    private var giveupJob: Job? = null
    private var connLostAlertJob: Job? = null
    private var connLostAlerted = false
    // Dash Wi‑Fi was up on the last state update (to spot a mid-ride drop).
    private var dashWifiWasConnected = false

    private var userWantsConnection = false
    private var authAttempts = 0       // bounded auto-retry of the (flaky) auth handshake
    // This connection reached STREAMING at least once → a drop gets the long reconnect window.
    @Volatile private var hasStreamedThisConnection = false
    // A route request is waiting for a usable GPS origin fix (see fetchRoute).
    @Volatile private var waitingForFreshRouteOrigin = false

    // ── Navigation/map state read by the 4 fps frame loop ──
    @Volatile private var destLat: Double? = null
    @Volatile private var destLng: Double? = null
    @Volatile private var routeWaypoints: List<GeoPoint> = emptyList()
    @Volatile private var routeStops: List<com.example.wayfindr.data.SharedLocation> = emptyList()
    @Volatile private var activeStopIndex = 0
    // A reroute starts at the current stop, so its leg zero may be a later itinerary stop.
    @Volatile private var routeLegOffset = 0
    @JvmField @Volatile var route: Route? = null  // read by NavPlus.pickPlanned
    @Volatile private var panX = 0f
    @Volatile private var panY = 0f
    @Volatile private var zoom = START_ZOOM  // dash map always opens at z17
    @Volatile private var headingUp = true
    @JvmField @Volatile var mapDark = false  // read by NavPlus (dash menu)
    @Volatile private var followMode = true
    @Volatile private var lastManualPanAt = 0L

    // Smoothed camera — eased toward the latest GPS target every frame so the map
    // glides at 8 fps instead of jumping once per 1 Hz fix (the "cheap"/laggy feel).
    private var camLat = 0.0
    private var camLng = 0.0
    private var camHdg = 0f
    private var camInit = false
    private var lastTickNs = 0L          // for framerate-independent smoothing

    // Dead-reckoning: last GPS fix + its velocity, so the camera keeps gliding forward
    // between the 1 Hz fixes instead of easing to a stop (the "laggy" feel at speed).
    private var fixLat = 0.0
    private var fixLng = 0.0
    private var fixBearing = 0f
    private var fixSpeed = 0f             // m/s
    private var fixWallMs = 0L
    private var lastFixTime = 0L
    // The fix's own measurement time and when this loop received it (elapsedRealtime ns): the
    // prediction counts from the measurement, so a fix that is already old on delivery
    // (fused / Navigation SDK) no longer leaves the marker that far behind.
    private var fixElapsedNs = 0L
    private var fixArrivalNs = 0L
    private var fixAccuracy = 12f
    // Turns the per-fix prediction into continuous motion (see RiderMotion); the mode tag
    // (0 none, 1 moving, 2 stopped) rebases it when the prediction switches source.
    private val riderMotion = com.example.wayfindr.dash.map.RiderMotion()
    private var motionMode = 0
    private var motionOnRoute = false
    private var fixIsNew = false
    // Route progress the on-route prediction landed on (-1 = off route): the camera heading follows
    // the road there instead of the last fix's GPS bearing.
    private var predProgressM = -1.0
    private var lastWarmMs = 0L
    private var gpsFixIntervalMs = 0.0   // EMA of the gap between fixes — exposes screen-off GPS throttling

    // GPS health surfaced to the dash + phone (computed each tick from accuracy + fix age).
    @Volatile private var gpsStatus = GpsStatus.LOST
    // Held marker position while stationary — deadbands sub-accuracy GPS wander so a parked bike's
    // dot doesn't drift/jitter. Updated to the live fix while moving; reset on teardown.
    private var heldRider: Pair<Double, Double>? = null

    // Smoothed rider position shown on the dash frame (locked to the camera centre so the
    // marker stays put and the map slides under it). null = no GPS.
    @Volatile private var frameRiderLat: Double? = null
    @Volatile private var frameRiderLng: Double? = null

    // Stable ETA (Google-like): the raw estimate jitters with instantaneous speed, so we
    // smooth it and only recompute the absolute arrival clock occasionally — no per-frame churn.
    private var smoothEtaSec = 0.0
    private var etaRoute: com.example.wayfindr.dash.nav.Route? = null   // route the ETA was last based on
    @Volatile private var etaArrivalMs = 0L
    private var lastArrivalCalcMs = 0L
    // The minutes-remaining shown to the rider, refreshed on the same 5 s cadence as the arrival
    // clock so it stops ticking/bouncing every second ("tacky ETA"). null = no active ETA.
    @Volatile private var displayedEtaMin: Int? = null

    // Off-route → reroute, debounced. Now feasible because the app keeps cellular
    // internet while bound to the dash (per-socket binding), so Router can run mid-ride.
    @Volatile private var offRouteSince = 0L
    // Several distinct off-route fixes over a short span before a reroute (not one GPS wobble).
    private val offRouteConfirmation = OffRouteConfirmation()
    @Volatile private var lastRerouteAt = 0L
    @Volatile private var rerouting = false
    @Volatile private var trafficRefreshInFlight = false
    // Bumped by every new route request (initial, reroute, traffic refresh) and every cancel; a
    // result whose generation is no longer current is dropped.
    @Volatile private var routeRequestGeneration = 0L
    private var routeRequestJob: Job? = null
    @Volatile private var lastTrafficRefreshAt = 0L
    @Volatile private var rideDestName: String? = null   // navigation destination during this ride → ride title
    /** "Navigasyonu duraklat": route, stops and destination kept; guidance, rerouting and route alerts off. */
    @Volatile private var navPaused = false
    /** "Navigasyonu bitir" ended the ride: the next one starts with a new route or when riding off. */
    @Volatile private var rideEnded = false
    /** Paused / just ended: the console shows the wallpaper (if chosen) until the rider rides off. */
    @Volatile private var wallHold = false
    @Volatile private var wallHoldAt: Pair<Double, Double>? = null
    @Volatile private var lastTrafficSwitchAt = 0L   // no flip-flop: see TrafficSwitch.MIN_GAP_MS
    // Routes API bill guard (automatic calls only — a destination the rider picks is never blocked).
    @Volatile private var rerouteStreak = 0          // reroutes since last stably back on route → backoff
    @Volatile private var autoRouteCalls = 0         // automatic reroute + traffic calls for this destination
    @Volatile private var autoRouteCapLogged = false
    // Debounce for the "Recalculating" state (ignore 1-frame off-route GPS blips).
    @Volatile private var offRouteStreakStart = 0L
    // Keep "Recalculating" on screen a beat after it clears, so a fast reroute is still seen.
    @Volatile private var recalcHoldUntil = 0L
    // Last lateral distance from the route (m) — logged so flyover/off-route thresholds are tunable.
    @Volatile private var lastSnapDist = -1.0
    // Weak GPS (WeakGps): speed to dead-reckon along the route with, the fix the route progress was
    // last committed from (token + measurement time) and whether this tick ran on dead reckoning.
    private var drSpeedMps = 0.0
    private var progressFixToken = 0L
    private var progressFixNs = 0L
    @Volatile private var deadReckoning = false
    private var drLoggedAt = 0L
    // Rolling average of actual moving speed (m/s), held through stops — drives a STEADY ETA
    // instead of one that swings with instantaneous speed at every brake/light.
    private var smoothSpeedMps = 0.0

    // Map-matched rider position: snapped onto the route while on it (kills the GPS
    // lane/road jitter), raw GPS when genuinely off-route. Drives the marker + camera.
    @Volatile private var matchedLat: Double? = null
    @Volatile private var matchedLng: Double? = null

    // Frame cache (avoid the expensive redraw when nothing changed)
    private var frameBitmap: Bitmap? = null
    private var lastSignature = ""
    // Zoom-level box on the dash: shown for ZOOM_BADGE_MS after each zoom.
    @Volatile private var zoomBadgeUntilMs = 0L
    // Lets joystick input cut the frame loop's sleep short so the change is drawn at once.
    private val frameWake = kotlinx.coroutines.channels.Channel<Unit>(kotlinx.coroutines.channels.Channel.CONFLATED)
    private fun wakeFrameLoop() { frameWake.trySend(Unit) }
    // Recent joystick presses for the optional bottom-centre "DDRR" box.
    private val joyTrail = JoystickTrail()
    private var lastRedrawAt = 0L

    // Ride diagnostics: one-shot "first frame" marker + a throttle for the periodic ride line.
    @Volatile private var loggedFirstFrame = false
    private var lastDiagMs = 0L

    companion object {
        /** Route progress (m) for NavPlus, which reads it from Java. */
        @JvmStatic
        fun nav10Progress(vm: DashViewModel): Double = vm.progressM

        private const val MANUAL_IDLE_MS = 8_000L
        private const val FORCE_REDRAW_MS = 2_000L
        // How long to keep trying to (re)connect before giving up and freeing all background
        // resources. Covers the flaky initial handshake AND a mid-ride drop; if the dash is truly
        // gone (parked / powered off / out of range) we stop draining instead of retrying forever.
        // Once a connection has streamed, a mid-ride drop keeps retrying for up to 6 h (the dash AP
        // may hang until a bike power-cycle, or the rider parks for a coffee): the itinerary and the
        // service stay up, locks are released while waiting (DashKeepAliveService.pauseForReconnect).
        private const val RECONNECT_GIVEUP_MS = 21_600_000L
        // A first connect that never reached STREAMING still gives up after 2 min.
        private const val INITIAL_CONNECT_GIVEUP_MS = 120_000L
        // Alert the rider after this long of sustained (non-transient) link loss — long enough to skip
        // a brief blip that auto-recovers, short enough that they're not riding blind for minutes.
        private const val CONN_LOST_ALERT_MS = 12_000L
        private const val SMOOTH_TAU = 0.20      // camera smoothing time constant (s) — tighter so the map trails the bike less
        // GPS bearing is only meaningful while genuinely moving; below this it's noise that
        // would spin the heading-up map (the "turns to the opposite direction when I stop" bug).
        private const val BEARING_MIN_SPEED = 1.1f   // m/s (~4 km/h)
        // GPS health on the dash. 4 s was too tight next to tall buildings: the chip
        // flipped to "lost" during a slow/multipath lock we still had. 4–12 s is WEAK
        // (last fix still usable); LOST only after 12 s with no accepted update.
        private const val GPS_WEAK_AGE_MS = 4_000L
        private const val GPS_LOST_AGE_MS = 12_000L
        private const val WAYPOINT_ARRIVE_M = 35.0
        private const val WAYPOINT_PROGRESS_TOLERANCE_M = 25.0
        private const val TRAFFIC_REFRESH_MS = 300_000L
        // Route origin quality: a fix within PRECISE_M is used at once; otherwise wait up to WAIT_MS
        // for one, and accept a fallback fix (see NavPlus.fallbackOrigin) when it arrives.
        private const val ROUTE_ORIGIN_PRECISE_M = 60f
        private const val ROUTE_ORIGIN_FALLBACK_M = 100f
        private const val ROUTE_ORIGIN_WAIT_MS = 2_500L
        // Show the dash's "then ..." turn only when it follows the next turn this closely.
        private const val SECONDARY_TURN_MAX_GAP_M = 500.0
        // Routes API bill guard. Reroute waits back off 3 → 6 → 12 → 20 s while the rider stays
        // off-route (or GPS keeps claiming so), and reset after a minute back on route.
        // Hard cap on automatic calls per destination so a bad-GPS ride can't run up a bill.
        private const val REROUTE_BASE_MS = 3_000L
        private const val REROUTE_MAX_MS = 20_000L
        private const val REROUTE_STREAK_RESET_MS = 60_000L
        private const val MAX_AUTO_ROUTE_CALLS = 150
        /** Look-ahead tile warming points, seconds of travel ahead of the rider. */
        private val WARM_AHEAD_S = doubleArrayOf(3.0, 6.0)
        // Display lead and dead-reckoning cap: RiderMotion.LEAD_S / MAX_PREDICT_S (were 0.8 s from
        // when the fix was first seen, + a camera ease; now 0.5 s from the fix's measurement time).
        // Motion-adaptive frame cadence — the heart of the RE-style power/stability model.
        // Measuringthe dash showed it projects the map at just 2–4 fps with two
        // profiles (q2g: 4 fps/200 kbps "frameToSend"=250 ms, and 2 fps/100 kbps =500 ms) and switches
        // between them. CR gates on battery; we gate on MOTION instead (smarter for our screen-off
        // power goal, and the user explicitly didn't want a battery gate): stream the fast profile
        // while the map is actually changing (driving / panning / zooming), drop to the slow profile
        // when it's static. A few fps is plenty for a map AND never overruns the dash decoder — which
        // is what caused the blink/stutter at our old fixed 24 fps. ACTIVE is the knob to raise if
        // joystick panning feels choppy (at the cost of more decoder load).
        // ACTIVE was 250 ms (4 fps, matched) but that 250 ms quantum IS the ~0.25 s position lag
        // ("catch-up") felt while moving. Raised to 100 ms (10 fps) — comfortably under the 12 fps the
        // field log proved stable on this dash, so position tracks ~2.5× tighter with no overrun risk.
        // IDLE stays low: a static map has no lag to fix and 2 fps saves power.
        // Back to 4 fps to MATCH the dash exactly (measured at ~3.7 fps). The
        // 12 fps experiment was smoother but ~3× the 2.4 GHz WiFi load, which stresses the dash AP and
        // its BT coexistence; 4 fps is the proven-stable envelope the dash decoder never overruns.
        // 10 fps again (rider: map moves "kare kare" — at 50 km/h, zoom 19, 4 fps is a ~15 px jump per
        // frame). The WiFi-load worry no longer holds: the encoder is CBR now, so bytes/s stay at the
        // same 200 kbps whatever the frame rate; only the per-frame budget shrinks, and small per-frame
        // motion is exactly what P-frames compress well. Field log showed 12 fps stable on this dash.
        internal const val FRAME_MS_ACTIVE = 100L  // 10 fps
        /**
         * "Akıcı harita" setting: 15 fps (66 ms). FrameBench (CI run 44353212824): at 90 km/h, zoom 18 the map
         * scrolls 5.5 px per frame at 10 fps vs 3.6 px at 15 fps, with p95 step unevenness 1.0 → 0.7 px and
         * render+encode p95 ~15 ms either way. Not yet ridden: the dash was only proven stable to 12 fps.
         */
        internal const val FRAME_MS_SMOOTH = 66L
        private const val FRAME_MS_IDLE   = 100L   // steady cadence while connected; avoids dash idle stalls
        private const val PREVIEW_MS = 250L        // phone preview copy cadence (a full-frame copy per frame is GC churn)
        // Stay on the fast profile this long after the last visible change, so brief pauses (e.g.
        // between joystick nudges, or a GPS fix gap) don't flap the cadence back and forth.
        private const val MOTION_HOLD_MS  = 1_500L
        private const val MAX_AUTH_ATTEMPTS = 4   // the fw 11.63 handshake often needs a couple of tries
        // Dash joystick codes for call control while a call card is showing. UP = answer, DOWN =
        // reject (intuitive accept/decline; verified on the bike that UP is the natural answer press,
        // not IN). The HOME button is left alone — it does the dash's own exit-to-home.
        private const val BTN_ANSWER = 0x06       // joystick UP
        private const val BTN_REJECT = 0x07       // joystick DOWN
        /** Map-mode joystick UP (unconfirmed, see BTN_JOY_DOWN): with no call ringing, UP or this
         *  opens the navigation prompt ("◀ Duraklat · Bitir ▶"). */
        private const val BTN_JOY_UP = 0x12
        /**
         * Codes that open the "◀ Duraklat · Bitir ▶" prompt (no call ringing). V60 on the bike: UP in map
         * mode reaches the FIRMWARE (its own English "Exit navigation" dialog), so neither of these is
         * likely to arrive there; 0x12 is even noted as BACK in TODO.md. Once "Joystick testi" shows
         * which code a usable gesture sends, put it here — this one line is the whole change.
         */
        private val NAV_PROMPT_CODES = intArrayOf(BTN_ANSWER, BTN_JOY_UP)
        /** Joystick test overlay stays this long after each received code. */
        private const val JOY_TEST_SHOW_MS = 3_000L
        /** Joystick CENTRE (IN) click: inside the ▼ music window it plays / pauses. */
        private const val BTN_JOY_CENTRE = 0x18
        /** The UP prompt closes itself ("Vazgeçildi") after this long without input. */
        private const val NAV_PROMPT_MS = 5_000L
        // MAP-mode joystick codes (the dash forwards these directly while projecting, no media-control
        // mode needed) — confirmed in the ride log: 0x13/0x14 with callRinging=false during the map.
        // These give DIRECT zoom like the dash. In/out guess; swap if the ride feels wrong.
        private const val BTN_MAP_ZOOM_IN  = 0x14
        private const val BTN_MAP_ZOOM_OUT = 0x13
        // MEDIA-mode codes (after pressing IN to enter the dash's audio controls): L/R skip tracks on
        // the phone's player, NOT map zoom. Map zoom stays on the direct map-mode codes above.
        private const val BTN_MEDIA_NEXT = 0x09   // media-mode RIGHT → next track
        private const val BTN_MEDIA_PREV = 0x0A   // media-mode LEFT  → previous track
        // PHYSICAL joystick directions in map mode (ride logs + rider: LEFT zooms in by default).
        private const val BTN_JOY_LEFT  = BTN_MAP_ZOOM_IN    // 0x14
        private const val BTN_JOY_RIGHT = BTN_MAP_ZOOM_OUT   // 0x13
        // Map-mode joystick DOWN: 0x15 (rider pressed down ~11× in the 2026-10-16 12:46 ride log;
        // the dash sends one event per press, nothing while held). Map-mode UP is most likely 0x12:
        // seen twice in ride logs and it completes the run 12/13/14/15 = up/right/left/down (unconfirmed).
        // CENTRE click is 0x18 — the dash uses it to enter its own media controls, so we leave it alone.
        private const val BTN_JOY_DOWN = 0x15
        // Destination-list gesture (Settings → "Destination list: double-tap"): press DOWN, or
        // double-tap LEFT or RIGHT (same direction twice within DOUBLE_TAP_MS).
        private const val DOUBLE_TAP_MS = 500L
        private const val ZOOM_BADGE_MS = 3_000L
        private const val BIG_FRAME_BYTES = 30_000
        // DOWN must be pressed twice within this to open the list.
        // 3 s (was 1.5 s): the dash drops about half of quick second presses (2026-10-16 logs: on
        // every miss only one 0x15 arrived), so a lone DOWN shows a hint and waits long enough
        // for a deliberate re-press.
        private const val DOUBLE_DOWN_MS = 3_000L
        private const val PICKER_TIMEOUT_MS = 30_000L
        private const val DASH_MIN_ZOOM = 11
        /** Number of NavPlus quick-action rows (music, zoom, dark map, fuel) on the menu ROOT. */
        private const val JM_ROW_COUNT = 7
        /** Paused / ended: this far from where it happened = ridden off → map back. */
        private const val WALL_HOLD_M = 150.0
        private const val START_ZOOM = 17   // console map and every navigation start (was 16)
        private const val DASH_MAX_ZOOM = 20
        /** How long a dash notice ("Skipped …") stays up. */
        private const val NOTICE_MS = 4_000L
    }

    /** Project a lat/lng forward [distM] metres along [bearingDeg] (great-circle). */
    private fun project(lat: Double, lng: Double, bearingDeg: Double, distM: Double): Pair<Double, Double> {
        val r = 6_371_000.0
        val br = Math.toRadians(bearingDeg)
        val dr = distM / r
        val lat1 = Math.toRadians(lat); val lng1 = Math.toRadians(lng)
        val lat2 = Math.asin(Math.sin(lat1) * Math.cos(dr) + Math.cos(lat1) * Math.sin(dr) * Math.cos(br))
        val lng2 = lng1 + Math.atan2(
            Math.sin(br) * Math.sin(dr) * Math.cos(lat1),
            Math.cos(dr) - Math.sin(lat1) * Math.sin(lat2),
        )
        return Math.toDegrees(lat2) to Math.toDegrees(lng2)
    }

    /** Speed-limit / console notices from NavPlus: feed it the last fix, show what it queued. */
    private fun navplusNotice() {
        try {
            NavPlus.spdTick(fixSpeed, fixLat, fixLng)
            NavPlus.takeNotice()?.let { showNotice(*it) }
        } catch (_: Throwable) {
        }
    }

    /**
     * Where to draw the rider [dist] m ahead. On the route (fix within 12 m of the tracked progress
     * point) the prediction runs along the route line; otherwise [arc] (NavPlus.arcProject) is
     * snapped to the road.
     */
    @Suppress("UNCHECKED_CAST")
    private fun navplusPredict(arc: Pair<*, *>?, dist: Double, routeDist: Double = dist): Pair<Double, Double>? {
        try {
            val r = route
            if (WeakGps.onRoute(lastSnapDist, fixAccuracy.toDouble()) && r != null) {
                val geometry = r.geometry
                val cumulative = r.cumulative
                val progress = progressM
                val here = NavPlus.pointAt(geometry, cumulative, progress)
                if (here != null && GeoPoint.distMeters(here, GeoPoint(fixLat, fixLng)) <= 12.0) {
                    val ahead = NavPlus.pointAt(geometry, cumulative, progress + routeDist)
                    if (ahead != null) {
                        NavPlus.onRoute = true
                        predProgressM = progress + routeDist
                        return ahead.lat to ahead.lng
                    }
                }
            }
        } catch (_: Throwable) {
        }
        NavPlus.onRoute = false
        predProgressM = -1.0
        return NavPlus.roadSnap(arc) as Pair<Double, Double>?
    }

    init {
        // A ride the last process didn't get to save (killed: bike off, battery saver) → save it now
        // if it's over; a fresh one is continued by the next startRecording().
        recorder.journal = rideJournal()
        checkRideJournal()
        // Reflect the rider's stored dash WiFi config (SSID may be blank until discovered).
        mapDark = DarkSchedule.effective(dashConfig.mapDarkAuto.value, dashConfig.mapDarkMode.value)
        _ui.update {
            it.copy(ssid = dashConfig.ssid, wifiPassword = dashConfig.password,
                mapDark = mapDark, mapDarkAuto = dashConfig.mapDarkAuto.value)
        }
        // Effective dark map = clock schedule (auto) or the manual switch. The clock flow sleeps
        // until the next 7 pm / 5 am boundary (two wake-ups a day), so auto flips mid-ride without
        // polling; a setting change applies at once.
        viewModelScope.launch {
            val clock = flow {
                while (true) { emit(Unit); delay(DarkSchedule.msUntilNextChange() + 1_000) }
            }
            combine(dashConfig.mapDarkAuto, dashConfig.mapDarkMode, clock) { auto, manual, _ ->
                auto to DarkSchedule.effective(auto, manual)
            }.distinctUntilChanged().collect { (auto, dark) ->
                if (dark != mapDark) {
                    com.example.wayfindr.data.RideDiagnostics.log("map",
                        "dark map ${if (dark) "on" else "off"} (${if (auto) "auto 7 pm–5 am" else "manual"})")
                }
                mapDark = dark
                lastSignature = ""
                _ui.update { it.copy(mapDark = dark, mapDarkAuto = auto) }
            }
        }

        // When we connect to a previously-unknown dash by prefix, learn + persist its exact
        // SSID so subsequent connects target it directly (no system picker again).
        wifiManager.onSsidResolved = { learned ->
            dashConfig.ssid = learned
            _ui.update { it.copy(ssid = learned) }
        }

        viewModelScope.launch {
            combine(wifiManager.state, session.state) { ws, st ->
                ws.status == WifiConnStatus.CONNECTED || st == DashState.STREAMING
            }.distinctUntilChanged().collect { com.example.wayfindr.util.IdleSleep.setDashLinked(it) }
        }
        viewModelScope.launch {
            com.example.wayfindr.util.IdleSleep.asleep.collect { asleep ->
                if (asleep && session.state.value != DashState.STREAMING) sleepNow()
            }
        }
        // A route request waiting for a usable origin fix: fire it as soon as one arrives.
        viewModelScope.launch {
            location.location.collect { fix ->
                if (waitingForFreshRouteOrigin && fix != null && isFallbackRouteOrigin(fix)) {
                    val lat = destLat ?: return@collect
                    val lng = destLng ?: return@collect
                    waitingForFreshRouteOrigin = false
                    fetchRoute(lat, lng, routeWaypoints)
                }
            }
        }
        viewModelScope.launch {
            wifiManager.state.collect { ws ->
                com.example.wayfindr.data.RideDiagnostics.log(
                    "wifi",
                    ws.status.toString() +
                            (ws.ssid.takeIf { it.isNotBlank() }?.let { " ssid=$it" } ?: "") +
                            (ws.error?.let { " err=$it" } ?: ""),
                )
                // Dash Wi‑Fi dropped mid-ride: close the session bound to the dead network and free
                // the GPS/encoder/locks, but keep the itinerary so the reconnect resumes the trip.
                val wifiConnected = ws.status == WifiConnStatus.CONNECTED
                if (dashWifiWasConnected && !wifiConnected && userWantsConnection) {
                    com.example.wayfindr.data.RideDiagnostics.log("connect",
                        "dash Wi-Fi lost — closing old network-bound session; keeping itinerary for reconnect")
                    session.disconnect()
                    teardown()
                    location.stop()
                    DashKeepAliveService.pauseForReconnect(getApplication())
                    // Invalidate any in-flight route request: a late result from it drops itself.
                    routeRequestGeneration++
                    routeRequestJob?.cancel()
                    routeRequestJob = null
                    rerouting = false
                    trafficRefreshInFlight = false
                    offRouteSince = 0L
                    offRouteConfirmation.reset()
                    _ui.update { it.copy(errorMessage = "Gösterge bağlantısı kesildi; otomatik yeniden bağlanılıyor…") }
                }
                dashWifiWasConnected = wifiConnected
                when (ws.status) {
                    WifiConnStatus.CONNECTED -> {
                        refreshStage()
                        if (userWantsConnection &&
                            session.state.value in listOf(DashState.IDLE, DashState.ERROR)
                        ) {
                            delay(1_200)
                            // Re-check: the user may have hit Disconnect during the delay.
                            if (userWantsConnection &&
                                wifiManager.state.value.status == WifiConnStatus.CONNECTED
                            ) {
                                // Coming back from a drop: re-take the locks + GPS and fetch the
                                // route again if it was lost, then reopen the dash session.
                                DashKeepAliveService.start(getApplication())
                                location.start()
                                val lat = destLat; val lng = destLng
                                if (route == null && lat != null && lng != null) fetchRoute(lat, lng, routeWaypoints)
                                session.connect(_ui.value.ssid, wifiManager.network)
                            }
                        }
                    }
                    WifiConnStatus.ERROR -> {
                        // Dash is gone for good (bike powered off / out of range) → the ride is
                        // over. Save it even though the user never tapped Disconnect.
                        stopRecording()
                        // …and release everything connect() acquired (wake/wifi locks, GPS,
                        // encoder loop). These used to leak when a connection ENDED on failure
                        // rather than via the Disconnect button, draining the battery in the
                        // background until the app was force-closed.
                        userWantsConnection = false
                        releaseBackgroundResources()
                        session.disconnect()   // wifi status stays ERROR → stage remains ERROR
                        _ui.update { it.copy(errorMessage = ws.error) }; refreshStage()
                        com.example.wayfindr.data.RideDiagnostics.stop("wifi error / dash gone")
                    }
                    else -> refreshStage()
                }
            }
        }

        viewModelScope.launch {
            session.state.collect { state ->
                com.example.wayfindr.data.RideDiagnostics.log("session", "→ $state")
                refreshStage()
                // Battery backstop: count down to a full give-up unless/until we're actually
                // streaming. Reaching STREAMING cancels it; any non-streaming state (re)arms it.
                // A connection that has streamed once gets the long mid-ride window; a first connect
                // that never got going keeps the short one.
                if (state == DashState.STREAMING) {
                    hasStreamedThisConnection = true
                    cancelReconnectGiveup(); cancelConnLostAlert()
                } else if (userWantsConnection) {
                    armReconnectGiveup(if (hasStreamedThisConnection) RECONNECT_GIVEUP_MS else INITIAL_CONNECT_GIVEUP_MS)
                    armConnLostAlert()
                }
                when (state) {
                    DashState.READY -> { authAttempts = 0; _ui.update { it.copy(retryAttempt = 0) }; startStream() }
                    DashState.STREAMING -> {
                        authAttempts = 0; _ui.update { it.copy(retryAttempt = 0, errorMessage = null) }
                    }
                    // The handshake to fw 11.63 is flaky — a single failed attempt is normal.
                    // Auto-retry (WiFi is already up) instead of making the rider re-tap Connect.
                    DashState.ERROR -> {
                        if (userWantsConnection &&
                            wifiManager.state.value.status == WifiConnStatus.CONNECTED &&
                            authAttempts < MAX_AUTH_ATTEMPTS
                        ) {
                            authAttempts++
                            _ui.update { it.copy(retryAttempt = authAttempts, errorMessage = null) }
                            delay(1_500)
                            if (userWantsConnection && wifiManager.state.value.status == WifiConnStatus.CONNECTED)
                                session.connect(_ui.value.ssid, wifiManager.network)
                        } else {
                            _ui.update { it.copy(retryAttempt = 0) }
                            // Retries exhausted while the WiFi link is still up → give up fully now
                            // (don't wait out the timer). giveUp() also stops the Wi‑Fi reconnect loop.
                            if (userWantsConnection && authAttempts >= MAX_AUTH_ATTEMPTS &&
                                wifiManager.state.value.status == WifiConnStatus.CONNECTED
                            ) {
                                if (hasStreamedThisConnection) {
                                    // Mid-ride: never give up on the handshake — start a fresh
                                    // round of attempts after a short pause.
                                    authAttempts = 0
                                    delay(3_000)
                                    if (userWantsConnection &&
                                        wifiManager.state.value.status == WifiConnStatus.CONNECTED &&
                                        session.state.value == DashState.ERROR
                                    ) session.connect(_ui.value.ssid, wifiManager.network)
                                } else {
                                    giveUp("auth exhausted after $authAttempts attempts")
                                }
                            }
                        }
                    }
                    else -> {}
                }
            }
        }

        session.onError = { msg ->
            com.example.wayfindr.data.RideDiagnostics.log("error", msg)
            _ui.update { it.copy(errorMessage = msg) }; refreshStage()
        }
        // Joystick handling. While a call card is on the dash, the joystick answers/rejects the call
        // (IN = answer, DOWN = reject) instead of zooming; otherwise RIGHT/LEFT zoom the map. The
        // separate HOME button is intentionally untouched — it does the dash's native exit; its code
        // just falls through to the logged-else branch. Codes verified fw 11.63: IN=0x18, RIGHT=0x09,
        // LEFT=0x0A, DOWN=0x07. Every unmatched code is shown in ui.lastButton so we can retune.
        // "Joystick testi": every event the dash sends (09 00 codes and anything else) shown big on the
        // console for 3 s and logged with what the app was doing. Only shown/logged, never acted on here.
        session.stateLabel = { joyStateLabel() }
        session.onEvent = { type, sub, value ->
            if (dashConfig.joystickTestActive()) {
                val now = System.currentTimeMillis()
                val code = value.lastOrNull()?.toInt()?.and(0xFF)
                val hex = value.joinToString(" ") { "%02X".format(it) }
                joyTestLines = if (type == 0x09 && sub == 0x00 && code != null)
                    listOf("Son tuş: 0x%02X".format(code), joyCodeName(code), "${joyStateShort()}")
                else listOf("Olay: %02X %02X".format(type, sub), hex.take(30).ifBlank { "(boş)" }, "${joyStateShort()}")
                joyTestUntilMs = now + JOY_TEST_SHOW_MS
                val line = "%02X %02X val=[%s] %s | %s".format(
                    type, sub, hex, if (type == 0x09 && sub == 0x00 && code != null) joyCodeName(code) else "olay", joyStateLabel())
                com.example.wayfindr.data.RideDiagnostics.log("joytest", line)
                android.util.Log.i("DashViewModel", "JOYTEST $line")
                lastSignature = ""; wakeFrameLoop()
            }
        }
        viewModelScope.launch { dashConfig.joystickTest.collect { session.rawInputLog = it; if (!it) joyTestLines = null } }
        session.onButton = { btn ->
            val code = btn.toInt() and 0xFF
            when (code) {   // physical directions: 0x15 down, 0x13 right, 0x14 left
                BTN_JOY_DOWN -> 'D'; BTN_JOY_RIGHT -> 'R'; BTN_JOY_LEFT -> 'L'; else -> null
            }?.let { if (dashConfig.showJoystickTrail.value) joyTrail.add(it, System.currentTimeMillis()) }
            val call = com.example.wayfindr.media.CallInfoProvider.incomingCall.value
            val ringing = call?.incoming == true
            val active  = call != null && !call.incoming
            if (ringing && navPromptOpen) closeNavPrompt(null)   // the call has priority
            val pickerLabel = if (!ringing) handleNavPrompt(code) ?: handlePickerInput(code) else null
            val label = pickerLabel ?: when {
                // Ringing: IN answers, DOWN rejects. Active call: DOWN hangs up (fixes "can't end").
                ringing && code == BTN_ANSWER -> { answerCall(call!!); "Call answered" }
                ringing && code == BTN_REJECT -> { endCall(call!!);    "Call rejected" }
                active  && code == BTN_REJECT -> { endCall(call!!);    "Call ended" }
                // Map/media controls run whenever a call ISN'T ringing (still usable during an active
                // call — only DOWN is reserved for hang-up, and that's not a map/media code anyway).
                // "Invert zoom" setting swaps which joystick direction zooms in.
                // ▼ then ▶ / ◀ (within 3 s, menu closed, music playing): next / previous track.
                !ringing && pickerItems == null && System.currentTimeMillis() < musicHintUntilMs &&
                    (code == BTN_JOY_RIGHT || code == BTN_JOY_LEFT) -> {
                    musicHintUntilMs = 0L; downHintUntilMs = 0L
                    if (code == BTN_JOY_RIGHT) { mediaInfo.skipNext(); "Next track (▼▶)" } else { mediaInfo.skipPrevious(); "Previous track (▼◀)" }
                }
                // ▼ then ● (centre): play / pause, same window.
                !ringing && pickerItems == null && System.currentTimeMillis() < musicHintUntilMs && code == BTN_JOY_CENTRE -> {
                    musicHintUntilMs = 0L; downHintUntilMs = 0L
                    toggleMusic()
                }
                !ringing && code == zoomInCode()  -> { zoomIn();  "Zoom in (map)" }
                !ringing && code == zoomOutCode() -> { zoomOut(); "Zoom out (map)" }
                !ringing && code == BTN_MEDIA_NEXT -> { mediaInfo.skipNext(); "Next track" }
                !ringing && code == BTN_MEDIA_PREV -> { mediaInfo.skipPrevious(); "Previous track" }
                code == (DashCommands.EVT_COMPASS.toInt() and 0xFF) -> "Compass (dash)"
                else -> "code 0x${code.toString(16).uppercase()}"
            }
            // Persist every button code so untethered tests are diagnosable from the remote pull
            // (the on-screen readout is invisible with the phone screen off / projecting).
            com.example.wayfindr.data.RideDiagnostics.log("button",
                "0x%02X (ringing=%b active=%b) → %s".format(code, ringing, active, label))
            _ui.update { it.copy(lastButton = label) }
        }
    }

    private fun refreshStage() {
        val wifi = wifiManager.state.value.status
        val dash = session.state.value
        val stage = when {
            dash == DashState.STREAMING -> ConnStage.STREAMING
            dash == DashState.ERROR || wifi == WifiConnStatus.ERROR -> ConnStage.ERROR
            dash == DashState.AUTHENTICATING || dash == DashState.CONNECTING || dash == DashState.READY -> ConnStage.AUTH
            wifi == WifiConnStatus.REQUESTING || wifi == WifiConnStatus.CONNECTED -> ConnStage.WIFI
            else -> ConnStage.OFFLINE
        }
        _ui.update { it.copy(stage = stage) }
    }

    // ── Connection ─────────────────────────────────────────────────────────

    fun connect() {
        userWantsConnection = true
        hasStreamedThisConnection = false
        zoom = START_ZOOM   // every ride starts at z17, whatever was zoomed last time
        authAttempts = 0
        _ui.update { it.copy(errorMessage = null, retryAttempt = 0, needsWifiOn = false) }

        // The dash link needs the Wi‑Fi radio on (WifiNetworkSpecifier joins it as a separate
        // per-app network — the phone's current Wi‑Fi/cellular is untouched). Android 10+ won't
        // let us enable Wi‑Fi for the user, so if it's off, surface it immediately and let the UI
        // open the Wi‑Fi panel — far better than a silent 30s timeout.
        if (!wifiManager.isWifiEnabled()) {
            userWantsConnection = false
            _ui.update { it.copy(stage = ConnStage.ERROR, needsWifiOn = true,
                errorMessage = "Göstergeye bağlanmak için Wi‑Fi'yi açın") }
            return
        }
        com.example.wayfindr.data.RideDiagnostics.init(getApplication())
        com.example.wayfindr.data.RideDiagnostics.start("connect")
        // Stamp the running build's identity into the log so a pulled ride is matched 1:1 to the
        // pushed APK — never again analyse a ride and not know which build produced it.
        com.example.wayfindr.data.RideDiagnostics.log(
            "build", "apk=${com.example.wayfindr.util.BuildId.sha12(getApplication())}" +
                    " vc=${com.example.wayfindr.BuildConfig.VERSION_CODE} name=${com.example.wayfindr.BuildConfig.VERSION_NAME}",
        )
        com.example.wayfindr.data.RideDiagnostics.log(
            "connect", "ssid='${dashConfig.ssid}' needsDiscovery=${dashConfig.needsDiscovery} dest=${_ui.value.destinationName}",
        )
        loggedFirstFrame = false
        DashKeepAliveService.start(getApplication())
        location.start()
        startRecording()

        // Start a lightweight background ticker (4 Hz) to keep the phone map preview up-to-date
        // during the connection handshake. This ensures the rider sees their location immediately
        // while Wi‑Fi and Auth are spinning up.
        tickerJob?.cancel()
        tickerJob = viewModelScope.launch(Dispatchers.Default) {
            while (isActive && session.state.value != DashState.STREAMING) {
                if (!debugSim) tick()   // the weak-GPS simulation runs tick() itself
                delay(250)
            }
        }

        // We must know the EXACT dash SSID before connecting — the dash validates it inside
        // the encrypted auth handshake (DashAuth), and Android redacts the SSID of a network
        // we've already joined. So if we don't have it stored, find it from a WiFi scan.
        if (dashConfig.needsDiscovery) {
            wifiManager.findDashSsid(dashConfig.ssidPrefix)?.let { found ->
                dashConfig.ssid = found
                _ui.update { it.copy(ssid = found) }
            }
        }

        when {
            wifiManager.state.value.status == WifiConnStatus.CONNECTED ->
                session.connect(_ui.value.ssid, wifiManager.network)
            // Known SSID (stored or just found by scan) → exact connect + correct auth.
            dashConfig.ssid.isNotBlank() ->
                wifiManager.connect(dashConfig.ssid, dashConfig.password)
            // Couldn't find it in scan results — fall back to prefix discovery so we at
            // least associate (auth may still need the SSID; a rescan usually fixes it).
            else ->
                wifiManager.connect(dashConfig.ssidPrefix, dashConfig.password, prefixMatch = true)
        }
    }

    fun disconnect() {
        userWantsConnection = false
        cancelReconnectGiveup()
        com.example.wayfindr.data.RideDiagnostics.log("connect", "user disconnect")
        stopRecording()        // a connect→disconnect session = one saved ride
        session.disconnect()
        wifiManager.disconnect()
        releaseBackgroundResources()
        refreshStage()
        com.example.wayfindr.data.RideDiagnostics.stop("disconnect")
    }

    /**
     * Release everything that keeps the CPU/GPS/WiFi awake: the foreground service
     * (PARTIAL_WAKE_LOCK + WifiLock), GPS updates, and the encoder/stream loop. Called
     * whenever the connection has ended so the app stops draining the battery in the
     * background — on an explicit disconnect, on a terminal connection failure (dash
     * unreachable / handshake exhausted), and when the ViewModel is cleared.
     */
    private fun releaseBackgroundResources() {
        teardown()                                   // stream loop + encoder + frame bitmap
        location.stop()                              // stop GPS updates
        DashKeepAliveService.stop(getApplication())  // release wake + wifi locks
    }

    /**
     * Complete stop of all background work — including the Wi‑Fi manager's reconnect loop, which
     * [releaseBackgroundResources] alone does NOT stop (that loop re-joins the dash AP every few
     * seconds and was the main source of drain after the dash went away). The session stays in
     * ERROR so the Dash screen still shows "Couldn't connect" + Try again.
     */
    private fun giveUp(reason: String) {
        cancelReconnectGiveup()
        stopRecording()   // the dash is gone for good: the ride is over (saved, or dropped if trivial)
        userWantsConnection = false
        session.disconnect()
        wifiManager.disconnect()       // stops the indefinite AP reconnect loop
        releaseBackgroundResources()
        refreshStage()
        com.example.wayfindr.data.RideDiagnostics.log("error", "gave up: $reason — background released")
        com.example.wayfindr.data.RideDiagnostics.stop(reason)
    }

    /** App asleep (IdleSleep): stop any connect/reconnect attempt and release GPS, locks, loops. */
    private fun sleepNow() {
        com.example.wayfindr.data.RideDiagnostics.log("sleep", "app asleep — stopping background work")
        if (userWantsConnection) giveUp("app asleep (idle ${com.example.wayfindr.util.IdleSleep.IDLE_MS / 60_000} min)")
        else { wifiManager.disconnect(); releaseBackgroundResources() }
    }

    /** Start the give-up countdown if it isn't already running (idempotent). */
    private fun armReconnectGiveup(timeoutMs: Long) {
        if (giveupJob?.isActive == true) return
        giveupJob = viewModelScope.launch {
            delay(timeoutMs)
            if (session.state.value != DashState.STREAMING) giveUp("reconnect window elapsed")
        }
    }

    private fun cancelReconnectGiveup() { giveupJob?.cancel(); giveupJob = null }

    // Non-silent disconnect alert: this dash-AP-hang failure only recovers after the rider
    // power-cycles the bike, so they must KNOW the link dropped. Fire once after a sustained loss
    // (not a transient blip) via vibration + a BT-routed voice cue, so it carries through the helmet
    // with the phone screen off. Re-armed each non-streaming state; cancelled (and reset) on recovery.
    private fun armConnLostAlert() {
        if (connLostAlertJob?.isActive == true || connLostAlerted) return
        connLostAlertJob = viewModelScope.launch {
            delay(CONN_LOST_ALERT_MS)
            if (session.state.value != DashState.STREAMING && userWantsConnection) {
                connLostAlerted = true
                com.example.wayfindr.data.RideDiagnostics.log("connect", "sustained link loss → rider alert (vibrate + voice)")
                runCatching {
                    val v = getApplication<Application>().getSystemService(android.os.Vibrator::class.java)
                    v?.vibrate(android.os.VibrationEffect.createWaveform(longArrayOf(0, 300, 200, 300, 200, 300), -1))
                }
                voice.alert("Gösterge bağlantısı koptu. Yeniden bağlanmak için motoru kapatıp açın.")
            }
        }
    }

    private fun cancelConnLostAlert() {
        connLostAlertJob?.cancel(); connLostAlertJob = null
        if (connLostAlerted) {
            connLostAlerted = false
            voice.alert("Gösterge bağlantısı yeniden kuruldu")
        }
    }

    // ── Ride recording (the connected session) ───────────────────────────────
    private fun startRecording() {
        if (recorder.isRecording) return
        val navName = routeStops.lastOrNull()?.name?.takeIf { it.isNotBlank() && it != "Paylaşılan konum" }
        val j = rideJournal()
        // Another view model of this process (a second MainActivity) already records into it.
        recorder.journal = if (com.example.wayfindr.data.RideRecorder.isLive(j)) null else j
        val age = if (recorder.journal != null && j.exists()) com.example.wayfindr.data.RideRecorder.journalLastFixMs(j)?.let { System.currentTimeMillis() - it } else null
        if (age != null && age < com.example.wayfindr.data.RideRecorder.JOURNAL_STALE_MS) {
            // The app was killed mid-ride and is back within 30 min: the same ride goes on.
            val name = recorder.resume(j)
            if (name != null) {
                rideDestName = navName ?: name.ifBlank { null }
                com.example.wayfindr.data.RideDiagnostics.log("ride", "resumed from journal (${age / 1000}s since last fix, " +
                    "%.2f km) name=${rideDestName ?: "-"}".format(recorder.distanceSoFarM / 1000.0))
            } else { recoverRideJournal("unreadable"); recorder.start() }
        } else {
            if (age != null) recoverRideJournal("stale journal at start (${age / 60_000} min)")
            rideDestName = navName
            recorder.start()
        }
        recorder.journalName(rideDestName)
        recordJob = viewModelScope.launch {
            location.location.collect { loc ->
                if (loc != null) {
                    val alt = if (loc.hasAltitude() && (!loc.hasVerticalAccuracy() || loc.verticalAccuracyMeters <= 25f)) loc.altitude else Double.NaN
                    recorder.add(loc.latitude, loc.longitude, loc.speed, loc.accuracy, loc.time, alt)
                    // Dash compass heading: GPS direction of travel, only trusted when moving.
                    session.updateGps(loc.bearing.takeIf { loc.hasBearing() && loc.speed >= BEARING_MIN_SPEED })
                }
            }
        }
    }

    /** Ends the recording; true when a ride was saved (a trivial one isn't). */
    private fun stopRecording(): Boolean {
        recordJob?.cancel(); recordJob = null
        if (!recorder.isRecording) return false
        val ride = recorder.stop()   // null = trivial session, don't save
        if (ride == null) {
            com.example.wayfindr.data.RideDiagnostics.log("ride", "dropped: ${recorder.dropReason}")
            android.util.Log.i("DashViewModel", "Ride dropped: ${recorder.dropReason}")
            return false
        }
        saveRide(ride, rideDestName, "saved")
        rideDestName = null
        return true
    }

    private fun saveRide(ride: com.example.wayfindr.data.Ride, name: String?, how: String) {
        repo.addRide(ride)   // self-scoped on the repo (survives ViewModel teardown)
        name?.let { getApplication<android.app.Application>().getSharedPreferences("ride_names", 0).edit().putString(ride.sid, it).apply() }
        android.util.Log.i("DashViewModel", "Ride $how: ${"%.1f".format(ride.distanceKm)} km, ${ride.durationSec}s name=${name ?: "-"}")
        com.example.wayfindr.data.RideDiagnostics.log("ride", "$how ${ride.sid}: ${"%.1f".format(ride.distanceKm)} km name=${name ?: "-"}")
    }

    private fun rideJournal() = java.io.File(getApplication<android.app.Application>().filesDir, "ride_journal.txt")

    /** App start: a journal whose last fix is ≥ 30 min old is a finished ride → save it; a fresh one
     *  waits for startRecording() to continue it, and is saved at the 30-min mark if that never comes. */
    private fun checkRideJournal() {
        val j = rideJournal()
        if (!j.exists()) return
        viewModelScope.launch {
            val last = withContext(Dispatchers.IO) { com.example.wayfindr.data.RideRecorder.journalLastFixMs(j) }
            val age = last?.let { System.currentTimeMillis() - it } ?: Long.MAX_VALUE
            if (age < com.example.wayfindr.data.RideRecorder.JOURNAL_STALE_MS) {
                com.example.wayfindr.data.RideDiagnostics.log("ride", "journal found (${age / 1000}s old) — continued on connect, else saved in ${(com.example.wayfindr.data.RideRecorder.JOURNAL_STALE_MS - age) / 60_000} min")
                delay(com.example.wayfindr.data.RideRecorder.JOURNAL_STALE_MS - age)
            }
            if (!recorder.isRecording && j.exists()) recoverRideJournal("app start, last fix ${age / 60_000} min ago")
        }
    }

    /** Saves (or drops, if trivial) the ride in a left-over journal and deletes it. */
    private fun recoverRideJournal(why: String) {
        val j = rideJournal()
        if (!j.exists() || recorder.isRecording || com.example.wayfindr.data.RideRecorder.isLive(j)) return
        val (ride, name, reason) = com.example.wayfindr.data.RideRecorder.recover(j)
        if (ride != null) saveRide(ride, name, "recovered ($why)")
        else {
            com.example.wayfindr.data.RideDiagnostics.log("ride", "journal dropped ($why): $reason")
            android.util.Log.i("DashViewModel", "Ride journal dropped: $reason")
        }
    }

    // ── Dash WiFi config (Settings) ──────────────────────────────────────────
    fun setSsid(s: String) { dashConfig.ssid = s.trim(); _ui.update { it.copy(ssid = s.trim()) } }
    fun setWifiPassword(p: String) { dashConfig.password = p; _ui.update { it.copy(wifiPassword = p) } }
    /** Forget the paired dash so the next connect rediscovers any RE_* dash by prefix. */
    fun forgetDash() { dashConfig.forgetDash(); _ui.update { it.copy(ssid = "") } }

    // ── Destination + routing ───────────────────────────────────────────────

    fun prefetchTiles(lat: Double, lng: Double) {
        val loc = location.location.value
        tiles.prefetch(lat, lng, loc?.latitude, loc?.longitude)
    }

    /**
     * Start a trip to [stops]. [selectedRoute] is the route the rider already picked on the Route
     * screen: when it still fits this trip (same routing source, sane geometry, ending at the final
     * stop) it is used as-is instead of asking the router again.
     */
    fun setDestinations(
        stops: List<com.example.wayfindr.data.SharedLocation>,
        selectedRoute: Route? = null,
    ) {
        // Invalidate any in-flight route request: a late result from it drops itself.
        routeRequestGeneration++
        routeRequestJob?.cancel()
        routeRequestJob = null
        rerouting = false
        trafficRefreshInFlight = false
        waitingForFreshRouteOrigin = false
        offRouteSince = 0L
        offRouteConfirmation.reset()
        offRouteStreakStart = 0L
        val validStops = stops.filter { it.lat != null && it.lng != null }
        val finalDestination = validStops.lastOrNull()
        val name = finalDestination?.name ?: "Hedef"
        val lat = finalDestination?.lat
        val lng = finalDestination?.lng
        val selectedForTrip = selectedRoute?.takeIf { r ->
            val end = r.geometry.lastOrNull()
            // Any router's route (routing is automatic: Google, OSM or offline BRouter).
            r.geometry.size >= 2 &&
                r.totalMeters.isFinite() && r.totalMeters >= 0.0 &&
                end != null && lat != null && lng != null &&
                GeoPoint.distMeters(end, GeoPoint(lat, lng)) <= 750.0
        }
        routeStops = validStops
        // The ride is named after where it was going (not a reverse-geocoded house number).
        validStops.lastOrNull()?.name?.takeIf { it.isNotBlank() && it != "Paylaşılan konum" }?.let { rideDestName = it; recorder.journalName(it) }
        // Every navigation starts at z17 (START_ZOOM).
        if (validStops.isNotEmpty()) { zoom = START_ZOOM; _ui.update { it.copy(mapZoom = START_ZOOM) } }
        // A new route after "Navigasyonu bitir": guidance runs again and a new ride starts.
        setNavPaused(false); setWallHold(false)
        if (rideEnded) { rideEnded = false; if (userWantsConnection) startRecording() }
        // New destination picked by the rider → fresh Routes API budget and reroute backoff.
        autoRouteCalls = 0; autoRouteCapLogged = false; rerouteStreak = 0; lastRerouteAt = 0L
        lastTrafficSwitchAt = 0L
        activeStopIndex = 0
        routeLegOffset = 0
        routeWaypoints = validStops.dropLast(1).map { GeoPoint(it.lat!!, it.lng!!) }
        _ui.update { it.copy(
            destinationName = name, hasRoute = selectedForTrip != null,
            destLatLng = if (lat != null && lng != null) lat to lng else null,
            routePoints = selectedForTrip?.geometry ?: emptyList(),
            routeTraffic = selectedForTrip?.traffic ?: emptyList(),
        ) }
        destLat = lat
        destLng = lng
        route = selectedForTrip
        // Prepare the same renderer-backed preview used by the physical dash before the Wi-Fi
        // handshake finishes, so DashScreen does not switch to a different map presentation.
        if (frameBitmap == null) {
            frameBitmap = newFrameBitmap()
        }
        lastSignature = ""
        progressM = 0.0; progressFixNs = 0L
        smoothEtaSec = 0.0; etaArrivalMs = 0L; displayedEtaMin = null   // fresh ETA for the new route
        voice.resetTrip()   // fresh announcements for the new route
        routeWeather.newTrip()
        session.updateRouteCard(name)
        location.snappedFeed = false
        if (lat != null && lng != null) googleNav.start(validStops) else googleNav.stop()
        if (lat != null && lng != null) {
            val loc = location.lastKnown()
            tiles.prefetch(lat, lng, loc?.latitude, loc?.longitude)
            if (selectedForTrip != null) {
                lastTrafficRefreshAt = System.currentTimeMillis()
                tiles.prefetchRoute(selectedForTrip.geometry)
                return
            }
            fetchRoute(finalDestination!!.lat!!, finalDestination.lng!!, routeWaypoints)
        }
    }

    /** Backwards-compatible single-destination entry point. */
    fun setDestination(name: String, lat: Double?, lng: Double?) =
        setDestinations(listOf(com.example.wayfindr.data.SharedLocation(name, lat, lng)))

    /** Drop the destination/route → free roam. The map keeps streaming and follows the rider. */
    fun exitNavigation() {
        // Invalidate any in-flight route request: a late result from it drops itself.
        routeRequestGeneration++
        routeRequestJob?.cancel()
        routeRequestJob = null
        rerouting = false
        trafficRefreshInFlight = false
        waitingForFreshRouteOrigin = false
        destLat = null
        destLng = null
        routeWaypoints = emptyList()
        routeStops = emptyList()
        activeStopIndex = 0
        routeLegOffset = 0
        route = null
        progressM = 0.0; progressFixNs = 0L
        offRouteSince = 0L
        offRouteConfirmation.reset()
        panX = 0f; panY = 0f; followMode = true
        smoothEtaSec = 0.0; etaArrivalMs = 0L; displayedEtaMin = null
        setNavPaused(false); setWallHold(false)
        voice.resetTrip()
        googleNav.stop()
        location.snappedFeed = false
        lastSignature = ""   // force a redraw with no route line
        _ui.update { it.copy(
            destinationName = null,
            hasRoute = false,
            destLatLng = null,
            routePoints = emptyList(),
            routeTraffic = emptyList(),
            remainingKm = null,
            etaMinutes = null,
            maneuver = null,
            offRoute = false,
            followMode = true,
        ) }
        session.updateRouteCard("")   // dash card → app name + 0.0 km, nav off
    }

    private fun setNavPaused(on: Boolean) {
        navPaused = on
        _ui.update { it.copy(navPaused = on) }
    }

    /** Wallpaper instead of the map (NavPlus draws it when a photo is chosen) until the rider rides off. */
    private fun setWallHold(on: Boolean) {
        wallHold = on
        wallHoldAt = if (on) location.lastKnown()?.let { it.latitude to it.longitude } else null
        NavPlus.phHold = on
        lastSignature = ""
    }

    /**
     * "Navigasyonu duraklat" (fuel stop): route, stops and destination stay; turn card, voice,
     * rerouting, off-route, traffic switching and the route's curve/rain alerts stop. The ride
     * recording goes on as the same ride.
     */
    fun pauseNavigation() {
        if (!hasDestination() || navPaused) return
        routeRequestGeneration++
        routeRequestJob?.cancel()
        routeRequestJob = null
        rerouting = false
        trafficRefreshInFlight = false
        waitingForFreshRouteOrigin = false
        offRouteSince = 0L
        offRouteConfirmation.reset()
        offRouteStreakStart = 0L
        recalcHoldUntil = 0L
        setNavPaused(true)
        turnCard = null
        googleNav.stop()
        location.snappedFeed = false
        NavPlus.pend1 = null; NavPlus.pend2 = null
        session.updateRouteCard("")   // the dash's own nav widget off while paused
        setWallHold(true)
        _ui.update { it.copy(maneuver = null, offRoute = false, recalculating = false, etaMinutes = null) }
        com.example.wayfindr.data.RideDiagnostics.log("nav", "paused (route kept: ${routeStops.map { it.name }})")
        wakeFrameLoop()
    }

    /** "Navigasyona devam et": guidance again from where the rider is now (progress re-matched, rerouted if off the line). */
    fun resumeNavigation() {
        if (!navPaused) return
        setNavPaused(false)
        setWallHold(false)
        offRouteSince = 0L
        offRouteConfirmation.reset()
        offRouteStreakStart = 0L
        smoothEtaSec = 0.0; etaArrivalMs = 0L; displayedEtaMin = null
        voice.resetTrip()
        val ahead = stopsAhead()
        session.updateRouteCard(ahead.firstOrNull()?.name ?: _ui.value.destinationName.orEmpty())
        val lat = destLat; val lng = destLng
        if (lat != null && lng != null) {
            googleNav.start(ahead)
            if (route == null) fetchRoute(lat, lng, routeWaypoints)
        }
        com.example.wayfindr.data.RideDiagnostics.log("nav", "resumed")
        wakeFrameLoop()
    }

    fun togglePauseNavigation() = if (navPaused) resumeNavigation() else pauseNavigation()

    /** "Navigasyonu bitir": navigation off and the ride stopped + saved (named after the destination). */
    fun endNavigation() {
        val name = rideDestName
        debugLastStops = routeStops.takeIf { it.isNotEmpty() }
        exitNavigation()
        val saved = stopRecording()
        rideEnded = true
        setWallHold(true)
        com.example.wayfindr.data.RideDiagnostics.log("nav", "ended; ride ${if (saved) "saved" else "not saved (too short)"} name=${name ?: "-"}")
        showNotice("Navigasyon bitti", if (saved) "Sürüş kaydedildi" else "Sürüş çok kısa, kaydedilmedi")
        if (navPromptOpen) closeNavPrompt(null)
    }

    /** Paused / ended: the hold lifts once the rider has clearly ridden off; after "bitir" a new ride starts. */
    private fun checkWallHold(loc: android.location.Location?) {
        if (!wallHold || loc == null) return
        val at = wallHoldAt ?: (loc.latitude to loc.longitude).also { wallHoldAt = it }
        if (GeoPoint.distMeters(GeoPoint(at.first, at.second), GeoPoint(loc.latitude, loc.longitude)) < WALL_HOLD_M) return
        wallHold = false
        NavPlus.phHold = false
        lastSignature = ""
        com.example.wayfindr.data.RideDiagnostics.log("nav", "rode off from the stop → map back")
        if (rideEnded && userWantsConnection) viewModelScope.launch {
            if (rideEnded && !recorder.isRecording) { rideEnded = false; startRecording() }
        }
    }

    /**
     * Compute the road route now (while internet is reachable) and cache it. The origin must be a
     * fresh, accurate fix: a stale/coarse one starts the route on the wrong road. Wait briefly for
     * one; if none comes, take a fallback-quality fix, or wait for the next usable fix
     * ([waitingForFreshRouteOrigin], picked up by the location collector in init).
     */
    private fun fetchRoute(destLatV: Double, destLngV: Double, waypoints: List<GeoPoint>) {
        routeRequestGeneration++
        val requestId = routeRequestGeneration
        routeRequestJob?.cancel()
        waitingForFreshRouteOrigin = false
        routeRequestJob = viewModelScope.launch {
            try {
                val origin = location.lastKnown()?.takeIf { isPreciseRouteOrigin(it) }
                    ?: withTimeoutOrNull(ROUTE_ORIGIN_WAIT_MS) {
                        location.location.filterNotNull().first { isPreciseRouteOrigin(it) }
                    }
                    ?: location.lastKnownAny()?.takeIf { isFallbackRouteOrigin(it) }
                if (requestId != routeRequestGeneration || destLat != destLatV || destLng != destLngV) return@launch
                if (origin == null) {
                    waitingForFreshRouteOrigin = true
                    _ui.update { it.copy(errorMessage = "Güncel GPS konumu bekleniyor; rota sinyal alınınca hesaplanacak.") }
                    android.util.Log.w("DashViewModel", "fetchRoute: no recent usable GPS origin; waiting for a new fix")
                    return@launch
                }
                val r = Router.route(getApplication(), GeoPoint(origin.latitude, origin.longitude), GeoPoint(destLatV, destLngV), waypoints)
                if (requestId != routeRequestGeneration || destLat != destLatV || destLng != destLngV) return@launch
                if (r != null) {
                    route = r
                    lastTrafficRefreshAt = System.currentTimeMillis()
                    tiles.prefetchRoute(r.geometry)
                    _ui.update { it.copy(errorMessage = null, hasRoute = true, routePoints = r.geometry, routeTraffic = r.traffic) }
                    android.util.Log.i("DashViewModel", "Route ready: ${r.geometry.size} pts, ${r.totalMeters.toInt()} m")
                } else {
                    _ui.update { it.copy(errorMessage = "Rota bulunamadı. İnternet bağlantınızı ve varış noktasını kontrol edin.") }
                    android.util.Log.w("DashViewModel", "Router returned null")
                }
            } finally {
                if (requestId == routeRequestGeneration) routeRequestJob = null
            }
        }
    }

    /** A fresh fix accurate enough to start a route from without waiting. */
    private fun isPreciseRouteOrigin(loc: android.location.Location): Boolean =
        LocationTracker.isFreshForNavigation(loc) && loc.hasAccuracy() && loc.accuracy <= ROUTE_ORIGIN_PRECISE_M

    /** A fix still good enough to route from when no precise one arrived in time (NavPlus decides). */
    private fun isFallbackRouteOrigin(loc: android.location.Location): Boolean = NavPlus.fallbackOrigin(loc)

    // ── Map controls ────────────────────────────────────────────────────────

    /**
     * Hands-free pause / end: with a route set (or paused), the menu closed and no call ringing, UP
     * opens a prompt; LEFT = pause/resume, RIGHT = end (+ save the ride, as the menu's "Bitir"),
     * DOWN or [NAV_PROMPT_MS] without input = "Vazgeçildi". Returns a log label when it took the
     * code, else null (DOWN keeps its music-hint / double-DOWN-menu meaning when the prompt is closed).
     */
    private fun handleNavPrompt(code: Int): String? {
        if (!navPromptOpen) {
            if (code in NAV_PROMPT_CODES && pickerItems == null && hasDestination()) {
                navPromptOpen = true
                downHintUntilMs = 0L; musicHintUntilMs = 0L
                navPromptJob?.cancel()
                navPromptJob = viewModelScope.launch {
                    delay(NAV_PROMPT_MS)
                    if (navPromptOpen) closeNavPrompt("timeout")
                }
                lastSignature = ""; wakeFrameLoop()
                com.example.wayfindr.data.RideDiagnostics.log("navprompt", "opened (paused=$navPaused)")
                return "UP → navigation prompt"
            }
            return null
        }
        return when (code) {
            BTN_JOY_LEFT -> {
                closeNavPrompt(null)
                if (navPaused) { resumeNavigation(); showNotice("Navigasyon devam ediyor") }
                else { pauseNavigation(); showNotice("Navigasyon duraklatıldı") }
                "Prompt LEFT → navigation ${if (navPaused) "paused" else "resumed"}"
            }
            BTN_JOY_RIGHT -> {
                closeNavPrompt(null)
                com.example.wayfindr.data.RideDiagnostics.log("navprompt", "navigation ended (UP prompt)")
                endNavigation()
                "Prompt RIGHT → navigation ended"
            }
            BTN_JOY_DOWN, BTN_REJECT -> { closeNavPrompt("DOWN"); "Prompt dismissed" }
            else -> "Prompt: ignored 0x%02X".format(code)
        }
    }

    @Volatile private var joyTestLines: List<String>? = null
    @Volatile private var joyTestUntilMs = 0L

    /** What each joystick code is known to be (ride logs, TODO.md:31-33), "?" when unknown. */
    private fun joyCodeName(code: Int): String = when (code) {
        0x06 -> "YUKARI (arama/medya modu)"
        0x07 -> "AŞAĞI (arama/medya modu)"
        0x09 -> "SAĞ (medya modu)"
        0x0A -> "SOL (medya modu)"
        0x12 -> "GERİ? / YUKARI? (doğrulanmadı)"
        0x13 -> "SAĞ (harita)"
        0x14 -> "SOL (harita)"
        0x15 -> "AŞAĞI (harita)"
        0x18 -> "ORTA / IN"
        0x22 -> "Pusula açıldı"
        else -> "? (bilinmeyen)"
    }

    private fun joyStateShort(): String = when {
        com.example.wayfindr.media.CallInfoProvider.incomingCall.value != null -> "arama"
        pickerItems != null -> "menü"
        navPromptOpen -> "istem"
        navPaused -> "duraklatıldı"
        hasDestination() -> "navigasyon"
        else -> "serbest"
    }

    private fun joyStateLabel(): String =
        "dash=${session.state.value} mode=${joyStateShort()} route=${hasDestination()} paused=$navPaused " +
            "prompt=$navPromptOpen menu=${if (pickerItems == null) "closed" else "$menuPage@$pickerIndex"} " +
            "musicHint=${System.currentTimeMillis() < musicHintUntilMs} " +
            "call=${com.example.wayfindr.media.CallInfoProvider.incomingCall.value?.let { if (it.incoming) "ringing" else "active" } ?: "-"} " +
            "music=${(NavPlus.npNow as? com.example.wayfindr.media.NowPlaying)?.let { if (it.playing) "playing" else "paused" } ?: "-"}"

    /** "duraklat" while the track plays, "devam" while it's paused (music hint). */
    private fun musicToggleWord(): String =
        if ((NavPlus.npNow as? com.example.wayfindr.media.NowPlaying)?.playing == false) "devam" else "duraklat"

    /** ▼ then ●: play/pause the phone's player; the fake CI track flips its own flag. */
    private fun toggleMusic(): String {
        var playing = mediaInfo.togglePlayPause()
        if (playing == null && com.example.wayfindr.util.TestHooks.on(getApplication())) {
            (NavPlus.npNow as? com.example.wayfindr.media.NowPlaying)?.let { np ->
                NavPlus.npNow = np.copy(playing = !np.playing); playing = !np.playing
            }
        }
        if (playing == null) return "Play/pause: no player"
        showNotice(if (playing == true) "Müzik devam ediyor" else "Müzik duraklatıldı")
        com.example.wayfindr.data.RideDiagnostics.log("media", "play/pause (▼●) → playing=$playing")
        return "Music ${if (playing == true) "resumed" else "paused"} (▼●)"
    }

    /** Closes the UP prompt; with a [reason] (DOWN / timeout) it says "Vazgeçildi". */
    private fun closeNavPrompt(reason: String?) {
        navPromptOpen = false
        navPromptJob?.cancel(); navPromptJob = null
        if (reason != null) {
            com.example.wayfindr.data.RideDiagnostics.log("navprompt", "dismissed ($reason)")
            showNotice("Vazgeçildi")
        }
        lastSignature = ""; wakeFrameLoop()
    }

    /**
     * Joystick input for the on-dash destination picker. Returns a log label when the event was
     * consumed by the picker (or opened it), or null to let the normal map/media handling run.
     * Runs on the main thread (DashSession posts onButton there).
     */
    private fun handlePickerInput(code: Int): String? {
        val now = System.currentTimeMillis()
        val doubleTapMode = dashConfig.pickerDoubleTap.value
        // Is this the "open / select" gesture? If it follows a plain RIGHT tap closely, that tap's
        // effect (a zoom-out, or a move down the list) gets undone below.
        val g = pickerGesture.onCode(code, now, doubleTapMode)
        if (code == BTN_JOY_DOWN && pickerItems == null && !g.activate) {
            val gap = if (lastLoneDownMs > 0L) "${now - lastLoneDownMs} ms after the previous lone DOWN" else "first"
            com.example.wayfindr.data.RideDiagnostics.log("picker",
                "single DOWN ($gap) — waiting up to ${DOUBLE_DOWN_MS} ms for a second press")
            lastLoneDownMs = now
            if (canOpenPicker()) { downHintUntilMs = now + DOUBLE_DOWN_MS; wakeFrameLoop() }
            // Music playing: ▼ then ▶/◀ within the same window changes track (no menu needed).
            if (NavPlus.phNow() != null) { musicHintUntilMs = now + DOUBLE_DOWN_MS; wakeFrameLoop() }
        }
        if (code == BTN_JOY_DOWN && g.activate) { downHintUntilMs = 0L; musicHintUntilMs = 0L }
        // Plain RIGHT tap: remember the state before it acts, in case it turns out to be the first
        // half of a double-tap (or is followed by a long-press code).
        if (g.plainTap) { zoomBeforeRightTap = zoom }
        val activate = g.activate
        val undoPrevTap = g.undoPrevTap
        val gesture = if (code == BTN_JOY_DOWN) "Double DOWN" else "Double-tap"

        if (pickerItems != null) {
            // Menu open: RIGHT opens/uses the highlighted row, LEFT goes back a page (closes from
            // the first page), DOWN (0x15) moves the highlight down, wrapping and skipping section
            // headers. UP is deliberately unused (rider's choice).
            pickerLastInputMs = now
            pickerInputAtMs = now
            wakeFrameLoop()   // show the move on the very next frame, not at the next 250 ms grid point
            return when (code) {
                BTN_JOY_RIGHT -> "Picker RIGHT → " + selectPickerItem()
                BTN_JOY_LEFT -> if (menuPage != MenuPage.ROOT) {
                    // Back to the row that opened this page.
                    menuPage = MenuPage.ROOT
                    pickerIndex = navPills().size   // the "Uyarılar" row
                    "Picker back → menu"
                } else { closePicker("cancelled (LEFT)"); "Picker cancelled" }
                BTN_JOY_DOWN, BTN_REJECT -> {
                    pickerIndex = nextMenuIndex(menuRows(), pickerIndex); "Picker down → $pickerIndex"
                }
                else -> "Picker: ignored 0x%02X".format(code)
            }
        }
        if (activate) {
            if (!canOpenPicker()) {
                com.example.wayfindr.data.RideDiagnostics.log("picker",
                    "$gesture ignored — not streaming to the dash")
                return null
            }
            if (undoPrevTap && zoom != zoomBeforeRightTap) setZoom(zoomBeforeRightTap)   // undo the tap's zoom
            openPicker()
            wakeFrameLoop()
            return "$gesture → open destination picker"
        }
        return null
    }

    /** Any time we're streaming — with a route set, picking an entry replaces it. (Free-roam-only
     *  blocked the rider's double-tap mid-route on 2026-10-16.) */
    private fun canOpenPicker() = session.state.value == DashState.STREAMING || debugInjecting

    /** "Viraj uyarısı": the spoken sharp-curve warning, or null when off / no curve ahead. */
    private fun curveAlert(w: com.example.wayfindr.dash.nav.CurveWarner, r: Route, progressM: Double, speedMps: Double): String? =
        if (dashConfig.curveWarning.value) w.tick(r, progressM, speedMps) else null

    /**
     * Debug/CI: switches each alert setting off and on and checks that its feature follows
     * (Viraj on a synthetic hairpin, Yağmur through NavPlus' weather alert, Radar ve hız limiti
     * through the speed-limit / camera ticks and the map overlay). Returns one line per check.
     */
    private fun debugToggleCheck(): String {
        val out = StringBuilder()
        val cfg = dashConfig
        val saved = Triple(cfg.curveWarning.value, cfg.rainWarning.value, cfg.offlineSafety.value)
        // Viraj: straight 400 m north, then a hairpin back south.
        val pts = ArrayList<GeoPoint>()
        for (i in 0..40) pts += GeoPoint(39.90 + i * 0.00009, 32.85)
        for (i in 1..20) { val a = Math.PI * i / 20; pts += GeoPoint(39.90 + 40 * 0.00009 + Math.sin(a) * 0.00027, 32.85 + (1 - Math.cos(a)) * 0.00035) }
        for (i in 1..40) pts += GeoPoint(39.90 + 40 * 0.00009 - i * 0.00009, 32.85 + 0.0007)
        val cum = DoubleArray(pts.size).also { c -> for (i in 1 until pts.size) c[i] = c[i - 1] + GeoPoint.distMeters(pts[i - 1], pts[i]) }
        val hairpin = Route(pts, emptyList(), cum.last(), 120.0, cum)
        for (on in listOf(false, true)) {
            cfg.setCurveWarning(on)
            val a = curveAlert(com.example.wayfindr.dash.nav.CurveWarner(), hairpin, 330.0, 15.0)
            out.append("Viraj uyarısı ${if (on) "açık" else "kapalı"} → ${a ?: "uyarı yok"} : ${if ((a != null) == on) "OK" else "HATA"}\n")
        }
        for (on in listOf(false, true)) {
            cfg.setRainWarning(on)
            val a = NavPlus.debugRainCheck()
            out.append("Yağmur uyarısı ${if (on) "açık" else "kapalı"} → ${a ?: "uyarı yok"} : ${if ((a != null) == on) "OK" else "HATA"}\n")
        }
        for (on in listOf(false, true)) {
            cfg.setOfflineSafety(on)
            NavPlus.spdLimit = 0; NavPlus.rdAheadM = -1
            NavPlus.spdNextAt = Long.MAX_VALUE   // no network fetch in this check
            NavPlus.spdLimit = 50; NavPlus.spdAt = android.os.SystemClock.elapsedRealtime()
            NavPlus.spdTick0(20f, 39.92, 32.85)
            NavPlus.rdNextAt = Long.MAX_VALUE; NavPlus.rdAheadM = 300; NavPlus.rdAheadAt = android.os.SystemClock.elapsedRealtime()
            NavPlus.rdTick(20f, 39.92, 32.85)
            val shown = NavPlus.safetyOn() && NavPlus.spdLimit > 0
            out.append("Radar ve hız limiti ${if (on) "açık" else "kapalı"} → limit=${NavPlus.spdLimit} radarPill=${NavPlus.rdAheadM} " +
                "çizim=${if (NavPlus.safetyOn()) "var" else "yok"} : ${if (shown == on) "OK" else "HATA"}\n")
        }
        cfg.setCurveWarning(saved.first); cfg.setRainWarning(saved.second); cfg.setOfflineSafety(saved.third)
        NavPlus.spdNextAt = 0L; NavPlus.rdNextAt = 0L; NavPlus.spdLimit = 0; NavPlus.rdAheadM = -1; NavPlus.pend1 = null; NavPlus.pend2 = null
        return out.toString()
    }

    /** Debug/CI: the weak-GPS simulation drives tick() as if streaming to the dash. */
    @Volatile private var debugSim = false

    /**
     * Debug/CI (TestHooks): rides the current route at 45 km/h with simulated fixes — good, then GPS
     * whose accuracy degrades 50 → 150 m, then coarse network fixes every 3 s with no speed, then
     * 20 s with no fix at all, then good GPS again — through the real fix filter and tick(). Writes
     * per-second figures and the no-freeze checks to files/weakgps.txt, frames to diag/weakgps-*.png.
     */
    private fun runWeakGpsSim(c: android.content.Context) {
        val r = route
        val outFile = java.io.File(c.getExternalFilesDir(null), "weakgps.txt")
        if (r == null || r.totalMeters < 1_500.0 || debugSim) {
            outFile.writeText("HATA: rota yok / kısa (${r?.totalMeters?.toInt()} m) / zaten çalışıyor\n"); return
        }
        debugSim = true
        LocationTracker.debugFeed = true
        googleNav.stop(); location.snappedFeed = false
        setNavPaused(false); setWallHold(false)
        progressM = 0.0; progressFixNs = 0L; drSpeedMps = 0.0; lastSnapDist = -1.0
        if (frameBitmap == null) frameBitmap = newFrameBitmap()
        viewModelScope.launch(Dispatchers.Default) {
            val out = StringBuilder("t(s) faz   doğru_kalan  gösterilen_kalan  dönüşe  işaret_ilerleme  doğru_ilerleme  gps\n")
            val rnd = java.util.Random(7)
            val v = 12.5
            val t0 = android.os.SystemClock.elapsedRealtime()
            var nextFixT = 0.0
            val rows = ArrayList<DoubleArray>()   // t, shown remaining, marker progress
            var lastRow = -1
            val snaps = intArrayOf(8, 20, 32, 45, 55, 66)
            val accepted = HashMap<String, Int>(); val sent = HashMap<String, Int>()
            try {
                while (true) {
                    val t = (android.os.SystemClock.elapsedRealtime() - t0) / 1000.0
                    if (t > 75.0) break
                    val truth = (50.0 + v * t).coerceAtMost(r.totalMeters - 1.0)
                    val phase = when { t < 10 -> "A"; t < 25 -> "B"; t < 40 -> "C"; t < 60 -> "D"; else -> "E" }
                    if (t >= nextFixT && phase != "D") {
                        val acc = when (phase) {
                            "B" -> 50.0 + (t - 10) / 15.0 * 100.0
                            "C" -> 80.0 + rnd.nextDouble() * 70.0
                            else -> 5.0
                        }
                        val noise = if (phase == "A" || phase == "E") 2.0 else acc * 0.4
                        val p = com.example.wayfindr.dash.map.RiderMotion.pointAt(r.geometry, r.cumulative, truth + rnd.nextGaussian() * noise)!!
                        val hdg = com.example.wayfindr.dash.map.RiderMotion.bearingAt(r.geometry, r.cumulative, truth) ?: 0f
                        val side = Math.toRadians(hdg + 90.0); val off = rnd.nextGaussian() * noise
                        val loc = android.location.Location(if (phase == "C") "network" else "gps").apply {
                            latitude = p.lat + off * Math.cos(side) / 111_195.0
                            longitude = p.lng + off * Math.sin(side) / (111_195.0 * Math.cos(Math.toRadians(p.lat)))
                            accuracy = acc.toFloat()
                            time = System.currentTimeMillis()
                            elapsedRealtimeNanos = android.os.SystemClock.elapsedRealtimeNanos()
                            if (phase != "C") { speed = (v + rnd.nextGaussian() * 0.5).toFloat(); bearing = hdg }
                        }
                        location.debugFix(loc)
                        val ok = location.location.value === loc
                        accepted[phase] = (accepted[phase] ?: 0) + if (ok) 1 else 0
                        sent[phase] = (sent[phase] ?: 0) + 1
                        nextFixT += if (phase == "C") 3.0 else 1.0
                    } else if (phase == "D") nextFixT = 60.0
                    tick()
                    val sec = t.toInt()
                    if (sec != lastRow) {
                        lastRow = sec
                        val shown = _ui.value.remainingKm?.let { it * 1000.0 } ?: -1.0
                        val marker = com.example.wayfindr.dash.nav.RouteProgressMatcher.match(
                            r.geometry, r.cumulative, GeoPoint(camLat, camLng), truth, null, v)?.progressMeters ?: -1.0
                        rows += doubleArrayOf(t, shown, marker)
                        out.append("%4d  %s  %9.0f  %12.0f  %8.0f  %12.0f  %12.0f  %s%s\n".format(sec, phase, r.totalMeters - truth, shown,
                            turnCard?.meters ?: -1.0, marker, truth, gpsStatus, if (deadReckoning) " DR" else ""))
                        if (sec in snaps) frameBitmap?.let { bmp ->
                            java.io.File(c.getExternalFilesDir("diag"), "weakgps-%02d.png".format(sec)).outputStream().use { bmp.compress(Bitmap.CompressFormat.PNG, 100, it) }
                        }
                    }
                    delay(100)
                }
                // No freeze: in every 3 s of the weak phases (B, C, D) the shown remaining distance
                // drops and the marker moves on along the route.
                val weak = rows.filter { it[0] >= 11.0 && it[0] < 60.0 }
                var frozen = 0
                for (i in 3 until weak.size) {
                    val a = weak[i - 3]; val b = weak[i]
                    if (a[1] - b[1] < 10.0 || b[2] - a[2] < 10.0) { frozen++; out.append("DONMA ${a[0].toInt()}–${b[0].toInt()} s: kalan ${a[1].toInt()}→${b[1].toInt()} m, işaret ${a[2].toInt()}→${b[2].toInt()} m\n") }
                }
                val maxErr = rows.filter { it[0] >= 11.0 }.maxOfOrNull { kotlin.math.abs(it[2] - (50.0 + v * it[0])) } ?: -1.0
                out.append(if (weak.size >= 40 && frozen == 0) "zayıf GPS'te donma yok: OK\n" else "HATA: zayıf GPS'te donma ($frozen pencere, ${weak.size} satır)\n")
                out.append("işaretin doğru konumdan en büyük sapması (11 s sonrası): %.0f m\n".format(maxErr))
                out.append("kabul edilen konum / gönderilen: " + sent.keys.sorted().joinToString(" ") { "$it ${accepted[it] ?: 0}/${sent[it]}" } + "\n")
            } catch (t: Throwable) {
                out.append("HATA: ${t}\n")
            } finally {
                debugSim = false
                LocationTracker.debugFeed = false
                outFile.writeText(out.toString())
                com.example.wayfindr.data.RideDiagnostics.log("debug", "weak GPS sim:\n$out")
                android.util.Log.i("DashViewModel", "DEBUG weakgps done")
            }
        }
    }

    /** True while a debug-build adb broadcast is feeding a joystick code (see [debugButtonRx]). */
    @Volatile private var debugInjecting = false

    /**
     * Debug builds only: `adb shell am broadcast -a com.wayfindr.DASH_BUTTON -p com.wayfindr.app
     * --ei code 21` feeds a dash joystick code (21 = DOWN 0x15, 19 = RIGHT 0x13, 20 = LEFT 0x14)
     * through the same handler as the bike, so the on-dash menu can be exercised without the dash.
     * `--ez dump true` logs the trip state ([debug] lines in the ride log and logcat).
     * `--ez snap true` draws one dash frame at the last GPS fix into files/diag/snap.png.
     */
    private val debugButtonRx: android.content.BroadcastReceiver? =
        if (!com.example.wayfindr.BuildConfig.DEBUG) null else object : android.content.BroadcastReceiver() {
            override fun onReceive(c: android.content.Context, intent: android.content.Intent) {
                if (!com.example.wayfindr.util.TestHooks.on(c)) return
                // CI: "Joystick testi" on/off (--ez joy_test true|false).
                if (intent.hasExtra("joy_test")) dashConfig.setJoystickTest(intent.getBooleanExtra("joy_test", false))
                val code = intent.getIntExtra("code", -1)
                if (code >= 0) {
                    debugInjecting = true
                    try { session.onButton?.invoke(code.toByte()) } finally { debugInjecting = false }
                    session.onEvent?.invoke(0x09, 0x00, byteArrayOf(0x00, 0x01, code.toByte()))
                }
                // CI: a non-button event as the dash would send it (--ei ev_type 9 --ei ev_sub 1 --es ev_hex "55").
                if (intent.hasExtra("ev_type")) session.onEvent?.invoke(intent.getIntExtra("ev_type", 0), intent.getIntExtra("ev_sub", 0),
                    intent.getStringExtra("ev_hex").orEmpty().chunked(2).mapNotNull { it.toIntOrNull(16)?.toByte() }.toByteArray())
                // Fake now-playing (CI: the music card on the console): --es np_title … --es np_artist …
                intent.getStringExtra("np_title")?.let { t ->
                    val art = Bitmap.createBitmap(96, 96, Bitmap.Config.ARGB_8888).also { b ->
                        val c = Canvas(b)
                        c.drawColor(android.graphics.Color.rgb(200, 60, 40))
                        c.drawCircle(48f, 48f, 30f, android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply { color = android.graphics.Color.rgb(250, 200, 60) })
                    }
                    NavPlus.npNow = com.example.wayfindr.media.NowPlaying(t, "Albüm", intent.getStringExtra("np_artist").orEmpty(), art, true)
                }
                if (intent.getBooleanExtra("np_clear", false)) NavPlus.npNow = null
                // CI: the same stops again after "Bitir" (UP prompt → RIGHT needs a route).
                if (intent.getBooleanExtra("redo_dest", false)) debugLastStops?.let { setDestinations(it) }
                // CI: a ringing / answered / ended call without the dialer (when `adb emu gsm call` gives none).
                intent.getStringExtra("fake_call")?.let { com.example.wayfindr.media.CallInfoProvider.update(com.example.wayfindr.media.IncomingCall(it, true)) }
                if (intent.getBooleanExtra("fake_call_answer", false)) com.example.wayfindr.media.CallInfoProvider.incomingCall.value?.let {
                    com.example.wayfindr.media.CallInfoProvider.update(it.copy(incoming = false)) }
                if (intent.getBooleanExtra("fake_call_end", false)) com.example.wayfindr.media.CallInfoProvider.update(null)
                // CI: a console wallpaper ("Fotoğrafımı konsolda göster") without the photo picker.
                if (intent.getBooleanExtra("test_wallpaper", false)) {
                    val wp = Bitmap.createBitmap(1052, 600, Bitmap.Config.ARGB_8888)
                    Canvas(wp).apply {
                        drawPaint(android.graphics.Paint().apply { shader = android.graphics.LinearGradient(0f, 0f, 1052f, 600f,
                            android.graphics.Color.rgb(30, 90, 140), android.graphics.Color.rgb(200, 120, 40), android.graphics.Shader.TileMode.CLAMP) })
                        drawText("TEST DUVAR KAĞIDI", 526f, 300f, android.graphics.Paint(android.graphics.Paint.ANTI_ALIAS_FLAG).apply {
                            color = android.graphics.Color.WHITE; textSize = 64f; textAlign = android.graphics.Paint.Align.CENTER })
                    }
                    NavPlus.phFile(c).outputStream().use { wp.compress(Bitmap.CompressFormat.JPEG, 90, it) }
                    NavPlus.phSet(c, "ph_on", true)
                    NavPlus.phBmp = wp; NavPlus.phTried = true; NavPlus.phOn = true
                }
                if (intent.getBooleanExtra("clear_wallpaper", false)) NavPlus.phClear(c)
                // CI: ~500 m of riding into the ride recording, so "Navigasyonu bitir" has a ride to save.
                if (intent.getBooleanExtra("fake_ride", false)) {
                    if (!recorder.isRecording) startRecording()
                    val l0 = location.lastKnown()
                    val lat0 = l0?.latitude ?: 39.92; val lng0 = l0?.longitude ?: 32.85
                    // fake_ride_age_min: fixes that long ago (CI: a killed ride whose journal is "over").
                    val t0 = System.currentTimeMillis() - 45_000L - intent.getIntExtra("fake_ride_age_min", 0) * 60_000L
                    for (i in 0..44) recorder.add(lat0 + i * 0.0001, lng0, 11f, 5f, t0 + i * 1_000L)
                }
                intent.getStringExtra("set_safety")?.let { dashConfig.setOfflineSafety(it == "true") }
                intent.getStringExtra("set_mapmus")?.let { NavPlus.phSet(c, "map_mus", it == "true") }
                // Fake weather/fuel pill and speed sign / camera pill for layout frames.
                if (intent.getBooleanExtra("fakehud", false)) {
                    val now = android.os.SystemClock.elapsedRealtime()
                    NavPlus.wxAt = now; NavPlus.wxStartAt = now; NavPlus.wxTemp = 14f; NavPlus.wxCode = 3; NavPlus.wxDay = 1; NavPlus.fuBest = 2300f
                    NavPlus.wxNextAt = Long.MAX_VALUE; NavPlus.fuNextAt = Long.MAX_VALUE
                    NavPlus.spdLimit = 50; NavPlus.spdAt = now; NavPlus.spdShowUntil = now + 60_000
                    NavPlus.rdAheadM = 320; NavPlus.rdAheadAt = now + 60_000
                }
                // CI: weak GPS along the current route (accuracy 50–150 m, network fixes, a 20 s gap).
                // CI (quick run, no offline pack): a 4 km synthetic route from here, as the trip.
                if (intent.getBooleanExtra("synth_route", false)) {
                    location.start()   // quick run never taps "Navigasyonu başlat": the fix filter must be running
                    val l0 = location.lastKnown()
                    val lat0 = l0?.latitude ?: 39.92; val lng0 = l0?.longitude ?: 32.85
                    val pts = ArrayList<GeoPoint>()
                    for (i in 0..400) { val t = i / 400.0; pts += GeoPoint(lat0 + 0.012 * t + 0.002 * Math.sin(t * 6.0), lng0 + 0.04 * t) }
                    val cum = DoubleArray(pts.size).also { cc -> for (i in 1 until pts.size) cc[i] = cc[i - 1] + GeoPoint.distMeters(pts[i - 1], pts[i]) }
                    val end = pts.last()
                    setDestinations(listOf(com.example.wayfindr.data.SharedLocation("Test hedefi", end.lat, end.lng)),
                        Route(pts, emptyList(), cum.last(), cum.last() / 12.5, cum))
                }
                if (intent.getBooleanExtra("weak_gps_sim", false)) runWeakGpsSim(c)
                if (intent.getBooleanExtra("toggles", false)) {
                    val report = debugToggleCheck()
                    android.util.Log.i("DashViewModel", "DEBUG toggles:\n$report")
                    com.example.wayfindr.data.RideDiagnostics.log("debug", "toggles: $report")
                    getApplication<android.app.Application>().getExternalFilesDir(null)?.let { java.io.File(it, "toggles.txt").writeText(report) }
                }
                if (intent.getBooleanExtra("snap", false)) {
                    // Draw one dash frame at the last fix and save it as diag/<snapname>.png (no dash needed).
                    val loc = location.lastKnown()
                    if (frameBitmap == null) frameBitmap = newFrameBitmap()
                    val r = route
                    if (r != null && turnCard == null && NavPlus.navCard && !navPaused) {
                        // Not streaming, so tick() made no turn card: the route's next turn stands in.
                        val m = r.maneuvers.firstOrNull { it.cumulativeMeters > progressM + 1 }
                        if (m != null) turnCard = MapRenderer.TurnCard(m.dashCode, m.cumulativeMeters - progressM, m.instruction, null)
                        if (displayedEtaMin == null) {
                            displayedEtaMin = (r.totalSeconds / 60).toInt()
                            etaArrivalMs = System.currentTimeMillis() + (r.totalSeconds * 1000).toLong()
                        }
                    }
                    if (loc != null) {
                        frameRiderLat = loc.latitude; frameRiderLng = loc.longitude
                        val hdg = r?.geometry?.let { g -> if (g.size >= 2) GeoPoint.bearing(g[0], g[1]).toFloat() else 0f } ?: 0f
                        redrawFrame(loc.latitude, loc.longitude, hdg, hdg, r?.let { it.totalMeters - progressM })
                    }
                    val dir = getApplication<android.app.Application>().getExternalFilesDir("diag")
                    val name = intent.getStringExtra("snapname")?.takeIf { com.example.wayfindr.util.TestHooks.safeName(it) } ?: "snap"
                    frameBitmap?.let { bmp ->
                        java.io.File(dir, "$name.png").outputStream().use { bmp.compress(Bitmap.CompressFormat.PNG, 100, it) }
                    }
                    android.util.Log.i("DashViewModel", "DEBUG snap saved (fix=${loc != null})")
                }
                if (intent.getBooleanExtra("dump", false)) {
                    val r = route
                    val msg = "stops=${routeStops.map { it.name }} active=$activeStopIndex " +
                        "dest=${destLat?.let { "%.5f".format(it) }},${destLng?.let { "%.5f".format(it) }} " +
                        "route=${r?.geometry?.size ?: 0} pts ${r?.totalMeters?.toInt() ?: 0} m " +
                        "end=${r?.geometry?.lastOrNull()?.let { "%.5f,%.5f".format(it.lat, it.lng) }} " +
                        "menu=${if (pickerItems == null) "closed" else "$menuPage@$pickerIndex"} " +
                        "notice=${notice?.takeIf { System.currentTimeMillis() < noticeUntilMs }}" +
                        " zoom=$zoom range=${zoomRange()} map=OSM" +
                        " paused=$navPaused wallHold=$wallHold rideEnded=$rideEnded recording=${recorder.isRecording}" +
                        " ball=[${session.debugBallGlyphs()}] prompt=$navPromptOpen" +
                        " call=${com.example.wayfindr.media.CallInfoProvider.incomingCall.value?.let { "${it.caller}/${if (it.incoming) "ringing" else "active"}" } ?: "-"}" +
                        " music=${(NavPlus.npNow as? com.example.wayfindr.media.NowPlaying)?.let { if (it.playing) "playing" else "paused" } ?: "-"}"
                    android.util.Log.i("DashViewModel", "DEBUG $msg")
                    com.example.wayfindr.data.RideDiagnostics.log("debug", msg)
                }
            }
        }

    init {
        // Declared after the property it registers (init blocks run in declaration order).
        debugButtonRx?.let {
            val filter = android.content.IntentFilter("com.wayfindr.DASH_BUTTON")
            val app = getApplication<android.app.Application>()
            if (android.os.Build.VERSION.SDK_INT >= 33) app.registerReceiver(it, filter, "android.permission.DUMP", null, android.content.Context.RECEIVER_EXPORTED)
            else app.registerReceiver(it, filter, "android.permission.DUMP", null)
        }
    }

    private fun openPicker() {
        viewModelScope.launch {
            // Newest first (the query orders by created_ms DESC), below the "Source" row.
            val saved = withContext(Dispatchers.IO) { runCatching { repo.savedLocations() }.getOrDefault(emptyList()) }
            menuPage = MenuPage.ROOT
            pickerIndex = 0
            pickerLastInputMs = System.currentTimeMillis()
            pickerItems = saved
            com.example.wayfindr.data.RideDiagnostics.log("picker", "opened with ${saved.size} destination(s)")
        }
    }

    private fun closePicker(reason: String) {
        if (pickerItems == null) return
        pickerItems = null
        menuPage = MenuPage.ROOT
        com.example.wayfindr.data.RideDiagnostics.log("picker", "closed: $reason")
    }

    private fun hasDestination() = destLat != null && destLng != null

    /** Stops still ahead, the active one first. */
    private fun stopsAhead() = routeStops.drop(activeStopIndex)

    /** "Cancel next stop" only makes sense with another stop after the one being ridden to. */
    private fun canSkipStop() = hasDestination() && stopsAhead().size >= 2

    /** Rows before the saved destinations on ROOT: the fixed rows plus the NavPlus quick actions. */
    private fun rootFixedRows() = rootFixedRows0() + JM_ROW_COUNT

    /** Fixed rows on ROOT: the two navigation pills (while navigating), "Uyarılar", then "skip next stop". */
    private fun rootFixedRows0() = navPills().size + 1 + (if (canSkipStop()) 1 else 0)

    /** Rows of the menu page showing now; ROOT gets the NavPlus quick actions after its fixed rows. */
    private fun menuRows(): List<MapRenderer.MenuRow> {
        val rows = menuRows0()
        try {
            val fixed = rootFixedRows0()
            if (menuPage == MenuPage.ROOT && rows.size >= fixed) {
                return ArrayList(rows).apply {
                    @Suppress("UNCHECKED_CAST")
                    addAll(fixed, NavPlus.jmRows(this@DashViewModel) as List<MapRenderer.MenuRow>)
                }
            }
        } catch (_: Throwable) {}
        return rows
    }

    private fun menuRows0(): List<MapRenderer.MenuRow> = when (menuPage) {
        MenuPage.ROOT -> navPills().map { MapRenderer.MenuRow(it.first, pill = true) } +
            MapRenderer.MenuRow("Uyarılar", submenu = true) +
            (if (canSkipStop()) listOf(MapRenderer.MenuRow("Sonraki durağı atla: ${stopsAhead().first().name}")) else emptyList()) +
            (pickerItems ?: emptyList()).map { MapRenderer.MenuRow(it.name) }
        // Routing is automatic since V57 (Google → OSM → offline BRouter), so this page only holds
        // the alert switches: the green tick = on; select toggles it.
        MenuPage.SOURCE ->
            listOf(MapRenderer.MenuRow("Uyarılar", header = true)) +
                alertToggles().map { (label, on) -> MapRenderer.MenuRow(label, active = on) }
    }

    /** Alert switches shown on the SOURCE page (label, on). Order matches [toggleAlert]. */
    private fun alertToggles(): List<Pair<String, Boolean>> = listOf(
        "Viraj uyarısı" to dashConfig.curveWarning.value,
        "Yağmur uyarısı" to dashConfig.rainWarning.value,
        "Radar ve hız limiti" to dashConfig.offlineSafety.value,
    )

    private fun toggleAlert(i: Int): String? = when (i) {
        0 -> { val on = !dashConfig.curveWarning.value; dashConfig.setCurveWarning(on); "Viraj uyarısı ${if (on) "açık" else "kapalı"}" }
        1 -> { val on = !dashConfig.rainWarning.value; dashConfig.setRainWarning(on); "Yağmur uyarısı ${if (on) "açık" else "kapalı"}" }
        2 -> { val on = !dashConfig.offlineSafety.value; dashConfig.setOfflineSafety(on); "Radar ve hız limiti ${if (on) "açık" else "kapalı"}" }
        else -> null
    }

    /** The two side-by-side pills at the top of ROOT while a route is set (label, action id);
     *  the cursor steps through them like rows. */
    private fun navPills(): List<Pair<String, String>> = if (!hasDestination()) emptyList() else listOf(
        (if (navPaused) "Devam et" else "Duraklat") to "pause",
        "Bitir" to "end",
    )

    /** Next row the cursor can land on (wrapping, skipping headers). */
    private fun nextMenuIndex(rows: List<MapRenderer.MenuRow>, from: Int): Int {
        var i = from
        repeat(rows.size) {
            i = (i + 1) % rows.size
            if (!rows[i].header) return i
        }
        return from
    }

    /** A NavPlus quick-action row on ROOT runs its action (and closes the menu); else the normal select. */
    private fun selectPickerItem(): String {
        val jm = pickerIndex - rootFixedRows0()
        if (pickerItems == null || menuPage != MenuPage.ROOT || jm < 0 || jm >= JM_ROW_COUNT) return selectPickerItem0()
        val result = NavPlus.jmAct(this, jm)
        closePicker(result)
        return result
    }

    private fun selectPickerItem0(): String {
        val items = pickerItems ?: return "Picker: nothing to select"
        if (menuPage == MenuPage.SOURCE) {
            // Rows: 0 header "Uyarılar", then the alert switches. Stays on the page so the tick updates.
            val msg = toggleAlert(pickerIndex - 1) ?: return "Picker: nothing to select"
            showNotice(msg)
            return "Picker → $msg"
        }
        val nav = navPills()
        if (pickerIndex < nav.size) {
            return when (nav[pickerIndex].second) {
                "pause" -> {
                    val resume = navPaused
                    closePicker(if (resume) "navigation resumed" else "navigation paused")
                    if (resume) { resumeNavigation(); showNotice("Navigasyon devam ediyor") }
                    else pauseNavigation()   // the wallpaper or the "Navigasyon duraklatıldı" pill shows it
                    "Picker → navigation ${if (resume) "resumed" else "paused"}"
                }
                else -> {
                    closePicker("navigation ended")
                    com.example.wayfindr.data.RideDiagnostics.log("picker", "navigation ended (from the dash)")
                    endNavigation()
                    "Picker → navigation ended"
                }
            }
        }
        if (pickerIndex == nav.size) {
            menuPage = MenuPage.SOURCE
            pickerIndex = 1
            return "Picker → Source page"
        }
        if (pickerIndex == nav.size + 1 && canSkipStop()) {
            val ahead = stopsAhead()
            closePicker("skipped stop ${ahead.first().name}")
            com.example.wayfindr.data.RideDiagnostics.log("picker",
                "cancel next stop ${ahead.first().name} → routing to ${ahead.drop(1).first().name} (from the dash)")
            // Re-plan from here through the stops after it (a new route request).
            setDestinations(ahead.drop(1))
            showNotice("Atlandı: ${ahead.first().name}", "→ ${ahead.drop(1).first().name}")
            return "Picker → skipped ${ahead.first().name}"
        }
        val loc = items.getOrNull(pickerIndex - rootFixedRows())
        if (loc == null) { closePicker("cancel selected"); return "Picker cancelled" }
        closePicker("selected ${loc.name}")
        // Same stops the app uses when a saved place is tapped (multi-stop itineraries included).
        setDestinations(loc.waypoints.ifEmpty {
            listOf(com.example.wayfindr.data.SharedLocation(name = loc.name, lat = loc.lat, lng = loc.lng))
        })
        return "Picker → navigate to ${loc.name}"
    }

    private fun zoomInCode()  = if (dashConfig.invertZoom.value) BTN_MAP_ZOOM_OUT else BTN_MAP_ZOOM_IN
    private fun zoomOutCode() = if (dashConfig.invertZoom.value) BTN_MAP_ZOOM_IN else BTN_MAP_ZOOM_OUT
    fun zoomIn()  = setZoom(zoom + 1)
    fun zoomOut() = setZoom(zoom - 1)
    /** Dash zoom range: 11–20. */
    private fun zoomRange(): IntRange = DASH_MIN_ZOOM..DASH_MAX_ZOOM

    private fun setZoom(z: Int) {
        val clamped = z.coerceIn(zoomRange())
        if (clamped == zoom) return
        zoom = clamped
        _ui.update { it.copy(mapZoom = zoom) }
        // Make zoom feel instant: force a redraw on the very next tick (the renderer bridges the
        // gap with scaled neighbouring-zoom tiles), and warm the real tiles for the new level so
        // the scaled view sharpens in quickly.
        lastSignature = ""
        zoomBadgeUntilMs = System.currentTimeMillis() + ZOOM_BADGE_MS
        wakeFrameLoop()
        // No forced IDR here any more: zoom keyframes came out 65–100 KB (ride 2026-10-16), i.e.
        // 3–4 s of the dash's ~25 KB/s video budget in one burst, and the new view took seconds
        // to appear. An ordinary frame stays in budget; the 1 s periodic IDR sharpens it after.
        zoomPressedAtMs = System.currentTimeMillis()
        if (camInit) tiles.prefetchZoom(camLat, camLng, zoom)
    }
    fun panBy(dx: Float, dy: Float) = manualPan(dx, dy)
    fun recenter() {
        panX = 0f; panY = 0f; followMode = true
        _ui.update { it.copy(followMode = true) }
    }
    fun toggleHeadingUp() {
        headingUp = !headingUp
        _ui.update { it.copy(headingUp = headingUp) }
    }

    fun setMapDark(dark: Boolean) { dashConfig.setMapDarkMode(dark); dashConfig.setMapDarkAuto(false) }
    fun setMapDarkAuto(auto: Boolean) = dashConfig.setMapDarkAuto(auto)

    private fun manualPan(dx: Float, dy: Float) {
        panX += dx; panY += dy
        followMode = false
        lastManualPanAt = System.currentTimeMillis()
        _ui.update { it.copy(followMode = false) }
    }

    // ── Video + nav loop ────────────────────────────────────────────────────

    /** Frame interval / encoder fps for this stream ("Akıcı harita" read once per stream start). */
    @Volatile private var streamFrameMs = FRAME_MS_ACTIVE
    private val streamFps get() = (1000L / streamFrameMs).toInt()

    private fun startStream() {
        streamFrameMs = if (dashConfig.smoothMap.value) FRAME_MS_SMOOTH else FRAME_MS_ACTIVE
        session.projectionHbMs = streamFrameMs
        com.example.wayfindr.data.RideDiagnostics.log("stream", "frame interval ${streamFrameMs} ms (${streamFps} fps)")
        com.example.wayfindr.data.RideDiagnostics.log("stream", "startStream — encoder up, RTP→dash beginning")
        videoPtsMs = 0L
        val packetizer = RtpPacketizer { rtpPkt -> session.sendRtp(rtpPkt) }
        val nalProc    = NalProcessor { nal, _ ->
            // Even, monotonic presentation timestamp (set per frame by the send loop) instead of
            // wall clock — so the dash sees a clean, evenly-spaced timeline regardless of tiny
            // send-time wobble. All NALs of one frame share the same value.
            packetizer.packetize(nal, endOfAU = true, wallClockMs = videoPtsMs)
        }
        val onEncoded: (ByteArray, Boolean) -> Unit = { annexB, isKey ->
            nalProc.process(annexB)
            // First encoded frame leaving the phone — its delay after READY is the prime suspect
            // for the dash's "Timeout!" (the dash waits for video and gives up if it's late).
            if (!loggedFirstFrame) {
                loggedFirstFrame = true
                com.example.wayfindr.data.RideDiagnostics.log("stream", "first video frame sent (key=$isKey, ${annexB.size}B)")
            }
            if (annexB.size > BIG_FRAME_BYTES) com.example.wayfindr.data.RideDiagnostics.log("stream",
                "big frame: ${annexB.size / 1024} KB (key=$isKey) — budget is ~6 KB/frame")
            val pickAt = pickerInputAtMs
            if (pickAt > 0L) {
                pickerInputAtMs = 0L
                com.example.wayfindr.data.RideDiagnostics.log("picker",
                    "list change → frame sent in ${System.currentTimeMillis() - pickAt} ms (key=$isKey, ${annexB.size}B)")
            }
            val zoomAt = zoomPressedAtMs
            if (zoomAt > 0L) {
                zoomPressedAtMs = 0L
                com.example.wayfindr.data.RideDiagnostics.log("button",
                    "zoom → first frame sent in ${System.currentTimeMillis() - zoomAt} ms (key=$isKey, ${annexB.size}B)")
            }
            // Atomic update: this runs on the encoder's callback thread, concurrent with the
            // frame loop's _ui writes — a plain copy() read-modify-write would drop updates.
            _ui.update { it.copy(frameCount = it.frameCount + 1) }
        }
        encoder?.release()
        encoder = DashEncoder(onEncoded).also { it.prepare(streamFps) }

        // Handover from the handshake ticker to the high-performance stream loop.
        tickerJob?.cancel(); tickerJob = null
        frameBitmap = newFrameBitmap()
        lastSignature = ""
        // Fresh camera so it snaps to the first fix instead of gliding from a stale spot.
        camInit = false; lastTickNs = 0L; lastFixTime = 0L; heldRider = null; riderMotion.reset(); motionMode = 0

        session.startStreaming()
        startMediaForwarding()
        location.location.value?.let { tiles.prefetch(it.latitude, it.longitude) }

        streamJob = viewModelScope.launch(Dispatchers.Default) {
            var lastPrefetch = 0L
            var failures = 0
            // Motion-adaptive, absolute-grid pacing (RE-style). Each iteration tick() tells us whether
            // the map actually changed; we hold the fast profile (FRAME_MS_ACTIVE) for MOTION_HOLD_MS
            // after the last change, then settle to the slow profile (FRAME_MS_IDLE). The grid sleeps
            // only the REMAINDER to the next frame so render+encode work doesn't make the cadence
            // wobble — uneven arrival is what makes the dash decoder jitter/teleport. videoPtsMs
            // advances by the interval actually used so RTP timestamps stay proportional to real time.
            var lastChangeMs = System.currentTimeMillis()
            var idleBitrate = false   // track the live profile so we only poke the encoder on a switch
            var liveBitrate = DashEncoder.BITRATE   // what the encoder was last configured/asked for
            var bitrateEncoder: DashEncoder? = null // the encoder that bitrate applies to (rebuilt on errors)
            var frameMs = FRAME_MS_ACTIVE
            var nextFrameMs = System.currentTimeMillis()
            var lastSendMs = System.currentTimeMillis()
            // The loop must NEVER die silently: the session's heartbeats keep the dash
            // connected, so a dead frame loop = frozen map with the connection "up".
            while (isActive && session.state.value == DashState.STREAMING) {
                try {
                    val tickStartNs = System.nanoTime()
                    val changed = tick()
                    val encStartNs = System.nanoTime()
                    tickMsAvg += ((encStartNs - tickStartNs) / 1e6 - tickMsAvg) * 0.1
                    val nowMs = System.currentTimeMillis()
                    if (changed) lastChangeMs = nowMs
                    // Keep a steady connected-dash cadence. The dash can stop advancing its
                    // projection when the sender switches to the idle profile at standstill.
                    val active = true
                    frameMs = streamFrameMs
                    val bmp = frameBitmap
                    val enc = encoder
                    if (bmp != null && enc != null) {
                        // Bitrate follows the "Higher video quality" setting; poke the encoder only on change.
                        val wantBitrate = if (dashConfig.highQualityVideo.value) DashEncoder.BITRATE_HQ else DashEncoder.BITRATE
                        if (idleBitrate || enc !== bitrateEncoder || wantBitrate != liveBitrate) {
                            enc.requestBitrate(wantBitrate)
                            liveBitrate = wantBitrate; bitrateEncoder = enc
                            idleBitrate = false
                            com.example.wayfindr.data.RideDiagnostics.log("stream", "video bitrate → ${wantBitrate / 1000} kbps")
                        }
                        // Scales a 2× "sharper map" frame down to 526×300 (plain copy at 1×).
                        enc.renderBitmap(bmp)
                        // Advance the presentation clock by THIS frame's interval BEFORE draining, so the
                        // frame(s) pulled this iteration carry an evenly-spaced RTP timestamp.
                        videoPtsMs += frameMs
                        enc.drain()
                        encodeMsAvg += ((System.nanoTime() - encStartNs) / 1e6 - encodeMsAvg) * 0.1
                    }
                    failures = 0
                    // Track the real send cadence (EMA + worst) so the ride log proves it's even.
                    val sendNow = System.currentTimeMillis()
                    val gap = sendNow - lastSendMs
                    lastSendMs = sendNow
                    sendPaceMs = if (sendPaceMs <= 0.0) gap.toDouble() else sendPaceMs + (gap - sendPaceMs) * 0.1
                    if (gap > sendPaceMaxMs) sendPaceMaxMs = gap
                    // Warm the tile cache ahead of the rider every ~20 s.
                    if (sendNow - lastPrefetch > 20_000) {
                        lastPrefetch = sendNow
                        location.location.value?.let { tiles.prefetch(it.latitude, it.longitude) }
                    }
                } catch (e: CancellationException) {
                    throw e
                } catch (e: Exception) {
                    failures++
                    android.util.Log.e("DashViewModel", "Frame loop error #$failures", e)
                    if (failures >= 3 && isActive) {   // never rebuild after teardown cancelled us
                        // MediaCodec is in an error state — rebuild the encoder so the
                        // stream recovers. The fresh encoder re-emits SPS/PPS, which the
                        // NAL processor bundles into the next IDR for the dash decoder.
                        runCatching { encoder?.release() }
                        encoder = runCatching { DashEncoder(onEncoded).also { it.prepare(streamFps) } }
                            .onFailure { android.util.Log.e("DashViewModel", "Encoder rebuild failed", it) }
                            .getOrNull()
                        lastSignature = "" // force a full redraw on the next tick
                        failures = 0
                    }
                }
                // Sleep only the remaining time to the next grid point.
                nextFrameMs += frameMs
                val sleepMs = nextFrameMs - System.currentTimeMillis()
                if (sleepMs > 1) {
                    // Joystick input wakes us early; re-anchor the grid so the next frame follows.
                    if (kotlinx.coroutines.withTimeoutOrNull(sleepMs) { frameWake.receive() } != null)
                        nextFrameMs = System.currentTimeMillis()
                } else if (sleepMs < -frameMs) {
                    // Fell more than a frame behind (render spike) — re-anchor so we don't fire a
                    // burst of catch-up frames (which would itself look like a jump).
                    nextFrameMs = System.currentTimeMillis()
                }
            }
        }
    }

    /**
     * Compute nav state, push nav-info to the dash, and redraw the frame only if it changed.
     * @return true if the visible map content changed this tick (drives the motion-adaptive cadence).
     */
    private fun tick(): Boolean {
        navplusNotice()
        // Revert to follow mode after the rider stops nudging the joystick.
        if (!followMode && System.currentTimeMillis() - lastManualPanAt > MANUAL_IDLE_MS) {
            panX = 0f; panY = 0f; followMode = true
            _ui.update { it.copy(followMode = true) }
        }

        val loc = location.location.value
        val r = route
        val dLat = destLat; val dLng = destLng

        if (r !== alertsRoute) {   // new/rerouted route: refresh the offline corridor + rain check
            alertsRoute = r; curveWarner.reset(); routeWeather.reset()
            if (r != null) com.example.wayfindr.dash.nav.OfflineSafetyCache.prepare(getApplication<android.app.Application>(), r)
        }

        val accuracy = loc?.takeIf { it.hasAccuracy() }?.accuracy?.toDouble()
        // GPS bearing is trusted only while moving AND with a reasonably precise fix.
        val headingKnown = loc != null && loc.hasBearing() && loc.speed >= BEARING_MIN_SPEED &&
            (accuracy == null || accuracy <= 35.0)

        // Default to raw GPS; map-matching below snaps it onto the route when on it.
        matchedLat = loc?.latitude
        matchedLng = loc?.longitude

        var remainingM: Double? = null
        var etaSec: Double? = null
        // 1. Calculate the TARGET heading (where we want the camera to point).
        // Priority: GPS bearing (if moving) > Route orientation > Last camera heading.
        var targetHeading = when {
            headingKnown -> normalizeHeading(loc!!.bearing)
            camInit -> normalizeHeading(camHdg)
            else -> 0f
        }

        var offRoute = false
        var recalculating = false
        // Navigation state (progress, waypoints, off-route, ETA, dash nav card, voice) only advances
        // while streaming to the dash; the phone preview before/without a dash just shows the map.
        // Paused ("Navigasyonu duraklat"): the map keeps following, but no guidance of any kind.
        val streaming = (userWantsConnection && session.state.value == DashState.STREAMING || debugSim) && !navPaused
        checkWallHold(loc)

        // Navigation SDK turn-by-turn while its guidance runs (null = our own route data).
        val navInfo = com.example.wayfindr.dash.nav.GoogleNav.navInfo?.takeIf { googleNav.active }
        // Card stays through a stale GPS tick (like the dash's widget); gone with the route or the link.
        if (r == null || !streaming) turnCard = null
        val fixFresh = loc != null && LocationTracker.isFreshForNavigation(loc)
        // Seconds since the fix the progress was committed from (elapsedRealtime clock).
        val progressFixAgeS = if (progressFixNs > 0L)
            (android.os.SystemClock.elapsedRealtimeNanos() - progressFixNs) / 1e9 else Double.MAX_VALUE
        // Weak GPS: with no fresh fix (gap) keep guiding along the route at the last speed for up to
        // WeakGps.MAX_S, instead of freezing the marker, distance and turn countdown.
        val drOnly = !fixFresh && streaming && navInfo == null && r != null && loc != null &&
            WeakGps.onRoute(lastSnapDist, fixAccuracy.toDouble()) &&
            WeakGps.canDeadReckon(drSpeedMps, progressFixAgeS)
        deadReckoning = false
        if (r != null && loc != null && (fixFresh || drOnly)) {
            val ns = (if (drOnly) navStateAt(r, progressM + WeakGps.extraMeters(drSpeedMps, progressFixAgeS))
                else trackProgress(
                    r, GeoPoint(loc.latitude, loc.longitude),
                    gpsBearing = if (headingKnown) loc.bearing else null,
                    speedMps = if (loc.hasSpeed()) loc.speed.toDouble() else 0.0,
                    advanceProgress = streaming,
                    fix = loc,
                ).let { base ->
                    // Between fixes (> WeakGps.GAP_S, e.g. one fix per 5 s): distance and turn keep counting down.
                    val ageNow = if (progressFixNs > 0L) (android.os.SystemClock.elapsedRealtimeNanos() - progressFixNs) / 1e9 else 0.0
                    val extra = if (streaming && navInfo == null && WeakGps.onRoute(base.snapDist, accuracy))
                        WeakGps.extraMeters(drSpeedMps, ageNow) else 0.0
                    if (extra > 0.0) navStateAt(r, base.progressM + extra).copy(snapDist = base.snapDist) else base
                }).let { base -> navInfo?.let { withNavInfo(base, it) } ?: base }
            deadReckoning = drOnly || ns.progressM > progressM + 0.5
            if (deadReckoning && System.currentTimeMillis() - drLoggedAt > 5_000) {
                drLoggedAt = System.currentTimeMillis()
                com.example.wayfindr.data.RideDiagnostics.log("gps", "weak GPS: route dead reckoning " +
                    "%.0f m ahead of the last fix (%.1f s old, %.1f m/s, acc %.0f m)".format(
                        ns.progressM - progressM, progressFixAgeS, drSpeedMps, fixAccuracy))
            }
            // If GPS bearing is weak or we are at low speed, use the route's current
            // segment bearing as the target so the map points the right way immediately.
            if (!headingKnown) {
                targetHeading = normalizeHeading(ns.heading)
            }
            // With the SDK, its ArrivalListener moves to the next stop instead.
            if (streaming && navInfo == null && !deadReckoning && advanceWaypointIfReached(loc, ns)) {
                // The route geometry already continues to the next leg; reset the displayed ETA
                // immediately while the tracker carries on from the same cumulative progress.
                smoothEtaSec = 0.0; etaArrivalMs = 0L; displayedEtaMin = null
            }
            // Keep the dash distance/ETA for the complete itinerary. Waypoint progression still
            // updates maneuver guidance, but the rider's total remaining distance must not reset
            // to the current leg after a multi-stop route is loaded.
            remainingM = ns.remainingM
            // Keep the visible marker on the rendered route when GPS is close enough to make
            // that match trustworthy. Once outside this band, retain raw GPS so a parallel road
            // is not falsely pulled onto the route.
            // Poor fixes too (band grows with their accuracy, WeakGps.snapBand), so a weak signal keeps
            // the marker on the line instead of dropping it beside the road.
            if (WeakGps.onRoute(ns.snapDist, accuracy)) {
                matchedLat = ns.snapped.lat
                matchedLng = ns.snapped.lng
            }
            // Heading-aware off-route. A flyover (or its service road) runs PARALLEL to and almost
            // on top of the route, so flat GPS can sit well past the lateral threshold while the
            // rider is still effectively on the line. Distance alone then cries "off-route" and
            // triggers a sudden reroute. So in the ambiguous band we require the heading to ALSO
            // disagree (a genuine divergence / wrong turn); a clearly different road (very far) is
            // off-route regardless. This is the main flyover-confusion mitigation.
            lastSnapDist = ns.snapDist
            val headingOff = headingKnown && angleDelta(loc.bearing, ns.heading) > 50f
            // Clearly a different road → off; in the parallel/flyover band only if the heading
            // also diverges; and never on a fix too coarse to tell (NavigationReliability).
            // The Navigation SDK reroutes by itself: our off-route/reroute stays off while it guides.
            offRoute = streaming && navInfo == null && !drOnly && com.example.wayfindr.dash.map.NavigationReliability
                .isConfidentlyOffRoute(ns.snapDist, accuracy, headingKnown, headingOff)
            val nowMs = System.currentTimeMillis()

            // "Recalculating" = off the line for >2 s (ignores brief GPS blips), OR a reroute
            // request is in flight. While true the distance is measured against a route we've left,
            // so the ETA is meaningless — we FREEZE it (and the pill says "Recalculating") rather
            // than let the number lurch until the fresh route lands. recalcHoldUntil keeps the pill
            // up a beat after the new route lands so a fast reroute is still visible to the rider.
            if (offRoute) { if (offRouteStreakStart == 0L) offRouteStreakStart = nowMs }
            else offRouteStreakStart = 0L
            val rawRecalc = streaming && (rerouting || (offRouteStreakStart != 0L && nowMs - offRouteStreakStart > 2_000) ||
                navInfo?.navState == com.google.android.libraries.mapsplatform.turnbyturn.model.NavState.REROUTING)
            if (rawRecalc) recalcHoldUntil = nowMs + 1_500
            recalculating = streaming && (rawRecalc || nowMs < recalcHoldUntil)

            if (streaming && !recalculating) {
                // ETA from GOOGLE's traffic-aware duration for the part of the route still ahead
                // (route.totalSeconds × remaining / total; the 2-min traffic refresh keeps it fresh).
                // It used to be remaining ÷ our own rolling speed, floored at 3 m/s before we'd
                // moved — Udupi (~400 km) read 37 h against Google's 7 h 37 m.
                if (loc.speed > 0.5f) {
                    smoothSpeedMps = if (smoothSpeedMps <= 0.0) loc.speed.toDouble()
                    else smoothSpeedMps + (loc.speed - smoothSpeedMps) * 0.05
                }
                val rawEta = navInfo?.timeToFinalDestinationSeconds?.toDouble()
                    ?: if (r.totalMeters > 0.0 && r.totalSeconds > 0.0)
                    r.totalSeconds * (ns.remainingM / r.totalMeters).coerceIn(0.0, 1.0)
                else ns.remainingM / smoothSpeedMps.coerceAtLeast(8.0)   // no duration from Google
                smoothEtaSec = if (smoothEtaSec <= 0.0 || r !== etaRoute) rawEta
                    else smoothEtaSec + (rawEta - smoothEtaSec) * 0.08
                if (r !== etaRoute) { etaRoute = r; etaArrivalMs = 0L }   // refresh the shown value now
                // Refresh the displayed minutes + arrival clock only every 5 s so they read steady
                // (like Google Maps) instead of flickering every second.
                if (etaArrivalMs == 0L || nowMs - lastArrivalCalcMs > 5_000) {
                    etaArrivalMs = nowMs + (smoothEtaSec * 1000).toLong()
                    displayedEtaMin = Math.round(smoothEtaSec / 60.0).toInt()
                    lastArrivalCalcMs = nowMs
                }
            }
            etaSec = if (smoothEtaSec > 0.0) smoothEtaSec else null
            // Feed the dash's own turn-by-turn widget: the next turn's glyph, distance to it,
            // total remaining and the (held) arrival time. No turn left → destination flag.
            val (pv, pu) = toDashDistance(ns.nextTurnM)
            val (tv, tu) = toDashDistance(ns.remainingM)
            val etaHHMM = etaSec?.let {
                val arrival = java.util.Calendar.getInstance().apply { add(java.util.Calendar.SECOND, it.toInt()) }
                "%02d%02d".format(arrival.get(java.util.Calendar.HOUR_OF_DAY), arrival.get(java.util.Calendar.MINUTE))
            }
            val glyph = ns.nextManeuver?.dashCode ?: com.example.wayfindr.dash.nav.DashGlyph.DESTINATION
            // "Then ..." turn: only for back-to-back junctions (the turn after the next one comes
            // within SECONDARY_TURN_MAX_GAP_M), like Google Maps' secondary instruction. The
            // distance sent is the gap between the two turns. Otherwise "none".
            val next = ns.nextManeuver
            val second = ns.secondManeuver
            val gap = if (next != null && second != null) second.cumulativeMeters - next.cumulativeMeters else null
            val (secGlyph, secDist, secUnit) = if (second != null && gap != null && gap <= SECONDARY_TURN_MAX_GAP_M) {
                val (sv, su) = toDashDistance(gap)
                Triple(second.dashCode, sv, su)
            } else Triple(DashCommands.NAV_SECONDARY_NONE, 0, DashCommands.NAV_UNIT_METERS)
            // Turn card on: the frame shows the turn, so the dash gets no glyph and drops its round widget.
            val card = NavPlus.navCard
            turnCard = if (streaming && card) MapRenderer.TurnCard(glyph, ns.nextTurnM, next?.instruction,
                secGlyph.takeIf { it != DashCommands.NAV_SECONDARY_NONE }) else null
            if (streaming) session.updateNavInfo(
                if (card) DashCommands.NAV_MANEUVER_NONE else glyph, pv, pu, tv, tu, etaHHMM,
                if (card) DashCommands.NAV_SECONDARY_NONE else secGlyph, secDist, secUnit,
            )
            // Spoken/chime turn guidance (no-op when voice mode is OFF).
            // Voice output must never be allowed to cancel the navigation ticker. TTS providers
            // can throw during initialization or audio-route changes while the map must continue.
            if (streaming) runCatching {
                curveAlert(curveWarner, r, ns.progressM, loc.speed.toDouble())?.let { voice.alert(it) }
                if (dashConfig.rainWarning.value) routeWeather.tick(r, ns.progressM)?.let { voice.alert("$it, dikkatli sürüşler"); NavPlus.pend1 = "Yağmur olabilir"; NavPlus.pend2 = it.removePrefix("Yaklaşık ") }
                voice.maybeAnnounce(ns.nextManeuver, ns.nextTurnM, ns.remainingM)
            }.onFailure {
                android.util.Log.w("DashViewModel", "Voice guidance failed: ${it.message}")
            }
        } else if (loc != null && dLat != null && dLng != null && LocationTracker.isFreshForNavigation(loc)) {
            remainingM = GeoPoint.distMeters(
                GeoPoint(loc.latitude, loc.longitude), GeoPoint(dLat, dLng)
            )
        }

        // Recompute the route if the rider has clearly left it for a few seconds.
        maybeReroute(offRoute, loc)
        if (!googleNav.active && !navPaused) maybeRefreshTraffic(loc)

        // Periodic ride snapshot (~15 s) — GPS quality, motion, frame throughput and thermal,
        // so a post-ride read shows where the map stuttered (weak GPS) or the dash struggled.
        val nowD = System.currentTimeMillis()
        if (nowD - lastDiagMs > 15_000) {
            val diagSpanS = (nowD - lastDiagMs) / 1000.0
            lastDiagMs = nowD
            com.example.wayfindr.data.RideDiagnostics.log(
                "ride",
                "gps=" + (loc?.let { "acc=%.0fm spd=%.1fm/s (%.0fkm/h)".format(it.accuracy, it.speed, it.speed * 3.6) } ?: "none") +
                        " ridden=%.2fkm".format(recorder.distanceSoFarM / 1000.0) +
                        " fixGap=%.0fms".format(gpsFixIntervalMs) +
                        " frames=${_ui.value.frameCount} thermal=${_ui.value.thermal}" +
                        " pace=%.0fms/max=%dms".format(sendPaceMs, sendPaceMaxMs) +  // send cadence (≈100ms = 10 fps)
                        " fps=%.1f tick=%.0fms enc=%.0fms".format(if (sendPaceMs > 0) 1000 / sendPaceMs else 0.0, tickMsAvg, encodeMsAvg) +
                        " dashFps=%.1f".format((session.framesDecoded - diagDecoded) / diagSpanS).also { diagDecoded = session.framesDecoded } +
                        " remaining=" + (remainingM?.let { "%.1fkm".format(it / 1000.0) } ?: "-") +
                        " offRoute=$offRoute" +
                        (if (lastSnapDist >= 0) " snap=%.0fm".format(lastSnapDist) else ""),
            )
            sendPaceMaxMs = 0L  // reset the worst-case window each diag line
        }

        // ── Predictive, framerate-independent camera (CarPlay-smooth) ──
        // Capture each fresh GPS fix + its velocity for dead-reckoning.
        if (loc != null && loc.time != lastFixTime) {
            lastFixTime = loc.time
            // Track the GAP between fixes (EMA): if this balloons past ~1 s with the screen off,
            // GPS callbacks are being throttled — that's what the predictor has to bridge and the
            // root of any residual "stuck then teleport". Logged in the periodic ride line.
            val nowWall = System.currentTimeMillis()
            if (fixWallMs > 0L) {
                val gap = (nowWall - fixWallMs).toDouble()
                gpsFixIntervalMs = if (gpsFixIntervalMs <= 0.0) gap else gpsFixIntervalMs + (gap - gpsFixIntervalMs) * 0.3
            }
            fixLat = matchedLat ?: loc.latitude; fixLng = matchedLng ?: loc.longitude
            // A fix without speed (network / cell, weak GPS) moves at the dead-reckoning speed instead of
            // reading as "stopped" (which held the marker still while riding).
            fixBearing = loc.bearing; fixSpeed = if (loc.hasSpeed()) loc.speed else drSpeedMps.toFloat()
            fixWallMs = nowWall
            fixElapsedNs = loc.elapsedRealtimeNanos
            fixArrivalNs = android.os.SystemClock.elapsedRealtimeNanos()
            fixAccuracy = if (loc.hasAccuracy()) loc.accuracy else 30f
            fixIsNew = true
        }

        // GPS health after applying this tick's fix, so a just-accepted update isn't still
        // classified from the previous gap. LOST is "no usable lock", not "slow for a few seconds".
        val fixAgeMs = if (fixWallMs > 0L) System.currentTimeMillis() - fixWallMs else Long.MAX_VALUE
        gpsStatus = when {
            // Dead reckoning along the route through a gap: "weak", the guidance keeps going.
            loc != null && deadReckoning -> GpsStatus.WEAK
            loc == null || fixAgeMs > GPS_LOST_AGE_MS -> GpsStatus.LOST
            loc.accuracy > 25f || gpsFixIntervalMs > 2_500 || fixAgeMs > GPS_WEAK_AGE_MS -> GpsStatus.WEAK
            else -> GpsStatus.GOOD
        }

        // Extrapolate where the rider IS NOW (last fix + velocity·elapsed), so the camera
        // doesn't trail the bike between 1 Hz fixes. Only predict while genuinely moving.
        val rider: Pair<Double, Double>? = when {
            loc == null -> null
            fixSpeed > 0.5f -> {
                // Dead-reckon forward from the last fix so the camera keeps GLIDING through GPS
                // gaps instead of freezing then snapping ("stuck then jump"). Constant speed, capped
                // at RiderMotion.MAX_PREDICT_S so a long dropout or an unannounced stop can't fling
                // the camera far ahead; RiderMotion fades the next fix's correction in.
                // elapsed = time since the fix was MEASURED (its age on delivery + time since it
                // arrived, elapsedRealtime clock; it used to start when this loop first saw the
                // fix, so the fix's own age and up to a frame of polling were lost = lag ∝ speed).
                // + LEAD_S leads the marker past "now" to cancel encode + RTP + dash decode/show.
                val elapsed = com.example.wayfindr.dash.map.RiderMotion.sinceFixSeconds(
                    fixElapsedNs, fixArrivalNs, android.os.SystemClock.elapsedRealtimeNanos())
                // Weak-GPS guard: a noisy fix has an unreliable position AND bearing/speed, so
                // scale the prediction down for GENUINELY poor fixes (full to 30 m, none at 60 m).
                // Any scale-down leaves a 1 Hz forward sawtooth, so it starts later than the old 25 m.
                val conf = com.example.wayfindr.dash.map.RiderMotion.confidence(fixAccuracy)
                val dist = com.example.wayfindr.dash.map.RiderMotion.distance(
                    fixSpeed, elapsed + com.example.wayfindr.dash.map.RiderMotion.LEAD_S) * conf
                // Remember where the bike actually is (snapped to the road), so that when it stops
                // the marker holds the real fix (not the lead-projected point ahead of it).
                heldRider = NavPlus.roadSnapHold(fixLat to fixLng) as Pair<Double, Double>?
                // Project along the curving road / route rather than in a straight line.
                // On the route the prediction is not cut at MAX_PREDICT_S nor scaled down by accuracy:
                // through a weak-GPS gap the marker keeps riding the line (WeakGps.MAX_S at most).
                val routeDist = fixSpeed * (elapsed + com.example.wayfindr.dash.map.RiderMotion.LEAD_S).coerceIn(0.0, WeakGps.MAX_S)
                navplusPredict(NavPlus.arcProject(fixLat, fixLng, fixBearing.toDouble(), dist), dist, routeDist)
            }
            else -> {
                // Stopped: hold position and IGNORE sub-accuracy GPS wander, so a parked bike's dot
                // doesn't drift or jitter. Only move the held point if the fix steps clearly outside
                // its own error radius (i.e. the bike really moved a little / GPS re-converged).
                val cand = matchedLat!! to matchedLng!!
                val h = heldRider
                val band = loc.accuracy.coerceIn(8f, 30f).toDouble()
                if (h == null || GeoPoint.distMeters(
                        GeoPoint(h.first, h.second), GeoPoint(cand.first, cand.second)
                    ) > band
                ) {
                    (NavPlus.roadSnapHold(cand) as Pair<Double, Double>?).also { heldRider = it }
                } else h
            }
        }

        // Continuous motion: a new fix / prediction source becomes a fading offset (RiderMotion)
        // instead of a jump, and the prediction itself moves at full speed — the camera no longer
        // low-passes the position (that trailed the bike by speed × τ and made a 1 Hz sawtooth).
        val nowNs = System.nanoTime()
        val mode = when { rider == null -> 0; fixSpeed > 0.5f -> 1; else -> 2 }
        val onRouteNow = mode == 1 && predProgressM >= 0.0
        val rebase = fixIsNew || mode != motionMode || onRouteNow != motionOnRoute
        fixIsNew = false; motionMode = mode; motionOnRoute = onRouteNow
        val shown = rider?.let {
            riderMotion.step(it.first, it.second, rebase, nowNs, com.example.wayfindr.dash.map.RiderMotion.tauFor(fixAccuracy))
        }
        // Heading-up on the route: follow the road's bearing at the predicted point (smooth through
        // bends, no 1 Hz GPS-bearing steps); off the route the GPS bearing as before.
        if (onRouteNow && fixSpeed >= BEARING_MIN_SPEED) r?.let {
            com.example.wayfindr.dash.map.RiderMotion.bearingAt(it.geometry, it.cumulative, predProgressM)
        }?.let { targetHeading = normalizeHeading(it) }

        // Follow mode: track the rider. If GPS is lost, HOLD the last position instead of
        // jumping to the destination or (0,0). Manual pan mode tracks the panned centre.
        val targetLat = shown?.first ?: camLat
        val targetLng = shown?.second ?: camLng

        // ...but if we have NO camera state yet and NO rider, then and only then snap to dest.
        val finalTargetLat = if (!camInit && shown == null) (dLat ?: targetLat) else targetLat
        val finalTargetLng = if (!camInit && shown == null) (dLng ?: targetLng) else targetLng

        // Time-based smoothing (heading only): alpha derived from the real frame interval + a
        // time constant, so rotation is equally smooth at any frame rate.
        val dt = if (lastTickNs == 0L) 0.042 else ((nowNs - lastTickNs) / 1e9).coerceIn(0.0, 0.5)
        lastTickNs = nowNs
        // Adaptive smoothing: ease HARDER (bigger time constant) when the fix is noisy so weak-GPS
        // jitter doesn't reach the camera, and stay snappy when the signal is clean. This is the
        // other half of the "stuck then jump" fix — the predictor bridges gaps, this absorbs the
        // lateral noise that makes a poor signal jitter.
        val acc = loc?.accuracy ?: 12f
        val tau = when {
            acc > 25f -> SMOOTH_TAU * 4.0   // poor signal → heavy smoothing
            acc > 12f -> SMOOTH_TAU * 1.5   // fair
            else      -> SMOOTH_TAU         // good → responsive
        }
        val a = if (camInit) (1.0 - Math.exp(-dt / tau)) else 1.0

        if (!camInit) {
            camLat = finalTargetLat
            camLng = finalTargetLng
            camHdg = normalizeHeading(targetHeading)
            camInit = true
        } else {
            camLat = finalTargetLat
            camLng = finalTargetLng
            // Smoothly rotate toward the target heading.
            val dh = (((targetHeading - camHdg) % 360f) + 540f) % 360f - 180f
            camHdg = normalizeHeading(camHdg + dh * a.toFloat())
        }
        frameRiderLat = if (rider != null) camLat else null
        frameRiderLng = if (rider != null) camLng else null

        // Tiles 3 s and 6 s ahead into memory (Google tiles only), twice a second, so they
        // are decoded before they scroll in at speed.
        val nowWarm = System.currentTimeMillis()
        if (followMode && mode == 1 && fixSpeed > 5f && nowWarm - lastWarmMs > 500) {
            lastWarmMs = nowWarm
            for (aheadS in WARM_AHEAD_S) {
                val d = fixSpeed * aheadS
                val p = if (onRouteNow && r != null)
                    com.example.wayfindr.dash.map.RiderMotion.pointAt(r.geometry, r.cumulative, predProgressM + d)?.let { it.lat to it.lng }
                else project(camLat, camLng, camHdg.toDouble(), d)
                p?.let { tiles.warmAhead(it.first, it.second, zoom) }
            }
        }

        val centerLat = if (camInit) camLat else 0.0
        val centerLng = if (camInit) camLng else 0.0
        val travelHeading = normalizeHeading(targetHeading)
        val camHeading = normalizeHeading(if (camInit) camHdg else travelHeading)

        val sig = buildString {
            // High resolution (6 dp ≈ 0.1 m, 0.1° heading) so every smoothed step redraws
            // for buttery motion. Safe from standstill jitter because the camera is fed the
            // SMOOTHED position (which settles and stops), not raw GPS.
            append("%.6f".format(centerLat)); append("%.6f".format(centerLng))
            append(zoom); append(panX.toInt()); append(panY.toInt())
            append(if (headingUp) (camHeading * 10).toInt() else 0)
            append(if (mapDark) 1 else 0)
            append((travelHeading * 10).toInt())
            append(remainingM?.let { (it / 100).toInt() } ?: -1) // 100 m resolution to avoid jitter
            append(if (r != null) r.geometry.size else 0)
            // ETA text is rendered into the encoded dash frame. Include its changing values here;
            // otherwise the phone UI can update while the dash keeps receiving an old bitmap
            // until a joystick action changes the map.
            append(displayedEtaMin ?: -1)
            append(etaArrivalMs / 1_000L)
            // The dash frame contains time-dependent content. A wall-clock token guarantees a
            // fresh Canvas render at standstill even when GPS and route geometry are unchanged.
            append(System.currentTimeMillis() / 1_000L)
            append(if (recalculating) 1 else 0)
            // Include the incoming-call caller so a call arriving forces an immediate redraw of the
            // banner even when the map is static (otherwise it'd wait for the 2 s forced refresh).
            append(com.example.wayfindr.media.CallInfoProvider.incomingCall.value?.takeIf { it.incoming }?.caller ?: "")
        }
        val now = System.currentTimeMillis()
        // A genuine content change (not the periodic forced refresh) is what marks the map as
        // "moving" for the adaptive cadence — a forced redraw of identical content stays "idle".
        val contentChanged = sig != lastSignature
        // Always refresh the source bitmap before encoding. The dash must receive a newly drawn
        // frame while stationary too; otherwise its projection can appear frozen until joystick input.
        lastSignature = sig
        lastRedrawAt = now
        // The encoded raster map follows the smoothed camera heading. ETA/GPS overlays remain
        // screen-space in MapRenderer, while cached tile labels rotate with this world layer.
        // Picker housekeeping: drop it after inactivity, or if a destination got set elsewhere.
        if (pickerItems != null) {
            if (System.currentTimeMillis() - pickerLastInputMs > PICKER_TIMEOUT_MS) closePicker("timeout")
            else if (!canOpenPicker()) closePicker("destination set / not streaming")
        }
        redrawFrame(centerLat, centerLng, camHeading, travelHeading, remainingM)

        // Publish nav figures to the phone UI.
        _ui.update { it.copy(
            hasGps = loc != null,
            gpsStatus = gpsStatus,
            riderLat = matchedLat,
            riderLng = matchedLng,
            riderBearing = travelHeading,
            camLat = centerLat,
            camLng = centerLng,
            camBearing = if (headingUp) camHeading else 0f,
            mapZoom = zoom,
            remainingKm = remainingM?.let { d -> d / 1000.0 },
            etaMinutes = if (etaSec != null && !navPaused) displayedEtaMin else null,
            maneuver = null,
            offRoute = offRoute,
            recalculating = recalculating,
        ) }
        updateThermal()

        return contentChanged
    }

    /**
     * Reroute when off the line for >2 s, with a backed-off wait between attempts and a per-
     * destination cap on automatic calls (see takeAutoRouteCall). Routes from
     * the live GPS position to the saved destination and swaps the polyline in. Needs
     * internet — available now because only the dash sockets are bound to the dash WiFi.
     */
    private fun maybeReroute(offRoute: Boolean, loc: android.location.Location?) {
        // Only while actually riding with the dash: no reroute calls while disconnected/reconnecting.
        if (!userWantsConnection || session.state.value != DashState.STREAMING) {
            offRouteSince = 0L
            offRouteConfirmation.reset()
            return
        }
        val finalStop = routeStops.lastOrNull()
        val dLat = finalStop?.lat ?: destLat
        val dLng = finalStop?.lng ?: destLng
        val now = System.currentTimeMillis()
        if (!offRoute || loc == null || !LocationTracker.isFreshForNavigation(loc) || dLat == null || dLng == null) {
            offRouteSince = 0L
            offRouteConfirmation.reset()
            // Back on route for a while → next miss starts from the short wait again.
            if (rerouteStreak > 0 && now - lastRerouteAt > REROUTE_STREAK_RESET_MS) rerouteStreak = 0
            return
        }
        if (offRouteSince == 0L) offRouteSince = now
        // A real miss needs several distinct off-route fixes over a short span (not one GPS wobble).
        // The wait between attempts backs off while the rider stays off-route, so a stuck/noisy GPS
        // can't fire a call every few seconds.
        val fixToken = loc.elapsedRealtimeNanos.takeIf { it > 0 } ?: loc.time
        if (!offRouteConfirmation.record(fixToken, now)) return
        val cooldown = (REROUTE_BASE_MS shl rerouteStreak.coerceAtMost(5)).coerceAtMost(REROUTE_MAX_MS)
        if (now - lastRerouteAt < cooldown || rerouting) return
        if (trafficRefreshInFlight || !takeAutoRouteCall("reroute")) return
        rerouteStreak++
        lastRerouteAt = now
        rerouting = true
        routeRequestGeneration++
        val requestId = routeRequestGeneration
        val waypointSnapshot = routeWaypoints.toList()
        val stopIndexSnapshot = activeStopIndex
        val offSec = (now - offRouteSince) / 1000
        android.util.Log.i("DashViewModel", "Off-route ${offSec}s → rerouting")
        com.example.wayfindr.data.RideDiagnostics.log("reroute", "off-route ${offSec}s → requesting new route")
        val startMs = System.currentTimeMillis()
        routeRequestJob?.cancel()
        routeRequestJob = viewModelScope.launch {
            try {
                NavPlus.quietAlts()
                val alternatives = Router.alternatives(getApplication(), GeoPoint(loc.latitude, loc.longitude), GeoPoint(dLat, dLng), waypointSnapshot)
                // Prefer an alternative that leads back onto the planned roads (NavPlus decides).
                val r = NavPlus.pickPlanned(alternatives, this@DashViewModel, 0)
                // Stale: a newer request or a passed stop replaced this one.
                if (requestId != routeRequestGeneration || activeStopIndex != stopIndexSnapshot) return@launch
                val took = System.currentTimeMillis() - startMs
                if (r != null) {
                    route = r
                    lastTrafficRefreshAt = System.currentTimeMillis()
                    progressM = 0.0; progressFixNs = 0L
                    routeLegOffset = activeStopIndex
                    offRouteSince = 0L
                    offRouteConfirmation.reset()
                    tiles.prefetchRoute(r.geometry)
                    _ui.update { it.copy(hasRoute = true, routePoints = r.geometry, routeTraffic = r.traffic) }
                    android.util.Log.i("DashViewModel", "Reroute ok: ${r.geometry.size} pts, ${r.totalMeters.toInt()} m")
                    com.example.wayfindr.data.RideDiagnostics.log("reroute", "new route in ${took}ms (${r.geometry.size} pts, ${r.totalMeters} m)")
                } else {
                    android.util.Log.w("DashViewModel", "Reroute failed (no internet?)")
                    com.example.wayfindr.data.RideDiagnostics.log("reroute", "FAILED after ${took}ms (no internet?)")
                }
            } finally {
                if (requestId == routeRequestGeneration) {
                    rerouting = false
                    routeRequestJob = null
                }
            }
        }
    }

    /** Move to the next itinerary stop once the rider reaches the end of the active route leg. */
    private fun advanceWaypointIfReached(loc: android.location.Location, nav: NavState): Boolean {
        val stops = routeStops
        val index = activeStopIndex
        if (index >= stops.lastIndex) return false // Final destination keeps the normal arrival behavior.
        val stop = stops[index]
        val lat = stop.lat ?: return false
        val lng = stop.lng ?: return false
        // Use both the actual stop distance and the leg progress. The second check avoids advancing
        // merely because two roads happen to pass close to the waypoint before reaching it.
        // Neither is trusted on a coarse fix (> 60 m), and the leg progress only when the route
        // match itself is close (a parallel road must not complete the leg).
        val accuracy = loc.takeIf { it.hasAccuracy() }?.accuracy?.takeIf { it.isFinite() }?.toDouble()
        val accuracyTrusted = accuracy == null || accuracy <= 60.0
        val closeToStop = accuracyTrusted &&
            GeoPoint.distMeters(GeoPoint(loc.latitude, loc.longitude), GeoPoint(lat, lng)) <= WAYPOINT_ARRIVE_M
        val legIndex = index - routeLegOffset
        val routeMatchTrusted = accuracyTrusted &&
            nav.snapDist <= maxOf(WAYPOINT_ARRIVE_M, (accuracy ?: 0.0) * 1.5)
        val reachedLegEnd = routeMatchTrusted &&
            (route?.legEndCumulative?.getOrNull(legIndex)?.let { nav.progressM >= it - WAYPOINT_PROGRESS_TOLERANCE_M } ?: false)
        if (!closeToStop && !reachedLegEnd) return false
        advanceToNextStop(index)
        return true
    }

    /** Stop [index] of the itinerary is done: continue to the next one (route already covers it). */
    private fun advanceToNextStop(index: Int) {
        val stops = routeStops
        // The next leg is already in the route: drop any request still running for the old one.
        routeRequestGeneration++
        routeRequestJob?.cancel()
        routeRequestJob = null
        rerouting = false
        trafficRefreshInFlight = false
        activeStopIndex = index + 1
        val next = stops[activeStopIndex]
        destLat = next.lat
        destLng = next.lng
        routeWaypoints = stops.subList(activeStopIndex + 1, stops.lastIndex)
            .map { GeoPoint(it.lat!!, it.lng!!) }
        voice.resetTrip()
        voice.announceNextStop(next.name)
        session.updateRouteCard(next.name)
        _ui.update { it.copy(destinationName = next.name, destLatLng = next.lat!! to next.lng!!) }
        android.util.Log.i("DashViewModel", "Waypoint ${index + 1}/${stops.size} reached; continuing to ${next.name}")
    }

    private fun remainingToActiveStop(r: Route, nav: NavState): Double {
        val legIndex = activeStopIndex - routeLegOffset
        val end = r.legEndCumulative.getOrNull(legIndex) ?: r.totalMeters
        return (end - nav.progressM).coerceAtLeast(0.0)
    }

    /** Refresh the active route every five minutes so congestion colours and ETA stay current. */
    private fun maybeRefreshTraffic(loc: android.location.Location?) {
        if (!userWantsConnection || session.state.value != DashState.STREAMING) return
        if (loc == null || !LocationTracker.isFreshForNavigation(loc) || route == null || rerouting || trafficRefreshInFlight) return
        // Free/offline routing has no traffic data, so a refresh would only load the router.
        if (route?.source != com.example.wayfindr.dash.RoutingSource.GOOGLE) return
        if (!com.example.wayfindr.dash.GoogleKey.present) return
        val now = System.currentTimeMillis()
        if (now - lastTrafficRefreshAt < TRAFFIC_REFRESH_MS) return
        val final = routeStops.lastOrNull() ?: return
        val finalLat = final.lat ?: return
        val finalLng = final.lng ?: return
        lastTrafficRefreshAt = now
        if (!takeAutoRouteCall("traffic refresh")) return
        trafficRefreshInFlight = true
        routeRequestGeneration++
        val requestId = routeRequestGeneration
        val waypointSnapshot = routeWaypoints.toList()
        val stopIndexSnapshot = activeStopIndex
        routeRequestJob?.cancel()
        routeRequestJob = viewModelScope.launch {
            try {
                val alternatives = Router.alternatives(
                    getApplication(), GeoPoint(loc.latitude, loc.longitude), GeoPoint(finalLat, finalLng), waypointSnapshot,
                )
                // Same roads → plain traffic refresh (NavPlus decides). Other roads are taken only when
                // clearly faster (TrafficSwitch), and announced once.
                val planned = NavPlus.pickPlanned(alternatives, this@DashViewModel, 1)
                if (requestId != routeRequestGeneration || activeStopIndex != stopIndexSnapshot) return@launch
                val current = route
                val best = alternatives.minByOrNull { it.totalSeconds }
                // Time left on the current roads: prefer the fresh same-road result (it carries today's
                // traffic, so a new jam on our road counts); else scale the old estimate.
                val leftS = planned?.totalSeconds?.takeIf { it > 0.0 }
                    ?: current?.let { TrafficSwitch.remainingSeconds(it.totalSeconds, it.totalMeters, progressM) } ?: 0.0
                val gainS = if (best != null && best !== planned) leftS - best.totalSeconds else 0.0
                val switchNow = best != null && TrafficSwitch.worthSwitching(
                    leftS, best.totalSeconds, System.currentTimeMillis(), lastTrafficSwitchAt) && best !== planned
                val refreshed = if (switchNow) best else planned
                if (switchNow) {
                    lastTrafficSwitchAt = System.currentTimeMillis()
                    tiles.prefetchRoute(best!!.geometry)
                    voice.alert(TrafficSwitch.message(gainS))
                    com.example.wayfindr.data.RideDiagnostics.log("reroute", "traffic switch: ${gainS.toInt()} s faster")
                }
                if (refreshed != null) {
                    route = refreshed
                    progressM = 0.0; progressFixNs = 0L
                    routeLegOffset = activeStopIndex
                    // No tile prefetch here: a traffic refresh is the same road. Prefetching on every
                    // refresh downloaded ~600 new tiles each time on a long route, even parked.
                    _ui.update { it.copy(routePoints = refreshed.geometry, routeTraffic = refreshed.traffic) }
                    android.util.Log.i("DashViewModel", "Traffic refreshed: ${refreshed.traffic.size} route intervals")
                }
            } finally {
                if (requestId == routeRequestGeneration) {
                    trafficRefreshInFlight = false
                    routeRequestJob = null
                }
            }
        }
    }

    /** Spend one automatic Routes API call from this destination's budget; false = over the cap. */
    private fun takeAutoRouteCall(kind: String): Boolean {
        if (autoRouteCalls >= MAX_AUTO_ROUTE_CALLS) {
            if (!autoRouteCapLogged) {
                autoRouteCapLogged = true
                com.example.wayfindr.data.RideDiagnostics.log("reroute",
                    "auto Routes API cap ($MAX_AUTO_ROUTE_CALLS) reached — no more $kind/reroutes for this destination")
            }
            return false
        }
        autoRouteCalls++
        com.example.wayfindr.data.RideDiagnostics.log("reroute", "$kind: auto Routes call $autoRouteCalls/$MAX_AUTO_ROUTE_CALLS")
        return true
    }

    private fun normalizeHeading(degrees: Float): Float {
        var value = degrees % 360f
        if (value < 0f) value += 360f
        return value
    }

    /** 2 when the "Sharper map" setting is on: the frame is drawn at 1052×600 and scaled down. */
    private fun renderScale() = if (dashConfig.sharperMap.value) 2 else 1

    private fun newFrameBitmap(): Bitmap = renderScale().let { s ->
        Bitmap.createBitmap(DashEncoder.WIDTH * s, DashEncoder.HEIGHT * s, Bitmap.Config.ARGB_8888)
    }

    private fun redrawFrame(
        centerLat: Double,
        centerLng: Double,
        cameraHeading: Float,
        travelHeading: Float,
        remainingM: Double?,
    ) {
        // Follow the "Sharper map" setting: swap the frame size and tile resolution together.
        // (The old bitmap isn't recycled — the encoder may still be reading it this tick.)
        val scale = renderScale()
        tiles.hiDpi = scale > 1
        var bmp = frameBitmap ?: return
        if (bmp.width != DashEncoder.WIDTH * scale) { bmp = newFrameBitmap(); frameBitmap = bmp }
        // Glanceable ETA — minutes remaining + a stable 12-hour arrival clock. Both come
        // from the smoothed estimate so they don't flicker every second.
        val mins = displayedEtaMin
        val arriving = mins != null && mins <= 0
        val recalculating = _ui.value.recalculating
        // Off-route / rerouting: tell the rider the ETA is being recomputed instead of showing a
        // frozen or wildly-swinging number.
        val etaPrimary = when {
            navPaused -> null
            recalculating && route != null -> "Hesaplanıyor"
            mins != null && etaArrivalMs > 0L -> when {
                arriving   -> "Varış"
                mins >= 60 -> "${mins / 60} sa ${mins % 60} dk"
                else       -> "$mins dk"
            }
            else -> null
        }
        // 24-hour arrival clock (Turkish); hidden once arriving or while recalculating.
        val etaSecondary = if (etaPrimary != null && !arriving && !recalculating)
            java.text.SimpleDateFormat("HH:mm", java.util.Locale("tr", "TR")).format(java.util.Date(etaArrivalMs))
        else null
        // Only the part of the route still ahead of the rider (NavPlus trims the ridden part).
        val progress = progressM
        val r = route
        @Suppress("UNCHECKED_CAST")
        val aheadRoute = r?.let { NavPlus.aheadGeometry(it, progress, frameRiderLat, frameRiderLng) as List<GeoPoint>? } ?: emptyList()
        @Suppress("UNCHECKED_CAST")
        val aheadTraffic = r?.let { NavPlus.aheadTraffic(it, progress, frameRiderLat, frameRiderLng) as List<TrafficInterval>? } ?: emptyList()
        val frame = MapRenderer.Frame(
            centerLat = centerLat,
            centerLng = centerLng,
            zoom = zoom,
            panX = panX,
            panY = panY,
            headingUp = headingUp,
            cameraHeading = cameraHeading,
            riderHeading = travelHeading,
            riderLat = frameRiderLat,
            riderLng = frameRiderLng,
            destLat = destLat,
            destLng = destLng,
            destinations = routeStops.mapNotNull { stop ->
                if (stop.lat != null && stop.lng != null) GeoPoint(stop.lat, stop.lng) else null
            },
            destName = _ui.value.destinationName,
            route = aheadRoute,
            traffic = aheadTraffic,
            maneuverText = null, // turn-by-turn maneuver banner removed
            turnCard = turnCard,
            remainingText = remainingM?.takeIf { !navPaused }?.let { fmtDist(it) },
            paused = navPaused,
            // Top-down (heading-up) nav view. The 3D perspective tilt is DISABLED: warping
            // flat raster tiles via setPolyToPoly stretches the baked-in map labels and
            // skews the angle (you can't get true Google-Maps 3D without vector tiles).
            tilt3d = false,
            etaPrimary = etaPrimary,
            etaSecondary = etaSecondary,
            pickerRows = pickerItems?.let { menuRows() },
            navPrompt = if (navPromptOpen && pickerItems == null) listOf(
                "NAVİGASYON",
                if (navPaused) "◀ Devam et" else "◀ Duraklat",
                "Bitir ▶",
                "▼ vazgeç",
            ) else null,
            pickerTitle = when (menuPage) { MenuPage.SOURCE -> "UYARILAR"; MenuPage.ROOT -> if (hasDestination()) "MENÜ" else "KAYITLI YERLER" },
            pickerSelectHint = when (menuPage) {
                MenuPage.SOURCE -> "Sol/sağ: gez · sağ basılı: seç"
                MenuPage.ROOT -> "Sol/sağ: gez · sağ basılı: aç"
            },
            pickerIndex = pickerIndex,
            zoomLabel = if (System.currentTimeMillis() < zoomBadgeUntilMs) "Ölçek $zoom" else null,
            // The router that made the current route (BRouter may have fallen back to OSM);
            // with no route, the one the next route will use.
            routingBadge = route?.source ?: dashConfig.routingSource.value,
            notice = notice?.takeIf { pickerItems == null && System.currentTimeMillis() < noticeUntilMs },
            downHint = run {
                val now = System.currentTimeMillis()
                val music = pickerItems == null && now < musicHintUntilMs
                val menu = pickerItems == null && now < downHintUntilMs
                when {
                    music && menu -> "▼ menü · ● ${musicToggleWord()} · ◀ ▶ şarkı"
                    music -> "● ${musicToggleWord()} · ◀ ▶ şarkı"
                    menu -> "▼ tekrar: menüyü aç"
                    else -> null
                }
            },
            joystickTrail = if (dashConfig.showJoystickTrail.value) joyTrail.text(System.currentTimeMillis()) else null,
            joyTest = joyTestLines?.takeIf { System.currentTimeMillis() < joyTestUntilMs && dashConfig.joystickTestActive() },
            attribution = TileProvider.OSM_ATTRIBUTION,
            phoneBattery = if (dashConfig.showPhoneBattery.value) session.latestPhoneStatus?.let { it.batteryPct to it.charging } else null,
            gpsWeak = gpsStatus == GpsStatus.WEAK,
            gpsLost = gpsStatus == GpsStatus.LOST,
            dark = mapDark,
        )
        val canvas = Canvas(bmp)
        // Draw in LOGICAL 526×300 coordinates at any render scale, so layout is identical.
        canvas.scale(scale.toFloat(), scale.toFloat())
        mapRenderer.draw(canvas, frame, DashEncoder.WIDTH, DashEncoder.HEIGHT)
        // The console HUD (weather/fuel pill) is drawn by MapRenderer, stacked above the music card.
        // The dash shows ONLY our video frame while projecting — it won't overlay its native
        // now-playing/call cards (confirmed: 05 0d/05 22 are sent fine but never displayed mid-map).
        // So we draw them INTO the frame ourselves, as the CLAUDE.md design always intended.
        drawDashOverlays(canvas, DashEncoder.WIDTH, DashEncoder.HEIGHT)
        // Publish a snapshot for the in-app preview (immutable copy — see _previewFrame), at 4 Hz.
        val nowP = System.currentTimeMillis()
        if (nowP - lastPreviewMs >= PREVIEW_MS) { lastPreviewMs = nowP; _previewFrame.value = bmp.copy(Bitmap.Config.ARGB_8888, false) }
    }

    // Overlay paints — created once (never per-frame). ~50% smaller than the first cut, per feedback.
    private val ovBg by lazy { android.graphics.Paint().apply { color = 0xCC0B0D0E.toInt(); isAntiAlias = true } }
    private val ovTitle by lazy { android.graphics.Paint().apply {
        color = android.graphics.Color.WHITE; isAntiAlias = true; textSize = 15f
        typeface = android.graphics.Typeface.DEFAULT_BOLD; textAlign = android.graphics.Paint.Align.CENTER } }
    private val ovSub by lazy { android.graphics.Paint().apply {
        color = 0xFFCFCFCF.toInt(); isAntiAlias = true; textSize = 10f; textAlign = android.graphics.Paint.Align.CENTER } }
    private val ovAccent by lazy { android.graphics.Paint().apply {
        color = 0xFFF2A93B.toInt(); isAntiAlias = true; textSize = 10f; textAlign = android.graphics.Paint.Align.CENTER } }

    /**
     * Draw the INCOMING-call banner onto the projected frame (the dash shows only our video while
     * projecting, so this is the only way it's visible mid-ride). Incoming only — outgoing/active
     * calls don't set CallInfoProvider, so this never fires for them. Now-playing has no overlay; it
     * goes to the dash's audio screen via the 05 0d TLV instead. Centred in the round display's safe zone.
     */
    private fun drawDashOverlays(canvas: Canvas, w: Int, h: Int) {
        val call = com.example.wayfindr.media.CallInfoProvider.incomingCall.value
        if (call == null || !call.incoming) return   // overlay only for a ringing call, not active/outgoing
        val cx = w / 2f; val cy = h / 2f
        val half = h * 0.28f   // ~half the previous width; stays inside the round display
        canvas.drawRoundRect(cx - half, cy - 26f, cx + half, cy + 26f, 10f, 10f, ovBg)
        canvas.drawText("Gelen arama", cx, cy - 11f, ovSub)
        canvas.drawText(ellipsizeOverlay(call.caller, 16), cx, cy + 5f, ovTitle)
        canvas.drawText("▲ yanıtla · ▼ reddet", cx, cy + 19f, ovAccent)
    }

    private fun ellipsizeOverlay(s: String, max: Int) = if (s.length > max) s.take(max - 1) + "…" else s

    /** Answer via the dialer's own notification action (most reliable); fall back to TelecomManager. */
    private fun answerCall(c: com.example.wayfindr.media.IncomingCall) {
        val sent = c.answerIntent?.let { runCatching { it.send() }.isSuccess } ?: false
        if (!sent) callController.answer()
        com.example.wayfindr.data.RideDiagnostics.log("media", "answer (notifAction=$sent)")
        android.util.Log.i("DashViewModel", "Call answer: notifAction=$sent")
    }

    /** Reject (ringing) or hang up (active) via the dialer's action; fall back to TelecomManager. */
    private fun endCall(c: com.example.wayfindr.media.IncomingCall) {
        val sent = c.declineIntent?.let { runCatching { it.send() }.isSuccess } ?: false
        if (!sent) callController.hangup()
        com.example.wayfindr.data.RideDiagnostics.log("media", "end/reject (notifAction=$sent)")
    }

    // ── Monotonic route-progress tracker ────────────────────────────────────
    // Snapping to the GLOBALLY nearest segment makes the remaining distance flicker
    // (a winding route can pass near the start again, so it jumps to ~straight-line).
    // Instead we advance progress along the route, searching a window AHEAD of where
    // we already are, only re-acquiring globally if we're clearly off-route.
    @Volatile private var progressM = 0.0
    /** Next turn for the frame's turn card (null = no card); set by tick(), read by redrawFrame(). */
    @Volatile private var turnCard: MapRenderer.TurnCard? = null

    private data class NavState(
        val remainingM: Double, val nextTurnM: Double, val heading: Float, val offRoute: Boolean,
        val snapped: GeoPoint, val snapDist: Double,
        val nextManeuver: com.example.wayfindr.dash.nav.Maneuver?,
        val progressM: Double,
        /** The turn after [nextManeuver] (null = none), for the dash's "then ..." field. */
        val secondManeuver: com.example.wayfindr.dash.nav.Maneuver? = null,
    )

    /**
     * Advance [progressM] along [r] for the fix at [pos] (RouteProgressMatcher: continuity-aware,
     * GPS bearing breaks ties between overlapping passes). With [advanceProgress] false (preview
     * without a dash) the progress is only read, never moved.
     */
    private fun trackProgress(
        r: Route,
        pos: GeoPoint,
        gpsBearing: Float?,
        speedMps: Double,
        advanceProgress: Boolean = true,
        fix: android.location.Location? = null,
    ): NavState {
        // A NEW fix after a gap or with poor accuracy (WeakGps): where dead reckoning along the
        // route puts the rider at that fix's time, so the fix is weighted against it, and the
        // matcher's forward window reaches that far (a 20 s gap at 15 m/s is 300 m ahead).
        val token = fix?.let { f -> f.elapsedRealtimeNanos.takeIf { it > 0 } ?: f.time } ?: 0L
        val newFix = advanceProgress && token != 0L && token != progressFixToken
        val fixNs = fix?.elapsedRealtimeNanos?.takeIf { it > 0 } ?: android.os.SystemClock.elapsedRealtimeNanos()
        val acc = fix?.takeIf { it.hasAccuracy() }?.accuracy?.toDouble()
        val gapS = if (progressFixNs > 0L) (fixNs - progressFixNs) / 1e9 else 0.0
        val drAtFix = if (newFix && progressFixNs > 0L && WeakGps.onRoute(lastSnapDist, acc))
            progressM + WeakGps.predictedMeters(drSpeedMps, gapS) else null
        val matchSpeed = maxOf(speedMps, ((drAtFix ?: progressM) - progressM + (acc ?: 0.0)) / 4.0)
        val match = com.example.wayfindr.dash.nav.RouteProgressMatcher.match(
            r.geometry, r.cumulative, pos, progressM, gpsBearing, matchSpeed,
        )
        if (match == null) {
            val remaining = (r.totalMeters - progressM).coerceAtLeast(0.0)
            return NavState(remaining, remaining, gpsBearing ?: 0f, true, pos, Double.MAX_VALUE, null, progressM, null)
        }
        var matchedProgress = progressM
        // A poor fix is used, not dropped: weighted against the dead-reckoned position (only once per
        // fix — re-blending the same fix every frame would walk it to the raw match).
        val poor = acc != null && acc > WeakGps.GOOD_ACC_M
        val measured = if (newFix && poor && WeakGps.onRoute(match.lateralDistanceMeters, acc))
            WeakGps.blend(drAtFix, match.progressMeters, acc) else match.progressMeters
        // Mostly forward, tolerate small GPS slide.
        // A poor fix while moving never steps the progress back: its noise (±tens of metres) made the
        // remaining distance stand still for seconds (CI 44380760112, 14–17 s). Good fixes still may.
        val noBack = poor && drSpeedMps >= WeakGps.MIN_SPEED_MPS
        if (advanceProgress && (newFix || !poor)) matchedProgress =
            // …and keeps at least half the dead-reckoned advance, so noise behind can't cancel the motion
            // (CI 44387192724: three 1 Hz poor fixes in a row moved it only 10 m at 12.5 m/s).
            if (noBack) maxOf(matchedProgress + 0.5 * ((drAtFix ?: matchedProgress) - matchedProgress), measured)
            else maxOf(matchedProgress - WAYPOINT_PROGRESS_TOLERANCE_M, measured)
        if (newFix) {
            // GPS speed is Doppler-measured: good even when the position is poor, so it is always taken.
            // Without it (network fixes) only progress over 3 s+ counts, so position noise can't drive it.
            val derived = if (progressFixNs > 0L && gapS >= 3.0) (matchedProgress - progressM) / gapS else null
            val gpsSpeed = fix?.takeIf { it.hasSpeed() }?.speed?.toDouble()
            drSpeedMps = WeakGps.nextSpeed(drSpeedMps, gpsSpeed, derived)
            progressFixToken = token
            progressFixNs = fixNs
        }
        if (advanceProgress) progressM = matchedProgress

        val remaining = (r.totalMeters - matchedProgress).coerceAtLeast(0.0)
        val nextMan = r.maneuvers.firstOrNull {
            it.cumulativeMeters > matchedProgress + 1.0 && it.type != com.example.wayfindr.dash.nav.ManeuverType.DEPART
        }
        val nextTurn = nextMan?.let { (it.cumulativeMeters - matchedProgress).coerceAtLeast(0.0) } ?: remaining
        val secondMan = nextMan?.let { n ->
            r.maneuvers.firstOrNull {
                it.cumulativeMeters > n.cumulativeMeters + 1.0 && it.type != com.example.wayfindr.dash.nav.ManeuverType.DEPART
            }
        }
        return NavState(
            remaining, nextTurn, match.headingDegrees, match.lateralDistanceMeters > 70.0,
            // A weighted poor fix is drawn where its committed progress is, not at its raw match.
            if (poor) com.example.wayfindr.dash.map.RiderMotion.pointAt(r.geometry, r.cumulative, matchedProgress) ?: match.snappedPoint
            else match.snappedPoint,
            match.lateralDistanceMeters, nextMan, matchedProgress, secondMan,
        )
    }

    /** Navigation state at [atM] metres along [r] with no fix (weak-GPS dead reckoning, WeakGps). */
    private fun navStateAt(r: Route, atM: Double): NavState {
        val p = atM.coerceIn(0.0, maxOf(0.0, r.totalMeters - 1.0))
        val remaining = (r.totalMeters - p).coerceAtLeast(0.0)
        val nextMan = r.maneuvers.firstOrNull {
            it.cumulativeMeters > p + 1.0 && it.type != com.example.wayfindr.dash.nav.ManeuverType.DEPART
        }
        val secondMan = nextMan?.let { n ->
            r.maneuvers.firstOrNull {
                it.cumulativeMeters > n.cumulativeMeters + 1.0 && it.type != com.example.wayfindr.dash.nav.ManeuverType.DEPART
            }
        }
        val at = com.example.wayfindr.dash.map.RiderMotion.pointAt(r.geometry, r.cumulative, p) ?: r.geometry.last()
        val hdg = com.example.wayfindr.dash.map.RiderMotion.bearingAt(r.geometry, r.cumulative, p) ?: camHdg
        return NavState(
            remaining, nextMan?.let { (it.cumulativeMeters - p).coerceAtLeast(0.0) } ?: remaining, hdg, false,
            at, lastSnapDist.coerceAtLeast(0.0), nextMan, p, secondMan,
        )
    }

    /** [ns] with the next turns, distances from the Navigation SDK's [info] (position match stays ours). */
    private fun withNavInfo(ns: NavState, info: com.google.android.libraries.mapsplatform.turnbyturn.model.NavInfo): NavState {
        val remaining = info.distanceToFinalDestinationMeters?.toDouble() ?: ns.remainingM
        val toStep = info.distanceToCurrentStepMeters?.toDouble() ?: return ns.copy(remainingM = remaining)
        val nextAt = ns.progressM + toStep
        val next = com.example.wayfindr.dash.nav.GoogleNav.maneuverOf(info.currentStep, nextAt, navSdkRouteVersion)
        val after = info.remainingSteps?.firstOrNull()
        val second = next?.let {
            com.example.wayfindr.dash.nav.GoogleNav.maneuverOf(after, nextAt + (after?.distanceFromPrevStepMeters ?: 0), navSdkRouteVersion)
        }
        return ns.copy(
            remainingM = remaining, nextTurnM = if (next != null) toStep else remaining,
            nextManeuver = next, secondManeuver = second,
        )
    }

    private fun updateThermal() {
        val status = runCatching { powerManager.currentThermalStatus }.getOrDefault(PowerManager.THERMAL_STATUS_NONE)
        val label = when (status) {
            PowerManager.THERMAL_STATUS_NONE, PowerManager.THERMAL_STATUS_LIGHT -> "OK"
            PowerManager.THERMAL_STATUS_MODERATE -> "Warm"
            PowerManager.THERMAL_STATUS_SEVERE, PowerManager.THERMAL_STATUS_CRITICAL -> "Hot"
            else -> "Throttling"
        }
        if (label != _ui.value.thermal) _ui.update { it.copy(thermal = label) }
    }

    // ── Helpers ──────────────────────────────────────────────────────────────

    /** metres → (value, dash unit). <1 km → metres (0x30); else km×10 (0x10). */
    private fun toDashDistance(meters: Double): Pair<Int, Int> =
        if (meters < 1000.0) meters.toInt().coerceIn(0, 0xFFFF) to DashCommands.NAV_UNIT_METERS
        else (meters / 100.0).toInt().coerceIn(0, 0xFFFF) to DashCommands.NAV_UNIT_KM_TENTHS

    private fun fmtDist(m: Double): String =
        if (m < 1000) "${m.toInt()} m" else "%.1f km".format(m / 1000.0)

    /** Smallest absolute angle (0..180°) between two compass bearings. */
    private fun angleDelta(a: Float, b: Float): Float {
        val d = (((a - b) % 360f) + 540f) % 360f - 180f
        return kotlin.math.abs(d)
    }

    private fun teardown() {
        // cancel() only REQUESTS a stop — a frame already mid-draw keeps running on its worker
        // thread. Freeing its bitmap/encoder underneath it was the "Canvas: trying to use a
        // recycled bitmap" crash (2026-08-27 crash log). So: detach the resources now, and release
        // the encoder only once both loops have actually finished.
        val loops = listOfNotNull(tickerJob, streamJob)
        tickerJob = null; streamJob = null
        loops.forEach { it.cancel() }
        stopMediaForwarding()
        val oldEncoder = encoder
        encoder = null
        // Never recycle the frame bitmap by hand: a cancelled tick may still be drawing on it.
        // Dropping the reference lets GC free it once nothing uses it.
        frameBitmap = null
        _previewFrame.value = null   // drop the stale dash frame so the preview falls back to the live map
        if (oldEncoder != null) {
            if (loops.isEmpty()) oldEncoder.release()
            else {
                val running = java.util.concurrent.atomic.AtomicInteger(loops.size)
                // invokeOnCompletion fires even if viewModelScope itself is being cancelled.
                loops.forEach { job ->
                    job.invokeOnCompletion { if (running.decrementAndGet() == 0) runCatching { oldEncoder.release() } }
                }
            }
        }
    }

    /**
     * Bridge the phone's now-playing session + incoming-call notification onto the dash (TLV 05 0d /
     * 05 22), matching the dash. Needs notification access; without it MediaInfoProvider
     * stays silent and the dash simply shows no media card. Album art (05 40) is captured in the
     * protocol but not auto-sent yet (05 40 fragmentation not decoded).
     */
    private fun startMediaForwarding() {
        val granted = com.example.wayfindr.media.MediaInfoProvider.isAccessGranted(getApplication())
        com.example.wayfindr.data.RideDiagnostics.log("media", "forwarding start — notification access=$granted")
        mediaInfo.start()
        mediaObserveJob?.cancel()
        mediaObserveJob = viewModelScope.launch {
            launch {
                mediaInfo.nowPlaying.collect { np ->
                    com.example.wayfindr.data.RideDiagnostics.log("media", "now-playing observed: ${np?.title ?: "(none)"}")
                    session.updateNowPlaying(np?.title, np?.album.orEmpty(), np?.artist.orEmpty(), np?.playing ?: false)
                }
            }
            launch {
                com.example.wayfindr.media.CallInfoProvider.incomingCall.collect { call ->
                    com.example.wayfindr.data.RideDiagnostics.log("media", "call observed: ${call?.caller ?: "(none)"}")
                    session.updateCall(call?.caller)
                }
            }
        }
    }

    private fun stopMediaForwarding() {
        mediaObserveJob?.cancel(); mediaObserveJob = null
        mediaInfo.stop()
        session.updateNowPlaying(null, "", "", false)
        session.updateCall(null)
    }

    override fun onCleared() {
        super.onCleared()
        debugButtonRx?.let { runCatching { getApplication<android.app.Application>().unregisterReceiver(it) } }
        stopRecording()        // save the in-progress ride if the app is closed mid-session
        session.disconnect()
        wifiManager.disconnect()
        // Streaming can't continue once the ViewModel is gone, so make sure the foreground
        // service + wake/wifi locks + GPS don't outlive it (otherwise they'd hold the CPU/WiFi
        // awake with nothing producing frames).
        releaseBackgroundResources()
        googleNav.cleanup()
        voice.shutdown()
        com.example.wayfindr.data.RideDiagnostics.stop("app closed")
    }
}
