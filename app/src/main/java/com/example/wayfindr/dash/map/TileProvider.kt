package com.example.wayfindr.dash.map

import android.app.ActivityManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.util.Log
import android.util.LruCache
import com.example.wayfindr.dash.nav.GPlus
import com.example.wayfindr.dash.nav.GeoPoint
import com.example.wayfindr.dash.nav.NavPlus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ConcurrentHashMap

/**
 * OSM raster tile provider with memory + disk cache.
 *
 * Live OpenStreetMap tiles only (OSM's tile policy forbids pre-seeding / route caching, so every
 * prefetch below is skipped). [GPlus] may swap in Google Map Tiles when the user has a key, and
 * [NavPlus.mbTile] serves tiles from an imported offline .mbtiles file. Night mode is a draw-time
 * ColorMatrix in [MapRenderer] so one disk cache serves both looks and the mode can flip offline.
 *
 * Cache misses fetch through whichever network has connectivity (cellular when bound to the dash
 * WiFi), rate-limited to avoid hot loops.
 */
class TileProvider(context: Context, private val scope: CoroutineScope) {
    companion object {
        private const val TAG = "TileProvider"
        // Free source: OpenStreetMap's own tile server. Its usage policy
        // (https://operations.osmfoundation.org/policies/tiles/) allows live viewing only — no
        // pre-seeding, route caching or offline bulk download — and requires an identifying
        // User-Agent, a >=7-day cache, and the "© OpenStreetMap contributors" credit on the map.
        private const val OSM_URL_TEMPLATE = "https://tile.openstreetmap.org/%d/%d/%d.png"
        private val OSM_USER_AGENT =
            "Wayfindr/${com.example.wayfindr.BuildConfig.VERSION_NAME} (personal motorcycle navigation app; Android)"
        private const val OSM_REVALIDATE_MS = 7L * 24 * 60 * 60 * 1000
        const val OSM_ATTRIBUTION = "© OpenStreetMap contributors"
        private const val MAX_PREFETCH_TILES = 600
        /** A tile that failed isn't requested again for this long (doubling up to the max). */
        private const val FAIL_BACKOFF_MS = 60_000L
        private const val FAIL_BACKOFF_MAX_MS = 15 * 60_000L
        private const val MIN_FETCH_GAP_MS = 60L // be gentle on OSM + the radio

        /** ~1/5 of the app heap for the tile cache, clamped to a sane 48–128 MB. */
        private fun memoryCacheBytes(ctx: Context): Int {
            val am = ctx.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val heapBytes = am.memoryClass.toLong() * 1024L * 1024L
            return (heapBytes / 5L).coerceIn(48L * 1024 * 1024, 128L * 1024 * 1024).toInt()
        }
    }

    init { GPlus.init(context) }

    private val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val diskDirOsm = File(context.cacheDir, "tiles_osm_v1").apply { mkdirs() }

    /** "Sharper map" setting; only the Google tile session ([GPlus.tileUrl]) uses it. */
    @Volatile var hiDpi = false

    /** OSM's rules forbid prefetching, so every prefetch below is skipped. */
    private val liveOsmOnly = true

    private fun keyOf(z: Int, x: Int, y: Int) = "osm/$z/$x/$y"
    // Byte-budgeted (not count-based): adapts to tile size and OEM heap, keeping the current zoom
    // PLUS a neighbouring level resident for the renderer's scaled-tile fallback.
    private val memory = object : LruCache<String, Bitmap>(memoryCacheBytes(context)) {
        override fun sizeOf(key: String, value: Bitmap): Int = value.allocationByteCount
    }
    private val inflight = ConcurrentHashMap.newKeySet<String>()
    @Volatile private var lastFetchAt = 0L

    /** Non-blocking: returns the cached tile, else kicks off async load. */
    fun get(z: Int, x: Int, y: Int): Bitmap? {
        val max = 1 shl z
        if (y < 0 || y >= max) return null
        val xw = ((x % max) + max) % max
        val key = keyOf(z, xw, y)

        memory.get(key)?.let { return it }

        if (inflight.add(key)) {
            scope.launch(Dispatchers.IO) {
                try {
                    // Standard map tiles (Google-Maps-like look). No per-tile filter — also the cheapest path.
                    val raw = loadDisk(key) ?: fetch(z, xw, y, key)
                    if (raw != null) memory.put(key, raw)
                    // OSM asks for week-old tiles to be re-checked; the old one shows meanwhile.
                    if (raw != null && key.startsWith("osm/") &&
                        System.currentTimeMillis() - diskFile(key).lastModified() > OSM_REVALIDATE_MS) {
                        fetch(z, xw, y, key)?.let { memory.put(key, it) }
                    }
                } finally {
                    inflight.remove(key)
                }
            }
        }
        return null
    }

