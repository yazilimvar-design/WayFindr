package com.example.wayfindr.dash.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.wayfindr.dash.offline.OfflinePack
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.maplibre.android.MapLibre
import org.maplibre.android.camera.CameraPosition
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.maps.Style
import org.maplibre.android.snapshotter.MapSnapshotter
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.resume
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.exp

/**
 * Raster console tiles drawn from the offline Türkiye vector map (PMTiles, [OfflinePack]).
 *
 * One MapLibre [MapSnapshotter] (main looper) renders a block of [BLOCK]×[BLOCK] 256-px tiles at a
 * time, with a margin so labels near the block edge are placed as in the neighbouring block; the
 * block is cut into tiles and written to a disk cache (per map date), so every tile is drawn once.
 * Style: assets/offline/style.json (Protomaps "light", Turkish names, bold roads, no sprites) with
 * glyphs from assets/glyphs. Night look stays the draw-time ColorMatrix in [MapRenderer].
 */
object VectorTiles {
    private const val TAG = "VectorTiles"
    private const val BLOCK = 3
    private const val MARGIN = 64
    private const val MAX_Z = 20
    private const val CACHE_LIMIT = 300L * 1024 * 1024
    // Area of the map (bbox of the extract), with a little slack.
    private const val W = 25.5; private const val S = 35.7; private const val E = 45.0; private const val N = 42.3

    @Volatile private var snapshotter: MapSnapshotter? = null
    @Volatile private var snapFile: String? = null
    private val renderLock = Mutex()
    private val blocks = ConcurrentHashMap<String, CompletableDeferred<Unit>>()
    @Volatile private var lastPrune = 0L

    /** Map file changed (update installed): new snapshotter + new tile cache. */
    fun reset() {
        Handler(Looper.getMainLooper()).post {
            runCatching { snapshotter?.cancel() }
            snapshotter = null; snapFile = null
        }
    }

    fun available(ctx: Context) = OfflinePack.mapFile(ctx) != null

    /** Is tile z/x/y inside the offline map's area? */
    fun covers(z: Int, x: Int, y: Int): Boolean {
        if (z < 1 || z > MAX_Z) return false
        val n = 1 shl z
        val west = x.toDouble() / n * 360 - 180; val east = (x + 1).toDouble() / n * 360 - 180
        val north = tileLat(y, z); val south = tileLat(y + 1, z)
        return east > W && west < E && north > S && south < N
    }

    private fun tileLat(y: Int, z: Int): Double {
        val n = PI - 2.0 * PI * y / (1 shl z)
        return Math.toDegrees(atan(0.5 * (exp(n) - exp(-n))))
    }

    private fun cacheDir(ctx: Context): File? {
        val date = OfflinePack.mapDate(ctx) ?: return null
        return File(ctx.cacheDir, "tiles_vec_$date").apply { mkdirs() }
    }

    private fun tileFile(dir: File, z: Int, x: Int, y: Int) = File(dir, "${z}_${x}_$y.jpg")

    /** Already rendered? (disk only, any thread) */
    fun cached(ctx: Context, z: Int, x: Int, y: Int): Bitmap? {
        val f = tileFile(cacheDir(ctx) ?: return null, z, x, y)
        return if (f.exists()) BitmapFactory.decodeFile(f.absolutePath) else null
    }

    /**
     * The tile, rendering its block first if needed. Suspends (off the main thread) up to ~20 s;
     * null when the map isn't installed, the tile is outside it or rendering failed.
     */
    suspend fun tile(ctx: Context, z: Int, x: Int, y: Int): Bitmap? {
        if (!covers(z, x, y)) return null
        val mapFile = OfflinePack.mapFile(ctx) ?: return null
        val dir = cacheDir(ctx) ?: return null
        tileFile(dir, z, x, y).takeIf { it.exists() }?.let { return BitmapFactory.decodeFile(it.absolutePath) }
        val bx = Math.floorDiv(x, BLOCK) * BLOCK; val by = Math.floorDiv(y, BLOCK) * BLOCK
        val key = "${dir.name}/$z/$bx/$by"
        val mine = CompletableDeferred<Unit>()
        val running = blocks.putIfAbsent(key, mine)
        if (running != null) running.await()
        else try {
            renderBlock(ctx, mapFile, dir, z, bx, by)
        } finally {
            mine.complete(Unit); blocks.remove(key)
        }
        return tileFile(dir, z, x, y).takeIf { it.exists() }?.let { BitmapFactory.decodeFile(it.absolutePath) }
    }

