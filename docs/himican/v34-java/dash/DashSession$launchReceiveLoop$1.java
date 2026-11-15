package com.example.wayfindr.dash;

import android.util.Log;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import okhttp3.internal.http.StatusLine;

/* compiled from: DashSession.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession$launchReceiveLoop$1", f = "DashSession.kt", i = {0}, l = {StatusLine.HTTP_TEMP_REDIRECT}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes11.dex */
final class DashSession$launchReceiveLoop$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DashSocket $sock;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$launchReceiveLoop$1(DashSocket dashSocket, DashSession dashSession, Continuation<? super DashSession$launchReceiveLoop$1> continuation) {
        super(2, continuation);
        this.$sock = dashSocket;
        this.this$0 = dashSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashSession$launchReceiveLoop$1 dashSession$launchReceiveLoop$1 = new DashSession$launchReceiveLoop$1(this.$sock, this.this$0, continuation);
        dashSession$launchReceiveLoop$1.L$0 = obj;
        return dashSession$launchReceiveLoop$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashSession$launchReceiveLoop$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0032, code lost:
    
        r9 = r0;
        r0 = r1;
        r1 = r2;
        r2 = r3;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0039 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00b9  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Exception e;
        DashSession$launchReceiveLoop$1 dashSession$launchReceiveLoop$1;
        CoroutineScope $this$launch;
        Object obj;
        DashSession$launchReceiveLoop$1 dashSession$launchReceiveLoop$12;
        Object $result2;
        Function1<String, Unit> onError;
        byte[] pkt;
        Object $result3;
        CoroutineScope $this$launch2;
        Object obj2;
        CoroutineScope $this$launch3 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                obj2 = coroutine_suspended;
                $this$launch2 = $this$launch3;
                $result3 = $result;
                dashSession$launchReceiveLoop$1 = this;
                if (CoroutineScopeKt.isActive($this$launch2)) {
                    try {
                        dashSession$launchReceiveLoop$1.L$0 = $this$launch2;
                        dashSession$launchReceiveLoop$1.label = 1;
                        Object receive = dashSession$launchReceiveLoop$1.$sock.receive(dashSession$launchReceiveLoop$1);
                        if (receive == obj2) {
                            return obj2;
                        }
                        Object obj3 = $result3;
                        dashSession$launchReceiveLoop$12 = dashSession$launchReceiveLoop$1;
                        $result = receive;
                        obj = obj2;
                        $this$launch = $this$launch2;
                        $result2 = obj3;
                        try {
                            pkt = (byte[]) $result;
                            if (pkt != null) {
                                dashSession$launchReceiveLoop$12.this$0.lastRxMs = System.currentTimeMillis();
                                dashSession$launchReceiveLoop$12.this$0.dispatchIncoming(pkt, dashSession$launchReceiveLoop$12.$sock);
                            }
                            dashSession$launchReceiveLoop$1 = dashSession$launchReceiveLoop$12;
                            $result3 = $result2;
                            $this$launch2 = $this$launch;
                            obj2 = obj;
                            if (CoroutineScopeKt.isActive($this$launch2)) {
                            }
                        } catch (CancellationException e2) {
                            throw e2;
                        } catch (Exception e3) {
                            e = e3;
                            dashSession$launchReceiveLoop$1 = dashSession$launchReceiveLoop$12;
                            Log.w("DashSession", "RX loop stopped — socket error: " + e.getMessage());
                            RideDiagnostics.INSTANCE.log("error", "RX loop stopped — socket error: " + e.getMessage());
                            onError = dashSession$launchReceiveLoop$1.this$0.getOnError();
                            if (onError != null) {
                            }
                            return Unit.INSTANCE;
                        }
                    } catch (CancellationException e4) {
                        throw e4;
                    } catch (Exception e5) {
                        e = e5;
                        Log.w("DashSession", "RX loop stopped — socket error: " + e.getMessage());
                        RideDiagnostics.INSTANCE.log("error", "RX loop stopped — socket error: " + e.getMessage());
                        onError = dashSession$launchReceiveLoop$1.this$0.getOnError();
                        if (onError != null) {
                            onError.invoke("Lost connection to dash");
                        }
                        return Unit.INSTANCE;
                    }
                }
                return Unit.INSTANCE;
            case 1:
                try {
                    ResultKt.throwOnFailure($result);
                    $this$launch = $this$launch3;
                    obj = coroutine_suspended;
                    dashSession$launchReceiveLoop$12 = this;
                    $result2 = $result;
                    pkt = (byte[]) $result;
                    if (pkt != null) {
                    }
                    dashSession$launchReceiveLoop$1 = dashSession$launchReceiveLoop$12;
                    $result3 = $result2;
                    $this$launch2 = $this$launch;
                    obj2 = obj;
                    if (CoroutineScopeKt.isActive($this$launch2)) {
                    }
                } catch (CancellationException e6) {
                    throw e6;
                } catch (Exception e7) {
                    e = e7;
                    dashSession$launchReceiveLoop$1 = this;
                    Log.w("DashSession", "RX loop stopped — socket error: " + e.getMessage());
                    RideDiagnostics.INSTANCE.log("error", "RX loop stopped — socket error: " + e.getMessage());
                    onError = dashSession$launchReceiveLoop$1.this$0.getOnError();
                    if (onError != null) {
                    }
                    return Unit.INSTANCE;
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
