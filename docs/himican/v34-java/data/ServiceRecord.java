package com.example.wayfindr.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GarageModels.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b!\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001Bu\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0005¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\t\u0010(\u001a\u00020\u0005HÆ\u0003J\u000f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\t\u0010*\u001a\u00020\fHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u000fHÆ\u0003J\t\u0010-\u001a\u00020\u0005HÆ\u0003J\t\u0010.\u001a\u00020\u0005HÆ\u0003J}\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0005HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\fHÖ\u0001J\t\u00104\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0010\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017¨\u00065"}, d2 = {"Lcom/example/wayfindr/data/ServiceRecord;", "", "id", "", "sid", "", "title", "kind", "scheduledKey", "itemSids", "", "odometerKm", "", "dateMs", "cost", "", "invoicePath", "note", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;IJDLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getSid", "()Ljava/lang/String;", "getTitle", "getKind", "getScheduledKey", "getItemSids", "()Ljava/util/List;", "getOdometerKm", "()I", "getDateMs", "getCost", "()D", "getInvoicePath", "getNote", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class ServiceRecord {
    public static final int $stable = 8;
    private final double cost;
    private final long dateMs;
    private final long id;
    private final String invoicePath;
    private final List<String> itemSids;
    private final String kind;
    private final String note;
    private final int odometerKm;
    private final String scheduledKey;
    private final String sid;
    private final String title;

    /* renamed from: component1, reason: from getter */
    public final long getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getInvoicePath() {
        return this.invoicePath;
    }

    /* renamed from: component11, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component4, reason: from getter */
    public final String getKind() {
        return this.kind;
    }

    /* renamed from: component5, reason: from getter */
    public final String getScheduledKey() {
        return this.scheduledKey;
    }

    public final List<String> component6() {
        return this.itemSids;
    }

    /* renamed from: component7, reason: from getter */
    public final int getOdometerKm() {
        return this.odometerKm;
    }

    /* renamed from: component8, reason: from getter */
    public final long getDateMs() {
        return this.dateMs;
    }

    /* renamed from: component9, reason: from getter */
    public final double getCost() {
        return this.cost;
    }

    public final ServiceRecord copy(long id, String sid, String title, String kind, String scheduledKey, List<String> itemSids, int odometerKm, long dateMs, double cost, String invoicePath, String note) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(scheduledKey, "scheduledKey");
        Intrinsics.checkNotNullParameter(itemSids, "itemSids");
        Intrinsics.checkNotNullParameter(invoicePath, "invoicePath");
        Intrinsics.checkNotNullParameter(note, "note");
        return new ServiceRecord(id, sid, title, kind, scheduledKey, itemSids, odometerKm, dateMs, cost, invoicePath, note);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ServiceRecord)) {
            return false;
        }
        ServiceRecord serviceRecord = (ServiceRecord) other;
        return this.id == serviceRecord.id && Intrinsics.areEqual(this.sid, serviceRecord.sid) && Intrinsics.areEqual(this.title, serviceRecord.title) && Intrinsics.areEqual(this.kind, serviceRecord.kind) && Intrinsics.areEqual(this.scheduledKey, serviceRecord.scheduledKey) && Intrinsics.areEqual(this.itemSids, serviceRecord.itemSids) && this.odometerKm == serviceRecord.odometerKm && this.dateMs == serviceRecord.dateMs && Double.compare(this.cost, serviceRecord.cost) == 0 && Intrinsics.areEqual(this.invoicePath, serviceRecord.invoicePath) && Intrinsics.areEqual(this.note, serviceRecord.note);
    }

    public int hashCode() {
        return (((((((((((((((((((Long.hashCode(this.id) * 31) + this.sid.hashCode()) * 31) + this.title.hashCode()) * 31) + this.kind.hashCode()) * 31) + this.scheduledKey.hashCode()) * 31) + this.itemSids.hashCode()) * 31) + Integer.hashCode(this.odometerKm)) * 31) + Long.hashCode(this.dateMs)) * 31) + Double.hashCode(this.cost)) * 31) + this.invoicePath.hashCode()) * 31) + this.note.hashCode();
    }

    public String toString() {
        return "ServiceRecord(id=" + this.id + ", sid=" + this.sid + ", title=" + this.title + ", kind=" + this.kind + ", scheduledKey=" + this.scheduledKey + ", itemSids=" + this.itemSids + ", odometerKm=" + this.odometerKm + ", dateMs=" + this.dateMs + ", cost=" + this.cost + ", invoicePath=" + this.invoicePath + ", note=" + this.note + ")";
    }

    public ServiceRecord(long id, String sid, String title, String kind, String scheduledKey, List<String> itemSids, int odometerKm, long dateMs, double cost, String invoicePath, String note) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(scheduledKey, "scheduledKey");
        Intrinsics.checkNotNullParameter(itemSids, "itemSids");
        Intrinsics.checkNotNullParameter(invoicePath, "invoicePath");
        Intrinsics.checkNotNullParameter(note, "note");
        this.id = id;
        this.sid = sid;
        this.title = title;
        this.kind = kind;
        this.scheduledKey = scheduledKey;
        this.itemSids = itemSids;
        this.odometerKm = odometerKm;
        this.dateMs = dateMs;
        this.cost = cost;
        this.invoicePath = invoicePath;
        this.note = note;
    }

    public /* synthetic */ ServiceRecord(long j, String str, String str2, String str3, String str4, List list, int i, long j2, double d, String str5, String str6, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0L : j, (i2 & 2) != 0 ? "" : str, str2, (i2 & 8) != 0 ? "company" : str3, (i2 & 16) != 0 ? "" : str4, (i2 & 32) != 0 ? CollectionsKt.emptyList() : list, i, j2, (i2 & 256) != 0 ? 0.0d : d, (i2 & 512) != 0 ? "" : str5, (i2 & 1024) != 0 ? "" : str6);
    }

    public final long getId() {
        return this.id;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getKind() {
        return this.kind;
    }

    public final String getScheduledKey() {
        return this.scheduledKey;
    }

    public final List<String> getItemSids() {
        return this.itemSids;
    }

    public final int getOdometerKm() {
        return this.odometerKm;
    }

    public final long getDateMs() {
        return this.dateMs;
    }

    public final double getCost() {
        return this.cost;
    }

    public final String getInvoicePath() {
        return this.invoicePath;
    }

    public final String getNote() {
        return this.note;
    }
}
