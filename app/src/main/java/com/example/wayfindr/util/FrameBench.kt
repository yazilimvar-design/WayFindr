package com.example.wayfindr.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.wayfindr.dash.map.MapRenderer
import com.example.wayfindr.dash.map.Mercator
import com.example.wayfindr.dash.map.RiderMotion
import com.example.wayfindr.dash.map.TileProvider
import com.example.wayfindr.dash.nav.DashGlyph
import com.example.wayfindr.dash.nav.GeoPoint
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.dash.nav.Route
import com.example.wayfindr.dash.nav.RouteProgressMatcher
import com.example.wayfindr.dash.video.DashEncoder
import java.io.File
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

/**
 * Debug only (CI smoke test, behind [TestHooks]): the console frame path driven along a real Ankara
 * route (Kızılay → Gölbaşı, OSRM/OpenStreetMap geometry) at 30/60/90/120 km/h with ~1 Hz GPS fixes,
 * in real time at the dash cadence: position → render → H.264 encode, like DashViewModel's loop.
 *
 * Each speed runs twice on the same fix stream: "önce" = the V59 position path (camera ease toward
 * a prediction that counts from when the loop first saw the fix, 0.8 s lead) with the whole route
 * stroked and no tile look-ahead; "sonra" = [RiderMotion] (fix-age-aware prediction, fading
 * corrections, road-bearing heading) with route culling and look-ahead tiles. "sonra" runs first,
 * so the tile disk cache, if anything, favours "önce".
 *
 * Fix model (assumed, not measured on the bike): one fix per 1.0 s ± 50 ms, delivered 0.35 s ± 0.1 s
 * after its measurement, position noise σ 2 m along + 2 m across, speed σ 0.25 m/s, bearing σ 2°,
 * accuracy 4–20 m. Ground truth = constant speed along the route. "dash" columns compare with where
 * the bike is when the frame is SHOWN, assuming 0.5 s frame → dash display latency.
 */
object FrameBench {
    private const val ZOOM = 18
    private const val START_M = 3_000.0
    private const val RUN_S = 14.0
    private const val MEASURE_FROM_S = 2.0
    private const val FIX_AGE_S = 0.35
    private const val PIPE_S = 0.5
    private const val SMOOTH_TAU = 0.20       // DashViewModel's heading (and, before, position) ease
    private const val STRIP_SPEED = 90
    private const val STRIP_FROM = 40         // measured frame index of the first frame in the PNG strip
    private const val STRIP_N = 8

    private class Fix(
        val tMeas: Double, val tDeliver: Double, val lat: Double, val lng: Double,
        val speed: Float, val bearing: Float, val acc: Float,
    )

    private fun parseRoute(): List<GeoPoint> {
        val v = ROUTE.split(',').map { it.toDouble() }
        return List(v.size / 2) { GeoPoint(v[it * 2], v[it * 2 + 1]) }
    }

    private fun cumulative(g: List<GeoPoint>) = DoubleArray(g.size).also { c ->
        for (i in 1 until g.size) c[i] = c[i - 1] + GeoPoint.distMeters(g[i - 1], g[i])
    }

    /** Offset [p] by [east]/[north] metres. */
    private fun offset(p: GeoPoint, east: Double, north: Double) = GeoPoint(
        p.lat + north / 111_195.0, p.lng + east / (111_195.0 * cos(Math.toRadians(p.lat))),
    )

    private fun fixes(g: List<GeoPoint>, c: DoubleArray, v: Double, seed: Long): List<Fix> {
        val rnd = java.util.Random(seed)
        return (-1..(RUN_S + 2).toInt()).map { k ->
            val tm = k + (rnd.nextDouble() - 0.5) * 0.1
            val age = FIX_AGE_S + (rnd.nextDouble() - 0.5) * 0.2
            val s = START_M + v * tm
            val p = RiderMotion.pointAt(g, c, s)!!
            val b = RiderMotion.bearingAt(g, c, s) ?: 0f
            val br = Math.toRadians(b.toDouble())
            val along = rnd.nextGaussian() * 2.0; val across = rnd.nextGaussian() * 2.0
            val q = offset(p, along * sin(br) + across * cos(br), along * cos(br) - across * sin(br))
            Fix(tm, tm + age, q.lat, q.lng, (v + rnd.nextGaussian() * 0.25).toFloat(),
                ((b + rnd.nextGaussian() * 2.0 + 360.0) % 360.0).toFloat(), (4.0 + rnd.nextDouble() * 16.0).toFloat())
        }
    }

