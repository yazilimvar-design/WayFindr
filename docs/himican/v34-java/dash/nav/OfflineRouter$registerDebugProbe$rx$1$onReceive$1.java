package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.Scopes;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OfflineRouter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.OfflineRouter$registerDebugProbe$rx$1$onReceive$1", f = "OfflineRouter.kt", i = {}, l = {87}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class OfflineRouter$registerDebugProbe$rx$1$onReceive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $c;
    final /* synthetic */ Bundle $extra;
    final /* synthetic */ Intent $intent;
    final /* synthetic */ List<GeoPoint> $pts;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OfflineRouter$registerDebugProbe$rx$1$onReceive$1(Context context, List<GeoPoint> list, Bundle bundle, Intent intent, Continuation<? super OfflineRouter$registerDebugProbe$rx$1$onReceive$1> continuation) {
        super(2, continuation);
        this.$c = context;
        this.$pts = list;
        this.$extra = bundle;
        this.$intent = intent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OfflineRouter$registerDebugProbe$rx$1$onReceive$1(this.$c, this.$pts, this.$extra, this.$intent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OfflineRouter$registerDebugProbe$rx$1$onReceive$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object callService;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                OfflineRouter offlineRouter = OfflineRouter.INSTANCE;
                Context applicationContext = this.$c.getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                this.label = 1;
                callService = offlineRouter.callService(applicationContext, this.$pts, this.$extra, this);
                if (callService == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                callService = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        String res = (String) callService;
        String stringExtra = this.$intent.getStringExtra(Scopes.PROFILE);
        if (stringExtra == null) {
            stringExtra = OfflineRouter.PROFILE;
        }
        String stringExtra2 = this.$intent.getStringExtra("timode");
        if (stringExtra2 == null) {
            stringExtra2 = "3";
        }
        Log.i("OfflineRouter", "PROBE " + stringExtra + " timode=" + stringExtra2 + " → " + (res != null ? StringsKt.take(res, 300) : null));
        return Unit.INSTANCE;
    }
}
