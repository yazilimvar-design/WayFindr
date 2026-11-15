package com.example.wayfindr.ui.navigation;

import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.navigation.NavBackStackEntry;
import com.example.wayfindr.ui.screens.RidesScreenKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppNavigation.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class ComposableSingletons$AppNavigationKt {
    public static final ComposableSingletons$AppNavigationKt INSTANCE = new ComposableSingletons$AppNavigationKt();

    /* renamed from: lambda$-2055826793, reason: not valid java name */
    private static Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> f148lambda$2055826793 = ComposableLambdaKt.composableLambdaInstance(-2055826793, false, new Function4() { // from class: com.example.wayfindr.ui.navigation.ComposableSingletons$AppNavigationKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            return ComposableSingletons$AppNavigationKt.lambda__2055826793$lambda$0((AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
        }
    });

    /* renamed from: getLambda$-2055826793$app, reason: not valid java name */
    public final Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit> m8359getLambda$2055826793$app() {
        return f148lambda$2055826793;
    }

    static final Unit lambda__2055826793$lambda$0(AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)161@7303L13:AppNavigation.kt#pgyg2t");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2055826793, $changed, -1, "com.example.wayfindr.ui.navigation.ComposableSingletons$AppNavigationKt.lambda$-2055826793.<anonymous> (AppNavigation.kt:161)");
        }
        RidesScreenKt.RidesScreen(null, $composer, 0, 1);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }
}
