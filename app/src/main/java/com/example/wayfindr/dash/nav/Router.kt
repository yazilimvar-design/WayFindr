package com.example.wayfindr.dash.nav

import android.content.Context
import android.util.Log
import com.example.wayfindr.dash.RoutingSource
import com.example.wayfindr.util.InternetNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

/**
 * Fetches a route using Google Maps Routes API (DRIVE mode, with the user's own key from
 * [GPlus.key]), the free OSM server ([FreeRouter]) or BRouter on the phone ([OfflineRouter]).
 *
 * Called while planning, when the phone has internet access. The resulting
 * Route can then be cached and used for offline navigation.
 */
object Router {

    private const val TAG = "Router"

    private const val ENDPOINT =
        "https://routes.googleapis.com/directions/v2:computeRoutes"

    /**
     * Only request the fields Wayfindr actually needs.
     *
     * Route:
     * - distance
     * - duration
     * - full route geometry
     *
     * Steps:
     * - navigation instruction
     * - maneuver type
     * - start/end locations
     */
    private const val FIELD_MASK =
        "routes.distanceMeters," +
                "routes.duration," +
                "routes.polyline.encodedPolyline," +
                "routes.legs.endLocation," +
                "routes.legs.steps.startLocation," +
                "routes.legs.steps.endLocation," +
                "routes.legs.steps.navigationInstruction"

    @Volatile private var currentSource = RoutingSource.GOOGLE

    /** "Manzaralı rota": no highways/tolls for Google and OSM (BRouter has no such switch here). Set from DashConfig. */
    @Volatile var scenic = false

    /**
     * Which router to use ("Routing" setting). Set from DashConfig; a user choice also ends any
     * offline override [NavPlus.rtPick] made.
     */
    var source: RoutingSource
        get() = currentSource
        set(value) {
            NavPlus.rtUserSet()
            setSource0(value)
        }

    /** Sets the router without counting as the user's choice (NavPlus' automatic offline switch). */
    fun setSource0(value: RoutingSource) {
        currentSource = value
    }

    /**
     * Computes an ordered multi-stop route; [intermediates] are visited before [to].
     * BRouter runs on the phone; if it fails (app missing, area not downloaded) the free OSM
     * server is tried — never Google, so the BRouter choice can't add to the Google bill — and
     * that simply fails too when there's no internet. Without internet [NavPlus.rtPick] switches
     * to BRouter for the time being.
     */
    suspend fun route(context: Context, from: GeoPoint, to: GeoPoint, intermediates: List<GeoPoint> = emptyList()): Route? {
        NavPlus.rtPick(context)
        val src = effectiveSource()
        val online = src != RoutingSource.BROUTER
        val t0 = System.currentTimeMillis()
        return when (src) {
            RoutingSource.GOOGLE ->
                if (!com.example.wayfindr.dash.GoogleKey.present)
                    FreeRouter.route(context, from, to, intermediates)?.copy(source = RoutingSource.OSM)
                // A failed Google route falls back to OSM (GPlus.googleFallback); GPlus.sourceFor tells which.
                else googleRoute(context, from, to, intermediates)?.let { it.copy(source = GPlus.sourceFor(it)) }
            RoutingSource.OSM ->
                FreeRouter.route(context, from, to, intermediates)?.copy(source = RoutingSource.OSM)
            RoutingSource.BROUTER ->
                OfflineRouter.route(from, to, intermediates)?.copy(source = RoutingSource.BROUTER)
                    ?: run {
                        com.example.wayfindr.data.RideDiagnostics.log("route",
                            "BRouter unavailable (${OfflineRouter.lastError}) — trying the free OSM server")
                        FreeRouter.route(context, from, to, intermediates)?.copy(source = RoutingSource.OSM)
                    }
        }.also { r ->
            com.example.wayfindr.data.RideDiagnostics.log("route", "auto routing: $src → " +
                (r?.let { "${it.source} %.1f km".format(it.totalMeters / 1000) } ?: "failed") +
                " in ${System.currentTimeMillis() - t0} ms")
        } ?: if (online) offlineFallback(from, to, intermediates) else null
    }

