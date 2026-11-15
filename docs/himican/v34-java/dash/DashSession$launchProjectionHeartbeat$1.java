package com.example.wayfindr.dash;

import com.example.wayfindr.dash.protocol.DashCommands;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: DashSession.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession$launchProjectionHeartbeat$1", f = "DashSession.kt", i = {0}, l = {497}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes11.dex */
final class DashSession$launchProjectionHeartbeat$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$launchProjectionHeartbeat$1(DashSession dashSession, Continuation<? super DashSession$launchProjectionHeartbeat$1> continuation) {
        super(2, continuation);
        this.this$0 = dashSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashSession$launchProjectionHeartbeat$1 dashSession$launchProjectionHeartbeat$1 = new DashSession$launchProjectionHeartbeat$1(this.this$0, continuation);
        dashSession$launchProjectionHeartbeat$1.L$0 = obj;
        return dashSession$launchProjectionHeartbeat$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashSession$launchProjectionHeartbeat$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object obj;
        CoroutineScope $this$launch;
        DashSession$launchProjectionHeartbeat$1 dashSession$launchProjectionHeartbeat$1;
        MutableStateFlow mutableStateFlow;
        DashSocket dashSocket;
        CoroutineScope $this$launch2 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                $this$launch = $this$launch2;
                dashSession$launchProjectionHeartbeat$1 = this;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                $this$launch = $this$launch2;
                dashSession$launchProjectionHeartbeat$1 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive($this$launch)) {
            mutableStateFlow = dashSession$launchProjectionHeartbeat$1.this$0._state;
            if (mutableStateFlow.getValue() == DashState.STREAMING) {
                dashSocket = dashSession$launchProjectionHeartbeat$1.this$0.socket;
                if (dashSocket != null) {
                    dashSocket.send(DashCommands.INSTANCE.projectionFrame());
                }
                dashSession$launchProjectionHeartbeat$1.L$0 = $this$launch;
                dashSession$launchProjectionHeartbeat$1.label = 1;
                if (DelayKt.delay(250L, dashSession$launchProjectionHeartbeat$1) == obj) {
                    return obj;
                }
            } else {
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }
}
