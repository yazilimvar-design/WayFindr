package com.example.wayfindr.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GarageModels.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BE\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003J\t\u0010 \u001a\u00020\u000bHÆ\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\tHÖ\u0001J\t\u0010&\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018¨\u0006'"}, d2 = {"Lcom/example/wayfindr/data/FuelFillup;", "", "id", "", "dateMs", "litres", "", "cost", "odometerKm", "", "location", "", "sid", "<init>", "(JJDDILjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getDateMs", "getLitres", "()D", "getCost", "getOdometerKm", "()I", "getLocation", "()Ljava/lang/String;", "getSid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class FuelFillup {
    public static final int $stable = 0;
    private final double cost;
    private final long dateMs;
    private final long id;
    private final double litres;
    private final String location;
    private final int odometerKm;
    private final String sid;

    public static /* synthetic */ FuelFillup copy$default(FuelFillup fuelFillup, long j, long j2, double d, double d2, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            j = fuelFillup.id;
        }
        return fuelFillup.copy(j, (i2 & 2) != 0 ? fuelFillup.dateMs : j2, (i2 & 4) != 0 ? fuelFillup.litres : d, (i2 & 8) != 0 ? fuelFillup.cost : d2, (i2 & 16) != 0 ? fuelFillup.odometerKm : i, (i2 & 32) != 0 ? fuelFillup.location : str, (i2 & 64) != 0 ? fuelFillup.sid : str2);
    }

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final long getDateMs() {
        return this.dateMs;
    }

    /* renamed from: component3, reason: from getter */
    public final double getLitres() {
        return this.litres;
    }

    /* renamed from: component4, reason: from getter */
    public final double getCost() {
        return this.cost;
    }

    /* renamed from: component5, reason: from getter */
    public final int getOdometerKm() {
        return this.odometerKm;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    /* renamed from: component7, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    public final FuelFillup copy(long id, long dateMs, double litres, double cost, int odometerKm, String location, String sid) {
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new FuelFillup(id, dateMs, litres, cost, odometerKm, location, sid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FuelFillup)) {
            return false;
        }
        FuelFillup fuelFillup = (FuelFillup) other;
        return this.id == fuelFillup.id && this.dateMs == fuelFillup.dateMs && Double.compare(this.litres, fuelFillup.litres) == 0 && Double.compare(this.cost, fuelFillup.cost) == 0 && this.odometerKm == fuelFillup.odometerKm && Intrinsics.areEqual(this.location, fuelFillup.location) && Intrinsics.areEqual(this.sid, fuelFillup.sid);
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + Long.hashCode(this.dateMs)) * 31) + Double.hashCode(this.litres)) * 31) + Double.hashCode(this.cost)) * 31) + Integer.hashCode(this.odometerKm)) * 31) + this.location.hashCode()) * 31) + this.sid.hashCode();
    }

    public String toString() {
        return "FuelFillup(id=" + this.id + ", dateMs=" + this.dateMs + ", litres=" + this.litres + ", cost=" + this.cost + ", odometerKm=" + this.odometerKm + ", location=" + this.location + ", sid=" + this.sid + ")";
    }

    public FuelFillup(long id, long dateMs, double litres, double cost, int odometerKm, String location, String sid) {
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(sid, "sid");
        this.id = id;
        this.dateMs = dateMs;
        this.litres = litres;
        this.cost = cost;
        this.odometerKm = odometerKm;
        this.location = location;
        this.sid = sid;
    }

    public /* synthetic */ FuelFillup(long j, long j2, double d, double d2, int i, String str, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j, j2, d, d2, i, (i2 & 32) != 0 ? "" : str, (i2 & 64) != 0 ? "" : str2);
    }

    public final long getId() {
        return this.id;
    }

    public final long getDateMs() {
        return this.dateMs;
    }

    public final double getLitres() {
        return this.litres;
    }

    public final double getCost() {
        return this.cost;
    }

    public final int getOdometerKm() {
        return this.odometerKm;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getSid() {
        return this.sid;
    }
}
