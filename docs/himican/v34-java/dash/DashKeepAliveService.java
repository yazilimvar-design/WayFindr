package com.example.wayfindr.dash;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.example.wayfindr.MainActivity;
import com.example.wayfindr.R;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DashKeepAliveService.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\"\u0010\u000e\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0002J\b\u0010\u0017\u001a\u00020\u0013H\u0002J\b\u0010\u0018\u001a\u00020\u0013H\u0016J\b\u0010\u0019\u001a\u00020\u0013H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002R\u0014\u0010\u0004\u001a\b\u0018\u00010\u0005R\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0018\u00010\bR\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/example/wayfindr/dash/DashKeepAliveService;", "Landroid/app/Service;", "<init>", "()V", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "wifiLock", "Landroid/net/wifi/WifiManager$WifiLock;", "Landroid/net/wifi/WifiManager;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onStartCommand", "", "flags", "startId", "startForegroundLocks", "", "hasLocationPermission", "", "acquireLocks", "releaseLocks", "onDestroy", "createChannel", "buildNotification", "Landroid/app/Notification;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashKeepAliveService extends Service {
    public static final String ACTION_PAUSE_FOR_RECONNECT = "com.example.wayfindr.DASH_PAUSE_RECONNECT";
    public static final String ACTION_START = "com.example.wayfindr.DASH_START";
    public static final String ACTION_STOP = "com.example.wayfindr.DASH_STOP";
    private static final String CHANNEL_ID = "wayfindr_dash";
    private static final int NOTIF_ID = 4701;
    private static final String TAG = "DashKeepAlive";
    private PowerManager.WakeLock wakeLock;
    private WifiManager.WifiLock wifiLock;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: DashKeepAliveService.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/example/wayfindr/dash/DashKeepAliveService$Companion;", "", "<init>", "()V", "TAG", "", "CHANNEL_ID", "NOTIF_ID", "", "ACTION_START", "ACTION_STOP", "ACTION_PAUSE_FOR_RECONNECT", "start", "", "context", "Landroid/content/Context;", "stop", "pauseForReconnect", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intent i = new Intent(context, (Class<?>) DashKeepAliveService.class).setAction(DashKeepAliveService.ACTION_START);
            Intrinsics.checkNotNullExpressionValue(i, "setAction(...)");
            context.startForegroundService(i);
        }

        public final void stop(Context context) {
            Object m8392constructorimpl;
            Intrinsics.checkNotNullParameter(context, "context");
            Intent i = new Intent(context, (Class<?>) DashKeepAliveService.class);
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                m8392constructorimpl = Result.m8392constructorimpl(context.startService(i.setAction(DashKeepAliveService.ACTION_STOP)));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            if (Result.m8395exceptionOrNullimpl(m8392constructorimpl) != null) {
                context.stopService(new Intent(context, (Class<?>) DashKeepAliveService.class));
            }
        }

        public final void pauseForReconnect(Context context) {
            Object m8392constructorimpl;
            Intrinsics.checkNotNullParameter(context, "context");
            Intent i = new Intent(context, (Class<?>) DashKeepAliveService.class).setAction(DashKeepAliveService.ACTION_PAUSE_FOR_RECONNECT);
            Intrinsics.checkNotNullExpressionValue(i, "setAction(...)");
            try {
                Result.Companion companion = Result.INSTANCE;
                Companion companion2 = this;
                m8392constructorimpl = Result.m8392constructorimpl(context.startForegroundService(i));
            } catch (Throwable th) {
                Result.Companion companion3 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
            if (m8395exceptionOrNullimpl != null) {
                Log.w(DashKeepAliveService.TAG, "Could not pause foreground locks for reconnect", m8395exceptionOrNullimpl);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case -287538092:
                    if (action.equals(ACTION_PAUSE_FOR_RECONNECT)) {
                        releaseLocks();
                        Log.i(TAG, "Dash unavailable — keeping service alive without GPS/CPU/Wi‑Fi locks");
                        return 1;
                    }
                    break;
                case 1661954940:
                    if (action.equals(ACTION_STOP)) {
                        stopSelf();
                        return 2;
                    }
                    break;
            }
        }
        startForegroundLocks();
        return 1;
    }

    private final void startForegroundLocks() {
        createChannel();
        int type = 16;
        try {
            if (!hasLocationPermission()) {
                Log.w(TAG, "Location not granted — starting FGS without the LOCATION type");
            } else {
                type = 16 | 8;
            }
            startForeground(NOTIF_ID, buildNotification(), type);
            acquireLocks();
            Log.i(TAG, "Foreground service up — wake+wifi locks held");
        } catch (Exception e) {
            Log.e(TAG, "Foreground service start failed — continuing without locks", e);
            stopSelf();
        }
    }

    private final boolean hasLocationPermission() {
        boolean fine = checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0;
        boolean coarse = checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == 0;
        return fine || coarse;
    }

    private final void acquireLocks() {
        WifiManager.WifiLock wifiLock;
        PowerManager.WakeLock wakeLock;
        Object systemService = getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        PowerManager pm = (PowerManager) systemService;
        if (this.wakeLock == null) {
            PowerManager.WakeLock newWakeLock = pm.newWakeLock(1, "wayfindr:dash");
            newWakeLock.setReferenceCounted(false);
            this.wakeLock = newWakeLock;
        }
        PowerManager.WakeLock wakeLock2 = this.wakeLock;
        if (((wakeLock2 == null || wakeLock2.isHeld()) ? false : true) && (wakeLock = this.wakeLock) != null) {
            wakeLock.acquire();
        }
        Object systemService2 = getApplicationContext().getSystemService("wifi");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.net.wifi.WifiManager");
        WifiManager wm = (WifiManager) systemService2;
        if (this.wifiLock == null) {
            WifiManager.WifiLock createWifiLock = wm.createWifiLock(4, "wayfindr:dash");
            createWifiLock.setReferenceCounted(false);
            this.wifiLock = createWifiLock;
        }
        WifiManager.WifiLock wifiLock2 = this.wifiLock;
        if (!((wifiLock2 == null || wifiLock2.isHeld()) ? false : true) || (wifiLock = this.wifiLock) == null) {
            return;
        }
        wifiLock.acquire();
    }

    private final void releaseLocks() {
        WifiManager.WifiLock wifiLock;
        PowerManager.WakeLock wakeLock;
        boolean z = true;
        try {
            Result.Companion companion = Result.INSTANCE;
            DashKeepAliveService dashKeepAliveService = this;
            PowerManager.WakeLock wakeLock2 = dashKeepAliveService.wakeLock;
            if ((wakeLock2 != null && wakeLock2.isHeld()) && (wakeLock = dashKeepAliveService.wakeLock) != null) {
                wakeLock.release();
            }
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        try {
            Result.Companion companion3 = Result.INSTANCE;
            DashKeepAliveService dashKeepAliveService2 = this;
            WifiManager.WifiLock wifiLock2 = dashKeepAliveService2.wifiLock;
            if (wifiLock2 == null || !wifiLock2.isHeld()) {
                z = false;
            }
            if (z && (wifiLock = dashKeepAliveService2.wifiLock) != null) {
                wifiLock.release();
            }
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th2) {
            Result.Companion companion4 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th2));
        }
        this.wakeLock = null;
        this.wifiLock = null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        releaseLocks();
        Log.i(TAG, "Foreground service stopped — locks released");
        super.onDestroy();
    }

    private final void createChannel() {
        NotificationManager nm = (NotificationManager) getSystemService(NotificationManager.class);
        if (nm.getNotificationChannel(CHANNEL_ID) == null) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, "Konsol bağlantısı", 2);
            notificationChannel.setDescription("Harita aktarımını ve otomatik yeniden bağlanmayı sürdürür.");
            nm.createNotificationChannel(notificationChannel);
        }
    }

    private final Notification buildNotification() {
        PendingIntent open = PendingIntent.getActivity(this, 0, new Intent(this, (Class<?>) MainActivity.class).addFlags(536870912), AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL);
        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID);
        Notification build = builder.setContentTitle("Himalayan 450 Casa Brown").setContentText("Konsol bağlantısı ve otomatik yeniden bağlanma etkin.").setSmallIcon(R.mipmap.ic_launcher).setOngoing(true).setContentIntent(open).build();
        Intrinsics.checkNotNullExpressionValue(build, "build(...)");
        return build;
    }
}
