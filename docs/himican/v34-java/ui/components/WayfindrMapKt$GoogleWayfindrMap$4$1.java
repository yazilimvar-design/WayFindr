package com.example.wayfindr.ui.components;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.CameraPositionState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: WayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.WayfindrMapKt$GoogleWayfindrMap$4$1", f = "WayfindrMap.kt", i = {}, l = {97}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class WayfindrMapKt$GoogleWayfindrMap$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CameraPositionState $cameraPositionState;
    final /* synthetic */ boolean $centerOnRiderInitially;
    final /* synthetic */ long $recenterKey;
    final /* synthetic */ Double $riderLat;
    final /* synthetic */ Double $riderLng;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WayfindrMapKt$GoogleWayfindrMap$4$1(boolean z, Double d, Double d2, long j, CameraPositionState cameraPositionState, Continuation<? super WayfindrMapKt$GoogleWayfindrMap$4$1> continuation) {
        super(2, continuation);
        this.$centerOnRiderInitially = z;
        this.$riderLat = d;
        this.$riderLng = d2;
        this.$recenterKey = j;
        this.$cameraPositionState = cameraPositionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WayfindrMapKt$GoogleWayfindrMap$4$1(this.$centerOnRiderInitially, this.$riderLat, this.$riderLng, this.$recenterKey, this.$cameraPositionState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WayfindrMapKt$GoogleWayfindrMap$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$centerOnRiderInitially && this.$riderLat != null && this.$riderLng != null && this.$recenterKey == 0) {
                    CameraPositionState cameraPositionState = this.$cameraPositionState;
                    CameraUpdate newLatLngZoom = CameraUpdateFactory.newLatLngZoom(new LatLng(this.$riderLat.doubleValue(), this.$riderLng.doubleValue()), 15.5f);
                    Intrinsics.checkNotNullExpressionValue(newLatLngZoom, "newLatLngZoom(...)");
                    this.label = 1;
                    if (cameraPositionState.animate(newLatLngZoom, 500, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
