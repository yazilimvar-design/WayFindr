package com.example.wayfindr.dash.nav;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: OfflineRouter.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.OfflineRouter", f = "OfflineRouter.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {52, 59}, m = "route", n = {"from", "to", "intermediates", "ctx", "points", "text", "from", "to", "intermediates", "ctx", "points", "text"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes9.dex */
final class OfflineRouter$route$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OfflineRouter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OfflineRouter$route$1(OfflineRouter offlineRouter, Continuation<? super OfflineRouter$route$1> continuation) {
        super(continuation);
        this.this$0 = offlineRouter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.route(null, null, null, this);
    }
}
