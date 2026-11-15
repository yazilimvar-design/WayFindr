package com.example.wayfindr.dash;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: GoogleKey.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/example/wayfindr/dash/GoogleKey;", "", "<init>", "()V", "PLACEHOLDER", "", "isReal", "", "key", "present", "getPresent", "()Z", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class GoogleKey {
    public static final int $stable = 0;
    public static final GoogleKey INSTANCE = new GoogleKey();
    public static final String PLACEHOLDER = "add gmaps api key here";

    private GoogleKey() {
    }

    public final boolean isReal(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return (StringsKt.isBlank(key) || Intrinsics.areEqual(key, "add gmaps api key here")) ? false : true;
    }

    public final boolean getPresent() {
        return isReal("add gmaps api key here");
    }
}
