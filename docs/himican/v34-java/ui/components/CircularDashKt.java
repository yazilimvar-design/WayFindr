package com.example.wayfindr.ui.components;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.MenuKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.location.LocationRequestCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.window.core.layout.WindowSizeClass;
import com.example.wayfindr.ui.theme.TypeKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: CircularDash.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a]\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001a\f\u0010\u0012\u001a\u00020\u0001*\u00020\u0013H\u0002\u001a\f\u0010\u0014\u001a\u00020\u0001*\u00020\u0013H\u0002¨\u0006\u0015²\u0006\n\u0010\u0016\u001a\u00020\u0007X\u008a\u0084\u0002²\u0006\n\u0010\u0017\u001a\u00020\u0007X\u008a\u0084\u0002"}, d2 = {"CircularDash", "", "size", "Landroidx/compose/ui/unit/Dp;", "pan", "Landroidx/compose/ui/geometry/Offset;", "zoom", "", "distance", "", "street", "live", "", "compact", "modifier", "Landroidx/compose/ui/Modifier;", "CircularDash-BrkIAW0", "(FJFLjava/lang/String;Ljava/lang/String;ZZLandroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "drawMapContents", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "drawTickRing", "app", "ringScale", "ringAlpha"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class CircularDashKt {
    static final Unit CircularDash_BrkIAW0$lambda$17(float f, long j, float f2, String str, String str2, boolean z, boolean z2, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m8354CircularDashBrkIAW0(f, j, f2, str, str2, z, z2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x0410, code lost:
    
        if (r3 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L134;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x04d3, code lost:
    
        if (r9 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L152;
     */
    /* renamed from: CircularDash-BrkIAW0, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void m8354CircularDashBrkIAW0(final float size, long pan, float zoom, String distance, String street, boolean live, boolean compact, Modifier modifier, Composer $composer, final int $changed, final int i) {
        final long pan2;
        final float zoom2;
        String street2;
        final boolean live2;
        boolean compact2;
        int i2;
        Composer $composer2;
        final String distance2;
        final long pan3;
        final Modifier modifier2;
        final boolean compact3;
        final String street3;
        final float zoom3;
        Brush m5057radialGradientP_VxKs;
        Function0 function0;
        String street4;
        Function0 function02;
        Composer $composer3 = $composer.startRestartGroup(1133278675);
        ComposerKt.sourceInformation($composer3, "C(CircularDash)N(size:c#ui.unit.Dp,pan:c#ui.geometry.Offset,zoom,distance,street,live,compact,modifier)47@1639L47,48@1727L178,53@1946L176,59@2128L4734:CircularDash.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(size) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            pan2 = pan;
        } else if (($changed & 48) == 0) {
            pan2 = pan;
            $dirty |= $composer3.changed(pan2) ? 32 : 16;
        } else {
            pan2 = pan;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            zoom2 = zoom;
        } else if (($changed & 384) == 0) {
            zoom2 = zoom;
            $dirty |= $composer3.changed(zoom2) ? 256 : 128;
        } else {
            zoom2 = zoom;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(distance) ? 2048 : 1024;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            street2 = street;
        } else if (($changed & 24576) == 0) {
            street2 = street;
            $dirty |= $composer3.changed(street2) ? 16384 : 8192;
        } else {
            street2 = street;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            live2 = live;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            live2 = live;
            $dirty |= $composer3.changed(live2) ? 131072 : 65536;
        } else {
            live2 = live;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            compact2 = compact;
        } else if (($changed & 1572864) == 0) {
            compact2 = compact;
            $dirty |= $composer3.changed(compact2) ? 1048576 : 524288;
        } else {
            compact2 = compact;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty |= 12582912;
            i2 = i9;
        } else if (($changed & 12582912) == 0) {
            i2 = i9;
            $dirty |= $composer3.changed(modifier) ? 8388608 : 4194304;
        } else {
            i2 = i9;
        }
        if ($composer3.shouldExecute(($dirty & 4793491) != 4793490, $dirty & 1)) {
            if (i3 != 0) {
                pan2 = Offset.INSTANCE.m4865getZeroF1C5BW0();
            }
            if (i4 != 0) {
                zoom2 = 1.0f;
            }
            String distance3 = i5 != 0 ? "400 m" : distance;
            if (i6 != 0) {
                street2 = "NH-707, Shimla";
            }
            if (i7 != 0) {
                live2 = true;
            }
            if (i8 != 0) {
                compact2 = false;
            }
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1133278675, $dirty, -1, "com.example.wayfindr.ui.components.CircularDash (CircularDash.kt:45)");
            }
            InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("live-ring", $composer3, 6, 0);
            $composer2 = $composer3;
            final State ringScale$delegate = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.7f, 2.0f, AnimationSpecKt.m170infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(2200, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), "ring-scale", $composer2, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            boolean compact4 = compact2;
            final State ringAlpha$delegate = InfiniteTransitionKt.animateFloat(infiniteTransition, 0.7f, 0.0f, AnimationSpecKt.m170infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(2200, 0, EasingKt.getLinearEasing(), 2, null), null, 0L, 6, null), "ring-alpha", $composer2, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifier4 = modifier3;
            boolean live3 = live2;
            Modifier clip = ClipKt.clip(PaddingKt.m816padding3ABfNKs(BorderKt.m273borderxT4_qwU(PaddingKt.m816padding3ABfNKs(BorderKt.m273borderxT4_qwU(SizeKt.m862size3ABfNKs(modifier3, size), Dp.m7908constructorimpl(2), ColorKt.Color(4280493865L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2)), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6)), RoundedCornerShapeKt.getCircleShape());
            m5057radialGradientP_VxKs = Brush.INSTANCE.m5057radialGradientP_VxKs((Pair<Float, Color>[]) new Pair[]{TuplesKt.to(Float.valueOf(0.0f), Color.m5084boximpl(ColorKt.Color(4279509023L))), TuplesKt.to(Float.valueOf(0.7f), Color.m5084boximpl(ColorKt.Color(4278981651L))), TuplesKt.to(Float.valueOf(1.0f), Color.m5084boximpl(ColorKt.Color(4278651659L)))}, (r12 & 2) != 0 ? Offset.INSTANCE.m4864getUnspecifiedF1C5BW0() : 0L, (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.0f, (r12 & 8) != 0 ? TileMode.INSTANCE.m5489getClamp3opZhB0() : 0);
            Modifier background$default = BackgroundKt.background$default(clip, m5057radialGradientP_VxKs, null, 0.0f, 6, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, background$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i12 = ((48 >> 6) & 112) | 6;
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 2016270602, "C83@2935L235,90@3181L41,80@2844L378,95@3292L38,95@3261L69,113@3896L713,113@3867L742,130@4640L324,180@6435L421,180@6404L452:CircularDash.kt#7crva5");
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 2143250104, "CC(remember):CircularDash.kt#9igjgp");
            int $dirty2 = $dirty;
            boolean z = (($dirty & 896) == 256) | (($dirty & 112) == 32);
            Object rememberedValue = $composer2.rememberedValue();
            if (z) {
            }
            rememberedValue = new Function1() { // from class: com.example.wayfindr.ui.components.CircularDashKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return CircularDashKt.CircularDash_BrkIAW0$lambda$16$lambda$3$lambda$2(pan2, zoom2, (GraphicsLayerScope) obj);
                }
            };
            $composer2.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier graphicsLayer = GraphicsLayerModifierKt.graphicsLayer(fillMaxSize$default, (Function1) rememberedValue);
            ComposerKt.sourceInformationMarkerStart($composer2, 2143257782, "CC(remember):CircularDash.kt#9igjgp");
            Object rememberedValue2 = $composer2.rememberedValue();
            long pan4 = pan2;
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.components.CircularDashKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return CircularDashKt.CircularDash_BrkIAW0$lambda$16$lambda$5$lambda$4((DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                rememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(graphicsLayer, (Function1) rememberedValue2, $composer2, 48);
            Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 2143261331, "CC(remember):CircularDash.kt#9igjgp");
            Object rememberedValue3 = $composer2.rememberedValue();
            if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.components.CircularDashKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return CircularDashKt.CircularDash_BrkIAW0$lambda$16$lambda$7$lambda$6((DrawScope) obj3);
                    }
                };
                $composer2.updateRememberedValue(obj2);
                rememberedValue3 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(fillMaxSize$default2, (Function1) rememberedValue3, $composer2, 54);
            if (live3) {
                $composer2.startReplaceGroup(2016713157);
                ComposerKt.sourceInformation($composer2, "101@3448L345,101@3417L376");
                Modifier fillMaxSize$default3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 2143266630, "CC(remember):CircularDash.kt#9igjgp");
                boolean changed = $composer2.changed(ringAlpha$delegate) | $composer2.changed(ringScale$delegate);
                Object rememberedValue4 = $composer2.rememberedValue();
                if (changed) {
                }
                Object obj3 = new Function1() { // from class: com.example.wayfindr.ui.components.CircularDashKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return CircularDashKt.CircularDash_BrkIAW0$lambda$16$lambda$9$lambda$8(State.this, ringScale$delegate, (DrawScope) obj4);
                    }
                };
                $composer2.updateRememberedValue(obj3);
                rememberedValue4 = obj3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(fillMaxSize$default3, (Function1) rememberedValue4, $composer2, 6);
            } else {
                $composer2.startReplaceGroup(2013323989);
            }
            $composer2.endReplaceGroup();
            Modifier m862size3ABfNKs = SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(26));
            ComposerKt.sourceInformationMarkerStart($composer2, 2143281334, "CC(remember):CircularDash.kt#9igjgp");
            Object rememberedValue5 = $composer2.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function1() { // from class: com.example.wayfindr.ui.components.CircularDashKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return CircularDashKt.CircularDash_BrkIAW0$lambda$16$lambda$12$lambda$11((DrawScope) obj5);
                    }
                };
                $composer2.updateRememberedValue(obj4);
                rememberedValue5 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(m862size3ABfNKs, (Function1) rememberedValue5, $composer2, 54);
            TextKt.m3069TextNvy7gAk("N", PaddingKt.m820paddingqDBjuR0$default(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, Dp.m7908constructorimpl(0.085f * size), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(0.05f * size), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 14156166, 0, 261928);
            if (compact4) {
                street4 = street2;
                $composer2.startReplaceGroup(2013323989);
            } else {
                $composer2.startReplaceGroup(2018378291);
                ComposerKt.sourceInformation($composer2, "144@5099L713,164@5864L493");
                Alignment center2 = Alignment.INSTANCE.getCenter();
                Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(PaddingKt.m820paddingqDBjuR0$default(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopCenter()), 0.0f, Dp.m7908constructorimpl(0.17f * size), 0.0f, 0.0f, 13, null), RoundedCornerShapeKt.getCircleShape()), ColorKt.Color(3490450444L), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getGoldTint2(), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(13), Dp.m7908constructorimpl(5));
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i13 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function02 = constructor2;
                    $composer2.createNode(function02);
                } else {
                    function02 = constructor2;
                    $composer2.useNode();
                }
                Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl2, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i14 = (i13 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i15 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -970188364, "C154@5548L250:CircularDash.kt#7crva5");
                TextKt.m3069TextNvy7gAk(distance3, null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(0.062f * size), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, (($dirty2 >> 9) & 14) | 14156160, 0, 261930);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                street4 = street2;
                TextKt.m3069TextNvy7gAk(street4, SizeKt.m869widthInVpY3zN4$default(PaddingKt.m820paddingqDBjuR0$default(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(0.13f * size), 7, null), 0.0f, Dp.m7908constructorimpl(size * 0.74f), 1, null), com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(0.052f * size), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.1d), null, null, 0L, 0, false, 1, 0, null, null, $composer2, (($dirty2 >> 12) & 14) | 14156160, 24576, 245288);
            }
            $composer2.endReplaceGroup();
            Modifier fillMaxSize$default4 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 2143362290, "CC(remember):CircularDash.kt#9igjgp");
            boolean z2 = ($dirty2 & 14) == 4;
            Object rememberedValue6 = $composer2.rememberedValue();
            if (z2 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function1() { // from class: com.example.wayfindr.ui.components.CircularDashKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj6) {
                        return CircularDashKt.CircularDash_BrkIAW0$lambda$16$lambda$15$lambda$14(size, (DrawScope) obj6);
                    }
                };
                $composer2.updateRememberedValue(obj5);
                rememberedValue6 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(fillMaxSize$default4, (Function1) rememberedValue6, $composer2, 6);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            live2 = live3;
            compact3 = compact4;
            modifier2 = modifier4;
            distance2 = distance3;
            street3 = street4;
            pan3 = pan4;
            zoom3 = zoom2;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            distance2 = distance;
            pan3 = pan2;
            modifier2 = modifier;
            compact3 = compact2;
            street3 = street2;
            zoom3 = zoom2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.CircularDashKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return CircularDashKt.CircularDash_BrkIAW0$lambda$17(size, pan3, zoom3, distance2, street3, live2, compact3, modifier2, $changed, i, (Composer) obj6, ((Integer) obj7).intValue());
                }
            });
        }
    }

    private static final float CircularDash_BrkIAW0$lambda$0(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    private static final float CircularDash_BrkIAW0$lambda$1(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit CircularDash_BrkIAW0$lambda$16$lambda$3$lambda$2(long $pan, float $zoom, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationX(Float.intBitsToFloat((int) ($pan >> 32)));
        graphicsLayer.setTranslationY(Float.intBitsToFloat((int) (4294967295L & $pan)));
        graphicsLayer.setScaleX($zoom);
        graphicsLayer.setScaleY($zoom);
        graphicsLayer.mo5295setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.5f));
        return Unit.INSTANCE;
    }

    static final Unit CircularDash_BrkIAW0$lambda$16$lambda$5$lambda$4(DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        drawMapContents(Canvas);
        return Unit.INSTANCE;
    }

    static final Unit CircularDash_BrkIAW0$lambda$16$lambda$7$lambda$6(DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        drawTickRing(Canvas);
        return Unit.INSTANCE;
    }

    static final Unit CircularDash_BrkIAW0$lambda$16$lambda$9$lambda$8(State $ringAlpha$delegate, State $ringScale$delegate, DrawScope Canvas) {
        long m5092copywmQWz5c;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        m5092copywmQWz5c = Color.m5092copywmQWz5c(r2, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r2) : 0.5f * CircularDash_BrkIAW0$lambda$1($ringAlpha$delegate), (r12 & 2) != 0 ? Color.m5100getRedimpl(r2) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r2) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(com.example.wayfindr.ui.theme.ColorKt.getGold()) : 0.0f);
        DrawScope.m5649drawCircleVaOC9Bg$default(Canvas, m5092copywmQWz5c, Size.m4917getMinDimensionimpl(Canvas.mo5668getSizeNHjbRc()) * 0.12f * CircularDash_BrkIAW0$lambda$0($ringScale$delegate), Canvas.mo5667getCenterF1C5BW0(), 0.0f, new Stroke(Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(2)), 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
        return Unit.INSTANCE;
    }

    static final Unit CircularDash_BrkIAW0$lambda$16$lambda$12$lambda$11(DrawScope Canvas) {
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        float cx = Float.intBitsToFloat((int) (Canvas.mo5668getSizeNHjbRc() >> 32)) / 2.0f;
        float cy = Float.intBitsToFloat((int) (Canvas.mo5668getSizeNHjbRc() & 4294967295L)) / 2.0f;
        float r9 = Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(9));
        DrawScope.m5649drawCircleVaOC9Bg$default(Canvas, ColorKt.Color(4278981651L), r9, Offset.m4841constructorimpl((Float.floatToRawIntBits(cx) << 32) | (Float.floatToRawIntBits(cy) & 4294967295L)), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        DrawScope.m5649drawCircleVaOC9Bg$default(Canvas, com.example.wayfindr.ui.theme.ColorKt.getGold(), r9, Offset.m4841constructorimpl((Float.floatToRawIntBits(cx) << 32) | (Float.floatToRawIntBits(cy) & 4294967295L)), 0.0f, new Stroke(Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl((float) 1.5d)), 0.0f, 0, 0, null, 30, null), null, 0, LocationRequestCompat.QUALITY_LOW_POWER, null);
        Path path = AndroidPath_androidKt.Path();
        path.moveTo(cx, Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(2)));
        path.lineTo(Float.intBitsToFloat((int) (Canvas.mo5668getSizeNHjbRc() >> 32)) - Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(2)), Float.intBitsToFloat((int) (Canvas.mo5668getSizeNHjbRc() & 4294967295L)) - Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(2)));
        path.lineTo(cx, Float.intBitsToFloat((int) (Canvas.mo5668getSizeNHjbRc() & 4294967295L)) - Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(5)));
        path.lineTo(Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(2)), Float.intBitsToFloat((int) (Canvas.mo5668getSizeNHjbRc() & 4294967295L)) - Canvas.mo403toPx0680j_4(Dp.m7908constructorimpl(2)));
        path.close();
        DrawScope.m5658drawPathLG529CI$default(Canvas, path, com.example.wayfindr.ui.theme.ColorKt.getGold(), 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    static final Unit CircularDash_BrkIAW0$lambda$16$lambda$15$lambda$14(float $size, DrawScope Canvas) {
        long m5092copywmQWz5c;
        Brush m5057radialGradientP_VxKs;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        Brush.Companion companion = Brush.INSTANCE;
        Float valueOf = Float.valueOf(0.0f);
        m5092copywmQWz5c = Color.m5092copywmQWz5c(r6, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r6) : 0.07f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r6) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r6) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(Color.INSTANCE.m5131getWhite0d7_KjU()) : 0.0f);
        m5057radialGradientP_VxKs = companion.m5057radialGradientP_VxKs((Pair<Float, Color>[]) new Pair[]{TuplesKt.to(valueOf, Color.m5084boximpl(m5092copywmQWz5c)), TuplesKt.to(Float.valueOf(0.42f), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU()))}, (r12 & 2) != 0 ? Offset.INSTANCE.m4864getUnspecifiedF1C5BW0() : Offset.m4841constructorimpl((Float.floatToRawIntBits(Canvas.mo403toPx0680j_4($size) * 0.3f) << 32) | (Float.floatToRawIntBits(Canvas.mo403toPx0680j_4($size) * 0.08f) & 4294967295L)), (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : 0.6f * Canvas.mo403toPx0680j_4($size), (r12 & 8) != 0 ? TileMode.INSTANCE.m5489getClamp3opZhB0() : 0);
        DrawScope.m5648drawCircleV9BoPsw$default(Canvas, m5057radialGradientP_VxKs, 0.0f, 0L, 0.0f, null, null, 0, WebSocketProtocol.PAYLOAD_SHORT, null);
        return Unit.INSTANCE;
    }

    private static final void drawMapContents(DrawScope $this$drawMapContents) {
        float w = Float.intBitsToFloat((int) ($this$drawMapContents.mo5668getSizeNHjbRc() >> 32));
        float h = Float.intBitsToFloat((int) (4294967295L & $this$drawMapContents.mo5668getSizeNHjbRc()));
        Path Path = AndroidPath_androidKt.Path();
        Path.moveTo(0.0f, h * 0.75f);
        Path.cubicTo(w * 0.2f, h * 0.675f, w * 0.35f, h * 0.825f, w * 0.55f, h * 0.75f);
        Path.cubicTo(w * 0.75f, 0.69f * h, w * 0.875f, 0.79f * h, w, h * 0.75f);
        Path.lineTo(w, h);
        Path.lineTo(0.0f, h);
        Path.close();
        DrawScope.m5658drawPathLG529CI$default($this$drawMapContents, Path, ColorKt.Color(4279245594L), 0.0f, null, null, 0, 60, null);
        Paint roadPaint = AndroidPaint_androidKt.Paint();
        roadPaint.mo4970setColor8_81llA(ColorKt.Color(4280101418L));
        roadPaint.setStrokeWidth(5.0f);
        roadPaint.mo4972setStrokeCapBeK7IIE(StrokeCap.INSTANCE.m5469getRoundKaPHkGw());
        roadPaint.mo4974setStylek9PVt8s(PaintingStyle.INSTANCE.m5376getStrokeTiuSbCo());
        Canvas canvas = $this$drawMapContents.getDrawContext().getCanvas();
        Path Path2 = AndroidPath_androidKt.Path();
        Path2.moveTo(0.1f * w, 0.2f * h);
        Path2.cubicTo(w * 0.3f, h * 0.35f, w * 0.3f, h * 0.6f, w * 0.15f, h * 0.85f);
        canvas.drawPath(Path2, roadPaint);
        Canvas canvas2 = $this$drawMapContents.getDrawContext().getCanvas();
        Path Path3 = AndroidPath_androidKt.Path();
        Path3.moveTo(w * 0.9f, 0.15f * h);
        Path3.cubicTo(w * 0.75f, h * 0.35f, w * 0.825f, h * 0.6f, w * 0.75f, h * 0.9f);
        canvas2.drawPath(Path3, roadPaint);
        Canvas canvas3 = $this$drawMapContents.getDrawContext().getCanvas();
        Path Path4 = AndroidPath_androidKt.Path();
        Path4.moveTo(0.05f * w, h * 0.55f);
        Path4.lineTo(0.4f * w, h * 0.5f);
        Path4.lineTo(w * 0.75f, h * 0.56f);
        Path4.lineTo(w, h * 0.5f);
        canvas3.drawPath(Path4, roadPaint);
        Paint routePaint = AndroidPaint_androidKt.Paint();
        routePaint.mo4970setColor8_81llA(com.example.wayfindr.ui.theme.ColorKt.getGold());
        routePaint.setStrokeWidth(7.0f);
        routePaint.mo4972setStrokeCapBeK7IIE(StrokeCap.INSTANCE.m5469getRoundKaPHkGw());
        routePaint.mo4973setStrokeJoinWw9F2mQ(StrokeJoin.INSTANCE.m5480getRoundLxFBmk8());
        routePaint.mo4974setStylek9PVt8s(PaintingStyle.INSTANCE.m5376getStrokeTiuSbCo());
        Path routePath = AndroidPath_androidKt.Path();
        routePath.moveTo(w * 0.5f, 0.875f * h);
        routePath.cubicTo(w * 0.5f, h * 0.75f, w * 0.35f, h * 0.7f, w * 0.36f, h * 0.55f);
        routePath.cubicTo(w * 0.37f, h * 0.41f, w * 0.55f, h * 0.39f, w * 0.56f, h * 0.26f);
        routePath.cubicTo(w * 0.565f, h * 0.17f, w * 0.5f, h * 0.13f, w * 0.5f, h * 0.06f);
        $this$drawMapContents.getDrawContext().getCanvas().drawPath(routePath, routePaint);
        Canvas canvas4 = $this$drawMapContents.getDrawContext().getCanvas();
        Paint Paint = AndroidPaint_androidKt.Paint();
        Paint.mo4970setColor8_81llA(ColorKt.Color(2164258269L));
        Paint.setStrokeWidth(1.6f);
        Paint.mo4972setStrokeCapBeK7IIE(StrokeCap.INSTANCE.m5469getRoundKaPHkGw());
        Paint.mo4974setStylek9PVt8s(PaintingStyle.INSTANCE.m5376getStrokeTiuSbCo());
        Paint.setPathEffect(PathEffect.Companion.dashPathEffect$default(PathEffect.INSTANCE, new float[]{2.0f, 7.0f}, 0.0f, 2, null));
        Unit unit = Unit.INSTANCE;
        canvas4.drawPath(routePath, Paint);
    }

    private static final void drawTickRing(DrawScope $this$drawTickRing) {
        long m5131getWhite0d7_KjU;
        int i;
        Object obj;
        float f;
        long m5092copywmQWz5c;
        char c = ' ';
        float f2 = 2.0f;
        float cx = Float.intBitsToFloat((int) ($this$drawTickRing.mo5668getSizeNHjbRc() >> 32)) / 2.0f;
        long j = 4294967295L;
        float cy = Float.intBitsToFloat((int) ($this$drawTickRing.mo5668getSizeNHjbRc() & 4294967295L)) / 2.0f;
        int i2 = 0;
        while (i2 < 60) {
            float angle = (((i2 / 60.0f) * f2) * 3.1415927f) - 1.5707964f;
            boolean major = i2 % 5 == 0;
            float f3 = f2;
            int i3 = i2;
            float r1 = (major ? 0.9f : 0.93f) * (Float.intBitsToFloat((int) ($this$drawTickRing.mo5668getSizeNHjbRc() >> c)) / f3);
            float r2 = (Float.intBitsToFloat((int) ($this$drawTickRing.mo5668getSizeNHjbRc() >> c)) / f3) * 0.96f;
            if (major) {
                m5131getWhite0d7_KjU = com.example.wayfindr.ui.theme.ColorKt.getGold();
                i = 14;
                obj = null;
                f = 0.45f;
            } else {
                m5131getWhite0d7_KjU = Color.INSTANCE.m5131getWhite0d7_KjU();
                i = 14;
                obj = null;
                f = 0.13f;
            }
            m5092copywmQWz5c = Color.m5092copywmQWz5c(m5131getWhite0d7_KjU, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(m5131getWhite0d7_KjU) : f, (r12 & 2) != 0 ? Color.m5100getRedimpl(m5131getWhite0d7_KjU) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(m5131getWhite0d7_KjU) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(m5131getWhite0d7_KjU) : 0.0f);
            char c2 = c;
            float cx2 = cx;
            long j2 = j;
            DrawScope.m5654drawLineNGM6Ib0$default($this$drawTickRing, m5092copywmQWz5c, Offset.m4841constructorimpl((Float.floatToRawIntBits((((float) Math.cos(angle)) * r1) + cx) << c2) | (Float.floatToRawIntBits((((float) Math.sin(angle)) * r1) + cy) & j2)), Offset.m4841constructorimpl((Float.floatToRawIntBits(cx2 + (((float) Math.cos(angle)) * r2)) << c2) | (Float.floatToRawIntBits((((float) Math.sin(angle)) * r2) + cy) & j2)), major ? 1.4f : 0.8f, StrokeCap.INSTANCE.m5468getButtKaPHkGw(), null, 0.0f, null, 0, WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND, null);
            i2 = i3 + 1;
            f2 = f3;
            c = c2;
            cx = cx2;
            j = j2;
        }
    }
}
