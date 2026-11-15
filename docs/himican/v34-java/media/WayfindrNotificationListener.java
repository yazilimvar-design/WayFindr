package com.example.wayfindr.media;

import android.app.Notification;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: WayfindrNotificationListener.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\u0012\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u000eH\u0002¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/media/WayfindrNotificationListener;", "Landroid/service/notification/NotificationListenerService;", "<init>", "()V", "onListenerConnected", "", "onListenerDisconnected", "onNotificationPosted", "sbn", "Landroid/service/notification/StatusBarNotification;", "extractActions", "Lkotlin/Pair;", "Landroid/app/PendingIntent;", "n", "Landroid/app/Notification;", "incoming", "", "onNotificationRemoved", "callerName", "", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class WayfindrNotificationListener extends NotificationListenerService {
    private static final String TAG = "NsNotifListener";
    private static volatile WayfindrNotificationListener bound;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @Override // android.service.notification.NotificationListenerService
    public void onListenerConnected() {
        bound = this;
        RideDiagnostics.INSTANCE.log("media", "notification listener CONNECTED");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onListenerDisconnected() {
        if (bound == this) {
            bound = null;
        }
        RideDiagnostics.INSTANCE.log("media", "notification listener DISCONNECTED");
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationPosted(StatusBarNotification sbn) {
        Notification n;
        if (sbn == null || (n = sbn.getNotification()) == null || !Intrinsics.areEqual(n.category, NotificationCompat.CATEGORY_CALL)) {
            return;
        }
        int type = Build.VERSION.SDK_INT >= 31 ? n.extras.getInt(NotificationCompat.EXTRA_CALL_TYPE, 0) : 1;
        boolean incoming = type == 1;
        boolean active = type == 2 || type == 3;
        if (!incoming && !active) {
            CallInfoProvider.INSTANCE.update(null);
            return;
        }
        Pair<PendingIntent, PendingIntent> extractActions = extractActions(n, incoming);
        PendingIntent answer = extractActions.component1();
        PendingIntent decline = extractActions.component2();
        String callerName = callerName(n);
        if (StringsKt.isBlank(callerName)) {
            callerName = incoming ? "Call" : "On call";
        }
        String caller = callerName;
        RideDiagnostics.INSTANCE.log("media", (incoming ? "INCOMING" : "ACTIVE") + " call: '" + caller + "' (answerAct=" + (answer != null) + " declineAct=" + (decline != null) + ")");
        CallInfoProvider.INSTANCE.update(new IncomingCall(caller, incoming, answer, decline));
        Log.d(TAG, "call(" + incoming + "): " + caller);
    }

    private final Pair<PendingIntent, PendingIntent> extractActions(Notification n, boolean incoming) {
        String str;
        Object answer;
        String obj;
        Object answer2 = null;
        Object decline = null;
        Notification.Action[] acts = n.actions;
        if (acts != null) {
            for (Notification.Action action : acts) {
                CharSequence charSequence = action.title;
                if (charSequence == null || (obj = charSequence.toString()) == null) {
                    str = null;
                } else {
                    str = obj.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
                }
                if (str == null) {
                    str = "";
                }
                if (answer2 == null) {
                    answer = answer2;
                    if (StringsKt.contains$default((CharSequence) str, (CharSequence) "answer", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "accept", false, 2, (Object) null)) {
                        answer2 = action.actionIntent;
                    }
                } else {
                    answer = answer2;
                }
                if (decline == null && (StringsKt.contains$default((CharSequence) str, (CharSequence) "decline", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "reject", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "dismiss", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "hang", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "end", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str, (CharSequence) "ignore", false, 2, (Object) null))) {
                    decline = action.actionIntent;
                    answer2 = answer;
                } else {
                    answer2 = answer;
                }
            }
        }
        if (incoming && answer2 == null && decline == null && acts != null && acts.length == 2) {
            decline = acts[0].actionIntent;
            answer2 = acts[1].actionIntent;
        }
        return TuplesKt.to(answer2, decline);
    }

    @Override // android.service.notification.NotificationListenerService
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Notification notification;
        if (Intrinsics.areEqual((sbn == null || (notification = sbn.getNotification()) == null) ? null : notification.category, NotificationCompat.CATEGORY_CALL)) {
            CallInfoProvider.INSTANCE.update(null);
        }
    }

    private final String callerName(Notification n) {
        Bundle e = n.extras;
        CharSequence charSequence = e.getCharSequence(NotificationCompat.EXTRA_TITLE);
        String title = charSequence != null ? charSequence.toString() : null;
        String str = title;
        if (!(str == null || StringsKt.isBlank(str))) {
            return title;
        }
        CharSequence charSequence2 = e.getCharSequence(NotificationCompat.EXTRA_TEXT);
        String obj = charSequence2 != null ? charSequence2.toString() : null;
        return obj == null ? "" : obj;
    }

    /* compiled from: WayfindrNotificationListener.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/example/wayfindr/media/WayfindrNotificationListener$Companion;", "", "<init>", "()V", "TAG", "", "bound", "Lcom/example/wayfindr/media/WayfindrNotificationListener;", "unbindNow", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void unbindNow() {
            Object m8392constructorimpl;
            WayfindrNotificationListener wayfindrNotificationListener = WayfindrNotificationListener.bound;
            if (wayfindrNotificationListener != null) {
                Companion companion = WayfindrNotificationListener.INSTANCE;
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    wayfindrNotificationListener.requestUnbind();
                    m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                }
                Result.m8391boximpl(m8392constructorimpl);
            }
        }
    }
}
