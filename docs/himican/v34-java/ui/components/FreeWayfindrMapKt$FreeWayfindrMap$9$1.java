package com.example.wayfindr.ui.components;

import androidx.compose.runtime.MutableState;
import java.util.concurrent.atomic.AtomicBoolean;
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
import org.maplibre.android.camera.CameraPosition;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.maps.MapLibreMap;

/* compiled from: FreeWayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$9$1", f = "FreeWayfindrMap.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class FreeWayfindrMapKt$FreeWayfindrMap$9$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Float $camBearing;
    final /* synthetic */ Double $camLat;
    final /* synthetic */ Double $camLng;
    final /* synthetic */ Float $camZoom;
    final /* synthetic */ Pair<Double, Double> $dest;
    final /* synthetic */ AtomicBoolean $destroyed;
    final /* synthetic */ boolean $fitRoute;
    final /* synthetic */ boolean $followRider;
    final /* synthetic */ MutableState<MapLibreMap> $map$delegate;
    final /* synthetic */ boolean $navMode;
    final /* synthetic */ MutableState<Boolean> $styleReady$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FreeWayfindrMapKt$FreeWayfindrMap$9$1(Double d, Double d2, Float f, Float f2, boolean z, boolean z2, boolean z3, Pair<Double, Double> pair, AtomicBoolean atomicBoolean, MutableState<Boolean> mutableState, MutableState<MapLibreMap> mutableState2, Continuation<? super FreeWayfindrMapKt$FreeWayfindrMap$9$1> continuation) {
        super(2, continuation);
        this.$camLat = d;
        this.$camLng = d2;
        this.$camZoom = f;
        this.$camBearing = f2;
        this.$navMode = z;
        this.$followRider = z2;
        this.$fitRoute = z3;
        this.$dest = pair;
        this.$destroyed = atomicBoolean;
        this.$styleReady$delegate = mutableState;
        this.$map$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FreeWayfindrMapKt$FreeWayfindrMap$9$1(this.$camLat, this.$camLng, this.$camZoom, this.$camBearing, this.$navMode, this.$followRider, this.$fitRoute, this.$dest, this.$destroyed, this.$styleReady$delegate, this.$map$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FreeWayfindrMapKt$FreeWayfindrMap$9$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (this.$camLat != null && this.$camLng != null) {
                    FreeWayfindrMapKt.FreeWayfindrMap$animate(this.$destroyed, this.$styleReady$delegate, this.$map$delegate, CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(new LatLng(this.$camLat.doubleValue(), this.$camLng.doubleValue())).zoom((this.$camZoom != null ? r4.floatValue() : 15.5d) - 1.0d).bearing(this.$camBearing != null ? r4.floatValue() : 0.0d).tilt(this.$navMode ? 45.0d : 0.0d).build()), 400);
                } else if (!this.$followRider && !this.$fitRoute && this.$dest != null) {
                    FreeWayfindrMapKt.FreeWayfindrMap$animate(this.$destroyed, this.$styleReady$delegate, this.$map$delegate, CameraUpdateFactory.newLatLngZoom(new LatLng(this.$dest.getFirst().doubleValue(), this.$dest.getSecond().doubleValue()), 12.0d), 800);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
