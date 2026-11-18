package com.example.wayfindr.dash.map

import com.example.wayfindr.dash.nav.GeoPoint
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.hypot

/**
 * Where to draw the rider on each console frame between ~1 Hz GPS fixes.
 *
 * The caller dead-reckons a prediction for the moment the frame will be SHOWN (fix position +
 * speed × (fix age + [LEAD_S]), along the route when on it). This class turns that piecewise
 * prediction into continuous motion: when a new fix (or a mode change) moves the prediction, the
 * difference is kept as an offset that fades out over [tauFor] — no backward snap, no forward
 * jump — while the prediction itself keeps moving at full speed. There is no position low-pass on
 * top, so no speed-proportional lag (the old camera ease trailed the bike by speed × τ, ~10 m at
 * 120 km/h, plus a 1 Hz sawtooth).
 *
 * Pure Kotlin (no Android types) so the frame benchmark and unit tests run the production code.
 */
class RiderMotion {
    companion object {
        /** Frame → H.264 encode → RTP over WiFi → dash decode/show latency the display leads by (s). */
        const val LEAD_S = 0.5
        /** Cap on the dead-reckoning horizon (fix age + lead, s): bounds over-shoot across a dropout or a stop. */
        const val MAX_PREDICT_S = 3.0
        /** A fix reported older than this on delivery is treated as this old (clock oddities). */
        const val MAX_FIX_AGE_S = 1.5
        /** A correction this large is a reposition, not drift: snap instead of gliding. */
        const val RESYNC_M = 80.0

        /** Correction fade time (s): longer for poorer fixes so their noise is spread out. */
        fun tauFor(accuracyM: Float): Double = when {
            accuracyM > 25f -> 1.0
            accuracyM > 12f -> 0.5
            else -> 0.35
        }

        /** Share of the dead-reckoned distance used for a fix of this accuracy: full to 30 m, none at 60 m. */
        fun confidence(accuracyM: Float): Double = ((60.0 - accuracyM) / 30.0).coerceIn(0.0, 1.0)

        /**
         * Seconds from the fix's own measurement to [nowNs] (elapsedRealtime clock): its age on
         * delivery (capped at [MAX_FIX_AGE_S]) plus the time since it arrived. [fixElapsedNs] ≤ 0
         * (no fix clock) counts from the arrival only.
         */
        fun sinceFixSeconds(fixElapsedNs: Long, arrivalNs: Long, nowNs: Long): Double {
            val ageOnArrival = if (fixElapsedNs in 1..arrivalNs) ((arrivalNs - fixElapsedNs) / 1e9).coerceAtMost(MAX_FIX_AGE_S) else 0.0
            return ageOnArrival + ((nowNs - arrivalNs) / 1e9).coerceAtLeast(0.0)
        }

        /** Dead-reckoned distance (m) for [speedMps] over [seconds] (capped at [MAX_PREDICT_S]). */
        fun distance(speedMps: Float, seconds: Double): Double =
            speedMps * seconds.coerceIn(0.0, MAX_PREDICT_S)

        /** Point [atM] metres along [geometry] ([cumulative] = metres at each vertex), clamped to its ends. */
        fun pointAt(geometry: List<GeoPoint>, cumulative: DoubleArray, atM: Double): GeoPoint? {
            val n = geometry.size
            if (n < 2 || cumulative.size != n || atM.isNaN()) return null
            if (atM <= cumulative[0]) return geometry[0]
            if (atM >= cumulative[n - 1]) return geometry[n - 1]
            var i = java.util.Arrays.binarySearch(cumulative, atM)
            if (i >= 0) return geometry[i]
            i = (-i - 2).coerceIn(0, n - 2)
            val span = cumulative[i + 1] - cumulative[i]
            val t = if (span <= 0.0) 0.0 else (atM - cumulative[i]) / span
            val a = geometry[i]; val b = geometry[i + 1]
            return GeoPoint(a.lat + (b.lat - a.lat) * t, a.lng + (b.lng - a.lng) * t)
        }

        /** Road bearing (deg) around [atM] on the route: from 8 m behind to 12 m ahead (smooth through vertices). */
        fun bearingAt(geometry: List<GeoPoint>, cumulative: DoubleArray, atM: Double): Float? {
            val a = pointAt(geometry, cumulative, atM - 8.0) ?: return null
            val b = pointAt(geometry, cumulative, atM + 12.0) ?: return null
            if (GeoPoint.distMeters(a, b) < 5.0) return null
            return GeoPoint.bearing(a, b).toFloat()
        }

        private const val M_PER_DEG = 111_195.0
    }

