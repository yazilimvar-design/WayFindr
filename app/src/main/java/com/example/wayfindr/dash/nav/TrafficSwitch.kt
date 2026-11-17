package com.example.wayfindr.dash.nav

import kotlin.math.max
import kotlin.math.roundToInt

/** When a faster route found through live traffic is worth switching to (pure, unit-tested). */
object TrafficSwitch {
    const val MIN_GAIN_S = 180.0          // at least 3 min ...
    const val MIN_GAIN_FRACTION = 0.10    // ... or 10 % of the time left, whichever is larger
    const val MIN_GAP_MS = 300_000L       // no flip-flop: 5 min between switches

    /** Seconds left on [totalSeconds] of a [totalMeters] route after [progressM] m. */
    fun remainingSeconds(totalSeconds: Double, totalMeters: Double, progressM: Double): Double =
        if (totalMeters > 0.0) totalSeconds * (1.0 - progressM / totalMeters).coerceIn(0.0, 1.0) else totalSeconds

    fun worthSwitching(currentRemainingS: Double, altS: Double, nowMs: Long, lastSwitchMs: Long): Boolean =
        currentRemainingS > 0.0 && altS > 0.0 && nowMs - lastSwitchMs >= MIN_GAP_MS &&
            currentRemainingS - altS >= max(MIN_GAIN_S, MIN_GAIN_FRACTION * currentRemainingS)

    fun message(gainS: Double) = "Trafik nedeniyle daha hızlı rota: ${(gainS / 60).roundToInt()} dakika kazanç"
}
