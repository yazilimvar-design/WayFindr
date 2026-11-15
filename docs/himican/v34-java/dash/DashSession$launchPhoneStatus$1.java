package com.example.wayfindr.dash;

import androidx.autofill.HintConstants;
import androidx.lifecycle.CoroutineLiveDataKt;
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
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;

/* compiled from: DashSession.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession$launchPhoneStatus$1", f = "DashSession.kt", i = {0, 0, 0}, l = {459}, m = "invokeSuspend", n = {"$this$launch", "logged", "p"}, s = {"L$0", "L$1", "L$2"})
/* loaded from: classes11.dex */
final class DashSession$launchPhoneStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$launchPhoneStatus$1(DashSession dashSession, Continuation<? super DashSession$launchPhoneStatus$1> continuation) {
        super(2, continuation);
        this.this$0 = dashSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashSession$launchPhoneStatus$1 dashSession$launchPhoneStatus$1 = new DashSession$launchPhoneStatus$1(this.this$0, continuation);
        dashSession$launchPhoneStatus$1.L$0 = obj;
        return dashSession$launchPhoneStatus$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashSession$launchPhoneStatus$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00f2  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x00ed -> B:7:0x00ef). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Object obj;
        Ref.ObjectRef logged;
        CoroutineScope $this$launch;
        DashSession$launchPhoneStatus$1 dashSession$launchPhoneStatus$1;
        CoroutineScope $this$launch2;
        Object m8392constructorimpl;
        CoroutineScope $this$launch3 = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                logged = new Ref.ObjectRef();
                $this$launch = $this$launch3;
                dashSession$launchPhoneStatus$1 = this;
                if (CoroutineScopeKt.isActive($this$launch)) {
                    Function0 p = dashSession$launchPhoneStatus$1.this$0.getPhoneStatusProvider();
                    if (p != null) {
                        long t0 = System.currentTimeMillis();
                        try {
                        } catch (Throwable th) {
                            Result.Companion companion = Result.INSTANCE;
                            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                        }
                        Result.Companion companion2 = Result.INSTANCE;
                        m8392constructorimpl = Result.m8392constructorimpl(p.invoke());
                        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                            m8392constructorimpl = null;
                        }
                        PhoneStatus phoneStatus = (PhoneStatus) m8392constructorimpl;
                        if (phoneStatus != null) {
                            dashSession$launchPhoneStatus$1.this$0.setLatestPhoneStatus(phoneStatus);
                        }
                        long took = System.currentTimeMillis() - t0;
                        if (took > 200) {
                            RideDiagnostics.INSTANCE.log(HintConstants.AUTOFILL_HINT_PHONE, "status read slow: " + took + " ms");
                        }
                        PhoneStatus latestPhoneStatus = dashSession$launchPhoneStatus$1.this$0.getLatestPhoneStatus();
                        if (latestPhoneStatus != null) {
                            T t = Intrinsics.areEqual(latestPhoneStatus, logged.element) ? 0 : latestPhoneStatus;
                            if (t != 0) {
                                logged.element = t;
                                RideDiagnostics.INSTANCE.log(HintConstants.AUTOFILL_HINT_PHONE, "status → dash: " + t);
                            }
                        }
                    }
                    dashSession$launchPhoneStatus$1.L$0 = $this$launch;
                    dashSession$launchPhoneStatus$1.L$1 = logged;
                    dashSession$launchPhoneStatus$1.L$2 = SpillingKt.nullOutSpilledVariable(p);
                    dashSession$launchPhoneStatus$1.label = 1;
                    if (DelayKt.delay(CoroutineLiveDataKt.DEFAULT_TIMEOUT, dashSession$launchPhoneStatus$1) == obj) {
                        return obj;
                    }
                    $this$launch2 = $this$launch;
                    $this$launch = $this$launch2;
                    if (CoroutineScopeKt.isActive($this$launch)) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            case 1:
                Ref.ObjectRef logged2 = (Ref.ObjectRef) this.L$1;
                ResultKt.throwOnFailure($result);
                dashSession$launchPhoneStatus$1 = this;
                $this$launch2 = $this$launch3;
                logged = logged2;
                obj = coroutine_suspended;
                $this$launch = $this$launch2;
                if (CoroutineScopeKt.isActive($this$launch)) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
