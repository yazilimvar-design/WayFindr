package com.example.wayfindr.dash.nav

import android.content.Context
import android.media.AudioManager
import android.media.ToneGenerator
import android.speech.tts.TextToSpeech
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Locale

enum class VoiceMode { OFF, CHIME, FULL }

/**
 * Turn-by-turn voice guidance. We own this layer (CLAUDE.md feature 2), so it's a plain
 * per-trip setting: OFF (silent), CHIME (a beep before each turn), or FULL (spoken
 * directions). Singleton so the RouteScreen toggle and the nav loop in [DashViewModel]
 * share one engine + persisted mode.
 *
 * Announcements run through [maybeAnnounce]: as the rider approaches a maneuver it fires
 * once at a FAR range ("in 400 m, turn left onto X") and once NEAR it ("turn left now"),
 * de-duped per maneuver so it never nags.
 */
class VoiceManager private constructor(context: Context) {

    private val app = context.applicationContext
    private val prefs = app.getSharedPreferences("voice", Context.MODE_PRIVATE)

    private val _mode = MutableStateFlow(
        runCatching { VoiceMode.valueOf(prefs.getString(KEY_MODE, VoiceMode.CHIME.name)!!) }
            .getOrDefault(VoiceMode.CHIME)
    )
    val mode = _mode.asStateFlow()

    fun setMode(m: VoiceMode) {
        prefs.edit().putString(KEY_MODE, m.name).apply()
        _mode.value = m
        if (m == VoiceMode.OFF) tts?.stop()
        else if (m == VoiceMode.FULL) ensureTts()  // warm up so the first turn isn't silent
    }

    // ── TTS engine (lazy; created only when FULL is actually used) ──────────────
    @Volatile private var tts: TextToSpeech? = null
    @Volatile private var ttsReady = false
    private var tone: ToneGenerator? = null

    private fun ensureTts() {
        if (tts != null) return
        tts = TextToSpeech(app) { status ->
            ttsReady = status == TextToSpeech.SUCCESS
            if (ttsReady) {
                // Directions are Turkish; without an installed Turkish voice fall back to chimes
                // rather than reading Turkish text with a foreign voice.
                val turkish = Locale.forLanguageTag("tr-TR")
                val languageStatus = tts?.isLanguageAvailable(turkish) ?: TextToSpeech.LANG_NOT_SUPPORTED
                ttsReady = languageStatus >= TextToSpeech.LANG_AVAILABLE
                if (ttsReady) tts?.language = turkish
                else Log.w(TAG, "Turkish TTS voice is not installed; spoken directions will use chimes")
                // Without explicit attributes the nav voice can land on the earpiece/phone speaker
                // instead of the connected BT helmet. NAVIGATION_GUIDANCE routes to the active media
                // output (BT A2DP / speaker) and ducks music while speaking — exactly what we want.
                tts?.setAudioAttributes(
                    android.media.AudioAttributes.Builder()
                        .setUsage(android.media.AudioAttributes.USAGE_ASSISTANCE_NAVIGATION_GUIDANCE)
                        .setContentType(android.media.AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build()
                )
            } else Log.w(TAG, "TextToSpeech init failed: $status")
        }
    }

    private fun chime(reason: String = "") {
        com.example.wayfindr.data.RideDiagnostics.log("voice", "chime ${reason}".trim())
        runCatching {
            if (tone == null) tone = ToneGenerator(AudioManager.STREAM_MUSIC, 80)
            tone?.startTone(ToneGenerator.TONE_PROP_BEEP, 180)
        }.onFailure { Log.w(TAG, "chime failed: ${it.message}") }
    }

    /**
     * One-off spoken status alert (e.g. "Dash disconnected") — distinct from turn guidance. Speaks
     * a real phrase even in CHIME mode (a status alert needs words), routed to BT via the TTS audio
     * attributes; stays silent only when voice is fully OFF.
     */
    fun alert(text: String) {
        if (_mode.value == VoiceMode.OFF) return
        speak(text)
    }

    private fun speak(text: String) {
        ensureTts()
        com.example.wayfindr.data.RideDiagnostics.log("voice", "speak \"$text\" (ready=$ttsReady)")
        if (ttsReady) tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, text.hashCode().toString())
        else chime("(TTS not ready: $text)") // engine not ready yet → at least beep
    }

