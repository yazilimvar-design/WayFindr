package com.example.wayfindr.dash.nav;

import androidx.appcompat.app.AppCompatDelegate;
import btools.routingapp.IBRouterService;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OfflineRouter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lbtools/routingapp/IBRouterService;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.OfflineRouter$callService$service$1", f = "OfflineRouter.kt", i = {}, l = {AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class OfflineRouter$callService$service$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super IBRouterService>, Object> {
    final /* synthetic */ CompletableDeferred<IBRouterService> $connected;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OfflineRouter$callService$service$1(CompletableDeferred<IBRouterService> completableDeferred, Continuation<? super OfflineRouter$callService$service$1> continuation) {
        super(2, continuation);
        this.$connected = completableDeferred;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OfflineRouter$callService$service$1(this.$connected, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super IBRouterService> continuation) {
        return ((OfflineRouter$callService$service$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object await = this.$connected.await(this);
                return await == coroutine_suspended ? coroutine_suspended : await;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
