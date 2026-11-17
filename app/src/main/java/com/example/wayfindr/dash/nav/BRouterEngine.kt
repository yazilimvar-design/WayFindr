package com.example.wayfindr.dash.nav

import android.content.Context
import android.util.Log
import btools.router.FormatJson
import btools.router.RoutingContext
import btools.router.RoutingEngine
import btools.router.RoutingParamCollector
import com.example.wayfindr.dash.offline.OfflinePack
import java.io.File

/**
 * BRouter running inside the app (the vendored :brouter module, MIT), routing from the .rd5
 * segments of the "Türkiye çevrimdışı paketi" ([OfflinePack]). Same request keys and the same
 * GeoJSON + voicehints answer as the BRouter app's service, so [OfflineRouter] parses both alike.
 * Profiles (car-fast, car-eco + lookups.dat) ship in assets/brouter and are copied to filesDir once
 * per app version, because the engine reads them as files.
 */
object BRouterEngine {
    private const val TAG = "BRouterEngine"

    /** Segment file (5° tile, e.g. E30_N35.rd5) that holds this point, as BRouter names it. */
    fun segmentName(p: GeoPoint): String {
        val lon = Math.floorDiv(Math.floor(p.lng).toInt(), 5) * 5
        val lat = Math.floorDiv(Math.floor(p.lat).toInt(), 5) * 5
        return (if (lon < 0) "W${-lon}" else "E$lon") + "_" + (if (lat < 0) "S${-lat}" else "N$lat") + ".rd5"
    }

    /** True when every point lies in a segment that is on the phone. */
    fun covers(ctx: Context, points: List<GeoPoint>): Boolean {
        val dir = OfflinePack.segmentDir(ctx)
        return points.isNotEmpty() && points.all { File(dir, segmentName(it)).length() > 0 }
    }

    @Volatile private var profilesReady = false

    private fun profileDir(ctx: Context): File {
        val dir = File(ctx.filesDir, "brouter/profiles")
        if (profilesReady) return dir
        synchronized(this) {
            val stamp = File(dir, ".version")
            val want = com.example.wayfindr.BuildConfig.VERSION_CODE.toString() + "/" +
                runCatching { ctx.packageManager.getPackageInfo(ctx.packageName, 0).lastUpdateTime }.getOrDefault(0L)
            if (!stamp.exists() || stamp.readText() != want) {
                dir.mkdirs()
                for (name in ctx.assets.list("brouter").orEmpty()) {
                    ctx.assets.open("brouter/$name").use { input -> File(dir, name).outputStream().use { input.copyTo(it) } }
                }
                stamp.writeText(want)
            }
            btools.router.ProfileCache.setSize(4)   // car-fast + car-eco, also while alternatives run in parallel
            profilesReady = true
        }
        return dir
    }

    /**
     * Runs one routing request. [params] are BRouter's request keys ("profile", "timode",
     * "alternativeidx", "maxRunningTime", …), as the BRouter service takes them. Returns GeoJSON
     * on success, else BRouter's error text (callers check for a leading "{").
     */
    fun route(ctx: Context, points: List<GeoPoint>, params: Map<String, String>): String {
        val t0 = System.currentTimeMillis()
        return try {
            val collector = RoutingParamCollector()
            val waypoints = collector.readPositions(
                DoubleArray(points.size) { points[it].lng }, DoubleArray(points.size) { points[it].lat })
            val rc = RoutingContext()
            // BRouter sizes its caches from this (MB). Half the app heap: the dash tile caches need the rest.
            rc.memoryclass = ((ctx.getSystemService(Context.ACTIVITY_SERVICE) as android.app.ActivityManager)
                .memoryClass / 2).coerceIn(64, 192)
            val p = HashMap(params)
            val profile = p.remove("profile") ?: OfflineRouter.PROFILE
            val maxSeconds = p.remove("maxRunningTime")?.toLongOrNull() ?: 60L
            p.putIfAbsent("trackFormat", "json")
            collector.setParams(rc, waypoints, p)
            rc.localFunction = File(profileDir(ctx), "$profile.brf").absolutePath
            val engine = RoutingEngine(null, null, OfflinePack.segmentDir(ctx), waypoints, rc, 0)
            engine.quite = true
            engine.doRun(maxSeconds * 1000)
            val err = engine.errorMessage
            val track = engine.foundTrack
            val out = when {
                err != null -> err
                track == null -> "no route found"
                else -> FormatJson(rc).format(track)
            }
            Log.i(TAG, "$profile ${points.size} points in ${System.currentTimeMillis() - t0} ms: " +
                if (out.startsWith("{")) "${out.length} chars" else out.take(160))
            out
        } catch (t: Throwable) {
            Log.w(TAG, "routing failed", t)
            "BRouter engine error: ${t.message ?: t.javaClass.simpleName}"
        }
    }
}
