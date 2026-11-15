package com.example.wayfindr.dash.nav;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: GeoPoint.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/dash/nav/GeoPoint;", "", "lat", "", "lng", "<init>", "(DD)V", "getLat", "()D", "getLng", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class GeoPoint {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final double EARTH_KM = 6371.0d;
    private final double lat;
    private final double lng;

    public static /* synthetic */ GeoPoint copy$default(GeoPoint geoPoint, double d, double d2, int i, Object obj) {
        if ((i & 1) != 0) {
            d = geoPoint.lat;
        }
        if ((i & 2) != 0) {
            d2 = geoPoint.lng;
        }
        return geoPoint.copy(d, d2);
    }

    /* renamed from: component1, reason: from getter */
    public final double getLat() {
        return this.lat;
    }

    /* renamed from: component2, reason: from getter */
    public final double getLng() {
        return this.lng;
    }

    public final GeoPoint copy(double lat, double lng) {
        return new GeoPoint(lat, lng);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GeoPoint)) {
            return false;
        }
        GeoPoint geoPoint = (GeoPoint) other;
        return Double.compare(this.lat, geoPoint.lat) == 0 && Double.compare(this.lng, geoPoint.lng) == 0;
    }

    public int hashCode() {
        return (Double.hashCode(this.lat) * 31) + Double.hashCode(this.lng);
    }

    public String toString() {
        return "GeoPoint(lat=" + this.lat + ", lng=" + this.lng + ")";
    }

    public GeoPoint(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public final double getLat() {
        return this.lat;
    }

    public final double getLng() {
        return this.lng;
    }

    /* compiled from: GeoPoint.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ*\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/example/wayfindr/dash/nav/GeoPoint$Companion;", "", "<init>", "()V", "EARTH_KM", "", "distMeters", "a", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "b", "bearing", "projectOnSegment", "Lkotlin/Pair;", "p", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final double distMeters(GeoPoint a, GeoPoint b) {
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            double dLat = Math.toRadians(b.getLat() - a.getLat());
            double dLng = Math.toRadians(b.getLng() - a.getLng());
            double s = (Math.sin(dLat / 2.0d) * Math.sin(dLat / 2.0d)) + (Math.cos(Math.toRadians(a.getLat())) * Math.cos(Math.toRadians(b.getLat())) * Math.sin(dLng / 2.0d) * Math.sin(dLng / 2.0d));
            return Math.atan2(Math.sqrt(s), Math.sqrt(1.0d - s)) * 1.2742E7d;
        }

        public final double bearing(GeoPoint a, GeoPoint b) {
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            double lat1 = Math.toRadians(a.getLat());
            double lat2 = Math.toRadians(b.getLat());
            double dLng = Math.toRadians(b.getLng() - a.getLng());
            double y = Math.sin(dLng) * Math.cos(lat2);
            double x = (Math.cos(lat1) * Math.sin(lat2)) - ((Math.sin(lat1) * Math.cos(lat2)) * Math.cos(dLng));
            return (Math.toDegrees(Math.atan2(y, x)) + 360.0d) % 360.0d;
        }

        public final Pair<GeoPoint, Double> projectOnSegment(GeoPoint p, GeoPoint a, GeoPoint b) {
            Intrinsics.checkNotNullParameter(p, "p");
            Intrinsics.checkNotNullParameter(a, "a");
            Intrinsics.checkNotNullParameter(b, "b");
            double latRef = Math.toRadians(a.getLat());
            double ax = projectOnSegment$x(latRef, a);
            double ay = projectOnSegment$y(a);
            double bx = projectOnSegment$x(latRef, b);
            double by = projectOnSegment$y(b);
            double px = projectOnSegment$x(latRef, p);
            double py = projectOnSegment$y(p);
            double dx = bx - ax;
            double dy = by - ay;
            double len2 = (dx * dx) + (dy * dy);
            double t = (len2 > 0.0d ? 1 : (len2 == 0.0d ? 0 : -1)) == 0 ? 0.0d : RangesKt.coerceIn((((px - ax) * dx) + ((py - ay) * dy)) / len2, 0.0d, 1.0d);
            GeoPoint proj = new GeoPoint(a.getLat() + ((b.getLat() - a.getLat()) * t), a.getLng() + ((b.getLng() - a.getLng()) * t));
            return TuplesKt.to(proj, Double.valueOf(t));
        }

        private static final double projectOnSegment$x(double latRef, GeoPoint g) {
            return Math.toRadians(g.getLng()) * Math.cos(latRef);
        }

        private static final double projectOnSegment$y(GeoPoint g) {
            return Math.toRadians(g.getLat());
        }
    }
}
