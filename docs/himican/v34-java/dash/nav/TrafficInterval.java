package com.example.wayfindr.dash.nav;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Route.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/example/wayfindr/dash/nav/TrafficInterval;", "", "startPointIndex", "", "endPointIndex", "speed", "Lcom/example/wayfindr/dash/nav/TrafficSpeed;", "<init>", "(IILcom/example/wayfindr/dash/nav/TrafficSpeed;)V", "getStartPointIndex", "()I", "getEndPointIndex", "getSpeed", "()Lcom/example/wayfindr/dash/nav/TrafficSpeed;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class TrafficInterval {
    public static final int $stable = 0;
    private final int endPointIndex;
    private final TrafficSpeed speed;
    private final int startPointIndex;

    public static /* synthetic */ TrafficInterval copy$default(TrafficInterval trafficInterval, int i, int i2, TrafficSpeed trafficSpeed, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = trafficInterval.startPointIndex;
        }
        if ((i3 & 2) != 0) {
            i2 = trafficInterval.endPointIndex;
        }
        if ((i3 & 4) != 0) {
            trafficSpeed = trafficInterval.speed;
        }
        return trafficInterval.copy(i, i2, trafficSpeed);
    }

    /* renamed from: component1, reason: from getter */
    public final int getStartPointIndex() {
        return this.startPointIndex;
    }

    /* renamed from: component2, reason: from getter */
    public final int getEndPointIndex() {
        return this.endPointIndex;
    }

    /* renamed from: component3, reason: from getter */
    public final TrafficSpeed getSpeed() {
        return this.speed;
    }

    public final TrafficInterval copy(int startPointIndex, int endPointIndex, TrafficSpeed speed) {
        Intrinsics.checkNotNullParameter(speed, "speed");
        return new TrafficInterval(startPointIndex, endPointIndex, speed);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrafficInterval)) {
            return false;
        }
        TrafficInterval trafficInterval = (TrafficInterval) other;
        return this.startPointIndex == trafficInterval.startPointIndex && this.endPointIndex == trafficInterval.endPointIndex && this.speed == trafficInterval.speed;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.startPointIndex) * 31) + Integer.hashCode(this.endPointIndex)) * 31) + this.speed.hashCode();
    }

    public String toString() {
        return "TrafficInterval(startPointIndex=" + this.startPointIndex + ", endPointIndex=" + this.endPointIndex + ", speed=" + this.speed + ")";
    }

    public TrafficInterval(int startPointIndex, int endPointIndex, TrafficSpeed speed) {
        Intrinsics.checkNotNullParameter(speed, "speed");
        this.startPointIndex = startPointIndex;
        this.endPointIndex = endPointIndex;
        this.speed = speed;
    }

    public final int getStartPointIndex() {
        return this.startPointIndex;
    }

    public final int getEndPointIndex() {
        return this.endPointIndex;
    }

    public final TrafficSpeed getSpeed() {
        return this.speed;
    }
}
