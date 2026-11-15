package com.example.wayfindr.dash.video;

import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NalProcessor.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\u0018\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0004J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0002J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0004H\u0002J\f\u0010\u0016\u001a\u00020\u000b*\u00020\u0004H\u0002J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u00182\u0006\u0010\u0019\u001a\u00020\u0004H\u0002R \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/example/wayfindr/dash/video/NalProcessor;", "", "onNal", "Lkotlin/Function2;", "", "", "", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "START_CODE_4", "TAG", "", "sps", "pps", "loggedParams", "idrCount", "", "process", "annexB", "normalizeSpsForDash", "emitIdr", "idr", "hex", "split", "", "data", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class NalProcessor {
    public static final int $stable = 8;
    private final byte[] START_CODE_4;
    private final String TAG;
    private int idrCount;
    private boolean loggedParams;
    private final Function2<byte[], Boolean, Unit> onNal;
    private byte[] pps;
    private byte[] sps;

    /* JADX WARN: Multi-variable type inference failed */
    public NalProcessor(Function2<? super byte[], ? super Boolean, Unit> onNal) {
        Intrinsics.checkNotNullParameter(onNal, "onNal");
        this.onNal = onNal;
        this.START_CODE_4 = new byte[]{0, 0, 0, 1};
        this.TAG = "NalProcessor";
    }

    public final void process(byte[] annexB) {
        Intrinsics.checkNotNullParameter(annexB, "annexB");
        for (byte[] nal : split(annexB)) {
            if (!(nal.length == 0)) {
                int type = nal[0] & 31;
                switch (type) {
                    case 5:
                        emitIdr(nal);
                        break;
                    case 6:
                    case 9:
                        break;
                    case 7:
                        this.sps = normalizeSpsForDash(nal);
                        break;
                    case 8:
                        this.pps = nal;
                        break;
                    default:
                        if (!(1 <= type && type < 5)) {
                            if (!(10 <= type && type < 13)) {
                                break;
                            }
                        }
                        this.onNal.invoke(nal, false);
                        break;
                }
            }
        }
    }

    private final byte[] normalizeSpsForDash(byte[] sps) {
        if (sps.length >= 4 && (sps[0] & 31) == 7 && sps[1] == 66 && sps[3] == 41) {
            byte[] out = Arrays.copyOf(sps, sps.length);
            Intrinsics.checkNotNullExpressionValue(out, "copyOf(...)");
            out[2] = 0;
            return out;
        }
        return sps;
    }

    private final void emitIdr(byte[] idr) {
        byte[] nal;
        byte[] s = this.sps;
        byte[] p = this.pps;
        if (!this.loggedParams && s != null && p != null) {
            this.loggedParams = true;
            Log.i(this.TAG, "SPS=" + hex(s) + " PPS=" + hex(p));
        }
        if (s == null || p == null) {
            Log.w(this.TAG, "IDR with no SPS/PPS cached — dash will not decode");
        }
        if (s != null && p != null) {
            nal = ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(s, this.START_CODE_4), p), this.START_CODE_4), idr);
        } else {
            nal = idr;
        }
        this.idrCount++;
        if (this.idrCount <= 3) {
            Log.d(this.TAG, "emit IDR #" + this.idrCount + " (" + nal.length + "B bundled)");
        }
        this.onNal.invoke(nal, true);
    }

    private final String hex(byte[] $this$hex) {
        return ArraysKt.joinToString$default($this$hex, (CharSequence) " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.example.wayfindr.dash.video.NalProcessor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return NalProcessor.hex$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence hex$lambda$0(byte it) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final List<byte[]> split(byte[] data) {
        boolean sc3;
        List nals = new ArrayList();
        int start = -1;
        int i = 0;
        while (true) {
            sc3 = false;
            if (i >= data.length) {
                break;
            }
            boolean sc4 = i + 3 < data.length && data[i] == 0 && data[i + 1] == 0 && data[i + 2] == 0 && data[i + 3] == 1;
            if (!sc4 && i + 2 < data.length && data[i] == 0 && data[i + 1] == 0 && data[i + 2] == 1) {
                sc3 = true;
            }
            if (sc4) {
                if (start >= 0) {
                    nals.add(ArraysKt.copyOfRange(data, start, i));
                }
                start = i + 4;
                i += 4;
            } else if (sc3) {
                if (start >= 0) {
                    nals.add(ArraysKt.copyOfRange(data, start, i));
                }
                start = i + 3;
                i += 3;
            } else {
                i++;
            }
        }
        if (start >= 0 && start < data.length) {
            sc3 = true;
        }
        if (sc3) {
            nals.add(ArraysKt.copyOfRange(data, start, data.length));
        }
        return nals;
    }
}
