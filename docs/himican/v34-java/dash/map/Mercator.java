package com.example.wayfindr.dash.map;

import kotlin.Metadata;

/* compiled from: Mercator.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005J\u0016\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005J&\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/dash/map/Mercator;", "", "<init>", "()V", "TILE_SIZE", "", "lngToTileX", "", "lng", "z", "latToTileY", "lat", "tileXToLng", "x", "tileYToLat", "y", "haversineKm", "lat1", "lng1", "lat2", "lng2", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class Mercator {
    public static final int $stable = 0;
    public static final Mercator INSTANCE = new Mercator();
    public static final int TILE_SIZE = 256;

    private Mercator() {
    }

    public final double lngToTileX(double lng, int z) {
        return ((180.0d + lng) / 360.0d) * (1 << z);
    }

    public final double latToTileY(double lat, int z) {
        double r = Math.toRadians(lat);
        return ((1.0d - (Math.log(Math.tan(r) + (1.0d / Math.cos(r))) / 3.141592653589793d)) / 2.0d) * (1 << z);
    }

    public final double tileXToLng(double x, int z) {
        return ((x / (1 << z)) * 360.0d) - 180.0d;
    }

    public final double tileYToLat(double y, int z) {
        return Math.toDegrees(Math.atan(Math.sinh((1.0d - ((2.0d * y) / (1 << z))) * 3.141592653589793d)));
    }

    public final double haversineKm(double lat1, double lng1, double lat2, double lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = (Math.sin(dLat / 2.0d) * Math.sin(dLat / 2.0d)) + (Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLng / 2.0d) * Math.sin(dLng / 2.0d));
        return 2.0d * 6371.0d * Math.atan(Math.sqrt(a) / Math.sqrt(1.0d - a));
    }
}