    private fun List<Double>.pct(q: Double) = if (isEmpty()) Double.NaN else sorted()[((size - 1) * q).toInt()]
    private fun ease(cur: Float, target: Float, a: Double): Float {
        val dh = (((target - cur) % 360f) + 540f) % 360f - 180f
        return ((cur + dh * a.toFloat()) % 360f + 360f) % 360f
    }

    private class Result(
        val fps: Double, val workP50: Double, val workP95: Double, val workMax: Double, val dropped: Int,
        val gapMax: Double, val errRenderMean: Double, val errDashMean: Double, val errDashP95: Double,
        val errDashMax: Double, val latP95: Double, val stepDevP95: Double, val stepDevMax: Double,
        val missFrames: Int, val frames: Int, val bytesPerFrame: Long,
    )

    fun run(ctx: Context): String {
        val g = parseRoute(); val c = cumulative(g)
        val route = Route(g, emptyList(), c.last(), c.last() / 13.0, c)
        val tiles = TileProvider(ctx, kotlinx.coroutines.MainScope())
        val renderer = MapRenderer(tiles)
        val w = DashEncoder.WIDTH; val h = DashEncoder.HEIGHT
        val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val mPerPx = 156_543.03 * cos(Math.toRadians(39.93)) / (1 shl ZOOM)
        val out = StringBuilder()
        out.append("\n== Sürüş benzetimi: Kızılay→Gölbaşı rotası, 1 Hz konum, zoom $ZOOM (%.2f m/px), ${DashViewModelFrameMs()} ms kare, kaynak=%s ==\n"
            .format(mPerPx, if (com.example.wayfindr.dash.nav.GPlus.tilesOn) "Google karoları" else "OSM karoları"))
        out.append("Varsayım: konum 1.0±0.05 s'de bir, ölçümden 0.35±0.1 s sonra gelir, gürültü σ2 m; 'konsol' = kare +%.1f s sonra görünür.\n".format(PIPE_S))
        out.append("hız      | sürüm | fps  | iş p50/p95/max ms | düşen | konum farkı (+önde/−geride) render ort / konsol ort, |p95|, |max| m | yanal p95 | kare adım sapması p95/max px | karo eksik kare\n")
        val strips = HashMap<String, MutableList<Bitmap>>()
        // Warm-up (JIT, encoder, first tiles) — not measured.
        runOne(ctx, g, c, route, renderer, tiles, bmp, 60, legacy = false, measure = false, seconds = 3.0, strip = null, video = null)
        for (kmh in listOf(30, 60, 90, 120)) {
            for (legacy in listOf(false, true)) {
                tiles.debugEvictMemory()
                val strip = if (kmh == STRIP_SPEED) ArrayList<Bitmap>().also { strips[if (legacy) "ÖNCE" else "SONRA"] = it } else null
                val video = if (kmh == STRIP_SPEED) File(ctx.getExternalFilesDir(null), "bench$kmh-${if (legacy) "once" else "sonra"}.h264") else null
                val r = runOne(ctx, g, c, route, renderer, tiles, bmp, kmh, legacy, true, RUN_S, strip, video)
                out.append("%3d km/sa | %-5s | %4.1f | %5.1f / %5.1f / %5.1f | %3d (en uzun ara %.0f ms) | %+6.1f / %+6.1f, %5.1f, %5.1f | %4.1f | %5.1f / %5.1f | %d/%d, %d B/kare\n".format(
                    kmh, if (legacy) "önce" else "sonra", r.fps, r.workP50, r.workP95, r.workMax, r.dropped, r.gapMax,
                    r.errRenderMean, r.errDashMean, r.errDashP95, r.errDashMax, r.latP95,
                    r.stepDevP95 / mPerPx, r.stepDevMax / mPerPx, r.missFrames, r.frames, r.bytesPerFrame))
            }
        }
        writeStrip(ctx, strips)
        out.append(longRoute(renderer))
        return out.toString()
    }

