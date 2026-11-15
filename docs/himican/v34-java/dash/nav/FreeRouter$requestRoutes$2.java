package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.util.InternetNetwork;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: FreeRouter.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/wayfindr/dash/nav/Route;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.FreeRouter$requestRoutes$2", f = "FreeRouter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class FreeRouter$requestRoutes$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Route>>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ GeoPoint $from;
    final /* synthetic */ boolean $includeAlternates;
    final /* synthetic */ List<GeoPoint> $intermediates;
    final /* synthetic */ GeoPoint $to;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FreeRouter$requestRoutes$2(GeoPoint geoPoint, GeoPoint geoPoint2, List<GeoPoint> list, boolean z, Context context, Continuation<? super FreeRouter$requestRoutes$2> continuation) {
        super(2, continuation);
        this.$from = geoPoint;
        this.$to = geoPoint2;
        this.$intermediates = list;
        this.$includeAlternates = z;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FreeRouter$requestRoutes$2(this.$from, this.$to, this.$intermediates, this.$includeAlternates, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Route>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<Route>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Route>> continuation) {
        return ((FreeRouter$requestRoutes$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    JSONObject body = FreeRouter.INSTANCE.buildRequest$app(this.$from, this.$to, this.$intermediates, this.$includeAlternates);
                    ApiUsage.INSTANCE.record(ApiUsage.FREE_ROUTES);
                    HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(this.$context, "https://valhalla1.openstreetmap.de/route");
                    openConnection.setRequestMethod("POST");
                    openConnection.setDoOutput(true);
                    openConnection.setRequestProperty("Content-Type", "application/json");
                    str = FreeRouter.USER_AGENT;
                    openConnection.setRequestProperty("User-Agent", str);
                    openConnection.setConnectTimeout(15000);
                    openConnection.setReadTimeout(25000);
                    InputStream outputStream = openConnection.getOutputStream();
                    try {
                        String jSONObject = body.toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                        byte[] bytes = jSONObject.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                        outputStream.write(bytes);
                        Unit unit = Unit.INSTANCE;
                        String text = null;
                        CloseableKt.closeFinally(outputStream, null);
                        int code = openConnection.getResponseCode();
                        InputStream inputStream = 200 <= code && code < 300 ? openConnection.getInputStream() : openConnection.getErrorStream();
                        if (inputStream != null) {
                            outputStream = inputStream;
                            try {
                                String str2 = new String(ByteStreamsKt.readBytes(outputStream), Charsets.UTF_8);
                                CloseableKt.closeFinally(outputStream, null);
                                text = str2;
                            } finally {
                            }
                        }
                        if (text == null) {
                            text = "";
                        }
                        openConnection.disconnect();
                        if (200 <= code && code < 300) {
                            return FreeRouter.INSTANCE.parseRoutes$app(text);
                        }
                        Log.e("FreeRouter", "Valhalla HTTP " + code + ": " + StringsKt.take(text, 300));
                        RideDiagnostics.INSTANCE.log("route", "free routing failed: HTTP " + code);
                        return CollectionsKt.emptyList();
                    } finally {
                        try {
                            throw th;
                        } finally {
                        }
                    }
                } catch (Exception e) {
                    Log.e("FreeRouter", "route request failed", e);
                    RideDiagnostics.INSTANCE.log("route", "free routing failed: " + e.getMessage());
                    return CollectionsKt.emptyList();
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
