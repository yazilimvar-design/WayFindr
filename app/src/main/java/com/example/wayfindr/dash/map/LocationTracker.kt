package com.example.wayfindr.dash.map

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.SystemClock
import android.util.Log
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.util.Dbg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/** GPS position via LocationManager (no Play Services dependency). */
class LocationTracker(context: Context) {
    companion object {
        private const val TAG = "LocationTracker"
        // While a GPS fix is younger than this, ignore coarse NETWORK fixes entirely.
        private const val GPS_STALE_MS = 10_000L
        /**
         * Debug/CI only (TestHooks): while a simulation feeds [debugFix], EVERY tracker ignores real
         * fixes — LocPlus' provider choice is global, so another view model's tracker still taking the
         * emulator's fixes would make it reject the simulated ones.
         */
        @Volatile @JvmStatic var debugFeed = false

        /** A fix older than this is not used for navigation (route origin, off-route, reroute). */
        const val MAX_NAV_FIX_AGE_MS = 10_000L

        @JvmStatic
        fun isFreshForNavigation(
            loc: Location,
            nowElapsedNanos: Long = SystemClock.elapsedRealtimeNanos(),
            nowWallMs: Long = System.currentTimeMillis(),
        ): Boolean {
            val age = fixAgeMs(loc, nowElapsedNanos, nowWallMs) ?: return false
            return NavigationReliability.isFreshFixAge(age, MAX_NAV_FIX_AGE_MS)
        }

        /**
         * Age of [loc] in ms, from the monotonic elapsed-realtime clock when the fix has one (wall
         * clocks on GPS fixes can be off), else from its wall time; null if it lies in the future.
         */
        @JvmStatic
        fun fixAgeMs(loc: Location, nowElapsedNanos: Long, nowWallMs: Long): Long? {
            val fixElapsed = loc.elapsedRealtimeNanos
            if (fixElapsed > 0 && nowElapsedNanos >= fixElapsed) return (nowElapsedNanos - fixElapsed) / 1_000_000L
            return (nowWallMs - loc.time).takeIf { it >= 0 }
        }

        /** Seconds between two fixes, preferring the monotonic clock. */
        private fun elapsedDeltaSeconds(previous: Location, next: Location): Double {
            val previousElapsed = previous.elapsedRealtimeNanos
            val nextElapsed = next.elapsedRealtimeNanos
            if (previousElapsed > 0 && nextElapsed > 0) return (nextElapsed - previousElapsed) / 1e9
            return (next.time - previous.time) / 1000.0
        }
    }

    private val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val _location = MutableStateFlow<Location?>(null)
    val location = _location.asStateFlow()

    /** True while Google Navigation feeds road-snapped fixes via [feedSnapped]; raw fixes are then ignored. */
    @Volatile var snappedFeed = false

    /** A road-snapped fix from the Navigation SDK (already filtered and on the road). */
    fun feedSnapped(loc: Location) {
        if (!snappedFeed) return
        // The freshness checks need a fix clock; never let a missing one stall navigation.
        val fix = Location(loc).apply {
            if (time == 0L) time = System.currentTimeMillis()
            if (elapsedRealtimeNanos == 0L) elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
        }
        _location.value = fix
        NavPlus.onFix(fix)
    }

    /** Debug/CI only: one simulated fix through the same acceptance rules as a real one. */
    fun debugFix(loc: Location) { if (debugFeed) onFix(loc) }

    private val listener = LocationListener { loc -> if (!debugFeed) onFix(loc) }

    private fun onFix(loc: Location) {
        if (snappedFeed) return
        val cur = _location.value
        if (acceptFix(cur, loc)) {
            _location.value = loc
            NavPlus.onFix(loc)
            Dbg.d(TAG) { "fix ${loc.provider} acc=${loc.accuracy} (${loc.latitude},${loc.longitude})" }
        } else {
            Log.d(TAG, "REJECT ${loc.provider} acc=${loc.accuracy} dt=${if (cur != null) elapsedDeltaSeconds(cur, loc) else 0.0}s")
            if (debugFeed) com.example.wayfindr.data.RideDiagnostics.log("gps", "sim fix REJECTED ${loc.provider} acc=${loc.accuracy}")
        }
    }

