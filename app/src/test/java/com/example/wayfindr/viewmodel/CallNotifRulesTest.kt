package com.example.wayfindr.viewmodel

import com.example.wayfindr.media.CallNotifRules
import com.example.wayfindr.media.CallNotifRules.Kind
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class CallNotifRulesTest {
    @Test fun ringingThenAnsweredThenOtherPosts() {
        assertEquals(Kind.INCOMING, CallNotifRules.kind(1, 34, true, false, false))
        assertEquals(Kind.ACTIVE, CallNotifRules.kind(2, 34, false, true, true))
        // A non-CallStyle update of the card (no type, no answer button) must not clear it.
        assertEquals(Kind.IGNORE, CallNotifRules.kind(0, 34, false, true, true))
        assertEquals(Kind.INCOMING, CallNotifRules.kind(0, 34, true, true, true))
        // Another app's screening/unknown CALL post leaves our ringing call alone.
        assertEquals(Kind.IGNORE, CallNotifRules.kind(4, 34, false, false, true))
        assertEquals(Kind.CLEAR, CallNotifRules.kind(4, 34, false, true, true))
        assertEquals(Kind.INCOMING, CallNotifRules.kind(0, 29, false, false, false))
    }

    @Test fun onlyTheCallsOwnNotificationEndsIt() {
        assertFalse(CallNotifRules.removalEndsCall("other", "call"))
        assertTrue(CallNotifRules.removalEndsCall("call", "call"))
        assertTrue(CallNotifRules.removalEndsCall("x", null))
    }

    @Test fun genericRepostKeepsTheName() {
        val g = setOf("Call", "Gelen arama")
        assertEquals("Ahmet", CallNotifRules.callerName("Gelen arama", g, "Ahmet", true))
        assertEquals("Ahmet", CallNotifRules.callerName("", g, "Ahmet", true))
        assertEquals("Mehmet", CallNotifRules.callerName("Mehmet", g, "Ahmet", true))
        assertEquals("Gelen arama", CallNotifRules.callerName("Gelen arama", g, "Ahmet", false))
    }
}