    private suspend fun renderBlock(ctx: Context, mapFile: File, dir: File, z: Int, bx: Int, by: Int) = renderLock.withLock {
        val t0 = System.currentTimeMillis()
        val n = 1 shl z
        val px = BLOCK * 256 + 2 * MARGIN
        // Block centre in world tile units → lat/lng. MapLibre zoom uses 512-px tiles: zoom z-1.
        val cx = bx + BLOCK / 2.0; val cy = by + BLOCK / 2.0
        val lng = cx / n * 360.0 - 180.0
        val lat = Math.toDegrees(atan(kotlin.math.sinh(PI - 2.0 * PI * cy / n)))
        val bmp = snapshot(ctx, mapFile, px, lat, lng, z - 1.0) ?: return@withLock
        withContext(Dispatchers.IO) {
            val tileBmp = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888)
            val c = Canvas(tileBmp)
            for (dx in 0 until BLOCK) for (dy in 0 until BLOCK) {
                val tx = bx + dx; val ty = by + dy
                if (ty < 0 || ty >= n) continue
                val xw = ((tx % n) + n) % n
                val sx = MARGIN + dx * 256; val sy = MARGIN + dy * 256
                c.drawBitmap(bmp, Rect(sx, sy, sx + 256, sy + 256), Rect(0, 0, 256, 256), null)
                val f = tileFile(dir, z, xw, ty)
                val tmp = File(dir, f.name + ".tmp")
                tmp.outputStream().use { tileBmp.compress(Bitmap.CompressFormat.JPEG, 88, it) }
                tmp.renameTo(f)
            }
            tileBmp.recycle()
            Log.i(TAG, "block z$z $bx,$by rendered in ${System.currentTimeMillis() - t0} ms")
            prune(ctx)
        }
    }

    private suspend fun snapshot(ctx: Context, mapFile: File, px: Int, lat: Double, lng: Double, zoom: Double): Bitmap? =
        withTimeoutOrNull(20_000) {
            withContext(Dispatchers.Main) {   // MapSnapshotter lives on the main looper
                suspendCancellableCoroutine { cont ->
                    val cam = CameraPosition.Builder().target(LatLng(lat, lng)).zoom(zoom).bearing(0.0).tilt(0.0).build()
                    val s = try {
                        val existing = snapshotter
                        if (existing != null && snapFile == mapFile.absolutePath) {
                            existing.setCameraPosition(cam); existing
                        } else {
                            MapLibre.getInstance(ctx)
                            runCatching { existing?.cancel() }
                            MapSnapshotter(ctx, MapSnapshotter.Options(px, px)
                                .withStyleBuilder(Style.Builder().fromJson(styleJson(ctx, mapFile)))
                                .withCameraPosition(cam)
                                .withPixelRatio(1f)
                                .withLogo(false)).also { snapshotter = it; snapFile = mapFile.absolutePath }
                        }
                    } catch (t: Throwable) {
                        Log.w(TAG, "snapshotter init failed", t); cont.resume(null); return@suspendCancellableCoroutine
                    }
                    s.start({ snap -> if (cont.isActive) cont.resume(snap.bitmap) }) { e ->
                        Log.w(TAG, "snapshot failed: $e"); if (cont.isActive) cont.resume(null)
                    }
                    cont.invokeOnCancellation { Handler(Looper.getMainLooper()).post { runCatching { s.cancel() } } }
                }
            }
        }

    @Volatile private var styleTemplate: String? = null

    fun styleJson(ctx: Context, mapFile: File): String {
        val t = styleTemplate ?: ctx.assets.open("offline/style.json").use { it.readBytes().toString(Charsets.UTF_8) }
            .also { styleTemplate = it }
        return t.replace("{PMTILES}", "pmtiles://file://" + mapFile.absolutePath)
    }

    /** Keeps the rendered-tile cache under [CACHE_LIMIT] (oldest first); drops caches of older maps. */
    private fun prune(ctx: Context) {
        val now = System.currentTimeMillis()
        if (now - lastPrune < 60_000) return
        lastPrune = now
        val current = cacheDir(ctx) ?: return
        ctx.cacheDir.listFiles { f -> f.isDirectory && f.name.startsWith("tiles_vec_") && f != current }
            ?.forEach { it.deleteRecursively() }
        val files = current.listFiles() ?: return
        var total = files.sumOf { it.length() }
        if (total <= CACHE_LIMIT) return
        for (f in files.sortedBy { it.lastModified() }) {
            total -= f.length(); f.delete()
            if (total <= CACHE_LIMIT * 8 / 10) break
        }
    }
}
