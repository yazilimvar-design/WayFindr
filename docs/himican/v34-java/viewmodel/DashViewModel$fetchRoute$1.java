package com.example.wayfindr.viewmodel;

import android.location.Location;
import android.util.Log;
import com.example.wayfindr.dash.map.LocationTracker;
import com.example.wayfindr.dash.map.TileProvider;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.Router;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: DashViewModel.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class DashViewModel$fetchRoute$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ double $destLatV;
    final /* synthetic */ double $destLngV;
    final /* synthetic */ long $requestId;
    final /* synthetic */ List $waypoints;
    Object L$0;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$fetchRoute$1(DashViewModel dashViewModel, long j, double d, double d2, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
        this.$requestId = j;
        this.$destLatV = d;
        this.$destLngV = d2;
        this.$waypoints = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new DashViewModel$fetchRoute$1(this.this$0, this.$requestId, this.$destLatV, this.$destLngV, this.$waypoints, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0047, code lost:
    
        if (r2 == null) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0167 A[Catch: all -> 0x0028, TryCatch #0 {all -> 0x0028, blocks: (B:8:0x001b, B:9:0x0159, B:11:0x0167, B:13:0x0175, B:17:0x018a, B:18:0x01aa, B:21:0x0203, B:28:0x0237, B:29:0x023c, B:32:0x0290, B:34:0x02aa, B:39:0x0022, B:41:0x0060, B:43:0x0064, B:45:0x0070, B:49:0x007c, B:51:0x0089, B:53:0x0097, B:57:0x00a9, B:58:0x00b5, B:61:0x0109, B:67:0x0122, B:71:0x02be, B:77:0x002f, B:79:0x003b, B:84:0x0049), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0064 A[Catch: all -> 0x0028, TryCatch #0 {all -> 0x0028, blocks: (B:8:0x001b, B:9:0x0159, B:11:0x0167, B:13:0x0175, B:17:0x018a, B:18:0x01aa, B:21:0x0203, B:28:0x0237, B:29:0x023c, B:32:0x0290, B:34:0x02aa, B:39:0x0022, B:41:0x0060, B:43:0x0064, B:45:0x0070, B:49:0x007c, B:51:0x0089, B:53:0x0097, B:57:0x00a9, B:58:0x00b5, B:61:0x0109, B:67:0x0122, B:71:0x02be, B:77:0x002f, B:79:0x003b, B:84:0x0049), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0089 A[Catch: all -> 0x0028, TryCatch #0 {all -> 0x0028, blocks: (B:8:0x001b, B:9:0x0159, B:11:0x0167, B:13:0x0175, B:17:0x018a, B:18:0x01aa, B:21:0x0203, B:28:0x0237, B:29:0x023c, B:32:0x0290, B:34:0x02aa, B:39:0x0022, B:41:0x0060, B:43:0x0064, B:45:0x0070, B:49:0x007c, B:51:0x0089, B:53:0x0097, B:57:0x00a9, B:58:0x00b5, B:61:0x0109, B:67:0x0122, B:71:0x02be, B:77:0x002f, B:79:0x003b, B:84:0x0049), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        long j;
        LocationTracker locationTracker;
        Location lastKnown;
        Object withTimeoutOrNull;
        boolean isPreciseRouteOrigin;
        long j2;
        long j3;
        long j4;
        long j5;
        Double d;
        Double d2;
        Object route;
        MutableStateFlow mutableStateFlow;
        Object value;
        LocationTracker locationTracker2;
        boolean isFallbackRouteOrigin;
        long j6;
        long j7;
        long j8;
        long j9;
        Double d3;
        Double d4;
        MutableStateFlow mutableStateFlow2;
        Object value2;
        long j10;
        TileProvider tileProvider;
        MutableStateFlow mutableStateFlow3;
        Object value3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    locationTracker = this.this$0.location;
                    lastKnown = locationTracker.lastKnown();
                    if (lastKnown != null) {
                        isPreciseRouteOrigin = this.this$0.isPreciseRouteOrigin(lastKnown);
                        if (!isPreciseRouteOrigin) {
                            lastKnown = null;
                            break;
                        } else {
                            break;
                        }
                    }
                    this.label = 1;
                    withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(2500L, new origin.2(this.this$0, (Continuation) null), (Continuation) this);
                    if (withTimeoutOrNull == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    lastKnown = (Location) withTimeoutOrNull;
                    if (lastKnown == null) {
                        locationTracker2 = this.this$0.location;
                        lastKnown = locationTracker2.lastKnownAny();
                        if (lastKnown != null) {
                            isFallbackRouteOrigin = this.this$0.isFallbackRouteOrigin(lastKnown);
                            if (isFallbackRouteOrigin) {
                            }
                        }
                        lastKnown = null;
                    }
                    j2 = this.$requestId;
                    j3 = this.this$0.routeRequestGeneration;
                    if (j2 == j3) {
                        d = this.this$0.destLat;
                        if (Intrinsics.areEqual(d, this.$destLatV)) {
                            d2 = this.this$0.destLng;
                            if (Intrinsics.areEqual(d2, this.$destLngV)) {
                                if (lastKnown == null) {
                                    this.this$0.waitingForFreshRouteOrigin = true;
                                    mutableStateFlow = this.this$0._ui;
                                    do {
                                        value = mutableStateFlow.getValue();
                                    } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, "Güncel GPS konumu bekleniyor; rota sinyal alınınca hesaplanacak.", 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -65, (Object) null)));
                                    Log.w("DashViewModel", "fetchRoute: no recent usable GPS origin; waiting for a new fix");
                                    return Unit.INSTANCE;
                                }
                                this.L$0 = SpillingKt.nullOutSpilledVariable(lastKnown);
                                this.label = 2;
                                route = Router.INSTANCE.route(this.this$0.getApplication(), new GeoPoint(lastKnown.getLatitude(), lastKnown.getLongitude()), new GeoPoint(this.$destLatV, this.$destLngV), this.$waypoints, (Continuation) this);
                                if (route == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                Route route2 = (Route) route;
                                j6 = this.$requestId;
                                j7 = this.this$0.routeRequestGeneration;
                                if (j6 == j7) {
                                    d3 = this.this$0.destLat;
                                    if (Intrinsics.areEqual(d3, this.$destLatV)) {
                                        d4 = this.this$0.destLng;
                                        if (Intrinsics.areEqual(d4, this.$destLngV)) {
                                            DashViewModel dashViewModel = this.this$0;
                                            if (route2 != null) {
                                                dashViewModel.route = route2;
                                                this.this$0.lastTrafficRefreshAt = System.currentTimeMillis();
                                                tileProvider = this.this$0.tiles;
                                                tileProvider.prefetchRoute(route2.getGeometry());
                                                mutableStateFlow3 = this.this$0._ui;
                                                do {
                                                    value3 = mutableStateFlow3.getValue();
                                                } while (!mutableStateFlow3.compareAndSet(value3, DashUiState.copy$default((DashUiState) value3, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, true, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, route2.getGeometry(), route2.getTraffic(), 1073725375, (Object) null)));
                                                Log.i("DashViewModel", "Route ready: " + route2.getGeometry().size() + " pts, " + ((int) route2.getTotalMeters()) + " m");
                                            } else {
                                                mutableStateFlow2 = dashViewModel._ui;
                                                do {
                                                    value2 = mutableStateFlow2.getValue();
                                                } while (!mutableStateFlow2.compareAndSet(value2, DashUiState.copy$default((DashUiState) value2, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, "Rota bulunamadı. İnternet bağlantınızı ve varış noktasını kontrol edin.", 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -65, (Object) null)));
                                                Log.w("DashViewModel", "Router returned null");
                                            }
                                            long j11 = this.$requestId;
                                            j10 = this.this$0.routeRequestGeneration;
                                            if (j11 == j10) {
                                                this.this$0.routeRequestJob = null;
                                            }
                                            return Unit.INSTANCE;
                                        }
                                    }
                                }
                                Unit unit = Unit.INSTANCE;
                                j8 = this.$requestId;
                                j9 = this.this$0.routeRequestGeneration;
                                if (j8 == j9) {
                                    this.this$0.routeRequestJob = null;
                                }
                                return unit;
                            }
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    j4 = this.$requestId;
                    j5 = this.this$0.routeRequestGeneration;
                    if (j4 == j5) {
                        this.this$0.routeRequestJob = null;
                    }
                    return unit2;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    withTimeoutOrNull = obj;
                    lastKnown = (Location) withTimeoutOrNull;
                    if (lastKnown == null) {
                    }
                    j2 = this.$requestId;
                    j3 = this.this$0.routeRequestGeneration;
                    if (j2 == j3) {
                    }
                    Unit unit22 = Unit.INSTANCE;
                    j4 = this.$requestId;
                    j5 = this.this$0.routeRequestGeneration;
                    if (j4 == j5) {
                    }
                    return unit22;
                case 2:
                    ResultKt.throwOnFailure(obj);
                    route = obj;
                    Route route22 = (Route) route;
                    j6 = this.$requestId;
                    j7 = this.this$0.routeRequestGeneration;
                    if (j6 == j7) {
                    }
                    Unit unit3 = Unit.INSTANCE;
                    j8 = this.$requestId;
                    j9 = this.this$0.routeRequestGeneration;
                    if (j8 == j9) {
                    }
                    return unit3;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } finally {
            long j12 = this.$requestId;
            j = this.this$0.routeRequestGeneration;
            if (j12 == j) {
                this.this$0.routeRequestJob = null;
            }
        }
    }
}
