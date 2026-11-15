package com.example.wayfindr.viewmodel;

import android.location.Location;
import android.util.Log;
import com.example.wayfindr.dash.map.TileProvider;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.Router;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$fetchRoute$1", f = "DashViewModel.kt", i = {1}, l = {938, 949}, m = "invokeSuspend", n = {"origin"}, s = {"L$0"})
/* loaded from: classes4.dex */
final class DashViewModel$fetchRoute$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $destLatV;
    final /* synthetic */ double $destLngV;
    final /* synthetic */ long $requestId;
    final /* synthetic */ List<GeoPoint> $waypoints;
    Object L$0;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$fetchRoute$1(DashViewModel dashViewModel, long j, double d, double d2, List<GeoPoint> list, Continuation<? super DashViewModel$fetchRoute$1> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
        this.$requestId = j;
        this.$destLatV = d;
        this.$destLngV = d2;
        this.$waypoints = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashViewModel$fetchRoute$1(this.this$0, this.$requestId, this.$destLatV, this.$destLngV, this.$waypoints, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$fetchRoute$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:82:0x0047, code lost:
    
        if (r2 == null) goto L20;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a9 A[Catch: all -> 0x0028, TryCatch #0 {all -> 0x0028, blocks: (B:8:0x001b, B:9:0x0159, B:11:0x0167, B:13:0x0175, B:17:0x018a, B:18:0x01aa, B:21:0x0203, B:28:0x0237, B:29:0x023c, B:32:0x0290, B:34:0x02aa, B:39:0x0022, B:41:0x0060, B:43:0x0064, B:45:0x0070, B:49:0x007c, B:51:0x0089, B:53:0x0097, B:57:0x00a9, B:58:0x00b5, B:61:0x0109, B:67:0x0122, B:71:0x02be, B:77:0x002f, B:79:0x003b, B:84:0x0049), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0122 A[Catch: all -> 0x0028, TRY_ENTER, TryCatch #0 {all -> 0x0028, blocks: (B:8:0x001b, B:9:0x0159, B:11:0x0167, B:13:0x0175, B:17:0x018a, B:18:0x01aa, B:21:0x0203, B:28:0x0237, B:29:0x023c, B:32:0x0290, B:34:0x02aa, B:39:0x0022, B:41:0x0060, B:43:0x0064, B:45:0x0070, B:49:0x007c, B:51:0x0089, B:53:0x0097, B:57:0x00a9, B:58:0x00b5, B:61:0x0109, B:67:0x0122, B:71:0x02be, B:77:0x002f, B:79:0x003b, B:84:0x0049), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x02cc  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Location origin;
        Object withTimeoutOrNull;
        boolean isPreciseRouteOrigin;
        Object route;
        Object value;
        Object value2;
        TileProvider tileProvider;
        Object value3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    origin = this.this$0.location.lastKnown();
                    if (origin != null) {
                        isPreciseRouteOrigin = this.this$0.isPreciseRouteOrigin(origin);
                        if (!isPreciseRouteOrigin) {
                            origin = null;
                            break;
                        } else {
                            break;
                        }
                    }
                    this.label = 1;
                    withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(12000L, new DashViewModel$fetchRoute$1$origin$2(this.this$0, null), this);
                    if (withTimeoutOrNull == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    origin = (Location) withTimeoutOrNull;
                    if (origin == null && ((origin = this.this$0.location.lastKnown()) == null || !this.this$0.isFallbackRouteOrigin(origin))) {
                        origin = null;
                    }
                    if (this.$requestId == this.this$0.routeRequestGeneration && Intrinsics.areEqual(this.this$0.destLat, this.$destLatV) && Intrinsics.areEqual(this.this$0.destLng, this.$destLngV)) {
                        if (origin != null) {
                            this.this$0.waitingForFreshRouteOrigin = true;
                            MutableStateFlow mutableStateFlow = this.this$0._ui;
                            do {
                                value = mutableStateFlow.getValue();
                            } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, null, 0, null, null, null, null, "Güncel GPS konumu bekleniyor; rota sinyal alınınca hesaplanacak.", 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -65, null)));
                            Log.w("DashViewModel", "fetchRoute: no recent usable GPS origin; waiting for a new fix");
                            return Unit.INSTANCE;
                        }
                        this.L$0 = SpillingKt.nullOutSpilledVariable(origin);
                        this.label = 2;
                        route = Router.INSTANCE.route(this.this$0.getApplication(), new GeoPoint(origin.getLatitude(), origin.getLongitude()), new GeoPoint(this.$destLatV, this.$destLngV), this.$waypoints, this);
                        if (route == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        Route r = (Route) route;
                        if (this.$requestId == this.this$0.routeRequestGeneration || !Intrinsics.areEqual(this.this$0.destLat, this.$destLatV) || !Intrinsics.areEqual(this.this$0.destLng, this.$destLngV)) {
                            Unit unit = Unit.INSTANCE;
                            if (this.$requestId == this.this$0.routeRequestGeneration) {
                                this.this$0.routeRequestJob = null;
                            }
                            return unit;
                        }
                        DashViewModel dashViewModel = this.this$0;
                        if (r != null) {
                            dashViewModel.route = r;
                            this.this$0.lastTrafficRefreshAt = System.currentTimeMillis();
                            tileProvider = this.this$0.tiles;
                            tileProvider.prefetchRoute(r.getGeometry());
                            MutableStateFlow mutableStateFlow2 = this.this$0._ui;
                            do {
                                value3 = mutableStateFlow2.getValue();
                            } while (!mutableStateFlow2.compareAndSet(value3, DashUiState.copy$default((DashUiState) value3, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, true, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, r.getGeometry(), r.getTraffic(), 1073725375, null)));
                            Log.i("DashViewModel", "Route ready: " + r.getGeometry().size() + " pts, " + ((int) r.getTotalMeters()) + " m");
                        } else {
                            MutableStateFlow mutableStateFlow3 = dashViewModel._ui;
                            do {
                                value2 = mutableStateFlow3.getValue();
                            } while (!mutableStateFlow3.compareAndSet(value2, DashUiState.copy$default((DashUiState) value2, null, 0, null, null, null, null, "Rota bulunamadı. İnternet bağlantınızı ve varış noktasını kontrol edin.", 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -65, null)));
                            Log.w("DashViewModel", "Router returned null");
                        }
                        if (this.$requestId == this.this$0.routeRequestGeneration) {
                            this.this$0.routeRequestJob = null;
                        }
                        return Unit.INSTANCE;
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (this.$requestId == this.this$0.routeRequestGeneration) {
                        this.this$0.routeRequestJob = null;
                    }
                    return unit2;
                case 1:
                    ResultKt.throwOnFailure($result);
                    withTimeoutOrNull = $result;
                    origin = (Location) withTimeoutOrNull;
                    if (origin == null) {
                        origin = null;
                        break;
                    }
                    if (this.$requestId == this.this$0.routeRequestGeneration) {
                        if (origin != null) {
                        }
                        break;
                    }
                    Unit unit22 = Unit.INSTANCE;
                    if (this.$requestId == this.this$0.routeRequestGeneration) {
                    }
                    return unit22;
                case 2:
                    ResultKt.throwOnFailure($result);
                    route = $result;
                    Route r2 = (Route) route;
                    if (this.$requestId == this.this$0.routeRequestGeneration) {
                        break;
                    }
                    Unit unit3 = Unit.INSTANCE;
                    if (this.$requestId == this.this$0.routeRequestGeneration) {
                    }
                    return unit3;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        } finally {
            if (this.$requestId == this.this$0.routeRequestGeneration) {
                this.this$0.routeRequestJob = null;
            }
        }
    }
}
