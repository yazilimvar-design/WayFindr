package com.example.wayfindr.viewmodel;

import com.example.wayfindr.data.SharedLocation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$selectLocation$1", f = "RouteViewModel.kt", i = {}, l = {400}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class RouteViewModel$selectLocation$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $lat;
    final /* synthetic */ double $lng;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$selectLocation$1(RouteViewModel routeViewModel, double d, double d2, Continuation<? super RouteViewModel$selectLocation$1> continuation) {
        super(2, continuation);
        this.this$0 = routeViewModel;
        this.$lat = d;
        this.$lng = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteViewModel$selectLocation$1(this.this$0, this.$lat, this.$lng, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteViewModel$selectLocation$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object reverseGeocode;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                reverseGeocode = this.this$0.reverseGeocode(this.$lat, this.$lng, this);
                if (reverseGeocode == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                reverseGeocode = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String str = (String) reverseGeocode;
        if (str == null) {
            str = "Dropped pin";
        }
        String name = str;
        Iterable<SharedLocation> waypoints = ((RouteState) this.this$0._state.getValue()).getWaypoints();
        double d = this.$lat;
        double d2 = this.$lng;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(waypoints, 10));
        for (SharedLocation sharedLocation : waypoints) {
            if (Intrinsics.areEqual(sharedLocation.getLat(), d) && Intrinsics.areEqual(sharedLocation.getLng(), d2)) {
                sharedLocation = SharedLocation.copy$default(sharedLocation, name, null, null, null, false, 30, null);
            }
            arrayList.add(sharedLocation);
        }
        List stops = (List) arrayList;
        this.this$0._state.setValue(RouteState.copy$default((RouteState) this.this$0._state.getValue(), (SharedLocation) CollectionsKt.lastOrNull(stops), stops, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131036, null));
        this.this$0.computeRoute(stops);
        return Unit.INSTANCE;
    }
}
