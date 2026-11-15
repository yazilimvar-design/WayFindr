package com.example.wayfindr.data;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GarageModels.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B]\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003Jc\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020(HÖ\u0001J\t\u0010)\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012¨\u0006*"}, d2 = {"Lcom/example/wayfindr/data/VehicleDocument;", "", "id", "", "sid", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "title", "number", "issueMs", "expiryMs", "filePath", "note", "<init>", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/String;)V", "getId", "()J", "getSid", "()Ljava/lang/String;", "getType", "getTitle", "getNumber", "getIssueMs", "getExpiryMs", "getFilePath", "getNote", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class VehicleDocument {
    public static final int $stable = 0;
    private final long expiryMs;
    private final String filePath;
    private final long id;
    private final long issueMs;
    private final String note;
    private final String number;
    private final String sid;
    private final String title;
    private final String type;

    public static /* synthetic */ VehicleDocument copy$default(VehicleDocument vehicleDocument, long j, String str, String str2, String str3, String str4, long j2, long j3, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            j = vehicleDocument.id;
        }
        return vehicleDocument.copy(j, (i & 2) != 0 ? vehicleDocument.sid : str, (i & 4) != 0 ? vehicleDocument.type : str2, (i & 8) != 0 ? vehicleDocument.title : str3, (i & 16) != 0 ? vehicleDocument.number : str4, (i & 32) != 0 ? vehicleDocument.issueMs : j2, (i & 64) != 0 ? vehicleDocument.expiryMs : j3, (i & 128) != 0 ? vehicleDocument.filePath : str5, (i & 256) != 0 ? vehicleDocument.note : str6);
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
    public final String getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final String getTitle() {
        return this.title;
    }

    /* renamed from: component5, reason: from getter */
    public final String getNumber() {
        return this.number;
    }

    /* renamed from: component6, reason: from getter */
    public final long getIssueMs() {
        return this.issueMs;
    }

    /* renamed from: component7, reason: from getter */
    public final long getExpiryMs() {
        return this.expiryMs;
    }

    /* renamed from: component8, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    /* renamed from: component9, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    public final VehicleDocument copy(long id, String sid, String type, String title, String number, long issueMs, long expiryMs, String filePath, String note) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(note, "note");
        return new VehicleDocument(id, sid, type, title, number, issueMs, expiryMs, filePath, note);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof VehicleDocument)) {
            return false;
        }
        VehicleDocument vehicleDocument = (VehicleDocument) other;
        return this.id == vehicleDocument.id && Intrinsics.areEqual(this.sid, vehicleDocument.sid) && Intrinsics.areEqual(this.type, vehicleDocument.type) && Intrinsics.areEqual(this.title, vehicleDocument.title) && Intrinsics.areEqual(this.number, vehicleDocument.number) && this.issueMs == vehicleDocument.issueMs && this.expiryMs == vehicleDocument.expiryMs && Intrinsics.areEqual(this.filePath, vehicleDocument.filePath) && Intrinsics.areEqual(this.note, vehicleDocument.note);
    }

    public int hashCode() {
        return (((((((((((((((Long.hashCode(this.id) * 31) + this.sid.hashCode()) * 31) + this.type.hashCode()) * 31) + this.title.hashCode()) * 31) + this.number.hashCode()) * 31) + Long.hashCode(this.issueMs)) * 31) + Long.hashCode(this.expiryMs)) * 31) + this.filePath.hashCode()) * 31) + this.note.hashCode();
    }

    public String toString() {
        return "VehicleDocument(id=" + this.id + ", sid=" + this.sid + ", type=" + this.type + ", title=" + this.title + ", number=" + this.number + ", issueMs=" + this.issueMs + ", expiryMs=" + this.expiryMs + ", filePath=" + this.filePath + ", note=" + this.note + ")";
    }

    public VehicleDocument(long id, String sid, String type, String title, String number, long issueMs, long expiryMs, String filePath, String note) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(number, "number");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Intrinsics.checkNotNullParameter(note, "note");
        this.id = id;
        this.sid = sid;
        this.type = type;
        this.title = title;
        this.number = number;
        this.issueMs = issueMs;
        this.expiryMs = expiryMs;
        this.filePath = filePath;
        this.note = note;
    }

    public /* synthetic */ VehicleDocument(long j, String str, String str2, String str3, String str4, long j2, long j3, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, (i & 2) != 0 ? "" : str, str2, str3, (i & 16) != 0 ? "" : str4, (i & 32) != 0 ? 0L : j2, (i & 64) != 0 ? 0L : j3, (i & 128) != 0 ? "" : str5, (i & 256) != 0 ? "" : str6);
    }

    public final long getId() {
        return this.id;
    }

    public final String getSid() {
        return this.sid;
    }

    public final String getType() {
        return this.type;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getNumber() {
        return this.number;
    }

    public final long getIssueMs() {
        return this.issueMs;
    }

    public final long getExpiryMs() {
        return this.expiryMs;
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final String getNote() {
        return this.note;
    }
}
