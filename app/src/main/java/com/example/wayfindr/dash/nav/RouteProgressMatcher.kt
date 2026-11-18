package com.example.wayfindr.dash.nav

/** Where the rider is along the route: distance travelled, offset from it, its heading there. */
data class RouteProgressMatch(
    val progressMeters: Double,
    val lateralDistanceMeters: Double,
    val headingDegrees: Float,
    val snappedPoint: GeoPoint,
)

/**
 * Snaps a fix onto the route geometry with continuity: segments near the current progress are
 * preferred (small step back allowed, a speed-scaled step forward), so a route that doubles back
 * or runs alongside itself doesn't make the progress jump to the wrong pass. The GPS bearing, when
 * known, breaks ties between overlapping segments. Falls back to a whole-route search when the
 * local window finds nothing or only a far-off match.
 */
object RouteProgressMatcher {
    fun match(
        geometry: List<GeoPoint>,
        cumulativeMeters: DoubleArray,
        position: GeoPoint,
        currentProgressMeters: Double,
        gpsBearingDegrees: Float? = null,
        speedMetersPerSecond: Double = 0.0,
    ): RouteProgressMatch? {
        if (geometry.size < 2 || cumulativeMeters.size != geometry.size) return null
        if (!currentProgressMeters.isFinite() || currentProgressMeters < 0.0) return null
        val safeSpeed = speedMetersPerSecond.takeIf { it.isFinite() && it > 0.0 } ?: 0.0
        val forwardAllowance = maxOf(40.0, 4.0 * safeSpeed)
        val trustedBearing = gpsBearingDegrees?.takeIf { it.isFinite() }

        fun search(lo: Double, hi: Double, global: Boolean): RouteProgressMatch? {
            var bestScore = Double.POSITIVE_INFINITY
            var best: RouteProgressMatch? = null
            // Start at the first segment that can reach [lo] (cumulative is ascending) and stop past
            // [hi]: same segments as a full scan, without walking a 20 000-point route every tick.
            var first = java.util.Arrays.binarySearch(cumulativeMeters, lo)
            if (first < 0) first = -first - 1
            while (first > 0 && cumulativeMeters[first] >= lo) first--   // duplicate vertices
            for (index in first until geometry.lastIndex) {
                if (cumulativeMeters[index] > hi) break
                if (cumulativeMeters[index + 1] < lo) continue
                val start = geometry[index]
                val end = geometry[index + 1]
                val (projection, fraction) = GeoPoint.projectOnSegment(position, start, end)
                val lateral = GeoPoint.distMeters(position, projection)
                val segmentLength = GeoPoint.distMeters(start, end)
                val candidateProgress = cumulativeMeters[index] + segmentLength * fraction
                val routeHeading = GeoPoint.bearing(start, end).toFloat()
                val backwardMeters = (currentProgressMeters - candidateProgress - 12.0).coerceAtLeast(0.0)
                val forwardMeters = (candidateProgress - currentProgressMeters - forwardAllowance).coerceAtLeast(0.0)
                val continuityPenalty =
                    if (global) 0.2 * backwardMeters + 0.04 * forwardMeters
                    else 0.7 * backwardMeters + 1.25 * forwardMeters
                val headingPenalty =
                    if (trustedBearing != null) angularDifference(trustedBearing, routeHeading) * 0.5 else 0.0
                val score = lateral + continuityPenalty + headingPenalty
                if (score < bestScore) {
                    bestScore = score
                    best = RouteProgressMatch(candidateProgress, lateral, routeHeading, projection)
                }
            }
            return best
        }

        val local = search(
            (currentProgressMeters - 60.0).coerceAtLeast(0.0), currentProgressMeters + 1000.0, global = false,
        ) ?: return search(0.0, cumulativeMeters.last(), global = true)
        if (local.lateralDistanceMeters > 80.0) {
            val global = search(0.0, cumulativeMeters.last(), global = true)
            if (global != null && global.lateralDistanceMeters < local.lateralDistanceMeters) return global
        }
        return local
    }

    private fun angularDifference(a: Float, b: Float): Double {
        val delta = (((a - b) % 360f) + 540f) % 360f - 180f
        return kotlin.math.abs(delta).toDouble()
    }
}
