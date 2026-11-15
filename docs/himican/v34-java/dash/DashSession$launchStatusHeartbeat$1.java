package com.example.wayfindr.dash;

import android.util.Log;
import androidx.autofill.HintConstants;
import com.example.wayfindr.dash.protocol.DashCommands;
import com.example.wayfindr.data.RideDiagnostics;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: DashSession.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession$launchStatusHeartbeat$1", f = "DashSession.kt", i = {0, 0, 0}, l = {487}, m = "invokeSuspend", n = {"$this$launch", HintConstants.AUTOFILL_HINT_PHONE, "n"}, s = {"L$0", "L$1", "I$0"})
/* loaded from: classes11.dex */
final class DashSession$launchStatusHeartbeat$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DashSocket $sock;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$launchStatusHeartbeat$1(DashSession dashSession, DashSocket dashSocket, Continuation<? super DashSession$launchStatusHeartbeat$1> continuation) {
        super(2, continuation);
        this.this$0 = dashSession;
        this.$sock = dashSocket;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashSession$launchStatusHeartbeat$1 dashSession$launchStatusHeartbeat$1 = new DashSession$launchStatusHeartbeat$1(this.this$0, this.$sock, continuation);
        dashSession$launchStatusHeartbeat$1.L$0 = obj;
        return dashSession$launchStatusHeartbeat$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashSession$launchStatusHeartbeat$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:10|(1:12)|13|14|15|16|(3:18|19|20)|24|(2:26|(2:28|(2:30|(1:32))))|33|(1:35)(4:36|7|8|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x006b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x006c, code lost:
    
        r5 = kotlin.Result.INSTANCE;
        kotlin.Result.m8392constructorimpl(kotlin.ResultKt.createFailure(r0));
     */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0139 -> B:7:0x013c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Object obj;
        int n;
        CoroutineScope $this$launch;
        DashSession$launchStatusHeartbeat$1 dashSession$launchStatusHeartbeat$1;
        MutableStateFlow mutableStateFlow;
        Object obj2;
        boolean z;
        long j;
        long j2;
        long j3;
        int i;
        CoroutineScope $this$launch2 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                n = 0;
                $this$launch = $this$launch2;
                dashSession$launchStatusHeartbeat$1 = this;
                if (CoroutineScopeKt.isActive($this$launch)) {
                    PhoneStatus latestPhoneStatus = dashSession$launchStatusHeartbeat$1.this$0.getLatestPhoneStatus();
                    if (latestPhoneStatus == null) {
                        latestPhoneStatus = new PhoneStatus(0, false, false, false, 0, 0, 63, null);
                    }
                    PhoneStatus phone = latestPhoneStatus;
                    DashSocket dashSocket = dashSession$launchStatusHeartbeat$1.$sock;
                    DashSession dashSession = dashSession$launchStatusHeartbeat$1.this$0;
                    Result.Companion companion = Result.INSTANCE;
                    DashCommands dashCommands = DashCommands.INSTANCE;
                    i = dashSession.compassHeading;
                    dashSocket.send(DashCommands.heartbeat$default(dashCommands, 0, i, phone, 1, null));
                    Result.m8392constructorimpl(Unit.INSTANCE);
                    int n2 = n + 1;
                    if (n % 30 == 0) {
                        DashSocket dashSocket2 = dashSession$launchStatusHeartbeat$1.$sock;
                        try {
                        } catch (Throwable th) {
                            Result.Companion companion2 = Result.INSTANCE;
                            Result.m8392constructorimpl(ResultKt.createFailure(th));
                        }
                        Result.Companion companion3 = Result.INSTANCE;
                        dashSocket2.send(DashCommands.INSTANCE.timeSync());
                        Result.m8392constructorimpl(Unit.INSTANCE);
                    }
                    mutableStateFlow = dashSession$launchStatusHeartbeat$1.this$0._state;
                    if (mutableStateFlow.getValue() == DashState.STREAMING) {
                        z = dashSession$launchStatusHeartbeat$1.this$0.loggedFirstAck;
                        if (z) {
                            j = dashSession$launchStatusHeartbeat$1.this$0.lastRxMs;
                            if (j > 0) {
                                long currentTimeMillis = System.currentTimeMillis();
                                j2 = dashSession$launchStatusHeartbeat$1.this$0.lastRxMs;
                                if (currentTimeMillis - j2 > 6000) {
                                    long currentTimeMillis2 = System.currentTimeMillis();
                                    j3 = dashSession$launchStatusHeartbeat$1.this$0.lastRxMs;
                                    long silent = (currentTimeMillis2 - j3) / 1000;
                                    Log.w("DashSession", "RX watchdog: no dash packets for " + silent + "s — link lost");
                                    RideDiagnostics.INSTANCE.log("error", "RX watchdog: dash silent " + silent + "s → link lost");
                                    dashSession$launchStatusHeartbeat$1.this$0.fail("Dash stopped responding — connection lost");
                                }
                            }
                        }
                    }
                    dashSession$launchStatusHeartbeat$1.L$0 = $this$launch;
                    dashSession$launchStatusHeartbeat$1.L$1 = SpillingKt.nullOutSpilledVariable(phone);
                    dashSession$launchStatusHeartbeat$1.I$0 = n2;
                    dashSession$launchStatusHeartbeat$1.label = 1;
                    if (DelayKt.delay(1000L, dashSession$launchStatusHeartbeat$1) == obj) {
                        return obj;
                    }
                    obj2 = obj;
                    n = n2;
                    obj = obj2;
                    if (CoroutineScopeKt.isActive($this$launch)) {
                    }
                }
                return Unit.INSTANCE;
            case 1:
                int n3 = this.I$0;
                ResultKt.throwOnFailure($result);
                n = n3;
                $this$launch = $this$launch2;
                obj2 = coroutine_suspended;
                dashSession$launchStatusHeartbeat$1 = this;
                obj = obj2;
                if (CoroutineScopeKt.isActive($this$launch)) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
