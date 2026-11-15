package com.example.wayfindr.viewmodel;

import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.data.SharedLocation;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b7\b\u0087\b\u0018\u00002\u00020\u0001BÙ\u0001\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u000e\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0017\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u000e¢\u0006\u0004\b\u001d\u0010\u001eJ\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J\u0017\u0010:\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007HÆ\u0003J\t\u0010;\u001a\u00020\nHÆ\u0003J\t\u0010<\u001a\u00020\fHÆ\u0003J\t\u0010=\u001a\u00020\u000eHÆ\u0003J\t\u0010>\u001a\u00020\u000eHÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u000f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005HÆ\u0003J\t\u0010A\u001a\u00020\u0014HÆ\u0003J\t\u0010B\u001a\u00020\u000eHÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u000b\u0010E\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u000b\u0010F\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\u000b\u0010G\u001a\u0004\u0018\u00010\u0017HÆ\u0003J\t\u0010H\u001a\u00020\u000eHÆ\u0003JÛ\u0001\u0010I\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00052\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00052\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u000e2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00172\b\b\u0002\u0010\u001c\u001a\u00020\u000eHÆ\u0001J\u0013\u0010J\u001a\u00020\u000e2\b\u0010K\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010L\u001a\u00020\u0014HÖ\u0001J\t\u0010M\u001a\u00020\u0017HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001f\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010)R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010)R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005¢\u0006\b\n\u0000\u001a\u0004\b-\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b0\u0010)R\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b3\u00102R\u0013\u0010\u0019\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b4\u00102R\u0013\u0010\u001a\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b5\u00102R\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u0017¢\u0006\b\n\u0000\u001a\u0004\b6\u00102R\u0011\u0010\u001c\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b7\u0010)¨\u0006N"}, d2 = {"Lcom/example/wayfindr/viewmodel/RouteState;", "", "destination", "Lcom/example/wayfindr/data/SharedLocation;", "waypoints", "", "currentLocation", "Lkotlin/Pair;", "", "currentBearing", "", "locationRefreshKey", "", "isResolving", "", "pendingNavigate", "route", "Lcom/example/wayfindr/dash/nav/Route;", "routeAlternatives", "selectedRouteIndex", "", "routing", "distanceText", "", "durationText", "etaText", "searchNote", "sharedRouteName", "fromWayfindr", "<init>", "(Lcom/example/wayfindr/data/SharedLocation;Ljava/util/List;Lkotlin/Pair;FJZZLcom/example/wayfindr/dash/nav/Route;Ljava/util/List;IZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getDestination", "()Lcom/example/wayfindr/data/SharedLocation;", "getWaypoints", "()Ljava/util/List;", "getCurrentLocation", "()Lkotlin/Pair;", "getCurrentBearing", "()F", "getLocationRefreshKey", "()J", "()Z", "getPendingNavigate", "getRoute", "()Lcom/example/wayfindr/dash/nav/Route;", "getRouteAlternatives", "getSelectedRouteIndex", "()I", "getRouting", "getDistanceText", "()Ljava/lang/String;", "getDurationText", "getEtaText", "getSearchNote", "getSharedRouteName", "getFromWayfindr", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "copy", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class RouteState {
    public static final int $stable = 8;
    private final float currentBearing;
    private final Pair<Double, Double> currentLocation;
    private final SharedLocation destination;
    private final String distanceText;
    private final String durationText;
    private final String etaText;
    private final boolean fromWayfindr;
    private final boolean isResolving;
    private final long locationRefreshKey;
    private final boolean pendingNavigate;
    private final Route route;
    private final List<Route> routeAlternatives;
    private final boolean routing;
    private final String searchNote;
    private final int selectedRouteIndex;
    private final String sharedRouteName;
    private final List<SharedLocation> waypoints;

    public RouteState() {
        this(null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131071, null);
    }

    public static /* synthetic */ RouteState copy$default(RouteState routeState, SharedLocation sharedLocation, List list, Pair pair, float f, long j, boolean z, boolean z2, Route route, List list2, int i, boolean z3, String str, String str2, String str3, String str4, String str5, boolean z4, int i2, Object obj) {
        boolean z5;
        String str6;
        SharedLocation sharedLocation2 = (i2 & 1) != 0 ? routeState.destination : sharedLocation;
        List list3 = (i2 & 2) != 0 ? routeState.waypoints : list;
        Pair pair2 = (i2 & 4) != 0 ? routeState.currentLocation : pair;
        float f2 = (i2 & 8) != 0 ? routeState.currentBearing : f;
        long j2 = (i2 & 16) != 0 ? routeState.locationRefreshKey : j;
        boolean z6 = (i2 & 32) != 0 ? routeState.isResolving : z;
        boolean z7 = (i2 & 64) != 0 ? routeState.pendingNavigate : z2;
        Route route2 = (i2 & 128) != 0 ? routeState.route : route;
        List list4 = (i2 & 256) != 0 ? routeState.routeAlternatives : list2;
        int i3 = (i2 & 512) != 0 ? routeState.selectedRouteIndex : i;
        boolean z8 = (i2 & 1024) != 0 ? routeState.routing : z3;
        String str7 = (i2 & 2048) != 0 ? routeState.distanceText : str;
        String str8 = (i2 & 4096) != 0 ? routeState.durationText : str2;
        SharedLocation sharedLocation3 = sharedLocation2;
        String str9 = (i2 & 8192) != 0 ? routeState.etaText : str3;
        String str10 = (i2 & 16384) != 0 ? routeState.searchNote : str4;
        String str11 = (i2 & 32768) != 0 ? routeState.sharedRouteName : str5;
        if ((i2 & 65536) != 0) {
            str6 = str11;
            z5 = routeState.fromWayfindr;
        } else {
            z5 = z4;
            str6 = str11;
        }
        return routeState.copy(sharedLocation3, list3, pair2, f2, j2, z6, z7, route2, list4, i3, z8, str7, str8, str9, str10, str6, z5);
    }

    /* renamed from: component1, reason: from getter */
    public final SharedLocation getDestination() {
        return this.destination;
    }

    /* renamed from: component10, reason: from getter */
    public final int getSelectedRouteIndex() {
        return this.selectedRouteIndex;
    }

    /* renamed from: component11, reason: from getter */
    public final boolean getRouting() {
        return this.routing;
    }

    /* renamed from: component12, reason: from getter */
    public final String getDistanceText() {
        return this.distanceText;
    }

    /* renamed from: component13, reason: from getter */
    public final String getDurationText() {
        return this.durationText;
    }

    /* renamed from: component14, reason: from getter */
    public final String getEtaText() {
        return this.etaText;
    }

    /* renamed from: component15, reason: from getter */
    public final String getSearchNote() {
        return this.searchNote;
    }

    /* renamed from: component16, reason: from getter */
    public final String getSharedRouteName() {
        return this.sharedRouteName;
    }

    /* renamed from: component17, reason: from getter */
    public final boolean getFromWayfindr() {
        return this.fromWayfindr;
    }

    public final List<SharedLocation> component2() {
        return this.waypoints;
    }

    public final Pair<Double, Double> component3() {
        return this.currentLocation;
    }

    /* renamed from: component4, reason: from getter */
    public final float getCurrentBearing() {
        return this.currentBearing;
    }

    /* renamed from: component5, reason: from getter */
    public final long getLocationRefreshKey() {
        return this.locationRefreshKey;
    }

    /* renamed from: component6, reason: from getter */
    public final boolean getIsResolving() {
        return this.isResolving;
    }

    /* renamed from: component7, reason: from getter */
    public final boolean getPendingNavigate() {
        return this.pendingNavigate;
    }

    /* renamed from: component8, reason: from getter */
    public final Route getRoute() {
        return this.route;
    }

    public final List<Route> component9() {
        return this.routeAlternatives;
    }

    public final RouteState copy(SharedLocation destination, List<SharedLocation> waypoints, Pair<Double, Double> currentLocation, float currentBearing, long locationRefreshKey, boolean isResolving, boolean pendingNavigate, Route route, List<Route> routeAlternatives, int selectedRouteIndex, boolean routing, String distanceText, String durationText, String etaText, String searchNote, String sharedRouteName, boolean fromWayfindr) {
        Intrinsics.checkNotNullParameter(waypoints, "waypoints");
        Intrinsics.checkNotNullParameter(routeAlternatives, "routeAlternatives");
        return new RouteState(destination, waypoints, currentLocation, currentBearing, locationRefreshKey, isResolving, pendingNavigate, route, routeAlternatives, selectedRouteIndex, routing, distanceText, durationText, etaText, searchNote, sharedRouteName, fromWayfindr);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RouteState)) {
            return false;
        }
        RouteState routeState = (RouteState) other;
        return Intrinsics.areEqual(this.destination, routeState.destination) && Intrinsics.areEqual(this.waypoints, routeState.waypoints) && Intrinsics.areEqual(this.currentLocation, routeState.currentLocation) && Float.compare(this.currentBearing, routeState.currentBearing) == 0 && this.locationRefreshKey == routeState.locationRefreshKey && this.isResolving == routeState.isResolving && this.pendingNavigate == routeState.pendingNavigate && Intrinsics.areEqual(this.route, routeState.route) && Intrinsics.areEqual(this.routeAlternatives, routeState.routeAlternatives) && this.selectedRouteIndex == routeState.selectedRouteIndex && this.routing == routeState.routing && Intrinsics.areEqual(this.distanceText, routeState.distanceText) && Intrinsics.areEqual(this.durationText, routeState.durationText) && Intrinsics.areEqual(this.etaText, routeState.etaText) && Intrinsics.areEqual(this.searchNote, routeState.searchNote) && Intrinsics.areEqual(this.sharedRouteName, routeState.sharedRouteName) && this.fromWayfindr == routeState.fromWayfindr;
    }

    public int hashCode() {
        return ((((((((((((((((((((((((((((((((this.destination == null ? 0 : this.destination.hashCode()) * 31) + this.waypoints.hashCode()) * 31) + (this.currentLocation == null ? 0 : this.currentLocation.hashCode())) * 31) + Float.hashCode(this.currentBearing)) * 31) + Long.hashCode(this.locationRefreshKey)) * 31) + Boolean.hashCode(this.isResolving)) * 31) + Boolean.hashCode(this.pendingNavigate)) * 31) + (this.route == null ? 0 : this.route.hashCode())) * 31) + this.routeAlternatives.hashCode()) * 31) + Integer.hashCode(this.selectedRouteIndex)) * 31) + Boolean.hashCode(this.routing)) * 31) + (this.distanceText == null ? 0 : this.distanceText.hashCode())) * 31) + (this.durationText == null ? 0 : this.durationText.hashCode())) * 31) + (this.etaText == null ? 0 : this.etaText.hashCode())) * 31) + (this.searchNote == null ? 0 : this.searchNote.hashCode())) * 31) + (this.sharedRouteName != null ? this.sharedRouteName.hashCode() : 0)) * 31) + Boolean.hashCode(this.fromWayfindr);
    }

    public String toString() {
        return "RouteState(destination=" + this.destination + ", waypoints=" + this.waypoints + ", currentLocation=" + this.currentLocation + ", currentBearing=" + this.currentBearing + ", locationRefreshKey=" + this.locationRefreshKey + ", isResolving=" + this.isResolving + ", pendingNavigate=" + this.pendingNavigate + ", route=" + this.route + ", routeAlternatives=" + this.routeAlternatives + ", selectedRouteIndex=" + this.selectedRouteIndex + ", routing=" + this.routing + ", distanceText=" + this.distanceText + ", durationText=" + this.durationText + ", etaText=" + this.etaText + ", searchNote=" + this.searchNote + ", sharedRouteName=" + this.sharedRouteName + ", fromWayfindr=" + this.fromWayfindr + ")";
    }

    public RouteState(SharedLocation destination, List<SharedLocation> waypoints, Pair<Double, Double> pair, float currentBearing, long locationRefreshKey, boolean isResolving, boolean pendingNavigate, Route route, List<Route> routeAlternatives, int selectedRouteIndex, boolean routing, String distanceText, String durationText, String etaText, String searchNote, String sharedRouteName, boolean fromWayfindr) {
        Intrinsics.checkNotNullParameter(waypoints, "waypoints");
        Intrinsics.checkNotNullParameter(routeAlternatives, "routeAlternatives");
        this.destination = destination;
        this.waypoints = waypoints;
        this.currentLocation = pair;
        this.currentBearing = currentBearing;
        this.locationRefreshKey = locationRefreshKey;
        this.isResolving = isResolving;
        this.pendingNavigate = pendingNavigate;
        this.route = route;
        this.routeAlternatives = routeAlternatives;
        this.selectedRouteIndex = selectedRouteIndex;
        this.routing = routing;
        this.distanceText = distanceText;
        this.durationText = durationText;
        this.etaText = etaText;
        this.searchNote = searchNote;
        this.sharedRouteName = sharedRouteName;
        this.fromWayfindr = fromWayfindr;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ RouteState(SharedLocation sharedLocation, List list, Pair pair, float f, long j, boolean z, boolean z2, Route route, List list2, int i, boolean z3, String str, String str2, String str3, String str4, String str5, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(r16, r22, r23, r24, r25, r27, r28, r29, r30, r31, r32, r33, r2, r9, r1, (i2 & 32768) != 0 ? null : str5, (i2 & 65536) != 0 ? false : z4);
        SharedLocation sharedLocation2 = (i2 & 1) != 0 ? null : sharedLocation;
        List emptyList = (i2 & 2) != 0 ? CollectionsKt.emptyList() : list;
        Pair pair2 = (i2 & 4) != 0 ? null : pair;
        float f2 = (i2 & 8) != 0 ? 0.0f : f;
        long j2 = (i2 & 16) != 0 ? 0L : j;
        boolean z5 = (i2 & 32) != 0 ? false : z;
        boolean z6 = (i2 & 64) != 0 ? false : z2;
        Route route2 = (i2 & 128) != 0 ? null : route;
        List emptyList2 = (i2 & 256) != 0 ? CollectionsKt.emptyList() : list2;
        int i3 = (i2 & 512) != 0 ? 0 : i;
        boolean z7 = (i2 & 1024) != 0 ? false : z3;
        String str6 = (i2 & 2048) != 0 ? null : str;
        String str7 = (i2 & 4096) != 0 ? null : str2;
        String str8 = (i2 & 8192) != 0 ? null : str3;
        SharedLocation sharedLocation3 = sharedLocation2;
        String str9 = (i2 & 16384) != 0 ? null : str4;
        List list3 = emptyList;
        Pair pair3 = pair2;
        float f3 = f2;
        long j3 = j2;
        boolean z8 = z5;
        boolean z9 = z6;
        Route route3 = route2;
        List list4 = emptyList2;
        int i4 = i3;
        boolean z10 = z7;
        String str10 = str6;
    }

    public final SharedLocation getDestination() {
        return this.destination;
    }

    public final List<SharedLocation> getWaypoints() {
        return this.waypoints;
    }

    public final Pair<Double, Double> getCurrentLocation() {
        return this.currentLocation;
    }

    public final float getCurrentBearing() {
        return this.currentBearing;
    }

    public final long getLocationRefreshKey() {
        return this.locationRefreshKey;
    }

    public final boolean isResolving() {
        return this.isResolving;
    }

    public final boolean getPendingNavigate() {
        return this.pendingNavigate;
    }

    public final Route getRoute() {
        return this.route;
    }

    public final List<Route> getRouteAlternatives() {
        return this.routeAlternatives;
    }

    public final int getSelectedRouteIndex() {
        return this.selectedRouteIndex;
    }

    public final boolean getRouting() {
        return this.routing;
    }

    public final String getDistanceText() {
        return this.distanceText;
    }

    public final String getDurationText() {
        return this.durationText;
    }

    public final String getEtaText() {
        return this.etaText;
    }

    public final String getSearchNote() {
        return this.searchNote;
    }

    public final String getSharedRouteName() {
        return this.sharedRouteName;
    }

    public final boolean getFromWayfindr() {
        return this.fromWayfindr;
    }
}
