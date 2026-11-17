package com.example.wayfindr.dash.nav

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TrafficSwitchTest {
    private val long = 10_000_000L

    @Test fun smallGainUnderThreeMinutesIsIgnored() =
        assertFalse(TrafficSwitch.worthSwitching(1200.0, 1100.0, long, 0))

    @Test fun tenPercentRuleBeatsThreeMinutesOnLongTrips() {
        assertFalse(TrafficSwitch.worthSwitching(7200.0, 6800.0, long, 0))   // 6.7 min < 12 min
        assertTrue(TrafficSwitch.worthSwitching(7200.0, 6400.0, long, 0))    // 13.3 min
    }

    @Test fun threeMinutesOnShortTripSwitches() =
        assertTrue(TrafficSwitch.worthSwitching(1200.0, 1000.0, long, 0))

    @Test fun noFlipFlopWithinFiveMinutes() {
        assertFalse(TrafficSwitch.worthSwitching(1200.0, 600.0, long, long - 299_000))
        assertTrue(TrafficSwitch.worthSwitching(1200.0, 600.0, long, long - 300_000))
    }

    @Test fun remainingAndMessage() {
        assertEquals(300.0, TrafficSwitch.remainingSeconds(600.0, 10_000.0, 5_000.0), 1e-9)
        assertEquals("Trafik nedeniyle daha hızlı rota: 5 dakika kazanç", TrafficSwitch.message(300.0))
    }
}