    private fun DashViewModelFrameMs() = com.example.wayfindr.viewmodel.DashViewModel.FRAME_MS_ACTIVE

    private fun runOne(
        ctx: Context, g: List<GeoPoint>, c: DoubleArray, route: Route, renderer: MapRenderer, tiles: TileProvider,
        bmp: Bitmap, kmh: Int, legacy: Boolean, measure: Boolean, seconds: Double,
        strip: MutableList<Bitmap>?, video: File?,
    ): Result {
        val v = kmh / 3.6
        val fx = fixes(g, c, v, 1_000L + kmh)
        renderer.cullRoute = !legacy
        val frameMs = DashViewModelFrameMs()
        val videoOut = video?.outputStream()
        var bytes = 0L; var encFrames = 0
        val enc = DashEncoder { data, _ -> bytes += data.size; encFrames++; videoOut?.write(data) }.also { it.prepare() }
        val motion = RiderMotion()
        val dest = g.last()
        var progress = 0.0
        var idx = -1
        var fixLat = 0.0; var fixLng = 0.0; var seenT = 0.0; var arrivalNs = 0L; var measNs = 0L
        var camLat = 0.0; var camLng = 0.0; var camHdg = 0f; var camInit = false
        var lastNs = 0L; var lastWarm = 0.0
        val work = ArrayList<Double>(); val gaps = ArrayList<Double>()
        val errR = ArrayList<Double>(); val errD = ArrayList<Double>(); val lat = ArrayList<Double>(); val dev = ArrayList<Double>()
        var prevS = Double.NaN; var prevT = 0.0
        var missFrames = 0; var measured = 0
        val t0 = System.nanoTime(); var next = t0
        while (true) {
            val nowNs = System.nanoTime()
            val t = (nowNs - t0) / 1e9
            if (t >= seconds) break
            val dt = if (lastNs == 0L) 0.1 else ((nowNs - lastNs) / 1e9).coerceIn(0.0, 0.5)
            if (measure && t >= MEASURE_FROM_S && lastNs != 0L) gaps += (nowNs - lastNs) / 1e6
            lastNs = nowNs
            var newFix = false
            while (idx + 1 < fx.size && fx[idx + 1].tDeliver <= t) { idx++; newFix = true }
            if (idx >= 0) {
                val f = fx[idx]
                if (newFix) {
                    val m = RouteProgressMatcher.match(g, c, GeoPoint(f.lat, f.lng), progress, f.bearing, f.speed.toDouble())
                    if (m != null) {
                        progress = if (progress == 0.0) m.progressMeters else maxOf(progress - 25.0, m.progressMeters)
                        val snapped = m.lateralDistanceMeters <= 35.0
                        fixLat = if (snapped) m.snappedPoint.lat else f.lat; fixLng = if (snapped) m.snappedPoint.lng else f.lng
                    }
                    seenT = t; arrivalNs = nowNs; measNs = t0 + (f.tMeas * 1e9).toLong()
                }
                val tau = SMOOTH_TAU * (if (f.acc > 25f) 4.0 else if (f.acc > 12f) 1.5 else 1.0)
                val a = if (camInit) 1.0 - exp(-dt / tau) else 1.0
                val here = RiderMotion.pointAt(g, c, progress)
                val onRoute = here != null && GeoPoint.distMeters(here, GeoPoint(fixLat, fixLng)) <= 12.0
                var hdg: Float
                if (legacy) {
                    // V59 DashViewModel.tick(): elapsed from when the loop SAW the fix, 0.8 s lead, 2.25 s
                    // cap, confidence full to 25 m; then the camera eases toward that target.
                    val conf = ((50f - f.acc) / 25f).toDouble().coerceIn(0.0, 1.0)
                    val dist = f.speed * (t - seenT + 0.8).coerceIn(0.0, 2.25) * conf
                    val p = if (onRoute) RiderMotion.pointAt(g, c, progress + dist)!! else GeoPoint(fixLat, fixLng)
                    if (!camInit) { camLat = p.lat; camLng = p.lng } else { camLat += (p.lat - camLat) * a; camLng += (p.lng - camLng) * a }
                    hdg = f.bearing
                } else {
                    val dist = RiderMotion.distance(f.speed, RiderMotion.sinceFixSeconds(measNs, arrivalNs, nowNs) + RiderMotion.LEAD_S) *
                        RiderMotion.confidence(f.acc)
                    val ps = progress + dist
                    val p = if (onRoute) RiderMotion.pointAt(g, c, ps)!! else GeoPoint(fixLat, fixLng)
                    val shown = motion.step(p.lat, p.lng, newFix, nowNs, RiderMotion.tauFor(f.acc))
                    camLat = shown.first; camLng = shown.second
                    hdg = (if (onRoute) RiderMotion.bearingAt(g, c, ps) else null) ?: f.bearing
                    if (t - lastWarm > 0.5) {
                        lastWarm = t
                        for (s in doubleArrayOf(3.0, 6.0)) RiderMotion.pointAt(g, c, ps + f.speed * s)?.let { tiles.warmAhead(it.lat, it.lng, ZOOM) }
                    }
                }
                camHdg = if (!camInit) hdg else ease(camHdg, hdg, a)
                camInit = true
                val missBefore = renderer.tileMisses
                @Suppress("UNCHECKED_CAST")
                val ahead = NavPlus.aheadGeometry(route, progress, camLat, camLng) as List<GeoPoint>? ?: g
                renderer.draw(Canvas(bmp), MapRenderer.Frame(
                    centerLat = camLat, centerLng = camLng, zoom = ZOOM, headingUp = true,
                    cameraHeading = camHdg, riderHeading = hdg, riderLat = camLat, riderLng = camLng,
                    destLat = dest.lat, destLng = dest.lng, route = ahead,
                    etaPrimary = "24 dk", etaSecondary = "13:32", remainingText = "%.1f km".format((c.last() - progress) / 1000),
                    turnCard = MapRenderer.TurnCard(DashGlyph.TURN_RIGHT, 420.0, "Konya Yolu'na sağa dönün"),
                    routingBadge = com.example.wayfindr.dash.RoutingSource.GOOGLE,
                    attribution = TileProvider.OSM_ATTRIBUTION, phoneBattery = 80 to true,
                ), DashEncoder.WIDTH, DashEncoder.HEIGHT)
                enc.renderBitmap(bmp); enc.drain()
                if (measure && t >= MEASURE_FROM_S) {
                    work += (System.nanoTime() - nowNs) / 1e6
                    if (renderer.tileMisses > missBefore) missFrames++
                    val truthR = START_M + v * t
                    val m = RouteProgressMatcher.match(g, c, GeoPoint(camLat, camLng), truthR, null, v)
                    if (m != null) {
                        errR += m.progressMeters - truthR
                        errD += m.progressMeters - (truthR + v * PIPE_S)
                        lat += m.lateralDistanceMeters
                        if (!prevS.isNaN()) dev += abs((m.progressMeters - prevS) - v * (t - prevT))
                        prevS = m.progressMeters; prevT = t
                    }
                    if (strip != null && measured in STRIP_FROM until STRIP_FROM + STRIP_N) {
                        strip += bmp.copy(Bitmap.Config.ARGB_8888, true).also {
                            markTruth(it, camLat, camLng, camHdg, RiderMotion.pointAt(g, c, truthR + v * PIPE_S)!!)
                        }
                    }
                    measured++
                }
            }
            next += frameMs * 1_000_000L
            val sleep = (next - System.nanoTime()) / 1_000_000L
            if (sleep > 1) Thread.sleep(sleep) else if (sleep < -frameMs) next = System.nanoTime()
        }
        enc.release(); videoOut?.close()
        val span = seconds - MEASURE_FROM_S
        return Result(
            fps = measured / span, workP50 = work.pct(0.5), workP95 = work.pct(0.95), workMax = work.maxOrNull() ?: 0.0,
            dropped = gaps.count { it > frameMs * 1.5 }, gapMax = gaps.maxOrNull() ?: 0.0,
            errRenderMean = errR.average(), errDashMean = errD.average(), errDashP95 = errD.map { abs(it) }.pct(0.95),
            errDashMax = errD.maxOfOrNull { abs(it) } ?: 0.0, latP95 = lat.pct(0.95),
            stepDevP95 = dev.pct(0.95), stepDevMax = dev.maxOrNull() ?: 0.0,
            missFrames = missFrames, frames = measured, bytesPerFrame = if (encFrames > 0) bytes / encFrames else 0,
        )
    }