    /**
     * Memory-cache-only peek — render-thread safe and never triggers a load. Used by the
     * renderer to substitute a cached neighbouring-zoom tile while the exact one loads.
     */
    fun getCached(z: Int, x: Int, y: Int): Bitmap? {
        val max = 1 shl z
        if (y < 0 || y >= max) return null
        val xw = ((x % max) + max) % max
        return memory.get(keyOf(z, xw, y))
    }

    /** Warm a single zoom level around a point right now (e.g. right after the rider zooms). */
    fun prefetchZoom(lat: Double, lng: Double, z: Int, radius: Int = 2) {
        if (z < 0 || liveOsmOnly) return
        scope.launch(Dispatchers.IO) { prefetchAround(lat, lng, z, radius) }
    }

    /** Prefetch tiles around a point (and optionally a straight corridor) into disk. */
    fun prefetch(lat: Double, lng: Double, fromLat: Double? = null, fromLng: Double? = null) {
        if (liveOsmOnly) return
        scope.launch(Dispatchers.IO) {
            var count = 0
            // 11..20 so the rider's vicinity has tiles at every zoom level offline
            // (default nav zoom is now 19, max 20).
            for (z in 11..20) {
                val radius = if (z in 15..16) 2 else 1
                count += prefetchAround(lat, lng, z, radius)
                if (fromLat != null && fromLng != null) {
                    count += prefetchAround(fromLat, fromLng, z, radius)
                    if (z in 12..13) for (i in 1..8) {
                        val f = i / 9.0
                        count += prefetchAround(fromLat + (lat - fromLat) * f, fromLng + (lng - fromLng) * f, z, 1)
                    }
                }
                if (count > MAX_PREFETCH_TILES) break
            }
            Log.i(TAG, "Prefetch (point) done — ~$count tiles ensured")
        }
    }

    private var routeJob: kotlinx.coroutines.Job? = null

    /**
     * Cache a route when it's set: riding zoom (15–19) for the first 50 km — the whole route if
     * shorter — plus overview zoom (11–14) for the whole route. See [RouteTilePlan]. Tiles already
     * on disk cost nothing; a new route cancels the previous job. While riding, the rolling
     * prefetch around the rider only fills what's still missing (e.g. beyond 50 km).
     */
    fun prefetchRoute(route: List<GeoPoint>) {
        if (route.size < 2) return
        routeJob?.cancel()
        if (liveOsmOnly) {
            com.example.wayfindr.data.RideDiagnostics.log("tiles",
                "route cache skipped: OpenStreetMap tiles can't be pre-downloaded (OSM tile policy)")
            return
        }
        routeJob = scope.launch(Dispatchers.IO) {
            val plan = RouteTilePlan.plan(route)
            val missing = plan.filter { !diskFile(keyOf(it.z, it.x, it.y)).exists() }
            com.example.wayfindr.data.RideDiagnostics.log("tiles",
                "route cache: ${plan.size} tiles planned, ${missing.size} to download")
            var ok = 0
            for ((i, t) in missing.withIndex()) {
                if (!isActive) return@launch
                if (fetchKey(t.z, t.x, t.y, keyOf(t.z, t.x, t.y)) != null) ok++
                if ((i + 1) % 500 == 0) com.example.wayfindr.data.RideDiagnostics.log("tiles",
                    "route cache: ${i + 1}/${missing.size}")
            }
            com.example.wayfindr.data.RideDiagnostics.log("tiles",
                "route cache done: $ok/${missing.size} downloaded")
        }
    }

