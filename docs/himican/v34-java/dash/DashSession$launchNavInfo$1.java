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
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession$launchNavInfo$1", f = "DashSession.kt", i = {0}, l = {530}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes11.dex */
final class DashSession$launchNavInfo$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$launchNavInfo$1(DashSession dashSession, Continuation<? super DashSession$launchNavInfo$1> continuation) {
        super(2, continuation);
        this.this$0 = dashSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashSession$launchNavInfo$1 dashSession$launchNavInfo$1 = new DashSession$launchNavInfo$1(this.this$0, continuation);
        dashSession$launchNavInfo$1.L$0 = obj;
        return dashSession$launchNavInfo$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashSession$launchNavInfo$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0043, code lost:
    
        r4 = r17.this$0.socket;
     */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Object obj;
        CoroutineScope $this$launch;
        MutableStateFlow mutableStateFlow;
        boolean z;
        DashSocket dashSocket;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        CoroutineScope $this$launch2 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                $this$launch = $this$launch2;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                $this$launch = $this$launch2;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (CoroutineScopeKt.isActive($this$launch)) {
            mutableStateFlow = this.this$0._state;
            if (mutableStateFlow.getValue() == DashState.STREAMING) {
                z = this.this$0.navActive;
                if (z && dashSocket != null) {
                    DashCommands dashCommands = DashCommands.INSTANCE;
                    i = this.this$0.navManeuver;
                    i2 = this.this$0.navPrimaryDist;
                    i3 = this.this$0.navPrimaryUnit;
                    i4 = this.this$0.navTotalDist;
                    i5 = this.this$0.navTotalUnit;
                    i6 = this.this$0.navSecondary;
                    i7 = this.this$0.navSecondaryDist;
                    i8 = this.this$0.navSecondaryUnit;
                    dashSocket.send(DashCommands.activeNavPacket$default(dashCommands, i, i2, i3, i4, i5, false, i6, i7, i8, 32, null));
                }
                this.L$0 = $this$launch;
                this.label = 1;
                if (DelayKt.delay(1000L, this) == obj) {
                    return obj;
                }
            } else {
                return Unit.INSTANCE;
            }
        }
        return Unit.INSTANCE;
    }
}
