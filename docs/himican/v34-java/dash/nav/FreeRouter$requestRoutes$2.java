package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.util.InternetNetwork;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

/* compiled from: FreeRouter.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class FreeRouter$requestRoutes$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $context;
    final /* synthetic */ GeoPoint $from;
    final /* synthetic */ boolean $includeAlternates;
    final /* synthetic */ List $intermediates;
    final /* synthetic */ GeoPoint $to;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    FreeRouter$requestRoutes$2(GeoPoint geoPoint, GeoPoint geoPoint2, List list, boolean z, Context context, Continuation continuation) {
        super(2, continuation);
        this.$from = geoPoint;
        this.$to = geoPoint2;
        this.$intermediates = list;
        this.$includeAlternates = z;
        this.$context = context;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new FreeRouter$requestRoutes$2(this.$from, this.$to, this.$intermediates, this.$includeAlternates, this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                try {
                    JSONObject buildRequest$app = FreeRouter.INSTANCE.buildRequest$app(this.$from, this.$to, this.$intermediates, this.$includeAlternates);
                    ApiUsage.INSTANCE.record("Valhalla routing (OSM, free)");
                    HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(this.$context, "https://valhalla1.openstreetmap.de/route");
                    openConnection.setRequestMethod("POST");
                    openConnection.setDoOutput(true);
                    openConnection.setRequestProperty("Content-Type", "application/json");
                    str = FreeRouter.USER_AGENT;
                    openConnection.setRequestProperty("User-Agent", str);
                    openConnection.setConnectTimeout(8000);
                    openConnection.setReadTimeout(25000);
                    InputStream outputStream = openConnection.getOutputStream();
                    try {
                        String jSONObject = buildRequest$app.toString();
                        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
                        byte[] bytes = jSONObject.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                        outputStream.write(bytes);
                        Unit unit = Unit.INSTANCE;
                        String str2 = null;
                        CloseableKt.closeFinally(outputStream, (Throwable) null);
                        int responseCode = openConnection.getResponseCode();
                        InputStream inputStream = 200 <= responseCode && responseCode < 300 ? openConnection.getInputStream() : openConnection.getErrorStream();
                        if (inputStream != null) {
                            outputStream = inputStream;
                            try {
                                String str3 = new String(ByteStreamsKt.readBytes(outputStream), Charsets.UTF_8);
                                CloseableKt.closeFinally(outputStream, (Throwable) null);
                                str2 = str3;
                            } finally {
                            }
                        }
                        if (str2 == null) {
                            str2 = "";
                        }
                        openConnection.disconnect();
                        if (200 <= responseCode && responseCode < 300) {
                            return FreeRouter.INSTANCE.parseRoutes$app(str2);
                        }
                        Log.e("FreeRouter", "Valhalla HTTP " + responseCode + ": " + StringsKt.take(str2, 300));
                        RideDiagnostics.INSTANCE.log("route", "free routing failed: HTTP " + responseCode);
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
