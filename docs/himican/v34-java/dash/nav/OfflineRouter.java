package com.example.wayfindr.dash.nav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import btools.routingapp.IBRouterService;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: OfflineRouter.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class OfflineRouter {
    public static final String BROUTER_PACKAGE = "btools.routingapp";
    private static final String BROUTER_SERVICE = "btools.routingapp.BRouterService";
    public static final String FALLBACK_PROFILE = "car-eco";
    public static final String PROFILE = "car-fast";
    private static final String TAG = "OfflineRouter";
    private static Context appContext;
    private static volatile String lastError;
    public static final OfflineRouter INSTANCE = new OfflineRouter();
    public static final int $stable = 8;

    private OfflineRouter() {
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Not initialized variable reg: 16, insn: 0x02d6: MOVE (r11 I:??[OBJECT, ARRAY]) = (r16 I:??[OBJECT, ARRAY]), block:B:98:0x02d5 */
    /* JADX WARN: Not initialized variable reg: 20, insn: 0x02d8: MOVE (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r20 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]), block:B:98:0x02d5 */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x02da: MOVE (r10 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY]), block:B:98:0x02d5 */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 6 */
    public final java.lang.Object callService(android.content.Context r24, java.util.List r25, android.os.Bundle r26, kotlin.coroutines.Continuation r27) {
        /*
            Method dump skipped, instructions count: 820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.wayfindr.dash.nav.OfflineRouter.callService(android.content.Context, java.util.List, android.os.Bundle, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object callService$default(OfflineRouter offlineRouter, Context context, List list, Bundle bundle, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            bundle = null;
        }
        return offlineRouter.callService(context, list, bundle, continuation);
    }

    private final Void fail(String str) {
        lastError = str;
        Log.w(TAG, str);
        RideDiagnostics.INSTANCE.log("route", "offline routing failed: " + str);
        return null;
    }

    private final String instructionFor(int i, int i2) {
        switch (i) {
            case 1:
            case 13:
            case 16:
            default:
                return "Continue";
            case 2:
                return "Turn left";
            case 3:
                return "Slight left";
            case 4:
                return "Sharp left";
            case 5:
                return "Turn right";
            case 6:
                return "Slight right";
            case 7:
                return "Sharp right";
            case 8:
                return "Keep left";
            case 9:
                return "Keep right";
            case 10:
            case 11:
            case 12:
                return "Make a U-turn";
            case 14:
            case 15:
                return i2 > 0 ? "At the roundabout, take exit " + i2 : "Enter the roundabout";
            case 17:
                return "Take the exit on the left";
            case 18:
                return "Take the exit on the right";
        }
    }

    public static void mapWarnShim(String str) {
        NavPlus.mapWarn(str);
    }

    public static Route navAlt1(IBRouterService iBRouterService, Bundle bundle, int i, List list) {
        Route parse;
        try {
            bundle.putInt("alternativeidx", i);
            bundle.putString("maxRunningTime", "5");
            String trackFromParams = iBRouterService.getTrackFromParams(bundle);
            if (trackFromParams == null || !trackFromParams.trim().startsWith("{") || (parse = INSTANCE.parse(trackFromParams, list)) == null) {
                return null;
            }
            return NavPlus.asBRouter(parse);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void navAlts(IBRouterService iBRouterService, Bundle bundle, List list, String str) {
        try {
            NavPlus.brPending = null;
            if (!NavPlus.brWant || iBRouterService == null || bundle == null || list == null || str == null || !str.trim().startsWith("{")) {
                return;
            }
            NavPlus.brWant = false;
            List drop = CollectionsKt.drop(list, 1);
            Route parse = INSTANCE.parse(str, drop);
            if (parse != null) {
                long elapsedRealtime = SystemClock.elapsedRealtime() + 5000;
                NavPlus.BrJob brJob = new NavPlus.BrJob(iBRouterService, new Bundle(bundle), 1, drop);
                Thread thread = new Thread(brJob, "BRouterAlt1");
                thread.setDaemon(true);
                thread.start();
                NavPlus.BrJob brJob2 = new NavPlus.BrJob(iBRouterService, new Bundle(bundle), 2, drop);
                Thread thread2 = new Thread(brJob2, "BRouterAlt2");
                thread2.setDaemon(true);
                thread2.start();
                NavPlus.joinUntil(thread, elapsedRealtime);
                NavPlus.joinUntil(thread2, elapsedRealtime);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(parse);
                Route route = brJob.res;
                if (route != null && NavPlus.brDistinct(route, arrayList2)) {
                    arrayList.add(route);
                    arrayList2.add(route);
                }
                Route route2 = brJob2.res;
                if (route2 != null && NavPlus.brDistinct(route2, arrayList2)) {
                    arrayList.add(route2);
                    arrayList2.add(route2);
                }
                if (!arrayList.isEmpty()) {
                    NavPlus.brPending = arrayList;
                }
                NavPlus.rlog("BRouter alternatives: " + arrayList.size() + " in " + ((SystemClock.elapsedRealtime() - elapsedRealtime) + 5000) + " ms (parallel, 5 s limit)");
            }
        } catch (Throwable unused) {
        }
    }

    private final Route parse(String str, List list) {
        boolean z;
        JSONArray jSONArray;
        double d;
        int i = 0;
        JSONObject jSONObject = new JSONObject(str).getJSONArray("features").getJSONObject(0);
        JSONArray jSONArray2 = jSONObject.getJSONObject("geometry").getJSONArray("coordinates");
        ArrayList arrayList = new ArrayList(jSONArray2.length());
        int length = jSONArray2.length();
        for (int i2 = 0; i2 < length; i2++) {
            JSONArray jSONArray3 = jSONArray2.getJSONArray(i2);
            arrayList.add(new GeoPoint(jSONArray3.getDouble(1), jSONArray3.getDouble(0)));
        }
        if (arrayList.size() < 2) {
            return null;
        }
        double[] dArr = new double[arrayList.size()];
        int size = arrayList.size();
        for (int i3 = 1; i3 < size; i3++) {
            double d2 = dArr[i3 - 1];
            GeoPoint.Companion companion = GeoPoint.Companion;
            Object obj = arrayList.get(i3 - 1);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            Object obj2 = arrayList.get(i3);
            Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
            dArr[i3] = d2 + companion.distMeters((GeoPoint) obj, (GeoPoint) obj2);
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("properties");
        ArrayList arrayList2 = new ArrayList();
        ManeuverType maneuverType = ManeuverType.DEPART;
        Object obj3 = arrayList.get(0);
        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
        arrayList2.add(new Maneuver(maneuverType, "Head out", (GeoPoint) obj3, 0.0d, "DEPART"));
        JSONArray optJSONArray = jSONObject2.optJSONArray("voicehints");
        if (optJSONArray != null) {
            int i4 = 0;
            int length2 = optJSONArray.length();
            while (i4 < length2) {
                JSONArray jSONArray4 = optJSONArray.getJSONArray(i4);
                int coerceIn = RangesKt.coerceIn(jSONArray4.getInt(i), i, CollectionsKt.getLastIndex(arrayList));
                JSONObject jSONObject3 = jSONObject;
                int i5 = jSONArray4.getInt(1);
                int optInt = jSONArray4.optInt(2, i);
                String googleManeuverFor = INSTANCE.googleManeuverFor(i5);
                if (googleManeuverFor != null) {
                    ManeuverType fromGoogle = ManeuverType.Companion.fromGoogle(googleManeuverFor);
                    String instructionFor = INSTANCE.instructionFor(i5, optInt);
                    Object obj4 = arrayList.get(coerceIn);
                    Intrinsics.checkNotNullExpressionValue(obj4, "get(...)");
                    arrayList2.add(new Maneuver(fromGoogle, instructionFor, (GeoPoint) obj4, dArr[coerceIn], googleManeuverFor));
                }
                i4++;
                jSONObject = jSONObject3;
                i = 0;
            }
        }
        int i6 = 0;
        List list2 = list;
        boolean z2 = false;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        int i7 = 0;
        for (Object obj5 : list2) {
            int i8 = i7 + 1;
            if (i7 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int i9 = i6;
            GeoPoint geoPoint = (GeoPoint) obj5;
            List list3 = list2;
            if (i7 == CollectionsKt.getLastIndex(list)) {
                d = ArraysKt.last(dArr);
                z = z2;
                jSONArray = jSONArray2;
                i6 = i9;
            } else {
                double d3 = Double.MAX_VALUE;
                int i10 = i9;
                int size2 = arrayList.size();
                z = z2;
                int i11 = i9;
                while (i11 < size2) {
                    int i12 = size2;
                    GeoPoint.Companion companion2 = GeoPoint.Companion;
                    JSONArray jSONArray5 = jSONArray2;
                    Object obj6 = arrayList.get(i11);
                    Intrinsics.checkNotNullExpressionValue(obj6, "get(...)");
                    double distMeters = companion2.distMeters(geoPoint, (GeoPoint) obj6);
                    if (distMeters < d3) {
                        d3 = distMeters;
                        i10 = i11;
                    }
                    i11++;
                    size2 = i12;
                    jSONArray2 = jSONArray5;
                }
                jSONArray = jSONArray2;
                i6 = i10;
                d = dArr[i10];
            }
            arrayList3.add(Double.valueOf(d));
            i7 = i8;
            list2 = list3;
            z2 = z;
            jSONArray2 = jSONArray;
        }
        ArrayList arrayList4 = arrayList3;
        ArrayList arrayList5 = arrayList;
        ArrayList arrayList6 = arrayList2;
        String optString = jSONObject2.optString("track-length");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        Double doubleOrNull = StringsKt.toDoubleOrNull(optString);
        double doubleValue = doubleOrNull != null ? doubleOrNull.doubleValue() : ArraysKt.last(dArr);
        String optString2 = jSONObject2.optString("total-time");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        Double doubleOrNull2 = StringsKt.toDoubleOrNull(optString2);
        return new Route(arrayList5, arrayList6, doubleValue, doubleOrNull2 != null ? doubleOrNull2.doubleValue() : ArraysKt.last(dArr) / 11.0d, dArr, arrayList4, CollectionsKt.emptyList(), (RoutingSource) null, 128, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Object route$default(OfflineRouter offlineRouter, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        return offlineRouter.route(geoPoint, geoPoint2, list, continuation);
    }

    public final String getLastError() {
        return lastError;
    }

    public final String googleManeuverFor(int i) {
        switch (i) {
            case 1:
                return "STRAIGHT";
            case 2:
                return "TURN_LEFT";
            case 3:
                return "TURN_SLIGHT_LEFT";
            case 4:
                return "TURN_SHARP_LEFT";
            case 5:
                return "TURN_RIGHT";
            case 6:
                return "TURN_SLIGHT_RIGHT";
            case 7:
                return "TURN_SHARP_RIGHT";
            case 8:
                return "FORK_LEFT";
            case 9:
                return "FORK_RIGHT";
            case 10:
            case 11:
                return "UTURN_LEFT";
            case 12:
                return "UTURN_RIGHT";
            case 13:
            case 16:
            default:
                return null;
            case 14:
            case 15:
                return "ROUNDABOUT_LEFT";
            case 17:
                return "RAMP_LEFT";
            case 18:
                return "RAMP_RIGHT";
        }
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        appContext = context.getApplicationContext();
    }

    public final boolean isInstalled(Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.Companion;
            OfflineRouter offlineRouter = this;
            context.getPackageManager().getPackageInfo(BROUTER_PACKAGE, 0);
            obj = Result.constructor-impl(true);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = false;
        }
        return ((Boolean) obj).booleanValue();
    }

    public final void registerDebugProbe(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BuildConfig.DEBUG) {
            registerDebugProbe.rx.1 r0 = new registerDebugProbe.rx.1();
            IntentFilter intentFilter = new IntentFilter("com.wayfindr.BROUTER_PROBE");
            if (Build.VERSION.SDK_INT >= 33) {
                context.registerReceiver((BroadcastReceiver) r0, intentFilter, 2);
            } else {
                context.registerReceiver((BroadcastReceiver) r0, intentFilter);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0178 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f3 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object route(GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation) {
        route.1 r0;
        route.1 r3;
        Ref.ObjectRef objectRef;
        List list2;
        Context context;
        Ref.ObjectRef objectRef2;
        Object obj;
        List list3;
        GeoPoint geoPoint3;
        GeoPoint geoPoint4;
        String str;
        Object withContext;
        Ref.ObjectRef objectRef3;
        List list4;
        Context context2;
        List list5;
        GeoPoint geoPoint5;
        GeoPoint geoPoint6;
        Object obj2;
        String str2;
        if (continuation instanceof route.1) {
            r0 = (route.1) continuation;
            if ((r0.label & Integer.MIN_VALUE) != 0) {
                r0.label -= Integer.MIN_VALUE;
                r3 = r0;
                Object obj3 = r3.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (r3.label) {
                    case 0:
                        ResultKt.throwOnFailure(obj3);
                        Context context3 = appContext;
                        if (context3 == null) {
                            return fail("not initialised");
                        }
                        if (!isInstalled(context3)) {
                            return fail("BRouter app is not installed");
                        }
                        NavPlus.brWantFor(list);
                        List plus = CollectionsKt.plus(CollectionsKt.plus(CollectionsKt.listOf(geoPoint), list), geoPoint2);
                        objectRef = new Ref.ObjectRef();
                        CoroutineContext io = Dispatchers.getIO();
                        Function2 function2 = new route.text.1(context3, plus, (Continuation) null);
                        r3.L$0 = SpillingKt.nullOutSpilledVariable(geoPoint);
                        r3.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint2);
                        r3.L$2 = SpillingKt.nullOutSpilledVariable(list);
                        r3.L$3 = context3;
                        r3.L$4 = plus;
                        r3.L$5 = objectRef;
                        r3.L$6 = objectRef;
                        r3.label = 1;
                        Object withContext2 = BuildersKt.withContext(io, function2, r3);
                        if (withContext2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        list2 = list;
                        context = context3;
                        objectRef2 = objectRef;
                        obj = withContext2;
                        list3 = plus;
                        geoPoint3 = geoPoint2;
                        geoPoint4 = geoPoint;
                        str = (String) obj;
                        if (str != null) {
                            return null;
                        }
                        objectRef2.element = str;
                        ApiUsage.INSTANCE.record("BRouter (offline, on phone)");
                        if (!StringsKt.startsWith$default(StringsKt.trimStart((String) objectRef.element).toString(), "{", false, 2, (Object) null)) {
                            Log.i(TAG, "car-fast failed (" + StringsKt.take((String) objectRef.element, 120) + ") — retrying with car-eco");
                            CoroutineContext io2 = Dispatchers.getIO();
                            Function2 function22 = new route.retry.1(context, list3, (Continuation) null);
                            r3.L$0 = SpillingKt.nullOutSpilledVariable(geoPoint4);
                            r3.L$1 = SpillingKt.nullOutSpilledVariable(geoPoint3);
                            r3.L$2 = SpillingKt.nullOutSpilledVariable(list2);
                            r3.L$3 = SpillingKt.nullOutSpilledVariable(context);
                            r3.L$4 = list3;
                            r3.L$5 = objectRef;
                            r3.L$6 = null;
                            r3.label = 2;
                            withContext = BuildersKt.withContext(io2, function22, r3);
                            if (withContext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            objectRef3 = objectRef;
                            list4 = list3;
                            context2 = context;
                            list5 = list2;
                            geoPoint5 = geoPoint3;
                            geoPoint6 = geoPoint4;
                            str2 = (String) withContext;
                            if (str2 != null) {
                                return null;
                            }
                            ApiUsage.INSTANCE.record("BRouter (offline, on phone)");
                            objectRef3.element = str2;
                            objectRef = objectRef3;
                            list3 = list4;
                        }
                        if (!StringsKt.startsWith$default(StringsKt.trimStart((String) objectRef.element).toString(), "{", false, 2, (Object) null)) {
                            return fail("BRouter: " + StringsKt.take((String) objectRef.element, 200));
                        }
                        try {
                            Result.Companion companion = Result.Companion;
                            obj2 = Result.constructor-impl(parse((String) objectRef.element, CollectionsKt.drop(list3, 1)));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.Companion;
                            obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                        }
                        Throwable th2 = Result.exceptionOrNull-impl(obj2);
                        if (th2 != null) {
                            obj2 = (Route) INSTANCE.fail("unreadable BRouter result (" + th2.getMessage() + ")");
                        }
                        Route route = (Route) obj2;
                        if (route == null) {
                            return null;
                        }
                        NavPlus.brAttach(route);
                        lastError = null;
                        return route;
                    case 1:
                        objectRef2 = (Ref.ObjectRef) r3.L$6;
                        objectRef = (Ref.ObjectRef) r3.L$5;
                        list3 = (List) r3.L$4;
                        context = (Context) r3.L$3;
                        list2 = (List) r3.L$2;
                        geoPoint3 = (GeoPoint) r3.L$1;
                        geoPoint4 = (GeoPoint) r3.L$0;
                        ResultKt.throwOnFailure(obj3);
                        obj = obj3;
                        str = (String) obj;
                        if (str != null) {
                        }
                        break;
                    case 2:
                        objectRef3 = (Ref.ObjectRef) r3.L$5;
                        list4 = (List) r3.L$4;
                        context2 = (Context) r3.L$3;
                        list5 = (List) r3.L$2;
                        geoPoint5 = (GeoPoint) r3.L$1;
                        geoPoint6 = (GeoPoint) r3.L$0;
                        ResultKt.throwOnFailure(obj3);
                        withContext = obj3;
                        str2 = (String) withContext;
                        if (str2 != null) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        r0 = new route.1(this, continuation);
        r3 = r0;
        Object obj32 = r3.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (r3.label) {
        }
    }
}
