package com.example.wayfindr.ui.components;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.MarkerState;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: WayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.WayfindrMapKt$GoogleWayfindrMap$8$3$1$1", f = "WayfindrMap.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class WayfindrMapKt$GoogleWayfindrMap$8$3$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MarkerState $markerState;
    final /* synthetic */ Pair<Double, Double> $point;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WayfindrMapKt$GoogleWayfindrMap$8$3$1$1(MarkerState markerState, Pair<Double, Double> pair, Continuation<? super WayfindrMapKt$GoogleWayfindrMap$8$3$1$1> continuation) {
        super(2, continuation);
        this.$markerState = markerState;
        this.$point = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WayfindrMapKt$GoogleWayfindrMap$8$3$1$1(this.$markerState, this.$point, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WayfindrMapKt$GoogleWayfindrMap$8$3$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.$markerState.setPosition(new LatLng(this.$point.getFirst().doubleValue(), this.$point.getSecond().doubleValue()));
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
