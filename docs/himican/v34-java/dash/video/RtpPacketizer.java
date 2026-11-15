package com.example.wayfindr.dash.video;

import java.util.Random;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RtpPacketizer.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000eJ \u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000eH\u0002J \u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u000eH\u0002R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/example/wayfindr/dash/video/RtpPacketizer;", "", "onPacket", "Lkotlin/Function1;", "", "", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "rng", "Ljava/util/Random;", "seq", "", "ssrc", "tsBase", "", "packetize", "nal", "endOfAU", "", "wallClockMs", "fuA", "ts", "emit", "payload", "marker", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class RtpPacketizer {
    private static final int MAX_PAYLOAD = 1380;
    private static final int PT = 96;
    private final Function1<byte[], Unit> onPacket;
    private final Random rng;
    private int seq;
    private final int ssrc;
    private final long tsBase;
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public RtpPacketizer(Function1<? super byte[], Unit> onPacket) {
        Intrinsics.checkNotNullParameter(onPacket, "onPacket");
        this.onPacket = onPacket;
        this.rng = new Random();
        this.seq = this.rng.nextInt(65535);
        this.ssrc = this.rng.nextInt();
        this.tsBase = this.rng.nextInt() & 4294967295L;
    }

    public final void packetize(byte[] nal, boolean endOfAU, long wallClockMs) {
        Intrinsics.checkNotNullParameter(nal, "nal");
        long ts = (this.tsBase + (90 * wallClockMs)) & 4294967295L;
        if (nal.length <= MAX_PAYLOAD) {
            emit(nal, endOfAU, ts);
        } else {
            fuA(nal, endOfAU, ts);
        }
    }

    private final void fuA(byte[] nal, boolean endOfAU, long ts) {
        int nalType = nal[0] & 31;
        byte fuInd = (byte) ((nal[0] & 224) | 28);
        int offset = 1;
        boolean isFirst = true;
        while (offset < nal.length) {
            int remaining = nal.length - offset;
            int chunkLen = Math.min(1378, remaining);
            boolean z = true;
            boolean isLast = chunkLen >= remaining;
            byte fuHeader = (byte) ((isFirst ? 128 : 0) | (isLast ? 64 : 0) | nalType);
            byte[] payload = new byte[chunkLen + 2];
            payload[0] = fuInd;
            payload[1] = fuHeader;
            ArraysKt.copyInto(nal, payload, 2, offset, offset + chunkLen);
            if (!isLast || !endOfAU) {
                z = false;
            }
            emit(payload, z, ts);
            offset += chunkLen;
            isFirst = false;
        }
    }

    private final void emit(byte[] payload, boolean marker, long ts) {
        byte[] pkt = new byte[payload.length + 12];
        pkt[0] = ByteCompanionObject.MIN_VALUE;
        pkt[1] = (byte) ((marker ? 128 : 0) | PT);
        pkt[2] = (byte) ((this.seq >> 8) & 255);
        pkt[3] = (byte) (this.seq & 255);
        pkt[4] = (byte) ((ts >> 24) & 255);
        pkt[5] = (byte) ((ts >> 16) & 255);
        pkt[6] = (byte) ((ts >> 8) & 255);
        pkt[7] = (byte) (ts & 255);
        long s = 4294967295L & this.ssrc;
        pkt[8] = (byte) ((s >> 24) & 255);
        pkt[9] = (byte) ((s >> 16) & 255);
        pkt[10] = (byte) ((s >> 8) & 255);
        pkt[11] = (byte) (s & 255);
        ArraysKt.copyInto$default(payload, pkt, 12, 0, 0, 12, (Object) null);
        this.seq = (this.seq + 1) & 65535;
        this.onPacket.invoke(pkt);
    }
}
