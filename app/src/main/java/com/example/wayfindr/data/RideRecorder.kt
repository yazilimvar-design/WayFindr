package com.example.wayfindr.data

import com.example.wayfindr.dash.nav.GeoPoint
import com.example.wayfindr.dash.nav.NavPlus
import com.example.wayfindr.dash.nav.PolylineCodec
import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Accumulates GPS fixes for one ride (a connect→disconnect dash session) and produces a
 * [Ride] at the end. Points are thinned to ~every [MIN_MOVE_M] metres so the stored track
 * stays small; distance/speed are computed from the accepted points.
 *
 * Also stopped time, altitude and per-point time/altitude ([RideStats] side files), and the
 * per-point speeds NavPlus colours the track with (files/ride_speeds). Used live by DashViewModel and offline by [GpxImport].
 *
 * Not thread-safe — one instance per ride.
 */
class RideRecorder {

    private val points = ArrayList<GeoPoint>()
    private var startMs = 0L
    private var lastMs = 0L
    private var distanceM = 0.0
    private var maxSpeed = 0.0
    private var last: GeoPoint? = null
    private var recording = false
    private val speeds = ByteArrayOutputStream()   // km/h per point, same format as NavPlus.rsAdd
    private var lastFixMs = 0L
    private var stoppedMs = 0L
    private var altSmooth = Double.NaN   // EMA of GPS altitude; raw GPS altitude jitters by metres
    private var altRef = Double.NaN      // hysteresis reference for elevation gain
    private var climbM = 0.0
    private var altMin = Double.NaN
    private var altMax = Double.NaN
    private var altLat = 0.0; private var altLng = 0.0
    private val times = ArrayList<Long>()     // fix time of each track point
    private val alts = ArrayList<Float>()     // altitude at each track point (NaN = none yet)

    val isRecording: Boolean get() = recording

    /**
     * Crash-safe journal of the ride in progress (app storage): a header with the start time, then
     * every fix [add] gets ("lat,lng,speed,acc,time,alt"), and "N,<name>" when the ride's name
     * changes. Flushed per line, so a killed process (bike off, battery saver) loses at most the
     * last fix; [recover] turns a left-over journal into the ride. Deleted by [stop].
     */
    var journal: File? = null
    private var jw: java.io.Writer? = null
    private var replaying = false
    /** Why the last [stop] returned null ("< 150 m", "< 2 points"), for the ride log. */
    var dropReason: String? = null; private set

    private fun jWrite(line: String) {
        if (replaying) return
        val f = journal ?: return
        try {
            val w = jw ?: java.io.OutputStreamWriter(java.io.FileOutputStream(f, true), Charsets.UTF_8).also { jw = it }
            w.write(line); w.write("\n"); w.flush()
        } catch (_: Throwable) {}
    }

    private fun jClose(delete: Boolean) {
        try { jw?.close() } catch (_: Throwable) {}
        jw = null
        if (delete) try { journal?.delete() } catch (_: Throwable) {}
    }

    /** Name the ride (navigation destination) in the journal, so a recovered ride keeps it. */
    fun journalName(name: String?) {
        if (recording && !name.isNullOrBlank()) jWrite("N," + name.replace('\n', ' '))
    }
    /** Distance ridden so far this session (m). */
    val distanceSoFarM: Double get() = distanceM

    fun start(nowMs: Long = System.currentTimeMillis()) {
        points.clear(); distanceM = 0.0; maxSpeed = 0.0; last = null; speeds.reset(); times.clear(); alts.clear()
        lastFixMs = 0L; stoppedMs = 0L; climbM = 0.0
        altSmooth = Double.NaN; altRef = Double.NaN; altMin = Double.NaN; altMax = Double.NaN
        startMs = nowMs; lastMs = startMs
        recording = true
        if (!replaying && journal != null) { jClose(delete = true); jWrite("H,1,$nowMs"); live = this }
    }

    /**
     * Continue the journal's ride (the app was killed and came back within [JOURNAL_STALE_MS]):
     * its fixes are replayed, new ones append to the same file. Returns the journal's ride name.
     */
    fun resume(file: File): String? {
        val r = replay(file) ?: return null
        journal = file
        live = this
        return r
    }

    /** Replays [file] into this recorder (recording on afterwards); the ride name, or null if not a journal. */
    private fun replay(file: File): String? {
        val lines = try { file.readLines(Charsets.UTF_8) } catch (_: Throwable) { return null }
        val head = lines.firstOrNull()?.split(',') ?: return null
        if (head.size < 3 || head[0] != "H") return null
        var name: String? = null
        replaying = true
        try {
            start(head[2].toLongOrNull() ?: return null)
            for (l in lines.drop(1)) {
                if (l.startsWith("N,")) { name = l.substring(2).ifBlank { null }; continue }
                val f = l.split(',')
                if (f.size < 6) continue   // a half-written last line
                val lat = f[0].toDoubleOrNull() ?: continue; val lng = f[1].toDoubleOrNull() ?: continue
                add(lat, lng, f[2].toFloatOrNull() ?: 0f, f[3].toFloatOrNull() ?: 99f, f[4].toLongOrNull() ?: continue,
                    f[5].toDoubleOrNull() ?: Double.NaN)
            }
        } finally { replaying = false }
        return name ?: ""
    }

