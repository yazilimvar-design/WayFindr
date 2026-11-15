package com.example.wayfindr.viewmodel;

import com.example.wayfindr.dash.DashState;
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

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$connect$3", f = "DashViewModel.kt", i = {0}, l = {676}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes4.dex */
final class DashViewModel$connect$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$connect$3(DashViewModel dashViewModel, Continuation<? super DashViewModel$connect$3> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashViewModel$connect$3 dashViewModel$connect$3 = new DashViewModel$connect$3(this.this$0, continuation);
        dashViewModel$connect$3.L$0 = obj;
        return dashViewModel$connect$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$connect$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object obj;
        CoroutineScope $this$launch;
        DashViewModel$connect$3 dashViewModel$connect$3;
        CoroutineScope $this$launch2 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                $this$launch = $this$launch2;
                dashViewModel$connect$3 = this;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                $this$launch = $this$launch2;
                dashViewModel$connect$3 = this;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive($this$launch) && dashViewModel$connect$3.this$0.session.getState().getValue() != DashState.STREAMING) {
            dashViewModel$connect$3.this$0.tick();
            dashViewModel$connect$3.L$0 = $this$launch;
            dashViewModel$connect$3.label = 1;
            if (DelayKt.delay(250L, dashViewModel$connect$3) == obj) {
                return obj;
            }
        }
        return Unit.INSTANCE;
    }
}
