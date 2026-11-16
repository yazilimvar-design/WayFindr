package com.example.wayfindr.ui.screens;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
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
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.PolylineCodec;
import com.example.wayfindr.data.Ride;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.WayfindrComponentsKt;
import com.example.wayfindr.ui.screens.ComposableSingletons;
import com.example.wayfindr.ui.screens.RidesScreenKt$;
import com.example.wayfindr.ui.screens.SettingsScreenKt;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.viewmodel.RidesViewModel;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;

/* compiled from: RidesScreen.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class RidesScreenKt {
    private static final void EmptyRides(Composer composer, int i) {
        Composer startRestartGroup = composer.startRestartGroup(-1909109053);
        ComposerKt.sourceInformation(startRestartGroup, "C(EmptyRides)164@7235L987:RidesScreen.kt#cef2n4");
        if (startRestartGroup.shouldExecute(i != 0, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1909109053, i, -1, "com.example.wayfindr.ui.screens.EmptyRides (RidesScreen.kt:163)");
            }
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(28), (Function0) null, ComposableSingletons.RidesScreenKt.INSTANCE.getLambda$1900764168$app(), startRestartGroup, 24966, 10);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RidesScreenKt$.ExternalSyntheticLambda9(i));
        }
    }

    static final Unit EmptyRides$lambda$34(int i, Composer composer, int i2) {
        EmptyRides(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void MiniStat(String str, String str2, Composer composer, int i) {
        String str3;
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(-1990223214);
        ComposerKt.sourceInformation(startRestartGroup, "C(MiniStat)N(value,label)132@5585L247:RidesScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changed(str) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(str2) ? 32 : 16;
        }
        if (startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1990223214, i2, -1, "com.example.wayfindr.ui.screens.MiniStat (RidesScreen.kt:131)");
            }
            Alignment.Horizontal start = Alignment.Companion.getStart();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.Companion;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), start, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i3 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor);
            } else {
                startRestartGroup.useNode();
            }
            Composer composer3 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -481794398, "C133@5641L105,134@5755L71:RidesScreen.kt#cef2n4");
            composer2 = startRestartGroup;
            TextKt.Text-Nvy7gAk(str, (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, (i2 & 14) | 1597440, 0, 261930);
            str3 = str2;
            TextKt.Text-Nvy7gAk(str3, (Modifier) null, ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, ((i2 >> 3) & 14) | 24576, 0, 261994);
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
            str3 = str2;
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RidesScreenKt$.ExternalSyntheticLambda2(str, str3, i));
        }
    }

    static final Unit MiniStat$lambda$25(String str, String str2, int i, Composer composer, int i2) {
        MiniStat(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void RideCard(Ride ride, Function0 function0, Composer composer, int i) {
        Composer startRestartGroup = composer.startRestartGroup(2043649561);
        ComposerKt.sourceInformation(startRestartGroup, "C(RideCard)N(ride,onDelete)88@3284L144,92@3499L2013,92@3433L2079:RidesScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changed(ride) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        int i3 = i2;
        if (startRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2043649561, i3, -1, "com.example.wayfindr.ui.screens.RideCard (RidesScreen.kt:87)");
            }
            String trackPolyline = ride.getTrackPolyline();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1147176713, "CC(remember):RidesScreen.kt#9igjgp");
            boolean changed = startRestartGroup.changed(trackPolyline);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                List emptyList = StringsKt.isBlank(ride.getTrackPolyline()) ? CollectionsKt.emptyList() : PolylineCodec.decode$default(PolylineCodec.INSTANCE, ride.getTrackPolyline(), 0, 2, (Object) null);
                startRestartGroup.updateRememberedValue(emptyList);
                rememberedValue = emptyList;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(16), (Function0) null, ComposableLambdaKt.rememberComposableLambda(1552608222, true, new RidesScreenKt$.ExternalSyntheticLambda0(ride, (List) rememberedValue, function0), startRestartGroup, 54), startRestartGroup, 24966, 10);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RidesScreenKt$.ExternalSyntheticLambda1(ride, function0, i));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0831, code lost:
    
        if (r6 != androidx.compose.runtime.Composer.Companion.getEmpty()) goto L58;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit RideCard$lambda$22(Ride ride, List list, Function0 function0, ColumnScope columnScope, Composer composer, int i) {
        Function0 function02;
        Function0 function03;
        Function0 function04;
        Function0 function05;
        Function0 function06;
        Function0 function07;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C93@3509L1997:RidesScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1552608222, i, -1, "com.example.wayfindr.ui.screens.RideCard.<anonymous> (RidesScreen.kt:93)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i2 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function02 = constructor;
                composer.createNode(function02);
            } else {
                function02 = constructor;
                composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composer);
            Updater.set-impl(composer2, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
            int i4 = ((6 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, 217662758, "C94@3555L1183,113@4751L30,114@4794L702:RidesScreen.kt#cef2n4");
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer, fillMaxWidth$default2);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i5 = ((((390 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function03 = constructor2;
                composer.createNode(function03);
            } else {
                function03 = constructor2;
                composer.useNode();
            }
            Composer composer3 = Updater.constructor-impl(composer);
            Updater.set-impl(composer3, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode2), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i6 = (i5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            int i7 = ((390 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -1003588904, "C96@3693L426,103@4136L29,104@4182L398,111@4597L127:RidesScreen.kt#cef2n4");
            Alignment center = Alignment.Companion.getCenter();
            Modifier modifier = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(64)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), ColorKt.getSurf2(), (Shape) null, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer, modifier);
            Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
            int i8 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function04 = constructor3;
                composer.createNode(function04);
            } else {
                function04 = constructor3;
                composer.useNode();
            }
            Composer composer4 = Updater.constructor-impl(composer);
            Updater.set-impl(composer4, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer4, Integer.valueOf(hashCode3), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer4, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer4, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
            int i9 = (i8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScope boxScope = BoxScopeInstance.INSTANCE;
            int i10 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, 1717153946, "C:RidesScreen.kt#cef2n4");
            if (list.size() >= 2) {
                composer.startReplaceGroup(-1745722730);
                ComposerKt.sourceInformation(composer, "100@3919L56");
                TrackSketch(list, PaddingKt.padding-3ABfNKs(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(8)), composer, 48);
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(1717252928);
                ComposerKt.sourceInformation(composer, "101@4001L100");
                IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getRoute(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), ColorKt.getTextLo(), composer, 432, 0);
                composer.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(14)), composer, 6);
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composer, weight$default);
            Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
            int i11 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function05 = constructor4;
                composer.createNode(function05);
            } else {
                function05 = constructor4;
                composer.useNode();
            }
            Composer composer5 = Updater.constructor-impl(composer);
            Updater.set-impl(composer5, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer5, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer5, Integer.valueOf(hashCode4), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer5, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer5, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
            int i12 = (i11 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope3 = ColumnScopeInstance.INSTANCE;
            int i13 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, 653027435, "C105@4232L121,106@4374L188:RidesScreen.kt#cef2n4");
            String fmtDate = fmtDate(ride.getStartMs());
            Intrinsics.checkNotNullExpressionValue(fmtDate, "fmtDate(...)");
            TextKt.Text-Nvy7gAk(fmtDate, (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 1597440, 0, 261930);
            TextKt.Text-Nvy7gAk(fmtTime(ride.getStartMs()) + " – " + fmtTime(ride.getEndMs()), (Modifier) null, ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24576, 0, 261994);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            String format = String.format("%.1f km", Arrays.copyOf(new Object[]{Double.valueOf(ride.getDistanceKm())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            TextKt.Text-Nvy7gAk(format, (Modifier) null, ColorKt.getGold(), (TextAutoSize) null, TextUnitKt.getSp(18), (FontStyle) null, FontWeight.Companion.getBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 1597440, 0, 261930);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer, 6);
            Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, Alignment.Companion.getTop(), composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier5 = ComposedModifierKt.materializeModifier(composer, fillMaxWidth$default3);
            Function0 constructor5 = ComposeUiNode.Companion.getConstructor();
            int i14 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function06 = constructor5;
                composer.createNode(function06);
            } else {
                function06 = constructor5;
                composer.useNode();
            }
            Composer composer6 = Updater.constructor-impl(composer);
            Updater.set-impl(composer6, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer6, currentCompositionLocalMap5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer6, Integer.valueOf(hashCode5), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer6, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer6, materializeModifier5, ComposeUiNode.Companion.getSetModifier());
            int i15 = (i14 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScope rowScope2 = RowScopeInstance.INSTANCE;
            int i16 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -1562663438, "C115@4902L47,116@4966L59,117@5042L60,121@5304L14,118@5119L363:RidesScreen.kt#cef2n4");
            MiniStat(fmtDuration(ride.getDurationSec()), "süre", composer, 48);
            String format2 = String.format("%.0f km/sa", Arrays.copyOf(new Object[]{Double.valueOf(ride.getAvgSpeedKmh())}, 1));
            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
            MiniStat(format2, "ortalama", composer, 48);
            String format3 = String.format("%.0f km/sa", Arrays.copyOf(new Object[]{Double.valueOf(ride.getMaxSpeedKmh())}, 1));
            Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
            MiniStat(format3, "en yüksek", composer, 48);
            Alignment center2 = Alignment.Companion.getCenter();
            Modifier modifier2 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(34)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getSurf2(), (Shape) null, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, -1020227525, "CC(remember):RidesScreen.kt#9igjgp");
            boolean changed = composer.changed(function0);
            Object rememberedValue = composer.rememberedValue();
            if (changed) {
            }
            RidesScreenKt$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new RidesScreenKt$.ExternalSyntheticLambda3(function0);
            composer.updateRememberedValue(externalSyntheticLambda3);
            rememberedValue = externalSyntheticLambda3;
            ComposerKt.sourceInformationMarkerEnd(composer);
            Modifier modifier3 = ClickableKt.clickable-oSLSa3U$default(modifier2, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue, 15, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap6 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier6 = ComposedModifierKt.materializeModifier(composer, modifier3);
            Function0 constructor6 = ComposeUiNode.Companion.getConstructor();
            int i17 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function07 = constructor6;
                composer.createNode(function07);
            } else {
                function07 = constructor6;
                composer.useNode();
            }
            Composer composer7 = Updater.constructor-impl(composer);
            Updater.set-impl(composer7, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer7, currentCompositionLocalMap6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer7, Integer.valueOf(hashCode6), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer7, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer7, materializeModifier6, ComposeUiNode.Companion.getSetModifier());
            int i18 = (i17 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
            int i19 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, 1782448340, "C123@5360L104:RidesScreen.kt#cef2n4");
            IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getX(), "Sürüşü sil", SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), ColorKt.getTextLo(), composer, 432, 0);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RideCard$lambda$22$lambda$21$lambda$20$lambda$18$lambda$17(Function0 function0) {
        function0.invoke();
        return Unit.INSTANCE;
    }

    static final Unit RideCard$lambda$23(Ride ride, Function0 function0, int i, Composer composer, int i2) {
        RideCard(ride, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void RideTotals(List list, Composer composer, int i) {
        Composer startRestartGroup = composer.startRestartGroup(-1820115426);
        ComposerKt.sourceInformation(startRestartGroup, "C(RideTotals)N(rides)69@2610L262,69@2544L328:RidesScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changedInstance(list) ? 4 : 2;
        }
        if (startRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1820115426, i2, -1, "com.example.wayfindr.ui.screens.RideTotals (RidesScreen.kt:66)");
            }
            Iterator it = list.iterator();
            double d = 0.0d;
            while (it.hasNext()) {
                d += ((Ride) it.next()).getDistanceKm();
            }
            double d2 = d;
            Iterator it2 = list.iterator();
            long j = 0;
            while (it2.hasNext()) {
                j += ((Ride) it2.next()).getDurationSec();
            }
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(18), (Function0) null, ComposableLambdaKt.rememberComposableLambda(-1231609629, true, new RidesScreenKt$.ExternalSyntheticLambda5(list, d2, j), startRestartGroup, 54), startRestartGroup, 24966, 10);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RidesScreenKt$.ExternalSyntheticLambda6(list, i));
        }
    }

    static final Unit RideTotals$lambda$10(List list, int i, Composer composer, int i2) {
        RideTotals(list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static Unit RideTotals$lambda$9(List list, double d, long j, ColumnScope columnScope, Composer composer, int i) {
        Unit RideTotals$lambda$9o = RideTotals$lambda$9o(list, d, j, columnScope, composer, (i & (-4)) | 1);
        ImageVector chart = WayfindrIcons.INSTANCE.getChart();
        SettingsScreenKt.OffRow offRow = new SettingsScreenKt.OffRow(2);
        SettingsScreenKt.SettingRow(chart, "Haftalık / aylık özet, rekorlar, paylaş", "Renkli sürüş izleri ve WhatsApp / Instagram için paylaşım kartı", offRow, false, offRow, composer, 0, 16);
        return RideTotals$lambda$9o;
    }

    static final Unit RideTotals$lambda$9o(List list, double d, long j, ColumnScope columnScope, Composer composer, int i) {
        Function0 function0;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C70@2620L246:RidesScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1231609629, i, -1, "com.example.wayfindr.ui.screens.RideTotals.<anonymous> (RidesScreen.kt:70)");
            }
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, Alignment.Companion.getTop(), composer, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i2 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function0 = constructor;
                composer.createNode(function0);
            } else {
                function0 = constructor;
                composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composer);
            Updater.set-impl(composer2, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i4 = ((54 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -1546668832, "C71@2724L30,72@2767L41,73@2821L35:RidesScreen.kt#cef2n4");
            Stat(String.valueOf(list.size()), "sürüş", composer, 48);
            String format = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(d)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Stat(format, "toplam km", composer, 48);
            Stat(fmtDuration(j), "süre", composer, 48);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void RidesScreen(RidesViewModel ridesViewModel, Composer composer, int i, int i2) {
        Composer composer2;
        int i3;
        Function0 function0;
        RidesViewModel ridesViewModel2;
        boolean z;
        boolean changed;
        Composer composer3;
        RidesViewModel ridesViewModel3;
        int i4;
        RidesViewModel ridesViewModel4 = ridesViewModel;
        Composer startRestartGroup = composer.startRestartGroup(1035058807);
        ComposerKt.sourceInformation(startRestartGroup, "C(RidesScreen)N(ridesViewModel)40@1663L16,45@1776L21,42@1685L697:RidesScreen.kt#cef2n4");
        int i5 = i;
        if ((i & 6) == 0) {
            if ((i2 & 1) == 0) {
                if ((i & 8) == 0 ? startRestartGroup.changed(ridesViewModel4) : startRestartGroup.changedInstance(ridesViewModel4)) {
                    i4 = 4;
                    i5 |= i4;
                }
            }
            i4 = 2;
            i5 |= i4;
        }
        if (startRestartGroup.shouldExecute((i5 & 3) != 2, i5 & 1)) {
            startRestartGroup.startDefaults();
            ComposerKt.sourceInformation(startRestartGroup, "39@1610L11");
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipToGroupEnd();
                if ((i2 & 1) != 0) {
                    i5 &= -15;
                }
                i3 = 6;
            } else if ((i2 & 1) != 0) {
                startRestartGroup.startReplaceableGroup(1729797275);
                ComposerKt.sourceInformation(startRestartGroup, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                HasDefaultViewModelProviderFactory current = LocalViewModelStoreOwner.INSTANCE.getCurrent(startRestartGroup, 6);
                if (current == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                }
                i3 = 6;
                ViewModel viewModel = ViewModelKt.viewModel(Reflection.getOrCreateKotlinClass(RidesViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, current instanceof HasDefaultViewModelProviderFactory ? current.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, startRestartGroup, ((0 << 3) & 7168) | ((0 << 3) & 112) | ((0 << 3) & 896) | ((0 << 3) & 57344), 0);
                startRestartGroup.endReplaceableGroup();
                ridesViewModel4 = (RidesViewModel) viewModel;
                i5 &= -15;
            } else {
                i3 = 6;
            }
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1035058807, i5, -1, "com.example.wayfindr.ui.screens.RidesScreen (RidesScreen.kt:39)");
            }
            State collectAsState = SnapshotStateKt.collectAsState(ridesViewModel4.getRides(), (CoroutineContext) null, startRestartGroup, 0, 1);
            Modifier modifier = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(24), 7, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            int i6 = i5;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                function0 = constructor;
                startRestartGroup.createNode(function0);
            } else {
                function0 = constructor;
                startRestartGroup.useNode();
            }
            Composer composer4 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer4, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer4, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer4, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer4, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i8 = (i7 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i9 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 96464167, "C49@1881L162:RidesScreen.kt#cef2n4");
            WayfindrComponentsKt.ScreenHeader((Modifier) null, "Sürüş geçmişi", "Telemetri", "Bağlantı kurulan her sürüş burada kaydedilir: mesafe, süre ve izlediğiniz rota.", false, (Function0) null, (Function2) null, startRestartGroup, 3504, 113);
            if (RidesScreen$lambda$0(collectAsState).isEmpty()) {
                startRestartGroup.startReplaceGroup(96641393);
                ComposerKt.sourceInformation(startRestartGroup, "53@2088L12");
                EmptyRides(startRestartGroup, 0);
                startRestartGroup.endReplaceGroup();
                ridesViewModel2 = ridesViewModel4;
                composer2 = startRestartGroup;
            } else {
                startRestartGroup.startReplaceGroup(96690001);
                ComposerKt.sourceInformation(startRestartGroup, "55@2130L17,56@2160L30,*58@2269L35,58@2243L62,59@2322L30");
                RideTotals(RidesScreen$lambda$0(collectAsState), startRestartGroup, 0);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(14)), startRestartGroup, i3);
                List RidesScreen$lambda$0 = RidesScreen$lambda$0(collectAsState);
                boolean z2 = false;
                Iterator it = RidesScreen$lambda$0.iterator();
                while (it.hasNext()) {
                    List list = RidesScreen$lambda$0;
                    Ride ride = (Ride) it.next();
                    boolean z3 = z2;
                    Iterator it2 = it;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -491660117, "CC(remember):RidesScreen.kt#9igjgp");
                    if ((((i6 & 14) ^ 6) <= 4 || !startRestartGroup.changedInstance(ridesViewModel4)) && (i6 & 6) != 4) {
                        z = false;
                        changed = z | startRestartGroup.changed(ride);
                        Object rememberedValue = startRestartGroup.rememberedValue();
                        if (changed) {
                            composer3 = startRestartGroup;
                            if (rememberedValue != Composer.Companion.getEmpty()) {
                                ridesViewModel3 = ridesViewModel4;
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                RideCard(ride, (Function0) rememberedValue, startRestartGroup, 0);
                                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                                RidesScreen$lambda$0 = list;
                                z2 = z3;
                                it = it2;
                                ridesViewModel4 = ridesViewModel3;
                                startRestartGroup = composer3;
                            }
                        } else {
                            composer3 = startRestartGroup;
                        }
                        RidesScreenKt$.ExternalSyntheticLambda10 externalSyntheticLambda10 = new RidesScreenKt$.ExternalSyntheticLambda10(ridesViewModel4, ride);
                        ridesViewModel3 = ridesViewModel4;
                        startRestartGroup.updateRememberedValue(externalSyntheticLambda10);
                        rememberedValue = externalSyntheticLambda10;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        RideCard(ride, (Function0) rememberedValue, startRestartGroup, 0);
                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                        RidesScreen$lambda$0 = list;
                        z2 = z3;
                        it = it2;
                        ridesViewModel4 = ridesViewModel3;
                        startRestartGroup = composer3;
                    }
                    z = true;
                    changed = z | startRestartGroup.changed(ride);
                    Object rememberedValue2 = startRestartGroup.rememberedValue();
                    if (changed) {
                    }
                    RidesScreenKt$.ExternalSyntheticLambda10 externalSyntheticLambda102 = new RidesScreenKt$.ExternalSyntheticLambda10(ridesViewModel4, ride);
                    ridesViewModel3 = ridesViewModel4;
                    startRestartGroup.updateRememberedValue(externalSyntheticLambda102);
                    rememberedValue2 = externalSyntheticLambda102;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    RideCard(ride, (Function0) rememberedValue2, startRestartGroup, 0);
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                    RidesScreen$lambda$0 = list;
                    z2 = z3;
                    it = it2;
                    ridesViewModel4 = ridesViewModel3;
                    startRestartGroup = composer3;
                }
                ridesViewModel2 = ridesViewModel4;
                composer2 = startRestartGroup;
                startRestartGroup.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            ridesViewModel4 = ridesViewModel2;
        } else {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RidesScreenKt$.ExternalSyntheticLambda11(ridesViewModel4, i, i2));
        }
    }

    private static final List RidesScreen$lambda$0(State state) {
        return (List) state.getValue();
    }

    static final Unit RidesScreen$lambda$4$lambda$3$lambda$2$lambda$1(RidesViewModel ridesViewModel, Ride ride) {
        ridesViewModel.deleteRide(ride);
        return Unit.INSTANCE;
    }

    static final Unit RidesScreen$lambda$5(RidesViewModel ridesViewModel, int i, int i2, Composer composer, int i3) {
        RidesScreen(ridesViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static final void Stat(String str, String str2, Composer composer, int i) {
        String str3;
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(-2084699927);
        ComposerKt.sourceInformation(startRestartGroup, "C(Stat)N(value,label)80@2941L254:RidesScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changed(str) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(str2) ? 32 : 16;
        }
        if (startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2084699927, i2, -1, "com.example.wayfindr.ui.screens.Stat (RidesScreen.kt:79)");
            }
            Alignment.Horizontal centerHorizontally = Alignment.Companion.getCenterHorizontally();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.Companion;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i3 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                startRestartGroup.createNode(constructor);
            } else {
                startRestartGroup.useNode();
            }
            Composer composer3 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i5 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -317062863, "C81@3010L99,82@3118L71:RidesScreen.kt#cef2n4");
            composer2 = startRestartGroup;
            TextKt.Text-Nvy7gAk(str, (Modifier) null, ColorKt.getGold(), (TextAutoSize) null, TextUnitKt.getSp(22), (FontStyle) null, FontWeight.Companion.getBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, (i2 & 14) | 1597440, 0, 261930);
            str3 = str2;
            TextKt.Text-Nvy7gAk(str3, (Modifier) null, ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, ((i2 >> 3) & 14) | 24576, 0, 261994);
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
            str3 = str2;
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RidesScreenKt$.ExternalSyntheticLambda4(str, str3, i));
        }
    }

    static final Unit Stat$lambda$12(String str, String str2, int i, Composer composer, int i2) {
        Stat(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void TrackSketch(List list, Modifier modifier, Composer composer, int i) {
        Composer startRestartGroup = composer.startRestartGroup(-1342807127);
        ComposerKt.sourceInformation(startRestartGroup, "C(TrackSketch)N(points,modifier)140@5969L1219,140@5952L1236:RidesScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changedInstance(list) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(modifier) ? 32 : 16;
        }
        if (startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1342807127, i2, -1, "com.example.wayfindr.ui.screens.TrackSketch (RidesScreen.kt:139)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1947454772, "CC(remember):RidesScreen.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(list);
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                RidesScreenKt$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new RidesScreenKt$.ExternalSyntheticLambda7(list);
                startRestartGroup.updateRememberedValue(externalSyntheticLambda7);
                rememberedValue = externalSyntheticLambda7;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(modifier, (Function1) rememberedValue, startRestartGroup, (i2 >> 3) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            startRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RidesScreenKt$.ExternalSyntheticLambda8(list, modifier, i));
        }
    }

    static Unit TrackSketch$lambda$32$lambda$31(List list, DrawScope drawScope) {
        try {
            NavPlus.trackDrawSz(AndroidCanvas_androidKt.getNativeCanvas(drawScope.getDrawContext().getCanvas()), list, drawScope.getSize-NH-jbRc());
        } catch (Throwable unused) {
        }
        return Unit.INSTANCE;
    }

    static final Unit TrackSketch$lambda$33(List list, Modifier modifier, int i, Composer composer, int i2) {
        TrackSketch(list, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final String fmtDate(long j) {
        return new SimpleDateFormat("d MMM yyyy, EEEE", Locale.forLanguageTag("tr-TR")).format(new Date(j));
    }

    private static final String fmtDuration(long j) {
        long j2 = j / 3600;
        return (j2 > 0 ? new StringBuilder().append(j2).append(" sa ") : new StringBuilder()).append((j % 3600) / 60).append(" dk").toString();
    }

    private static final String fmtTime(long j) {
        return new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(j));
    }
}
