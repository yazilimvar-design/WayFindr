package com.example.wayfindr.ui.screens;

import android.content.Context;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
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
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import androidx.profileinstaller.ProfileVerifier;
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
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.viewmodel.ConnectionState;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: SettingsScreen.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\u001a7\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b\u001a\u0015\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000bH\u0003¢\u0006\u0002\u0010\f\u001aX\u0010\r\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u000b2\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u0010\b\u0002\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007H\u0003¢\u0006\u0002\u0010\u0017¨\u0006\u0018²\u0006\n\u0010\u0019\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010\u001a\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\u001b\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\u001c\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u001d\u001a\u00020\u001eX\u008a\u0084\u0002²\u0006\n\u0010\u001f\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010 \u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010!\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010\"\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010#\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010$\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010%\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010&\u001a\u00020\u0015X\u008a\u0084\u0002²\u0006\n\u0010'\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010(\u001a\u00020\u0015X\u008a\u008e\u0002"}, d2 = {"SettingsScreen", "", "conn", "Lcom/example/wayfindr/viewmodel/ConnectionState;", "onConnChange", "Lkotlin/Function1;", "onBack", "Lkotlin/Function0;", "(Lcom/example/wayfindr/viewmodel/ConnectionState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SectionLabel", "label", "", "(Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "SettingRow", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "title", "sub", "control", "Landroidx/compose/runtime/Composable;", "last", "", "onClick", "(Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "app", "autoConnect", "screenOff", "keepAwake", "units", "voiceMode", "Lcom/example/wayfindr/dash/nav/VoiceMode;", "hqVideo", "mapDark", "mapDarkAuto", "invertZoom", "sharperMap", "pickerDoubleTap", "showJoystickTrail", "showPhoneBattery", "mediaGranted", "callAnswerGranted"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SettingsScreenKt {

    /* compiled from: SettingsScreen.kt */
    /* loaded from: classes2.dex */
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

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            Composer composer = (Composer) obj2;
            Unit SettingsScreen$lambda$86$lambda$78 = SettingsScreenKt.SettingsScreen$lambda$86$lambda$78(this.ctx, this.launcher, this.media, this.answer, (ColumnScope) obj, composer, (((Integer) obj3).intValue() & (-4)) | 1);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6.0f), 0.0f, 2, null), composer, 6, 0);
            ImageVector dash = WayfindrIcons.INSTANCE.getDash();
            OffRow offRow = new OffRow(1);
            SettingsScreenKt.SettingRow(dash, "Konsol duvar kağıdı", "Beklemedeyken konsolda fotoğraf, müzik şeridi, albüm kapağı", offRow, false, offRow, composer, 0, 16);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6.0f), 0.0f, 2, null), composer, 6, 0);
            ImageVector download = WayfindrIcons.INSTANCE.getDownload();
            OffRow offRow2 = new OffRow(0);
            SettingsScreenKt.SettingRow(download, "Çevrimdışı harita", "Harita dosyası yükle, aç/kapat, sil", offRow2, false, offRow2, composer, 0, 16);
            return SettingsScreen$lambda$86$lambda$78;
        }
    }

    /* compiled from: SettingsScreen.kt */
    /* loaded from: classes2.dex */
    final class OffRow implements Function0, Function2 {
        private final int mode;

        public OffRow(int i) {
            this.mode = i;
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            NavPlus.offMode = this.mode;
            NavPlus.openOffline();
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return Unit.INSTANCE;
        }
    }

    /* compiled from: SettingsScreen.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[VoiceMode.values().length];
            try {
                iArr[VoiceMode.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VoiceMode.CHIME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VoiceMode.FULL.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ConnectionState.values().length];
            try {
                iArr2[ConnectionState.Connected.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[ConnectionState.Searching.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[ConnectionState.Offline.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit SectionLabel$lambda$88(String str, int i, Composer composer, int i2) {
        SectionLabel(str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SettingRow$lambda$93(ImageVector imageVector, String str, String str2, Function2 function2, boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        SettingRow(imageVector, str, str2, function2, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$87(ConnectionState connectionState, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        SettingsScreen(connectionState, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void SettingsScreen(ConnectionState conn, final Function1<? super ConnectionState, Unit> onConnChange, Function0<Unit> function0, Composer $composer, final int $changed) {
        final ConnectionState connectionState;
        Composer $composer2;
        State autoConnect$delegate;
        MutableState units$delegate;
        String voice;
        Function0 function02;
        final Function0<Unit> onBack = function0;
        Intrinsics.checkNotNullParameter(conn, "conn");
        Intrinsics.checkNotNullParameter(onConnChange, "onConnChange");
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Composer $composer3 = $composer.startRestartGroup(-1712294710);
        ComposerKt.sourceInformation($composer3, "C(SettingsScreen)N(conn,onConnChange,onBack)38@1517L7,39@1546L32,40@1632L16,41@1672L33,42@1729L33,43@1786L40,46@1934L64,47@2038L16,57@2395L21,54@2304L10309:SettingsScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(conn.ordinal()) ? 4 : 2;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onBack) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 131) != 130, $dirty2 & 1)) {
            connectionState = conn;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1712294710, $dirty2, -1, "com.example.wayfindr.ui.screens.SettingsScreen (SettingsScreen.kt:37)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Context ctx = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, -542735894, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = DashConfig.INSTANCE.get(ctx);
                $composer3.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            final DashConfig autoConfig = (DashConfig) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            State autoConnect$delegate2 = SnapshotStateKt.collectAsState(autoConfig.getAutoConnectOnStart(), null, $composer3, 0, 1);
            ComposerKt.sourceInformationMarkerStart($composer3, -542731861, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                autoConnect$delegate = autoConnect$delegate2;
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default);
                rememberedValue2 = mutableStateOf$default;
            } else {
                autoConnect$delegate = autoConnect$delegate2;
            }
            final MutableState screenOff$delegate = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -542730037, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default2);
                rememberedValue3 = mutableStateOf$default2;
            }
            final MutableState keepAwake$delegate = (MutableState) rememberedValue3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -542728206, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue4 = $composer3.rememberedValue();
            if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("Kilometre", null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default3);
                rememberedValue4 = mutableStateOf$default3;
            }
            MutableState units$delegate2 = (MutableState) rememberedValue4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -542723446, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue5 = $composer3.rememberedValue();
            if (rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                units$delegate = units$delegate2;
                Object obj2 = VoiceManager.INSTANCE.get(ctx);
                $composer3.updateRememberedValue(obj2);
                rememberedValue5 = obj2;
            } else {
                units$delegate = units$delegate2;
            }
            final VoiceManager voiceManager = (VoiceManager) rememberedValue5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            State voiceMode$delegate = SnapshotStateKt.collectAsState(voiceManager.getMode(), null, $composer3, 0, 1);
            switch (WhenMappings.$EnumSwitchMapping$0[SettingsScreen$lambda$12(voiceMode$delegate).ordinal()]) {
                case 1:
                    voice = "Kapalı";
                    break;
                case 2:
                    voice = "Uyarı sesi";
                    break;
                case 3:
                    voice = "Tam TTS";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                function02 = constructor;
                $composer3.createNode(function02);
            } else {
                function02 = constructor;
                $composer3.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer3);
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            final String voice2 = voice;
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i3 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -584712428, "C61@2500L159,64@2669L24,65@2767L1178,65@2702L1243,80@3955L31,81@4012L32,82@4091L16,83@4158L16,84@4223L16,85@4288L16,86@4363L16,87@4442L16,88@4519L16,89@4609L3295,89@4544L3360,128@7914L42,131@8212L7,135@8466L94,136@8594L44,137@8680L434,137@8647L467,147@9225L46,147@9146L125,193@11461L34,194@11570L440,194@11504L506,204@12020L24,205@12119L117,205@12053L183,209@12246L30,211@12303L76,212@12388L219:SettingsScreen.kt#cef2n4");
            int i4 = (($dirty2 << 9) & 458752) | 3120;
            final MutableState units$delegate3 = units$delegate;
            final State autoConnect$delegate3 = autoConnect$delegate;
            $composer2 = $composer3;
            onBack = function0;
            WayfindrComponentsKt.ScreenHeader(null, "Ayarlar", null, "Bağlantı, ekran kapalıyken aktarım, sesli yönlendirme, birimler ve medya/arama izinleri.", false, onBack, null, $composer3, i4, 85);
            SectionLabel("Bağlantı", $composer3, 6);
            connectionState = conn;
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(6), null, ComposableLambdaKt.rememberComposableLambda(1113277849, true, new Function3() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj3, Object obj4, Object obj5) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$22(ConnectionState.this, autoConfig, autoConnect$delegate3, ctx, (ColumnScope) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, $composer3, 54), $composer3, 24966, 10);
            SectionLabel("Sürüş sırasında", $composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, -1819938700, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue6 = $composer3.rememberedValue();
            if (rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                Object obj3 = DashConfig.INSTANCE.get(ctx);
                $composer3.updateRememberedValue(obj3);
                rememberedValue6 = obj3;
            }
            final DashConfig dashConfig = (DashConfig) rememberedValue6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final State collectAsState = SnapshotStateKt.collectAsState(dashConfig.getMapDarkMode(), null, $composer3, 0, 1);
            final State collectAsState2 = SnapshotStateKt.collectAsState(dashConfig.getMapDarkAuto(), null, $composer3, 0, 1);
            final State collectAsState3 = SnapshotStateKt.collectAsState(dashConfig.getInvertZoom(), null, $composer3, 0, 1);
            final State collectAsState4 = SnapshotStateKt.collectAsState(dashConfig.getSharperMap(), null, $composer3, 0, 1);
            final State collectAsState5 = SnapshotStateKt.collectAsState(dashConfig.getPickerDoubleTap(), null, $composer3, 0, 1);
            final State collectAsState6 = SnapshotStateKt.collectAsState(dashConfig.getShowJoystickTrail(), null, $composer3, 0, 1);
            final State collectAsState7 = SnapshotStateKt.collectAsState(dashConfig.getShowPhoneBattery(), null, $composer3, 0, 1);
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(6), null, ComposableLambdaKt.rememberComposableLambda(497943682, true, new Function3() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj4, Object obj5, Object obj6) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58(MutableState.this, keepAwake$delegate, collectAsState2, dashConfig, collectAsState, collectAsState3, collectAsState5, collectAsState6, collectAsState7, collectAsState4, (ColumnScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                }
            }, $composer3, 54), $composer3, 24966, 10);
            SectionLabel("Konsolda medya ve aramalar", $composer3, 6);
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume2 = $composer3.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final LifecycleOwner lifecycleOwner = (LifecycleOwner) consume2;
            ComposerKt.sourceInformationMarkerStart($composer3, -1819796110, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue7 = $composer3.rememberedValue();
            if (rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(MediaInfoProvider.INSTANCE.isAccessGranted(ctx)), null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default4);
                rememberedValue7 = mutableStateOf$default4;
            }
            final MutableState mutableState = (MutableState) rememberedValue7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1819792064, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue8 = $composer3.rememberedValue();
            if (rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(SettingsScreen$lambda$86$answerGranted(ctx)), null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default5);
                rememberedValue8 = mutableStateOf$default5;
            }
            final MutableState mutableState2 = (MutableState) rememberedValue8;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -1819788922, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer3.changedInstance(ctx) | $composer3.changedInstance(lifecycleOwner);
            Object rememberedValue9 = $composer3.rememberedValue();
            if (changedInstance || rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                Object obj4 = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$68$lambda$67(LifecycleOwner.this, ctx, mutableState, mutableState2, (DisposableEffectScope) obj5);
                    }
                };
                $composer3.updateRememberedValue(obj4);
                rememberedValue9 = obj4;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.DisposableEffect(lifecycleOwner, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue9, $composer3, 0);
            ActivityResultContracts.RequestPermission requestPermission = new ActivityResultContracts.RequestPermission();
            ComposerKt.sourceInformationMarkerStart($composer3, -1819771870, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue10 = $composer3.rememberedValue();
            if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                Object obj5 = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj6) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$70$lambda$69(MutableState.this, ((Boolean) obj6).booleanValue());
                    }
                };
                $composer3.updateRememberedValue(obj5);
                rememberedValue10 = obj5;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission, (Function1) rememberedValue10, $composer3, 48);
            $composer3.startReplaceGroup(-1819767952);
            ComposerKt.sourceInformation($composer3, "150@9334L476");
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(6), null, ComposableSingletons$SettingsScreenKt.INSTANCE.getLambda$720111070$app(), $composer3, 24966, 10);
            $composer3.endReplaceGroup();
            SectionLabel("Ses ve yönlendirme", $composer3, 6);
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(14), null, ComposableLambdaKt.rememberComposableLambda(-186286623, true, new Function3() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj6, Object obj7, Object obj8) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$81(voice2, voiceManager, (ColumnScope) obj6, (Composer) obj7, ((Integer) obj8).intValue());
                }
            }, $composer3, 54), $composer3, 24966, 10);
            SectionLabel("Birimler", $composer3, 6);
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(14), null, ComposableLambdaKt.rememberComposableLambda(-870516928, true, new Function3() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj6, Object obj7, Object obj8) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$84(MutableState.this, (ColumnScope) obj6, (Composer) obj7, ((Integer) obj8).intValue());
                }
            }, $composer3, 54), $composer3, 24966, 10);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), $composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, -1819673344, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue11 = $composer3.rememberedValue();
            if (rememberedValue11 == Composer.INSTANCE.getEmpty()) {
                Object currentVersionName = UpdateChecker.INSTANCE.currentVersionName(ctx);
                $composer3.updateRememberedValue(currentVersionName);
                rememberedValue11 = currentVersionName;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            TextKt.m3069TextNvy7gAk("Wayfindr v" + ((String) rememberedValue11), PaddingKt.m820paddingqDBjuR0$default(columnScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterHorizontally()), 0.0f, Dp.m7908constructorimpl(10), 0.0f, 0.0f, 13, null), ColorKt.getTextDis(), null, TextUnitKt.getSp(11), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24576, 0, 261992);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj6, Object obj7) {
                    return SettingsScreenKt.SettingsScreen$lambda$87(ConnectionState.this, onConnChange, onBack, $changed, (Composer) obj6, ((Integer) obj7).intValue());
                }
            });
        }
    }

    private static final boolean SettingsScreen$lambda$1(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$3(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$4(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SettingsScreen$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String SettingsScreen$lambda$9(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final VoiceMode SettingsScreen$lambda$12(State<? extends VoiceMode> state) {
        return (VoiceMode) state.getValue();
    }

    static final Unit SettingsScreen$lambda$86$lambda$22(final ConnectionState $conn, final DashConfig $autoConfig, final State $autoConnect$delegate, Context $ctx, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        String str;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C68@3015L162,66@2781L397,69@3191L52,71@3377L72,70@3256L194,72@3463L52,73@3544L32,74@3631L16,77@3856L65,75@3660L275:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1113277849, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:66)");
            }
            ImageVector bt = WayfindrIcons.INSTANCE.getBt();
            switch (WhenMappings.$EnumSwitchMapping$1[$conn.ordinal()]) {
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
            SettingRow(bt, "Konsol ekranı", str, ComposableLambdaKt.rememberComposableLambda(-1426613793, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$22$lambda$13(ConnectionState.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getSync(), "Başlangıçta otomatik bağlan", "Motosiklet yakındayken bağlan", ComposableLambdaKt.rememberComposableLambda(-971327672, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$22$lambda$16(DashConfig.this, $autoConnect$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            ComposerKt.sourceInformationMarkerStart($composer, 413181081, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = DashConfig.INSTANCE.get($ctx);
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            final DashConfig streamCfg = (DashConfig) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer);
            final State hqVideo$delegate = SnapshotStateKt.collectAsState(streamCfg.getHighQualityVideo(), null, $composer, 0, 1);
            SettingRow(WayfindrIcons.INSTANCE.getZap(), "Daha yüksek video kalitesi", SettingsScreen$lambda$86$lambda$22$lambda$18(hqVideo$delegate) ? "400 kbps · daha net görüntü, konsola daha fazla yük" : "Dengeli · 200 kbps", ComposableLambdaKt.rememberComposableLambda(-70259737, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$22$lambda$21(DashConfig.this, hqVideo$delegate, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), true, null, $composer, 27696, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$13(ConnectionState $conn, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C68@3017L158:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1426613793, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:68)");
            }
            WayfindrComponentsKt.WayfindrChip($conn == ConnectionState.Connected ? "Bağlı" : "Kapalı", $conn == ConnectionState.Connected ? ChipTone.Gold : ChipTone.Off, true, null, null, $composer, 384, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$16(final DashConfig $autoConfig, State $autoConnect$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C71@3407L40,71@3379L68:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-971327672, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:71)");
            }
            boolean SettingsScreen$lambda$1 = SettingsScreen$lambda$1($autoConnect$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1437988560, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($autoConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$22$lambda$16$lambda$15$lambda$14(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$1, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$16$lambda$15$lambda$14(DashConfig $autoConfig, boolean it) {
        $autoConfig.setAutoConnectOnStart(it);
        return Unit.INSTANCE;
    }

    private static final boolean SettingsScreen$lambda$86$lambda$22$lambda$18(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$21(final DashConfig $streamCfg, State $hqVideo$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C77@3882L37,77@3858L61:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-70259737, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:77)");
            }
            boolean SettingsScreen$lambda$86$lambda$22$lambda$18 = SettingsScreen$lambda$86$lambda$22$lambda$18($hqVideo$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 1199870348, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($streamCfg);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$22$lambda$21$lambda$20$lambda$19(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$22$lambda$18, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$22$lambda$21$lambda$20$lambda$19(DashConfig $streamCfg, boolean it) {
        $streamCfg.setHighQualityVideo(it);
        return Unit.INSTANCE;
    }

    private static final boolean SettingsScreen$lambda$86$lambda$24(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$25(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$26(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$27(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$28(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$29(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    private static final boolean SettingsScreen$lambda$86$lambda$30(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    static final Unit SettingsScreen$lambda$86$lambda$58(final MutableState $screenOff$delegate, final MutableState $keepAwake$delegate, final State $mapDarkAuto$delegate, final DashConfig $dashConfig, State $mapDark$delegate, final State $invertZoom$delegate, final State $pickerDoubleTap$delegate, final State $showJoystickTrail$delegate, final State $showPhoneBattery$delegate, final State $sharperMap$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C91@4748L48,90@4623L174,92@4810L52,94@4988L48,93@4875L162,95@5050L52,98@5350L65,96@5115L301,99@5429L52,104@5917L95,102@5674L339,105@6026L52,108@6308L63,106@6091L281,109@6385L52,113@6766L73,110@6450L390,114@6853L52,117@7102L77,115@6918L262,118@7193L52,121@7443L75,119@7258L261,122@7532L52,125@7817L63,123@7597L297:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(497943682, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:90)");
            }
            SettingRow(WayfindrIcons.INSTANCE.getPower(), "Telefon ekranını kapat", "Harita konsola aktarılmaya devam eder", ComposableLambdaKt.rememberComposableLambda(-796500152, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$33(MutableState.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getDash(), "Konsolu uyanık tut", "Konsolun uykuya geçmesini önle", ComposableLambdaKt.rememberComposableLambda(-1353202063, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda28
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$36(MutableState.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getMoon(), "Haritada otomatik karanlık mod", SettingsScreen$lambda$86$lambda$25($mapDarkAuto$delegate) ? "Saat 19.00–05.00 arasında karanlık (telefon saatine göre)" : "Kapalı: aşağıdaki anahtarla elle ayarlayın", ComposableLambdaKt.rememberComposableLambda(1284656976, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda29
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$39(DashConfig.this, $mapDarkAuto$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            final boolean darkNow = DarkSchedule.effective$default(DarkSchedule.INSTANCE, SettingsScreen$lambda$86$lambda$25($mapDarkAuto$delegate), SettingsScreen$lambda$86$lambda$24($mapDark$delegate), false, 4, null);
            SettingRow(WayfindrIcons.INSTANCE.getMoon(), "Konsol haritası: karanlık mod", SettingsScreen$lambda$86$lambda$25($mapDarkAuto$delegate) ? "Saati izliyor · elle ayarlamak için dokunun" : "Elle · aynı önbelleğe alınmış harita kutucuklarında gece görünümü", ComposableLambdaKt.rememberComposableLambda(-372451281, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$42(darkNow, $dashConfig, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getSync(), "Joystick yakınlaştırmasını tersine çevir", SettingsScreen$lambda$86$lambda$26($invertZoom$delegate) ? "Sağa: yakınlaştır · sola: uzaklaştır" : "Sola: yakınlaştır · sağa: uzaklaştır", ComposableLambdaKt.rememberComposableLambda(-2029559538, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$45(DashConfig.this, $invertZoom$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getPin(), "Sola/sağa iki kez basınca hedef listesi açılsın", SettingsScreen$lambda$86$lambda$28($pickerDoubleTap$delegate) ? "Açık: sola/sağa iki kez dokunun veya aşağıya iki kez basın; kayıtlı liste açılır" : "Kapalı: aşağıya 3 sn içinde iki kez basınca kayıtlı liste açılır", ComposableLambdaKt.rememberComposableLambda(608299501, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$48(DashConfig.this, $pickerDoubleTap$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3120, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getTarget(), "Joystick basışlarını göster", "Konsolun alt ortasında son 4 basışı D / R / L olarak, her biri 2 sn göster", ComposableLambdaKt.rememberComposableLambda(-1048808756, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$51(DashConfig.this, $showJoystickTrail$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getTarget(), "Telefon pilini konsolda göster", "Tahmini varış süresinin üstünde pil yüzdesi; şarj olurken şimşek simgesi", ComposableLambdaKt.rememberComposableLambda(1589050283, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$54(DashConfig.this, $showPhoneBattery$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), false, null, $composer, 3504, 48);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            SettingRow(WayfindrIcons.INSTANCE.getTarget(), "Daha keskin harita", SettingsScreen$lambda$86$lambda$27($sharperMap$delegate) ? "2× çizim, yüksek çözünürlüklü harita kutucukları · daha fazla bellek" : "Standart harita kutucukları", ComposableLambdaKt.rememberComposableLambda(-68057974, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda35
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$57(DashConfig.this, $sharperMap$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), true, null, $composer, 27696, 32);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$33(final MutableState $screenOff$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C91@4776L18,91@4750L44:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-796500152, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:91)");
            }
            boolean SettingsScreen$lambda$3 = SettingsScreen$lambda$3($screenOff$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -932466470, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$33$lambda$32$lambda$31(MutableState.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$3, (Function1) rememberedValue, $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$33$lambda$32$lambda$31(MutableState $screenOff$delegate, boolean it) {
        SettingsScreen$lambda$4($screenOff$delegate, it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$36(final MutableState $keepAwake$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C94@5016L18,94@4990L44:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1353202063, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:94)");
            }
            boolean SettingsScreen$lambda$6 = SettingsScreen$lambda$6($keepAwake$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 947909059, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$36$lambda$35$lambda$34(MutableState.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$6, (Function1) rememberedValue, $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$36$lambda$35$lambda$34(MutableState $keepAwake$delegate, boolean it) {
        SettingsScreen$lambda$7($keepAwake$delegate, it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$39(final DashConfig $dashConfig, State $mapDarkAuto$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C98@5380L33,98@5352L61:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1284656976, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:98)");
            }
            boolean SettingsScreen$lambda$86$lambda$25 = SettingsScreen$lambda$86$lambda$25($mapDarkAuto$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 1899740977, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dashConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda38
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$39$lambda$38$lambda$37(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$25, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$39$lambda$38$lambda$37(DashConfig $dashConfig, boolean it) {
        $dashConfig.setMapDarkAuto(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$42(boolean $darkNow, final DashConfig $dashConfig, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C104@5943L67,104@5919L91:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-372451281, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:104)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1443394574, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dashConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda36
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$42$lambda$41$lambda$40(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle($darkNow, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$42$lambda$41$lambda$40(DashConfig $dashConfig, boolean it) {
        $dashConfig.setMapDarkMode(it);
        $dashConfig.setMapDarkAuto(false);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$45(final DashConfig $dashConfig, State $invertZoom$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C108@6337L32,108@6310L59:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2029559538, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:108)");
            }
            boolean SettingsScreen$lambda$86$lambda$26 = SettingsScreen$lambda$86$lambda$26($invertZoom$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -491562674, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dashConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda12
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$45$lambda$44$lambda$43(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$26, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$45$lambda$44$lambda$43(DashConfig $dashConfig, boolean it) {
        $dashConfig.setInvertZoom(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$48(final DashConfig $dashConfig, State $pickerDoubleTap$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C113@6800L37,113@6768L69:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(608299501, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:113)");
            }
            boolean SettingsScreen$lambda$86$lambda$28 = SettingsScreen$lambda$86$lambda$28($pickerDoubleTap$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 460269330, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dashConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda25
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$48$lambda$47$lambda$46(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$28, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$48$lambda$47$lambda$46(DashConfig $dashConfig, boolean it) {
        $dashConfig.setPickerDoubleTap(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$51(final DashConfig $dashConfig, State $showJoystickTrail$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C117@7138L39,117@7104L73:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1048808756, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:117)");
            }
            boolean SettingsScreen$lambda$86$lambda$29 = SettingsScreen$lambda$86$lambda$29($showJoystickTrail$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 1412101235, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dashConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda26
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$51$lambda$50$lambda$49(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$29, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$51$lambda$50$lambda$49(DashConfig $dashConfig, boolean it) {
        $dashConfig.setShowJoystickTrail(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$54(final DashConfig $dashConfig, State $showPhoneBattery$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C121@7478L38,121@7445L71:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1589050283, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:121)");
            }
            boolean SettingsScreen$lambda$86$lambda$30 = SettingsScreen$lambda$86$lambda$30($showPhoneBattery$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -1931034255, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dashConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$54$lambda$53$lambda$52(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$30, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$54$lambda$53$lambda$52(DashConfig $dashConfig, boolean it) {
        $dashConfig.setShowPhoneBattery(it);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$57(final DashConfig $dashConfig, State $sharperMap$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C125@7846L32,125@7819L59:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-68057974, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:125)");
            }
            boolean SettingsScreen$lambda$86$lambda$27 = SettingsScreen$lambda$86$lambda$27($sharperMap$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -979202614, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($dashConfig);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda14
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$58$lambda$57$lambda$56$lambda$55(DashConfig.this, ((Boolean) obj2).booleanValue());
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrToggle(SettingsScreen$lambda$86$lambda$27, (Function1) rememberedValue, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$58$lambda$57$lambda$56$lambda$55(DashConfig $dashConfig, boolean it) {
        $dashConfig.setSharperMap(it);
        return Unit.INSTANCE;
    }

    private static final boolean SettingsScreen$lambda$86$answerGranted(Context ctx) {
        return ContextCompat.checkSelfPermission(ctx, "android.permission.ANSWER_PHONE_CALLS") == 0;
    }

    private static final boolean SettingsScreen$lambda$86$lambda$60(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$86$lambda$61(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean SettingsScreen$lambda$86$lambda$63(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void SettingsScreen$lambda$86$lambda$64(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final DisposableEffectResult SettingsScreen$lambda$86$lambda$68$lambda$67(final LifecycleOwner $lifecycleOwner, final Context $ctx, final MutableState $mediaGranted$delegate, final MutableState $callAnswerGranted$delegate, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final LifecycleEventObserver obs = new LifecycleEventObserver() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda24
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                SettingsScreenKt.SettingsScreen$lambda$86$lambda$68$lambda$67$lambda$65($ctx, $mediaGranted$delegate, $callAnswerGranted$delegate, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycle().addObserver(obs);
        return new DisposableEffectResult() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$SettingsScreen$lambda$86$lambda$68$lambda$67$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                LifecycleOwner.this.getLifecycle().removeObserver(obs);
            }
        };
    }

    static final void SettingsScreen$lambda$86$lambda$68$lambda$67$lambda$65(Context $ctx, MutableState $mediaGranted$delegate, MutableState $callAnswerGranted$delegate, LifecycleOwner lifecycleOwner, Lifecycle.Event e) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(e, "e");
        if (e == Lifecycle.Event.ON_RESUME) {
            SettingsScreen$lambda$86$lambda$61($mediaGranted$delegate, MediaInfoProvider.INSTANCE.isAccessGranted($ctx));
            SettingsScreen$lambda$86$lambda$64($callAnswerGranted$delegate, SettingsScreen$lambda$86$answerGranted($ctx));
        }
    }

    static final Unit SettingsScreen$lambda$86$lambda$70$lambda$69(MutableState $callAnswerGranted$delegate, boolean it) {
        SettingsScreen$lambda$86$lambda$64($callAnswerGranted$delegate, it);
        return Unit.INSTANCE;
    }

    private static final Unit SettingsScreen$lambda$86$lambda$78(final Context $ctx, final ManagedActivityResultLauncher $callPermLauncher, final MutableState $mediaGranted$delegate, final MutableState $callAnswerGranted$delegate, final ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C164@10183L231,159@9895L736,174@10644L52,180@10980L241,175@10709L732:SettingsScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(WayfindrCard) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1178376821, $dirty2, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:159)");
            }
            ImageVector zap = WayfindrIcons.INSTANCE.getZap();
            String str = SettingsScreen$lambda$86$lambda$60($mediaGranted$delegate) ? "Etkin · sürüş sırasında şarkı bilgisi ve arayan gösterilir" : "Bildirim erişimine izin vermek için dokunun";
            ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1375422651, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$78$lambda$71(MutableState.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54);
            Function0 function02 = null;
            if (SettingsScreen$lambda$86$lambda$60($mediaGranted$delegate)) {
                $composer.startReplaceGroup(-2038228600);
                $composer.endReplaceGroup();
                function0 = null;
            } else {
                $composer.startReplaceGroup(-2038214277);
                ComposerKt.sourceInformation($composer, "171@10492L106");
                ComposerKt.sourceInformationMarkerStart($composer, -1451221505, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean changedInstance = (($dirty2 & 14) == 4) | $composer.changedInstance($ctx);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.SettingsScreen$lambda$86$lambda$78$lambda$74$lambda$73(ColumnScope.this, $ctx);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    rememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endReplaceGroup();
                function0 = (Function0) rememberedValue;
            }
            SettingRow(zap, "Çalan medya ve aramalar konsolda", str, rememberComposableLambda, false, function0, $composer, 3120, 16);
            WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 2, null), $composer, 6, 0);
            ImageVector bt = WayfindrIcons.INSTANCE.getBt();
            String str2 = SettingsScreen$lambda$86$lambda$63($callAnswerGranted$delegate) ? "Etkin · YUKARI yanıtlar, AŞAĞI reddeder" : "Aramaları yanıtlamak/reddetmek için dokunun";
            ComposableLambda rememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(717417764, true, new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$86$lambda$78$lambda$75(MutableState.this, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54);
            if (SettingsScreen$lambda$86$lambda$63($callAnswerGranted$delegate)) {
                $composer.startReplaceGroup(-2037394328);
            } else {
                $composer.startReplaceGroup(-2037380966);
                ComposerKt.sourceInformation($composer, "188@11333L75");
                ComposerKt.sourceInformationMarkerStart($composer, -1451194624, "CC(remember):SettingsScreen.kt#9igjgp");
                boolean changedInstance2 = $composer.changedInstance($callPermLauncher);
                Object rememberedValue2 = $composer.rememberedValue();
                if (changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return SettingsScreenKt.SettingsScreen$lambda$86$lambda$78$lambda$77$lambda$76(ManagedActivityResultLauncher.this);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    rememberedValue2 = obj2;
                }
                function02 = (Function0) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer);
            }
            $composer.endReplaceGroup();
            SettingRow(bt, "Aramaları joystick ile yanıtla", str2, rememberComposableLambda2, true, function02, $composer, 27696, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$71(MutableState $mediaGranted$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C165@10205L191:SettingsScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1375422651, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:165)");
            }
            WayfindrComponentsKt.WayfindrChip(SettingsScreen$lambda$86$lambda$60($mediaGranted$delegate) ? "Açık" : "Etkinleştir", SettingsScreen$lambda$86$lambda$60($mediaGranted$delegate) ? ChipTone.Gold : ChipTone.Off, true, null, null, $composer, 384, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$74$lambda$73(ColumnScope $this_WayfindrCard, Context $ctx) {
        try {
            Result.Companion companion = Result.INSTANCE;
            $ctx.startActivity(MediaInfoProvider.INSTANCE.accessSettingsIntent());
            Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$75(MutableState $callAnswerGranted$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C181@11002L201:SettingsScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(717417764, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:181)");
            }
            WayfindrComponentsKt.WayfindrChip(SettingsScreen$lambda$86$lambda$63($callAnswerGranted$delegate) ? "Açık" : "Etkinleştir", SettingsScreen$lambda$86$lambda$63($callAnswerGranted$delegate) ? ChipTone.Gold : ChipTone.Off, true, null, null, $composer, 384, 24);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$78$lambda$77$lambda$76(ManagedActivityResultLauncher $callPermLauncher) {
        $callPermLauncher.launch("android.permission.ANSWER_PHONE_CALLS");
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$81(String $voice, final VoiceManager $voiceManager, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C195@11652L322,195@11584L416:SettingsScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-186286623, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:195)");
            }
            List listOf = CollectionsKt.listOf((Object[]) new String[]{"Kapalı", "Uyarı sesi", "Tam TTS"});
            ComposerKt.sourceInformationMarkerStart($composer, 1367184675, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($voiceManager);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$81$lambda$80$lambda$79(VoiceManager.this, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrSegmented(listOf, $voice, (Function1) rememberedValue, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 3078, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$81$lambda$80$lambda$79(VoiceManager $voiceManager, String it) {
        VoiceMode voiceMode;
        Intrinsics.checkNotNullParameter(it, "it");
        if (Intrinsics.areEqual(it, "Kapalı")) {
            voiceMode = VoiceMode.OFF;
        } else {
            voiceMode = Intrinsics.areEqual(it, "Tam TTS") ? VoiceMode.FULL : VoiceMode.CHIME;
        }
        $voiceManager.setMode(voiceMode);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$84(final MutableState $units$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C206@12186L14,206@12133L93:SettingsScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-870516928, $changed, -1, "com.example.wayfindr.ui.screens.SettingsScreen.<anonymous>.<anonymous> (SettingsScreen.kt:206)");
            }
            List listOf = CollectionsKt.listOf((Object[]) new String[]{"Kilometre", "Mil"});
            String SettingsScreen$lambda$9 = SettingsScreen$lambda$9($units$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, 951857070, "CC(remember):SettingsScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return SettingsScreenKt.SettingsScreen$lambda$86$lambda$84$lambda$83$lambda$82(MutableState.this, (String) obj2);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrSegmented(listOf, SettingsScreen$lambda$9, (Function1) rememberedValue, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), $composer, 3462, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$86$lambda$84$lambda$83$lambda$82(MutableState $units$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $units$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final void SectionLabel(final String label, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(441747781);
        ComposerKt.sourceInformation($composer2, "C(SectionLabel)N(label)222@12675L74:SettingsScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(label) ? 4 : 2;
        }
        if ($composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(441747781, $dirty, -1, "com.example.wayfindr.ui.screens.SectionLabel (SettingsScreen.kt:221)");
            }
            WayfindrComponentsKt.Eyebrow(label, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(4), Dp.m7908constructorimpl(22), 0.0f, Dp.m7908constructorimpl(9), 4, null), $composer2, $dirty & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SectionLabel$lambda$88(label, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SettingRow(final ImageVector icon, final String title, String sub, final Function2<? super Composer, ? super Integer, Unit> function2, boolean last, Function0<Unit> function0, Composer $composer, final int $changed, final int i) {
        ImageVector imageVector;
        String str;
        Function0 function02;
        final String sub2;
        final Function0 onClick;
        final boolean last2;
        Function0 onClick2;
        Function0 function03;
        Function0 function04;
        Function0 function05;
        String sub3;
        Composer $composer2 = $composer.startRestartGroup(1650730910);
        ComposerKt.sourceInformation($composer2, "C(SettingRow)N(icon,title,sub,control,last,onClick)234@12963L933:SettingsScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            imageVector = icon;
            $dirty |= $composer2.changed(imageVector) ? 4 : 2;
        } else {
            imageVector = icon;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(title) ? 32 : 16;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            str = sub;
        } else if (($changed & 384) == 0) {
            str = sub;
            $dirty |= $composer2.changed(str) ? 256 : 128;
        } else {
            str = sub;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 2048 : 1024;
        }
        int i3 = i & 32;
        if (i3 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function02 = function0;
        } else if ((196608 & $changed) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 131072 : 65536;
        } else {
            function02 = function0;
        }
        if ($composer2.shouldExecute((66707 & $dirty) != 66706, $dirty & 1)) {
            String sub4 = i2 != 0 ? null : str;
            boolean last3 = (i & 16) != 0 ? false : last;
            Function0 onClick3 = i3 != 0 ? null : function02;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1650730910, $dirty, -1, "com.example.wayfindr.ui.screens.SettingRow (SettingsScreen.kt:233)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            if (onClick3 != null) {
                onClick2 = onClick3;
                fillMaxWidth$default = ClickableKt.m297clickableoSLSa3U$default(fillMaxWidth$default, false, null, null, null, onClick3, 15, null);
            } else {
                onClick2 = onClick3;
            }
            Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(fillMaxWidth$default, Dp.m7908constructorimpl(6), Dp.m7908constructorimpl(13));
            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            int $dirty2 = $dirty;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
            String sub5 = sub4;
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
            boolean last4 = last3;
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i6 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, -594260516, "C241@13237L315,247@13561L29,248@13599L273,252@13881L9:SettingsScreen.kt#cef2n4");
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(38)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(11))), ColorKt.getSurf2(), null, 2, null), Dp.m7908constructorimpl(1), ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(11)));
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU);
            Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1590988487, "C245@13456L86:SettingsScreen.kt#cef2n4");
            IconKt.m2517Iconww6aTOc(imageVector, (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(19)), ColorKt.getTextMid(), $composer2, ($dirty2 & 14) | 432, 0);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, 6);
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, weight$default);
            Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function05 = constructor3;
                $composer2.createNode(function05);
            } else {
                function05 = constructor3;
                $composer2.useNode();
            }
            Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer2);
            Updater.m4372setimpl(m4364constructorimpl3, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i12 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -2071840254, "C249@13641L107:SettingsScreen.kt#cef2n4");
            TextKt.m3069TextNvy7gAk(title, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(14.5d), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, (($dirty2 >> 3) & 14) | 1597440, 0, 261930);
            if (sub5 != null) {
                $composer2.startReplaceGroup(-2071708598);
                ComposerKt.sourceInformation($composer2, "250@13778L84");
                sub3 = sub5;
                TextKt.m3069TextNvy7gAk(sub3, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, (($dirty2 >> 6) & 14) | 24624, 0, 262120);
            } else {
                sub3 = sub5;
                $composer2.startReplaceGroup(-2085379970);
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            function2.invoke($composer2, Integer.valueOf(($dirty2 >> 9) & 14));
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sub2 = sub3;
            onClick = onClick2;
            last2 = last4;
        } else {
            $composer2.skipToGroupEnd();
            sub2 = str;
            onClick = function02;
            last2 = last;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.SettingsScreenKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingRow$lambda$93(ImageVector.this, title, sub2, function2, last2, onClick, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
