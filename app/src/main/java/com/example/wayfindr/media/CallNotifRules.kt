package com.example.wayfindr.media

/**
 * Decisions for the dialer's CATEGORY_CALL notifications, so the caller stays on the console
 * until the call is answered (OFFHOOK → "active") or ends (notification gone = IDLE). Pure (no
 * Android), unit-tested on the JVM.
 *
 * Why the card used to vanish early: every CALL-category post that wasn't CallStyle INCOMING/ONGOING
 * (EXTRA_CALL_TYPE missing on dialers without CallStyle, or another app's call notification) and
 * every CALL-category removal (any key — e.g. a heads-up copy, or the dialer re-posting the card
 * while it looks the contact up) cleared the call while the phone was still ringing.
 */
object CallNotifRules {
    enum class Kind { INCOMING, ACTIVE, CLEAR, IGNORE }

    /** How long a removed call card may take to come back (dialer re-post) before we clear it. */
    const val REMOVE_GRACE_MS = 1_500L

    /**
     * [type] = Notification.EXTRA_CALL_TYPE (API 31+; 0 = absent), [hasAnswerAction] = an
     * "answer/accept" button, [sameKey] = the post is the notification of the call we show now.
     */
    fun kind(type: Int, sdk: Int, hasAnswerAction: Boolean, sameKey: Boolean, hasCall: Boolean): Kind {
        val t = if (sdk < 31) 1 else type   // before CallStyle there's no type: assume ringing
        return when (t) {
            1 -> Kind.INCOMING
            2, 3 -> Kind.ACTIVE
            // No CallStyle type: ringing if it offers "answer", else an update that changes nothing.
            0 -> if (hasAnswerAction) Kind.INCOMING else Kind.IGNORE
            // Screening (4) / unknown: drops our call only when it's that call's own notification.
            else -> if (hasCall && !sameKey) Kind.IGNORE else Kind.CLEAR
        }
    }

    /** A removed CALL notification ends the call only when it's the one we show (or we don't know). */
    fun removalEndsCall(removedKey: String?, callKey: String?): Boolean = callKey == null || removedKey == callKey

    /** Keep a known caller name when the same call is re-posted with only a generic/blank title. */
    fun callerName(newName: String, generic: Set<String>, previous: String?, sameKey: Boolean): String =
        if (sameKey && previous != null && previous !in generic && (newName.isBlank() || newName in generic)) previous else newName
}
