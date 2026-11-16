package com.example.wayfindr.viewmodel;

import android.location.Location;
import android.util.Log;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.Router;
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
final class DashViewModel$maybeRefreshTraffic$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ double $finalLat;
    final /* synthetic */ double $finalLng;
    final /* synthetic */ Location $loc;
    final /* synthetic */ long $requestId;
    final /* synthetic */ int $stopIndexSnapshot;
    final /* synthetic */ List $waypointSnapshot;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$maybeRefreshTraffic$1(DashViewModel dashViewModel, Location location, double d, double d2, List list, long j, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
        this.$loc = location;
        this.$finalLat = d;
        this.$finalLng = d2;
        this.$waypointSnapshot = list;
        this.$requestId = j;
        this.$stopIndexSnapshot = i;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new DashViewModel$maybeRefreshTraffic$1(this.this$0, this.$loc, this.$finalLat, this.$finalLng, this.$waypointSnapshot, this.$requestId, this.$stopIndexSnapshot, continuation);
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
        MutableStateFlow mutableStateFlow;
        Object value;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        try {
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    alternatives = Router.INSTANCE.alternatives(this.this$0.getApplication(), new GeoPoint(this.$loc.getLatitude(), this.$loc.getLongitude()), new GeoPoint(this.$finalLat, this.$finalLng), this.$waypointSnapshot, (Continuation) this);
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
            Route pickPlanned = NavPlus.pickPlanned(alternatives, this.this$0, 1);
            long j4 = this.$requestId;
            j2 = this.this$0.routeRequestGeneration;
            if (j4 == j2) {
                i = this.this$0.activeStopIndex;
                if (i == this.$stopIndexSnapshot) {
                    if (pickPlanned != null) {
                        this.this$0.route = pickPlanned;
                        this.this$0.progressM = 0.0d;
                        DashViewModel dashViewModel = this.this$0;
                        i2 = this.this$0.activeStopIndex;
                        dashViewModel.routeLegOffset = i2;
                        mutableStateFlow = this.this$0._ui;
                        do {
                            value = mutableStateFlow.getValue();
                        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, pickPlanned.getGeometry(), pickPlanned.getTraffic(), 1073741823, (Object) null)));
                        Log.i("DashViewModel", "Traffic refreshed: " + pickPlanned.getTraffic().size() + " route intervals");
                    }
                    return Unit.INSTANCE;
                }
            }
            Unit unit = Unit.INSTANCE;
            long j5 = this.$requestId;
            j3 = this.this$0.routeRequestGeneration;
            if (j5 == j3) {
                this.this$0.trafficRefreshInFlight = false;
                this.this$0.routeRequestJob = null;
            }
            return unit;
        } finally {
            long j6 = this.$requestId;
            j = this.this$0.routeRequestGeneration;
            if (j6 == j) {
                this.this$0.trafficRefreshInFlight = false;
                this.this$0.routeRequestJob = null;
            }
        }
    }
}
