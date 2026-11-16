package com.example.wayfindr.data;

import android.content.Context;
import android.os.Build;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.data.RideDiagnostics$;
import com.example.wayfindr.data.RideDiagnostics$rotate$;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: RideDiagnostics.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class RideDiagnostics {
    private static final int KEEP_FILES = 12;
    private static volatile File dir;
    private static volatile File file;
    private static volatile long sessionStartMs;
    public static final RideDiagnostics INSTANCE = new RideDiagnostics();
    private static final SimpleDateFormat clock = new SimpleDateFormat("HH:mm:ss.SSS", Locale.US);
    private static final Object lock = new Object();
    public static final int $stable = 8;

    private RideDiagnostics() {
    }

    private final void raw(String str) {
        File file2 = file;
        if (file2 == null) {
            return;
        }
        try {
            Result.Companion companion = Result.Companion;
            RideDiagnostics rideDiagnostics = this;
            FilesKt.appendText$default(file2, clock.format(new Date()) + "  " + str + "\n", (Charset) null, 2, (Object) null);
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
    }

    private final void rotate(File file2) {
        File[] listFiles = file2.listFiles((FileFilter) new RideDiagnostics$.ExternalSyntheticLambda0());
        if (listFiles != null && listFiles.length > KEEP_FILES) {
            for (File file3 : CollectionsKt.dropLast(ArraysKt.sortedWith(listFiles, new RideDiagnostics$rotate$.inlined.sortedBy.1()), KEEP_FILES)) {
                RideDiagnostics rideDiagnostics = INSTANCE;
                try {
                    Result.Companion companion = Result.Companion;
                    Result.constructor-impl(Boolean.valueOf(file3.delete()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    Result.constructor-impl(ResultKt.createFailure(th));
                }
            }
        }
    }

    static final boolean rotate$lambda$7(File file2) {
        String name = file2.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        if (!StringsKt.startsWith$default(name, "ride-", false, 2, (Object) null)) {
            return false;
        }
        String name2 = file2.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        return StringsKt.endsWith$default(name2, ".log", false, 2, (Object) null);
    }

    public final void init(Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(context, "context");
        if (dir != null) {
            return;
        }
        try {
            Result.Companion companion = Result.Companion;
            RideDiagnostics rideDiagnostics = this;
            File file2 = new File(context.getExternalFilesDir(null), "diag");
            file2.mkdirs();
            obj = Result.constructor-impl(file2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isSuccess-impl(obj)) {
            dir = (File) obj;
        }
    }

    public final void log(String str, String str2) {
        String str3;
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(str2, "msg");
        if (file == null) {
            return;
        }
        synchronized (lock) {
            if (sessionStartMs > 0) {
                str3 = String.format("+%6dms", Arrays.copyOf(new Object[]{Long.valueOf(System.currentTimeMillis() - sessionStartMs)}, 1));
                Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            } else {
                str3 = "         ";
            }
            INSTANCE.raw(str3 + "  [" + str + "] " + str2);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void start(String str) {
        Intrinsics.checkNotNullParameter(str, "reason");
        File file2 = dir;
        if (file2 == null) {
            return;
        }
        NavPlus.exportLogs(file2);
        synchronized (lock) {
            sessionStartMs = System.currentTimeMillis();
            file = new File(file2, "ride-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date()) + ".log");
            INSTANCE.rotate(file2);
            INSTANCE.raw("==== session start: " + str + " — " + Build.MANUFACTURER + " " + Build.MODEL + ", Android " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ") ====");
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void stop(String str) {
        Intrinsics.checkNotNullParameter(str, "reason");
        if (file == null) {
            return;
        }
        synchronized (lock) {
            INSTANCE.raw("==== session end: " + str + " ====");
            file = null;
            Unit unit = Unit.INSTANCE;
        }
        NavPlus.exportLogs(dir);
    }
}
