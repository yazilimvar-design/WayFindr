package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.util.InternetNetwork;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

/* compiled from: Router.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class Router$googleRoute$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ GeoPoint $from;
    final /* synthetic */ List $intermediates;
    final /* synthetic */ GeoPoint $to;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Router$googleRoute$2(GeoPoint geoPoint, GeoPoint geoPoint2, List list, Context context, Continuation continuation) {
        super(2, continuation);
        this.$from = geoPoint;
        this.$to = geoPoint2;
        this.$intermediates = list;
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new Router$googleRoute$2(this.$from, this.$to, this.$intermediates, this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object invokeSuspend(Object obj) {
        Exception exc;
        JSONObject buildRequest;
        boolean z;
        int responseCode;
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Route route = null;
                try {
                    buildRequest = Router.INSTANCE.buildRequest(this.$from, this.$to, this.$intermediates);
                    ApiUsage.INSTANCE.record("Google Routes API (computeRoutes)");
                    HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(this.$context, "https://routes.googleapis.com/directions/v2:computeRoutes");
                    openConnection.setRequestMethod("POST");
                    z = true;
                    openConnection.setDoOutput(true);
                    openConnection.setRequestProperty("Content-Type", "application/json");
                    openConnection.setRequestProperty("X-Goog-Api-Key", GPlus.key());
                    openConnection.setRequestProperty("X-Goog-FieldMask", "routes.distanceMeters,routes.duration,routes.polyline.encodedPolyline,routes.legs.endLocation,routes.legs.steps.startLocation,routes.legs.steps.endLocation,routes.legs.steps.navigationInstruction");
                    openConnection.setConnectTimeout(15000);
                    openConnection.setReadTimeout(20000);
                    OutputStream outputStream = openConnection.getOutputStream();
                    try {
                        String jSONObject = buildRequest.toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                        byte[] bytes = jSONObject.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                        outputStream.write(bytes);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStream, (Throwable) null);
                        responseCode = openConnection.getResponseCode();
                        if (200 <= responseCode && responseCode < 300) {
                            InputStream inputStream = openConnection.getInputStream();
                            try {
                                InputStream inputStream2 = inputStream;
                                Intrinsics.checkNotNull(inputStream2);
                                str = new String(ByteStreamsKt.readBytes(inputStream2), Charsets.UTF_8);
                                CloseableKt.closeFinally(inputStream, (Throwable) null);
                            } finally {
                            }
                        } else {
                            InputStream errorStream = openConnection.getErrorStream();
                            if (errorStream != null) {
                                InputStream inputStream3 = errorStream;
                                try {
                                    str = new String(ByteStreamsKt.readBytes(inputStream3), Charsets.UTF_8);
                                    CloseableKt.closeFinally(inputStream3, (Throwable) null);
                                } finally {
                                    try {
                                        throw th;
                                    } finally {
                                    }
                                }
                            } else {
                                str = "";
                            }
                        }
                        openConnection.disconnect();
                        if (200 > responseCode || responseCode >= 300) {
                            z = false;
                        }
                    } finally {
                        try {
                            throw th;
                        } finally {
                        }
                    }
                } catch (Exception e) {
                    Log.e("Router", "route() failed", e);
                    exc = e;
                }
                if (!z) {
                    Log.e("Router", "Google Routes API failed: HTTP " + responseCode + ": " + str);
                    return GPlus.googleFallback(this, null, str);
                }
                Router router = Router.INSTANCE;
                route = router.parse(str);
                NavPlus.stashAlts(this, str, route);
                exc = router;
                return GPlus.googleFallback(this, route, exc);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
