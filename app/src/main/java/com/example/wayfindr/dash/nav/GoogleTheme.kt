package com.example.wayfindr.dash.nav

import android.content.Context
import android.graphics.Color
import com.example.wayfindr.dash.DashConfig
import com.example.wayfindr.dash.MapTheme
import org.json.JSONArray
import org.json.JSONObject

/**
 * The app's map themes (Himalaya, Ralli, Gece, …) applied to Google map tiles through the
 * Map Tiles API session "styles". At night a light theme switches to "Gece"; an already-dark
 * theme stays as it is. The renderer then skips its night colour inversion for Google tiles.
 */
object GoogleTheme {
    /** Set by the renderer every frame: the dash wants the night look. */
    @Volatile @JvmField var dark = false

    fun isDark(t: MapTheme): Boolean = luminance(t.background) < 0.4

    @JvmStatic
    fun current(ctx: Context?): MapTheme {
        val t = runCatching { DashConfig.get(ctx!!).mapTheme.value }.getOrDefault(MapTheme.HIMALAYAN)
        return if (dark && !isDark(t)) MapTheme.NIGHT_EXPEDITION else t
    }

    /** Cache / session key: tiles of different themes never mix. */
    @JvmStatic
    fun key(ctx: Context?): String = current(ctx).name.lowercase()

    @JvmStatic
    fun styles(ctx: Context?): JSONArray {
        val t = current(ctx)
        val nightish = isDark(t)
        val text = if (nightish) "#e9e4d8" else "#2a2a2a"
        val halo = t.background
        val a = JSONArray()
        fun s(feature: String, element: String, vararg stylers: Pair<String, Any>) {
            val st = JSONArray()
            stylers.forEach { (k, v) -> st.put(JSONObject().put(k, v)) }
            a.put(JSONObject().put("featureType", feature).put("elementType", element).put("stylers", st))
        }
        s("all", "geometry", "color" to t.background)
        s("landscape.natural", "geometry", "color" to t.land)
        s("landscape.man_made", "geometry", "color" to t.buildings)
        s("poi", "geometry", "color" to t.land)
        s("poi.park", "geometry", "color" to t.parks)
        s("water", "geometry", "color" to t.water)
        s("road", "geometry.fill", "color" to t.roads)
        s("road", "geometry.stroke", "color" to t.roadCasing)
        s("road.highway", "geometry.fill", "color" to t.roads)
        s("administrative", "geometry.stroke", "color" to t.roadCasing, "weight" to 0.6)
        s("all", "labels.text.fill", "color" to text)
        s("all", "labels.text.stroke", "color" to halo)
        return a
    }

    private fun luminance(hex: String): Double {
        val c = Color.parseColor(hex)
        return (0.299 * Color.red(c) + 0.587 * Color.green(c) + 0.114 * Color.blue(c)) / 255.0
    }
}