    /**
     * Automatic router: no internet ([NavPlus.rtPick] set BROUTER) → BRouter on the phone; otherwise
     * Google when a key is set, else the free OSM server. Re-checked per request so a key entered
     * later counts at once.
     */
    fun effectiveSource(): RoutingSource = when {
        source == RoutingSource.BROUTER -> RoutingSource.BROUTER
        com.example.wayfindr.dash.GoogleKey.present -> RoutingSource.GOOGLE
        else -> RoutingSource.OSM
    }

    /**
     * Google and the free server both failed (no signal, server down): route on the phone with the
     * built-in BRouter when the Türkiye çevrimdışı paketi covers the trip (or the BRouter app is there).
     */
    private suspend fun offlineFallback(from: GeoPoint, to: GeoPoint, intermediates: List<GeoPoint>): Route? {
        if (!OfflineRouter.canRoute(listOf(from) + intermediates + to)) return null
        com.example.wayfindr.data.RideDiagnostics.log("route", "online routing failed — offline BRouter")
        return OfflineRouter.route(from, to, intermediates)?.copy(source = RoutingSource.BROUTER)
    }

    /**
     * The route plus its alternatives, fastest first: Valhalla's alternates for OSM; for Google and
     * BRouter the [NavPlus.altMarker] request asks for alternatives, which [NavPlus.googleChoices]
     * collects.
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun alternatives(
        context: Context, from: GeoPoint, to: GeoPoint, intermediates: List<GeoPoint> = emptyList(),
    ): List<Route> {
        NavPlus.rtPick(context)
        val src = effectiveSource()
        val routes: List<Route> =
            if (src == RoutingSource.OSM)
                FreeRouter.alternatives(context, from, to, intermediates).map { it.copy(source = RoutingSource.OSM) }
                    .ifEmpty { listOfNotNull(offlineFallback(from, to, intermediates)) }
            else NavPlus.googleChoices(route(context, from, to, NavPlus.altMarker(intermediates) as List<GeoPoint>)) as List<Route>
        // Google often returns a single route (always with stops); top up the picker with the free OSM
        // alternatives so the rider still gets up to 3 choices. Costs nothing extra.
        val choices = if (src == RoutingSource.GOOGLE && routes.size < 2 && routes.firstOrNull()?.source != RoutingSource.BROUTER)
            routes + runCatching { FreeRouter.alternatives(context, from, to, intermediates) }.getOrDefault(emptyList())
                .map { it.copy(source = RoutingSource.OSM) }
        else routes
        return NavPlus.fastestFirst(choices) as List<Route>
    }

    private suspend fun googleRoute(context: Context, from: GeoPoint, to: GeoPoint, intermediates: List<GeoPoint>): Route? =
        withContext(Dispatchers.IO) {

            try {
                val body = buildRequest(from, to, intermediates)

                com.example.wayfindr.data.ApiUsage.record(com.example.wayfindr.data.ApiUsage.ROUTES)
                val conn =
                    InternetNetwork.openConnection(context, ENDPOINT).apply {

                        GPlus.androidAuth(this)
                        requestMethod = "POST"
                        doOutput = true

                        setRequestProperty(
                            "Content-Type",
                            "application/json"
                        )

                        setRequestProperty(
                            "X-Goog-Api-Key",
                            GPlus.key()
                        )

                        setRequestProperty(
                            "X-Goog-FieldMask",
                            FIELD_MASK
                        )

                        connectTimeout = 15_000
                        readTimeout = 20_000
                    }

                conn.outputStream.use {
                    it.write(body.toString().toByteArray(Charsets.UTF_8))
                }

                val responseCode = conn.responseCode

                val responseBody =
                    if (responseCode in 200..299) {
                        conn.inputStream.use {
                            it.readBytes().toString(Charsets.UTF_8)
                        }
                    } else {
                        conn.errorStream?.use {
                            it.readBytes().toString(Charsets.UTF_8)
                        } ?: ""
                    }

                conn.disconnect()

                if (responseCode !in 200..299) {
                    Log.e(
                        TAG,
                        "Google Routes API failed: HTTP $responseCode: $responseBody"
                    )
                    return@withContext GPlus.googleFallback(context, from, to, intermediates, null, responseBody)
                }

                val route = parse(responseBody)
                NavPlus.stashAlts(intermediates, responseBody, route)
                GPlus.googleFallback(context, from, to, intermediates, route, null)

            } catch (e: Exception) {
                Log.e(TAG, "route() failed", e)
                GPlus.googleFallback(context, from, to, intermediates, null, e)
            }
        }

    /**
     * Build a Google Routes API TWO_WHEELER request.
     */
    private fun buildRequest(
        from: GeoPoint,
        to: GeoPoint,
        intermediates: List<GeoPoint>,
    ): JSONObject {

        return JSONObject().apply {

            val origin = waypoint(from)
            put("origin", origin)
            NavPlus.addGoogleHeading(origin, from)   // rider's heading, when known

            put(
                "destination",
                waypoint(to)
            )

            if (intermediates.isNotEmpty()) {
                put("intermediates", JSONArray().apply {
                    intermediates.forEach { put(waypoint(it)) }
                })
            }

            put("travelMode", "DRIVE")

            // Best live-traffic ETA/route (TRAFFIC_AWARE_OPTIMAL). Scenic keeps plain TRAFFIC_AWARE:
            // shortcut: unverified that OPTIMAL accepts avoid modifiers; upgrade once checked.
            put("routingPreference", if (scenic) "TRAFFIC_AWARE" else "TRAFFIC_AWARE_OPTIMAL")

            put("computeAlternativeRoutes", false)
            NavPlus.googleAlts(this, intermediates)   // alternatives for the route picker

            put(
                "routeModifiers",
                JSONObject().apply {
                    put("avoidTolls", scenic)
                    put("avoidHighways", scenic)
                    put("avoidFerries", false)
                }
            )

            put("languageCode", "tr") // Turkish turn text on the dash card
            put("units", "METRIC")
        }
    }

