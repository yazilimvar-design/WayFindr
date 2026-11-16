package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.dash.GoogleKey;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Router.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class Router {
    private static final String ENDPOINT = "https://routes.googleapis.com/directions/v2:computeRoutes";
    private static final String FIELD_MASK = "routes.distanceMeters,routes.duration,routes.polyline.encodedPolyline,routes.travelAdvisory.speedReadingIntervals,routes.legs.endLocation,routes.legs.steps.startLocation,routes.legs.steps.endLocation,routes.legs.steps.navigationInstruction";
    private static final String TAG = "Router";
    public static final Router INSTANCE = new Router();
    private static volatile RoutingSource source = RoutingSource.GOOGLE;
    public static final int $stable = 8;

    private Router() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final JSONObject buildRequest(GeoPoint geoPoint, GeoPoint geoPoint2, List list) {
        JSONObject jSONObject = new JSONObject();
        JSONObject waypoint = INSTANCE.waypoint(geoPoint);
        jSONObject.put("origin", waypoint);
        NavPlus.addGoogleHeading(waypoint, geoPoint);
        jSONObject.put("destination", INSTANCE.waypoint(geoPoint2));
        if (!list.isEmpty()) {
            JSONArray jSONArray = new JSONArray();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                jSONArray.put(INSTANCE.waypoint((GeoPoint) it.next()));
            }
            Unit unit = Unit.INSTANCE;
            jSONObject.put("intermediates", jSONArray);
        }
        jSONObject.put("travelMode", "DRIVE");
        jSONObject.put("routingPreference", "TRAFFIC_AWARE");
        jSONObject.put("extraComputations", new JSONArray().put("TRAFFIC_ON_POLYLINE"));
        jSONObject.remove("extraComputations");
        jSONObject.put("computeAlternativeRoutes", false);
        NavPlus.googleAlts(jSONObject, list);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final Object googleRoute(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new Router$googleRoute$2(geoPoint, geoPoint2, list, context, null), continuation);
    }

    private final double nearestCumulative(GeoPoint geoPoint, List list, double[] dArr) {
        double d = Double.MAX_VALUE;
        double d2 = 0.0d;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            double distMeters = GeoPoint.Companion.distMeters(geoPoint, (GeoPoint) list.get(i));
            if (distMeters < d) {
                d = distMeters;
                d2 = dArr[i];
            }
        }
        return d2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Route parse(String str) {
        JSONArray optJSONArray = new JSONObject(str).optJSONArray("routes");
        if (optJSONArray == null || optJSONArray.length() == 0) {
            Log.w(TAG, "Google Routes API returned no routes: " + str);
            return null;
        }
        JSONObject jSONObject = optJSONArray.getJSONObject(0);
        JSONObject optJSONObject = jSONObject.optJSONObject("polyline");
        String optString = optJSONObject != null ? optJSONObject.optString("encodedPolyline") : null;
        if (optString == null) {
            optString = "";
        }
        if (StringsKt.isBlank(optString)) {
            Log.w(TAG, "Route contains no encoded polyline");
            return null;
        }
        List decode$default = PolylineCodec.decode$default(PolylineCodec.INSTANCE, optString, 0, 2, (Object) null);
        if (decode$default.size() < 2) {
            Log.w(TAG, "Decoded route contains fewer than 2 points");
            return null;
        }
        double[] dArr = new double[decode$default.size()];
        int size = decode$default.size();
        for (int i = 1; i < size; i++) {
            dArr[i] = dArr[i - 1] + GeoPoint.Companion.distMeters((GeoPoint) decode$default.get(i - 1), (GeoPoint) decode$default.get(i));
        }
        Intrinsics.checkNotNull(jSONObject);
        List parseManeuvers = parseManeuvers(jSONObject, decode$default, dArr);
        double optDouble = jSONObject.optDouble("distanceMeters", ArraysKt.last(dArr));
        String optString2 = jSONObject.optString("duration", "0s");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        return new Route(decode$default, parseManeuvers, optDouble, parseDuration(optString2), dArr, parseLegEnds(jSONObject, decode$default, dArr), parseTraffic(jSONObject, CollectionsKt.getLastIndex(decode$default)), (RoutingSource) null, 128, (DefaultConstructorMarker) null);
    }

    private final double parseDuration(String str) {
        Double doubleOrNull = StringsKt.toDoubleOrNull(StringsKt.removeSuffix(str, "s"));
        if (doubleOrNull != null) {
            return doubleOrNull.doubleValue();
        }
        return 0.0d;
    }

    private final List parseLegEnds(JSONObject jSONObject, List list, double[] dArr) {
        JSONArray optJSONArray = jSONObject.optJSONArray("legs");
        if (optJSONArray == null) {
            return CollectionsKt.emptyList();
        }
        List createListBuilder = CollectionsKt.createListBuilder();
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("endLocation");
                if (optJSONObject2 != null) {
                    JSONObject optJSONObject3 = optJSONObject2.optJSONObject("latLng");
                    if (optJSONObject3 != null) {
                        createListBuilder.add(Double.valueOf(INSTANCE.nearestCumulative(new GeoPoint(optJSONObject3.optDouble("latitude"), optJSONObject3.optDouble("longitude")), list, dArr)));
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
    private final List parseManeuvers(JSONObject jSONObject, List list, double[] dArr) {
        JSONArray optJSONArray;
        JSONArray jSONArray;
        String str;
        String optString;
        String optString2;
        ArrayList arrayList = new ArrayList();
        JSONArray optJSONArray2 = jSONObject.optJSONArray("legs");
        if (optJSONArray2 == null) {
            return arrayList;
        }
        int i = 0;
        int length = optJSONArray2.length();
        while (i < length) {
            JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
            if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONArray("steps")) != null) {
                int i2 = 0;
                int length2 = optJSONArray.length();
                while (i2 < length2) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                    if (optJSONObject2 == null) {
                        jSONArray = optJSONArray2;
                    } else {
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("startLocation");
                        if (optJSONObject3 != null) {
                            JSONObject optJSONObject4 = optJSONObject3.optJSONObject("latLng");
                            if (optJSONObject4 != null) {
                                GeoPoint geoPoint = new GeoPoint(optJSONObject4.optDouble("latitude"), optJSONObject4.optDouble("longitude"));
                                JSONObject optJSONObject5 = optJSONObject2.optJSONObject("navigationInstruction");
                                if (optJSONObject5 != null && (optString2 = optJSONObject5.optString("instructions")) != null) {
                                    String str2 = optString2;
                                    if (StringsKt.isBlank(str2)) {
                                        str2 = null;
                                    }
                                    String str3 = str2;
                                    if (str3 != null) {
                                        str = str3;
                                        optString = optJSONObject5 != null ? optJSONObject5.optString("maneuver") : null;
                                        if (optString == null) {
                                            optString = "";
                                        }
                                        String str4 = optString;
                                        jSONArray = optJSONArray2;
                                        arrayList.add(new Maneuver(ManeuverType.Companion.fromGoogle(str4), str, geoPoint, nearestCumulative(geoPoint, list, dArr), str4));
                                    }
                                }
                                str = "Continue";
                                if (optJSONObject5 != null) {
                                }
                                if (optString == null) {
                                }
                                String str42 = optString;
                                jSONArray = optJSONArray2;
                                arrayList.add(new Maneuver(ManeuverType.Companion.fromGoogle(str42), str, geoPoint, nearestCumulative(geoPoint, list, dArr), str42));
                            } else {
                                jSONArray = optJSONArray2;
                            }
                        } else {
                            jSONArray = optJSONArray2;
                        }
                    }
                    i2++;
                    optJSONArray2 = jSONArray;
                }
            }
            i++;
            optJSONArray2 = optJSONArray2;
        }
        return arrayList;
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
    private final List parseTraffic(JSONObject jSONObject, int i) {
        JSONArray optJSONArray;
        TrafficSpeed trafficSpeed;
        JSONObject optJSONObject = jSONObject.optJSONObject("travelAdvisory");
        if (optJSONObject == null || (optJSONArray = optJSONObject.optJSONArray("speedReadingIntervals")) == null) {
            return CollectionsKt.emptyList();
        }
        List createListBuilder = CollectionsKt.createListBuilder();
        int length = optJSONArray.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
            if (optJSONObject2 != null) {
                int coerceIn = RangesKt.coerceIn(optJSONObject2.optInt("startPolylinePointIndex", 0), 0, i);
                int coerceIn2 = RangesKt.coerceIn(optJSONObject2.optInt("endPolylinePointIndex", i), coerceIn, i);
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

    public static /* synthetic */ Object route$default(Router router, Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return router.route(context, geoPoint, geoPoint2, list, continuation);
    }

    private final JSONObject waypoint(GeoPoint geoPoint) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("latitude", geoPoint.getLat());
        jSONObject3.put("longitude", geoPoint.getLng());
        Unit unit = Unit.INSTANCE;
        jSONObject2.put("latLng", jSONObject3);
        Unit unit2 = Unit.INSTANCE;
        jSONObject.put("location", jSONObject2);
        return jSONObject;
    }

    public final Object alternatives(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        NavPlus.rtPick(context);
        return alternatives0(context, geoPoint, geoPoint2, list, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00c2 A[LOOP:0: B:17:0x00bc->B:19:0x00c2, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object alternatives0(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        alternatives.1 r1;
        Router router;
        alternatives.1 r7;
        Object route;
        Object obj;
        Iterator it;
        ArrayList arrayList;
        if (continuation instanceof alternatives.1) {
            r1 = (alternatives.1) continuation;
            if ((r1.label & Integer.MIN_VALUE) != 0) {
                r1.label -= Integer.MIN_VALUE;
                router = this;
                r7 = r1;
                Object obj2 = r7.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (r7.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj2);
                        if (WhenMappings.$EnumSwitchMapping$0[source.ordinal()] != 2) {
                            r7.L$0 = SpillingKt.nullOutSpilledVariable(context);
                            r7.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint);
                            r7.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint2);
                            r7.L$3 = SpillingKt.nullOutSpilledVariable(list);
                            r7.label = 2;
                            route = router.route(context, geoPoint, geoPoint2, NavPlus.altMarker(list), r7);
                            if (route == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            arrayList = NavPlus.googleChoices(route);
                            return NavPlus.fastestFirst(arrayList);
                        }
                        FreeRouter freeRouter = FreeRouter.INSTANCE;
                        r7.L$0 = SpillingKt.nullOutSpilledVariable(context);
                        r7.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint);
                        r7.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint2);
                        r7.L$3 = SpillingKt.nullOutSpilledVariable(list);
                        r7.label = 1;
                        Object alternatives = freeRouter.alternatives(context, geoPoint, geoPoint2, list, r7);
                        if (alternatives == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        obj = alternatives;
                        Iterable iterable = (Iterable) obj;
                        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                        it = iterable.iterator();
                        while (it.hasNext()) {
                            arrayList2.add(Route.copy$default((Route) it.next(), (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.OSM, 127, (Object) null));
                        }
                        arrayList = arrayList2;
                        return NavPlus.fastestFirst(arrayList);
                    case 1:
                        ResultKt.throwOnFailure(obj2);
                        obj = obj2;
                        Iterable iterable2 = (Iterable) obj;
                        ArrayList arrayList22 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                        it = iterable2.iterator();
                        while (it.hasNext()) {
                        }
                        arrayList = arrayList22;
                        return NavPlus.fastestFirst(arrayList);
                    case 2:
                        ResultKt.throwOnFailure(obj2);
                        route = obj2;
                        arrayList = NavPlus.googleChoices(route);
                        return NavPlus.fastestFirst(arrayList);
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        router = this;
        r1 = new alternatives.1(router, continuation);
        r7 = r1;
        Object obj22 = r7.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (r7.label) {
        }
    }

    public final RoutingSource getSource() {
        return source;
    }

    public final Object route(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        NavPlus.rtPick(context);
        return route0(context, geoPoint, geoPoint2, list, continuation);
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
    public final Object route0(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        route.1 r1;
        route.1 r6;
        Object googleRoute;
        Context context2;
        GeoPoint geoPoint3;
        GeoPoint geoPoint4;
        List list2;
        Context context3;
        GeoPoint geoPoint5;
        GeoPoint geoPoint6;
        Object obj;
        List list3;
        Context context4;
        GeoPoint geoPoint7;
        GeoPoint geoPoint8;
        Object obj2;
        List list4;
        Object route;
        Context context5;
        GeoPoint geoPoint9;
        List list5;
        GeoPoint geoPoint10;
        Route route2;
        Route route3;
        Object route4;
        Context context6;
        GeoPoint geoPoint11;
        List list6;
        Route copy$default;
        if (continuation instanceof route.1) {
            r1 = (route.1) continuation;
            if ((r1.label & Integer.MIN_VALUE) != 0) {
                r1.label -= Integer.MIN_VALUE;
                r6 = r1;
                Object obj3 = r6.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (r6.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj3);
                        switch (WhenMappings.$EnumSwitchMapping$0[source.ordinal()]) {
                            case 1:
                                if (!GoogleKey.INSTANCE.getPresent()) {
                                    FreeRouter freeRouter = FreeRouter.INSTANCE;
                                    r6.L$0 = SpillingKt.nullOutSpilledVariable(context);
                                    r6.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint);
                                    r6.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint2);
                                    r6.L$3 = SpillingKt.nullOutSpilledVariable(list);
                                    r6.label = 1;
                                    Object route5 = freeRouter.route(context, geoPoint, geoPoint2, list, r6);
                                    if (route5 == coroutine_suspended) {
                                        return coroutine_suspended;
                                    }
                                    context3 = context;
                                    geoPoint5 = geoPoint;
                                    geoPoint6 = geoPoint2;
                                    obj = route5;
                                    list3 = list;
                                    Route route6 = (Route) obj;
                                    return route6 != null ? Route.copy$default(route6, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.OSM, 127, (Object) null) : null;
                                }
                                r6.L$0 = SpillingKt.nullOutSpilledVariable(context);
                                r6.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint);
                                r6.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint2);
                                r6.L$3 = SpillingKt.nullOutSpilledVariable(list);
                                r6.label = 2;
                                googleRoute = googleRoute(context, geoPoint, geoPoint2, list, r6);
                                if (googleRoute == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context2 = context;
                                geoPoint3 = geoPoint;
                                geoPoint4 = geoPoint2;
                                list2 = list;
                                route2 = (Route) googleRoute;
                                if (route2 != null) {
                                    RoutingSource routingSource = RoutingSource.GOOGLE;
                                    r16 = Route.copy$default(route2, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, GPlus.sourceFor(route2), 127, (Object) null);
                                }
                                return r16;
                            case 2:
                                FreeRouter freeRouter2 = FreeRouter.INSTANCE;
                                r6.L$0 = SpillingKt.nullOutSpilledVariable(context);
                                r6.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint);
                                r6.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint2);
                                r6.L$3 = SpillingKt.nullOutSpilledVariable(list);
                                r6.label = 3;
                                Object route7 = freeRouter2.route(context, geoPoint, geoPoint2, list, r6);
                                if (route7 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context4 = context;
                                geoPoint7 = geoPoint;
                                geoPoint8 = geoPoint2;
                                obj2 = route7;
                                list4 = list;
                                Route route8 = (Route) obj2;
                                return route8 != null ? Route.copy$default(route8, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.OSM, 127, (Object) null) : null;
                            case 3:
                                OfflineRouter offlineRouter = OfflineRouter.INSTANCE;
                                r6.L$0 = context;
                                r6.L$1 = geoPoint;
                                r6.L$2 = geoPoint2;
                                r6.L$3 = list;
                                r6.label = 4;
                                route = offlineRouter.route(geoPoint, geoPoint2, list, r6);
                                if (route == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context5 = context;
                                geoPoint9 = geoPoint2;
                                list5 = list;
                                geoPoint10 = geoPoint;
                                route3 = (Route) route;
                                if (route3 == null && (copy$default = Route.copy$default(route3, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.BROUTER, 127, (Object) null)) != null) {
                                    return copy$default;
                                }
                                RideDiagnostics.INSTANCE.log("route", "BRouter unavailable (" + OfflineRouter.INSTANCE.getLastError() + ") — trying the free OSM server");
                                FreeRouter freeRouter3 = FreeRouter.INSTANCE;
                                r6.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                                r6.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint10);
                                r6.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint9);
                                r6.L$3 = SpillingKt.nullOutSpilledVariable(list5);
                                r6.L$4 = SpillingKt.nullOutSpilledVariable(this);
                                r6.I$0 = 0;
                                r6.label = 5;
                                route4 = freeRouter3.route(context5, geoPoint10, geoPoint9, list5, r6);
                                if (route4 != coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                context6 = context5;
                                geoPoint11 = geoPoint9;
                                list6 = list5;
                                Route route9 = (Route) route4;
                                return route9 != null ? Route.copy$default(route9, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.OSM, 127, (Object) null) : null;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    case 1:
                        list3 = (List) r6.L$3;
                        geoPoint6 = (GeoPoint) r6.L$2;
                        geoPoint5 = (GeoPoint) r6.L$1;
                        context3 = (Context) r6.L$0;
                        ResultKt.throwOnFailure(obj3);
                        obj = obj3;
                        Route route62 = (Route) obj;
                        return route62 != null ? Route.copy$default(route62, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.OSM, 127, (Object) null) : null;
                    case 2:
                        list2 = (List) r6.L$3;
                        geoPoint4 = (GeoPoint) r6.L$2;
                        geoPoint3 = (GeoPoint) r6.L$1;
                        context2 = (Context) r6.L$0;
                        ResultKt.throwOnFailure(obj3);
                        googleRoute = obj3;
                        route2 = (Route) googleRoute;
                        if (route2 != null) {
                        }
                        return r16;
                    case 3:
                        list4 = (List) r6.L$3;
                        geoPoint8 = (GeoPoint) r6.L$2;
                        geoPoint7 = (GeoPoint) r6.L$1;
                        context4 = (Context) r6.L$0;
                        ResultKt.throwOnFailure(obj3);
                        obj2 = obj3;
                        Route route82 = (Route) obj2;
                        return route82 != null ? Route.copy$default(route82, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.OSM, 127, (Object) null) : null;
                    case 4:
                        List list7 = (List) r6.L$3;
                        GeoPoint geoPoint12 = (GeoPoint) r6.L$2;
                        GeoPoint geoPoint13 = (GeoPoint) r6.L$1;
                        Context context7 = (Context) r6.L$0;
                        ResultKt.throwOnFailure(obj3);
                        list5 = list7;
                        geoPoint9 = geoPoint12;
                        context5 = context7;
                        route = obj3;
                        geoPoint10 = geoPoint13;
                        route3 = (Route) route;
                        if (route3 == null) {
                            break;
                        }
                        RideDiagnostics.INSTANCE.log("route", "BRouter unavailable (" + OfflineRouter.INSTANCE.getLastError() + ") — trying the free OSM server");
                        FreeRouter freeRouter32 = FreeRouter.INSTANCE;
                        r6.L$0 = SpillingKt.nullOutSpilledVariable(context5);
                        r6.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint10);
                        r6.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint9);
                        r6.L$3 = SpillingKt.nullOutSpilledVariable(list5);
                        r6.L$4 = SpillingKt.nullOutSpilledVariable(this);
                        r6.I$0 = 0;
                        r6.label = 5;
                        route4 = freeRouter32.route(context5, geoPoint10, geoPoint9, list5, r6);
                        if (route4 != coroutine_suspended) {
                        }
                        break;
                    case 5:
                        int i = r6.I$0;
                        list6 = (List) r6.L$3;
                        geoPoint11 = (GeoPoint) r6.L$2;
                        context6 = (Context) r6.L$0;
                        ResultKt.throwOnFailure(obj3);
                        route4 = obj3;
                        Route route92 = (Route) route4;
                        return route92 != null ? Route.copy$default(route92, (List) null, (List) null, 0.0d, 0.0d, (double[]) null, (List) null, (List) null, RoutingSource.OSM, 127, (Object) null) : null;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        r1 = new route.1(this, continuation);
        r6 = r1;
        Object obj32 = r6.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (r6.label) {
        }
    }

    public final void setSource(RoutingSource routingSource) {
        NavPlus.rtUserSet();
        setSource0(routingSource);
    }

    public final void setSource0(RoutingSource routingSource) {
        Intrinsics.checkNotNullParameter(routingSource, "<set-?>");
        source = routingSource;
    }
}
