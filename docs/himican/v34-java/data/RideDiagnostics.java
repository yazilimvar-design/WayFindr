package com.example.wayfindr.data;

import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.maplibre.android.style.layers.Property;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RideDiagnostics.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0014J\u000e\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0014H\u0002J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\nH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/example/wayfindr/data/RideDiagnostics;", "", "<init>", "()V", "KEEP_FILES", "", "clock", "Ljava/text/SimpleDateFormat;", "lock", "dir", "Ljava/io/File;", "file", "sessionStartMs", "", "init", "", "context", "Landroid/content/Context;", "start", "reason", "", "log", "tag", NotificationCompat.CATEGORY_MESSAGE, "stop", "raw", Property.SYMBOL_PLACEMENT_LINE, "rotate", "d", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
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

    public final void init(Context context) {
        Object m8392constructorimpl;
        Intrinsics.checkNotNullParameter(context, "context");
        if (dir != null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            RideDiagnostics rideDiagnostics = this;
            File file2 = new File(context.getExternalFilesDir(null), "diag");
            file2.mkdirs();
            m8392constructorimpl = Result.m8392constructorimpl(file2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8399isSuccessimpl(m8392constructorimpl)) {
            dir = (File) m8392constructorimpl;
        }
    }

    public final void start(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        File d = dir;
        if (d == null) {
            return;
        }
        synchronized (lock) {
            sessionStartMs = System.currentTimeMillis();
            file = new File(d, "ride-" + new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date()) + ".log");
            INSTANCE.rotate(d);
            INSTANCE.raw("==== session start: " + reason + " — " + Build.MANUFACTURER + " " + Build.MODEL + ", Android " + Build.VERSION.RELEASE + " (API " + Build.VERSION.SDK_INT + ") ====");
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void log(String tag, String msg) {
        String str;
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(msg, "msg");
        if (file == null) {
            return;
        }
        synchronized (lock) {
            if (sessionStartMs > 0) {
                str = String.format("+%6dms", Arrays.copyOf(new Object[]{Long.valueOf(System.currentTimeMillis() - sessionStartMs)}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            } else {
                str = "         ";
            }
            INSTANCE.raw(str + "  [" + tag + "] " + msg);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final void stop(String reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        if (file == null) {
            return;
        }
        synchronized (lock) {
            INSTANCE.raw("==== session end: " + reason + " ====");
            file = null;
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void raw(String line) {
        File f = file;
        if (f == null) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            RideDiagnostics rideDiagnostics = this;
            FilesKt.appendText$default(f, clock.format(new Date()) + "  " + line + "\n", null, 2, null);
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
    }

    private final void rotate(File d) {
        File[] logs = d.listFiles(new FileFilter() { // from class: com.example.wayfindr.data.RideDiagnostics$$ExternalSyntheticLambda0
            @Override // java.io.FileFilter
            public final boolean accept(File file2) {
                return RideDiagnostics.rotate$lambda$7(file2);
            }
        });
        if (logs != null && logs.length > 12) {
            for (File file2 : CollectionsKt.dropLast(ArraysKt.sortedWith(logs, new Comparator() { // from class: com.example.wayfindr.data.RideDiagnostics$rotate$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(Long.valueOf(((File) t).lastModified()), Long.valueOf(((File) t2).lastModified()));
                }
            }), 12)) {
                RideDiagnostics rideDiagnostics = INSTANCE;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Result.m8392constructorimpl(Boolean.valueOf(file2.delete()));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m8392constructorimpl(ResultKt.createFailure(th));
                }
            }
        }
    }

    static final boolean rotate$lambda$7(File x) {
        String name = x.getName();
        Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
        if (!StringsKt.startsWith$default(name, "ride-", false, 2, (Object) null)) {
            return false;
        }
        String name2 = x.getName();
        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
        return StringsKt.endsWith$default(name2, ".log", false, 2, (Object) null);
    }
}
