package com.example.wayfindr.dash.map

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class WeakGpsTest {
    @Test fun snapBandGrowsWithAccuracyWithinLimits() {
        assertEquals(35.0, WeakGps.snapBand(null), 0.0)
        assertEquals(35.0, WeakGps.snapBand(10.0), 0.0)
        assertEquals(90.0, WeakGps.snapBand(60.0), 0.0)
        assertEquals(120.0, WeakGps.snapBand(150.0), 0.0)
    }

    @Test fun poorFixesAreWeightedNotDropped() {
        assertEquals(1.0, WeakGps.weight(8.0), 0.0)
        assertEquals(0.5, WeakGps.weight(50.0), 1e-9)
        assertEquals(0.2, WeakGps.weight(150.0), 1e-9)
        // Dead reckoning says 1000 m, a 50 m fix says 1100 m → halfway.
        assertEquals(1050.0, WeakGps.blend(1000.0, 1100.0, 50.0), 1e-9)
        // A good fix is taken as is.
        assertEquals(1100.0, WeakGps.blend(1000.0, 1100.0, 5.0), 1e-9)
        assertEquals(1100.0, WeakGps.blend(null, 1100.0, 150.0), 1e-9)
    }

    @Test fun deadReckoningOnlyThroughGapsAndBounded() {
        assertEquals(0.0, WeakGps.extraMeters(12.0, 1.0), 0.0)          // normal 1 Hz stream
        assertEquals(240.0, WeakGps.extraMeters(12.0, 20.0), 1e-9)      // 20 s gap
        assertEquals(12.0 * WeakGps.MAX_S, WeakGps.extraMeters(12.0, 300.0), 1e-9)
        assertEquals(0.0, WeakGps.extraMeters(0.3, 20.0), 0.0)          // stopped
        assertTrue(WeakGps.canDeadReckon(12.0, 30.0))
        assertFalse(WeakGps.canDeadReckon(12.0, 60.0))
        assertFalse(WeakGps.canDeadReckon(0.0, 5.0))
    }

    @Test fun speedWithoutGpsSpeedIsHeldOrEasedFromProgress() {
        assertEquals(10.0, WeakGps.nextSpeed(12.0, 10.0, null), 0.0)
        assertEquals(12.0, WeakGps.nextSpeed(12.0, null, null), 0.0)
        assertEquals(12.0 + (2.0 - 12.0) * 0.3, WeakGps.nextSpeed(12.0, null, 2.0), 1e-9)
    }

    @Test fun coarseFixNeverTakesTheRiderOffTheLine() {
        assertTrue(WeakGps.onRoute(30.0, 5.0))
        assertFalse(WeakGps.onRoute(60.0, 5.0))
        assertTrue(WeakGps.onRoute(140.0, 150.0))   // beyond the 120 m band, but a 150 m fix can't tell
        assertFalse(WeakGps.onRoute(-1.0, 150.0))   // no match yet
    }
}
