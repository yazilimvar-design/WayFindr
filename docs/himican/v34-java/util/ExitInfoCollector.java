package com.example.wayfindr.util;

import android.app.ActivityManager;
import android.app.ApplicationExitInfo;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import androidx.core.os.EnvironmentCompat;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* compiled from: ExitInfoCollector.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ \u0010\u0010\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/example/wayfindr/util/ExitInfoCollector;", "", "<init>", "()V", "TAG", "", "PREFS", "KEY_LAST_TS", "arm", "", "context", "Landroid/content/Context;", "collect", "INTERESTING", "", "", "writeExit", "dir", "Ljava/io/File;", "info", "Landroid/app/ApplicationExitInfo;", "reasonName", "reason", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ExitInfoCollector {
    private static final String KEY_LAST_TS = "last_exit_ts";
    private static final String PREFS = "wayfindr_exitinfo";
    private static final String TAG = "ExitInfoCollector";
    public static final ExitInfoCollector INSTANCE = new ExitInfoCollector();
    private static final Set<Integer> INTERESTING = SetsKt.setOf((Object[]) new Integer[]{4, 5, 6, 2, 3, 9});
    public static final int $stable = 8;

    private ExitInfoCollector() {
    }

    public final void arm(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT < 30) {
            return;
        }
        final Context app = context.getApplicationContext();
        Thread thread = new Thread(new Runnable() { // from class: com.example.wayfindr.util.ExitInfoCollector$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ExitInfoCollector.arm$lambda$2(app);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    static final void arm$lambda$2(Context $app) {
        Object m8392constructorimpl;
        ExitInfoCollector exitInfoCollector = INSTANCE;
        try {
            Result.Companion companion = Result.INSTANCE;
            ActivityManager activityManager = (ActivityManager) $app.getSystemService(ActivityManager.class);
            if (activityManager != null) {
                BuildId buildId = BuildId.INSTANCE;
                Intrinsics.checkNotNull($app);
                byte[] bytes = buildId.sha12($app).getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                activityManager.setProcessStateSummary(bytes);
            }
            m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "arm failed: " + m8395exceptionOrNullimpl.getMessage());
        }
    }

    public final void collect(Context context) {
        Object m8392constructorimpl;
        ExitInfoCollector exitInfoCollector;
        int i;
        ActivityManager activityManager;
        Object m8392constructorimpl2;
        Context context2 = context;
        Intrinsics.checkNotNullParameter(context2, "context");
        if (Build.VERSION.SDK_INT < 30) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            exitInfoCollector = this;
            i = 0;
            activityManager = (ActivityManager) context2.getSystemService(ActivityManager.class);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (activityManager == null) {
            return;
        }
        SharedPreferences sharedPreferences = context2.getSharedPreferences(PREFS, 0);
        long j = sharedPreferences.getLong(KEY_LAST_TS, 0L);
        List historicalProcessExitReasons = activityManager.getHistoricalProcessExitReasons(context2.getPackageName(), 0, 10);
        Intrinsics.checkNotNullExpressionValue(historicalProcessExitReasons, "getHistoricalProcessExitReasons(...)");
        File file = new File(context2.getExternalFilesDir(null), "diag");
        file.mkdirs();
        long j2 = j;
        for (ApplicationExitInfo applicationExitInfo : historicalProcessExitReasons) {
            if (applicationExitInfo.getTimestamp() > j) {
                ExitInfoCollector exitInfoCollector2 = exitInfoCollector;
                int i2 = i;
                j2 = Math.max(j2, applicationExitInfo.getTimestamp());
                if (INTERESTING.contains(Integer.valueOf(applicationExitInfo.getReason()))) {
                    try {
                        Result.Companion companion3 = Result.INSTANCE;
                        Intrinsics.checkNotNull(applicationExitInfo);
                        exitInfoCollector2.writeExit(context2, file, applicationExitInfo);
                        m8392constructorimpl2 = Result.m8392constructorimpl(Unit.INSTANCE);
                    } catch (Throwable th2) {
                        Result.Companion companion4 = Result.INSTANCE;
                        m8392constructorimpl2 = Result.m8392constructorimpl(ResultKt.createFailure(th2));
                    }
                    Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl2);
                    if (m8395exceptionOrNullimpl != null) {
                        Log.w(TAG, "write exit failed: " + m8395exceptionOrNullimpl.getMessage());
                    }
                }
                context2 = context;
                exitInfoCollector = exitInfoCollector2;
                i = i2;
            } else {
                context2 = context;
            }
        }
        sharedPreferences.edit().putLong(KEY_LAST_TS, j2).apply();
        m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
        Throwable m8395exceptionOrNullimpl2 = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl2 != null) {
            Log.w(TAG, "collect failed: " + m8395exceptionOrNullimpl2.getMessage());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x01e9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void writeExit(Context context, File dir, ApplicationExitInfo info) {
        Object m8392constructorimpl;
        Object m8392constructorimpl2;
        String crashedBuild;
        String description;
        byte[] processStateSummary;
        String str;
        String str2;
        String stamp = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.US).format(new Date(info.getTimestamp()));
        try {
            Result.Companion companion = Result.INSTANCE;
            ExitInfoCollector exitInfoCollector = this;
            InputStream traceInputStream = info.getTraceInputStream();
            if (traceInputStream != null) {
                Reader inputStreamReader = new InputStreamReader(traceInputStream, Charsets.UTF_8);
                BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
                try {
                    str2 = TextStreamsKt.readText(bufferedReader);
                    CloseableKt.closeFinally(bufferedReader, null);
                } finally {
                }
            } else {
                str2 = null;
            }
            m8392constructorimpl = Result.m8392constructorimpl(str2);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = null;
        }
        String trace = (String) m8392constructorimpl;
        try {
            Result.Companion companion3 = Result.INSTANCE;
            ExitInfoCollector exitInfoCollector2 = this;
            processStateSummary = info.getProcessStateSummary();
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            m8392constructorimpl2 = Result.m8392constructorimpl(ResultKt.createFailure(th2));
        }
        if (processStateSummary != null) {
            str = new String(processStateSummary, Charsets.UTF_8);
            if (!StringsKt.isBlank(str)) {
                m8392constructorimpl2 = Result.m8392constructorimpl(str);
                if (Result.m8398isFailureimpl(m8392constructorimpl2)) {
                    m8392constructorimpl2 = null;
                }
                crashedBuild = (String) m8392constructorimpl2;
                if (crashedBuild == null) {
                    crashedBuild = EnvironmentCompat.MEDIA_UNKNOWN;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("=== WayfindR process exit (ApplicationExitInfo) ===").append('\n');
                sb.append("when=" + stamp + " (" + info.getTimestamp() + ")").append('\n');
                String reasonName = INSTANCE.reasonName(info.getReason());
                description = info.getDescription();
                if (description == null) {
                    description = "";
                }
                sb.append("exception=" + reasonName + " — " + description).append('\n');
                sb.append("importance=" + info.getImportance() + " status=" + info.getStatus()).append('\n');
                sb.append("apk=" + crashedBuild).append('\n');
                sb.append("collected-by=" + BuildId.INSTANCE.sha12(context)).append('\n');
                sb.append("app=1.3.2-OSS-preview (14)").append('\n');
                sb.append("device=" + Build.MANUFACTURER + " " + Build.MODEL).append('\n');
                sb.append("android=" + Build.VERSION.RELEASE + " (sdk " + Build.VERSION.SDK_INT + ")").append('\n');
                if (trace != null) {
                    sb.append('\n');
                    sb.append("--- trace / tombstone ---").append('\n');
                    sb.append(trace);
                }
                String text = sb.toString();
                FilesKt.writeText$default(new File(dir, "crash-exit-" + stamp + ".log"), text, null, 2, null);
            }
        }
        str = null;
        m8392constructorimpl2 = Result.m8392constructorimpl(str);
        if (Result.m8398isFailureimpl(m8392constructorimpl2)) {
        }
        crashedBuild = (String) m8392constructorimpl2;
        if (crashedBuild == null) {
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("=== WayfindR process exit (ApplicationExitInfo) ===").append('\n');
        sb2.append("when=" + stamp + " (" + info.getTimestamp() + ")").append('\n');
        String reasonName2 = INSTANCE.reasonName(info.getReason());
        description = info.getDescription();
        if (description == null) {
        }
        sb2.append("exception=" + reasonName2 + " — " + description).append('\n');
        sb2.append("importance=" + info.getImportance() + " status=" + info.getStatus()).append('\n');
        sb2.append("apk=" + crashedBuild).append('\n');
        sb2.append("collected-by=" + BuildId.INSTANCE.sha12(context)).append('\n');
        sb2.append("app=1.3.2-OSS-preview (14)").append('\n');
        sb2.append("device=" + Build.MANUFACTURER + " " + Build.MODEL).append('\n');
        sb2.append("android=" + Build.VERSION.RELEASE + " (sdk " + Build.VERSION.SDK_INT + ")").append('\n');
        if (trace != null) {
        }
        String text2 = sb2.toString();
        FilesKt.writeText$default(new File(dir, "crash-exit-" + stamp + ".log"), text2, null, 2, null);
    }

    private final String reasonName(int reason) {
        switch (reason) {
            case 2:
                return "SIGNALED";
            case 3:
                return "LOW_MEMORY_KILL";
            case 4:
                return "JVM_CRASH";
            case 5:
                return "NATIVE_CRASH";
            case 6:
                return "ANR";
            case 7:
            case 8:
            default:
                return "REASON_" + reason;
            case 9:
                return "EXCESSIVE_RESOURCE_USAGE";
        }
    }
}
