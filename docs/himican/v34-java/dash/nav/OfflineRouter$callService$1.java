package com.example.wayfindr.dash.nav;

import androidx.appcompat.app.AppCompatDelegate;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: OfflineRouter.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.OfflineRouter", f = "OfflineRouter.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY}, m = "callService", n = {"ctx", "points", "overrides", "connected", "conn", "intent", "bound"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "Z$0"})
/* loaded from: classes9.dex */
final class OfflineRouter$callService$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ OfflineRouter this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OfflineRouter$callService$1(OfflineRouter offlineRouter, Continuation<? super OfflineRouter$callService$1> continuation) {
        super(continuation);
        this.this$0 = offlineRouter;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object callService;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        callService = this.this$0.callService(null, null, null, this);
        return callService;
    }
}
