package com.example.wayfindr.ui.components;

import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: RouteAlternativeStyle.kt */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0003H\u0000¨\u0006\u0007"}, d2 = {"routeDifferenceLabel", "", "routeMeters", "", "routeSeconds", "baseMeters", "baseSeconds", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class RouteAlternativeStyleKt {
    public static final String routeDifferenceLabel(double routeMeters, double routeSeconds, double baseMeters, double baseSeconds) {
        String sign;
        String duration;
        double distanceDelta = routeMeters - baseMeters;
        if (Math.abs(distanceDelta) < 50.0d) {
            sign = "0 km";
        } else {
            String sign2 = distanceDelta > 0.0d ? "+" : "−";
            String amount = String.format(Locale.forLanguageTag("tr-TR"), "%.1f", Arrays.copyOf(new Object[]{Double.valueOf(Math.abs(distanceDelta) / 1000.0d)}, 1));
            Intrinsics.checkNotNullExpressionValue(amount, "format(...)");
            sign = sign2 + amount + " km";
        }
        int minutes = MathKt.roundToInt((routeSeconds - baseSeconds) / 60.0d);
        if (minutes == 0) {
            duration = "0 dk";
        } else {
            duration = minutes > 0 ? "+" + minutes + " dk" : "−" + Math.abs(minutes) + " dk";
        }
        return sign + " · " + duration;
    }
}