    /**
     * Convert a GeoPoint to a Google Routes API waypoint.
     */
    private fun waypoint(point: GeoPoint): JSONObject =
        JSONObject().apply {
            put(
                "location",
                JSONObject().apply {
                    put(
                        "latLng",
                        JSONObject().apply {
                            put("latitude", point.lat)
                            put("longitude", point.lng)
                        }
                    )
                }
            )
        }

    /** Parses a computeRoutes response (first route). Also used by NavPlus for alternatives. */
    fun parse(json: String): Route? {

        val root = JSONObject(json)

        val routes = root.optJSONArray("routes")

        if (routes == null || routes.length() == 0) {
            Log.w(TAG, "Google Routes API returned no routes: $json")
            return null
        }

        val route = routes.getJSONObject(0)

        /*
         * Google returns:
         *
         * routes[0].polyline.encodedPolyline
         */
        val encodedPolyline =
            route.optJSONObject("polyline")
                ?.optString("encodedPolyline")
                .orEmpty()

        if (encodedPolyline.isBlank()) {
            Log.w(TAG, "Route contains no encoded polyline")
            return null
        }

        val geometry = PolylineCodec.decode(encodedPolyline)

        if (geometry.size < 2) {
            Log.w(TAG, "Decoded route contains fewer than 2 points")
            return null
        }

        /*
         * Build cumulative distance for Wayfindr's navigation engine.
         */
        val cumulative = DoubleArray(geometry.size)

        for (i in 1 until geometry.size) {
            cumulative[i] =
                cumulative[i - 1] +
                        GeoPoint.distMeters(
                            geometry[i - 1],
                            geometry[i]
                        )
        }

        val maneuvers = parseManeuvers(
            route = route,
            geometry = geometry,
            cumulative = cumulative
        )

        return Route(
            geometry = geometry,
            maneuvers = maneuvers,
            totalMeters = route.optDouble(
                "distanceMeters",
                cumulative.last()
            ),
            totalSeconds = parseDuration(
                route.optString("duration", "0s")
            ),
            cumulative = cumulative,
            legEndCumulative = parseLegEnds(route, geometry, cumulative),
            traffic = parseTraffic(route, geometry.lastIndex),
        )
    }

