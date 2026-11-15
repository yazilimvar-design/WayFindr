package com.example.wayfindr.ui.screens;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.wayfindr.ui.theme.ColorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HomeScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ComposableSingletons$HomeScreenKt {
    public static final ComposableSingletons$HomeScreenKt INSTANCE = new ComposableSingletons$HomeScreenKt();

    /* renamed from: lambda$-967594650, reason: not valid java name */
    private static Function3<ColumnScope, Composer, Integer, Unit> f149lambda$967594650 = ComposableLambdaKt.composableLambdaInstance(-967594650, false, new Function3() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$HomeScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$HomeScreenKt.lambda__967594650$lambda$0((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: getLambda$-967594650$app, reason: not valid java name */
    public final Function3<ColumnScope, Composer, Integer, Unit> m8360getLambda$967594650$app() {
        return f149lambda$967594650;
    }

    static final Unit lambda__967594650$lambda$0(ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C172@6915L232:HomeScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-967594650, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$HomeScreenKt.lambda$-967594650.<anonymous> (HomeScreen.kt:172)");
            }
            TextKt.m3069TextNvy7gAk("Henüz kaydedilmiş bir varış noktası yok. Google Maps'ten bir yer paylaşın, ardından “Bu varış noktasını kaydet” seçeneğine dokunun.", null, ColorKt.getTextLo(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24582, 0, 262122);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }
}
