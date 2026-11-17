package com.example.wayfindr

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.wayfindr.dash.map.MapRenderer
import com.example.wayfindr.dash.map.TileProvider
import com.example.wayfindr.dash.nav.DashGlyph
import com.example.wayfindr.dash.nav.GeoPoint
import com.example.wayfindr.dash.video.DashEncoder
import com.example.wayfindr.ui.navigation.AppNavigation
import com.example.wayfindr.ui.theme.Bg1
import com.example.wayfindr.ui.theme.WayfindrTheme
import com.example.wayfindr.viewmodel.RouteViewModel

class MainActivity : ComponentActivity() {
    private val routeViewModel: RouteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        handleIntent(intent)
        setContent {
            WayfindrTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Bg1) {
                    AppNavigation(routeViewModel = routeViewModel)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SEND -> {
                if (intent.type == "text/plain") {
                    val text = intent.getStringExtra(Intent.EXTRA_TEXT) ?: return
                    routeViewModel.handleSharedText(text)
                }
            }
            Intent.ACTION_VIEW -> {
                val uri = intent.data?.toString() ?: return
                routeViewModel.handleSharedText(uri)
            }
            ACTION_RENDER_NAV_CARD -> if (BuildConfig.DEBUG) renderNavCardPreview()
            ACTION_FRAME_BENCH -> if (BuildConfig.DEBUG) runFrameBench()
        }
    }

    /** Debug only (CI smoke test): draws console frames with fake turns to files/navcard.png. */
    private fun renderNavCardPreview() = Thread {
        val g = DashGlyph
        val cards = listOf(
            MapRenderer.TurnCard(g.TURN_RIGHT, 347.0, "Atatürk Bulvarı'na sağa dönün", g.TURN_LEFT),
            MapRenderer.TurnCard(g.TURN_SLIGHT_LEFT, 1240.0, "Hafif sola, Eskişehir Yolu / Konya Devlet Yolu üzerinden devam edin"),
            MapRenderer.TurnCard(g.TURN_SHARP_RIGHT, 80.0, "Keskin sağ, 1866. Sokak"),
            MapRenderer.TurnCard(g.U_TURN_COUNTERCLOCKWISE, 150.0, "U dönüşü yapın"),
            MapRenderer.TurnCard(g.ROUNDABOUT_CLOCKWISE, 520.0, "Göbekte 2. çıkışı alın", g.TURN_SHARP_LEFT),
            MapRenderer.TurnCard(g.DESTINATION, 40.0, "Hedef sağda"),
            MapRenderer.TurnCard(g.STRAIGHT, 9800.0, null),
        )
        val w = com.example.wayfindr.dash.video.DashEncoder.WIDTH; val h = com.example.wayfindr.dash.video.DashEncoder.HEIGHT
        val renderer = MapRenderer(TileProvider(applicationContext, kotlinx.coroutines.MainScope()))
        val out = android.graphics.Bitmap.createBitmap(w, h * cards.size, android.graphics.Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(out)
        repeat(2) { pass ->   // second pass once the tiles requested by the first have loaded
            if (pass == 1) Thread.sleep(8_000)
            cards.forEachIndexed { i, card ->
                canvas.save(); canvas.translate(0f, (h * i).toFloat()); canvas.clipRect(0, 0, w, h)
                renderer.draw(canvas, MapRenderer.Frame(
                    centerLat = 39.9208, centerLng = 32.8541, zoom = 16, headingUp = true,
                    riderLat = 39.9208, riderLng = 32.8541, destLat = 39.93, destLng = 32.86,
                    route = listOf(GeoPoint(39.9208, 32.8541),
                        GeoPoint(39.9235, 32.8541), GeoPoint(39.9235, 32.858)),
                    etaPrimary = "37 min", etaSecondary = "9:47 AM", phoneBattery = 100 to true,
                    routingBadge = com.example.wayfindr.dash.RoutingSource.GOOGLE,
                    attribution = TileProvider.OSM_ATTRIBUTION, turnCard = card,
                ), w, h)
                canvas.restore()
            }
        }
        java.io.File(getExternalFilesDir(null), "navcard.png").outputStream().use {
            out.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
    }.start()

    /**
     * Debug only (CI smoke test): the dash frame path (render → preview copy → encode) for a bike
     * moving at 50 km/h through a slow curve, at the old 4 fps and the current cadence. Writes
     * achieved fps, ms per stage and bytes per frame to files/framebench.txt.
     */
    private fun runFrameBench() = Thread {
        val w = DashEncoder.WIDTH; val h = DashEncoder.HEIGHT
        val renderer = MapRenderer(TileProvider(applicationContext, kotlinx.coroutines.MainScope()))
        val bmp = android.graphics.Bitmap.createBitmap(w, h, android.graphics.Bitmap.Config.ARGB_8888)
        val report = StringBuilder()
        for (frameMs in listOf(250L, com.example.wayfindr.viewmodel.DashViewModel.FRAME_MS_ACTIVE)) {
            var bytes = 0L; var frames = 0
            val e = DashEncoder { data, _ -> bytes += data.size; frames++ }.also { it.prepare() }
            val render = ArrayList<Long>(); val copy = ArrayList<Long>(); val encode = ArrayList<Long>()
            val t0 = System.currentTimeMillis(); var next = t0; var sent = 0
            while (System.currentTimeMillis() - t0 < 12_000) {
                val s = (System.currentTimeMillis() - t0) / 1000.0
                val hdg = 20.0 + s * 3.0                    // slow curve, 3°/s
                val d = 13.9 * s                            // 50 km/h
                val lat = 39.9208 + d * Math.cos(Math.toRadians(hdg)) / 111_195.0
                val lng = 32.8541 + d * Math.sin(Math.toRadians(hdg)) / (111_195.0 * Math.cos(Math.toRadians(39.92)))
                val a = System.nanoTime()
                renderer.draw(android.graphics.Canvas(bmp), MapRenderer.Frame(
                    centerLat = lat, centerLng = lng, zoom = 19, headingUp = true,
                    cameraHeading = hdg.toFloat(), riderHeading = hdg.toFloat(), riderLat = lat, riderLng = lng,
                    etaPrimary = "37 min", etaSecondary = "9:47 AM",
                    attribution = TileProvider.OSM_ATTRIBUTION,
                ), w, h)
                val b = System.nanoTime()
                bmp.copy(android.graphics.Bitmap.Config.ARGB_8888, false)
                val c = System.nanoTime()
                e.renderBitmap(bmp); e.drain()
                val d2 = System.nanoTime()
                if (s > 2) { render += (b - a) / 1_000_000; copy += (c - b) / 1_000_000; encode += (d2 - c) / 1_000_000; sent++ }
                next += frameMs
                (next - System.currentTimeMillis()).takeIf { it > 0 }?.let { Thread.sleep(it) }
            }
            e.release()
            fun List<Long>.p(q: Double) = if (isEmpty()) -1 else sorted()[((size - 1) * q).toInt()]
            report.append("target=${1000 / frameMs}fps achieved=%.1ffps render avg=%.1f p95=%d ms, preview copy avg=%.1f ms, encode avg=%.1f p95=%d ms, %d B/frame, %.0f kbps, map step=%.1f px/frame\n".format(
                sent / 10.0, render.average(), render.p(0.95), copy.average(), encode.average(), encode.p(0.95),
                if (frames > 0) bytes / frames else 0, bytes * 8 / 12_000.0,
                13.9 * frameMs / 1000.0 / (156_543.03 * Math.cos(Math.toRadians(39.92)) / (1 shl 19))))
        }
        android.util.Log.i("FrameBench", report.toString())
        java.io.File(getExternalFilesDir(null), "framebench.txt").writeText(report.toString())
    }.start()

    companion object {
        const val ACTION_RENDER_NAV_CARD = "com.example.wayfindr.RENDER_NAV_CARD"
        const val ACTION_FRAME_BENCH = "com.example.wayfindr.FRAME_BENCH"
    }
}
