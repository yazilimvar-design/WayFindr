package com.example.wayfindr.ui.components;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.compose.CameraPositionState;
import kotlin.Metadata;
import kotlin.Pair;
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

/* compiled from: WayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.WayfindrMapKt$GoogleWayfindrMap$7$1", f = "WayfindrMap.kt", i = {0}, l = {142, 145}, m = "invokeSuspend", n = {"position"}, s = {"L$0"})
/* loaded from: classes8.dex */
final class WayfindrMapKt$GoogleWayfindrMap$7$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Float $camBearing;
    final /* synthetic */ Double $camLat;
    final /* synthetic */ Double $camLng;
    final /* synthetic */ Float $camZoom;
    final /* synthetic */ CameraPositionState $cameraPositionState;
    final /* synthetic */ Pair<Double, Double> $dest;
    final /* synthetic */ boolean $fitRoute;
    final /* synthetic */ boolean $followRider;
    final /* synthetic */ boolean $navMode;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WayfindrMapKt$GoogleWayfindrMap$7$1(Double d, Double d2, Float f, Float f2, boolean z, CameraPositionState cameraPositionState, boolean z2, boolean z3, Pair<Double, Double> pair, Continuation<? super WayfindrMapKt$GoogleWayfindrMap$7$1> continuation) {
        super(2, continuation);
        this.$camLat = d;
        this.$camLng = d2;
        this.$camZoom = f;
        this.$camBearing = f2;
        this.$navMode = z;
        this.$cameraPositionState = cameraPositionState;
        this.$followRider = z2;
        this.$fitRoute = z3;
        this.$dest = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WayfindrMapKt$GoogleWayfindrMap$7$1(this.$camLat, this.$camLng, this.$camZoom, this.$camBearing, this.$navMode, this.$cameraPositionState, this.$followRider, this.$fitRoute, this.$dest, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WayfindrMapKt$GoogleWayfindrMap$7$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$camLat != null && this.$camLng != null) {
                    CameraPosition.Builder target = new CameraPosition.Builder().target(new LatLng(this.$camLat.doubleValue(), this.$camLng.doubleValue()));
                    Float f = this.$camZoom;
                    CameraPosition.Builder zoom = target.zoom(f != null ? f.floatValue() : 15.5f);
                    Float f2 = this.$camBearing;
                    CameraPosition position = zoom.bearing(f2 != null ? f2.floatValue() : 0.0f).tilt(this.$navMode ? 45.0f : 0.0f).build();
                    Intrinsics.checkNotNullExpressionValue(position, "build(...)");
                    CameraPositionState cameraPositionState = this.$cameraPositionState;
                    CameraUpdate newCameraPosition = CameraUpdateFactory.newCameraPosition(position);
                    Intrinsics.checkNotNullExpressionValue(newCameraPosition, "newCameraPosition(...)");
                    this.L$0 = SpillingKt.nullOutSpilledVariable(position);
                    this.label = 1;
                    if (cameraPositionState.animate(newCameraPosition, 400, this) != coroutine_suspended) {
                        break;
                    } else {
                        return coroutine_suspended;
                    }
                } else if (!this.$followRider && !this.$fitRoute && this.$dest != null) {
                    CameraPositionState cameraPositionState2 = this.$cameraPositionState;
                    CameraUpdate newLatLngZoom = CameraUpdateFactory.newLatLngZoom(new LatLng(this.$dest.getFirst().doubleValue(), this.$dest.getSecond().doubleValue()), 13.0f);
                    Intrinsics.checkNotNullExpressionValue(newLatLngZoom, "newLatLngZoom(...)");
                    this.label = 2;
                    if (cameraPositionState2.animate(newLatLngZoom, 800, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            case 2:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
