package com.example.wayfindr.dash.map

/**
 * Weak-GPS navigation rules (pure, unit-tested). V60 froze the console when the fix got poor or
 * slow: the marker's dead-reckoning stopped after [RiderMotion.MAX_PREDICT_S] and was scaled to
 * nothing at 60 m accuracy, the marker left the route beyond a fixed 35 m snap band, and route
 * progress (remaining distance, turn countdown) only moved when a fresh fix arrived — not at all
 * once the last fix was older than 10 s. These rules keep navigation going instead:
 *  - a poor fix is still used, snapped to the route within a band that grows with its accuracy,
 *    and weighted against the dead-reckoned progress ([weight], [blend]);
 *  - between fixes (gap > [GAP_S]) progress advances along the route at the last speed, for at
 *    most [MAX_S] — the rider is assumed to follow the route, like a car navigator in a tunnel.
 */
object WeakGps {
    /** A fix with accuracy up to this is used as is; poorer ones are weighted. */
    const val GOOD_ACC_M = 25.0
    /** Dead reckoning along the route stops this long after the last fix (s). */
    const val MAX_S = 45.0
    /** Fix age before progress starts to dead-reckon (s): a normal 1 Hz stream never triggers it. */
    const val GAP_S = 1.2
    /** Below this speed (m/s) nothing is dead-reckoned (stopped / walking pace). */
    const val MIN_SPEED_MPS = 1.0
    private const val MIN_SNAP_M = 35.0
    private const val MAX_SNAP_M = 120.0

    /** Lateral distance (m) within which a fix of [accuracyM] is drawn on the route line. */
    fun snapBand(accuracyM: Double?): Double =
        maxOf(MIN_SNAP_M, minOf((accuracyM ?: 0.0) * 1.5, MAX_SNAP_M))

    /** A fix too coarse to judge "off route" (as NavigationReliability) can't take the rider off the line. */
    const val UNTRUSTED_ACC_M = 60.0

    /**
     * Treat the rider as on the route for snapping / dead reckoning: within [snapBand], or the fix is
     * too coarse to say otherwise (a 150 m fix lands 100+ m beside the road while still riding it).
     */
    fun onRoute(lateralM: Double, accuracyM: Double?): Boolean =
        lateralM >= 0.0 && (lateralM <= snapBand(accuracyM) || (accuracyM ?: 0.0) > UNTRUSTED_ACC_M)

    /** Share of a fix's own route progress kept against the dead-reckoned one: 1 up to 25 m, 0.2 at 125 m+. */
    fun weight(accuracyM: Double?): Double {
        val a = accuracyM?.takeIf { it.isFinite() && it > 0.0 } ?: return 1.0
        return if (a <= GOOD_ACC_M) 1.0 else (GOOD_ACC_M / a).coerceIn(0.2, 1.0)
    }

    /** Progress to commit for a new fix: its matched progress weighted against the dead-reckoned one. */
    fun blend(deadReckonedM: Double?, matchedM: Double, accuracyM: Double?): Double {
        val dr = deadReckonedM ?: return matchedM
        val w = weight(accuracyM)
        return dr + w * (matchedM - dr)
    }

    /** Metres to add to the last fix's route progress [ageS] after it was measured, at [speedMps]. */
    fun extraMeters(speedMps: Double, ageS: Double): Double =
        if (!speedMps.isFinite() || speedMps < MIN_SPEED_MPS || !ageS.isFinite() || ageS <= GAP_S) 0.0
        else speedMps * ageS.coerceAtMost(MAX_S)

    /** Where dead reckoning puts a new fix [dtS] after the previous one: no [GAP_S] threshold (used to weigh it). */
    fun predictedMeters(speedMps: Double, dtS: Double): Double =
        if (!speedMps.isFinite() || speedMps < MIN_SPEED_MPS || !dtS.isFinite() || dtS <= 0.0) 0.0
        else speedMps * dtS.coerceAtMost(MAX_S)

    /** True while a fix [ageS] old may still be dead-reckoned. */
    fun canDeadReckon(speedMps: Double, ageS: Double): Boolean =
        speedMps >= MIN_SPEED_MPS && ageS in 0.0..MAX_S

    /**
     * Speed (m/s) to dead-reckon with after a new fix. The fix's own speed when it has one;
     * otherwise (network / cell fixes carry none) the progress made since the previous fix,
     * eased in, so a coarse position's noise can't jerk it; with neither, the old speed is held.
     */
    fun nextSpeed(prevMps: Double, fixSpeedMps: Double?, derivedMps: Double?): Double = when {
        fixSpeedMps != null && fixSpeedMps.isFinite() && fixSpeedMps >= 0.0 -> fixSpeedMps
        derivedMps != null && derivedMps.isFinite() -> prevMps + (derivedMps.coerceIn(0.0, 70.0) - prevMps) * 0.3
        else -> prevMps
    }
}