    // ── Announcement scheduling ────────────────────────────────────────────────
    private val announcer = TurnAnnouncer(FAR_M, NEAR_M, ARRIVE_M)

    /**
     * Call every nav tick. [maneuver] is the upcoming turn (null = none), [distanceM] the
     * distance to it, [remainingM] distance to the destination.
     */
    fun maybeAnnounce(maneuver: Maneuver?, distanceM: Double, remainingM: Double) {
        if (_mode.value == VoiceMode.OFF) return

        val full = _mode.value == VoiceMode.FULL
        val why = "Dönüşe %.0f metre, hedefe %.0f metre".format(distanceM, remainingM)
        when (announcer.onTick(maneuver, distanceM, remainingM)) {
            TurnAnnouncer.Event.ARRIVE -> if (full) speak("Hedefinize ulaştınız.") else chime("varış ($why)")
            TurnAnnouncer.Event.NEAR -> if (full) speak("Şimdi ${turnPhrase(maneuver!!)}.") else chime("yakın dönüş ($why)")
            TurnAnnouncer.Event.FAR -> if (full) speak("${roundDist(distanceM)} sonra ${turnPhrase(maneuver!!)}.") else chime("dönüş ($why)")
            TurnAnnouncer.Event.NONE -> Unit
        }
    }

    /** Reset trip state when a NEW destination is set or nav ends — never on a route refresh. */
    fun resetTrip() = announcer.reset()

    /** Announce a completed waypoint without ending guidance for the rest of the itinerary. */
    fun announceNextStop(name: String) {
        if (_mode.value == VoiceMode.OFF) return
        if (_mode.value == VoiceMode.FULL) speak("Durak geçildi. Şimdi $name yönüne devam edin.") else chime()
    }

    fun shutdown() {
        runCatching { tts?.stop(); tts?.shutdown() }
        tts = null; ttsReady = false
        runCatching { tone?.release() }; tone = null
    }

    private fun turnPhrase(m: Maneuver): String = when (m.type) {
        ManeuverType.TURN_LEFT    -> "sola dön"
        ManeuverType.TURN_RIGHT   -> "sağa dön"
        ManeuverType.SLIGHT_LEFT  -> "hafifçe sola dön"
        ManeuverType.SLIGHT_RIGHT -> "hafifçe sağa dön"
        ManeuverType.SHARP_LEFT   -> "keskin sola dön"
        ManeuverType.SHARP_RIGHT  -> "keskin sağa dön"
        ManeuverType.UTURN        -> "U dönüşü yap"
        ManeuverType.ROUNDABOUT   -> "dönel kavşağa gir"
        ManeuverType.ARRIVE       -> "hedefinize ulaştınız"
        else                      -> "düz devam et"
    }

    private fun roundDist(m: Double): String = when {
        m >= 1000 -> "%.1f kilometre".format(m / 1000.0)
        m >= 500  -> "500 metre"
        m >= 200  -> "200 metre"
        else      -> "${(m / 50).toInt() * 50} metre"
    }

    companion object {
        private const val TAG = "VoiceManager"
        private const val FAR_M = 450.0
        private const val NEAR_M = 60.0
        private const val ARRIVE_M = 30.0
        private const val KEY_MODE = "mode"

        @Volatile private var instance: VoiceManager? = null
        fun get(context: Context): VoiceManager =
            instance ?: synchronized(this) {
                instance ?: VoiceManager(context).also { instance = it }
            }
    }
}
