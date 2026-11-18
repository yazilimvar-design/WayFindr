package com.example.wayfindr.data

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File
import java.nio.file.Files

/** A killed process leaves a journal; recover() turns it into the same ride, named by destination. */
class RideJournalTest {
    private lateinit var dir: File

    @Before fun setUp() { dir = Files.createTempDirectory("journal").toFile(); RideStats.baseDir = dir }
    @After fun tearDown() { RideStats.baseDir = null; dir.deleteRecursively() }

    private fun ride(rec: RideRecorder, t0: Long, n: Int): Long {
        var t = t0; var lat = 39.92
        rec.start(t0)
        rec.journalName("Mamak Devlet Hastanesi")
        for (i in 1..n) { lat += 15 / 111_320.0; rec.add(lat, 32.85, 15f, 5f, t, 900.0 + i * 0.2); t += 1000 }
        return t
    }

    @Test fun killedRideIsRecoveredLikeANormalStop() {
        val t0 = 1_763_200_000_000L
        val j = File(dir, "ride_journal.txt")
        val live = RideRecorder().apply { journal = j }
        val end = ride(live, t0, 120)
        assertTrue(j.exists())
        // Process killed here: nothing calls stop(). The reference ride (no journal) for comparison:
        val ref = RideRecorder(); ride(ref, t0, 120)
        val expected = ref.stop(end - 1000)!!

        assertEquals(end - 1000, RideRecorder.journalLastFixMs(j))
        val (r, name, reason) = RideRecorder.recover(j)
        assertNotNull(r); r!!
        assertEquals("ok", reason)
        assertEquals("Mamak Devlet Hastanesi", name)
        assertEquals(expected.distanceMeters, r.distanceMeters, 0.01)
        assertEquals(expected.durationSec, r.durationSec)
        assertEquals(expected.trackPolyline, r.trackPolyline)
        assertFalse(j.exists())
    }

    @Test fun shortJournalIsDroppedWithReason() {
        val j = File(dir, "ride_journal.txt")
        val rec = RideRecorder().apply { journal = j }
        ride(rec, 1_763_200_000_000L, 5)   // ~75 m
        val (r, _, reason) = RideRecorder.recover(j)
        assertNull(r)
        assertTrue(reason, reason.startsWith("< 150 m"))
        assertFalse(j.exists())
    }

    @Test fun resumeContinuesTheSameRide() {
        val t0 = 1_763_200_000_000L
        val j = File(dir, "ride_journal.txt")
        ride(RideRecorder().apply { journal = j }, t0, 60)
        val back = RideRecorder().apply { journal = j }
        assertEquals("Mamak Devlet Hastanesi", back.resume(j))
        assertTrue(back.isRecording)
        var lat = 39.92 + 60 * 15 / 111_320.0; var t = t0 + 60_000
        repeat(60) { lat += 15 / 111_320.0; back.add(lat, 32.85, 15f, 5f, t, 900.0); t += 1000 }
        val r = back.stop(t)!!
        assertEquals(t0, r.startMs)
        assertEquals(1800.0, r.distanceMeters, 30.0)
        assertFalse(j.exists())
    }
}
