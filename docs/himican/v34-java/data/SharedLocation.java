package com.example.wayfindr.data;

import androidx.autofill.HintConstants;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SharedLocation.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003JF\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/example/wayfindr/data/SharedLocation;", "", HintConstants.AUTOFILL_HINT_NAME, "", "lat", "", "lng", ImagesContract.URL, "needsExpansion", "", "<init>", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Z)V", "getName", "()Ljava/lang/String;", "getLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getLng", "getUrl", "getNeedsExpansion", "()Z", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/String;Z)Lcom/example/wayfindr/data/SharedLocation;", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final /* data */ class SharedLocation {
    public static final int $stable = 0;
    private final Double lat;
    private final Double lng;
    private final String name;
    private final boolean needsExpansion;
    private final String url;

    public static /* synthetic */ SharedLocation copy$default(SharedLocation sharedLocation, String str, Double d, Double d2, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sharedLocation.name;
        }
        if ((i & 2) != 0) {
            d = sharedLocation.lat;
        }
        if ((i & 4) != 0) {
            d2 = sharedLocation.lng;
        }
        if ((i & 8) != 0) {
            str2 = sharedLocation.url;
        }
        if ((i & 16) != 0) {
            z = sharedLocation.needsExpansion;
        }
        boolean z2 = z;
        Double d3 = d2;
        return sharedLocation.copy(str, d, d3, str2, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final Double getLat() {
        return this.lat;
    }

    /* renamed from: component3, reason: from getter */
    public final Double getLng() {
        return this.lng;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component5, reason: from getter */
    public final boolean getNeedsExpansion() {
        return this.needsExpansion;
    }

    public final SharedLocation copy(String name, Double lat, Double lng, String url, boolean needsExpansion) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new SharedLocation(name, lat, lng, url, needsExpansion);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SharedLocation)) {
            return false;
        }
        SharedLocation sharedLocation = (SharedLocation) other;
        return Intrinsics.areEqual(this.name, sharedLocation.name) && Intrinsics.areEqual((Object) this.lat, (Object) sharedLocation.lat) && Intrinsics.areEqual((Object) this.lng, (Object) sharedLocation.lng) && Intrinsics.areEqual(this.url, sharedLocation.url) && this.needsExpansion == sharedLocation.needsExpansion;
    }

    public int hashCode() {
        return (((((((this.name.hashCode() * 31) + (this.lat == null ? 0 : this.lat.hashCode())) * 31) + (this.lng == null ? 0 : this.lng.hashCode())) * 31) + (this.url != null ? this.url.hashCode() : 0)) * 31) + Boolean.hashCode(this.needsExpansion);
    }

    public String toString() {
        return "SharedLocation(name=" + this.name + ", lat=" + this.lat + ", lng=" + this.lng + ", url=" + this.url + ", needsExpansion=" + this.needsExpansion + ")";
    }

    public SharedLocation(String name, Double lat, Double lng, String url, boolean needsExpansion) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.url = url;
        this.needsExpansion = needsExpansion;
    }

    public /* synthetic */ SharedLocation(String str, Double d, Double d2, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? null : d, (i & 4) != 0 ? null : d2, (i & 8) != 0 ? null : str2, (i & 16) != 0 ? false : z);
    }

    public final String getName() {
        return this.name;
    }

    public final Double getLat() {
        return this.lat;
    }

    public final Double getLng() {
        return this.lng;
    }

    public final String getUrl() {
        return this.url;
    }

    public final boolean getNeedsExpansion() {
        return this.needsExpansion;
    }
}
