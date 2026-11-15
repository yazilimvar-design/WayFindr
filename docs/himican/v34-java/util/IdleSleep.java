package com.example.wayfindr.util;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.service.notification.NotificationListenerService;
import android.util.Log;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.media.MediaInfoProvider;
import com.example.wayfindr.media.WayfindrNotificationListener;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: IdleSleep.kt */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001'B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000bJ\b\u0010\u001a\u001a\u00020\u0018H\u0002J\u000e\u0010\u001b\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u000fJ\b\u0010\u001d\u001a\u00020\u000fH\u0002J\b\u0010\u001e\u001a\u00020\u0018H\u0002J\r\u0010\u001f\u001a\u00020\u0018H\u0000¢\u0006\u0002\b J\b\u0010!\u001a\u00020\u0018H\u0002J\b\u0010\"\u001a\u00020\u0018H\u0002J\b\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\n"}, d2 = {"Lcom/example/wayfindr/util/IdleSleep;", "", "<init>", "()V", "TAG", "", "IDLE_MS", "", "main", "Landroid/os/Handler;", "app", "Landroid/app/Application;", "startedActivities", "", "dashLinked", "", "alarmSet", "_asleep", "Lkotlinx/coroutines/flow/MutableStateFlow;", "asleep", "Lkotlinx/coroutines/flow/StateFlow;", "getAsleep", "()Lkotlinx/coroutines/flow/StateFlow;", "init", "", "application", "registerDebugTrigger", "setDashLinked", "linked", "inUse", "reevaluate", "onAlarm", "onAlarm$app", "enterSleep", "wake", "alarmIntent", "Landroid/app/PendingIntent;", "cancelAlarm", "rebindNotificationListener", "AlarmReceiver"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class IdleSleep {
    public static final long IDLE_MS = 600000;
    private static final String TAG = "IdleSleep";
    private static boolean alarmSet;
    private static Application app;
    private static boolean dashLinked;
    private static int startedActivities;
    public static final IdleSleep INSTANCE = new IdleSleep();
    private static final Handler main = new Handler(Looper.getMainLooper());
    private static final MutableStateFlow<Boolean> _asleep = StateFlowKt.MutableStateFlow(false);
    private static final StateFlow<Boolean> asleep = FlowKt.asStateFlow(_asleep);
    public static final int $stable = 8;

    private IdleSleep() {
    }

    public final StateFlow<Boolean> getAsleep() {
        return asleep;
    }

    public final void init(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        app = application;
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.example.wayfindr.util.IdleSleep$init$1
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
                int i;
                int i2;
                Intrinsics.checkNotNullParameter(activity, "activity");
                i = IdleSleep.startedActivities;
                IdleSleep idleSleep = IdleSleep.INSTANCE;
                IdleSleep.startedActivities = i + 1;
                i2 = IdleSleep.startedActivities;
                if (i2 == 1) {
                    IdleSleep.INSTANCE.rebindNotificationListener();
                }
                IdleSleep.INSTANCE.reevaluate();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                int i;
                Intrinsics.checkNotNullParameter(activity, "activity");
                IdleSleep idleSleep = IdleSleep.INSTANCE;
                i = IdleSleep.startedActivities;
                IdleSleep.startedActivities = RangesKt.coerceAtLeast(i - 1, 0);
                IdleSleep.INSTANCE.reevaluate();
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Intrinsics.checkNotNullParameter(outState, "outState");
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
            }
        });
        reevaluate();
        registerDebugTrigger();
    }

    private final void registerDebugTrigger() {
        if (BuildConfig.DEBUG) {
            BroadcastReceiver broadcastReceiver = new BroadcastReceiver() { // from class: com.example.wayfindr.util.IdleSleep$registerDebugTrigger$rx$1
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(intent, "intent");
                    Log.i("IdleSleep", "debug: sleep now");
                    IdleSleep.INSTANCE.onAlarm$app();
                }
            };
            IntentFilter filter = new IntentFilter("com.wayfindr.IDLE_SLEEP_NOW");
            Application application = null;
            if (Build.VERSION.SDK_INT < 33) {
                Application application2 = app;
                if (application2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("app");
                } else {
                    application = application2;
                }
                application.registerReceiver(broadcastReceiver, filter);
                return;
            }
            Application application3 = app;
            if (application3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("app");
            } else {
                application = application3;
            }
            application.registerReceiver(broadcastReceiver, filter, 2);
        }
    }

    public final void setDashLinked(final boolean linked) {
        main.post(new Runnable() { // from class: com.example.wayfindr.util.IdleSleep$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                IdleSleep.setDashLinked$lambda$0(linked);
            }
        });
    }

    static final void setDashLinked$lambda$0(boolean $linked) {
        if (dashLinked == $linked) {
            return;
        }
        dashLinked = $linked;
        if ($linked && _asleep.getValue().booleanValue()) {
            INSTANCE.rebindNotificationListener();
        }
        INSTANCE.reevaluate();
    }

    private final boolean inUse() {
        return startedActivities > 0 || dashLinked;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reevaluate() {
        if (inUse()) {
            if (alarmSet) {
                Log.i(TAG, "in use (screens=" + startedActivities + ", dash=" + dashLinked + ") — sleep cancelled");
            }
            cancelAlarm();
            if (_asleep.getValue().booleanValue()) {
                wake();
                return;
            }
            return;
        }
        if (!_asleep.getValue().booleanValue() && !alarmSet) {
            Application application = app;
            if (application == null) {
                Intrinsics.throwUninitializedPropertyAccessException("app");
                application = null;
            }
            AlarmManager am = (AlarmManager) application.getSystemService(AlarmManager.class);
            if (am == null) {
                return;
            }
            am.setAndAllowWhileIdle(3, SystemClock.elapsedRealtime() + IDLE_MS, alarmIntent());
            alarmSet = true;
            Log.i(TAG, "idle — sleeping in 10 min unless the app or dash is used");
        }
    }

    public final void onAlarm$app() {
        alarmSet = false;
        if (inUse() || _asleep.getValue().booleanValue()) {
            return;
        }
        enterSleep();
    }

    private final void enterSleep() {
        Log.i(TAG, "asleep: no screen and no dash link for 10 min");
        cancelAlarm();
        _asleep.setValue(true);
        WayfindrNotificationListener.INSTANCE.unbindNow();
    }

    private final void wake() {
        Log.i(TAG, "awake");
        _asleep.setValue(false);
    }

    private final PendingIntent alarmIntent() {
        Application application = app;
        Application application2 = null;
        if (application == null) {
            Intrinsics.throwUninitializedPropertyAccessException("app");
            application = null;
        }
        Application application3 = application;
        Application application4 = app;
        if (application4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("app");
        } else {
            application2 = application4;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(application3, 0, new Intent(application2, (Class<?>) AlarmReceiver.class), 201326592);
        Intrinsics.checkNotNullExpressionValue(broadcast, "getBroadcast(...)");
        return broadcast;
    }

    private final void cancelAlarm() {
        Application application = app;
        if (application == null) {
            Intrinsics.throwUninitializedPropertyAccessException("app");
            application = null;
        }
        AlarmManager alarmManager = (AlarmManager) application.getSystemService(AlarmManager.class);
        if (alarmManager != null) {
            alarmManager.cancel(alarmIntent());
        }
        alarmSet = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rebindNotificationListener() {
        try {
            Result.Companion companion = Result.INSTANCE;
            IdleSleep idleSleep = this;
            MediaInfoProvider.Companion companion2 = MediaInfoProvider.INSTANCE;
            Application application = app;
            Application application2 = null;
            if (application == null) {
                Intrinsics.throwUninitializedPropertyAccessException("app");
                application = null;
            }
            if (companion2.isAccessGranted(application)) {
                Application application3 = app;
                if (application3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("app");
                } else {
                    application2 = application3;
                }
                NotificationListenerService.requestRebind(new ComponentName(application2, (Class<?>) WayfindrNotificationListener.class));
            }
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion3 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
    }

    /* compiled from: IdleSleep.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lcom/example/wayfindr/util/IdleSleep$AlarmReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AlarmReceiver extends BroadcastReceiver {
        public static final int $stable = 8;

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(intent, "intent");
            IdleSleep.INSTANCE.onAlarm$app();
        }
    }
}
