package com.example.wayfindr.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GarageModels.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b,\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0011\u001a\u00020\f¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\u0007HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0007HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\fHÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\u0007HÆ\u0003J\t\u00106\u001a\u00020\fHÆ\u0003J\u008b\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\fHÆ\u0001J\u0013\u00108\u001a\u0002092\b\u0010:\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010;\u001a\u00020<HÖ\u0001J\t\u0010=\u001a\u00020\fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0015R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\r\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0019R\u0011\u0010\u000f\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0011\u0010$\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b%\u0010\u0019R\u0011\u0010&\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b'\u0010\u0019R\u0011\u0010(\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b)\u0010\u0019¨\u0006>"}, d2 = {"Lcom/example/wayfindr/data/Ride;", "", "id", "", "startMs", "endMs", "distanceMeters", "", "durationSec", "avgSpeedMps", "maxSpeedMps", "trackPolyline", "", "startLat", "startLng", "endLat", "endLng", "sid", "<init>", "(JJJDJDDLjava/lang/String;DDDDLjava/lang/String;)V", "getId", "()J", "getStartMs", "getEndMs", "getDistanceMeters", "()D", "getDurationSec", "getAvgSpeedMps", "getMaxSpeedMps", "getTrackPolyline", "()Ljava/lang/String;", "getStartLat", "getStartLng", "getEndLat", "getEndLng", "getSid", "avgSpeedKmh", "getAvgSpeedKmh", "maxSpeedKmh", "getMaxSpeedKmh", "distanceKm", "getDistanceKm", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class Ride {
    public static final int $stable = 0;
    private final double avgSpeedMps;
    private final double distanceMeters;
    private final long durationSec;
    private final double endLat;
    private final double endLng;
    private final long endMs;
    private final long id;
    private final double maxSpeedMps;
    private final String sid;
    private final double startLat;
    private final double startLng;
    private final long startMs;
    private final String trackPolyline;

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final double getStartLng() {
        return this.startLng;
    }

    /* renamed from: component11, reason: from getter */
    public final double getEndLat() {
        return this.endLat;
    }

    /* renamed from: component12, reason: from getter */
    public final double getEndLng() {
        return this.endLng;
    }

    /* renamed from: component13, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    /* renamed from: component2, reason: from getter */
    public final long getStartMs() {
        return this.startMs;
    }

    /* renamed from: component3, reason: from getter */
    public final long getEndMs() {
        return this.endMs;
    }

    /* renamed from: component4, reason: from getter */
    public final double getDistanceMeters() {
        return this.distanceMeters;
    }

    /* renamed from: component5, reason: from getter */
    public final long getDurationSec() {
        return this.durationSec;
    }

    /* renamed from: component6, reason: from getter */
    public final double getAvgSpeedMps() {
        return this.avgSpeedMps;
    }

    /* renamed from: component7, reason: from getter */
    public final double getMaxSpeedMps() {
        return this.maxSpeedMps;
    }

    /* renamed from: component8, reason: from getter */
    public final String getTrackPolyline() {
        return this.trackPolyline;
    }

    /* renamed from: component9, reason: from getter */
    public final double getStartLat() {
        return this.startLat;
    }

    public final Ride copy(long id, long startMs, long endMs, double distanceMeters, long durationSec, double avgSpeedMps, double maxSpeedMps, String trackPolyline, double startLat, double startLng, double endLat, double endLng, String sid) {
        Intrinsics.checkNotNullParameter(trackPolyline, "trackPolyline");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new Ride(id, startMs, endMs, distanceMeters, durationSec, avgSpeedMps, maxSpeedMps, trackPolyline, startLat, startLng, endLat, endLng, sid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Ride)) {
            return false;
        }
        Ride ride = (Ride) other;
        return this.id == ride.id && this.startMs == ride.startMs && this.endMs == ride.endMs && Double.compare(this.distanceMeters, ride.distanceMeters) == 0 && this.durationSec == ride.durationSec && Double.compare(this.avgSpeedMps, ride.avgSpeedMps) == 0 && Double.compare(this.maxSpeedMps, ride.maxSpeedMps) == 0 && Intrinsics.areEqual(this.trackPolyline, ride.trackPolyline) && Double.compare(this.startLat, ride.startLat) == 0 && Double.compare(this.startLng, ride.startLng) == 0 && Double.compare(this.endLat, ride.endLat) == 0 && Double.compare(this.endLng, ride.endLng) == 0 && Intrinsics.areEqual(this.sid, ride.sid);
    }

    public int hashCode() {
        return (((((((((((((((((((((((Long.hashCode(this.id) * 31) + Long.hashCode(this.startMs)) * 31) + Long.hashCode(this.endMs)) * 31) + Double.hashCode(this.distanceMeters)) * 31) + Long.hashCode(this.durationSec)) * 31) + Double.hashCode(this.avgSpeedMps)) * 31) + Double.hashCode(this.maxSpeedMps)) * 31) + this.trackPolyline.hashCode()) * 31) + Double.hashCode(this.startLat)) * 31) + Double.hashCode(this.startLng)) * 31) + Double.hashCode(this.endLat)) * 31) + Double.hashCode(this.endLng)) * 31) + this.sid.hashCode();
    }

    public String toString() {
        return "Ride(id=" + this.id + ", startMs=" + this.startMs + ", endMs=" + this.endMs + ", distanceMeters=" + this.distanceMeters + ", durationSec=" + this.durationSec + ", avgSpeedMps=" + this.avgSpeedMps + ", maxSpeedMps=" + this.maxSpeedMps + ", trackPolyline=" + this.trackPolyline + ", startLat=" + this.startLat + ", startLng=" + this.startLng + ", endLat=" + this.endLat + ", endLng=" + this.endLng + ", sid=" + this.sid + ")";
    }

    public Ride(long id, long startMs, long endMs, double distanceMeters, long durationSec, double avgSpeedMps, double maxSpeedMps, String trackPolyline, double startLat, double startLng, double endLat, double endLng, String sid) {
        Intrinsics.checkNotNullParameter(trackPolyline, "trackPolyline");
        Intrinsics.checkNotNullParameter(sid, "sid");
        this.id = id;
        this.startMs = startMs;
        this.endMs = endMs;
        this.distanceMeters = distanceMeters;
        this.durationSec = durationSec;
        this.avgSpeedMps = avgSpeedMps;
        this.maxSpeedMps = maxSpeedMps;
        this.trackPolyline = trackPolyline;
        this.startLat = startLat;
        this.startLng = startLng;
        this.endLat = endLat;
        this.endLng = endLng;
        this.sid = sid;
    }

    public /* synthetic */ Ride(long j, long j2, long j3, double d, long j4, double d2, double d3, String str, double d4, double d5, double d6, double d7, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, j2, j3, d, j4, d2, d3, (i & 128) != 0 ? "" : str, (i & 256) != 0 ? 0.0d : d4, (i & 512) != 0 ? 0.0d : d5, (i & 1024) != 0 ? 0.0d : d6, (i & 2048) != 0 ? 0.0d : d7, (i & 4096) != 0 ? "" : str2);
    }

    public final long getId() {
        return this.id;
    }

    public final long getStartMs() {
        return this.startMs;
    }

    public final long getEndMs() {
        return this.endMs;
    }

    public final double getDistanceMeters() {
        return this.distanceMeters;
    }

    public final long getDurationSec() {
        return this.durationSec;
    }

    public final double getAvgSpeedMps() {
        return this.avgSpeedMps;
    }

    public final double getMaxSpeedMps() {
        return this.maxSpeedMps;
    }

    public final String getTrackPolyline() {
        return this.trackPolyline;
    }

    public final double getStartLat() {
        return this.startLat;
    }

    public final double getStartLng() {
        return this.startLng;
    }

    public final double getEndLat() {
        return this.endLat;
    }

    public final double getEndLng() {
        return this.endLng;
    }

    public final String getSid() {
        return this.sid;
    }

    public final double getAvgSpeedKmh() {
        return this.avgSpeedMps * 3.6d;
    }

    public final double getMaxSpeedKmh() {
        return this.maxSpeedMps * 3.6d;
    }

    public final double getDistanceKm() {
        return this.distanceMeters / 1000.0d;
    }
}
