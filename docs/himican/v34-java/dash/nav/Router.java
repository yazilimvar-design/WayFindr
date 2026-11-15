package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.dash.GoogleKey;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.maplibre.android.style.layers.Property;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: Router.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016H\u0086@¢\u0006\u0002\u0010\u0017J<\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00162\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u000e\b\u0002\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016H\u0086@¢\u0006\u0002\u0010\u0017J6\u0010\u0019\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016H\u0082@¢\u0006\u0002\u0010\u0017J&\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016H\u0002J\u0010\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u0013H\u0002J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001f\u001a\u00020\u0005H\u0002J\u001e\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00162\u0006\u0010\u000e\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020#H\u0002J,\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00162\u0006\u0010\u000e\u001a\u00020\u001b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130\u00162\u0006\u0010'\u001a\u00020(H\u0002J,\u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u00162\u0006\u0010\u000e\u001a\u00020\u001b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130\u00162\u0006\u0010'\u001a\u00020(H\u0002J\u0010\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020\u0005H\u0002J&\u0010-\u001a\u00020%2\u0006\u0010\u001d\u001a\u00020\u00132\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130\u00162\u0006\u0010'\u001a\u00020(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006."}, d2 = {"Lcom/example/wayfindr/dash/nav/Router;", "", "<init>", "()V", "TAG", "", "ENDPOINT", "FIELD_MASK", Property.SYMBOL_Z_ORDER_SOURCE, "Lcom/example/wayfindr/dash/RoutingSource;", "getSource", "()Lcom/example/wayfindr/dash/RoutingSource;", "setSource", "(Lcom/example/wayfindr/dash/RoutingSource;)V", "route", "Lcom/example/wayfindr/dash/nav/Route;", "context", "Landroid/content/Context;", "from", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "to", "intermediates", "", "(Landroid/content/Context;Lcom/example/wayfindr/dash/nav/GeoPoint;Lcom/example/wayfindr/dash/nav/GeoPoint;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "alternatives", "googleRoute", "buildRequest", "Lorg/json/JSONObject;", "waypoint", Property.SYMBOL_PLACEMENT_POINT, "parse", "json", "parseTraffic", "Lcom/example/wayfindr/dash/nav/TrafficInterval;", "lastPointIndex", "", "parseLegEnds", "", "geometry", "cumulative", "", "parseManeuvers", "Lcom/example/wayfindr/dash/nav/Maneuver;", "parseDuration", "duration", "nearestCumulative", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class Router {
    private static final String ENDPOINT = "https://routes.googleapis.com/directions/v2:computeRoutes";
    private static final String FIELD_MASK = "routes.distanceMeters,routes.duration,routes.polyline.encodedPolyline,routes.travelAdvisory.speedReadingIntervals,routes.legs.endLocation,routes.legs.steps.startLocation,routes.legs.steps.endLocation,routes.legs.steps.navigationInstruction";
    private static final String TAG = "Router";
    public static final Router INSTANCE = new Router();
    private static volatile RoutingSource source = RoutingSource.GOOGLE;
    public static final int $stable = 8;

    /* compiled from: Router.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RoutingSource.values().length];
            try {
                iArr[RoutingSource.GOOGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RoutingSource.OSM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RoutingSource.BROUTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private Router() {
    }

    public final RoutingSource getSource() {
        return source;
    }

    public final void setSource(RoutingSource routingSource) {
        Intrinsics.checkNotNullParameter(routingSource, "<set-?>");
        source = routingSource;
    }

    public static /* synthetic */ Object route$default(Router router, Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return router.route(context, geoPoint, geoPoint2, list, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0166 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object route(Context context, GeoPoint from, GeoPoint to, List<GeoPoint> list, Continuation<? super Route> continuation) {
        Router$route$1 router$route$1;
        Router$route$1 router$route$12;
        Object googleRoute;
        Context context2;
        GeoPoint from2;
        GeoPoint to2;
        List intermediates;
        Context context3;
        GeoPoint from3;
        GeoPoint to3;
        Object obj;
        List intermediates2;
        Context context4;
        GeoPoint from4;
        GeoPoint to4;
        Object obj2;
        List intermediates3;
        Object route;
        Context context5;
        GeoPoint to5;
        List intermediates4;
        GeoPoint from5;
        Route route2;
        Object route3;
        Context context6;
        GeoPoint to6;
        List intermediates5;
        Route copy$default;
        if (continuation instanceof Router$route$1) {
            router$route$1 = (Router$route$1) continuation;
            if ((router$route$1.label & Integer.MIN_VALUE) != 0) {
                router$route$1.label -= Integer.MIN_VALUE;
                router$route$12 = router$route$1;
                Object $result = router$route$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (router$route$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        switch (WhenMappings.$EnumSwitchMapping$0[source.ordinal()]) {
                            case 1:
                                if (GoogleKey.INSTANCE.getPresent()) {
                                    router$route$12.L$0 = SpillingKt.nullOutSpilledVariable(context);
                                    router$route$12.L$1 = SpillingKt.nullOutSpilledVariable(from);
                                    router$route$12.L$2 = SpillingKt.nullOutSpilledVariable(to);
                                    router$route$12.L$3 = SpillingKt.nullOutSpilledVariable(list);
                                    router$route$12.label = 2;
                                    googleRoute = googleRoute(context, from, to, list, router$route$12);
                                    if (googleRoute == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    context2 = context;
                                    from2 = from;
                                    to2 = to;
                                    intermediates = list;
                                    Route route4 = (Route) googleRoute;
                                    return route4 != null ? Route.copy$default(route4, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.GOOGLE, 127, null) : null;
                                }
                                FreeRouter freeRouter = FreeRouter.INSTANCE;
                                router$route$12.L$0 = SpillingKt.nullOutSpilledVariable(context);
                                router$route$12.L$1 = SpillingKt.nullOutSpilledVariable(from);
                                router$route$12.L$2 = SpillingKt.nullOutSpilledVariable(to);
                                router$route$12.L$3 = SpillingKt.nullOutSpilledVariable(list);
                                router$route$12.label = 1;
                                Object route5 = freeRouter.route(context, from, to, list, router$route$12);
                                if (route5 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context3 = context;
                                from3 = from;
                                to3 = to;
                                obj = route5;
                                intermediates2 = list;
                                Route route6 = (Route) obj;
                                return route6 != null ? Route.copy$default(route6, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.OSM, 127, null) : null;
                            case 2:
                                FreeRouter freeRouter2 = FreeRouter.INSTANCE;
                                router$route$12.L$0 = SpillingKt.nullOutSpilledVariable(context);
                                router$route$12.L$1 = SpillingKt.nullOutSpilledVariable(from);
                                router$route$12.L$2 = SpillingKt.nullOutSpilledVariable(to);
                                router$route$12.L$3 = SpillingKt.nullOutSpilledVariable(list);
                                router$route$12.label = 3;
                                Object route7 = freeRouter2.route(context, from, to, list, router$route$12);
                                if (route7 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context4 = context;
                                from4 = from;
                                to4 = to;
                                obj2 = route7;
                                intermediates3 = list;
                                Route route8 = (Route) obj2;
                                return route8 != null ? Route.copy$default(route8, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.OSM, 127, null) : null;
                            case 3:
                                OfflineRouter offlineRouter = OfflineRouter.INSTANCE;
                                router$route$12.L$0 = context;
                                router$route$12.L$1 = from;
                                router$route$12.L$2 = to;
                                router$route$12.L$3 = list;
                                router$route$12.label = 4;
                                route = offlineRouter.route(from, to, list, router$route$12);
                                if (route == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context5 = context;
                                to5 = to;
                                intermediates4 = list;
                                from5 = from;
                                route2 = (Route) route;
                                if (route2 == null && (copy$default = Route.copy$default(route2, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.BROUTER, 127, null)) != null) {
                                    return copy$default;
                                }
                                RideDiagnostics.INSTANCE.log("route", "BRouter unavailable (" + OfflineRouter.INSTANCE.getLastError() + ") — trying the free OSM server");
                                FreeRouter freeRouter3 = FreeRouter.INSTANCE;
                                router$route$12.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                router$route$12.L$1 = SpillingKt.nullOutSpilledVariable(from5);
                                router$route$12.L$2 = SpillingKt.nullOutSpilledVariable(to5);
                                router$route$12.L$3 = SpillingKt.nullOutSpilledVariable(intermediates4);
                                router$route$12.L$4 = SpillingKt.nullOutSpilledVariable(this);
                                router$route$12.I$0 = 0;
                                router$route$12.label = 5;
                                route3 = freeRouter3.route(context5, from5, to5, intermediates4, router$route$12);
                                if (route3 != coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context6 = context5;
                                to6 = to5;
                                intermediates5 = intermediates4;
                                Route route9 = (Route) route3;
                                return route9 != null ? Route.copy$default(route9, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.OSM, 127, null) : null;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    case 1:
                        intermediates2 = (List) router$route$12.L$3;
                        to3 = (GeoPoint) router$route$12.L$2;
                        from3 = (GeoPoint) router$route$12.L$1;
                        context3 = (Context) router$route$12.L$0;
                        ResultKt.throwOnFailure($result);
                        obj = $result;
                        Route route62 = (Route) obj;
                        return route62 != null ? Route.copy$default(route62, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.OSM, 127, null) : null;
                    case 2:
                        intermediates = (List) router$route$12.L$3;
                        to2 = (GeoPoint) router$route$12.L$2;
                        from2 = (GeoPoint) router$route$12.L$1;
                        context2 = (Context) router$route$12.L$0;
                        ResultKt.throwOnFailure($result);
                        googleRoute = $result;
                        Route route42 = (Route) googleRoute;
                        return route42 != null ? Route.copy$default(route42, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.GOOGLE, 127, null) : null;
                    case 3:
                        intermediates3 = (List) router$route$12.L$3;
                        to4 = (GeoPoint) router$route$12.L$2;
                        from4 = (GeoPoint) router$route$12.L$1;
                        context4 = (Context) router$route$12.L$0;
                        ResultKt.throwOnFailure($result);
                        obj2 = $result;
                        Route route82 = (Route) obj2;
                        return route82 != null ? Route.copy$default(route82, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.OSM, 127, null) : null;
                    case 4:
                        List intermediates6 = (List) router$route$12.L$3;
                        GeoPoint to7 = (GeoPoint) router$route$12.L$2;
                        GeoPoint from6 = (GeoPoint) router$route$12.L$1;
                        Context context7 = (Context) router$route$12.L$0;
                        ResultKt.throwOnFailure($result);
                        intermediates4 = intermediates6;
                        to5 = to7;
                        context5 = context7;
                        route = $result;
                        from5 = from6;
                        route2 = (Route) route;
                        if (route2 == null) {
                            break;
                        }
                        RideDiagnostics.INSTANCE.log("route", "BRouter unavailable (" + OfflineRouter.INSTANCE.getLastError() + ") — trying the free OSM server");
                        FreeRouter freeRouter32 = FreeRouter.INSTANCE;
                        router$route$12.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                        router$route$12.L$1 = SpillingKt.nullOutSpilledVariable(from5);
                        router$route$12.L$2 = SpillingKt.nullOutSpilledVariable(to5);
                        router$route$12.L$3 = SpillingKt.nullOutSpilledVariable(intermediates4);
                        router$route$12.L$4 = SpillingKt.nullOutSpilledVariable(this);
                        router$route$12.I$0 = 0;
                        router$route$12.label = 5;
                        route3 = freeRouter32.route(context5, from5, to5, intermediates4, router$route$12);
                        if (route3 != coroutine_suspended) {
                        }
                        break;
                    case 5:
                        int i = router$route$12.I$0;
                        intermediates5 = (List) router$route$12.L$3;
                        to6 = (GeoPoint) router$route$12.L$2;
                        context6 = (Context) router$route$12.L$0;
                        ResultKt.throwOnFailure($result);
                        route3 = $result;
                        Route route92 = (Route) route3;
                        return route92 != null ? Route.copy$default(route92, null, null, 0.0d, 0.0d, null, null, null, RoutingSource.OSM, 127, null) : null;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        router$route$1 = new Router$route$1(this, continuation);
        router$route$12 = router$route$1;
        Object $result2 = router$route$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (router$route$12.label) {
        }
    }

    public static /* synthetic */ Object alternatives$default(Router router, Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        List list2;
        if ((i & 8) == 0) {
            list2 = list;
        } else {
            list2 = CollectionsKt.emptyList();
        }
        return router.alternatives(context, geoPoint, geoPoint2, list2, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c2 A[LOOP:0: B:16:0x00bc->B:18:0x00c2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object alternatives(Context context, GeoPoint from, GeoPoint to, List<GeoPoint> list, Continuation<? super List<Route>> continuation) {
        Router$alternatives$1 router$alternatives$1;
        Router router;
        Router$alternatives$1 router$alternatives$12;
        Object route;
        Object obj;
        Iterator it;
        if (continuation instanceof Router$alternatives$1) {
            router$alternatives$1 = (Router$alternatives$1) continuation;
            if ((router$alternatives$1.label & Integer.MIN_VALUE) != 0) {
                router$alternatives$1.label -= Integer.MIN_VALUE;
                router = this;
                router$alternatives$12 = router$alternatives$1;
                Object $result = router$alternatives$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (router$alternatives$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        if (WhenMappings.$EnumSwitchMapping$0[source.ordinal()] != 2) {
                            router$alternatives$12.L$0 = SpillingKt.nullOutSpilledVariable(context);
                            router$alternatives$12.L$1 = SpillingKt.nullOutSpilledVariable(from);
                            router$alternatives$12.L$2 = SpillingKt.nullOutSpilledVariable(to);
                            router$alternatives$12.L$3 = SpillingKt.nullOutSpilledVariable(list);
                            router$alternatives$12.label = 2;
                            route = router.route(context, from, to, list, router$alternatives$12);
                            return route == coroutine_suspended ? coroutine_suspended : CollectionsKt.listOfNotNull(route);
                        }
                        FreeRouter freeRouter = FreeRouter.INSTANCE;
                        router$alternatives$12.L$0 = SpillingKt.nullOutSpilledVariable(context);
                        router$alternatives$12.L$1 = SpillingKt.nullOutSpilledVariable(from);
                        router$alternatives$12.L$2 = SpillingKt.nullOutSpilledVariable(to);
                        router$alternatives$12.L$3 = SpillingKt.nullOutSpilledVariable(list);
                        router$alternatives$12.label = 1;
                        Object alternatives = freeRouter.alternatives(context, from, to, list, router$alternatives$12);
                        if (alternatives == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = alternatives;
                        Iterable iterable = (Iterable) obj;
                        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                        it = iterable.iterator();
                        while (it.hasNext()) {
                            arrayList.add(Route.copy$default((Route) it.next(), null, null, 0.0d, 0.0d, null, null, null, RoutingSource.OSM, 127, null));
                        }
                        return (List) arrayList;
                    case 1:
                        ResultKt.throwOnFailure($result);
                        obj = $result;
                        Iterable iterable2 = (Iterable) obj;
                        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                        it = iterable2.iterator();
                        while (it.hasNext()) {
                        }
                        return (List) arrayList2;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        route = $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        router = this;
        router$alternatives$1 = new Router$alternatives$1(router, continuation);
        router$alternatives$12 = router$alternatives$1;
        Object $result2 = router$alternatives$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (router$alternatives$12.label) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object googleRoute(Context context, GeoPoint from, GeoPoint to, List<GeoPoint> list, Continuation<? super Route> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new Router$googleRoute$2(from, to, list, context, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject buildRequest(GeoPoint from, GeoPoint to, List<GeoPoint> intermediates) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("origin", INSTANCE.waypoint(from));
        jSONObject.put("destination", INSTANCE.waypoint(to));
        if (!intermediates.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = intermediates.iterator();
            while (it.hasNext()) {
                jSONArray.put(INSTANCE.waypoint((GeoPoint) it.next()));
            }
            Unit unit = Unit.INSTANCE;
            jSONObject.put("intermediates", jSONArray);
        }
        jSONObject.put("travelMode", "TWO_WHEELER");
        jSONObject.put("routingPreference", "TRAFFIC_AWARE");
        jSONObject.put("extraComputations", new JSONArray().put("TRAFFIC_ON_POLYLINE"));
        jSONObject.put("computeAlternativeRoutes", false);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("avoidTolls", false);
        jSONObject2.put("avoidHighways", false);
        jSONObject2.put("avoidFerries", false);
        Unit unit2 = Unit.INSTANCE;
        jSONObject.put("routeModifiers", jSONObject2);
        jSONObject.put("languageCode", "en-US");
        jSONObject.put("units", "METRIC");
        return jSONObject;
    }

    private final JSONObject waypoint(GeoPoint point) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("latitude", point.getLat());
        jSONObject3.put("longitude", point.getLng());
        Unit unit = Unit.INSTANCE;
        jSONObject2.put("latLng", jSONObject3);
        Unit unit2 = Unit.INSTANCE;
        jSONObject.put("location", jSONObject2);
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Route parse(String json) {
        JSONObject root = new JSONObject(json);
        JSONArray routes = root.optJSONArray("routes");
        if (routes == null || routes.length() == 0) {
            Log.w(TAG, "Google Routes API returned no routes: " + json);
            return null;
        }
        JSONObject route = routes.getJSONObject(0);
        JSONObject optJSONObject = route.optJSONObject("polyline");
        String encodedPolyline = optJSONObject != null ? optJSONObject.optString("encodedPolyline") : null;
        if (encodedPolyline == null) {
            encodedPolyline = "";
        }
        if (StringsKt.isBlank(encodedPolyline)) {
            Log.w(TAG, "Route contains no encoded polyline");
            return null;
        }
        List geometry = PolylineCodec.decode$default(PolylineCodec.INSTANCE, encodedPolyline, 0, 2, null);
        if (geometry.size() < 2) {
            Log.w(TAG, "Decoded route contains fewer than 2 points");
            return null;
        }
        double[] cumulative = new double[geometry.size()];
        int size = geometry.size();
        for (int i = 1; i < size; i++) {
            cumulative[i] = cumulative[i - 1] + GeoPoint.INSTANCE.distMeters(geometry.get(i - 1), geometry.get(i));
        }
        Intrinsics.checkNotNull(route);
        List maneuvers = parseManeuvers(route, geometry, cumulative);
        double optDouble = route.optDouble("distanceMeters", ArraysKt.last(cumulative));
        String optString = route.optString("duration", "0s");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        return new Route(geometry, maneuvers, optDouble, parseDuration(optString), cumulative, parseLegEnds(route, geometry, cumulative), parseTraffic(route, CollectionsKt.getLastIndex(geometry)), null, 128, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007a A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final List<TrafficInterval> parseTraffic(JSONObject route, int lastPointIndex) {
        JSONArray intervals;
        TrafficSpeed trafficSpeed;
        JSONObject optJSONObject = route.optJSONObject("travelAdvisory");
        if (optJSONObject == null || (intervals = optJSONObject.optJSONArray("speedReadingIntervals")) == null) {
            return CollectionsKt.emptyList();
        }
        List createListBuilder = CollectionsKt.createListBuilder();
        int length = intervals.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject2 = intervals.optJSONObject(i);
            if (optJSONObject2 != null) {
                int coerceIn = RangesKt.coerceIn(optJSONObject2.optInt("startPolylinePointIndex", 0), 0, lastPointIndex);
                int coerceIn2 = RangesKt.coerceIn(optJSONObject2.optInt("endPolylinePointIndex", lastPointIndex), coerceIn, lastPointIndex);
                String optString = optJSONObject2.optString("speed");
                if (optString != null) {
                    switch (optString.hashCode()) {
                        case -1986416409:
                            if (optString.equals("NORMAL")) {
                                trafficSpeed = TrafficSpeed.NORMAL;
                                break;
                            }
                            break;
                        case 2548225:
                            if (optString.equals("SLOW")) {
                                trafficSpeed = TrafficSpeed.SLOW;
                                break;
                            }
                            break;
                        case 518374548:
                            if (optString.equals("TRAFFIC_JAM")) {
                                trafficSpeed = TrafficSpeed.TRAFFIC_JAM;
                                break;
                            }
                            break;
                    }
                    if (coerceIn2 <= coerceIn) {
                        createListBuilder.add(new TrafficInterval(coerceIn, coerceIn2, trafficSpeed));
                    }
                }
                trafficSpeed = TrafficSpeed.UNKNOWN;
                if (coerceIn2 <= coerceIn) {
                }
            }
        }
        return CollectionsKt.build(createListBuilder);
    }

    private final List<Double> parseLegEnds(JSONObject route, List<GeoPoint> geometry, double[] cumulative) {
        JSONArray legs = route.optJSONArray("legs");
        if (legs == null) {
            return CollectionsKt.emptyList();
        }
        List createListBuilder = CollectionsKt.createListBuilder();
        int length = legs.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = legs.optJSONObject(i);
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("endLocation");
                if (optJSONObject2 != null) {
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("latLng");
                    if (optJSONObject3 != null) {
                        createListBuilder.add(Double.valueOf(INSTANCE.nearestCumulative(new GeoPoint(optJSONObject3.optDouble("latitude"), optJSONObject3.optDouble("longitude")), geometry, cumulative)));
                    }
                }
            }
        }
        return CollectionsKt.build(createListBuilder);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final List<Maneuver> parseManeuvers(JSONObject route, List<GeoPoint> geometry, double[] cumulative) {
        JSONArray steps;
        JSONArray legs;
        String instruction;
        String optString;
        String optString2;
        ArrayList maneuvers = new ArrayList();
        JSONArray legs2 = route.optJSONArray("legs");
        if (legs2 == null) {
            return maneuvers;
        }
        int legIndex = 0;
        int length = legs2.length();
        while (legIndex < length) {
            JSONObject leg = legs2.optJSONObject(legIndex);
            if (leg != null && (steps = leg.optJSONArray("steps")) != null) {
                int stepIndex = 0;
                int length2 = steps.length();
                while (stepIndex < length2) {
                    JSONObject step = steps.optJSONObject(stepIndex);
                    if (step == null) {
                        legs = legs2;
                    } else {
                        JSONObject optJSONObject = step.optJSONObject("startLocation");
                        if (optJSONObject != null) {
                            JSONObject startLocation = optJSONObject.optJSONObject("latLng");
                            if (startLocation != null) {
                                GeoPoint point = new GeoPoint(startLocation.optDouble("latitude"), startLocation.optDouble("longitude"));
                                JSONObject navigationInstruction = step.optJSONObject("navigationInstruction");
                                if (navigationInstruction != null && (optString2 = navigationInstruction.optString("instructions")) != null) {
                                    String str = optString2;
                                    if (StringsKt.isBlank(str)) {
                                        str = null;
                                    }
                                    String str2 = str;
                                    if (str2 != null) {
                                        instruction = str2;
                                        optString = navigationInstruction != null ? navigationInstruction.optString("maneuver") : null;
                                        if (optString == null) {
                                            optString = "";
                                        }
                                        String googleManeuver = optString;
                                        ManeuverType type = ManeuverType.INSTANCE.fromGoogle(googleManeuver);
                                        legs = legs2;
                                        maneuvers.add(new Maneuver(type, instruction, point, nearestCumulative(point, geometry, cumulative), googleManeuver));
                                    }
                                }
                                instruction = "Continue";
                                if (navigationInstruction != null) {
                                }
                                if (optString == null) {
                                }
                                String googleManeuver2 = optString;
                                ManeuverType type2 = ManeuverType.INSTANCE.fromGoogle(googleManeuver2);
                                legs = legs2;
                                maneuvers.add(new Maneuver(type2, instruction, point, nearestCumulative(point, geometry, cumulative), googleManeuver2));
                            } else {
                                legs = legs2;
                            }
                        } else {
                            legs = legs2;
                        }
                    }
                    stepIndex++;
                    legs2 = legs;
                }
            }
            legIndex++;
            legs2 = legs2;
        }
        return maneuvers;
    }

    private final double parseDuration(String duration) {
        Double doubleOrNull = StringsKt.toDoubleOrNull(StringsKt.removeSuffix(duration, (CharSequence) "s"));
        if (doubleOrNull != null) {
            return doubleOrNull.doubleValue();
        }
        return 0.0d;
    }

    private final double nearestCumulative(GeoPoint point, List<GeoPoint> geometry, double[] cumulative) {
        double bestDistance = Double.MAX_VALUE;
        double bestCumulative = 0.0d;
        int size = geometry.size();
        for (int i = 0; i < size; i++) {
            double distance = GeoPoint.INSTANCE.distMeters(point, geometry.get(i));
            if (distance < bestDistance) {
                bestDistance = distance;
                bestCumulative = cumulative[i];
            }
        }
        return bestCumulative;
    }
}
