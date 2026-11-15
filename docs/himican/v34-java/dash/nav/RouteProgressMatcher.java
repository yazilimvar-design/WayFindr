package com.example.wayfindr.dash.nav;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: RouteProgressMatcher.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JI\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\r¢\u0006\u0002\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/dash/nav/RouteProgressMatcher;", "", "<init>", "()V", "match", "Lcom/example/wayfindr/dash/nav/RouteProgressMatch;", "geometry", "", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "cumulativeMeters", "", "position", "currentProgressMeters", "", "gpsBearingDegrees", "", "speedMetersPerSecond", "(Ljava/util/List;[DLcom/example/wayfindr/dash/nav/GeoPoint;DLjava/lang/Float;D)Lcom/example/wayfindr/dash/nav/RouteProgressMatch;", "angularDifference", "a", "b", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class RouteProgressMatcher {
    public static final int $stable = 0;
    public static final RouteProgressMatcher INSTANCE = new RouteProgressMatcher();

    private RouteProgressMatcher() {
    }

    public static /* synthetic */ RouteProgressMatch match$default(RouteProgressMatcher routeProgressMatcher, List list, double[] dArr, GeoPoint geoPoint, double d, Float f, double d2, int i, Object obj) {
        Float f2;
        double d3;
        if ((i & 16) == 0) {
            f2 = f;
        } else {
            f2 = null;
        }
        if ((i & 32) == 0) {
            d3 = d2;
        } else {
            d3 = 0.0d;
        }
        return routeProgressMatcher.match(list, dArr, geoPoint, d, f2, d3);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final RouteProgressMatch match(List<GeoPoint> geometry, double[] cumulativeMeters, GeoPoint position, double currentProgressMeters, Float gpsBearingDegrees, double speedMetersPerSecond) {
        Float trustedBearing;
        RouteProgressMatch local;
        RouteProgressMatch global;
        Intrinsics.checkNotNullParameter(geometry, "geometry");
        Intrinsics.checkNotNullParameter(cumulativeMeters, "cumulativeMeters");
        Intrinsics.checkNotNullParameter(position, "position");
        if (geometry.size() < 2 || cumulativeMeters.length != geometry.size()) {
            return null;
        }
        if (!(Math.abs(currentProgressMeters) <= Double.MAX_VALUE) || currentProgressMeters < 0.0d) {
            return null;
        }
        Double valueOf = Double.valueOf(speedMetersPerSecond);
        double doubleValue = valueOf.doubleValue();
        if (((!((Math.abs(doubleValue) > Double.MAX_VALUE ? 1 : (Math.abs(doubleValue) == Double.MAX_VALUE ? 0 : -1)) <= 0) || doubleValue <= 0.0d) ? 0 : 1) == 0) {
            valueOf = null;
        }
        double safeSpeed = valueOf != null ? valueOf.doubleValue() : 0.0d;
        double forwardAllowance = Math.max(40.0d, 4.0d * safeSpeed);
        if (gpsBearingDegrees != null) {
            if (Math.abs(gpsBearingDegrees.floatValue()) <= Float.MAX_VALUE) {
                trustedBearing = gpsBearingDegrees;
                local = match$search(geometry, cumulativeMeters, position, currentProgressMeters, forwardAllowance, trustedBearing, RangesKt.coerceAtLeast(currentProgressMeters - 60.0d, 0.0d), currentProgressMeters + 1000.0d, false);
                if (local != null) {
                    return match$search(geometry, cumulativeMeters, position, currentProgressMeters, forwardAllowance, trustedBearing, 0.0d, ArraysKt.last(cumulativeMeters), true);
                }
                return (local.getLateralDistanceMeters() <= 80.0d || (global = match$search(geometry, cumulativeMeters, position, currentProgressMeters, forwardAllowance, trustedBearing, 0.0d, ArraysKt.last(cumulativeMeters), true)) == null || global.getLateralDistanceMeters() >= local.getLateralDistanceMeters()) ? local : global;
            }
        }
        trustedBearing = null;
        local = match$search(geometry, cumulativeMeters, position, currentProgressMeters, forwardAllowance, trustedBearing, RangesKt.coerceAtLeast(currentProgressMeters - 60.0d, 0.0d), currentProgressMeters + 1000.0d, false);
        if (local != null) {
        }
    }

    private static final RouteProgressMatch match$search(List<GeoPoint> list, double[] $cumulativeMeters, GeoPoint $position, double $currentProgressMeters, double forwardAllowance, Float trustedBearing, double lo, double hi, boolean global) {
        double bestScore;
        RouteProgressMatch best;
        double d;
        List<GeoPoint> list2 = list;
        GeoPoint geoPoint = $position;
        double bestScore2 = Double.POSITIVE_INFINITY;
        RouteProgressMatch best2 = null;
        int index = 0;
        int lastIndex = CollectionsKt.getLastIndex(list2);
        while (index < lastIndex) {
            if ($cumulativeMeters[index + 1] < lo) {
                bestScore = bestScore2;
                best = best2;
            } else if ($cumulativeMeters[index] > hi) {
                bestScore = bestScore2;
                best = best2;
            } else {
                GeoPoint start = list2.get(index);
                GeoPoint end = list2.get(index + 1);
                Pair<GeoPoint, Double> projectOnSegment = GeoPoint.INSTANCE.projectOnSegment(geoPoint, start, end);
                GeoPoint projection = projectOnSegment.component1();
                double fraction = projectOnSegment.component2().doubleValue();
                double lateral = GeoPoint.INSTANCE.distMeters(geoPoint, projection);
                double segmentLength = GeoPoint.INSTANCE.distMeters(start, end);
                double candidateProgress = $cumulativeMeters[index] + (segmentLength * fraction);
                float routeHeading = (float) GeoPoint.INSTANCE.bearing(start, end);
                bestScore = bestScore2;
                best = best2;
                double headingPenalty = 0.0d;
                double backwardMeters = RangesKt.coerceAtLeast(($currentProgressMeters - candidateProgress) - 12.0d, 0.0d);
                double forwardMeters = RangesKt.coerceAtLeast((candidateProgress - $currentProgressMeters) - forwardAllowance, 0.0d);
                if (global) {
                    d = (0.2d * backwardMeters) + (0.04d * forwardMeters);
                } else {
                    d = (0.7d * backwardMeters) + (1.25d * forwardMeters);
                }
                double continuityPenalty = d;
                if (trustedBearing != null) {
                    headingPenalty = INSTANCE.angularDifference(trustedBearing.floatValue(), routeHeading) * 0.5d;
                }
                double score = lateral + continuityPenalty + headingPenalty;
                if (score < bestScore) {
                    bestScore = score;
                    best2 = new RouteProgressMatch(candidateProgress, lateral, routeHeading, projection);
                    bestScore2 = bestScore;
                    index++;
                    list2 = list;
                    geoPoint = $position;
                }
            }
            best2 = best;
            bestScore2 = bestScore;
            index++;
            list2 = list;
            geoPoint = $position;
        }
        return best2;
    }

    private final double angularDifference(float a, float b) {
        float delta = ((((a - b) % 360.0f) + 540.0f) % 360.0f) - 180.0f;
        return Math.abs(delta);
    }
}
