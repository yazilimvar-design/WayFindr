package com.example.wayfindr.dash.nav

import com.google.android.libraries.mapsplatform.turnbyturn.model.Maneuver as M
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GoogleNavManeuverTest {
    private fun glyph(m: Int) = GoogleNav.googleName(m)?.let { DashGlyph.fromGoogle(it) }

    @Test fun mapsNavSdkManeuversToDashGlyphs() {
        assertNull(glyph(M.DESTINATION))
        assertNull(glyph(M.DESTINATION_RIGHT))
        assertEquals(DashGlyph.TURN_LEFT, glyph(M.TURN_LEFT))
        assertEquals(DashGlyph.TURN_SLIGHT_RIGHT, glyph(M.TURN_KEEP_RIGHT))
        assertEquals(DashGlyph.U_TURN_COUNTERCLOCKWISE, glyph(M.TURN_U_TURN_COUNTERCLOCKWISE))
        assertEquals(DashGlyph.OFF_RAMP_LEFT, glyph(M.ON_RAMP_SLIGHT_LEFT))
        assertEquals(DashGlyph.OFF_RAMP_RIGHT, glyph(M.ON_RAMP_RIGHT))
        assertEquals(DashGlyph.OFF_RAMP_LEFT, glyph(M.OFF_RAMP_SHARP_LEFT))
        assertEquals(DashGlyph.OFF_RAMP_RIGHT, glyph(M.OFF_RAMP_KEEP_RIGHT))
        assertEquals(DashGlyph.STRAIGHT, glyph(M.ON_RAMP_UNSPECIFIED))
        assertEquals(DashGlyph.STRAIGHT, glyph(M.NAME_CHANGE))
        assertEquals(DashGlyph.FERRY_TRAIN, glyph(M.FERRY_TRAIN))
        assertEquals("ROUNDABOUT_COUNTERCLOCKWISE", GoogleNav.googleName(M.ROUNDABOUT_LEFT_COUNTERCLOCKWISE))
        assertEquals("ROUNDABOUT_CLOCKWISE", GoogleNav.googleName(M.ROUNDABOUT_EXIT_CLOCKWISE))
    }
}
