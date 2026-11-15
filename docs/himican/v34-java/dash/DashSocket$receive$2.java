package com.example.wayfindr.dash;

import android.util.Log;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.util.Dbg;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DashSocket.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.DashSocket$receive$2", f = "DashSocket.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class DashSocket$receive$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super byte[]>, Object> {
    int label;
    final /* synthetic */ DashSocket this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashSocket$receive$2(DashSocket dashSocket, Continuation<? super DashSocket$receive$2> continuation) {
        super(2, continuation);
        this.this$0 = dashSocket;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashSocket$receive$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super byte[]> continuation) {
        return ((DashSocket$receive$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        DatagramSocket datagramSocket;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                DatagramPacket buf = new DatagramPacket(new byte[65535], 65535);
                try {
                    datagramSocket = this.this$0.rxSocket;
                    datagramSocket.receive(buf);
                    byte[] data = buf.getData();
                    Intrinsics.checkNotNullExpressionValue(data, "getData(...)");
                    byte[] bytes = Arrays.copyOf(data, buf.getLength());
                    Intrinsics.checkNotNullExpressionValue(bytes, "copyOf(...)");
                    Dbg dbg = Dbg.INSTANCE;
                    if (BuildConfig.DEBUG) {
                        InetAddress address = buf.getAddress();
                        String hostAddress = address != null ? address.getHostAddress() : null;
                        Log.d("DashSocket", "RX ←" + hostAddress + ":" + buf.getPort() + "  " + bytes.length + "B  " + DashSocketKt.hex$default(bytes, 0, 1, null));
                    }
                    return bytes;
                } catch (SocketTimeoutException e) {
                    return null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
