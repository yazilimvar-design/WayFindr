package com.example.wayfindr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.EdgeToEdge;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.example.wayfindr.ui.navigation.AppNavigationKt;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.ThemeKt;
import com.example.wayfindr.viewmodel.RouteViewModel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/wayfindr/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "routeViewModel", "Lcom/example/wayfindr/viewmodel/RouteViewModel;", "getRouteViewModel", "()Lcom/example/wayfindr/viewmodel/RouteViewModel;", "routeViewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "handleIntent", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 8;

    /* renamed from: routeViewModel$delegate, reason: from kotlin metadata */
    private final Lazy routeViewModel;

    public MainActivity() {
        final MainActivity mainActivity = this;
        final Function0 function0 = null;
        this.routeViewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(RouteViewModel.class), new Function0<ViewModelStore>() { // from class: com.example.wayfindr.MainActivity$special$$inlined$viewModels$default$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelStore invoke() {
                return ComponentActivity.this.getViewModelStore();
            }
        }, new Function0<ViewModelProvider.Factory>() { // from class: com.example.wayfindr.MainActivity$special$$inlined$viewModels$default$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewModelProvider.Factory invoke() {
                return ComponentActivity.this.getDefaultViewModelProviderFactory();
            }
        }, new Function0<CreationExtras>() { // from class: com.example.wayfindr.MainActivity$special$$inlined$viewModels$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final CreationExtras invoke() {
                CreationExtras creationExtras;
                Function0 function02 = Function0.this;
                return (function02 == null || (creationExtras = (CreationExtras) function02.invoke()) == null) ? mainActivity.getDefaultViewModelCreationExtras() : creationExtras;
            }
        });
    }

    private final RouteViewModel getRouteViewModel() {
        return (RouteViewModel) this.routeViewModel.getValue();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable$default(this, null, null, 3, null);
        handleIntent(getIntent());
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(45234727, true, new Function2() { // from class: com.example.wayfindr.MainActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.onCreate$lambda$2(MainActivity.this, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$2(final MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C25@895L174,25@881L188:MainActivity.kt#vmsv6t");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(45234727, $changed, -1, "com.example.wayfindr.MainActivity.onCreate.<anonymous> (MainActivity.kt:25)");
            }
            ThemeKt.WayfindrTheme(ComposableLambdaKt.rememberComposableLambda(-671685477, true, new Function2() { // from class: com.example.wayfindr.MainActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.onCreate$lambda$2$lambda$1(MainActivity.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$2$lambda$1(final MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C26@969L86,26@913L142:MainActivity.kt#vmsv6t");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-671685477, $changed, -1, "com.example.wayfindr.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:26)");
            }
            SurfaceKt.m2926SurfaceT9BRK9s(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, ColorKt.getBg1(), 0L, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1310890656, true, new Function2() { // from class: com.example.wayfindr.MainActivity$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return MainActivity.onCreate$lambda$2$lambda$1$lambda$0(MainActivity.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 12582918, 122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$2$lambda$1$lambda$0(MainActivity this$0, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C27@991L46:MainActivity.kt#vmsv6t");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1310890656, $changed, -1, "com.example.wayfindr.MainActivity.onCreate.<anonymous>.<anonymous>.<anonymous> (MainActivity.kt:27)");
            }
            AppNavigationKt.AppNavigation(null, null, this$0.getRouteViewModel(), $composer, RouteViewModel.$stable << 6, 3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        Intrinsics.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void handleIntent(Intent intent) {
        String text;
        Uri data;
        String uri;
        String action = intent != null ? intent.getAction() : null;
        if (action != null) {
            switch (action.hashCode()) {
                case -1173264947:
                    if (action.equals("android.intent.action.SEND") && Intrinsics.areEqual(intent.getType(), "text/plain") && (text = intent.getStringExtra("android.intent.extra.TEXT")) != null) {
                        getRouteViewModel().handleSharedText(text);
                        break;
                    }
                    break;
                case -1173171990:
                    if (action.equals("android.intent.action.VIEW") && (data = intent.getData()) != null && (uri = data.toString()) != null) {
                        getRouteViewModel().handleSharedText(uri);
                        break;
                    }
                    break;
            }
        }
    }
}
