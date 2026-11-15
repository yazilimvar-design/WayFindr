package com.example.wayfindr.ui.screens;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RouteScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ComposableSingletons$RouteScreenKt {
    public static final ComposableSingletons$RouteScreenKt INSTANCE = new ComposableSingletons$RouteScreenKt();
    private static Function2<Composer, Integer, Unit> lambda$600090294 = ComposableLambdaKt.composableLambdaInstance(600090294, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RouteScreenKt.lambda_600090294$lambda$0((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: lambda$-835303176, reason: not valid java name */
    private static Function3<ColumnScope, Composer, Integer, Unit> f153lambda$835303176 = ComposableLambdaKt.composableLambdaInstance(-835303176, false, new Function3() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$RouteScreenKt.lambda__835303176$lambda$3((ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function3<RowScope, Composer, Integer, Unit> lambda$1136666397 = ComposableLambdaKt.composableLambdaInstance(1136666397, false, new Function3() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda2
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$RouteScreenKt.lambda_1136666397$lambda$4((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$1619674018 = ComposableLambdaKt.composableLambdaInstance(1619674018, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RouteScreenKt.lambda_1619674018$lambda$5((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$837491251 = ComposableLambdaKt.composableLambdaInstance(837491251, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RouteScreenKt.lambda_837491251$lambda$6((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: lambda$-1895094998, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f151lambda$1895094998 = ComposableLambdaKt.composableLambdaInstance(-1895094998, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RouteScreenKt.lambda__1895094998$lambda$7((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: lambda$-660152949, reason: not valid java name */
    private static Function3<RowScope, Composer, Integer, Unit> f152lambda$660152949 = ComposableLambdaKt.composableLambdaInstance(-660152949, false, new Function3() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return ComposableSingletons$RouteScreenKt.lambda__660152949$lambda$8((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });

    /* renamed from: lambda$-1113408602, reason: not valid java name */
    private static Function2<Composer, Integer, Unit> f150lambda$1113408602 = ComposableLambdaKt.composableLambdaInstance(-1113408602, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RouteScreenKt.lambda__1113408602$lambda$9((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$568965429 = ComposableLambdaKt.composableLambdaInstance(568965429, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RouteScreenKt.lambda_568965429$lambda$10((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    private static Function2<Composer, Integer, Unit> lambda$73407262 = ComposableLambdaKt.composableLambdaInstance(73407262, false, new Function2() { // from class: com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ComposableSingletons$RouteScreenKt.lambda_73407262$lambda$11((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    /* renamed from: getLambda$-1113408602$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8361getLambda$1113408602$app() {
        return f150lambda$1113408602;
    }

    /* renamed from: getLambda$-1895094998$app, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m8362getLambda$1895094998$app() {
        return f151lambda$1895094998;
    }

    /* renamed from: getLambda$-660152949$app, reason: not valid java name */
    public final Function3<RowScope, Composer, Integer, Unit> m8363getLambda$660152949$app() {
        return f152lambda$660152949;
    }

    /* renamed from: getLambda$-835303176$app, reason: not valid java name */
    public final Function3<ColumnScope, Composer, Integer, Unit> m8364getLambda$835303176$app() {
        return f153lambda$835303176;
    }

    public final Function3<RowScope, Composer, Integer, Unit> getLambda$1136666397$app() {
        return lambda$1136666397;
    }

    public final Function2<Composer, Integer, Unit> getLambda$1619674018$app() {
        return lambda$1619674018;
    }

    public final Function2<Composer, Integer, Unit> getLambda$568965429$app() {
        return lambda$568965429;
    }

    public final Function2<Composer, Integer, Unit> getLambda$600090294$app() {
        return lambda$600090294;
    }

    public final Function2<Composer, Integer, Unit> getLambda$73407262$app() {
        return lambda$73407262;
    }

    public final Function2<Composer, Integer, Unit> getLambda$837491251$app() {
        return lambda$837491251;
    }

    static final Unit lambda_600090294$lambda$0(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C285@12830L78:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(600090294, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$600090294.<anonymous> (RouteScreen.kt:285)");
            }
            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getSearch(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), ColorKt.getGold(), $composer, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__835303176$lambda$3(ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Function0 function02;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C439@22479L974:RouteScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-835303176, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$-835303176.<anonymous> (RouteScreen.kt:439)");
            }
            Alignment.Vertical top = Alignment.INSTANCE.getTop();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function0 = constructor;
                $composer.createNode(function0);
            } else {
                function0 = constructor;
                $composer.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer);
            Updater.m4372setimpl(m4364constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1633952592, "C440@22544L98,441@22667L29,442@22721L710:RouteScreen.kt#cef2n4");
            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getShare(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), ColorKt.getGold(), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer, 6);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier2 = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function02 = constructor2;
                $composer.createNode(function02);
            } else {
                function02 = constructor2;
                $composer.useNode();
            }
            Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer);
            Updater.m4372setimpl(m4364constructorimpl2, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -21980662, "C443@22758L168,445@22955L450:RouteScreen.kt#cef2n4");
            TextKt.m3069TextNvy7gAk("Google Haritalar'dan rota başlatın", null, ColorKt.getTextHi(), null, TextUnitKt.getSp(15), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597446, 0, 261930);
            TextKt.m3069TextNvy7gAk("Google Haritalar'ı açın → bir yer seçin → Paylaş'a dokunun → Wayfindr'ı seçin. Hedef burada görünür; ardından gösterge ekranına göndermek için Navigasyonu başlat'a dokunun.", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(4), 0.0f, 0.0f, 13, null), ColorKt.getTextMid(), null, TextUnitKt.getSp(13), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24624, 0, 261992);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1136666397$lambda$4(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C744@39966L30:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1136666397, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$1136666397.<anonymous> (RouteScreen.kt:744)");
            }
            TextKt.m3069TextNvy7gAk("İptal", null, ColorKt.getTextMid(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_1619674018$lambda$5(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C745@40020L37:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1619674018, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$1619674018.<anonymous> (RouteScreen.kt:745)");
            }
            TextKt.m3069TextNvy7gAk("Hedefi kaydet", null, ColorKt.getTextHi(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_837491251$lambda$6(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C748@40191L10:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(837491251, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$837491251.<anonymous> (RouteScreen.kt:748)");
            }
            TextKt.m3069TextNvy7gAk("Ad", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1895094998$lambda$7(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C749@40352L26:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1895094998, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$-1895094998.<anonymous> (RouteScreen.kt:749)");
            }
            TextKt.m3069TextNvy7gAk("Not (isteğe bağlı)", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__660152949$lambda$8(RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C763@41181L26:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-660152949, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$-660152949.<anonymous> (RouteScreen.kt:763)");
            }
            TextKt.m3069TextNvy7gAk("Sil", null, ColorKt.getAlert(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda__1113408602$lambda$9(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C764@41231L38:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1113408602, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$-1113408602.<anonymous> (RouteScreen.kt:764)");
            }
            TextKt.m3069TextNvy7gAk("Hedefi düzenle", null, ColorKt.getTextHi(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_568965429$lambda$10(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C767@41403L10:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(568965429, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$568965429.<anonymous> (RouteScreen.kt:767)");
            }
            TextKt.m3069TextNvy7gAk("Ad", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit lambda_73407262$lambda$11(Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C768@41564L11:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(73407262, $changed, -1, "com.example.wayfindr.ui.screens.ComposableSingletons$RouteScreenKt.lambda$73407262.<anonymous> (RouteScreen.kt:768)");
            }
            TextKt.m3069TextNvy7gAk("Not", null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }
}
