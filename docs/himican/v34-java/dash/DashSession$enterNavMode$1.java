package com.example.wayfindr.dash;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DashSession.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession", f = "DashSession.kt", i = {0, 1, 2, 2, 2, 3, 4, 5}, l = {289, 290, 294, 296, 297, 298}, m = "enterNavMode", n = {"sock", "sock", "sock", "it\\1", "$i$a$-repeat-DashSession$enterNavMode$2\\1\\292\\0", "sock", "sock", "sock"}, s = {"L$0", "L$0", "L$0", "I$2", "I$3", "L$0", "L$0", "L$0"})
/* loaded from: classes11.dex */
final class DashSession$enterNavMode$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$enterNavMode$1(DashSession dashSession, Continuation<? super DashSession$enterNavMode$1> continuation) {
        super(continuation);
        this.this$0 = dashSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object enterNavMode;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        enterNavMode = this.this$0.enterNavMode(null, this);
        return enterNavMode;
    }
}
