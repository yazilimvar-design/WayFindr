package com.example.wayfindr.data;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GarageModels.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\t\u0010\u001e\u001a\u00020\bHÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\b2\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\bHÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012¨\u0006("}, d2 = {"Lcom/example/wayfindr/data/MaintenanceItem;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "", "iconKey", "intervalKm", "", "lastDoneOdoKm", "lastDoneDateMs", "intervalMonths", "sid", "<init>", "(JLjava/lang/String;Ljava/lang/String;IIJILjava/lang/String;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getIconKey", "getIntervalKm", "()I", "getLastDoneOdoKm", "getLastDoneDateMs", "getIntervalMonths", "getSid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class MaintenanceItem {
    public static final int $stable = 0;
    private final String iconKey;
    private final long id;
    private final int intervalKm;
    private final int intervalMonths;
    private final long lastDoneDateMs;
    private final int lastDoneOdoKm;
    private final String name;
    private final String sid;

    public static /* synthetic */ MaintenanceItem copy$default(MaintenanceItem maintenanceItem, long j, String str, String str2, int i, int i2, long j2, int i3, String str3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            j = maintenanceItem.id;
        }
        long j3 = j;
        if ((i4 & 2) != 0) {
            str = maintenanceItem.name;
        }
        String str4 = str;
        if ((i4 & 4) != 0) {
            str2 = maintenanceItem.iconKey;
        }
        return maintenanceItem.copy(j3, str4, str2, (i4 & 8) != 0 ? maintenanceItem.intervalKm : i, (i4 & 16) != 0 ? maintenanceItem.lastDoneOdoKm : i2, (i4 & 32) != 0 ? maintenanceItem.lastDoneDateMs : j2, (i4 & 64) != 0 ? maintenanceItem.intervalMonths : i3, (i4 & 128) != 0 ? maintenanceItem.sid : str3);
    }

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getIconKey() {
        return this.iconKey;
    }

    /* renamed from: component4, reason: from getter */
    public final int getIntervalKm() {
        return this.intervalKm;
    }

    /* renamed from: component5, reason: from getter */
    public final int getLastDoneOdoKm() {
        return this.lastDoneOdoKm;
    }

    /* renamed from: component6, reason: from getter */
    public final long getLastDoneDateMs() {
        return this.lastDoneDateMs;
    }

    /* renamed from: component7, reason: from getter */
    public final int getIntervalMonths() {
        return this.intervalMonths;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    public final MaintenanceItem copy(long id, String name, String iconKey, int intervalKm, int lastDoneOdoKm, long lastDoneDateMs, int intervalMonths, String sid) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(iconKey, "iconKey");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new MaintenanceItem(id, name, iconKey, intervalKm, lastDoneOdoKm, lastDoneDateMs, intervalMonths, sid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MaintenanceItem)) {
            return false;
        }
        MaintenanceItem maintenanceItem = (MaintenanceItem) other;
        return this.id == maintenanceItem.id && Intrinsics.areEqual(this.name, maintenanceItem.name) && Intrinsics.areEqual(this.iconKey, maintenanceItem.iconKey) && this.intervalKm == maintenanceItem.intervalKm && this.lastDoneOdoKm == maintenanceItem.lastDoneOdoKm && this.lastDoneDateMs == maintenanceItem.lastDoneDateMs && this.intervalMonths == maintenanceItem.intervalMonths && Intrinsics.areEqual(this.sid, maintenanceItem.sid);
    }

    public int hashCode() {
        return (((((((((((((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + this.iconKey.hashCode()) * 31) + Integer.hashCode(this.intervalKm)) * 31) + Integer.hashCode(this.lastDoneOdoKm)) * 31) + Long.hashCode(this.lastDoneDateMs)) * 31) + Integer.hashCode(this.intervalMonths)) * 31) + this.sid.hashCode();
    }

    public String toString() {
        return "MaintenanceItem(id=" + this.id + ", name=" + this.name + ", iconKey=" + this.iconKey + ", intervalKm=" + this.intervalKm + ", lastDoneOdoKm=" + this.lastDoneOdoKm + ", lastDoneDateMs=" + this.lastDoneDateMs + ", intervalMonths=" + this.intervalMonths + ", sid=" + this.sid + ")";
    }

    public MaintenanceItem(long id, String name, String iconKey, int intervalKm, int lastDoneOdoKm, long lastDoneDateMs, int intervalMonths, String sid) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(iconKey, "iconKey");
        Intrinsics.checkNotNullParameter(sid, "sid");
        this.id = id;
        this.name = name;
        this.iconKey = iconKey;
        this.intervalKm = intervalKm;
        this.lastDoneOdoKm = lastDoneOdoKm;
        this.lastDoneDateMs = lastDoneDateMs;
        this.intervalMonths = intervalMonths;
        this.sid = sid;
    }

    public /* synthetic */ MaintenanceItem(long j, String str, String str2, int i, int i2, long j2, int i3, String str3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0L : j, str, str2, i, i2, j2, (i4 & 64) != 0 ? 0 : i3, (i4 & 128) != 0 ? "" : str3);
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getIconKey() {
        return this.iconKey;
    }

    public final int getIntervalKm() {
        return this.intervalKm;
    }

    public final int getLastDoneOdoKm() {
        return this.lastDoneOdoKm;
    }

    public final long getLastDoneDateMs() {
        return this.lastDoneDateMs;
    }

    public final int getIntervalMonths() {
        return this.intervalMonths;
    }

    public final String getSid() {
        return this.sid;
    }
}
