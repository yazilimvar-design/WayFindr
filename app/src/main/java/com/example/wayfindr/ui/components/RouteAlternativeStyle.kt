package com.example.wayfindr.ui.components

import androidx.compose.ui.graphics.Color
import java.util.Locale
import kotlin.math.abs
import kotlin.math.roundToInt

/** Colours for the alternative routes: the same index on the map line and its choice card. */
object RouteAlternativeStyle {
    private val hexColors = listOf("#2F80ED", "#F2994A", "#9B51E0")
    private val composeColors = listOf(Color(0xFF2F80ED), Color(0xFFF2994A), Color(0xFF9B51E0))

    fun mapLibreHex(index: Int): String = hexColors[index.coerceIn(0, hexColors.lastIndex)]

    fun composeColor(index: Int): Color = composeColors[index.coerceIn(0, composeColors.lastIndex)]
}

/** "+1,2 km · +4 dk": how an alternative compares with the first (fastest) route. */
internal fun routeDifferenceLabel(
    routeMeters: Double,
    routeSeconds: Double,
    baseMeters: Double,
    baseSeconds: Double,
): String {
    val distanceDelta = routeMeters - baseMeters
    val distance = if (abs(distanceDelta) < 50.0) {
        "0 km"
    } else {
        val sign = if (distanceDelta > 0) "+" else "−"
        val amount = String.format(Locale.forLanguageTag("tr-TR"), "%.1f", abs(distanceDelta) / 1000.0)
        "$sign$amount km"
    }
    val minutes = ((routeSeconds - baseSeconds) / 60.0).roundToInt()
    val duration = when {
        minutes == 0 -> "0 dk"
        minutes > 0 -> "+$minutes dk"
        else -> "−${abs(minutes)} dk"
    }
    return "$distance · $duration"
}
