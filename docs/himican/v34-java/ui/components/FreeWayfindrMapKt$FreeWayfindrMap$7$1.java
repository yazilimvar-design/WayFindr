package com.example.wayfindr.ui.components;

import androidx.compose.runtime.MutableState;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.maplibre.android.camera.CameraUpdateFactory;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.geometry.LatLngBounds;
import org.maplibre.android.maps.MapLibreMap;

/* compiled from: FreeWayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$7$1", f = "FreeWayfindrMap.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class FreeWayfindrMapKt$FreeWayfindrMap$7$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AtomicBoolean $destroyed;
    final /* synthetic */ boolean $fitRoute;
    final /* synthetic */ MutableState<MapLibreMap> $map$delegate;
    final /* synthetic */ List<Route> $routeAlternatives;
    final /* synthetic */ List<GeoPoint> $routePoints;
    final /* synthetic */ MutableState<Boolean> $styleReady$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FreeWayfindrMapKt$FreeWayfindrMap$7$1(List<Route> list, boolean z, List<GeoPoint> list2, AtomicBoolean atomicBoolean, MutableState<Boolean> mutableState, MutableState<MapLibreMap> mutableState2, Continuation<? super FreeWayfindrMapKt$FreeWayfindrMap$7$1> continuation) {
        super(2, continuation);
        this.$routeAlternatives = list;
        this.$fitRoute = z;
        this.$routePoints = list2;
        this.$destroyed = atomicBoolean;
        this.$styleReady$delegate = mutableState;
        this.$map$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FreeWayfindrMapKt$FreeWayfindrMap$7$1(this.$routeAlternatives, this.$fitRoute, this.$routePoints, this.$destroyed, this.$styleReady$delegate, this.$map$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FreeWayfindrMapKt$FreeWayfindrMap$7$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Iterable take = CollectionsKt.take(this.$routeAlternatives, 3);
                Collection arrayList = new ArrayList();
                Iterator it = take.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList, ((Route) it.next()).getGeometry());
                }
                Collection collection = (List) arrayList;
                Collection collection2 = this.$routePoints;
                if (collection.isEmpty()) {
                    collection = collection2;
                }
                List<GeoPoint> framingPoints = (List) collection;
                if (this.$fitRoute && framingPoints.size() >= 2) {
                    LatLngBounds.Builder b = new LatLngBounds.Builder();
                    for (GeoPoint geoPoint : framingPoints) {
                        b.include(new LatLng(geoPoint.getLat(), geoPoint.getLng()));
                    }
                    FreeWayfindrMapKt.FreeWayfindrMap$animate(this.$destroyed, this.$styleReady$delegate, this.$map$delegate, CameraUpdateFactory.newLatLngBounds(b.build(), 110), 800);
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
