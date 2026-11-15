package com.example.wayfindr.util;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CrashGuard.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ \u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/example/wayfindr/util/CrashGuard;", "", "<init>", "()V", "TAG", "", "install", "", "context", "Landroid/content/Context;", "writeCrash", "thread", "Ljava/lang/Thread;", "error", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class CrashGuard {
    public static final int $stable = 0;
    public static final CrashGuard INSTANCE = new CrashGuard();
    private static final String TAG = "CrashGuard";

    private CrashGuard() {
    }

    public final void install(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        final Context app = context.getApplicationContext();
        final Thread.UncaughtExceptionHandler previous = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.example.wayfindr.util.CrashGuard$$ExternalSyntheticLambda0
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public final void uncaughtException(Thread thread, Throwable th) {
                CrashGuard.install$lambda$2(previous, app, thread, th);
            }
        });
        Log.i(TAG, "Uncaught-exception trace installed");
    }

    static final void install$lambda$2(Thread.UncaughtExceptionHandler $previous, Context $app, Thread thread, Throwable error) {
        Object m8392constructorimpl;
        CrashGuard crashGuard = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            Intrinsics.checkNotNull($app);
            Intrinsics.checkNotNull(thread);
            Intrinsics.checkNotNull(error);
            crashGuard.writeCrash($app, thread, error);
            m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "failed to persist crash: " + m8395exceptionOrNullimpl.getMessage());
        }
        if ($previous != null) {
            $previous.uncaughtException(thread, error);
        }
    }

    private final void writeCrash(Context context, Thread thread, Throwable error) {
        File file = new File(context.getExternalFilesDir(null), "diag");
        file.mkdirs();
        long now = System.currentTimeMillis();
        String stamp = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date(now));
        StringWriter stringWriter = new StringWriter();
        error.printStackTrace(new PrintWriter(stringWriter));
        String stack = stringWriter.toString();
        Intrinsics.checkNotNullExpressionValue(stack, "toString(...)");
        StringBuilder sb = new StringBuilder();
        sb.append("=== WayfindR crash ===").append('\n');
        sb.append("when=" + stamp + " (" + now + ")").append('\n');
        sb.append("thread=" + thread.getName()).append('\n');
        sb.append("exception=" + error.getClass().getName() + ": " + error.getMessage()).append('\n');
        sb.append("apk=" + BuildId.INSTANCE.sha12(context)).append('\n');
        sb.append("app=1.3.2-OSS-preview (14)").append('\n');
        sb.append("device=" + Build.MANUFACTURER + " " + Build.MODEL).append('\n');
        sb.append("android=" + Build.VERSION.RELEASE + " (sdk " + Build.VERSION.SDK_INT + ")").append('\n');
        sb.append('\n');
        sb.append(stack);
        String text = sb.toString();
        FilesKt.writeText$default(new File(file, "crash-" + stamp + ".log"), text, null, 2, null);
    }
}
