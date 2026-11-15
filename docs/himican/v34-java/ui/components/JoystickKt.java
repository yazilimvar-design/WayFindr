package com.example.wayfindr.ui.components;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.OffsetKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.window.core.layout.WindowSizeClass;
import com.example.wayfindr.ui.theme.ColorKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Joystick.kt */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a7\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0004\b\t\u0010\n¨\u0006\u000b²\u0006\n\u0010\f\u001a\u00020\u0006X\u008a\u008e\u0002"}, d2 = {"Joystick", "", "size", "Landroidx/compose/ui/unit/Dp;", "onMove", "Lkotlin/Function1;", "Landroidx/compose/ui/geometry/Offset;", "modifier", "Landroidx/compose/ui/Modifier;", "Joystick-Kz89ssw", "(FLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app", "knobOffset"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class JoystickKt {
    static final Unit Joystick_Kz89ssw$lambda$13(float f, Function1 function1, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m8355JoystickKz89ssw(f, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:58:0x0300, code lost:
    
        if (r7 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L94;
     */
    /* renamed from: Joystick-Kz89ssw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m8355JoystickKz89ssw(float size, final Function1<? super Offset, Unit> onMove, Modifier modifier, Composer $composer, final int $changed, final int i) {
        float f;
        Modifier modifier2;
        Composer $composer2;
        final float size2;
        final Modifier modifier3;
        Brush m5057radialGradientP_VxKs;
        Function0 function0;
        Intrinsics.checkNotNullParameter(onMove, "onMove");
        Composer $composer3 = $composer.startRestartGroup(407315469);
        ComposerKt.sourceInformation($composer3, "C(Joystick)N(size:c#ui.unit.Dp,onMove,modifier)31@1038L40,32@1095L40,45@1515L1538,34@1141L3374:Joystick.kt#7crva5");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            f = size;
        } else if (($changed & 6) == 0) {
            f = size;
            $dirty |= $composer3.changed(f) ? 4 : 2;
        } else {
            f = size;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onMove) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if ($composer3.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            final float size3 = i2 != 0 ? Dp.m7908constructorimpl(132) : f;
            if (i3 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(407315469, $dirty2, -1, "com.example.wayfindr.ui.components.Joystick (Joystick.kt:30)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 588146293, "CC(remember):Joystick.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4838boximpl(Offset.INSTANCE.m4865getZeroF1C5BW0()), null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default);
                rememberedValue = mutableStateOf$default;
            }
            final MutableState knobOffset$delegate = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 588148117, "CC(remember):Joystick.kt#9igjgp");
            boolean z = ($dirty2 & 14) == 4;
            Object rememberedValue2 = $composer3.rememberedValue();
            if (z || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object valueOf = Float.valueOf((size3 / 2.0f) - 24.0f);
                $composer3.updateRememberedValue(valueOf);
                rememberedValue2 = valueOf;
            }
            float maxPx = ((Number) rememberedValue2).floatValue();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier clip = ClipKt.clip(SizeKt.m862size3ABfNKs(modifier2, size3), RoundedCornerShapeKt.getCircleShape());
            m5057radialGradientP_VxKs = Brush.INSTANCE.m5057radialGradientP_VxKs((Pair<Float, Color>[]) new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m5084boximpl(ColorKt.getSurf2())), TuplesKt.to(Float.valueOf(1.0f), Color.m5084boximpl(ColorKt.getSurf1()))}, (r12 & 2) != 0 ? Offset.INSTANCE.m4864getUnspecifiedF1C5BW0() : 0L, (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (r12 & 8) != 0 ? TileMode.INSTANCE.m5489getClamp3opZhB0() : 0);
            Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(BackgroundKt.background$default(clip, m5057radialGradientP_VxKs, null, 0.0f, 6, null), Dp.m7908constructorimpl(1), ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
            Unit unit = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 588163055, "CC(remember):Joystick.kt#9igjgp");
            boolean changed = (($dirty2 & 14) == 4) | $composer3.changed(maxPx) | (($dirty2 & 112) == 32);
            Object rememberedValue3 = $composer3.rememberedValue();
            if (changed || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj = (PointerInputEventHandler) new JoystickKt$Joystick$1$1(size3, maxPx, onMove, knobOffset$delegate);
                $composer3.updateRememberedValue(obj);
                rememberedValue3 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(m273borderxT4_qwU, unit, (PointerInputEventHandler) rememberedValue3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            $composer2 = $composer3;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, pointerInput);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function0 = constructor;
                $composer3.createNode(function0);
            } else {
                function0 = constructor;
                $composer3.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer3);
            Updater.m4372setimpl(m4364constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i6 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -871083953, "C83@3131L538,83@3100L569,100@3777L82,101@3870L639,97@3695L814:Joystick.kt#7crva5");
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -1690667347, "CC(remember):Joystick.kt#9igjgp");
            boolean z2 = ($dirty2 & 14) == 4;
            Object rememberedValue4 = $composer3.rememberedValue();
            if (z2) {
            }
            Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.components.JoystickKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return JoystickKt.Joystick_Kz89ssw$lambda$12$lambda$7$lambda$6(size3, (DrawScope) obj3);
                }
            };
            $composer3.updateRememberedValue(obj2);
            rememberedValue4 = obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CanvasKt.Canvas(fillMaxSize$default, (Function1) rememberedValue4, $composer3, 6);
            Modifier m862size3ABfNKs = SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(44));
            ComposerKt.sourceInformationMarkerStart($composer3, -1690647131, "CC(remember):Joystick.kt#9igjgp");
            float size4 = size3;
            Object rememberedValue5 = $composer3.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                rememberedValue5 = new Function1() { // from class: com.example.wayfindr.ui.components.JoystickKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return JoystickKt.Joystick_Kz89ssw$lambda$12$lambda$9$lambda$8(MutableState.this, (Density) obj3);
                    }
                };
                $composer3.updateRememberedValue(rememberedValue5);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier offset = OffsetKt.offset(m862size3ABfNKs, (Function1) rememberedValue5);
            ComposerKt.sourceInformationMarkerStart($composer3, -1690643598, "CC(remember):Joystick.kt#9igjgp");
            Object rememberedValue6 = $composer3.rememberedValue();
            if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = new Function1() { // from class: com.example.wayfindr.ui.components.JoystickKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return JoystickKt.Joystick_Kz89ssw$lambda$12$lambda$11$lambda$10((DrawScope) obj4);
                    }
                };
                $composer3.updateRememberedValue(obj3);
                rememberedValue6 = obj3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            CanvasKt.Canvas(offset, (Function1) rememberedValue6, $composer3, 48);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            size2 = size4;
            modifier3 = modifier2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            size2 = f;
            modifier3 = modifier2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.JoystickKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return JoystickKt.Joystick_Kz89ssw$lambda$13(size2, onMove, modifier3, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final long Joystick_Kz89ssw$lambda$1(MutableState<Offset> mutableState) {
        return mutableState.getValue().m4859unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Joystick_Kz89ssw$lambda$2(MutableState<Offset> mutableState, long j) {
        mutableState.setValue(Offset.m4838boximpl(j));
    }

    static final Unit Joystick_Kz89ssw$lambda$12$lambda$7$lambda$6(float $size, DrawScope $this$Canvas) {
        DrawScope Canvas = $this$Canvas;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float s = Canvas.mo403toPx0680j_4($size);
        Iterable<Pair> guides = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to(Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.5f) << 32) | (Float.floatToRawIntBits(s * 0.14f) & 4294967295L))), Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.5f) << 32) | (Float.floatToRawIntBits(s * 0.32f) & 4294967295L)))), TuplesKt.to(Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.5f) << 32) | (Float.floatToRawIntBits(s * 0.86f) & 4294967295L))), Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.5f) << 32) | (Float.floatToRawIntBits(s * 0.68f) & 4294967295L)))), TuplesKt.to(Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.14f) << 32) | (Float.floatToRawIntBits(s * 0.5f) & 4294967295L))), Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.32f) << 32) | (Float.floatToRawIntBits(s * 0.5f) & 4294967295L)))), TuplesKt.to(Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.86f) << 32) | (Float.floatToRawIntBits(s * 0.5f) & 4294967295L))), Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(s * 0.68f) << 32) | (Float.floatToRawIntBits(s * 0.5f) & 4294967295L))))});
        for (Pair pair : guides) {
            DrawScope.m5654drawLineNGM6Ib0$default(Canvas, ColorKt.getLine3(), ((Offset) pair.component1()).m4859unboximpl(), ((Offset) pair.component2()).m4859unboximpl(), 2.0f, StrokeCap.INSTANCE.m5469getRoundKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
            Canvas = $this$Canvas;
        }
        return Unit.INSTANCE;
    }

    static final IntOffset Joystick_Kz89ssw$lambda$12$lambda$9$lambda$8(MutableState $knobOffset$delegate, Density offset) {
        Intrinsics.checkNotNullParameter(offset, "$this$offset");
        return IntOffset.m8027boximpl(IntOffset.m8030constructorimpl((((int) Float.intBitsToFloat((int) (Joystick_Kz89ssw$lambda$1($knobOffset$delegate) >> 32))) << 32) | (4294967295L & ((int) Float.intBitsToFloat((int) (Joystick_Kz89ssw$lambda$1($knobOffset$delegate) & 4294967295L))))));
    }

    static final Unit Joystick_Kz89ssw$lambda$12$lambda$11$lambda$10(DrawScope Canvas) {
        Brush m5054linearGradientmHitzGk;
        long m5092copywmQWz5c;
        long m5092copywmQWz5c2;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float r = Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(22));
        m5054linearGradientmHitzGk = Brush.INSTANCE.m5054linearGradientmHitzGk((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(ColorKt.getGoldBright()), Color.m5084boximpl(ColorKt.getGoldDeep())}), (r14 & 2) != 0 ? Offset.INSTANCE.m4865getZeroF1C5BW0() : Offset.m4841constructorimpl((Float.floatToRawIntBits(r * 0.4f) << 32) | (Float.floatToRawIntBits(0.4f * r) & 4294967295L)), (r14 & 4) != 0 ? Offset.INSTANCE.m4863getInfiniteF1C5BW0() : Offset.m4841constructorimpl((Float.floatToRawIntBits(r * 1.6f) << 32) | (Float.floatToRawIntBits(1.6f * r) & 4294967295L)), (r14 & 8) != 0 ? TileMode.INSTANCE.m5489getClamp3opZhB0() : 0);
        DrawScope.m5648drawCircleV9BoPsw$default(Canvas, m5054linearGradientmHitzGk, r, 0L, 0.0f, null, null, 0, 124, null);
        m5092copywmQWz5c = Color.m5092copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r3) : 0.4f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(Color.INSTANCE.m5131getWhite0d7_KjU()) : 0.0f);
        DrawScope.m5649drawCircleVaOC9Bg$default(Canvas, m5092copywmQWz5c, r, 0L, 0.0f, new Stroke(Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(1)), 0.0f, 0, 0, null, 30, null), null, 0, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, null);
        m5092copywmQWz5c2 = Color.m5092copywmQWz5c(r3, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r3) : 0.5f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r3) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r3) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(androidx.compose.ui.graphics.ColorKt.Color(4279899138L)) : 0.0f);
        DrawScope.m5649drawCircleVaOC9Bg$default(Canvas, m5092copywmQWz5c2, Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(6)), 0L, 0.0f, null, null, 0, 124, null);
        return Unit.INSTANCE;
    }
}