    /** Time of the last fix in the journal (or its start), for the "ride is over" age check. */
    val lastFixTimeMs: Long get() = if (lastFixMs > 0) lastFixMs else startMs

    /** [altM]: GPS (WGS84 ellipsoid) altitude, NaN when the fix has none. */
    fun add(lat: Double, lng: Double, speedMps: Float, accuracyM: Float, timeMs: Long, altM: Double = Double.NaN) {
        if (!recording) return
        jWrite("$lat,$lng,$speedMps,$accuracyM,$timeMs,${if (altM.isNaN()) "" else altM.toString()}")
        // Stopped time counts every fix (parked fixes are often imprecise); a long gap is capped
        // so a GPS outage isn't all banked as stopped.
        if (lastFixMs > 0 && speedMps < STILL_SPEED) stoppedMs += (timeMs - lastFixMs).coerceIn(0, if (fromFile) Long.MAX_VALUE else MAX_GAP_MS)
        lastFixMs = timeMs
        // Drop noisy fixes entirely: a fix accurate only to 30 m wanders ~30 m between samples,
        // and that wander gets counted as travel — the main reason the recorded distance ran
        // ~0.2 km over the dash's odometer. Only trust reasonably precise fixes.
        if (accuracyM > ACC_GATE_M) return
        if (!altM.isNaN()) addAlt(lat, lng, altM)
        val p = GeoPoint(lat, lng)
        val prev = last
        if (prev == null) {
            addPoint(p, speedMps, timeMs); return
        }
        val step = GeoPoint.distMeters(prev, p)
        if (step < MIN_MOVE_M) return   // thin out jitter / stationary noise
        // Suppress parked drift: a step that shows up while essentially not moving is GPS wander,
        // not distance ridden. Still advance the point so we don't bank a jump when you set off.
        if (speedMps >= STILL_SPEED) distanceM += step
        // Only count max speed on genuine movement, so parked GPS speed spikes don't inflate it.
        if (speedMps > maxSpeed) maxSpeed = speedMps.toDouble()
        addPoint(p, speedMps, timeMs)
    }

    private fun addPoint(p: GeoPoint, speedMps: Float, timeMs: Long) {
        points.add(p); speeds.write(kmh(speedMps)); times.add(timeMs); alts.add(altSmooth.toFloat())
        last = p; lastMs = timeMs
    }

    private fun kmh(mps: Float) = (mps * 3.6f).roundToInt().coerceIn(0, 250)

    private fun addAlt(lat: Double, lng: Double, alt: Double) {
        altSmooth = if (altSmooth.isNaN() || fromFile) alt else altSmooth + (alt - altSmooth) * 0.3
        if (altRef.isNaN()) { altRef = altSmooth; altLat = lat; altLng = lng }
        val step = if (fromFile) 0.0 else CLIMB_STEP_M
        if (altSmooth > altRef + step) { climbM += altSmooth - altRef; altRef = altSmooth }
        else if (altSmooth < altRef - step) altRef = altSmooth
        altMin = if (altMin.isNaN()) altSmooth else min(altMin, altSmooth)
        altMax = if (altMax.isNaN()) altSmooth else max(altMax, altSmooth)
    }

    /** Write the per-point speeds where NavPlus.rsLoad looks for them (what NavPlus.rsSave did). */
    private fun saveSpeeds() {
        if (points.size < 2) return
        try {
            val dir = File(RideStats.baseDir ?: return, "ride_speeds")   // = NavPlus.rsDir()
            dir.mkdirs()
            val key = NavPlus.rsKey(points[0])
            File(dir, key).writeBytes(speeds.toByteArray())
            NavPlus.rsCache?.remove(key)
        } catch (_: Throwable) {}
    }

