package com.example.wayfindr.dash

import com.example.wayfindr.dash.protocol.DashCommands
import org.junit.Assert.assertEquals
import org.junit.Test

/** The dash's own round ball stays hidden: every route card / nav packet carries glyph 0xFF. */
class NoDashBallTest {
    @Test fun `idle and paused route cards carry no glyph`() {
        assertEquals(0xFF, DashCommands.glyphOf(DashCommands.routeCard("Himalayan450", projectionOn = false)))
        assertEquals(0xFF, DashCommands.glyphOf(DashCommands.routeCard("Himalayan450", projectionOn = true)))
        assertEquals(0xFF, DashCommands.glyphOf(DashCommands.routeCard("Mamak Devlet Hastanesi", projectionOn = true)))
    }

    @Test fun `navigating with the turn card sends no glyph`() {
        val card = DashCommands.routeCard("Ev", true, maneuver = DashCommands.NAV_MANEUVER_NONE, totalDist = 120,
            totalUnit = DashCommands.NAV_UNIT_KM_TENTHS, etaHHMM = "1845")
        assertEquals(0xFF, DashCommands.glyphOf(card))
        assertEquals(0xFF, DashCommands.glyphOf(DashCommands.activeNavPacket(maneuver = DashCommands.NAV_MANEUVER_NONE)))
    }
}
