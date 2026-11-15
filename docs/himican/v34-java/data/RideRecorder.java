package com.example.wayfindr.data;

import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.PolylineCodec;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.RangesKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RideRecorder.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 !2\u00020\u0001:\u0001!B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0016\u001a\u00020\u0017J.\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\tJ\b\u0010\u001f\u001a\u0004\u0018\u00010 R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/example/wayfindr/data/RideRecorder;", "", "<init>", "()V", "points", "Ljava/util/ArrayList;", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "Lkotlin/collections/ArrayList;", "startMs", "", "lastMs", "distanceM", "", "maxSpeed", "last", "recording", "", "isRecording", "()Z", "distanceSoFarM", "getDistanceSoFarM", "()D", "start", "", "add", "lat", "lng", "speedMps", "", "accuracyM", "timeMs", "stop", "Lcom/example/wayfindr/data/Ride;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RideRecorder {
    private static final float ACC_GATE_M = 20.0f;
    private static final double MIN_MOVE_M = 8.0d;
    private static final double MIN_RIDE_M = 150.0d;
    private static final float STILL_SPEED = 0.7f;
    private double distanceM;
    private GeoPoint last;
    private long lastMs;
    private double maxSpeed;
    private final ArrayList<GeoPoint> points = new ArrayList<>();
    private boolean recording;
    private long startMs;
    public static final int $stable = 8;

    /* renamed from: isRecording, reason: from getter */
    public final boolean getRecording() {
        return this.recording;
    }

    /* renamed from: getDistanceSoFarM, reason: from getter */
    public final double getDistanceM() {
        return this.distanceM;
    }

    public final void start() {
        this.points.clear();
        this.distanceM = 0.0d;
        this.maxSpeed = 0.0d;
        this.last = null;
        this.startMs = System.currentTimeMillis();
        this.lastMs = this.startMs;
        this.recording = true;
    }

    public final void add(double lat, double lng, float speedMps, float accuracyM, long timeMs) {
        if (this.recording && accuracyM <= 20.0f) {
            GeoPoint p = new GeoPoint(lat, lng);
            GeoPoint prev = this.last;
            if (prev == null) {
                this.points.add(p);
                this.last = p;
                this.lastMs = timeMs;
                return;
            }
            double step = GeoPoint.INSTANCE.distMeters(prev, p);
            if (step < MIN_MOVE_M) {
                return;
            }
            if (speedMps >= STILL_SPEED) {
                this.distanceM += step;
            }
            if (speedMps > this.maxSpeed) {
                this.maxSpeed = speedMps;
            }
            this.points.add(p);
            this.last = p;
            this.lastMs = timeMs;
        }
    }

    public final Ride stop() {
        this.recording = false;
        long end = System.currentTimeMillis();
        long durationS = RangesKt.coerceAtLeast((end - this.startMs) / 1000, 0L);
        if (this.points.size() < 2 || this.distanceM < 150.0d) {
            return null;
        }
        double avg = durationS > 0 ? this.distanceM / durationS : 0.0d;
        GeoPoint first = (GeoPoint) CollectionsKt.first((List) this.points);
        GeoPoint lastPt = (GeoPoint) CollectionsKt.last((List) this.points);
        return new Ride(0L, this.startMs, end, this.distanceM, durationS, avg, this.maxSpeed, PolylineCodec.encode$default(PolylineCodec.INSTANCE, this.points, 0, 2, null), first.getLat(), first.getLng(), lastPt.getLat(), lastPt.getLng(), WayfindrDb.INSTANCE.newSid(), 1, null);
    }
}
