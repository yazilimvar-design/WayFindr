package com.example.wayfindr.ui.components;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.ChevronLeftKt;
import androidx.compose.material.icons.outlined.ChevronRightKt;
import androidx.compose.material3.IconKt;
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
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: WayfindrComponents.kt */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a]\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\r0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001aW\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00152\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\u0011H\u0007¢\u0006\u0002\u0010'\u001aO\u0010(\u001a\u00020\r2\u0006\u0010 \u001a\u00020!2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\u00152\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u00132\b\b\u0002\u0010)\u001a\u00020\u00112\n\b\u0002\u0010*\u001a\u0004\u0018\u00010+H\u0007¢\u0006\u0004\b,\u0010-\u001a?\u0010.\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u00020\u00112\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u00102\u001a)\u00103\u001a\u00020\r2\u0006\u00104\u001a\u00020\u00112\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0\u0017H\u0007¢\u0006\u0002\u00106\u001aA\u00107\u001a\u00020\r2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001f092\u0006\u0010:\u001a\u00020\u001f2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\r0\u00172\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010<\u001a\u0017\u0010=\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010>\u001a\u001f\u0010?\u001a\u00020\r2\u0006\u0010@\u001a\u00020\u001f2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010A\u001a\u007f\u0010B\u001a\u00020\r2\u0006\u0010C\u001a\u00020\u001f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010D\u001a\u00020E2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010G\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010H\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010I\u001a\u00020\u00112\b\b\u0002\u0010J\u001a\u00020\u00112\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0015H\u0007¢\u0006\u0002\u0010K\u001an\u0010L\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010C\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010M\u001a\u0004\u0018\u00010\u001f2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010O\u001a\u00020\u00112\u0010\b\u0002\u0010P\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00152\u0015\b\u0002\u0010Q\u001a\u000f\u0012\u0004\u0012\u00020\r\u0018\u00010\u0015¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010R\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0003\"\u0011\u0010\b\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0003\"\u0011\u0010\n\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0003¨\u0006S"}, d2 = {"CardShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "getCardShape", "()Landroidx/compose/foundation/shape/RoundedCornerShape;", "BtnShape", "getBtnShape", "InputShape", "getInputShape", "ChipShape", "getChipShape", "IconBtnShape", "getIconBtnShape", "WayfindrCard", "", "modifier", "Landroidx/compose/ui/Modifier;", "glow", "", "padding", "Landroidx/compose/ui/unit/Dp;", "onClick", "Lkotlin/Function0;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "WayfindrCard-TN_CM5M", "(Landroidx/compose/ui/Modifier;ZFLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "WayfindrBtn", "label", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "variant", "Lcom/example/wayfindr/ui/components/BtnVariant;", "size", "Lcom/example/wayfindr/ui/components/BtnSize;", "enabled", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/vector/ImageVector;Lcom/example/wayfindr/ui/components/BtnVariant;Lcom/example/wayfindr/ui/components/BtnSize;ZLandroidx/compose/runtime/Composer;II)V", "WayfindrIconBtn", "active", "tint", "Landroidx/compose/ui/graphics/Color;", "WayfindrIconBtn-N59uMVk", "(Landroidx/compose/ui/graphics/vector/ImageVector;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;FZLandroidx/compose/ui/graphics/Color;Landroidx/compose/runtime/Composer;II)V", "WayfindrChip", "tone", "Lcom/example/wayfindr/ui/components/ChipTone;", "dot", "(Ljava/lang/String;Lcom/example/wayfindr/ui/components/ChipTone;ZLandroidx/compose/ui/graphics/vector/ImageVector;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "WayfindrToggle", DebugKt.DEBUG_PROPERTY_VALUE_ON, "onChange", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "WayfindrSegmented", "options", "", "selected", "onSelect", "(Ljava/util/List;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "WayfindrDivider", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Eyebrow", "text", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "WayfindrRow", "title", "iconTone", "Lcom/example/wayfindr/ui/components/IconTone;", "sub", "right", "rightSub", "accentRight", "trailingIcon", "(Ljava/lang/String;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/vector/ImageVector;Lcom/example/wayfindr/ui/components/IconTone;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "ScreenHeader", "eyebrow", "hint", "wordmark", "onBack", "trailing", "(Landroidx/compose/ui/Modifier;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class WayfindrComponentsKt {
    private static final RoundedCornerShape CardShape = RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18));
    private static final RoundedCornerShape BtnShape = RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14));
    private static final RoundedCornerShape InputShape = RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12));
    private static final RoundedCornerShape ChipShape = RoundedCornerShapeKt.getCircleShape();
    private static final RoundedCornerShape IconBtnShape = RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14));

    /* compiled from: WayfindrComponents.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;
        public static final /* synthetic */ int[] $EnumSwitchMapping$2;
        public static final /* synthetic */ int[] $EnumSwitchMapping$3;

        static {
            int[] iArr = new int[BtnVariant.values().length];
            try {
                iArr[BtnVariant.Primary.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BtnVariant.Secondary.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BtnVariant.Ghost.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BtnVariant.Danger.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[BtnSize.values().length];
            try {
                iArr2[BtnSize.Lg.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[BtnSize.Md.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr2[BtnSize.Sm.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$1 = iArr2;
            int[] iArr3 = new int[ChipTone.values().length];
            try {
                iArr3[ChipTone.Gold.ordinal()] = 1;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr3[ChipTone.Warn.ordinal()] = 2;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr3[ChipTone.Alert.ordinal()] = 3;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr3[ChipTone.Off.ordinal()] = 4;
            } catch (NoSuchFieldError e11) {
            }
            try {
                iArr3[ChipTone.Neutral.ordinal()] = 5;
            } catch (NoSuchFieldError e12) {
            }
            $EnumSwitchMapping$2 = iArr3;
            int[] iArr4 = new int[IconTone.values().length];
            try {
                iArr4[IconTone.Gold.ordinal()] = 1;
            } catch (NoSuchFieldError e13) {
            }
            try {
                iArr4[IconTone.Alert.ordinal()] = 2;
            } catch (NoSuchFieldError e14) {
            }
            try {
                iArr4[IconTone.Warn.ordinal()] = 3;
            } catch (NoSuchFieldError e15) {
            }
            try {
                iArr4[IconTone.Neutral.ordinal()] = 4;
            } catch (NoSuchFieldError e16) {
            }
            $EnumSwitchMapping$3 = iArr4;
        }
    }

    static final Unit Eyebrow$lambda$19(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        Eyebrow(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ScreenHeader$lambda$29(Modifier modifier, String str, String str2, String str3, boolean z, Function0 function0, Function2 function2, int i, int i2, Composer composer, int i3) {
        ScreenHeader(modifier, str, str2, str3, z, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrBtn$lambda$2(String str, Function0 function0, Modifier modifier, ImageVector imageVector, BtnVariant btnVariant, BtnSize btnSize, boolean z, int i, int i2, Composer composer, int i3) {
        WayfindrBtn(str, function0, modifier, imageVector, btnVariant, btnSize, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrCard_TN_CM5M$lambda$0(Modifier modifier, boolean z, float f, Function0 function0, Function3 function3, int i, int i2, Composer composer, int i3) {
        m8357WayfindrCardTN_CM5M(modifier, z, f, function0, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrChip$lambda$6(String str, ChipTone chipTone, boolean z, ImageVector imageVector, Modifier modifier, int i, int i2, Composer composer, int i3) {
        WayfindrChip(str, chipTone, z, imageVector, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrDivider$lambda$18(Modifier modifier, int i, int i2, Composer composer, int i3) {
        WayfindrDivider(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrIconBtn_N59uMVk$lambda$4(ImageVector imageVector, Function0 function0, Modifier modifier, float f, boolean z, Color color, int i, int i2, Composer composer, int i3) {
        m8358WayfindrIconBtnN59uMVk(imageVector, function0, modifier, f, z, color, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrRow$lambda$24(String str, Modifier modifier, ImageVector imageVector, IconTone iconTone, String str2, String str3, String str4, boolean z, boolean z2, Function0 function0, int i, int i2, Composer composer, int i3) {
        WayfindrRow(str, modifier, imageVector, iconTone, str2, str3, str4, z, z2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrSegmented$lambda$17(List list, String str, Function1 function1, Modifier modifier, int i, int i2, Composer composer, int i3) {
        WayfindrSegmented(list, str, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrToggle$lambda$11(boolean z, Function1 function1, int i, Composer composer, int i2) {
        WayfindrToggle(z, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final RoundedCornerShape getCardShape() {
        return CardShape;
    }

    public static final RoundedCornerShape getBtnShape() {
        return BtnShape;
    }

    public static final RoundedCornerShape getInputShape() {
        return InputShape;
    }

    public static final RoundedCornerShape getChipShape() {
        return ChipShape;
    }

    public static final RoundedCornerShape getIconBtnShape() {
        return IconBtnShape;
    }

    /* renamed from: WayfindrCard-TN_CM5M, reason: not valid java name */
    public static final void m8357WayfindrCardTN_CM5M(Modifier modifier, boolean glow, float padding, Function0<Unit> function0, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        boolean z;
        float padding2;
        Function0 function02;
        final Modifier modifier3;
        final boolean glow2;
        final Function0 onClick;
        final float padding3;
        Modifier.Companion modifier4;
        boolean glow3;
        Function0 onClick2;
        Modifier border;
        Function0 function03;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer $composer2 = $composer.startRestartGroup(1005044612);
        ComposerKt.sourceInformation($composer2, "C(WayfindrCard)N(modifier,glow,padding:c#ui.unit.Dp,onClick,content)51@1878L277:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            z = glow;
        } else if (($changed & 48) == 0) {
            z = glow;
            $dirty |= $composer2.changed(z) ? 32 : 16;
        } else {
            z = glow;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            padding2 = padding;
        } else if (($changed & 384) == 0) {
            padding2 = padding;
            $dirty |= $composer2.changed(padding2) ? 256 : 128;
        } else {
            padding2 = padding;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            function02 = function0;
        } else if (($changed & 3072) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 2048 : 1024;
        } else {
            function02 = function0;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(content) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 9363) != 9362, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            glow2 = z;
            onClick = function02;
            padding3 = padding2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 == 0) {
                glow3 = z;
            } else {
                glow3 = false;
            }
            if (i4 != 0) {
                padding2 = Dp.m7908constructorimpl(18);
            }
            if (i5 != 0) {
                onClick2 = null;
            } else {
                onClick2 = function02;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1005044612, $dirty2, -1, "com.example.wayfindr.ui.components.WayfindrCard (WayfindrComponents.kt:45)");
            }
            if (glow3) {
                border = BorderKt.m273borderxT4_qwU(Modifier.INSTANCE, Dp.m7908constructorimpl(1), ColorKt.getGoldTint2(), CardShape);
            } else {
                border = BorderKt.m273borderxT4_qwU(Modifier.INSTANCE, Dp.m7908constructorimpl(1), ColorKt.getLine(), CardShape);
            }
            Modifier then = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(modifier4, CardShape), ColorKt.getSurf1(), null, 2, null).then(border);
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier m816padding3ABfNKs = PaddingKt.m816padding3ABfNKs(then.then(onClick2 != null ? ClickableKt.m297clickableoSLSa3U$default(companion, false, null, null, null, onClick2, 15, null) : companion), padding2);
            int i6 = ($dirty2 >> 3) & 7168;
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((i6 >> 3) & 14) | ((i6 >> 3) & 112));
            Modifier modifier5 = modifier4;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            boolean glow4 = glow3;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m816padding3ABfNKs);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((i6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function03 = constructor;
                $composer2.createNode(function03);
            } else {
                function03 = constructor;
                $composer2.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer2);
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            content.invoke(ColumnScopeInstance.INSTANCE, $composer2, Integer.valueOf(((i6 >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            glow2 = glow4;
            modifier3 = modifier5;
            onClick = onClick2;
            padding3 = padding2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WayfindrComponentsKt.WayfindrCard_TN_CM5M$lambda$0(Modifier.this, glow2, padding3, onClick, content, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x04e7  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x04fb  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void WayfindrBtn(final String label, final Function0<Unit> onClick, Modifier modifier, ImageVector icon, BtnVariant variant, BtnSize size, boolean enabled, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        ImageVector imageVector;
        int i2;
        boolean z;
        int i3;
        Composer $composer2;
        final BtnSize size2;
        final boolean enabled2;
        final Modifier modifier3;
        final ImageVector icon2;
        final BtnVariant variant2;
        ScopeUpdateScope endRestartGroup;
        List listOf;
        float m7908constructorimpl;
        long sp;
        float iconSize;
        Composer composer;
        ImageVector icon3;
        long m5092copywmQWz5c;
        Intrinsics.checkNotNullParameter(label, "label");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer3 = $composer.startRestartGroup(644190215);
        ComposerKt.sourceInformation($composer3, "C(WayfindrBtn)N(label,onClick,modifier,icon,variant,size,enabled)86@3195L967:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onClick) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            imageVector = icon;
        } else if (($changed & 3072) == 0) {
            imageVector = icon;
            $dirty |= $composer3.changed(imageVector) ? 2048 : 1024;
        } else {
            imageVector = icon;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(variant == null ? -1 : variant.ordinal()) ? 16384 : 8192;
        }
        int i7 = i & 32;
        int i8 = ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        if (i7 == 0) {
            if ((196608 & $changed) == 0) {
                i8 = $composer3.changed(size == null ? -1 : size.ordinal()) ? 131072 : 65536;
            }
            i2 = i & 64;
            if (i2 == 0) {
                $dirty |= 1572864;
                z = enabled;
                i3 = 16;
            } else if (($changed & 1572864) == 0) {
                z = enabled;
                i3 = 16;
                $dirty |= $composer3.changed(z) ? 1048576 : 524288;
            } else {
                z = enabled;
                i3 = 16;
            }
            if ($composer3.shouldExecute(($dirty & 599187) == 599186, $dirty & 1)) {
                $composer2 = $composer3;
                $composer2.skipToGroupEnd();
                size2 = size;
                enabled2 = z;
                modifier3 = modifier2;
                icon2 = imageVector;
                variant2 = variant;
            } else {
                Modifier modifier4 = i4 != 0 ? Modifier.INSTANCE : modifier2;
                ImageVector icon4 = i5 != 0 ? null : imageVector;
                BtnVariant variant3 = i6 != 0 ? BtnVariant.Primary : variant;
                BtnSize size3 = i7 != 0 ? BtnSize.Md : size;
                boolean enabled3 = i2 != 0 ? true : z;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(644190215, $dirty, -1, "com.example.wayfindr.ui.components.WayfindrBtn (WayfindrComponents.kt:75)");
                }
                switch (WhenMappings.$EnumSwitchMapping$0[variant3.ordinal()]) {
                    case 1:
                        listOf = CollectionsKt.listOf(Color.m5084boximpl(ColorKt.getGold()), Color.m5084boximpl(ColorKt.getOnGold()), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU()), true);
                        break;
                    case 2:
                        listOf = CollectionsKt.listOf(Color.m5084boximpl(ColorKt.getSurf2()), Color.m5084boximpl(ColorKt.getTextHi()), Color.m5084boximpl(ColorKt.getLine2()), false);
                        break;
                    case 3:
                        listOf = CollectionsKt.listOf(Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU()), Color.m5084boximpl(ColorKt.getTextMid()), Color.m5084boximpl(ColorKt.getLine2()), false);
                        break;
                    case 4:
                        Color m5084boximpl = Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU());
                        Color m5084boximpl2 = Color.m5084boximpl(ColorKt.getAlert());
                        m5092copywmQWz5c = Color.m5092copywmQWz5c(r29, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r29) : 0.4f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r29) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r29) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getAlert()) : 0.0f);
                        listOf = CollectionsKt.listOf(m5084boximpl, m5084boximpl2, Color.m5084boximpl(m5092copywmQWz5c), false);
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                Object bgColor = listOf.get(0);
                Object fgColor = listOf.get(1);
                Object borderColor = listOf.get(2);
                listOf.get(3);
                switch (WhenMappings.$EnumSwitchMapping$1[size3.ordinal()]) {
                    case 1:
                        m7908constructorimpl = Dp.m7908constructorimpl(58);
                        break;
                    case 2:
                        m7908constructorimpl = Dp.m7908constructorimpl(50);
                        break;
                    case 3:
                        m7908constructorimpl = Dp.m7908constructorimpl(40);
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                float height = m7908constructorimpl;
                switch (WhenMappings.$EnumSwitchMapping$1[size3.ordinal()]) {
                    case 1:
                        sp = TextUnitKt.getSp(i3);
                        break;
                    case 2:
                        sp = TextUnitKt.getSp(15);
                        break;
                    case 3:
                        sp = TextUnitKt.getSp(13.5d);
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                long fontSize = sp;
                float iconSize2 = WhenMappings.$EnumSwitchMapping$1[size3.ordinal()] == 3 ? Dp.m7908constructorimpl(17) : Dp.m7908constructorimpl(20);
                Arrangement.Horizontal center = Arrangement.INSTANCE.getCenter();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Modifier clip = ClipKt.clip(SizeKt.m848height3ABfNKs(modifier4, height), BtnShape);
                Intrinsics.checkNotNull(bgColor, "null cannot be cast to non-null type androidx.compose.ui.graphics.Color");
                Modifier m262backgroundbw27NRU$default = BackgroundKt.m262backgroundbw27NRU$default(clip, ((Color) bgColor).m5104unboximpl(), null, 2, null);
                float m7908constructorimpl2 = Dp.m7908constructorimpl(1);
                Intrinsics.checkNotNull(borderColor, "null cannot be cast to non-null type androidx.compose.ui.graphics.Color");
                boolean enabled4 = enabled3;
                Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(m262backgroundbw27NRU$default, m7908constructorimpl2, ((Color) borderColor).m5104unboximpl(), BtnShape);
                float iconSize3 = iconSize2;
                int $dirty2 = $dirty;
                Modifier m818paddingVpY3zN4$default = PaddingKt.m818paddingVpY3zN4$default(ClickableKt.m297clickableoSLSa3U$default(m273borderxT4_qwU, enabled4, null, null, null, onClick, 14, null), Dp.m7908constructorimpl(20), 0.0f, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, m818paddingVpY3zN4$default);
                Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i9 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    $composer3.createNode(constructor);
                } else {
                    $composer3.useNode();
                }
                Composer m4364constructorimpl = Updater.m4364constructorimpl($composer3);
                Updater.m4372setimpl(m4364constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i10 = (i9 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i11 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 2007772023, "C106@3894L262:WayfindrComponents.kt#7crva5");
                if (icon4 != null) {
                    $composer3.startReplaceGroup(2007780919);
                    ComposerKt.sourceInformation($composer3, "98@3643L191,104@3847L28");
                    Intrinsics.checkNotNull(fgColor, "null cannot be cast to non-null type androidx.compose.ui.graphics.Color");
                    IconKt.m2517Iconww6aTOc(icon4, (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, iconSize3), ((Color) fgColor).m5104unboximpl(), $composer3, (($dirty2 >> 9) & 14) | 48, 0);
                    icon3 = icon4;
                    composer = $composer3;
                    iconSize = iconSize3;
                    float iconSize4 = 9;
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(iconSize4)), composer, 6);
                } else {
                    iconSize = iconSize3;
                    composer = $composer3;
                    icon3 = icon4;
                    composer.startReplaceGroup(2004172023);
                }
                composer.endReplaceGroup();
                Intrinsics.checkNotNull(fgColor, "null cannot be cast to non-null type androidx.compose.ui.graphics.Color");
                Composer composer2 = composer;
                Modifier modifier5 = modifier4;
                $composer2 = $composer3;
                TextKt.m3069TextNvy7gAk(label, null, ((Color) fgColor).m5104unboximpl(), null, fontSize, null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.15d), null, null, 0L, 0, false, 1, 0, null, null, composer2, ($dirty2 & 14) | 14155776, 24576, 245290);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                icon2 = icon3;
                variant2 = variant3;
                size2 = size3;
                enabled2 = enabled4;
                modifier3 = modifier5;
            }
            endRestartGroup = $composer2.endRestartGroup();
            if (endRestartGroup == null) {
                endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return WayfindrComponentsKt.WayfindrBtn$lambda$2(label, onClick, modifier3, icon2, variant2, size2, enabled2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
                return;
            }
            return;
        }
        $dirty |= i8;
        i2 = i & 64;
        if (i2 == 0) {
        }
        if ($composer3.shouldExecute(($dirty & 599187) == 599186, $dirty & 1)) {
        }
        endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup == null) {
        }
    }

    /* renamed from: WayfindrIconBtn-N59uMVk, reason: not valid java name */
    public static final void m8358WayfindrIconBtnN59uMVk(final ImageVector icon, final Function0<Unit> onClick, Modifier modifier, float size, boolean active, Color tint, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        float f;
        boolean active2;
        Color tint2;
        final Modifier modifier3;
        final float size2;
        final boolean active3;
        final Color tint3;
        Modifier modifier4;
        float size3;
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        Composer $composer2 = $composer.startRestartGroup(-1479100000);
        ComposerKt.sourceInformation($composer2, "C(WayfindrIconBtn)N(icon,onClick,modifier,size:c#ui.unit.Dp,active,tint:c#ui.graphics.Color)132@4557L358:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(icon) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onClick) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            f = size;
        } else if (($changed & 3072) == 0) {
            f = size;
            $dirty |= $composer2.changed(f) ? 2048 : 1024;
        } else {
            f = size;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty |= 24576;
            active2 = active;
        } else if (($changed & 24576) == 0) {
            active2 = active;
            $dirty |= $composer2.changed(active2) ? 16384 : 8192;
        } else {
            active2 = active;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tint2 = tint;
        } else if ((196608 & $changed) == 0) {
            tint2 = tint;
            $dirty |= $composer2.changed(tint2) ? 131072 : 65536;
        } else {
            tint2 = tint;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute((74899 & $dirty2) != 74898, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            size2 = f;
            active3 = active2;
            tint3 = tint2;
        } else {
            if (i2 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i3 == 0) {
                size3 = f;
            } else {
                size3 = Dp.m7908constructorimpl(44);
            }
            if (i4 != 0) {
                active2 = false;
            }
            if (i5 != 0) {
                tint2 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1479100000, $dirty2, -1, "com.example.wayfindr.ui.components.WayfindrIconBtn (WayfindrComponents.kt:127)");
            }
            long bg = active2 ? ColorKt.getGoldTint() : ColorKt.getSurf2();
            long bd = active2 ? ColorKt.getGoldTint2() : ColorKt.getLine2();
            long iconTint = tint2 != null ? tint2.m5104unboximpl() : active2 ? ColorKt.getGold() : ColorKt.getTextMid();
            Alignment center = Alignment.INSTANCE.getCenter();
            float size4 = size3;
            Modifier m297clickableoSLSa3U$default = ClickableKt.m297clickableoSLSa3U$default(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(modifier4, size3), IconBtnShape), bg, null, 2, null), Dp.m7908constructorimpl(1), bd, IconBtnShape), false, null, null, null, onClick, 15, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m297clickableoSLSa3U$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i6 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(constructor);
            } else {
                $composer2.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer2);
            Updater.m4372setimpl(m4364constructorimpl, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = (i6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i8 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1942636110, "C141@4822L87:WayfindrComponents.kt#7crva5");
            IconKt.m2517Iconww6aTOc(icon, (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), iconTint, $composer2, ($dirty2 & 14) | 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            size2 = size4;
            active3 = active2;
            tint3 = tint2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WayfindrComponentsKt.WayfindrIconBtn_N59uMVk$lambda$4(ImageVector.this, onClick, modifier3, size2, active3, tint3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void WayfindrChip(final String label, ChipTone tone, boolean dot, ImageVector icon, Modifier modifier, Composer $composer, final int $changed, final int i) {
        boolean z;
        ImageVector imageVector;
        Composer $composer2;
        final ChipTone tone2;
        final Modifier modifier2;
        final boolean dot2;
        final ImageVector icon2;
        ChipTone tone3;
        boolean dot3;
        ImageVector icon3;
        Modifier.Companion modifier3;
        Triple triple;
        long m5092copywmQWz5c;
        long m5092copywmQWz5c2;
        long m5092copywmQWz5c3;
        long m5092copywmQWz5c4;
        Function0 function0;
        Modifier modifier4;
        long fg;
        Composer composer;
        ImageVector icon4;
        Composer composer2;
        Intrinsics.checkNotNullParameter(label, "label");
        Composer $composer3 = $composer.startRestartGroup(1160288885);
        ComposerKt.sourceInformation($composer3, "C(WayfindrChip)N(label,tone,dot,icon,modifier)164@5590L721:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(tone == null ? -1 : tone.ordinal()) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            z = dot;
        } else if (($changed & 384) == 0) {
            z = dot;
            $dirty |= $composer3.changed(z) ? 256 : 128;
        } else {
            z = dot;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            imageVector = icon;
        } else if (($changed & 3072) == 0) {
            imageVector = icon;
            $dirty |= $composer3.changed(imageVector) ? 2048 : 1024;
        } else {
            imageVector = icon;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(modifier) ? 16384 : 8192;
        }
        if (!$composer3.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            tone2 = tone;
            modifier2 = modifier;
            dot2 = z;
            icon2 = imageVector;
        } else {
            if (i2 != 0) {
                tone3 = ChipTone.Neutral;
            } else {
                tone3 = tone;
            }
            if (i3 == 0) {
                dot3 = z;
            } else {
                dot3 = false;
            }
            if (i4 == 0) {
                icon3 = imageVector;
            } else {
                icon3 = null;
            }
            if (i5 == 0) {
                modifier3 = modifier;
            } else {
                modifier3 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1160288885, $dirty, -1, "com.example.wayfindr.ui.components.WayfindrChip (WayfindrComponents.kt:155)");
            }
            switch (WhenMappings.$EnumSwitchMapping$2[tone3.ordinal()]) {
                case 1:
                    triple = new Triple(Color.m5084boximpl(ColorKt.getGoldTint()), Color.m5084boximpl(ColorKt.getGold()), Color.m5084boximpl(ColorKt.getGoldTint2()));
                    break;
                case 2:
                    m5092copywmQWz5c = Color.m5092copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r14) : 0.13f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getWarn()) : 0.0f);
                    Color m5084boximpl = Color.m5084boximpl(m5092copywmQWz5c);
                    Color m5084boximpl2 = Color.m5084boximpl(ColorKt.getWarn());
                    m5092copywmQWz5c2 = Color.m5092copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r14) : 0.3f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getWarn()) : 0.0f);
                    triple = new Triple(m5084boximpl, m5084boximpl2, Color.m5084boximpl(m5092copywmQWz5c2));
                    break;
                case 3:
                    m5092copywmQWz5c3 = Color.m5092copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r14) : 0.13f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getAlert()) : 0.0f);
                    Color m5084boximpl3 = Color.m5084boximpl(m5092copywmQWz5c3);
                    Color m5084boximpl4 = Color.m5084boximpl(ColorKt.getAlert());
                    m5092copywmQWz5c4 = Color.m5092copywmQWz5c(r14, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r14) : 0.32f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r14) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r14) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getAlert()) : 0.0f);
                    triple = new Triple(m5084boximpl3, m5084boximpl4, Color.m5084boximpl(m5092copywmQWz5c4));
                    break;
                case 4:
                    triple = new Triple(Color.m5084boximpl(ColorKt.getSurf1()), Color.m5084boximpl(ColorKt.getTextLo()), Color.m5084boximpl(ColorKt.getLine()));
                    break;
                case 5:
                    triple = new Triple(Color.m5084boximpl(ColorKt.getSurf2()), Color.m5084boximpl(ColorKt.getTextMid()), Color.m5084boximpl(ColorKt.getLine2()));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            long bg = ((Color) triple.component1()).m5104unboximpl();
            long fg2 = ((Color) triple.component2()).m5104unboximpl();
            long bd = ((Color) triple.component3()).m5104unboximpl();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(7));
            Modifier m818paddingVpY3zN4$default = PaddingKt.m818paddingVpY3zN4$default(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(modifier3, Dp.m7908constructorimpl(30)), ChipShape), bg, null, 2, null), Dp.m7908constructorimpl(1), bd, ChipShape), Dp.m7908constructorimpl(12), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m686spacedBy0680j_4, centerVertically, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, m818paddingVpY3zN4$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i6 = ((((432 << 3) & 112) << 6) & 896) | 6;
            int $dirty2 = $dirty;
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
            Updater.m4372setimpl(m4364constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i7 = (i6 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i8 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 1993450483, "C180@6153L152:WayfindrComponents.kt#7crva5");
            if (dot3) {
                $composer3.startReplaceGroup(1993450048);
                ComposerKt.sourceInformation($composer3, "175@5945L57");
                modifier4 = materializeModifier;
                fg = fg2;
                BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(7)), RoundedCornerShapeKt.getCircleShape()), fg, null, 2, null), $composer3, 0);
            } else {
                modifier4 = materializeModifier;
                fg = fg2;
                $composer3.startReplaceGroup(1987562993);
            }
            $composer3.endReplaceGroup();
            if (icon3 != null) {
                $composer3.startReplaceGroup(1993557928);
                ComposerKt.sourceInformation($composer3, "178@6053L81");
                composer2 = $composer3;
                long fg3 = fg;
                IconKt.m2517Iconww6aTOc(icon3, (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), fg3, $composer3, (($dirty2 >> 9) & 14) | 432, 0);
                icon4 = icon3;
                composer = $composer3;
                fg = fg3;
            } else {
                composer = $composer3;
                icon4 = icon3;
                composer2 = $composer3;
                composer.startReplaceGroup(1987562993);
            }
            composer.endReplaceGroup();
            Modifier modifier5 = modifier3;
            $composer2 = $composer3;
            Composer composer3 = composer;
            TextKt.m3069TextNvy7gAk(label, null, fg, null, TextUnitKt.getSp(12.5d), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, composer3, ($dirty2 & 14) | 14180352, 24576, 245546);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            tone2 = tone3;
            dot2 = dot3;
            icon2 = icon4;
            modifier2 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WayfindrComponentsKt.WayfindrChip$lambda$6(label, tone2, dot2, icon2, modifier2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void WayfindrToggle(final boolean on, final Function1<? super Boolean, Unit> onChange, Composer $composer, final int $changed) {
        Composer $composer2;
        int i;
        float m7908constructorimpl;
        Modifier m292clickableO2vRcR0;
        Function0 function0;
        Intrinsics.checkNotNullParameter(onChange, "onChange");
        Composer $composer3 = $composer.startRestartGroup(452575772);
        ComposerKt.sourceInformation($composer3, "C(WayfindrToggle)N(on,onChange)202@6824L39,204@6914L17,195@6586L561:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(on) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(onChange) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(452575772, $dirty, -1, "com.example.wayfindr.ui.components.WayfindrToggle (WayfindrComponents.kt:189)");
            }
            long track = on ? ColorKt.getGold() : ColorKt.getSurf3();
            long bd = on ? ColorKt.getGold() : ColorKt.getLine3();
            long knob = on ? ColorKt.getOnGold() : ColorKt.getTextMid();
            if (!on) {
                i = 0;
                m7908constructorimpl = Dp.m7908constructorimpl((float) 2.5d);
            } else {
                m7908constructorimpl = Dp.m7908constructorimpl(22);
                i = 0;
            }
            float knobOffset = m7908constructorimpl;
            Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(50)), Dp.m7908constructorimpl(30)), RoundedCornerShapeKt.getCircleShape()), track, null, 2, null), Dp.m7908constructorimpl(1), bd, RoundedCornerShapeKt.getCircleShape());
            ComposerKt.sourceInformationMarkerStart($composer3, 1133635683, "CC(remember):WayfindrComponents.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            int i2 = i;
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object MutableInteractionSource = InteractionSourceKt.MutableInteractionSource();
                $composer3.updateRememberedValue(MutableInteractionSource);
                rememberedValue = MutableInteractionSource;
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 1133638541, "CC(remember):WayfindrComponents.kt#9igjgp");
            int i3 = (($dirty & 112) == 32 ? 1 : i2) | (($dirty & 14) == 4 ? 1 : i2);
            Object rememberedValue2 = $composer3.rememberedValue();
            if (i3 != 0 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return WayfindrComponentsKt.WayfindrToggle$lambda$9$lambda$8(Function1.this, on);
                    }
                };
                $composer3.updateRememberedValue(obj);
                rememberedValue2 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            m292clickableO2vRcR0 = ClickableKt.m292clickableO2vRcR0(m273borderxT4_qwU, mutableInteractionSource, null, (r14 & 4) != 0, (r14 & 8) != 0 ? null : null, (r14 & 16) != 0 ? null : null, (Function0) rememberedValue2);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            int i4 = (i2 << 3) & 112;
            $composer2 = $composer3;
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, i2));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, m292clickableO2vRcR0);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i5 = ((i4 << 6) & 896) | 6;
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
            int i6 = (i5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i7 = ((i2 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -1773668063, "C206@6949L192:WayfindrComponents.kt#7crva5");
            float knobOffset2 = 23;
            BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, knobOffset, Dp.m7908constructorimpl((float) 2.5d), 0.0f, 0.0f, 12, null), Dp.m7908constructorimpl(knobOffset2)), RoundedCornerShapeKt.getCircleShape()), knob, null, 2, null), $composer3, 0);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return WayfindrComponentsKt.WayfindrToggle$lambda$11(on, onChange, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit WayfindrToggle$lambda$9$lambda$8(Function1 $onChange, boolean $on) {
        $onChange.invoke(Boolean.valueOf(!$on));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x02a1, code lost:
    
        if (r2 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L71;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void WayfindrSegmented(final List<String> options, final String selected, final Function1<? super String, Unit> function1, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Modifier modifier3;
        Function0 function0;
        Function0 function02;
        Object selected2 = selected;
        final Function1<? super String, Unit> onSelect = function1;
        Intrinsics.checkNotNullParameter(options, "options");
        Intrinsics.checkNotNullParameter(selected2, "selected");
        Intrinsics.checkNotNullParameter(onSelect, "onSelect");
        Composer $composer2 = $composer.startRestartGroup(-2132616304);
        ComposerKt.sourceInformation($composer2, "C(WayfindrSegmented)N(options,selected,onSelect,modifier)224@7341L955:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(options) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(selected2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(onSelect) ? 256 : 128;
        }
        int i2 = i & 8;
        if (i2 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2132616304, $dirty2, -1, "com.example.wayfindr.ui.components.WayfindrSegmented (WayfindrComponents.kt:223)");
            }
            Modifier m816padding3ABfNKs = PaddingKt.m816padding3ABfNKs(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(modifier2, RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), Dp.m7908constructorimpl(3));
            Arrangement.Horizontal m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(2));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m686spacedBy0680j_4, Alignment.INSTANCE.getTop(), $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m816padding3ABfNKs);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i3 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            int i5 = 0;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i6 = ((48 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 700275018, "C:WayfindrComponents.kt#7crva5");
            $composer2.startReplaceGroup(438231761);
            ComposerKt.sourceInformation($composer2, "*241@8009L17,234@7691L589");
            List<String> list = options;
            int i7 = 0;
            for (final String str2 : list) {
                Iterable iterable = list;
                boolean areEqual = Intrinsics.areEqual(str2, selected2);
                Alignment center = Alignment.INSTANCE.getCenter();
                int i8 = i7;
                int i9 = i5;
                Modifier m262backgroundbw27NRU$default = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m7908constructorimpl(36)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(9))), areEqual ? ColorKt.getSurf3() : Color.INSTANCE.m5129getTransparent0d7_KjU(), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer2, -1054729771, "CC(remember):WayfindrComponents.kt#9igjgp");
                boolean changed = (($dirty2 & 896) == 256) | $composer2.changed(str2);
                Object rememberedValue = $composer2.rememberedValue();
                if (changed) {
                }
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return WayfindrComponentsKt.WayfindrSegmented$lambda$16$lambda$15$lambda$13$lambda$12(Function1.this, str2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                rememberedValue = obj;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier m297clickableoSLSa3U$default = ClickableKt.m297clickableoSLSa3U$default(m262backgroundbw27NRU$default, false, null, null, null, (Function0) rememberedValue, 15, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, m297clickableoSLSa3U$default);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, str);
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
                String str3 = str;
                Updater.m4372setimpl(m4364constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i11 = (i10 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i12 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 93368439, "C243@8060L206:WayfindrComponents.kt#7crva5");
                TextKt.m3069TextNvy7gAk(str2, null, areEqual ? ColorKt.getTextHi() : ColorKt.getTextLo(), null, TextUnitKt.getSp(13), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 14180352, 0, 261930);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                selected2 = selected;
                onSelect = function1;
                i5 = i9;
                list = iterable;
                i7 = i8;
                str = str3;
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return WayfindrComponentsKt.WayfindrSegmented$lambda$17(options, selected, function1, modifier3, $changed, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit WayfindrSegmented$lambda$16$lambda$15$lambda$13$lambda$12(Function1 $onSelect, String $opt) {
        $onSelect.invoke($opt);
        return Unit.INSTANCE;
    }

    public static final void WayfindrDivider(final Modifier modifier, Composer $composer, final int $changed, final int i) {
        Composer $composer2 = $composer.startRestartGroup(2132329495);
        ComposerKt.sourceInformation($composer2, "C(WayfindrDivider)N(modifier)256@8390L58:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (i2 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2132329495, $dirty, -1, "com.example.wayfindr.ui.components.WayfindrDivider (WayfindrComponents.kt:255)");
            }
            BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(SizeKt.m848height3ABfNKs(SizeKt.fillMaxWidth$default(modifier, 0.0f, 1, null), Dp.m7908constructorimpl(1)), ColorKt.getLine(), null, 2, null), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WayfindrComponentsKt.WayfindrDivider$lambda$18(Modifier.this, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void Eyebrow(final String text, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        final Modifier modifier3;
        Intrinsics.checkNotNullParameter(text, "text");
        Composer $composer3 = $composer.startRestartGroup(-1244014832);
        ComposerKt.sourceInformation($composer3, "C(Eyebrow)N(text,modifier)262@8554L234:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(text) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1244014832, $dirty, -1, "com.example.wayfindr.ui.components.Eyebrow (WayfindrComponents.kt:261)");
            }
            String upperCase = text.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            int $dirty2 = $dirty;
            modifier3 = modifier4;
            $composer2 = $composer3;
            TextKt.m3069TextNvy7gAk(upperCase, modifier3, ColorKt.getTextLo(), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getNormal(), TypeKt.getGeistMonoFamily(), TextUnitKt.getSp(0.16d), null, null, 0L, 0, false, 0, 0, null, null, $composer2, ($dirty2 & 112) | 114844032, 0, 261672);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WayfindrComponentsKt.Eyebrow$lambda$19(text, modifier3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0a59  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void WayfindrRow(final String title, Modifier modifier, ImageVector icon, IconTone iconTone, String sub, String right, String rightSub, boolean accentRight, boolean trailingIcon, Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        ImageVector icon2;
        String str;
        String str2;
        String str3;
        int i2;
        int i3;
        int i4;
        Composer $composer2;
        final Modifier modifier2;
        final IconTone iconTone2;
        final boolean accentRight2;
        final Function0 onClick;
        final String rightSub2;
        final ImageVector icon3;
        final String sub2;
        final String right2;
        final boolean trailingIcon2;
        Pair pair;
        long m5092copywmQWz5c;
        long m5092copywmQWz5c2;
        Function0 onClick2;
        Modifier.Companion companion;
        Alignment.Vertical vertical;
        CompositionLocalMap compositionLocalMap;
        int $dirty;
        ImageVector icon4;
        Function0 function02;
        Composer composer;
        int i5;
        Function0 function03;
        String right3;
        Function0 function04;
        Intrinsics.checkNotNullParameter(title, "title");
        Composer $composer3 = $composer.startRestartGroup(468339821);
        ComposerKt.sourceInformation($composer3, "C(WayfindrRow)N(title,modifier,icon,iconTone,sub,right,rightSub,accentRight,trailingIcon,onClick)296@9480L2122:WayfindrComponents.kt#7crva5");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(title) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty2 |= 384;
            icon2 = icon;
        } else if (($changed & 384) == 0) {
            icon2 = icon;
            $dirty2 |= $composer3.changed(icon2) ? 256 : 128;
        } else {
            icon2 = icon;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(iconTone == null ? -1 : iconTone.ordinal()) ? 2048 : 1024;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= 24576;
            str = sub;
        } else if (($changed & 24576) == 0) {
            str = sub;
            $dirty2 |= $composer3.changed(str) ? 16384 : 8192;
        } else {
            str = sub;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            str2 = right;
        } else if ((196608 & $changed) == 0) {
            str2 = right;
            $dirty2 |= $composer3.changed(str2) ? 131072 : 65536;
        } else {
            str2 = right;
        }
        int i11 = i & 64;
        if (i11 != 0) {
            $dirty2 |= 1572864;
            str3 = rightSub;
        } else if (($changed & 1572864) == 0) {
            str3 = rightSub;
            $dirty2 |= $composer3.changed(str3) ? 1048576 : 524288;
        } else {
            str3 = rightSub;
        }
        int i12 = i & 128;
        if (i12 != 0) {
            $dirty2 |= 12582912;
            i2 = i12;
        } else if (($changed & 12582912) == 0) {
            i2 = i12;
            $dirty2 |= $composer3.changed(accentRight) ? 8388608 : 4194304;
        } else {
            i2 = i12;
        }
        int i13 = i & 256;
        if (i13 != 0) {
            $dirty2 |= 100663296;
            i3 = i13;
        } else if (($changed & 100663296) == 0) {
            i3 = i13;
            $dirty2 |= $composer3.changed(trailingIcon) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i13;
        }
        int i14 = i & 512;
        if (i14 != 0) {
            $dirty2 |= 805306368;
            i4 = i14;
        } else if (($changed & 805306368) == 0) {
            i4 = i14;
            $dirty2 |= $composer3.changedInstance(function0) ? 536870912 : 268435456;
        } else {
            i4 = i14;
        }
        if ($composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty2 & 1)) {
            Modifier.Companion modifier3 = i6 != 0 ? Modifier.INSTANCE : modifier;
            if (i7 != 0) {
                icon2 = null;
            }
            IconTone iconTone3 = i8 != 0 ? IconTone.Neutral : iconTone;
            String sub3 = i9 != 0 ? null : str;
            String right4 = i10 != 0 ? null : str2;
            String rightSub3 = i11 != 0 ? null : str3;
            boolean accentRight3 = i2 != 0 ? false : accentRight;
            boolean trailingIcon3 = i3 != 0 ? false : trailingIcon;
            Function0 onClick3 = i4 != 0 ? null : function0;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(468339821, $dirty2, -1, "com.example.wayfindr.ui.components.WayfindrRow (WayfindrComponents.kt:288)");
            }
            switch (WhenMappings.$EnumSwitchMapping$3[iconTone3.ordinal()]) {
                case 1:
                    pair = TuplesKt.to(Color.m5084boximpl(ColorKt.getGoldTint()), Color.m5084boximpl(ColorKt.getGold()));
                    break;
                case 2:
                    m5092copywmQWz5c = Color.m5092copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r10) : 0.13f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getAlert()) : 0.0f);
                    pair = TuplesKt.to(Color.m5084boximpl(m5092copywmQWz5c), Color.m5084boximpl(ColorKt.getAlert()));
                    break;
                case 3:
                    m5092copywmQWz5c2 = Color.m5092copywmQWz5c(r10, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r10) : 0.13f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r10) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r10) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getWarn()) : 0.0f);
                    pair = TuplesKt.to(Color.m5084boximpl(m5092copywmQWz5c2), Color.m5084boximpl(ColorKt.getWarn()));
                    break;
                case 4:
                    pair = TuplesKt.to(Color.m5084boximpl(ColorKt.getSurf2()), Color.m5084boximpl(ColorKt.getTextMid()));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            long iconBg = ((Color) pair.component1()).m5104unboximpl();
            long iconFg = ((Color) pair.component2()).m5104unboximpl();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), Dp.m7908constructorimpl(4), Dp.m7908constructorimpl(13));
            if (onClick3 != null) {
                Function0 onClick4 = onClick3;
                companion = ClickableKt.m297clickableoSLSa3U$default(Modifier.INSTANCE, false, null, null, null, onClick4, 15, null);
                onClick2 = onClick4;
            } else {
                onClick2 = onClick3;
                companion = Modifier.INSTANCE;
            }
            Modifier then = m817paddingVpY3zN4.then(companion);
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, then);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i15 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer3);
            Modifier modifier4 = modifier3;
            Updater.m4372setimpl(m4364constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i16 = (i15 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i17 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 1085641586, "C316@10276L444:WayfindrComponents.kt#7crva5");
            if (icon2 != null) {
                $composer3.startReplaceGroup(1085617684);
                ComposerKt.sourceInformation($composer3, "304@9796L419,314@10228L29");
                Alignment center = Alignment.INSTANCE.getCenter();
                vertical = centerVertically;
                compositionLocalMap = currentCompositionLocalMap;
                Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(42)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), iconBg, null, 2, null), Dp.m7908constructorimpl(1), ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12)));
                ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer3, m273borderxT4_qwU);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i18 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function04 = constructor2;
                    $composer3.createNode(function04);
                } else {
                    function04 = constructor2;
                    $composer3.useNode();
                }
                Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer3);
                $dirty = $dirty2;
                Updater.m4372setimpl(m4364constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i19 = (i18 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                int i20 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 7982854, "C312@10116L85:WayfindrComponents.kt#7crva5");
                ImageVector icon5 = icon2;
                IconKt.m2517Iconww6aTOc(icon5, (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), iconFg, $composer3, (($dirty >> 6) & 14) | 432, 0);
                icon4 = icon5;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer3, 6);
                $composer3.endReplaceGroup();
            } else {
                vertical = centerVertically;
                compositionLocalMap = currentCompositionLocalMap;
                $dirty = $dirty2;
                icon4 = icon2;
                $composer3.startReplaceGroup(1075897913);
                $composer3.endReplaceGroup();
            }
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer3, weight$default);
            Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i21 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function02 = constructor3;
                $composer3.createNode(function02);
            } else {
                function02 = constructor3;
                $composer3.useNode();
            }
            Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer3);
            Updater.m4372setimpl(m4364constructorimpl3, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i22 = (i21 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i23 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -2001692536, "C317@10318L246:WayfindrComponents.kt#7crva5");
            $composer2 = $composer3;
            int $dirty3 = $dirty;
            TextKt.m3069TextNvy7gAk(title, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(15.5d), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.15d), null, null, 0L, TextOverflow.INSTANCE.m7819getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer3, ($dirty & 14) | 14180736, 24960, 241194);
            if (sub3 != null) {
                $composer3.startReplaceGroup(-2001427301);
                ComposerKt.sourceInformation($composer3, "323@10612L84");
                TextKt.m3069TextNvy7gAk(sub3, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, (($dirty3 >> 12) & 14) | 25008, 0, 262120);
            } else {
                $composer3.startReplaceGroup(-2011941137);
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (right4 == null) {
                composer = $composer3;
                if (rightSub3 == null) {
                    composer.startReplaceGroup(1075897913);
                    composer.endReplaceGroup();
                    right3 = right4;
                    i5 = 6;
                    if (!trailingIcon3 || (onClick2 != null && right3 == null)) {
                        composer.startReplaceGroup(1087190717);
                        ComposerKt.sourceInformation(composer, "342@11390L28,343@11431L155");
                        SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), composer, i5);
                        IconKt.m2517Iconww6aTOc(ChevronRightKt.getChevronRight(Icons.Outlined.INSTANCE), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), ColorKt.getTextLo(), composer, 3504, 0);
                    } else {
                        composer.startReplaceGroup(1075897913);
                    }
                    composer.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    right2 = right3;
                    iconTone2 = iconTone3;
                    sub2 = sub3;
                    rightSub2 = rightSub3;
                    accentRight2 = accentRight3;
                    trailingIcon2 = trailingIcon3;
                    onClick = onClick2;
                    icon3 = icon4;
                    modifier2 = modifier4;
                }
            } else {
                composer = $composer3;
            }
            composer.startReplaceGroup(1086597594);
            ComposerKt.sourceInformation(composer, "327@10782L519");
            Alignment.Horizontal end = Alignment.INSTANCE.getEnd();
            Composer composer2 = composer;
            ComposerKt.sourceInformationMarkerStart(composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier5 = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), end, composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer2, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifier5);
            Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i24 = ((((384 << 3) & 112) << 6) & 896) | 6;
            i5 = 6;
            ComposerKt.sourceInformationMarkerStart(composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                function03 = constructor4;
                composer2.createNode(function03);
            } else {
                function03 = constructor4;
                composer2.useNode();
            }
            Composer m4364constructorimpl4 = Updater.m4364constructorimpl(composer2);
            Updater.m4372setimpl(m4364constructorimpl4, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i25 = (i24 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i26 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer2, -1546859129, "C:WayfindrComponents.kt#7crva5");
            if (right4 != null) {
                composer2.startReplaceGroup(-1546844870);
                ComposerKt.sourceInformation(composer2, "329@10885L255");
                String right5 = right4;
                TextKt.m3069TextNvy7gAk(right5, null, accentRight3 ? ColorKt.getGold() : ColorKt.getTextHi(), null, TextUnitKt.getSp(14.5d), null, FontWeight.INSTANCE.getMedium(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, (($dirty3 >> 15) & 14) | 14180352, 0, 261930);
                right3 = right5;
            } else {
                right3 = right4;
                composer2.startReplaceGroup(-1557631103);
            }
            composer2.endReplaceGroup();
            if (rightSub3 != null) {
                composer2.startReplaceGroup(-1546519897);
                ComposerKt.sourceInformation(composer2, "337@11219L50");
                TextKt.m3069TextNvy7gAk(rightSub3, null, ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, (($dirty3 >> 18) & 14) | 24960, 0, 262122);
            } else {
                composer2.startReplaceGroup(-1557631103);
            }
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer.endReplaceGroup();
            if (trailingIcon3) {
            }
            composer.startReplaceGroup(1087190717);
            ComposerKt.sourceInformation(composer, "342@11390L28,343@11431L155");
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), composer, i5);
            IconKt.m2517Iconww6aTOc(ChevronRightKt.getChevronRight(Icons.Outlined.INSTANCE), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), ColorKt.getTextLo(), composer, 3504, 0);
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (ComposerKt.isTraceInProgress()) {
            }
            right2 = right3;
            iconTone2 = iconTone3;
            sub2 = sub3;
            rightSub2 = rightSub3;
            accentRight2 = accentRight3;
            trailingIcon2 = trailingIcon3;
            onClick = onClick2;
            icon3 = icon4;
            modifier2 = modifier4;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            iconTone2 = iconTone;
            accentRight2 = accentRight;
            onClick = function0;
            rightSub2 = str3;
            icon3 = icon2;
            sub2 = str;
            right2 = str2;
            trailingIcon2 = trailingIcon;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WayfindrComponentsKt.WayfindrRow$lambda$24(title, modifier2, icon3, iconTone2, sub2, right2, rightSub2, accentRight2, trailingIcon2, onClick, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void ScreenHeader(Modifier modifier, String title, String eyebrow, String hint, boolean wordmark, Function0<Unit> function0, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        String str;
        String eyebrow2;
        String hint2;
        boolean wordmark2;
        Function0 function02;
        final Modifier modifier2;
        final String title2;
        final String eyebrow3;
        final String hint3;
        final Function0 onBack;
        final Function2 trailing;
        final boolean wordmark3;
        Function0 function03;
        Function0 onBack2;
        Function0 function04;
        Composer composer;
        Function0 function05;
        String hint4;
        Function0 function06;
        Composer $composer2 = $composer.startRestartGroup(1432046249);
        ComposerKt.sourceInformation($composer2, "C(ScreenHeader)N(modifier,title,eyebrow,hint,wordmark,onBack,trailing)362@11921L1940:WayfindrComponents.kt#7crva5");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            str = title;
        } else if (($changed & 48) == 0) {
            str = title;
            $dirty |= $composer2.changed(str) ? 32 : 16;
        } else {
            str = title;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            eyebrow2 = eyebrow;
        } else if (($changed & 384) == 0) {
            eyebrow2 = eyebrow;
            $dirty |= $composer2.changed(eyebrow2) ? 256 : 128;
        } else {
            eyebrow2 = eyebrow;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            hint2 = hint;
        } else if (($changed & 3072) == 0) {
            hint2 = hint;
            $dirty |= $composer2.changed(hint2) ? 2048 : 1024;
        } else {
            hint2 = hint;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            wordmark2 = wordmark;
        } else if (($changed & 24576) == 0) {
            wordmark2 = wordmark;
            $dirty |= $composer2.changed(wordmark2) ? 16384 : 8192;
        } else {
            wordmark2 = wordmark;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function02 = function0;
        } else if ((196608 & $changed) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 131072 : 65536;
        } else {
            function02 = function0;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 1048576 : 524288;
        }
        if ($composer2.shouldExecute(($dirty & 599187) != 599186, $dirty & 1)) {
            Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            String title3 = i3 != 0 ? null : str;
            if (i4 != 0) {
                eyebrow2 = null;
            }
            if (i5 != 0) {
                hint2 = null;
            }
            if (i6 != 0) {
                wordmark2 = false;
            }
            Function0 onBack3 = i7 != 0 ? null : function02;
            Function2 trailing2 = i8 != 0 ? null : function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1432046249, $dirty, -1, "com.example.wayfindr.ui.components.ScreenHeader (WayfindrComponents.kt:361)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier m847defaultMinSizeVpY3zN4$default = SizeKt.m847defaultMinSizeVpY3zN4$default(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(18), 7, null), 0.0f, Dp.m7908constructorimpl(44), 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            Modifier modifier4 = modifier3;
            int $dirty2 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m847defaultMinSizeVpY3zN4$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i9 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function03 = constructor;
                $composer2.createNode(function03);
            } else {
                function03 = constructor;
                $composer2.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer2);
            Updater.m4372setimpl(m4364constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i10 = (i9 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i11 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 507966818, "C376@12365L1463:WayfindrComponents.kt#7crva5");
            if (onBack3 != null) {
                $composer2.startReplaceGroup(507939568);
                ComposerKt.sourceInformation($composer2, "370@12173L173");
                m8358WayfindrIconBtnN59uMVk(ChevronLeftKt.getChevronLeft(Icons.Outlined.INSTANCE), onBack3, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, Dp.m7908constructorimpl(12), 0.0f, 11, null), 0.0f, false, null, $composer2, (($dirty2 >> 12) & 112) | 384, 56);
                onBack2 = onBack3;
            } else {
                onBack2 = onBack3;
                $composer2.startReplaceGroup(495870741);
            }
            $composer2.endReplaceGroup();
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            Function0 onBack4 = onBack2;
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, weight$default);
            Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i12 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function04 = constructor2;
                $composer2.createNode(function04);
            } else {
                function04 = constructor2;
                $composer2.useNode();
            }
            Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer2);
            Updater.m4372setimpl(m4364constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i13 = (i12 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i14 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 1047925130, "C:WayfindrComponents.kt#7crva5");
            if (wordmark2) {
                $composer2.startReplaceGroup(1047910900);
                ComposerKt.sourceInformation($composer2, "378@12436L475");
                Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier5 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, modifier5);
                Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i15 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function06 = constructor3;
                    $composer2.createNode(function06);
                } else {
                    function06 = constructor3;
                    $composer2.useNode();
                }
                Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl3, rowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i16 = (i15 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i17 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1236608338, "C379@12510L254,385@12785L28,386@12834L59:WayfindrComponents.kt#7crva5");
                TextKt.m3069TextNvy7gAk("WayfindR", null, ColorKt.getTextHi(), null, TextUnitKt.getSp(21), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistMonoFamily(), TextUnitKt.getSp(0.14d), null, null, 0L, 0, false, 0, 0, null, null, $composer2, 114844038, 0, 261674);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), $composer2, 6);
                BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(6)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getGold(), null, 2, null), $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
                composer = $composer2;
                hint4 = hint2;
            } else {
                $composer2.startReplaceGroup(1048431576);
                ComposerKt.sourceInformation($composer2, "389@12949L855");
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier6 = Modifier.INSTANCE;
                composer = $composer2;
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer2, modifier6);
                Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i18 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function05 = constructor4;
                    $composer2.createNode(function05);
                } else {
                    function05 = constructor4;
                    $composer2.useNode();
                }
                Composer m4364constructorimpl4 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl4, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i19 = (i18 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i20 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1226566734, "C:WayfindrComponents.kt#7crva5");
                if (eyebrow2 != null) {
                    $composer2.startReplaceGroup(316661246);
                    ComposerKt.sourceInformation($composer2, "390@12999L49");
                    Eyebrow(eyebrow2, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(3), 7, null), $composer2, (($dirty2 >> 6) & 14) | 48, 0);
                } else {
                    $composer2.startReplaceGroup(1213666517);
                }
                $composer2.endReplaceGroup();
                if (title3 != null) {
                    $composer2.startReplaceGroup(1226659733);
                    ComposerKt.sourceInformation($composer2, "392@13114L240");
                    TextKt.m3069TextNvy7gAk(title3, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(23), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.46d), null, null, 0L, 0, false, 0, 0, null, null, $composer2, (($dirty2 >> 3) & 14) | 14180736, 0, 261674);
                } else {
                    $composer2.startReplaceGroup(1213666517);
                }
                $composer2.endReplaceGroup();
                if (hint2 != null) {
                    $composer2.startReplaceGroup(1227079907);
                    ComposerKt.sourceInformation($composer2, "400@13538L226");
                    hint4 = hint2;
                    TextKt.m3069TextNvy7gAk(hint4, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(3), Dp.m7908constructorimpl(8), 0.0f, 9, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, TextUnitKt.getSp(15), 0, false, 0, 0, null, null, $composer2, (($dirty2 >> 9) & 14) | 12607920, 48, 259944);
                } else {
                    hint4 = hint2;
                    $composer2.startReplaceGroup(1213666517);
                }
                $composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (trailing2 == null) {
                $composer2.startReplaceGroup(509608204);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-1369034347);
                ComposerKt.sourceInformation($composer2, "408@13847L8");
                trailing2.invoke($composer2, Integer.valueOf(($dirty2 >> 18) & 14));
                $composer2.endReplaceGroup();
                Unit unit = Unit.INSTANCE;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
            eyebrow3 = eyebrow2;
            hint3 = hint4;
            onBack = onBack4;
            trailing = trailing2;
            title2 = title3;
            wordmark3 = wordmark2;
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            title2 = str;
            eyebrow3 = eyebrow2;
            hint3 = hint2;
            onBack = function02;
            trailing = function2;
            wordmark3 = wordmark2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrComponentsKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WayfindrComponentsKt.ScreenHeader$lambda$29(Modifier.this, title2, eyebrow3, hint3, wordmark3, onBack, trailing, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
