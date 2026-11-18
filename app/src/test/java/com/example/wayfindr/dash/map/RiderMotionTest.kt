package com.example.wayfindr.dash.map

import com.example.wayfindr.dash.nav.GeoPoint
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.abs
import kotlin.math.exp

/**
 * Simulated ride (no Android): 1 Hz fixes that arrive 0.35 s after measurement, 10 fps frames.
 * The displayed position must keep up with the bike at every speed and never step backwards.
 */
class RiderMotionTest {
    // Straight north-bound road from Kızılay, 1 point / 50 m.
    private val geometry = List(400) { GeoPoint(39.92 + it * 50.0 / 111_195.0, 32.85) }
    private val cumulative = DoubleArray(geometry.size) { it * 50.0 }

    private class Run(val meanLagDash: Double, val maxBackStepM: Double, val maxStepDevM: Double)

    /** [legacy] = the V59 path: elapsed counted from when the loop saw the fix, 0.8 s lead, camera ease τ 0.2 s. */
    private fun ride(kmh: Double, legacy: Boolean, accuracy: Float = 8f): Run {
        val v = kmh / 3.6
        val motion = RiderMotion()
        var cam = Double.NaN
        var fixS = 0.0; var fixMeasNs = 0L; var seenNs = 0L; var lastFixK = -1
        var prevShown = Double.NaN
        var lagSum = 0.0; var n = 0; var maxBack = 0.0; var maxDev = 0.0
        val noise = doubleArrayOf(1.5, -2.0, 0.5, 2.5, -1.0, -2.5, 1.0, 0.0, 2.0, -1.5)
        for (frame in 0 until 300) {
            val nowNs = frame * 100_000_000L
            val t = nowNs / 1e9
            val k = Math.floor(t - 0.35).toInt()   // newest fix delivered by now (measured at k s)
            val newFix = k >= 0 && k != lastFixK
            if (newFix) {
                lastFixK = k
                fixS = 1_000.0 + v * k + noise[k % noise.size]
                fixMeasNs = k * 1_000_000_000L
                seenNs = nowNs
            }
            if (k < 0) continue
            val s: Double = if (legacy) {
                val target = fixS + v * ((nowNs - seenNs) / 1e9 + 0.8).coerceIn(0.0, 2.25)
                cam = if (cam.isNaN()) target else cam + (target - cam) * (1 - exp(-0.1 / 0.2))
                cam
            } else {
                val ps = fixS + RiderMotion.distance(v.toFloat(), RiderMotion.sinceFixSeconds(fixMeasNs, seenNs, nowNs) + RiderMotion.LEAD_S) *
                    RiderMotion.confidence(accuracy)
                val p = RiderMotion.pointAt(geometry, cumulative, ps)!!
                val shown = motion.step(p.lat, p.lng, newFix, nowNs, RiderMotion.tauFor(accuracy))
                (shown.first - geometry[0].lat) * 111_195.0
            }
            if (t >= 3.0) {
                val truthAtDash = 1_000.0 + v * (t + 0.5)
                lagSum += s - truthAtDash; n++
                if (!prevShown.isNaN()) {
                    val step = s - prevShown
                    maxBack = maxOf(maxBack, -step)
                    maxDev = maxOf(maxDev, abs(step - v * 0.1))
                }
            }
            prevShown = s
        }
        return Run(lagSum / n, maxBack, maxDev)
    }

    @Test fun keepsUpWithTheBikeAtEverySpeed() {
        for (kmh in listOf(30.0, 60.0, 90.0, 120.0)) {
            val old = ride(kmh, legacy = true)
            val new = ride(kmh, legacy = false)
            println("RiderMotion %3.0f km/h: konsolda ortalama fark önce %+.1f m, sonra %+.1f m; kare adım sapması max önce %.2f m, sonra %.2f m"
                .format(kmh, old.meanLagDash, new.meanLagDash, old.maxStepDevM, new.maxStepDevM))
            assertEquals("mean along-track error at the dash, $kmh km/h", 0.0, new.meanLagDash, 1.5)
            assertTrue("never steps backwards at $kmh km/h (${new.maxBackStepM} m)", new.maxBackStepM <= 0.0)
            assertTrue("no worse than before at $kmh km/h", abs(new.meanLagDash) <= abs(old.meanLagDash) + 0.5)
        }
    }

    @Test fun progressMatchWindowFindsTheSegmentAtItsStart() {
        // Duplicate vertex right at the window start (lo = 940 m): the segment ending there still counts.
        val g = geometry.take(20) + geometry[19] + geometry.drop(20).take(20)
        val c = DoubleArray(g.size).also { for (i in 1 until g.size) it[i] = it[i - 1] + GeoPoint.distMeters(g[i - 1], g[i]) }
        val p = GeoPoint(geometry[19].lat - 10.0 / 111_195.0, 32.85)
        val m = com.example.wayfindr.dash.nav.RouteProgressMatcher.match(g, c, p, c[19] + 60.0)!!
        assertEquals(c[19] - 10.0, m.progressMeters, 0.5)
        assertEquals(0.0, m.lateralDistanceMeters, 0.5)
    }

    @Test fun ageIsCountedFromTheMeasurement() {
        // Fix measured 0.4 s before it arrived, frame 0.2 s after arrival → 0.6 s.
        assertEquals(0.6, RiderMotion.sinceFixSeconds(1_000_000_000L, 1_400_000_000L, 1_600_000_000L), 1e-9)
        // No fix clock: from the arrival only.
        assertEquals(0.2, RiderMotion.sinceFixSeconds(0L, 1_400_000_000L, 1_600_000_000L), 1e-9)
        // Absurd age is capped.
        assertEquals(RiderMotion.MAX_FIX_AGE_S, RiderMotion.sinceFixSeconds(1L, 9_000_000_000L, 9_000_000_000L), 1e-9)
    }

    @Test fun largeCorrectionSnapsInsteadOfGliding() {
        val m = RiderMotion()
        m.step(39.92, 32.85, false, 0L, 0.35)
        val far = 39.92 + 500.0 / 111_195.0
        val shown = m.step(far, 32.85, true, 100_000_000L, 0.35)
        assertEquals(far, shown.first, 1e-9)
        // ...and a small one fades out over the next frames instead of jumping.
        val near = far + 10.0 / 111_195.0
        val first = m.step(near, 32.85, true, 200_000_000L, 0.35)
        assertTrue((near - first.first) * 111_195.0 > 5.0)
        var last = first
        for (i in 3..40) last = m.step(near, 32.85, false, i * 100_000_000L, 0.35)
        assertEquals(near, last.first, 0.1 / 111_195.0)
    }
}
