package com.example.wayfindr.dash;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import android.media.AudioManager;
import android.os.BatteryManager;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: PhoneStatus.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000  2\u00020\u0001:\u0001 BC\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003JE\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006!"}, d2 = {"Lcom/example/wayfindr/dash/PhoneStatus;", "", "batteryPct", "", "charging", "", "signal", "gpsOn", "mediaVolume", "callVolume", "<init>", "(IZZZII)V", "getBatteryPct", "()I", "getCharging", "()Z", "getSignal", "getGpsOn", "getMediaVolume", "getCallVolume", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "toString", "", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class PhoneStatus {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int batteryPct;
    private final int callVolume;
    private final boolean charging;
    private final boolean gpsOn;
    private final int mediaVolume;
    private final boolean signal;

    public PhoneStatus() {
        this(0, false, false, false, 0, 0, 63, null);
    }

    public static /* synthetic */ PhoneStatus copy$default(PhoneStatus phoneStatus, int i, boolean z, boolean z2, boolean z3, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = phoneStatus.batteryPct;
        }
        if ((i4 & 2) != 0) {
            z = phoneStatus.charging;
        }
        if ((i4 & 4) != 0) {
            z2 = phoneStatus.signal;
        }
        if ((i4 & 8) != 0) {
            z3 = phoneStatus.gpsOn;
        }
        if ((i4 & 16) != 0) {
            i2 = phoneStatus.mediaVolume;
        }
        if ((i4 & 32) != 0) {
            i3 = phoneStatus.callVolume;
        }
        int i5 = i2;
        int i6 = i3;
        return phoneStatus.copy(i, z, z2, z3, i5, i6);
    }

    /* renamed from: component1, reason: from getter */
    public final int getBatteryPct() {
        return this.batteryPct;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getCharging() {
        return this.charging;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getSignal() {
        return this.signal;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getGpsOn() {
        return this.gpsOn;
    }

    /* renamed from: component5, reason: from getter */
    public final int getMediaVolume() {
        return this.mediaVolume;
    }

    /* renamed from: component6, reason: from getter */
    public final int getCallVolume() {
        return this.callVolume;
    }

    public final PhoneStatus copy(int batteryPct, boolean charging, boolean signal, boolean gpsOn, int mediaVolume, int callVolume) {
        return new PhoneStatus(batteryPct, charging, signal, gpsOn, mediaVolume, callVolume);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PhoneStatus)) {
            return false;
        }
        PhoneStatus phoneStatus = (PhoneStatus) other;
        return this.batteryPct == phoneStatus.batteryPct && this.charging == phoneStatus.charging && this.signal == phoneStatus.signal && this.gpsOn == phoneStatus.gpsOn && this.mediaVolume == phoneStatus.mediaVolume && this.callVolume == phoneStatus.callVolume;
    }

    public int hashCode() {
        return (((((((((Integer.hashCode(this.batteryPct) * 31) + Boolean.hashCode(this.charging)) * 31) + Boolean.hashCode(this.signal)) * 31) + Boolean.hashCode(this.gpsOn)) * 31) + Integer.hashCode(this.mediaVolume)) * 31) + Integer.hashCode(this.callVolume);
    }

    public String toString() {
        return "PhoneStatus(batteryPct=" + this.batteryPct + ", charging=" + this.charging + ", signal=" + this.signal + ", gpsOn=" + this.gpsOn + ", mediaVolume=" + this.mediaVolume + ", callVolume=" + this.callVolume + ")";
    }

    public PhoneStatus(int batteryPct, boolean charging, boolean signal, boolean gpsOn, int mediaVolume, int callVolume) {
        this.batteryPct = batteryPct;
        this.charging = charging;
        this.signal = signal;
        this.gpsOn = gpsOn;
        this.mediaVolume = mediaVolume;
        this.callVolume = callVolume;
    }

    public /* synthetic */ PhoneStatus(int i, boolean z, boolean z2, boolean z3, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 62 : i, (i4 & 2) != 0 ? false : z, (i4 & 4) != 0 ? true : z2, (i4 & 8) != 0 ? true : z3, (i4 & 16) != 0 ? 3 : i2, (i4 & 32) != 0 ? 9 : i3);
    }

    public final int getBatteryPct() {
        return this.batteryPct;
    }

    public final boolean getCharging() {
        return this.charging;
    }

    public final boolean getSignal() {
        return this.signal;
    }

    public final boolean getGpsOn() {
        return this.gpsOn;
    }

    public final int getMediaVolume() {
        return this.mediaVolume;
    }

    public final int getCallVolume() {
        return this.callVolume;
    }

    /* compiled from: PhoneStatus.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u001f\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\rJ\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t¨\u0006\u0011"}, d2 = {"Lcom/example/wayfindr/dash/PhoneStatus$Companion;", "", "<init>", "()V", "read", "Lcom/example/wayfindr/dash/PhoneStatus;", "context", "Landroid/content/Context;", "volumeLevel", "", "am", "Landroid/media/AudioManager;", "stream", "(Landroid/media/AudioManager;I)Ljava/lang/Integer;", "volume", "min", "max", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0073, code lost:
        
            if (((r8 < 0 || r8 >= 101) ? 0 : 1) != 0) goto L25;
         */
        /* JADX WARN: Removed duplicated region for block: B:56:0x015c  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final PhoneStatus read(Context context) {
            Object m8392constructorimpl;
            Integer num;
            Object m8392constructorimpl2;
            Object m8392constructorimpl3;
            int mediaVolume;
            int callVolume;
            Intrinsics.checkNotNullParameter(context, "context");
            PhoneStatus d = new PhoneStatus(0, false, false, false, 0, 0, 63, null);
            Context app = context.getApplicationContext();
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                m8392constructorimpl = Result.m8392constructorimpl(app.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED")));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                m8392constructorimpl = null;
            }
            Intent battery = (Intent) m8392constructorimpl;
            Object systemService = app.getSystemService("batterymanager");
            BatteryManager batteryManager = systemService instanceof BatteryManager ? (BatteryManager) systemService : null;
            boolean z = true;
            if (batteryManager != null) {
                num = Integer.valueOf(batteryManager.getIntProperty(4));
                int intValue = num.intValue();
            }
            num = null;
            Integer pct = num;
            int status = battery != null ? battery.getIntExtra(NotificationCompat.CATEGORY_STATUS, -1) : -1;
            boolean charging = status == 2 || status == 5;
            try {
                Result.Companion companion4 = Result.INSTANCE;
                Companion companion5 = this;
                Object systemService2 = app.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
                Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.telephony.TelephonyManager");
                SignalStrength signalStrength = ((TelephonyManager) systemService2).getSignalStrength();
                if ((signalStrength != null ? signalStrength.getLevel() : 0) <= 0) {
                    z = false;
                }
                m8392constructorimpl2 = Result.m8392constructorimpl(Boolean.valueOf(z));
            } catch (Throwable th2) {
                Result.Companion companion6 = Result.INSTANCE;
                m8392constructorimpl2 = Result.m8392constructorimpl(ResultKt.createFailure(th2));
            }
            Boolean valueOf = Boolean.valueOf(d.getSignal());
            if (Result.m8398isFailureimpl(m8392constructorimpl2)) {
                m8392constructorimpl2 = valueOf;
            }
            boolean signal = ((Boolean) m8392constructorimpl2).booleanValue();
            try {
                Result.Companion companion7 = Result.INSTANCE;
                Companion companion8 = this;
                Object systemService3 = app.getSystemService("location");
                Intrinsics.checkNotNull(systemService3, "null cannot be cast to non-null type android.location.LocationManager");
                m8392constructorimpl3 = Result.m8392constructorimpl(Boolean.valueOf(((LocationManager) systemService3).isProviderEnabled("gps")));
            } catch (Throwable th3) {
                Result.Companion companion9 = Result.INSTANCE;
                m8392constructorimpl3 = Result.m8392constructorimpl(ResultKt.createFailure(th3));
            }
            Boolean valueOf2 = Boolean.valueOf(d.getGpsOn());
            if (Result.m8398isFailureimpl(m8392constructorimpl3)) {
                m8392constructorimpl3 = valueOf2;
            }
            boolean gps = ((Boolean) m8392constructorimpl3).booleanValue();
            Object systemService4 = app.getSystemService("audio");
            AudioManager am = systemService4 instanceof AudioManager ? (AudioManager) systemService4 : null;
            int intValue2 = pct != null ? pct.intValue() : d.getBatteryPct();
            boolean charging2 = battery != null ? charging : d.getCharging();
            if (am != null) {
                Integer volumeLevel = PhoneStatus.INSTANCE.volumeLevel(am, 3);
                if (volumeLevel != null) {
                    mediaVolume = volumeLevel.intValue();
                    if (am != null) {
                        Integer volumeLevel2 = PhoneStatus.INSTANCE.volumeLevel(am, 0);
                        if (volumeLevel2 != null) {
                            callVolume = volumeLevel2.intValue();
                            return new PhoneStatus(intValue2, charging2, signal, gps, mediaVolume, callVolume);
                        }
                    }
                    callVolume = d.getCallVolume();
                    return new PhoneStatus(intValue2, charging2, signal, gps, mediaVolume, callVolume);
                }
            }
            mediaVolume = d.getMediaVolume();
            if (am != null) {
            }
            callVolume = d.getCallVolume();
            return new PhoneStatus(intValue2, charging2, signal, gps, mediaVolume, callVolume);
        }

        private final Integer volumeLevel(AudioManager am, int stream) {
            Object m8392constructorimpl;
            try {
                Result.Companion companion = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(Integer.valueOf(volumeLevel(am.getStreamVolume(stream), am.getStreamMinVolume(stream), am.getStreamMaxVolume(stream))));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                m8392constructorimpl = null;
            }
            return (Integer) m8392constructorimpl;
        }

        public final int volumeLevel(int volume, int min, int max) {
            if (volume <= 0 || max <= min) {
                return 0;
            }
            float step = (max - min) / 10.0f;
            return RangesKt.coerceIn((int) Math.ceil(volume / step), 1, 10);
        }
    }
}
