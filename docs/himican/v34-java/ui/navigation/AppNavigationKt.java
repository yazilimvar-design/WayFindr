package com.example.wayfindr.ui.navigation;

import android.content.Context;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
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
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.PointerIconCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.Navigator;
import androidx.navigation.PopUpToBuilder;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostControllerKt;
import androidx.navigation.compose.NavHostKt;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.UpdatePromptKt;
import com.example.wayfindr.ui.navigation.Screen;
import com.example.wayfindr.ui.screens.DashScreenKt;
import com.example.wayfindr.ui.screens.HomeScreenKt;
import com.example.wayfindr.ui.screens.RouteScreenKt;
import com.example.wayfindr.ui.screens.SettingsScreenKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.viewmodel.AppViewModel;
import com.example.wayfindr.viewmodel.ConnStage;
import com.example.wayfindr.viewmodel.ConnectionState;
import com.example.wayfindr.viewmodel.DashUiState;
import com.example.wayfindr.viewmodel.DashViewModel;
import com.example.wayfindr.viewmodel.RouteState;
import com.example.wayfindr.viewmodel.RouteViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u001a+\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u000b\u001a+\u0010\f\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u0010H\u0003¢\u0006\u0002\u0010\u0012\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013²\u0006\f\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0017X\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0019X\u008a\u0084\u0002"}, d2 = {"bottomTabs", "", "Lcom/example/wayfindr/ui/navigation/NavTab;", "AppNavigation", "", "appViewModel", "Lcom/example/wayfindr/viewmodel/AppViewModel;", "dashViewModel", "Lcom/example/wayfindr/viewmodel/DashViewModel;", "routeViewModel", "Lcom/example/wayfindr/viewmodel/RouteViewModel;", "(Lcom/example/wayfindr/viewmodel/AppViewModel;Lcom/example/wayfindr/viewmodel/DashViewModel;Lcom/example/wayfindr/viewmodel/RouteViewModel;Landroidx/compose/runtime/Composer;II)V", "WayfindrBottomNav", "currentRoute", "", "onNavSelect", "Lkotlin/Function1;", "Lcom/example/wayfindr/ui/navigation/Screen;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app", "navBackStackEntry", "Landroidx/navigation/NavBackStackEntry;", "routeState", "Lcom/example/wayfindr/viewmodel/RouteState;", "conn", "Lcom/example/wayfindr/viewmodel/ConnectionState;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class AppNavigationKt {
    private static final List<NavTab> bottomTabs = CollectionsKt.listOf((Object[]) new NavTab[]{new NavTab(Screen.Home.INSTANCE, WayfindrIcons.INSTANCE.getHome(), "Ana Sayfa"), new NavTab(Screen.Route.INSTANCE, WayfindrIcons.INSTANCE.getNavi(), "Rota"), new NavTab(Screen.Dash.INSTANCE, WayfindrIcons.INSTANCE.getDash(), "Konsol"), new NavTab(Screen.Rides.INSTANCE, WayfindrIcons.INSTANCE.getHistory(), "Sürüşler")});

    /* compiled from: AppNavigation.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ConnStage.values().length];
            try {
                iArr[ConnStage.STREAMING.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ConnStage.WIFI.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ConnStage.AUTH.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit AppNavigation$lambda$30(AppViewModel appViewModel, DashViewModel dashViewModel, RouteViewModel routeViewModel, int i, int i2, Composer composer, int i3) {
        AppNavigation(appViewModel, dashViewModel, routeViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit WayfindrBottomNav$lambda$38(String str, Function1 function1, int i, Composer composer, int i2) {
        WayfindrBottomNav(str, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:77:0x0328, code lost:
    
        if (r2 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L129;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0579  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x06bf  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0724  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x06fe  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x057f  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0450  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x056d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void AppNavigation(AppViewModel appViewModel, DashViewModel dashViewModel, RouteViewModel routeViewModel, Composer $composer, final int $changed, final int i) {
        AppViewModel appViewModel2;
        final DashViewModel dashViewModel2;
        final RouteViewModel routeViewModel2;
        final AppViewModel appViewModel3;
        char c;
        int i2;
        final DashViewModel dashViewModel3;
        final RouteViewModel routeViewModel3;
        CreationExtras creationExtras;
        CreationExtras creationExtras2;
        CreationExtras creationExtras3;
        boolean showBottomNav;
        String currentRoute;
        String currentRoute2;
        Function0 function0;
        Function0 function02;
        boolean changedInstance;
        Object rememberedValue;
        Object obj;
        Composer composer;
        final AppViewModel appViewModel4;
        String str;
        NavDestination destination;
        int i3;
        int i4;
        int i5;
        DashViewModel dashViewModel4 = dashViewModel;
        Composer $composer2 = $composer.startRestartGroup(1462060927);
        ComposerKt.sourceInformation($composer2, "C(AppNavigation)N(appViewModel,dashViewModel,routeViewModel)58@2355L23,59@2422L30,61@2570L7,80@3480L16,90@4155L16,91@4188L299,102@4658L133,102@4585L206,108@4910L278,108@4853L335,117@5194L2878:AppNavigation.kt#pgyg2t");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            if ((i & 1) == 0) {
                appViewModel2 = appViewModel;
                if ($composer2.changedInstance(appViewModel2)) {
                    i5 = 4;
                    $dirty |= i5;
                }
            } else {
                appViewModel2 = appViewModel;
            }
            i5 = 2;
            $dirty |= i5;
        } else {
            appViewModel2 = appViewModel;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                if (($changed & 64) == 0 ? $composer2.changed(dashViewModel4) : $composer2.changedInstance(dashViewModel4)) {
                    i4 = 32;
                    $dirty |= i4;
                }
            }
            i4 = 16;
            $dirty |= i4;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                if (($changed & 512) == 0 ? $composer2.changed(routeViewModel) : $composer2.changedInstance(routeViewModel)) {
                    i3 = 256;
                    $dirty |= i3;
                }
            }
            i3 = 128;
            $dirty |= i3;
        }
        if ($composer2.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "54@2216L11,55@2264L11,56@2314L11");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty &= -15;
                }
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                dashViewModel3 = dashViewModel4;
                routeViewModel3 = routeViewModel;
                c = 2;
                i2 = 1;
            } else {
                c = 2;
                if ((i & 1) == 0) {
                    i2 = 1;
                } else {
                    $composer2.startReplaceableGroup(1729797275);
                    ComposerKt.sourceInformation($composer2, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                    ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer2, 6);
                    if (current == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                    }
                    i2 = 1;
                    if (current instanceof HasDefaultViewModelProviderFactory) {
                        creationExtras3 = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
                    } else {
                        creationExtras3 = CreationExtras.Empty.INSTANCE;
                    }
                    ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(AppViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, creationExtras3, $composer2, ((0 << 3) & 896) | ((0 << 3) & 112) | ((0 << 3) & 7168) | ((0 << 3) & 57344), 0);
                    $composer2.endReplaceableGroup();
                    appViewModel2 = (AppViewModel) viewModel;
                    $dirty &= -15;
                }
                if ((i & 2) != 0) {
                    $composer2.startReplaceableGroup(1729797275);
                    ComposerKt.sourceInformation($composer2, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                    ViewModelStoreOwner current2 = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer2, 6);
                    if (current2 == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                    }
                    if (current2 instanceof HasDefaultViewModelProviderFactory) {
                        creationExtras2 = ((HasDefaultViewModelProviderFactory) current2).getDefaultViewModelCreationExtras();
                    } else {
                        creationExtras2 = CreationExtras.Empty.INSTANCE;
                    }
                    ViewModel viewModel2 = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(DashViewModel.class), current2, (String) null, (ViewModelProvider.Factory) null, creationExtras2, $composer2, ((0 << 3) & 112) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344), 0);
                    $composer2.endReplaceableGroup();
                    dashViewModel4 = (DashViewModel) viewModel2;
                    $dirty &= -113;
                }
                if ((i & 4) == 0) {
                    dashViewModel3 = dashViewModel4;
                    routeViewModel3 = routeViewModel;
                } else {
                    $composer2.startReplaceableGroup(1729797275);
                    ComposerKt.sourceInformation($composer2, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                    ViewModelStoreOwner current3 = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer2, 6);
                    if (current3 == null) {
                        throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                    }
                    if (current3 instanceof HasDefaultViewModelProviderFactory) {
                        creationExtras = ((HasDefaultViewModelProviderFactory) current3).getDefaultViewModelCreationExtras();
                    } else {
                        creationExtras = CreationExtras.Empty.INSTANCE;
                    }
                    ViewModel viewModel3 = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(RouteViewModel.class), current3, (String) null, (ViewModelProvider.Factory) null, creationExtras, $composer2, ((0 << 3) & 112) | ((0 << 3) & 896) | ((0 << 3) & 7168) | ((0 << 3) & 57344), 0);
                    $composer2.endReplaceableGroup();
                    $dirty &= -897;
                    dashViewModel3 = dashViewModel4;
                    routeViewModel3 = (RouteViewModel) viewModel3;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1462060927, $dirty, -1, "com.example.wayfindr.ui.navigation.AppNavigation (AppNavigation.kt:57)");
            }
            final NavHostController navController = NavHostControllerKt.rememberNavController(new Navigator[0], $composer2, 0);
            State navBackStackEntry$delegate = NavHostControllerKt.currentBackStackEntryAsState(navController, $composer2, 0);
            NavBackStackEntry AppNavigation$lambda$0 = AppNavigation$lambda$0(navBackStackEntry$delegate);
            String currentRoute3 = (AppNavigation$lambda$0 == null || (destination = AppNavigation$lambda$0.getDestination()) == null) ? null : destination.getRoute();
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            String[] strArr = new String[4];
            strArr[0] = Screen.Home.INSTANCE.getRoute();
            strArr[i2] = Screen.Route.INSTANCE.getRoute();
            strArr[c] = Screen.Dash.INSTANCE.getRoute();
            strArr[3] = Screen.Rides.INSTANCE.getRoute();
            boolean showBottomNav2 = CollectionsKt.contains(CollectionsKt.listOf((Object[]) strArr), currentRoute3);
            int i6 = i2;
            State routeState$delegate = SnapshotStateKt.collectAsState(routeViewModel3.getState(), null, $composer2, 0, i6);
            final State dashUiState = SnapshotStateKt.collectAsState(dashViewModel3.getUi(), null, $composer2, 0, i6);
            ComposerKt.sourceInformationMarkerStart($composer2, 586310506, "CC(remember):AppNavigation.kt#9igjgp");
            Object rememberedValue2 = $composer2.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object derivedStateOf = SnapshotStateKt.derivedStateOf(new Function0() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavigationKt.AppNavigation$lambda$5$lambda$4(State.this);
                    }
                });
                $composer2.updateRememberedValue(derivedStateOf);
                rememberedValue2 = derivedStateOf;
            }
            final State conn$delegate = (State) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SharedLocation destination2 = AppNavigation$lambda$3(routeState$delegate).getDestination();
            Double lat = destination2 != null ? destination2.getLat() : null;
            SharedLocation destination3 = AppNavigation$lambda$3(routeState$delegate).getDestination();
            Double lng = destination3 != null ? destination3.getLng() : null;
            ComposerKt.sourceInformationMarkerStart($composer2, 586325380, "CC(remember):AppNavigation.kt#9igjgp");
            boolean changed = (((($dirty & 112) ^ 48) > 32 && $composer2.changedInstance(dashViewModel3)) || ($dirty & 48) == 32) | $composer2.changed(routeState$delegate);
            AppNavigationKt$AppNavigation$1$1 rememberedValue3 = $composer2.rememberedValue();
            if (changed) {
                showBottomNav = showBottomNav2;
            } else {
                showBottomNav = showBottomNav2;
            }
            rememberedValue3 = new AppNavigationKt$AppNavigation$1$1(dashViewModel3, routeState$delegate, null);
            $composer2.updateRememberedValue(rememberedValue3);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(lat, lng, (Function2) rememberedValue3, $composer2, 0);
            Boolean valueOf = Boolean.valueOf(AppNavigation$lambda$3(routeState$delegate).getPendingNavigate());
            ComposerKt.sourceInformationMarkerStart($composer2, 586333589, "CC(remember):AppNavigation.kt#9igjgp");
            boolean changed2 = $composer2.changed(routeState$delegate) | $composer2.changed(currentRoute3) | $composer2.changedInstance(navController) | (((($dirty & 896) ^ 384) > 256 && $composer2.changedInstance(routeViewModel3)) || ($dirty & 384) == 256);
            Object rememberedValue4 = $composer2.rememberedValue();
            if (changed2) {
                currentRoute = currentRoute3;
            } else {
                currentRoute = currentRoute3;
                if (rememberedValue4 != Composer.INSTANCE.getEmpty()) {
                    currentRoute2 = currentRoute;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(valueOf, currentRoute2, (Function2) rememberedValue4, $composer2, 0);
                    Modifier statusBarsPadding = WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, statusBarsPadding);
                    Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if (!$composer2.getInserting()) {
                        function0 = constructor;
                        $composer2.createNode(function0);
                    } else {
                        function0 = constructor;
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
                    int i9 = ((0 >> 6) & 112) | 6;
                    ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1633938707, "C118@5255L2405,176@7851L14:AppNavigation.kt#pgyg2t");
                    Modifier weight$default = ColumnScope.weight$default(columnScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, weight$default);
                    Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    $composer2.startReusableNode();
                    if (!$composer2.getInserting()) {
                        function02 = constructor2;
                        $composer2.createNode(function02);
                    } else {
                        function02 = constructor2;
                        $composer2.useNode();
                    }
                    Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11 = (i10 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    int i12 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1020361548, "C122@5418L2232,119@5294L2356:AppNavigation.kt#pgyg2t");
                    String route = Screen.Home.INSTANCE.getRoute();
                    ComposerKt.sourceInformationMarkerStart($composer2, -310005709, "CC(remember):AppNavigation.kt#9igjgp");
                    changedInstance = (((($dirty & 896) ^ 384) <= 256 && $composer2.changedInstance(routeViewModel3)) || ($dirty & 384) == 256) | $composer2.changedInstance(navController) | (((($dirty & 112) ^ 48) <= 32 && $composer2.changedInstance(dashViewModel3)) || ($dirty & 48) == 32) | $composer2.changedInstance(appViewModel2);
                    rememberedValue = $composer2.rememberedValue();
                    if (!changedInstance && rememberedValue != Composer.INSTANCE.getEmpty()) {
                        appViewModel4 = appViewModel2;
                        composer = $composer2;
                        obj = rememberedValue;
                        str = "CC(remember):AppNavigation.kt#9igjgp";
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        NavHostKt.NavHost(navController, route, null, null, null, null, null, null, null, null, (Function1) obj, $composer2, 0, 0, PointerIconCompat.TYPE_GRAB);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        UpdatePromptKt.UpdatePrompt($composer2, 0);
                        if (showBottomNav) {
                            $composer2.startReplaceGroup(1628637613);
                        } else {
                            $composer2.startReplaceGroup(1636474785);
                            ComposerKt.sourceInformation($composer2, "181@8002L39,179@7908L148");
                            ComposerKt.sourceInformationMarkerStart($composer2, -85754500, str);
                            boolean changedInstance2 = $composer2.changedInstance(navController);
                            Object rememberedValue5 = $composer2.rememberedValue();
                            if (changedInstance2 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda9
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Object invoke(Object obj3) {
                                        return AppNavigationKt.AppNavigation$lambda$29$lambda$28$lambda$27(NavHostController.this, (Screen) obj3);
                                    }
                                };
                                $composer2.updateRememberedValue(obj2);
                                rememberedValue5 = obj2;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            WayfindrBottomNav(currentRoute2, (Function1) rememberedValue5, $composer2, 0);
                        }
                        $composer2.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        $composer2.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd($composer2);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        routeViewModel2 = routeViewModel3;
                        dashViewModel2 = dashViewModel3;
                        appViewModel3 = appViewModel4;
                    }
                    composer = $composer2;
                    appViewModel4 = appViewModel2;
                    str = "CC(remember):AppNavigation.kt#9igjgp";
                    obj = new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24(NavHostController.this, routeViewModel3, conn$delegate, dashViewModel3, appViewModel4, (NavGraphBuilder) obj3);
                        }
                    };
                    $composer2.updateRememberedValue(obj);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    NavHostKt.NavHost(navController, route, null, null, null, null, null, null, null, null, (Function1) obj, $composer2, 0, 0, PointerIconCompat.TYPE_GRAB);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    UpdatePromptKt.UpdatePrompt($composer2, 0);
                    if (showBottomNav) {
                    }
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    routeViewModel2 = routeViewModel3;
                    dashViewModel2 = dashViewModel3;
                    appViewModel3 = appViewModel4;
                }
            }
            Object appNavigationKt$AppNavigation$2$1 = new AppNavigationKt$AppNavigation$2$1(currentRoute, navController, routeViewModel3, routeState$delegate, null);
            currentRoute2 = currentRoute;
            Object obj3 = (Function2) appNavigationKt$AppNavigation$2$1;
            $composer2.updateRememberedValue(obj3);
            rememberedValue4 = obj3;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            EffectsKt.LaunchedEffect(valueOf, currentRoute2, (Function2) rememberedValue4, $composer2, 0);
            Modifier statusBarsPadding2 = WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null));
            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, statusBarsPadding2);
            Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i72 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
            }
            $composer2.startReusableNode();
            if (!$composer2.getInserting()) {
            }
            Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer2);
            Updater.m4372setimpl(m4364constructorimpl3, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i82 = (i72 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i92 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 1633938707, "C118@5255L2405,176@7851L14:AppNavigation.kt#pgyg2t");
            Modifier weight$default2 = ColumnScope.weight$default(columnScope2, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
            CompositionLocalMap currentCompositionLocalMap22 = $composer2.getCurrentCompositionLocalMap();
            Modifier materializeModifier22 = ComposedModifierKt.materializeModifier($composer2, weight$default2);
            Function0 constructor22 = ComposeUiNode.INSTANCE.getConstructor();
            int i102 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
            }
            $composer2.startReusableNode();
            if (!$composer2.getInserting()) {
            }
            Composer m4364constructorimpl22 = Updater.m4364constructorimpl($composer2);
            Updater.m4372setimpl(m4364constructorimpl22, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl22, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl22, Integer.valueOf(hashCode22), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl22, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl22, materializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
            int i112 = (i102 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i122 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1020361548, "C122@5418L2232,119@5294L2356:AppNavigation.kt#pgyg2t");
            String route2 = Screen.Home.INSTANCE.getRoute();
            ComposerKt.sourceInformationMarkerStart($composer2, -310005709, "CC(remember):AppNavigation.kt#9igjgp");
            changedInstance = (((($dirty & 896) ^ 384) <= 256 && $composer2.changedInstance(routeViewModel3)) || ($dirty & 384) == 256) | $composer2.changedInstance(navController) | (((($dirty & 112) ^ 48) <= 32 && $composer2.changedInstance(dashViewModel3)) || ($dirty & 48) == 32) | $composer2.changedInstance(appViewModel2);
            rememberedValue = $composer2.rememberedValue();
            if (!changedInstance) {
                appViewModel4 = appViewModel2;
                composer = $composer2;
                obj = rememberedValue;
                str = "CC(remember):AppNavigation.kt#9igjgp";
                ComposerKt.sourceInformationMarkerEnd($composer2);
                NavHostKt.NavHost(navController, route2, null, null, null, null, null, null, null, null, (Function1) obj, $composer2, 0, 0, PointerIconCompat.TYPE_GRAB);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                UpdatePromptKt.UpdatePrompt($composer2, 0);
                if (showBottomNav) {
                }
                $composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                }
                routeViewModel2 = routeViewModel3;
                dashViewModel2 = dashViewModel3;
                appViewModel3 = appViewModel4;
            }
            composer = $composer2;
            appViewModel4 = appViewModel2;
            str = "CC(remember):AppNavigation.kt#9igjgp";
            obj = new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj32) {
                    return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24(NavHostController.this, routeViewModel3, conn$delegate, dashViewModel3, appViewModel4, (NavGraphBuilder) obj32);
                }
            };
            $composer2.updateRememberedValue(obj);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            NavHostKt.NavHost(navController, route2, null, null, null, null, null, null, null, null, (Function1) obj, $composer2, 0, 0, PointerIconCompat.TYPE_GRAB);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            UpdatePromptKt.UpdatePrompt($composer2, 0);
            if (showBottomNav) {
            }
            $composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
            }
            routeViewModel2 = routeViewModel3;
            dashViewModel2 = dashViewModel3;
            appViewModel3 = appViewModel4;
        } else {
            $composer2.skipToGroupEnd();
            dashViewModel2 = dashViewModel4;
            routeViewModel2 = routeViewModel;
            appViewModel3 = appViewModel2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return AppNavigationKt.AppNavigation$lambda$30(AppViewModel.this, dashViewModel2, routeViewModel2, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final NavBackStackEntry AppNavigation$lambda$0(State<NavBackStackEntry> state) {
        return (NavBackStackEntry) state.getValue();
    }

    private static final void AppNavigation$navigateTab(final NavHostController navController, String route) {
        navController.navigate(route, new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavigationKt.AppNavigation$navigateTab$lambda$2(NavHostController.this, (NavOptionsBuilder) obj);
            }
        });
    }

    static final Unit AppNavigation$navigateTab$lambda$2(NavHostController $navController, NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        navigate.popUpTo(NavGraph.INSTANCE.findStartDestination($navController.getGraph()).getId(), new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavigationKt.AppNavigation$navigateTab$lambda$2$lambda$1((PopUpToBuilder) obj);
            }
        });
        navigate.setLaunchSingleTop(true);
        navigate.setRestoreState(true);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$navigateTab$lambda$2$lambda$1(PopUpToBuilder popUpTo) {
        Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
        popUpTo.setSaveState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RouteState AppNavigation$lambda$3(State<RouteState> state) {
        return (RouteState) state.getValue();
    }

    private static final ConnectionState AppNavigation$lambda$6(State<? extends ConnectionState> state) {
        return (ConnectionState) state.getValue();
    }

    static final ConnectionState AppNavigation$lambda$5$lambda$4(State $dashUiState) {
        switch (WhenMappings.$EnumSwitchMapping$0[((DashUiState) $dashUiState.getValue()).getStage().ordinal()]) {
            case 1:
                return ConnectionState.Connected;
            case 2:
            case 3:
                return ConnectionState.Searching;
            default:
                return ConnectionState.Offline;
        }
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24(final NavHostController $navController, final RouteViewModel $routeViewModel, final State $conn$delegate, final DashViewModel $dashViewModel, final AppViewModel $appViewModel, NavGraphBuilder NavHost) {
        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
        NavGraphBuilderKt.composable$default(NavHost, Screen.Home.INSTANCE.getRoute(), null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1757444514, true, new Function4() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$11(NavHostController.this, $routeViewModel, $conn$delegate, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Route.INSTANCE.getRoute(), null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(151564565, true, new Function4() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17(NavHostController.this, $dashViewModel, $routeViewModel, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Dash.INSTANCE.getRoute(), null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1195352534, true, new Function4() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$18(DashViewModel.this, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Rides.INSTANCE.getRoute(), null, null, null, null, null, null, null, ComposableSingletons$AppNavigationKt.INSTANCE.m8359getLambda$2055826793$app(), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, Screen.Settings.INSTANCE.getRoute(), null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1012038824, true, new Function4() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$23(AppViewModel.this, $navController, $conn$delegate, (AnimatedContentScope) obj, (NavBackStackEntry) obj2, (Composer) obj3, ((Integer) obj4).intValue());
            }
        }), 254, null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$11(final NavHostController $navController, RouteViewModel $routeViewModel, State $conn$delegate, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)126@5574L641,124@5488L807:AppNavigation.kt#pgyg2t");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1757444514, $changed, -1, "com.example.wayfindr.ui.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:124)");
        }
        ConnectionState AppNavigation$lambda$6 = AppNavigation$lambda$6($conn$delegate);
        ComposerKt.sourceInformationMarkerStart($composer, -982026529, "CC(remember):AppNavigation.kt#9igjgp");
        boolean changedInstance = $composer.changedInstance($navController);
        Object rememberedValue = $composer.rememberedValue();
        if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$11$lambda$10$lambda$9(NavHostController.this, (String) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            rememberedValue = obj;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        HomeScreenKt.HomeScreen(AppNavigation$lambda$6, (Function1) rememberedValue, $routeViewModel, $composer, RouteViewModel.$stable << 6, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$11$lambda$10$lambda$9(NavHostController $navController, String dest) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        switch (dest.hashCode()) {
            case 3075986:
                if (dest.equals("dash")) {
                    AppNavigation$navigateTab($navController, Screen.Dash.INSTANCE.getRoute());
                    break;
                }
                break;
            case 108704329:
                if (dest.equals("route")) {
                    AppNavigation$navigateTab($navController, Screen.Route.INSTANCE.getRoute());
                    break;
                }
                break;
            case 1434631203:
                if (dest.equals("settings")) {
                    NavController.navigate$default((NavController) $navController, Screen.Settings.INSTANCE.getRoute(), (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
                    break;
                }
                break;
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17(final NavHostController $navController, final DashViewModel $dashViewModel, RouteViewModel $routeViewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)143@6487L32,144@6560L513,141@6384L712:AppNavigation.kt#pgyg2t");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(151564565, $changed, -1, "com.example.wayfindr.ui.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:141)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, -375337067, "CC(remember):AppNavigation.kt#9igjgp");
        boolean changedInstance = $composer.changedInstance($navController);
        Object rememberedValue = $composer.rememberedValue();
        if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function0() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17$lambda$13$lambda$12(NavHostController.this);
                }
            };
            $composer.updateRememberedValue(obj);
            rememberedValue = obj;
        }
        Function0 function0 = (Function0) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -375334250, "CC(remember):AppNavigation.kt#9igjgp");
        boolean changedInstance2 = $composer.changedInstance($dashViewModel) | $composer.changedInstance($navController);
        Object rememberedValue2 = $composer.rememberedValue();
        if (changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = new Function2() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17$lambda$16$lambda$15(DashViewModel.this, $navController, (List) obj3, (Route) obj4);
                }
            };
            $composer.updateRememberedValue(obj2);
            rememberedValue2 = obj2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        RouteScreenKt.RouteScreen(function0, (Function2) rememberedValue2, $routeViewModel, $composer, RouteViewModel.$stable << 6, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17$lambda$13$lambda$12(NavHostController $navController) {
        $navController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17$lambda$16$lambda$15(DashViewModel $dashViewModel, NavHostController $navController, List stops, Route selectedRoute) {
        Intrinsics.checkNotNullParameter(stops, "stops");
        $dashViewModel.setDestinations(stops, selectedRoute);
        $dashViewModel.connect();
        $navController.navigate(Screen.Dash.INSTANCE.getRoute(), new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17$lambda$16$lambda$15$lambda$14((NavOptionsBuilder) obj);
            }
        });
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$17$lambda$16$lambda$15$lambda$14(NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        NavOptionsBuilder.popUpTo$default(navigate, Screen.Home.INSTANCE.getRoute(), (Function1) null, 2, (Object) null);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$18(DashViewModel $dashViewModel, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)157@7184L30:AppNavigation.kt#pgyg2t");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1195352534, $changed, -1, "com.example.wayfindr.ui.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:157)");
        }
        DashScreenKt.DashScreen($dashViewModel, $composer, DashViewModel.$stable, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$23(final AppViewModel $appViewModel, final NavHostController $navController, State $conn$delegate, AnimatedContentScope composable, NavBackStackEntry it, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(composable, "$this$composable");
        Intrinsics.checkNotNullParameter(it, "it");
        ComposerKt.sourceInformation($composer, "CN(it)167@7500L28,168@7563L32,165@7408L210:AppNavigation.kt#pgyg2t");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1012038824, $changed, -1, "com.example.wayfindr.ui.navigation.AppNavigation.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNavigation.kt:165)");
        }
        ConnectionState AppNavigation$lambda$6 = AppNavigation$lambda$6($conn$delegate);
        ComposerKt.sourceInformationMarkerStart($composer, -1906697292, "CC(remember):AppNavigation.kt#9igjgp");
        boolean changedInstance = $composer.changedInstance($appViewModel);
        Object rememberedValue = $composer.rememberedValue();
        if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
            Object obj = new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$23$lambda$20$lambda$19(AppViewModel.this, (ConnectionState) obj2);
                }
            };
            $composer.updateRememberedValue(obj);
            rememberedValue = obj;
        }
        Function1 function1 = (Function1) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1906695272, "CC(remember):AppNavigation.kt#9igjgp");
        boolean changedInstance2 = $composer.changedInstance($navController);
        Object rememberedValue2 = $composer.rememberedValue();
        if (changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AppNavigationKt.AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$23$lambda$22$lambda$21(NavHostController.this);
                }
            };
            $composer.updateRememberedValue(obj2);
            rememberedValue2 = obj2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        SettingsScreenKt.SettingsScreen(AppNavigation$lambda$6, function1, (Function0) rememberedValue2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$23$lambda$20$lambda$19(AppViewModel $appViewModel, ConnectionState it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $appViewModel.setConn(it);
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$26$lambda$25$lambda$24$lambda$23$lambda$22$lambda$21(NavHostController $navController) {
        $navController.popBackStack();
        return Unit.INSTANCE;
    }

    static final Unit AppNavigation$lambda$29$lambda$28$lambda$27(NavHostController $navController, Screen screen) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        AppNavigation$navigateTab($navController, screen.getRoute());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0262, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L50;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void WayfindrBottomNav(final String currentRoute, final Function1<? super Screen, Unit> function1, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0 function0;
        Object obj;
        Composer $composer3;
        Modifier m292clickableO2vRcR0;
        Function0 function02;
        Function0 function03;
        Composer $composer4 = $composer.startRestartGroup(914105832);
        ComposerKt.sourceInformation($composer4, "C(WayfindrBottomNav)N(currentRoute,onNavSelect)192@8189L2126:AppNavigation.kt#pgyg2t");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changed(currentRoute) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        if (!$composer4.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(914105832, $dirty, -1, "com.example.wayfindr.ui.navigation.WayfindrBottomNav (AppNavigation.kt:191)");
            }
            Modifier navigationBarsPadding = WindowInsetsPadding_androidKt.navigationBarsPadding(PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ColorKt.Color(3859615505L), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RectangleShapeKt.getRectangleShape()), Dp.m7908constructorimpl(10), Dp.m7908constructorimpl(8)));
            ComposerKt.sourceInformationMarkerStart($composer4, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer4, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer4.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer4, navigationBarsPadding);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $dirty2 = $dirty;
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
            String str = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
            ComposerKt.sourceInformationMarkerStart($composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer4.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer4.startReusableNode();
            if ($composer4.getInserting()) {
                function0 = constructor;
                $composer4.createNode(function0);
            } else {
                function0 = constructor;
                $composer4.useNode();
            }
            Composer m4364constructorimpl = Updater.m4364constructorimpl($composer4);
            Updater.m4372setimpl(m4364constructorimpl, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            Composer composer = $composer4;
            int i3 = 0;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i4 = ((0 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, 1068919188, "C:AppNavigation.kt#pgyg2t");
            composer.startReplaceGroup(-1073897052);
            ComposerKt.sourceInformation(composer, "*208@8890L39,210@8996L27,202@8604L1695");
            Iterable<NavTab> iterable = bottomTabs;
            int i5 = 0;
            for (final NavTab navTab : iterable) {
                Iterable iterable2 = iterable;
                int i6 = i5;
                boolean areEqual = Intrinsics.areEqual(currentRoute, navTab.getScreen().getRoute());
                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                int i7 = i3;
                CompositionLocalMap compositionLocalMap = currentCompositionLocalMap;
                Arrangement.Vertical m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(4));
                Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart(composer, 71980602, "CC(remember):AppNavigation.kt#9igjgp");
                Composer composer2 = composer;
                Object rememberedValue = composer2.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    obj = InteractionSourceKt.MutableInteractionSource();
                    composer2.updateRememberedValue(obj);
                } else {
                    obj = rememberedValue;
                }
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) obj;
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerStart(composer, 71983982, "CC(remember):AppNavigation.kt#9igjgp");
                boolean changed = (($dirty2 & 112) == 32) | $composer4.changed(navTab);
                Composer composer3 = composer;
                Object rememberedValue2 = composer3.rememberedValue();
                if (changed) {
                    $composer3 = $composer4;
                } else {
                    $composer3 = $composer4;
                }
                Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavigationKt.WayfindrBottomNav$lambda$37$lambda$36$lambda$33$lambda$32(Function1.this, navTab);
                    }
                };
                composer3.updateRememberedValue(obj2);
                rememberedValue2 = obj2;
                ComposerKt.sourceInformationMarkerEnd(composer);
                m292clickableO2vRcR0 = ClickableKt.m292clickableO2vRcR0(weight$default, mutableInteractionSource, null, (r14 & 4) != 0, (r14 & 8) != 0 ? null : null, (r14 & 16) != 0 ? null : null, (Function0) rememberedValue2);
                Modifier m818paddingVpY3zN4$default = PaddingKt.m818paddingVpY3zN4$default(m292clickableO2vRcR0, 0.0f, Dp.m7908constructorimpl(6), 1, null);
                Composer composer4 = composer;
                ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4, centerHorizontally, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composer4.getCurrentCompositionLocalMap();
                Composer composer5 = composer;
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer4, m818paddingVpY3zN4$default);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i8 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, str);
                if (!(composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    function02 = constructor2;
                    composer4.createNode(function02);
                } else {
                    function02 = constructor2;
                    composer4.useNode();
                }
                Composer m4364constructorimpl2 = Updater.m4364constructorimpl(composer4);
                Updater.m4372setimpl(m4364constructorimpl2, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i10 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, 1047911434, "C216@9341L675,232@10033L252:AppNavigation.kt#pgyg2t");
                Modifier m848height3ABfNKs = SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(29));
                Alignment topCenter = Alignment.INSTANCE.getTopCenter();
                ComposerKt.sourceInformationMarkerStart(composer4, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(topCenter, false);
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer4.getCurrentCompositionLocalMap();
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer4, m848height3ABfNKs);
                Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                int i11 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, str);
                if (!(composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                    function03 = constructor3;
                    composer4.createNode(function03);
                } else {
                    function03 = constructor3;
                    composer4.useNode();
                }
                Composer m4364constructorimpl3 = Updater.m4364constructorimpl(composer4);
                String str2 = str;
                Updater.m4372setimpl(m4364constructorimpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                int i12 = (i11 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                int i13 = ((54 >> 6) & 112) | 6;
                BoxScope boxScope = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer4, -532137763, "C217@9431L211:AppNavigation.kt#pgyg2t");
                IconKt.m2517Iconww6aTOc(navTab.getIcon(), navTab.getLabel(), SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(23)), areEqual ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextLo(), composer4, 384, 0);
                if (areEqual) {
                    composer4.startReplaceGroup(-531903280);
                    ComposerKt.sourceInformation(composer4, "223@9701L275");
                    BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), Dp.m7908constructorimpl(4)), RoundedCornerShapeKt.getCircleShape()), com.example.wayfindr.ui.theme.ColorKt.getGold(), null, 2, null), composer4, 0);
                } else {
                    composer4.startReplaceGroup(-541511885);
                }
                composer4.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                TextKt.m3069TextNvy7gAk(navTab.getLabel(), null, areEqual ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(10.5d), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer4, 1597440, 0, 261930);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                $composer4 = $composer3;
                iterable = iterable2;
                i5 = i6;
                i3 = i7;
                currentCompositionLocalMap = compositionLocalMap;
                composer = composer5;
                str = str2;
            }
            $composer2 = $composer4;
            Composer composer6 = composer;
            composer6.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer);
            $composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return AppNavigationKt.WayfindrBottomNav$lambda$38(currentRoute, function1, $changed, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    static final Unit WayfindrBottomNav$lambda$37$lambda$36$lambda$33$lambda$32(Function1 $onNavSelect, NavTab $tab) {
        $onNavSelect.invoke($tab.getScreen());
        return Unit.INSTANCE;
    }
}
