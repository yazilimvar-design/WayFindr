package com.example.wayfindr.data;

import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.PolylineCodec;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* compiled from: RideRecorder.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class RideRecorder {
    private static final float ACC_GATE_M = 20.0f;
    private static final double MIN_MOVE_M = 8.0d;
    private static final double MIN_RIDE_M = 150.0d;
    private static final float STILL_SPEED = 0.7f;
    private double distanceM;
    private GeoPoint last;
    private long lastMs;
    private double maxSpeed;
    private final ArrayList points = new ArrayList();
    private boolean recording;
    private long startMs;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int $stable = 8;

    public final void add(double d, double d2, float f, float f2, long j) {
        if (this.recording && f2 <= ACC_GATE_M) {
            GeoPoint geoPoint = new GeoPoint(d, d2);
            GeoPoint geoPoint2 = this.last;
            if (geoPoint2 == null) {
                this.points.add(geoPoint);
                NavPlus.rsAdd(f);
                this.last = geoPoint;
                this.lastMs = j;
                return;
            }
            double distMeters = GeoPoint.Companion.distMeters(geoPoint2, geoPoint);
            if (distMeters < MIN_MOVE_M) {
                return;
            }
            if (f >= STILL_SPEED) {
                this.distanceM += distMeters;
            }
            if (f > this.maxSpeed) {
                this.maxSpeed = f;
            }
            this.points.add(geoPoint);
            NavPlus.rsAdd(f);
            this.last = geoPoint;
            this.lastMs = j;
        }
    }

    public final double getDistanceSoFarM() {
        return this.distanceM;
    }

    public final boolean isRecording() {
        return this.recording;
    }

    public final void start() {
        NavPlus.rsClear();
        this.points.clear();
        this.distanceM = 0.0d;
        this.maxSpeed = 0.0d;
        this.last = null;
        this.startMs = System.currentTimeMillis();
        this.lastMs = this.startMs;
        this.recording = true;
    }

    public final Ride stop() {
        NavPlus.rsSave(this.points);
        this.recording = false;
        long currentTimeMillis = System.currentTimeMillis();
        long coerceAtLeast = RangesKt.coerceAtLeast((currentTimeMillis - this.startMs) / 1000, 0L);
        if (this.points.size() < 2 || this.distanceM < MIN_RIDE_M) {
            return null;
        }
        double d = coerceAtLeast > 0 ? this.distanceM / coerceAtLeast : 0.0d;
        GeoPoint geoPoint = (GeoPoint) CollectionsKt.first(this.points);
        GeoPoint geoPoint2 = (GeoPoint) CollectionsKt.last(this.points);
        return new Ride(0L, this.startMs, currentTimeMillis, this.distanceM, coerceAtLeast, d, this.maxSpeed, PolylineCodec.encode$default(PolylineCodec.INSTANCE, this.points, 0, 2, (Object) null), geoPoint.getLat(), geoPoint.getLng(), geoPoint2.getLat(), geoPoint2.getLng(), WayfindrDb.Companion.newSid(), 1, (DefaultConstructorMarker) null);
    }
}