    /** Green ring where the bike really is when this frame is shown (same transform as MapRenderer, heading-up). */
    private fun markTruth(b: Bitmap, camLat: Double, camLng: Double, hdg: Float, truth: GeoPoint) {
        val ts = Mercator.TILE_SIZE
        val dx = (Mercator.lngToTileX(truth.lng, ZOOM) - Mercator.lngToTileX(camLng, ZOOM)) * ts
        val dy = (Mercator.latToTileY(truth.lat, ZOOM) - Mercator.latToTileY(camLat, ZOOM)) * ts
        val th = Math.toRadians(-hdg.toDouble())
        val x = b.width / 2f + (dx * cos(th) - dy * sin(th)).toFloat()
        val y = b.height * 0.66f + (dx * sin(th) + dy * cos(th)).toFloat()
        Canvas(b).drawCircle(x, y, 9f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE; strokeWidth = 3f; color = Color.rgb(0, 200, 83)
        })
    }

    /** files/framebench-frames.png: 8 consecutive frames at 90 km/h, ÖNCE left, SONRA right. */
    private fun writeStrip(ctx: Context, strips: Map<String, List<Bitmap>>) {
        val cols = listOf("ÖNCE", "SONRA")
        val w = DashEncoder.WIDTH; val h = DashEncoder.HEIGHT; val head = 28
        val out = Bitmap.createBitmap(w * 2 + 8, head + h * STRIP_N, Bitmap.Config.ARGB_8888)
        val cv = Canvas(out); cv.drawColor(Color.BLACK)
        val p = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.WHITE; textSize = 16f }
        cols.forEachIndexed { i, name ->
            cv.drawText("$name — $STRIP_SPEED km/sa, 100 ms arayla; yeşil halka = gerçek konum", i * (w + 8f) + 6f, 20f, p)
            strips[name]?.forEachIndexed { k, bm -> cv.drawBitmap(bm, i * (w + 8f), (head + h * k).toFloat(), null) }
        }
        File(ctx.getExternalFilesDir(null), "framebench-frames.png").outputStream().use { out.compress(Bitmap.CompressFormat.PNG, 100, it) }
    }

    /** Render cost of a 400 km route (20 000 points): whole route stroked every frame vs only what is on screen. */
    private fun longRoute(renderer: MapRenderer): String {
        val pts = ArrayList<GeoPoint>(20_000)
        var p = GeoPoint(39.9208, 32.8541); var b = 230.0
        repeat(20_000) { i ->
            pts += p
            b += sin(i / 40.0) * 4.0
            val br = Math.toRadians(b)
            p = offset(p, 20.0 * sin(br), 20.0 * cos(br))
        }
        val c = cumulative(pts)
        val bmp = Bitmap.createBitmap(DashEncoder.WIDTH, DashEncoder.HEIGHT, Bitmap.Config.ARGB_8888)
        val sb = StringBuilder("Uzun rota (400 km, 20 000 nokta) çizimi, zoom $ZOOM:")
        for (cull in listOf(false, true)) {
            renderer.cullRoute = cull
            val ms = ArrayList<Double>()
            for (k in 0 until 30) {
                val at = 500 + k
                val hdg = GeoPoint.bearing(pts[at], pts[at + 1]).toFloat()
                val a = System.nanoTime()
                renderer.draw(Canvas(bmp), MapRenderer.Frame(
                    centerLat = pts[at].lat, centerLng = pts[at].lng, zoom = ZOOM, headingUp = true,
                    cameraHeading = hdg, riderHeading = hdg, riderLat = pts[at].lat, riderLng = pts[at].lng,
                    route = pts.subList(at, pts.size),
                ), DashEncoder.WIDTH, DashEncoder.HEIGHT)
                if (k >= 5) ms += (System.nanoTime() - a) / 1e6
            }
            sb.append(" %s ort %.1f p95 %.1f ms;".format(if (cull) "sonra (görünen parça)" else "önce (tüm rota)", ms.average(), ms.pct(0.95)))
        }
        renderer.cullRoute = true
        // Route match per tick: on the route (local window) and 150 m off it (whole-route search).
        val off = offset(pts[600], 150.0, 0.0)
        for ((name, pos) in listOf("rotada" to pts[600], "rota dışı 150 m" to off)) {
            val a = System.nanoTime()
            repeat(50) { RouteProgressMatcher.match(pts, c, pos, c[600], null, 25.0) }
            sb.append(" rota eşleme $name %.2f ms/çağrı;".format((System.nanoTime() - a) / 1e6 / 50))
        }
        sb.append("\n")
        return sb.toString()
    }

    /** Kızılay → Gölbaşı, 21.2 km (lat,lng pairs; geometry © OpenStreetMap contributors via OSRM). */
    private const val ROUTE =
        "39.92086,32.85410,39.92087,32.85391,39.92089,32.85371,39.92093,32.85339,39.92095,32.85323,39.92098,32.85310,39" +
        ".92101,32.85298,39.92117,32.85254,39.92136,32.85211,39.92151,32.85187,39.92157,32.85178,39.92166,32.85167,39.9" +
        "2173,32.85157,39.92203,32.85123,39.92248,32.85068,39.92305,32.84996,39.92313,32.84985,39.92345,32.84945,39.923" +
        "71,32.84912,39.92390,32.84891,39.92391,32.84890,39.92394,32.84887,39.92405,32.84876,39.92411,32.84870,39.92436" +
        ",32.84850,39.92453,32.84837,39.92469,32.84826,39.92489,32.84813,39.92512,32.84800,39.92523,32.84793,39.92550,3" +
        "2.84781,39.92560,32.84777,39.92583,32.84766,39.92591,32.84762,39.92627,32.84743,39.92697,32.84710,39.92752,32." +
        "84683,39.92783,32.84668,39.92815,32.84653,39.92888,32.84617,39.92934,32.84590,39.92995,32.84548,39.93019,32.84" +
        "529,39.93073,32.84479,39.93126,32.84423,39.93173,32.84368,39.93222,32.84298,39.93257,32.84243,39.93271,32.8421" +
        "8,39.93279,32.84202,39.93280,32.84201,39.93290,32.84183,39.93322,32.84115,39.93349,32.84057,39.93371,32.84010," +
        "39.93406,32.83942,39.93426,32.83907,39.93450,32.83864,39.93490,32.83800,39.93494,32.83793,39.93507,32.83747,39" +
        ".93510,32.83731,39.93514,32.83712,39.93514,32.83699,39.93514,32.83698,39.93514,32.83686,39.93512,32.83671,39.9" +
        "3509,32.83656,39.93504,32.83638,39.93480,32.83561,39.93469,32.83523,39.93440,32.83429,39.93436,32.83415,39.934" +
        "04,32.83305,39.93394,32.83271,39.93357,32.83148,39.93311,32.82989,39.93306,32.82964,39.93305,32.82957,39.93305" +
        ",32.82954,39.93301,32.82928,39.93301,32.82921,39.93302,32.82915,39.93300,32.82910,39.93297,32.82905,39.93294,3" +
        "2.82902,39.93289,32.82900,39.93288,32.82891,39.93283,32.82877,39.93256,32.82816,39.93255,32.82808,39.93254,32." +
        "82797,39.93253,32.82785,39.93255,32.82769,39.93256,32.82764,39.93257,32.82756,39.93269,32.82644,39.93286,32.82" +
        "495,39.93288,32.82477,39.93288,32.82441,39.93287,32.82378,39.93286,32.82368,39.93281,32.82331,39.93273,32.8229" +
        "8,39.93272,32.82295,39.93266,32.82268,39.93256,32.82239,39.93220,32.82133,39.93208,32.82097,39.93203,32.82082," +
        "39.93198,32.82071,39.93198,32.82068,39.93181,32.82018,39.93175,32.82000,39.93172,32.81992,39.93169,32.81984,39" +
        ".93160,32.81940,39.93157,32.81927,39.93132,32.81909,39.93129,32.81907,39.93111,32.81893,39.93101,32.81885,39.9" +
        "3090,32.81877,39.93012,32.81817,39.92956,32.81772,39.92943,32.81762,39.92917,32.81744,39.92866,32.81716,39.928" +
        "40,32.81697,39.92828,32.81688,39.92747,32.81630,39.92697,32.81597,39.92674,32.81583,39.92652,32.81570,39.92587" +
        ",32.81535,39.92533,32.81509,39.92487,32.81489,39.92420,32.81465,39.92337,32.81438,39.92244,32.81415,39.92157,3" +
        "2.81397,39.92115,32.81392,39.92065,32.81386,39.92010,32.81383,39.91951,32.81382,39.91897,32.81382,39.91832,32." +
        "81386,39.91746,32.81393,39.91625,32.81399,39.91606,32.81400,39.91546,32.81407,39.91448,32.81418,39.91412,32.81" +
        "422,39.91302,32.81435,39.91237,32.81442,39.91157,32.81453,39.91104,32.81458,39.91071,32.81462,39.91029,32.8146" +
        "4,39.91011,32.81466,39.91001,32.81467,39.90957,32.81467,39.90947,32.81468,39.90868,32.81465,39.90820,32.81461," +
        "39.90783,32.81458,39.90740,32.81453,39.90572,32.81433,39.90329,32.81406,39.90093,32.81381,39.89974,32.81369,39" +
        ".89935,32.81365,39.89904,32.81361,39.89848,32.81356,39.89777,32.81348,39.89728,32.81342,39.89686,32.81339,39.8" +
        "9644,32.81334,39.89607,32.81330,39.89581,32.81328,39.89536,32.81324,39.89494,32.81319,39.89490,32.81318,39.894" +
        "51,32.81313,39.89428,32.81311,39.89403,32.81308,39.89364,32.81304,39.89308,32.81298,39.89276,32.81295,39.89269" +
        ",32.81294,39.89225,32.81289,39.89213,32.81288,39.89202,32.81287,39.89123,32.81278,39.89081,32.81274,39.89076,3" +
        "2.81274,39.89067,32.81273,39.89026,32.81268,39.89015,32.81267,39.88993,32.81265,39.88961,32.81263,39.88888,32." +
        "81258,39.88742,32.81240,39.88642,32.81230,39.88459,32.81214,39.88379,32.81206,39.88346,32.81202,39.88282,32.81" +
        "194,39.88216,32.81189,39.88096,32.81177,39.88034,32.81171,39.87913,32.81159,39.87745,32.81140,39.87575,32.8112" +
        "3,39.87413,32.81105,39.87235,32.81087,39.87055,32.81068,39.86936,32.81055,39.86709,32.81030,39.86559,32.81012," +
        "39.86538,32.81011,39.86518,32.81010,39.86476,32.81009,39.86433,32.81009,39.86394,32.81010,39.86351,32.81012,39" +
        ".86278,32.81015,39.86242,32.81016,39.86207,32.81016,39.86176,32.81015,39.86143,32.81011,39.86112,32.81005,39.8" +
        "6083,32.81000,39.86063,32.80995,39.86042,32.80989,39.86018,32.80982,39.85995,32.80974,39.85943,32.80956,39.859" +
        "16,32.80946,39.85888,32.80937,39.85831,32.80918,39.85809,32.80911,39.85789,32.80905,39.85767,32.80899,39.85745" +
        ",32.80894,39.85730,32.80891,39.85715,32.80889,39.85694,32.80886,39.85669,32.80884,39.85632,32.80882,39.85602,3" +
        "2.80881,39.85572,32.80881,39.85551,32.80882,39.85529,32.80884,39.85492,32.80890,39.85475,32.80893,39.85457,32." +
        "80897,39.85439,32.80901,39.85419,32.80906,39.85396,32.80913,39.85371,32.80921,39.85348,32.80930,39.85325,32.80" +
        "939,39.85307,32.80947,39.85287,32.80955,39.85263,32.80965,39.85238,32.80977,39.85150,32.81017,39.85113,32.8103" +
        "4,39.85092,32.81043,39.85073,32.81050,39.85060,32.81054,39.85050,32.81057,39.85028,32.81062,39.84999,32.81068," +
        "39.84972,32.81072,39.84949,32.81075,39.84925,32.81077,39.84902,32.81077,39.84879,32.81076,39.84860,32.81074,39" +
        ".84842,32.81073,39.84818,32.81070,39.84794,32.81065,39.84773,32.81060,39.84754,32.81054,39.84733,32.81047,39.8" +
        "4713,32.81039,39.84687,32.81027,39.84669,32.81018,39.84656,32.81010,39.84637,32.80997,39.84613,32.80979,39.845" +
        "89,32.80959,39.84574,32.80947,39.84558,32.80934,39.84534,32.80912,39.84515,32.80892,39.84497,32.80871,39.84470" +
        ",32.80838,39.84448,32.80809,39.84362,32.80692,39.84273,32.80575,39.84150,32.80414,39.84024,32.80249,39.83822,3" +
        "2.79979,39.83792,32.79939,39.83714,32.79835,39.83699,32.79816,39.83685,32.79801,39.83665,32.79780,39.83638,32." +
        "79756,39.83624,32.79745,39.83607,32.79735,39.83586,32.79722,39.83563,32.79710,39.83537,32.79699,39.83514,32.79" +
        "692,39.83490,32.79688,39.83460,32.79685,39.83432,32.79684,39.83410,32.79686,39.83389,32.79689,39.83365,32.7969" +
        "5,39.83345,32.79701,39.83283,32.79720,39.83263,32.79726,39.83242,32.79731,39.83218,32.79735,39.83198,32.79737," +
        "39.83171,32.79738,39.83146,32.79736,39.83122,32.79733,39.83101,32.79729,39.83082,32.79724,39.83064,32.79719,39" +
        ".83043,32.79711,39.83017,32.79699,39.82998,32.79689,39.82915,32.79644,39.82883,32.79627,39.82855,32.79613,39.8" +
        "2831,32.79602,39.82809,32.79595,39.82792,32.79590,39.82776,32.79586,39.82758,32.79583,39.82738,32.79581,39.827" +
        "17,32.79579,39.82695,32.79578,39.82665,32.79579,39.82635,32.79582,39.82312,32.79620,39.82112,32.79642,39.81979" +
        ",32.79657,39.81918,32.79666,39.81895,32.79671,39.81870,32.79676,39.81845,32.79682,39.81821,32.79689,39.81794,3" +
        "2.79698,39.81760,32.79712,39.81726,32.79728,39.81687,32.79750,39.81658,32.79765,39.81631,32.79784,39.81536,32." +
        "79855,39.81300,32.80035,39.81187,32.80122,39.81168,32.80136,39.81147,32.80152,39.81110,32.80175,39.81050,32.80" +
        "212,39.80967,32.80264,39.80933,32.80282,39.80903,32.80296,39.80856,32.80315,39.80840,32.80322,39.80788,32.8034" +
        "0,39.80738,32.80354,39.80660,32.80375,39.80633,32.80382,39.80567,32.80398,39.80516,32.80410,39.80452,32.80427," +
        "39.80391,32.80441,39.80345,32.80452,39.80303,32.80462,39.79912,32.80553,39.79806,32.80559,39.79793,32.80562,39" +
        ".79715,32.80580,39.79672,32.80589,39.79648,32.80594,39.79635,32.80597,39.79618,32.80601,39.79600,32.80606,39.7" +
        "9576,32.80611,39.79533,32.80621,39.79483,32.80631,39.79446,32.80636,39.79431,32.80637,39.79412,32.80638,39.793" +
        "73,32.80640,39.79343,32.80640,39.79300,32.80639,39.79275,32.80639,39.79246,32.80688,39.79202,32.80770,39.79193" +
        ",32.80788,39.79162,32.80850,39.79134,32.80909,39.79107,32.80967,39.79103,32.80975,39.79065,32.81056,39.79036,3" +
        "2.81038,39.78980,32.81003,39.78965,32.80995,39.78939,32.80979,39.78906,32.80960,39.78893,32.80953,39.78854,32." +
        "80931,39.78763,32.80880,39.78734,32.80863,39.78723,32.80857,39.78714,32.80850,39.78748,32.80745,39.78772,32.80" +
        "669,39.78796,32.80598"
}
