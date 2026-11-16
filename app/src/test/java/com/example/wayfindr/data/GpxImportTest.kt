package com.example.wayfindr.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class GpxImportTest {
    private val gpx = """<?xml version="1.0" encoding="UTF-8"?>
<gpx version="1.1" creator="Super TripperDash" xmlns="http://www.topografix.com/GPX/1/1">
  <metadata><name>Dosya adı</name><time>2026-11-15T09:00:00Z</time></metadata>
  <trk><name>XFİTness</name><trkseg>
    <trkpt lat="39.9300" lon="32.8500"><ele>950.0</ele><time>2026-11-15T09:07:00Z</time><name>p1</name></trkpt>
    <trkpt lat="39.9310" lon="32.8500"><ele>955.5</ele><time>2026-11-15T09:07:10Z</time></trkpt>
    <trkpt lat="39.9320" lon="32.8500"><ele>961.0</ele><time>2026-11-15T12:07:20+03:00</time></trkpt>
    <trkpt lat="39.9330" lon="32.8500"></trkpt>
  </trkseg></trk>
  <trk><trkseg><trkpt lat="1" lon="2"/></trkseg></trk>
</gpx>"""

    @Test fun parsesTracksNamesElevationAndTimes() {
        val t = GpxImport.parse(gpx.byteInputStream())
        assertEquals(2, t.size)
        assertEquals("XFİTness", t[0].name)
        assertEquals("Dosya adı", t[1].name)          // falls back to <metadata><name>
        assertEquals(4, t[0].points.size)
        assertEquals(955.5, t[0].points[1].ele!!, 1e-9)
        assertEquals(t[0].points[0].timeMs!! + 20_000, t[0].points[2].timeMs)   // +03:00 offset honoured
        assertNull(t[0].points[3].timeMs)
    }

    @Test fun speedsFromTimeAndDistance() {
        val pts = GpxImport.parse(gpx.byteInputStream())[0].points.take(3)
        val v = GpxImport.speeds(pts)
        assertEquals(0f, v[0], 0f)
        assertTrue("~111 m in 10 s ≈ 11 m/s, got ${v[2]}", v[2] in 10.5f..11.7f)
    }

    @Test fun routesUsedWhenNoTracks() {
        val r = GpxImport.parse("""<gpx><rte><name>R</name><rtept lat="1" lon="2"/></rte></gpx>""".byteInputStream())
        assertEquals("R", r.single().name)
    }
}

/** Real exports from Super TripperDash (test/resources/gpx); expected values are what that app showed. */
class GpxFixtureTest {
    private fun load(name: String) = GpxImport.parse(javaClass.getResourceAsStream("/gpx/$name")!!).single()
    private fun iso(ms: Long) = java.time.Instant.ofEpochMilli(ms).toString()

    @Test fun xfitness() {
        val t = load("xfitness.gpx")
        assertEquals("XFİTness", t.name)
        assertEquals(88, t.points.size)
        val (r, s) = GpxImport.toRide(t)!!
        assertEquals("2026-11-15T09:09:51Z", iso(r.startMs))
        assertEquals("2026-11-15T09:17:26Z", iso(r.endMs))
        assertTrue("distance ${r.distanceKm}", r.distanceKm in 3.15..3.5)          // app: 3,4 km
        assertTrue("max ${r.maxSpeedKmh}", r.maxSpeedKmh in 110.0..130.0)           // app: 125 km/sa
        assertEquals(960, s.altMinM)                                                 // app: 959 – 1090 m
        assertEquals(1091, s.altMaxM)
        assertTrue("climb ${s.climbM}", s.climbM!! in 58..70)                        // app: +63 m
        assertTrue("stopped ${s.stoppedSec}", s.stoppedSec!! in 30L..300L)
    }

    @Test fun saglikBakanligi() {
        val t = load("saglik.gpx")
        assertEquals("T.C. Sağlık Bakanlığı", t.name)
        assertEquals(718, t.points.size)
        val (r, s) = GpxImport.toRide(t)!!
        assertTrue("distance ${r.distanceKm}", r.distanceKm in 27.0..28.6)          // app: 28,1 km
        assertTrue("max ${r.maxSpeedKmh}", r.maxSpeedKmh in 110.0..130.0)           // app: 124 km/sa
        assertEquals(875, s.altMinM)                                                 // app: 875 – 1080 m
        assertEquals(1081, s.altMaxM)
        assertTrue("climb ${s.climbM}", s.climbM!! in 440..490)                      // app: +467 m
        assertTrue("stopped ${s.stoppedSec}", s.stoppedSec!! >= 15 * 60)            // app: 23 dk
    }
}
