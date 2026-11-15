package com.example.wayfindr.ui.components;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.CameraPositionState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: WayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.WayfindrMapKt$GoogleWayfindrMap$6$1", f = "WayfindrMap.kt", i = {0, 0}, l = {WebSocketProtocol.PAYLOAD_SHORT}, m = "invokeSuspend", n = {"target", "position"}, s = {"L$0", "L$1"})
/* loaded from: classes8.dex */
final class WayfindrMapKt$GoogleWayfindrMap$6$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CameraPositionState $cameraPositionState;
    final /* synthetic */ boolean $followRider;
    final /* synthetic */ boolean $navMode;
    final /* synthetic */ float $riderBearing;
    final /* synthetic */ Double $riderLat;
    final /* synthetic */ Double $riderLng;
    Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WayfindrMapKt$GoogleWayfindrMap$6$1(boolean z, Double d, Double d2, boolean z2, float f, CameraPositionState cameraPositionState, Continuation<? super WayfindrMapKt$GoogleWayfindrMap$6$1> continuation) {
        super(2, continuation);
        this.$followRider = z;
        this.$riderLat = d;
        this.$riderLng = d2;
        this.$navMode = z2;
        this.$riderBearing = f;
        this.$cameraPositionState = cameraPositionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WayfindrMapKt$GoogleWayfindrMap$6$1(this.$followRider, this.$riderLat, this.$riderLng, this.$navMode, this.$riderBearing, this.$cameraPositionState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WayfindrMapKt$GoogleWayfindrMap$6$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$followRider && this.$riderLat != null && this.$riderLng != null) {
                    LatLng target = new LatLng(this.$riderLat.doubleValue(), this.$riderLng.doubleValue());
                    CameraPosition position = new CameraPosition.Builder().target(target).zoom(15.5f).bearing(this.$navMode ? this.$riderBearing : 0.0f).tilt(this.$navMode ? 45.0f : 0.0f).build();
                    Intrinsics.checkNotNullExpressionValue(position, "build(...)");
                    CameraPositionState cameraPositionState = this.$cameraPositionState;
                    CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(position);
                    Intrinsics.checkNotNullExpressionValue(newCameraPosition, "newCameraPosition(...)");
                    this.L$0 = SpillingKt.nullOutSpilledVariable(target);
                    this.L$1 = SpillingKt.nullOutSpilledVariable(position);
                    this.label = 1;
                    if (cameraPositionState.animate(newCameraPosition, 600, this) != coroutine_suspended) {
                        break;
                    } else {
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
