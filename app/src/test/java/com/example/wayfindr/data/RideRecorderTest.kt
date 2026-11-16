package com.example.wayfindr.data

import com.example.wayfindr.dash.nav.PolylineCodec
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File
import java.nio.file.Files

/** Live recording path: synthetic 1 Hz fixes → saved ride + side files with speed, time and altitude. */
class RideRecorderTest {
    private lateinit var dir: File

    @Before fun setUp() { dir = Files.createTempDirectory("rides").toFile(); RideStats.baseDir = dir }
    @After fun tearDown() { RideStats.baseDir = null; dir.deleteRecursively() }

    @Test fun recordsSpeedTimeAltitudeAndDerivedStats() {
        val t0 = 1_763_200_000_000L
        val lat0 = 39.92; val lng = 32.85
        val rec = RideRecorder()
        rec.start(t0)
        var t = t0
        // 60 s parked, then 120 s north at 15 m/s (54 km/h) climbing 900 → 960 m, then 30 s stopped.
        repeat(60) { rec.add(lat0, lng, 0f, 5f, t, 900.0); t += 1000 }
        var lat = lat0
        for (i in 1..120) { lat += 15 / 111_320.0; rec.add(lat, lng, 15f, 5f, t, 900.0 + i * 0.5); t += 1000 }
        repeat(30) { rec.add(lat, lng, 0f, 5f, t, 960.0); t += 1000 }
        // A wildly inaccurate fix is ignored for the track but still counts toward stopped time.
        rec.add(lat + 0.01, lng, 0f, 80f, t, 2000.0)

        val ride = rec.stop(t)!!
        assertEquals(t0, ride.startMs)
        assertEquals(210L, ride.durationSec)
        assertEquals(1800.0, ride.distanceMeters, 30.0)
        assertEquals(54.0, ride.maxSpeedKmh, 0.5)
        val n = PolylineCodec.decode(ride.trackPolyline).size
        assertEquals(121, n)

        val s = RideStats.load(ride.sid)
        assertEquals(s, rec.stats)
        assertTrue("stopped ${s.stoppedSec}", s.stoppedSec!! in 88L..92L)
        assertTrue("climb ${s.climbM}", s.climbM!! in 55..60)
        assertEquals(900, s.altMinM)
        assertTrue("max ${s.altMaxM}", s.altMaxM!! in 958..960)

        val (times, alts) = RideStats.loadPoints(ride.sid)!!
        assertEquals(n, times.size)
        assertEquals(0, times[0])
        assertTrue((1 until n).all { times[it] > times[it - 1] })
        assertTrue(alts.none { it.isNaN() })
        assertTrue(alts.last() > 955f)

        // Per-point speeds, where NavPlus.rsLoad reads them (sp_<lat e5>_<lng e5>.bin of the first point).
        val first = PolylineCodec.decode(ride.trackPolyline)[0]
        val sp = File(dir, "ride_speeds/sp_${Math.round(first.lat * 1e5)}_${Math.round(first.lng * 1e5)}.bin").readBytes()
        assertEquals(n, sp.size)
        assertEquals(54, sp.last().toInt() and 0xFF)

        RideStats.delete(ride.sid)
        assertEquals(RideStats(), RideStats.load(ride.sid))
    }

    @Test fun oldRideWithoutSideFilesHasNoStats() {
        assertEquals(RideStats(), RideStats.load("no-such-ride"))
        assertEquals(null, RideStats.loadPoints("no-such-ride"))
    }
}
