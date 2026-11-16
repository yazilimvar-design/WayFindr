package com.example.wayfindr.ui.screens;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
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
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.foundation.text.selection.TextSelectionColors;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.material3.ButtonColors;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldDefaults;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.CompositionLocalMap;
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
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
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
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.MapTheme;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.OfflineRouter;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.VoiceManager;
import com.example.wayfindr.dash.nav.VoiceMode;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.BtnSize;
import com.example.wayfindr.ui.components.BtnVariant;
import com.example.wayfindr.ui.components.ChipTone;
import com.example.wayfindr.ui.components.FreeWayfindrMapKt;
import com.example.wayfindr.ui.components.RouteAlternativeStyle;
import com.example.wayfindr.ui.components.RouteAlternativeStyleKt;
import com.example.wayfindr.ui.components.RouteQrDialogKt;
import com.example.wayfindr.ui.components.WayfindrComponentsKt;
import com.example.wayfindr.ui.screens.ComposableSingletons;
import com.example.wayfindr.ui.screens.RouteScreenKt$;
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.viewmodel.RouteState;
import com.example.wayfindr.viewmodel.RouteViewModel;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;

/* compiled from: RouteScreen.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class RouteScreenKt {
    private static final void ChoiceHeader(String str, String str2, Composer composer, int i) {
        Composer composer2;
        String str3 = str2;
        Composer startRestartGroup = composer.startRestartGroup(392214881);
        ComposerKt.sourceInformation(startRestartGroup, "C(ChoiceHeader)N(label,detail)847@44888L375:RouteScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changed(str) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(str3) ? 32 : 16;
        }
        if (startRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(392214881, i2, -1, "com.example.wayfindr.ui.screens.ChoiceHeader (RouteScreen.kt:846)");
            }
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            Modifier modifier = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(9), 7, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, startRestartGroup, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i3 = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            Updater.set-impl(composer3, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i4 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i5 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -617312233, "C852@45090L14,853@45113L144:RouteScreen.kt#cef2n4");
            WayfindrComponentsKt.Eyebrow(str, (Modifier) null, startRestartGroup, i2 & 14, 2);
            composer2 = startRestartGroup;
            str3 = str2;
            TextKt.Text-Nvy7gAk(str3, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(12), 0.0f, 0.0f, 0.0f, 14, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(11.5d), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, startRestartGroup, ((i2 >> 3) & 14) | 24624, 24576, 245608);
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
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RouteScreenKt$.ExternalSyntheticLambda31(str, str3, i));
        }
    }

    static final Unit ChoiceHeader$lambda$206(String str, String str2, int i, Composer composer, int i2) {
        ChoiceHeader(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void EditLocationDialog(SavedLocation savedLocation, Function2 function2, Function0 function0, Function0 function02, Composer composer, int i) {
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(1703299186);
        ComposerKt.sourceInformation(startRestartGroup, "C(EditLocationDialog)N(loc,onSave,onDelete,onDismiss)758@40709L37,759@40763L37,762@40907L186,763@41119L92,765@41288L389,760@40832L884:RouteScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= (i & 8) == 0 ? startRestartGroup.changed(savedLocation) : startRestartGroup.changedInstance(savedLocation) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= startRestartGroup.changedInstance(function02) ? 2048 : 1024;
        }
        if (startRestartGroup.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1703299186, i2, -1, "com.example.wayfindr.ui.screens.EditLocationDialog (RouteScreen.kt:757)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 487983959, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(savedLocation.getName(), (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default);
                rememberedValue = mutableStateOf$default;
            }
            MutableState mutableState = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 487985687, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default2 = SnapshotStateKt.mutableStateOf$default(savedLocation.getNote(), (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default2);
                rememberedValue2 = mutableStateOf$default2;
            }
            MutableState mutableState2 = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function02, ComposableLambdaKt.rememberComposableLambda(1532949290, true, new RouteScreenKt$.ExternalSyntheticLambda48(function2, mutableState, mutableState2), startRestartGroup, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(209770344, true, new RouteScreenKt$.ExternalSyntheticLambda49(function0), startRestartGroup, 54), (Function2) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-1113408602$app(), ComposableLambdaKt.rememberComposableLambda(-1774998075, true, new RouteScreenKt$.ExternalSyntheticLambda50(mutableState, mutableState2), startRestartGroup, 54), (Shape) null, ColorKt.getSurf1(), 0L, 0L, 0L, 0.0f, (DialogProperties) null, composer2, ((i2 >> 9) & 14) | 1772592, 0, 16020);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RouteScreenKt$.ExternalSyntheticLambda51(savedLocation, function2, function0, function02, i));
        }
    }

    private static final String EditLocationDialog$lambda$179(MutableState mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String EditLocationDialog$lambda$182(MutableState mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    static final Unit EditLocationDialog$lambda$187(Function2 function2, MutableState mutableState, MutableState mutableState2, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C762@40986L36,762@41024L67,762@40936L155:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1532949290, i, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous> (RouteScreen.kt:762)");
            }
            boolean z = !StringsKt.isBlank(EditLocationDialog$lambda$179(mutableState));
            ComposerKt.sourceInformationMarkerStart(composer, -9512274, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed = composer.changed(function2);
            Object rememberedValue = composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                RouteScreenKt$.ExternalSyntheticLambda55 externalSyntheticLambda55 = new RouteScreenKt$.ExternalSyntheticLambda55(function2, mutableState, mutableState2);
                composer.updateRememberedValue(externalSyntheticLambda55);
                rememberedValue = externalSyntheticLambda55;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) rememberedValue, (Modifier) null, z, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(663025997, true, new RouteScreenKt$.ExternalSyntheticLambda56(mutableState), composer, 54), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$187$lambda$185$lambda$184(Function2 function2, MutableState mutableState, MutableState mutableState2) {
        function2.invoke(StringsKt.trim(EditLocationDialog$lambda$179(mutableState)).toString(), StringsKt.trim(EditLocationDialog$lambda$182(mutableState2)).toString());
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$187$lambda$186(MutableState mutableState, RowScope rowScope, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(rowScope, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C762@41026L63:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(663025997, i, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous>.<anonymous> (RouteScreen.kt:762)");
            }
            TextKt.Text-Nvy7gAk("Kaydet", (Modifier) null, !StringsKt.isBlank(EditLocationDialog$lambda$179(mutableState)) ? ColorKt.getGold() : ColorKt.getTextLo(), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$188(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C763@41148L61:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(209770344, i, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous> (RouteScreen.kt:763)");
            }
            ButtonKt.TextButton(function0, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-660152949$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$194(MutableState mutableState, MutableState mutableState2, Composer composer, int i) {
        Function0 function0;
        Object obj;
        Composer composer2;
        ComposerKt.sourceInformation(composer, "C766@41302L365:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1774998075, i, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous> (RouteScreen.kt:766)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.Companion;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i2 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Composer composer3 = Updater.constructor-impl(composer);
            Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, 563472974, "C767@41378L13,767@41354L117,768@41539L13,768@41515L138:RouteScreen.kt#cef2n4");
            String EditLocationDialog$lambda$179 = EditLocationDialog$lambda$179(mutableState);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1680745852, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                RouteScreenKt$.ExternalSyntheticLambda37 externalSyntheticLambda37 = new RouteScreenKt$.ExternalSyntheticLambda37(mutableState);
                composer.updateRememberedValue(externalSyntheticLambda37);
                obj = externalSyntheticLambda37;
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(EditLocationDialog$lambda$179, (Function1) obj, fillMaxWidth$default, false, false, (TextStyle) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$568965429$app(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1573296, 12582912, 0, 8257464);
            String EditLocationDialog$lambda$182 = EditLocationDialog$lambda$182(mutableState2);
            Modifier modifier2 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, Dp.constructor-impl(8), 0.0f, 0.0f, 13, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1680751004, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = composer.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                composer2 = composer;
                RouteScreenKt$.ExternalSyntheticLambda38 externalSyntheticLambda38 = new RouteScreenKt$.ExternalSyntheticLambda38(mutableState2);
                composer.updateRememberedValue(externalSyntheticLambda38);
                rememberedValue2 = externalSyntheticLambda38;
            } else {
                composer2 = composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            OutlinedTextFieldKt.OutlinedTextField(EditLocationDialog$lambda$182, (Function1) rememberedValue2, modifier2, false, false, (TextStyle) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$73407262$app(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1573296, 12582912, 0, 8257464);
            ComposerKt.sourceInformationMarkerEnd(composer2);
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

    static final Unit EditLocationDialog$lambda$194$lambda$193$lambda$190$lambda$189(MutableState mutableState, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$194$lambda$193$lambda$192$lambda$191(MutableState mutableState, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$195(SavedLocation savedLocation, Function2 function2, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        EditLocationDialog(savedLocation, function2, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final void RouteMapLegend(List list, int i, Function1 function1, Modifier modifier, Composer composer, int i2, int i3) {
        Modifier modifier2;
        Composer composer2;
        Modifier modifier3;
        Function0 function0;
        Function0 function02;
        Function0 function03;
        Function0 function04;
        int i4 = i;
        Function1 function12 = function1;
        Composer startRestartGroup = composer.startRestartGroup(-444490650);
        ComposerKt.sourceInformation(startRestartGroup, "C(RouteMapLegend)N(routes,selectedIndex,onSelect,modifier)793@42358L2350:RouteScreen.kt#cef2n4");
        int i5 = i2;
        if ((i2 & 6) == 0) {
            i5 |= startRestartGroup.changedInstance(list) ? 4 : 2;
        }
        if ((i2 & 48) == 0) {
            i5 |= startRestartGroup.changed(i4) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i5 |= startRestartGroup.changedInstance(function12) ? 256 : 128;
        }
        int i6 = i3 & 8;
        if (i6 != 0) {
            i5 |= 3072;
            modifier2 = modifier;
        } else if ((i2 & 3072) == 0) {
            modifier2 = modifier;
            i5 |= startRestartGroup.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i7 = i5;
        if (startRestartGroup.shouldExecute((i7 & 1171) != 1170, i7 & 1)) {
            Modifier modifier4 = i6 != 0 ? (Modifier) Modifier.Companion : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-444490650, i7, -1, "com.example.wayfindr.ui.screens.RouteMapLegend (RouteScreen.kt:790)");
            }
            if (list.size() <= 1) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new RouteScreenKt$.ExternalSyntheticLambda34(list, i4, function12, modifier4, i2, i3));
                    return;
                }
                return;
            }
            Route route = (Route) CollectionsKt.first(list);
            Modifier modifier5 = PaddingKt.padding-VpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, (Object) null), Brush.Companion.verticalGradient-8A-3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(Color.Companion.getTransparent-0d7_KjU()), Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(3893038610L))}), 0.0f, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl(12), Dp.constructor-impl(8));
            boolean z = false;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifier6 = modifier4;
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, modifier5);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i8 = ((((0 << 3) & 112) << 6) & 896) | 6;
            String str = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
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
            Composer composer3 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i9 = (i8 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i10 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1213299173, "C798@42578L210,803@42797L29,805@42904L21,804@42835L1867:RouteScreen.kt#cef2n4");
            TextKt.Text-Nvy7gAk("ANA ROTAYA GÖRE · MESAFE / SÜRE FARKI", (Modifier) null, Color.copy-wmQWz5c$default(Color.Companion.getWhite-0d7_KjU(), 0.82f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (TextAutoSize) null, TextUnitKt.getSp(9), (FontStyle) null, FontWeight.Companion.getBold(), TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, 1597830, 0, 261930);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(6)), startRestartGroup, 6);
            Modifier horizontalScroll$default = ScrollKt.horizontalScroll$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null);
            Arrangement.Horizontal horizontal = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(7));
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(horizontal, centerVertically, startRestartGroup, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, horizontalScroll$default);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i11 = ((((432 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
                function02 = constructor2;
                startRestartGroup.createNode(function02);
            } else {
                function02 = constructor2;
                startRestartGroup.useNode();
            }
            Composer composer4 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer4, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer4, Integer.valueOf(hashCode2), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer4, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer4, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i12 = (i11 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScope rowScope = RowScopeInstance.INSTANCE;
            int i13 = ((432 >> 6) & 112) | 6;
            Composer composer5 = startRestartGroup;
            ComposerKt.sourceInformationMarkerStart(composer5, -1988190157, "C:RouteScreen.kt#cef2n4");
            composer5.startReplaceGroup(-1726702687);
            ComposerKt.sourceInformation(composer5, "*816@43534L19,812@43248L1430");
            List take = CollectionsKt.take(list, 3);
            boolean z2 = false;
            int i14 = 0;
            for (Object obj : take) {
                int i15 = i14 + 1;
                if (i14 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Route route2 = (Route) obj;
                int i16 = i14;
                List list2 = take;
                boolean z3 = i16 == i4;
                boolean z4 = z2;
                Shape shape = RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10));
                Modifier modifier7 = materializeModifier2;
                boolean z5 = z;
                int i17 = hashCode;
                Modifier modifier8 = BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(Modifier.Companion, shape), androidx.compose.ui.graphics.ColorKt.Color(z3 ? 3844483890L : 3474003226L), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), z3 ? ColorKt.getGold() : Color.copy-wmQWz5c$default(Color.Companion.getWhite-0d7_KjU(), 0.18f, 0.0f, 0.0f, 0.0f, 14, (Object) null), shape);
                ComposerKt.sourceInformationMarkerStart(composer5, 1229478641, "CC(remember):RouteScreen.kt#9igjgp");
                boolean changed = ((i7 & 896) == 256) | startRestartGroup.changed(i16);
                Composer composer6 = composer5;
                Object rememberedValue = composer6.rememberedValue();
                if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                    RouteScreenKt$.ExternalSyntheticLambda35 externalSyntheticLambda35 = new RouteScreenKt$.ExternalSyntheticLambda35(function12, i16);
                    composer6.updateRememberedValue(externalSyntheticLambda35);
                    rememberedValue = externalSyntheticLambda35;
                }
                ComposerKt.sourceInformationMarkerEnd(composer5);
                Modifier modifier9 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-oSLSa3U$default(modifier8, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue, 15, (Object) null), Dp.constructor-impl(9), Dp.constructor-impl(6));
                Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
                Composer composer7 = composer5;
                ComposerKt.sourceInformationMarkerStart(composer7, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composer7, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer7, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer7, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer7.getCurrentCompositionLocalMap();
                Composer composer8 = composer5;
                Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer7, modifier9);
                Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                int i18 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer7, -553112988, str);
                if (!(composer7.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer7.startReusableNode();
                if (composer7.getInserting()) {
                    function03 = constructor3;
                    composer7.createNode(function03);
                } else {
                    function03 = constructor3;
                    composer7.useNode();
                }
                Composer composer9 = Updater.constructor-impl(composer7);
                Composer composer10 = startRestartGroup;
                Updater.set-impl(composer9, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer9, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer9, Integer.valueOf(hashCode3), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer9, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer9, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                int i19 = (i18 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer7, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                int i20 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer7, -985842583, "C820@43732L187,824@43940L28,825@43989L671:RouteScreen.kt#cef2n4");
                BoxKt.Box(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), Dp.constructor-impl(27)), RoundedCornerShapeKt.getCircleShape()), RouteAlternativeStyle.INSTANCE.composeColor-vNxB06k(i16), (Shape) null, 2, (Object) null), composer7, 0);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(7)), composer7, 6);
                ComposerKt.sourceInformationMarkerStart(composer7, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier10 = Modifier.Companion;
                MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer7, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer7, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer7, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composer7.getCurrentCompositionLocalMap();
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composer7, modifier10);
                Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
                int i21 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer7, -553112988, str);
                if (!(composer7.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer7.startReusableNode();
                if (composer7.getInserting()) {
                    function04 = constructor4;
                    composer7.createNode(function04);
                } else {
                    function04 = constructor4;
                    composer7.useNode();
                }
                Composer composer11 = Updater.constructor-impl(composer7);
                String str2 = str;
                Updater.set-impl(composer11, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer11, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer11, Integer.valueOf(hashCode4), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer11, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer11, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
                int i22 = (i21 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer7, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
                int i23 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer7, 222899479, "C826@44022L276,831@44323L315:RouteScreen.kt#cef2n4");
                TextKt.Text-Nvy7gAk(i16 == 0 ? "Ana rota" : "Alternatif " + i16, (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(11), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer7, 1597440, 24576, 245546);
                TextKt.Text-Nvy7gAk(RouteAlternativeStyleKt.routeDifferenceLabel(route2.getTotalMeters(), route2.getTotalSeconds(), route.getTotalMeters(), route.getTotalSeconds()), (Modifier) null, Color.copy-wmQWz5c$default(Color.Companion.getWhite-0d7_KjU(), 0.86f, 0.0f, 0.0f, 0.0f, 14, (Object) null), (TextAutoSize) null, TextUnitKt.getSp(9), (FontStyle) null, (FontWeight) null, TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer7, 24960, 24576, 245610);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                composer7.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer7);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                composer7.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer7);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                ComposerKt.sourceInformationMarkerEnd(composer7);
                i4 = i;
                function12 = function1;
                i14 = i15;
                take = list2;
                z2 = z4;
                hashCode = i17;
                materializeModifier2 = modifier7;
                z = z5;
                composer5 = composer8;
                startRestartGroup = composer10;
                str = str2;
            }
            Composer composer12 = composer5;
            composer2 = startRestartGroup;
            composer12.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer12);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier6;
        } else {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        ScopeUpdateScope endRestartGroup2 = composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.updateScope(new RouteScreenKt$.ExternalSyntheticLambda36(list, i, function1, modifier3, i2, i3));
        }
    }

    static final Unit RouteMapLegend$lambda$196(List list, int i, Function1 function1, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        RouteMapLegend(list, i, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit RouteMapLegend$lambda$203$lambda$202$lambda$201$lambda$198$lambda$197(Function1 function1, int i) {
        function1.invoke(Integer.valueOf(i));
        return Unit.INSTANCE;
    }

    static final Unit RouteMapLegend$lambda$204(List list, int i, Function1 function1, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        RouteMapLegend(list, i, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x06b3, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0708, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x0b94, code lost:
    
        if (r7 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L343;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x1308, code lost:
    
        if (r4 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L421;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x156d, code lost:
    
        if (r15 != androidx.compose.runtime.Composer.Companion.getEmpty()) goto L451;
     */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x2a12, code lost:
    
        if (r4 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L647;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x015c, code lost:
    
        if (r7.equals("") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0176, code lost:
    
        r18 = "Paylaşılan konum";
     */
    /* JADX WARN: Code restructure failed: missing block: B:634:0x3736, code lost:
    
        if (r6 != androidx.compose.runtime.Composer.Companion.getEmpty()) goto L826;
     */
    /* JADX WARN: Code restructure failed: missing block: B:663:0x3847, code lost:
    
        if (r7 != androidx.compose.runtime.Composer.Companion.getEmpty()) goto L859;
     */
    /* JADX WARN: Code restructure failed: missing block: B:673:0x3899, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L874;
     */
    /* JADX WARN: Code restructure failed: missing block: B:849:0x0173, code lost:
    
        if (r7.equals("Shared location") == false) goto L85;
     */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0540  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0599  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x05b1  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x05f4  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0692  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x06ab  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0700  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x073b  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x075a  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x079a  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x07ac  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0871  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0981  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x098d  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0a0a  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0a1f  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0a3c  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0a8e  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0ade  */
    /* JADX WARN: Removed duplicated region for block: B:221:0x0ae6  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0af6  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x0afe  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0b19  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0b1f  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0b23  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0b39  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x0b73  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0b8c  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0cc3  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0ccf  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0e04  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0e26  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0e6a  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0e82  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0f11  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x10b4  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x10c0  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x121c  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x1300  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x133f  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x13c6  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x161b  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x1623  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x1691  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x1741  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x174d  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x187d  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x1889  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x19e6  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x19f2  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x1a7d  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x1b30  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x1bf0  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x2393  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x23b1  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x23ce  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x247e  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x248a  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x2522  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x293a  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x296e  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x2976  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x29af A[LOOP:3: B:473:0x29a9->B:475:0x29af, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:479:0x29ee  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x2a0a  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x2b0f  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x2b1b  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x2bc3  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x3112  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x311e  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x31ae  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x31c5  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x3235  */
    /* JADX WARN: Removed duplicated region for block: B:570:0x3286  */
    /* JADX WARN: Removed duplicated region for block: B:572:0x328e  */
    /* JADX WARN: Removed duplicated region for block: B:581:0x32b2  */
    /* JADX WARN: Removed duplicated region for block: B:584:0x32e0  */
    /* JADX WARN: Removed duplicated region for block: B:587:0x32f5  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x3304 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:594:0x333c  */
    /* JADX WARN: Removed duplicated region for block: B:599:0x3378  */
    /* JADX WARN: Removed duplicated region for block: B:605:0x3410  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x36ed  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x3797  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x37f4  */
    /* JADX WARN: Removed duplicated region for block: B:648:0x38fb  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x3805  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x37a3  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x3786  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x36a7  */
    /* JADX WARN: Removed duplicated region for block: B:704:0x33f7  */
    /* JADX WARN: Removed duplicated region for block: B:706:0x334c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:708:0x32f8  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x32e7  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x32b7  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x328b  */
    /* JADX WARN: Removed duplicated region for block: B:720:0x31ca  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x31b5  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x3124  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x2b21  */
    /* JADX WARN: Removed duplicated region for block: B:724:0x2a16  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x297e  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x2981  */
    /* JADX WARN: Removed duplicated region for block: B:731:0x2951  */
    /* JADX WARN: Removed duplicated region for block: B:732:0x2490  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x23d1  */
    /* JADX WARN: Removed duplicated region for block: B:736:0x23b4  */
    /* JADX WARN: Removed duplicated region for block: B:739:0x2396  */
    /* JADX WARN: Removed duplicated region for block: B:743:0x2377  */
    /* JADX WARN: Removed duplicated region for block: B:744:0x1b92  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x1ad2  */
    /* JADX WARN: Removed duplicated region for block: B:746:0x19f8  */
    /* JADX WARN: Removed duplicated region for block: B:747:0x188f  */
    /* JADX WARN: Removed duplicated region for block: B:748:0x1753  */
    /* JADX WARN: Removed duplicated region for block: B:749:0x1694  */
    /* JADX WARN: Removed duplicated region for block: B:753:0x1620  */
    /* JADX WARN: Removed duplicated region for block: B:759:0x1606  */
    /* JADX WARN: Removed duplicated region for block: B:760:0x134f  */
    /* JADX WARN: Removed duplicated region for block: B:761:0x130c  */
    /* JADX WARN: Removed duplicated region for block: B:762:0x1227  */
    /* JADX WARN: Removed duplicated region for block: B:770:0x10c6  */
    /* JADX WARN: Removed duplicated region for block: B:775:0x0f8c  */
    /* JADX WARN: Removed duplicated region for block: B:780:0x0e29  */
    /* JADX WARN: Removed duplicated region for block: B:781:0x0e09  */
    /* JADX WARN: Removed duplicated region for block: B:785:0x0cd5  */
    /* JADX WARN: Removed duplicated region for block: B:786:0x0b98  */
    /* JADX WARN: Removed duplicated region for block: B:791:0x0b1b  */
    /* JADX WARN: Removed duplicated region for block: B:792:0x0afb  */
    /* JADX WARN: Removed duplicated region for block: B:793:0x0ae3  */
    /* JADX WARN: Removed duplicated region for block: B:795:0x0a64  */
    /* JADX WARN: Removed duplicated region for block: B:796:0x0a28  */
    /* JADX WARN: Removed duplicated region for block: B:797:0x0a13  */
    /* JADX WARN: Removed duplicated region for block: B:798:0x0991  */
    /* JADX WARN: Removed duplicated region for block: B:799:0x0877  */
    /* JADX WARN: Removed duplicated region for block: B:801:0x07ba  */
    /* JADX WARN: Removed duplicated region for block: B:802:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:804:0x0768 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:808:0x070c  */
    /* JADX WARN: Removed duplicated region for block: B:812:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:816:0x0634  */
    /* JADX WARN: Removed duplicated region for block: B:822:0x0556  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void RouteScreen(Function0 function0, Function2 function2, RouteViewModel routeViewModel, Composer composer, int i, int i2) {
        Composer composer2;
        RouteViewModel routeViewModel2;
        String str;
        String str2;
        String str3;
        String str4;
        VoiceManager voiceManager;
        String str5;
        boolean changedInstance;
        Context context;
        MutableState mutableState;
        MutableState mutableState2;
        State state;
        String str6;
        Context context2;
        boolean z;
        Object rememberedValue;
        RouteViewModel routeViewModel3;
        Object rememberedValue2;
        Object rememberedValue3;
        boolean z2;
        ManagedActivityResultLauncher managedActivityResultLauncher;
        boolean z3;
        ManagedActivityResultLauncher managedActivityResultLauncher2;
        boolean changedInstance2;
        boolean changed;
        ManagedActivityResultLauncher managedActivityResultLauncher3;
        State state2;
        Function0 function02;
        List geometry;
        List traffic;
        List routeAlternatives;
        List list;
        boolean z4;
        Function0 function03;
        boolean z5;
        Object rememberedValue4;
        Function0 function04;
        String searchNote;
        boolean changedInstance3;
        Object obj;
        Composer composer3;
        String sharedRouteName;
        MutableState mutableState3;
        Composer composer4;
        String str7;
        String str8;
        Function0 function05;
        Function0 function06;
        Function0 function07;
        String str9;
        String str10;
        String str11;
        MutableState mutableState4;
        boolean routing;
        String distanceText;
        String durationText;
        String etaText;
        Composer composer5;
        Function0 function08;
        State collectAsState;
        Object rememberedValue5;
        Context context3;
        String str12;
        Iterator it;
        boolean changed2;
        Composer composer6;
        Object obj2;
        Function0 function09;
        Function0 function010;
        boolean changedInstance4;
        Object rememberedValue6;
        boolean z6;
        boolean changedInstance5;
        boolean z7;
        MutableState mutableState5;
        MutableState mutableState6;
        RouteViewModel routeViewModel4;
        MutableState mutableState7;
        MutableState mutableState8;
        boolean z8;
        int i3;
        SavedLocation RouteScreen$lambda$16;
        SavedLocation RouteScreen$lambda$13;
        int i4;
        RouteViewModel routeViewModel5;
        Function0 function011;
        Composer composer7;
        MutableState mutableState9;
        Function0 function012;
        Function0 function013;
        Function0 function014;
        Function0 function015;
        Function0 function016;
        Function0 function017;
        Function0 function018;
        Composer composer8;
        Function0 function019;
        boolean z9;
        Alignment.Horizontal horizontal;
        Pair pair;
        String name;
        int i5;
        RouteViewModel routeViewModel6 = routeViewModel;
        Intrinsics.checkNotNullParameter(function0, "onBack");
        Intrinsics.checkNotNullParameter(function2, "onSentToDash");
        Composer startRestartGroup = composer.startRestartGroup(-1253613665);
        ComposerKt.sourceInformation(startRestartGroup, "C(RouteScreen)N(onBack,onSentToDash,routeViewModel)46@1896L16,61@2460L16,62@2504L7,63@2533L58,64@2615L64,65@2719L16,71@3019L34,72@3074L34,73@3128L75,74@3223L75,75@3322L31,76@3387L33,77@3444L87,78@3562L34,79@3626L42,80@3702L34,83@3854L291,83@3829L316,92@4202L1079,92@4151L1130,115@5367L272,129@6002L251,143@6733L15,141@6631L117,146@6897L73,146@6820L150,152@7096L41,150@7003L134,154@7164L205,154@7143L226,162@7396L144,162@7375L165,169@7546L31162:RouteScreen.kt#cef2n4");
        int i6 = i;
        if ((i & 6) == 0) {
            i6 |= startRestartGroup.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i6 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            if ((i2 & 4) == 0) {
                if ((i & 512) == 0 ? startRestartGroup.changed(routeViewModel6) : startRestartGroup.changedInstance(routeViewModel6)) {
                    i5 = 256;
                    i6 |= i5;
                }
            }
            i5 = 128;
            i6 |= i5;
        }
        if (startRestartGroup.shouldExecute((i6 & 147) != 146, i6 & 1)) {
            startRestartGroup.startDefaults();
            ComposerKt.sourceInformation(startRestartGroup, "44@1836L11");
            if ((i & 1) != 0 && !startRestartGroup.getDefaultsInvalid()) {
                startRestartGroup.skipToGroupEnd();
                if ((i2 & 4) != 0) {
                    i6 &= -897;
                }
            } else if ((i2 & 4) != 0) {
                startRestartGroup.startReplaceableGroup(1729797275);
                ComposerKt.sourceInformation(startRestartGroup, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                HasDefaultViewModelProviderFactory current = LocalViewModelStoreOwner.INSTANCE.getCurrent(startRestartGroup, 6);
                if (current == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                }
                ViewModel viewModel = ViewModelKt.viewModel(Reflection.getOrCreateKotlinClass(RouteViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, current instanceof HasDefaultViewModelProviderFactory ? current.getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, startRestartGroup, ((0 << 3) & 896) | ((0 << 3) & 112) | ((0 << 3) & 7168) | ((0 << 3) & 57344), 0);
                startRestartGroup.endReplaceableGroup();
                routeViewModel6 = (RouteViewModel) viewModel;
                i6 &= -897;
            }
            startRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1253613665, i6, -1, "com.example.wayfindr.ui.screens.RouteScreen (RouteScreen.kt:45)");
            }
            State collectAsState2 = SnapshotStateKt.collectAsState(routeViewModel6.getState(), (CoroutineContext) null, startRestartGroup, 0, 1);
            SharedLocation destination = RouteScreen$lambda$0(collectAsState2).getDestination();
            String name2 = destination != null ? destination.getName() : null;
            if (destination == null || (name = destination.getName()) == null) {
                str = null;
            } else {
                String str13 = name;
                if (StringsKt.isBlank(str13)) {
                    str13 = "";
                }
                str = str13;
            }
            if (str == null) {
                str = "";
            }
            switch (str.hashCode()) {
                case -708399888:
                    break;
                case -397906667:
                    if (str.equals("Dropped pin")) {
                        str2 = "Haritadan seçilen konum";
                        break;
                    }
                    str2 = str;
                    break;
                case 0:
                    break;
                default:
                    str2 = str;
                    break;
            }
            String str14 = str2;
            if ((destination != null ? destination.getLat() : null) == null || destination.getLng() == null) {
                str3 = (destination != null ? destination.getUrl() : null) != null ? "Harita bağlantısı" : "";
            } else {
                str3 = String.format("%.5f, %.5f", Arrays.copyOf(new Object[]{destination.getLat(), destination.getLng()}, 2));
                Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
            }
            String str15 = str3;
            State collectAsState3 = SnapshotStateKt.collectAsState(routeViewModel6.getSaved(), (CoroutineContext) null, startRestartGroup, 0, 1);
            CompositionLocal localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = startRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Context context4 = (Context) consume;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131862087, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue7 = startRestartGroup.rememberedValue();
            if (rememberedValue7 == Composer.Companion.getEmpty()) {
                DashConfig dashConfig = DashConfig.Companion.get(context4);
                startRestartGroup.updateRememberedValue(dashConfig);
                rememberedValue7 = dashConfig;
            }
            DashConfig dashConfig2 = (DashConfig) rememberedValue7;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131859457, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue8 = startRestartGroup.rememberedValue();
            if (rememberedValue8 == Composer.Companion.getEmpty()) {
                rememberedValue8 = VoiceManager.Companion.get(context4);
                startRestartGroup.updateRememberedValue(rememberedValue8);
            }
            VoiceManager voiceManager2 = (VoiceManager) rememberedValue8;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            State collectAsState4 = SnapshotStateKt.collectAsState(voiceManager2.getMode(), (CoroutineContext) null, startRestartGroup, 0, 1);
            switch (WhenMappings.$EnumSwitchMapping$0[RouteScreen$lambda$5(collectAsState4).ordinal()]) {
                case 1:
                    str4 = "Kapalı";
                    break;
                case 2:
                    str4 = "Yalnızca uyarı sesi";
                    break;
                case 3:
                    str4 = "Tam sesli yönlendirme";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            String str16 = str4;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131846559, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue9 = startRestartGroup.rememberedValue();
            if (rememberedValue9 == Composer.Companion.getEmpty()) {
                rememberedValue9 = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(rememberedValue9);
            }
            MutableState mutableState10 = (MutableState) rememberedValue9;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131844799, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue10 = startRestartGroup.rememberedValue();
            if (rememberedValue10 == Composer.Companion.getEmpty()) {
                voiceManager = voiceManager2;
                rememberedValue10 = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(rememberedValue10);
            } else {
                voiceManager = voiceManager2;
            }
            MutableState mutableState11 = (MutableState) rememberedValue10;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131843030, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue11 = startRestartGroup.rememberedValue();
            if (rememberedValue11 == Composer.Companion.getEmpty()) {
                rememberedValue11 = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(rememberedValue11);
            }
            MutableState mutableState12 = (MutableState) rememberedValue11;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131839990, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue12 = startRestartGroup.rememberedValue();
            if (rememberedValue12 == Composer.Companion.getEmpty()) {
                rememberedValue12 = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(rememberedValue12);
            }
            MutableState mutableState13 = (MutableState) rememberedValue12;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131836866, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue13 = startRestartGroup.rememberedValue();
            if (rememberedValue13 == Composer.Companion.getEmpty()) {
                rememberedValue13 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(rememberedValue13);
            }
            MutableState mutableState14 = (MutableState) rememberedValue13;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131834784, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue14 = startRestartGroup.rememberedValue();
            if (rememberedValue14 == Composer.Companion.getEmpty()) {
                rememberedValue14 = SnapshotStateKt.mutableStateOf$default(true, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(rememberedValue14);
            }
            MutableState mutableState15 = (MutableState) rememberedValue14;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131832906, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue15 = startRestartGroup.rememberedValue();
            if (rememberedValue15 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default);
                rememberedValue15 = mutableStateOf$default;
            }
            MutableState mutableState16 = (MutableState) rememberedValue15;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131829183, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue16 = startRestartGroup.rememberedValue();
            if (rememberedValue16 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default2 = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default2);
                rememberedValue16 = mutableStateOf$default2;
            }
            MutableState mutableState17 = (MutableState) rememberedValue16;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131827127, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue17 = startRestartGroup.rememberedValue();
            if (rememberedValue17 == Composer.Companion.getEmpty()) {
                rememberedValue17 = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(rememberedValue17);
            }
            MutableState mutableState18 = (MutableState) rememberedValue17;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131824703, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue18 = startRestartGroup.rememberedValue();
            if (rememberedValue18 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default3 = SnapshotStateKt.mutableStateOf$default(false, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default3);
                rememberedValue18 = mutableStateOf$default3;
            }
            MutableState mutableState19 = (MutableState) rememberedValue18;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131819582, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed3 = startRestartGroup.changed(collectAsState2) | startRestartGroup.changed(name2) | startRestartGroup.changed(str14);
            Object rememberedValue19 = startRestartGroup.rememberedValue();
            if (!changed3 && rememberedValue19 != Composer.Companion.getEmpty()) {
                str5 = str14;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                EffectsKt.LaunchedEffect(str5, (Function2) rememberedValue19, startRestartGroup, 0);
                String RouteScreen$lambda$19 = RouteScreen$lambda$19(mutableState14);
                Boolean valueOf = Boolean.valueOf(RouteScreen$lambda$34(mutableState19));
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131807658, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance = startRestartGroup.changedInstance(context4) | startRestartGroup.changed(collectAsState2);
                Object rememberedValue20 = startRestartGroup.rememberedValue();
                if (changedInstance) {
                    context = context4;
                    if (rememberedValue20 != Composer.Companion.getEmpty()) {
                        str6 = str5;
                        context2 = context;
                        state = collectAsState2;
                        mutableState = mutableState19;
                        mutableState2 = mutableState17;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        EffectsKt.LaunchedEffect(RouteScreen$lambda$19, valueOf, (Function2) rememberedValue20, startRestartGroup, 0);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131771185, "CC(remember):RouteScreen.kt#9igjgp");
                        z = (((i6 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel6)) || (i6 & 384) == 256;
                        rememberedValue = startRestartGroup.rememberedValue();
                        if (!z || rememberedValue == Composer.Companion.getEmpty()) {
                            MutableState mutableState20 = mutableState;
                            RouteViewModel routeViewModel7 = routeViewModel6;
                            RouteScreenKt$.ExternalSyntheticLambda4 externalSyntheticLambda4 = new RouteScreenKt$.ExternalSyntheticLambda4(routeViewModel7, mutableState20, mutableState16, mutableState2, mutableState18, mutableState14, mutableState15);
                            routeViewModel3 = routeViewModel7;
                            mutableState = mutableState20;
                            mutableState16 = mutableState16;
                            startRestartGroup.updateRememberedValue(externalSyntheticLambda4);
                            rememberedValue = externalSyntheticLambda4;
                        } else {
                            routeViewModel3 = routeViewModel6;
                        }
                        Function1 function1 = (Function1) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131750886, "CC(remember):RouteScreen.kt#9igjgp");
                        rememberedValue2 = startRestartGroup.rememberedValue();
                        if (rememberedValue2 == Composer.Companion.getEmpty()) {
                            List createListBuilder = CollectionsKt.createListBuilder();
                            createListBuilder.add("android.permission.ACCESS_FINE_LOCATION");
                            if (Build.VERSION.SDK_INT >= 33) {
                                createListBuilder.add("android.permission.NEARBY_WIFI_DEVICES");
                            }
                            String[] strArr = (String[]) CollectionsKt.build(createListBuilder).toArray(new String[0]);
                            startRestartGroup.updateRememberedValue(strArr);
                            rememberedValue2 = strArr;
                        }
                        String[] strArr2 = (String[]) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ActivityResultContract requestMultiplePermissions = new ActivityResultContracts.RequestMultiplePermissions();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131727730, "CC(remember):RouteScreen.kt#9igjgp");
                        rememberedValue3 = startRestartGroup.rememberedValue();
                        if (rememberedValue3 == Composer.Companion.getEmpty()) {
                            RouteScreenKt$.ExternalSyntheticLambda15 externalSyntheticLambda15 = new RouteScreenKt$.ExternalSyntheticLambda15(mutableState10);
                            startRestartGroup.updateRememberedValue(externalSyntheticLambda15);
                            rememberedValue3 = externalSyntheticLambda15;
                        }
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ManagedActivityResultLauncher rememberLauncherForActivityResult = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions, (Function1) rememberedValue3, startRestartGroup, 48);
                        ActivityResultContract pickVisualMedia = new ActivityResultContracts.PickVisualMedia();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131722424, "CC(remember):RouteScreen.kt#9igjgp");
                        z2 = (((i6 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i6 & 384) == 256;
                        Object rememberedValue21 = startRestartGroup.rememberedValue();
                        if (z2) {
                            managedActivityResultLauncher = rememberLauncherForActivityResult;
                        } else {
                            managedActivityResultLauncher = rememberLauncherForActivityResult;
                        }
                        RouteScreenKt$.ExternalSyntheticLambda20 externalSyntheticLambda20 = new RouteScreenKt$.ExternalSyntheticLambda20(routeViewModel3);
                        startRestartGroup.updateRememberedValue(externalSyntheticLambda20);
                        rememberedValue21 = externalSyntheticLambda20;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ManagedActivityResultLauncher rememberLauncherForActivityResult2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(pickVisualMedia, (Function1) rememberedValue21, startRestartGroup, 0);
                        ActivityResultContract requestPermission = new ActivityResultContracts.RequestPermission();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131716088, "CC(remember):RouteScreen.kt#9igjgp");
                        z3 = (((i6 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i6 & 384) == 256;
                        Object rememberedValue22 = startRestartGroup.rememberedValue();
                        if (z3) {
                            managedActivityResultLauncher2 = rememberLauncherForActivityResult2;
                        } else {
                            managedActivityResultLauncher2 = rememberLauncherForActivityResult2;
                        }
                        RouteScreenKt$.ExternalSyntheticLambda21 externalSyntheticLambda21 = new RouteScreenKt$.ExternalSyntheticLambda21(routeViewModel3);
                        startRestartGroup.updateRememberedValue(externalSyntheticLambda21);
                        rememberedValue22 = externalSyntheticLambda21;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ManagedActivityResultLauncher rememberLauncherForActivityResult3 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission, (Function1) rememberedValue22, startRestartGroup, 0);
                        Unit unit = Unit.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131713748, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance2 = startRestartGroup.changedInstance(context2) | ((((i6 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i6 & 384) == 256) | startRestartGroup.changedInstance(rememberLauncherForActivityResult3);
                        Object rememberedValue23 = startRestartGroup.rememberedValue();
                        if (changedInstance2 && rememberedValue23 != Composer.Companion.getEmpty()) {
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            EffectsKt.LaunchedEffect(unit, (Function2) rememberedValue23, startRestartGroup, 6);
                            Boolean valueOf2 = Boolean.valueOf(RouteScreen$lambda$7(mutableState10));
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                            changed = ((i6 & 112) == 32) | startRestartGroup.changed(state);
                            Object rememberedValue24 = startRestartGroup.rememberedValue();
                            if (changed) {
                                managedActivityResultLauncher3 = rememberLauncherForActivityResult3;
                            } else {
                                managedActivityResultLauncher3 = rememberLauncherForActivityResult3;
                                if (rememberedValue24 != Composer.Companion.getEmpty()) {
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    EffectsKt.LaunchedEffect(valueOf2, (Function2) rememberedValue24, startRestartGroup, 0);
                                    Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Arrangement.Vertical top = Arrangement.INSTANCE.getTop();
                                    Alignment.Horizontal start = Alignment.Companion.getStart();
                                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, start, startRestartGroup, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                                    state2 = state;
                                    Context context5 = context2;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                    CompositionLocalMap currentCompositionLocalMap = startRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxSize$default);
                                    Function0 constructor = ComposeUiNode.Companion.getConstructor();
                                    int i7 = ((((6 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    startRestartGroup.startReusableNode();
                                    if (startRestartGroup.getInserting()) {
                                        function02 = constructor;
                                        startRestartGroup.useNode();
                                    } else {
                                        function02 = constructor;
                                        startRestartGroup.createNode(function02);
                                    }
                                    Composer composer9 = Updater.constructor-impl(startRestartGroup);
                                    Updater.set-impl(composer9, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer9, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer9, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer9, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer9, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                                    int i8 = (i7 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    int i9 = ((6 >> 6) & 112) | 6;
                                    ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                                    Modifier modifier = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.46f), ColorKt.getMapBase(), (Shape) null, 2, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                    CompositionLocalMap currentCompositionLocalMap2 = startRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier);
                                    Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                                    int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    startRestartGroup.startReusableNode();
                                    if (startRestartGroup.getInserting()) {
                                        startRestartGroup.useNode();
                                    } else {
                                        startRestartGroup.createNode(constructor2);
                                    }
                                    Composer composer10 = Updater.constructor-impl(startRestartGroup);
                                    Updater.set-impl(composer10, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer10, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer10, Integer.valueOf(hashCode2), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer10, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer10, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                                    int i11 = (i10 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                    int i12 = ((0 >> 6) & 112) | 6;
                                    BoxScope boxScope = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                                    Pair currentLocation = RouteScreen$lambda$0(state2).getCurrentLocation();
                                    Double d = currentLocation == null ? (Double) currentLocation.getFirst() : null;
                                    Pair currentLocation2 = RouteScreen$lambda$0(state2).getCurrentLocation();
                                    Double d2 = currentLocation2 == null ? (Double) currentLocation2.getSecond() : null;
                                    float currentBearing = RouteScreen$lambda$0(state2).getCurrentBearing();
                                    long locationRefreshKey = RouteScreen$lambda$0(state2).getLocationRefreshKey();
                                    Pair pair2 = destination == null ? (destination.getLat() == null || destination.getLng() == null) ? null : TuplesKt.to(destination.getLat(), destination.getLng()) : null;
                                    List<SharedLocation> waypoints = RouteScreen$lambda$0(state2).getWaypoints();
                                    boolean z10 = false;
                                    ArrayList arrayList = new ArrayList();
                                    for (SharedLocation sharedLocation : waypoints) {
                                        if (sharedLocation.getLat() == null || sharedLocation.getLng() == null) {
                                            z9 = z10;
                                            horizontal = start;
                                            pair = null;
                                        } else {
                                            z9 = z10;
                                            horizontal = start;
                                            pair = TuplesKt.to(sharedLocation.getLat(), sharedLocation.getLng());
                                        }
                                        if (pair != null) {
                                            arrayList.add(pair);
                                        }
                                        z10 = z9;
                                        start = horizontal;
                                    }
                                    ArrayList arrayList2 = arrayList;
                                    Route route = RouteScreen$lambda$0(state2).getRoute();
                                    geometry = route == null ? route.getGeometry() : null;
                                    if (geometry == null) {
                                        geometry = CollectionsKt.emptyList();
                                    }
                                    List list2 = geometry;
                                    Route route2 = RouteScreen$lambda$0(state2).getRoute();
                                    traffic = route2 == null ? route2.getTraffic() : null;
                                    if (traffic == null) {
                                        traffic = CollectionsKt.emptyList();
                                    }
                                    List list3 = traffic;
                                    routeAlternatives = RouteScreen$lambda$0(state2).getRouteAlternatives();
                                    list = routeAlternatives;
                                    if (!(routeAlternatives.size() <= 1)) {
                                        list = null;
                                    }
                                    if (list == null) {
                                        list = CollectionsKt.emptyList();
                                    }
                                    int selectedRouteIndex = RouteScreen$lambda$0(state2).getSelectedRouteIndex();
                                    boolean z11 = RouteScreen$lambda$0(state2).getRoute() == null && !RouteScreen$lambda$0(state2).getRouting();
                                    Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                                    z4 = (((i6 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i6 & 384) == 256;
                                    Object rememberedValue25 = startRestartGroup.rememberedValue();
                                    if (z4) {
                                    }
                                    RouteScreenKt$.ExternalSyntheticLambda22 externalSyntheticLambda22 = new RouteScreenKt$.ExternalSyntheticLambda22(routeViewModel3);
                                    startRestartGroup.updateRememberedValue(externalSyntheticLambda22);
                                    rememberedValue25 = externalSyntheticLambda22;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    FreeWayfindrMapKt.WayfindrMap(d, d2, pair2, arrayList2, list2, list3, list, selectedRouteIndex, true, fillMaxSize$default2, z11, false, currentBearing, locationRefreshKey, false, true, (Double) null, (Double) null, (Float) null, (Float) null, false, (Function1) rememberedValue25, startRestartGroup, 905969664, 221184, 0, 2033664);
                                    Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                                    Modifier modifier2 = PaddingKt.padding-VpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope.align(Modifier.Companion, Alignment.Companion.getTopStart()), 0.0f, 1, (Object) null), Brush.Companion.verticalGradient-8A-3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.box-impl(Color.Companion.getTransparent-0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl(16), Dp.constructor-impl(14));
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                    CompositionLocalMap currentCompositionLocalMap3 = startRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier2);
                                    Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                                    int i13 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    int i14 = i6;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    startRestartGroup.startReusableNode();
                                    if (startRestartGroup.getInserting()) {
                                        function03 = constructor3;
                                        startRestartGroup.useNode();
                                    } else {
                                        function03 = constructor3;
                                        startRestartGroup.createNode(function03);
                                    }
                                    Composer composer11 = Updater.constructor-impl(startRestartGroup);
                                    Updater.set-impl(composer11, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer11, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer11, Integer.valueOf(hashCode3), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer11, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer11, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                                    int i15 = (i13 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScope rowScope = RowScopeInstance.INSTANCE;
                                    int i16 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                                    ManagedActivityResultLauncher managedActivityResultLauncher4 = managedActivityResultLauncher;
                                    ManagedActivityResultLauncher managedActivityResultLauncher5 = managedActivityResultLauncher2;
                                    int i17 = i14;
                                    WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), function0, BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, ((i14 << 3) & 112) | 3072, 48);
                                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                                    WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(state2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(state2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(state2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), startRestartGroup, 0, 4);
                                    ImageVector recenter = WayfindrIcons.INSTANCE.getRecenter();
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                                    z5 = (((i17 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i17 & 384) == 256;
                                    rememberedValue4 = startRestartGroup.rememberedValue();
                                    if (!z5 || rememberedValue4 == Composer.Companion.getEmpty()) {
                                        KFunction kFunction = new RouteScreen.5.1.5.1.1(routeViewModel3);
                                        startRestartGroup.updateRememberedValue(kFunction);
                                        rememberedValue4 = kFunction;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(recenter, (KFunction) rememberedValue4, BackgroundKt.background-bw27NRU(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(8), 0.0f, 0.0f, 0.0f, 14, (Object) null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, 3072, 48);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    startRestartGroup.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                                        startRestartGroup.startReplaceGroup(-744620986);
                                        ComposerKt.sourceInformation(startRestartGroup, "241@10984L27,238@10811L289");
                                        List routeAlternatives2 = RouteScreen$lambda$0(state2).getRouteAlternatives();
                                        int selectedRouteIndex2 = RouteScreen$lambda$0(state2).getSelectedRouteIndex();
                                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -162561546, "CC(remember):RouteScreen.kt#9igjgp");
                                        boolean z12 = (((i17 & 896) ^ 384) > 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i17 & 384) == 256;
                                        Object rememberedValue26 = startRestartGroup.rememberedValue();
                                        if (z12 || rememberedValue26 == Composer.Companion.getEmpty()) {
                                            KFunction kFunction2 = new RouteScreen.5.1.6.1(routeViewModel3);
                                            startRestartGroup.updateRememberedValue(kFunction2);
                                            rememberedValue26 = kFunction2;
                                        }
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        RouteMapLegend(routeAlternatives2, selectedRouteIndex2, (KFunction) rememberedValue26, boxScope.align(Modifier.Companion, Alignment.Companion.getBottomStart()), startRestartGroup, 0, 0);
                                    } else {
                                        startRestartGroup.startReplaceGroup(-755338585);
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    startRestartGroup.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    Modifier modifier3 = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-VpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.background-bw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-a9UjIt4$default(Dp.constructor-impl(22), Dp.constructor-impl(22), 0.0f, 0.0f, 12, (Object) null)), ColorKt.getBg1(), (Shape) null, 2, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18), Dp.constructor-impl(8)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(20), 7, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                                    CompositionLocalMap currentCompositionLocalMap4 = startRestartGroup.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier3);
                                    Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
                                    int i18 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    startRestartGroup.startReusableNode();
                                    if (startRestartGroup.getInserting()) {
                                        function04 = constructor4;
                                        startRestartGroup.useNode();
                                    } else {
                                        function04 = constructor4;
                                        startRestartGroup.createNode(function04);
                                    }
                                    Composer composer12 = Updater.constructor-impl(startRestartGroup);
                                    Updater.set-impl(composer12, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer12, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer12, Integer.valueOf(hashCode4), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer12, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer12, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
                                    int i19 = (i18 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    int i20 = ((0 >> 6) & 112) | 6;
                                    ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                                    BoxKt.Box(PaddingKt.padding-qDBjuR0$default(columnScope2.align(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), Dp.constructor-impl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), (Shape) null, 2, (Object) null), Alignment.Companion.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), startRestartGroup, 0);
                                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                                    RouteViewModel routeViewModel8 = routeViewModel3;
                                    WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(4), (Function0) null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new RouteScreenKt$.ExternalSyntheticLambda23(function1, routeViewModel8, mutableState14, mutableState16, mutableState, mutableState18, state2, mutableState2), startRestartGroup, 54), startRestartGroup, 24966, 10);
                                    searchNote = RouteScreen$lambda$0(state2).getSearchNote();
                                    if (searchNote != null) {
                                        startRestartGroup.startReplaceGroup(2013138439);
                                        startRestartGroup.endReplaceGroup();
                                    } else {
                                        startRestartGroup.startReplaceGroup(2013138440);
                                        ComposerKt.sourceInformation(startRestartGroup, "*364@17627L110");
                                        TextKt.Text-Nvy7gAk(StringsKt.contains(searchNote, "no match", true) ? "Eşleşme bulunamadı." : StringsKt.contains(searchNote, "no internet", true) ? "İnternet bağlantısı yok; arama yapılamadı." : "Arama hizmetine ulaşılamadı. Lütfen bağlantınızı kontrol edip tekrar deneyin.", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(6), 0.0f, 0.0f, Dp.constructor-impl(12), 6, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, startRestartGroup, 24624, 0, 262120);
                                        Unit unit2 = Unit.INSTANCE;
                                        startRestartGroup.endReplaceGroup();
                                        Unit unit3 = Unit.INSTANCE;
                                    }
                                    WayfindrIcons.INSTANCE.getQrScan();
                                    BtnVariant btnVariant = BtnVariant.Ghost;
                                    BtnSize btnSize = BtnSize.Sm;
                                    PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(12), 7, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                                    changedInstance3 = startRestartGroup.changedInstance(managedActivityResultLauncher5);
                                    Object rememberedValue27 = startRestartGroup.rememberedValue();
                                    if (changedInstance3) {
                                        obj = rememberedValue27;
                                    }
                                    RouteScreenKt$.ExternalSyntheticLambda24 externalSyntheticLambda24 = new RouteScreenKt$.ExternalSyntheticLambda24(managedActivityResultLauncher5);
                                    startRestartGroup.updateRememberedValue(externalSyntheticLambda24);
                                    obj = externalSyntheticLambda24;
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    composer3 = startRestartGroup;
                                    sharedRouteName = RouteScreen$lambda$0(state2).getSharedRouteName();
                                    if (sharedRouteName != null) {
                                        composer3.startReplaceGroup(2014314920);
                                        composer3.endReplaceGroup();
                                        mutableState3 = mutableState11;
                                    } else {
                                        composer3.startReplaceGroup(2014314921);
                                        ComposerKt.sourceInformation(composer3, "*380@18512L1154,380@18422L1244");
                                        mutableState3 = mutableState11;
                                        WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(14), (Function0) null, ComposableLambdaKt.rememberComposableLambda(-2113600503, true, new RouteScreenKt$.ExternalSyntheticLambda25(sharedRouteName, state2, mutableState3), composer3, 54), composer3, 24966, 10);
                                        Unit unit4 = Unit.INSTANCE;
                                        composer3.endReplaceGroup();
                                        Unit unit5 = Unit.INSTANCE;
                                    }
                                    if (RouteScreen$lambda$0(state2).getWaypoints().isEmpty()) {
                                        composer3.startReplaceGroup(2015763458);
                                        ComposerKt.sourceInformation(composer3, "404@19828L646,418@20580L1669,418@20491L1758");
                                        Alignment.Vertical centerVertically2 = Alignment.Companion.getCenterVertically();
                                        Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                                        Modifier modifier4 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(6), 7, (Object) null);
                                        str8 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str8);
                                        MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically2, composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                        CompositionLocalMap currentCompositionLocalMap5 = composer3.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier5 = ComposedModifierKt.materializeModifier(composer3, modifier4);
                                        Function0 constructor5 = ComposeUiNode.Companion.getConstructor();
                                        int i21 = ((((438 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            function019 = constructor5;
                                            composer3.createNode(function019);
                                        } else {
                                            function019 = constructor5;
                                            composer3.useNode();
                                        }
                                        Composer composer13 = Updater.constructor-impl(composer3);
                                        composer4 = startRestartGroup;
                                        Updater.set-impl(composer13, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                                        Updater.set-impl(composer13, currentCompositionLocalMap5, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                        Updater.init-impl(composer13, Integer.valueOf(hashCode5), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                        Updater.reconcile-impl(composer13, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                        Updater.set-impl(composer13, materializeModifier5, ComposeUiNode.Companion.getSetModifier());
                                        int i22 = (i21 >> 6) & 14;
                                        str7 = "C101@5233L9:Row.kt#2w3rfo";
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str7);
                                        RowScope rowScope2 = RowScopeInstance.INSTANCE;
                                        int i23 = ((438 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer3, -867246901, "C409@20090L56,412@20257L33,410@20167L289:RouteScreen.kt#cef2n4");
                                        WayfindrComponentsKt.Eyebrow("Rota durakları · " + RouteScreen$lambda$0(state2).getWaypoints().size(), (Modifier) null, composer3, 0, 2);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1551991349, "CC(remember):RouteScreen.kt#9igjgp");
                                        boolean z13 = (((i17 & 896) ^ 384) > 256 && startRestartGroup.changedInstance(routeViewModel8)) || (i17 & 384) == 256;
                                        Object rememberedValue28 = composer3.rememberedValue();
                                        if (z13) {
                                        }
                                        KFunction kFunction3 = new RouteScreen.5.2.5.1.1(routeViewModel8);
                                        composer3.updateRememberedValue(kFunction3);
                                        rememberedValue28 = kFunction3;
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        WayfindrComponentsKt.WayfindrBtn("Temizle", (KFunction) rememberedValue28, (Modifier) null, WayfindrIcons.INSTANCE.getX(), BtnVariant.Ghost, BtnSize.Sm, false, composer3, 221190, 68);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(6), (Function0) null, ComposableLambdaKt.rememberComposableLambda(-1714951167, true, new RouteScreenKt$.ExternalSyntheticLambda26(state2, routeViewModel8), composer3, 54), composer3, 24966, 10);
                                    } else {
                                        composer4 = startRestartGroup;
                                        str7 = "C101@5233L9:Row.kt#2w3rfo";
                                        str8 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                                        composer3.startReplaceGroup(1996034903);
                                    }
                                    composer3.endReplaceGroup();
                                    if (((destination == null ? destination.getLat() : null) != null || destination.getLng() == null) && !RouteScreen$lambda$0(state2).isResolving()) {
                                        composer3.startReplaceGroup(2018241319);
                                        ComposerKt.sourceInformation(composer3, "438@22367L1104");
                                        PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null);
                                        Dp.constructor-impl(16);
                                        ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-835303176$app();
                                    } else {
                                        composer3.startReplaceGroup(1996034903);
                                    }
                                    composer3.endReplaceGroup();
                                    WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(state2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(6), 7, (Object) null), composer3, 48, 0);
                                    Alignment.Vertical top2 = Alignment.Companion.getTop();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str8);
                                    Modifier modifier5 = Modifier.Companion;
                                    MeasurePolicy rowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top2, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap6 = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier6 = ComposedModifierKt.materializeModifier(composer3, modifier5);
                                    Function0 constructor6 = ComposeUiNode.Companion.getConstructor();
                                    int i24 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        function05 = constructor6;
                                        composer3.useNode();
                                    } else {
                                        function05 = constructor6;
                                        composer3.createNode(function05);
                                    }
                                    Composer composer14 = Updater.constructor-impl(composer3);
                                    Updater.set-impl(composer14, rowMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer14, currentCompositionLocalMap6, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer14, Integer.valueOf(hashCode6), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer14, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer14, materializeModifier6, ComposeUiNode.Companion.getSetModifier());
                                    int i25 = (i24 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str7);
                                    RowScope rowScope3 = RowScopeInstance.INSTANCE;
                                    int i26 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                                    Alignment center = Alignment.Companion.getCenter();
                                    Modifier modifier6 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(44)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap7 = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier7 = ComposedModifierKt.materializeModifier(composer3, modifier6);
                                    Function0 constructor7 = ComposeUiNode.Companion.getConstructor();
                                    int i27 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        function06 = constructor7;
                                        composer3.useNode();
                                    } else {
                                        function06 = constructor7;
                                        composer3.createNode(function06);
                                    }
                                    Composer composer15 = Updater.constructor-impl(composer3);
                                    Updater.set-impl(composer15, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer15, currentCompositionLocalMap7, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer15, Integer.valueOf(hashCode7), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer15, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer15, materializeModifier7, ComposeUiNode.Companion.getSetModifier());
                                    int i28 = (i27 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                    BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
                                    int i29 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                                    IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), ColorKt.getGold(), composer3, 432, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer3, 6);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Modifier modifier7 = Modifier.Companion;
                                    MeasurePolicy columnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap8 = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier8 = ComposedModifierKt.materializeModifier(composer3, modifier7);
                                    Function0 constructor8 = ComposeUiNode.Companion.getConstructor();
                                    int i30 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        function07 = constructor8;
                                        composer3.useNode();
                                    } else {
                                        function07 = constructor8;
                                        composer3.createNode(function07);
                                    }
                                    Composer composer16 = Updater.constructor-impl(composer3);
                                    Updater.set-impl(composer16, columnMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer16, currentCompositionLocalMap8, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer16, Integer.valueOf(hashCode8), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer16, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer16, materializeModifier8, ComposeUiNode.Companion.getSetModifier());
                                    int i31 = (i30 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScope columnScope3 = ColumnScopeInstance.INSTANCE;
                                    int i32 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1541055177, "C:RouteScreen.kt#cef2n4");
                                    if (RouteScreen$lambda$0(state2).isResolving()) {
                                        composer3.startReplaceGroup(-1540849431);
                                        ComposerKt.sourceInformation(composer3, "473@24415L132");
                                        String str17 = str6;
                                        TextKt.Text-Nvy7gAk(str17, (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(19), (FontStyle) null, FontWeight.Companion.getBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.38d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, 1597440, 0, 261674);
                                        str9 = str17;
                                        composer3.endReplaceGroup();
                                    } else {
                                        composer3.startReplaceGroup(-1541040670);
                                        ComposerKt.sourceInformation(composer3, "471@24222L139");
                                        TextKt.Text-Nvy7gAk("Çözümleniyor…", (Modifier) null, ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(19), (FontStyle) null, FontWeight.Companion.getBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.38d), (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, 1597446, 0, 261674);
                                        composer3.endReplaceGroup();
                                        str9 = str6;
                                    }
                                    if (StringsKt.isBlank(str15)) {
                                        composer3.startReplaceGroup(-1540624650);
                                        ComposerKt.sourceInformation(composer3, "476@24642L119");
                                        str10 = str9;
                                        TextKt.Text-Nvy7gAk(str15, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, Dp.constructor-impl(2), 0.0f, 0.0f, 13, (Object) null), ColorKt.getTextMid(), (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, 24624, 0, 261992);
                                        str11 = str15;
                                    } else {
                                        str10 = str9;
                                        str11 = str15;
                                        composer3.startReplaceGroup(-1565049891);
                                    }
                                    composer3.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), composer3, 6);
                                    if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                                        composer3.startReplaceGroup(2020849566);
                                        ComposerKt.sourceInformation(composer3, "484@24934L3050,528@28001L30");
                                        Arrangement.Vertical vertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
                                        ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                        Modifier modifier8 = Modifier.Companion;
                                        Alignment.Horizontal start2 = Alignment.Companion.getStart();
                                        MeasurePolicy columnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(vertical, start2, composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                        CompositionLocalMap currentCompositionLocalMap9 = composer3.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier9 = ComposedModifierKt.materializeModifier(composer3, modifier8);
                                        Function0 constructor9 = ComposeUiNode.Companion.getConstructor();
                                        int i33 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            function016 = constructor9;
                                            composer3.createNode(function016);
                                        } else {
                                            function016 = constructor9;
                                            composer3.useNode();
                                        }
                                        Composer composer17 = Updater.constructor-impl(composer3);
                                        Updater.set-impl(composer17, columnMeasurePolicy4, ComposeUiNode.Companion.getSetMeasurePolicy());
                                        Updater.set-impl(composer17, currentCompositionLocalMap9, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                        Updater.init-impl(composer17, Integer.valueOf(hashCode9), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                        Updater.reconcile-impl(composer17, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                        Updater.set-impl(composer17, materializeModifier9, ComposeUiNode.Companion.getSetModifier());
                                        int i34 = (i33 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                        ColumnScope columnScope4 = ColumnScopeInstance.INSTANCE;
                                        int i35 = ((48 >> 6) & 112) | 6;
                                        Composer composer18 = composer3;
                                        ComposerKt.sourceInformationMarkerStart(composer18, 2004497286, "C485@25013L103:RouteScreen.kt#cef2n4");
                                        WayfindrComponentsKt.Eyebrow("OSM rotaları · " + RouteScreen$lambda$0(state2).getRouteAlternatives().size() + " seçenek", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(2), 7, (Object) null), composer18, 48, 0);
                                        composer18.startReplaceGroup(-1736449372);
                                        ComposerKt.sourceInformation(composer18, "*493@25684L37,488@25302L2642");
                                        List routeAlternatives3 = RouteScreen$lambda$0(state2).getRouteAlternatives();
                                        boolean z14 = false;
                                        int i36 = 0;
                                        Iterator it2 = routeAlternatives3.iterator();
                                        while (it2.hasNext()) {
                                            Object next = it2.next();
                                            int i37 = i36 + 1;
                                            if (i36 < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            Route route3 = (Route) next;
                                            List list4 = routeAlternatives3;
                                            boolean z15 = i36 == RouteScreen$lambda$0(state2).getSelectedRouteIndex();
                                            boolean z16 = z14;
                                            Iterator it3 = it2;
                                            Alignment.Horizontal horizontal2 = start2;
                                            MutableState mutableState21 = mutableState3;
                                            Composer composer19 = composer3;
                                            Modifier modifier9 = BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), z15 ? ColorKt.getSurf3() : ColorKt.getSurf1(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), z15 ? ColorKt.getGold() : ColorKt.getLine(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)));
                                            boolean z17 = !RouteScreen$lambda$0(state2).getRouting();
                                            ComposerKt.sourceInformationMarkerStart(composer18, -1258408201, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changed4 = ((((i17 & 896) ^ 384) > 256 && startRestartGroup.changedInstance(routeViewModel8)) || (i17 & 384) == 256) | startRestartGroup.changed(i36);
                                            Composer composer20 = composer18;
                                            Object rememberedValue29 = composer20.rememberedValue();
                                            if (changed4 || rememberedValue29 == Composer.Companion.getEmpty()) {
                                                RouteScreenKt$.ExternalSyntheticLambda27 externalSyntheticLambda27 = new RouteScreenKt$.ExternalSyntheticLambda27(routeViewModel8, i36);
                                                composer20.updateRememberedValue(externalSyntheticLambda27);
                                                rememberedValue29 = externalSyntheticLambda27;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer18);
                                            Modifier modifier10 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-oSLSa3U$default(modifier9, z17, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue29, 14, (Object) null), Dp.constructor-impl(14), Dp.constructor-impl(12));
                                            Alignment.Vertical centerVertically3 = Alignment.Companion.getCenterVertically();
                                            Composer composer21 = composer18;
                                            ComposerKt.sourceInformationMarkerStart(composer21, 844473419, str8);
                                            MeasurePolicy rowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer21, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer21, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer21, 0));
                                            CompositionLocalMap currentCompositionLocalMap10 = composer21.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier10 = ComposedModifierKt.materializeModifier(composer21, modifier10);
                                            Function0 constructor10 = ComposeUiNode.Companion.getConstructor();
                                            int i38 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer21, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer21.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer21.startReusableNode();
                                            if (composer21.getInserting()) {
                                                function017 = constructor10;
                                                composer21.createNode(function017);
                                            } else {
                                                function017 = constructor10;
                                                composer21.useNode();
                                            }
                                            Composer composer22 = Updater.constructor-impl(composer21);
                                            Composer composer23 = composer18;
                                            Updater.set-impl(composer22, rowMeasurePolicy4, ComposeUiNode.Companion.getSetMeasurePolicy());
                                            Updater.set-impl(composer22, currentCompositionLocalMap10, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                            Updater.init-impl(composer22, Integer.valueOf(hashCode10), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                            Updater.reconcile-impl(composer22, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                            Updater.set-impl(composer22, materializeModifier10, ComposeUiNode.Companion.getSetModifier());
                                            int i39 = (i38 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer21, 1456264949, str7);
                                            int i40 = ((384 >> 6) & 112) | 6;
                                            RowScope rowScope4 = RowScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(composer21, 1033834161, "C497@25930L248,502@26207L29,503@26265L1387:RouteScreen.kt#cef2n4");
                                            BoxKt.Box(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), Dp.constructor-impl(34)), RoundedCornerShapeKt.getCircleShape()), RouteAlternativeStyle.INSTANCE.composeColor-vNxB06k(i36), (Shape) null, 2, (Object) null), composer21, 0);
                                            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), composer21, 6);
                                            Modifier weight$default = RowScope.weight$default(rowScope4, Modifier.Companion, 1.0f, false, 2, (Object) null);
                                            ComposerKt.sourceInformationMarkerStart(composer21, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            MeasurePolicy columnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer21, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer21, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer21, 0));
                                            CompositionLocalMap currentCompositionLocalMap11 = composer21.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier11 = ComposedModifierKt.materializeModifier(composer21, weight$default);
                                            Function0 constructor11 = ComposeUiNode.Companion.getConstructor();
                                            int i41 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer21, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer21.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer21.startReusableNode();
                                            if (composer21.getInserting()) {
                                                function018 = constructor11;
                                                composer21.createNode(function018);
                                            } else {
                                                function018 = constructor11;
                                                composer21.useNode();
                                            }
                                            Composer composer24 = Updater.constructor-impl(composer21);
                                            Updater.set-impl(composer24, columnMeasurePolicy5, ComposeUiNode.Companion.getSetMeasurePolicy());
                                            Updater.set-impl(composer24, currentCompositionLocalMap11, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                            Updater.init-impl(composer24, Integer.valueOf(hashCode11), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                            Updater.reconcile-impl(composer24, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                            Updater.set-impl(composer24, materializeModifier11, ComposeUiNode.Companion.getSetModifier());
                                            int i42 = (i41 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer21, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScope columnScope5 = ColumnScopeInstance.INSTANCE;
                                            int i43 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer21, 1158178236, "C504@26327L321,509@26681L352:RouteScreen.kt#cef2n4");
                                            TextKt.Text-Nvy7gAk(i36 == 0 ? "Ana rota" : "Alternatif " + i36, (Modifier) null, z15 ? ColorKt.getTextHi() : ColorKt.getTextMid(), (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer21, 1597440, 0, 261930);
                                            TextKt.Text-Nvy7gAk(formatAlternativeDistance(route3.getTotalMeters()) + " · " + formatAlternativeDuration(route3.getTotalSeconds()), PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, Dp.constructor-impl(3), 0.0f, 0.0f, 13, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer21, 24624, 0, 261992);
                                            Composer composer25 = composer21;
                                            if (i36 > 0) {
                                                composer25.startReplaceGroup(1158902829);
                                                ComposerKt.sourceInformation(composer25, "516@27203L385");
                                                Route route4 = (Route) CollectionsKt.first(RouteScreen$lambda$0(state2).getRouteAlternatives());
                                                TextKt.Text-Nvy7gAk("Ana rotaya göre " + RouteAlternativeStyleKt.routeDifferenceLabel(route3.getTotalMeters(), route3.getTotalSeconds(), route4.getTotalMeters(), route4.getTotalSeconds()), PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, Dp.constructor-impl(2), 0.0f, 0.0f, 13, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(10), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer25, 24624, 0, 261992);
                                                composer25 = composer25;
                                            } else {
                                                composer25.startReplaceGroup(1132020714);
                                            }
                                            composer25.endReplaceGroup();
                                            ComposerKt.sourceInformationMarkerEnd(composer25);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            composer21.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            if (z15) {
                                                composer21.startReplaceGroup(1035526078);
                                                ComposerKt.sourceInformation(composer21, "523@27695L86");
                                                IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getCheck(), "Seçili rota", SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), ColorKt.getGold(), composer21, 432, 0);
                                                composer21.endReplaceGroup();
                                                composer8 = composer21;
                                            } else {
                                                composer21.startReplaceGroup(1141786581);
                                                ComposerKt.sourceInformation(composer21, "524@27815L103");
                                                TextKt.Text-Nvy7gAk("Seç", (Modifier) null, ColorKt.getGold(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer21, 1597446, 0, 261930);
                                                composer8 = composer21;
                                                composer8.endReplaceGroup();
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer8);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            composer21.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            i36 = i37;
                                            z14 = z16;
                                            routeAlternatives3 = list4;
                                            it2 = it3;
                                            start2 = horizontal2;
                                            composer3 = composer19;
                                            mutableState3 = mutableState21;
                                            composer18 = composer23;
                                        }
                                        mutableState4 = mutableState3;
                                        Composer composer26 = composer18;
                                        composer26.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer26);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3 = composer3;
                                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(16)), composer3, 6);
                                    } else {
                                        mutableState4 = mutableState3;
                                        composer3.startReplaceGroup(1996034903);
                                    }
                                    composer3.endReplaceGroup();
                                    routing = RouteScreen$lambda$0(state2).getRouting();
                                    Triple[] tripleArr = new Triple[3];
                                    if (routing) {
                                        distanceText = RouteScreen$lambda$0(state2).getDistanceText();
                                        if (distanceText == null) {
                                            distanceText = "—";
                                        }
                                    } else {
                                        distanceText = "…";
                                    }
                                    tripleArr[0] = new Triple(distanceText, "", "Mesafe");
                                    if (routing) {
                                        durationText = RouteScreen$lambda$0(state2).getDurationText();
                                        if (durationText == null) {
                                            durationText = "—";
                                        }
                                    } else {
                                        durationText = "…";
                                    }
                                    tripleArr[1] = new Triple(durationText, "", "Süre");
                                    if (routing) {
                                        etaText = RouteScreen$lambda$0(state2).getEtaText();
                                        if (etaText == null) {
                                            etaText = "—";
                                        }
                                    } else {
                                        etaText = "…";
                                    }
                                    tripleArr[2] = new Triple(etaText, "", "Varış");
                                    List listOf = CollectionsKt.listOf(tripleArr);
                                    Arrangement.Horizontal horizontal3 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
                                    composer5 = composer3;
                                    boolean z18 = false;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str8);
                                    Modifier modifier11 = Modifier.Companion;
                                    MeasurePolicy rowMeasurePolicy5 = RowKt.rowMeasurePolicy(horizontal3, Alignment.Companion.getTop(), composer5, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                                    CompositionLocalMap currentCompositionLocalMap12 = composer5.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier12 = ComposedModifierKt.materializeModifier(composer5, modifier11);
                                    Function0 constructor12 = ComposeUiNode.Companion.getConstructor();
                                    int i44 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer5, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer5.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer5.startReusableNode();
                                    if (composer5.getInserting()) {
                                        function08 = constructor12;
                                        composer5.useNode();
                                    } else {
                                        function08 = constructor12;
                                        composer5.createNode(function08);
                                    }
                                    Composer composer27 = Updater.constructor-impl(composer5);
                                    MeasurePolicy measurePolicy = rowMeasurePolicy5;
                                    Updater.set-impl(composer27, measurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer27, currentCompositionLocalMap12, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer27, Integer.valueOf(hashCode12), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer27, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer27, materializeModifier12, ComposeUiNode.Companion.getSetModifier());
                                    int i45 = (i44 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, str7);
                                    int i46 = ((48 >> 6) & 112) | 6;
                                    RowScope rowScope5 = RowScopeInstance.INSTANCE;
                                    Composer composer28 = composer5;
                                    ComposerKt.sourceInformationMarkerStart(composer28, 213090107, "C:RouteScreen.kt#cef2n4");
                                    composer28.startReplaceGroup(1807989502);
                                    ComposerKt.sourceInformation(composer28, "*540@28620L859");
                                    List<Triple> list5 = listOf;
                                    boolean z19 = false;
                                    for (Triple triple : list5) {
                                        String str18 = (String) triple.component1();
                                        String str19 = (String) triple.component2();
                                        List list6 = list5;
                                        String str20 = (String) triple.component3();
                                        boolean z20 = z19;
                                        Composer composer29 = composer28;
                                        Modifier modifier12 = materializeModifier12;
                                        Composer composer30 = composer5;
                                        boolean z21 = z18;
                                        MeasurePolicy measurePolicy2 = measurePolicy;
                                        Modifier modifier13 = PaddingKt.padding-3ABfNKs(BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope5, Modifier.Companion, 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14))), ColorKt.getSurf1(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), ColorKt.getLine(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14))), Dp.constructor-impl(13));
                                        ComposerKt.sourceInformationMarkerStart(composer29, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                        MeasurePolicy columnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer29, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer29, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer29, 0));
                                        CompositionLocalMap currentCompositionLocalMap13 = composer29.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier13 = ComposedModifierKt.materializeModifier(composer29, modifier13);
                                        Function0 constructor13 = ComposeUiNode.Companion.getConstructor();
                                        int i47 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer29, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer29.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer29.startReusableNode();
                                        if (composer29.getInserting()) {
                                            function014 = constructor13;
                                            composer29.createNode(function014);
                                        } else {
                                            function014 = constructor13;
                                            composer29.useNode();
                                        }
                                        Composer composer31 = Updater.constructor-impl(composer29);
                                        Updater.set-impl(composer31, columnMeasurePolicy6, ComposeUiNode.Companion.getSetMeasurePolicy());
                                        Updater.set-impl(composer31, currentCompositionLocalMap13, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                        Updater.init-impl(composer31, Integer.valueOf(hashCode13), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                        Updater.reconcile-impl(composer31, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                        Updater.set-impl(composer31, materializeModifier13, ComposeUiNode.Companion.getSetModifier());
                                        int i48 = (i47 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer29, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                        ColumnScope columnScope6 = ColumnScopeInstance.INSTANCE;
                                        int i49 = ((0 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer29, -293938655, "C548@28988L404,553@29417L40:RouteScreen.kt#cef2n4");
                                        Alignment.Vertical bottom = Alignment.Companion.getBottom();
                                        ComposerKt.sourceInformationMarkerStart(composer29, 844473419, str8);
                                        Modifier modifier14 = Modifier.Companion;
                                        MeasurePolicy rowMeasurePolicy6 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), bottom, composer29, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer29, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer29, 0));
                                        CompositionLocalMap currentCompositionLocalMap14 = composer29.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier14 = ComposedModifierKt.materializeModifier(composer29, modifier14);
                                        Function0 constructor14 = ComposeUiNode.Companion.getConstructor();
                                        int i50 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer29, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer29.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer29.startReusableNode();
                                        if (composer29.getInserting()) {
                                            function015 = constructor14;
                                            composer29.createNode(function015);
                                        } else {
                                            function015 = constructor14;
                                            composer29.useNode();
                                        }
                                        Composer composer32 = Updater.constructor-impl(composer29);
                                        Updater.set-impl(composer32, rowMeasurePolicy6, ComposeUiNode.Companion.getSetMeasurePolicy());
                                        Updater.set-impl(composer32, currentCompositionLocalMap14, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                        Updater.init-impl(composer32, Integer.valueOf(hashCode14), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                        Updater.reconcile-impl(composer32, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                        Updater.set-impl(composer32, materializeModifier14, ComposeUiNode.Companion.getSetModifier());
                                        int i51 = (i50 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer29, 1456264949, str7);
                                        RowScope rowScope6 = RowScopeInstance.INSTANCE;
                                        int i52 = ((384 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer29, 665601096, "C549@29060L105,550@29194L28,551@29251L115:RouteScreen.kt#cef2n4");
                                        TextKt.Text-Nvy7gAk(str18, (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(22), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer29, 1597440, 0, 261930);
                                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(4)), composer29, 6);
                                        TextKt.Text-Nvy7gAk(str19, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(2), 7, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(11), (FontStyle) null, (FontWeight) null, TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer29, 24624, 0, 261992);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        composer29.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        WayfindrComponentsKt.Eyebrow(str20, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, Dp.constructor-impl(4), 0.0f, 0.0f, 13, (Object) null), composer29, 48, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        composer29.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        ComposerKt.sourceInformationMarkerEnd(composer29);
                                        composer28 = composer29;
                                        z18 = z21;
                                        materializeModifier12 = modifier12;
                                        composer5 = composer30;
                                        list5 = list6;
                                        z19 = z20;
                                        measurePolicy = measurePolicy2;
                                    }
                                    Composer composer33 = composer28;
                                    composer33.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(composer33);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    composer5.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), composer3, 6);
                                    collectAsState = SnapshotStateKt.collectAsState(dashConfig2.getRoutingSource(), (CoroutineContext) null, composer3, 0, 1);
                                    State collectAsState5 = SnapshotStateKt.collectAsState(dashConfig2.getMapTheme(), (CoroutineContext) null, composer3, 0, 1);
                                    ComposerKt.sourceInformationMarkerStart(composer3, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                                    Composer composer34 = composer3;
                                    rememberedValue5 = composer34.rememberedValue();
                                    if (rememberedValue5 != Composer.Companion.getEmpty()) {
                                        context3 = context5;
                                        Boolean valueOf3 = Boolean.valueOf(OfflineRouter.INSTANCE.isInstalled(context3));
                                        composer34.updateRememberedValue(valueOf3);
                                        rememberedValue5 = valueOf3;
                                    } else {
                                        context3 = context5;
                                    }
                                    boolean booleanValue = ((Boolean) rememberedValue5).booleanValue();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                                        case 1:
                                            str12 = "Motosiklet · canlı trafik";
                                            break;
                                        case 2:
                                            str12 = "Ücretsiz sunucu · internet gerekir";
                                            break;
                                        case 3:
                                            if (booleanValue) {
                                                str12 = "Telefonda · internet gerekmez";
                                                break;
                                            } else {
                                                str12 = "BRouter uygulaması yüklü değil";
                                                break;
                                            }
                                        default:
                                            throw new NoWhenBranchMatchedException();
                                    }
                                    ChoiceHeader("Rota sağlayıcısı", str12, composer3, 6);
                                    List available = RoutingSource.Companion.getAvailable();
                                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available, 10));
                                    it = available.iterator();
                                    while (it.hasNext()) {
                                        arrayList3.add(((RoutingSource) it.next()).getLabel());
                                        available = available;
                                    }
                                    ArrayList arrayList4 = arrayList3;
                                    String label = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                                    ComposerKt.sourceInformationMarkerStart(composer3, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                                    changed2 = startRestartGroup.changed(collectAsState) | startRestartGroup.changedInstance(dashConfig2) | ((((i17 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel8)) || (i17 & 384) == 256);
                                    Composer composer35 = composer3;
                                    composer6 = composer3;
                                    Object rememberedValue30 = composer35.rememberedValue();
                                    if (changed2) {
                                        obj2 = rememberedValue30;
                                    }
                                    RouteScreenKt$.ExternalSyntheticLambda5 externalSyntheticLambda5 = new RouteScreenKt$.ExternalSyntheticLambda5(dashConfig2, routeViewModel8, collectAsState);
                                    composer35.updateRememberedValue(externalSyntheticLambda5);
                                    obj2 = externalSyntheticLambda5;
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    WayfindrComponentsKt.WayfindrSegmented(arrayList4, label, (Function1) obj2, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), composer6, 3072, 0);
                                    ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer6, 54);
                                    Modifier modifier15 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null);
                                    Arrangement.Vertical vertical2 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
                                    boolean z22 = false;
                                    ComposerKt.sourceInformationMarkerStart(composer6, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    MeasurePolicy columnMeasurePolicy7 = ColumnKt.columnMeasurePolicy(vertical2, Alignment.Companion.getStart(), composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                                    CompositionLocalMap currentCompositionLocalMap15 = composer6.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier15 = ComposedModifierKt.materializeModifier(composer6, modifier15);
                                    Function0 constructor15 = ComposeUiNode.Companion.getConstructor();
                                    int i53 = ((((54 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer6.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer6.startReusableNode();
                                    if (composer6.getInserting()) {
                                        function09 = constructor15;
                                        composer6.useNode();
                                    } else {
                                        function09 = constructor15;
                                        composer6.createNode(function09);
                                    }
                                    Composer composer36 = Updater.constructor-impl(composer6);
                                    MeasurePolicy measurePolicy3 = columnMeasurePolicy7;
                                    Updater.set-impl(composer36, measurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer36, currentCompositionLocalMap15, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer36, Integer.valueOf(hashCode15), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer36, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer36, materializeModifier15, ComposeUiNode.Companion.getSetModifier());
                                    int i54 = (i53 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer6, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScope columnScope7 = ColumnScopeInstance.INSTANCE;
                                    int i55 = ((54 >> 6) & 112) | 6;
                                    Composer composer37 = composer6;
                                    ComposerKt.sourceInformationMarkerStart(composer37, -1001505061, "C:RouteScreen.kt#cef2n4");
                                    composer37.startReplaceGroup(-1140683288);
                                    ComposerKt.sourceInformation(composer37, "*585@31259L1578");
                                    List<List> chunked = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                                    boolean z23 = false;
                                    for (List list7 : chunked) {
                                        List list8 = chunked;
                                        boolean z24 = z23;
                                        Composer composer38 = composer37;
                                        Arrangement.Horizontal horizontal4 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
                                        Modifier modifier16 = materializeModifier15;
                                        Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                                        ComposerKt.sourceInformationMarkerStart(composer38, 844473419, str8);
                                        MeasurePolicy rowMeasurePolicy7 = RowKt.rowMeasurePolicy(horizontal4, Alignment.Companion.getTop(), composer38, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer38, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer38, 0));
                                        CompositionLocalMap currentCompositionLocalMap16 = composer38.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier16 = ComposedModifierKt.materializeModifier(composer38, fillMaxWidth$default);
                                        Function0 constructor16 = ComposeUiNode.Companion.getConstructor();
                                        int i56 = ((((54 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer38, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer38.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer38.startReusableNode();
                                        if (composer38.getInserting()) {
                                            function012 = constructor16;
                                            composer38.createNode(function012);
                                        } else {
                                            function012 = constructor16;
                                            composer38.useNode();
                                        }
                                        Composer composer39 = Updater.constructor-impl(composer38);
                                        MeasurePolicy measurePolicy4 = measurePolicy3;
                                        MeasurePolicy measurePolicy5 = rowMeasurePolicy7;
                                        Updater.set-impl(composer39, measurePolicy5, ComposeUiNode.Companion.getSetMeasurePolicy());
                                        Updater.set-impl(composer39, currentCompositionLocalMap16, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                        Updater.init-impl(composer39, Integer.valueOf(hashCode16), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                        Updater.reconcile-impl(composer39, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                        Updater.set-impl(composer39, materializeModifier16, ComposeUiNode.Companion.getSetModifier());
                                        int i57 = (i56 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer38, 1456264949, str7);
                                        int i58 = ((54 >> 6) & 112) | 6;
                                        RowScope rowScope7 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer38, -114499861, "C:RouteScreen.kt#cef2n4");
                                        composer38.startReplaceGroup(-3693311);
                                        ComposerKt.sourceInformation(composer38, "*593@31909L33,588@31495L1217");
                                        List<Enum> list9 = list7;
                                        boolean z25 = false;
                                        for (Enum r1 : list9) {
                                            List list10 = list9;
                                            boolean z26 = z25;
                                            boolean z27 = r1 == RouteScreen$lambda$144$lambda$143$lambda$109(collectAsState5);
                                            Alignment.Vertical centerVertically4 = Alignment.Companion.getCenterVertically();
                                            boolean z28 = z27;
                                            Modifier modifier17 = materializeModifier16;
                                            MeasurePolicy measurePolicy6 = measurePolicy5;
                                            int i59 = i17;
                                            Context context6 = context3;
                                            boolean z29 = z22;
                                            Modifier modifier18 = BorderKt.border-xT4_qwU(BackgroundKt.background-bw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope7, Modifier.Companion, 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), z28 ? ColorKt.getSurf3() : ColorKt.getSurf1(), (Shape) null, 2, (Object) null), Dp.constructor-impl(1), z28 ? ColorKt.getGold() : ColorKt.getLine(), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)));
                                            ComposerKt.sourceInformationMarkerStart(composer38, -752154161, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changedInstance6 = composer38.changedInstance(dashConfig2) | composer38.changed(r1.ordinal());
                                            Object rememberedValue31 = composer38.rememberedValue();
                                            if (changedInstance6 || rememberedValue31 == Composer.Companion.getEmpty()) {
                                                RouteScreenKt$.ExternalSyntheticLambda6 externalSyntheticLambda6 = new RouteScreenKt$.ExternalSyntheticLambda6(dashConfig2, r1);
                                                composer38.updateRememberedValue(externalSyntheticLambda6);
                                                rememberedValue31 = externalSyntheticLambda6;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer38);
                                            Modifier modifier19 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-oSLSa3U$default(modifier18, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue31, 15, (Object) null), Dp.constructor-impl(12), Dp.constructor-impl(11));
                                            ComposerKt.sourceInformationMarkerStart(composer38, 844473419, str8);
                                            MeasurePolicy rowMeasurePolicy8 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer38, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer38, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode17 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer38, 0));
                                            CompositionLocalMap currentCompositionLocalMap17 = composer38.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier17 = ComposedModifierKt.materializeModifier(composer38, modifier19);
                                            Function0 constructor17 = ComposeUiNode.Companion.getConstructor();
                                            int i60 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer38, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer38.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer38.startReusableNode();
                                            if (composer38.getInserting()) {
                                                function013 = constructor17;
                                                composer38.createNode(function013);
                                            } else {
                                                function013 = constructor17;
                                                composer38.useNode();
                                            }
                                            Composer composer40 = Updater.constructor-impl(composer38);
                                            Updater.set-impl(composer40, rowMeasurePolicy8, ComposeUiNode.Companion.getSetMeasurePolicy());
                                            Updater.set-impl(composer40, currentCompositionLocalMap17, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                            Updater.init-impl(composer40, Integer.valueOf(hashCode17), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                            Updater.reconcile-impl(composer40, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                            Updater.set-impl(composer40, materializeModifier17, ComposeUiNode.Companion.getSetModifier());
                                            int i61 = (i60 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer38, 1456264949, str7);
                                            RowScope rowScope8 = RowScopeInstance.INSTANCE;
                                            int i62 = ((384 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer38, -433335358, "C596@32091L208,600@32332L28,601@32393L289:RouteScreen.kt#cef2n4");
                                            BoxKt.Box(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(8)), RoundedCornerShapeKt.getCircleShape()), z28 ? ColorKt.getGold() : ColorKt.getTextLo(), (Shape) null, 2, (Object) null), composer38, 0);
                                            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(9)), composer38, 6);
                                            TextKt.Text-Nvy7gAk(r1.getLabel(), (Modifier) null, z28 ? ColorKt.getTextHi() : ColorKt.getTextMid(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.Companion.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composer38, 1597440, 24960, 241450);
                                            ComposerKt.sourceInformationMarkerEnd(composer38);
                                            ComposerKt.sourceInformationMarkerEnd(composer38);
                                            composer38.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer38);
                                            ComposerKt.sourceInformationMarkerEnd(composer38);
                                            ComposerKt.sourceInformationMarkerEnd(composer38);
                                            list9 = list10;
                                            z25 = z26;
                                            materializeModifier16 = modifier17;
                                            measurePolicy5 = measurePolicy6;
                                            i17 = i59;
                                            context3 = context6;
                                            z22 = z29;
                                        }
                                        int i63 = i17;
                                        Context context7 = context3;
                                        boolean z30 = z22;
                                        composer38.endReplaceGroup();
                                        if (list7.size() == 1) {
                                            composer38.startReplaceGroup(-3649803);
                                            ComposerKt.sourceInformation(composer38, "606@32788L27");
                                            SpacerKt.Spacer(RowScope.weight$default(rowScope7, Modifier.Companion, 1.0f, false, 2, (Object) null), composer38, 0);
                                        } else {
                                            composer38.startReplaceGroup(-145671416);
                                        }
                                        composer38.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer38);
                                        ComposerKt.sourceInformationMarkerEnd(composer38);
                                        composer38.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer38);
                                        ComposerKt.sourceInformationMarkerEnd(composer38);
                                        ComposerKt.sourceInformationMarkerEnd(composer38);
                                        chunked = list8;
                                        z23 = z24;
                                        composer37 = composer38;
                                        materializeModifier15 = modifier16;
                                        measurePolicy3 = measurePolicy4;
                                        i17 = i63;
                                        context3 = context7;
                                        z22 = z30;
                                    }
                                    Composer composer41 = composer37;
                                    int i64 = i17;
                                    Context context8 = context3;
                                    composer41.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(composer41);
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    composer6.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    Arrangement.Horizontal spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
                                    Alignment.Vertical centerVertically5 = Alignment.Companion.getCenterVertically();
                                    Modifier modifier20 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(9), 7, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(composer6, 844473419, str8);
                                    MeasurePolicy rowMeasurePolicy9 = RowKt.rowMeasurePolicy(spaceBetween2, centerVertically5, composer6, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode18 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                                    CompositionLocalMap currentCompositionLocalMap18 = composer6.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier18 = ComposedModifierKt.materializeModifier(composer6, modifier20);
                                    Function0 constructor18 = ComposeUiNode.Companion.getConstructor();
                                    int i65 = ((((438 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer6.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer6.startReusableNode();
                                    if (composer6.getInserting()) {
                                        function010 = constructor18;
                                        composer6.useNode();
                                    } else {
                                        function010 = constructor18;
                                        composer6.createNode(function010);
                                    }
                                    Composer composer42 = Updater.constructor-impl(composer6);
                                    Updater.set-impl(composer42, rowMeasurePolicy9, ComposeUiNode.Companion.getSetMeasurePolicy());
                                    Updater.set-impl(composer42, currentCompositionLocalMap18, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                    Updater.init-impl(composer42, Integer.valueOf(hashCode18), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                    Updater.reconcile-impl(composer42, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                    Updater.set-impl(composer42, materializeModifier18, ComposeUiNode.Companion.getSetModifier());
                                    int i66 = (i65 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str7);
                                    RowScope rowScope9 = RowScopeInstance.INSTANCE;
                                    int i67 = ((438 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer6, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                                    WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", (Modifier) null, composer6, 6, 2);
                                    IconKt.Icon-ww6aTOc(RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer6, 432, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    composer6.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    List listOf2 = CollectionsKt.listOf(new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                                    ComposerKt.sourceInformationMarkerStart(composer6, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                                    VoiceManager voiceManager3 = voiceManager;
                                    changedInstance4 = composer6.changedInstance(voiceManager3);
                                    rememberedValue6 = composer6.rememberedValue();
                                    if (!changedInstance4 || rememberedValue6 == Composer.Companion.getEmpty()) {
                                        RouteScreenKt$.ExternalSyntheticLambda7 externalSyntheticLambda7 = new RouteScreenKt$.ExternalSyntheticLambda7(voiceManager3);
                                        composer6.updateRememberedValue(externalSyntheticLambda7);
                                        rememberedValue6 = externalSyntheticLambda7;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    WayfindrComponentsKt.WayfindrSegmented(listOf2, str16, (Function1) rememberedValue6, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null), composer6, 3078, 0);
                                    z6 = ((destination == null ? destination.getLat() : null) != null || destination.getLng() == null || RouteScreen$lambda$0(state2).isResolving() || RouteScreen$lambda$0(state2).getRouting()) ? false : true;
                                    String str21 = !RouteScreen$lambda$7(mutableState10) ? "Navigasyon başlatılıyor…" : RouteScreen$lambda$0(state2).isResolving() ? "Hedef çözümleniyor…" : RouteScreen$lambda$0(state2).getRouting() ? "Rota aranıyor…" : "Navigasyonu başlat";
                                    ImageVector check = !RouteScreen$lambda$7(mutableState10) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                                    BtnVariant btnVariant2 = !RouteScreen$lambda$7(mutableState10) ? BtnVariant.Secondary : BtnVariant.Primary;
                                    BtnSize btnSize2 = BtnSize.Lg;
                                    boolean z31 = RouteScreen$lambda$7(mutableState10) && z6;
                                    Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                                    ComposerKt.sourceInformationMarkerStart(composer6, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                                    changedInstance5 = composer6.changedInstance(context8) | composer6.changedInstance(managedActivityResultLauncher4) | composer6.changedInstance(strArr2);
                                    Object rememberedValue32 = composer6.rememberedValue();
                                    if (!changedInstance5 && rememberedValue32 != Composer.Companion.getEmpty()) {
                                        z7 = z6;
                                        mutableState5 = mutableState10;
                                        ComposerKt.sourceInformationMarkerEnd(composer6);
                                        WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue32, fillMaxWidth$default2, check, btnVariant2, btnSize2, z31, composer6, 196992, 0);
                                        if (z7) {
                                            mutableState6 = mutableState4;
                                            composer6.startReplaceGroup(1996034903);
                                        } else {
                                            composer6.startReplaceGroup(2030857482);
                                            ComposerKt.sourceInformation(composer6, "655@35108L30,658@35246L19,656@35155L318");
                                            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(10)), composer6, 6);
                                            ImageVector pin = WayfindrIcons.INSTANCE.getPin();
                                            BtnVariant btnVariant3 = BtnVariant.Ghost;
                                            BtnSize btnSize3 = BtnSize.Md;
                                            Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                                            ComposerKt.sourceInformationMarkerStart(composer6, 481158142, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue33 = composer6.rememberedValue();
                                            if (rememberedValue33 == Composer.Companion.getEmpty()) {
                                                mutableState6 = mutableState4;
                                                RouteScreenKt$.ExternalSyntheticLambda9 externalSyntheticLambda9 = new RouteScreenKt$.ExternalSyntheticLambda9(mutableState6);
                                                composer6.updateRememberedValue(externalSyntheticLambda9);
                                                rememberedValue33 = externalSyntheticLambda9;
                                            } else {
                                                mutableState6 = mutableState4;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer6);
                                            WayfindrComponentsKt.WayfindrBtn("Bu hedefi kaydet", (Function0) rememberedValue33, fillMaxWidth$default3, pin, btnVariant3, btnSize3, false, composer6, 221622, 64);
                                        }
                                        composer6.endReplaceGroup();
                                        if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
                                            composer6.startReplaceGroup(2031417156);
                                            ComposerKt.sourceInformation(composer6, "668@35587L30,669@35634L568");
                                            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), composer6, 6);
                                            Alignment.Vertical centerVertically6 = Alignment.Companion.getCenterVertically();
                                            Arrangement.Horizontal spaceBetween3 = Arrangement.INSTANCE.getSpaceBetween();
                                            Modifier modifier21 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(4), 0.0f, 0.0f, Dp.constructor-impl(6), 6, (Object) null);
                                            ComposerKt.sourceInformationMarkerStart(composer6, 844473419, str8);
                                            MeasurePolicy rowMeasurePolicy10 = RowKt.rowMeasurePolicy(spaceBetween3, centerVertically6, composer6, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode19 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                                            CompositionLocalMap currentCompositionLocalMap19 = composer6.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier19 = ComposedModifierKt.materializeModifier(composer6, modifier21);
                                            Function0 constructor19 = ComposeUiNode.Companion.getConstructor();
                                            int i68 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer6.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer6.startReusableNode();
                                            if (composer6.getInserting()) {
                                                function011 = constructor19;
                                                composer6.createNode(function011);
                                            } else {
                                                function011 = constructor19;
                                                composer6.useNode();
                                            }
                                            Composer composer43 = Updater.constructor-impl(composer6);
                                            Updater.set-impl(composer43, rowMeasurePolicy10, ComposeUiNode.Companion.getSetMeasurePolicy());
                                            Updater.set-impl(composer43, currentCompositionLocalMap19, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                                            Updater.init-impl(composer43, Integer.valueOf(hashCode19), ComposeUiNode.Companion.getSetCompositeKeyHash());
                                            Updater.reconcile-impl(composer43, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                                            Updater.set-impl(composer43, materializeModifier19, ComposeUiNode.Companion.getSetModifier());
                                            int i69 = (i68 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str7);
                                            RowScope rowScope10 = RowScopeInstance.INSTANCE;
                                            int i70 = ((432 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer6, 1317978541, "C674@35910L47,676@36119L50,675@35978L206:RouteScreen.kt#cef2n4");
                                            WayfindrComponentsKt.Eyebrow("Kayıtlı hedefler · " + RouteScreen$lambda$2(collectAsState3).size(), (Modifier) null, composer6, 0, 2);
                                            String str22 = RouteScreen$lambda$22(mutableState15) ? "Gizle" : "Göster";
                                            long gold = ColorKt.getGold();
                                            long sp = TextUnitKt.getSp(12);
                                            Modifier modifier22 = Modifier.Companion;
                                            ComposerKt.sourceInformationMarkerStart(composer6, 1427995222, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue34 = composer6.rememberedValue();
                                            if (rememberedValue34 == Composer.Companion.getEmpty()) {
                                                composer7 = composer6;
                                                mutableState9 = mutableState15;
                                                RouteScreenKt$.ExternalSyntheticLambda10 externalSyntheticLambda10 = new RouteScreenKt$.ExternalSyntheticLambda10(mutableState9);
                                                composer6.updateRememberedValue(externalSyntheticLambda10);
                                                rememberedValue34 = externalSyntheticLambda10;
                                            } else {
                                                composer7 = composer6;
                                                mutableState9 = mutableState15;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer7);
                                            TextKt.Text-Nvy7gAk(str22, PaddingKt.padding-3ABfNKs(ClickableKt.clickable-oSLSa3U$default(modifier22, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue34, 15, (Object) null), Dp.constructor-impl(6)), gold, (TextAutoSize) null, sp, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer7, 24576, 0, 262120);
                                            ComposerKt.sourceInformationMarkerEnd(composer7);
                                            ComposerKt.sourceInformationMarkerEnd(composer6);
                                            composer6.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer6);
                                            ComposerKt.sourceInformationMarkerEnd(composer6);
                                            ComposerKt.sourceInformationMarkerEnd(composer6);
                                            if (RouteScreen$lambda$22(mutableState9)) {
                                                composer6.startReplaceGroup(481192555);
                                                ComposerKt.sourceInformation(composer6, "678@36311L2367,678@36246L2432");
                                                z8 = true;
                                                routeViewModel4 = routeViewModel8;
                                                mutableState8 = mutableState13;
                                                mutableState7 = mutableState12;
                                                WayfindrComponentsKt.WayfindrCard-TN_CM5M(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), false, Dp.constructor-impl(6), (Function0) null, ComposableLambdaKt.rememberComposableLambda(-1756830890, true, new RouteScreenKt$.ExternalSyntheticLambda11(collectAsState3, routeViewModel8, mutableState9, mutableState13, mutableState12), composer6, 54), composer6, 24966, 10);
                                            } else {
                                                routeViewModel4 = routeViewModel8;
                                                mutableState7 = mutableState12;
                                                mutableState8 = mutableState13;
                                                z8 = true;
                                                composer6.startReplaceGroup(1996034903);
                                            }
                                            composer6.endReplaceGroup();
                                        } else {
                                            routeViewModel4 = routeViewModel8;
                                            mutableState7 = mutableState12;
                                            mutableState8 = mutableState13;
                                            z8 = true;
                                            composer6.startReplaceGroup(1996034903);
                                        }
                                        composer6.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer6);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        startRestartGroup.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                        if (RouteScreen$lambda$10(mutableState6)) {
                                            i3 = i64;
                                            startRestartGroup.startReplaceGroup(-1665730781);
                                        } else {
                                            startRestartGroup.startReplaceGroup(-2130703672);
                                            ComposerKt.sourceInformation(startRestartGroup, "723@38827L85,724@38934L20,721@38728L233");
                                            String sharedRouteName2 = RouteScreen$lambda$0(state2).getSharedRouteName();
                                            if (sharedRouteName2 == null) {
                                                sharedRouteName2 = str10;
                                            }
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2130700652, "CC(remember):RouteScreen.kt#9igjgp");
                                            i3 = i64;
                                            boolean z32 = ((((i3 & 896) ^ 384) <= 256 || !startRestartGroup.changedInstance(routeViewModel4)) && (i3 & 384) != 256) ? false : z8;
                                            Object rememberedValue35 = startRestartGroup.rememberedValue();
                                            if (z32) {
                                            }
                                            RouteScreenKt$.ExternalSyntheticLambda12 externalSyntheticLambda12 = new RouteScreenKt$.ExternalSyntheticLambda12(routeViewModel4, mutableState6);
                                            startRestartGroup.updateRememberedValue(externalSyntheticLambda12);
                                            rememberedValue35 = externalSyntheticLambda12;
                                            Function2 function22 = (Function2) rememberedValue35;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2130697293, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue36 = startRestartGroup.rememberedValue();
                                            if (rememberedValue36 == Composer.Companion.getEmpty()) {
                                                rememberedValue36 = new RouteScreenKt$.ExternalSyntheticLambda13(mutableState6);
                                                startRestartGroup.updateRememberedValue(rememberedValue36);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            SaveLocationDialog(sharedRouteName2, function22, (Function0) rememberedValue36, startRestartGroup, 384);
                                        }
                                        startRestartGroup.endReplaceGroup();
                                        RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
                                        if (RouteScreen$lambda$16 != null) {
                                            startRestartGroup.startReplaceGroup(-1627064575);
                                            startRestartGroup.endReplaceGroup();
                                        } else {
                                            startRestartGroup.startReplaceGroup(-1627064574);
                                            ComposerKt.sourceInformation(startRestartGroup, "*726@39019L18,726@38988L50");
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 689785320, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue37 = startRestartGroup.rememberedValue();
                                            if (rememberedValue37 == Composer.Companion.getEmpty()) {
                                                rememberedValue37 = new RouteScreenKt$.ExternalSyntheticLambda14(mutableState8);
                                                startRestartGroup.updateRememberedValue(rememberedValue37);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            RouteQrDialogKt.RouteQrDialog(RouteScreen$lambda$16, (Function0) rememberedValue37, startRestartGroup, SavedLocation.$stable | 48);
                                            Unit unit6 = Unit.INSTANCE;
                                            startRestartGroup.endReplaceGroup();
                                            Unit unit7 = Unit.INSTANCE;
                                        }
                                        RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
                                        if (RouteScreen$lambda$13 != null) {
                                            startRestartGroup.startReplaceGroup(-1626978953);
                                            startRestartGroup.endReplaceGroup();
                                            routeViewModel5 = routeViewModel4;
                                            composer2 = startRestartGroup;
                                        } else {
                                            startRestartGroup.startReplaceGroup(-1626978952);
                                            ComposerKt.sourceInformation(startRestartGroup, "*730@39139L77,731@39241L51,732@39318L18,728@39075L272");
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -496465606, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changedInstance7 = startRestartGroup.changedInstance(RouteScreen$lambda$13) | ((((i3 & 896) ^ 384) > 256 && startRestartGroup.changedInstance(routeViewModel4)) || (i3 & 384) == 256);
                                            Object rememberedValue38 = startRestartGroup.rememberedValue();
                                            if (changedInstance7) {
                                            }
                                            RouteScreenKt$.ExternalSyntheticLambda16 externalSyntheticLambda16 = new RouteScreenKt$.ExternalSyntheticLambda16(routeViewModel4, RouteScreen$lambda$13, mutableState7);
                                            startRestartGroup.updateRememberedValue(externalSyntheticLambda16);
                                            rememberedValue38 = externalSyntheticLambda16;
                                            Function2 function23 = (Function2) rememberedValue38;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -496462368, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changedInstance8 = ((((i3 & 896) ^ 384) > 256 && startRestartGroup.changedInstance(routeViewModel4)) || (i3 & 384) == 256) | startRestartGroup.changedInstance(RouteScreen$lambda$13);
                                            Object rememberedValue39 = startRestartGroup.rememberedValue();
                                            if (changedInstance8) {
                                                i4 = i3;
                                            } else {
                                                i4 = i3;
                                            }
                                            RouteScreenKt$.ExternalSyntheticLambda17 externalSyntheticLambda17 = new RouteScreenKt$.ExternalSyntheticLambda17(routeViewModel4, RouteScreen$lambda$13, mutableState7);
                                            startRestartGroup.updateRememberedValue(externalSyntheticLambda17);
                                            rememberedValue39 = externalSyntheticLambda17;
                                            Function0 function020 = (Function0) rememberedValue39;
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -496459937, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue40 = startRestartGroup.rememberedValue();
                                            routeViewModel5 = routeViewModel4;
                                            if (rememberedValue40 == Composer.Companion.getEmpty()) {
                                                RouteScreenKt$.ExternalSyntheticLambda18 externalSyntheticLambda18 = new RouteScreenKt$.ExternalSyntheticLambda18(mutableState7);
                                                startRestartGroup.updateRememberedValue(externalSyntheticLambda18);
                                                rememberedValue40 = externalSyntheticLambda18;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                            EditLocationDialog(RouteScreen$lambda$13, function23, function020, (Function0) rememberedValue40, startRestartGroup, SavedLocation.$stable | 3072);
                                            composer2 = startRestartGroup;
                                            Unit unit8 = Unit.INSTANCE;
                                            composer2.endReplaceGroup();
                                            Unit unit9 = Unit.INSTANCE;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        routeViewModel2 = routeViewModel5;
                                    }
                                    z7 = z6;
                                    mutableState5 = mutableState10;
                                    RouteScreenKt$.ExternalSyntheticLambda8 externalSyntheticLambda8 = new RouteScreenKt$.ExternalSyntheticLambda8(managedActivityResultLauncher4, strArr2, context8, mutableState5);
                                    composer6.updateRememberedValue(externalSyntheticLambda8);
                                    rememberedValue32 = externalSyntheticLambda8;
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue32, fillMaxWidth$default2, check, btnVariant2, btnSize2, z31, composer6, 196992, 0);
                                    if (z7) {
                                    }
                                    composer6.endReplaceGroup();
                                    if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
                                    }
                                    composer6.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(composer6);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    startRestartGroup.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    startRestartGroup.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                    if (RouteScreen$lambda$10(mutableState6)) {
                                    }
                                    startRestartGroup.endReplaceGroup();
                                    RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
                                    if (RouteScreen$lambda$16 != null) {
                                    }
                                    RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
                                    if (RouteScreen$lambda$13 != null) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    routeViewModel2 = routeViewModel5;
                                }
                            }
                            Function2 function24 = new RouteScreen.4.1(function2, mutableState10, state, (Continuation) null);
                            startRestartGroup.updateRememberedValue(function24);
                            rememberedValue24 = function24;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            EffectsKt.LaunchedEffect(valueOf2, (Function2) rememberedValue24, startRestartGroup, 0);
                            Modifier fillMaxSize$default3 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Arrangement.Vertical top3 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal start3 = Alignment.Companion.getStart();
                            MeasurePolicy columnMeasurePolicy8 = ColumnKt.columnMeasurePolicy(top3, start3, startRestartGroup, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                            state2 = state;
                            Context context52 = context2;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode20 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                            CompositionLocalMap currentCompositionLocalMap20 = startRestartGroup.getCurrentCompositionLocalMap();
                            Modifier materializeModifier20 = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxSize$default3);
                            Function0 constructor20 = ComposeUiNode.Companion.getConstructor();
                            int i72 = ((((6 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                            }
                            startRestartGroup.startReusableNode();
                            if (startRestartGroup.getInserting()) {
                            }
                            Composer composer92 = Updater.constructor-impl(startRestartGroup);
                            Updater.set-impl(composer92, columnMeasurePolicy8, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer92, currentCompositionLocalMap20, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer92, Integer.valueOf(hashCode20), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer92, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer92, materializeModifier20, ComposeUiNode.Companion.getSetModifier());
                            int i82 = (i72 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            int i92 = ((6 >> 6) & 112) | 6;
                            ColumnScope columnScope8 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                            Modifier modifier23 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.46f), ColorKt.getMapBase(), (Shape) null, 2, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                            CompositionLocalMap currentCompositionLocalMap22 = startRestartGroup.getCurrentCompositionLocalMap();
                            Modifier materializeModifier22 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier23);
                            Function0 constructor22 = ComposeUiNode.Companion.getConstructor();
                            int i102 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                            }
                            startRestartGroup.startReusableNode();
                            if (startRestartGroup.getInserting()) {
                            }
                            Composer composer102 = Updater.constructor-impl(startRestartGroup);
                            Updater.set-impl(composer102, maybeCachedBoxMeasurePolicy3, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer102, currentCompositionLocalMap22, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer102, Integer.valueOf(hashCode22), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer102, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer102, materializeModifier22, ComposeUiNode.Companion.getSetModifier());
                            int i112 = (i102 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            int i122 = ((0 >> 6) & 112) | 6;
                            BoxScope boxScope3 = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                            Pair currentLocation3 = RouteScreen$lambda$0(state2).getCurrentLocation();
                            if (currentLocation3 == null) {
                            }
                            Pair currentLocation22 = RouteScreen$lambda$0(state2).getCurrentLocation();
                            if (currentLocation22 == null) {
                            }
                            float currentBearing2 = RouteScreen$lambda$0(state2).getCurrentBearing();
                            long locationRefreshKey2 = RouteScreen$lambda$0(state2).getLocationRefreshKey();
                            if (destination == null) {
                            }
                            List<SharedLocation> waypoints2 = RouteScreen$lambda$0(state2).getWaypoints();
                            boolean z102 = false;
                            ArrayList arrayList5 = new ArrayList();
                            while (r101.hasNext()) {
                            }
                            ArrayList arrayList22 = arrayList5;
                            Route route5 = RouteScreen$lambda$0(state2).getRoute();
                            if (route5 == null) {
                            }
                            if (geometry == null) {
                            }
                            List list22 = geometry;
                            Route route22 = RouteScreen$lambda$0(state2).getRoute();
                            if (route22 == null) {
                            }
                            if (traffic == null) {
                            }
                            List list32 = traffic;
                            routeAlternatives = RouteScreen$lambda$0(state2).getRouteAlternatives();
                            list = routeAlternatives;
                            if (!(routeAlternatives.size() <= 1)) {
                            }
                            if (list == null) {
                            }
                            int selectedRouteIndex3 = RouteScreen$lambda$0(state2).getSelectedRouteIndex();
                            if (RouteScreen$lambda$0(state2).getRoute() == null) {
                            }
                            Modifier fillMaxSize$default22 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                            if (((i6 & 896) ^ 384) <= 256) {
                            }
                            Object rememberedValue252 = startRestartGroup.rememberedValue();
                            if (z4) {
                            }
                            RouteScreenKt$.ExternalSyntheticLambda22 externalSyntheticLambda222 = new RouteScreenKt$.ExternalSyntheticLambda22(routeViewModel3);
                            startRestartGroup.updateRememberedValue(externalSyntheticLambda222);
                            rememberedValue252 = externalSyntheticLambda222;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            FreeWayfindrMapKt.WayfindrMap(d, d2, pair2, arrayList22, list22, list32, list, selectedRouteIndex3, true, fillMaxSize$default22, z11, false, currentBearing2, locationRefreshKey2, false, true, (Double) null, (Double) null, (Float) null, (Float) null, false, (Function1) rememberedValue252, startRestartGroup, 905969664, 221184, 0, 2033664);
                            Alignment.Vertical centerVertically7 = Alignment.Companion.getCenterVertically();
                            Modifier modifier24 = PaddingKt.padding-VpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope3.align(Modifier.Companion, Alignment.Companion.getTopStart()), 0.0f, 1, (Object) null), Brush.Companion.verticalGradient-8A-3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.box-impl(Color.Companion.getTransparent-0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl(16), Dp.constructor-impl(14));
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy11 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically7, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode32 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                            CompositionLocalMap currentCompositionLocalMap32 = startRestartGroup.getCurrentCompositionLocalMap();
                            Modifier materializeModifier32 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier24);
                            Function0 constructor32 = ComposeUiNode.Companion.getConstructor();
                            int i132 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            int i142 = i6;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                            }
                            startRestartGroup.startReusableNode();
                            if (startRestartGroup.getInserting()) {
                            }
                            Composer composer112 = Updater.constructor-impl(startRestartGroup);
                            Updater.set-impl(composer112, rowMeasurePolicy11, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer112, currentCompositionLocalMap32, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer112, Integer.valueOf(hashCode32), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer112, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer112, materializeModifier32, ComposeUiNode.Companion.getSetModifier());
                            int i152 = (i132 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScope rowScope11 = RowScopeInstance.INSTANCE;
                            int i162 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                            ManagedActivityResultLauncher managedActivityResultLauncher42 = managedActivityResultLauncher;
                            ManagedActivityResultLauncher managedActivityResultLauncher52 = managedActivityResultLauncher2;
                            int i172 = i142;
                            WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), function0, BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, ((i142 << 3) & 112) | 3072, 48);
                            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                            WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(state2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(state2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(state2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), startRestartGroup, 0, 4);
                            ImageVector recenter2 = WayfindrIcons.INSTANCE.getRecenter();
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                            if (((i172 & 896) ^ 384) <= 256) {
                            }
                            rememberedValue4 = startRestartGroup.rememberedValue();
                            if (!z5) {
                            }
                            KFunction kFunction4 = new RouteScreen.5.1.5.1.1(routeViewModel3);
                            startRestartGroup.updateRememberedValue(kFunction4);
                            rememberedValue4 = kFunction4;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(recenter2, (KFunction) rememberedValue4, BackgroundKt.background-bw27NRU(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(8), 0.0f, 0.0f, 0.0f, 14, (Object) null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, 3072, 48);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            startRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                            }
                            startRestartGroup.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            startRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            Modifier modifier32 = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-VpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.background-bw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope8, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-a9UjIt4$default(Dp.constructor-impl(22), Dp.constructor-impl(22), 0.0f, 0.0f, 12, (Object) null)), ColorKt.getBg1(), (Shape) null, 2, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18), Dp.constructor-impl(8)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(20), 7, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy22 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode42 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                            CompositionLocalMap currentCompositionLocalMap42 = startRestartGroup.getCurrentCompositionLocalMap();
                            Modifier materializeModifier42 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier32);
                            Function0 constructor42 = ComposeUiNode.Companion.getConstructor();
                            int i182 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(startRestartGroup.getApplier() instanceof Applier)) {
                            }
                            startRestartGroup.startReusableNode();
                            if (startRestartGroup.getInserting()) {
                            }
                            Composer composer122 = Updater.constructor-impl(startRestartGroup);
                            Updater.set-impl(composer122, columnMeasurePolicy22, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer122, currentCompositionLocalMap42, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer122, Integer.valueOf(hashCode42), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer122, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer122, materializeModifier42, ComposeUiNode.Companion.getSetModifier());
                            int i192 = (i182 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            int i202 = ((0 >> 6) & 112) | 6;
                            ColumnScope columnScope22 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                            BoxKt.Box(PaddingKt.padding-qDBjuR0$default(columnScope22.align(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), Dp.constructor-impl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), (Shape) null, 2, (Object) null), Alignment.Companion.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), startRestartGroup, 0);
                            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                            RouteViewModel routeViewModel82 = routeViewModel3;
                            WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(4), (Function0) null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new RouteScreenKt$.ExternalSyntheticLambda23(function1, routeViewModel82, mutableState14, mutableState16, mutableState, mutableState18, state2, mutableState2), startRestartGroup, 54), startRestartGroup, 24966, 10);
                            searchNote = RouteScreen$lambda$0(state2).getSearchNote();
                            if (searchNote != null) {
                            }
                            WayfindrIcons.INSTANCE.getQrScan();
                            BtnVariant btnVariant4 = BtnVariant.Ghost;
                            BtnSize btnSize4 = BtnSize.Sm;
                            PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(12), 7, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                            changedInstance3 = startRestartGroup.changedInstance(managedActivityResultLauncher52);
                            Object rememberedValue272 = startRestartGroup.rememberedValue();
                            if (changedInstance3) {
                            }
                            RouteScreenKt$.ExternalSyntheticLambda24 externalSyntheticLambda242 = new RouteScreenKt$.ExternalSyntheticLambda24(managedActivityResultLauncher52);
                            startRestartGroup.updateRememberedValue(externalSyntheticLambda242);
                            obj = externalSyntheticLambda242;
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            composer3 = startRestartGroup;
                            sharedRouteName = RouteScreen$lambda$0(state2).getSharedRouteName();
                            if (sharedRouteName != null) {
                            }
                            if (RouteScreen$lambda$0(state2).getWaypoints().isEmpty()) {
                            }
                            composer3.endReplaceGroup();
                            if ((destination == null ? destination.getLat() : null) != null) {
                            }
                            composer3.startReplaceGroup(2018241319);
                            ComposerKt.sourceInformation(composer3, "438@22367L1104");
                            PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null);
                            Dp.constructor-impl(16);
                            ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-835303176$app();
                            composer3.endReplaceGroup();
                            WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(state2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(6), 7, (Object) null), composer3, 48, 0);
                            Alignment.Vertical top22 = Alignment.Companion.getTop();
                            ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str8);
                            Modifier modifier52 = Modifier.Companion;
                            MeasurePolicy rowMeasurePolicy32 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top22, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode62 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap62 = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier62 = ComposedModifierKt.materializeModifier(composer3, modifier52);
                            Function0 constructor62 = ComposeUiNode.Companion.getConstructor();
                            int i242 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer3.getApplier() instanceof Applier)) {
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                            }
                            Composer composer142 = Updater.constructor-impl(composer3);
                            Updater.set-impl(composer142, rowMeasurePolicy32, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer142, currentCompositionLocalMap62, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer142, Integer.valueOf(hashCode62), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer142, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer142, materializeModifier62, ComposeUiNode.Companion.getSetModifier());
                            int i252 = (i242 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str7);
                            RowScope rowScope32 = RowScopeInstance.INSTANCE;
                            int i262 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer3, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                            Alignment center2 = Alignment.Companion.getCenter();
                            Modifier modifier62 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(44)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy22 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode72 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap72 = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier72 = ComposedModifierKt.materializeModifier(composer3, modifier62);
                            Function0 constructor72 = ComposeUiNode.Companion.getConstructor();
                            int i272 = ((((48 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer3.getApplier() instanceof Applier)) {
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                            }
                            Composer composer152 = Updater.constructor-impl(composer3);
                            Updater.set-impl(composer152, maybeCachedBoxMeasurePolicy22, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer152, currentCompositionLocalMap72, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer152, Integer.valueOf(hashCode72), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer152, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer152, materializeModifier72, ComposeUiNode.Companion.getSetModifier());
                            int i282 = (i272 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            BoxScope boxScope22 = BoxScopeInstance.INSTANCE;
                            int i292 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer3, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                            IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), ColorKt.getGold(), composer3, 432, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer3, 6);
                            ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Modifier modifier72 = Modifier.Companion;
                            MeasurePolicy columnMeasurePolicy32 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode82 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap82 = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier82 = ComposedModifierKt.materializeModifier(composer3, modifier72);
                            Function0 constructor82 = ComposeUiNode.Companion.getConstructor();
                            int i302 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer3.getApplier() instanceof Applier)) {
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                            }
                            Composer composer162 = Updater.constructor-impl(composer3);
                            Updater.set-impl(composer162, columnMeasurePolicy32, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer162, currentCompositionLocalMap82, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer162, Integer.valueOf(hashCode82), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer162, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer162, materializeModifier82, ComposeUiNode.Companion.getSetModifier());
                            int i312 = (i302 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScope columnScope32 = ColumnScopeInstance.INSTANCE;
                            int i322 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer3, -1541055177, "C:RouteScreen.kt#cef2n4");
                            if (RouteScreen$lambda$0(state2).isResolving()) {
                            }
                            if (StringsKt.isBlank(str15)) {
                            }
                            composer3.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), composer3, 6);
                            if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                            }
                            composer3.endReplaceGroup();
                            routing = RouteScreen$lambda$0(state2).getRouting();
                            Triple[] tripleArr2 = new Triple[3];
                            if (routing) {
                            }
                            tripleArr2[0] = new Triple(distanceText, "", "Mesafe");
                            if (routing) {
                            }
                            tripleArr2[1] = new Triple(durationText, "", "Süre");
                            if (routing) {
                            }
                            tripleArr2[2] = new Triple(etaText, "", "Varış");
                            List listOf3 = CollectionsKt.listOf(tripleArr2);
                            Arrangement.Horizontal horizontal32 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
                            composer5 = composer3;
                            boolean z182 = false;
                            ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str8);
                            Modifier modifier112 = Modifier.Companion;
                            MeasurePolicy rowMeasurePolicy52 = RowKt.rowMeasurePolicy(horizontal32, Alignment.Companion.getTop(), composer5, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode122 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                            CompositionLocalMap currentCompositionLocalMap122 = composer5.getCurrentCompositionLocalMap();
                            Modifier materializeModifier122 = ComposedModifierKt.materializeModifier(composer5, modifier112);
                            Function0 constructor122 = ComposeUiNode.Companion.getConstructor();
                            int i442 = ((((48 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer5, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer5.getApplier() instanceof Applier)) {
                            }
                            composer5.startReusableNode();
                            if (composer5.getInserting()) {
                            }
                            Composer composer272 = Updater.constructor-impl(composer5);
                            MeasurePolicy measurePolicy7 = rowMeasurePolicy52;
                            Updater.set-impl(composer272, measurePolicy7, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer272, currentCompositionLocalMap122, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer272, Integer.valueOf(hashCode122), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer272, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer272, materializeModifier122, ComposeUiNode.Companion.getSetModifier());
                            int i452 = (i442 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, str7);
                            int i462 = ((48 >> 6) & 112) | 6;
                            RowScope rowScope52 = RowScopeInstance.INSTANCE;
                            Composer composer282 = composer5;
                            ComposerKt.sourceInformationMarkerStart(composer282, 213090107, "C:RouteScreen.kt#cef2n4");
                            composer282.startReplaceGroup(1807989502);
                            ComposerKt.sourceInformation(composer282, "*540@28620L859");
                            List<Triple> list52 = listOf3;
                            boolean z192 = false;
                            while (r39.hasNext()) {
                            }
                            Composer composer332 = composer282;
                            composer332.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(composer332);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            ComposerKt.sourceInformationMarkerEnd(composer5);
                            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), composer3, 6);
                            collectAsState = SnapshotStateKt.collectAsState(dashConfig2.getRoutingSource(), (CoroutineContext) null, composer3, 0, 1);
                            State collectAsState52 = SnapshotStateKt.collectAsState(dashConfig2.getMapTheme(), (CoroutineContext) null, composer3, 0, 1);
                            ComposerKt.sourceInformationMarkerStart(composer3, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                            Composer composer342 = composer3;
                            rememberedValue5 = composer342.rememberedValue();
                            if (rememberedValue5 != Composer.Companion.getEmpty()) {
                            }
                            boolean booleanValue2 = ((Boolean) rememberedValue5).booleanValue();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                            }
                            ChoiceHeader("Rota sağlayıcısı", str12, composer3, 6);
                            List available2 = RoutingSource.Companion.getAvailable();
                            ArrayList arrayList32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available2, 10));
                            it = available2.iterator();
                            while (it.hasNext()) {
                            }
                            ArrayList arrayList42 = arrayList32;
                            String label2 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                            ComposerKt.sourceInformationMarkerStart(composer3, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                            changed2 = startRestartGroup.changed(collectAsState) | startRestartGroup.changedInstance(dashConfig2) | ((((i172 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel82)) || (i172 & 384) == 256);
                            Composer composer352 = composer3;
                            composer6 = composer3;
                            Object rememberedValue302 = composer352.rememberedValue();
                            if (changed2) {
                            }
                            RouteScreenKt$.ExternalSyntheticLambda5 externalSyntheticLambda52 = new RouteScreenKt$.ExternalSyntheticLambda5(dashConfig2, routeViewModel82, collectAsState);
                            composer352.updateRememberedValue(externalSyntheticLambda52);
                            obj2 = externalSyntheticLambda52;
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            WayfindrComponentsKt.WayfindrSegmented(arrayList42, label2, (Function1) obj2, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), composer6, 3072, 0);
                            ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer6, 54);
                            Modifier modifier152 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null);
                            Arrangement.Vertical vertical22 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
                            boolean z222 = false;
                            ComposerKt.sourceInformationMarkerStart(composer6, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy72 = ColumnKt.columnMeasurePolicy(vertical22, Alignment.Companion.getStart(), composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode152 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                            CompositionLocalMap currentCompositionLocalMap152 = composer6.getCurrentCompositionLocalMap();
                            Modifier materializeModifier152 = ComposedModifierKt.materializeModifier(composer6, modifier152);
                            Function0 constructor152 = ComposeUiNode.Companion.getConstructor();
                            int i532 = ((((54 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer6.getApplier() instanceof Applier)) {
                            }
                            composer6.startReusableNode();
                            if (composer6.getInserting()) {
                            }
                            Composer composer362 = Updater.constructor-impl(composer6);
                            MeasurePolicy measurePolicy32 = columnMeasurePolicy72;
                            Updater.set-impl(composer362, measurePolicy32, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer362, currentCompositionLocalMap152, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer362, Integer.valueOf(hashCode152), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer362, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer362, materializeModifier152, ComposeUiNode.Companion.getSetModifier());
                            int i542 = (i532 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer6, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScope columnScope72 = ColumnScopeInstance.INSTANCE;
                            int i552 = ((54 >> 6) & 112) | 6;
                            Composer composer372 = composer6;
                            ComposerKt.sourceInformationMarkerStart(composer372, -1001505061, "C:RouteScreen.kt#cef2n4");
                            composer372.startReplaceGroup(-1140683288);
                            ComposerKt.sourceInformation(composer372, "*585@31259L1578");
                            List<List> chunked2 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                            boolean z232 = false;
                            while (r41.hasNext()) {
                            }
                            Composer composer412 = composer372;
                            int i642 = i172;
                            Context context82 = context3;
                            composer412.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(composer412);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            composer6.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            Arrangement.Horizontal spaceBetween22 = Arrangement.INSTANCE.getSpaceBetween();
                            Alignment.Vertical centerVertically52 = Alignment.Companion.getCenterVertically();
                            Modifier modifier202 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(9), 7, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(composer6, 844473419, str8);
                            MeasurePolicy rowMeasurePolicy92 = RowKt.rowMeasurePolicy(spaceBetween22, centerVertically52, composer6, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode182 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                            CompositionLocalMap currentCompositionLocalMap182 = composer6.getCurrentCompositionLocalMap();
                            Modifier materializeModifier182 = ComposedModifierKt.materializeModifier(composer6, modifier202);
                            Function0 constructor182 = ComposeUiNode.Companion.getConstructor();
                            int i652 = ((((438 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer6.getApplier() instanceof Applier)) {
                            }
                            composer6.startReusableNode();
                            if (composer6.getInserting()) {
                            }
                            Composer composer422 = Updater.constructor-impl(composer6);
                            Updater.set-impl(composer422, rowMeasurePolicy92, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer422, currentCompositionLocalMap182, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer422, Integer.valueOf(hashCode182), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer422, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer422, materializeModifier182, ComposeUiNode.Companion.getSetModifier());
                            int i662 = (i652 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str7);
                            RowScope rowScope92 = RowScopeInstance.INSTANCE;
                            int i672 = ((438 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer6, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                            WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", (Modifier) null, composer6, 6, 2);
                            IconKt.Icon-ww6aTOc(RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer6, 432, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            composer6.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            List listOf22 = CollectionsKt.listOf(new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                            ComposerKt.sourceInformationMarkerStart(composer6, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                            VoiceManager voiceManager32 = voiceManager;
                            changedInstance4 = composer6.changedInstance(voiceManager32);
                            rememberedValue6 = composer6.rememberedValue();
                            if (!changedInstance4) {
                            }
                            RouteScreenKt$.ExternalSyntheticLambda7 externalSyntheticLambda72 = new RouteScreenKt$.ExternalSyntheticLambda7(voiceManager32);
                            composer6.updateRememberedValue(externalSyntheticLambda72);
                            rememberedValue6 = externalSyntheticLambda72;
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            WayfindrComponentsKt.WayfindrSegmented(listOf22, str16, (Function1) rememberedValue6, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null), composer6, 3078, 0);
                            if ((destination == null ? destination.getLat() : null) != null) {
                            }
                            if (!RouteScreen$lambda$7(mutableState10)) {
                            }
                            ImageVector check2 = !RouteScreen$lambda$7(mutableState10) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                            BtnVariant btnVariant22 = !RouteScreen$lambda$7(mutableState10) ? BtnVariant.Secondary : BtnVariant.Primary;
                            BtnSize btnSize22 = BtnSize.Lg;
                            if (RouteScreen$lambda$7(mutableState10)) {
                            }
                            Modifier fillMaxWidth$default22 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(composer6, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                            changedInstance5 = composer6.changedInstance(context82) | composer6.changedInstance(managedActivityResultLauncher42) | composer6.changedInstance(strArr2);
                            Object rememberedValue322 = composer6.rememberedValue();
                            if (!changedInstance5) {
                                z7 = z6;
                                mutableState5 = mutableState10;
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue322, fillMaxWidth$default22, check2, btnVariant22, btnSize22, z31, composer6, 196992, 0);
                                if (z7) {
                                }
                                composer6.endReplaceGroup();
                                if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
                                }
                                composer6.endReplaceGroup();
                                ComposerKt.sourceInformationMarkerEnd(composer6);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                startRestartGroup.endNode();
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                startRestartGroup.endNode();
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                                if (RouteScreen$lambda$10(mutableState6)) {
                                }
                                startRestartGroup.endReplaceGroup();
                                RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
                                if (RouteScreen$lambda$16 != null) {
                                }
                                RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
                                if (RouteScreen$lambda$13 != null) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                routeViewModel2 = routeViewModel5;
                            }
                            z7 = z6;
                            mutableState5 = mutableState10;
                            RouteScreenKt$.ExternalSyntheticLambda8 externalSyntheticLambda82 = new RouteScreenKt$.ExternalSyntheticLambda8(managedActivityResultLauncher42, strArr2, context82, mutableState5);
                            composer6.updateRememberedValue(externalSyntheticLambda82);
                            rememberedValue322 = externalSyntheticLambda82;
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue322, fillMaxWidth$default22, check2, btnVariant22, btnSize22, z31, composer6, 196992, 0);
                            if (z7) {
                            }
                            composer6.endReplaceGroup();
                            if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
                            }
                            composer6.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(composer6);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            startRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            startRestartGroup.endNode();
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                            if (RouteScreen$lambda$10(mutableState6)) {
                            }
                            startRestartGroup.endReplaceGroup();
                            RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
                            if (RouteScreen$lambda$16 != null) {
                            }
                            RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
                            if (RouteScreen$lambda$13 != null) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            routeViewModel2 = routeViewModel5;
                        }
                        Function2 function25 = new RouteScreen.3.1(routeViewModel3, rememberLauncherForActivityResult3, context2, (Continuation) null);
                        startRestartGroup.updateRememberedValue(function25);
                        rememberedValue23 = function25;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        EffectsKt.LaunchedEffect(unit, (Function2) rememberedValue23, startRestartGroup, 6);
                        Boolean valueOf22 = Boolean.valueOf(RouteScreen$lambda$7(mutableState10));
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                        changed = ((i6 & 112) == 32) | startRestartGroup.changed(state);
                        Object rememberedValue242 = startRestartGroup.rememberedValue();
                        if (changed) {
                        }
                        Function2 function242 = new RouteScreen.4.1(function2, mutableState10, state, (Continuation) null);
                        startRestartGroup.updateRememberedValue(function242);
                        rememberedValue242 = function242;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        EffectsKt.LaunchedEffect(valueOf22, (Function2) rememberedValue242, startRestartGroup, 0);
                        Modifier fillMaxSize$default32 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        Arrangement.Vertical top32 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal start32 = Alignment.Companion.getStart();
                        MeasurePolicy columnMeasurePolicy82 = ColumnKt.columnMeasurePolicy(top32, start32, startRestartGroup, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        state2 = state;
                        Context context522 = context2;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode202 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap202 = startRestartGroup.getCurrentCompositionLocalMap();
                        Modifier materializeModifier202 = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxSize$default32);
                        Function0 constructor202 = ComposeUiNode.Companion.getConstructor();
                        int i722 = ((((6 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(startRestartGroup.getApplier() instanceof Applier)) {
                        }
                        startRestartGroup.startReusableNode();
                        if (startRestartGroup.getInserting()) {
                        }
                        Composer composer922 = Updater.constructor-impl(startRestartGroup);
                        Updater.set-impl(composer922, columnMeasurePolicy82, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer922, currentCompositionLocalMap202, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer922, Integer.valueOf(hashCode202), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer922, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer922, materializeModifier202, ComposeUiNode.Companion.getSetModifier());
                        int i822 = (i722 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        int i922 = ((6 >> 6) & 112) | 6;
                        ColumnScope columnScope82 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                        Modifier modifier232 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.46f), ColorKt.getMapBase(), (Shape) null, 2, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy32 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap222 = startRestartGroup.getCurrentCompositionLocalMap();
                        Modifier materializeModifier222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier232);
                        Function0 constructor222 = ComposeUiNode.Companion.getConstructor();
                        int i1022 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(startRestartGroup.getApplier() instanceof Applier)) {
                        }
                        startRestartGroup.startReusableNode();
                        if (startRestartGroup.getInserting()) {
                        }
                        Composer composer1022 = Updater.constructor-impl(startRestartGroup);
                        Updater.set-impl(composer1022, maybeCachedBoxMeasurePolicy32, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer1022, currentCompositionLocalMap222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer1022, Integer.valueOf(hashCode222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer1022, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer1022, materializeModifier222, ComposeUiNode.Companion.getSetModifier());
                        int i1122 = (i1022 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        int i1222 = ((0 >> 6) & 112) | 6;
                        BoxScope boxScope32 = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                        Pair currentLocation32 = RouteScreen$lambda$0(state2).getCurrentLocation();
                        if (currentLocation32 == null) {
                        }
                        Pair currentLocation222 = RouteScreen$lambda$0(state2).getCurrentLocation();
                        if (currentLocation222 == null) {
                        }
                        float currentBearing22 = RouteScreen$lambda$0(state2).getCurrentBearing();
                        long locationRefreshKey22 = RouteScreen$lambda$0(state2).getLocationRefreshKey();
                        if (destination == null) {
                        }
                        List<SharedLocation> waypoints22 = RouteScreen$lambda$0(state2).getWaypoints();
                        boolean z1022 = false;
                        ArrayList arrayList52 = new ArrayList();
                        while (r101.hasNext()) {
                        }
                        ArrayList arrayList222 = arrayList52;
                        Route route52 = RouteScreen$lambda$0(state2).getRoute();
                        if (route52 == null) {
                        }
                        if (geometry == null) {
                        }
                        List list222 = geometry;
                        Route route222 = RouteScreen$lambda$0(state2).getRoute();
                        if (route222 == null) {
                        }
                        if (traffic == null) {
                        }
                        List list322 = traffic;
                        routeAlternatives = RouteScreen$lambda$0(state2).getRouteAlternatives();
                        list = routeAlternatives;
                        if (!(routeAlternatives.size() <= 1)) {
                        }
                        if (list == null) {
                        }
                        int selectedRouteIndex32 = RouteScreen$lambda$0(state2).getSelectedRouteIndex();
                        if (RouteScreen$lambda$0(state2).getRoute() == null) {
                        }
                        Modifier fillMaxSize$default222 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                        if (((i6 & 896) ^ 384) <= 256) {
                        }
                        Object rememberedValue2522 = startRestartGroup.rememberedValue();
                        if (z4) {
                        }
                        RouteScreenKt$.ExternalSyntheticLambda22 externalSyntheticLambda2222 = new RouteScreenKt$.ExternalSyntheticLambda22(routeViewModel3);
                        startRestartGroup.updateRememberedValue(externalSyntheticLambda2222);
                        rememberedValue2522 = externalSyntheticLambda2222;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        FreeWayfindrMapKt.WayfindrMap(d, d2, pair2, arrayList222, list222, list322, list, selectedRouteIndex32, true, fillMaxSize$default222, z11, false, currentBearing22, locationRefreshKey22, false, true, (Double) null, (Double) null, (Float) null, (Float) null, false, (Function1) rememberedValue2522, startRestartGroup, 905969664, 221184, 0, 2033664);
                        Alignment.Vertical centerVertically72 = Alignment.Companion.getCenterVertically();
                        Modifier modifier242 = PaddingKt.padding-VpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope32.align(Modifier.Companion, Alignment.Companion.getTopStart()), 0.0f, 1, (Object) null), Brush.Companion.verticalGradient-8A-3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.box-impl(Color.Companion.getTransparent-0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl(16), Dp.constructor-impl(14));
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy rowMeasurePolicy112 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically72, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode322 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap322 = startRestartGroup.getCurrentCompositionLocalMap();
                        Modifier materializeModifier322 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier242);
                        Function0 constructor322 = ComposeUiNode.Companion.getConstructor();
                        int i1322 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        int i1422 = i6;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(startRestartGroup.getApplier() instanceof Applier)) {
                        }
                        startRestartGroup.startReusableNode();
                        if (startRestartGroup.getInserting()) {
                        }
                        Composer composer1122 = Updater.constructor-impl(startRestartGroup);
                        Updater.set-impl(composer1122, rowMeasurePolicy112, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer1122, currentCompositionLocalMap322, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer1122, Integer.valueOf(hashCode322), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer1122, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer1122, materializeModifier322, ComposeUiNode.Companion.getSetModifier());
                        int i1522 = (i1322 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScope rowScope112 = RowScopeInstance.INSTANCE;
                        int i1622 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                        ManagedActivityResultLauncher managedActivityResultLauncher422 = managedActivityResultLauncher;
                        ManagedActivityResultLauncher managedActivityResultLauncher522 = managedActivityResultLauncher2;
                        int i1722 = i1422;
                        WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), function0, BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, ((i1422 << 3) & 112) | 3072, 48);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                        WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(state2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(state2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(state2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), startRestartGroup, 0, 4);
                        ImageVector recenter22 = WayfindrIcons.INSTANCE.getRecenter();
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                        if (((i1722 & 896) ^ 384) <= 256) {
                        }
                        rememberedValue4 = startRestartGroup.rememberedValue();
                        if (!z5) {
                        }
                        KFunction kFunction42 = new RouteScreen.5.1.5.1.1(routeViewModel3);
                        startRestartGroup.updateRememberedValue(kFunction42);
                        rememberedValue4 = kFunction42;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(recenter22, (KFunction) rememberedValue4, BackgroundKt.background-bw27NRU(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(8), 0.0f, 0.0f, 0.0f, 14, (Object) null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, 3072, 48);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        startRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                        }
                        startRestartGroup.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        startRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        Modifier modifier322 = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-VpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.background-bw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope82, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-a9UjIt4$default(Dp.constructor-impl(22), Dp.constructor-impl(22), 0.0f, 0.0f, 12, (Object) null)), ColorKt.getBg1(), (Shape) null, 2, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18), Dp.constructor-impl(8)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(20), 7, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode422 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap422 = startRestartGroup.getCurrentCompositionLocalMap();
                        Modifier materializeModifier422 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier322);
                        Function0 constructor422 = ComposeUiNode.Companion.getConstructor();
                        int i1822 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(startRestartGroup.getApplier() instanceof Applier)) {
                        }
                        startRestartGroup.startReusableNode();
                        if (startRestartGroup.getInserting()) {
                        }
                        Composer composer1222 = Updater.constructor-impl(startRestartGroup);
                        Updater.set-impl(composer1222, columnMeasurePolicy222, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer1222, currentCompositionLocalMap422, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer1222, Integer.valueOf(hashCode422), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer1222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer1222, materializeModifier422, ComposeUiNode.Companion.getSetModifier());
                        int i1922 = (i1822 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        int i2022 = ((0 >> 6) & 112) | 6;
                        ColumnScope columnScope222 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                        BoxKt.Box(PaddingKt.padding-qDBjuR0$default(columnScope222.align(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), Dp.constructor-impl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), (Shape) null, 2, (Object) null), Alignment.Companion.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), startRestartGroup, 0);
                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                        RouteViewModel routeViewModel822 = routeViewModel3;
                        WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(4), (Function0) null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new RouteScreenKt$.ExternalSyntheticLambda23(function1, routeViewModel822, mutableState14, mutableState16, mutableState, mutableState18, state2, mutableState2), startRestartGroup, 54), startRestartGroup, 24966, 10);
                        searchNote = RouteScreen$lambda$0(state2).getSearchNote();
                        if (searchNote != null) {
                        }
                        WayfindrIcons.INSTANCE.getQrScan();
                        BtnVariant btnVariant42 = BtnVariant.Ghost;
                        BtnSize btnSize42 = BtnSize.Sm;
                        PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(12), 7, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(startRestartGroup, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance3 = startRestartGroup.changedInstance(managedActivityResultLauncher522);
                        Object rememberedValue2722 = startRestartGroup.rememberedValue();
                        if (changedInstance3) {
                        }
                        RouteScreenKt$.ExternalSyntheticLambda24 externalSyntheticLambda2422 = new RouteScreenKt$.ExternalSyntheticLambda24(managedActivityResultLauncher522);
                        startRestartGroup.updateRememberedValue(externalSyntheticLambda2422);
                        obj = externalSyntheticLambda2422;
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        composer3 = startRestartGroup;
                        sharedRouteName = RouteScreen$lambda$0(state2).getSharedRouteName();
                        if (sharedRouteName != null) {
                        }
                        if (RouteScreen$lambda$0(state2).getWaypoints().isEmpty()) {
                        }
                        composer3.endReplaceGroup();
                        if ((destination == null ? destination.getLat() : null) != null) {
                        }
                        composer3.startReplaceGroup(2018241319);
                        ComposerKt.sourceInformation(composer3, "438@22367L1104");
                        PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null);
                        Dp.constructor-impl(16);
                        ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-835303176$app();
                        composer3.endReplaceGroup();
                        WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(state2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(6), 7, (Object) null), composer3, 48, 0);
                        Alignment.Vertical top222 = Alignment.Companion.getTop();
                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str8);
                        Modifier modifier522 = Modifier.Companion;
                        MeasurePolicy rowMeasurePolicy322 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top222, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode622 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap622 = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier622 = ComposedModifierKt.materializeModifier(composer3, modifier522);
                        Function0 constructor622 = ComposeUiNode.Companion.getConstructor();
                        int i2422 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                        }
                        Composer composer1422 = Updater.constructor-impl(composer3);
                        Updater.set-impl(composer1422, rowMeasurePolicy322, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer1422, currentCompositionLocalMap622, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer1422, Integer.valueOf(hashCode622), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer1422, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer1422, materializeModifier622, ComposeUiNode.Companion.getSetModifier());
                        int i2522 = (i2422 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str7);
                        RowScope rowScope322 = RowScopeInstance.INSTANCE;
                        int i2622 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                        Alignment center22 = Alignment.Companion.getCenter();
                        Modifier modifier622 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(44)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy222 = BoxKt.maybeCachedBoxMeasurePolicy(center22, false);
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode722 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap722 = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier722 = ComposedModifierKt.materializeModifier(composer3, modifier622);
                        Function0 constructor722 = ComposeUiNode.Companion.getConstructor();
                        int i2722 = ((((48 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                        }
                        Composer composer1522 = Updater.constructor-impl(composer3);
                        Updater.set-impl(composer1522, maybeCachedBoxMeasurePolicy222, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer1522, currentCompositionLocalMap722, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer1522, Integer.valueOf(hashCode722), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer1522, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer1522, materializeModifier722, ComposeUiNode.Companion.getSetModifier());
                        int i2822 = (i2722 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        BoxScope boxScope222 = BoxScopeInstance.INSTANCE;
                        int i2922 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                        IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), ColorKt.getGold(), composer3, 432, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer3, 6);
                        ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        Modifier modifier722 = Modifier.Companion;
                        MeasurePolicy columnMeasurePolicy322 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode822 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap822 = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier822 = ComposedModifierKt.materializeModifier(composer3, modifier722);
                        Function0 constructor822 = ComposeUiNode.Companion.getConstructor();
                        int i3022 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                        }
                        Composer composer1622 = Updater.constructor-impl(composer3);
                        Updater.set-impl(composer1622, columnMeasurePolicy322, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer1622, currentCompositionLocalMap822, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer1622, Integer.valueOf(hashCode822), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer1622, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer1622, materializeModifier822, ComposeUiNode.Companion.getSetModifier());
                        int i3122 = (i3022 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScope columnScope322 = ColumnScopeInstance.INSTANCE;
                        int i3222 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -1541055177, "C:RouteScreen.kt#cef2n4");
                        if (RouteScreen$lambda$0(state2).isResolving()) {
                        }
                        if (StringsKt.isBlank(str15)) {
                        }
                        composer3.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), composer3, 6);
                        if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                        }
                        composer3.endReplaceGroup();
                        routing = RouteScreen$lambda$0(state2).getRouting();
                        Triple[] tripleArr22 = new Triple[3];
                        if (routing) {
                        }
                        tripleArr22[0] = new Triple(distanceText, "", "Mesafe");
                        if (routing) {
                        }
                        tripleArr22[1] = new Triple(durationText, "", "Süre");
                        if (routing) {
                        }
                        tripleArr22[2] = new Triple(etaText, "", "Varış");
                        List listOf32 = CollectionsKt.listOf(tripleArr22);
                        Arrangement.Horizontal horizontal322 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
                        composer5 = composer3;
                        boolean z1822 = false;
                        ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str8);
                        Modifier modifier1122 = Modifier.Companion;
                        MeasurePolicy rowMeasurePolicy522 = RowKt.rowMeasurePolicy(horizontal322, Alignment.Companion.getTop(), composer5, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode1222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                        CompositionLocalMap currentCompositionLocalMap1222 = composer5.getCurrentCompositionLocalMap();
                        Modifier materializeModifier1222 = ComposedModifierKt.materializeModifier(composer5, modifier1122);
                        Function0 constructor1222 = ComposeUiNode.Companion.getConstructor();
                        int i4422 = ((((48 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer5, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer5.getApplier() instanceof Applier)) {
                        }
                        composer5.startReusableNode();
                        if (composer5.getInserting()) {
                        }
                        Composer composer2722 = Updater.constructor-impl(composer5);
                        MeasurePolicy measurePolicy72 = rowMeasurePolicy522;
                        Updater.set-impl(composer2722, measurePolicy72, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer2722, currentCompositionLocalMap1222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer2722, Integer.valueOf(hashCode1222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer2722, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer2722, materializeModifier1222, ComposeUiNode.Companion.getSetModifier());
                        int i4522 = (i4422 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, str7);
                        int i4622 = ((48 >> 6) & 112) | 6;
                        RowScope rowScope522 = RowScopeInstance.INSTANCE;
                        Composer composer2822 = composer5;
                        ComposerKt.sourceInformationMarkerStart(composer2822, 213090107, "C:RouteScreen.kt#cef2n4");
                        composer2822.startReplaceGroup(1807989502);
                        ComposerKt.sourceInformation(composer2822, "*540@28620L859");
                        List<Triple> list522 = listOf32;
                        boolean z1922 = false;
                        while (r39.hasNext()) {
                        }
                        Composer composer3322 = composer2822;
                        composer3322.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer3322);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        composer5.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        ComposerKt.sourceInformationMarkerEnd(composer5);
                        SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), composer3, 6);
                        collectAsState = SnapshotStateKt.collectAsState(dashConfig2.getRoutingSource(), (CoroutineContext) null, composer3, 0, 1);
                        State collectAsState522 = SnapshotStateKt.collectAsState(dashConfig2.getMapTheme(), (CoroutineContext) null, composer3, 0, 1);
                        ComposerKt.sourceInformationMarkerStart(composer3, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                        Composer composer3422 = composer3;
                        rememberedValue5 = composer3422.rememberedValue();
                        if (rememberedValue5 != Composer.Companion.getEmpty()) {
                        }
                        boolean booleanValue22 = ((Boolean) rememberedValue5).booleanValue();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                        }
                        ChoiceHeader("Rota sağlayıcısı", str12, composer3, 6);
                        List available22 = RoutingSource.Companion.getAvailable();
                        ArrayList arrayList322 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available22, 10));
                        it = available22.iterator();
                        while (it.hasNext()) {
                        }
                        ArrayList arrayList422 = arrayList322;
                        String label22 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                        ComposerKt.sourceInformationMarkerStart(composer3, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                        changed2 = startRestartGroup.changed(collectAsState) | startRestartGroup.changedInstance(dashConfig2) | ((((i1722 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel822)) || (i1722 & 384) == 256);
                        Composer composer3522 = composer3;
                        composer6 = composer3;
                        Object rememberedValue3022 = composer3522.rememberedValue();
                        if (changed2) {
                        }
                        RouteScreenKt$.ExternalSyntheticLambda5 externalSyntheticLambda522 = new RouteScreenKt$.ExternalSyntheticLambda5(dashConfig2, routeViewModel822, collectAsState);
                        composer3522.updateRememberedValue(externalSyntheticLambda522);
                        obj2 = externalSyntheticLambda522;
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        WayfindrComponentsKt.WayfindrSegmented(arrayList422, label22, (Function1) obj2, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), composer6, 3072, 0);
                        ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer6, 54);
                        Modifier modifier1522 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null);
                        Arrangement.Vertical vertical222 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
                        boolean z2222 = false;
                        ComposerKt.sourceInformationMarkerStart(composer6, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy722 = ColumnKt.columnMeasurePolicy(vertical222, Alignment.Companion.getStart(), composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode1522 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                        CompositionLocalMap currentCompositionLocalMap1522 = composer6.getCurrentCompositionLocalMap();
                        Modifier materializeModifier1522 = ComposedModifierKt.materializeModifier(composer6, modifier1522);
                        Function0 constructor1522 = ComposeUiNode.Companion.getConstructor();
                        int i5322 = ((((54 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer6.getApplier() instanceof Applier)) {
                        }
                        composer6.startReusableNode();
                        if (composer6.getInserting()) {
                        }
                        Composer composer3622 = Updater.constructor-impl(composer6);
                        MeasurePolicy measurePolicy322 = columnMeasurePolicy722;
                        Updater.set-impl(composer3622, measurePolicy322, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer3622, currentCompositionLocalMap1522, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer3622, Integer.valueOf(hashCode1522), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer3622, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer3622, materializeModifier1522, ComposeUiNode.Companion.getSetModifier());
                        int i5422 = (i5322 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer6, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScope columnScope722 = ColumnScopeInstance.INSTANCE;
                        int i5522 = ((54 >> 6) & 112) | 6;
                        Composer composer3722 = composer6;
                        ComposerKt.sourceInformationMarkerStart(composer3722, -1001505061, "C:RouteScreen.kt#cef2n4");
                        composer3722.startReplaceGroup(-1140683288);
                        ComposerKt.sourceInformation(composer3722, "*585@31259L1578");
                        List<List> chunked22 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                        boolean z2322 = false;
                        while (r41.hasNext()) {
                        }
                        Composer composer4122 = composer3722;
                        int i6422 = i1722;
                        Context context822 = context3;
                        composer4122.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer4122);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        composer6.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        Arrangement.Horizontal spaceBetween222 = Arrangement.INSTANCE.getSpaceBetween();
                        Alignment.Vertical centerVertically522 = Alignment.Companion.getCenterVertically();
                        Modifier modifier2022 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(9), 7, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(composer6, 844473419, str8);
                        MeasurePolicy rowMeasurePolicy922 = RowKt.rowMeasurePolicy(spaceBetween222, centerVertically522, composer6, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode1822 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                        CompositionLocalMap currentCompositionLocalMap1822 = composer6.getCurrentCompositionLocalMap();
                        Modifier materializeModifier1822 = ComposedModifierKt.materializeModifier(composer6, modifier2022);
                        Function0 constructor1822 = ComposeUiNode.Companion.getConstructor();
                        int i6522 = ((((438 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer6.getApplier() instanceof Applier)) {
                        }
                        composer6.startReusableNode();
                        if (composer6.getInserting()) {
                        }
                        Composer composer4222 = Updater.constructor-impl(composer6);
                        Updater.set-impl(composer4222, rowMeasurePolicy922, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer4222, currentCompositionLocalMap1822, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer4222, Integer.valueOf(hashCode1822), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer4222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer4222, materializeModifier1822, ComposeUiNode.Companion.getSetModifier());
                        int i6622 = (i6522 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str7);
                        RowScope rowScope922 = RowScopeInstance.INSTANCE;
                        int i6722 = ((438 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer6, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                        WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", (Modifier) null, composer6, 6, 2);
                        IconKt.Icon-ww6aTOc(RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer6, 432, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        composer6.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        List listOf222 = CollectionsKt.listOf(new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                        ComposerKt.sourceInformationMarkerStart(composer6, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                        VoiceManager voiceManager322 = voiceManager;
                        changedInstance4 = composer6.changedInstance(voiceManager322);
                        rememberedValue6 = composer6.rememberedValue();
                        if (!changedInstance4) {
                        }
                        RouteScreenKt$.ExternalSyntheticLambda7 externalSyntheticLambda722 = new RouteScreenKt$.ExternalSyntheticLambda7(voiceManager322);
                        composer6.updateRememberedValue(externalSyntheticLambda722);
                        rememberedValue6 = externalSyntheticLambda722;
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        WayfindrComponentsKt.WayfindrSegmented(listOf222, str16, (Function1) rememberedValue6, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null), composer6, 3078, 0);
                        if ((destination == null ? destination.getLat() : null) != null) {
                        }
                        if (!RouteScreen$lambda$7(mutableState10)) {
                        }
                        ImageVector check22 = !RouteScreen$lambda$7(mutableState10) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                        BtnVariant btnVariant222 = !RouteScreen$lambda$7(mutableState10) ? BtnVariant.Secondary : BtnVariant.Primary;
                        BtnSize btnSize222 = BtnSize.Lg;
                        if (RouteScreen$lambda$7(mutableState10)) {
                        }
                        Modifier fillMaxWidth$default222 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(composer6, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance5 = composer6.changedInstance(context822) | composer6.changedInstance(managedActivityResultLauncher422) | composer6.changedInstance(strArr2);
                        Object rememberedValue3222 = composer6.rememberedValue();
                        if (!changedInstance5) {
                        }
                        z7 = z6;
                        mutableState5 = mutableState10;
                        RouteScreenKt$.ExternalSyntheticLambda8 externalSyntheticLambda822 = new RouteScreenKt$.ExternalSyntheticLambda8(managedActivityResultLauncher422, strArr2, context822, mutableState5);
                        composer6.updateRememberedValue(externalSyntheticLambda822);
                        rememberedValue3222 = externalSyntheticLambda822;
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue3222, fillMaxWidth$default222, check22, btnVariant222, btnSize222, z31, composer6, 196992, 0);
                        if (z7) {
                        }
                        composer6.endReplaceGroup();
                        if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
                        }
                        composer6.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer6);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        startRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        startRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                        if (RouteScreen$lambda$10(mutableState6)) {
                        }
                        startRestartGroup.endReplaceGroup();
                        RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
                        if (RouteScreen$lambda$16 != null) {
                        }
                        RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
                        if (RouteScreen$lambda$13 != null) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        routeViewModel2 = routeViewModel5;
                    }
                } else {
                    context = context4;
                }
                mutableState = mutableState19;
                mutableState2 = mutableState17;
                state = collectAsState2;
                str6 = str5;
                context2 = context;
                rememberedValue20 = new RouteScreen.2.1(context, mutableState14, mutableState16, mutableState2, mutableState18, mutableState, collectAsState2, (Continuation) null);
                startRestartGroup.updateRememberedValue(rememberedValue20);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                EffectsKt.LaunchedEffect(RouteScreen$lambda$19, valueOf, (Function2) rememberedValue20, startRestartGroup, 0);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131771185, "CC(remember):RouteScreen.kt#9igjgp");
                if (((i6 & 896) ^ 384) <= 256) {
                }
                rememberedValue = startRestartGroup.rememberedValue();
                if (z) {
                }
                MutableState mutableState202 = mutableState;
                RouteViewModel routeViewModel72 = routeViewModel6;
                RouteScreenKt$.ExternalSyntheticLambda4 externalSyntheticLambda42 = new RouteScreenKt$.ExternalSyntheticLambda4(routeViewModel72, mutableState202, mutableState16, mutableState2, mutableState18, mutableState14, mutableState15);
                routeViewModel3 = routeViewModel72;
                mutableState = mutableState202;
                mutableState16 = mutableState16;
                startRestartGroup.updateRememberedValue(externalSyntheticLambda42);
                rememberedValue = externalSyntheticLambda42;
                Function1 function12 = (Function1) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131750886, "CC(remember):RouteScreen.kt#9igjgp");
                rememberedValue2 = startRestartGroup.rememberedValue();
                if (rememberedValue2 == Composer.Companion.getEmpty()) {
                }
                String[] strArr22 = (String[]) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ActivityResultContract requestMultiplePermissions2 = new ActivityResultContracts.RequestMultiplePermissions();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131727730, "CC(remember):RouteScreen.kt#9igjgp");
                rememberedValue3 = startRestartGroup.rememberedValue();
                if (rememberedValue3 == Composer.Companion.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ManagedActivityResultLauncher rememberLauncherForActivityResult4 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions2, (Function1) rememberedValue3, startRestartGroup, 48);
                ActivityResultContract pickVisualMedia2 = new ActivityResultContracts.PickVisualMedia();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131722424, "CC(remember):RouteScreen.kt#9igjgp");
                if (((i6 & 896) ^ 384) <= 256) {
                }
                Object rememberedValue212 = startRestartGroup.rememberedValue();
                if (z2) {
                }
                RouteScreenKt$.ExternalSyntheticLambda20 externalSyntheticLambda202 = new RouteScreenKt$.ExternalSyntheticLambda20(routeViewModel3);
                startRestartGroup.updateRememberedValue(externalSyntheticLambda202);
                rememberedValue212 = externalSyntheticLambda202;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ManagedActivityResultLauncher rememberLauncherForActivityResult22 = ActivityResultRegistryKt.rememberLauncherForActivityResult(pickVisualMedia2, (Function1) rememberedValue212, startRestartGroup, 0);
                ActivityResultContract requestPermission2 = new ActivityResultContracts.RequestPermission();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131716088, "CC(remember):RouteScreen.kt#9igjgp");
                if (((i6 & 896) ^ 384) <= 256) {
                }
                Object rememberedValue222 = startRestartGroup.rememberedValue();
                if (z3) {
                }
                RouteScreenKt$.ExternalSyntheticLambda21 externalSyntheticLambda212 = new RouteScreenKt$.ExternalSyntheticLambda21(routeViewModel3);
                startRestartGroup.updateRememberedValue(externalSyntheticLambda212);
                rememberedValue222 = externalSyntheticLambda212;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ManagedActivityResultLauncher rememberLauncherForActivityResult32 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission2, (Function1) rememberedValue222, startRestartGroup, 0);
                Unit unit10 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131713748, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance2 = startRestartGroup.changedInstance(context2) | ((((i6 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i6 & 384) == 256) | startRestartGroup.changedInstance(rememberLauncherForActivityResult32);
                Object rememberedValue232 = startRestartGroup.rememberedValue();
                if (changedInstance2) {
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    EffectsKt.LaunchedEffect(unit10, (Function2) rememberedValue232, startRestartGroup, 6);
                    Boolean valueOf222 = Boolean.valueOf(RouteScreen$lambda$7(mutableState10));
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                    changed = ((i6 & 112) == 32) | startRestartGroup.changed(state);
                    Object rememberedValue2422 = startRestartGroup.rememberedValue();
                    if (changed) {
                    }
                    Function2 function2422 = new RouteScreen.4.1(function2, mutableState10, state, (Continuation) null);
                    startRestartGroup.updateRememberedValue(function2422);
                    rememberedValue2422 = function2422;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    EffectsKt.LaunchedEffect(valueOf222, (Function2) rememberedValue2422, startRestartGroup, 0);
                    Modifier fillMaxSize$default322 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Arrangement.Vertical top322 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal start322 = Alignment.Companion.getStart();
                    MeasurePolicy columnMeasurePolicy822 = ColumnKt.columnMeasurePolicy(top322, start322, startRestartGroup, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    state2 = state;
                    Context context5222 = context2;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode2022 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap2022 = startRestartGroup.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2022 = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxSize$default322);
                    Function0 constructor2022 = ComposeUiNode.Companion.getConstructor();
                    int i7222 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    }
                    startRestartGroup.startReusableNode();
                    if (startRestartGroup.getInserting()) {
                    }
                    Composer composer9222 = Updater.constructor-impl(startRestartGroup);
                    Updater.set-impl(composer9222, columnMeasurePolicy822, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer9222, currentCompositionLocalMap2022, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer9222, Integer.valueOf(hashCode2022), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer9222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer9222, materializeModifier2022, ComposeUiNode.Companion.getSetModifier());
                    int i8222 = (i7222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    int i9222 = ((6 >> 6) & 112) | 6;
                    ColumnScope columnScope822 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                    Modifier modifier2322 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.46f), ColorKt.getMapBase(), (Shape) null, 2, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy322 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode2222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap2222 = startRestartGroup.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier2322);
                    Function0 constructor2222 = ComposeUiNode.Companion.getConstructor();
                    int i10222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    }
                    startRestartGroup.startReusableNode();
                    if (startRestartGroup.getInserting()) {
                    }
                    Composer composer10222 = Updater.constructor-impl(startRestartGroup);
                    Updater.set-impl(composer10222, maybeCachedBoxMeasurePolicy322, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer10222, currentCompositionLocalMap2222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer10222, Integer.valueOf(hashCode2222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer10222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer10222, materializeModifier2222, ComposeUiNode.Companion.getSetModifier());
                    int i11222 = (i10222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    int i12222 = ((0 >> 6) & 112) | 6;
                    BoxScope boxScope322 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                    Pair currentLocation322 = RouteScreen$lambda$0(state2).getCurrentLocation();
                    if (currentLocation322 == null) {
                    }
                    Pair currentLocation2222 = RouteScreen$lambda$0(state2).getCurrentLocation();
                    if (currentLocation2222 == null) {
                    }
                    float currentBearing222 = RouteScreen$lambda$0(state2).getCurrentBearing();
                    long locationRefreshKey222 = RouteScreen$lambda$0(state2).getLocationRefreshKey();
                    if (destination == null) {
                    }
                    List<SharedLocation> waypoints222 = RouteScreen$lambda$0(state2).getWaypoints();
                    boolean z10222 = false;
                    ArrayList arrayList522 = new ArrayList();
                    while (r101.hasNext()) {
                    }
                    ArrayList arrayList2222 = arrayList522;
                    Route route522 = RouteScreen$lambda$0(state2).getRoute();
                    if (route522 == null) {
                    }
                    if (geometry == null) {
                    }
                    List list2222 = geometry;
                    Route route2222 = RouteScreen$lambda$0(state2).getRoute();
                    if (route2222 == null) {
                    }
                    if (traffic == null) {
                    }
                    List list3222 = traffic;
                    routeAlternatives = RouteScreen$lambda$0(state2).getRouteAlternatives();
                    list = routeAlternatives;
                    if (!(routeAlternatives.size() <= 1)) {
                    }
                    if (list == null) {
                    }
                    int selectedRouteIndex322 = RouteScreen$lambda$0(state2).getSelectedRouteIndex();
                    if (RouteScreen$lambda$0(state2).getRoute() == null) {
                    }
                    Modifier fillMaxSize$default2222 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                    if (((i6 & 896) ^ 384) <= 256) {
                    }
                    Object rememberedValue25222 = startRestartGroup.rememberedValue();
                    if (z4) {
                    }
                    RouteScreenKt$.ExternalSyntheticLambda22 externalSyntheticLambda22222 = new RouteScreenKt$.ExternalSyntheticLambda22(routeViewModel3);
                    startRestartGroup.updateRememberedValue(externalSyntheticLambda22222);
                    rememberedValue25222 = externalSyntheticLambda22222;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    FreeWayfindrMapKt.WayfindrMap(d, d2, pair2, arrayList2222, list2222, list3222, list, selectedRouteIndex322, true, fillMaxSize$default2222, z11, false, currentBearing222, locationRefreshKey222, false, true, (Double) null, (Double) null, (Float) null, (Float) null, false, (Function1) rememberedValue25222, startRestartGroup, 905969664, 221184, 0, 2033664);
                    Alignment.Vertical centerVertically722 = Alignment.Companion.getCenterVertically();
                    Modifier modifier2422 = PaddingKt.padding-VpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope322.align(Modifier.Companion, Alignment.Companion.getTopStart()), 0.0f, 1, (Object) null), Brush.Companion.verticalGradient-8A-3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.box-impl(Color.Companion.getTransparent-0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl(16), Dp.constructor-impl(14));
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy1122 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically722, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode3222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap3222 = startRestartGroup.getCurrentCompositionLocalMap();
                    Modifier materializeModifier3222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier2422);
                    Function0 constructor3222 = ComposeUiNode.Companion.getConstructor();
                    int i13222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    int i14222 = i6;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    }
                    startRestartGroup.startReusableNode();
                    if (startRestartGroup.getInserting()) {
                    }
                    Composer composer11222 = Updater.constructor-impl(startRestartGroup);
                    Updater.set-impl(composer11222, rowMeasurePolicy1122, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer11222, currentCompositionLocalMap3222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer11222, Integer.valueOf(hashCode3222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer11222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer11222, materializeModifier3222, ComposeUiNode.Companion.getSetModifier());
                    int i15222 = (i13222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScope rowScope1122 = RowScopeInstance.INSTANCE;
                    int i16222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                    ManagedActivityResultLauncher managedActivityResultLauncher4222 = managedActivityResultLauncher;
                    ManagedActivityResultLauncher managedActivityResultLauncher5222 = managedActivityResultLauncher2;
                    int i17222 = i14222;
                    WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), function0, BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, ((i14222 << 3) & 112) | 3072, 48);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                    WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(state2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(state2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(state2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), startRestartGroup, 0, 4);
                    ImageVector recenter222 = WayfindrIcons.INSTANCE.getRecenter();
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                    if (((i17222 & 896) ^ 384) <= 256) {
                    }
                    rememberedValue4 = startRestartGroup.rememberedValue();
                    if (!z5) {
                    }
                    KFunction kFunction422 = new RouteScreen.5.1.5.1.1(routeViewModel3);
                    startRestartGroup.updateRememberedValue(kFunction422);
                    rememberedValue4 = kFunction422;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(recenter222, (KFunction) rememberedValue4, BackgroundKt.background-bw27NRU(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(8), 0.0f, 0.0f, 0.0f, 14, (Object) null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, 3072, 48);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    startRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                    }
                    startRestartGroup.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    startRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    Modifier modifier3222 = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-VpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.background-bw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope822, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-a9UjIt4$default(Dp.constructor-impl(22), Dp.constructor-impl(22), 0.0f, 0.0f, 12, (Object) null)), ColorKt.getBg1(), (Shape) null, 2, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18), Dp.constructor-impl(8)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(20), 7, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy2222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode4222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap4222 = startRestartGroup.getCurrentCompositionLocalMap();
                    Modifier materializeModifier4222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier3222);
                    Function0 constructor4222 = ComposeUiNode.Companion.getConstructor();
                    int i18222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(startRestartGroup.getApplier() instanceof Applier)) {
                    }
                    startRestartGroup.startReusableNode();
                    if (startRestartGroup.getInserting()) {
                    }
                    Composer composer12222 = Updater.constructor-impl(startRestartGroup);
                    Updater.set-impl(composer12222, columnMeasurePolicy2222, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer12222, currentCompositionLocalMap4222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer12222, Integer.valueOf(hashCode4222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer12222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer12222, materializeModifier4222, ComposeUiNode.Companion.getSetModifier());
                    int i19222 = (i18222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    int i20222 = ((0 >> 6) & 112) | 6;
                    ColumnScope columnScope2222 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                    BoxKt.Box(PaddingKt.padding-qDBjuR0$default(columnScope2222.align(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), Dp.constructor-impl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), (Shape) null, 2, (Object) null), Alignment.Companion.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), startRestartGroup, 0);
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                    RouteViewModel routeViewModel8222 = routeViewModel3;
                    WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(4), (Function0) null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new RouteScreenKt$.ExternalSyntheticLambda23(function12, routeViewModel8222, mutableState14, mutableState16, mutableState, mutableState18, state2, mutableState2), startRestartGroup, 54), startRestartGroup, 24966, 10);
                    searchNote = RouteScreen$lambda$0(state2).getSearchNote();
                    if (searchNote != null) {
                    }
                    WayfindrIcons.INSTANCE.getQrScan();
                    BtnVariant btnVariant422 = BtnVariant.Ghost;
                    BtnSize btnSize422 = BtnSize.Sm;
                    PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(12), 7, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(startRestartGroup, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                    changedInstance3 = startRestartGroup.changedInstance(managedActivityResultLauncher5222);
                    Object rememberedValue27222 = startRestartGroup.rememberedValue();
                    if (changedInstance3) {
                    }
                    RouteScreenKt$.ExternalSyntheticLambda24 externalSyntheticLambda24222 = new RouteScreenKt$.ExternalSyntheticLambda24(managedActivityResultLauncher5222);
                    startRestartGroup.updateRememberedValue(externalSyntheticLambda24222);
                    obj = externalSyntheticLambda24222;
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    composer3 = startRestartGroup;
                    sharedRouteName = RouteScreen$lambda$0(state2).getSharedRouteName();
                    if (sharedRouteName != null) {
                    }
                    if (RouteScreen$lambda$0(state2).getWaypoints().isEmpty()) {
                    }
                    composer3.endReplaceGroup();
                    if ((destination == null ? destination.getLat() : null) != null) {
                    }
                    composer3.startReplaceGroup(2018241319);
                    ComposerKt.sourceInformation(composer3, "438@22367L1104");
                    PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null);
                    Dp.constructor-impl(16);
                    ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-835303176$app();
                    composer3.endReplaceGroup();
                    WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(state2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(6), 7, (Object) null), composer3, 48, 0);
                    Alignment.Vertical top2222 = Alignment.Companion.getTop();
                    ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str8);
                    Modifier modifier5222 = Modifier.Companion;
                    MeasurePolicy rowMeasurePolicy3222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top2222, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode6222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap6222 = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier6222 = ComposedModifierKt.materializeModifier(composer3, modifier5222);
                    Function0 constructor6222 = ComposeUiNode.Companion.getConstructor();
                    int i24222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                    }
                    Composer composer14222 = Updater.constructor-impl(composer3);
                    Updater.set-impl(composer14222, rowMeasurePolicy3222, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer14222, currentCompositionLocalMap6222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer14222, Integer.valueOf(hashCode6222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer14222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer14222, materializeModifier6222, ComposeUiNode.Companion.getSetModifier());
                    int i25222 = (i24222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str7);
                    RowScope rowScope3222 = RowScopeInstance.INSTANCE;
                    int i26222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                    Alignment center222 = Alignment.Companion.getCenter();
                    Modifier modifier6222 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(44)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy2222 = BoxKt.maybeCachedBoxMeasurePolicy(center222, false);
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode7222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap7222 = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier7222 = ComposedModifierKt.materializeModifier(composer3, modifier6222);
                    Function0 constructor7222 = ComposeUiNode.Companion.getConstructor();
                    int i27222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                    }
                    Composer composer15222 = Updater.constructor-impl(composer3);
                    Updater.set-impl(composer15222, maybeCachedBoxMeasurePolicy2222, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer15222, currentCompositionLocalMap7222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer15222, Integer.valueOf(hashCode7222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer15222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer15222, materializeModifier7222, ComposeUiNode.Companion.getSetModifier());
                    int i28222 = (i27222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScope boxScope2222 = BoxScopeInstance.INSTANCE;
                    int i29222 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                    IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), ColorKt.getGold(), composer3, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer3, 6);
                    ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier7222 = Modifier.Companion;
                    MeasurePolicy columnMeasurePolicy3222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode8222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap8222 = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier8222 = ComposedModifierKt.materializeModifier(composer3, modifier7222);
                    Function0 constructor8222 = ComposeUiNode.Companion.getConstructor();
                    int i30222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                    }
                    Composer composer16222 = Updater.constructor-impl(composer3);
                    Updater.set-impl(composer16222, columnMeasurePolicy3222, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer16222, currentCompositionLocalMap8222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer16222, Integer.valueOf(hashCode8222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer16222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer16222, materializeModifier8222, ComposeUiNode.Companion.getSetModifier());
                    int i31222 = (i30222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScope columnScope3222 = ColumnScopeInstance.INSTANCE;
                    int i32222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -1541055177, "C:RouteScreen.kt#cef2n4");
                    if (RouteScreen$lambda$0(state2).isResolving()) {
                    }
                    if (StringsKt.isBlank(str15)) {
                    }
                    composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), composer3, 6);
                    if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                    }
                    composer3.endReplaceGroup();
                    routing = RouteScreen$lambda$0(state2).getRouting();
                    Triple[] tripleArr222 = new Triple[3];
                    if (routing) {
                    }
                    tripleArr222[0] = new Triple(distanceText, "", "Mesafe");
                    if (routing) {
                    }
                    tripleArr222[1] = new Triple(durationText, "", "Süre");
                    if (routing) {
                    }
                    tripleArr222[2] = new Triple(etaText, "", "Varış");
                    List listOf322 = CollectionsKt.listOf(tripleArr222);
                    Arrangement.Horizontal horizontal3222 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
                    composer5 = composer3;
                    boolean z18222 = false;
                    ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str8);
                    Modifier modifier11222 = Modifier.Companion;
                    MeasurePolicy rowMeasurePolicy5222 = RowKt.rowMeasurePolicy(horizontal3222, Alignment.Companion.getTop(), composer5, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode12222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                    CompositionLocalMap currentCompositionLocalMap12222 = composer5.getCurrentCompositionLocalMap();
                    Modifier materializeModifier12222 = ComposedModifierKt.materializeModifier(composer5, modifier11222);
                    Function0 constructor12222 = ComposeUiNode.Companion.getConstructor();
                    int i44222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer5, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer5.getApplier() instanceof Applier)) {
                    }
                    composer5.startReusableNode();
                    if (composer5.getInserting()) {
                    }
                    Composer composer27222 = Updater.constructor-impl(composer5);
                    MeasurePolicy measurePolicy722 = rowMeasurePolicy5222;
                    Updater.set-impl(composer27222, measurePolicy722, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer27222, currentCompositionLocalMap12222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer27222, Integer.valueOf(hashCode12222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer27222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer27222, materializeModifier12222, ComposeUiNode.Companion.getSetModifier());
                    int i45222 = (i44222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, str7);
                    int i46222 = ((48 >> 6) & 112) | 6;
                    RowScope rowScope5222 = RowScopeInstance.INSTANCE;
                    Composer composer28222 = composer5;
                    ComposerKt.sourceInformationMarkerStart(composer28222, 213090107, "C:RouteScreen.kt#cef2n4");
                    composer28222.startReplaceGroup(1807989502);
                    ComposerKt.sourceInformation(composer28222, "*540@28620L859");
                    List<Triple> list5222 = listOf322;
                    boolean z19222 = false;
                    while (r39.hasNext()) {
                    }
                    Composer composer33222 = composer28222;
                    composer33222.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer33222);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    composer5.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    ComposerKt.sourceInformationMarkerEnd(composer5);
                    SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), composer3, 6);
                    collectAsState = SnapshotStateKt.collectAsState(dashConfig2.getRoutingSource(), (CoroutineContext) null, composer3, 0, 1);
                    State collectAsState5222 = SnapshotStateKt.collectAsState(dashConfig2.getMapTheme(), (CoroutineContext) null, composer3, 0, 1);
                    ComposerKt.sourceInformationMarkerStart(composer3, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                    Composer composer34222 = composer3;
                    rememberedValue5 = composer34222.rememberedValue();
                    if (rememberedValue5 != Composer.Companion.getEmpty()) {
                    }
                    boolean booleanValue222 = ((Boolean) rememberedValue5).booleanValue();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                    }
                    ChoiceHeader("Rota sağlayıcısı", str12, composer3, 6);
                    List available222 = RoutingSource.Companion.getAvailable();
                    ArrayList arrayList3222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available222, 10));
                    it = available222.iterator();
                    while (it.hasNext()) {
                    }
                    ArrayList arrayList4222 = arrayList3222;
                    String label222 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                    ComposerKt.sourceInformationMarkerStart(composer3, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                    changed2 = startRestartGroup.changed(collectAsState) | startRestartGroup.changedInstance(dashConfig2) | ((((i17222 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel8222)) || (i17222 & 384) == 256);
                    Composer composer35222 = composer3;
                    composer6 = composer3;
                    Object rememberedValue30222 = composer35222.rememberedValue();
                    if (changed2) {
                    }
                    RouteScreenKt$.ExternalSyntheticLambda5 externalSyntheticLambda5222 = new RouteScreenKt$.ExternalSyntheticLambda5(dashConfig2, routeViewModel8222, collectAsState);
                    composer35222.updateRememberedValue(externalSyntheticLambda5222);
                    obj2 = externalSyntheticLambda5222;
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    WayfindrComponentsKt.WayfindrSegmented(arrayList4222, label222, (Function1) obj2, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), composer6, 3072, 0);
                    ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer6, 54);
                    Modifier modifier15222 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null);
                    Arrangement.Vertical vertical2222 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
                    boolean z22222 = false;
                    ComposerKt.sourceInformationMarkerStart(composer6, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy7222 = ColumnKt.columnMeasurePolicy(vertical2222, Alignment.Companion.getStart(), composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode15222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                    CompositionLocalMap currentCompositionLocalMap15222 = composer6.getCurrentCompositionLocalMap();
                    Modifier materializeModifier15222 = ComposedModifierKt.materializeModifier(composer6, modifier15222);
                    Function0 constructor15222 = ComposeUiNode.Companion.getConstructor();
                    int i53222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer6.getApplier() instanceof Applier)) {
                    }
                    composer6.startReusableNode();
                    if (composer6.getInserting()) {
                    }
                    Composer composer36222 = Updater.constructor-impl(composer6);
                    MeasurePolicy measurePolicy3222 = columnMeasurePolicy7222;
                    Updater.set-impl(composer36222, measurePolicy3222, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer36222, currentCompositionLocalMap15222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer36222, Integer.valueOf(hashCode15222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer36222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer36222, materializeModifier15222, ComposeUiNode.Companion.getSetModifier());
                    int i54222 = (i53222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer6, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScope columnScope7222 = ColumnScopeInstance.INSTANCE;
                    int i55222 = ((54 >> 6) & 112) | 6;
                    Composer composer37222 = composer6;
                    ComposerKt.sourceInformationMarkerStart(composer37222, -1001505061, "C:RouteScreen.kt#cef2n4");
                    composer37222.startReplaceGroup(-1140683288);
                    ComposerKt.sourceInformation(composer37222, "*585@31259L1578");
                    List<List> chunked222 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                    boolean z23222 = false;
                    while (r41.hasNext()) {
                    }
                    Composer composer41222 = composer37222;
                    int i64222 = i17222;
                    Context context8222 = context3;
                    composer41222.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer41222);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    composer6.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    Arrangement.Horizontal spaceBetween2222 = Arrangement.INSTANCE.getSpaceBetween();
                    Alignment.Vertical centerVertically5222 = Alignment.Companion.getCenterVertically();
                    Modifier modifier20222 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(9), 7, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(composer6, 844473419, str8);
                    MeasurePolicy rowMeasurePolicy9222 = RowKt.rowMeasurePolicy(spaceBetween2222, centerVertically5222, composer6, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode18222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                    CompositionLocalMap currentCompositionLocalMap18222 = composer6.getCurrentCompositionLocalMap();
                    Modifier materializeModifier18222 = ComposedModifierKt.materializeModifier(composer6, modifier20222);
                    Function0 constructor18222 = ComposeUiNode.Companion.getConstructor();
                    int i65222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer6.getApplier() instanceof Applier)) {
                    }
                    composer6.startReusableNode();
                    if (composer6.getInserting()) {
                    }
                    Composer composer42222 = Updater.constructor-impl(composer6);
                    Updater.set-impl(composer42222, rowMeasurePolicy9222, ComposeUiNode.Companion.getSetMeasurePolicy());
                    Updater.set-impl(composer42222, currentCompositionLocalMap18222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer42222, Integer.valueOf(hashCode18222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer42222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer42222, materializeModifier18222, ComposeUiNode.Companion.getSetModifier());
                    int i66222 = (i65222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str7);
                    RowScope rowScope9222 = RowScopeInstance.INSTANCE;
                    int i67222 = ((438 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer6, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                    WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", (Modifier) null, composer6, 6, 2);
                    IconKt.Icon-ww6aTOc(RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer6, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    composer6.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    List listOf2222 = CollectionsKt.listOf(new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                    ComposerKt.sourceInformationMarkerStart(composer6, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                    VoiceManager voiceManager3222 = voiceManager;
                    changedInstance4 = composer6.changedInstance(voiceManager3222);
                    rememberedValue6 = composer6.rememberedValue();
                    if (!changedInstance4) {
                    }
                    RouteScreenKt$.ExternalSyntheticLambda7 externalSyntheticLambda7222 = new RouteScreenKt$.ExternalSyntheticLambda7(voiceManager3222);
                    composer6.updateRememberedValue(externalSyntheticLambda7222);
                    rememberedValue6 = externalSyntheticLambda7222;
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    WayfindrComponentsKt.WayfindrSegmented(listOf2222, str16, (Function1) rememberedValue6, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null), composer6, 3078, 0);
                    if ((destination == null ? destination.getLat() : null) != null) {
                    }
                    if (!RouteScreen$lambda$7(mutableState10)) {
                    }
                    ImageVector check222 = !RouteScreen$lambda$7(mutableState10) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                    BtnVariant btnVariant2222 = !RouteScreen$lambda$7(mutableState10) ? BtnVariant.Secondary : BtnVariant.Primary;
                    BtnSize btnSize2222 = BtnSize.Lg;
                    if (RouteScreen$lambda$7(mutableState10)) {
                    }
                    Modifier fillMaxWidth$default2222 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                    ComposerKt.sourceInformationMarkerStart(composer6, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                    changedInstance5 = composer6.changedInstance(context8222) | composer6.changedInstance(managedActivityResultLauncher4222) | composer6.changedInstance(strArr22);
                    Object rememberedValue32222 = composer6.rememberedValue();
                    if (!changedInstance5) {
                    }
                    z7 = z6;
                    mutableState5 = mutableState10;
                    RouteScreenKt$.ExternalSyntheticLambda8 externalSyntheticLambda8222 = new RouteScreenKt$.ExternalSyntheticLambda8(managedActivityResultLauncher4222, strArr22, context8222, mutableState5);
                    composer6.updateRememberedValue(externalSyntheticLambda8222);
                    rememberedValue32222 = externalSyntheticLambda8222;
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue32222, fillMaxWidth$default2222, check222, btnVariant2222, btnSize2222, z31, composer6, 196992, 0);
                    if (z7) {
                    }
                    composer6.endReplaceGroup();
                    if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
                    }
                    composer6.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    startRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    startRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                    if (RouteScreen$lambda$10(mutableState6)) {
                    }
                    startRestartGroup.endReplaceGroup();
                    RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
                    if (RouteScreen$lambda$16 != null) {
                    }
                    RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
                    if (RouteScreen$lambda$13 != null) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    routeViewModel2 = routeViewModel5;
                }
                Function2 function252 = new RouteScreen.3.1(routeViewModel3, rememberLauncherForActivityResult32, context2, (Continuation) null);
                startRestartGroup.updateRememberedValue(function252);
                rememberedValue232 = function252;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                EffectsKt.LaunchedEffect(unit10, (Function2) rememberedValue232, startRestartGroup, 6);
                Boolean valueOf2222 = Boolean.valueOf(RouteScreen$lambda$7(mutableState10));
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                changed = ((i6 & 112) == 32) | startRestartGroup.changed(state);
                Object rememberedValue24222 = startRestartGroup.rememberedValue();
                if (changed) {
                }
                Function2 function24222 = new RouteScreen.4.1(function2, mutableState10, state, (Continuation) null);
                startRestartGroup.updateRememberedValue(function24222);
                rememberedValue24222 = function24222;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                EffectsKt.LaunchedEffect(valueOf2222, (Function2) rememberedValue24222, startRestartGroup, 0);
                Modifier fillMaxSize$default3222 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Arrangement.Vertical top3222 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal start3222 = Alignment.Companion.getStart();
                MeasurePolicy columnMeasurePolicy8222 = ColumnKt.columnMeasurePolicy(top3222, start3222, startRestartGroup, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                state2 = state;
                Context context52222 = context2;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode20222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap20222 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier20222 = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxSize$default3222);
                Function0 constructor20222 = ComposeUiNode.Companion.getConstructor();
                int i72222 = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                }
                Composer composer92222 = Updater.constructor-impl(startRestartGroup);
                Updater.set-impl(composer92222, columnMeasurePolicy8222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer92222, currentCompositionLocalMap20222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer92222, Integer.valueOf(hashCode20222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer92222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer92222, materializeModifier20222, ComposeUiNode.Companion.getSetModifier());
                int i82222 = (i72222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i92222 = ((6 >> 6) & 112) | 6;
                ColumnScope columnScope8222 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                Modifier modifier23222 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.46f), ColorKt.getMapBase(), (Shape) null, 2, (Object) null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy3222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode22222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap22222 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier22222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier23222);
                Function0 constructor22222 = ComposeUiNode.Companion.getConstructor();
                int i102222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                }
                Composer composer102222 = Updater.constructor-impl(startRestartGroup);
                Updater.set-impl(composer102222, maybeCachedBoxMeasurePolicy3222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer102222, currentCompositionLocalMap22222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer102222, Integer.valueOf(hashCode22222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer102222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer102222, materializeModifier22222, ComposeUiNode.Companion.getSetModifier());
                int i112222 = (i102222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                int i122222 = ((0 >> 6) & 112) | 6;
                BoxScope boxScope3222 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                Pair currentLocation3222 = RouteScreen$lambda$0(state2).getCurrentLocation();
                if (currentLocation3222 == null) {
                }
                Pair currentLocation22222 = RouteScreen$lambda$0(state2).getCurrentLocation();
                if (currentLocation22222 == null) {
                }
                float currentBearing2222 = RouteScreen$lambda$0(state2).getCurrentBearing();
                long locationRefreshKey2222 = RouteScreen$lambda$0(state2).getLocationRefreshKey();
                if (destination == null) {
                }
                List<SharedLocation> waypoints2222 = RouteScreen$lambda$0(state2).getWaypoints();
                boolean z102222 = false;
                ArrayList arrayList5222 = new ArrayList();
                while (r101.hasNext()) {
                }
                ArrayList arrayList22222 = arrayList5222;
                Route route5222 = RouteScreen$lambda$0(state2).getRoute();
                if (route5222 == null) {
                }
                if (geometry == null) {
                }
                List list22222 = geometry;
                Route route22222 = RouteScreen$lambda$0(state2).getRoute();
                if (route22222 == null) {
                }
                if (traffic == null) {
                }
                List list32222 = traffic;
                routeAlternatives = RouteScreen$lambda$0(state2).getRouteAlternatives();
                list = routeAlternatives;
                if (!(routeAlternatives.size() <= 1)) {
                }
                if (list == null) {
                }
                int selectedRouteIndex3222 = RouteScreen$lambda$0(state2).getSelectedRouteIndex();
                if (RouteScreen$lambda$0(state2).getRoute() == null) {
                }
                Modifier fillMaxSize$default22222 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                if (((i6 & 896) ^ 384) <= 256) {
                }
                Object rememberedValue252222 = startRestartGroup.rememberedValue();
                if (z4) {
                }
                RouteScreenKt$.ExternalSyntheticLambda22 externalSyntheticLambda222222 = new RouteScreenKt$.ExternalSyntheticLambda22(routeViewModel3);
                startRestartGroup.updateRememberedValue(externalSyntheticLambda222222);
                rememberedValue252222 = externalSyntheticLambda222222;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                FreeWayfindrMapKt.WayfindrMap(d, d2, pair2, arrayList22222, list22222, list32222, list, selectedRouteIndex3222, true, fillMaxSize$default22222, z11, false, currentBearing2222, locationRefreshKey2222, false, true, (Double) null, (Double) null, (Float) null, (Float) null, false, (Function1) rememberedValue252222, startRestartGroup, 905969664, 221184, 0, 2033664);
                Alignment.Vertical centerVertically7222 = Alignment.Companion.getCenterVertically();
                Modifier modifier24222 = PaddingKt.padding-VpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope3222.align(Modifier.Companion, Alignment.Companion.getTopStart()), 0.0f, 1, (Object) null), Brush.Companion.verticalGradient-8A-3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.box-impl(Color.Companion.getTransparent-0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl(16), Dp.constructor-impl(14));
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy11222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically7222, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode32222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap32222 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier32222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier24222);
                Function0 constructor32222 = ComposeUiNode.Companion.getConstructor();
                int i132222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                int i142222 = i6;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                }
                Composer composer112222 = Updater.constructor-impl(startRestartGroup);
                Updater.set-impl(composer112222, rowMeasurePolicy11222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer112222, currentCompositionLocalMap32222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer112222, Integer.valueOf(hashCode32222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer112222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer112222, materializeModifier32222, ComposeUiNode.Companion.getSetModifier());
                int i152222 = (i132222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScope rowScope11222 = RowScopeInstance.INSTANCE;
                int i162222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                ManagedActivityResultLauncher managedActivityResultLauncher42222 = managedActivityResultLauncher;
                ManagedActivityResultLauncher managedActivityResultLauncher52222 = managedActivityResultLauncher2;
                int i172222 = i142222;
                WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), function0, BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, ((i142222 << 3) & 112) | 3072, 48);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(state2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(state2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(state2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), startRestartGroup, 0, 4);
                ImageVector recenter2222 = WayfindrIcons.INSTANCE.getRecenter();
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                if (((i172222 & 896) ^ 384) <= 256) {
                }
                rememberedValue4 = startRestartGroup.rememberedValue();
                if (!z5) {
                }
                KFunction kFunction4222 = new RouteScreen.5.1.5.1.1(routeViewModel3);
                startRestartGroup.updateRememberedValue(kFunction4222);
                rememberedValue4 = kFunction4222;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(recenter2222, (KFunction) rememberedValue4, BackgroundKt.background-bw27NRU(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(8), 0.0f, 0.0f, 0.0f, 14, (Object) null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, 3072, 48);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                }
                startRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                Modifier modifier32222 = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-VpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.background-bw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope8222, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-a9UjIt4$default(Dp.constructor-impl(22), Dp.constructor-impl(22), 0.0f, 0.0f, 12, (Object) null)), ColorKt.getBg1(), (Shape) null, 2, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18), Dp.constructor-impl(8)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(20), 7, (Object) null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy22222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode42222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap42222 = startRestartGroup.getCurrentCompositionLocalMap();
                Modifier materializeModifier42222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier32222);
                Function0 constructor42222 = ComposeUiNode.Companion.getConstructor();
                int i182222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(startRestartGroup.getApplier() instanceof Applier)) {
                }
                startRestartGroup.startReusableNode();
                if (startRestartGroup.getInserting()) {
                }
                Composer composer122222 = Updater.constructor-impl(startRestartGroup);
                Updater.set-impl(composer122222, columnMeasurePolicy22222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer122222, currentCompositionLocalMap42222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer122222, Integer.valueOf(hashCode42222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer122222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer122222, materializeModifier42222, ComposeUiNode.Companion.getSetModifier());
                int i192222 = (i182222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i202222 = ((0 >> 6) & 112) | 6;
                ColumnScope columnScope22222 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                BoxKt.Box(PaddingKt.padding-qDBjuR0$default(columnScope22222.align(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), Dp.constructor-impl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), (Shape) null, 2, (Object) null), Alignment.Companion.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), startRestartGroup, 0);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
                RouteViewModel routeViewModel82222 = routeViewModel3;
                WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(4), (Function0) null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new RouteScreenKt$.ExternalSyntheticLambda23(function12, routeViewModel82222, mutableState14, mutableState16, mutableState, mutableState18, state2, mutableState2), startRestartGroup, 54), startRestartGroup, 24966, 10);
                searchNote = RouteScreen$lambda$0(state2).getSearchNote();
                if (searchNote != null) {
                }
                WayfindrIcons.INSTANCE.getQrScan();
                BtnVariant btnVariant4222 = BtnVariant.Ghost;
                BtnSize btnSize4222 = BtnSize.Sm;
                PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(12), 7, (Object) null);
                ComposerKt.sourceInformationMarkerStart(startRestartGroup, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance3 = startRestartGroup.changedInstance(managedActivityResultLauncher52222);
                Object rememberedValue272222 = startRestartGroup.rememberedValue();
                if (changedInstance3) {
                }
                RouteScreenKt$.ExternalSyntheticLambda24 externalSyntheticLambda242222 = new RouteScreenKt$.ExternalSyntheticLambda24(managedActivityResultLauncher52222);
                startRestartGroup.updateRememberedValue(externalSyntheticLambda242222);
                obj = externalSyntheticLambda242222;
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                composer3 = startRestartGroup;
                sharedRouteName = RouteScreen$lambda$0(state2).getSharedRouteName();
                if (sharedRouteName != null) {
                }
                if (RouteScreen$lambda$0(state2).getWaypoints().isEmpty()) {
                }
                composer3.endReplaceGroup();
                if ((destination == null ? destination.getLat() : null) != null) {
                }
                composer3.startReplaceGroup(2018241319);
                ComposerKt.sourceInformation(composer3, "438@22367L1104");
                PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null);
                Dp.constructor-impl(16);
                ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-835303176$app();
                composer3.endReplaceGroup();
                WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(state2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(6), 7, (Object) null), composer3, 48, 0);
                Alignment.Vertical top22222 = Alignment.Companion.getTop();
                ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str8);
                Modifier modifier52222 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy32222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top22222, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode62222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap62222 = composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier62222 = ComposedModifierKt.materializeModifier(composer3, modifier52222);
                Function0 constructor62222 = ComposeUiNode.Companion.getConstructor();
                int i242222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                }
                Composer composer142222 = Updater.constructor-impl(composer3);
                Updater.set-impl(composer142222, rowMeasurePolicy32222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer142222, currentCompositionLocalMap62222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer142222, Integer.valueOf(hashCode62222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer142222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer142222, materializeModifier62222, ComposeUiNode.Companion.getSetModifier());
                int i252222 = (i242222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str7);
                RowScope rowScope32222 = RowScopeInstance.INSTANCE;
                int i262222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                Alignment center2222 = Alignment.Companion.getCenter();
                Modifier modifier62222 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(44)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
                ComposerKt.sourceInformationMarkerStart(composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy22222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222, false);
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode72222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap72222 = composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier72222 = ComposedModifierKt.materializeModifier(composer3, modifier62222);
                Function0 constructor72222 = ComposeUiNode.Companion.getConstructor();
                int i272222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                }
                Composer composer152222 = Updater.constructor-impl(composer3);
                Updater.set-impl(composer152222, maybeCachedBoxMeasurePolicy22222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer152222, currentCompositionLocalMap72222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer152222, Integer.valueOf(hashCode72222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer152222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer152222, materializeModifier72222, ComposeUiNode.Companion.getSetModifier());
                int i282222 = (i272222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScope boxScope22222 = BoxScopeInstance.INSTANCE;
                int i292222 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), ColorKt.getGold(), composer3, 432, 0);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer3, 6);
                ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier72222 = Modifier.Companion;
                MeasurePolicy columnMeasurePolicy32222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode82222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap82222 = composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier82222 = ComposedModifierKt.materializeModifier(composer3, modifier72222);
                Function0 constructor82222 = ComposeUiNode.Companion.getConstructor();
                int i302222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                }
                Composer composer162222 = Updater.constructor-impl(composer3);
                Updater.set-impl(composer162222, columnMeasurePolicy32222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer162222, currentCompositionLocalMap82222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer162222, Integer.valueOf(hashCode82222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer162222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer162222, materializeModifier82222, ComposeUiNode.Companion.getSetModifier());
                int i312222 = (i302222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScope columnScope32222 = ColumnScopeInstance.INSTANCE;
                int i322222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, -1541055177, "C:RouteScreen.kt#cef2n4");
                if (RouteScreen$lambda$0(state2).isResolving()) {
                }
                if (StringsKt.isBlank(str15)) {
                }
                composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), composer3, 6);
                if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
                }
                composer3.endReplaceGroup();
                routing = RouteScreen$lambda$0(state2).getRouting();
                Triple[] tripleArr2222 = new Triple[3];
                if (routing) {
                }
                tripleArr2222[0] = new Triple(distanceText, "", "Mesafe");
                if (routing) {
                }
                tripleArr2222[1] = new Triple(durationText, "", "Süre");
                if (routing) {
                }
                tripleArr2222[2] = new Triple(etaText, "", "Varış");
                List listOf3222 = CollectionsKt.listOf(tripleArr2222);
                Arrangement.Horizontal horizontal32222 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
                composer5 = composer3;
                boolean z182222 = false;
                ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str8);
                Modifier modifier112222 = Modifier.Companion;
                MeasurePolicy rowMeasurePolicy52222 = RowKt.rowMeasurePolicy(horizontal32222, Alignment.Companion.getTop(), composer5, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode122222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
                CompositionLocalMap currentCompositionLocalMap122222 = composer5.getCurrentCompositionLocalMap();
                Modifier materializeModifier122222 = ComposedModifierKt.materializeModifier(composer5, modifier112222);
                Function0 constructor122222 = ComposeUiNode.Companion.getConstructor();
                int i442222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer5, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer5.getApplier() instanceof Applier)) {
                }
                composer5.startReusableNode();
                if (composer5.getInserting()) {
                }
                Composer composer272222 = Updater.constructor-impl(composer5);
                MeasurePolicy measurePolicy7222 = rowMeasurePolicy52222;
                Updater.set-impl(composer272222, measurePolicy7222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer272222, currentCompositionLocalMap122222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer272222, Integer.valueOf(hashCode122222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer272222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer272222, materializeModifier122222, ComposeUiNode.Companion.getSetModifier());
                int i452222 = (i442222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, str7);
                int i462222 = ((48 >> 6) & 112) | 6;
                RowScope rowScope52222 = RowScopeInstance.INSTANCE;
                Composer composer282222 = composer5;
                ComposerKt.sourceInformationMarkerStart(composer282222, 213090107, "C:RouteScreen.kt#cef2n4");
                composer282222.startReplaceGroup(1807989502);
                ComposerKt.sourceInformation(composer282222, "*540@28620L859");
                List<Triple> list52222 = listOf3222;
                boolean z192222 = false;
                while (r39.hasNext()) {
                }
                Composer composer332222 = composer282222;
                composer332222.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer332222);
                ComposerKt.sourceInformationMarkerEnd(composer5);
                composer5.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd(composer5);
                SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), composer3, 6);
                collectAsState = SnapshotStateKt.collectAsState(dashConfig2.getRoutingSource(), (CoroutineContext) null, composer3, 0, 1);
                State collectAsState52222 = SnapshotStateKt.collectAsState(dashConfig2.getMapTheme(), (CoroutineContext) null, composer3, 0, 1);
                ComposerKt.sourceInformationMarkerStart(composer3, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                Composer composer342222 = composer3;
                rememberedValue5 = composer342222.rememberedValue();
                if (rememberedValue5 != Composer.Companion.getEmpty()) {
                }
                boolean booleanValue2222 = ((Boolean) rememberedValue5).booleanValue();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                }
                ChoiceHeader("Rota sağlayıcısı", str12, composer3, 6);
                List available2222 = RoutingSource.Companion.getAvailable();
                ArrayList arrayList32222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available2222, 10));
                it = available2222.iterator();
                while (it.hasNext()) {
                }
                ArrayList arrayList42222 = arrayList32222;
                String label2222 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                ComposerKt.sourceInformationMarkerStart(composer3, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                changed2 = startRestartGroup.changed(collectAsState) | startRestartGroup.changedInstance(dashConfig2) | ((((i172222 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel82222)) || (i172222 & 384) == 256);
                Composer composer352222 = composer3;
                composer6 = composer3;
                Object rememberedValue302222 = composer352222.rememberedValue();
                if (changed2) {
                }
                RouteScreenKt$.ExternalSyntheticLambda5 externalSyntheticLambda52222 = new RouteScreenKt$.ExternalSyntheticLambda5(dashConfig2, routeViewModel82222, collectAsState);
                composer352222.updateRememberedValue(externalSyntheticLambda52222);
                obj2 = externalSyntheticLambda52222;
                ComposerKt.sourceInformationMarkerEnd(composer6);
                WayfindrComponentsKt.WayfindrSegmented(arrayList42222, label2222, (Function1) obj2, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), composer6, 3072, 0);
                ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer6, 54);
                Modifier modifier152222 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null);
                Arrangement.Vertical vertical22222 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
                boolean z222222 = false;
                ComposerKt.sourceInformationMarkerStart(composer6, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy72222 = ColumnKt.columnMeasurePolicy(vertical22222, Alignment.Companion.getStart(), composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode152222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                CompositionLocalMap currentCompositionLocalMap152222 = composer6.getCurrentCompositionLocalMap();
                Modifier materializeModifier152222 = ComposedModifierKt.materializeModifier(composer6, modifier152222);
                Function0 constructor152222 = ComposeUiNode.Companion.getConstructor();
                int i532222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer6.getApplier() instanceof Applier)) {
                }
                composer6.startReusableNode();
                if (composer6.getInserting()) {
                }
                Composer composer362222 = Updater.constructor-impl(composer6);
                MeasurePolicy measurePolicy32222 = columnMeasurePolicy72222;
                Updater.set-impl(composer362222, measurePolicy32222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer362222, currentCompositionLocalMap152222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer362222, Integer.valueOf(hashCode152222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer362222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer362222, materializeModifier152222, ComposeUiNode.Companion.getSetModifier());
                int i542222 = (i532222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer6, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScope columnScope72222 = ColumnScopeInstance.INSTANCE;
                int i552222 = ((54 >> 6) & 112) | 6;
                Composer composer372222 = composer6;
                ComposerKt.sourceInformationMarkerStart(composer372222, -1001505061, "C:RouteScreen.kt#cef2n4");
                composer372222.startReplaceGroup(-1140683288);
                ComposerKt.sourceInformation(composer372222, "*585@31259L1578");
                List<List> chunked2222 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                boolean z232222 = false;
                while (r41.hasNext()) {
                }
                Composer composer412222 = composer372222;
                int i642222 = i172222;
                Context context82222 = context3;
                composer412222.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer412222);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                composer6.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                Arrangement.Horizontal spaceBetween22222 = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically52222 = Alignment.Companion.getCenterVertically();
                Modifier modifier202222 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(9), 7, (Object) null);
                ComposerKt.sourceInformationMarkerStart(composer6, 844473419, str8);
                MeasurePolicy rowMeasurePolicy92222 = RowKt.rowMeasurePolicy(spaceBetween22222, centerVertically52222, composer6, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode182222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
                CompositionLocalMap currentCompositionLocalMap182222 = composer6.getCurrentCompositionLocalMap();
                Modifier materializeModifier182222 = ComposedModifierKt.materializeModifier(composer6, modifier202222);
                Function0 constructor182222 = ComposeUiNode.Companion.getConstructor();
                int i652222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer6.getApplier() instanceof Applier)) {
                }
                composer6.startReusableNode();
                if (composer6.getInserting()) {
                }
                Composer composer422222 = Updater.constructor-impl(composer6);
                Updater.set-impl(composer422222, rowMeasurePolicy92222, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer422222, currentCompositionLocalMap182222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer422222, Integer.valueOf(hashCode182222), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer422222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer422222, materializeModifier182222, ComposeUiNode.Companion.getSetModifier());
                int i662222 = (i652222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str7);
                RowScope rowScope92222 = RowScopeInstance.INSTANCE;
                int i672222 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer6, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", (Modifier) null, composer6, 6, 2);
                IconKt.Icon-ww6aTOc(RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer6, 432, 0);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                composer6.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer6);
                List listOf22222 = CollectionsKt.listOf(new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                ComposerKt.sourceInformationMarkerStart(composer6, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                VoiceManager voiceManager32222 = voiceManager;
                changedInstance4 = composer6.changedInstance(voiceManager32222);
                rememberedValue6 = composer6.rememberedValue();
                if (!changedInstance4) {
                }
                RouteScreenKt$.ExternalSyntheticLambda7 externalSyntheticLambda72222 = new RouteScreenKt$.ExternalSyntheticLambda7(voiceManager32222);
                composer6.updateRememberedValue(externalSyntheticLambda72222);
                rememberedValue6 = externalSyntheticLambda72222;
                ComposerKt.sourceInformationMarkerEnd(composer6);
                WayfindrComponentsKt.WayfindrSegmented(listOf22222, str16, (Function1) rememberedValue6, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null), composer6, 3078, 0);
                if ((destination == null ? destination.getLat() : null) != null) {
                }
                if (!RouteScreen$lambda$7(mutableState10)) {
                }
                ImageVector check2222 = !RouteScreen$lambda$7(mutableState10) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                BtnVariant btnVariant22222 = !RouteScreen$lambda$7(mutableState10) ? BtnVariant.Secondary : BtnVariant.Primary;
                BtnSize btnSize22222 = BtnSize.Lg;
                if (RouteScreen$lambda$7(mutableState10)) {
                }
                Modifier fillMaxWidth$default22222 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
                ComposerKt.sourceInformationMarkerStart(composer6, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance5 = composer6.changedInstance(context82222) | composer6.changedInstance(managedActivityResultLauncher42222) | composer6.changedInstance(strArr22);
                Object rememberedValue322222 = composer6.rememberedValue();
                if (!changedInstance5) {
                }
                z7 = z6;
                mutableState5 = mutableState10;
                RouteScreenKt$.ExternalSyntheticLambda8 externalSyntheticLambda82222 = new RouteScreenKt$.ExternalSyntheticLambda8(managedActivityResultLauncher42222, strArr22, context82222, mutableState5);
                composer6.updateRememberedValue(externalSyntheticLambda82222);
                rememberedValue322222 = externalSyntheticLambda82222;
                ComposerKt.sourceInformationMarkerEnd(composer6);
                WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue322222, fillMaxWidth$default22222, check2222, btnVariant22222, btnSize22222, z31, composer6, 196992, 0);
                if (z7) {
                }
                composer6.endReplaceGroup();
                if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
                }
                composer6.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                startRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
                if (RouteScreen$lambda$10(mutableState6)) {
                }
                startRestartGroup.endReplaceGroup();
                RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
                if (RouteScreen$lambda$16 != null) {
                }
                RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
                if (RouteScreen$lambda$13 != null) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                routeViewModel2 = routeViewModel5;
            }
            RouteScreenKt$RouteScreen$1$1 routeScreenKt$RouteScreen$1$1 = new RouteScreenKt$RouteScreen$1$1(name2, str14, collectAsState2, mutableState19, mutableState16, mutableState14, null);
            str5 = str14;
            RouteScreenKt$RouteScreen$1$1 routeScreenKt$RouteScreen$1$12 = routeScreenKt$RouteScreen$1$1;
            startRestartGroup.updateRememberedValue(routeScreenKt$RouteScreen$1$12);
            rememberedValue19 = routeScreenKt$RouteScreen$1$12;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect(str5, (Function2) rememberedValue19, startRestartGroup, 0);
            String RouteScreen$lambda$192 = RouteScreen$lambda$19(mutableState14);
            Boolean valueOf4 = Boolean.valueOf(RouteScreen$lambda$34(mutableState19));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131807658, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance = startRestartGroup.changedInstance(context4) | startRestartGroup.changed(collectAsState2);
            Object rememberedValue202 = startRestartGroup.rememberedValue();
            if (changedInstance) {
            }
            mutableState = mutableState19;
            mutableState2 = mutableState17;
            state = collectAsState2;
            str6 = str5;
            context2 = context;
            rememberedValue202 = new RouteScreen.2.1(context, mutableState14, mutableState16, mutableState2, mutableState18, mutableState, collectAsState2, (Continuation) null);
            startRestartGroup.updateRememberedValue(rememberedValue202);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect(RouteScreen$lambda$192, valueOf4, (Function2) rememberedValue202, startRestartGroup, 0);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131771185, "CC(remember):RouteScreen.kt#9igjgp");
            if (((i6 & 896) ^ 384) <= 256) {
            }
            rememberedValue = startRestartGroup.rememberedValue();
            if (z) {
            }
            MutableState mutableState2022 = mutableState;
            RouteViewModel routeViewModel722 = routeViewModel6;
            RouteScreenKt$.ExternalSyntheticLambda4 externalSyntheticLambda422 = new RouteScreenKt$.ExternalSyntheticLambda4(routeViewModel722, mutableState2022, mutableState16, mutableState2, mutableState18, mutableState14, mutableState15);
            routeViewModel3 = routeViewModel722;
            mutableState = mutableState2022;
            mutableState16 = mutableState16;
            startRestartGroup.updateRememberedValue(externalSyntheticLambda422);
            rememberedValue = externalSyntheticLambda422;
            Function1 function122 = (Function1) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131750886, "CC(remember):RouteScreen.kt#9igjgp");
            rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
            }
            String[] strArr222 = (String[]) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ActivityResultContract requestMultiplePermissions22 = new ActivityResultContracts.RequestMultiplePermissions();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131727730, "CC(remember):RouteScreen.kt#9igjgp");
            rememberedValue3 = startRestartGroup.rememberedValue();
            if (rememberedValue3 == Composer.Companion.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ManagedActivityResultLauncher rememberLauncherForActivityResult42 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions22, (Function1) rememberedValue3, startRestartGroup, 48);
            ActivityResultContract pickVisualMedia22 = new ActivityResultContracts.PickVisualMedia();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131722424, "CC(remember):RouteScreen.kt#9igjgp");
            if (((i6 & 896) ^ 384) <= 256) {
            }
            Object rememberedValue2122 = startRestartGroup.rememberedValue();
            if (z2) {
            }
            RouteScreenKt$.ExternalSyntheticLambda20 externalSyntheticLambda2022 = new RouteScreenKt$.ExternalSyntheticLambda20(routeViewModel3);
            startRestartGroup.updateRememberedValue(externalSyntheticLambda2022);
            rememberedValue2122 = externalSyntheticLambda2022;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ManagedActivityResultLauncher rememberLauncherForActivityResult222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(pickVisualMedia22, (Function1) rememberedValue2122, startRestartGroup, 0);
            ActivityResultContract requestPermission22 = new ActivityResultContracts.RequestPermission();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131716088, "CC(remember):RouteScreen.kt#9igjgp");
            if (((i6 & 896) ^ 384) <= 256) {
            }
            Object rememberedValue2222 = startRestartGroup.rememberedValue();
            if (z3) {
            }
            RouteScreenKt$.ExternalSyntheticLambda21 externalSyntheticLambda2122 = new RouteScreenKt$.ExternalSyntheticLambda21(routeViewModel3);
            startRestartGroup.updateRememberedValue(externalSyntheticLambda2122);
            rememberedValue2222 = externalSyntheticLambda2122;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ManagedActivityResultLauncher rememberLauncherForActivityResult322 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission22, (Function1) rememberedValue2222, startRestartGroup, 0);
            Unit unit102 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131713748, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance2 = startRestartGroup.changedInstance(context2) | ((((i6 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel3)) || (i6 & 384) == 256) | startRestartGroup.changedInstance(rememberLauncherForActivityResult322);
            Object rememberedValue2322 = startRestartGroup.rememberedValue();
            if (changedInstance2) {
            }
            Function2 function2522 = new RouteScreen.3.1(routeViewModel3, rememberLauncherForActivityResult322, context2, (Continuation) null);
            startRestartGroup.updateRememberedValue(function2522);
            rememberedValue2322 = function2522;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect(unit102, (Function2) rememberedValue2322, startRestartGroup, 6);
            Boolean valueOf22222 = Boolean.valueOf(RouteScreen$lambda$7(mutableState10));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
            changed = ((i6 & 112) == 32) | startRestartGroup.changed(state);
            Object rememberedValue242222 = startRestartGroup.rememberedValue();
            if (changed) {
            }
            Function2 function242222 = new RouteScreen.4.1(function2, mutableState10, state, (Continuation) null);
            startRestartGroup.updateRememberedValue(function242222);
            rememberedValue242222 = function242222;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect(valueOf22222, (Function2) rememberedValue242222, startRestartGroup, 0);
            Modifier fillMaxSize$default32222 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Arrangement.Vertical top32222 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal start32222 = Alignment.Companion.getStart();
            MeasurePolicy columnMeasurePolicy82222 = ColumnKt.columnMeasurePolicy(top32222, start32222, startRestartGroup, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            state2 = state;
            Context context522222 = context2;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode202222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap202222 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier202222 = ComposedModifierKt.materializeModifier(startRestartGroup, fillMaxSize$default32222);
            Function0 constructor202222 = ComposeUiNode.Companion.getConstructor();
            int i722222 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
            }
            Composer composer922222 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer922222, columnMeasurePolicy82222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer922222, currentCompositionLocalMap202222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer922222, Integer.valueOf(hashCode202222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer922222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer922222, materializeModifier202222, ComposeUiNode.Companion.getSetModifier());
            int i822222 = (i722222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i922222 = ((6 >> 6) & 112) | 6;
            ColumnScope columnScope82222 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
            Modifier modifier232222 = BackgroundKt.background-bw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.46f), ColorKt.getMapBase(), (Shape) null, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy32222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap222222 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier222222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier232222);
            Function0 constructor222222 = ComposeUiNode.Companion.getConstructor();
            int i1022222 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
            }
            Composer composer1022222 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer1022222, maybeCachedBoxMeasurePolicy32222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer1022222, currentCompositionLocalMap222222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer1022222, Integer.valueOf(hashCode222222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer1022222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer1022222, materializeModifier222222, ComposeUiNode.Companion.getSetModifier());
            int i1122222 = (i1022222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i1222222 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope32222 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
            Pair currentLocation32222 = RouteScreen$lambda$0(state2).getCurrentLocation();
            if (currentLocation32222 == null) {
            }
            Pair currentLocation222222 = RouteScreen$lambda$0(state2).getCurrentLocation();
            if (currentLocation222222 == null) {
            }
            float currentBearing22222 = RouteScreen$lambda$0(state2).getCurrentBearing();
            long locationRefreshKey22222 = RouteScreen$lambda$0(state2).getLocationRefreshKey();
            if (destination == null) {
            }
            List<SharedLocation> waypoints22222 = RouteScreen$lambda$0(state2).getWaypoints();
            boolean z1022222 = false;
            ArrayList arrayList52222 = new ArrayList();
            while (r101.hasNext()) {
            }
            ArrayList arrayList222222 = arrayList52222;
            Route route52222 = RouteScreen$lambda$0(state2).getRoute();
            if (route52222 == null) {
            }
            if (geometry == null) {
            }
            List list222222 = geometry;
            Route route222222 = RouteScreen$lambda$0(state2).getRoute();
            if (route222222 == null) {
            }
            if (traffic == null) {
            }
            List list322222 = traffic;
            routeAlternatives = RouteScreen$lambda$0(state2).getRouteAlternatives();
            list = routeAlternatives;
            if (!(routeAlternatives.size() <= 1)) {
            }
            if (list == null) {
            }
            int selectedRouteIndex32222 = RouteScreen$lambda$0(state2).getSelectedRouteIndex();
            if (RouteScreen$lambda$0(state2).getRoute() == null) {
            }
            Modifier fillMaxSize$default222222 = SizeKt.fillMaxSize$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
            if (((i6 & 896) ^ 384) <= 256) {
            }
            Object rememberedValue2522222 = startRestartGroup.rememberedValue();
            if (z4) {
            }
            RouteScreenKt$.ExternalSyntheticLambda22 externalSyntheticLambda2222222 = new RouteScreenKt$.ExternalSyntheticLambda22(routeViewModel3);
            startRestartGroup.updateRememberedValue(externalSyntheticLambda2222222);
            rememberedValue2522222 = externalSyntheticLambda2222222;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            FreeWayfindrMapKt.WayfindrMap(d, d2, pair2, arrayList222222, list222222, list322222, list, selectedRouteIndex32222, true, fillMaxSize$default222222, z11, false, currentBearing22222, locationRefreshKey22222, false, true, (Double) null, (Double) null, (Float) null, (Float) null, false, (Function1) rememberedValue2522222, startRestartGroup, 905969664, 221184, 0, 2033664);
            Alignment.Vertical centerVertically72222 = Alignment.Companion.getCenterVertically();
            Modifier modifier242222 = PaddingKt.padding-VpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope32222.align(Modifier.Companion, Alignment.Companion.getTopStart()), 0.0f, 1, (Object) null), Brush.Companion.verticalGradient-8A-3gB4$default(Brush.Companion, CollectionsKt.listOf(new Color[]{Color.box-impl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.box-impl(Color.Companion.getTransparent-0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), (Shape) null, 0.0f, 6, (Object) null), Dp.constructor-impl(16), Dp.constructor-impl(14));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy112222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically72222, startRestartGroup, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode322222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap322222 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier322222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier242222);
            Function0 constructor322222 = ComposeUiNode.Companion.getConstructor();
            int i1322222 = ((((384 << 3) & 112) << 6) & 896) | 6;
            int i1422222 = i6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
            }
            Composer composer1122222 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer1122222, rowMeasurePolicy112222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer1122222, currentCompositionLocalMap322222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer1122222, Integer.valueOf(hashCode322222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer1122222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer1122222, materializeModifier322222, ComposeUiNode.Companion.getSetModifier());
            int i1522222 = (i1322222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScope rowScope112222 = RowScopeInstance.INSTANCE;
            int i1622222 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
            ManagedActivityResultLauncher managedActivityResultLauncher422222 = managedActivityResultLauncher;
            ManagedActivityResultLauncher managedActivityResultLauncher522222 = managedActivityResultLauncher2;
            int i1722222 = i1422222;
            WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), function0, BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, ((i1422222 << 3) & 112) | 3072, 48);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
            WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(state2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(state2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(state2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.background-bw27NRU(Modifier.Companion, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), startRestartGroup, 0, 4);
            ImageVector recenter22222 = WayfindrIcons.INSTANCE.getRecenter();
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
            if (((i1722222 & 896) ^ 384) <= 256) {
            }
            rememberedValue4 = startRestartGroup.rememberedValue();
            if (!z5) {
            }
            KFunction kFunction42222 = new RouteScreen.5.1.5.1.1(routeViewModel3);
            startRestartGroup.updateRememberedValue(kFunction42222);
            rememberedValue4 = kFunction42222;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            WayfindrComponentsKt.WayfindrIconBtn-N59uMVk(recenter22222, (KFunction) rememberedValue4, BackgroundKt.background-bw27NRU(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(8), 0.0f, 0.0f, 0.0f, 14, (Object) null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.constructor-impl(40), false, (Color) null, startRestartGroup, 3072, 48);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
            }
            startRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier modifier322222 = PaddingKt.padding-qDBjuR0$default(PaddingKt.padding-VpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.background-bw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope82222, SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 1.0f, false, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-a9UjIt4$default(Dp.constructor-impl(22), Dp.constructor-impl(22), 0.0f, 0.0f, 12, (Object) null)), ColorKt.getBg1(), (Shape) null, 2, (Object) null), ScrollKt.rememberScrollState(0, startRestartGroup, 0, 1), false, (FlingBehavior) null, false, 14, (Object) null), Dp.constructor-impl(18), Dp.constructor-impl(8)), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(20), 7, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), startRestartGroup, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode422222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(startRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap422222 = startRestartGroup.getCurrentCompositionLocalMap();
            Modifier materializeModifier422222 = ComposedModifierKt.materializeModifier(startRestartGroup, modifier322222);
            Function0 constructor422222 = ComposeUiNode.Companion.getConstructor();
            int i1822222 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(startRestartGroup.getApplier() instanceof Applier)) {
            }
            startRestartGroup.startReusableNode();
            if (startRestartGroup.getInserting()) {
            }
            Composer composer1222222 = Updater.constructor-impl(startRestartGroup);
            Updater.set-impl(composer1222222, columnMeasurePolicy222222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer1222222, currentCompositionLocalMap422222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer1222222, Integer.valueOf(hashCode422222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer1222222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer1222222, materializeModifier422222, ComposeUiNode.Companion.getSetModifier());
            int i1922222 = (i1822222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i2022222 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope222222 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
            BoxKt.Box(PaddingKt.padding-qDBjuR0$default(columnScope222222.align(BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), Dp.constructor-impl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), (Shape) null, 2, (Object) null), Alignment.Companion.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), startRestartGroup, 0);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), startRestartGroup, 6);
            RouteViewModel routeViewModel822222 = routeViewModel3;
            WayfindrComponentsKt.WayfindrCard-TN_CM5M(PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), false, Dp.constructor-impl(4), (Function0) null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new RouteScreenKt$.ExternalSyntheticLambda23(function122, routeViewModel822222, mutableState14, mutableState16, mutableState, mutableState18, state2, mutableState2), startRestartGroup, 54), startRestartGroup, 24966, 10);
            searchNote = RouteScreen$lambda$0(state2).getSearchNote();
            if (searchNote != null) {
            }
            WayfindrIcons.INSTANCE.getQrScan();
            BtnVariant btnVariant42222 = BtnVariant.Ghost;
            BtnSize btnSize42222 = BtnSize.Sm;
            PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(12), 7, (Object) null);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance3 = startRestartGroup.changedInstance(managedActivityResultLauncher522222);
            Object rememberedValue2722222 = startRestartGroup.rememberedValue();
            if (changedInstance3) {
            }
            RouteScreenKt$.ExternalSyntheticLambda24 externalSyntheticLambda2422222 = new RouteScreenKt$.ExternalSyntheticLambda24(managedActivityResultLauncher522222);
            startRestartGroup.updateRememberedValue(externalSyntheticLambda2422222);
            obj = externalSyntheticLambda2422222;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer3 = startRestartGroup;
            sharedRouteName = RouteScreen$lambda$0(state2).getSharedRouteName();
            if (sharedRouteName != null) {
            }
            if (RouteScreen$lambda$0(state2).getWaypoints().isEmpty()) {
            }
            composer3.endReplaceGroup();
            if ((destination == null ? destination.getLat() : null) != null) {
            }
            composer3.startReplaceGroup(2018241319);
            ComposerKt.sourceInformation(composer3, "438@22367L1104");
            PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null);
            Dp.constructor-impl(16);
            ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-835303176$app();
            composer3.endReplaceGroup();
            WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(state2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, 0.0f, Dp.constructor-impl(6), 7, (Object) null), composer3, 48, 0);
            Alignment.Vertical top222222 = Alignment.Companion.getTop();
            ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str8);
            Modifier modifier522222 = Modifier.Companion;
            MeasurePolicy rowMeasurePolicy322222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top222222, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode622222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
            CompositionLocalMap currentCompositionLocalMap622222 = composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier622222 = ComposedModifierKt.materializeModifier(composer3, modifier522222);
            Function0 constructor622222 = ComposeUiNode.Companion.getConstructor();
            int i2422222 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer3.getApplier() instanceof Applier)) {
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
            }
            Composer composer1422222 = Updater.constructor-impl(composer3);
            Updater.set-impl(composer1422222, rowMeasurePolicy322222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer1422222, currentCompositionLocalMap622222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer1422222, Integer.valueOf(hashCode622222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer1422222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer1422222, materializeModifier622222, ComposeUiNode.Companion.getSetModifier());
            int i2522222 = (i2422222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str7);
            RowScope rowScope322222 = RowScopeInstance.INSTANCE;
            int i2622222 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
            Alignment center22222 = Alignment.Companion.getCenter();
            Modifier modifier622222 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(44)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy222222 = BoxKt.maybeCachedBoxMeasurePolicy(center22222, false);
            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode722222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
            CompositionLocalMap currentCompositionLocalMap722222 = composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier722222 = ComposedModifierKt.materializeModifier(composer3, modifier622222);
            Function0 constructor722222 = ComposeUiNode.Companion.getConstructor();
            int i2722222 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer3.getApplier() instanceof Applier)) {
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
            }
            Composer composer1522222 = Updater.constructor-impl(composer3);
            Updater.set-impl(composer1522222, maybeCachedBoxMeasurePolicy222222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer1522222, currentCompositionLocalMap722222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer1522222, Integer.valueOf(hashCode722222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer1522222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer1522222, materializeModifier722222, ComposeUiNode.Companion.getSetModifier());
            int i2822222 = (i2722222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScope boxScope222222 = BoxScopeInstance.INSTANCE;
            int i2922222 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
            IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(22)), ColorKt.getGold(), composer3, 432, 0);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer3, 6);
            ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier722222 = Modifier.Companion;
            MeasurePolicy columnMeasurePolicy322222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode822222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
            CompositionLocalMap currentCompositionLocalMap822222 = composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier822222 = ComposedModifierKt.materializeModifier(composer3, modifier722222);
            Function0 constructor822222 = ComposeUiNode.Companion.getConstructor();
            int i3022222 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer3.getApplier() instanceof Applier)) {
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
            }
            Composer composer1622222 = Updater.constructor-impl(composer3);
            Updater.set-impl(composer1622222, columnMeasurePolicy322222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer1622222, currentCompositionLocalMap822222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer1622222, Integer.valueOf(hashCode822222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer1622222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer1622222, materializeModifier822222, ComposeUiNode.Companion.getSetModifier());
            int i3122222 = (i3022222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope322222 = ColumnScopeInstance.INSTANCE;
            int i3222222 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, -1541055177, "C:RouteScreen.kt#cef2n4");
            if (RouteScreen$lambda$0(state2).isResolving()) {
            }
            if (StringsKt.isBlank(str15)) {
            }
            composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), composer3, 6);
            if (RouteScreen$lambda$0(state2).getRouteAlternatives().size() <= 1) {
            }
            composer3.endReplaceGroup();
            routing = RouteScreen$lambda$0(state2).getRouting();
            Triple[] tripleArr22222 = new Triple[3];
            if (routing) {
            }
            tripleArr22222[0] = new Triple(distanceText, "", "Mesafe");
            if (routing) {
            }
            tripleArr22222[1] = new Triple(durationText, "", "Süre");
            if (routing) {
            }
            tripleArr22222[2] = new Triple(etaText, "", "Varış");
            List listOf32222 = CollectionsKt.listOf(tripleArr22222);
            Arrangement.Horizontal horizontal322222 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10));
            composer5 = composer3;
            boolean z1822222 = false;
            ComposerKt.sourceInformationMarkerStart(composer5, 844473419, str8);
            Modifier modifier1122222 = Modifier.Companion;
            MeasurePolicy rowMeasurePolicy522222 = RowKt.rowMeasurePolicy(horizontal322222, Alignment.Companion.getTop(), composer5, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer5, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode1222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer5, 0));
            CompositionLocalMap currentCompositionLocalMap1222222 = composer5.getCurrentCompositionLocalMap();
            Modifier materializeModifier1222222 = ComposedModifierKt.materializeModifier(composer5, modifier1122222);
            Function0 constructor1222222 = ComposeUiNode.Companion.getConstructor();
            int i4422222 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer5, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer5.getApplier() instanceof Applier)) {
            }
            composer5.startReusableNode();
            if (composer5.getInserting()) {
            }
            Composer composer2722222 = Updater.constructor-impl(composer5);
            MeasurePolicy measurePolicy72222 = rowMeasurePolicy522222;
            Updater.set-impl(composer2722222, measurePolicy72222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2722222, currentCompositionLocalMap1222222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2722222, Integer.valueOf(hashCode1222222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2722222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2722222, materializeModifier1222222, ComposeUiNode.Companion.getSetModifier());
            int i4522222 = (i4422222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer5, 1456264949, str7);
            int i4622222 = ((48 >> 6) & 112) | 6;
            RowScope rowScope522222 = RowScopeInstance.INSTANCE;
            Composer composer2822222 = composer5;
            ComposerKt.sourceInformationMarkerStart(composer2822222, 213090107, "C:RouteScreen.kt#cef2n4");
            composer2822222.startReplaceGroup(1807989502);
            ComposerKt.sourceInformation(composer2822222, "*540@28620L859");
            List<Triple> list522222 = listOf32222;
            boolean z1922222 = false;
            while (r39.hasNext()) {
            }
            Composer composer3322222 = composer2822222;
            composer3322222.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer3322222);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            composer5.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            ComposerKt.sourceInformationMarkerEnd(composer5);
            SpacerKt.Spacer(SizeKt.height-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), composer3, 6);
            collectAsState = SnapshotStateKt.collectAsState(dashConfig2.getRoutingSource(), (CoroutineContext) null, composer3, 0, 1);
            State collectAsState522222 = SnapshotStateKt.collectAsState(dashConfig2.getMapTheme(), (CoroutineContext) null, composer3, 0, 1);
            ComposerKt.sourceInformationMarkerStart(composer3, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
            Composer composer3422222 = composer3;
            rememberedValue5 = composer3422222.rememberedValue();
            if (rememberedValue5 != Composer.Companion.getEmpty()) {
            }
            boolean booleanValue22222 = ((Boolean) rememberedValue5).booleanValue();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
            }
            ChoiceHeader("Rota sağlayıcısı", str12, composer3, 6);
            List available22222 = RoutingSource.Companion.getAvailable();
            ArrayList arrayList322222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available22222, 10));
            it = available22222.iterator();
            while (it.hasNext()) {
            }
            ArrayList arrayList422222 = arrayList322222;
            String label22222 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
            ComposerKt.sourceInformationMarkerStart(composer3, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
            changed2 = startRestartGroup.changed(collectAsState) | startRestartGroup.changedInstance(dashConfig2) | ((((i1722222 & 896) ^ 384) <= 256 && startRestartGroup.changedInstance(routeViewModel822222)) || (i1722222 & 384) == 256);
            Composer composer3522222 = composer3;
            composer6 = composer3;
            Object rememberedValue3022222 = composer3522222.rememberedValue();
            if (changed2) {
            }
            RouteScreenKt$.ExternalSyntheticLambda5 externalSyntheticLambda522222 = new RouteScreenKt$.ExternalSyntheticLambda5(dashConfig2, routeViewModel822222, collectAsState);
            composer3522222.updateRememberedValue(externalSyntheticLambda522222);
            obj2 = externalSyntheticLambda522222;
            ComposerKt.sourceInformationMarkerEnd(composer6);
            WayfindrComponentsKt.WayfindrSegmented(arrayList422222, label22222, (Function1) obj2, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(16), 7, (Object) null), composer6, 3072, 0);
            ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer6, 54);
            Modifier modifier1522222 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null);
            Arrangement.Vertical vertical222222 = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(8));
            boolean z2222222 = false;
            ComposerKt.sourceInformationMarkerStart(composer6, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy722222 = ColumnKt.columnMeasurePolicy(vertical222222, Alignment.Companion.getStart(), composer6, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode1522222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
            CompositionLocalMap currentCompositionLocalMap1522222 = composer6.getCurrentCompositionLocalMap();
            Modifier materializeModifier1522222 = ComposedModifierKt.materializeModifier(composer6, modifier1522222);
            Function0 constructor1522222 = ComposeUiNode.Companion.getConstructor();
            int i5322222 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer6.getApplier() instanceof Applier)) {
            }
            composer6.startReusableNode();
            if (composer6.getInserting()) {
            }
            Composer composer3622222 = Updater.constructor-impl(composer6);
            MeasurePolicy measurePolicy322222 = columnMeasurePolicy722222;
            Updater.set-impl(composer3622222, measurePolicy322222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3622222, currentCompositionLocalMap1522222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3622222, Integer.valueOf(hashCode1522222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3622222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3622222, materializeModifier1522222, ComposeUiNode.Companion.getSetModifier());
            int i5422222 = (i5322222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer6, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope722222 = ColumnScopeInstance.INSTANCE;
            int i5522222 = ((54 >> 6) & 112) | 6;
            Composer composer3722222 = composer6;
            ComposerKt.sourceInformationMarkerStart(composer3722222, -1001505061, "C:RouteScreen.kt#cef2n4");
            composer3722222.startReplaceGroup(-1140683288);
            ComposerKt.sourceInformation(composer3722222, "*585@31259L1578");
            List<List> chunked22222 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
            boolean z2322222 = false;
            while (r41.hasNext()) {
            }
            Composer composer4122222 = composer3722222;
            int i6422222 = i1722222;
            Context context822222 = context3;
            composer4122222.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer4122222);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            composer6.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            Arrangement.Horizontal spaceBetween222222 = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically522222 = Alignment.Companion.getCenterVertically();
            Modifier modifier2022222 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(9), 7, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer6, 844473419, str8);
            MeasurePolicy rowMeasurePolicy922222 = RowKt.rowMeasurePolicy(spaceBetween222222, centerVertically522222, composer6, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer6, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode1822222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer6, 0));
            CompositionLocalMap currentCompositionLocalMap1822222 = composer6.getCurrentCompositionLocalMap();
            Modifier materializeModifier1822222 = ComposedModifierKt.materializeModifier(composer6, modifier2022222);
            Function0 constructor1822222 = ComposeUiNode.Companion.getConstructor();
            int i6522222 = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer6, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer6.getApplier() instanceof Applier)) {
            }
            composer6.startReusableNode();
            if (composer6.getInserting()) {
            }
            Composer composer4222222 = Updater.constructor-impl(composer6);
            Updater.set-impl(composer4222222, rowMeasurePolicy922222, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer4222222, currentCompositionLocalMap1822222, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer4222222, Integer.valueOf(hashCode1822222), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer4222222, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer4222222, materializeModifier1822222, ComposeUiNode.Companion.getSetModifier());
            int i6622222 = (i6522222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer6, 1456264949, str7);
            RowScope rowScope922222 = RowScopeInstance.INSTANCE;
            int i6722222 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer6, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
            WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", (Modifier) null, composer6, 6, 2);
            IconKt.Icon-ww6aTOc(RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18)), RouteScreen$lambda$5(collectAsState4) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer6, 432, 0);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            composer6.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(composer6);
            List listOf222222 = CollectionsKt.listOf(new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
            ComposerKt.sourceInformationMarkerStart(composer6, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
            VoiceManager voiceManager322222 = voiceManager;
            changedInstance4 = composer6.changedInstance(voiceManager322222);
            rememberedValue6 = composer6.rememberedValue();
            if (!changedInstance4) {
            }
            RouteScreenKt$.ExternalSyntheticLambda7 externalSyntheticLambda722222 = new RouteScreenKt$.ExternalSyntheticLambda7(voiceManager322222);
            composer6.updateRememberedValue(externalSyntheticLambda722222);
            rememberedValue6 = externalSyntheticLambda722222;
            ComposerKt.sourceInformationMarkerEnd(composer6);
            WayfindrComponentsKt.WayfindrSegmented(listOf222222, str16, (Function1) rememberedValue6, PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, 0.0f, 0.0f, Dp.constructor-impl(22), 7, (Object) null), composer6, 3078, 0);
            if ((destination == null ? destination.getLat() : null) != null) {
            }
            if (!RouteScreen$lambda$7(mutableState10)) {
            }
            ImageVector check22222 = !RouteScreen$lambda$7(mutableState10) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
            BtnVariant btnVariant222222 = !RouteScreen$lambda$7(mutableState10) ? BtnVariant.Secondary : BtnVariant.Primary;
            BtnSize btnSize222222 = BtnSize.Lg;
            if (RouteScreen$lambda$7(mutableState10)) {
            }
            Modifier fillMaxWidth$default222222 = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer6, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance5 = composer6.changedInstance(context822222) | composer6.changedInstance(managedActivityResultLauncher422222) | composer6.changedInstance(strArr222);
            Object rememberedValue3222222 = composer6.rememberedValue();
            if (!changedInstance5) {
            }
            z7 = z6;
            mutableState5 = mutableState10;
            RouteScreenKt$.ExternalSyntheticLambda8 externalSyntheticLambda822222 = new RouteScreenKt$.ExternalSyntheticLambda8(managedActivityResultLauncher422222, strArr222, context822222, mutableState5);
            composer6.updateRememberedValue(externalSyntheticLambda822222);
            rememberedValue3222222 = externalSyntheticLambda822222;
            ComposerKt.sourceInformationMarkerEnd(composer6);
            WayfindrComponentsKt.WayfindrBtn(str21, (Function0) rememberedValue3222222, fillMaxWidth$default222222, check22222, btnVariant222222, btnSize222222, z31, composer6, 196992, 0);
            if (z7) {
            }
            composer6.endReplaceGroup();
            if (RouteScreen$lambda$2(collectAsState3).isEmpty()) {
            }
            composer6.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer6);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            startRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            if (RouteScreen$lambda$10(mutableState6)) {
            }
            startRestartGroup.endReplaceGroup();
            RouteScreen$lambda$16 = RouteScreen$lambda$16(mutableState8);
            if (RouteScreen$lambda$16 != null) {
            }
            RouteScreen$lambda$13 = RouteScreen$lambda$13(mutableState7);
            if (RouteScreen$lambda$13 != null) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            routeViewModel2 = routeViewModel5;
        } else {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
            routeViewModel2 = routeViewModel6;
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RouteScreenKt$.ExternalSyntheticLambda19(function0, function2, routeViewModel2, i, i2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean RouteScreen$hasLocationPermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RouteState RouteScreen$lambda$0(State state) {
        return (RouteState) state.getValue();
    }

    private static final boolean RouteScreen$lambda$10(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void RouteScreen$lambda$11(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final SavedLocation RouteScreen$lambda$13(MutableState mutableState) {
        return (SavedLocation) ((State) mutableState).getValue();
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$103$lambda$102$lambda$99$lambda$98(RouteViewModel routeViewModel, int i) {
        routeViewModel.selectRoute(i);
        return Unit.INSTANCE;
    }

    private static final RoutingSource RouteScreen$lambda$144$lambda$143$lambda$108(State state) {
        return (RoutingSource) state.getValue();
    }

    private static final MapTheme RouteScreen$lambda$144$lambda$143$lambda$109(State state) {
        return (MapTheme) state.getValue();
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig dashConfig, RouteViewModel routeViewModel, State state, String str) {
        Intrinsics.checkNotNullParameter(str, "label");
        for (RoutingSource routingSource : RoutingSource.values()) {
            if (Intrinsics.areEqual(routingSource.getLabel(), str)) {
                if (routingSource != RouteScreen$lambda$144$lambda$143$lambda$108(state)) {
                    dashConfig.setRoutingSource(routingSource);
                    routeViewModel.recomputeRoute();
                }
                return Unit.INSTANCE;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$121$lambda$120$lambda$119$lambda$118$lambda$116$lambda$115(DashConfig dashConfig, MapTheme mapTheme) {
        dashConfig.setMapTheme(mapTheme);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager voiceManager, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        voiceManager.setMode(Intrinsics.areEqual(str, "Kapalı") ? VoiceMode.OFF : Intrinsics.areEqual(str, "Tam sesli yönlendirme") ? VoiceMode.FULL : VoiceMode.CHIME);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher managedActivityResultLauncher, String[] strArr, Context context, MutableState mutableState) {
        if (RouteScreen$hasLocationPermission(context)) {
            RouteScreen$lambda$8(mutableState, true);
        } else {
            managedActivityResultLauncher.launch(strArr);
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$128$lambda$127(MutableState mutableState) {
        RouteScreen$lambda$11(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$131$lambda$130$lambda$129(MutableState mutableState) {
        RouteScreen$lambda$23(mutableState, !RouteScreen$lambda$22(mutableState));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x045a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0522  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0527  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02eb  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x01a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142(State state, RouteViewModel routeViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, ColumnScope columnScope, Composer composer, int i) {
        Alignment.Vertical vertical;
        Object obj;
        Function0 function0;
        Function0 function02;
        Function0 function03;
        String format;
        boolean changedInstance;
        Object rememberedValue;
        boolean changedInstance2;
        Object rememberedValue2;
        RouteViewModel routeViewModel2 = routeViewModel;
        Composer composer2 = composer;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer2, "C*686@36767L169,681@36482L2156:RouteScreen.kt#cef2n4");
        if (composer2.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1756830890, i, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:679)");
            }
            List RouteScreen$lambda$2 = RouteScreen$lambda$2(state);
            boolean z = false;
            int i2 = 0;
            for (Object obj2 : RouteScreen$lambda$2) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                SavedLocation savedLocation = (SavedLocation) obj2;
                if (i2 > 0) {
                    composer2.startReplaceGroup(-149724587);
                    ComposerKt.sourceInformation(composer2, "680@36405L52");
                    WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(4), 0.0f, 2, (Object) null), composer2, 6, 0);
                } else {
                    composer2.startReplaceGroup(-382611263);
                }
                composer2.endReplaceGroup();
                Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                Modifier clip = ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(12)));
                ComposerKt.sourceInformationMarkerStart(composer2, -149712886, "CC(remember):RouteScreen.kt#9igjgp");
                boolean changedInstance3 = composer2.changedInstance(routeViewModel2) | composer2.changedInstance(savedLocation);
                Object rememberedValue3 = composer.rememberedValue();
                if (changedInstance3) {
                    vertical = centerVertically;
                } else {
                    vertical = centerVertically;
                    if (rememberedValue3 != Composer.Companion.getEmpty()) {
                        obj = rememberedValue3;
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        Modifier modifier = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-oSLSa3U$default(clip, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) obj, 15, (Object) null), Dp.constructor-impl(6), Dp.constructor-impl(12));
                        ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                        List list = RouteScreen$lambda$2;
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
                        Function0 constructor = ComposeUiNode.Companion.getConstructor();
                        int i4 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        boolean z2 = z;
                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                            function0 = constructor;
                            composer.useNode();
                        } else {
                            function0 = constructor;
                            composer.createNode(function0);
                        }
                        Composer composer3 = Updater.constructor-impl(composer);
                        Updater.set-impl(composer3, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                        int i5 = (i4 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        int i6 = ((384 >> 6) & 112) | 6;
                        RowScope rowScope = RowScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart(composer, -837086718, "C692@37072L309,696@37410L29,697@37468L595,708@38329L17,705@38092L285,712@38564L17,710@38406L206:RouteScreen.kt#cef2n4");
                        Alignment center = Alignment.Companion.getCenter();
                        Modifier modifier2 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(11))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifier2);
                        Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                        int i7 = ((((48 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                            function02 = constructor2;
                            composer.useNode();
                        } else {
                            function02 = constructor2;
                            composer.createNode(function02);
                        }
                        Composer composer4 = Updater.constructor-impl(composer);
                        Updater.set-impl(composer4, maybeCachedBoxMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer4, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer4, Integer.valueOf(hashCode2), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer4, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer4, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                        int i8 = (i7 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        BoxScope boxScope = BoxScopeInstance.INSTANCE;
                        int i9 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -270499303, "C695@37296L83:RouteScreen.kt#cef2n4");
                        IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), ColorKt.getGold(), composer, 432, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(13)), composer, 6);
                        Modifier weight$default = RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null);
                        ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
                        Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer, weight$default);
                        Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                        int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                            function03 = constructor3;
                            composer.useNode();
                        } else {
                            function03 = constructor3;
                            composer.createNode(function03);
                        }
                        Composer composer5 = Updater.constructor-impl(composer);
                        Updater.set-impl(composer5, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                        Updater.set-impl(composer5, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer5, Integer.valueOf(hashCode3), ComposeUiNode.Companion.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer5, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer5, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                        int i11 = (i10 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
                        int i12 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -1754874523, "C698@37530L124,699@37687L346:RouteScreen.kt#cef2n4");
                        TextKt.Text-Nvy7gAk(savedLocation.getName(), (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(14.5d), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer, 1597440, 24576, 245546);
                        if (StringsKt.isBlank(savedLocation.getNote())) {
                            format = savedLocation.getNote();
                        } else {
                            format = String.format("%.4f, %.4f", Arrays.copyOf(new Object[]{Double.valueOf(savedLocation.getLat()), Double.valueOf(savedLocation.getLng())}, 2));
                            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                        }
                        TextKt.Text-Nvy7gAk(format, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, Dp.constructor-impl(2), 0.0f, 0.0f, 13, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer, 24624, 24576, 245608);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ImageVector share = WayfindrIcons.INSTANCE.getShare();
                        String str = savedLocation.getName() + " konumunu paylaş";
                        long textLo = ColorKt.getTextLo();
                        Modifier modifier3 = SizeKt.size-3ABfNKs(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, Dp.constructor-impl(14), 0.0f, 11, (Object) null), Dp.constructor-impl(18));
                        ComposerKt.sourceInformationMarkerStart(composer, -1689532082, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance = composer.changedInstance(savedLocation);
                        rememberedValue = composer.rememberedValue();
                        if (!changedInstance || rememberedValue == Composer.Companion.getEmpty()) {
                            RouteScreenKt$.ExternalSyntheticLambda29 externalSyntheticLambda29 = new RouteScreenKt$.ExternalSyntheticLambda29(savedLocation, mutableState2);
                            composer.updateRememberedValue(externalSyntheticLambda29);
                            rememberedValue = externalSyntheticLambda29;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        IconKt.Icon-ww6aTOc(share, str, ClickableKt.clickable-oSLSa3U$default(modifier3, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue, 15, (Object) null), textLo, composer, 0, 0);
                        ImageVector edit = WayfindrIcons.INSTANCE.getEdit();
                        long textLo2 = ColorKt.getTextLo();
                        Modifier modifier4 = SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18));
                        ComposerKt.sourceInformationMarkerStart(composer, -1689524562, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance2 = composer.changedInstance(savedLocation);
                        rememberedValue2 = composer.rememberedValue();
                        if (!changedInstance2 || rememberedValue2 == Composer.Companion.getEmpty()) {
                            RouteScreenKt$.ExternalSyntheticLambda30 externalSyntheticLambda30 = new RouteScreenKt$.ExternalSyntheticLambda30(savedLocation, mutableState3);
                            composer.updateRememberedValue(externalSyntheticLambda30);
                            rememberedValue2 = externalSyntheticLambda30;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        IconKt.Icon-ww6aTOc(edit, "Düzenle", ClickableKt.clickable-oSLSa3U$default(modifier4, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue2, 15, (Object) null), textLo2, composer, 48, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        routeViewModel2 = routeViewModel;
                        composer2 = composer;
                        i2 = i3;
                        RouteScreen$lambda$2 = list;
                        z = z2;
                    }
                }
                RouteScreenKt$.ExternalSyntheticLambda28 externalSyntheticLambda28 = new RouteScreenKt$.ExternalSyntheticLambda28(routeViewModel2, savedLocation, mutableState);
                composer.updateRememberedValue(externalSyntheticLambda28);
                obj = externalSyntheticLambda28;
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifier5 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-oSLSa3U$default(clip, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) obj, 15, (Object) null), Dp.constructor-impl(6), Dp.constructor-impl(12));
                ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
                List list2 = RouteScreen$lambda$2;
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composer, modifier5);
                Function0 constructor4 = ComposeUiNode.Companion.getConstructor();
                int i42 = ((((384 << 3) & 112) << 6) & 896) | 6;
                boolean z22 = z;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                }
                Composer composer32 = Updater.constructor-impl(composer);
                Updater.set-impl(composer32, rowMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer32, currentCompositionLocalMap4, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer32, Integer.valueOf(hashCode4), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer32, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer32, materializeModifier4, ComposeUiNode.Companion.getSetModifier());
                int i52 = (i42 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i62 = ((384 >> 6) & 112) | 6;
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -837086718, "C692@37072L309,696@37410L29,697@37468L595,708@38329L17,705@38092L285,712@38564L17,710@38406L206:RouteScreen.kt#cef2n4");
                Alignment center2 = Alignment.Companion.getCenter();
                Modifier modifier22 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(11))), ColorKt.getGoldTint(), (Shape) null, 2, (Object) null);
                ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap22 = composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier22 = ComposedModifierKt.materializeModifier(composer, modifier22);
                Function0 constructor22 = ComposeUiNode.Companion.getConstructor();
                int i72 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                }
                Composer composer42 = Updater.constructor-impl(composer);
                Updater.set-impl(composer42, maybeCachedBoxMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer42, currentCompositionLocalMap22, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer42, Integer.valueOf(hashCode22), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer42, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer42, materializeModifier22, ComposeUiNode.Companion.getSetModifier());
                int i82 = (i72 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
                int i92 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -270499303, "C695@37296L83:RouteScreen.kt#cef2n4");
                IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), ColorKt.getGold(), composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(13)), composer, 6);
                Modifier weight$default2 = RowScope.weight$default(rowScope2, Modifier.Companion, 1.0f, false, 2, (Object) null);
                ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode32 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap32 = composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier32 = ComposedModifierKt.materializeModifier(composer, weight$default2);
                Function0 constructor32 = ComposeUiNode.Companion.getConstructor();
                int i102 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                }
                Composer composer52 = Updater.constructor-impl(composer);
                Updater.set-impl(composer52, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer52, currentCompositionLocalMap32, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer52, Integer.valueOf(hashCode32), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer52, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer52, materializeModifier32, ComposeUiNode.Companion.getSetModifier());
                int i112 = (i102 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScope columnScope22 = ColumnScopeInstance.INSTANCE;
                int i122 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -1754874523, "C698@37530L124,699@37687L346:RouteScreen.kt#cef2n4");
                TextKt.Text-Nvy7gAk(savedLocation.getName(), (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(14.5d), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer, 1597440, 24576, 245546);
                if (StringsKt.isBlank(savedLocation.getNote())) {
                }
                TextKt.Text-Nvy7gAk(format, PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, Dp.constructor-impl(2), 0.0f, 0.0f, 13, (Object) null), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, TypeKt.getGeistMonoFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer, 24624, 24576, 245608);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ImageVector share2 = WayfindrIcons.INSTANCE.getShare();
                String str2 = savedLocation.getName() + " konumunu paylaş";
                long textLo3 = ColorKt.getTextLo();
                Modifier modifier32 = SizeKt.size-3ABfNKs(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, 0.0f, 0.0f, Dp.constructor-impl(14), 0.0f, 11, (Object) null), Dp.constructor-impl(18));
                ComposerKt.sourceInformationMarkerStart(composer, -1689532082, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance = composer.changedInstance(savedLocation);
                rememberedValue = composer.rememberedValue();
                if (!changedInstance) {
                }
                RouteScreenKt$.ExternalSyntheticLambda29 externalSyntheticLambda292 = new RouteScreenKt$.ExternalSyntheticLambda29(savedLocation, mutableState2);
                composer.updateRememberedValue(externalSyntheticLambda292);
                rememberedValue = externalSyntheticLambda292;
                ComposerKt.sourceInformationMarkerEnd(composer);
                IconKt.Icon-ww6aTOc(share2, str2, ClickableKt.clickable-oSLSa3U$default(modifier32, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue, 15, (Object) null), textLo3, composer, 0, 0);
                ImageVector edit2 = WayfindrIcons.INSTANCE.getEdit();
                long textLo22 = ColorKt.getTextLo();
                Modifier modifier42 = SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18));
                ComposerKt.sourceInformationMarkerStart(composer, -1689524562, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance2 = composer.changedInstance(savedLocation);
                rememberedValue2 = composer.rememberedValue();
                if (!changedInstance2) {
                }
                RouteScreenKt$.ExternalSyntheticLambda30 externalSyntheticLambda302 = new RouteScreenKt$.ExternalSyntheticLambda30(savedLocation, mutableState3);
                composer.updateRememberedValue(externalSyntheticLambda302);
                rememberedValue2 = externalSyntheticLambda302;
                ComposerKt.sourceInformationMarkerEnd(composer);
                IconKt.Icon-ww6aTOc(edit2, "Düzenle", ClickableKt.clickable-oSLSa3U$default(modifier42, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue2, 15, (Object) null), textLo22, composer, 48, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                routeViewModel2 = routeViewModel;
                composer2 = composer;
                i2 = i3;
                RouteScreen$lambda$2 = list2;
                z = z22;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$133$lambda$132(RouteViewModel routeViewModel, SavedLocation savedLocation, MutableState mutableState) {
        routeViewModel.selectSaved(savedLocation);
        RouteScreen$lambda$23(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$137$lambda$136(SavedLocation savedLocation, MutableState mutableState) {
        mutableState.setValue(savedLocation);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$139$lambda$138(SavedLocation savedLocation, MutableState mutableState) {
        mutableState.setValue(savedLocation);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0497, code lost:
    
        if (r4 != androidx.compose.runtime.Composer.Companion.getEmpty()) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x027f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0842  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x035d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x03cb  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0449  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x03d5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x03ba  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x028f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74(Function1 function1, RouteViewModel routeViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, State state, MutableState mutableState5, ColumnScope columnScope, Composer composer, int i) {
        Function0 function0;
        Modifier modifier;
        Object obj;
        Object rememberedValue;
        int i2;
        String RouteScreen$lambda$31;
        Function0 function02;
        Function0 function03;
        Function1 function12 = function1;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C271@12046L5128:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(820477990, i, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:271)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier2 = Modifier.Companion;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, modifier2);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i3 = ((((0 << 3) & 112) << 6) & 896) | 6;
            int i4 = 6;
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
            Updater.set-impl(composer2, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i5 = (i3 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
            int i6 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -1309771922, "C305@14026L186,310@14318L320,274@12198L183,279@12421L307,286@12951L692,272@12102L2558:RouteScreen.kt#cef2n4");
            String RouteScreen$lambda$19 = RouteScreen$lambda$19(mutableState);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.Companion.getSearch-eUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart(composer, 234901494, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed = composer.changed(function12) | composer.changedInstance(routeViewModel);
            Object rememberedValue2 = composer.rememberedValue();
            if (changed) {
                modifier = fillMaxWidth$default;
            } else {
                modifier = fillMaxWidth$default;
                if (rememberedValue2 != Composer.Companion.getEmpty()) {
                    obj = rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    KeyboardActions keyboardActions = new KeyboardActions((Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) obj, (Function1) null, 47, (DefaultConstructorMarker) null);
                    TextFieldColors textFieldColors = TextFieldDefaults.INSTANCE.colors-0hiis_0(0L, 0L, 0L, 0L, Color.Companion.getTransparent-0d7_KjU(), Color.Companion.getTransparent-0d7_KjU(), 0L, 0L, 0L, 0L, (TextSelectionColors) null, Color.Companion.getTransparent-0d7_KjU(), Color.Companion.getTransparent-0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                    ComposerKt.sourceInformationMarkerStart(composer, 234842995, "CC(remember):RouteScreen.kt#9igjgp");
                    rememberedValue = composer.rememberedValue();
                    if (rememberedValue != Composer.Companion.getEmpty()) {
                        rememberedValue = new RouteScreenKt$.ExternalSyntheticLambda40(mutableState, mutableState3, mutableState4);
                        composer.updateRememberedValue(rememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    TextFieldKt.TextField(RouteScreen$lambda$19, (Function1) rememberedValue, modifier, false, false, (TextStyle) null, (Function2) null, ComposableLambdaKt.rememberComposableLambda(607470615, true, new RouteScreenKt$.ExternalSyntheticLambda41(state), composer, 54), ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$600090294$app(), ComposableLambdaKt.rememberComposableLambda(592709973, true, new RouteScreenKt$.ExternalSyntheticLambda42(mutableState, mutableState3, mutableState2, mutableState4), composer, 54), (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, keyboardOptions, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors, composer, 918553008, 12779520, 0, 3964024);
                    if (RouteScreen$lambda$28(mutableState5) && RouteScreen$lambda$25(mutableState2).isEmpty() && RouteScreen$lambda$31(mutableState4) == null) {
                        composer.startReplaceGroup(-1321908826);
                    } else {
                        composer.startReplaceGroup(-1307189716);
                        ComposerKt.sourceInformation(composer, "318@14788L58,*337@15888L25,334@15701L1407");
                        BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(1)), ColorKt.getLine(), (Shape) null, 2, (Object) null), composer, 0);
                        if (RouteScreen$lambda$28(mutableState5)) {
                            i2 = 12;
                            composer.startReplaceGroup(-1321908826);
                        } else {
                            composer.startReplaceGroup(-1307121330);
                            ComposerKt.sourceInformation(composer, "320@14925L288");
                            i2 = 12;
                            TextKt.Text-Nvy7gAk("OpenStreetMap'te aranıyor…", PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(16), Dp.constructor-impl(10)), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24630, 0, 261992);
                        }
                        composer.endReplaceGroup();
                        RouteScreen$lambda$31 = RouteScreen$lambda$31(mutableState4);
                        if (RouteScreen$lambda$31 != null) {
                            composer.startReplaceGroup(-1306736683);
                            composer.endReplaceGroup();
                        } else {
                            composer.startReplaceGroup(-1306736682);
                            ComposerKt.sourceInformation(composer, "*327@15327L266");
                            TextKt.Text-Nvy7gAk(RouteScreen$lambda$31, PaddingKt.padding-VpY3zN4(Modifier.Companion, Dp.constructor-impl(16), Dp.constructor-impl(10)), ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(i2), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24624, 0, 261992);
                            Unit unit = Unit.INSTANCE;
                            composer.endReplaceGroup();
                            Unit unit2 = Unit.INSTANCE;
                        }
                        List<PlaceSearch.Hit> RouteScreen$lambda$25 = RouteScreen$lambda$25(mutableState2);
                        boolean z = false;
                        for (PlaceSearch.Hit hit : RouteScreen$lambda$25) {
                            List list = RouteScreen$lambda$25;
                            boolean z2 = z;
                            Modifier clip = ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8)));
                            ComposerKt.sourceInformationMarkerStart(composer, 1657944588, "CC(remember):RouteScreen.kt#9igjgp");
                            boolean changed2 = composer.changed(function12) | composer.changed(hit);
                            Object rememberedValue3 = composer.rememberedValue();
                            if (changed2) {
                            }
                            RouteScreenKt$.ExternalSyntheticLambda43 externalSyntheticLambda43 = new RouteScreenKt$.ExternalSyntheticLambda43(function12, hit);
                            composer.updateRememberedValue(externalSyntheticLambda43);
                            rememberedValue3 = externalSyntheticLambda43;
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            Modifier modifier3 = PaddingKt.padding-VpY3zN4(ClickableKt.clickable-oSLSa3U$default(clip, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue3, 15, (Object) null), Dp.constructor-impl(14), Dp.constructor-impl(11));
                            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifier3);
                            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
                            int i7 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer.startReusableNode();
                            if (composer.getInserting()) {
                                function02 = constructor2;
                                composer.createNode(function02);
                            } else {
                                function02 = constructor2;
                                composer.useNode();
                            }
                            Composer composer3 = Updater.constructor-impl(composer);
                            Updater.set-impl(composer3, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer3, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer3, Integer.valueOf(hashCode2), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer3, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
                            int i8 = (i7 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            int i9 = ((384 >> 6) & 112) | 6;
                            RowScope rowScope = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart(composer, -1635304540, "C341@16142L83,342@16258L29,343@16320L758:RouteScreen.kt#cef2n4");
                            IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(19)), ColorKt.getGold(), composer, 432, 0);
                            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer, i4);
                            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null);
                            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
                            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer, weight$default);
                            Function0 constructor3 = ComposeUiNode.Companion.getConstructor();
                            int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            composer.startReusableNode();
                            if (composer.getInserting()) {
                                function03 = constructor3;
                                composer.createNode(function03);
                            } else {
                                function03 = constructor3;
                                composer.useNode();
                            }
                            Composer composer4 = Updater.constructor-impl(composer);
                            Updater.set-impl(composer4, columnMeasurePolicy2, ComposeUiNode.Companion.getSetMeasurePolicy());
                            Updater.set-impl(composer4, currentCompositionLocalMap3, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                            Updater.init-impl(composer4, Integer.valueOf(hashCode3), ComposeUiNode.Companion.getSetCompositeKeyHash());
                            Updater.reconcile-impl(composer4, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                            Updater.set-impl(composer4, materializeModifier3, ComposeUiNode.Companion.getSetModifier());
                            int i11 = (i10 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScope columnScope3 = ColumnScopeInstance.INSTANCE;
                            i4 = 6;
                            int i12 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, 1816346692, "C344@16386L265:RouteScreen.kt#cef2n4");
                            TextKt.Text-Nvy7gAk(hit.getName(), (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.Companion.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composer, 1597440, 24960, 241450);
                            if (StringsKt.isBlank(hit.getSubtitle())) {
                                composer.startReplaceGroup(1800070389);
                            } else {
                                composer.startReplaceGroup(1816666704);
                                ComposerKt.sourceInformation(composer, "348@16761L245");
                                TextKt.Text-Nvy7gAk(hit.getSubtitle(), (Modifier) null, ColorKt.getTextLo(), (TextAutoSize) null, TextUnitKt.getSp(i2), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.Companion.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composer, 24576, 24960, 241514);
                            }
                            composer.endReplaceGroup();
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
                            function12 = function1;
                            z = z2;
                            RouteScreen$lambda$25 = list;
                        }
                    }
                    composer.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }
            RouteScreenKt$.ExternalSyntheticLambda39 externalSyntheticLambda39 = new RouteScreenKt$.ExternalSyntheticLambda39(function12, routeViewModel, mutableState2, mutableState);
            composer.updateRememberedValue(externalSyntheticLambda39);
            obj = externalSyntheticLambda39;
            ComposerKt.sourceInformationMarkerEnd(composer);
            KeyboardActions keyboardActions2 = new KeyboardActions((Function1) null, (Function1) null, (Function1) null, (Function1) null, (Function1) obj, (Function1) null, 47, (DefaultConstructorMarker) null);
            TextFieldColors textFieldColors2 = TextFieldDefaults.INSTANCE.colors-0hiis_0(0L, 0L, 0L, 0L, Color.Companion.getTransparent-0d7_KjU(), Color.Companion.getTransparent-0d7_KjU(), 0L, 0L, 0L, 0L, (TextSelectionColors) null, Color.Companion.getTransparent-0d7_KjU(), Color.Companion.getTransparent-0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
            ComposerKt.sourceInformationMarkerStart(composer, 234842995, "CC(remember):RouteScreen.kt#9igjgp");
            rememberedValue = composer.rememberedValue();
            if (rememberedValue != Composer.Companion.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            TextFieldKt.TextField(RouteScreen$lambda$19, (Function1) rememberedValue, modifier, false, false, (TextStyle) null, (Function2) null, ComposableLambdaKt.rememberComposableLambda(607470615, true, new RouteScreenKt$.ExternalSyntheticLambda41(state), composer, 54), ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$600090294$app(), ComposableLambdaKt.rememberComposableLambda(592709973, true, new RouteScreenKt$.ExternalSyntheticLambda42(mutableState, mutableState3, mutableState2, mutableState4), composer, 54), (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, keyboardOptions, keyboardActions2, true, 0, 0, (MutableInteractionSource) null, (Shape) null, textFieldColors2, composer, 918553008, 12779520, 0, 3964024);
            if (RouteScreen$lambda$28(mutableState5)) {
            }
            composer.startReplaceGroup(-1307189716);
            ComposerKt.sourceInformation(composer, "318@14788L58,*337@15888L25,334@15701L1407");
            BoxKt.Box(BackgroundKt.background-bw27NRU$default(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(1)), ColorKt.getLine(), (Shape) null, 2, (Object) null), composer, 0);
            if (RouteScreen$lambda$28(mutableState5)) {
            }
            composer.endReplaceGroup();
            RouteScreen$lambda$31 = RouteScreen$lambda$31(mutableState4);
            if (RouteScreen$lambda$31 != null) {
            }
            List<PlaceSearch.Hit> RouteScreen$lambda$252 = RouteScreen$lambda$25(mutableState2);
            boolean z3 = false;
            while (r6.hasNext()) {
            }
            composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$60$lambda$59(Function1 function1, RouteViewModel routeViewModel, MutableState mutableState, MutableState mutableState2, KeyboardActionScope keyboardActionScope) {
        Intrinsics.checkNotNullParameter(keyboardActionScope, "$this$KeyboardActions");
        PlaceSearch.Hit hit = (PlaceSearch.Hit) CollectionsKt.firstOrNull(RouteScreen$lambda$25(mutableState));
        if (hit != null) {
            function1.invoke(hit);
        } else {
            routeViewModel.search(RouteScreen$lambda$19(mutableState2));
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$62$lambda$61(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        mutableState.setValue(str);
        RouteScreen$lambda$35(mutableState2, false);
        mutableState3.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$63(State state, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C280@12451L251:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(607470615, i, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:280)");
            }
            TextKt.Text-Nvy7gAk((RouteScreen$lambda$0(state).getWaypoints().isEmpty() ? "Yer ara" : "Durak ekle") + " · OpenStreetMap", (Modifier) null, ColorKt.getTextLo(), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.Companion.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composer, 0, 24960, 241658);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$66(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(592709973, i, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:287)");
            }
            if (RouteScreen$lambda$19(mutableState).length() > 0) {
                composer.startReplaceGroup(812330095);
                ComposerKt.sourceInformation(composer, "291@13256L297,288@13045L542");
                ImageVector cross = WayfindrIcons.INSTANCE.getCross();
                long textLo = ColorKt.getTextLo();
                Modifier modifier = SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(18));
                ComposerKt.sourceInformationMarkerStart(composer, -1636356258, "CC(remember):RouteScreen.kt#9igjgp");
                Object rememberedValue = composer.rememberedValue();
                if (rememberedValue == Composer.Companion.getEmpty()) {
                    RouteScreenKt$.ExternalSyntheticLambda54 externalSyntheticLambda54 = new RouteScreenKt$.ExternalSyntheticLambda54(mutableState, mutableState2, mutableState3, mutableState4);
                    composer.updateRememberedValue(externalSyntheticLambda54);
                    rememberedValue = externalSyntheticLambda54;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                IconKt.Icon-ww6aTOc(cross, "Aramayı temizle", ClickableKt.clickable-oSLSa3U$default(modifier, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue, 15, (Object) null), textLo, composer, 48, 0);
            } else {
                composer.startReplaceGroup(799403405);
            }
            composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$66$lambda$65$lambda$64(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        mutableState.setValue("");
        RouteScreen$lambda$35(mutableState2, false);
        mutableState3.setValue(CollectionsKt.emptyList());
        mutableState4.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$72$lambda$69$lambda$68(Function1 function1, PlaceSearch.Hit hit) {
        function1.invoke(hit);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher managedActivityResultLauncher) {
        NavPlus.openOffline();
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$83$lambda$82(String str, State state, MutableState mutableState, ColumnScope columnScope, Composer composer, int i) {
        Function0 function0;
        Function0 function02;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C381@18534L1114:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2113600503, i, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:381)");
            }
            Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.Companion;
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i2 = ((((384 << 3) & 112) << 6) & 896) | 6;
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
            int i4 = ((384 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composer, -888554936, "C382@18612L98,383@18735L29,384@18789L508,393@19419L19,391@19322L304:RouteScreen.kt#cef2n4");
            IconKt.Icon-ww6aTOc(WayfindrIcons.INSTANCE.getShare(), (String) null, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20)), ColorKt.getGold(), composer, 432, 0);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(12)), composer, 6);
            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier(composer, weight$default);
            Function0 constructor2 = ComposeUiNode.Companion.getConstructor();
            int i5 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                function02 = constructor2;
                composer.createNode(function02);
            } else {
                function02 = constructor2;
                composer.useNode();
            }
            Composer composer3 = Updater.constructor-impl(composer);
            Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap2, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode2), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier2, ComposeUiNode.Companion.getSetModifier());
            int i6 = (i5 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
            int i7 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -1796672564, "C385@18847L156,387@19032L239:RouteScreen.kt#cef2n4");
            TextKt.Text-Nvy7gAk(str, (Modifier) null, ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(15), (FontStyle) null, FontWeight.Companion.getSemiBold(), TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer, 1597440, 24576, 245546);
            int size = RouteScreen$lambda$0(state).getWaypoints().size();
            RouteScreen$lambda$0(state).getWaypoints().size();
            TextKt.Text-Nvy7gAk("Paylaşılan rota · " + size + " durak", (Modifier) null, ColorKt.getTextMid(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24576, 0, 261994);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerStart(composer, 1633929752, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                RouteScreenKt$.ExternalSyntheticLambda3 externalSyntheticLambda3 = new RouteScreenKt$.ExternalSyntheticLambda3(mutableState);
                composer.updateRememberedValue(externalSyntheticLambda3);
                rememberedValue = externalSyntheticLambda3;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            WayfindrComponentsKt.WayfindrBtn("Kaydet", (Function0) rememberedValue, (Modifier) null, WayfindrIcons.INSTANCE.getPin(), BtnVariant.Secondary, BtnSize.Sm, false, composer, 221238, 68);
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

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79(MutableState mutableState) {
        RouteScreen$lambda$11(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x02c0, code lost:
    
        if (r1 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0360, code lost:
    
        if (r1 == androidx.compose.runtime.Composer.Companion.getEmpty()) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x03ec, code lost:
    
        if (r5 != androidx.compose.runtime.Composer.Companion.getEmpty()) goto L74;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94(State state, RouteViewModel routeViewModel, ColumnScope columnScope, Composer composer, int i) {
        Function0 function0;
        Composer composer2;
        Object obj;
        Composer composer3;
        Object obj2;
        Composer composer4 = composer;
        Intrinsics.checkNotNullParameter(columnScope, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer4, "C*421@20771L1438:RouteScreen.kt#cef2n4");
        if (composer4.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1714951167, i, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:419)");
            }
            List waypoints = RouteScreen$lambda$0(state).getWaypoints();
            boolean z = false;
            int i2 = 0;
            for (Object obj3 : waypoints) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                SharedLocation sharedLocation = (SharedLocation) obj3;
                if (i2 > 0) {
                    composer4.startReplaceGroup(1413343954);
                    ComposerKt.sourceInformation(composer4, "420@20694L52");
                    WayfindrComponentsKt.WayfindrDivider(PaddingKt.padding-VpY3zN4$default(Modifier.Companion, Dp.constructor-impl(4), 0.0f, 2, (Object) null), composer4, 6, 0);
                } else {
                    composer4.startReplaceGroup(843458564);
                }
                composer4.endReplaceGroup();
                Alignment.Vertical centerVertically = Alignment.Companion.getCenterVertically();
                Modifier modifier = PaddingKt.padding-VpY3zN4(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), Dp.constructor-impl(8), Dp.constructor-impl(9));
                ComposerKt.sourceInformationMarkerStart(composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                List list = waypoints;
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
                Function0 constructor = ComposeUiNode.Companion.getConstructor();
                int i4 = ((((390 << 3) & 112) << 6) & 896) | 6;
                boolean z2 = z;
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
                Composer composer5 = Updater.constructor-impl(composer);
                Updater.set-impl(composer5, rowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.set-impl(composer5, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer5, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer5, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer5, materializeModifier, ComposeUiNode.Companion.getSetModifier());
                int i5 = (i4 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i6 = ((390 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart(composer, -668558625, "C422@20933L172,423@21134L121:RouteScreen.kt#cef2n4");
                TextKt.Text-Nvy7gAk(i2 == CollectionsKt.getLastIndex(RouteScreen$lambda$0(state).getWaypoints()) ? "Son" : String.valueOf(i2 + 1), SizeKt.width-3ABfNKs(Modifier.Companion, Dp.constructor-impl(38)), ColorKt.getGold(), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 1597488, 0, 262056);
                TextKt.Text-Nvy7gAk(sharedLocation.getName(), RowScope.weight$default(rowScope, Modifier.Companion, 1.0f, false, 2, (Object) null), ColorKt.getTextHi(), (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, TypeKt.getGeistFamily(), 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 1, 0, (Function1) null, (TextStyle) null, composer, 24576, 24576, 245608);
                Composer composer6 = composer;
                if (RouteScreen$lambda$0(state).getWaypoints().size() > 1) {
                    composer6.startReplaceGroup(-668187680);
                    ComposerKt.sourceInformation(composer6, "426@21539L49,425@21353L236,428@21871L49,427@21622L299,430@22112L40,429@21954L199");
                    ImageVector moveUp = WayfindrIcons.INSTANCE.getMoveUp();
                    long textMid = i2 > 0 ? ColorKt.getTextMid() : ColorKt.getTextLo();
                    Modifier modifier2 = SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(19));
                    boolean z3 = i2 > 0;
                    ComposerKt.sourceInformationMarkerStart(composer6, -714284877, "CC(remember):RouteScreen.kt#9igjgp");
                    boolean changedInstance = composer6.changedInstance(routeViewModel) | composer6.changed(i2);
                    Object rememberedValue = composer6.rememberedValue();
                    if (changedInstance) {
                        composer2 = composer6;
                    } else {
                        composer2 = composer6;
                        obj = rememberedValue;
                    }
                    RouteScreenKt$.ExternalSyntheticLambda0 externalSyntheticLambda0 = new RouteScreenKt$.ExternalSyntheticLambda0(routeViewModel, i2);
                    composer6.updateRememberedValue(externalSyntheticLambda0);
                    obj = externalSyntheticLambda0;
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    Composer composer7 = composer2;
                    IconKt.Icon-ww6aTOc(moveUp, "Durağı öne al", ClickableKt.clickable-oSLSa3U$default(modifier2, z3, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) obj, 14, (Object) null), textMid, composer7, 48, 0);
                    ImageVector moveDown = WayfindrIcons.INSTANCE.getMoveDown();
                    long textMid2 = i2 < CollectionsKt.getLastIndex(RouteScreen$lambda$0(state).getWaypoints()) ? ColorKt.getTextMid() : ColorKt.getTextLo();
                    Modifier modifier3 = SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(19));
                    boolean z4 = i2 < CollectionsKt.getLastIndex(RouteScreen$lambda$0(state).getWaypoints());
                    ComposerKt.sourceInformationMarkerStart(composer7, -714274253, "CC(remember):RouteScreen.kt#9igjgp");
                    boolean changedInstance2 = composer7.changedInstance(routeViewModel) | composer7.changed(i2);
                    Object rememberedValue2 = composer7.rememberedValue();
                    if (changedInstance2) {
                        composer3 = composer7;
                    } else {
                        composer3 = composer7;
                        obj2 = rememberedValue2;
                    }
                    RouteScreenKt$.ExternalSyntheticLambda1 externalSyntheticLambda1 = new RouteScreenKt$.ExternalSyntheticLambda1(routeViewModel, i2);
                    composer7.updateRememberedValue(externalSyntheticLambda1);
                    obj2 = externalSyntheticLambda1;
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    Composer composer8 = composer3;
                    IconKt.Icon-ww6aTOc(moveDown, "Durağı geriye al", ClickableKt.clickable-oSLSa3U$default(modifier3, z4, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) obj2, 14, (Object) null), textMid2, composer8, 48, 0);
                    composer6 = composer8;
                    ImageVector cross = WayfindrIcons.INSTANCE.getCross();
                    long textLo = ColorKt.getTextLo();
                    Modifier modifier4 = SizeKt.size-3ABfNKs(PaddingKt.padding-qDBjuR0$default(Modifier.Companion, Dp.constructor-impl(4), 0.0f, 0.0f, 0.0f, 14, (Object) null), Dp.constructor-impl(18));
                    ComposerKt.sourceInformationMarkerStart(composer6, -714266550, "CC(remember):RouteScreen.kt#9igjgp");
                    boolean changedInstance3 = composer6.changedInstance(routeViewModel) | composer6.changed(i2);
                    Object rememberedValue3 = composer6.rememberedValue();
                    if (changedInstance3) {
                    }
                    RouteScreenKt$.ExternalSyntheticLambda2 externalSyntheticLambda2 = new RouteScreenKt$.ExternalSyntheticLambda2(routeViewModel, i2);
                    composer6.updateRememberedValue(externalSyntheticLambda2);
                    rememberedValue3 = externalSyntheticLambda2;
                    ComposerKt.sourceInformationMarkerEnd(composer6);
                    IconKt.Icon-ww6aTOc(cross, "Durağı kaldır", ClickableKt.clickable-oSLSa3U$default(modifier4, false, (String) null, (Role) null, (MutableInteractionSource) null, (Function0) rememberedValue3, 15, (Object) null), textLo, composer6, 48, 0);
                } else {
                    composer6.startReplaceGroup(-689363904);
                }
                composer6.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer6);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer4 = composer;
                i2 = i3;
                waypoints = list;
                z = z2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$87$lambda$86(RouteViewModel routeViewModel, int i) {
        routeViewModel.moveWaypoint(i, i - 1);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$89$lambda$88(RouteViewModel routeViewModel, int i) {
        routeViewModel.moveWaypoint(i, i + 1);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$91$lambda$90(RouteViewModel routeViewModel, int i) {
        routeViewModel.removeWaypoint(i);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel routeViewModel, LatLng latLng) {
        Intrinsics.checkNotNullParameter(latLng, "it");
        routeViewModel.selectLocation(latLng.latitude, latLng.longitude);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$146$lambda$145(RouteViewModel routeViewModel, MutableState mutableState, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "note");
        routeViewModel.saveCurrentDestination(str, str2);
        RouteScreen$lambda$11(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$148$lambda$147(MutableState mutableState) {
        RouteScreen$lambda$11(mutableState, false);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$151$lambda$150$lambda$149(MutableState mutableState) {
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$158$lambda$153$lambda$152(RouteViewModel routeViewModel, SavedLocation savedLocation, MutableState mutableState, String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "note");
        routeViewModel.renameSaved(savedLocation, str, str2);
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$158$lambda$155$lambda$154(RouteViewModel routeViewModel, SavedLocation savedLocation, MutableState mutableState) {
        routeViewModel.deleteSaved(savedLocation);
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$158$lambda$157$lambda$156(MutableState mutableState) {
        mutableState.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$159(Function0 function0, Function2 function2, RouteViewModel routeViewModel, int i, int i2, Composer composer, int i3) {
        RouteScreen(function0, function2, routeViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    private static final SavedLocation RouteScreen$lambda$16(MutableState mutableState) {
        return (SavedLocation) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String RouteScreen$lambda$19(MutableState mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final List RouteScreen$lambda$2(State state) {
        return (List) state.getValue();
    }

    private static final boolean RouteScreen$lambda$22(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void RouteScreen$lambda$23(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final List RouteScreen$lambda$25(MutableState mutableState) {
        return (List) ((State) mutableState).getValue();
    }

    private static final boolean RouteScreen$lambda$28(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RouteScreen$lambda$29(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String RouteScreen$lambda$31(MutableState mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean RouteScreen$lambda$34(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RouteScreen$lambda$35(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit RouteScreen$lambda$39$lambda$38(RouteViewModel routeViewModel, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, PlaceSearch.Hit hit) {
        Intrinsics.checkNotNullParameter(hit, "hit");
        RouteScreen$lambda$35(mutableState, true);
        mutableState2.setValue(CollectionsKt.emptyList());
        RouteScreen$lambda$29(mutableState3, false);
        mutableState4.setValue(null);
        mutableState5.setValue("");
        RouteScreen$lambda$23(mutableState6, false);
        routeViewModel.selectPlaceSuggestion(hit);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$43$lambda$42(MutableState mutableState, Map map) {
        Intrinsics.checkNotNullParameter(map, "it");
        RouteScreen$lambda$8(mutableState, true);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$45$lambda$44(RouteViewModel routeViewModel, Uri uri) {
        if (uri != null) {
            routeViewModel.importQrImage(uri);
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$47$lambda$46(RouteViewModel routeViewModel, boolean z) {
        routeViewModel.startLocationUpdates();
        return Unit.INSTANCE;
    }

    private static final VoiceMode RouteScreen$lambda$5(State state) {
        return (VoiceMode) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean RouteScreen$lambda$7(MutableState mutableState) {
        return ((Boolean) ((State) mutableState).getValue()).booleanValue();
    }

    private static final void RouteScreen$lambda$8(MutableState mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final void SaveLocationDialog(String str, Function2 function2, Function0 function0, Composer composer, int i) {
        Composer composer2;
        Composer startRestartGroup = composer.startRestartGroup(-1163012778);
        ComposerKt.sourceInformation(startRestartGroup, "C(SaveLocationDialog)N(defaultName,onSave,onDismiss)739@39496L40,740@39553L31,743@39691L186,744@39903L97,746@40076L404,741@39616L903:RouteScreen.kt#cef2n4");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changed(str) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 256 : 128;
        }
        if (startRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1163012778, i2, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog (RouteScreen.kt:738)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -963778210, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default = SnapshotStateKt.mutableStateOf$default(str, (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default);
                rememberedValue = mutableStateOf$default;
            }
            MutableState mutableState = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -963776395, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                MutableState mutableStateOf$default2 = SnapshotStateKt.mutableStateOf$default("", (SnapshotMutationPolicy) null, 2, (Object) null);
                startRestartGroup.updateRememberedValue(mutableStateOf$default2);
                rememberedValue2 = mutableStateOf$default2;
            }
            MutableState mutableState2 = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            AndroidAlertDialog_androidKt.AlertDialog-Oix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-779189346, true, new RouteScreenKt$.ExternalSyntheticLambda44(function2, mutableState, mutableState2), startRestartGroup, 54), (Modifier) null, ComposableLambdaKt.rememberComposableLambda(-1727241312, true, new RouteScreenKt$.ExternalSyntheticLambda45(function0), startRestartGroup, 54), (Function2) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$1619674018$app(), ComposableLambdaKt.rememberComposableLambda(1145648035, true, new RouteScreenKt$.ExternalSyntheticLambda46(mutableState, mutableState2), startRestartGroup, 54), (Shape) null, ColorKt.getSurf1(), 0L, 0L, 0L, 0.0f, (DialogProperties) null, composer2, ((i2 >> 6) & 14) | 1772592, 0, 16020);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = startRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new RouteScreenKt$.ExternalSyntheticLambda47(str, function2, function0, i));
        }
    }

    private static final String SaveLocationDialog$lambda$161(MutableState mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    private static final String SaveLocationDialog$lambda$164(MutableState mutableState) {
        return (String) ((State) mutableState).getValue();
    }

    static final Unit SaveLocationDialog$lambda$169(Function2 function2, MutableState mutableState, MutableState mutableState2, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C743@39770L36,743@39808L67,743@39720L155:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-779189346, i, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous> (RouteScreen.kt:743)");
            }
            boolean z = !StringsKt.isBlank(SaveLocationDialog$lambda$161(mutableState));
            ComposerKt.sourceInformationMarkerStart(composer, -1476649438, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed = composer.changed(function2);
            Object rememberedValue = composer.rememberedValue();
            if (changed || rememberedValue == Composer.Companion.getEmpty()) {
                RouteScreenKt$.ExternalSyntheticLambda52 externalSyntheticLambda52 = new RouteScreenKt$.ExternalSyntheticLambda52(function2, mutableState, mutableState2);
                composer.updateRememberedValue(externalSyntheticLambda52);
                rememberedValue = externalSyntheticLambda52;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ButtonKt.TextButton((Function0) rememberedValue, (Modifier) null, z, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(2084718363, true, new RouteScreenKt$.ExternalSyntheticLambda53(mutableState), composer, 54), composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$169$lambda$167$lambda$166(Function2 function2, MutableState mutableState, MutableState mutableState2) {
        function2.invoke(StringsKt.trim(SaveLocationDialog$lambda$161(mutableState)).toString(), StringsKt.trim(SaveLocationDialog$lambda$164(mutableState2)).toString());
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$169$lambda$168(MutableState mutableState, RowScope rowScope, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(rowScope, "$this$TextButton");
        ComposerKt.sourceInformation(composer, "C743@39810L63:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2084718363, i, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous>.<anonymous> (RouteScreen.kt:743)");
            }
            TextKt.Text-Nvy7gAk("Kaydet", (Modifier) null, !StringsKt.isBlank(SaveLocationDialog$lambda$161(mutableState)) ? ColorKt.getGold() : ColorKt.getTextLo(), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$170(Function0 function0, Composer composer, int i) {
        ComposerKt.sourceInformation(composer, "C744@39932L66:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1727241312, i, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous> (RouteScreen.kt:744)");
            }
            ButtonKt.TextButton(function0, (Modifier) null, false, (Shape) null, (ButtonColors) null, (ButtonElevation) null, (BorderStroke) null, (PaddingValues) null, (MutableInteractionSource) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$1136666397$app(), composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$176(MutableState mutableState, MutableState mutableState2, Composer composer, int i) {
        Function0 function0;
        Object obj;
        Composer composer2;
        ComposerKt.sourceInformation(composer, "C747@40090L380:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1145648035, i, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous> (RouteScreen.kt:747)");
            }
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.Companion;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.Companion.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier(composer, modifier);
            Function0 constructor = ComposeUiNode.Companion.getConstructor();
            int i2 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            Composer composer3 = Updater.constructor-impl(composer);
            Updater.set-impl(composer3, columnMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(hashCode), ComposeUiNode.Companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, ComposeUiNode.Companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, materializeModifier, ComposeUiNode.Companion.getSetModifier());
            int i3 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
            int i4 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -1159392043, "C748@40166L13,748@40142L117,749@40327L13,749@40303L153:RouteScreen.kt#cef2n4");
            String SaveLocationDialog$lambda$161 = SaveLocationDialog$lambda$161(mutableState);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1486622214, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = composer.rememberedValue();
            if (rememberedValue == Composer.Companion.getEmpty()) {
                RouteScreenKt$.ExternalSyntheticLambda32 externalSyntheticLambda32 = new RouteScreenKt$.ExternalSyntheticLambda32(mutableState);
                composer.updateRememberedValue(externalSyntheticLambda32);
                obj = externalSyntheticLambda32;
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(SaveLocationDialog$lambda$161, (Function1) obj, fillMaxWidth$default, false, false, (TextStyle) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$837491251$app(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1573296, 12582912, 0, 8257464);
            String SaveLocationDialog$lambda$164 = SaveLocationDialog$lambda$164(mutableState2);
            Modifier modifier2 = PaddingKt.padding-qDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.Companion, 0.0f, 1, (Object) null), 0.0f, Dp.constructor-impl(8), 0.0f, 0.0f, 13, (Object) null);
            ComposerKt.sourceInformationMarkerStart(composer, 1486627366, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = composer.rememberedValue();
            if (rememberedValue2 == Composer.Companion.getEmpty()) {
                composer2 = composer;
                RouteScreenKt$.ExternalSyntheticLambda33 externalSyntheticLambda33 = new RouteScreenKt$.ExternalSyntheticLambda33(mutableState2);
                composer.updateRememberedValue(externalSyntheticLambda33);
                rememberedValue2 = externalSyntheticLambda33;
            } else {
                composer2 = composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer2);
            OutlinedTextFieldKt.OutlinedTextField(SaveLocationDialog$lambda$164, (Function1) rememberedValue2, modifier2, false, false, (TextStyle) null, ComposableSingletons.RouteScreenKt.INSTANCE.getLambda$-1895094998$app(), (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, (Function2) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer2, 1573296, 12582912, 0, 8257464);
            ComposerKt.sourceInformationMarkerEnd(composer2);
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

    static final Unit SaveLocationDialog$lambda$176$lambda$175$lambda$172$lambda$171(MutableState mutableState, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$176$lambda$175$lambda$174$lambda$173(MutableState mutableState, String str) {
        Intrinsics.checkNotNullParameter(str, "it");
        mutableState.setValue(str);
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$177(String str, Function2 function2, Function0 function0, int i, Composer composer, int i2) {
        SaveLocationDialog(str, function2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private static final String formatAlternativeDistance(double d) {
        if (d < 1000.0d) {
            return ((int) d) + " m";
        }
        String format = String.format(Locale.forLanguageTag("tr-TR"), "%.1f km", Arrays.copyOf(new Object[]{Double.valueOf(d / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private static final String formatAlternativeDuration(double d) {
        StringBuilder append;
        int coerceAtLeast = RangesKt.coerceAtLeast((int) (d / 60.0d), 0);
        if (coerceAtLeast >= 60) {
            append = new StringBuilder().append(coerceAtLeast / 60).append(" sa ").append(coerceAtLeast % 60);
        } else {
            append = new StringBuilder().append(coerceAtLeast);
        }
        return append.append(" dk").toString();
    }
}
