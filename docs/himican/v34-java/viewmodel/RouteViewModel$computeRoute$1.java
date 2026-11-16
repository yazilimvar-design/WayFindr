package com.example.wayfindr.viewmodel;

import android.app.Application;
import android.location.Location;
import com.example.wayfindr.dash.map.LocationTracker;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.Router;
import com.example.wayfindr.data.SharedLocation;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: RouteViewModel.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class RouteViewModel$computeRoute$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SharedLocation $destination;
    final /* synthetic */ long $requestId;
    final /* synthetic */ List $waypoints;
    Object L$0;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$computeRoute$1(RouteViewModel routeViewModel, long j, SharedLocation sharedLocation, List list, Continuation continuation) {
        super(2, continuation);
        this.this$0 = routeViewModel;
        this.$requestId = j;
        this.$destination = sharedLocation;
        this.$waypoints = list;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new RouteViewModel$computeRoute$1(this.this$0, this.$requestId, this.$destination, this.$waypoints, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
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
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        LocationTracker locationTracker;
        Location lastKnown;
        Object withTimeoutOrNull;
        boolean isPreciseRouteOrigin;
        long j;
        long j2;
        MutableStateFlow mutableStateFlow;
        MutableStateFlow mutableStateFlow2;
        Object alternatives;
        MutableStateFlow mutableStateFlow3;
        MutableStateFlow mutableStateFlow4;
        LocationTracker locationTracker2;
        boolean isFallbackRouteOrigin;
        long j3;
        long j4;
        MutableStateFlow mutableStateFlow5;
        MutableStateFlow mutableStateFlow6;
        RouteState copy$default;
        long j5;
        MutableStateFlow mutableStateFlow7;
        String fmtKm;
        String fmtDuration;
        String fmtEta;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                locationTracker = this.this$0.locationTracker;
                lastKnown = locationTracker.lastKnown();
                if (lastKnown != null) {
                    isPreciseRouteOrigin = this.this$0.isPreciseRouteOrigin(lastKnown);
                    if (!isPreciseRouteOrigin) {
                        lastKnown = null;
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
                    locationTracker2 = this.this$0.locationTracker;
                    lastKnown = locationTracker2.lastKnownAny();
                    if (lastKnown != null) {
                        isFallbackRouteOrigin = this.this$0.isFallbackRouteOrigin(lastKnown);
                        break;
                    }
                    lastKnown = null;
                }
                j = this.$requestId;
                j2 = this.this$0.routeRequestGeneration;
                if (j == j2) {
                    return Unit.INSTANCE;
                }
                RouteViewModel routeViewModel = this.this$0;
                if (lastKnown == null) {
                    mutableStateFlow3 = routeViewModel._state;
                    mutableStateFlow4 = this.this$0._state;
                    mutableStateFlow3.setValue(RouteState.copy$default((RouteState) mutableStateFlow4.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, "Güncel GPS konumu alınamadı; açık gökyüzünde tekrar deneyin.", (String) null, false, 113663, (Object) null));
                    return Unit.INSTANCE;
                }
                mutableStateFlow = routeViewModel._state;
                mutableStateFlow2 = this.this$0._state;
                mutableStateFlow.setValue(RouteState.copy$default((RouteState) mutableStateFlow2.getValue(), (SharedLocation) null, (List) null, TuplesKt.to(Boxing.boxDouble(lastKnown.getLatitude()), Boxing.boxDouble(lastKnown.getLongitude())), 0.0f, 0L, false, false, (Route) null, (List) null, 0, true, (String) null, (String) null, (String) null, (String) null, (String) null, false, 130043, (Object) null));
                Router router = Router.INSTANCE;
                Application application = this.this$0.getApplication();
                GeoPoint geoPoint = new GeoPoint(lastKnown.getLatitude(), lastKnown.getLongitude());
                Double lat = this.$destination.getLat();
                Intrinsics.checkNotNull(lat);
                double doubleValue = lat.doubleValue();
                Double lng = this.$destination.getLng();
                Intrinsics.checkNotNull(lng);
                this.L$0 = SpillingKt.nullOutSpilledVariable(lastKnown);
                this.label = 2;
                alternatives = router.alternatives(application, geoPoint, new GeoPoint(doubleValue, lng.doubleValue()), this.$waypoints, (Continuation) this);
                if (alternatives == coroutine_suspended) {
                    return coroutine_suspended;
                }
                List list = (List) alternatives;
                j3 = this.$requestId;
                j4 = this.this$0.routeRequestGeneration;
                if (j3 == j4) {
                    return Unit.INSTANCE;
                }
                Route route = (Route) CollectionsKt.firstOrNull(list);
                mutableStateFlow5 = this.this$0._state;
                RouteViewModel routeViewModel2 = this.this$0;
                if (route != null) {
                    mutableStateFlow7 = routeViewModel2._state;
                    RouteState routeState = (RouteState) mutableStateFlow7.getValue();
                    fmtKm = this.this$0.fmtKm(route.getTotalMeters());
                    fmtDuration = this.this$0.fmtDuration(route.getTotalSeconds());
                    fmtEta = this.this$0.fmtEta(route.getTotalSeconds());
                    copy$default = RouteState.copy$default(routeState, (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, route, list, 0, false, fmtKm, fmtDuration, fmtEta, (String) null, (String) null, false, 98431, (Object) null);
                } else {
                    mutableStateFlow6 = routeViewModel2._state;
                    copy$default = RouteState.copy$default((RouteState) mutableStateFlow6.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, CollectionsKt.emptyList(), 0, false, (String) null, (String) null, (String) null, "Rota bulunamadı. İnternet bağlantınızı ve varış noktasını kontrol edin.", (String) null, false, 112767, (Object) null);
                }
                mutableStateFlow5.setValue(copy$default);
                long j6 = this.$requestId;
                j5 = this.this$0.routeRequestGeneration;
                if (j6 == j5) {
                    this.this$0.routeRequestJob = null;
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure(obj);
                withTimeoutOrNull = obj;
                lastKnown = (Location) withTimeoutOrNull;
                if (lastKnown == null) {
                }
                j = this.$requestId;
                j2 = this.this$0.routeRequestGeneration;
                if (j == j2) {
                }
                break;
            case 2:
                ResultKt.throwOnFailure(obj);
                alternatives = obj;
                List list2 = (List) alternatives;
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
