package com.example.wayfindr.dash.nav

import android.content.Context
import com.example.wayfindr.dash.DashConfig
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import kotlin.math.cos

/**
 * Speed cameras, traffic calming and maxspeed ways along the active route, fetched once per route
 * (Overpass, corridor queries) into filesDir/offline_safety.json. NavPlus falls back to it when its
 * live Overpass lookups fail. File: {"key":..,"c":[[lat,lon,type]..],"w":[[limit,lat,lon,lat,lon..]..]},
 * type 1 = speed camera, 2 = traffic calming; one route at a time, ~100-600 KB for a long route.
 */
object OfflineSafetyCache {
    private class Data(val key: String, val cams: JSONArray, val ways: JSONArray)

    @Volatile private var data: Data? = null
    @Volatile private var busyKey: String? = null
    private const val SPACING_M = 400.0
    private const val CHUNK_PTS = 40          // ~16 km of route per Overpass query
    private const val MAX_CHUNKS = 40         // routes beyond ~640 km are cached only in part
    private const val NEAR_M = 20.0

    private fun enabled() = GPlus.ctx?.let { DashConfig.get(it).offlineSafety.value } == true
    private fun file(c: Context) = File(c.filesDir, "offline_safety.json")

    private fun keyOf(r: Route): String {
        val a = r.geometry.first(); val b = r.geometry.last()
        return "%.4f,%.4f>%.4f,%.4f:%d".format(a.lat, a.lng, b.lat, b.lng, r.totalMeters.toInt())
    }

    /** Fetch the corridor for [r] in the background; no-op when off, already cached or already running. */
    fun prepare(c: Context, r: Route) {
        if (!enabled() || r.geometry.size < 2) return
        val key = keyOf(r)
        if (busyKey == key || load(c)?.key == key) return
        busyKey = key
        Thread({
            try { fetch(c, r, key) } catch (_: Throwable) { } finally { busyKey = null }
        }, "OfflineSafety").apply { isDaemon = true }.start()
    }

    private fun fetch(c: Context, r: Route, key: String) {
        val pts = ArrayList<GeoPoint>()
        var next = 0.0
        for (i in r.geometry.indices) if (r.cumulative[i] >= next) { pts += r.geometry[i]; next = r.cumulative[i] + SPACING_M }
        if (pts.last() != r.geometry.last()) pts += r.geometry.last()
        val cams = JSONArray(); val ways = JSONArray()
        val seen = HashSet<Long>()
        var ok = 0
        var i = 0
        while (i < pts.size - 1 && ok < MAX_CHUNKS) {
            val chunk = pts.subList(i, minOf(i + CHUNK_PTS, pts.size))
            i += CHUNK_PTS - 1
            val line = chunk.joinToString(",") { "%.5f,%.5f".format(it.lat, it.lng) }
            val q = "[out:json][timeout:25];(node(around:150,$line)[\"highway\"=\"speed_camera\"];" +
                "node(around:150,$line)[\"traffic_calming\"];way(around:25,$line)[\"highway\"][\"maxspeed\"];);out tags geom qt;"
            val body = NavPlus.ovGet(q)?.let { runCatching { JSONObject(it).optJSONArray("elements") }.getOrNull() }
            if (body == null) { Thread.sleep(1500); continue }   // keep going: a failed chunk only leaves a gap
            ok++
            for (e in 0 until body.length()) {
                val o = body.optJSONObject(e) ?: continue
                if (!seen.add(o.optLong("id"))) continue
                val tags = o.optJSONObject("tags") ?: continue
                if (o.optString("type") == "node") {
                    cams.put(JSONArray().put(o.optDouble("lat")).put(o.optDouble("lon"))
                        .put(if (tags.optString("highway") == "speed_camera") 1 else 2))
                } else {
                    val lim = NavPlus.parseLimit(tags.optString("maxspeed"))
                    val g = o.optJSONArray("geometry") ?: continue
                    if (lim <= 0 || g.length() < 2) continue
                    val w = JSONArray().put(lim)
                    for (p in 0 until g.length()) g.optJSONObject(p)?.let { w.put(it.optDouble("lat")).put(it.optDouble("lon")) }
                    ways.put(w)
                }
            }
            Thread.sleep(1000)   // be polite to Overpass
        }
        if (ok == 0) return
        val out = JSONObject().put("key", key).put("c", cams).put("w", ways)
        val tmp = File(c.filesDir, "offline_safety.tmp")
        tmp.writeText(out.toString())
        tmp.renameTo(file(c))
        data = Data(key, cams, ways)
    }

    private fun load(c: Context): Data? = data ?: synchronized(this) {
        data ?: runCatching {
            val o = JSONObject(file(c).readText())
            Data(o.getString("key"), o.getJSONArray("c"), o.getJSONArray("w"))
        }.getOrNull()?.also { data = it }
    }

    /** Overpass-shaped JSON of cached cameras/calming within [radiusM] of the point, or null when none cached. */
    fun cameraJson(lat: Double, lng: Double, radiusM: Double): String? {
        if (!enabled()) return null
        val d = GPlus.ctx?.let { load(it) } ?: return null
        val els = JSONArray()
        for (i in 0 until d.cams.length()) {
            val c = d.cams.getJSONArray(i)
            if (dist(lat, lng, c.getDouble(0), c.getDouble(1)) > radiusM) continue
            val tags = JSONObject().put(if (c.getInt(2) == 1) "highway" else "traffic_calming", if (c.getInt(2) == 1) "speed_camera" else "yes")
            els.put(JSONObject().put("lat", c.getDouble(0)).put("lon", c.getDouble(1)).put("tags", tags))
        }
        return JSONObject().put("elements", els).toString()
    }

    /** Highest cached maxspeed (km/h) of a way passing within ~20 m, 0 if none. */
    fun limitAt(lat: Double, lng: Double): Int {
        if (!enabled()) return 0
        val d = GPlus.ctx?.let { load(it) } ?: return 0
        var best = 0
        for (i in 0 until d.ways.length()) {
            val w = d.ways.getJSONArray(i)
            val lim = w.getInt(0)
            if (lim <= best) continue
            var p = 1
            while (p + 3 < w.length()) {
                if (segDist(lat, lng, w.getDouble(p), w.getDouble(p + 1), w.getDouble(p + 2), w.getDouble(p + 3)) <= NEAR_M) { best = lim; break }
                p += 2
            }
        }
        return best
    }

    private fun dist(a: Double, b: Double, c: Double, d: Double) = GeoPoint.distMeters(GeoPoint(a, b), GeoPoint(c, d))

    /** Distance (m) from the point to segment a-b (local flat projection, fine at these scales). */
    private fun segDist(lat: Double, lng: Double, aLat: Double, aLng: Double, bLat: Double, bLng: Double): Double {
        val kx = 111_320.0 * cos(Math.toRadians(lat)); val ky = 111_320.0
        val ax = (aLng - lng) * kx; val ay = (aLat - lat) * ky
        val dx = (bLng - lng) * kx - ax; val dy = (bLat - lat) * ky - ay
        val len2 = dx * dx + dy * dy
        val t = if (len2 == 0.0) 0.0 else (-(ax * dx + ay * dy) / len2).coerceIn(0.0, 1.0)
        return Math.hypot(ax + t * dx, ay + t * dy)
    }
}
