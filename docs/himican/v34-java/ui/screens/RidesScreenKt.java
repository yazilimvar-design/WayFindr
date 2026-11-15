package com.example.wayfindr.ui.screens;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
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
import androidx.compose.material.MenuKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
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
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
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
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.PolylineCodec;
import com.example.wayfindr.data.Ride;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.WayfindrComponentsKt;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.viewmodel.RidesViewModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RidesScreen.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u001b\u0010\u0005\u001a\u00020\u00012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0003¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0003¢\u0006\u0002\u0010\u000e\u001a#\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0012H\u0003¢\u0006\u0002\u0010\u0013\u001a\u001d\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0003¢\u0006\u0002\u0010\u000e\u001a#\u0010\u0015\u001a\u00020\u00012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u00072\u0006\u0010\u0018\u001a\u00020\u0019H\u0003¢\u0006\u0002\u0010\u001a\u001a\r\u0010\u001b\u001a\u00020\u0001H\u0003¢\u0006\u0002\u0010\u001c\u001a\u0018\u0010\u001d\u001a\n \u001e*\u0004\u0018\u00010\f0\f2\u0006\u0010\u001f\u001a\u00020 H\u0002\u001a\u0018\u0010!\u001a\n \u001e*\u0004\u0018\u00010\f0\f2\u0006\u0010\u001f\u001a\u00020 H\u0002\u001a\u0010\u0010\"\u001a\u00020\f2\u0006\u0010#\u001a\u00020 H\u0002¨\u0006$²\u0006\u0010\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u008a\u0084\u0002"}, d2 = {"RidesScreen", "", "ridesViewModel", "Lcom/example/wayfindr/viewmodel/RidesViewModel;", "(Lcom/example/wayfindr/viewmodel/RidesViewModel;Landroidx/compose/runtime/Composer;II)V", "RideTotals", "rides", "", "Lcom/example/wayfindr/data/Ride;", "(Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "Stat", "value", "", "label", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "RideCard", "ride", "onDelete", "Lkotlin/Function0;", "(Lcom/example/wayfindr/data/Ride;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "MiniStat", "TrackSketch", "points", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/util/List;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "EmptyRides", "(Landroidx/compose/runtime/Composer;I)V", "fmtDate", "kotlin.jvm.PlatformType", "ms", "", "fmtTime", "fmtDuration", "sec", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RidesScreenKt {
    static final Unit EmptyRides$lambda$34(int i, Composer composer, int i2) {
        EmptyRides(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit MiniStat$lambda$25(String str, String str2, int i, Composer composer, int i2) {
        MiniStat(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit RideCard$lambda$23(Ride ride, Function0 function0, int i, Composer composer, int i2) {
        RideCard(ride, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit RideTotals$lambda$10(List list, int i, Composer composer, int i2) {
        RideTotals(list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit RidesScreen$lambda$5(RidesViewModel ridesViewModel, int i, int i2, Composer composer, int i3) {
        RidesScreen(ridesViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit Stat$lambda$12(String str, String str2, int i, Composer composer, int i2) {
        Stat(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TrackSketch$lambda$33(List list, Modifier modifier, int i, Composer composer, int i2) {
        TrackSketch(list, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void RidesScreen(RidesViewModel ridesViewModel, Composer $composer, final int $changed, final int i) {
        Composer $composer2;
        int i2;
        CreationExtras.Empty empty;
        Function0 function0;
        RidesViewModel ridesViewModel2;
        boolean z;
        boolean changed;
        Composer $composer3;
        RidesViewModel ridesViewModel3;
        int i3;
        final RidesViewModel ridesViewModel4 = ridesViewModel;
        Composer $composer4 = $composer.startRestartGroup(1035058807);
        ComposerKt.sourceInformation($composer4, "C(RidesScreen)N(ridesViewModel)40@1663L16,45@1776L21,42@1685L697:RidesScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            if ((i & 1) == 0) {
                if (($changed & 8) == 0 ? $composer4.changed(ridesViewModel4) : $composer4.changedInstance(ridesViewModel4)) {
                    i3 = 4;
                    $dirty |= i3;
                }
            }
            i3 = 2;
            $dirty |= i3;
        }
        if ($composer4.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer4.startDefaults();
            ComposerKt.sourceInformation($composer4, "39@1610L11");
            if (($changed & 1) != 0 && !$composer4.getDefaultsInvalid()) {
                $composer4.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty &= -15;
                }
                i2 = 6;
            } else if ((i & 1) == 0) {
                i2 = 6;
            } else {
                $composer4.startReplaceableGroup(1729797275);
                ComposerKt.sourceInformation($composer4, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer4, 6);
                if (current == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                }
                if (current instanceof HasDefaultViewModelProviderFactory) {
                    empty = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
                } else {
                    empty = CreationExtras.Empty.INSTANCE;
                }
                i2 = 6;
                ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(RidesViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, empty, $composer4, ((0 << 3) & 7168) | ((0 << 3) & 112) | ((0 << 3) & 896) | ((0 << 3) & 57344), 0);
                $composer4.endReplaceableGroup();
                ridesViewModel4 = (RidesViewModel) viewModel;
                $dirty &= -15;
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1035058807, $dirty, -1, "com.example.wayfindr.ui.screens.RidesScreen (RidesScreen.kt:39)");
            }
            State rides$delegate = SnapshotStateKt.collectAsState(ridesViewModel4.getRides(), null, $composer4, 0, 1);
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer4, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer4, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer4, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer4.getCurrentCompositionLocalMap();
            int $dirty2 = $dirty;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer4, m820paddingqDBjuR0$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer4, 96464167, "C49@1881L162:RidesScreen.kt#cef2n4");
            WayfindrComponentsKt.ScreenHeader(null, "Sürüş geçmişi", "Telemetri", "Bağlantı kurulan her sürüş burada kaydedilir: mesafe, süre ve izlediğiniz rota.", false, null, null, $composer4, 3504, 113);
            if (RidesScreen$lambda$0(rides$delegate).isEmpty()) {
                $composer4.startReplaceGroup(96641393);
                ComposerKt.sourceInformation($composer4, "53@2088L12");
                EmptyRides($composer4, 0);
                $composer4.endReplaceGroup();
                ridesViewModel2 = ridesViewModel4;
                $composer2 = $composer4;
            } else {
                $composer4.startReplaceGroup(96690001);
                ComposerKt.sourceInformation($composer4, "55@2130L17,56@2160L30,*58@2269L35,58@2243L62,59@2322L30");
                RideTotals(RidesScreen$lambda$0(rides$delegate), $composer4, 0);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer4, i2);
                Iterable RidesScreen$lambda$0 = RidesScreen$lambda$0(rides$delegate);
                int i7 = 0;
                Iterator it = RidesScreen$lambda$0.iterator();
                while (it.hasNext()) {
                    Iterable iterable = RidesScreen$lambda$0;
                    final Ride ride = (Ride) it.next();
                    int i8 = i7;
                    Iterator it2 = it;
                    ComposerKt.sourceInformationMarkerStart($composer4, -491660117, "CC(remember):RidesScreen.kt#9igjgp");
                    if (((($dirty2 & 14) ^ 6) <= 4 || !$composer4.changedInstance(ridesViewModel4)) && ($dirty2 & 6) != 4) {
                        z = false;
                        changed = z | $composer4.changed(ride);
                        Object rememberedValue = $composer4.rememberedValue();
                        if (changed) {
                            $composer3 = $composer4;
                            if (rememberedValue != Composer.INSTANCE.getEmpty()) {
                                ridesViewModel3 = ridesViewModel4;
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                RideCard(ride, (Function0) rememberedValue, $composer4, 0);
                                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer4, 6);
                                RidesScreen$lambda$0 = iterable;
                                i7 = i8;
                                it = it2;
                                ridesViewModel4 = ridesViewModel3;
                                $composer4 = $composer3;
                            }
                        } else {
                            $composer3 = $composer4;
                        }
                        Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda10
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RidesScreenKt.RidesScreen$lambda$4$lambda$3$lambda$2$lambda$1(RidesViewModel.this, ride);
                            }
                        };
                        ridesViewModel3 = ridesViewModel4;
                        $composer4.updateRememberedValue(obj);
                        rememberedValue = obj;
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        RideCard(ride, (Function0) rememberedValue, $composer4, 0);
                        SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer4, 6);
                        RidesScreen$lambda$0 = iterable;
                        i7 = i8;
                        it = it2;
                        ridesViewModel4 = ridesViewModel3;
                        $composer4 = $composer3;
                    }
                    z = true;
                    changed = z | $composer4.changed(ride);
                    Object rememberedValue2 = $composer4.rememberedValue();
                    if (changed) {
                    }
                    Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RidesScreenKt.RidesScreen$lambda$4$lambda$3$lambda$2$lambda$1(RidesViewModel.this, ride);
                        }
                    };
                    ridesViewModel3 = ridesViewModel4;
                    $composer4.updateRememberedValue(obj2);
                    rememberedValue2 = obj2;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    RideCard(ride, (Function0) rememberedValue2, $composer4, 0);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer4, 6);
                    RidesScreen$lambda$0 = iterable;
                    i7 = i8;
                    it = it2;
                    ridesViewModel4 = ridesViewModel3;
                    $composer4 = $composer3;
                }
                ridesViewModel2 = ridesViewModel4;
                $composer2 = $composer4;
                $composer4.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            $composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ridesViewModel4 = ridesViewModel2;
        } else {
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return RidesScreenKt.RidesScreen$lambda$5(RidesViewModel.this, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    private static final List<Ride> RidesScreen$lambda$0(State<? extends List<Ride>> state) {
        return (List) state.getValue();
    }

    static final Unit RidesScreen$lambda$4$lambda$3$lambda$2$lambda$1(RidesViewModel $ridesViewModel, Ride $ride) {
        $ridesViewModel.deleteRide($ride);
        return Unit.INSTANCE;
    }

    private static final void RideTotals(final List<Ride> list, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1820115426);
        ComposerKt.sourceInformation($composer2, "C(RideTotals)N(rides)69@2610L262,69@2544L328:RidesScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1820115426, $dirty, -1, "com.example.wayfindr.ui.screens.RideTotals (RidesScreen.kt:66)");
            }
            Iterator<T> it = list.iterator();
            double d = 0.0d;
            while (it.hasNext()) {
                d += ((Ride) it.next()).getDistanceKm();
            }
            final double totalKm = d;
            Iterator<T> it2 = list.iterator();
            final long totalSec = 0;
            while (it2.hasNext()) {
                totalSec += ((Ride) it2.next()).getDurationSec();
            }
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(18), null, ComposableLambdaKt.rememberComposableLambda(-1231609629, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return RidesScreenKt.RideTotals$lambda$9(list, totalKm, totalSec, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 24966, 10);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RidesScreenKt.RideTotals$lambda$10(list, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit RideTotals$lambda$9(List $rides, double $totalKm, long $totalSec, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C70@2620L246:RidesScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1231609629, $changed, -1, "com.example.wayfindr.ui.screens.RideTotals.<anonymous> (RidesScreen.kt:70)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, Alignment.INSTANCE.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((54 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1546668832, "C71@2724L30,72@2767L41,73@2821L35:RidesScreen.kt#cef2n4");
            Stat(String.valueOf($rides.size()), "sürüş", $composer, 48);
            String format = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf($totalKm)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Stat(format, "toplam km", $composer, 48);
            Stat(fmtDuration($totalSec), "süre", $composer, 48);
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

    private static final void Stat(final String value, String label, Composer $composer, final int $changed) {
        final String str;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-2084699927);
        ComposerKt.sourceInformation($composer3, "C(Stat)N(value,label)80@2941L254:RidesScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(label) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            str = label;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2084699927, $dirty, -1, "com.example.wayfindr.ui.screens.Stat (RidesScreen.kt:79)");
            }
            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((384 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -317062863, "C81@3010L99,82@3118L71:RidesScreen.kt#cef2n4");
            $composer2 = $composer3;
            TextKt.m3069TextNvy7gAk(value, null, ColorKt.getGold(), null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, ($dirty & 14) | 1597440, 0, 261930);
            str = label;
            TextKt.m3069TextNvy7gAk(str, null, ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, (($dirty >> 3) & 14) | 24576, 0, 261994);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RidesScreenKt.Stat$lambda$12(value, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void RideCard(final Ride ride, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2043649561);
        ComposerKt.sourceInformation($composer2, "C(RideCard)N(ride,onDelete)88@3284L144,92@3499L2013,92@3433L2079:RidesScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(ride) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2043649561, $dirty2, -1, "com.example.wayfindr.ui.screens.RideCard (RidesScreen.kt:87)");
            }
            String trackPolyline = ride.getTrackPolyline();
            ComposerKt.sourceInformationMarkerStart($composer2, 1147176713, "CC(remember):RidesScreen.kt#9igjgp");
            boolean changed = $composer2.changed(trackPolyline);
            Object rememberedValue = $composer2.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object emptyList = StringsKt.isBlank(ride.getTrackPolyline()) ? CollectionsKt.emptyList() : PolylineCodec.decode$default(PolylineCodec.INSTANCE, ride.getTrackPolyline(), 0, 2, null);
                $composer2.updateRememberedValue(emptyList);
                rememberedValue = emptyList;
            }
            final List track = (List) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(16), null, ComposableLambdaKt.rememberComposableLambda(1552608222, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return RidesScreenKt.RideCard$lambda$22(Ride.this, track, function0, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 24966, 10);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RidesScreenKt.RideCard$lambda$23(Ride.this, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0831, code lost:
    
        if (r6 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L58;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit RideCard$lambda$22(Ride $ride, List $track, final Function0 $onDelete, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Function0 function02;
        Function0 function03;
        Function0 function04;
        Function0 function05;
        Function0 function06;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C93@3509L1997:RidesScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1552608222, $changed, -1, "com.example.wayfindr.ui.screens.RideCard.<anonymous> (RidesScreen.kt:93)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((6 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 217662758, "C94@3555L1183,113@4751L30,114@4794L702:RidesScreen.kt#cef2n4");
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default2);
            Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            int i4 = ((((390 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl2, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            int i5 = (i4 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i6 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, -1003588904, "C96@3693L426,103@4136L29,104@4182L398,111@4597L127:RidesScreen.kt#cef2n4");
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier m262backgroundbw27NRU$default = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(64)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getSurf2(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, m262backgroundbw27NRU$default);
            Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl3, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i9 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1717153946, "C:RidesScreen.kt#cef2n4");
            if ($track.size() >= 2) {
                $composer.startReplaceGroup(-1745722730);
                ComposerKt.sourceInformation($composer, "100@3919L56");
                TrackSketch($track, PaddingKt.m816padding3ABfNKs(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7908constructorimpl(8)), $composer, 48);
                $composer.endReplaceGroup();
            } else {
                $composer.startReplaceGroup(1717252928);
                ComposerKt.sourceInformation($composer, "101@4001L100");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getRoute(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), ColorKt.getTextLo(), $composer, 432, 0);
                $composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer, 6);
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer, weight$default);
            Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
            int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl4, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
            int i11 = (i10 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
            int i12 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 653027435, "C105@4232L121,106@4374L188:RidesScreen.kt#cef2n4");
            String fmtDate = fmtDate($ride.getStartMs());
            Intrinsics.checkNotNullExpressionValue(fmtDate, "fmtDate(...)");
            TextKt.m3069TextNvy7gAk(fmtDate, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(15), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 261930);
            TextKt.m3069TextNvy7gAk(fmtTime($ride.getStartMs()) + " – " + fmtTime($ride.getEndMs()), null, ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 261994);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            String format = String.format("%.1f km", Arrays.copyOf(new Object[]{Double.valueOf($ride.getDistanceKm())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            TextKt.m3069TextNvy7gAk(format, null, ColorKt.getGold(), null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 261930);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer, 6);
            Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, Alignment.INSTANCE.getTop(), $composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap5 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier5 = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default3);
            Function0 constructor5 = ComposeUiNode.INSTANCE.getConstructor();
            int i13 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function05 = constructor5;
                $composer.createNode(function05);
            } else {
                function05 = constructor5;
                $composer.useNode();
            }
            Composer m4364constructorimpl5 = Updater.m4364constructorimpl($composer);
            Updater.m4372setimpl(m4364constructorimpl5, rowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl5, Integer.valueOf(hashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl5, materializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
            int i14 = (i13 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i15 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1562663438, "C115@4902L47,116@4966L59,117@5042L60,121@5304L14,118@5119L363:RidesScreen.kt#cef2n4");
            MiniStat(fmtDuration($ride.getDurationSec()), "süre", $composer, 48);
            String format2 = String.format("%.0f km/sa", Arrays.copyOf(new Object[]{Double.valueOf($ride.getAvgSpeedKmh())}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            MiniStat(format2, "ortalama", $composer, 48);
            String format3 = String.format("%.0f km/sa", Arrays.copyOf(new Object[]{Double.valueOf($ride.getMaxSpeedKmh())}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
            MiniStat(format3, "en yüksek", $composer, 48);
            Alignment center2 = Alignment.INSTANCE.getCenter();
            Modifier m262backgroundbw27NRU$default2 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(34)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getSurf2(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer, -1020227525, "CC(remember):RidesScreen.kt#9igjgp");
            boolean changed = $composer.changed($onDelete);
            Object rememberedValue = $composer.rememberedValue();
            if (changed) {
            }
            Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RidesScreenKt.RideCard$lambda$22$lambda$21$lambda$20$lambda$18$lambda$17(Function0.this);
                }
            };
            $composer.updateRememberedValue(obj);
            rememberedValue = obj;
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier m297clickableoSLSa3U$default = ClickableKt.m297clickableoSLSa3U$default(m262backgroundbw27NRU$default2, false, null, null, null, (Function0) rememberedValue, 15, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap6 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier6 = ComposedModifierKt.materializeModifier($composer, m297clickableoSLSa3U$default);
            Function0 constructor6 = ComposeUiNode.INSTANCE.getConstructor();
            int i16 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer.startReusableNode();
            if ($composer.getInserting()) {
                function06 = constructor6;
                $composer.createNode(function06);
            } else {
                function06 = constructor6;
                $composer.useNode();
            }
            Composer m4364constructorimpl6 = Updater.m4364constructorimpl($composer);
            Updater.m4372setimpl(m4364constructorimpl6, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl6, Integer.valueOf(hashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl6, materializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
            int i17 = (i16 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            int i18 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 1782448340, "C123@5360L104:RidesScreen.kt#cef2n4");
            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getX(), "Sürüşü sil", SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), ColorKt.getTextLo(), $composer, 432, 0);
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

    static final Unit RideCard$lambda$22$lambda$21$lambda$20$lambda$18$lambda$17(Function0 $onDelete) {
        $onDelete.invoke();
        return Unit.INSTANCE;
    }

    private static final void MiniStat(final String value, String label, Composer $composer, final int $changed) {
        final String str;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1990223214);
        ComposerKt.sourceInformation($composer3, "C(MiniStat)N(value,label)132@5585L247:RidesScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(value) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(label) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            str = label;
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1990223214, $dirty, -1, "com.example.wayfindr.ui.screens.MiniStat (RidesScreen.kt:131)");
            }
            Alignment.Horizontal start = Alignment.INSTANCE.getStart();
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), start, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, modifier);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((384 << 3) & 112) << 6) & 896) | 6;
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
            Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i3 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -481794398, "C133@5641L105,134@5755L71:RidesScreen.kt#cef2n4");
            $composer2 = $composer3;
            TextKt.m3069TextNvy7gAk(value, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, ($dirty & 14) | 1597440, 0, 261930);
            str = label;
            TextKt.m3069TextNvy7gAk(str, null, ColorKt.getTextLo(), null, TextUnitKt.getSp(11), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, (($dirty >> 3) & 14) | 24576, 0, 261994);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RidesScreenKt.MiniStat$lambda$25(value, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void TrackSketch(final List<GeoPoint> list, final Modifier modifier, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1342807127);
        ComposerKt.sourceInformation($composer2, "C(TrackSketch)N(points,modifier)140@5969L1219,140@5952L1236:RidesScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(list) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1342807127, $dirty, -1, "com.example.wayfindr.ui.screens.TrackSketch (RidesScreen.kt:139)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1947454772, "CC(remember):RidesScreen.kt#9igjgp");
            boolean changedInstance = $composer2.changedInstance(list);
            Object rememberedValue = $composer2.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function1() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return RidesScreenKt.TrackSketch$lambda$32$lambda$31(list, (DrawScope) obj2);
                    }
                };
                $composer2.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(modifier, (Function1) rememberedValue, $composer2, ($dirty >> 3) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return RidesScreenKt.TrackSketch$lambda$33(list, modifier, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit TrackSketch$lambda$32$lambda$31(List $points, DrawScope $this$Canvas) {
        DrawScope Canvas = $this$Canvas;
        Intrinsics.checkNotNullParameter(Canvas, "$this$Canvas");
        Iterator it = $points.iterator();
        if (!it.hasNext()) {
            throw new NoSuchElementException();
        }
        double lat = ((GeoPoint) it.next()).getLat();
        while (it.hasNext()) {
            lat = Math.min(lat, ((GeoPoint) it.next()).getLat());
        }
        double minLat = lat;
        Iterator it2 = $points.iterator();
        if (!it2.hasNext()) {
            throw new NoSuchElementException();
        }
        double lat2 = ((GeoPoint) it2.next()).getLat();
        while (it2.hasNext()) {
            lat2 = Math.max(lat2, ((GeoPoint) it2.next()).getLat());
        }
        double maxLat = lat2;
        Iterator it3 = $points.iterator();
        if (!it3.hasNext()) {
            throw new NoSuchElementException();
        }
        double lng = ((GeoPoint) it3.next()).getLng();
        while (it3.hasNext()) {
            lng = Math.min(lng, ((GeoPoint) it3.next()).getLng());
        }
        double minLng = lng;
        Iterator it4 = $points.iterator();
        if (!it4.hasNext()) {
            throw new NoSuchElementException();
        }
        double lng2 = ((GeoPoint) it4.next()).getLng();
        while (it4.hasNext()) {
            lng2 = Math.max(lng2, ((GeoPoint) it4.next()).getLng());
        }
        double maxLng = lng2;
        double spanLat = RangesKt.coerceAtLeast(maxLat - minLat, 1.0E-6d);
        double spanLng = RangesKt.coerceAtLeast(maxLng - minLng, 1.0E-6d);
        double span = Math.max(spanLat, spanLng);
        double spanLng2 = spanLng;
        Path path = AndroidPath_androidKt.Path();
        List list = $points;
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            GeoPoint geoPoint = (GeoPoint) obj;
            Iterable iterable = list;
            float intBitsToFloat = (float) (Float.intBitsToFloat((int) (Canvas.mo5668getSizeNHjbRc() >> 32)) * ((geoPoint.getLng() - minLng) / span));
            double spanLng3 = spanLng2;
            float intBitsToFloat2 = (float) (Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() & 4294967295L)) - (((geoPoint.getLat() - minLat) / span) * Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() & 4294967295L))));
            if (i == 0) {
                path.moveTo(intBitsToFloat, intBitsToFloat2);
            } else {
                path.lineTo(intBitsToFloat, intBitsToFloat2);
            }
            Canvas = $this$Canvas;
            i = i2;
            spanLng2 = spanLng3;
            list = iterable;
        }
        DrawScope.m5658drawPathLG529CI$default($this$Canvas, path, ColorKt.getGold(), 0.0f, new Stroke(3.0f, 0.0f, StrokeCap.INSTANCE.m5469getRoundKaPHkGw(), StrokeJoin.INSTANCE.m5480getRoundLxFBmk8(), null, 18, null), null, 0, 52, null);
        GeoPoint first = (GeoPoint) CollectionsKt.first($points);
        GeoPoint last = (GeoPoint) CollectionsKt.last($points);
        long textHi = ColorKt.getTextHi();
        float lng3 = (float) (((first.getLng() - minLng) / span) * Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() >> 32)));
        float intBitsToFloat3 = (float) (Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() & 4294967295L)) - (((first.getLat() - minLat) / span) * Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() & 4294967295L))));
        DrawScope.m5649drawCircleVaOC9Bg$default($this$Canvas, textHi, 2.5f, Offset.m4841constructorimpl((Float.floatToRawIntBits(lng3) << 32) | (Float.floatToRawIntBits(intBitsToFloat3) & 4294967295L)), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        long gold = ColorKt.getGold();
        float lng4 = (float) (((last.getLng() - minLng) / span) * Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() >> 32)));
        float intBitsToFloat4 = (float) (Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() & 4294967295L)) - (((last.getLat() - minLat) / span) * Float.intBitsToFloat((int) ($this$Canvas.mo5668getSizeNHjbRc() & 4294967295L))));
        DrawScope.m5649drawCircleVaOC9Bg$default($this$Canvas, gold, 2.5f, Offset.m4841constructorimpl((Float.floatToRawIntBits(lng4) << 32) | (Float.floatToRawIntBits(intBitsToFloat4) & 4294967295L)), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        return Unit.INSTANCE;
    }

    private static final void EmptyRides(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1909109053);
        ComposerKt.sourceInformation($composer2, "C(EmptyRides)164@7235L987:RidesScreen.kt#cef2n4");
        if (!$composer2.shouldExecute($changed != 0, $changed & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1909109053, $changed, -1, "com.example.wayfindr.ui.screens.EmptyRides (RidesScreen.kt:163)");
            }
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(28), null, ComposableSingletons$RidesScreenKt.INSTANCE.getLambda$1900764168$app(), $composer2, 24966, 10);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RidesScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RidesScreenKt.EmptyRides$lambda$34($changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String fmtDate(long ms) {
        return new SimpleDateFormat("d MMM yyyy, EEEE", Locale.forLanguageTag("tr-TR")).format(new Date(ms));
    }

    private static final String fmtTime(long ms) {
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(ms));
    }

    private static final String fmtDuration(long sec) {
        long h = sec / 3600;
        long m = (sec % 3600) / 60;
        return (h > 0 ? new StringBuilder().append(h).append(" sa ") : new StringBuilder()).append(m).append(" dk").toString();
    }
}
