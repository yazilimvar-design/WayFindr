package com.example.wayfindr.viewmodel;

import com.example.wayfindr.dash.DashState;
import com.example.wayfindr.viewmodel.DashViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DashViewModel.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$8$1", f = "DashViewModel.kt", i = {0, 1}, l = {554, 566}, m = "emit", n = {"state", "state"}, s = {"L$0", "L$0"})
/* loaded from: classes4.dex */
final class DashViewModel$8$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DashViewModel.AnonymousClass8.AnonymousClass1<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    DashViewModel$8$1$emit$1(DashViewModel.AnonymousClass8.AnonymousClass1<? super T> anonymousClass1, Continuation<? super DashViewModel$8$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((DashState) null, (Continuation<? super Unit>) this);
    }
}
