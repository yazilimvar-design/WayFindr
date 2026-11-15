package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.ToneGenerator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import androidx.autofill.HintConstants;
import com.example.wayfindr.dash.nav.TurnAnnouncer;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: VoiceManager.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\b\u0007\u0018\u0000 /2\u00020\u0001:\u0001/B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fJ\b\u0010\u001a\u001a\u00020\u0012H\u0002J\u0012\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u0002J\u000e\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u001dJ\u0010\u0010 \u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u001dH\u0002J \u0010#\u001a\u00020\u00122\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'J\u0006\u0010)\u001a\u00020\u0012J\u000e\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u001dJ\u0006\u0010,\u001a\u00020\u0012J\u0010\u0010-\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020%H\u0002J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020'H\u0002R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/wayfindr/dash/nav/VoiceManager;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "app", "kotlin.jvm.PlatformType", "prefs", "Landroid/content/SharedPreferences;", "_mode", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/dash/nav/VoiceMode;", VoiceManager.KEY_MODE, "Lkotlinx/coroutines/flow/StateFlow;", "getMode", "()Lkotlinx/coroutines/flow/StateFlow;", "setMode", "", "m", "tts", "Landroid/speech/tts/TextToSpeech;", "ttsReady", "", "tone", "Landroid/media/ToneGenerator;", "ensureTts", "chime", "reason", "", "alert", "text", "speak", "announcer", "Lcom/example/wayfindr/dash/nav/TurnAnnouncer;", "maybeAnnounce", "maneuver", "Lcom/example/wayfindr/dash/nav/Maneuver;", "distanceM", "", "remainingM", "resetTrip", "announceNextStop", HintConstants.AUTOFILL_HINT_NAME, "shutdown", "turnPhrase", "roundDist", "Companion"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class VoiceManager {
    private static final double ARRIVE_M = 30.0d;
    private static final double FAR_M = 450.0d;
    private static final String KEY_MODE = "mode";
    private static final double NEAR_M = 60.0d;
    private static final String TAG = "VoiceManager";
    private static volatile VoiceManager instance;
    private final MutableStateFlow<VoiceMode> _mode;
    private final TurnAnnouncer announcer;
    private final Context app;
    private final StateFlow<VoiceMode> mode;
    private final SharedPreferences prefs;
    private ToneGenerator tone;
    private volatile TextToSpeech tts;
    private volatile boolean ttsReady;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: VoiceManager.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TurnAnnouncer.Event.values().length];
            try {
                iArr[TurnAnnouncer.Event.ARRIVE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TurnAnnouncer.Event.NEAR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TurnAnnouncer.Event.FAR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TurnAnnouncer.Event.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ManeuverType.values().length];
            try {
                iArr2[ManeuverType.TURN_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[ManeuverType.TURN_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[ManeuverType.SLIGHT_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr2[ManeuverType.SLIGHT_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr2[ManeuverType.SHARP_LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr2[ManeuverType.SHARP_RIGHT.ordinal()] = 6;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr2[ManeuverType.UTURN.ordinal()] = 7;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr2[ManeuverType.ROUNDABOUT.ordinal()] = 8;
            } catch (NoSuchFieldError e12) {
            }
            try {
                iArr2[ManeuverType.ARRIVE.ordinal()] = 9;
            } catch (NoSuchFieldError e13) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public /* synthetic */ VoiceManager(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private VoiceManager(Context context) {
        Object m8392constructorimpl;
        this.app = context.getApplicationContext();
        this.prefs = this.app.getSharedPreferences("voice", 0);
        try {
            Result.Companion companion = Result.INSTANCE;
            String string = this.prefs.getString(KEY_MODE, "CHIME");
            Intrinsics.checkNotNull(string);
            m8392constructorimpl = Result.m8392constructorimpl(VoiceMode.valueOf(string));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        this._mode = StateFlowKt.MutableStateFlow(Result.m8398isFailureimpl(m8392constructorimpl) ? VoiceMode.CHIME : m8392constructorimpl);
        this.mode = FlowKt.asStateFlow(this._mode);
        this.announcer = new TurnAnnouncer(450.0d, 60.0d, ARRIVE_M, 0.0d, 8, null);
    }

    public final StateFlow<VoiceMode> getMode() {
        return this.mode;
    }

    public final void setMode(VoiceMode m) {
        Intrinsics.checkNotNullParameter(m, "m");
        this.prefs.edit().putString(KEY_MODE, m.name()).apply();
        this._mode.setValue(m);
        if (m != VoiceMode.OFF) {
            if (m == VoiceMode.FULL) {
                ensureTts();
            }
        } else {
            TextToSpeech textToSpeech = this.tts;
            if (textToSpeech != null) {
                textToSpeech.stop();
            }
        }
    }

    private final void ensureTts() {
        if (this.tts != null) {
            return;
        }
        this.tts = new TextToSpeech(this.app, new TextToSpeech.OnInitListener() { // from class: com.example.wayfindr.dash.nav.VoiceManager$$ExternalSyntheticLambda0
            @Override // android.speech.tts.TextToSpeech.OnInitListener
            public final void onInit(int i) {
                VoiceManager.ensureTts$lambda$1(VoiceManager.this, i);
            }
        });
    }

    static final void ensureTts$lambda$1(VoiceManager this$0, int status) {
        this$0.ttsReady = status == 0;
        if (this$0.ttsReady) {
            Locale turkish = Locale.forLanguageTag("tr-TR");
            TextToSpeech textToSpeech = this$0.tts;
            int languageStatus = textToSpeech != null ? textToSpeech.isLanguageAvailable(turkish) : -2;
            this$0.ttsReady = languageStatus >= 0;
            if (this$0.ttsReady) {
                TextToSpeech textToSpeech2 = this$0.tts;
                if (textToSpeech2 != null) {
                    textToSpeech2.setLanguage(turkish);
                }
            } else {
                Log.w(TAG, "Turkish TTS voice is not installed; spoken directions will use chimes");
            }
            TextToSpeech textToSpeech3 = this$0.tts;
            if (textToSpeech3 != null) {
                textToSpeech3.setAudioAttributes(new AudioAttributes.Builder().setUsage(12).setContentType(1).build());
                return;
            }
            return;
        }
        Log.w(TAG, "TextToSpeech init failed: " + status);
    }

    static /* synthetic */ void chime$default(VoiceManager voiceManager, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = "";
        }
        voiceManager.chime(str);
    }

    private final void chime(String reason) {
        Object m8392constructorimpl;
        RideDiagnostics.INSTANCE.log("voice", StringsKt.trim((CharSequence) ("chime " + reason)).toString());
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceManager voiceManager = this;
            if (voiceManager.tone == null) {
                voiceManager.tone = new ToneGenerator(3, 80);
            }
            ToneGenerator toneGenerator = voiceManager.tone;
            m8392constructorimpl = Result.m8392constructorimpl(toneGenerator != null ? Boolean.valueOf(toneGenerator.startTone(24, 180)) : null);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "chime failed: " + m8395exceptionOrNullimpl.getMessage());
        }
    }

    public final void alert(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        if (this._mode.getValue() == VoiceMode.OFF) {
            return;
        }
        speak(text);
    }

    private final void speak(String text) {
        ensureTts();
        RideDiagnostics.INSTANCE.log("voice", "speak \"" + text + "\" (ready=" + this.ttsReady + ")");
        if (!this.ttsReady) {
            chime("(TTS not ready: " + text + ")");
            return;
        }
        TextToSpeech textToSpeech = this.tts;
        if (textToSpeech != null) {
            textToSpeech.speak(text, 0, null, String.valueOf(text.hashCode()));
        }
    }

    public final void maybeAnnounce(Maneuver maneuver, double distanceM, double remainingM) {
        if (this._mode.getValue() == VoiceMode.OFF) {
            return;
        }
        boolean full = this._mode.getValue() == VoiceMode.FULL;
        String why = String.format("Dönüşe %.0f metre, hedefe %.0f metre", Arrays.copyOf(new Object[]{Double.valueOf(distanceM), Double.valueOf(remainingM)}, 2));
        Intrinsics.checkNotNullExpressionValue(why, "format(...)");
        switch (WhenMappings.$EnumSwitchMapping$0[this.announcer.onTick(maneuver, distanceM, remainingM).ordinal()]) {
            case 1:
                if (full) {
                    speak("Hedefinize ulaştınız.");
                    return;
                } else {
                    chime("varış (" + why + ")");
                    return;
                }
            case 2:
                if (full) {
                    Intrinsics.checkNotNull(maneuver);
                    speak("Şimdi " + turnPhrase(maneuver) + ".");
                    return;
                } else {
                    chime("yakın dönüş (" + why + ")");
                    return;
                }
            case 3:
                if (full) {
                    String roundDist = roundDist(distanceM);
                    Intrinsics.checkNotNull(maneuver);
                    speak(roundDist + " sonra " + turnPhrase(maneuver) + ".");
                    return;
                }
                chime("dönüş (" + why + ")");
                return;
            case 4:
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final void resetTrip() {
        this.announcer.reset();
    }

    public final void announceNextStop(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (this._mode.getValue() == VoiceMode.OFF) {
            return;
        }
        if (this._mode.getValue() == VoiceMode.FULL) {
            speak("Durak geçildi. Şimdi " + name + " yönüne devam edin.");
        } else {
            chime$default(this, null, 1, null);
        }
    }

    public final void shutdown() {
        Unit unit;
        Unit unit2;
        try {
            Result.Companion companion = Result.INSTANCE;
            VoiceManager voiceManager = this;
            TextToSpeech textToSpeech = voiceManager.tts;
            if (textToSpeech != null) {
                textToSpeech.stop();
            }
            TextToSpeech textToSpeech2 = voiceManager.tts;
            if (textToSpeech2 != null) {
                textToSpeech2.shutdown();
                unit2 = Unit.INSTANCE;
            } else {
                unit2 = null;
            }
            Result.m8392constructorimpl(unit2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        this.tts = null;
        this.ttsReady = false;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            ToneGenerator toneGenerator = this.tone;
            if (toneGenerator != null) {
                toneGenerator.release();
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m8392constructorimpl(unit);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th2));
        }
        this.tone = null;
    }

    private final String turnPhrase(Maneuver m) {
        switch (WhenMappings.$EnumSwitchMapping$1[m.getType().ordinal()]) {
            case 1:
                return "sola dön";
            case 2:
                return "sağa dön";
            case 3:
                return "hafifçe sola dön";
            case 4:
                return "hafifçe sağa dön";
            case 5:
                return "keskin sola dön";
            case 6:
                return "keskin sağa dön";
            case 7:
                return "U dönüşü yap";
            case 8:
                return "dönel kavşağa gir";
            case 9:
                return "hedefinize ulaştınız";
            default:
                return "düz devam et";
        }
    }

    private final String roundDist(double m) {
        if (m >= 1000.0d) {
            String format = String.format("%.1f kilometre", Arrays.copyOf(new Object[]{Double.valueOf(m / 1000.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
        if (m >= 500.0d) {
            return "500 metre";
        }
        if (m >= 200.0d) {
            return "200 metre";
        }
        return (((int) (m / 50.0d)) * 50) + " metre";
    }

    /* compiled from: VoiceManager.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/example/wayfindr/dash/nav/VoiceManager$Companion;", "", "<init>", "()V", "TAG", "", "FAR_M", "", "NEAR_M", "ARRIVE_M", "KEY_MODE", "instance", "Lcom/example/wayfindr/dash/nav/VoiceManager;", "get", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VoiceManager get(Context context) {
            VoiceManager voiceManager;
            Intrinsics.checkNotNullParameter(context, "context");
            VoiceManager voiceManager2 = VoiceManager.instance;
            if (voiceManager2 == null) {
                synchronized (this) {
                    voiceManager = VoiceManager.instance;
                    if (voiceManager == null) {
                        voiceManager = new VoiceManager(context, null);
                        Companion companion = VoiceManager.INSTANCE;
                        VoiceManager.instance = voiceManager;
                    }
                }
                return voiceManager;
            }
            return voiceManager2;
        }
    }
}
