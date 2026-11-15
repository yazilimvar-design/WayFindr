package com.example.wayfindr.ui.components;

import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.text.font.FontWeight;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: UpdatePrompt.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class ComposableSingletons$UpdatePromptKt {
    public static final ComposableSingletons$UpdatePromptKt INSTANCE = new ComposableSingletons$UpdatePromptKt();
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1774979970 = ComposableLambdaKt.composableLambdaInstance(1774979970, false, new Function3() { // from class: com.example.wayfindr.ui.components.ComposableSingletons$UpdatePromptKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$UpdatePromptKt.lambda_1774979970$lambda$0((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1180414343 = ComposableLambdaKt.composableLambdaInstance(1180414343, false, new Function2() { // from class: com.example.wayfindr.ui.components.ComposableSingletons$UpdatePromptKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$UpdatePromptKt.lambda_1180414343$lambda$1((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public final Function2<Composer, Integer, Unit> getLambda$1180414343$app() {
        return lambda$1180414343;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1774979970$app() {
        return lambda$1774979970;
    }

    static final Unit lambda_1180414343$lambda$1(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C59@2423L176:UpdatePrompt.kt#7crva5");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1180414343, $changed, -1, "com.example.wayfindr.ui.components.ComposableSingletons$UpdatePromptKt.lambda$1180414343.<anonymous> (UpdatePrompt.kt:59)");
            }
            TextKt.m3069TextNvy7gAk("Güncelleme mevcut", null, ColorKt.getTextHi(), null, 0L, null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572870, 0, 261946);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1774979970$lambda$0(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C151@6085L60:UpdatePrompt.kt#7crva5");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1774979970, $changed, -1, "com.example.wayfindr.ui.components.ComposableSingletons$UpdatePromptKt.lambda$1774979970.<anonymous> (UpdatePrompt.kt:151)");
            }
            TextKt.m3069TextNvy7gAk("Daha sonra", null, ColorKt.getTextLo(), null, 0L, null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262010);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