    private var has = false
    private var dispLat = 0.0
    private var dispLng = 0.0
    // Correction still to fade out (metres east / north of the prediction).
    private var offE = 0.0
    private var offN = 0.0
    // Last prediction and its velocity (m/s east / north), to extrapolate the old prediction to "now"
    // when a new fix replaces it (else the rebase itself would kick the marker back by speed × dt).
    private var lastPredLat = 0.0
    private var lastPredLng = 0.0
    private var velE = 0.0
    private var velN = 0.0
    private var lastNs = 0L

    /** Forget everything: the next [step] starts exactly at its prediction (new stream, GPS lost). */
    fun reset() { has = false; offE = 0.0; offN = 0.0; velE = 0.0; velN = 0.0 }

    /**
     * Displayed rider position for the frame at [nowNs] (any monotonic clock) given this frame's
     * prediction. [rebase] = the prediction was rebuilt since the last call (new fix, on/off route,
     * moving/stopped): the jump is turned into a fading offset instead of being shown.
     */
    fun step(predLat: Double, predLng: Double, rebase: Boolean, nowNs: Long, tauS: Double): Pair<Double, Double> {
        if (!has) {
            has = true
            dispLat = predLat; dispLng = predLng
            lastPredLat = predLat; lastPredLng = predLng
            offE = 0.0; offN = 0.0; velE = 0.0; velN = 0.0
            lastNs = nowNs
            return predLat to predLng
        }
        val dt = ((nowNs - lastNs) / 1e9).coerceIn(0.0, 0.5)
        lastNs = nowNs
        val mPerDegLng = M_PER_DEG * cos(Math.toRadians(predLat)).coerceAtLeast(0.01)
        if (rebase) {
            // Where the OLD prediction would be now, plus what was still fading.
            val oldE = (lastPredLng - predLng) * mPerDegLng + velE * dt + offE
            val oldN = (lastPredLat - predLat) * M_PER_DEG + velN * dt + offN
            if (hypot(oldE, oldN) > RESYNC_M) { offE = 0.0; offN = 0.0 } else { offE = oldE; offN = oldN }
        } else if (dt > 0.0) {
            velE = (predLng - lastPredLng) * mPerDegLng / dt
            velN = (predLat - lastPredLat) * M_PER_DEG / dt
        }
        lastPredLat = predLat; lastPredLng = predLng
        val k = if (tauS > 0.0) exp(-dt / tauS) else 0.0
        // Fade the correction, but never so fast that the marker moves against the direction of
        // travel (a fix that lands behind the shown position slows the marker, it doesn't pull it back).
        var fadeE = offE * (1 - k); var fadeN = offN * (1 - k)
        val speed = hypot(velE, velN)
        if (speed > 1.0 && dt > 0.0) {
            val uE = velE / speed; val uN = velN / speed
            // Displacement this frame without any fade (prediction step + carried correction).
            val moveE = (predLng - dispLng) * mPerDegLng + offE
            val moveN = (predLat - dispLat) * M_PER_DEG + offN
            val along = moveE * uE + moveN * uN
            val fadeAlong = fadeE * uE + fadeN * uN
            if (fadeAlong > 0.0 && along - fadeAlong < 0.0) {
                val keep = (along / fadeAlong).coerceIn(0.0, 1.0)
                fadeE *= keep; fadeN *= keep
            }
        }
        offE -= fadeE; offN -= fadeN
        dispLat = predLat + offN / M_PER_DEG
        dispLng = predLng + offE / mPerDegLng
        return dispLat to dispLng
    }
}
