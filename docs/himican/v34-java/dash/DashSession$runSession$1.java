package com.example.wayfindr.dash;

import androidx.core.view.InputDeviceCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: DashSession.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSession", f = "DashSession.kt", i = {0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {InputDeviceCompat.SOURCE_KEYBOARD, 263, 272}, m = "runSession", n = {"ssid", "network", "sock", "pkt", "ssid", "network", "sock", "deadline", "ssid", "network", "sock", "deadline"}, s = {"L$0", "L$1", "L$2", "L$4", "L$0", "L$1", "L$2", "J$0", "L$0", "L$1", "L$2", "J$0"})
/* loaded from: classes11.dex */
final class DashSession$runSession$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DashSession this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSession$runSession$1(DashSession dashSession, Continuation<? super DashSession$runSession$1> continuation) {
        super(continuation);
        this.this$0 = dashSession;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object runSession;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        runSession = this.this$0.runSession(null, null, this);
        return runSession;
    }
}
