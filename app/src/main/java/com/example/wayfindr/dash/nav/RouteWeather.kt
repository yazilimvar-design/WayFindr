package com.example.wayfindr.dash.nav

import org.json.JSONObject

/** Rain on the remaining route: samples ~5 points, asks open-meteo for each at its ETA hour. */
class RouteWeather {
    @Volatile private var checkedAt = 0L
    @Volatile private var busy = false
    @Volatile private var pending: String? = null
    private var announced = false

    /** New route (reroute included): look again at once, but don't repeat an announcement already made. */
    fun reset() { checkedAt = 0L; pending = null }
    /** New destination: announce again if it rains. */
    fun newTrip() { reset(); announced = false }

    /** Text to announce now, or null. Starts a background check at route start and every 20 min. */
    fun tick(r: Route, progressM: Double): String? {
        val now = System.currentTimeMillis()
        if (!busy && now - checkedAt >= CHECK_EVERY_MS) {
            busy = true; checkedAt = now
            Thread({
                try { pending = check(r, progressM) } catch (_: Throwable) { } finally { busy = false }
            }, "RouteWeather").apply { isDaemon = true }.start()
        }
        val p = pending ?: return null
        pending = null
        if (announced) return null
        announced = true
        return p
    }

    private fun check(r: Route, progressM: Double): String? {
        val left = r.totalMeters - progressM
        if (left < 2_000 || r.geometry.size < 2) return null
        val speed = if (r.totalSeconds > 0) (r.totalMeters / r.totalSeconds).coerceIn(5.0, 40.0) else 11.0
        for (i in 1..POINTS) {
            val at = progressM + left * i / POINTS
            val p = NavPlus.pointAt(r.geometry, r.cumulative, at) ?: continue
            val hour = (((at - progressM) / speed) / 3600.0).let { Math.round(it).toInt() }.coerceIn(0, 23)
            val json = NavPlus.httpGet("https://api.open-meteo.com/v1/forecast?latitude=${p.lat}&longitude=${p.lng}" +
                "&hourly=precipitation_probability,precipitation&forecast_hours=24&timezone=auto") ?: continue
            val h = runCatching { JSONObject(json).getJSONObject("hourly") }.getOrNull() ?: continue
            if (h.optJSONArray("precipitation_probability")?.optInt(hour, 0)?.let { it >= PROB } == true ||
                (h.optJSONArray("precipitation")?.optDouble(hour, 0.0) ?: 0.0) >= MM) {
                return "Yaklaşık ${maxOf(1, Math.round((at - progressM) / 1000.0).toInt())} km sonra yağmur olabilir"
            }
        }
        return null
    }

    private companion object {
        const val CHECK_EVERY_MS = 20 * 60_000L
        const val POINTS = 5
        const val PROB = 60
        const val MM = 0.5
    }
}
