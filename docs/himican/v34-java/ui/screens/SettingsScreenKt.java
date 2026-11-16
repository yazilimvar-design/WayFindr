package com.example.wayfindr.ui.screens;

import android.content.Context;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScope;
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
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import com.example.wayfindr.dash.DarkSchedule;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.VoiceManager;
import com.example.wayfindr.dash.nav.VoiceMode;
import com.example.wayfindr.data.UpdateChecker;
import com.example.wayfindr.media.MediaInfoProvider;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.ChipTone;
import com.example.wayfindr.ui.components.WayfindrComponentsKt;
import com.example.wayfindr.ui.screens.SettingsScreenKt$;
import com.example.wayfindr.ui.screens.SettingsScreenKt$SettingsScreen$lambda$86$lambda$68$lambda$67$;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.viewmodel.ConnectionState;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsScreen.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class SettingsScreenKt {

    /* compiled from: SettingsScreen.kt */
    final class MediaCard implements Function3 {
        private final MutableState answer;
        private final Context ctx;
        private final ManagedActivityResultLauncher launcher;
        private final MutableState media;

        public MediaCard(Context context, ManagedActivityResultLauncher managedActivityResultLauncher, MutableState mutableState, MutableState mutableState2) {
            this.ctx = context;
            this.launcher = managedActivityResultLauncher;
            this.media = mutableState;
            this.answer = mutableState2;
        }

        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            Unit SettingsScreen$lambda$86$lambda$78 = SettingsScreenKt.SettingsScreen$lambda$86$lambda$78(this.ctx, this.launcher, this.media, this.answer, (ColumnScope) obj, composer, (((Integer) obj3).intValue() & (-4)) | 1);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6.0f), 0.0f, 2, (Object) null), composer, 6, 0);
            ImageVector dash = WayfindrIcons.INSTANCE.getDash();
            OffRow offRow = new OffRow(1);
            SettingsScreenKt.SettingRow(dash, "Konsol duvar kağıdı", "Beklemedeyken konsolda fotoğraf, müzik şeridi, albüm kapağı", offRow, false, offRow, composer, 0, 16);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6.0f), 0.0f, 2, (Object) null), composer, 6, 0);
            ImageVector download = WayfindrIcons.INSTANCE.getDownload();
            OffRow offRow2 = new OffRow(0);
            SettingsScreenKt.SettingRow(download, "Çevrimdışı harita", "Harita dosyası yükle, aç/kapat, sil", offRow2, false, offRow2, composer, 0, 16);
            return SettingsScreen$lambda$86$lambda$78;
        }
    }

    /* compiled from: SettingsScreen.kt */
    final class OffRow implements Function0, Function2 {
        private final int mode;

        public OffRow(int i) {
            this.mode = i;
        }

        public final Object invoke() {
            NavPlus.offMode = this.mode;
            NavPlus.openOffline();
            return Unit.INSTANCE;
        }

        public final Object invoke(Object obj, Object obj2) {
            return Unit.INSTANCE;
        }
    }

    private static final void SectionLabel(String str, Composer composer, int i) {
        Composer startRestartGroup = composer.startRestartGroup(441747781);
        ComposerKt.sourceInformation(startRestartGroup, "C(SectionLabel)N(label)222@12675L74:SettingsScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changed(str) ? 4 : 2;
        }
        if (startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(441747781, i2, -1, "com.example.wayfindr.ui.screens.SectionLabel (SettingsScreen.kt:221)");
            }
            WayfindrComponentsKt.Eyebrow(str, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(4), Dp.constructor-impl(22), 0.0f, Dp.constructor-impl(9), 4, (Object) null), startRestartGroup, i2 & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new SettingsScreenKt$.ExternalSyntheticLambda22(str, i));
        }
    }

    static final Unit SectionLabel$lambda$88(String str, int i, Composer composer, int i2) {
        SectionLabel(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SettingRow(ImageVector imageVector, String str, String str2, Function2 function2, boolean z, Function0 function0, Composer composer, int i, int i2) {
        ImageVector imageVector2;
        String str3;
        Function0 function02;
        String str4;
        Function0 function03;
        boolean z2;
        Function0 function04;
        Function0 function05;
        Function0 function06;
        Function0 function07;
        String str5;
        Composer startRestartGroup = composer.startRestartGroup(1650730910);
        ComposerKt.sourceInformation(startRestartGroup, "C(SettingRow)N(icon,title,sub,control,last,onClick)234@12963L933:SettingsScreen.kt#cef2n4");
        int i3 = i;
        if ((i & 6) == 0) {
            imageVector2 = imageVector;
            i3 |= startRestartGroup.changed(imageVector2) ? 4 : 2;
        } else {
            imageVector2 = imageVector;
        }
        if ((i & 48) == 0) {
            i3 |= startRestartGroup.changed(str) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 != 0) {
            i3 |= 384;
            str3 = str2;
        } else if ((i & 384) == 0) {
            str3 = str2;
            i3 |= startRestartGroup.changed(str3) ? 256 : 128;
        } else {
            str3 = str2;
        }
        if ((i & 3072) == 0) {
            i3 |= startRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        int i5 = i2 & 32;
        if (i5 != 0) {
            i3 |= 196608;
            function02 = function0;
        } else if ((196608 & i) == 0) {
            function02 = function0;
            i3 |= startRestartGroup.changedInstance(function02) ? 131072 : 65536;
        } else {
            function02 = function0;
        }
        if (startRestartGroup.shouldExecute((66707 & i3) != 66706, i3 & 1)) {
            String str6 = i4 != 0 ? null : str3;
            boolean z3 = (i2 & 16) != 0 ? false : z;
            Function0 function08 = i5 != 0 ? null : function02;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1650730910, i3, -1, "com.example.wayfindr.ui.screens.SettingRow (SettingsScreen.kt:233)");
            }
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            if (function08 != null) {
                function04 = function08;
                fillMaxWidth$default = ClickableKt.clickable-oSLSa3U$default(fillMaxWidth$default, false, (String) null, (Role) null, (MutableInteractionSource) null, function08, 15, (Object) null);
            } else {
                function04 = function08;
            }
            Modifier modifier = PaddingKt.padding-VpY3zN4(fillMaxWidth$default, Dp.constructor-impl(6), Dp.constructor-impl(13));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            int i6 = i3;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
            String str7 = str6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                function05 = constructor;
                startRestartGroup.createNode(function05);
            } else {
                function05 = constructor;
                startRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            boolean z4 = z3;
            Updater.init-impl(composer2, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i9 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -594260516, "C241@13237L315,247@13561L29,248@13599L273,252@13881L9:SettingsScreen.kt#cef2n4");
            Alignment center = Alignment.Companion.getCenter();
            Modifier modifier2 = BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(38)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(11))), ColorKt.getSurf2(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), ColorKt.getLine(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(11)));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier2);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                function06 = constructor2;
                startRestartGroup.createNode(function06);
            } else {
                function06 = constructor2;
                startRestartGroup.useNode();
            }
            Composer composer3 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer3, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode2), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            int i12 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1590988487, "C245@13456L86:SettingsScreen.kt#cef2n4");
            IconKt.Icon-ww6aTOc(imageVector2, (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(19)), ColorKt.getTextMid(), startRestartGroup, (i6 & 14) | 432, 0);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(14)), startRestartGroup, 6);
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap3 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(startRestartGroup, weight$default);
            Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
            int i13 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                function07 = constructor3;
                startRestartGroup.createNode(function07);
            } else {
                function07 = constructor3;
                startRestartGroup.useNode();
            }
            Composer composer4 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer4, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer4, Integer.valueOf(hashCode3), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer4, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer4, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i15 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2071840254, "C249@13641L107:SettingsScreen.kt#cef2n4");
            TextKt.Text-Nvy7gAk(str, (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(14.5d), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, ((i6 >> 3) & 14) | 1597440, 0, 261930);
            if (str7 != null) {
                startRestartGroup.startReplaceGroup(-2071708598);
                ComposerKt.sourceInformation(startRestartGroup, "250@13778L84");
                str5 = str7;
                TextKt.Text-Nvy7gAk(str5, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, Dp.constructor-impl(2), 0.0f, 0.0f, 13, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, ((i6 >> 6) & 14) | 24624, 0, 262120);
            } else {
                str5 = str7;
                startRestartGroup.startReplaceGroup(-2085379970);
            }
            startRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            function2.invoke(startRestartGroup, Integer.valueOf((i6 >> 9) & 14));
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            str4 = str5;
            function03 = function04;
            z2 = z4;
        } else {
            startRestartGroup.skipToGroupEnd();
            str4 = str3;
            function03 = function02;
            z2 = z;
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new SettingsScreenKt$.ExternalSyntheticLambda13(imageVector, str, str4, function2, z2, function03, i, i2));
        }
    }

    static final Unit SettingRow$lambda$93(ImageVector imageVector, String str, String str2, Function2 function2, boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        SettingRow(imageVector, str, str2, function2, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void SettingsScreen(ConnectionState connectionState, Function1 function1, Function0 function0, Composer composer, int i) {
        ConnectionState connectionState2;
        Composer composer2;
        State state;
        MutableState mutableState;
        String str;
        Function0 function02;
        Function0 function03 = function0;
        Intrinsics.checkNotNullParameter(connectionState, "conn");
        Intrinsics.checkNotNullParameter(function1, "onConnChange");
        Intrinsics.checkNotNullParameter(function03, "onBack");
        Composer startRestartGroup = composer.startRestartGroup(-1712294710);
        ComposerKt.sourceInformation(startRestartGroup, "C(SettingsScreen)N(conn,onConnChange,onBack)38@1517L7,39@1546L32,40@1632L16,41@1672L33,42@1729L33,43@1786L40,46@1934L64,47@2038L16,57@2395L21,54@2304L10309:SettingsScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changed(((Enum) connectionState).ordinal()) ? 4 : 2;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function03) ? 256 : 128;
        }
        int i3 = i2;
        if (startRestartGroup.shouldExecute((i3 & 131) != 130, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1712294710, i3, -1, "com.example.wayfindr.ui.screens.SettingsScreen (SettingsScreen.kt:37)");
            }
            CompositionLocal localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -542735894, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                DashConfig dashConfig = DashConfig.Companion.get(context);
                startRestartGroup.updateRememberedValue(dashConfig);
                rememberedValue = dashConfig;
            }
            DashConfig dashConfig2 = (DashConfig) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            State collectAsState = SnapshotStateKt.collectAsState(dashConfig2.getAutoConnectOnStart(), (CoroutineContext) null, startRestartGroup, 0, 1);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -542731861, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                state = collectAsState;
                MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(true, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default);
                rememberedValue2 = mutableStateOf$default;
            } else {
                state = collectAsState;
            }
            MutableState mutableState2 = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -542730037, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue3 = startRestartGroup.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default2 = SnapshotStateKt.mutableStateOf$default(true, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default2);
                rememberedValue3 = mutableStateOf$default2;
            }
            MutableState mutableState3 = (MutableState) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -542728206, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue4 = startRestartGroup.rememberedValue();
            if (rememberedValue4 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default3 = SnapshotStateKt.mutableStateOf$default("Kilometre", (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default3);
                rememberedValue4 = mutableStateOf$default3;
            }
            MutableState mutableState4 = (MutableState) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -542723446, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue5 = startRestartGroup.rememberedValue();
            if (rememberedValue5 == Composer.Companion.getEmpty()) {
                mutableState = mutableState4;
                VoiceManager voiceManager = VoiceManager.Companion.get(context);
                startRestartGroup.updateRememberedValue(voiceManager);
                rememberedValue5 = voiceManager;
            } else {
                mutableState = mutableState4;
            }
            VoiceManager voiceManager2 = (VoiceManager) rememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            switch (WhenMappings.$EnumSwitchMapping$0[SettingsScreen$lambda$12(SnapshotStateKt.collectAsState(voiceManager2.getMode(), (CoroutineContext) null, startRestartGroup, 0, 1)).ordinal()]) {
                case 1:
                    str = "Kapalı";
                    break;
                case 2:
                    str = "Uyarı sesi";
                    break;
                case 3:
                    str = "Tam TTS";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            Modifier modifier = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(24), 7, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                function02 = constructor;
                startRestartGroup.createNode(function02);
            } else {
                function02 = constructor;
                startRestartGroup.useNode();
            }
            Composer composer3 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            String str2 = str;
            Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i6 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -584712428, "C61@2500L159,64@2669L24,65@2767L1178,65@2702L1243,80@3955L31,81@4012L32,82@4091L16,83@4158L16,84@4223L16,85@4288L16,86@4363L16,87@4442L16,88@4519L16,89@4609L3295,89@4544L3360,128@7914L42,131@8212L7,135@8466L94,136@8594L44,137@8680L434,137@8647L467,147@9225L46,147@9146L125,193@11461L34,194@11570L440,194@11504L506,204@12020L24,205@12119L117,205@12053L183,209@12246L30,211@12303L76,212@12388L219:SettingsScreen.kt#cef2n4");
            MutableState mutableState5 = mutableState;
            composer2 = startRestartGroup;
            function03 = function0;
            WayfindrComponentsKt.ScreenHeader((Modifier) null, "Ayarlar", (String) null, "Bağlantı, ekran kapalıyken aktarım, sesli yönlendirme, birimler ve medya/arama izinleri.", false, function03, (Function2) null, startRestartGroup, ((i3 << 9) & 458752) | 3120, 85);
            SectionLabel("Bağlantı", startRestartGroup, 6);
            connectionState2 = connectionState;
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(6), (Function0) null, ComposableLambdaKt.rememberComposableLambda(1113277849, true, new SettingsScreenKt$.ExternalSyntheticLambda3(connectionState2, dashConfig2, state, context), startRestartGroup, 54), startRestartGroup, 24966, 10);
            SectionLabel("Sürüş sırasında", startRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1819938700, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue6 = startRestartGroup.rememberedValue();
            if (rememberedValue6 == Composer.Companion.getEmpty()) {
                DashConfig dashConfig3 = DashConfig.Companion.get(context);
                startRestartGroup.updateRememberedValue(dashConfig3);
                rememberedValue6 = dashConfig3;
            }
            DashConfig dashConfig4 = (DashConfig) rememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(6), (Function0) null, ComposableLambdaKt.rememberComposableLambda(497943682, true, new SettingsScreenKt$.ExternalSyntheticLambda4(mutableState2, mutableState3, SnapshotStateKt.collectAsState(dashConfig4.getMapDarkAuto(), (CoroutineContext) null, startRestartGroup, 0, 1), dashConfig4, SnapshotStateKt.collectAsState(dashConfig4.getMapDarkMode(), (CoroutineContext) null, startRestartGroup, 0, 1), SnapshotStateKt.collectAsState(dashConfig4.getInvertZoom(), (CoroutineContext) null, startRestartGroup, 0, 1), SnapshotStateKt.collectAsState(dashConfig4.getPickerDoubleTap(), (CoroutineContext) null, startRestartGroup, 0, 1), SnapshotStateKt.collectAsState(dashConfig4.getShowJoystickTrail(), (CoroutineContext) null, startRestartGroup, 0, 1), SnapshotStateKt.collectAsState(dashConfig4.getShowPhoneBattery(), (CoroutineContext) null, startRestartGroup, 0, 1), SnapshotStateKt.collectAsState(dashConfig4.getSharperMap(), (CoroutineContext) null, startRestartGroup, 0, 1)), startRestartGroup, 54), startRestartGroup, 24966, 10);
            SectionLabel("Konsolda medya ve aramalar", startRestartGroup, 6);
            CompositionLocal localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume2 = startRestartGroup.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) consume2;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1819796110, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue7 = startRestartGroup.rememberedValue();
            if (rememberedValue7 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default4 = SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(MediaInfoProvider.Companion.isAccessGranted(context)), (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default4);
                rememberedValue7 = mutableStateOf$default4;
            }
            MutableState mutableState6 = (MutableState) rememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1819792064, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue8 = startRestartGroup.rememberedValue();
            if (rememberedValue8 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default5 = SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(SettingsScreen$lambda$86$answerGranted(context)), (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default5);
                rememberedValue8 = mutableStateOf$default5;
            }
            MutableState mutableState7 = (MutableState) rememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1819788922, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(context) | startRestartGroup.changedInstance(lifecycleOwner);
            Object rememberedValue9 = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue9 == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new SettingsScreenKt$.ExternalSyntheticLambda5(lifecycleOwner, context, mutableState6, mutableState7);
                startRestartGroup.updateRememberedValue(externalSyntheticLambda5);
                rememberedValue9 = externalSyntheticLambda5;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.DisposableEffect(lifecycleOwner, (Function1) rememberedValue9, startRestartGroup, 0);
            ActivityResultContract requestPermission = new ActivityResultContracts.RequestPermission();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1819771870, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue10 = startRestartGroup.rememberedValue();
            if (rememberedValue10 == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda6 externalSyntheticLambda6 = new SettingsScreenKt$.ExternalSyntheticLambda6(mutableState7);
                startRestartGroup.updateRememberedValue(externalSyntheticLambda6);
                rememberedValue10 = externalSyntheticLambda6;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ManagedActivityResultLauncher rememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission, (Function1) rememberedValue10, startRestartGroup, 48);
            startRestartGroup.startReplaceGroup(-1819767952);
            ComposerKt.sourceInformation(startRestartGroup, "150@9334L476");
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(6), (Function0) null, new MediaCard(context, rememberLauncherForActivityResult, mutableState6, mutableState7), startRestartGroup, 24966, 10);
            startRestartGroup.endReplaceGroup();
            SectionLabel("Ses ve yönlendirme", startRestartGroup, 6);
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(14), (Function0) null, ComposableLambdaKt.rememberComposableLambda(-186286623, true, new SettingsScreenKt$.ExternalSyntheticLambda7(str2, voiceManager2), startRestartGroup, 54), startRestartGroup, 24966, 10);
            SectionLabel("Birimler", startRestartGroup, 6);
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(14), (Function0) null, ComposableLambdaKt.rememberComposableLambda(-870516928, true, new SettingsScreenKt$.ExternalSyntheticLambda8(mutableState5), startRestartGroup, 54), startRestartGroup, 24966, 10);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), startRestartGroup, 6);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1819673344, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue11 = startRestartGroup.rememberedValue();
            if (rememberedValue11 == Composer.Companion.getEmpty()) {
                String currentVersionName = UpdateChecker.INSTANCE.currentVersionName(context);
                startRestartGroup.updateRememberedValue(currentVersionName);
                rememberedValue11 = currentVersionName;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            TextKt.Text-Nvy7gAk("Wayfindr v" + ((String) rememberedValue11), PaddingKt.padding-qDBjuR0$default(columnScope.align(Modifier.Companion, Alignment.Companion.getCenterHorizontally()), 0.0f, Dp.constructor-impl(10), 0.0f, 0.0f, 13, (Object) null), ColorKt.getTextDis(), (TextAutoSize) null, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, 24576, 0, 261992);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            connectionState2 = connectionState;
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new SettingsScreenKt$.ExternalSyntheticLambda9(connectionState2, function1, function03, i));
        }
    }

    private static final boolean SettingsScreen$lambda$1(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final VoiceMode SettingsScreen$lambda$12(State state) {
        return (VoiceMode) state.getValue();
    }

    private static final boolean SettingsScreen$lambda$3(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void SettingsScreen$lambda$4(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SettingsScreen$lambda$6(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void SettingsScreen$lambda$7(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SettingsScreen$lambda$86$answerGranted(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ANSWER_PHONE_CALLS") == 0;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22(ConnectionState connectionState, DashConfig dashConfig, State state, Context context, ColumnScope columnScope, Composer composer, int i) {
        String str;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C68@3015L162,66@2781L397,69@3191L52,71@3377L72,70@3256L194,72@3463L52,73@3544L32,74@3631L16,77@3856L65,75@3660L275:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1113277849, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:66)");
            }
            ImageVector bt = WayfindrIcons.INSTANCE.getBt();
            switch (WhenMappings.$EnumSwitchMapping$1[connectionState.ordinal()]) {
                case 1:
                    str = "Bağlı";
                    break;
                case 2:
                    str = "Bağlanıyor…";
                    break;
                case 3:
                    str = "Bağlı değil";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            SettingRow(bt, "Konsol ekranı", str, ComposableLambdaKt.rememberComposableLambda(-1426613793, true, new SettingsScreenKt$.ExternalSyntheticLambda0(connectionState), composer, 54), false, null, composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getSync(), "Başlangıçta otomatik bağlan", "Motosiklet yakındayken bağlan", ComposableLambdaKt.rememberComposableLambda(-971327672, true, new SettingsScreenKt$.ExternalSyntheticLambda1(dashConfig, state), composer, 54), false, null, composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            ComposerKt.sourceInformationMarkerStart(composer, 413181081, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                DashConfig dashConfig2 = DashConfig.Companion.get(context);
                composer.updateRememberedValue(dashConfig2);
                rememberedValue = dashConfig2;
            }
            DashConfig dashConfig3 = (DashConfig) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composer);
            State collectAsState = SnapshotStateKt.collectAsState(dashConfig3.getHighQualityVideo(), (CoroutineContext) null, composer, 0, 1);
            SettingRow(WayfindrIcons.INSTANCE.getZap(), "Daha yüksek video kalitesi", SettingsScreen$lambda$86$lambda$22$lambda$18(collectAsState) ? "400 kbps · daha net görüntü, konsola daha fazla yük" : "Dengeli · 200 kbps", ComposableLambdaKt.rememberComposableLambda(-70259737, true, new SettingsScreenKt$.ExternalSyntheticLambda2(dashConfig3, collectAsState), composer, 54), true, null, composer, 27696, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$13(ConnectionState connectionState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C68@3017L158:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1426613793, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:68)");
            }
            WayfindrComponentsKt.WayfindrChip(connectionState == ConnectionState.Connected ? "Bağlı" : "Kapalı", connectionState == ConnectionState.Connected ? ChipTone.Gold : ChipTone.Off, true, (ImageVector) null, (Modifier) null, composer, 384, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$16(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C71@3407L40,71@3379L68:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-971327672, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:71)");
            }
            boolean SettingsScreen$lambda$1 = SettingsScreen$lambda$1(state);
            ComposerKt.sourceInformationMarkerStart(composer, -1437988560, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda11 externalSyntheticLambda11 = new SettingsScreenKt$.ExternalSyntheticLambda11(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda11);
                rememberedValue = externalSyntheticLambda11;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$1, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$16$lambda$15$lambda$14(DashConfig dashConfig, boolean z) {
        dashConfig.setAutoConnectOnStart(z);
        return Unit.INSTANCE;
    }

    private static final boolean SettingsScreen$lambda$86$lambda$22$lambda$18(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$21(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C77@3882L37,77@3858L61:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-70259737, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:77)");
            }
            boolean SettingsScreen$lambda$86$lambda$22$lambda$18 = SettingsScreen$lambda$86$lambda$22$lambda$18(state);
            ComposerKt.sourceInformationMarkerStart(composer, 1199870348, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda37 externalSyntheticLambda37 = new SettingsScreenKt$.ExternalSyntheticLambda37(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda37);
                rememberedValue = externalSyntheticLambda37;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$22$lambda$18, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$21$lambda$20$lambda$19(DashConfig dashConfig, boolean z) {
        dashConfig.setHighQualityVideo(z);
        return Unit.INSTANCE;
    }

    private static final boolean SettingsScreen$lambda$86$lambda$24(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$25(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$26(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$27(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$28(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$29(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$30(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    static final Unit SettingsScreen$lambda$86$lambda$58(MutableState mutableState, MutableState mutableState2, State state, DashConfig dashConfig, State state2, State state3, State state4, State state5, State state6, State state7, ColumnScope columnScope, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C91@4748L48,90@4623L174,92@4810L52,94@4988L48,93@4875L162,95@5050L52,98@5350L65,96@5115L301,99@5429L52,104@5917L95,102@5674L339,105@6026L52,108@6308L63,106@6091L281,109@6385L52,113@6766L73,110@6450L390,114@6853L52,117@7102L77,115@6918L262,118@7193L52,121@7443L75,119@7258L261,122@7532L52,125@7817L63,123@7597L297:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(497943682, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:90)");
            }
            SettingRow(WayfindrIcons.INSTANCE.getPower(), "Telefon ekranını kapat", "Harita konsola aktarılmaya devam eder", ComposableLambdaKt.rememberComposableLambda(-796500152, true, new SettingsScreenKt$.ExternalSyntheticLambda27(mutableState), composer, 54), false, null, composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getDash(), "Konsolu uyanık tut", "Konsolun uykuya geçmesini önle", ComposableLambdaKt.rememberComposableLambda(-1353202063, true, new SettingsScreenKt$.ExternalSyntheticLambda28(mutableState2), composer, 54), false, null, composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getMoon(), "Haritada otomatik karanlık mod", SettingsScreen$lambda$86$lambda$25(state) ? "Saat 19.00–05.00 arasında karanlık (telefon saatine göre)" : "Kapalı: aşağıdaki anahtarla elle ayarlayın", ComposableLambdaKt.rememberComposableLambda(1284656976, true, new SettingsScreenKt$.ExternalSyntheticLambda29(dashConfig, state), composer, 54), false, null, composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getMoon(), "Konsol haritası: karanlık mod", SettingsScreen$lambda$86$lambda$25(state) ? "Saati izliyor · elle ayarlamak için dokunun" : "Elle · aynı önbelleğe alınmış harita kutucuklarında gece görünümü", ComposableLambdaKt.rememberComposableLambda(-372451281, true, new SettingsScreenKt$.ExternalSyntheticLambda30(DarkSchedule.effective$default(DarkSchedule.INSTANCE, SettingsScreen$lambda$86$lambda$25(state), SettingsScreen$lambda$86$lambda$24(state2), false, 4, (Object) null), dashConfig), composer, 54), false, null, composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getSync(), "Joystick yakınlaştırmasını tersine çevir", SettingsScreen$lambda$86$lambda$26(state3) ? "Sağa: yakınlaştır · sola: uzaklaştır" : "Sola: yakınlaştır · sağa: uzaklaştır", ComposableLambdaKt.rememberComposableLambda(-2029559538, true, new SettingsScreenKt$.ExternalSyntheticLambda31(dashConfig, state3), composer, 54), false, null, composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getPin(), "Sola/sağa iki kez basınca hedef listesi açılsın", SettingsScreen$lambda$86$lambda$28(state4) ? "Açık: sola/sağa iki kez dokunun veya aşağıya iki kez basın; kayıtlı liste açılır" : "Kapalı: aşağıya 3 sn içinde iki kez basınca kayıtlı liste açılır", ComposableLambdaKt.rememberComposableLambda(608299501, true, new SettingsScreenKt$.ExternalSyntheticLambda32(dashConfig, state4), composer, 54), false, null, composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getTarget(), "Joystick basışlarını göster", "Konsolun alt ortasında son 4 basışı D / R / L olarak, her biri 2 sn göster", ComposableLambdaKt.rememberComposableLambda(-1048808756, true, new SettingsScreenKt$.ExternalSyntheticLambda33(dashConfig, state5), composer, 54), false, null, composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getTarget(), "Telefon pilini konsolda göster", "Tahmini varış süresinin üstünde pil yüzdesi; şarj olurken şimşek simgesi", ComposableLambdaKt.rememberComposableLambda(1589050283, true, new SettingsScreenKt$.ExternalSyntheticLambda34(dashConfig, state6), composer, 54), false, null, composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getTarget(), "Daha keskin harita", SettingsScreen$lambda$86$lambda$27(state7) ? "2× çizim, yüksek çözünürlüklü harita kutucukları · daha fazla bellek" : "Standart harita kutucukları", ComposableLambdaKt.rememberComposableLambda(-68057974, true, new SettingsScreenKt$.ExternalSyntheticLambda35(dashConfig, state7), composer, 54), true, null, composer, 27696, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$33(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C91@4776L18,91@4750L44:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-796500152, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:91)");
            }
            boolean SettingsScreen$lambda$3 = SettingsScreen$lambda$3(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, -932466470, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda21 externalSyntheticLambda21 = new SettingsScreenKt$.ExternalSyntheticLambda21(mutableState);
                composer.updateRememberedValue(externalSyntheticLambda21);
                rememberedValue = externalSyntheticLambda21;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$3, (Function1) rememberedValue, composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$33$lambda$32$lambda$31(MutableState mutableState, boolean z) {
        SettingsScreen$lambda$4(mutableState, z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$36(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C94@5016L18,94@4990L44:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1353202063, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:94)");
            }
            boolean SettingsScreen$lambda$6 = SettingsScreen$lambda$6(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, 947909059, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda23 externalSyntheticLambda23 = new SettingsScreenKt$.ExternalSyntheticLambda23(mutableState);
                composer.updateRememberedValue(externalSyntheticLambda23);
                rememberedValue = externalSyntheticLambda23;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$6, (Function1) rememberedValue, composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$36$lambda$35$lambda$34(MutableState mutableState, boolean z) {
        SettingsScreen$lambda$7(mutableState, z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$39(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C98@5380L33,98@5352L61:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1284656976, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:98)");
            }
            boolean SettingsScreen$lambda$86$lambda$25 = SettingsScreen$lambda$86$lambda$25(state);
            ComposerKt.sourceInformationMarkerStart(composer, 1899740977, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda38 externalSyntheticLambda38 = new SettingsScreenKt$.ExternalSyntheticLambda38(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda38);
                rememberedValue = externalSyntheticLambda38;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$25, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$39$lambda$38$lambda$37(DashConfig dashConfig, boolean z) {
        dashConfig.setMapDarkAuto(z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$42(boolean z, DashConfig dashConfig, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C104@5943L67,104@5919L91:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-372451281, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:104)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1443394574, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda36 externalSyntheticLambda36 = new SettingsScreenKt$.ExternalSyntheticLambda36(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda36);
                rememberedValue = externalSyntheticLambda36;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(z, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$42$lambda$41$lambda$40(DashConfig dashConfig, boolean z) {
        dashConfig.setMapDarkMode(z);
        dashConfig.setMapDarkAuto(false);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$45(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C108@6337L32,108@6310L59:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2029559538, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:108)");
            }
            boolean SettingsScreen$lambda$86$lambda$26 = SettingsScreen$lambda$86$lambda$26(state);
            ComposerKt.sourceInformationMarkerStart(composer, -491562674, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda12 externalSyntheticLambda12 = new SettingsScreenKt$.ExternalSyntheticLambda12(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda12);
                rememberedValue = externalSyntheticLambda12;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$26, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$45$lambda$44$lambda$43(DashConfig dashConfig, boolean z) {
        dashConfig.setInvertZoom(z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$48(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C113@6800L37,113@6768L69:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(608299501, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:113)");
            }
            boolean SettingsScreen$lambda$86$lambda$28 = SettingsScreen$lambda$86$lambda$28(state);
            ComposerKt.sourceInformationMarkerStart(composer, 460269330, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda25 externalSyntheticLambda25 = new SettingsScreenKt$.ExternalSyntheticLambda25(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda25);
                rememberedValue = externalSyntheticLambda25;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$28, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$48$lambda$47$lambda$46(DashConfig dashConfig, boolean z) {
        dashConfig.setPickerDoubleTap(z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$51(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C117@7138L39,117@7104L73:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1048808756, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:117)");
            }
            boolean SettingsScreen$lambda$86$lambda$29 = SettingsScreen$lambda$86$lambda$29(state);
            ComposerKt.sourceInformationMarkerStart(composer, 1412101235, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda26 externalSyntheticLambda26 = new SettingsScreenKt$.ExternalSyntheticLambda26(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda26);
                rememberedValue = externalSyntheticLambda26;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$29, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$51$lambda$50$lambda$49(DashConfig dashConfig, boolean z) {
        dashConfig.setShowJoystickTrail(z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$54(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C121@7478L38,121@7445L71:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1589050283, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:121)");
            }
            boolean SettingsScreen$lambda$86$lambda$30 = SettingsScreen$lambda$86$lambda$30(state);
            ComposerKt.sourceInformationMarkerStart(composer, -1931034255, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda16 externalSyntheticLambda16 = new SettingsScreenKt$.ExternalSyntheticLambda16(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda16);
                rememberedValue = externalSyntheticLambda16;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$30, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$54$lambda$53$lambda$52(DashConfig dashConfig, boolean z) {
        dashConfig.setShowPhoneBattery(z);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$57(DashConfig dashConfig, State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C125@7846L32,125@7819L59:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-68057974, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:125)");
            }
            boolean SettingsScreen$lambda$86$lambda$27 = SettingsScreen$lambda$86$lambda$27(state);
            ComposerKt.sourceInformationMarkerStart(composer, -979202614, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(dashConfig);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda14 externalSyntheticLambda14 = new SettingsScreenKt$.ExternalSyntheticLambda14(dashConfig);
                composer.updateRememberedValue(externalSyntheticLambda14);
                rememberedValue = externalSyntheticLambda14;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$27, (Function1) rememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$57$lambda$56$lambda$55(DashConfig dashConfig, boolean z) {
        dashConfig.setSharperMap(z);
        return Unit.INSTANCE;
    }

    private static final boolean SettingsScreen$lambda$86$lambda$60(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void SettingsScreen$lambda$86$lambda$61(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SettingsScreen$lambda$86$lambda$63(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void SettingsScreen$lambda$86$lambda$64(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final DisposableEffectResult SettingsScreen$lambda$86$lambda$68$lambda$67(LifecycleOwner lifecycleOwner, Context context, MutableState mutableState, MutableState mutableState2, DisposableEffectScope disposableEffectScope) {
        Intrinsics.checkNotNullParameter(disposableEffectScope, "$this$DisposableEffect");
        LifecycleObserver externalSyntheticLambda24 = new SettingsScreenKt$.ExternalSyntheticLambda24(context, mutableState, mutableState2);
        lifecycleOwner.getLifecycle().addObserver(externalSyntheticLambda24);
        return new SettingsScreenKt$SettingsScreen$lambda$86$lambda$68$lambda$67$.inlined.onDispose.1(lifecycleOwner, externalSyntheticLambda24);
    }

    static final void SettingsScreen$lambda$86$lambda$68$lambda$67$lambda$65(Context context, MutableState mutableState, MutableState mutableState2, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "e");
        if (event == Lifecycle.Event.ON_RESUME) {
            SettingsScreen$lambda$86$lambda$61(mutableState, MediaInfoProvider.Companion.isAccessGranted(context));
            SettingsScreen$lambda$86$lambda$64(mutableState2, SettingsScreen$lambda$86$answerGranted(context));
        }
    }

    static final Unit SettingsScreen$lambda$86$lambda$70$lambda$69(MutableState mutableState, boolean z) {
        SettingsScreen$lambda$86$lambda$64(mutableState, z);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0145, code lost:
    
        if (r11 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L51;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit SettingsScreen$lambda$86$lambda$78(Context context, ManagedActivityResultLauncher managedActivityResultLauncher, MutableState mutableState, MutableState mutableState2, ColumnScope columnScope, Composer composer, int i) {
        Function0 function0;
        Object obj;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C164@10183L231,159@9895L736,174@10644L52,180@10980L241,175@10709L732:SettingsScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= composer.changed(columnScope) ? 4 : 2;
        }
        int i3 = i2;
        if (composer.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1178376821, i3, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:159)");
            }
            ImageVector zap = WayfindrIcons.INSTANCE.getZap();
            String str = SettingsScreen$lambda$86$lambda$60(mutableState) ? "Etkin · sürüş sırasında şarkı bilgisi ve arayan gösterilir" : "Bildirim erişimine izin vermek için dokunun";
            Function2 rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1375422651, true, new SettingsScreenKt$.ExternalSyntheticLambda17(mutableState), composer, 54);
            Function0 function02 = null;
            if (SettingsScreen$lambda$86$lambda$60(mutableState)) {
                composer.startReplaceGroup(-2038228600);
                composer.endReplaceGroup();
                function0 = null;
            } else {
                composer.startReplaceGroup(-2038214277);
                ComposerKt.sourceInformation(composer, "171@10492L106");
                ComposerKt.sourceInformationMarkerStart(composer, -1451221505, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean changedInstance = ((i3 & 14) == 4) | composer.changedInstance(context);
                Object rememberedValue = composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                    SettingsScreenKt$.ExternalSyntheticLambda18 externalSyntheticLambda18 = new SettingsScreenKt$.ExternalSyntheticLambda18(columnScope, context);
                    composer.updateRememberedValue(externalSyntheticLambda18);
                    rememberedValue = externalSyntheticLambda18;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endReplaceGroup();
                function0 = (Function0) rememberedValue;
            }
            SettingRow(zap, "Çalan medya ve aramalar konsolda", str, rememberComposableLambda, false, function0, composer, 3120, 16);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 2, (Object) null), composer, 6, 0);
            ImageVector bt = WayfindrIcons.INSTANCE.getBt();
            String str2 = SettingsScreen$lambda$86$lambda$63(mutableState2) ? "Etkin · YUKARI yanıtlar, AŞAĞI reddeder" : "Aramaları yanıtlamak/reddetmek için dokunun";
            Function2 rememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(717417764, true, new SettingsScreenKt$.ExternalSyntheticLambda19(mutableState2), composer, 54);
            if (SettingsScreen$lambda$86$lambda$63(mutableState2)) {
                composer.startReplaceGroup(-2037394328);
            } else {
                composer.startReplaceGroup(-2037380966);
                ComposerKt.sourceInformation(composer, "188@11333L75");
                ComposerKt.sourceInformationMarkerStart(composer, -1451194624, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean changedInstance2 = composer.changedInstance(managedActivityResultLauncher);
                Object rememberedValue2 = composer.rememberedValue();
                if (!changedInstance2) {
                    obj = rememberedValue2;
                }
                Object externalSyntheticLambda20 = new SettingsScreenKt$.ExternalSyntheticLambda20(managedActivityResultLauncher);
                composer.updateRememberedValue(externalSyntheticLambda20);
                obj = externalSyntheticLambda20;
                function02 = (Function0) obj;
                ComposerKt.sourceInformationMarkerEnd(composer);
            }
            composer.endReplaceGroup();
            SettingRow(bt, "Aramaları joystick ile yanıtla", str2, rememberComposableLambda2, true, function02, composer, 27696, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$71(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C165@10205L191:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1375422651, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:165)");
            }
            WayfindrComponentsKt.WayfindrChip(SettingsScreen$lambda$86$lambda$60(mutableState) ? "Açık" : "Etkinleştir", SettingsScreen$lambda$86$lambda$60(mutableState) ? ChipTone.Gold : ChipTone.Off, true, (ImageVector) null, (Modifier) null, composer, 384, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$74$lambda$73(ColumnScope columnScope, Context context) {
        try {
            Result.Companion companion = Result.Companion;
            context.startActivity(MediaInfoProvider.Companion.accessSettingsIntent());
            Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$75(MutableState mutableState, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C181@11002L201:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(717417764, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:181)");
            }
            WayfindrComponentsKt.WayfindrChip(SettingsScreen$lambda$86$lambda$63(mutableState) ? "Açık" : "Etkinleştir", SettingsScreen$lambda$86$lambda$63(mutableState) ? ChipTone.Gold : ChipTone.Off, true, (ImageVector) null, (Modifier) null, composer, 384, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$77$lambda$76(ManagedActivityResultLauncher managedActivityResultLauncher) {
        managedActivityResultLauncher.launch("android.permission.ANSWER_PHONE_CALLS");
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$81(String str, VoiceManager voiceManager, ColumnScope columnScope, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C195@11652L322,195@11584L416:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-186286623, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:195)");
            }
            List listOf = CollectionsKt.listOf(new String[]{"Kapalı", "Uyarı sesi", "Tam TTS"});
            ComposerKt.sourceInformationMarkerStart(composer, 1367184675, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = composer.changedInstance(voiceManager);
            Object rememberedValue = composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda15 externalSyntheticLambda15 = new SettingsScreenKt$.ExternalSyntheticLambda15(voiceManager);
                composer.updateRememberedValue(externalSyntheticLambda15);
                rememberedValue = externalSyntheticLambda15;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrSegmented(listOf, str, (Function1) rememberedValue, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), composer, 3078, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$81$lambda$80$lambda$79(VoiceManager voiceManager, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        voiceManager.setMode(Intrinsics.areEqual(str, "Kapalı") ? VoiceMode.OFF : Intrinsics.areEqual(str, "Tam TTS") ? VoiceMode.FULL : VoiceMode.CHIME);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$84(MutableState mutableState, ColumnScope columnScope, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C206@12186L14,206@12133L93:SettingsScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-870516928, i, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:206)");
            }
            List listOf = CollectionsKt.listOf(new String[]{"Kilometre", "Mil"});
            String SettingsScreen$lambda$9 = SettingsScreen$lambda$9(mutableState);
            ComposerKt.sourceInformationMarkerStart(composer, 951857070, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                SettingsScreenKt$.ExternalSyntheticLambda10 externalSyntheticLambda10 = new SettingsScreenKt$.ExternalSyntheticLambda10(mutableState);
                composer.updateRememberedValue(externalSyntheticLambda10);
                rememberedValue = externalSyntheticLambda10;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrSegmented(listOf, SettingsScreen$lambda$9, (Function1) rememberedValue, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), composer, 3462, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$84$lambda$83$lambda$82(MutableState mutableState, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$87(ConnectionState connectionState, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        SettingsScreen(connectionState, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final String SettingsScreen$lambda$9(MutableState mutableState) {
        return (String) ((State) mutableState).getValue();
    }
}
