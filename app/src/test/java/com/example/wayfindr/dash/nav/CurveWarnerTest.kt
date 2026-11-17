package com.example.wayfindr.dash.nav

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.cos
import kotlin.math.sin

class CurveWarnerTest {
    /** Route from metre offsets (x east, y north) around Ankara. */
    private fun route(vararg xy: Pair<Double, Double>): Route {
        val g = xy.map { GeoPoint(39.9 + it.second / 111_320.0, 32.8 + it.first / (111_320.0 * cos(Math.toRadians(39.9)))) }
        val cum = DoubleArray(g.size)
        for (i in 1 until g.size) cum[i] = cum[i - 1] + GeoPoint.distMeters(g[i - 1], g[i])
        return Route(g, emptyList(), cum.last(), 0.0, cum)
    }

    // 600 m north, then a right-angle turn east for 600 m.
    private val corner = route(0.0 to 0.0, 0.0 to 600.0, 600.0 to 600.0)

    @Test fun `right angle corner is a sharp right curve near the corner`() {
        val c = CurveWarner.find(corner.geometry, corner.cumulative, 300.0, 400.0).single()
        assertTrue(c.right); assertTrue(!c.hairpin)
        assertEquals(600.0, c.apexM, 25.0)
    }

    @Test fun `gentle bend is ignored`() {
        val r = route(0.0 to 0.0, 0.0 to 300.0, 100.0 to 600.0, 300.0 to 900.0)
        assertTrue(CurveWarner.find(r.geometry, r.cumulative, 0.0, 400.0).isEmpty())
    }

    @Test fun `tight U-turn is a hairpin and corner on a maneuver is skipped`() {
        val h = route(0.0 to 0.0, 0.0 to 500.0, -20.0 to 520.0, -40.0 to 500.0, -40.0 to 0.0)
        val c = CurveWarner.find(h.geometry, h.cumulative, 300.0, 400.0).single()
        assertTrue(c.hairpin); assertTrue(!c.right)
        assertTrue(CurveWarner.find(corner.geometry, corner.cumulative, 300.0, 400.0, listOf(600.0)).isEmpty())
    }

    @Test fun `warns once, ~150-250 m before, with Turkish text`() {
        val w = CurveWarner()
        assertNull(w.tick(corner, 300.0, 20.0))                         // 300 m away
        val msg = w.tick(corner, 420.0, 20.0)                           // ~175 m away
        assertEquals("Keskin sağ viraj, 150 metre", msg)
        assertNull(w.tick(corner, 440.0, 20.0))                         // once only
    }
}
