package com.example.wayfindr.data;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GarageModels.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u001b\b\u0087\b\u0018\u00002\u00020\u0001BG\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003JO\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\bHÆ\u0001J\u0013\u0010\"\u001a\u00020\u000b2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\bHÖ\u0001J\t\u0010%\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\f\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015¨\u0006&"}, d2 = {"Lcom/example/wayfindr/data/ScheduledService;", "", "id", "", "sid", "", "label", "targetKm", "", "targetMonths", "free", "", "orderIdx", "<init>", "(JLjava/lang/String;Ljava/lang/String;IIZI)V", "getId", "()J", "getSid", "()Ljava/lang/String;", "getLabel", "getTargetKm", "()I", "getTargetMonths", "getFree", "()Z", "getOrderIdx", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class ScheduledService {
    public static final int $stable = 0;
    private final boolean free;
    private final long id;
    private final String label;
    private final int orderIdx;
    private final String sid;
    private final int targetKm;
    private final int targetMonths;

    public static /* synthetic */ ScheduledService copy$default(ScheduledService scheduledService, long j, String str, String str2, int i, int i2, boolean z, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            j = scheduledService.id;
        }
        long j2 = j;
        if ((i4 & 2) != 0) {
            str = scheduledService.sid;
        }
        String str3 = str;
        if ((i4 & 4) != 0) {
            str2 = scheduledService.label;
        }
        String str4 = str2;
        if ((i4 & 8) != 0) {
            i = scheduledService.targetKm;
        }
        int i5 = i;
        if ((i4 & 16) != 0) {
            i2 = scheduledService.targetMonths;
        }
        return scheduledService.copy(j2, str3, str4, i5, i2, (i4 & 32) != 0 ? scheduledService.free : z, (i4 & 64) != 0 ? scheduledService.orderIdx : i3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component4, reason: from getter */
    public final int getTargetKm() {
        return this.targetKm;
    }

    /* renamed from: component5, reason: from getter */
    public final int getTargetMonths() {
        return this.targetMonths;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getFree() {
        return this.free;
    }

    /* renamed from: component7, reason: from getter */
    public final int getOrderIdx() {
        return this.orderIdx;
    }

    public final ScheduledService copy(long id, String sid, String label, int targetKm, int targetMonths, boolean free, int orderIdx) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(label, "label");
        return new ScheduledService(id, sid, label, targetKm, targetMonths, free, orderIdx);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScheduledService)) {
            return false;
        }
        ScheduledService scheduledService = (ScheduledService) other;
        return this.id == scheduledService.id && Intrinsics.areEqual(this.sid, scheduledService.sid) && Intrinsics.areEqual(this.label, scheduledService.label) && this.targetKm == scheduledService.targetKm && this.targetMonths == scheduledService.targetMonths && this.free == scheduledService.free && this.orderIdx == scheduledService.orderIdx;
    }

    public int hashCode() {
        return (((((((((((Long.hashCode(this.id) * 31) + this.sid.hashCode()) * 31) + this.label.hashCode()) * 31) + Integer.hashCode(this.targetKm)) * 31) + Integer.hashCode(this.targetMonths)) * 31) + Boolean.hashCode(this.free)) * 31) + Integer.hashCode(this.orderIdx);
    }

    public String toString() {
        return "ScheduledService(id=" + this.id + ", sid=" + this.sid + ", label=" + this.label + ", targetKm=" + this.targetKm + ", targetMonths=" + this.targetMonths + ", free=" + this.free + ", orderIdx=" + this.orderIdx + ")";
    }

    public ScheduledService(long id, String sid, String label, int targetKm, int targetMonths, boolean free, int orderIdx) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(label, "label");
        this.id = id;
        this.sid = sid;
        this.label = label;
        this.targetKm = targetKm;
        this.targetMonths = targetMonths;
        this.free = free;
        this.orderIdx = orderIdx;
    }

    public /* synthetic */ ScheduledService(long j, String str, String str2, int i, int i2, boolean z, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0L : j, (i4 & 2) != 0 ? "" : str, str2, i, i2, (i4 & 32) != 0 ? false : z, (i4 & 64) != 0 ? 0 : i3);
    }

    public final long getId() {
        return this.id;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getLabel() {
        return this.label;
    }

    public final int getTargetKm() {
        return this.targetKm;
    }

    public final int getTargetMonths() {
        return this.targetMonths;
    }

    public final boolean getFree() {
        return this.free;
    }

    public final int getOrderIdx() {
        return this.orderIdx;
    }
}
