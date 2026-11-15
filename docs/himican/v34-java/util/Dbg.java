package com.example.wayfindr.util;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.example.wayfindr.BuildConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Dbg.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0086\bø\u0001\u0000J\"\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {"Lcom/example/wayfindr/util/Dbg;", "", "<init>", "()V", "d", "", "tag", "", NotificationCompat.CATEGORY_MESSAGE, "Lkotlin/Function0;", "i", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class Dbg {
    public static final int $stable = 0;
    public static final Dbg INSTANCE = new Dbg();

    private Dbg() {
    }

    public final void d(String tag, Function0<String> msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg.invoke());
        }
    }

    public final void i(String tag, Function0<String> msg) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg.invoke());
        }
    }
}
