package com.example.wayfindr.ui.components;

import android.content.Context;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.app.NotificationCompat;
import com.example.wayfindr.data.UpdateChecker;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UpdatePrompt.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¨\u0006\u0006²\u0006\f\u0010\u0007\u001a\u0004\u0018\u00010\bX\u008a\u008e\u0002²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\u000b\u001a\u00020\nX\u008a\u008e\u0002²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\nX\u008a\u008e\u0002"}, d2 = {"UpdatePrompt", "", "(Landroidx/compose/runtime/Composer;I)V", "prettifyNotes", "", "md", "app", "release", "Lcom/example/wayfindr/data/UpdateChecker$ReleaseInfo;", "dismissed", "", "downloading", NotificationCompat.CATEGORY_PROGRESS, "", "failed"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class UpdatePromptKt {
    static final Unit UpdatePrompt$lambda$16(int i, Composer composer, int i2) {
        UpdatePrompt(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$17(int i, Composer composer, int i2) {
        UpdatePrompt(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$32(int i, Composer composer, int i2) {
        UpdatePrompt(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void UpdatePrompt(Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1877154107);
        ComposerKt.sourceInformation($composer3, "C(UpdatePrompt)31@1315L7,32@1339L24,34@1384L61,35@1467L34,36@1525L34,37@1580L36,38@1635L34,43@1936L210,43@1915L231,54@2249L38,115@4595L1318,146@5939L230,66@2626L1943,53@2209L3967:UpdatePrompt.kt#7crva5");
        if ($composer3.shouldExecute($changed != 0, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1877154107, $changed, -1, "com.example.wayfindr.ui.components.UpdatePrompt (UpdatePrompt.kt:30)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, 773894976, "CC(rememberCoroutineScope)N(getContext)600@27430L68:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart($composer3, 683736516, "CC(remember):Effects.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object createCompositionCoroutineScope = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer3);
                $composer3.updateRememberedValue(createCompositionCoroutineScope);
                rememberedValue = createCompositionCoroutineScope;
            }
            final CoroutineScope scope = (CoroutineScope) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 58836824, "CC(remember):UpdatePrompt.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default);
                rememberedValue2 = mutableStateOf$default;
            }
            MutableState release$delegate = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 58839453, "CC(remember):UpdatePrompt.kt#9igjgp");
            Object rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default2);
                rememberedValue3 = mutableStateOf$default2;
            }
            final MutableState dismissed$delegate = (MutableState) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 58841309, "CC(remember):UpdatePrompt.kt#9igjgp");
            Object rememberedValue4 = $composer3.rememberedValue();
            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default3);
                rememberedValue4 = mutableStateOf$default3;
            }
            final MutableState downloading$delegate = (MutableState) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 58843071, "CC(remember):UpdatePrompt.kt#9igjgp");
            Object rememberedValue5 = $composer3.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object mutableFloatStateOf = PrimitiveSnapshotStateKt.mutableFloatStateOf(0.0f);
                $composer3.updateRememberedValue(mutableFloatStateOf);
                rememberedValue5 = mutableFloatStateOf;
            }
            final MutableFloatState progress$delegate = (MutableFloatState) rememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 58844829, "CC(remember):UpdatePrompt.kt#9igjgp");
            Object rememberedValue6 = $composer3.rememberedValue();
            if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default4);
                rememberedValue6 = mutableStateOf$default4;
            }
            final MutableState failed$delegate = (MutableState) rememberedValue6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 58854637, "CC(remember):UpdatePrompt.kt#9igjgp");
            boolean changedInstance = $composer3.changedInstance(context);
            Object rememberedValue7 = $composer3.rememberedValue();
            if (changedInstance || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new UpdatePromptKt$UpdatePrompt$1$1(context, release$delegate, null);
                $composer3.updateRememberedValue(obj);
                rememberedValue7 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue7, $composer3, 6);
            final UpdateChecker.ReleaseInfo r = UpdatePrompt$lambda$1(release$delegate);
            if (r == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return UpdatePromptKt.UpdatePrompt$lambda$16($changed, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            if (UpdatePrompt$lambda$4(dismissed$delegate)) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup2 = $composer3.endRestartGroup();
                if (endRestartGroup2 != null) {
                    endRestartGroup2.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj2, Object obj3) {
                            return UpdatePromptKt.UpdatePrompt$lambda$17($changed, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            long surf1 = ColorKt.getSurf1();
            long textHi = ColorKt.getTextHi();
            long textMid = ColorKt.getTextMid();
            ComposerKt.sourceInformationMarkerStart($composer3, 58864481, "CC(remember):UpdatePrompt.kt#9igjgp");
            Object rememberedValue8 = $composer3.rememberedValue();
            if (rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UpdatePromptKt.UpdatePrompt$lambda$19$lambda$18(MutableState.this, dismissed$delegate);
                    }
                };
                $composer3.updateRememberedValue(obj2);
                rememberedValue8 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.m2063AlertDialogOix01E0((Function0) rememberedValue8, ComposableLambdaKt.rememberComposableLambda(-500268157, true, new Function2() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return UpdatePromptKt.UpdatePrompt$lambda$23(CoroutineScope.this, context, r, downloading$delegate, failed$delegate, progress$delegate, dismissed$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-1807410555, true, new Function2() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return UpdatePromptKt.UpdatePrompt$lambda$26(MutableState.this, dismissed$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), null, ComposableSingletons$UpdatePromptKt.INSTANCE.getLambda$1180414343$app(), ComposableLambdaKt.rememberComposableLambda(-1620640504, true, new Function2() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return UpdatePromptKt.UpdatePrompt$lambda$31(UpdateChecker.ReleaseInfo.this, downloading$delegate, progress$delegate, failed$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer3, 54), null, surf1, 0L, textHi, textMid, 0.0f, null, $composer2, 1772598, 0, 12948);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup3 = $composer2.endRestartGroup();
        if (endRestartGroup3 != null) {
            endRestartGroup3.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return UpdatePromptKt.UpdatePrompt$lambda$32($changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final UpdateChecker.ReleaseInfo UpdatePrompt$lambda$1(MutableState<UpdateChecker.ReleaseInfo> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean UpdatePrompt$lambda$4(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void UpdatePrompt$lambda$5(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean UpdatePrompt$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void UpdatePrompt$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float UpdatePrompt$lambda$10(MutableFloatState $progress$delegate) {
        return $progress$delegate.getFloatValue();
    }

    private static final boolean UpdatePrompt$lambda$13(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void UpdatePrompt$lambda$14(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit UpdatePrompt$lambda$19$lambda$18(MutableState $downloading$delegate, MutableState $dismissed$delegate) {
        if (!UpdatePrompt$lambda$7($downloading$delegate)) {
            UpdatePrompt$lambda$5($dismissed$delegate, true);
        }
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$31(UpdateChecker.ReleaseInfo $r, MutableState $downloading$delegate, MutableFloatState $progress$delegate, MutableState $failed$delegate, Composer $composer, int $changed) {
        Function0 function0;
        Composer composer;
        final MutableFloatState mutableFloatState;
        Function0 function02;
        ComposerKt.sourceInformation($composer, "C67@2640L1919:UpdatePrompt.kt#7crva5");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1620640504, $changed, -1, "com.example.wayfindr.ui.components.UpdatePrompt.<anonymous> (UpdatePrompt.kt:67)");
            }
            Arrangement.Vertical m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4, Alignment.INSTANCE.getStart(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 195542144, "C68@2716L244:UpdatePrompt.kt#7crva5");
            TextKt.m3069TextNvy7gAk("WayfindR " + $r.getVersionName(), null, ColorKt.getGold(), null, TextUnitKt.getSp(13), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 261930);
            Composer composer2 = $composer;
            if (StringsKt.isBlank($r.getNotes())) {
                composer = $composer;
                composer2.startReplaceGroup(192790180);
            } else {
                composer2.startReplaceGroup(195789399);
                ComposerKt.sourceInformation(composer2, "79@3162L21,76@3025L581");
                composer = $composer;
                Modifier verticalScroll$default = ScrollKt.verticalScroll$default(SizeKt.m850heightInVpY3zN4$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(280), 1, null), ScrollKt.rememberScrollState(0, composer2, 0, 1), false, null, false, 14, null);
                Arrangement.Vertical m686spacedBy0680j_42 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(2));
                ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_42, Alignment.INSTANCE.getStart(), composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer2, verticalScroll$default);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
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
                Updater.m4372setimpl(m4364constructorimpl2, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i6 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer2, 2046292032, "C82@3308L276:UpdatePrompt.kt#7crva5");
                TextKt.m3069TextNvy7gAk(prettifyNotes($r.getNotes()), null, ColorKt.getTextMid(), null, TextUnitKt.getSp(13), null, null, TypeKt.getGeistFamily(), 0L, null, null, TextUnitKt.getSp(19), 0, false, 0, 0, null, null, composer2, 24576, 48, 259946);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
            }
            composer2.endReplaceGroup();
            if (UpdatePrompt$lambda$7($downloading$delegate)) {
                composer2.startReplaceGroup(196437547);
                ComposerKt.sourceInformation(composer2, "93@3740L12,92@3680L241,98@3942L267");
                ComposerKt.sourceInformationMarkerStart(composer2, 6338762, "CC(remember):UpdatePrompt.kt#9igjgp");
                Object rememberedValue = composer2.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    mutableFloatState = $progress$delegate;
                    Object obj = new Function0() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            float UpdatePrompt$lambda$10;
                            UpdatePrompt$lambda$10 = UpdatePromptKt.UpdatePrompt$lambda$10(MutableFloatState.this);
                            return Float.valueOf(UpdatePrompt$lambda$10);
                        }
                    };
                    composer2.updateRememberedValue(obj);
                    rememberedValue = obj;
                } else {
                    mutableFloatState = $progress$delegate;
                }
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ProgressIndicatorKt.m2732LinearProgressIndicatorGJbTh5U((Function0) rememberedValue, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.getGold(), ColorKt.getLine(), 0, 0.0f, null, composer2, 54, 112);
                TextKt.m3069TextNvy7gAk(UpdatePrompt$lambda$10(mutableFloatState) > 0.0f ? "İndiriliyor… " + ((int) (UpdatePrompt$lambda$10(mutableFloatState) * 100.0f)) + "%" : "İndiriliyor…", null, ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24576, 0, 261994);
                composer2 = composer2;
            } else {
                composer2.startReplaceGroup(192790180);
            }
            composer2.endReplaceGroup();
            if (UpdatePrompt$lambda$13($failed$delegate)) {
                composer2.startReplaceGroup(197022083);
                ComposerKt.sourceInformation(composer2, "106@4278L249");
                TextKt.m3069TextNvy7gAk("İndirme başarısız oldu — bunun yerine Releases sayfası açılıyor.", null, ColorKt.getWarn(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 24582, 0, 261994);
            } else {
                composer2.startReplaceGroup(192790180);
            }
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$23(final CoroutineScope $scope, final Context $context, final UpdateChecker.ReleaseInfo $r, final MutableState $downloading$delegate, final MutableState $failed$delegate, final MutableFloatState $progress$delegate, final MutableState $dismissed$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C118@4687L913,137@5616L287,116@4609L1294:UpdatePrompt.kt#7crva5");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-500268157, $changed, -1, "com.example.wayfindr.ui.components.UpdatePrompt.<anonymous> (UpdatePrompt.kt:116)");
            }
            boolean z = !UpdatePrompt$lambda$7($downloading$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 278642132, "CC(remember):UpdatePrompt.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($scope) | $composer.changedInstance($context) | $composer.changed($r);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UpdatePromptKt.UpdatePrompt$lambda$23$lambda$21$lambda$20(CoroutineScope.this, $context, $downloading$delegate, $failed$delegate, $progress$delegate, $r, $dismissed$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) rememberedValue, null, z, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(-1212844928, true, new Function3() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return UpdatePromptKt.UpdatePrompt$lambda$23$lambda$22(MutableState.this, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$23$lambda$21$lambda$20(CoroutineScope $scope, Context $context, MutableState $downloading$delegate, MutableState $failed$delegate, MutableFloatState $progress$delegate, UpdateChecker.ReleaseInfo $r, MutableState $dismissed$delegate) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new UpdatePromptKt$UpdatePrompt$4$1$1$1($context, $downloading$delegate, $failed$delegate, $progress$delegate, $r, $dismissed$delegate, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$23$lambda$22(MutableState $downloading$delegate, RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C138@5634L255:UpdatePrompt.kt#7crva5");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1212844928, $changed, -1, "com.example.wayfindr.ui.components.UpdatePrompt.<anonymous>.<anonymous> (UpdatePrompt.kt:138)");
            }
            TextKt.m3069TextNvy7gAk(UpdatePrompt$lambda$7($downloading$delegate) ? "İndiriliyor…" : "Güncelle", null, UpdatePrompt$lambda$7($downloading$delegate) ? ColorKt.getTextLo() : ColorKt.getGold(), null, 0L, null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1572864, 0, 261946);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$26(MutableState $downloading$delegate, final MutableState $dismissed$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C149@6031L20,147@5953L206:UpdatePrompt.kt#7crva5");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1807410555, $changed, -1, "com.example.wayfindr.ui.components.UpdatePrompt.<anonymous> (UpdatePrompt.kt:147)");
            }
            boolean UpdatePrompt$lambda$7 = true ^ UpdatePrompt$lambda$7($downloading$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1754752807, "CC(remember):UpdatePrompt.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return UpdatePromptKt.UpdatePrompt$lambda$26$lambda$25$lambda$24(MutableState.this);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) rememberedValue, null, UpdatePrompt$lambda$7, null, null, null, null, null, null, ComposableSingletons$UpdatePromptKt.INSTANCE.getLambda$1774979970$app(), $composer, 805306374, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit UpdatePrompt$lambda$26$lambda$25$lambda$24(MutableState $dismissed$delegate) {
        UpdatePrompt$lambda$5($dismissed$delegate, true);
        return Unit.INSTANCE;
    }

    static final CharSequence prettifyNotes$lambda$33(String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        return StringsKt.replace$default(new Regex("^\\s*[-*] ").replace(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.removePrefix(StringsKt.trimEnd((CharSequence) line).toString(), (CharSequence) "### "), (CharSequence) "## "), (CharSequence) "# "), "• "), "**", "", false, 4, (Object) null);
    }

    private static final String prettifyNotes(String md) {
        return StringsKt.trim((CharSequence) SequencesKt.joinToString$default(StringsKt.lineSequence(md), "\n", null, null, 0, null, new Function1() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UpdatePromptKt.prettifyNotes$lambda$33((String) obj);
            }
        }, 30, null)).toString();
    }
}