    /**
     * Decide whether [loc] should replace [cur]. This is the FIRST line against teleport/jitter:
     * a clean fix stream is what the camera smoothing and predictor assume.
     *  - A stale first fix is never accepted, and time never runs backwards.
     *  - [LocPlus] provider preference (GPS / fused / network) may reject the new fix.
     *  - GPS always wins over a coarse NETWORK fix while a fresh GPS fix exists (else the marker
     *    jumps to a cell-tower estimate km away — the classic screen-off failure).
     *  - Physically impossible jumps (>306 km/h) are dropped outright.
     *  - Accuracy-+speed-aware outlier gate: a jump well beyond what our speed and the two fixes'
     *    error radii can explain is a GPS glitch — reject it so it never reaches the map. Recovers
     *    after a few rejects (or a long gap) so a real reposition still resyncs.
     */
    private fun acceptFix(cur: Location?, loc: Location): Boolean {
        if (cur == null) {
            if (!isFreshForNavigation(loc)) return false
            rejectStreak = 0
            return true
        }
        val dt = elapsedDeltaSeconds(cur, loc)
        if (dt < 0.0) return false
        if ((dt == 0.0 && loc.time < cur.time) || LocPlus.preferReject(cur, loc, dt)) return false
        LocPlus.stillLock(loc)
        val isGps = loc.provider == LocationManager.GPS_PROVIDER
        if (LocPlus.rank(loc.provider) != 3 && !isGps && cur.provider == LocationManager.GPS_PROVIDER &&
            isFreshForNavigation(cur)
        ) return false
        val jump = cur.distanceTo(loc).toDouble()
        // Hard cap: nothing on this bike sustains 85 m/s — that's a corrupt fix.
        if (!NavigationReliability.isPlausibleJump(jump, dt)) return reject()
        // A last-known fix can be stale when navigation starts. After a long gap there is no
        // reliable way to judge the distance or speed between fixes, so accept the fresh fix to
        // resynchronize instead of rejecting every update behind the outlier gate.
        if (dt > GPS_STALE_MS / 1000.0) {
            rejectStreak = 0
            return true
        }
        // Outlier gate (skipped for long gaps, which legitimately cover ground, and after a few
        // rejects so we resync to a real reposition instead of freezing on the wrong spot).
        if (dt in 0.0..8.0 && rejectStreak < 3) {
            val oldSpeed = if (cur.hasSpeed()) cur.speed else 0f
            val newSpeed = if (loc.hasSpeed()) loc.speed else 0f
            val plausibleSpeed = maxOf(oldSpeed, newSpeed).coerceAtLeast(1f) // m/s, floored
            val expected = plausibleSpeed * dt
            val oldAccuracy = if (cur.hasAccuracy()) cur.accuracy else 50f
            val newAccuracy = if (loc.hasAccuracy()) loc.accuracy else 50f
            val noise = oldAccuracy + newAccuracy                               // combined error radius
            // Urban canyon / building facade: reported accuracy is often optimistic while the
            // point jumps tens of metres. A tight gate dropped those fixes, starved the age
            // clock, and flashed "GPS lost" even though GPS was still talking.
            val gate = expected + noise * 2.5 + 25.0
            if (jump > gate && jump > 40.0) return reject()
        }
        rejectStreak = 0
        return true
    }

    // Consecutive outlier rejects — bounded so a genuine reposition can't lock the marker out.
    private var rejectStreak = 0
    private fun reject(): Boolean { rejectStreak++; return false }

    private var running = false
    val isRunning: Boolean get() = running
    private var handlerThread: android.os.HandlerThread? = null

    /** Requires ACCESS_FINE_LOCATION at runtime; no-ops without it. */
    @SuppressLint("MissingPermission")
    fun start() {
        if (running) return
        try {
            _location.value = bestKnown(requireFresh = true)

            // Move location updates to a dedicated background thread. Using the main looper
            // can lead to throttling when the screen is off and the UI thread is de-prioritized.
            handlerThread = android.os.HandlerThread("LocationTracker").apply { start() }
            val looper = handlerThread!!.looper
            
            for (provider in listOf(LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER)) {
                if (lm.isProviderEnabled(provider)) {
                    lm.requestLocationUpdates(provider, 500L, 0f, listener, looper)
                }
            }
            LocPlus.addFused(lm, listener, looper)
            running = true
            Log.i(TAG, "Location updates started on background thread")
        } catch (e: SecurityException) {
            Log.w(TAG, "Location permission missing — GPS disabled")
        } catch (e: Exception) {
            Log.w(TAG, "GPS start failed: ${e.message}")
        }
    }

    fun stop() {
        if (!running) return
        lm.removeUpdates(listener)
        handlerThread?.quitSafely()
        handlerThread = null
        running = false
    }

    /** Best fresh last-known fix without starting updates (for routing before connecting). */
    @SuppressLint("MissingPermission")
    fun lastKnown(): Location? = try {
        bestKnown(requireFresh = true)
    } catch (e: Exception) {
        null
    }

    /** Best last-known fix of any age (route origin fallback when no fresh fix exists). */
    fun lastKnownAny(): Location? = try {
        bestKnown(requireFresh = false)
    } catch (_: Throwable) {
        null
    }

    /** The current fix, else the best last-known one of any age — for showing the map only. */
    @SuppressLint("MissingPermission")
    fun lastKnownForDisplay(): Location? = try {
        _location.value ?: bestKnown(requireFresh = false)
    } catch (e: Exception) {
        null
    }

    /** Lowest [NavigationReliability.routeOriginScore] among the providers' last-known fixes and ours. */
    @SuppressLint("MissingPermission")
    private fun bestKnown(requireFresh: Boolean): Location? {
        val candidates = listOf(
            LocationManager.GPS_PROVIDER, LocationManager.NETWORK_PROVIDER, LocationManager.PASSIVE_PROVIDER,
        ).mapNotNull { runCatching { lm.getLastKnownLocation(it) }.getOrNull() } + listOfNotNull(_location.value)
        val nowElapsed = SystemClock.elapsedRealtimeNanos()
        val nowWall = System.currentTimeMillis()
        return candidates
            .filter { !requireFresh || isFreshForNavigation(it, nowElapsed, nowWall) }
            .minByOrNull {
                NavigationReliability.routeOriginScore(
                    if (it.hasAccuracy()) it.accuracy.toDouble() else null,
                    fixAgeMs(it, nowElapsed, nowWall) ?: Long.MAX_VALUE,
                )
            }
    }
}
