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

    companion object {
        const val ACTION_RENDER_NAV_CARD = "com.example.wayfindr.RENDER_NAV_CARD"
    }
}
