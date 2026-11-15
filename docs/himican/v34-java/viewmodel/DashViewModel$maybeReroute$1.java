package com.example.wayfindr.viewmodel;

import android.location.Location;
import android.util.Log;
import com.example.wayfindr.dash.map.TileProvider;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.Router;
import com.example.wayfindr.data.RideDiagnostics;
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

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$maybeReroute$1", f = "DashViewModel.kt", i = {}, l = {1789}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class DashViewModel$maybeReroute$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Double $dLat;
    final /* synthetic */ Double $dLng;
    final /* synthetic */ Location $loc;
    final /* synthetic */ long $requestId;
    final /* synthetic */ long $startMs;
    final /* synthetic */ int $stopIndexSnapshot;
    final /* synthetic */ List<GeoPoint> $waypointSnapshot;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$maybeReroute$1(DashViewModel dashViewModel, Location location, Double d, Double d2, List<GeoPoint> list, long j, int i, long j2, Continuation<? super DashViewModel$maybeReroute$1> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
        this.$loc = location;
        this.$dLat = d;
        this.$dLng = d2;
        this.$waypointSnapshot = list;
        this.$requestId = j;
        this.$stopIndexSnapshot = i;
        this.$startMs = j2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashViewModel$maybeReroute$1(this.this$0, this.$loc, this.$dLat, this.$dLng, this.$waypointSnapshot, this.$requestId, this.$stopIndexSnapshot, this.$startMs, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$maybeReroute$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object route;
        int i;
        int i2;
        TileProvider tileProvider;
        Object value;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    this.label = 1;
                    route = Router.INSTANCE.route(this.this$0.getApplication(), new GeoPoint(this.$loc.getLatitude(), this.$loc.getLongitude()), new GeoPoint(this.$dLat.doubleValue(), this.$dLng.doubleValue()), this.$waypointSnapshot, this);
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
            Route r = (Route) route;
            if (this.$requestId == this.this$0.routeRequestGeneration) {
                i = this.this$0.activeStopIndex;
                if (i == this.$stopIndexSnapshot) {
                    long took = System.currentTimeMillis() - this.$startMs;
                    if (r != null) {
                        this.this$0.route = r;
                        this.this$0.lastTrafficRefreshAt = System.currentTimeMillis();
                        this.this$0.progressM = 0.0d;
                        DashViewModel dashViewModel = this.this$0;
                        i2 = this.this$0.activeStopIndex;
                        dashViewModel.routeLegOffset = i2;
                        this.this$0.offRouteSince = 0L;
                        this.this$0.offRouteConfirmation.reset();
                        tileProvider = this.this$0.tiles;
                        tileProvider.prefetchRoute(r.getGeometry());
                        MutableStateFlow mutableStateFlow = this.this$0._ui;
                        do {
                            value = mutableStateFlow.getValue();
                        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, true, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, r.getGeometry(), r.getTraffic(), 1073725439, null)));
                        Log.i("DashViewModel", "Reroute ok: " + r.getGeometry().size() + " pts, " + ((int) r.getTotalMeters()) + " m");
                        RideDiagnostics.INSTANCE.log("reroute", "new route in " + took + "ms (" + r.getGeometry().size() + " pts, " + r.getTotalMeters() + " m)");
                    } else {
                        Log.w("DashViewModel", "Reroute failed (no internet?)");
                        RideDiagnostics.INSTANCE.log("reroute", "FAILED after " + took + "ms (no internet?)");
                    }
                    return Unit.INSTANCE;
                }
            }
            Unit unit = Unit.INSTANCE;
            if (this.$requestId == this.this$0.routeRequestGeneration) {
                this.this$0.rerouting = false;
                this.this$0.routeRequestJob = null;
            }
            return unit;
        } finally {
            if (this.$requestId == this.this$0.routeRequestGeneration) {
                this.this$0.rerouting = false;
                this.this$0.routeRequestJob = null;
            }
        }
    }
}
