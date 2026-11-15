package com.example.wayfindr.dash;

import android.net.Network;
import android.util.Log;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.protocol.K1GPacket;
import com.example.wayfindr.util.Dbg;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: DashSocket.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\b\u0007\u0018\u0000 \u00182\u00060\u0001j\u0002`\u0002:\u0001\u0018B\u0013\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0013H\u0086@¢\u0006\u0002\u0010\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/example/wayfindr/dash/DashSocket;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "network", "Landroid/net/Network;", "<init>", "(Landroid/net/Network;)V", "broadcastAddr", "Ljava/net/InetAddress;", "dashAddr", "txSocket", "Ljava/net/DatagramSocket;", "rxSocket", "rtpSocket", "seq", "Ljava/util/concurrent/atomic/AtomicInteger;", "send", "", "data", "", "sendRtp", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashSocket implements AutoCloseable {
    public static final String BROADCAST = "192.168.1.255";
    private static final int BUF = 65535;
    public static final int CTRL_PORT = 2000;
    public static final String DASH_IP = "192.168.1.1";
    private static final int RECV_TIMEOUT_MS = 500;
    public static final int RTP_PORT = 5000;
    public static final int RX_PORT = 2002;
    private static final String TAG = "DashSocket";
    private final InetAddress broadcastAddr;
    private final InetAddress dashAddr;
    private final Network network;
    private final DatagramSocket rtpSocket;
    private final DatagramSocket rxSocket;
    private final AtomicInteger seq;
    private final DatagramSocket txSocket;
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public DashSocket() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public DashSocket(Network network) {
        this.network = network;
        InetAddress byName = InetAddress.getByName(BROADCAST);
        Intrinsics.checkNotNullExpressionValue(byName, "getByName(...)");
        this.broadcastAddr = byName;
        InetAddress byName2 = InetAddress.getByName(DASH_IP);
        Intrinsics.checkNotNullExpressionValue(byName2, "getByName(...)");
        this.dashAddr = byName2;
        this.seq = new AtomicInteger(0);
        DatagramSocket tx = null;
        DatagramSocket rx = null;
        DatagramSocket rtp = null;
        try {
            DatagramSocket datagramSocket = new DatagramSocket((SocketAddress) null);
            datagramSocket.setReuseAddress(true);
            datagramSocket.setBroadcast(true);
            datagramSocket.bind(new InetSocketAddress(CTRL_PORT));
            Network network2 = this.network;
            if (network2 != null) {
                network2.bindSocket(datagramSocket);
            }
            tx = datagramSocket;
            DatagramSocket datagramSocket2 = new DatagramSocket((SocketAddress) null);
            datagramSocket2.setReuseAddress(true);
            datagramSocket2.setSoTimeout(RECV_TIMEOUT_MS);
            datagramSocket2.bind(new InetSocketAddress(RX_PORT));
            Network network3 = this.network;
            if (network3 != null) {
                network3.bindSocket(datagramSocket2);
            }
            rx = datagramSocket2;
            DatagramSocket datagramSocket3 = new DatagramSocket();
            Network network4 = this.network;
            if (network4 != null) {
                network4.bindSocket(datagramSocket3);
            }
            rtp = datagramSocket3;
            Log.i(TAG, "Sockets open — TX :2000→192.168.1.255:2000 (broadcast), RX :2002, RTP→192.168.1.1:5000");
            this.txSocket = tx;
            this.rxSocket = rx;
            this.rtpSocket = rtp;
        } catch (Exception e) {
            if (tx != null) {
                tx.close();
            }
            if (rx != null) {
                rx.close();
            }
            if (rtp != null) {
                rtp.close();
            }
            throw e;
        }
    }

    public /* synthetic */ DashSocket(Network network, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : network);
    }

    public final void send(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] pkt = K1GPacket.INSTANCE.patchSeq(data, this.seq.getAndIncrement());
        Dbg dbg = Dbg.INSTANCE;
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "TX →192.168.1.255:2000  " + pkt.length + "B  " + DashSocketKt.hex$default(pkt, 0, 1, null));
        }
        try {
            this.txSocket.send(new DatagramPacket(pkt, pkt.length, this.broadcastAddr, CTRL_PORT));
        } catch (Exception e) {
            Log.w(TAG, "TX send failed (link down?): " + e.getMessage());
        }
    }

    public final void sendRtp(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            this.rtpSocket.send(new DatagramPacket(data, data.length, this.dashAddr, RTP_PORT));
        } catch (Exception e) {
            Log.d(TAG, "RTP send failed (link down?): " + e.getMessage());
        }
    }

    public final Object receive(Continuation<? super byte[]> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new DashSocket$receive$2(this, null), continuation);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Log.d(TAG, "Sockets closed");
        try {
            Result.Companion companion = Result.INSTANCE;
            this.txSocket.close();
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            this.rxSocket.close();
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th2));
        }
        try {
            Result.Companion companion5 = Result.INSTANCE;
            this.rtpSocket.close();
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th3) {
            Result.Companion companion6 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th3));
        }
    }
}
