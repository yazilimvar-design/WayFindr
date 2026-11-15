package com.example.wayfindr;

import android.app.Application;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.nav.OfflineRouter;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.util.BuildId;
import com.example.wayfindr.util.CrashGuard;
import com.example.wayfindr.util.ExitInfoCollector;
import com.example.wayfindr.util.IdleSleep;
import kotlin.Metadata;

/* compiled from: WayfindrApplication.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lcom/example/wayfindr/WayfindrApplication;", "Landroid/app/Application;", "<init>", "()V", "onCreate", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class WayfindrApplication extends Application {
    public static final int $stable = 8;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        ApiUsage.INSTANCE.init(this);
        DashConfig.INSTANCE.get(this);
        OfflineRouter.INSTANCE.init(this);
        OfflineRouter.INSTANCE.registerDebugProbe(this);
        BuildId.INSTANCE.warm(this);
        CrashGuard.INSTANCE.install(this);
        ExitInfoCollector.INSTANCE.arm(this);
        ExitInfoCollector.INSTANCE.collect(this);
        IdleSleep.INSTANCE.init(this);
    }
}
