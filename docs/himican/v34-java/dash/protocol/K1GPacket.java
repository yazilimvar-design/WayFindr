package com.example.wayfindr.dash.protocol;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: K1GPacket.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0007\u001a\u00020\u00052\u0012\u0010\b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0\t\"\u00020\n¢\u0006\u0002\u0010\u000bJ\u0016\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fJ\"\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\n\u0010\u0013\u001a\u00020\u0014\"\u00020\u000fJ\u001e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0005J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\n0\u00172\u0006\u0010\u0018\u001a\u00020\u0005J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/example/wayfindr/dash/protocol/K1GPacket;", "", "<init>", "()V", "MAGIC", "", "FIXED", "build", "tlvs", "", "Lcom/example/wayfindr/dash/protocol/Tlv;", "([Lcom/example/wayfindr/dash/protocol/Tlv;)[B", "patchSeq", "pkt", "seq", "", "tlv", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "sub", "values", "", "value", "parseIncoming", "", "data", "indexOfMagic", "b", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class K1GPacket {
    public static final K1GPacket INSTANCE = new K1GPacket();
    private static final byte[] MAGIC = {75, 49, 71, 32};
    private static final byte[] FIXED = {0, 0, 0, 0, 2, 1, 0, 5, 75, 49, 71, 32};
    public static final int $stable = 8;

    private K1GPacket() {
    }

    public final byte[] build(Tlv... tlvs) {
        Intrinsics.checkNotNullParameter(tlvs, "tlvs");
        int segCount = tlvs.length + 1;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(0);
        out.write(0);
        out.write((segCount >> 8) & 255);
        out.write(segCount & 255);
        out.write(FIXED);
        out.write(0);
        for (Tlv tlv : tlvs) {
            out.write(tlv.getType() & 255);
            out.write(tlv.getSub() & 255);
            out.write((tlv.getValue().length >> 8) & 255);
            out.write(tlv.getValue().length & 255);
            out.write(tlv.getValue());
        }
        byte[] bytes = out.toByteArray();
        bytes[0] = (byte) ((bytes.length >> 8) & 255);
        bytes[1] = (byte) (bytes.length & 255);
        Intrinsics.checkNotNull(bytes);
        return bytes;
    }

    public final byte[] patchSeq(byte[] pkt, int seq) {
        Intrinsics.checkNotNullParameter(pkt, "pkt");
        byte[] out = Arrays.copyOf(pkt, pkt.length);
        Intrinsics.checkNotNullExpressionValue(out, "copyOf(...)");
        int k = indexOfMagic(out);
        if (k >= 0 && k + 4 < out.length) {
            out[k + 4] = (byte) (seq & 255);
        }
        out[0] = (byte) ((out.length >> 8) & 255);
        out[1] = (byte) (out.length & 255);
        return out;
    }

    public final Tlv tlv(int type, int sub, int... values) {
        Intrinsics.checkNotNullParameter(values, "values");
        int length = values.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) values[i];
        }
        return new Tlv(type, sub, bArr);
    }

    public final Tlv tlv(int type, int sub, byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Tlv(type, sub, value);
    }

    public final List<Tlv> parseIncoming(byte[] data) {
        Intrinsics.checkNotNullParameter(data, "data");
        List tlvs = new ArrayList();
        if (data.length < 8) {
            return tlvs;
        }
        int segCount = ((data[2] & UByte.MAX_VALUE) << 8) | (data[3] & UByte.MAX_VALUE);
        int i = 8;
        for (int n = 0; n < segCount && i + 4 <= data.length; n++) {
            int type = data[i] & UByte.MAX_VALUE;
            int sub = data[i + 1] & UByte.MAX_VALUE;
            int len = ((data[i + 2] & UByte.MAX_VALUE) << 8) | (data[i + 3] & UByte.MAX_VALUE);
            int i2 = i + 4;
            int end = RangesKt.coerceAtMost(i2 + len, data.length);
            tlvs.add(new Tlv(type, sub, ArraysKt.copyOfRange(data, i2, end)));
            i = end;
        }
        return tlvs;
    }

    private final int indexOfMagic(byte[] b) {
        int i = 0;
        int length = b.length - 4;
        if (0 > length) {
            return -1;
        }
        while (true) {
            for (int j = 0; j < 4; j++) {
                if (b[i + j] != MAGIC[j]) {
                    if (i == length) {
                        return -1;
                    }
                    i++;
                }
            }
            return i;
        }
    }
}
