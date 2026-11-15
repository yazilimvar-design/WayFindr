package com.example.wayfindr.viewmodel;

import androidx.lifecycle.ViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: AppViewModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/example/wayfindr/viewmodel/AppViewModel;", "Landroidx/lifecycle/ViewModel;", "<init>", "()V", "_conn", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/viewmodel/ConnectionState;", "conn", "Lkotlinx/coroutines/flow/StateFlow;", "getConn", "()Lkotlinx/coroutines/flow/StateFlow;", "setConn", "", "state", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AppViewModel extends ViewModel {
    public static final int $stable = 8;
    private final MutableStateFlow<ConnectionState> _conn = StateFlowKt.MutableStateFlow(ConnectionState.Connected);
    private final StateFlow<ConnectionState> conn = FlowKt.asStateFlow(this._conn);

    public final StateFlow<ConnectionState> getConn() {
        return this.conn;
    }

    public final void setConn(ConnectionState state) {
        Intrinsics.checkNotNullParameter(state, "state");
        this._conn.setValue(state);
    }
}