    /**
     * Finish the ride. Returns null for a trivial session so we don't litter the history with
     * parking-lot blips. A ride must have COVERED GROUND ([MIN_RIDE_M]); the old rule also kept any
     * session that merely LASTED a while, which saved 0 km "rides" whenever you connected and sat
     * (traffic/parked) for >90 s before disconnecting — distance is the only thing that makes it a ride.
     */
    fun stop(end: Long = System.currentTimeMillis()): Ride? {
        saveSpeeds()
        recording = false
        jClose(delete = true)
        if (live === this) live = null
        val durationS = ((end - startMs) / 1000L).coerceAtLeast(0)
        dropReason = when {
            points.size < 2 -> "< 2 points"
            distanceM < MIN_RIDE_M -> "< ${MIN_RIDE_M.toInt()} m (${distanceM.roundToInt()} m)"
            else -> null
        }
        if (dropReason != null) return null
        val avg = if (durationS > 0) distanceM / durationS else 0.0
        val first = points.first(); val lastPt = points.last()
        val sid = WayfindrDb.newSid()
        val hasAlt = !altMin.isNaN()
        val stats = RideStats(
            stoppedSec = (stoppedMs / 1000).coerceAtMost(durationS),
            climbM = if (hasAlt) climbM.roundToInt() else null,
            altMinM = if (hasAlt) altMin.roundToInt() else null,
            altMaxM = if (hasAlt) altMax.roundToInt() else null,
        )
        this.stats = stats
        val t = times.toLongArray(); val a = alts.toFloatArray()
        val lo = altMin; val hi = altMax
        val persist = { off: Double ->
            RideStats.save(sid, if (off == 0.0) stats else stats.copy(
                altMinM = (lo + off).roundToInt(), altMaxM = (hi + off).roundToInt()))
            RideStats.savePoints(sid, startMs, t, a, off.toFloat())
        }
        if (hasAlt && !fromFile && com.example.wayfindr.dash.nav.GPlus.ctx != null && android.os.Build.VERSION.SDK_INT >= 34) {
            // GPS altitude is above the WGS84 ellipsoid; Rakım is above sea level. The geoid offset
            // barely changes over a ride, so one lookup (Android 14+, disk read, off the main thread) fixes all.
            val lat0 = altLat; val lng0 = altLng
            Thread { persist(mslOffset(lat0, lng0, lo) ?: 0.0) }.start()
        } else persist(0.0)
        return Ride(
            sid = sid,
            startMs = startMs, endMs = end,
            distanceMeters = distanceM, durationSec = durationS,
            avgSpeedMps = avg, maxSpeedMps = maxSpeed,
            trackPolyline = PolylineCodec.encode(points),
            startLat = first.lat, startLng = first.lng, endLat = lastPt.lat, endLng = lastPt.lng,
        )
    }

    /**
     * Replaying another app's export (GPX): its <ele> is already above sea level and already
     * filtered, and its points are sparse (a long gap is a real stop) — so no geoid correction,
     * no altitude smoothing and no gap cap. Live 1 Hz GPS needs all three.
     */
    var fromFile = false

    /** What [stop] saved to the [RideStats] side file (before the async geoid fix). */
    var stats = RideStats(); private set

    private fun mslOffset(lat: Double, lng: Double, alt: Double): Double? {
        return try {
            val ctx = com.example.wayfindr.dash.nav.GPlus.ctx ?: return null
            val loc = android.location.Location("gps").apply { latitude = lat; longitude = lng; altitude = alt }
            android.location.altitude.AltitudeConverter().addMslAltitudeToLocation(ctx, loc)
            loc.mslAltitudeMeters - alt
        } catch (_: Throwable) { null }
    }

    companion object {
        /** A journal whose last fix is older than this is a finished ride, not one to continue. */
        const val JOURNAL_STALE_MS = 30 * 60_000L

        /** The recorder writing a journal in this process (a second view model must not touch it). */
        @Volatile private var live: RideRecorder? = null
        fun isLive(file: File): Boolean = live?.let { it.recording && it.journal == file } == true

        /**
         * Finish a journal left by a killed process: the ride (null if trivial — see the returned
         * reason), its name, and the journal is deleted either way.
         */
        fun recover(file: File): Triple<Ride?, String?, String> {
            val r = RideRecorder()
            val name = r.replay(file)
            if (name == null) { file.delete(); return Triple(null, null, "unreadable journal") }
            r.journal = file
            val ride = r.stop(r.lastFixTimeMs)
            return Triple(ride, name.ifBlank { null }, r.dropReason ?: "ok")
        }

        /** Age of a journal = now − its last fix (reads only the last line). */
        fun journalLastFixMs(file: File): Long? = try {
            val lines = file.readLines(Charsets.UTF_8)
            lines.asReversed().firstNotNullOfOrNull { l ->
                val f = l.split(','); if (f.size >= 6) f[4].toLongOrNull() else if (f.size >= 3 && f[0] == "H") f[2].toLongOrNull() else null
            }
        } catch (_: Throwable) { null }

        private const val MIN_MOVE_M = 8.0     // thin track points to ~every 8 m
        private const val MIN_RIDE_M = 150.0   // discard rides that covered less than this (parking-lot blips)
        private const val ACC_GATE_M = 20f     // ignore fixes worse than this (m); noisy fixes inflate distance
        private const val STILL_SPEED = 0.7f   // m/s (~2.5 km/h); below this, a step is parked GPS drift
        private const val MAX_GAP_MS = 120_000L
        private const val CLIMB_STEP_M = 4.0   // elevation gain hysteresis: ignore wobble smaller than this
    }
}
