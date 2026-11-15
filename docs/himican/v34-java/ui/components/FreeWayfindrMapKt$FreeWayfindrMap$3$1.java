package com.example.wayfindr.ui.components;

import android.graphics.Bitmap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.google.android.gms.maps.model.LatLng;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.maps.OnMapReadyCallback;
import org.maplibre.android.maps.Style;
import org.maplibre.android.maps.UiSettings;
import org.maplibre.android.plugins.annotation.LineManager;
import org.maplibre.android.plugins.annotation.SymbolManager;

/* compiled from: FreeWayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$3$1", f = "FreeWayfindrMap.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class FreeWayfindrMapKt$FreeWayfindrMap$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<Function1<LatLng, Unit>> $clickHandler$delegate;
    final /* synthetic */ AtomicBoolean $destroyed;
    final /* synthetic */ MutableState<LineManager> $lineMgr$delegate;
    final /* synthetic */ MutableState<MapLibreMap> $map$delegate;
    final /* synthetic */ MapView $mapView;
    final /* synthetic */ String $styleJson;
    final /* synthetic */ MutableState<Boolean> $styleReady$delegate;
    final /* synthetic */ MutableState<SymbolManager> $symbolMgr$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    FreeWayfindrMapKt$FreeWayfindrMap$3$1(MapView mapView, AtomicBoolean atomicBoolean, String str, MutableState<MapLibreMap> mutableState, State<? extends Function1<? super LatLng, Unit>> state, MutableState<LineManager> mutableState2, MutableState<SymbolManager> mutableState3, MutableState<Boolean> mutableState4, Continuation<? super FreeWayfindrMapKt$FreeWayfindrMap$3$1> continuation) {
        super(2, continuation);
        this.$mapView = mapView;
        this.$destroyed = atomicBoolean;
        this.$styleJson = str;
        this.$map$delegate = mutableState;
        this.$clickHandler$delegate = state;
        this.$lineMgr$delegate = mutableState2;
        this.$symbolMgr$delegate = mutableState3;
        this.$styleReady$delegate = mutableState4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FreeWayfindrMapKt$FreeWayfindrMap$3$1(this.$mapView, this.$destroyed, this.$styleJson, this.$map$delegate, this.$clickHandler$delegate, this.$lineMgr$delegate, this.$symbolMgr$delegate, this.$styleReady$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FreeWayfindrMapKt$FreeWayfindrMap$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                MapView mapView = this.$mapView;
                final AtomicBoolean atomicBoolean = this.$destroyed;
                final String str = this.$styleJson;
                final MutableState<MapLibreMap> mutableState = this.$map$delegate;
                final State<Function1<LatLng, Unit>> state = this.$clickHandler$delegate;
                final MapView mapView2 = this.$mapView;
                final MutableState<LineManager> mutableState2 = this.$lineMgr$delegate;
                final MutableState<SymbolManager> mutableState3 = this.$symbolMgr$delegate;
                final MutableState<Boolean> mutableState4 = this.$styleReady$delegate;
                mapView.getMapAsync(new OnMapReadyCallback() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$3$1$$ExternalSyntheticLambda0
                    @Override // org.maplibre.android.maps.OnMapReadyCallback
                    public final void onMapReady(MapLibreMap mapLibreMap) {
                        FreeWayfindrMapKt$FreeWayfindrMap$3$1.invokeSuspend$lambda$4(atomicBoolean, str, mutableState, state, mapView2, mutableState2, mutableState3, mutableState4, mapLibreMap);
                    }
                });
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final void invokeSuspend$lambda$4(final AtomicBoolean $destroyed, String $styleJson, MutableState $map$delegate, final State $clickHandler$delegate, final MapView $mapView, final MutableState $lineMgr$delegate, final MutableState $symbolMgr$delegate, final MutableState $styleReady$delegate, final MapLibreMap m) {
        if ($destroyed.get()) {
            return;
        }
        $map$delegate.setValue(m);
        UiSettings uiSettings = m.getUiSettings();
        uiSettings.setRotateGesturesEnabled(false);
        uiSettings.setTiltGesturesEnabled(false);
        uiSettings.setCompassEnabled(false);
        uiSettings.setAttributionEnabled(true);
        uiSettings.setLogoEnabled(false);
        m.addOnMapClickListener(new MapLibreMap.OnMapClickListener() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$3$1$$ExternalSyntheticLambda1
            @Override // org.maplibre.android.maps.MapLibreMap.OnMapClickListener
            public final boolean onMapClick(org.maplibre.android.geometry.LatLng latLng) {
                return FreeWayfindrMapKt$FreeWayfindrMap$3$1.invokeSuspend$lambda$4$lambda$1(State.this, latLng);
            }
        });
        Style.Builder builder = new Style.Builder().fromJson($styleJson);
        Intrinsics.checkNotNullExpressionValue(builder, "fromJson(...)");
        m.setStyle(builder, new Style.OnStyleLoaded() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$3$1$$ExternalSyntheticLambda2
            @Override // org.maplibre.android.maps.Style.OnStyleLoaded
            public final void onStyleLoaded(Style style) {
                FreeWayfindrMapKt$FreeWayfindrMap$3$1.invokeSuspend$lambda$4$lambda$3($destroyed, $mapView, m, $lineMgr$delegate, $symbolMgr$delegate, $styleReady$delegate, style);
            }
        });
    }

    static final boolean invokeSuspend$lambda$4$lambda$1(State $clickHandler$delegate, org.maplibre.android.geometry.LatLng p) {
        Function1 FreeWayfindrMap$lambda$20;
        Function1 FreeWayfindrMap$lambda$202;
        FreeWayfindrMap$lambda$20 = FreeWayfindrMapKt.FreeWayfindrMap$lambda$20($clickHandler$delegate);
        if (FreeWayfindrMap$lambda$20 != null) {
            FreeWayfindrMap$lambda$20.invoke(new LatLng(p.getLatitude(), p.getLongitude()));
        }
        FreeWayfindrMap$lambda$202 = FreeWayfindrMapKt.FreeWayfindrMap$lambda$20($clickHandler$delegate);
        return FreeWayfindrMap$lambda$202 != null;
    }

    static final void invokeSuspend$lambda$4$lambda$3(AtomicBoolean $destroyed, MapView $mapView, MapLibreMap $m, MutableState $lineMgr$delegate, MutableState $symbolMgr$delegate, MutableState $styleReady$delegate, Style style) {
        Bitmap freeChevronBitmap;
        Bitmap freeDestPinBitmap;
        if ($destroyed.get()) {
            return;
        }
        freeChevronBitmap = FreeWayfindrMapKt.freeChevronBitmap();
        style.addImage("rider-chevron", freeChevronBitmap);
        freeDestPinBitmap = FreeWayfindrMapKt.freeDestPinBitmap();
        style.addImage("dest-pin", freeDestPinBitmap);
        $lineMgr$delegate.setValue(new LineManager($mapView, $m, style));
        SymbolManager symbolManager = new SymbolManager($mapView, $m, style);
        symbolManager.setIconAllowOverlap(true);
        symbolManager.setIconIgnorePlacement(true);
        $symbolMgr$delegate.setValue(symbolManager);
        FreeWayfindrMapKt.FreeWayfindrMap$lambda$19($styleReady$delegate, true);
    }
}
