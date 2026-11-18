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
            ACTION_RENDER_NAV_CARD -> if (com.example.wayfindr.util.TestHooks.on(this)) renderNavCardPreview()
            ACTION_FRAME_BENCH -> if (com.example.wayfindr.util.TestHooks.on(this)) runFrameBench()
            ACTION_OFFLINE_TEST -> if (com.example.wayfindr.util.TestHooks.on(this)) runOfflineTest(intent)
            ACTION_SAFETY_TEST -> if (com.example.wayfindr.util.TestHooks.on(this)) runSafetyTest()
            ACTION_SEARCH_TEST -> if (com.example.wayfindr.util.TestHooks.on(this)) runSearchTest()
        }
    }

    /** Debug only (CI smoke test): draws console frames with fake turns to files/navcard.png. */
    private fun renderNavCardPreview() = Thread {
        val g = DashGlyph
        val cards = listOf(
            MapRenderer.TurnCard(g.TURN_RIGHT, 347.0, "Atatürk Bulvarı'na sağa dönün", g.TURN_LEFT),
            MapRenderer.TurnCard(g.TURN_SLIGHT_LEFT, 1240.0, "Hafif sola, Eskişehir Yolu / Konya Devlet Yolu üzerinden devam edin"),
            MapRenderer.TurnCard(g.TURN_SHARP_RIGHT, 80.0, "Keskin sağ, Gazi Mustafa Kemal Bulvarı"),
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
        // Driving along a real route at 30–120 km/h: frame cadence, frame cost and position lag, before/after.
        report.append(runCatching { com.example.wayfindr.util.FrameBench.run(applicationContext) }
            .getOrElse { "FrameBench failed: $it\n" + it.stackTraceToString() })
        android.util.Log.i("FrameBench", report.toString())
        java.io.File(getExternalFilesDir(null), "framebench.txt").writeText(report.toString())
    }.start()

    /**
     * Debug only (CI smoke test of the Türkiye çevrimdışı paketi). Expects a map extract and
     * segment(s) already pushed to files/offline/ (see .github/scripts/smoke.sh): routes Kızılay →
     * Gölbaşı through [com.example.wayfindr.dash.nav.Router] (no network → built-in BRouter) and
     * draws console frames from the offline vector map. Writes files/offline-route.txt and
     * files/offline-console.png.
     */
    private fun runOfflineTest(intent: Intent) = Thread {
        val ctx = applicationContext
        val report = StringBuilder()
        intent.getStringExtra("map")?.let {
            com.example.wayfindr.dash.offline.OfflinePack.debugInstallMap(ctx, it, intent.getStringExtra("date") ?: "20000101")
        }
        intent.getStringExtra("search")?.let {
            com.example.wayfindr.dash.offline.OfflinePack.debugInstallSearch(ctx, it, intent.getStringExtra("date") ?: "20000101")
        }
        report.append("internet: ${com.example.wayfindr.util.InternetNetwork.validated(ctx) != null}\n")
        report.append("search: ${com.example.wayfindr.dash.offline.OfflinePack.searchFile(ctx)}\n")
        for (q in listOf("mamak devlet hastanesi", "kizilay", "tunali hilmi", "ISTANBUL", "Çankaya", "Mamak")) {
            val hits = com.example.wayfindr.dash.offline.OfflineSearch.search(ctx, q, 39.9208 to 32.8541, 3)
            report.append("  ara '$q': " + hits.joinToString(" | ") { "${it.name} · ${it.subtitle} %.5f,%.5f".format(java.util.Locale.ROOT, it.lat, it.lng) }.ifEmpty { "-" } + "\n")
        }
        report.append("map: ${com.example.wayfindr.dash.offline.OfflinePack.mapFile(ctx)}\n")
        val from = GeoPoint(39.9208, 32.8541); val to = GeoPoint(39.7880, 32.8060)
        val t0 = System.currentTimeMillis()
        val route = kotlinx.coroutines.runBlocking { com.example.wayfindr.dash.nav.Router.route(ctx, from, to) }
        report.append("route: ${if (route == null) "FAILED (${com.example.wayfindr.dash.nav.OfflineRouter.lastError})" else
            "source=${route.source} %.1f km %.0f min, ${route.geometry.size} points, ${route.maneuvers.size} maneuvers".format(
                route.totalMeters / 1000, route.totalSeconds / 60)} in ${System.currentTimeMillis() - t0} ms\n")
        route?.maneuvers?.take(12)?.forEach { report.append("  %.0f m: %s (%s)\n".format(it.cumulativeMeters, it.instruction, it.googleManeuver)) }
        java.io.File(getExternalFilesDir(null), "offline-route.txt").writeText(report.toString())

        val w = DashEncoder.WIDTH; val h = DashEncoder.HEIGHT
        val tiles = TileProvider(ctx, kotlinx.coroutines.MainScope()).apply { forceVector = intent.getBooleanExtra("force", false) }
        val renderer = MapRenderer(tiles)
        val views = listOf(Triple(39.9208, 32.8541, 15), Triple(39.9208, 32.8541, 17), Triple(39.8600, 32.8300, 13))
        val out = android.graphics.Bitmap.createBitmap(w, h * views.size * 2, android.graphics.Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(out)
        val tr = System.currentTimeMillis()
        repeat(4) { pass ->   // tiles are rendered in the background; later passes draw them
            if (pass > 0) Thread.sleep(12_000)
            var row = 0
            for (dark in listOf(false, true)) for ((lat, lng, z) in views) {
                canvas.save(); canvas.translate(0f, (h * row).toFloat()); canvas.clipRect(0, 0, w, h)
                renderer.draw(canvas, MapRenderer.Frame(
                    centerLat = lat, centerLng = lng, zoom = z, riderLat = 39.9208, riderLng = 32.8541,
                    destLat = to.lat, destLng = to.lng, route = route?.geometry ?: emptyList(),
                    etaPrimary = route?.let { "${(it.totalSeconds / 60).toInt()} dk" }, etaSecondary = "çevrimdışı",
                    routingBadge = route?.source, attribution = TileProvider.OSM_ATTRIBUTION, dark = dark,
                ), w, h)
                canvas.restore(); row++
            }
        }
        java.io.File(getExternalFilesDir(null), "offline-console.png").outputStream().use {
            out.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, it)
        }
        val cache = java.io.File(cacheDir.path).listFiles()?.filter { it.name.startsWith("tiles_vec_") }
            ?.sumOf { it.listFiles()?.size ?: 0 } ?: 0
        java.io.File(getExternalFilesDir(null), "offline-route.txt")
            .appendText("console: ${views.size * 2} frames, $cache offline tiles rendered in ${System.currentTimeMillis() - tr} ms\n")
        android.util.Log.i("OfflineTest", report.toString())
    }.start()

    /**
     * Debug only (CI, needs internet): the live radar / speed-limit fetch exactly as on the road —
     * NavPlus.fetchLimit (Overpass maxspeed around a point) on known Ankara roads and NavPlus.rdFetch
     * (speed cameras + traffic calming within 6 km of Kızılay) — with "Radar ve hız limiti" on, then
     * once with it off (must fetch nothing). Writes files/safety.txt.
     */
    private fun runSafetyTest() = Thread {
        val ctx = applicationContext
        val cfg = com.example.wayfindr.dash.DashConfig.get(ctx)
        val was = cfg.offlineSafety.value
        val report = StringBuilder("internet: ${com.example.wayfindr.util.InternetNetwork.validated(ctx) != null}\n")
        cfg.setOfflineSafety(true)
        // Parser check with Overpass-style answers (the app's own parseLimit).
        for (v in listOf("50", "90 mph", "TR:urban", "TR:rural", "TR:motorway", "none"))
            report.append("parseLimit('$v') = ${com.example.wayfindr.dash.nav.NavPlus.parseLimit(v)}\n")
        val roads = listOf(
            "Eskişehir Yolu (ODTÜ)" to (39.90795 to 32.77297),
            "İstanbul Yolu (Batıkent)" to (39.95560 to 32.71520),
            "Atatürk Bulvarı (Kızılay)" to (39.92310 to 32.85395),
        )
        var anyLimit = false
        for ((name, p) in roads) {
            com.example.wayfindr.dash.nav.NavPlus.spdLimit = 0
            val t0 = System.currentTimeMillis()
            com.example.wayfindr.dash.nav.NavPlus.fetchLimit(p.first, p.second)
            if (com.example.wayfindr.dash.nav.NavPlus.spdLimit > 0) anyLimit = true
            report.append("hız limiti $name ${p.first},${p.second}: ${com.example.wayfindr.dash.nav.NavPlus.spdLimit} km/s (${System.currentTimeMillis() - t0} ms) [${com.example.wayfindr.dash.nav.NavPlus.ovLast}]\n")
            Thread.sleep(1500)   // be gentle with the public Overpass server
        }
        com.example.wayfindr.dash.nav.NavPlus.rdData = null; com.example.wayfindr.dash.nav.NavPlus.rdN = 0
        var t0 = System.currentTimeMillis()
        com.example.wayfindr.dash.nav.NavPlus.rdFetch(39.9208, 32.8541)
        val kinds = (com.example.wayfindr.dash.nav.NavPlus.rdData as? Array<*>)?.getOrNull(2) as? IntArray
        val cams = kinds?.take(com.example.wayfindr.dash.nav.NavPlus.rdN)?.count { it == 1 } ?: 0
        report.append("radar sunucu: ${com.example.wayfindr.dash.nav.NavPlus.ovLast}\n")
        report.append("radar Kızılay 6 km: ${com.example.wayfindr.dash.nav.NavPlus.rdN} nokta, $cams hız kamerası, ${com.example.wayfindr.dash.nav.NavPlus.rdN - cams} kasis/yavaşlatma (${System.currentTimeMillis() - t0} ms)\n")
        report.append("SONUÇ açık: limit=${if (anyLimit) "OK" else "YOK"} radar=${if (com.example.wayfindr.dash.nav.NavPlus.rdN > 0) "OK" else "YOK"}\n")
        cfg.setOfflineSafety(false)
        com.example.wayfindr.dash.nav.NavPlus.spdLimit = 0; com.example.wayfindr.dash.nav.NavPlus.rdData = null; com.example.wayfindr.dash.nav.NavPlus.rdN = 0
        t0 = System.currentTimeMillis()
        com.example.wayfindr.dash.nav.NavPlus.fetchLimit(roads[0].second.first, roads[0].second.second)
        com.example.wayfindr.dash.nav.NavPlus.rdFetch(39.9208, 32.8541)
        report.append("SONUÇ kapalı: limit=${com.example.wayfindr.dash.nav.NavPlus.spdLimit} radar=${com.example.wayfindr.dash.nav.NavPlus.rdN} (${System.currentTimeMillis() - t0} ms) : " +
            "${if (com.example.wayfindr.dash.nav.NavPlus.spdLimit == 0 && com.example.wayfindr.dash.nav.NavPlus.rdN == 0) "OK (hiç sorgu yok)" else "HATA"}\n")
        cfg.setOfflineSafety(was)
        com.example.wayfindr.dash.nav.NavPlus.spdLimit = 0
        android.util.Log.i("SafetyTest", report.toString())
        java.io.File(getExternalFilesDir(null), "safety.txt").writeText(report.toString())
    }.start()

    /**
     * Debug only (CI, needs internet): the Rota tab's suggestion → selection path for areas and a
     * POI. Each query goes through PlaceSearch.suggestions (Google Places when a key is set), the
     * same-name suggestion is picked with RouteViewModel.selectPlaceSuggestion, and the resulting
     * destination is logged. Once with the offline index (if installed), once with only the online
     * centre lookup. Writes files/search-centre.txt; "Mamak" must land within 3 km of 39.93, 32.91.
     */
    private fun runSearchTest() = Thread {
        val ctx = applicationContext
        val near = 39.9208 to 32.8541
        val ac = com.example.wayfindr.dash.nav.AreaCentre
        val report = StringBuilder("internet: ${com.example.wayfindr.util.InternetNetwork.validated(ctx) != null}, " +
            "Google key: ${com.example.wayfindr.dash.nav.GPlus.isReal(com.example.wayfindr.dash.nav.GPlus.key())}, " +
            "offline index: ${com.example.wayfindr.dash.offline.OfflineSearch.available(ctx)}\n")
        val queries = listOf("Mamak", "Çankaya", "Keçiören", "Kızılay", "Mamak Devlet Hastanesi")
        val fold = com.example.wayfindr.dash.offline.OfflineSearch::norm
        val savedKey = com.example.wayfindr.dash.nav.GPlus.key
        // The build's key (CI secret) stands in for a key typed into the app, in memory only, for the Places pass.
        val places = !com.example.wayfindr.dash.nav.GPlus.isReal(com.example.wayfindr.dash.nav.GPlus.key()) && com.example.wayfindr.dash.nav.GPlus.isReal(com.example.wayfindr.BuildConfig.MAPS_API_KEY)
        val passes = listOf(false to true, false to false) + (if (places) listOf(true to true, true to false) else emptyList())
        for ((usePlaces, offline) in passes) {
            if (usePlaces) { com.example.wayfindr.dash.nav.GPlus.key = com.example.wayfindr.BuildConfig.MAPS_API_KEY; com.example.wayfindr.dash.nav.GPlus.placesFailUntil = 0 }
            ac.useOffline = offline; ac.clearCache(); com.example.wayfindr.dash.nav.GPlus.pcache?.clear()
            report.append("== ${if (usePlaces) "Google Places" else "anahtarsız (Photon + geocoder)"}; merkez kaynağı: " +
                "${if (offline) "çevrimdışı dizin, sonra çevrimiçi" else "yalnız çevrimiçi (Photon/Nominatim)"}\n")
            for (q in queries) {
                val hits = kotlinx.coroutines.runBlocking { com.example.wayfindr.dash.nav.PlaceSearch.suggestions(ctx, q, near) }
                val pick = hits.firstOrNull { fold(it.name) == fold(q) } ?: hits.firstOrNull()
                if (pick == null) { report.append("'$q': öneri yok (${com.example.wayfindr.dash.nav.PlaceSearch.lastError})\n"); continue }
                runOnUiThread { routeViewModel.clearDestinations(); routeViewModel.selectPlaceSuggestion(pick) }
                Thread.sleep(800)
                val d = routeViewModel.state.value.destination
                val km = if (d?.lat != null && d.lng != null) GeoPoint.distMeters(GeoPoint(d.lat, d.lng), GeoPoint(39.93, 32.91)) / 1000 else Double.NaN
                report.append("'$q' → ${pick.name} (${pick.provider}; ${pick.subtitle}) hedef=%.5f,%.5f".format(java.util.Locale.ROOT, d?.lat, d?.lng) +
                    (if (q == "Mamak") " uzaklık(39.93,32.91)=%.2f km %s".format(java.util.Locale.ROOT, km,
                        if (km <= 3) "OK" else if (offline) "HATA" else "UYARI") else "") + "\n")
            }
            synchronized(ac.notes) { ac.notes.forEach { report.append("  $it\n") } }
            if (usePlaces) report.append("  Places: ${com.example.wayfindr.dash.nav.GPlus.lastErr ?: "-"}\n")
            com.example.wayfindr.dash.nav.GPlus.key = savedKey
        }
        ac.useOffline = true; ac.clearCache(); com.example.wayfindr.dash.nav.GPlus.pcache?.clear()
        runOnUiThread { routeViewModel.clearDestinations() }
        android.util.Log.i("SearchTest", report.toString())
        java.io.File(getExternalFilesDir(null), "search-centre.txt").writeText(report.toString())
    }.start()

    companion object {
        const val ACTION_SEARCH_TEST = "com.example.wayfindr.SEARCH_TEST"
        const val ACTION_SAFETY_TEST = "com.example.wayfindr.SAFETY_TEST"
        const val ACTION_OFFLINE_TEST = "com.example.wayfindr.OFFLINE_TEST"
        const val ACTION_RENDER_NAV_CARD = "com.example.wayfindr.RENDER_NAV_CARD"
        const val ACTION_FRAME_BENCH = "com.example.wayfindr.FRAME_BENCH"
    }
}
