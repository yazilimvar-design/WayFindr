package com.example.wayfindr.ui.components;

import androidx.compose.runtime.MutableState;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.TrafficInterval;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import org.maplibre.android.geometry.LatLng;
import org.maplibre.android.plugins.annotation.LineManager;
import org.maplibre.android.plugins.annotation.LineOptions;
import org.maplibre.android.plugins.annotation.SymbolManager;
import org.maplibre.android.plugins.annotation.SymbolOptions;

/* compiled from: FreeWayfindrMap.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$4$1", f = "FreeWayfindrMap.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class FreeWayfindrMapKt$FreeWayfindrMap$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ AtomicBoolean $destroyed;
    final /* synthetic */ MutableState<LineManager> $lineMgr$delegate;
    final /* synthetic */ List<Pair<Double, Double>> $markerDestinations;
    final /* synthetic */ boolean $navMode;
    final /* synthetic */ float $riderBearing;
    final /* synthetic */ Double $riderLat;
    final /* synthetic */ Double $riderLng;
    final /* synthetic */ List<Route> $routeAlternatives;
    final /* synthetic */ List<GeoPoint> $routePoints;
    final /* synthetic */ int $selectedRouteIndex;
    final /* synthetic */ MutableState<SymbolManager> $symbolMgr$delegate;
    final /* synthetic */ List<TrafficInterval> $trafficIntervals;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FreeWayfindrMapKt$FreeWayfindrMap$4$1(AtomicBoolean atomicBoolean, List<Route> list, int i, List<GeoPoint> list2, List<TrafficInterval> list3, List<Pair<Double, Double>> list4, Double d, Double d2, boolean z, float f, MutableState<LineManager> mutableState, MutableState<SymbolManager> mutableState2, Continuation<? super FreeWayfindrMapKt$FreeWayfindrMap$4$1> continuation) {
        super(2, continuation);
        this.$destroyed = atomicBoolean;
        this.$routeAlternatives = list;
        this.$selectedRouteIndex = i;
        this.$routePoints = list2;
        this.$trafficIntervals = list3;
        this.$markerDestinations = list4;
        this.$riderLat = d;
        this.$riderLng = d2;
        this.$navMode = z;
        this.$riderBearing = f;
        this.$lineMgr$delegate = mutableState;
        this.$symbolMgr$delegate = mutableState2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FreeWayfindrMapKt$FreeWayfindrMap$4$1(this.$destroyed, this.$routeAlternatives, this.$selectedRouteIndex, this.$routePoints, this.$trafficIntervals, this.$markerDestinations, this.$riderLat, this.$riderLng, this.$navMode, this.$riderBearing, this.$lineMgr$delegate, this.$symbolMgr$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FreeWayfindrMapKt$FreeWayfindrMap$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
    
        r1 = com.example.wayfindr.ui.components.FreeWayfindrMapKt.FreeWayfindrMap$lambda$12(r33.$lineMgr$delegate);
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x002b, code lost:
    
        r2 = com.example.wayfindr.ui.components.FreeWayfindrMapKt.FreeWayfindrMap$lambda$15(r33.$symbolMgr$delegate);
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        LineManager lm;
        SymbolManager sm;
        List drawOrder;
        Iterable iterable;
        int i;
        Iterator it;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                if (!this.$destroyed.get() && lm != null && sm != null) {
                    lm.deleteAll();
                    sm.deleteAll();
                    List routes = CollectionsKt.take(this.$routeAlternatives, 3);
                    int i2 = 2;
                    if (routes.size() > 1) {
                        int chosenIndex = RangesKt.coerceIn(this.$selectedRouteIndex, 0, CollectionsKt.getLastIndex(routes));
                        Iterable indices = CollectionsKt.getIndices(routes);
                        Collection arrayList = new ArrayList();
                        for (Integer num : indices) {
                            if ((num.intValue() != chosenIndex ? 1 : 0) != 0) {
                                arrayList.add(num);
                            }
                        }
                        List drawOrder2 = CollectionsKt.plus((Collection<? extends Integer>) arrayList, Boxing.boxInt(chosenIndex));
                        List list = drawOrder2;
                        int i3 = 0;
                        Iterator it2 = list.iterator();
                        while (it2.hasNext()) {
                            int intValue = ((Number) it2.next()).intValue();
                            List geometry = ((Route) routes.get(intValue)).getGeometry();
                            if (geometry.size() < i2) {
                                drawOrder = drawOrder2;
                                iterable = list;
                                i = i3;
                                it = it2;
                            } else {
                                float f = intValue == chosenIndex ? 6.5f : 3.8f;
                                List list2 = geometry;
                                drawOrder = drawOrder2;
                                iterable = list;
                                Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                                Iterable<GeoPoint> iterable2 = list2;
                                int i4 = 0;
                                for (GeoPoint geoPoint : iterable2) {
                                    arrayList2.add(new LatLng(geoPoint.getLat(), geoPoint.getLng()));
                                    list2 = list2;
                                    iterable2 = iterable2;
                                    i4 = i4;
                                    i3 = i3;
                                    it2 = it2;
                                }
                                i = i3;
                                it = it2;
                                List list3 = (List) arrayList2;
                                lm.create((LineManager) new LineOptions().withLatLngs(list3).withLineColor("#15191F").withLineWidth(Boxing.boxFloat(f + 2.8f)));
                                lm.create((LineManager) new LineOptions().withLatLngs(list3).withLineColor(RouteAlternativeStyle.INSTANCE.mapLibreHex(intValue)).withLineWidth(Boxing.boxFloat(f)));
                            }
                            drawOrder2 = drawOrder;
                            list = iterable;
                            i3 = i;
                            it2 = it;
                            i2 = 2;
                        }
                        FreeWayfindrMapKt.drawTraffic(lm, ((Route) routes.get(chosenIndex)).getGeometry(), ((Route) routes.get(chosenIndex)).getTraffic());
                    } else if (this.$routePoints.size() >= 2) {
                        Iterable iterable3 = this.$routePoints;
                        Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                        Iterable<GeoPoint> iterable4 = iterable3;
                        int i5 = 0;
                        for (GeoPoint geoPoint2 : iterable4) {
                            arrayList3.add(new LatLng(geoPoint2.getLat(), geoPoint2.getLng()));
                            routes = routes;
                            iterable3 = iterable3;
                            iterable4 = iterable4;
                            i5 = i5;
                        }
                        List latLngs = (List) arrayList3;
                        lm.create((LineManager) new LineOptions().withLatLngs(latLngs).withLineColor("#15191F").withLineWidth(Boxing.boxFloat(8.8f)));
                        lm.create((LineManager) new LineOptions().withLatLngs(latLngs).withLineColor("#4285F4").withLineWidth(Boxing.boxFloat(6.0f)));
                        FreeWayfindrMapKt.drawTraffic(lm, this.$routePoints, this.$trafficIntervals);
                    }
                    for (Pair pair : this.$markerDestinations) {
                        sm.create((SymbolManager) new SymbolOptions().withLatLng(new LatLng(((Number) pair.getFirst()).doubleValue(), ((Number) pair.getSecond()).doubleValue())).withIconImage("dest-pin").withIconSize(Boxing.boxFloat(1.1f)));
                    }
                    if (this.$riderLat != null && this.$riderLng != null) {
                        sm.create((SymbolManager) new SymbolOptions().withLatLng(new LatLng(this.$riderLat.doubleValue(), this.$riderLng.doubleValue())).withIconImage("rider-chevron").withIconRotate(Boxing.boxFloat(this.$navMode ? 0.0f : this.$riderBearing)).withIconSize(Boxing.boxFloat(1.0f)));
                    }
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
