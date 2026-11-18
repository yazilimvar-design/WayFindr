package com.example.wayfindr.media

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log

/**
 * Two jobs:
 *  1. Its mere existence + being enabled grants [MediaInfoProvider] access to MediaSessionManager
 *     (Android gates getActiveSessions() behind an enabled notification listener component).
 *  2. It watches CATEGORY_CALL notifications (the dialer's incoming/active-call card) and pushes the
 *     caller name to [CallInfoProvider] — that's how the dash shows "Amma calling" without any
 *     telephony permission, exactly mirroring what the dash put on the wire (TLV 05 22).
 */
class WayfindrNotificationListener : NotificationListenerService() {

    // These two confirm in the PERSISTENT diagnostics whether the listener is actually bound — the
    // crux of the "reinstall unbinds the listener" bug. If 'listener connected' never appears in a
    // remote pull, the service is dead (toggle notification access off/on, or the requestRebind in
    // WayfindrApplication should have re-bound it).
    override fun onListenerConnected() {
        bound = this
        com.example.wayfindr.data.RideDiagnostics.log("media", "notification listener CONNECTED")
    }

    override fun onListenerDisconnected() {
        if (bound === this) bound = null
        com.example.wayfindr.data.RideDiagnostics.log("media", "notification listener DISCONNECTED")
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        val n = sbn?.notification ?: return
        if (n.category != Notification.CATEGORY_CALL) return
        // CallStyle's EXTRA_CALL_TYPE (API 31+): 1=INCOMING, 2=ONGOING, 3=OUTGOING, 4=SCREENING.
        // INCOMING → ringing (overlay + answer/reject). ONGOING/OUTGOING → active (end only, no
        // overlay). Pre-31 we can't tell, so assume incoming.
        // Himican: the caller stays until answered (→ active) or the call's own notification goes
        // (CallNotifRules): other CALL posts/removals and re-posts no longer clear it while ringing.
        val type = if (android.os.Build.VERSION.SDK_INT >= 31) n.extras.getInt(Notification.EXTRA_CALL_TYPE, 0) else 1
        val current = CallInfoProvider.incomingCall.value
        val sameKey = sbn.key == callKey
        val kind = CallNotifRules.kind(type, android.os.Build.VERSION.SDK_INT,
            extractActions(n, false).first != null, sameKey, current != null)
        com.example.wayfindr.data.RideDiagnostics.log("media", "call notification type=$type sameKey=$sameKey → $kind")
        when (kind) {
            CallNotifRules.Kind.IGNORE -> return
            CallNotifRules.Kind.CLEAR -> { cancelPendingClear(); callKey = null; CallInfoProvider.update(null); return }
            else -> {}
        }
        cancelPendingClear()
        callKey = sbn.key
        val incoming = kind == CallNotifRules.Kind.INCOMING

        val (answer, decline) = extractActions(n, incoming)
        val caller = CallNotifRules.callerName(callerName(n), GENERIC, current?.caller, sameKey)
            .ifBlank { if (incoming) "Call" else "On call" }
        com.example.wayfindr.data.RideDiagnostics.log("media",
            "${if (incoming) "INCOMING" else "ACTIVE"} call: '$caller' (answerAct=${answer != null} declineAct=${decline != null})")
        CallInfoProvider.update(IncomingCall(caller, incoming, answer, decline))
        Log.d(TAG, "call($incoming): $caller")
    }

    /**
     * Pull the dialer's Answer / Decline (or Hang-up) PendingIntents off the notification's action
     * buttons — firing these is the reliable way to control the call. Match by button title; for a
     * 2-button incoming call with no title match, fall back to [decline, answer] order.
     */
    private fun extractActions(n: Notification, incoming: Boolean): Pair<android.app.PendingIntent?, android.app.PendingIntent?> {
        var answer: android.app.PendingIntent? = null
        var decline: android.app.PendingIntent? = null
        val acts = n.actions
        acts?.forEach { a ->
            val t = a.title?.toString()?.lowercase().orEmpty()
            when {
                answer == null && (t.contains("answer") || t.contains("accept")) -> answer = a.actionIntent
                decline == null && (t.contains("decline") || t.contains("reject") || t.contains("dismiss") ||
                    t.contains("hang") || t.contains("end") || t.contains("ignore")) -> decline = a.actionIntent
            }
        }
        if (incoming && answer == null && decline == null && acts != null && acts.size == 2) {
            decline = acts[0].actionIntent; answer = acts[1].actionIntent
        }
        return answer to decline
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        if (sbn?.notification?.category != Notification.CATEGORY_CALL) return
        if (!CallNotifRules.removalEndsCall(sbn.key, callKey)) {
            com.example.wayfindr.data.RideDiagnostics.log("media", "other call notification removed — call kept")
            return
        }
        // A dialer may drop and re-post the card (contact lookup, heads-up → shade): clear only if
        // it doesn't come back shortly.
        cancelPendingClear()
        val clear = Runnable {
            callKey = null
            CallInfoProvider.update(null)
            com.example.wayfindr.data.RideDiagnostics.log("media", "call ended (notification removed)")
        }
        pendingClear = clear
        handler.postDelayed(clear, CallNotifRules.REMOVE_GRACE_MS)
    }

    private val handler = android.os.Handler(android.os.Looper.getMainLooper())
    private var pendingClear: Runnable? = null
    private fun cancelPendingClear() { pendingClear?.let { handler.removeCallbacks(it) }; pendingClear = null }

    /** Caller name = the call notification's title; fall back to its text. */
    private fun callerName(n: Notification): String {
        val e = n.extras
        val title = e.getCharSequence(Notification.EXTRA_TITLE)?.toString()
        if (!title.isNullOrBlank()) return title
        return e.getCharSequence(Notification.EXTRA_TEXT)?.toString().orEmpty()
    }

    companion object {
        private const val TAG = "NsNotifListener"
        @Volatile private var bound: WayfindrNotificationListener? = null
        /** Key of the CALL notification behind [CallInfoProvider.incomingCall]. */
        @Volatile private var callKey: String? = null
        private val GENERIC = setOf("Call", "On call", "Gelen arama", "Incoming call", "Arama", "Telefon", "Phone")

        /** Ask Android to unbind the listener (app asleep); IdleSleep re-binds it on wake. */
        fun unbindNow() {
            bound?.let { runCatching { it.requestUnbind() } }
        }
    }
}
