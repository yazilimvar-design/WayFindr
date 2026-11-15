package com.example.wayfindr.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GarageModels.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B9\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/example/wayfindr/data/BikeIdentity;", "", "vin", "", "engineNo", "regNo", "purchaseMs", "", "colour", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;)V", "getVin", "()Ljava/lang/String;", "getEngineNo", "getRegNo", "getPurchaseMs", "()J", "getColour", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class BikeIdentity {
    public static final int $stable = 0;
    private final String colour;
    private final String engineNo;
    private final long purchaseMs;
    private final String regNo;
    private final String vin;

    public BikeIdentity() {
        this(null, null, null, 0L, null, 31, null);
    }

    public static /* synthetic */ BikeIdentity copy$default(BikeIdentity bikeIdentity, String str, String str2, String str3, long j, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = bikeIdentity.vin;
        }
        if ((i & 2) != 0) {
            str2 = bikeIdentity.engineNo;
        }
        if ((i & 4) != 0) {
            str3 = bikeIdentity.regNo;
        }
        if ((i & 8) != 0) {
            j = bikeIdentity.purchaseMs;
        }
        if ((i & 16) != 0) {
            str4 = bikeIdentity.colour;
        }
        String str5 = str4;
        String str6 = str3;
        return bikeIdentity.copy(str, str2, str6, j, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getVin() {
        return this.vin;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEngineNo() {
        return this.engineNo;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRegNo() {
        return this.regNo;
    }

    /* renamed from: component4, reason: from getter */
    public final long getPurchaseMs() {
        return this.purchaseMs;
    }

    /* renamed from: component5, reason: from getter */
    public final String getColour() {
        return this.colour;
    }

    public final BikeIdentity copy(String vin, String engineNo, String regNo, long purchaseMs, String colour) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(engineNo, "engineNo");
        Intrinsics.checkNotNullParameter(regNo, "regNo");
        Intrinsics.checkNotNullParameter(colour, "colour");
        return new BikeIdentity(vin, engineNo, regNo, purchaseMs, colour);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BikeIdentity)) {
            return false;
        }
        BikeIdentity bikeIdentity = (BikeIdentity) other;
        return Intrinsics.areEqual(this.vin, bikeIdentity.vin) && Intrinsics.areEqual(this.engineNo, bikeIdentity.engineNo) && Intrinsics.areEqual(this.regNo, bikeIdentity.regNo) && this.purchaseMs == bikeIdentity.purchaseMs && Intrinsics.areEqual(this.colour, bikeIdentity.colour);
    }

    public int hashCode() {
        return (((((((this.vin.hashCode() * 31) + this.engineNo.hashCode()) * 31) + this.regNo.hashCode()) * 31) + Long.hashCode(this.purchaseMs)) * 31) + this.colour.hashCode();
    }

    public String toString() {
        return "BikeIdentity(vin=" + this.vin + ", engineNo=" + this.engineNo + ", regNo=" + this.regNo + ", purchaseMs=" + this.purchaseMs + ", colour=" + this.colour + ")";
    }

    public BikeIdentity(String vin, String engineNo, String regNo, long purchaseMs, String colour) {
        Intrinsics.checkNotNullParameter(vin, "vin");
        Intrinsics.checkNotNullParameter(engineNo, "engineNo");
        Intrinsics.checkNotNullParameter(regNo, "regNo");
        Intrinsics.checkNotNullParameter(colour, "colour");
        this.vin = vin;
        this.engineNo = engineNo;
        this.regNo = regNo;
        this.purchaseMs = purchaseMs;
        this.colour = colour;
    }

    public /* synthetic */ BikeIdentity(String str, String str2, String str3, long j, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? 0L : j, (i & 16) != 0 ? "" : str4);
    }

    public final String getVin() {
        return this.vin;
    }

    public final String getEngineNo() {
        return this.engineNo;
    }

    public final String getRegNo() {
        return this.regNo;
    }

    public final long getPurchaseMs() {
        return this.purchaseMs;
    }

    public final String getColour() {
        return this.colour;
    }
}
