package com.example.wayfindr.dash.protocol;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;

/* compiled from: K1GPacket.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"hexToBytes", "", "", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class K1GPacketKt {
    public static final byte[] hexToBytes(String $this$hexToBytes) {
        Intrinsics.checkNotNullParameter($this$hexToBytes, "<this>");
        String clean = StringsKt.replace$default($this$hexToBytes, " ", "", false, 4, (Object) null);
        int length = clean.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            String substring = clean.substring(i2, i2 + 2);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            bArr[i] = (byte) Integer.parseInt(substring, CharsKt.checkRadix(16));
        }
        return bArr;
    }
}
