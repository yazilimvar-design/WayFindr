package com.example.wayfindr.data

import com.example.wayfindr.dash.nav.GeoPoint
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.io.InputStream
import java.time.OffsetDateTime
import javax.xml.parsers.SAXParserFactory

/**
 * Rides exported as GPX 1.1 by another app (e.g. Super TripperDash): each <trk> (or, without
 * tracks, each <rte>) becomes one [Ride], replayed through [RideRecorder] so distance, speeds,
 * stopped time and altitude are computed exactly like a ride recorded here.
 */
object GpxImport {
    class Pt(val lat: Double, val lng: Double, val ele: Double?, val timeMs: Long?)
    class Track(val name: String?, val points: List<Pt>)

    /** Parse one GPX file. Points without a time are kept here and dropped by [toRide]. */
    fun parse(input: InputStream): List<Track> {
        val tracks = ArrayList<Track>(); val routes = ArrayList<Track>()
        var metaName: String? = null
        val handler = object : DefaultHandler() {
            val text = StringBuilder()
            var pts: ArrayList<Pt>? = null; var name: String? = null; var inPt = false
            var lat = 0.0; var lng = 0.0; var ele: Double? = null; var time: Long? = null
            var depth = 0; var listDepth = 0   // depth of the open <trk>/<rte>, so a <name> inside a point is ignored
            override fun startElement(uri: String?, local: String?, qName: String, a: Attributes) {
                depth++
                text.setLength(0)
                when (tag(qName)) {
                    "trk", "rte" -> { pts = ArrayList(); name = null; listDepth = depth }
                    "trkpt", "rtept" -> if (pts != null) {
                        inPt = true; ele = null; time = null
                        lat = a.getValue("lat")?.toDoubleOrNull() ?: Double.NaN
                        lng = a.getValue("lon")?.toDoubleOrNull() ?: Double.NaN
                    }
                }
            }
            override fun characters(ch: CharArray, start: Int, length: Int) { text.append(ch, start, length) }
            override fun endElement(uri: String?, local: String?, qName: String) {
                val t = text.toString().trim()
                when (tag(qName)) {
                    "ele" -> if (inPt) ele = t.toDoubleOrNull()
                    "time" -> if (inPt) time = parseTime(t)
                    "name" -> when {
                        pts != null && depth == listDepth + 1 -> name = t.ifEmpty { null }
                        pts == null && metaName == null -> metaName = t.ifEmpty { null }
                    }
                    "trkpt", "rtept" -> if (inPt) {
                        inPt = false
                        if (!lat.isNaN() && !lng.isNaN()) pts?.add(Pt(lat, lng, ele, time))
                    }
                    "trk" -> { pts?.let { tracks.add(Track(name, it)) }; pts = null }
                    "rte" -> { pts?.let { routes.add(Track(name, it)) }; pts = null }
                }
                text.setLength(0)
                depth--
            }
        }
        val factory = SAXParserFactory.newInstance()
        // Untrusted file: never resolve external entities (XXE). Not every parser knows the feature.
        runCatching { factory.setFeature("http://xml.org/sax/features/external-general-entities", false) }
        runCatching { factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false) }
        factory.newSAXParser().parse(input, handler)
        return (tracks.ifEmpty { routes }).map { if (it.name == null && metaName != null) Track(metaName, it.points) else it }
    }

    private fun tag(qName: String) = qName.substringAfter(':')

    private fun parseTime(s: String): Long? = try { OffsetDateTime.parse(s).toInstant().toEpochMilli() } catch (_: Exception) { null }

    /** Speed at each point over a trailing window of at least [WINDOW_MS] (1 Hz GPX speeds are jittery). m/s. */
    fun speeds(pts: List<Pt>): FloatArray {
        val cum = DoubleArray(pts.size)
        for (i in 1 until pts.size) cum[i] = cum[i - 1] + GeoPoint.distMeters(GeoPoint(pts[i - 1].lat, pts[i - 1].lng), GeoPoint(pts[i].lat, pts[i].lng))
        var j = 0
        return FloatArray(pts.size) { i ->
            while (j + 1 < i && pts[i].timeMs!! - pts[j + 1].timeMs!! >= WINDOW_MS) j++
            val dt = (pts[i].timeMs!! - pts[j].timeMs!!) / 1000.0
            if (dt > 0) ((cum[i] - cum[j]) / dt).toFloat() else 0f
        }
    }

    /** Null when the track has no timestamps or is too short to be a ride. */
    fun toRide(t: Track): Pair<Ride, RideStats>? {
        val pts = t.points.filter { it.timeMs != null }.sortedBy { it.timeMs }
        if (pts.size < 2) return null
        val v = speeds(pts)
        val rec = RideRecorder().apply { fromFile = true }
        rec.start(pts.first().timeMs!!)
        pts.forEachIndexed { i, p -> rec.add(p.lat, p.lng, v[i], 0f, p.timeMs!!, p.ele ?: Double.NaN) }
        return rec.stop(pts.last().timeMs!!)?.let { it to rec.stats }
    }

    private const val WINDOW_MS = 4_000L
}
