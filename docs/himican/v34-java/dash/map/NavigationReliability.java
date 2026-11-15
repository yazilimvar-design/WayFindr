package com.example.wayfindr.dash.map;

import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: NavigationReliability.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u001d\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000eJ \u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00052\b\b\u0002\u0010\u0012\u001a\u00020\u0005J-\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\b¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/example/wayfindr/dash/map/NavigationReliability;", "", "<init>", "()V", "MAX_PLAUSIBLE_SPEED_MPS", "", "MAX_TRUSTED_ACCURACY_M", "isFreshFixAge", "", "ageMs", "", "maxAgeMs", "routeOriginScore", "accuracyMeters", "(Ljava/lang/Double;J)D", "isPlausibleJump", "distanceMeters", "intervalSeconds", "maxSpeedMps", "isConfidentlyOffRoute", "snapDistanceMeters", "headingKnown", "headingDisagrees", "(DLjava/lang/Double;ZZ)Z", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class NavigationReliability {
    public static final int $stable = 0;
    public static final NavigationReliability INSTANCE = new NavigationReliability();
    public static final double MAX_PLAUSIBLE_SPEED_MPS = 85.0d;
    private static final double MAX_TRUSTED_ACCURACY_M = 60.0d;

    private NavigationReliability() {
    }

    public final boolean isFreshFixAge(long ageMs, long maxAgeMs) {
        return 0 <= ageMs && ageMs <= maxAgeMs;
    }

    public final double routeOriginScore(Double accuracyMeters, long ageMs) {
        double accuracy;
        double agePenalty;
        if (accuracyMeters != null) {
            double doubleValue = accuracyMeters.doubleValue();
            Double d = ((Math.abs(doubleValue) > Double.MAX_VALUE ? 1 : (Math.abs(doubleValue) == Double.MAX_VALUE ? 0 : -1)) <= 0) && doubleValue >= 0.0d ? accuracyMeters : null;
            if (d != null) {
                accuracy = d.doubleValue();
                if (ageMs >= 0 || ageMs == Long.MAX_VALUE) {
                    agePenalty = Double.POSITIVE_INFINITY;
                } else {
                    agePenalty = (ageMs / 1000.0d) * 2.0d;
                }
                return accuracy + agePenalty;
            }
        }
        accuracy = 100.0d;
        if (ageMs >= 0) {
        }
        agePenalty = Double.POSITIVE_INFINITY;
        return accuracy + agePenalty;
    }

    public static /* synthetic */ boolean isPlausibleJump$default(NavigationReliability navigationReliability, double d, double d2, double d3, int i, Object obj) {
        double d4;
        if ((i & 4) == 0) {
            d4 = d3;
        } else {
            d4 = 85.0d;
        }
        return navigationReliability.isPlausibleJump(d, d2, d4);
    }

    public final boolean isPlausibleJump(double distanceMeters, double intervalSeconds, double maxSpeedMps) {
        if ((Math.abs(distanceMeters) <= Double.MAX_VALUE) && distanceMeters >= 0.0d) {
            if ((Math.abs(intervalSeconds) <= Double.MAX_VALUE) && intervalSeconds >= 0.0d && maxSpeedMps > 0.0d) {
                return (intervalSeconds > 0.0d ? 1 : (intervalSeconds == 0.0d ? 0 : -1)) == 0 ? distanceMeters <= 1.0d : distanceMeters / intervalSeconds <= maxSpeedMps;
            }
        }
        return false;
    }

    public final boolean isConfidentlyOffRoute(double snapDistanceMeters, Double accuracyMeters, boolean headingKnown, boolean headingDisagrees) {
        if (accuracyMeters != null) {
            double doubleValue = accuracyMeters.doubleValue();
            Double d = ((Math.abs(doubleValue) > Double.MAX_VALUE ? 1 : (Math.abs(doubleValue) == Double.MAX_VALUE ? 0 : -1)) <= 0) && (doubleValue > 0.0d ? 1 : (doubleValue == 0.0d ? 0 : -1)) >= 0 ? accuracyMeters : null;
            if (d != null) {
                double accuracy = d.doubleValue();
                if (!(Math.abs(snapDistanceMeters) <= Double.MAX_VALUE) || snapDistanceMeters < 0.0d || accuracy > 60.0d) {
                    return false;
                }
                double clearGap = Math.max(150.0d, 3.0d * accuracy);
                double ambiguousGap = Math.max(70.0d, 2.0d * accuracy);
                if (snapDistanceMeters > clearGap) {
                    return true;
                }
                return snapDistanceMeters > ambiguousGap && headingKnown && headingDisagrees;
            }
        }
        return false;
    }
}
