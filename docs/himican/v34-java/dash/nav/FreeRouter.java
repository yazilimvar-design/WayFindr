package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.viewmodel.RouteViewModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Result;
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

/* compiled from: FreeRouter.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class FreeRouter {
    public static final int $stable = 0;
    private static final String ENDPOINT = "https://valhalla1.openstreetmap.de/route";
    private static final int MAX_ALTERNATES = 2;
    private static final String TAG = "FreeRouter";
    public static final FreeRouter INSTANCE = new FreeRouter();
    private static final String USER_AGENT = "Wayfindr/1.3.2-OSS-preview (personal motorcycle navigation app; Android)";

    private FreeRouter() {
    }

    public static /* synthetic */ JSONObject buildRequest$app$default(FreeRouter freeRouter, GeoPoint geoPoint, GeoPoint geoPoint2, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return freeRouter.buildRequest$app(geoPoint, geoPoint2, list, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01d8 A[LOOP:4: B:55:0x01d6->B:56:0x01d8, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Route parseTrip(JSONObject jSONObject) {
        double last;
        double d;
        int length;
        int i;
        JSONArray jSONArray;
        int[] iArr;
        int[] iArr2;
        JSONArray optJSONArray = jSONObject.optJSONArray("legs");
        if (optJSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        int[] iArr3 = new int[optJSONArray.length()];
        int[] iArr4 = new int[optJSONArray.length()];
        int length2 = optJSONArray.length();
        for (int i2 = 0; i2 < length2; i2++) {
            PolylineCodec polylineCodec = PolylineCodec.INSTANCE;
            String optString = optJSONArray.getJSONObject(i2).optString("shape");
            Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
            List decode = polylineCodec.decode(optString, 6);
            if (decode.isEmpty()) {
                return null;
            }
            int i3 = !arrayList.isEmpty() ? 1 : 0;
            iArr3[i2] = arrayList.size() - i3;
            arrayList.addAll(CollectionsKt.drop(decode, i3));
            iArr4[i2] = CollectionsKt.getLastIndex(arrayList);
        }
        if (arrayList.size() < MAX_ALTERNATES) {
            return null;
        }
        double[] dArr = new double[arrayList.size()];
        int size = arrayList.size();
        for (int i4 = 1; i4 < size; i4++) {
            double d2 = dArr[i4 - 1];
            GeoPoint.Companion companion = GeoPoint.Companion;
            Object obj = arrayList.get(i4 - 1);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            Object obj2 = arrayList.get(i4);
            Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
            dArr[i4] = d2 + companion.distMeters((GeoPoint) obj, (GeoPoint) obj2);
        }
        ArrayList arrayList2 = new ArrayList();
        int i5 = 0;
        int length3 = optJSONArray.length();
        while (i5 < length3) {
            JSONArray optJSONArray2 = optJSONArray.getJSONObject(i5).optJSONArray("maneuvers");
            if (optJSONArray2 != null) {
                int i6 = 0;
                int length4 = optJSONArray2.length();
                while (i6 < length4) {
                    JSONObject jSONObject2 = optJSONArray2.getJSONObject(i6);
                    String googleManeuverFor = googleManeuverFor(jSONObject2.optInt("type"));
                    if (googleManeuverFor == null) {
                        jSONArray = optJSONArray;
                        iArr = iArr3;
                        iArr2 = iArr4;
                    } else {
                        jSONArray = optJSONArray;
                        iArr = iArr3;
                        iArr2 = iArr4;
                        int coerceIn = RangesKt.coerceIn(iArr3[i5] + jSONObject2.optInt("begin_shape_index", 0), 0, CollectionsKt.getLastIndex(arrayList));
                        ManeuverType fromGoogle = ManeuverType.Companion.fromGoogle(googleManeuverFor);
                        String optString2 = jSONObject2.optString("instruction");
                        if (StringsKt.isBlank(optString2)) {
                            optString2 = "Continue";
                        }
                        Intrinsics.checkNotNullExpressionValue(optString2, "ifBlank(...)");
                        Object obj3 = arrayList.get(coerceIn);
                        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
                        arrayList2.add(new Maneuver(fromGoogle, optString2, (GeoPoint) obj3, dArr[coerceIn], googleManeuverFor));
                    }
                    i6++;
                    optJSONArray = jSONArray;
                    iArr3 = iArr;
                    iArr4 = iArr2;
                }
            }
            i5++;
            optJSONArray = optJSONArray;
            iArr3 = iArr3;
            iArr4 = iArr4;
        }
        int[] iArr5 = iArr4;
        JSONObject optJSONObject = jSONObject.optJSONObject("summary");
        ArrayList arrayList3 = arrayList;
        ArrayList arrayList4 = arrayList2;
        if (optJSONObject != null) {
            Double valueOf = Double.valueOf(optJSONObject.optDouble("length"));
            if (Double.isNaN(valueOf.doubleValue())) {
                valueOf = null;
            }
            if (valueOf != null) {
                last = valueOf.doubleValue() * 1000.0d;
                if (optJSONObject != null) {
                    Double valueOf2 = Double.valueOf(optJSONObject.optDouble("time"));
                    if (Double.isNaN(valueOf2.doubleValue())) {
                        valueOf2 = null;
                    }
                    if (valueOf2 != null) {
                        d = valueOf2.doubleValue();
                        double d3 = d;
                        ArrayList arrayList5 = new ArrayList(iArr5.length);
                        i = 0;
                        for (length = iArr5.length; i < length; length = length) {
                            arrayList5.add(Double.valueOf(dArr[iArr5[i]]));
                            i++;
                        }
                        return new Route(arrayList3, arrayList4, last, d3, dArr, arrayList5, CollectionsKt.emptyList(), (RoutingSource) null, 128, (DefaultConstructorMarker) null);
                    }
                }
                d = 0.0d;
                double d32 = d;
                ArrayList arrayList52 = new ArrayList(iArr5.length);
                i = 0;
                while (i < length) {
                }
                return new Route(arrayList3, arrayList4, last, d32, dArr, arrayList52, CollectionsKt.emptyList(), (RoutingSource) null, 128, (DefaultConstructorMarker) null);
            }
        }
        last = ArraysKt.last(dArr);
        if (optJSONObject != null) {
        }
        d = 0.0d;
        double d322 = d;
        ArrayList arrayList522 = new ArrayList(iArr5.length);
        i = 0;
        while (i < length) {
        }
        return new Route(arrayList3, arrayList4, last, d322, dArr, arrayList522, CollectionsKt.emptyList(), (RoutingSource) null, 128, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object requestRoutes(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, boolean z, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new FreeRouter$requestRoutes$2(geoPoint, geoPoint2, list, z, context, null), continuation);
    }

    public static /* synthetic */ Object route$default(FreeRouter freeRouter, Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return freeRouter.route(context, geoPoint, geoPoint2, list, continuation);
    }

    public final Object alternatives(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        return requestRoutes(context, geoPoint, geoPoint2, list, list.isEmpty(), continuation);
    }

    public final JSONObject buildRequest$app(GeoPoint geoPoint, GeoPoint geoPoint2, List list, boolean z) {
        Intrinsics.checkNotNullParameter(geoPoint, "from");
        Intrinsics.checkNotNullParameter(geoPoint2, "to");
        Intrinsics.checkNotNullParameter(list, "intermediates");
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (GeoPoint geoPoint3 : CollectionsKt.plus(CollectionsKt.plus(CollectionsKt.listOf(geoPoint), list), geoPoint2)) {
            jSONArray.put(new JSONObject().put("lat", geoPoint3.getLat()).put("lon", geoPoint3.getLng()).put("type", "break"));
            jSONObject = jSONObject;
        }
        NavPlus.addHeading(jSONArray, geoPoint);
        JSONObject jSONObject2 = jSONObject;
        Unit unit = Unit.INSTANCE;
        jSONObject.put("locations", jSONArray);
        jSONObject.put("costing", "motorcycle");
        jSONObject.put("units", "kilometers");
        jSONObject.put("language", "en-US");
        if (z && list.isEmpty()) {
            jSONObject.put("alternates", MAX_ALTERNATES);
        }
        return jSONObject2;
    }

    public final String googleManeuverFor(int i) {
        switch (i) {
            case 1:
            case MAX_ALTERNATES /* 2 */:
            case 3:
                return "DEPART";
            case 4:
            case 5:
            case 6:
            case 27:
            case 29:
                return null;
            case 7:
            case 8:
            case 17:
            case 22:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            default:
                return "STRAIGHT";
            case 9:
                return "TURN_SLIGHT_RIGHT";
            case 10:
                return "TURN_RIGHT";
            case 11:
                return "TURN_SHARP_RIGHT";
            case 12:
                return "UTURN_RIGHT";
            case 13:
                return "UTURN_LEFT";
            case 14:
                return "TURN_SHARP_LEFT";
            case 15:
                return "TURN_LEFT";
            case 16:
                return "TURN_SLIGHT_LEFT";
            case 18:
            case 20:
                return "RAMP_RIGHT";
            case 19:
            case 21:
                return "RAMP_LEFT";
            case 23:
                return "FORK_RIGHT";
            case 24:
                return "FORK_LEFT";
            case 25:
            case 37:
            case 38:
                return "MERGE";
            case RouteViewModel.MAX_STOPS /* 26 */:
                return "ROUNDABOUT_LEFT";
            case 28:
                return "FERRY";
        }
    }

    public final List parseRoutes$app(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "json");
        try {
            JSONObject jSONObject = new JSONObject(str);
            List createListBuilder = CollectionsKt.createListBuilder();
            JSONObject optJSONObject = jSONObject.optJSONObject("trip");
            if (optJSONObject != null) {
                createListBuilder.add(optJSONObject);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("alternates");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    if (optJSONObject2 != null) {
                        JSONObject optJSONObject3 = optJSONObject2.optJSONObject("trip");
                        if (optJSONObject3 == null) {
                            optJSONObject3 = optJSONObject2;
                        }
                        createListBuilder.add(optJSONObject3);
                    }
                }
            }
            List<JSONObject> build = CollectionsKt.build(createListBuilder);
            ArrayList arrayList = new ArrayList();
            for (JSONObject jSONObject2 : build) {
                FreeRouter freeRouter = INSTANCE;
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.constructor-impl(freeRouter.parseTrip(jSONObject2));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.isFailure-impl(obj)) {
                    obj = null;
                }
                Route route = (Route) obj;
                if (route != null) {
                    arrayList.add(route);
                }
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : arrayList) {
                if (hashSet.add(((Route) obj2).getGeometry())) {
                    arrayList2.add(obj2);
                }
            }
            return arrayList2;
        } catch (Exception e) {
            Log.w(TAG, "Could not parse Valhalla route alternatives", e);
            return CollectionsKt.emptyList();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object route(Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        route.1 r0;
        route.1 r7;
        Object requestRoutes;
        if (continuation instanceof route.1) {
            r0 = (route.1) continuation;
            if ((r0.label & Integer.MIN_VALUE) != 0) {
                r0.label -= Integer.MIN_VALUE;
                r7 = r0;
                Object obj = r7.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (r7.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj);
                        r7.L$0 = SpillingKt.nullOutSpilledVariable(context);
                        r7.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint);
                        r7.L$2 = SpillingKt.nullOutSpilledVariable(geoPoint2);
                        r7.L$3 = SpillingKt.nullOutSpilledVariable(list);
                        r7.label = 1;
                        requestRoutes = requestRoutes(context, geoPoint, geoPoint2, list, false, r7);
                        if (requestRoutes != coroutine_suspended) {
                            break;
                        } else {
                            return coroutine_suspended;
                        }
                    case 1:
                        ResultKt.throwOnFailure(obj);
                        requestRoutes = obj;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return CollectionsKt.firstOrNull((List) requestRoutes);
            }
        }
        r0 = new route.1(this, continuation);
        r7 = r0;
        Object obj2 = r7.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (r7.label) {
        }
        return CollectionsKt.firstOrNull((List) requestRoutes);
    }
}
