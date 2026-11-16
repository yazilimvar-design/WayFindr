package com.example.wayfindr.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.graphics.Rect
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.LruCache
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import com.example.wayfindr.dash.DashConfig
import com.example.wayfindr.dash.map.Mercator
import com.example.wayfindr.dash.nav.GPlus
import com.example.wayfindr.dash.nav.GeoPoint
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.dash.nav.PolylineCodec
import com.example.wayfindr.data.Ride
import com.example.wayfindr.ui.theme.MapBase
import com.example.wayfindr.util.InternetNetwork
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.resume
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeoutOrNull
import org.maplibre.android.MapLibre
import org.maplibre.android.geometry.LatLng
import org.maplibre.android.geometry.LatLngBounds
import org.maplibre.android.maps.Style
import org.maplibre.android.snapshotter.MapSnapshot
import org.maplibre.android.snapshotter.MapSnapshotter
import java.io.File
import kotlin.math.floor
import kotlin.math.ln
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Map thumbnail of a recorded ride: the track coloured by speed (start dot green, finish dot gold)
 * over a dark map. The map comes only from tiles already on the phone (imported .mbtiles, then the
 * dash map's disk cache) — no network; tiles that aren't there leave the plain dark base.
 */
@Composable
fun RideThumb(ride: Ride, modifier: Modifier = Modifier, live: Boolean = false) {
    val ctx = LocalContext.current
    val density = LocalDensity.current.density
    BoxWithConstraints(modifier.background(MapBase)) {
        val w = constraints.maxWidth; val h = constraints.maxHeight
        val bmp by produceState<Bitmap?>(RideThumbs.cached(ride, w, h), ride.sid, ride.trackPolyline, w, h) {
            if (value == null && w in 1..4096 && h in 1..4096) value = if (live) RideThumbs.renderLive(ctx, ride, w, h, density)
                else withContext(Dispatchers.IO) { RideThumbs.render(ctx, ride, w, h, density) }
        }
        bmp?.let { Image(it.asImageBitmap(), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.matchParentSize()) }
    }
}

internal object RideThumbs {
    private val cache = object : LruCache<String, Bitmap>(16 * 1024 * 1024) {
        override fun sizeOf(key: String, value: Bitmap) = value.allocationByteCount
    }
    private const val START = 0xFFE9B949.toInt()   // Gold
    private const val END = 0xFF3DDC84.toInt()     // same green as NavPlus.spColor's slow band

    private fun key(r: Ride, w: Int, h: Int) = "${r.sid}|${r.trackPolyline.hashCode()}|${w}x$h"
    fun cached(r: Ride, w: Int, h: Int): Bitmap? = cache.get(key(r, w, h))

    private fun decode(ride: Ride) = try { PolylineCodec.decode(ride.trackPolyline) } catch (_: Throwable) { emptyList() }

    /** [render] over a MapLibre snapshot in the current map theme; offline or on failure/timeout, over cached tiles. */
    suspend fun renderLive(ctx: Context, ride: Ride, w: Int, h: Int, density: Float): Bitmap? {
        val track = decode(ride)
        val snap = if (track.size >= 2 && InternetNetwork.validated(ctx) != null) snapshot(ctx, track, w, h, density) else null
        return withContext(Dispatchers.IO) { render(ctx, ride, w, h, density, snap) }
    }

    private suspend fun snapshot(ctx: Context, track: List<GeoPoint>, w: Int, h: Int, ratio: Float): MapSnapshot? =
        withTimeoutOrNull(8_000) {
            withContext(Dispatchers.Main) {   // MapSnapshotter is main-thread only
                suspendCancellableCoroutine { cont ->
                    val s = try {
                        MapLibre.getInstance(ctx)
                        val minLat = track.minOf { it.lat }; val maxLat = track.maxOf { it.lat }
                        val minLng = track.minOf { it.lng }; val maxLng = track.maxOf { it.lng }
                        // ~14 % padding per side, like the tile renderer; floor so a tiny track isn't zoomed to street level.
                        val pLat = max(maxLat - minLat, 0.003) * 0.2; val pLng = max(maxLng - minLng, 0.003) * 0.2
                        MapSnapshotter(ctx, MapSnapshotter.Options((w / ratio).roundToInt(), (h / ratio).roundToInt())
                            .withStyleBuilder(Style.Builder().fromJson(onlineThemeStyleJson(ctx, DashConfig.get(ctx).mapTheme.value)))
                            .withRegion(LatLngBounds.from(maxLat + pLat, maxLng + pLng, minLat - pLat, minLng - pLng))
                            .withPixelRatio(ratio)
                            .withLogo(false))
                    } catch (t: Throwable) {
                        Log.w("RideThumbs", "snapshotter init failed", t); cont.resume(null); return@suspendCancellableCoroutine
                    }
                    s.start({ if (cont.isActive) cont.resume(it) }) { e ->
                        Log.w("RideThumbs", "snapshot failed: $e"); if (cont.isActive) cont.resume(null)
                    }
                    cont.invokeOnCancellation { Handler(Looper.getMainLooper()).post { s.cancel() } }
                }
            }
        }

    fun render(ctx: Context, ride: Ride, w: Int, h: Int, density: Float, snap: MapSnapshot? = null): Bitmap? {
        val track = decode(ride)
        val out = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val c = Canvas(out)
        c.drawColor(MAP_BG)
        if (track.size < 2) return out.also { cache.put(key(ride, w, h), it) }

        val xs: FloatArray; val ys: FloatArray
        if (snap != null) {
            val k = w.toFloat() / snap.bitmap.width   // pixelForLatLng is in snapshot bitmap pixels
            c.drawBitmap(snap.bitmap, null, Rect(0, 0, w, h), Paint(Paint.FILTER_BITMAP_FLAG))
            val pts = track.map { snap.pixelForLatLng(LatLng(it.lat, it.lng)) }
            xs = FloatArray(track.size) { pts[it].x * k }; ys = FloatArray(track.size) { pts[it].y * k }
        } else {
            // Tiles drawn at ~half device density so labels stay legible on a small thumb.
            val tilePx = 256f * max(1f, density / 2f)
            val pad = 0.14f
            val minLat = track.minOf { it.lat }; val maxLat = track.maxOf { it.lat }
            val minLng = track.minOf { it.lng }; val maxLng = track.maxOf { it.lng }
            // Largest zoom whose track bbox fits the thumb (with padding).
            val spanX = (Mercator.lngToTileX(maxLng, 0) - Mercator.lngToTileX(minLng, 0)).coerceAtLeast(1e-9)
            val spanY = (Mercator.latToTileY(minLat, 0) - Mercator.latToTileY(maxLat, 0)).coerceAtLeast(1e-9)
            val fit = min(w * (1 - 2 * pad) / (spanX * tilePx), h * (1 - 2 * pad) / (spanY * tilePx))
            val z = floor(ln(fit) / ln(2.0)).toInt().coerceIn(3, 17)
            val scale = (fit / (1 shl z)).coerceAtMost(1.6).toFloat() * tilePx / 256f   // screen px per tile px
            val cx = (Mercator.lngToTileX(minLng, z) + Mercator.lngToTileX(maxLng, z)) / 2
            val cy = (Mercator.latToTileY(maxLat, z) + Mercator.latToTileY(minLat, z)) / 2
            val tileOnScreen = 256f * scale
            fun sx(tx: Double) = (w / 2f + (tx - cx) * tileOnScreen).toFloat()
            fun sy(ty: Double) = (h / 2f + (ty - cy) * tileOnScreen).toFloat()

            drawTiles(ctx, c, z, cx, cy, w, h, tileOnScreen)
            xs = FloatArray(track.size) { sx(Mercator.lngToTileX(track[it].lng, z)) }
            ys = FloatArray(track.size) { sy(Mercator.latToTileY(track[it].lat, z)) }
        }
        c.drawColor(0x40000000)   // push the map back so the track reads first

        val speeds = NavPlus.rsLoad(track[0])?.takeIf { it.size == track.size }   // km/h per point; absent on old/synced rides
        val stroke = max(3f * density, min(w, h) * 0.022f)
        val casing = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = 0xB3000000.toInt(); strokeWidth = stroke * 1.9f; strokeCap = Paint.Cap.ROUND
        }
        val line = Paint(Paint.ANTI_ALIAS_FLAG).apply { strokeWidth = stroke; strokeCap = Paint.Cap.ROUND; color = START }
        for (i in 1 until track.size) c.drawLine(xs[i - 1], ys[i - 1], xs[i], ys[i], casing)
        for (i in 1 until track.size) {
            if (speeds != null) line.color = NavPlus.spColor(max(speeds[i - 1].toInt() and 0xFF, speeds[i].toInt() and 0xFF))
            c.drawLine(xs[i - 1], ys[i - 1], xs[i], ys[i], line)
        }
        val dot = Paint(Paint.ANTI_ALIAS_FLAG)
        val r = stroke * 1.7f
        for ((i, col) in listOf(0 to END, track.lastIndex to START)) { // like TripperDash: green = start, gold = finish
            dot.color = 0xE6000000.toInt(); c.drawCircle(xs[i], ys[i], r + stroke * 0.6f, dot)
            dot.color = col; c.drawCircle(xs[i], ys[i], r, dot)
        }
        cache.put(key(ride, w, h), out)
        return out
    }

    private fun drawTiles(ctx: Context, c: Canvas, z: Int, cx: Double, cy: Double, w: Int, h: Int, tile: Float) {
        try { GPlus.init(ctx) } catch (_: Throwable)
        val dir = try { GPlus.tileDir(File(ctx.cacheDir, "tiles_osm_v1")) } catch (_: Throwable) { File(ctx.cacheDir, "tiles_osm_v1") }
        val x0 = floor(cx - w / 2.0 / tile).toInt(); val x1 = floor(cx + w / 2.0 / tile).toInt()
        val y0 = floor(cy - h / 2.0 / tile).toInt(); val y1 = floor(cy + h / 2.0 / tile).toInt()
        val n = 1 shl z
        val paint = Paint(Paint.FILTER_BITMAP_FLAG).apply { colorFilter = NIGHT }
        val dst = android.graphics.RectF()
        for (ty in y0..y1) for (tx in x0..x1) {
            if (ty !in 0 until n) continue
            val xw = ((tx % n) + n) % n
            val bmp = try {
                NavPlus.mbTile0("osm/$z/$xw/$ty")
                    ?: File(dir, "${z}_${xw}_$ty.png").takeIf { it.exists() }?.let { BitmapFactory.decodeFile(it.absolutePath) }
            } catch (_: Throwable) { null } ?: continue
            val left = (w / 2.0 + (tx - cx) * tile).toFloat(); val top = (h / 2.0 + (ty - cy) * tile).toFloat()
            dst.set(left, top, left + tile, top + tile)
            c.drawBitmap(bmp, Rect(0, 0, bmp.width, bmp.height), dst, paint)
        }
    }

    private const val MAP_BG = 0xFF0E1416.toInt()   // MapBase

    /** Light raster → dark map: invert, rotate hue back (water stays blue), dim. Same idea as MapRenderer's night mode. */
    private val NIGHT = ColorMatrixColorFilter(ColorMatrix(floatArrayOf(
        -1f, 0f, 0f, 0f, 255f, 0f, -1f, 0f, 0f, 255f, 0f, 0f, -1f, 0f, 255f, 0f, 0f, 0f, 1f, 0f,
    )).apply {
        postConcat(ColorMatrix(floatArrayOf(
            -0.574f, 1.430f, 0.144f, 0f, 0f, 0.426f, 0.430f, 0.144f, 0f, 0f,
            0.426f, 1.430f, -0.856f, 0f, 0f, 0f, 0f, 0f, 1f, 0f,
        )))
        postConcat(ColorMatrix(floatArrayOf(
            0.8f, 0f, 0f, 0f, 4f, 0f, 0.8f, 0f, 0f, 4f, 0f, 0f, 0.8f, 0f, 4f, 0f, 0f, 0f, 1f, 0f,
        )))
    })
}