    private fun prefetchAround(lat: Double, lng: Double, z: Int, radius: Int): Int {
        val cx = Mercator.lngToTileX(lng, z).toInt()
        val cy = Mercator.latToTileY(lat, z).toInt()
        var n = 0
        for (dx in -radius..radius) for (dy in -radius..radius) {
            val x = cx + dx; val y = cy + dy
            if (y < 0 || y >= (1 shl z)) continue
            val key = keyOf(z, x, y)
            if (!diskFile(key).exists()) { fetchKey(z, x, y, key); n++ }
        }
        return n
    }

    // ── Internals ─────────────────────────────────────────────────────────

    // GPlus.tileDir swaps in the Google tile cache while Google tiles are in use.
    private fun diskFile(key: String) =
        File(GPlus.tileDir(diskDirOsm), key.removePrefix("osm/").replace('/', '_') + ".png")

    private fun loadDisk(key: String): Bitmap? {
        NavPlus.mbTile(key)?.let { return it }   // imported offline .mbtiles first
        val f = diskFile(key)
        if (!f.exists()) return null
        return BitmapFactory.decodeFile(f.absolutePath)
    }

    private fun fetch(z: Int, x: Int, y: Int, key: String): Bitmap? =
        NavPlus.mbTile(key) ?: fetchKey(z, x, y, key)

    // key → (retry-not-before ms, current backoff ms) for tiles whose download failed.
    private val failedUntil = ConcurrentHashMap<String, Pair<Long, Long>>()

    private fun fetchKey(z: Int, x: Int, y: Int, key: String): Bitmap? {
        val max = 1 shl z
        if (y < 0 || y >= max) return null
        failedUntil[key]?.let { (until, _) -> if (System.currentTimeMillis() < until) return null }
        val bmp = download(z, x, y, key, max)
        if (bmp == null) {
            val prev = failedUntil[key]?.second ?: 0L
            val backoff = if (prev == 0L) FAIL_BACKOFF_MS else (prev * 2).coerceAtMost(FAIL_BACKOFF_MAX_MS)
            failedUntil[key] = (System.currentTimeMillis() + backoff) to backoff
        } else failedUntil.remove(key)
        return bmp
    }

    private fun download(z: Int, x: Int, y: Int, key: String, max: Int): Bitmap? {
        // Rate-limit network fetches to avoid hot loops on the radio.
        val now = System.currentTimeMillis()
        val wait = MIN_FETCH_GAP_MS - (now - lastFetchAt)
        if (wait > 0) try { Thread.sleep(wait) } catch (_: InterruptedException) {}
        lastFetchAt = System.currentTimeMillis()

        val net = internetNetwork()
        return try {
            val yw = ((y % max) + max) % max
            // GPlus.tileUrl returns a Google Map Tiles URL when a key + session exist, else the OSM one.
            val url = URL(GPlus.tileUrl(OSM_URL_TEMPLATE.format(z, ((x % max) + max) % max, yw), z, x, y, max, hiDpi))
            com.example.wayfindr.data.ApiUsage.record(GPlus.usageName(com.example.wayfindr.data.ApiUsage.OSM_TILES))
            val conn = (net?.openConnection(url) ?: url.openConnection()) as HttpURLConnection
            conn.setRequestProperty("User-Agent", OSM_USER_AGENT)
            GPlus.androidAuth(conn) // only acts on Google tile URLs
            conn.connectTimeout = 8_000
            conn.readTimeout = 8_000
            if (conn.responseCode !in 200..299) {
                val code = conn.responseCode
                GPlus.tileHttp(code)
                Log.w(TAG, "Tile $key HTTP $code")
                conn.disconnect(); return null
            }
            val bytes = conn.inputStream.use { it.readBytes() }
            conn.disconnect()
            // Only cache a tile that actually DECODES. Writing a throttle/error body or a
            // truncated read to disk would poison the cache: the file would then exist (so
            // prefetch skips it) but never decode, leaving a permanently blank tile offline.
            val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            if (bmp == null) {
                Log.w(TAG, "Tile $key decoded null (${bytes.size}B) — not caching")
                return null
            }
            diskFile(key).writeBytes(bytes)
            bmp
        } catch (e: Exception) {
            Log.w(TAG, "Tile $key fetch failed: ${e.message}")
            null
        }
    }

    @Suppress("DEPRECATION")
    private fun internetNetwork(): Network? =
        cm.allNetworks.firstOrNull { n ->
            cm.getNetworkCapabilities(n)?.let {
                it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                it.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            } == true
        }
}
