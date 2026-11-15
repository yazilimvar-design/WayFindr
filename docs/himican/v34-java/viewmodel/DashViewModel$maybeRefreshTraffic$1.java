package com.example.wayfindr.viewmodel;

import android.location.Location;
import android.util.Log;
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
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$maybeRefreshTraffic$1", f = "DashViewModel.kt", i = {}, l = {1882}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class DashViewModel$maybeRefreshTraffic$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $finalLat;
    final /* synthetic */ double $finalLng;
    final /* synthetic */ Location $loc;
    final /* synthetic */ long $requestId;
    final /* synthetic */ int $stopIndexSnapshot;
    final /* synthetic */ List<GeoPoint> $waypointSnapshot;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$maybeRefreshTraffic$1(DashViewModel dashViewModel, Location location, double d, double d2, List<GeoPoint> list, long j, int i, Continuation<? super DashViewModel$maybeRefreshTraffic$1> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
        this.$loc = location;
        this.$finalLat = d;
        this.$finalLng = d2;
        this.$waypointSnapshot = list;
        this.$requestId = j;
        this.$stopIndexSnapshot = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashViewModel$maybeRefreshTraffic$1(this.this$0, this.$loc, this.$finalLat, this.$finalLng, this.$waypointSnapshot, this.$requestId, this.$stopIndexSnapshot, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$maybeRefreshTraffic$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object route;
        int i;
        int i2;
        Object value;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    route = Router.INSTANCE.route(this.this$0.getApplication(), new GeoPoint(this.$loc.getLatitude(), this.$loc.getLongitude()), new GeoPoint(this.$finalLat, this.$finalLng), this.$waypointSnapshot, this);
                    if (route == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    route = $result;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Route refreshed = (Route) route;
            if (this.$requestId == this.this$0.routeRequestGeneration) {
                i = this.this$0.activeStopIndex;
                if (i == this.$stopIndexSnapshot) {
                    if (refreshed != null) {
                        this.this$0.route = refreshed;
                        this.this$0.progressM = 0.0d;
                        DashViewModel dashViewModel = this.this$0;
                        i2 = this.this$0.activeStopIndex;
                        dashViewModel.routeLegOffset = i2;
                        MutableStateFlow mutableStateFlow = this.this$0._ui;
                        do {
                            value = mutableStateFlow.getValue();
                        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, refreshed.getGeometry(), refreshed.getTraffic(), LockFreeTaskQueueCore.MAX_CAPACITY_MASK, null)));
                        Log.i("DashViewModel", "Traffic refreshed: " + refreshed.getTraffic().size() + " route intervals");
                    }
                    return Unit.INSTANCE;
                }
            }
            Unit unit = Unit.INSTANCE;
            if (this.$requestId == this.this$0.routeRequestGeneration) {
                this.this$0.trafficRefreshInFlight = false;
                this.this$0.routeRequestJob = null;
            }
            return unit;
        } finally {
            if (this.$requestId == this.this$0.routeRequestGeneration) {
                this.this$0.trafficRefreshInFlight = false;
                this.this$0.routeRequestJob = null;
            }
        }
    }
}
