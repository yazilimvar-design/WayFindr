package com.example.wayfindr.ui.theme;

import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Theme.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0002\u0010\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"WayfindrColorScheme", "Landroidx/compose/material3/ColorScheme;", "WayfindrTheme", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ThemeKt {
    private static final ColorScheme WayfindrColorScheme = ColorSchemeKt.m2266darkColorScheme_VG5OTI$default(ColorKt.getGold(), ColorKt.getOnGold(), ColorKt.getGoldTint(), ColorKt.getGold(), 0, ColorKt.getTextMid(), ColorKt.getBg1(), 0, 0, 0, 0, 0, 0, ColorKt.getBg1(), ColorKt.getTextHi(), ColorKt.getSurf1(), ColorKt.getTextHi(), ColorKt.getSurf2(), ColorKt.getTextMid(), 0, 0, 0, ColorKt.getAlert(), ColorKt.getTextHi(), 0, 0, ColorKt.getLine2(), 0, ColorKt.getBg0(), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -348643440, 65535, null);

    static final Unit WayfindrTheme$lambda$0(Function2 function2, int i, Composer composer, int i2) {
        WayfindrTheme(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void WayfindrTheme(Function2<? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed) {
        final Function2 content2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(-2005026917);
        ComposerKt.sourceInformation($composer2, "C(WayfindrTheme)N(content)27@819L123:Theme.kt#anc1gi");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            content2 = content;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2005026917, $dirty, -1, "com.example.wayfindr.ui.theme.WayfindrTheme (Theme.kt:26)");
            }
            content2 = content;
            MaterialThemeKt.MaterialTheme(WayfindrColorScheme, null, TypeKt.getTypography(), content2, $composer2, (($dirty << 9) & 7168) | 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.theme.ThemeKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ThemeKt.WayfindrTheme$lambda$0(Function2.this, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
