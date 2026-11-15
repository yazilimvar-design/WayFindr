package com.example.wayfindr.dash.nav;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Router.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.Router", f = "Router.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4}, l = {64, 65, 67, 69, 73}, m = "route", n = {"context", "from", "to", "intermediates", "context", "from", "to", "intermediates", "context", "from", "to", "intermediates", "context", "from", "to", "intermediates", "context", "from", "to", "intermediates", "$this$route_u24lambda_u240\\1", "$i$a$-run-Router$route$2\\1\\70\\0"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0"})
/* loaded from: classes9.dex */
final class Router$route$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ Router this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    Router$route$1(Router router, Continuation<? super Router$route$1> continuation) {
        super(continuation);
        this.this$0 = router;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.route(null, null, null, null, this);
    }
}
