package com.example.wayfindr.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeviceId.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/example/wayfindr/util/DeviceId;", "", "<init>", "()V", "PREFS", "", "KEY", "get", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DeviceId {
    public static final int $stable = 0;
    public static final DeviceId INSTANCE = new DeviceId();
    private static final String KEY = "device_id";
    private static final String PREFS = "wayfindr_device";

    private DeviceId() {
    }

    public final String get(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(PREFS, 0);
        String string = prefs.getString(KEY, null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        String id = "dev-" + StringsKt.take(uuid, 12);
        prefs.edit().putString(KEY, id).apply();
        return id;
    }
}
