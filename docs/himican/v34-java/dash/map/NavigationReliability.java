package com.example.wayfindr.dash.map;

/* compiled from: NavigationReliability.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class NavigationReliability {
    public static final int $stable = 0;
    public static final NavigationReliability INSTANCE = new NavigationReliability();
    public static final double MAX_PLAUSIBLE_SPEED_MPS = 85.0d;
    private static final double MAX_TRUSTED_ACCURACY_M = 60.0d;

    private NavigationReliability() {
    }

    public final boolean isConfidentlyOffRoute(double d, Double d2, boolean z, boolean z2) {
        if (d2 != null) {
            double doubleValue = d2.doubleValue();
            Double d3 = ((Math.abs(doubleValue) > Double.MAX_VALUE ? 1 : (Math.abs(doubleValue) == Double.MAX_VALUE ? 0 : -1)) <= 0) && (doubleValue > 0.0d ? 1 : (doubleValue == 0.0d ? 0 : -1)) >= 0 ? d2 : null;
            if (d3 != null) {
                double doubleValue2 = d3.doubleValue();
                if (!(Math.abs(d) <= Double.MAX_VALUE) || d < 0.0d || doubleValue2 > MAX_TRUSTED_ACCURACY_M) {
                    return false;
                }
                double max = Math.max(75.0d, 3.0d * doubleValue2);
                double max2 = Math.max(45.0d, 2.0d * doubleValue2);
                if (d > max) {
                    return true;
                }
                return d > max2 && z && z2;
            }
        }
        return false;
    }

    public final boolean isFreshFixAge(long j, long j2) {
        return 0 <= j && j <= j2;
    }

    public final boolean isPlausibleJump(double d, double d2, double d3) {
        if ((Math.abs(d) <= Double.MAX_VALUE) && d >= 0.0d) {
            if ((Math.abs(d2) <= Double.MAX_VALUE) && d2 >= 0.0d && d3 > 0.0d) {
                return (d2 > 0.0d ? 1 : (d2 == 0.0d ? 0 : -1)) == 0 ? d <= 1.0d : d / d2 <= d3;
            }
        }
        return false;
    }

    public final double routeOriginScore(Double d, long j) {
        double d2;
        if (d != null) {
            double doubleValue = d.doubleValue();
            Double d3 = ((Math.abs(doubleValue) > Double.MAX_VALUE ? 1 : (Math.abs(doubleValue) == Double.MAX_VALUE ? 0 : -1)) <= 0) && doubleValue >= 0.0d ? d : null;
            if (d3 != null) {
                d2 = d3.doubleValue();
                return d2 + ((j >= 0 || j == Long.MAX_VALUE) ? Double.POSITIVE_INFINITY : (j / 1000.0d) * 2.0d);
            }
        }
        d2 = 100.0d;
        return d2 + ((j >= 0 || j == Long.MAX_VALUE) ? Double.POSITIVE_INFINITY : (j / 1000.0d) * 2.0d);
    }
}
