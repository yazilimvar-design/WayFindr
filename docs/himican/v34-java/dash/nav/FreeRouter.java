package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import androidx.core.view.MotionEventCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.example.wayfindr.dash.nav.GeoPoint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
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

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: FreeRouter.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J8\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0010H\u0086@¢\u0006\u0002\u0010\u0011J<\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0010H\u0086@¢\u0006\u0002\u0010\u0011JB\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0082@¢\u0006\u0002\u0010\u0016J7\u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0019J\u001b\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\t0\u00102\u0006\u0010\u001b\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u001cJ\u0012\u0010\u001d\u001a\u0004\u0018\u00010\t2\u0006\u0010\u001e\u001a\u00020\u0018H\u0002J\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u00052\u0006\u0010 \u001a\u00020!R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082T¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/example/wayfindr/dash/nav/FreeRouter;", "", "<init>", "()V", "TAG", "", "ENDPOINT", "USER_AGENT", "route", "Lcom/example/wayfindr/dash/nav/Route;", "context", "Landroid/content/Context;", "from", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "to", "intermediates", "", "(Landroid/content/Context;Lcom/example/wayfindr/dash/nav/GeoPoint;Lcom/example/wayfindr/dash/nav/GeoPoint;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "alternatives", "requestRoutes", "includeAlternates", "", "(Landroid/content/Context;Lcom/example/wayfindr/dash/nav/GeoPoint;Lcom/example/wayfindr/dash/nav/GeoPoint;Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "buildRequest", "Lorg/json/JSONObject;", "buildRequest$app", "parseRoutes", "json", "parseRoutes$app", "parseTrip", "trip", "googleManeuverFor", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "", "MAX_ALTERNATES", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class FreeRouter {
    public static final int $stable = 0;
    private static final String ENDPOINT = "https://valhalla1.openstreetmap.de/route";
    private static final int MAX_ALTERNATES = 2;
    private static final String TAG = "FreeRouter";
    public static final FreeRouter INSTANCE = new FreeRouter();
    private static final String USER_AGENT = "Wayfindr/1.3.2-OSS-preview (personal motorcycle navigation app; Android)";

    private FreeRouter() {
    }

    public static /* synthetic */ Object route$default(FreeRouter freeRouter, Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return freeRouter.route(context, geoPoint, geoPoint2, list, continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object route(Context context, GeoPoint from, GeoPoint to, List<GeoPoint> list, Continuation<? super Route> continuation) {
        FreeRouter$route$1 freeRouter$route$1;
        FreeRouter$route$1 freeRouter$route$12;
        Object requestRoutes;
        if (continuation instanceof FreeRouter$route$1) {
            freeRouter$route$1 = (FreeRouter$route$1) continuation;
            if ((freeRouter$route$1.label & Integer.MIN_VALUE) != 0) {
                freeRouter$route$1.label -= Integer.MIN_VALUE;
                freeRouter$route$12 = freeRouter$route$1;
                Object $result = freeRouter$route$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (freeRouter$route$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        freeRouter$route$12.L$0 = SpillingKt.nullOutSpilledVariable(context);
                        freeRouter$route$12.L$1 = SpillingKt.nullOutSpilledVariable(from);
                        freeRouter$route$12.L$2 = SpillingKt.nullOutSpilledVariable(to);
                        freeRouter$route$12.L$3 = SpillingKt.nullOutSpilledVariable(list);
                        freeRouter$route$12.label = 1;
                        requestRoutes = requestRoutes(context, from, to, list, false, freeRouter$route$12);
                        if (requestRoutes != coroutine_suspended) {
                            break;
                        } else {
                            return coroutine_suspended;
                        }
                    case 1:
                        ResultKt.throwOnFailure($result);
                        requestRoutes = $result;
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                return CollectionsKt.firstOrNull((List) requestRoutes);
            }
        }
        freeRouter$route$1 = new FreeRouter$route$1(this, continuation);
        freeRouter$route$12 = freeRouter$route$1;
        Object $result2 = freeRouter$route$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (freeRouter$route$12.label) {
        }
        return CollectionsKt.firstOrNull((List) requestRoutes);
    }

    public static /* synthetic */ Object alternatives$default(FreeRouter freeRouter, Context context, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        List list2;
        if ((i & 8) == 0) {
            list2 = list;
        } else {
            list2 = CollectionsKt.emptyList();
        }
        return freeRouter.alternatives(context, geoPoint, geoPoint2, list2, continuation);
    }

    public final Object alternatives(Context context, GeoPoint from, GeoPoint to, List<GeoPoint> list, Continuation<? super List<Route>> continuation) {
        return requestRoutes(context, from, to, list, list.isEmpty(), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object requestRoutes(Context context, GeoPoint from, GeoPoint to, List<GeoPoint> list, boolean includeAlternates, Continuation<? super List<Route>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new FreeRouter$requestRoutes$2(from, to, list, includeAlternates, context, null), continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ JSONObject buildRequest$app$default(FreeRouter freeRouter, GeoPoint geoPoint, GeoPoint geoPoint2, List list, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        if ((i & 8) != 0) {
            z = false;
        }
        return freeRouter.buildRequest$app(geoPoint, geoPoint2, list, z);
    }

    public final JSONObject buildRequest$app(GeoPoint from, GeoPoint to, List<GeoPoint> intermediates, boolean includeAlternates) {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        Intrinsics.checkNotNullParameter(intermediates, "intermediates");
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (GeoPoint geoPoint : CollectionsKt.plus((Collection<? extends GeoPoint>) CollectionsKt.plus((Collection) CollectionsKt.listOf(from), (Iterable) intermediates), to)) {
            jSONArray.put(new JSONObject().put("lat", geoPoint.getLat()).put("lon", geoPoint.getLng()).put(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "break"));
            jSONObject = jSONObject;
        }
        JSONObject jSONObject2 = jSONObject;
        Unit unit = Unit.INSTANCE;
        jSONObject.put("locations", jSONArray);
        jSONObject.put("costing", "motorcycle");
        jSONObject.put("units", "kilometers");
        jSONObject.put("language", "en-US");
        if (includeAlternates && intermediates.isEmpty()) {
            jSONObject.put("alternates", 2);
        }
        return jSONObject2;
    }

    public final List<Route> parseRoutes$app(String json) {
        Object m8392constructorimpl;
        Intrinsics.checkNotNullParameter(json, "json");
        try {
            JSONObject root = new JSONObject(json);
            List createListBuilder = CollectionsKt.createListBuilder();
            JSONObject optJSONObject = root.optJSONObject("trip");
            if (optJSONObject != null) {
                createListBuilder.add(optJSONObject);
            }
            JSONArray optJSONArray = root.optJSONArray("alternates");
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
            Iterable trips = CollectionsKt.build(createListBuilder);
            Iterable<JSONObject> iterable = trips;
            Collection arrayList = new ArrayList();
            for (JSONObject jSONObject : iterable) {
                FreeRouter freeRouter = INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    m8392constructorimpl = Result.m8392constructorimpl(freeRouter.parseTrip(jSONObject));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                    m8392constructorimpl = null;
                }
                Route route = (Route) m8392constructorimpl;
                if (route != null) {
                    arrayList.add(route);
                }
            }
            HashSet hashSet = new HashSet();
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : (List) arrayList) {
                if (hashSet.add(((Route) obj).getGeometry())) {
                    arrayList2.add(obj);
                }
            }
            return arrayList2;
        } catch (Exception e) {
            Log.w(TAG, "Could not parse Valhalla route alternatives", e);
            return CollectionsKt.emptyList();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01d8 A[LOOP:4: B:55:0x01d6->B:56:0x01d8, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Route parseTrip(JSONObject trip) {
        double last;
        double d;
        int length;
        int i;
        JSONArray jSONArray;
        int[] iArr;
        int[] iArr2;
        JSONArray optJSONArray = trip.optJSONArray("legs");
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
            List<GeoPoint> decode = polylineCodec.decode(optString, 6);
            if (decode.isEmpty()) {
                return null;
            }
            int i3 = !arrayList.isEmpty() ? 1 : 0;
            iArr3[i2] = arrayList.size() - i3;
            arrayList.addAll(CollectionsKt.drop(decode, i3));
            iArr4[i2] = CollectionsKt.getLastIndex(arrayList);
        }
        if (arrayList.size() < 2) {
            return null;
        }
        double[] dArr = new double[arrayList.size()];
        int size = arrayList.size();
        for (int i4 = 1; i4 < size; i4++) {
            double d2 = dArr[i4 - 1];
            GeoPoint.Companion companion = GeoPoint.INSTANCE;
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
                    JSONObject jSONObject = optJSONArray2.getJSONObject(i6);
                    String googleManeuverFor = googleManeuverFor(jSONObject.optInt(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY));
                    if (googleManeuverFor == null) {
                        jSONArray = optJSONArray;
                        iArr = iArr3;
                        iArr2 = iArr4;
                    } else {
                        jSONArray = optJSONArray;
                        iArr = iArr3;
                        iArr2 = iArr4;
                        int coerceIn = RangesKt.coerceIn(iArr3[i5] + jSONObject.optInt("begin_shape_index", 0), 0, CollectionsKt.getLastIndex(arrayList));
                        ManeuverType fromGoogle = ManeuverType.INSTANCE.fromGoogle(googleManeuverFor);
                        String optString2 = jSONObject.optString("instruction");
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
        JSONObject optJSONObject = trip.optJSONObject("summary");
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
                        return new Route(arrayList3, arrayList4, last, d3, dArr, arrayList5, CollectionsKt.emptyList(), null, 128, null);
                    }
                }
                d = 0.0d;
                double d32 = d;
                ArrayList arrayList52 = new ArrayList(iArr5.length);
                i = 0;
                while (i < length) {
                }
                return new Route(arrayList3, arrayList4, last, d32, dArr, arrayList52, CollectionsKt.emptyList(), null, 128, null);
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
        return new Route(arrayList3, arrayList4, last, d322, dArr, arrayList522, CollectionsKt.emptyList(), null, 128, null);
    }

    public final String googleManeuverFor(int type) {
        switch (type) {
            case 1:
            case 2:
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
            case MotionEventCompat.AXIS_GENERIC_4 /* 35 */:
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
            case MotionEventCompat.AXIS_GENERIC_7 /* 38 */:
                return "MERGE";
            case 26:
                return "ROUNDABOUT_LEFT";
            case MotionEventCompat.AXIS_RELATIVE_Y /* 28 */:
                return "FERRY";
        }
    }
}
