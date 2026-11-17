package com.example.wayfindr.dash.nav

import java.util.Arrays

/** Sharp-curve detection on the route line ahead, and a once-per-curve spoken warning. */
class CurveWarner {
    data class Curve(val apexM: Double, val right: Boolean, val angle: Double) {
        val hairpin get() = angle >= HAIRPIN_DEG
    }

    private val announced = HashSet<Long>()

    fun reset() = announced.clear()

    /** Text to speak now, or null. [progressM] is the rider's route progress, [speedMps] the GPS speed. */
    fun tick(r: Route, progressM: Double, speedMps: Double): String? {
        if (speedMps < MIN_SPEED_MPS) return null
        val lead = (speedMps * 9.0).coerceIn(150.0, 250.0)   // ~150 m at 60 km/h, ~250 m at 100 km/h
        val c = find(r.geometry, r.cumulative, progressM, AHEAD_M, r.maneuvers.map { it.cumulativeMeters })
            .firstOrNull { it.apexM - progressM in 30.0..lead && announced.add((it.apexM / STEP).toLong()) }
            ?: return null
        if (c.hairpin) return "Firkete viraj"
        val d = ((c.apexM - progressM) / 50).toInt() * 50
        return "Keskin ${if (c.right) "sağ" else "sol"} viraj, $d metre"
    }

    companion object {
        const val STEP = 10.0
        const val WINDOW_STEPS = 8          // 80 m
        const val SHARP_DEG = 60.0
        const val HAIRPIN_DEG = 120.0
        const val AHEAD_M = 400.0
        const val MANEUVER_NEAR_M = 40.0
        const val MIN_SPEED_MPS = 4.0

        /**
         * Curves in (fromM, fromM+aheadM]: heading change >= 60 deg within 80 m, skipping those within
         * 40 m of a maneuver in [maneuversM] (the turn announcer already covers them).
         */
        fun find(
            geometry: List<GeoPoint>, cumulative: DoubleArray, fromM: Double, aheadM: Double,
            maneuversM: List<Double> = emptyList(),
        ): List<Curve> {
            if (geometry.size < 2 || cumulative.size != geometry.size) return emptyList()
            val s0 = Math.floor(fromM / STEP) * STEP   // fixed grid: a curve keeps its id as progress moves
            val n = (aheadM / STEP).toInt() + 1
            val pts = (0..n).map { pointAt(geometry, cumulative, s0 + it * STEP) }
            val hd = (0 until n).map { GeoPoint.bearing(pts[it], pts[it + 1]) }
            // Sum of signed per-step turns, so a ~180 deg hairpin keeps its direction (no +-180 wrap).
            val turn = (0 until n - 1).map { ((hd[it + 1] - hd[it] + 540.0) % 360.0) - 180.0 }
            val delta = (0 until n - WINDOW_STEPS).map { k -> (k until k + WINDOW_STEPS).sumOf { turn[it] } }
            val out = ArrayList<Curve>()
            var k = 0
            while (k < delta.size) {
                if (Math.abs(delta[k]) < SHARP_DEG) { k++; continue }
                val right = delta[k] > 0
                var j = k
                var peak = 0.0
                while (j < delta.size && Math.abs(delta[j]) >= SHARP_DEG && (delta[j] > 0) == right) {
                    peak = maxOf(peak, Math.abs(delta[j])); j++
                }
                val apex = s0 + (k + j - 1 + WINDOW_STEPS) / 2.0 * STEP
                if (apex > fromM && maneuversM.none { Math.abs(it - apex) <= MANEUVER_NEAR_M + (j - k) * STEP / 2 })
                    out += Curve(apex, right, peak)
                k = j
            }
            return out
        }

        private fun pointAt(g: List<GeoPoint>, cum: DoubleArray, s: Double): GeoPoint {
            if (s <= cum[0]) return g[0]
            if (s >= cum[cum.size - 1]) return g[g.size - 1]
            var i = Arrays.binarySearch(cum, s)
            if (i < 0) i = -i - 2
            val seg = cum[i + 1] - cum[i]
            val t = if (seg > 0) (s - cum[i]) / seg else 0.0
            return GeoPoint(g[i].lat + (g[i + 1].lat - g[i].lat) * t, g[i].lng + (g[i + 1].lng - g[i].lng) * t)
        }
    }
}
