package com.example.wayfindr.data

import com.example.wayfindr.dash.nav.GPlus
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.File
import java.util.Properties

/**
 * Ride extras the `ride` table has no columns for, kept in side files next to NavPlus' ride_speeds:
 * files/ride_stats/<sid>.properties (stopped time, elevation gain, min/max altitude) and
 * files/ride_stats/<sid>.pts (time + altitude of every track point). Rides recorded before this
 * existed, or synced from another phone, have no files: every field is null and the UI hides it.
 */
data class RideStats(
    val stoppedSec: Long? = null,
    val climbM: Int? = null,      // total elevation gain
    val altMinM: Int? = null,     // metres above sea level
    val altMaxM: Int? = null,
) {
    companion object {
        /** App files dir; a unit test points it at a temp folder. */
        var baseDir: File? = null
            get() = field ?: GPlus.ctx?.filesDir

        private fun file(sid: String, ext: String = "properties"): File? =
            baseDir?.let { File(File(it, "ride_stats"), "$sid.$ext") }

        fun load(sid: String): RideStats {
            val f = file(sid)?.takeIf { sid.isNotEmpty() && it.exists() } ?: return RideStats()
            val p = Properties()
            return try {
                f.inputStream().use { p.load(it) }
                RideStats(
                    p.getProperty("stopped")?.toLongOrNull(),
                    p.getProperty("climb")?.toIntOrNull(),
                    p.getProperty("altMin")?.toIntOrNull(),
                    p.getProperty("altMax")?.toIntOrNull(),
                )
            } catch (_: Exception) { RideStats() }
        }

        fun save(sid: String, s: RideStats) {
            val f = file(sid) ?: return
            try {
                f.parentFile?.mkdirs()
                val p = Properties()
                s.stoppedSec?.let { p.setProperty("stopped", it.toString()) }
                s.climbM?.let { p.setProperty("climb", it.toString()) }
                s.altMinM?.let { p.setProperty("altMin", it.toString()) }
                s.altMaxM?.let { p.setProperty("altMax", it.toString()) }
                f.outputStream().use { p.store(it, null) }
            } catch (_: Exception) {}
        }

        /** Per track point (same order as the ride's polyline): ms since [startMs], altitude in m (NaN = none). */
        fun savePoints(sid: String, startMs: Long, timesMs: LongArray, altM: FloatArray, altOffset: Float) {
            val f = file(sid, "pts") ?: return
            try {
                f.parentFile?.mkdirs()
                DataOutputStream(f.outputStream().buffered()).use { o ->
                    o.writeInt(timesMs.size)
                    for (i in timesMs.indices) { o.writeInt((timesMs[i] - startMs).toInt()); o.writeFloat(altM[i] + altOffset) }
                }
            } catch (_: Exception) {}
        }

        /** (ms since start, altitude) per point, or null when the ride has no point file. */
        fun loadPoints(sid: String): Pair<IntArray, FloatArray>? = try {
            file(sid, "pts")?.takeIf { it.exists() }?.let { f ->
                DataInputStream(f.inputStream().buffered()).use { i ->
                    val n = i.readInt(); val t = IntArray(n); val a = FloatArray(n)
                    for (k in 0 until n) { t[k] = i.readInt(); a[k] = i.readFloat() }
                    t to a
                }
            }
        } catch (_: Exception) { null }

        fun delete(sid: String) { if (sid.isNotEmpty()) { file(sid)?.delete(); file(sid, "pts")?.delete() } }
    }
}
