package com.example.wayfindr.util;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.PowerManager;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceReadiness.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0007J\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/wayfindr/util/DeviceReadiness;", "", "<init>", "()V", "PREFS", "", "KEY_BATTERY_ASKED", "isIgnoringBatteryOptimizations", "", "ctx", "Landroid/content/Context;", "batteryExemptionAsked", "markBatteryExemptionAsked", "", "batteryExemptionIntent", "Landroid/content/Intent;", "appSettingsIntent", "hasBackgroundLocation", "locationServicesEnabled", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DeviceReadiness {
    public static final int $stable = 0;
    public static final DeviceReadiness INSTANCE = new DeviceReadiness();
    private static final String KEY_BATTERY_ASKED = "battery_exemption_asked";
    private static final String PREFS = "wayfindr_readiness";

    private DeviceReadiness() {
    }

    public final boolean isIgnoringBatteryOptimizations(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Object systemService = ctx.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        PowerManager pm = (PowerManager) systemService;
        return pm.isIgnoringBatteryOptimizations(ctx.getPackageName());
    }

    public final boolean batteryExemptionAsked(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return ctx.getSharedPreferences(PREFS, 0).getBoolean(KEY_BATTERY_ASKED, false);
    }

    public final void markBatteryExemptionAsked(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        ctx.getSharedPreferences(PREFS, 0).edit().putBoolean(KEY_BATTERY_ASKED, true).apply();
    }

    public final Intent batteryExemptionIntent(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intent addFlags = new Intent("android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS", Uri.parse("package:" + ctx.getPackageName())).addFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(addFlags, "addFlags(...)");
        return addFlags;
    }

    public final Intent appSettingsIntent(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        Intent addFlags = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + ctx.getPackageName())).addFlags(268435456);
        Intrinsics.checkNotNullExpressionValue(addFlags, "addFlags(...)");
        return addFlags;
    }

    public final boolean hasBackgroundLocation(Context ctx) {
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        return ctx.checkSelfPermission("android.permission.ACCESS_BACKGROUND_LOCATION") == 0;
    }

    public final boolean locationServicesEnabled(Context ctx) {
        Object m8392constructorimpl;
        Intrinsics.checkNotNullParameter(ctx, "ctx");
        try {
            Result.Companion companion = Result.INSTANCE;
            DeviceReadiness deviceReadiness = this;
            Object systemService = ctx.getSystemService("location");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
            m8392constructorimpl = Result.m8392constructorimpl(Boolean.valueOf(((LocationManager) systemService).isLocationEnabled()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = true;
        }
        return ((Boolean) m8392constructorimpl).booleanValue();
    }
}
