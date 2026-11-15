package com.example.wayfindr.dash.nav;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RouteProgressMatcher.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0015\u001a\u00020\bHÆ\u0003J1\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/example/wayfindr/dash/nav/RouteProgressMatch;", "", "progressMeters", "", "lateralDistanceMeters", "headingDegrees", "", "snappedPoint", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "<init>", "(DDFLcom/example/wayfindr/dash/nav/GeoPoint;)V", "getProgressMeters", "()D", "getLateralDistanceMeters", "getHeadingDegrees", "()F", "getSnappedPoint", "()Lcom/example/wayfindr/dash/nav/GeoPoint;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class RouteProgressMatch {
    public static final int $stable = 0;
    private final float headingDegrees;
    private final double lateralDistanceMeters;
    private final double progressMeters;
    private final GeoPoint snappedPoint;

    public static /* synthetic */ RouteProgressMatch copy$default(RouteProgressMatch routeProgressMatch, double d, double d2, float f, GeoPoint geoPoint, int i, Object obj) {
        if ((i & 1) != 0) {
            d = routeProgressMatch.progressMeters;
        }
        double d3 = d;
        if ((i & 2) != 0) {
            d2 = routeProgressMatch.lateralDistanceMeters;
        }
        double d4 = d2;
        if ((i & 4) != 0) {
            f = routeProgressMatch.headingDegrees;
        }
        float f2 = f;
        if ((i & 8) != 0) {
            geoPoint = routeProgressMatch.snappedPoint;
        }
        return routeProgressMatch.copy(d3, d4, f2, geoPoint);
    }

    /* renamed from: component1, reason: from getter */
    public final double getProgressMeters() {
        return this.progressMeters;
    }

    /* renamed from: component2, reason: from getter */
    public final double getLateralDistanceMeters() {
        return this.lateralDistanceMeters;
    }

    /* renamed from: component3, reason: from getter */
    public final float getHeadingDegrees() {
        return this.headingDegrees;
    }

    /* renamed from: component4, reason: from getter */
    public final GeoPoint getSnappedPoint() {
        return this.snappedPoint;
    }

    public final RouteProgressMatch copy(double progressMeters, double lateralDistanceMeters, float headingDegrees, GeoPoint snappedPoint) {
        Intrinsics.checkNotNullParameter(snappedPoint, "snappedPoint");
        return new RouteProgressMatch(progressMeters, lateralDistanceMeters, headingDegrees, snappedPoint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RouteProgressMatch)) {
            return false;
        }
        RouteProgressMatch routeProgressMatch = (RouteProgressMatch) other;
        return Double.compare(this.progressMeters, routeProgressMatch.progressMeters) == 0 && Double.compare(this.lateralDistanceMeters, routeProgressMatch.lateralDistanceMeters) == 0 && Float.compare(this.headingDegrees, routeProgressMatch.headingDegrees) == 0 && Intrinsics.areEqual(this.snappedPoint, routeProgressMatch.snappedPoint);
    }

    public int hashCode() {
        return (((((Double.hashCode(this.progressMeters) * 31) + Double.hashCode(this.lateralDistanceMeters)) * 31) + Float.hashCode(this.headingDegrees)) * 31) + this.snappedPoint.hashCode();
    }

    public String toString() {
        return "RouteProgressMatch(progressMeters=" + this.progressMeters + ", lateralDistanceMeters=" + this.lateralDistanceMeters + ", headingDegrees=" + this.headingDegrees + ", snappedPoint=" + this.snappedPoint + ")";
    }

    public RouteProgressMatch(double progressMeters, double lateralDistanceMeters, float headingDegrees, GeoPoint snappedPoint) {
        Intrinsics.checkNotNullParameter(snappedPoint, "snappedPoint");
        this.progressMeters = progressMeters;
        this.lateralDistanceMeters = lateralDistanceMeters;
        this.headingDegrees = headingDegrees;
        this.snappedPoint = snappedPoint;
    }

    public final double getProgressMeters() {
        return this.progressMeters;
    }

    public final double getLateralDistanceMeters() {
        return this.lateralDistanceMeters;
    }

    public final float getHeadingDegrees() {
        return this.headingDegrees;
    }

    public final GeoPoint getSnappedPoint() {
        return this.snappedPoint;
    }
}
