package com.example.wayfindr.data;

import androidx.autofill.HintConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedLocation.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001BW\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0005¢\u0006\u0004\b\u000f\u0010\u0010J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0007HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\u000f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\t\u0010\"\u001a\u00020\u0005HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J_\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0005HÆ\u0001J\u0013\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010)\u001a\u00020*HÖ\u0001J\t\u0010+\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014¨\u0006,"}, d2 = {"Lcom/example/wayfindr/data/SavedLocation;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "", "lat", "", "lng", "waypoints", "", "Lcom/example/wayfindr/data/SharedLocation;", "note", "createdMs", "sid", "<init>", "(JLjava/lang/String;DDLjava/util/List;Ljava/lang/String;JLjava/lang/String;)V", "getId", "()J", "getName", "()Ljava/lang/String;", "getLat", "()D", "getLng", "getWaypoints", "()Ljava/util/List;", "getNote", "getCreatedMs", "getSid", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class SavedLocation {
    public static final int $stable = 8;
    private final long createdMs;
    private final long id;
    private final double lat;
    private final double lng;
    private final String name;
    private final String note;
    private final String sid;
    private final List<SharedLocation> waypoints;

    public static /* synthetic */ SavedLocation copy$default(SavedLocation savedLocation, long j, String str, double d, double d2, List list, String str2, long j2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            j = savedLocation.id;
        }
        return savedLocation.copy(j, (i & 2) != 0 ? savedLocation.name : str, (i & 4) != 0 ? savedLocation.lat : d, (i & 8) != 0 ? savedLocation.lng : d2, (i & 16) != 0 ? savedLocation.waypoints : list, (i & 32) != 0 ? savedLocation.note : str2, (i & 64) != 0 ? savedLocation.createdMs : j2, (i & 128) != 0 ? savedLocation.sid : str3);
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
    public final double getLat() {
        return this.lat;
    }

    /* renamed from: component4, reason: from getter */
    public final double getLng() {
        return this.lng;
    }

    public final List<SharedLocation> component5() {
        return this.waypoints;
    }

    /* renamed from: component6, reason: from getter */
    public final String getNote() {
        return this.note;
    }

    /* renamed from: component7, reason: from getter */
    public final long getCreatedMs() {
        return this.createdMs;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSid() {
        return this.sid;
    }

    public final SavedLocation copy(long id, String name, double lat, double lng, List<SharedLocation> waypoints, String note, long createdMs, String sid) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(waypoints, "waypoints");
        Intrinsics.checkNotNullParameter(note, "note");
        Intrinsics.checkNotNullParameter(sid, "sid");
        return new SavedLocation(id, name, lat, lng, waypoints, note, createdMs, sid);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SavedLocation)) {
            return false;
        }
        SavedLocation savedLocation = (SavedLocation) other;
        return this.id == savedLocation.id && Intrinsics.areEqual(this.name, savedLocation.name) && Double.compare(this.lat, savedLocation.lat) == 0 && Double.compare(this.lng, savedLocation.lng) == 0 && Intrinsics.areEqual(this.waypoints, savedLocation.waypoints) && Intrinsics.areEqual(this.note, savedLocation.note) && this.createdMs == savedLocation.createdMs && Intrinsics.areEqual(this.sid, savedLocation.sid);
    }

    public int hashCode() {
        return (((((((((((((Long.hashCode(this.id) * 31) + this.name.hashCode()) * 31) + Double.hashCode(this.lat)) * 31) + Double.hashCode(this.lng)) * 31) + this.waypoints.hashCode()) * 31) + this.note.hashCode()) * 31) + Long.hashCode(this.createdMs)) * 31) + this.sid.hashCode();
    }

    public String toString() {
        return "SavedLocation(id=" + this.id + ", name=" + this.name + ", lat=" + this.lat + ", lng=" + this.lng + ", waypoints=" + this.waypoints + ", note=" + this.note + ", createdMs=" + this.createdMs + ", sid=" + this.sid + ")";
    }

    public SavedLocation(long id, String name, double lat, double lng, List<SharedLocation> waypoints, String note, long createdMs, String sid) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(waypoints, "waypoints");
        Intrinsics.checkNotNullParameter(note, "note");
        Intrinsics.checkNotNullParameter(sid, "sid");
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.waypoints = waypoints;
        this.note = note;
        this.createdMs = createdMs;
        this.sid = sid;
    }

    public /* synthetic */ SavedLocation(long j, String str, double d, double d2, List list, String str2, long j2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 0L : j, str, d, d2, (i & 16) != 0 ? CollectionsKt.emptyList() : list, (i & 32) != 0 ? "" : str2, (i & 64) != 0 ? System.currentTimeMillis() : j2, (i & 128) != 0 ? "" : str3);
    }

    public final long getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final double getLat() {
        return this.lat;
    }

    public final double getLng() {
        return this.lng;
    }

    public final List<SharedLocation> getWaypoints() {
        return this.waypoints;
    }

    public final String getNote() {
        return this.note;
    }

    public final long getCreatedMs() {
        return this.createdMs;
    }

    public final String getSid() {
        return this.sid;
    }
}
