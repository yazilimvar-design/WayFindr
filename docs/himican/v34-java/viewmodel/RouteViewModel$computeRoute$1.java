package com.example.wayfindr.viewmodel;

import android.app.Application;
import android.location.Location;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.Router;
import com.example.wayfindr.data.SharedLocation;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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
/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$computeRoute$1", f = "RouteViewModel.kt", i = {1}, l = {265, 278}, m = "invokeSuspend", n = {"origin"}, s = {"L$0"})
/* loaded from: classes4.dex */
final class RouteViewModel$computeRoute$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedLocation $destination;
    final /* synthetic */ long $requestId;
    final /* synthetic */ List<GeoPoint> $waypoints;
    Object L$0;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$computeRoute$1(RouteViewModel routeViewModel, long j, SharedLocation sharedLocation, List<GeoPoint> list, Continuation<? super RouteViewModel$computeRoute$1> continuation) {
        super(2, continuation);
        this.this$0 = routeViewModel;
        this.$requestId = j;
        this.$destination = sharedLocation;
        this.$waypoints = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteViewModel$computeRoute$1(this.this$0, this.$requestId, this.$destination, this.$waypoints, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteViewModel$computeRoute$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0072, code lost:
    
        if (r4 != false) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0040, code lost:
    
        if (r2 == null) goto L15;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x016d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Location origin;
        Object withTimeoutOrNull;
        boolean isPreciseRouteOrigin;
        long j;
        long j2;
        Object alternatives;
        boolean isFallbackRouteOrigin;
        long j3;
        long j4;
        RouteState copy$default;
        long j5;
        String fmtKm;
        String fmtDuration;
        String fmtEta;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                origin = this.this$0.locationTracker.lastKnown();
                if (origin != null) {
                    isPreciseRouteOrigin = this.this$0.isPreciseRouteOrigin(origin);
                    if (!isPreciseRouteOrigin) {
                        origin = null;
                        break;
                    }
                }
                this.label = 1;
                withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(12000L, new RouteViewModel$computeRoute$1$origin$2(this.this$0, null), this);
                if (withTimeoutOrNull == coroutine_suspended) {
                    return coroutine_suspended;
                }
                origin = (Location) withTimeoutOrNull;
                if (origin == null) {
                    origin = this.this$0.locationTracker.lastKnown();
                    if (origin != null) {
                        isFallbackRouteOrigin = this.this$0.isFallbackRouteOrigin(origin);
                        break;
                    }
                    origin = null;
                }
                j = this.$requestId;
                j2 = this.this$0.routeRequestGeneration;
                if (j == j2) {
                    return Unit.INSTANCE;
                }
                RouteViewModel routeViewModel = this.this$0;
                if (origin == null) {
                    routeViewModel._state.setValue(RouteState.copy$default((RouteState) this.this$0._state.getValue(), null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, "Güncel GPS konumu alınamadı; açık gökyüzünde tekrar deneyin.", null, false, 113663, null));
                    return Unit.INSTANCE;
                }
                routeViewModel._state.setValue(RouteState.copy$default((RouteState) this.this$0._state.getValue(), null, null, TuplesKt.to(Boxing.boxDouble(origin.getLatitude()), Boxing.boxDouble(origin.getLongitude())), 0.0f, 0L, false, false, null, null, 0, true, null, null, null, null, null, false, 130043, null));
                Router router = Router.INSTANCE;
                Application application = this.this$0.getApplication();
                GeoPoint geoPoint = new GeoPoint(origin.getLatitude(), origin.getLongitude());
                Double lat = this.$destination.getLat();
                Intrinsics.checkNotNull(lat);
                double doubleValue = lat.doubleValue();
                Double lng = this.$destination.getLng();
                Intrinsics.checkNotNull(lng);
                this.L$0 = SpillingKt.nullOutSpilledVariable(origin);
                this.label = 2;
                alternatives = router.alternatives(application, geoPoint, new GeoPoint(doubleValue, lng.doubleValue()), this.$waypoints, this);
                if (alternatives == coroutine_suspended) {
                    return coroutine_suspended;
                }
                List routes = (List) alternatives;
                j3 = this.$requestId;
                j4 = this.this$0.routeRequestGeneration;
                if (j3 == j4) {
                    return Unit.INSTANCE;
                }
                Route r = (Route) CollectionsKt.firstOrNull(routes);
                MutableStateFlow mutableStateFlow = this.this$0._state;
                RouteViewModel routeViewModel2 = this.this$0;
                if (r != null) {
                    RouteState routeState = (RouteState) routeViewModel2._state.getValue();
                    fmtKm = this.this$0.fmtKm(r.getTotalMeters());
                    fmtDuration = this.this$0.fmtDuration(r.getTotalSeconds());
                    fmtEta = this.this$0.fmtEta(r.getTotalSeconds());
                    copy$default = RouteState.copy$default(routeState, null, null, null, 0.0f, 0L, false, false, r, routes, 0, false, fmtKm, fmtDuration, fmtEta, null, null, false, 98431, null);
                } else {
                    copy$default = RouteState.copy$default((RouteState) routeViewModel2._state.getValue(), null, null, null, 0.0f, 0L, false, false, null, CollectionsKt.emptyList(), 0, false, null, null, null, "Rota bulunamadı. İnternet bağlantınızı ve varış noktasını kontrol edin.", null, false, 112767, null);
                }
                mutableStateFlow.setValue(copy$default);
                long j6 = this.$requestId;
                j5 = this.this$0.routeRequestGeneration;
                if (j6 == j5) {
                    this.this$0.routeRequestJob = null;
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                withTimeoutOrNull = $result;
                origin = (Location) withTimeoutOrNull;
                if (origin == null) {
                }
                j = this.$requestId;
                j2 = this.this$0.routeRequestGeneration;
                if (j == j2) {
                }
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                alternatives = $result;
                List routes2 = (List) alternatives;
                j3 = this.$requestId;
                j4 = this.this$0.routeRequestGeneration;
                if (j3 == j4) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
