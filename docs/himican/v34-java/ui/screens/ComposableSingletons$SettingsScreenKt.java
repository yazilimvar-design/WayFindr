package com.example.wayfindr.ui.screens;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.ChipTone;
import com.example.wayfindr.ui.components.WayfindrComponentsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ComposableSingletons$SettingsScreenKt {
    public static final ComposableSingletons$SettingsScreenKt INSTANCE = new ComposableSingletons$SettingsScreenKt();
    private static Function2<Composer, Integer, Unit> lambda$1102592676 = ComposableLambdaKt.composableLambdaInstance(1102592676, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$SettingsScreenKt.lambda_1102592676$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function3<ColumnScope, Composer, Integer, Unit> lambda$720111070 = ComposableLambdaKt.composableLambdaInstance(720111070, false, new Function3() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$SettingsScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$SettingsScreenKt.lambda_720111070$lambda$1((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$1102592676$app() {
        return lambda$1102592676;
    }

    public final Function3<ColumnScope, Composer, Integer, Unit> getLambda$720111070$app() {
        return lambda$720111070;
    }

    static final Unit lambda_720111070$lambda$1(ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C151@9413L387:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(720111070, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$SettingsScreenKt.lambda$720111070.<anonymous> (SettingsScreen.kt:151)");
            }
            SettingsScreenKt.SettingRow(WayfindrIcons.INSTANCE.getZap(), "Çalan medya ve aramalar konsolda", "Bu sürümde kullanılamaz. Şarkı bilgilerini ve arayan adlarını konsolda görmek için WayfindR tam sürümünü Sürümler bölümünden yükleyin.", lambda$1102592676, true, null, $composer, 28080, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1102592676$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C155@9694L60:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1102592676, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$SettingsScreenKt.lambda$1102592676.<anonymous> (SettingsScreen.kt:155)");
            }
            WayfindrComponentsKt.WayfindrChip("Yalnızca tam sürüm", ChipTone.Off, true, null, null, $composer, 438, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
