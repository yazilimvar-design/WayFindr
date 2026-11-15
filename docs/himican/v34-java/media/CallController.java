package com.example.wayfindr.media;

import android.content.Context;
import android.telecom.TelecomManager;
import android.util.Log;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CallController.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/example/wayfindr/media/CallController;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "tm", "Landroid/telecom/TelecomManager;", "kotlin.jvm.PlatformType", "hasPermission", "", "answer", "hangup", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CallController {
    private static final String TAG = "CallController";
    private final Context context;
    private final TelecomManager tm;
    public static final int $stable = 8;

    public CallController(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.tm = (TelecomManager) this.context.getSystemService(TelecomManager.class);
    }

    public final boolean hasPermission() {
        return ContextCompat.checkSelfPermission(this.context, "android.permission.ANSWER_PHONE_CALLS") == 0;
    }

    public final boolean answer() {
        Object m8392constructorimpl;
        if (!hasPermission()) {
            Log.i(TAG, "answer: no ANSWER_PHONE_CALLS");
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            TelecomManager telecomManager = this.tm;
            if (telecomManager != null) {
                telecomManager.acceptRingingCall();
            }
            m8392constructorimpl = Result.m8392constructorimpl(true);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "answer failed: " + m8395exceptionOrNullimpl.getMessage());
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = false;
        }
        return ((Boolean) m8392constructorimpl).booleanValue();
    }

    public final boolean hangup() {
        Object m8392constructorimpl;
        if (!hasPermission()) {
            Log.i(TAG, "hangup: no ANSWER_PHONE_CALLS");
            return false;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            TelecomManager telecomManager = this.tm;
            m8392constructorimpl = Result.m8392constructorimpl(Boolean.valueOf(telecomManager != null ? telecomManager.endCall() : false));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "hangup failed: " + m8395exceptionOrNullimpl.getMessage());
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = false;
        }
        return ((Boolean) m8392constructorimpl).booleanValue();
    }
}
