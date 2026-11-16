package com.example.wayfindr.dash.map

/**
 * Small, pure rules shared by the location tracker and the navigation loop, kept here so they can
 * be unit-tested without Android: fix freshness, jump plausibility, which last-known fix to start a
 * route from, and when the rider is confidently off the route.
 */
object NavigationReliability {
    /** Nothing on this bike sustains 85 m/s (306 km/h) — a faster jump is a corrupt fix. */
    const val MAX_PLAUSIBLE_SPEED_MPS = 85.0
    /** A fix less accurate than this can't decide an off-route call on its own. */
    private const val MAX_TRUSTED_ACCURACY_M = 60.0

    fun isFreshFixAge(ageMs: Long, maxAgeMs: Long): Boolean = ageMs in 0..maxAgeMs

    /**
     * Lower is better: accuracy radius (100 m when unknown) plus 2 m per second of age. Unknown or
     * negative age is never chosen over a dated fix.
     */
    fun routeOriginScore(accuracyMeters: Double?, ageMs: Long): Double {
        val accuracy = accuracyMeters?.takeIf { it.isFinite() && it >= 0.0 } ?: 100.0
        val agePenalty = if (ageMs < 0 || ageMs == Long.MAX_VALUE) Double.POSITIVE_INFINITY
            else ageMs / 1000.0 * 2.0
        return accuracy + agePenalty
    }

    /** Whether moving [distanceMeters] in [intervalSeconds] is physically possible. */
    fun isPlausibleJump(
        distanceMeters: Double,
        intervalSeconds: Double,
        maxSpeedMps: Double = MAX_PLAUSIBLE_SPEED_MPS,
    ): Boolean {
        if (!distanceMeters.isFinite() || distanceMeters < 0.0) return false
        if (!intervalSeconds.isFinite() || intervalSeconds < 0.0 || maxSpeedMps <= 0.0) return false
        return if (intervalSeconds == 0.0) distanceMeters <= 1.0
            else distanceMeters / intervalSeconds <= maxSpeedMps
    }

    /**
     * Off route only when the snap distance clearly exceeds what the fix's error radius explains,
     * or is ambiguous but the heading also disagrees with the route. Untrusted fixes never count.
     */
    fun isConfidentlyOffRoute(
        snapDistanceMeters: Double,
        accuracyMeters: Double?,
        headingKnown: Boolean,
        headingDisagrees: Boolean,
    ): Boolean {
        val accuracy = accuracyMeters?.takeIf { it.isFinite() && it >= 0.0 } ?: return false
        if (!snapDistanceMeters.isFinite() || snapDistanceMeters < 0.0 || accuracy > MAX_TRUSTED_ACCURACY_M) return false
        val clearGap = maxOf(75.0, 3.0 * accuracy)
        val ambiguousGap = maxOf(45.0, 2.0 * accuracy)
        if (snapDistanceMeters > clearGap) return true
        return snapDistanceMeters > ambiguousGap && headingKnown && headingDisagrees
    }
}

/**
 * Debounces the off-route decision: it only fires after [requiredFixes] distinct GPS fixes
 * (identified by a strictly increasing token) spanning at least [minimumSpanMs].
 */
class OffRouteConfirmation(
    private val requiredFixes: Int = 3,
    private val minimumSpanMs: Long = 3_000L,
) {
    private var firstObservedAtMs = 0L
    private var lastFixToken = 0L
    private var distinctFixes = 0

    /** Records an off-route observation; true once it is confirmed. */
    fun record(fixToken: Long, nowMs: Long): Boolean {
        if (fixToken <= 0) return false
        if (fixToken > lastFixToken) {
            if (distinctFixes == 0) firstObservedAtMs = nowMs
            lastFixToken = fixToken
            distinctFixes++
        }
        return distinctFixes >= requiredFixes && nowMs - firstObservedAtMs >= minimumSpanMs
    }

    fun reset() {
        firstObservedAtMs = 0L
        lastFixToken = 0L
        distinctFixes = 0
    }
}
