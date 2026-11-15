package com.example.wayfindr.ui.components;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.MarkerState;
import kotlin.Metadata;
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
@DebugMetadata(c = "com.example.wayfindr.ui.components.WayfindrMapKt$GoogleWayfindrMap$1$1", f = "WayfindrMap.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class WayfindrMapKt$GoogleWayfindrMap$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Double $riderLat;
    final /* synthetic */ Double $riderLng;
    final /* synthetic */ MarkerState $riderMarkerState;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WayfindrMapKt$GoogleWayfindrMap$1$1(Double d, Double d2, MarkerState markerState, Continuation<? super WayfindrMapKt$GoogleWayfindrMap$1$1> continuation) {
        super(2, continuation);
        this.$riderLat = d;
        this.$riderLng = d2;
        this.$riderMarkerState = markerState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WayfindrMapKt$GoogleWayfindrMap$1$1(this.$riderLat, this.$riderLng, this.$riderMarkerState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WayfindrMapKt$GoogleWayfindrMap$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$riderLat != null && this.$riderLng != null) {
                    this.$riderMarkerState.setPosition(new LatLng(this.$riderLat.doubleValue(), this.$riderLng.doubleValue()));
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
