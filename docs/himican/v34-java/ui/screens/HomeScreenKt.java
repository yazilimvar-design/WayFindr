package com.example.wayfindr.ui.screens;

import android.content.Context;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
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
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.BtnSize;
import com.example.wayfindr.ui.components.BtnVariant;
import com.example.wayfindr.ui.components.ChipTone;
import com.example.wayfindr.ui.components.CircularDashKt;
import com.example.wayfindr.ui.components.RouteQrDialogKt;
import com.example.wayfindr.ui.components.WayfindrComponentsKt;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.util.BuildId;
import com.example.wayfindr.viewmodel.ConnectionState;
import com.example.wayfindr.viewmodel.RouteViewModel;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

/* compiled from: HomeScreen.kt */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\u001a3\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bH\u0007¢\u0006\u0002\u0010\t¨\u0006\n²\u0006\u0010\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u008a\u0084\u0002²\u0006\f\u0010\u000e\u001a\u0004\u0018\u00010\rX\u008a\u008e\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0010X\u008a\u0084\u0002"}, d2 = {"HomeScreen", "", "conn", "Lcom/example/wayfindr/viewmodel/ConnectionState;", "onNavigate", "Lkotlin/Function1;", "", "routeViewModel", "Lcom/example/wayfindr/viewmodel/RouteViewModel;", "(Lcom/example/wayfindr/viewmodel/ConnectionState;Lkotlin/jvm/functions/Function1;Lcom/example/wayfindr/viewmodel/RouteViewModel;Landroidx/compose/runtime/Composer;II)V", "app", "saved", "", "Lcom/example/wayfindr/data/SavedLocation;", "sharing", "pulseAlpha", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class HomeScreenKt {

    /* compiled from: HomeScreen.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnectionState.values().length];
            try {
                iArr[ConnectionState.Connected.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ConnectionState.Searching.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ConnectionState.Offline.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit HomeScreen$lambda$31(ConnectionState connectionState, Function1 function1, RouteViewModel routeViewModel, int i, int i2, Composer composer, int i3) {
        HomeScreen(connectionState, function1, routeViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:85:0x07c1, code lost:
    
        if (r2 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L123;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void HomeScreen(final ConnectionState conn, final Function1<? super String, Unit> onNavigate, RouteViewModel routeViewModel, Composer $composer, final int $changed, final int i) {
        final RouteViewModel routeViewModel2;
        State saved$delegate;
        Triple triple;
        String str;
        RouteViewModel routeViewModel3;
        long m5092copywmQWz5c;
        long m5092copywmQWz5c2;
        Function0 function0;
        int i2;
        Intrinsics.checkNotNullParameter(conn, "conn");
        Intrinsics.checkNotNullParameter(onNavigate, "onNavigate");
        Composer $composer2 = $composer.startRestartGroup(-1647001853);
        ComposerKt.sourceInformation($composer2, "C(HomeScreen)N(conn,onNavigate,routeViewModel)37@1461L16,38@1497L75,48@2091L47,49@2180L170,58@2447L21,55@2356L5951:HomeScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(conn.ordinal()) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(onNavigate) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                routeViewModel2 = routeViewModel;
                if ($composer2.changedInstance(routeViewModel2)) {
                    i2 = 256;
                    $dirty |= i2;
                }
            } else {
                routeViewModel2 = routeViewModel;
            }
            i2 = 128;
            $dirty |= i2;
        } else {
            routeViewModel2 = routeViewModel;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "35@1406L11");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
            } else if ((i & 4) != 0) {
                $composer2.startReplaceableGroup(1729797275);
                ComposerKt.sourceInformation($composer2, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer2, 6);
                if (current == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                }
                ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(RouteViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, current instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, $composer2, ((0 << 3) & 7168) | ((0 << 3) & 112) | ((0 << 3) & 896) | ((0 << 3) & 57344), 0);
                $composer2.endReplaceableGroup();
                routeViewModel2 = (RouteViewModel) viewModel;
                $dirty &= -897;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1647001853, $dirty, -1, "com.example.wayfindr.ui.screens.HomeScreen (HomeScreen.kt:36)");
            }
            State saved$delegate2 = SnapshotStateKt.collectAsState(routeViewModel2.getSaved(), null, $composer2, 0, 1);
            ComposerKt.sourceInformationMarkerStart($composer2, 2074158510, "CC(remember):HomeScreen.kt#9igjgp");
            Object rememberedValue = $composer2.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer2.updateRememberedValue(mutableStateOf$default);
                rememberedValue = mutableStateOf$default;
            }
            final MutableState sharing$delegate = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SavedLocation HomeScreen$lambda$2 = HomeScreen$lambda$2(sharing$delegate);
            if (HomeScreen$lambda$2 == null) {
                $composer2.startReplaceGroup(-125507651);
                $composer2.endReplaceGroup();
                saved$delegate = saved$delegate2;
            } else {
                $composer2.startReplaceGroup(-125507650);
                ComposerKt.sourceInformation($composer2, "*39@1630L18,39@1599L50");
                ComposerKt.sourceInformationMarkerStart($composer2, -381612852, "CC(remember):HomeScreen.kt#9igjgp");
                Object rememberedValue2 = $composer2.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    saved$delegate = saved$delegate2;
                    Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return HomeScreenKt.HomeScreen$lambda$6$lambda$5$lambda$4(MutableState.this);
                        }
                    };
                    $composer2.updateRememberedValue(obj);
                    rememberedValue2 = obj;
                } else {
                    saved$delegate = saved$delegate2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                RouteQrDialogKt.RouteQrDialog(HomeScreen$lambda$2, (Function0) rememberedValue2, $composer2, SavedLocation.$stable | 48);
                Unit unit = Unit.INSTANCE;
                $composer2.endReplaceGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            switch (WhenMappings.$EnumSwitchMapping$0[conn.ordinal()]) {
                case 1:
                    triple = new Triple("Bağlandı", "Konsol ekranına aktarılıyor", Color.m5084boximpl(ColorKt.getGold()));
                    break;
                case 2:
                    triple = new Triple("Aranıyor…", "Konsol ekranı aranıyor", Color.m5084boximpl(ColorKt.getWarn()));
                    break;
                case 3:
                    triple = new Triple("Çevrimdışı", "Konsol ekranı algılanmadı", Color.m5084boximpl(ColorKt.getOffline()));
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            Triple status = triple;
            final String statusLabel = (String) status.component1();
            final String statusSub = (String) status.component2();
            final long statusDot = ((Color) status.component3()).m5104unboximpl();
            InfiniteTransition infiniteTransition = InfiniteTransitionKt.rememberInfiniteTransition("dot-pulse", $composer2, 6, 0);
            final State pulseAlpha$delegate = InfiniteTransitionKt.animateFloat(infiniteTransition, 1.0f, 0.35f, AnimationSpecKt.m170infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(1800, 0, EasingKt.getFastOutSlowInEasing(), 2, null), RepeatMode.Reverse, 0L, 4, null), "pulse", $composer2, InfiniteTransition.$stable | 25008 | (InfiniteRepeatableSpec.$stable << 9), 0);
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            int $dirty2 = $dirty;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            final RouteViewModel routeViewModel4 = routeViewModel2;
            int i3 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, 203771733, "C64@2618L77,62@2552L154,106@4466L1631,102@4311L1786,150@6107L30,153@6204L23,152@6147L227,158@6384L30,161@6481L22,160@6424L228,166@6662L30,168@6702L84:HomeScreen.kt#cef2n4");
            WayfindrComponentsKt.ScreenHeader(null, null, null, null, true, null, ComposableLambdaKt.rememberComposableLambda(-583922907, true, new Function2() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return HomeScreenKt.HomeScreen$lambda$30$lambda$10(Function1.this, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 1597440, 47);
            if (BuildConfig.DEBUG) {
                $composer2.startReplaceGroup(204014803);
                ComposerKt.sourceInformation($composer2, "70@2972L7,71@3007L31,72@3051L1208");
                ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume = $composer2.consume(localContext);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Context context = (Context) consume;
                ComposerKt.sourceInformationMarkerStart($composer2, 422224120, "CC(remember):HomeScreen.kt#9igjgp");
                Object rememberedValue3 = $composer2.rememberedValue();
                str = "CC(remember):HomeScreen.kt#9igjgp";
                if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    rememberedValue3 = BuildId.INSTANCE.sha12(context);
                    $composer2.updateRememberedValue(rememberedValue3);
                }
                String str2 = (String) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Modifier clip = ClipKt.clip(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(14), 7, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(8)));
                m5092copywmQWz5c = Color.m5092copywmQWz5c(r64, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r64) : 0.1f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r64) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r64) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getGold()) : 0.0f);
                Modifier m262backgroundbw27NRU$default = BackgroundKt.m262backgroundbw27NRU$default(clip, m5092copywmQWz5c, null, 2, null);
                float m7908constructorimpl = Dp.m7908constructorimpl(1);
                m5092copywmQWz5c2 = Color.m5092copywmQWz5c(r64, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r64) : 0.4f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r64) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r64) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(ColorKt.getGold()) : 0.0f);
                Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(m262backgroundbw27NRU$default, m7908constructorimpl, m5092copywmQWz5c2, RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(8))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i6 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    function0 = constructor2;
                    $composer2.createNode(function0);
                } else {
                    function0 = constructor2;
                    $composer2.useNode();
                }
                Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl2, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i7 = (i6 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i8 = ((384 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 897673250, "C82@3532L214,87@3763L28,88@3808L187,93@4012L27,94@4056L189:HomeScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk("TEST SÜRÜMÜ", null, ColorKt.getGold(), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistMonoFamily(), TextUnitKt.getSp(0.1d), null, null, 0L, 0, false, 0, 0, null, null, $composer2, 102260742, 0, 261674);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), $composer2, 6);
                TextKt.m3069TextNvy7gAk(str2, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(13), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                SpacerKt.Spacer(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), $composer2, 0);
                TextKt.m3069TextNvy7gAk("v1.3.2-OSS-preview (14)", null, ColorKt.getTextLo(), null, TextUnitKt.getSp(11), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24576, 0, 261994);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
            } else {
                str = "CC(remember):HomeScreen.kt#9igjgp";
                $composer2.startReplaceGroup(201060937);
            }
            $composer2.endReplaceGroup();
            final State saved$delegate3 = saved$delegate;
            String str3 = str;
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), conn == ConnectionState.Connected, Dp.m7908constructorimpl(20), null, ComposableLambdaKt.rememberComposableLambda(-38979436, true, new Function3() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return HomeScreenKt.HomeScreen$lambda$30$lambda$17(ConnectionState.this, statusSub, statusDot, statusLabel, pulseAlpha$delegate, (ColumnScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer2, 54), $composer2, 24966, 8);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), $composer2, 6);
            ImageVector navi = WayfindrIcons.INSTANCE.getNavi();
            BtnVariant btnVariant = BtnVariant.Primary;
            BtnSize btnSize = BtnSize.Lg;
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 422326416, str3);
            boolean z = ($dirty2 & 112) == 32;
            Object rememberedValue4 = $composer2.rememberedValue();
            if (z || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$30$lambda$19$lambda$18(Function1.this);
                    }
                };
                $composer2.updateRememberedValue(obj2);
                rememberedValue4 = obj2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            WayfindrComponentsKt.WayfindrBtn("Navigasyonu başlat", (Function0) rememberedValue4, fillMaxWidth$default, navi, btnVariant, btnSize, false, $composer2, 221574, 64);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
            ImageVector dash = WayfindrIcons.INSTANCE.getDash();
            BtnVariant btnVariant2 = BtnVariant.Secondary;
            BtnSize btnSize2 = BtnSize.Md;
            Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 422335279, str3);
            boolean z2 = ($dirty2 & 112) == 32;
            Object rememberedValue5 = $composer2.rememberedValue();
            if (z2) {
            }
            Object obj3 = new Function0() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return HomeScreenKt.HomeScreen$lambda$30$lambda$21$lambda$20(Function1.this);
                }
            };
            $composer2.updateRememberedValue(obj3);
            rememberedValue5 = obj3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            WayfindrComponentsKt.WayfindrBtn("Konsol ekranını aç", (Function0) rememberedValue5, fillMaxWidth$default2, dash, btnVariant2, btnSize2, false, $composer2, 221574, 64);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), $composer2, 6);
            WayfindrComponentsKt.Eyebrow("Kaydedilen varış noktaları", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(4), 0.0f, 0.0f, Dp.m7908constructorimpl(6), 6, null), $composer2, 6, 0);
            if (HomeScreen$lambda$0(saved$delegate3).isEmpty()) {
                $composer2.startReplaceGroup(207835367);
                ComposerKt.sourceInformation($composer2, "171@6831L330");
                WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(16), null, ComposableSingletons$HomeScreenKt.INSTANCE.m8360getLambda$967594650$app(), $composer2, 24966, 10);
                $composer2.endReplaceGroup();
                routeViewModel3 = routeViewModel4;
            } else {
                $composer2.startReplaceGroup(208216357);
                ComposerKt.sourceInformation($composer2, "178@7256L1035,178@7191L1100");
                Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                float m7908constructorimpl2 = Dp.m7908constructorimpl(6);
                Function3 function3 = new Function3() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj4, Object obj5, Object obj6) {
                        return HomeScreenKt.HomeScreen$lambda$30$lambda$29(State.this, routeViewModel4, onNavigate, sharing$delegate, (ColumnScope) obj4, (Composer) obj5, ((Integer) obj6).intValue());
                    }
                };
                routeViewModel3 = routeViewModel4;
                WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(fillMaxWidth$default3, false, m7908constructorimpl2, null, ComposableLambdaKt.rememberComposableLambda(2095229423, true, function3, $composer2, 54), $composer2, 24966, 10);
                $composer2.endReplaceGroup();
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
            routeViewModel2 = routeViewModel3;
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return HomeScreenKt.HomeScreen$lambda$31(ConnectionState.this, onNavigate, routeViewModel2, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final List<SavedLocation> HomeScreen$lambda$0(State<? extends List<SavedLocation>> state) {
        return (List) state.getValue();
    }

    private static final SavedLocation HomeScreen$lambda$2(MutableState<SavedLocation> mutableState) {
        return mutableState.getValue();
    }

    static final Unit HomeScreen$lambda$6$lambda$5$lambda$4(MutableState $sharing$delegate) {
        $sharing$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    private static final float HomeScreen$lambda$7(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit HomeScreen$lambda$30$lambda$10(final Function1 $onNavigate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C64@2666L26,64@2620L73:HomeScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-583922907, $changed, -1, "com.example.wayfindr.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:64)");
            }
            ImageVector gear = WayfindrIcons.INSTANCE.getGear();
            ComposerKt.sourceInformationMarkerStart($composer, 1490803711, "CC(remember):HomeScreen.kt#9igjgp");
            boolean changed = $composer.changed($onNavigate);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$30$lambda$10$lambda$9$lambda$8(Function1.this);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(gear, (Function0) rememberedValue, null, 0.0f, false, null, $composer, 0, 60);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$30$lambda$10$lambda$9$lambda$8(Function1 $onNavigate) {
        $onNavigate.invoke("settings");
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$30$lambda$17(ConnectionState $conn, String $statusSub, long $statusDot, String $statusLabel, State $pulseAlpha$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Function0 function02;
        Function0 function03;
        long m5092copywmQWz5c;
        Function0 function04;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C107@4480L1607:HomeScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-38979436, $changed, -1, "com.example.wayfindr.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:107)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
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
            int i3 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 850648632, "C108@4550L234,116@4802L29,118@4849L1224:HomeScreen.kt#cef2n4");
            CircularDashKt.m8354CircularDashBrkIAW0(Dp.m7908constructorimpl(118), Offset.INSTANCE.m4865getZeroF1C5BW0(), 1.0f, null, null, $conn == ConnectionState.Connected, true, null, $composer, 1573302, 152);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), $composer, 6);
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, weight$default);
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
            ComposerKt.sourceInformationMarkerStart($composer, 223371409, "C119@4899L36,121@4957L29,123@5008L676,137@5706L29,139@5757L50,141@5829L30,143@5881L174:HomeScreen.kt#cef2n4");
            WayfindrComponentsKt.Eyebrow("Common Remington · Konsol", null, $composer, 6, 2);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(7)), $composer, 6);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier2 = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function03 = constructor3;
                $composer.createNode(function03);
            } else {
                function03 = constructor3;
                $composer.useNode();
            }
            Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer);
            Updater.m4372setimpl(m4364constructorimpl3, rowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i9 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 423220209, "C124@5086L252,129@5363L28,130@5416L246:HomeScreen.kt#cef2n4");
            Modifier clip = ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), RoundedCornerShapeKt.getCircleShape());
            m5092copywmQWz5c = Color.m5092copywmQWz5c($statusDot, (r12 & 1) != 0 ? Color.m5096getAlphaimpl($statusDot) : $conn == ConnectionState.Connected ? HomeScreen$lambda$7($pulseAlpha$delegate) : 1.0f, (r12 & 2) != 0 ? Color.m5100getRedimpl($statusDot) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl($statusDot) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl($statusDot) : 0.0f);
            BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(clip, m5092copywmQWz5c, null, 2, null), $composer, 0);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), $composer, 6);
            TextKt.m3069TextNvy7gAk($statusLabel, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(19), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.38d), null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 261674);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(4)), $composer, 6);
            TextKt.m3069TextNvy7gAk($statusSub, null, ColorKt.getTextMid(), null, TextUnitKt.getSp(13), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 262122);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer, 6);
            Arrangement.Horizontal m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier3 = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy3 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4, Alignment.INSTANCE.getTop(), $composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer, modifier3);
            Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function04 = constructor4;
                $composer.createNode(function04);
            } else {
                function04 = constructor4;
                $composer.useNode();
            }
            Composer m4364constructorimpl4 = Updater.m4364constructorimpl($composer);
            Updater.m4372setimpl(m4364constructorimpl4, rowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
            int i12 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -588917676, "C144@5963L70:HomeScreen.kt#cef2n4");
            WayfindrComponentsKt.WayfindrChip("Mt.Fuji 450", ChipTone.Gold, false, WayfindrIcons.INSTANCE.getMotor(), null, $composer, 54, 20);
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

    static final Unit HomeScreen$lambda$30$lambda$19$lambda$18(Function1 $onNavigate) {
        $onNavigate.invoke("route");
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$30$lambda$21$lambda$20(Function1 $onNavigate) {
        $onNavigate.invoke("dash");
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$30$lambda$29(State $saved$delegate, RouteViewModel $routeViewModel, Function1 $onNavigate, final MutableState $sharing$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Iterable iterable;
        Function0 function0;
        String str;
        ImageVector imageVector;
        boolean changedInstance;
        Object rememberedValue;
        final RouteViewModel routeViewModel = $routeViewModel;
        final Function1 function1 = $onNavigate;
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C*181@7411L848:HomeScreen.kt#cef2n4");
        if (composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2095229423, $changed, -1, "com.example.wayfindr.ui.screens.HomeScreen.<anonymous>.<anonymous> (HomeScreen.kt:179)");
            }
            Iterable HomeScreen$lambda$0 = HomeScreen$lambda$0($saved$delegate);
            int i = 0;
            int i2 = 0;
            for (Object obj : HomeScreen$lambda$0) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                final SavedLocation savedLocation = (SavedLocation) obj;
                if (i2 > 0) {
                    composer.startReplaceGroup(-108293970);
                    ComposerKt.sourceInformation(composer, "180@7338L52");
                    iterable = HomeScreen$lambda$0;
                    WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(4), 0.0f, 2, null), composer, 6, 0);
                } else {
                    iterable = HomeScreen$lambda$0;
                    composer.startReplaceGroup(930572328);
                }
                composer.endReplaceGroup();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
                Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                int i5 = i;
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
                int i6 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i7 = ((384 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 1918049505, "C186@7755L56,182@7489L409,192@8133L17,189@7923L314:HomeScreen.kt#cef2n4");
                String name = savedLocation.getName();
                ImageVector locationPin = WayfindrIcons.INSTANCE.getLocationPin();
                String note = savedLocation.getNote();
                if (StringsKt.isBlank(note)) {
                    str = name;
                    imageVector = locationPin;
                    String format = String.format("%.4f, %.4f", Arrays.copyOf(new Object[]{Double.valueOf(savedLocation.getLat()), Double.valueOf(savedLocation.getLng())}, 2));
                    Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                    note = format;
                } else {
                    str = name;
                    imageVector = locationPin;
                }
                String str2 = note;
                Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1585901038, "CC(remember):HomeScreen.kt#9igjgp");
                boolean changedInstance2 = $composer.changedInstance(routeViewModel) | $composer.changedInstance(savedLocation) | $composer.changed(function1);
                Object rememberedValue2 = $composer.rememberedValue();
                if (!changedInstance2 && rememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    WayfindrComponentsKt.WayfindrRow(str, weight$default, imageVector, null, str2, null, null, false, true, (Function0) rememberedValue2, $composer, 100663296, 232);
                    ImageVector share = WayfindrIcons.INSTANCE.getShare();
                    String str3 = savedLocation.getName() + " konumunu paylaş";
                    long textLo = ColorKt.getTextLo();
                    Modifier clip = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.getCircleShape());
                    ComposerKt.sourceInformationMarkerStart($composer, 1585913095, "CC(remember):HomeScreen.kt#9igjgp");
                    changedInstance = $composer.changedInstance(savedLocation);
                    rememberedValue = $composer.rememberedValue();
                    if (!changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        rememberedValue = new Function0() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return HomeScreenKt.HomeScreen$lambda$30$lambda$29$lambda$28$lambda$27$lambda$26$lambda$25(SavedLocation.this, $sharing$delegate);
                            }
                        };
                        $composer.updateRememberedValue(rememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    IconKt.m2517Iconww6aTOc(share, str3, SizeKt.m862size3ABfNKs(PaddingKt.m816padding3ABfNKs(ClickableKt.m297clickableoSLSa3U$default(clip, false, null, null, null, (Function0) rememberedValue, 15, null), Dp.m7908constructorimpl(12)), Dp.m7908constructorimpl(18)), textLo, $composer, 0, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    $composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    routeViewModel = $routeViewModel;
                    function1 = $onNavigate;
                    composer = $composer;
                    i2 = i3;
                    HomeScreen$lambda$0 = iterable;
                    i = i5;
                }
                rememberedValue2 = new Function0() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$30$lambda$29$lambda$28$lambda$27$lambda$24$lambda$23(RouteViewModel.this, savedLocation, function1);
                    }
                };
                $composer.updateRememberedValue(rememberedValue2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                WayfindrComponentsKt.WayfindrRow(str, weight$default, imageVector, null, str2, null, null, false, true, (Function0) rememberedValue2, $composer, 100663296, 232);
                ImageVector share2 = WayfindrIcons.INSTANCE.getShare();
                String str32 = savedLocation.getName() + " konumunu paylaş";
                long textLo2 = ColorKt.getTextLo();
                Modifier clip2 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.getCircleShape());
                ComposerKt.sourceInformationMarkerStart($composer, 1585913095, "CC(remember):HomeScreen.kt#9igjgp");
                changedInstance = $composer.changedInstance(savedLocation);
                rememberedValue = $composer.rememberedValue();
                if (!changedInstance) {
                }
                rememberedValue = new Function0() { // from class: com.example.wayfindr.ui.screens.HomeScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return HomeScreenKt.HomeScreen$lambda$30$lambda$29$lambda$28$lambda$27$lambda$26$lambda$25(SavedLocation.this, $sharing$delegate);
                    }
                };
                $composer.updateRememberedValue(rememberedValue);
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconKt.m2517Iconww6aTOc(share2, str32, SizeKt.m862size3ABfNKs(PaddingKt.m816padding3ABfNKs(ClickableKt.m297clickableoSLSa3U$default(clip2, false, null, null, null, (Function0) rememberedValue, 15, null), Dp.m7908constructorimpl(12)), Dp.m7908constructorimpl(18)), textLo2, $composer, 0, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                routeViewModel = $routeViewModel;
                function1 = $onNavigate;
                composer = $composer;
                i2 = i3;
                HomeScreen$lambda$0 = iterable;
                i = i5;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$30$lambda$29$lambda$28$lambda$27$lambda$24$lambda$23(RouteViewModel $routeViewModel, SavedLocation $loc, Function1 $onNavigate) {
        $routeViewModel.selectSaved($loc);
        $onNavigate.invoke("route");
        return Unit.INSTANCE;
    }

    static final Unit HomeScreen$lambda$30$lambda$29$lambda$28$lambda$27$lambda$26$lambda$25(SavedLocation $loc, MutableState $sharing$delegate) {
        $sharing$delegate.setValue($loc);
        return Unit.INSTANCE;
    }
}
