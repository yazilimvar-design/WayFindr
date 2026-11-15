package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.util.InternetNetwork;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: Router.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/wayfindr/dash/nav/Route;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.Router$googleRoute$2", f = "Router.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class Router$googleRoute$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Route>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ GeoPoint $from;
    final /* synthetic */ List<GeoPoint> $intermediates;
    final /* synthetic */ GeoPoint $to;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Router$googleRoute$2(GeoPoint geoPoint, GeoPoint geoPoint2, List<GeoPoint> list, Context context, Continuation<? super Router$googleRoute$2> continuation) {
        super(2, continuation);
        this.$from = geoPoint;
        this.$to = geoPoint2;
        this.$intermediates = list;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Router$googleRoute$2(this.$from, this.$to, this.$intermediates, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Route> continuation) {
        return ((Router$googleRoute$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        JSONObject body;
        String responseBody;
        Route parse;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    body = Router.INSTANCE.buildRequest(this.$from, this.$to, this.$intermediates);
                    ApiUsage.INSTANCE.record(ApiUsage.ROUTES);
                    HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(this.$context, "https://routes.googleapis.com/directions/v2:computeRoutes");
                    openConnection.setRequestMethod("POST");
                    boolean z = true;
                    openConnection.setDoOutput(true);
                    openConnection.setRequestProperty("Content-Type", "application/json");
                    openConnection.setRequestProperty("X-Goog-Api-Key", "add gmaps api key here");
                    openConnection.setRequestProperty("X-Goog-FieldMask", "routes.distanceMeters,routes.duration,routes.polyline.encodedPolyline,routes.travelAdvisory.speedReadingIntervals,routes.legs.endLocation,routes.legs.steps.startLocation,routes.legs.steps.endLocation,routes.legs.steps.navigationInstruction");
                    openConnection.setConnectTimeout(15000);
                    openConnection.setReadTimeout(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
                    InputStream outputStream = openConnection.getOutputStream();
                    try {
                        String jSONObject = body.toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                        byte[] bytes = jSONObject.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                        outputStream.write(bytes);
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(outputStream, null);
                        int responseCode = openConnection.getResponseCode();
                        if (200 <= responseCode && responseCode < 300) {
                            outputStream = openConnection.getInputStream();
                            try {
                                InputStream inputStream = outputStream;
                                Intrinsics.checkNotNull(inputStream);
                                responseBody = new String(ByteStreamsKt.readBytes(inputStream), Charsets.UTF_8);
                                CloseableKt.closeFinally(outputStream, null);
                            } finally {
                            }
                        } else {
                            InputStream errorStream = openConnection.getErrorStream();
                            if (errorStream != null) {
                                outputStream = errorStream;
                                try {
                                    responseBody = new String(ByteStreamsKt.readBytes(outputStream), Charsets.UTF_8);
                                    CloseableKt.closeFinally(outputStream, null);
                                } finally {
                                    try {
                                        throw th;
                                    } finally {
                                    }
                                }
                            } else {
                                responseBody = "";
                            }
                        }
                        openConnection.disconnect();
                        if (200 > responseCode || responseCode >= 300) {
                            z = false;
                        }
                        if (z) {
                            parse = Router.INSTANCE.parse(responseBody);
                            return parse;
                        }
                        Log.e("Router", "Google Routes API failed: HTTP " + responseCode + ": " + responseBody);
                        return null;
                    } finally {
                        try {
                            throw th;
                        } finally {
                        }
                    }
                } catch (Exception e) {
                    Log.e("Router", "route() failed", e);
                    return null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
