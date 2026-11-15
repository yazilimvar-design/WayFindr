package com.example.wayfindr.viewmodel;

import android.app.Application;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.nav.Router;
import com.example.wayfindr.data.SharedLocation;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.maplibre.android.style.layers.Property;

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$search$1", f = "RouteViewModel.kt", i = {0}, l = {379}, m = "invokeSuspend", n = {Property.SYMBOL_Z_ORDER_SOURCE}, s = {"L$0"})
/* loaded from: classes4.dex */
final class RouteViewModel$search$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $query;
    Object L$0;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$search$1(RouteViewModel routeViewModel, String str, Continuation<? super RouteViewModel$search$1> continuation) {
        super(2, continuation);
        this.this$0 = routeViewModel;
        this.$query = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteViewModel$search$1(this.this$0, this.$query, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteViewModel$search$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableStateFlow mutableStateFlow;
        Object search;
        RoutingSource source;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                RoutingSource source2 = Router.INSTANCE.getSource();
                PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                Application application = this.this$0.getApplication();
                String str = this.$query;
                Pair<Double, Double> currentLocation = ((RouteState) this.this$0._state.getValue()).getCurrentLocation();
                mutableStateFlow = this.this$0._saved;
                this.L$0 = source2;
                this.label = 1;
                search = placeSearch.search(application, str, source2, currentLocation, (List) mutableStateFlow.getValue(), this);
                if (search == coroutine_suspended) {
                    return coroutine_suspended;
                }
                source = source2;
                break;
            case 1:
                source = (RoutingSource) this.L$0;
                ResultKt.throwOnFailure($result);
                search = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        PlaceSearch.Hit hit = (PlaceSearch.Hit) search;
        RouteViewModel routeViewModel = this.this$0;
        if (hit == null) {
            MutableStateFlow mutableStateFlow2 = routeViewModel._state;
            RouteState routeState = (RouteState) this.this$0._state.getValue();
            String label = PlaceSearch.INSTANCE.label(source);
            String lastError = PlaceSearch.INSTANCE.getLastError();
            if (lastError == null) {
                lastError = "no match";
            }
            mutableStateFlow2.setValue(RouteState.copy$default(routeState, null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, label + ": " + lastError, null, false, 114655, null));
        } else {
            RouteViewModel.addWaypoint$default(routeViewModel, new SharedLocation(hit.getName(), Boxing.boxDouble(hit.getLat()), Boxing.boxDouble(hit.getLng()), null, false, 24, null), false, 2, null);
        }
        return Unit.INSTANCE;
    }
}
