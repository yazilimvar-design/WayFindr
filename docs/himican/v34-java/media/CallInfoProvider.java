package com.example.wayfindr.media;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: CallInfoProvider.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0006R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/example/wayfindr/media/CallInfoProvider;", "", "<init>", "()V", "_incomingCall", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/media/IncomingCall;", "incomingCall", "Lkotlinx/coroutines/flow/StateFlow;", "getIncomingCall", "()Lkotlinx/coroutines/flow/StateFlow;", "update", "", NotificationCompat.CATEGORY_CALL, "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CallInfoProvider {
    public static final CallInfoProvider INSTANCE = new CallInfoProvider();
    private static final MutableStateFlow<IncomingCall> _incomingCall = StateFlowKt.MutableStateFlow(null);
    private static final StateFlow<IncomingCall> incomingCall = FlowKt.asStateFlow(_incomingCall);
    public static final int $stable = 8;

    private CallInfoProvider() {
    }

    public final StateFlow<IncomingCall> getIncomingCall() {
        return incomingCall;
    }

    public final void update(IncomingCall call) {
        _incomingCall.setValue(call);
    }
}
