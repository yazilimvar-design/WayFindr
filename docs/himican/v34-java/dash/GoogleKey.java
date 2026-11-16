package com.example.wayfindr.dash;

import com.example.wayfindr.dash.nav.GPlus;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: GoogleKey.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class GoogleKey {
    public static final int $stable = 0;
    public static final GoogleKey INSTANCE = new GoogleKey();
    public static final String PLACEHOLDER = "add gmaps api key here";

    private GoogleKey() {
    }

    public final boolean getPresent() {
        return isReal(GPlus.key());
    }

    public final boolean isReal(String str) {
        Intrinsics.checkNotNullParameter(str, "key");
        return (StringsKt.isBlank(str) || Intrinsics.areEqual(str, PLACEHOLDER)) ? false : true;
    }
}
