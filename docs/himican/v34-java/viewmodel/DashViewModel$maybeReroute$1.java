package com.example.wayfindr.viewmodel;

import android.location.Location;
import android.util.Log;
import com.example.wayfindr.dash.map.OffRouteConfirmation;
import com.example.wayfindr.dash.map.TileProvider;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.Router;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.List;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: DashViewModel.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class DashViewModel$maybeReroute$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Double $dLat;
    final /* synthetic */ Double $dLng;
    final /* synthetic */ Location $loc;
    final /* synthetic */ long $requestId;
    final /* synthetic */ long $startMs;
    final /* synthetic */ int $stopIndexSnapshot;
    final /* synthetic */ List $waypointSnapshot;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$maybeReroute$1(DashViewModel dashViewModel, Location location, Double d, Double d2, List list, long j, int i, long j2, Continuation continuation) {
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

    public final Continuation create(Object obj, Continuation continuation) {
        return new DashViewModel$maybeReroute$1(this.this$0, this.$loc, this.$dLat, this.$dLng, this.$waypointSnapshot, this.$requestId, this.$stopIndexSnapshot, this.$startMs, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        long j;
        Object alternatives;
        long j2;
        long j3;
        int i;
        int i2;
        OffRouteConfirmation offRouteConfirmation;
        TileProvider tileProvider;
        MutableStateFlow mutableStateFlow;
        Object value;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    NavPlus.quietAlts();
                    alternatives = Router.INSTANCE.alternatives(this.this$0.getApplication(), new GeoPoint(this.$loc.getLatitude(), this.$loc.getLongitude()), new GeoPoint(this.$dLat.doubleValue(), this.$dLng.doubleValue()), this.$waypointSnapshot, (Continuation) this);
                    if (alternatives == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure(obj);
                    alternatives = obj;
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Route pickPlanned = NavPlus.pickPlanned(alternatives, this.this$0, 0);
            long j4 = this.$requestId;
            j2 = this.this$0.routeRequestGeneration;
            if (j4 == j2) {
                i = this.this$0.activeStopIndex;
                if (i == this.$stopIndexSnapshot) {
                    long currentTimeMillis = System.currentTimeMillis() - this.$startMs;
                    if (pickPlanned != null) {
                        this.this$0.route = pickPlanned;
                        this.this$0.lastTrafficRefreshAt = System.currentTimeMillis();
                        this.this$0.progressM = 0.0d;
                        DashViewModel dashViewModel = this.this$0;
                        i2 = this.this$0.activeStopIndex;
                        dashViewModel.routeLegOffset = i2;
                        this.this$0.offRouteSince = 0L;
                        offRouteConfirmation = this.this$0.offRouteConfirmation;
                        offRouteConfirmation.reset();
                        tileProvider = this.this$0.tiles;
                        tileProvider.prefetchRoute(pickPlanned.getGeometry());
                        mutableStateFlow = this.this$0._ui;
                        do {
                            value = mutableStateFlow.getValue();
                        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, true, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, pickPlanned.getGeometry(), pickPlanned.getTraffic(), 1073725439, (Object) null)));
                        Log.i("DashViewModel", "Reroute ok: " + pickPlanned.getGeometry().size() + " pts, " + ((int) pickPlanned.getTotalMeters()) + " m");
                        RideDiagnostics.INSTANCE.log("reroute", "new route in " + currentTimeMillis + "ms (" + pickPlanned.getGeometry().size() + " pts, " + pickPlanned.getTotalMeters() + " m)");
                    } else {
                        Log.w("DashViewModel", "Reroute failed (no internet?)");
                        RideDiagnostics.INSTANCE.log("reroute", "FAILED after " + currentTimeMillis + "ms (no internet?)");
                    }
                    return Unit.INSTANCE;
                }
            }
            Unit unit = Unit.INSTANCE;
            long j5 = this.$requestId;
            j3 = this.this$0.routeRequestGeneration;
            if (j5 == j3) {
                this.this$0.rerouting = false;
                this.this$0.routeRequestJob = null;
            }
            return unit;
        } finally {
            long j6 = this.$requestId;
            j = this.this$0.routeRequestGeneration;
            if (j6 == j) {
                this.this$0.rerouting = false;
                this.this$0.routeRequestJob = null;
            }
        }
    }
}