    private fun parseTraffic(route: JSONObject, lastPointIndex: Int): List<TrafficInterval> {
        val intervals = route.optJSONObject("travelAdvisory")
            ?.optJSONArray("speedReadingIntervals") ?: return emptyList()
        return buildList {
            for (index in 0 until intervals.length()) {
                val item = intervals.optJSONObject(index) ?: continue
                val start = item.optInt("startPolylinePointIndex", 0).coerceIn(0, lastPointIndex)
                val end = item.optInt("endPolylinePointIndex", lastPointIndex).coerceIn(start, lastPointIndex)
                val speed = when (item.optString("speed")) {
                    "NORMAL" -> TrafficSpeed.NORMAL
                    "SLOW" -> TrafficSpeed.SLOW
                    "TRAFFIC_JAM" -> TrafficSpeed.TRAFFIC_JAM
                    else -> TrafficSpeed.UNKNOWN
                }
                if (end > start) add(TrafficInterval(start, end, speed))
            }
        }
    }

    private fun parseLegEnds(route: JSONObject, geometry: List<GeoPoint>, cumulative: DoubleArray): List<Double> {
        val legs = route.optJSONArray("legs") ?: return emptyList()
        return buildList {
            for (index in 0 until legs.length()) {
                val point = legs.optJSONObject(index)?.optJSONObject("endLocation")
                    ?.optJSONObject("latLng") ?: continue
                add(nearestCumulative(GeoPoint(point.optDouble("latitude"), point.optDouble("longitude")), geometry, cumulative))
            }
        }
    }

    /**
     * Parse Google Routes API step navigation instructions into Wayfindr
     * Maneuver objects.
     */
    private fun parseManeuvers(
        route: JSONObject,
        geometry: List<GeoPoint>,
        cumulative: DoubleArray
    ): List<Maneuver> {

        val maneuvers = ArrayList<Maneuver>()

        val legs = route.optJSONArray("legs") ?: return maneuvers

        for (legIndex in 0 until legs.length()) {

            val leg = legs.optJSONObject(legIndex) ?: continue

            val steps = leg.optJSONArray("steps") ?: continue

            for (stepIndex in 0 until steps.length()) {

                val step =
                    steps.optJSONObject(stepIndex) ?: continue

                // A step's navigationInstruction is the maneuver that BEGINS the step, so it
                // happens at the step's start point. Anchoring it at the end showed every turn
                // one step late (the previous step's arrow at the next junction).
                val startLocation =
                    step.optJSONObject("startLocation")
                        ?.optJSONObject("latLng")
                        ?: continue

                val point = GeoPoint(
                    startLocation.optDouble("latitude"),
                    startLocation.optDouble("longitude")
                )

                val navigationInstruction =
                    step.optJSONObject("navigationInstruction")

                val instruction =
                    navigationInstruction
                        ?.optString("instructions")
                        ?.ifBlank { null }
                        ?: "Continue"

                val googleManeuver =
                    navigationInstruction
                        ?.optString("maneuver")
                        .orEmpty()

                val type = ManeuverType.fromGoogle(
                    googleManeuver
                )

                maneuvers.add(
                    Maneuver(
                        type = type,
                        instruction = instruction,
                        location = point,
                        cumulativeMeters = nearestCumulative(
                            point,
                            geometry,
                            cumulative
                        ),
                        googleManeuver = googleManeuver
                    )
                )
            }
        }

        return maneuvers
    }

    /**
     * Google returns protobuf Duration strings, for example:
     *
     * "302s"
     * "3600s"
     * "3723.5s"
     */
    private fun parseDuration(duration: String): Double {
        return duration
            .removeSuffix("s")
            .toDoubleOrNull()
            ?: 0.0
    }

    /**
     * Find the route geometry vertex closest to a maneuver.
     */
    private fun nearestCumulative(
        point: GeoPoint,
        geometry: List<GeoPoint>,
        cumulative: DoubleArray
    ): Double {

        var bestDistance = Double.MAX_VALUE
        var bestCumulative = 0.0

        for (i in geometry.indices) {

            val distance =
                GeoPoint.distMeters(point, geometry[i])

            if (distance < bestDistance) {
                bestDistance = distance
                bestCumulative = cumulative[i]
            }
        }

        return bestCumulative
    }
}
