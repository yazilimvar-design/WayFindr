package com.example.wayfindr.ui.components;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;
import org.maplibre.android.style.layers.Property;

/* compiled from: BarChart.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"WayfindrBarChart", "", "data", "", "Lcom/example/wayfindr/ui/components/BarEntry;", "modifier", "Landroidx/compose/ui/Modifier;", Property.ICON_TEXT_FIT_HEIGHT, "Landroidx/compose/ui/unit/Dp;", "WayfindrBarChart-6a0pyJM", "(Ljava/util/List;Landroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class BarChartKt {
    static final Unit WayfindrBarChart_6a0pyJM$lambda$0(List list, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        m8353WayfindrBarChart6a0pyJM(list, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrBarChart_6a0pyJM$lambda$8(List list, Modifier modifier, float f, int i, int i2, Composer composer, int i3) {
        m8353WayfindrBarChart6a0pyJM(list, modifier, f, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* renamed from: WayfindrBarChart-6a0pyJM, reason: not valid java name */
    public static final void m8353WayfindrBarChart6a0pyJM(final List<BarEntry> data, Modifier modifier, float height, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        final Modifier modifier3;
        final float height2;
        Function0 function0;
        Function0 function02;
        Intrinsics.checkNotNullParameter(data, "data");
        Composer $composer2 = $composer.startRestartGroup(264573362);
        ComposerKt.sourceInformation($composer2, "C(WayfindrBarChart)N(data,modifier,height:c#ui.unit.Dp)28@885L1871:BarChart.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(data) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            f = height;
        } else if (($changed & 384) == 0) {
            f = height;
            $dirty |= $composer2.changed(f) ? 256 : 128;
        } else {
            f = height;
        }
        int $dirty2 = $dirty;
        if ($composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            final Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            final float height3 = i3 != 0 ? Dp.m7908constructorimpl(AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR) : f;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(264573362, $dirty2, -1, "com.example.wayfindr.ui.components.WayfindrBarChart (BarChart.kt:24)");
            }
            if (data.isEmpty()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.BarChartKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return BarChartKt.WayfindrBarChart_6a0pyJM$lambda$0(data, modifier4, height3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            Iterator<T> it = data.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            float value = ((BarEntry) it.next()).getValue();
            while (it.hasNext()) {
                value = Math.max(value, ((BarEntry) it.next()).getValue());
            }
            float maxVal = value * 1.12f;
            Arrangement.Horizontal m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
            Alignment.Vertical bottom = Alignment.INSTANCE.getBottom();
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(SizeKt.m848height3ABfNKs(modifier4, height3), 0.0f, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 13, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m686spacedBy0680j_4, bottom, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier modifier5 = modifier4;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((432 << 3) & 112) << 6) & 896) | 6;
            float height4 = height3;
            String str = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer2);
            MeasurePolicy measurePolicy = rowMeasurePolicy;
            Updater.m4372setimpl(m4364constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            int i6 = 0;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i7 = ((432 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            Composer composer = $composer2;
            ComposerKt.sourceInformationMarkerStart(composer, -1310475866, "C:BarChart.kt#7crva5");
            composer.startReplaceGroup(96274072);
            ComposerKt.sourceInformation(composer, "*40@1295L1445");
            List<BarEntry> list = data;
            int i8 = 0;
            for (BarEntry barEntry : list) {
                float value2 = barEntry.getValue() / maxVal;
                float value3 = barEntry.getValue();
                Iterator<T> it2 = data.iterator();
                if (!it2.hasNext()) {
                    throw new NoSuchElementException();
                }
                Composer composer2 = composer;
                float value4 = ((BarEntry) it2.next()).getValue();
                while (it2.hasNext()) {
                    value4 = Math.max(value4, ((BarEntry) it2.next()).getValue());
                    list = list;
                }
                Iterable iterable = list;
                final boolean z = value3 == value4;
                float f2 = (height4 - 26.0f) * value2;
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical bottom2 = Arrangement.INSTANCE.getBottom();
                Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                int i9 = i8;
                int i10 = i6;
                ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy measurePolicy2 = measurePolicy;
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(bottom2, centerHorizontally, composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, weight$default);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i11 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, str);
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    function02 = constructor2;
                    composer2.createNode(function02);
                } else {
                    function02 = constructor2;
                    composer2.useNode();
                }
                Composer m4364constructorimpl2 = Updater.m4364constructorimpl(composer2);
                String str2 = str;
                Updater.m4372setimpl(m4364constructorimpl2, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i12 = (i11 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i13 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, 70597116, "C45@1509L336,59@2081L358,54@1863L595,70@2476L250:BarChart.kt#7crva5");
                TextKt.m3069TextNvy7gAk(String.valueOf(barEntry.getValue()), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(7), 7, null), z ? ColorKt.getGold() : ColorKt.getTextLo(), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 14180400, 0, 261928);
                Modifier clip = ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7908constructorimpl(f2)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(6)));
                ComposerKt.sourceInformationMarkerStart(composer2, 833578764, "CC(remember):BarChart.kt#9igjgp");
                boolean changed = composer2.changed(z);
                Object rememberedValue = composer2.rememberedValue();
                if (!changed && rememberedValue != Composer.INSTANCE.getEmpty()) {
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    BoxKt.Box(DrawModifierKt.drawBehind(clip, (Function1) rememberedValue), composer2, 0);
                    TextKt.m3069TextNvy7gAk(barEntry.getLabel(), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(7), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(10.5d), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 12607920, 0, 261992);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    measurePolicy = measurePolicy2;
                    composer = composer2;
                    list = iterable;
                    i8 = i9;
                    i6 = i10;
                    str = str2;
                }
                rememberedValue = new Function1() { // from class: com.example.wayfindr.ui.components.BarChartKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return BarChartKt.WayfindrBarChart_6a0pyJM$lambda$7$lambda$6$lambda$5$lambda$4$lambda$3(z, (DrawScope) obj);
                    }
                };
                composer2.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                BoxKt.Box(DrawModifierKt.drawBehind(clip, (Function1) rememberedValue), composer2, 0);
                TextKt.m3069TextNvy7gAk(barEntry.getLabel(), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(7), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(10.5d), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 12607920, 0, 261992);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                measurePolicy = measurePolicy2;
                composer = composer2;
                list = iterable;
                i8 = i9;
                i6 = i10;
                str = str2;
            }
            Composer composer3 = composer;
            composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            height2 = height4;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            height2 = f;
        }
        ScopeUpdateScope endRestartGroup2 = $composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.BarChartKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BarChartKt.WayfindrBarChart_6a0pyJM$lambda$8(data, modifier3, height2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit WayfindrBarChart_6a0pyJM$lambda$7$lambda$6$lambda$5$lambda$4$lambda$3(boolean $best, DrawScope drawBehind) {
        Intrinsics.checkNotNullParameter(drawBehind, "$this$drawBehind");
        if ($best) {
            DrawScope.m5661drawRectAsUm42w$default(drawBehind, Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(ColorKt.getGold()), Color.m5084boximpl(ColorKt.getGoldDeep())}), 0.0f, 0.0f, 0, 14, (Object) null), 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        } else {
            DrawScope.m5662drawRectnJ9OG0$default(drawBehind, ColorKt.getSurf3(), 0L, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        }
        return Unit.INSTANCE;
    }
}
