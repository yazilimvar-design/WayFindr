package com.example.wayfindr.dash;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DashSocket.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0002¨\u0006\u0005"}, d2 = {"hex", "", "", "max", "", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashSocketKt {
    static /* synthetic */ String hex$default(byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 64;
        }
        return hex(bArr, i);
    }

    private static final String hex(byte[] $this$hex, int max) {
        String joinToString$default = CollectionsKt.joinToString$default(ArraysKt.take($this$hex, max), " ", null, null, 0, null, new Function1() { // from class: com.example.wayfindr.dash.DashSocketKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashSocketKt.hex$lambda$0(((Byte) obj).byteValue());
            }
        }, 30, null);
        if ($this$hex.length <= max) {
            return joinToString$default;
        }
        return joinToString$default + " …(+" + ($this$hex.length - max) + ")";
    }

    static final CharSequence hex$lambda$0(byte it) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
