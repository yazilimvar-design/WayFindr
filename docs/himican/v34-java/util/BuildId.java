package com.example.wayfindr.util;

import android.content.Context;
import androidx.core.os.EnvironmentCompat;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: BuildId.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/example/wayfindr/util/BuildId;", "", "<init>", "()V", "cached", "", "sha12", "context", "Landroid/content/Context;", "warm", "", "compute", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class BuildId {
    private static volatile String cached;
    public static final BuildId INSTANCE = new BuildId();
    public static final int $stable = 8;

    private BuildId() {
    }

    public final String sha12(Context context) {
        String str;
        Object m8392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        String str2 = cached;
        if (str2 == null) {
            synchronized (this) {
                str = cached;
                if (str == null) {
                    BuildId buildId = INSTANCE;
                    try {
                        Result.Companion companion = Result.INSTANCE;
                        m8392constructorimpl = Result.m8392constructorimpl(buildId.compute(context));
                    } catch (Throwable th) {
                        Result.Companion companion2 = Result.INSTANCE;
                        m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                    }
                    if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                        m8392constructorimpl = EnvironmentCompat.MEDIA_UNKNOWN;
                    }
                    cached = (String) m8392constructorimpl;
                    str = (String) m8392constructorimpl;
                }
            }
            return str;
        }
        return str2;
    }

    public final void warm(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        Thread thread = new Thread(new Runnable() { // from class: com.example.wayfindr.util.BuildId$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                BuildId.warm$lambda$3(app);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    static final void warm$lambda$3(Context $app) {
        BuildId buildId = INSTANCE;
        Intrinsics.checkNotNull($app);
        buildId.sha12($app);
    }

    private final String compute(Context context) {
        File apk = new File(context.getApplicationInfo().sourceDir);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        FileInputStream fileInputStream = new FileInputStream(apk);
        try {
            FileInputStream fileInputStream2 = fileInputStream;
            byte[] bArr = new byte[65536];
            while (true) {
                int read = fileInputStream2.read(bArr);
                if (read < 0) {
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(fileInputStream, null);
                    byte[] digest = md.digest();
                    Intrinsics.checkNotNullExpressionValue(digest, "digest(...)");
                    return StringsKt.take(ArraysKt.joinToString$default(digest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.example.wayfindr.util.BuildId$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return BuildId.compute$lambda$6(((Byte) obj).byteValue());
                        }
                    }, 30, (Object) null), 12);
                }
                md.update(bArr, 0, read);
            }
        } finally {
        }
    }

    static final CharSequence compute$lambda$6(byte it) {
        String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
