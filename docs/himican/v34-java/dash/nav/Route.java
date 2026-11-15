package com.example.wayfindr.dash.nav;

import com.example.wayfindr.dash.RoutingSource;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.maplibre.android.style.layers.Property;

/* compiled from: Route.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\t\u0010$\u001a\u00020\bHÆ\u0003J\t\u0010%\u001a\u00020\bHÆ\u0003J\t\u0010&\u001a\u00020\u000bHÆ\u0003J\u000f\u0010'\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003HÆ\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\u0010HÆ\u0003Js\u0010*\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÆ\u0001J\u0013\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010.\u001a\u00020/HÖ\u0001J\t\u00100\u001a\u000201HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0013\u0010\u001f\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b \u0010!¨\u00062"}, d2 = {"Lcom/example/wayfindr/dash/nav/Route;", "", "geometry", "", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "maneuvers", "Lcom/example/wayfindr/dash/nav/Maneuver;", "totalMeters", "", "totalSeconds", "cumulative", "", "legEndCumulative", "traffic", "Lcom/example/wayfindr/dash/nav/TrafficInterval;", Property.SYMBOL_Z_ORDER_SOURCE, "Lcom/example/wayfindr/dash/RoutingSource;", "<init>", "(Ljava/util/List;Ljava/util/List;DD[DLjava/util/List;Ljava/util/List;Lcom/example/wayfindr/dash/RoutingSource;)V", "getGeometry", "()Ljava/util/List;", "getManeuvers", "getTotalMeters", "()D", "getTotalSeconds", "getCumulative", "()[D", "getLegEndCumulative", "getTraffic", "getSource", "()Lcom/example/wayfindr/dash/RoutingSource;", "destination", "getDestination", "()Lcom/example/wayfindr/dash/nav/GeoPoint;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class Route {
    public static final int $stable = 8;
    private final double[] cumulative;
    private final List<GeoPoint> geometry;
    private final List<Double> legEndCumulative;
    private final List<Maneuver> maneuvers;
    private final RoutingSource source;
    private final double totalMeters;
    private final double totalSeconds;
    private final List<TrafficInterval> traffic;

    public static /* synthetic */ Route copy$default(Route route, List list, List list2, double d, double d2, double[] dArr, List list3, List list4, RoutingSource routingSource, int i, Object obj) {
        if ((i & 1) != 0) {
            list = route.geometry;
        }
        if ((i & 2) != 0) {
            list2 = route.maneuvers;
        }
        if ((i & 4) != 0) {
            d = route.totalMeters;
        }
        if ((i & 8) != 0) {
            d2 = route.totalSeconds;
        }
        if ((i & 16) != 0) {
            dArr = route.cumulative;
        }
        if ((i & 32) != 0) {
            list3 = route.legEndCumulative;
        }
        if ((i & 64) != 0) {
            list4 = route.traffic;
        }
        if ((i & 128) != 0) {
            routingSource = route.source;
        }
        double d3 = d2;
        double d4 = d;
        return route.copy(list, list2, d4, d3, dArr, list3, list4, routingSource);
    }

    public final List<GeoPoint> component1() {
        return this.geometry;
    }

    public final List<Maneuver> component2() {
        return this.maneuvers;
    }

    /* renamed from: component3, reason: from getter */
    public final double getTotalMeters() {
        return this.totalMeters;
    }

    /* renamed from: component4, reason: from getter */
    public final double getTotalSeconds() {
        return this.totalSeconds;
    }

    /* renamed from: component5, reason: from getter */
    public final double[] getCumulative() {
        return this.cumulative;
    }

    public final List<Double> component6() {
        return this.legEndCumulative;
    }

    public final List<TrafficInterval> component7() {
        return this.traffic;
    }

    /* renamed from: component8, reason: from getter */
    public final RoutingSource getSource() {
        return this.source;
    }

    public final Route copy(List<GeoPoint> geometry, List<Maneuver> maneuvers, double totalMeters, double totalSeconds, double[] cumulative, List<Double> legEndCumulative, List<TrafficInterval> traffic, RoutingSource source) {
        Intrinsics.checkNotNullParameter(geometry, "geometry");
        Intrinsics.checkNotNullParameter(maneuvers, "maneuvers");
        Intrinsics.checkNotNullParameter(cumulative, "cumulative");
        Intrinsics.checkNotNullParameter(legEndCumulative, "legEndCumulative");
        Intrinsics.checkNotNullParameter(traffic, "traffic");
        return new Route(geometry, maneuvers, totalMeters, totalSeconds, cumulative, legEndCumulative, traffic, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Route)) {
            return false;
        }
        Route route = (Route) other;
        return Intrinsics.areEqual(this.geometry, route.geometry) && Intrinsics.areEqual(this.maneuvers, route.maneuvers) && Double.compare(this.totalMeters, route.totalMeters) == 0 && Double.compare(this.totalSeconds, route.totalSeconds) == 0 && Intrinsics.areEqual(this.cumulative, route.cumulative) && Intrinsics.areEqual(this.legEndCumulative, route.legEndCumulative) && Intrinsics.areEqual(this.traffic, route.traffic) && this.source == route.source;
    }

    public int hashCode() {
        return (((((((((((((this.geometry.hashCode() * 31) + this.maneuvers.hashCode()) * 31) + Double.hashCode(this.totalMeters)) * 31) + Double.hashCode(this.totalSeconds)) * 31) + Arrays.hashCode(this.cumulative)) * 31) + this.legEndCumulative.hashCode()) * 31) + this.traffic.hashCode()) * 31) + (this.source == null ? 0 : this.source.hashCode());
    }

    public String toString() {
        return "Route(geometry=" + this.geometry + ", maneuvers=" + this.maneuvers + ", totalMeters=" + this.totalMeters + ", totalSeconds=" + this.totalSeconds + ", cumulative=" + Arrays.toString(this.cumulative) + ", legEndCumulative=" + this.legEndCumulative + ", traffic=" + this.traffic + ", source=" + this.source + ")";
    }

    public Route(List<GeoPoint> geometry, List<Maneuver> maneuvers, double totalMeters, double totalSeconds, double[] cumulative, List<Double> legEndCumulative, List<TrafficInterval> traffic, RoutingSource source) {
        Intrinsics.checkNotNullParameter(geometry, "geometry");
        Intrinsics.checkNotNullParameter(maneuvers, "maneuvers");
        Intrinsics.checkNotNullParameter(cumulative, "cumulative");
        Intrinsics.checkNotNullParameter(legEndCumulative, "legEndCumulative");
        Intrinsics.checkNotNullParameter(traffic, "traffic");
        this.geometry = geometry;
        this.maneuvers = maneuvers;
        this.totalMeters = totalMeters;
        this.totalSeconds = totalSeconds;
        this.cumulative = cumulative;
        this.legEndCumulative = legEndCumulative;
        this.traffic = traffic;
        this.source = source;
    }

    public /* synthetic */ Route(List list, List list2, double d, double d2, double[] dArr, List list3, List list4, RoutingSource routingSource, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, list2, d, d2, dArr, (i & 32) != 0 ? CollectionsKt.emptyList() : list3, (i & 64) != 0 ? CollectionsKt.emptyList() : list4, (i & 128) != 0 ? null : routingSource);
    }

    public final List<GeoPoint> getGeometry() {
        return this.geometry;
    }

    public final List<Maneuver> getManeuvers() {
        return this.maneuvers;
    }

    public final double getTotalMeters() {
        return this.totalMeters;
    }

    public final double getTotalSeconds() {
        return this.totalSeconds;
    }

    public final double[] getCumulative() {
        return this.cumulative;
    }

    public final List<Double> getLegEndCumulative() {
        return this.legEndCumulative;
    }

    public final List<TrafficInterval> getTraffic() {
        return this.traffic;
    }

    public final RoutingSource getSource() {
        return this.source;
    }

    public final GeoPoint getDestination() {
        return (GeoPoint) CollectionsKt.lastOrNull((List) this.geometry);
    }
}
