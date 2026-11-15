package com.example.wayfindr.dash.nav;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.compose.material.MenuKt;
import androidx.compose.runtime.ComposerKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.data.RideDiagnostics;
import com.google.android.gms.common.Scopes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: OfflineRouter.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000e\u001a\u00020\u000bJ0\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u000e\b\u0002\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00180\u001bH\u0086@¢\u0006\u0002\u0010\u001cJ\u000e\u0010\u001d\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ2\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u000b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00180\u001b2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"H\u0082@¢\u0006\u0002\u0010#J\u0012\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020\u0005H\u0002J \u0010'\u001a\u0004\u0018\u00010\u00162\u0006\u0010(\u001a\u00020\u00052\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00180\u001bH\u0002J\u0010\u0010*\u001a\u0004\u0018\u00010\u00052\u0006\u0010+\u001a\u00020,J\u0018\u0010-\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,2\u0006\u0010.\u001a\u00020,H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0012\u001a\u0004\u0018\u00010\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006/"}, d2 = {"Lcom/example/wayfindr/dash/nav/OfflineRouter;", "", "<init>", "()V", "TAG", "", "BROUTER_PACKAGE", "BROUTER_SERVICE", "PROFILE", "FALLBACK_PROFILE", "appContext", "Landroid/content/Context;", "init", "", "context", "isInstalled", "", "value", "lastError", "getLastError", "()Ljava/lang/String;", "route", "Lcom/example/wayfindr/dash/nav/Route;", "from", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "to", "intermediates", "", "(Lcom/example/wayfindr/dash/nav/GeoPoint;Lcom/example/wayfindr/dash/nav/GeoPoint;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "registerDebugProbe", "callService", "ctx", "points", "overrides", "Landroid/os/Bundle;", "(Landroid/content/Context;Ljava/util/List;Landroid/os/Bundle;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fail", "", "why", "parse", "json", "stops", "googleManeuverFor", "cmd", "", "instructionFor", "exit", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
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

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        appContext = context.getApplicationContext();
    }

    public final boolean isInstalled(Context context) {
        Object m8392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.INSTANCE;
            OfflineRouter offlineRouter = this;
            context.getPackageManager().getPackageInfo(BROUTER_PACKAGE, 0);
            m8392constructorimpl = Result.m8392constructorimpl(true);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = false;
        }
        return ((Boolean) m8392constructorimpl).booleanValue();
    }

    public final String getLastError() {
        return lastError;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object route$default(OfflineRouter offlineRouter, GeoPoint geoPoint, GeoPoint geoPoint2, List list, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        return offlineRouter.route(geoPoint, geoPoint2, list, continuation);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0175 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0176  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00f0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /* JADX WARN: Type inference failed for: r2v20, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v2, types: [T, java.lang.String] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object route(GeoPoint from, GeoPoint to, List<GeoPoint> list, Continuation<? super Route> continuation) {
        OfflineRouter$route$1 offlineRouter$route$1;
        OfflineRouter$route$1 offlineRouter$route$12;
        Ref.ObjectRef text;
        List intermediates;
        Context ctx;
        Ref.ObjectRef objectRef;
        Object obj;
        List intermediates2;
        GeoPoint to2;
        GeoPoint from2;
        ?? r9;
        Object withContext;
        Ref.ObjectRef text2;
        List points;
        Context ctx2;
        List points2;
        GeoPoint to3;
        GeoPoint from3;
        Object m8392constructorimpl;
        ?? r2;
        if (continuation instanceof OfflineRouter$route$1) {
            offlineRouter$route$1 = (OfflineRouter$route$1) continuation;
            if ((offlineRouter$route$1.label & Integer.MIN_VALUE) != 0) {
                offlineRouter$route$1.label -= Integer.MIN_VALUE;
                offlineRouter$route$12 = offlineRouter$route$1;
                Object $result = offlineRouter$route$12.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (offlineRouter$route$12.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        Context ctx3 = appContext;
                        if (ctx3 == null) {
                            return fail("not initialised");
                        }
                        if (!isInstalled(ctx3)) {
                            return fail("BRouter app is not installed");
                        }
                        List points3 = CollectionsKt.plus((Collection<? extends GeoPoint>) CollectionsKt.plus((Collection) CollectionsKt.listOf(from), (Iterable) list), to);
                        text = new Ref.ObjectRef();
                        CoroutineDispatcher io = Dispatchers.getIO();
                        OfflineRouter$route$text$1 offlineRouter$route$text$1 = new OfflineRouter$route$text$1(ctx3, points3, null);
                        offlineRouter$route$12.L$0 = SpillingKt.nullOutSpilledVariable(from);
                        offlineRouter$route$12.L$1 = SpillingKt.nullOutSpilledVariable(to);
                        offlineRouter$route$12.L$2 = SpillingKt.nullOutSpilledVariable(list);
                        offlineRouter$route$12.L$3 = ctx3;
                        offlineRouter$route$12.L$4 = points3;
                        offlineRouter$route$12.L$5 = text;
                        offlineRouter$route$12.L$6 = text;
                        offlineRouter$route$12.label = 1;
                        Object withContext2 = BuildersKt.withContext(io, offlineRouter$route$text$1, offlineRouter$route$12);
                        if (withContext2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        intermediates = list;
                        ctx = ctx3;
                        objectRef = text;
                        obj = withContext2;
                        intermediates2 = points3;
                        to2 = to;
                        from2 = from;
                        r9 = (String) obj;
                        if (r9 != 0) {
                            return null;
                        }
                        objectRef.element = r9;
                        ApiUsage.INSTANCE.record(ApiUsage.OFFLINE_ROUTES);
                        if (!StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) text.element).toString(), "{", false, 2, (Object) null)) {
                            Log.i(TAG, "car-fast failed (" + StringsKt.take((String) text.element, MenuKt.InTransitionDuration) + ") — retrying with car-eco");
                            CoroutineDispatcher io2 = Dispatchers.getIO();
                            OfflineRouter$route$retry$1 offlineRouter$route$retry$1 = new OfflineRouter$route$retry$1(ctx, intermediates2, null);
                            offlineRouter$route$12.L$0 = SpillingKt.nullOutSpilledVariable(from2);
                            offlineRouter$route$12.L$1 = SpillingKt.nullOutSpilledVariable(to2);
                            offlineRouter$route$12.L$2 = SpillingKt.nullOutSpilledVariable(intermediates);
                            offlineRouter$route$12.L$3 = SpillingKt.nullOutSpilledVariable(ctx);
                            offlineRouter$route$12.L$4 = intermediates2;
                            offlineRouter$route$12.L$5 = text;
                            offlineRouter$route$12.L$6 = null;
                            offlineRouter$route$12.label = 2;
                            withContext = BuildersKt.withContext(io2, offlineRouter$route$retry$1, offlineRouter$route$12);
                            if (withContext == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            text2 = text;
                            points = intermediates2;
                            ctx2 = ctx;
                            points2 = intermediates;
                            to3 = to2;
                            from3 = from2;
                            r2 = (String) withContext;
                            if (r2 != 0) {
                                return null;
                            }
                            ApiUsage.INSTANCE.record(ApiUsage.OFFLINE_ROUTES);
                            text2.element = r2;
                            text = text2;
                            intermediates2 = points;
                        }
                        if (!StringsKt.startsWith$default(StringsKt.trimStart((CharSequence) text.element).toString(), "{", false, 2, (Object) null)) {
                            return fail("BRouter: " + StringsKt.take((String) text.element, ComposerKt.invocationKey));
                        }
                        try {
                            Result.Companion companion = Result.INSTANCE;
                            m8392constructorimpl = Result.m8392constructorimpl(parse((String) text.element, CollectionsKt.drop(intermediates2, 1)));
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                        }
                        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
                        if (m8395exceptionOrNullimpl != null) {
                            m8392constructorimpl = (Route) INSTANCE.fail("unreadable BRouter result (" + m8395exceptionOrNullimpl.getMessage() + ")");
                        }
                        Route route = (Route) m8392constructorimpl;
                        if (route == null) {
                            return null;
                        }
                        lastError = null;
                        return route;
                    case 1:
                        objectRef = (Ref.ObjectRef) offlineRouter$route$12.L$6;
                        text = (Ref.ObjectRef) offlineRouter$route$12.L$5;
                        intermediates2 = (List) offlineRouter$route$12.L$4;
                        ctx = (Context) offlineRouter$route$12.L$3;
                        intermediates = (List) offlineRouter$route$12.L$2;
                        to2 = (GeoPoint) offlineRouter$route$12.L$1;
                        from2 = (GeoPoint) offlineRouter$route$12.L$0;
                        ResultKt.throwOnFailure($result);
                        obj = $result;
                        r9 = (String) obj;
                        if (r9 != 0) {
                        }
                        break;
                    case 2:
                        text2 = (Ref.ObjectRef) offlineRouter$route$12.L$5;
                        points = (List) offlineRouter$route$12.L$4;
                        ctx2 = (Context) offlineRouter$route$12.L$3;
                        points2 = (List) offlineRouter$route$12.L$2;
                        to3 = (GeoPoint) offlineRouter$route$12.L$1;
                        from3 = (GeoPoint) offlineRouter$route$12.L$0;
                        ResultKt.throwOnFailure($result);
                        withContext = $result;
                        r2 = (String) withContext;
                        if (r2 != 0) {
                        }
                        break;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        offlineRouter$route$1 = new OfflineRouter$route$1(this, continuation);
        offlineRouter$route$12 = offlineRouter$route$1;
        Object $result2 = offlineRouter$route$12.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (offlineRouter$route$12.label) {
        }
    }

    public final void registerDebugProbe(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (BuildConfig.DEBUG) {
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.example.wayfindr.dash.nav.OfflineRouter$registerDebugProbe$rx$1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context c, Intent intent) {
                    Intrinsics.checkNotNullParameter(c, "c");
                    Intrinsics.checkNotNullParameter(intent, "intent");
                    String stringExtra = intent.getStringExtra("lonlats");
                    if (stringExtra != null) {
                        String str = stringExtra;
                        int i = 1;
                        int i2 = 0;
                        Iterable split$default = StringsKt.split$default((CharSequence) str, new char[]{'|'}, false, 0, 6, (Object) null);
                        if (split$default == null) {
                            return;
                        }
                        Iterable<String> iterable = split$default;
                        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                        for (String str2 : iterable) {
                            char[] cArr = new char[i];
                            cArr[i2] = ',';
                            List split$default2 = StringsKt.split$default((CharSequence) str2, cArr, false, 0, 6, (Object) null);
                            arrayList.add(new GeoPoint(Double.parseDouble((String) split$default2.get(i)), Double.parseDouble((String) split$default2.get(i2))));
                            i = 1;
                            i2 = 0;
                        }
                        List pts = (List) arrayList;
                        Bundle bundle = new Bundle();
                        String stringExtra2 = intent.getStringExtra(Scopes.PROFILE);
                        if (stringExtra2 != null) {
                            bundle.putString(Scopes.PROFILE, stringExtra2);
                        }
                        String stringExtra3 = intent.getStringExtra("timode");
                        if (stringExtra3 != null) {
                            bundle.putString("timode", stringExtra3);
                        }
                        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new OfflineRouter$registerDebugProbe$rx$1$onReceive$1(c, pts, bundle, intent, null), 2, null);
                    }
                }
            };
            IntentFilter filter = new IntentFilter("com.wayfindr.BROUTER_PROBE");
            if (Build.VERSION.SDK_INT < 33) {
                context.registerReceiver(broadcastReceiver, filter);
            } else {
                context.registerReceiver(broadcastReceiver, filter, 2);
            }
        }
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
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Not initialized variable reg: 16, insn: 0x02d0: MOVE (r11 I:??[OBJECT, ARRAY]) = (r16 I:??[OBJECT, ARRAY] A[D('conn' com.example.wayfindr.dash.nav.OfflineRouter$callService$conn$1)]), block:B:98:0x02cf */
    /* JADX WARN: Not initialized variable reg: 20, insn: 0x02d2: MOVE (r7 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY]) = (r20 I:??[int, float, boolean, short, byte, char, OBJECT, ARRAY] A[D('bound' boolean)]), block:B:98:0x02cf */
    /* JADX WARN: Not initialized variable reg: 22, insn: 0x02d4: MOVE (r10 I:??[OBJECT, ARRAY]) = (r22 I:??[OBJECT, ARRAY] A[D('intent' android.content.Intent)]), block:B:98:0x02cf */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 6 */
    public final java.lang.Object callService(android.content.Context r24, java.util.List<com.example.wayfindr.dash.nav.GeoPoint> r25, android.os.Bundle r26, kotlin.coroutines.Continuation<? super java.lang.String> r27) {
        /*
            Method dump skipped, instructions count: 814
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

    private final Void fail(String why) {
        lastError = why;
        Log.w(TAG, why);
        RideDiagnostics.INSTANCE.log("route", "offline routing failed: " + why);
        return null;
    }

    private final Route parse(String json, List<GeoPoint> stops) {
        int i;
        JSONArray coords;
        double d;
        int i2 = 0;
        JSONObject feature = new JSONObject(json).getJSONArray("features").getJSONObject(0);
        JSONArray coords2 = feature.getJSONObject("geometry").getJSONArray("coordinates");
        ArrayList geometry = new ArrayList(coords2.length());
        int length = coords2.length();
        for (int i3 = 0; i3 < length; i3++) {
            JSONArray c = coords2.getJSONArray(i3);
            geometry.add(new GeoPoint(c.getDouble(1), c.getDouble(0)));
        }
        int i4 = geometry.size();
        if (i4 < 2) {
            return null;
        }
        double[] cum = new double[geometry.size()];
        int size = geometry.size();
        for (int i5 = 1; i5 < size; i5++) {
            double d2 = cum[i5 - 1];
            GeoPoint.Companion companion = GeoPoint.INSTANCE;
            Object obj = geometry.get(i5 - 1);
            Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
            Object obj2 = geometry.get(i5);
            Intrinsics.checkNotNullExpressionValue(obj2, "get(...)");
            cum[i5] = d2 + companion.distMeters((GeoPoint) obj, (GeoPoint) obj2);
        }
        JSONObject props = feature.getJSONObject("properties");
        ArrayList maneuvers = new ArrayList();
        ManeuverType maneuverType = ManeuverType.DEPART;
        Object obj3 = geometry.get(0);
        Intrinsics.checkNotNullExpressionValue(obj3, "get(...)");
        maneuvers.add(new Maneuver(maneuverType, "Head out", (GeoPoint) obj3, 0.0d, "DEPART"));
        JSONArray optJSONArray = props.optJSONArray("voicehints");
        if (optJSONArray != null) {
            int i6 = 0;
            int length2 = optJSONArray.length();
            while (i6 < length2) {
                JSONArray jSONArray = optJSONArray.getJSONArray(i6);
                int coerceIn = RangesKt.coerceIn(jSONArray.getInt(i2), i2, CollectionsKt.getLastIndex(geometry));
                JSONObject feature2 = feature;
                int i7 = jSONArray.getInt(1);
                int optInt = jSONArray.optInt(2, i2);
                String googleManeuverFor = INSTANCE.googleManeuverFor(i7);
                if (googleManeuverFor != null) {
                    ManeuverType fromGoogle = ManeuverType.INSTANCE.fromGoogle(googleManeuverFor);
                    String instructionFor = INSTANCE.instructionFor(i7, optInt);
                    Object obj4 = geometry.get(coerceIn);
                    Intrinsics.checkNotNullExpressionValue(obj4, "get(...)");
                    maneuvers.add(new Maneuver(fromGoogle, instructionFor, (GeoPoint) obj4, cum[coerceIn], googleManeuverFor));
                }
                i6++;
                feature = feature2;
                i2 = 0;
            }
        }
        int from = 0;
        List<GeoPoint> list = stops;
        int i8 = 0;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i9 = 0;
        for (Object obj5 : list) {
            int i10 = i9 + 1;
            if (i9 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int from2 = from;
            GeoPoint geoPoint = (GeoPoint) obj5;
            Iterable iterable = list;
            if (i9 == CollectionsKt.getLastIndex(stops)) {
                d = ArraysKt.last(cum);
                i = i8;
                coords = coords2;
                from = from2;
            } else {
                double d3 = Double.MAX_VALUE;
                int i11 = from2;
                int size2 = geometry.size();
                i = i8;
                int i12 = from2;
                while (i12 < size2) {
                    int i13 = size2;
                    GeoPoint.Companion companion2 = GeoPoint.INSTANCE;
                    JSONArray coords3 = coords2;
                    Object obj6 = geometry.get(i12);
                    Intrinsics.checkNotNullExpressionValue(obj6, "get(...)");
                    double distMeters = companion2.distMeters(geoPoint, (GeoPoint) obj6);
                    if (distMeters < d3) {
                        d3 = distMeters;
                        i11 = i12;
                    }
                    i12++;
                    size2 = i13;
                    coords2 = coords3;
                }
                coords = coords2;
                int from3 = i11;
                from = from3;
                d = cum[i11];
            }
            arrayList.add(Double.valueOf(d));
            i9 = i10;
            list = iterable;
            i8 = i;
            coords2 = coords;
        }
        List legEnds = (List) arrayList;
        ArrayList arrayList2 = geometry;
        ArrayList arrayList3 = maneuvers;
        String optString = props.optString("track-length");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        Double doubleOrNull = StringsKt.toDoubleOrNull(optString);
        double doubleValue = doubleOrNull != null ? doubleOrNull.doubleValue() : ArraysKt.last(cum);
        String optString2 = props.optString("total-time");
        Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
        Double doubleOrNull2 = StringsKt.toDoubleOrNull(optString2);
        return new Route(arrayList2, arrayList3, doubleValue, doubleOrNull2 != null ? doubleOrNull2.doubleValue() : ArraysKt.last(cum) / 11.0d, cum, legEnds, CollectionsKt.emptyList(), null, 128, null);
    }

    public final String googleManeuverFor(int cmd) {
        switch (cmd) {
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

    private final String instructionFor(int cmd, int exit) {
        switch (cmd) {
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
                return exit > 0 ? "At the roundabout, take exit " + exit : "Enter the roundabout";
            case 17:
                return "Take the exit on the left";
            case 18:
                return "Take the exit on the right";
        }
    }
}
