package com.example.wayfindr.ui.components;

import com.example.wayfindr.dash.nav.GeoPoint;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.compose.CameraPositionState;
import java.util.List;
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

/* compiled from: WayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.WayfindrMapKt$GoogleWayfindrMap$5$1", f = "WayfindrMap.kt", i = {0}, l = {111}, m = "invokeSuspend", n = {"bounds"}, s = {"L$0"})
/* loaded from: classes8.dex */
final class WayfindrMapKt$GoogleWayfindrMap$5$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CameraPositionState $cameraPositionState;
    final /* synthetic */ boolean $fitRoute;
    final /* synthetic */ List<GeoPoint> $routePoints;
    Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    WayfindrMapKt$GoogleWayfindrMap$5$1(boolean z, List<GeoPoint> list, CameraPositionState cameraPositionState, Continuation<? super WayfindrMapKt$GoogleWayfindrMap$5$1> continuation) {
        super(2, continuation);
        this.$fitRoute = z;
        this.$routePoints = list;
        this.$cameraPositionState = cameraPositionState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new WayfindrMapKt$GoogleWayfindrMap$5$1(this.$fitRoute, this.$routePoints, this.$cameraPositionState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((WayfindrMapKt$GoogleWayfindrMap$5$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$fitRoute && this.$routePoints.size() >= 2) {
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (GeoPoint geoPoint : this.$routePoints) {
                        builder.include(new LatLng(geoPoint.getLat(), geoPoint.getLng()));
                    }
                    LatLngBounds bounds = builder.build();
                    Intrinsics.checkNotNullExpressionValue(bounds, "build(...)");
                    CameraPositionState cameraPositionState = this.$cameraPositionState;
                    CameraUpdate newLatLngBounds = CameraUpdateFactory.newLatLngBounds(bounds, 110);
                    Intrinsics.checkNotNullExpressionValue(newLatLngBounds, "newLatLngBounds(...)");
                    this.L$0 = SpillingKt.nullOutSpilledVariable(bounds);
                    this.label = 1;
                    if (cameraPositionState.animate(newLatLngBounds, 800, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
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
