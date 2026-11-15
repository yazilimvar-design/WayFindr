package com.example.wayfindr.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$2$clock$1", f = "DashViewModel.kt", i = {0, 1}, l = {419, 419}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
final class DashViewModel$2$clock$1 extends SuspendLambda implements Function2<FlowCollector<? super Unit>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    DashViewModel$2$clock$1(Continuation<? super DashViewModel$2$clock$1> continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashViewModel$2$clock$1 dashViewModel$2$clock$1 = new DashViewModel$2$clock$1(continuation);
        dashViewModel$2$clock$1.L$0 = obj;
        return dashViewModel$2$clock$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(FlowCollector<? super Unit> flowCollector, Continuation<? super Unit> continuation) {
        return ((DashViewModel$2$clock$1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0055 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x003c A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0053 -> B:7:0x002d). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = r8.L$0
            kotlinx.coroutines.flow.FlowCollector r0 = (kotlinx.coroutines.flow.FlowCollector) r0
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r8.label
            r3 = 1
            switch(r2) {
                case 0: goto L26;
                case 1: goto L1e;
                case 2: goto L16;
                default: goto Le;
            }
        Le:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L16:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r1
            r1 = r0
            r0 = r9
            r9 = r8
            goto L56
        L1e:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r1
            r1 = r0
            r0 = r9
            r9 = r8
            goto L3d
        L26:
            kotlin.ResultKt.throwOnFailure(r9)
            r2 = r1
            r1 = r0
            r0 = r9
            r9 = r8
        L2d:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE
            r5 = r9
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r9.L$0 = r1
            r9.label = r3
            java.lang.Object r4 = r1.emit(r4, r5)
            if (r4 != r2) goto L3d
            return r2
        L3d:
            com.example.wayfindr.dash.DarkSchedule r4 = com.example.wayfindr.dash.DarkSchedule.INSTANCE
            r5 = 0
            long r4 = com.example.wayfindr.dash.DarkSchedule.msUntilNextChange$default(r4, r5, r3, r5)
            r6 = 1000(0x3e8, double:4.94E-321)
            long r4 = r4 + r6
            r6 = r9
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r9.L$0 = r1
            r7 = 2
            r9.label = r7
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r4, r6)
            if (r4 != r2) goto L56
            return r2
        L56:
            goto L2d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.wayfindr.viewmodel.DashViewModel$2$clock$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
