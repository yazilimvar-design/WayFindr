package com.example.wayfindr.ui.screens;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequestKt;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ScrollKt;
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
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.AndroidAlertDialog_androidKt;
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
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
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
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.MapTheme;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.OfflineRouter;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.TrafficInterval;
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
import com.example.wayfindr.ui.theme.ColorKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.viewmodel.RouteState;
import com.example.wayfindr.viewmodel.RouteViewModel;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.maplibre.turf.TurfConstants;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RouteScreen.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aG\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032 \u0010\u0004\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u000b\u001a=\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003¢\u0006\u0002\u0010\u0011\u001aK\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003¢\u0006\u0002\u0010\u0016\u001a\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002\u001a\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u0019H\u0002\u001aA\u0010\u001c\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010!2\b\b\u0002\u0010\"\u001a\u00020#H\u0003¢\u0006\u0002\u0010$\u001a\u001d\u0010%\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u000eH\u0003¢\u0006\u0002\u0010(¨\u0006)²\u0006\n\u0010*\u001a\u00020+X\u008a\u0084\u0002²\u0006\u0010\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140\u0006X\u008a\u0084\u0002²\u0006\n\u0010-\u001a\u00020.X\u008a\u0084\u0002²\u0006\n\u0010/\u001a\u000200X\u008a\u008e\u0002²\u0006\n\u00101\u001a\u000200X\u008a\u008e\u0002²\u0006\f\u00102\u001a\u0004\u0018\u00010\u0014X\u008a\u008e\u0002²\u0006\f\u00103\u001a\u0004\u0018\u00010\u0014X\u008a\u008e\u0002²\u0006\n\u00104\u001a\u00020\u000eX\u008a\u008e\u0002²\u0006\n\u00105\u001a\u000200X\u008a\u008e\u0002²\u0006\u0010\u00106\u001a\b\u0012\u0004\u0012\u0002070\u0006X\u008a\u008e\u0002²\u0006\n\u00108\u001a\u000200X\u008a\u008e\u0002²\u0006\f\u00109\u001a\u0004\u0018\u00010\u000eX\u008a\u008e\u0002²\u0006\n\u0010:\u001a\u000200X\u008a\u008e\u0002²\u0006\n\u0010;\u001a\u00020<X\u008a\u0084\u0002²\u0006\n\u0010=\u001a\u00020>X\u008a\u0084\u0002²\u0006\n\u0010?\u001a\u00020\u000eX\u008a\u008e\u0002²\u0006\n\u0010@\u001a\u00020\u000eX\u008a\u008e\u0002²\u0006\n\u0010?\u001a\u00020\u000eX\u008a\u008e\u0002²\u0006\n\u0010@\u001a\u00020\u000eX\u008a\u008e\u0002"}, d2 = {"RouteScreen", "", "onBack", "Lkotlin/Function0;", "onSentToDash", "Lkotlin/Function2;", "", "Lcom/example/wayfindr/data/SharedLocation;", "Lcom/example/wayfindr/dash/nav/Route;", "routeViewModel", "Lcom/example/wayfindr/viewmodel/RouteViewModel;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lcom/example/wayfindr/viewmodel/RouteViewModel;Landroidx/compose/runtime/Composer;II)V", "SaveLocationDialog", "defaultName", "", "onSave", "onDismiss", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "EditLocationDialog", "loc", "Lcom/example/wayfindr/data/SavedLocation;", "onDelete", "(Lcom/example/wayfindr/data/SavedLocation;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "formatAlternativeDistance", TurfConstants.UNIT_METERS, "", "formatAlternativeDuration", "seconds", "RouteMapLegend", "routes", "selectedIndex", "", "onSelect", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "(Ljava/util/List;ILkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "ChoiceHeader", "label", "detail", "(Ljava/lang/String;Ljava/lang/String;Landroidx/compose/runtime/Composer;I)V", "app", "routeState", "Lcom/example/wayfindr/viewmodel/RouteState;", "savedList", "voiceMode", "Lcom/example/wayfindr/dash/nav/VoiceMode;", "sent", "", "showSave", "editing", "sharing", "searchQuery", "showSavedDestinations", "suggestions", "Lcom/example/wayfindr/dash/nav/PlaceSearch$Hit;", "suggestionsLoading", "suggestionsStatus", "suggestionsSuppressed", "routingSource", "Lcom/example/wayfindr/dash/RoutingSource;", "mapTheme", "Lcom/example/wayfindr/dash/MapTheme;", HintConstants.AUTOFILL_HINT_NAME, "note"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RouteScreenKt {

    /* compiled from: RouteScreen.kt */
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
            int[] iArr2 = new int[RoutingSource.values().length];
            try {
                iArr2[RoutingSource.GOOGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[RoutingSource.OSM.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[RoutingSource.BROUTER.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit ChoiceHeader$lambda$206(String str, String str2, int i, Composer composer, int i2) {
        ChoiceHeader(str, str2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$195(SavedLocation savedLocation, Function2 function2, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        EditLocationDialog(savedLocation, function2, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit RouteMapLegend$lambda$196(List list, int i, Function1 function1, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        RouteMapLegend(list, i, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit RouteMapLegend$lambda$204(List list, int i, Function1 function1, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        RouteMapLegend(list, i, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$159(Function0 function0, Function2 function2, RouteViewModel routeViewModel, int i, int i2, Composer composer, int i3) {
        RouteScreen(function0, function2, routeViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$177(String str, Function2 function2, Function0 function0, int i, Composer composer, int i2) {
        SaveLocationDialog(str, function2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x06b3, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L212;
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0708, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L228;
     */
    /* JADX WARN: Code restructure failed: missing block: B:247:0x0b94, code lost:
    
        if (r7 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L343;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x1308, code lost:
    
        if (r4 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L421;
     */
    /* JADX WARN: Code restructure failed: missing block: B:315:0x156f, code lost:
    
        if (r15 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L451;
     */
    /* JADX WARN: Code restructure failed: missing block: B:485:0x2a16, code lost:
    
        if (r4 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L647;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x015c, code lost:
    
        if (r7.equals("") == false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0176, code lost:
    
        r18 = "Paylaşılan konum";
     */
    /* JADX WARN: Code restructure failed: missing block: B:634:0x373a, code lost:
    
        if (r6 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L826;
     */
    /* JADX WARN: Code restructure failed: missing block: B:663:0x384b, code lost:
    
        if (r7 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L859;
     */
    /* JADX WARN: Code restructure failed: missing block: B:673:0x389d, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L874;
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
    /* JADX WARN: Removed duplicated region for block: B:298:0x1341  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x13c8  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x161d  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x1625  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x1695  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x1745  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x1751  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x1881  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x188d  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x19ea  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x19f6  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x1a81  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x1b34  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x1bf4  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x2397  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x23b5  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x23d2  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x2482  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x248e  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x2526  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x293e  */
    /* JADX WARN: Removed duplicated region for block: B:468:0x2972  */
    /* JADX WARN: Removed duplicated region for block: B:470:0x297a  */
    /* JADX WARN: Removed duplicated region for block: B:475:0x29b3 A[LOOP:3: B:473:0x29ad->B:475:0x29b3, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:479:0x29f2  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x2a0e  */
    /* JADX WARN: Removed duplicated region for block: B:489:0x2b13  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x2b1f  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x2bc7  */
    /* JADX WARN: Removed duplicated region for block: B:553:0x3116  */
    /* JADX WARN: Removed duplicated region for block: B:556:0x3122  */
    /* JADX WARN: Removed duplicated region for block: B:559:0x31b2  */
    /* JADX WARN: Removed duplicated region for block: B:562:0x31c9  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x3239  */
    /* JADX WARN: Removed duplicated region for block: B:570:0x328a  */
    /* JADX WARN: Removed duplicated region for block: B:572:0x3292  */
    /* JADX WARN: Removed duplicated region for block: B:581:0x32b6  */
    /* JADX WARN: Removed duplicated region for block: B:584:0x32e4  */
    /* JADX WARN: Removed duplicated region for block: B:587:0x32f9  */
    /* JADX WARN: Removed duplicated region for block: B:590:0x3308 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:594:0x3340  */
    /* JADX WARN: Removed duplicated region for block: B:599:0x337c  */
    /* JADX WARN: Removed duplicated region for block: B:605:0x3414  */
    /* JADX WARN: Removed duplicated region for block: B:623:0x36f1  */
    /* JADX WARN: Removed duplicated region for block: B:642:0x379b  */
    /* JADX WARN: Removed duplicated region for block: B:645:0x37f8  */
    /* JADX WARN: Removed duplicated region for block: B:648:0x38ff  */
    /* JADX WARN: Removed duplicated region for block: B:655:0x3809  */
    /* JADX WARN: Removed duplicated region for block: B:687:0x37a7  */
    /* JADX WARN: Removed duplicated region for block: B:697:0x378a  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x36ab  */
    /* JADX WARN: Removed duplicated region for block: B:704:0x33fb  */
    /* JADX WARN: Removed duplicated region for block: B:706:0x3350 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:708:0x32fc  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x32eb  */
    /* JADX WARN: Removed duplicated region for block: B:710:0x32bb  */
    /* JADX WARN: Removed duplicated region for block: B:718:0x328f  */
    /* JADX WARN: Removed duplicated region for block: B:720:0x31ce  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x31b9  */
    /* JADX WARN: Removed duplicated region for block: B:722:0x3128  */
    /* JADX WARN: Removed duplicated region for block: B:723:0x2b25  */
    /* JADX WARN: Removed duplicated region for block: B:724:0x2a1a  */
    /* JADX WARN: Removed duplicated region for block: B:729:0x2982  */
    /* JADX WARN: Removed duplicated region for block: B:730:0x2985  */
    /* JADX WARN: Removed duplicated region for block: B:731:0x2955  */
    /* JADX WARN: Removed duplicated region for block: B:732:0x2494  */
    /* JADX WARN: Removed duplicated region for block: B:733:0x23d5  */
    /* JADX WARN: Removed duplicated region for block: B:736:0x23b8  */
    /* JADX WARN: Removed duplicated region for block: B:739:0x239a  */
    /* JADX WARN: Removed duplicated region for block: B:743:0x237b  */
    /* JADX WARN: Removed duplicated region for block: B:744:0x1b96  */
    /* JADX WARN: Removed duplicated region for block: B:745:0x1ad6  */
    /* JADX WARN: Removed duplicated region for block: B:746:0x19fc  */
    /* JADX WARN: Removed duplicated region for block: B:747:0x1893  */
    /* JADX WARN: Removed duplicated region for block: B:748:0x1757  */
    /* JADX WARN: Removed duplicated region for block: B:749:0x1698  */
    /* JADX WARN: Removed duplicated region for block: B:753:0x1622  */
    /* JADX WARN: Removed duplicated region for block: B:759:0x1608  */
    /* JADX WARN: Removed duplicated region for block: B:760:0x1351  */
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
    public static final void RouteScreen(final Function0<Unit> onBack, final Function2<? super List<SharedLocation>, ? super Route, Unit> onSentToDash, RouteViewModel routeViewModel, Composer $composer, final int $changed, final int i) {
        Composer $composer2;
        final RouteViewModel routeViewModel2;
        String name;
        String str;
        String str2;
        String str3;
        VoiceManager voiceManager;
        String sourceDestName;
        boolean changedInstance;
        Context ctx;
        MutableState suggestionsSuppressed$delegate;
        MutableState suggestionsSuppressed$delegate2;
        State routeState$delegate;
        String destName;
        Context ctx2;
        boolean z;
        Object rememberedValue;
        final RouteViewModel routeViewModel3;
        Object rememberedValue2;
        Object rememberedValue3;
        boolean z2;
        ManagedActivityResultLauncher navPermLauncher;
        boolean z3;
        ManagedActivityResultLauncher qrPicker;
        boolean changedInstance2;
        boolean changed;
        ManagedActivityResultLauncher locationPermLauncher;
        final State routeState$delegate2;
        Function0 function0;
        List<GeoPoint> geometry;
        List<TrafficInterval> traffic;
        List routeAlternatives;
        List list;
        boolean z4;
        Function0 function02;
        boolean z5;
        Object rememberedValue4;
        Function0 function03;
        String searchNote;
        boolean changedInstance3;
        Composer composer;
        final String sharedRouteName;
        final MutableState showSave$delegate;
        Composer composer2;
        String str4;
        String str5;
        Function0 function04;
        Function0 function05;
        Function0 function06;
        String destName2;
        String destName3;
        String destSub;
        MutableState showSave$delegate2;
        boolean routing;
        String distanceText;
        String durationText;
        String etaText;
        Composer composer3;
        Function0 function07;
        final State collectAsState;
        Object rememberedValue5;
        Context ctx3;
        String str6;
        Iterator it;
        boolean changed2;
        Composer composer4;
        Function0 function08;
        Function0 function09;
        boolean changedInstance4;
        Object rememberedValue6;
        boolean z6;
        boolean changedInstance5;
        boolean z7;
        final MutableState sent$delegate;
        final MutableState sent$delegate2;
        final RouteViewModel routeViewModel4;
        final MutableState editing$delegate;
        final MutableState sharing$delegate;
        boolean z8;
        int $dirty;
        SavedLocation RouteScreen$lambda$16;
        final SavedLocation RouteScreen$lambda$13;
        int $dirty2;
        RouteViewModel routeViewModel5;
        Function0 function010;
        Composer composer5;
        final MutableState showSavedDestinations$delegate;
        Function0 function011;
        Function0 function012;
        Function0 function013;
        Function0 function014;
        Function0 function015;
        Function0 function016;
        Function0 function017;
        Composer composer6;
        Function0 function018;
        int i2;
        Alignment.Horizontal horizontal;
        Pair pair;
        String name2;
        int i3;
        RouteViewModel routeViewModel6 = routeViewModel;
        Intrinsics.checkNotNullParameter(onBack, "onBack");
        Intrinsics.checkNotNullParameter(onSentToDash, "onSentToDash");
        Composer $composer3 = $composer.startRestartGroup(-1253613665);
        ComposerKt.sourceInformation($composer3, "C(RouteScreen)N(onBack,onSentToDash,routeViewModel)46@1896L16,61@2460L16,62@2504L7,63@2533L58,64@2615L64,65@2719L16,71@3019L34,72@3074L34,73@3128L75,74@3223L75,75@3322L31,76@3387L33,77@3444L87,78@3562L34,79@3626L42,80@3702L34,83@3854L291,83@3829L316,92@4202L1079,92@4151L1130,115@5367L272,129@6002L251,143@6733L15,141@6631L117,146@6897L73,146@6820L150,152@7096L41,150@7003L134,154@7164L205,154@7143L226,162@7396L144,162@7375L165,169@7546L31162:RouteScreen.kt#cef2n4");
        int $dirty3 = $changed;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(onBack) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(onSentToDash) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                if (($changed & 512) == 0 ? $composer3.changed(routeViewModel6) : $composer3.changedInstance(routeViewModel6)) {
                    i3 = 256;
                    $dirty3 |= i3;
                }
            }
            i3 = 128;
            $dirty3 |= i3;
        }
        if ($composer3.shouldExecute(($dirty3 & 147) != 146, $dirty3 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "44@1836L11");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
            } else if ((i & 4) != 0) {
                $composer3.startReplaceableGroup(1729797275);
                ComposerKt.sourceInformation($composer3, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer3, 6);
                if (current == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                }
                ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(RouteViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, current instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, $composer3, ((0 << 3) & 896) | ((0 << 3) & 112) | ((0 << 3) & 7168) | ((0 << 3) & 57344), 0);
                $composer3.endReplaceableGroup();
                routeViewModel6 = (RouteViewModel) viewModel;
                $dirty3 &= -897;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1253613665, $dirty3, -1, "com.example.wayfindr.ui.screens.RouteScreen (RouteScreen.kt:45)");
            }
            State routeState$delegate3 = SnapshotStateKt.collectAsState(routeViewModel6.getState(), null, $composer3, 0, 1);
            SharedLocation dest = RouteScreen$lambda$0(routeState$delegate3).getDestination();
            String sourceDestName2 = dest != null ? dest.getName() : null;
            if (dest == null || (name2 = dest.getName()) == null) {
                name = null;
            } else {
                String str7 = name2;
                if (StringsKt.isBlank(str7)) {
                    str7 = "";
                }
                name = str7;
            }
            if (name == null) {
                name = "";
            }
            switch (name.hashCode()) {
                case -708399888:
                    break;
                case -397906667:
                    if (name.equals("Dropped pin")) {
                        str = "Haritadan seçilen konum";
                        break;
                    }
                    str = name;
                    break;
                case 0:
                    break;
                default:
                    str = name;
                    break;
            }
            String name3 = str;
            if ((dest != null ? dest.getLat() : null) == null || dest.getLng() == null) {
                str2 = (dest != null ? dest.getUrl() : null) != null ? "Harita bağlantısı" : "";
            } else {
                str2 = String.format("%.5f, %.5f", Arrays.copyOf(new Object[]{dest.getLat(), dest.getLng()}, 2));
                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
            }
            String destSub2 = str2;
            final State savedList$delegate = SnapshotStateKt.collectAsState(routeViewModel6.getSaved(), null, $composer3, 0, 1);
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context ctx4 = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, -2131862087, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue7 = $composer3.rememberedValue();
            if (rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                Object obj = DashConfig.INSTANCE.get(ctx4);
                $composer3.updateRememberedValue(obj);
                rememberedValue7 = obj;
            }
            final DashConfig dashConfig = (DashConfig) rememberedValue7;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131859457, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue8 = $composer3.rememberedValue();
            if (rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                rememberedValue8 = VoiceManager.INSTANCE.get(ctx4);
                $composer3.updateRememberedValue(rememberedValue8);
            }
            VoiceManager voiceManager2 = (VoiceManager) rememberedValue8;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            State voiceMode$delegate = SnapshotStateKt.collectAsState(voiceManager2.getMode(), null, $composer3, 0, 1);
            switch (WhenMappings.$EnumSwitchMapping$0[RouteScreen$lambda$5(voiceMode$delegate).ordinal()]) {
                case 1:
                    str3 = "Kapalı";
                    break;
                case 2:
                    str3 = "Yalnızca uyarı sesi";
                    break;
                case 3:
                    str3 = "Tam sesli yönlendirme";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            String voice = str3;
            ComposerKt.sourceInformationMarkerStart($composer3, -2131846559, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue9 = $composer3.rememberedValue();
            if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                rememberedValue9 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(rememberedValue9);
            }
            final MutableState sent$delegate3 = (MutableState) rememberedValue9;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131844799, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue10 = $composer3.rememberedValue();
            if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                voiceManager = voiceManager2;
                rememberedValue10 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(rememberedValue10);
            } else {
                voiceManager = voiceManager2;
            }
            MutableState showSave$delegate3 = (MutableState) rememberedValue10;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131843030, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue11 = $composer3.rememberedValue();
            if (rememberedValue11 == Composer.INSTANCE.getEmpty()) {
                rememberedValue11 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(rememberedValue11);
            }
            final MutableState editing$delegate2 = (MutableState) rememberedValue11;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131839990, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue12 = $composer3.rememberedValue();
            if (rememberedValue12 == Composer.INSTANCE.getEmpty()) {
                rememberedValue12 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(rememberedValue12);
            }
            final MutableState sharing$delegate2 = (MutableState) rememberedValue12;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131836866, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue13 = $composer3.rememberedValue();
            if (rememberedValue13 == Composer.INSTANCE.getEmpty()) {
                rememberedValue13 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(rememberedValue13);
            }
            final MutableState searchQuery$delegate = (MutableState) rememberedValue13;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131834784, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue14 = $composer3.rememberedValue();
            if (rememberedValue14 == Composer.INSTANCE.getEmpty()) {
                rememberedValue14 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                $composer3.updateRememberedValue(rememberedValue14);
            }
            final MutableState showSavedDestinations$delegate2 = (MutableState) rememberedValue14;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131832906, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue15 = $composer3.rememberedValue();
            if (rememberedValue15 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default);
                rememberedValue15 = mutableStateOf$default;
            }
            final MutableState suggestions$delegate = (MutableState) rememberedValue15;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131829183, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue16 = $composer3.rememberedValue();
            if (rememberedValue16 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default2);
                rememberedValue16 = mutableStateOf$default2;
            }
            MutableState suggestionsLoading$delegate = (MutableState) rememberedValue16;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131827127, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue17 = $composer3.rememberedValue();
            if (rememberedValue17 == Composer.INSTANCE.getEmpty()) {
                rememberedValue17 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(rememberedValue17);
            }
            final MutableState suggestionsStatus$delegate = (MutableState) rememberedValue17;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131824703, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue18 = $composer3.rememberedValue();
            if (rememberedValue18 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default3);
                rememberedValue18 = mutableStateOf$default3;
            }
            MutableState suggestionsSuppressed$delegate3 = (MutableState) rememberedValue18;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131819582, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed3 = $composer3.changed(routeState$delegate3) | $composer3.changed(sourceDestName2) | $composer3.changed(name3);
            Object rememberedValue19 = $composer3.rememberedValue();
            if (!changed3 && rememberedValue19 != Composer.INSTANCE.getEmpty()) {
                sourceDestName = name3;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(sourceDestName, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue19, $composer3, 0);
                String RouteScreen$lambda$19 = RouteScreen$lambda$19(searchQuery$delegate);
                Boolean valueOf = Boolean.valueOf(RouteScreen$lambda$34(suggestionsSuppressed$delegate3));
                ComposerKt.sourceInformationMarkerStart($composer3, -2131807658, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance = $composer3.changedInstance(ctx4) | $composer3.changed(routeState$delegate3);
                Object rememberedValue20 = $composer3.rememberedValue();
                if (changedInstance) {
                    ctx = ctx4;
                    if (rememberedValue20 != Composer.INSTANCE.getEmpty()) {
                        destName = sourceDestName;
                        ctx2 = ctx;
                        routeState$delegate = routeState$delegate3;
                        suggestionsSuppressed$delegate = suggestionsSuppressed$delegate3;
                        suggestionsSuppressed$delegate2 = suggestionsLoading$delegate;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        EffectsKt.LaunchedEffect(RouteScreen$lambda$19, valueOf, (Function2) rememberedValue20, $composer3, 0);
                        ComposerKt.sourceInformationMarkerStart($composer3, -2131771185, "CC(remember):RouteScreen.kt#9igjgp");
                        z = ((($dirty3 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel6)) || ($dirty3 & 384) == 256;
                        rememberedValue = $composer3.rememberedValue();
                        if (!z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                            final MutableState suggestionsSuppressed$delegate4 = suggestionsSuppressed$delegate;
                            final RouteViewModel routeViewModel7 = routeViewModel6;
                            final MutableState suggestions$delegate2 = suggestionsSuppressed$delegate2;
                            Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj3) {
                                    return RouteScreenKt.RouteScreen$lambda$39$lambda$38(RouteViewModel.this, suggestionsSuppressed$delegate4, suggestions$delegate, suggestions$delegate2, suggestionsStatus$delegate, searchQuery$delegate, showSavedDestinations$delegate2, (PlaceSearch.Hit) obj3);
                                }
                            };
                            routeViewModel3 = routeViewModel7;
                            suggestionsSuppressed$delegate = suggestionsSuppressed$delegate4;
                            suggestions$delegate = suggestions$delegate;
                            $composer3.updateRememberedValue(obj2);
                            rememberedValue = obj2;
                        } else {
                            routeViewModel3 = routeViewModel6;
                        }
                        final Function1 selectSuggestion = (Function1) rememberedValue;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerStart($composer3, -2131750886, "CC(remember):RouteScreen.kt#9igjgp");
                        rememberedValue2 = $composer3.rememberedValue();
                        if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            List createListBuilder = CollectionsKt.createListBuilder();
                            createListBuilder.add("android.permission.ACCESS_FINE_LOCATION");
                            if (Build.VERSION.SDK_INT >= 33) {
                                createListBuilder.add("android.permission.NEARBY_WIFI_DEVICES");
                            }
                            Object obj3 = (String[]) CollectionsKt.build(createListBuilder).toArray(new String[0]);
                            $composer3.updateRememberedValue(obj3);
                            rememberedValue2 = obj3;
                        }
                        final String[] navPermissions = (String[]) rememberedValue2;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions = new ActivityResultContracts.RequestMultiplePermissions();
                        ComposerKt.sourceInformationMarkerStart($composer3, -2131727730, "CC(remember):RouteScreen.kt#9igjgp");
                        rememberedValue3 = $composer3.rememberedValue();
                        if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            Object obj4 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj5) {
                                    return RouteScreenKt.RouteScreen$lambda$43$lambda$42(MutableState.this, (Map) obj5);
                                }
                            };
                            $composer3.updateRememberedValue(obj4);
                            rememberedValue3 = obj4;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ManagedActivityResultLauncher navPermLauncher2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions, (Function1) rememberedValue3, $composer3, 48);
                        ActivityResultContracts.PickVisualMedia pickVisualMedia = new ActivityResultContracts.PickVisualMedia();
                        ComposerKt.sourceInformationMarkerStart($composer3, -2131722424, "CC(remember):RouteScreen.kt#9igjgp");
                        z2 = ((($dirty3 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty3 & 384) == 256;
                        Object rememberedValue21 = $composer3.rememberedValue();
                        if (z2) {
                            navPermLauncher = navPermLauncher2;
                        } else {
                            navPermLauncher = navPermLauncher2;
                        }
                        Object obj5 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda20
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj6) {
                                return RouteScreenKt.RouteScreen$lambda$45$lambda$44(RouteViewModel.this, (Uri) obj6);
                            }
                        };
                        $composer3.updateRememberedValue(obj5);
                        rememberedValue21 = obj5;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ManagedActivityResultLauncher qrPicker2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(pickVisualMedia, (Function1) rememberedValue21, $composer3, 0);
                        ActivityResultContracts.RequestPermission requestPermission = new ActivityResultContracts.RequestPermission();
                        ComposerKt.sourceInformationMarkerStart($composer3, -2131716088, "CC(remember):RouteScreen.kt#9igjgp");
                        z3 = ((($dirty3 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty3 & 384) == 256;
                        Object rememberedValue22 = $composer3.rememberedValue();
                        if (z3) {
                            qrPicker = qrPicker2;
                        } else {
                            qrPicker = qrPicker2;
                        }
                        Object obj6 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda21
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj7) {
                                return RouteScreenKt.RouteScreen$lambda$47$lambda$46(RouteViewModel.this, ((Boolean) obj7).booleanValue());
                            }
                        };
                        $composer3.updateRememberedValue(obj6);
                        rememberedValue22 = obj6;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ManagedActivityResultLauncher locationPermLauncher2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission, (Function1) rememberedValue22, $composer3, 0);
                        Unit unit = Unit.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -2131713748, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance2 = $composer3.changedInstance(ctx2) | (((($dirty3 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty3 & 384) == 256) | $composer3.changedInstance(locationPermLauncher2);
                        Object rememberedValue23 = $composer3.rememberedValue();
                        if (changedInstance2 && rememberedValue23 != Composer.INSTANCE.getEmpty()) {
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue23, $composer3, 6);
                            Boolean valueOf2 = Boolean.valueOf(RouteScreen$lambda$7(sent$delegate3));
                            ComposerKt.sourceInformationMarkerStart($composer3, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                            changed = (($dirty3 & 112) == 32) | $composer3.changed(routeState$delegate);
                            Object rememberedValue24 = $composer3.rememberedValue();
                            if (changed) {
                                locationPermLauncher = locationPermLauncher2;
                            } else {
                                locationPermLauncher = locationPermLauncher2;
                                if (rememberedValue24 != Composer.INSTANCE.getEmpty()) {
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    EffectsKt.LaunchedEffect(valueOf2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue24, $composer3, 0);
                                    Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Arrangement.Vertical top = Arrangement.INSTANCE.getTop();
                                    Alignment.Horizontal start = Alignment.INSTANCE.getStart();
                                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(top, start, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                                    routeState$delegate2 = routeState$delegate;
                                    Context ctx5 = ctx2;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, fillMaxSize$default);
                                    Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
                                    int i4 = ((((6 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer3.startReusableNode();
                                    if ($composer3.getInserting()) {
                                        function0 = constructor;
                                        $composer3.useNode();
                                    } else {
                                        function0 = constructor;
                                        $composer3.createNode(function0);
                                    }
                                    Composer m4364constructorimpl = Updater.m4364constructorimpl($composer3);
                                    Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i5 = (i4 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    int i6 = ((6 >> 6) & 112) | 6;
                                    ColumnScope columnScope = ColumnScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                                    Modifier m262backgroundbw27NRU$default = BackgroundKt.m262backgroundbw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.46f), ColorKt.getMapBase(), null, 2, null);
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer3, m262backgroundbw27NRU$default);
                                    Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i7 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer3.startReusableNode();
                                    if ($composer3.getInserting()) {
                                        $composer3.useNode();
                                    } else {
                                        $composer3.createNode(constructor2);
                                    }
                                    Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer3);
                                    Updater.m4372setimpl(m4364constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i8 = (i7 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                    int i9 = ((0 >> 6) & 112) | 6;
                                    BoxScope boxScope = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                                    Pair<Double, Double> currentLocation = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                                    Double first = currentLocation == null ? currentLocation.getFirst() : null;
                                    Pair<Double, Double> currentLocation2 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                                    Double second = currentLocation2 == null ? currentLocation2.getSecond() : null;
                                    float currentBearing = RouteScreen$lambda$0(routeState$delegate2).getCurrentBearing();
                                    long locationRefreshKey = RouteScreen$lambda$0(routeState$delegate2).getLocationRefreshKey();
                                    Pair pair2 = dest == null ? (dest.getLat() == null || dest.getLng() == null) ? null : TuplesKt.to(dest.getLat(), dest.getLng()) : null;
                                    Iterable<SharedLocation> waypoints = RouteScreen$lambda$0(routeState$delegate2).getWaypoints();
                                    int i10 = 0;
                                    Collection arrayList = new ArrayList();
                                    for (SharedLocation sharedLocation : waypoints) {
                                        if (sharedLocation.getLat() == null || sharedLocation.getLng() == null) {
                                            i2 = i10;
                                            horizontal = start;
                                            pair = null;
                                        } else {
                                            i2 = i10;
                                            horizontal = start;
                                            pair = TuplesKt.to(sharedLocation.getLat(), sharedLocation.getLng());
                                        }
                                        if (pair != null) {
                                            arrayList.add(pair);
                                        }
                                        i10 = i2;
                                        start = horizontal;
                                    }
                                    ArrayList arrayList2 = (List) arrayList;
                                    Route route = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                                    geometry = route == null ? route.getGeometry() : null;
                                    if (geometry == null) {
                                        geometry = CollectionsKt.emptyList();
                                    }
                                    List<GeoPoint> list2 = geometry;
                                    Route route2 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                                    traffic = route2 == null ? route2.getTraffic() : null;
                                    if (traffic == null) {
                                        traffic = CollectionsKt.emptyList();
                                    }
                                    List<TrafficInterval> list3 = traffic;
                                    routeAlternatives = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
                                    list = routeAlternatives;
                                    if (!(routeAlternatives.size() <= 1)) {
                                        list = null;
                                    }
                                    if (list == null) {
                                        list = CollectionsKt.emptyList();
                                    }
                                    int selectedRouteIndex = RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
                                    boolean z9 = RouteScreen$lambda$0(routeState$delegate2).getRoute() == null && !RouteScreen$lambda$0(routeState$delegate2).getRouting();
                                    Modifier fillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    ComposerKt.sourceInformationMarkerStart($composer3, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                                    z4 = ((($dirty3 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty3 & 384) == 256;
                                    Object rememberedValue25 = $composer3.rememberedValue();
                                    if (z4) {
                                    }
                                    Object obj7 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda22
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj8) {
                                            return RouteScreenKt.RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel.this, (LatLng) obj8);
                                        }
                                    };
                                    $composer3.updateRememberedValue(obj7);
                                    rememberedValue25 = obj7;
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    FreeWayfindrMapKt.WayfindrMap(first, second, pair2, arrayList2, list2, list3, list, selectedRouteIndex, true, fillMaxSize$default2, z9, false, currentBearing, locationRefreshKey, false, true, null, null, null, null, false, (Function1) rememberedValue25, $composer3, 905969664, 221184, 0, 2033664);
                                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                    Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopStart()), 0.0f, 1, null), Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(14));
                                    ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap3 = $composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer3, m817paddingVpY3zN4);
                                    Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i11 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    int $dirty4 = $dirty3;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer3.startReusableNode();
                                    if ($composer3.getInserting()) {
                                        function02 = constructor3;
                                        $composer3.useNode();
                                    } else {
                                        function02 = constructor3;
                                        $composer3.createNode(function02);
                                    }
                                    Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer3);
                                    Updater.m4372setimpl(m4364constructorimpl3, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i12 = (i11 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    int i13 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                                    final ManagedActivityResultLauncher navPermLauncher3 = navPermLauncher;
                                    final ManagedActivityResultLauncher qrPicker3 = qrPicker;
                                    int $dirty5 = $dirty4;
                                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), onBack, BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, (($dirty4 << 3) & 112) | 3072, 48);
                                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                                    WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(routeState$delegate2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(routeState$delegate2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(routeState$delegate2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), $composer3, 0, 4);
                                    ImageVector recenter = WayfindrIcons.INSTANCE.getRecenter();
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                                    z5 = ((($dirty5 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty5 & 384) == 256;
                                    rememberedValue4 = $composer3.rememberedValue();
                                    if (!z5 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                        Object obj8 = (KFunction) new RouteScreenKt$RouteScreen$5$1$5$1$1(routeViewModel3);
                                        $composer3.updateRememberedValue(obj8);
                                        rememberedValue4 = obj8;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter, (Function0) ((KFunction) rememberedValue4), BackgroundKt.m261backgroundbw27NRU(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, 3072, 48);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                                        $composer3.startReplaceGroup(-744620986);
                                        ComposerKt.sourceInformation($composer3, "241@10984L27,238@10811L289");
                                        List<Route> routeAlternatives2 = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
                                        int selectedRouteIndex2 = RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
                                        ComposerKt.sourceInformationMarkerStart($composer3, -162561546, "CC(remember):RouteScreen.kt#9igjgp");
                                        boolean z10 = ((($dirty5 & 896) ^ 384) > 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty5 & 384) == 256;
                                        Object rememberedValue26 = $composer3.rememberedValue();
                                        if (z10 || rememberedValue26 == Composer.INSTANCE.getEmpty()) {
                                            Object obj9 = (KFunction) new RouteScreenKt$RouteScreen$5$1$6$1(routeViewModel3);
                                            $composer3.updateRememberedValue(obj9);
                                            rememberedValue26 = obj9;
                                        }
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        RouteMapLegend(routeAlternatives2, selectedRouteIndex2, (Function1) ((KFunction) rememberedValue26), boxScope.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomStart()), $composer3, 0, 0);
                                    } else {
                                        $composer3.startReplaceGroup(-755338585);
                                    }
                                    $composer3.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m817paddingVpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), RoundedCornerShapeKt.m1126RoundedCornerShapea9UjIt4$default(Dp.m7908constructorimpl(22), Dp.m7908constructorimpl(22), 0.0f, 0.0f, 12, null)), ColorKt.getBg1(), null, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18), Dp.m7908constructorimpl(8)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(20), 7, null);
                                    ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap4 = $composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default);
                                    Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i14 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    $composer3.startReusableNode();
                                    if ($composer3.getInserting()) {
                                        function03 = constructor4;
                                        $composer3.useNode();
                                    } else {
                                        function03 = constructor4;
                                        $composer3.createNode(function03);
                                    }
                                    Composer m4364constructorimpl4 = Updater.m4364constructorimpl($composer3);
                                    Updater.m4372setimpl(m4364constructorimpl4, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i15 = (i14 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    int i16 = ((0 >> 6) & 112) | 6;
                                    ColumnScope columnScope2 = ColumnScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer3, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                                    BoxKt.Box(PaddingKt.m820paddingqDBjuR0$default(columnScope2.align(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), Dp.m7908constructorimpl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), null, 2, null), Alignment.INSTANCE.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), $composer3, 0);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                                    final MutableState suggestionsSuppressed$delegate5 = suggestionsSuppressed$delegate;
                                    final MutableState suggestionsLoading$delegate2 = suggestionsSuppressed$delegate2;
                                    final RouteViewModel routeViewModel8 = routeViewModel3;
                                    WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(4), null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda23
                                        @Override // kotlin.jvm.functions.Function3
                                        public final Object invoke(Object obj10, Object obj11, Object obj12) {
                                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74(Function1.this, routeViewModel8, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate5, suggestionsStatus$delegate, routeState$delegate2, suggestionsLoading$delegate2, (ColumnScope) obj10, (Composer) obj11, ((Integer) obj12).intValue());
                                        }
                                    }, $composer3, 54), $composer3, 24966, 10);
                                    searchNote = RouteScreen$lambda$0(routeState$delegate2).getSearchNote();
                                    if (searchNote != null) {
                                        $composer3.startReplaceGroup(2013138439);
                                        $composer3.endReplaceGroup();
                                    } else {
                                        $composer3.startReplaceGroup(2013138440);
                                        ComposerKt.sourceInformation($composer3, "*364@17627L110");
                                        TextKt.m3069TextNvy7gAk(StringsKt.contains((CharSequence) searchNote, (CharSequence) "no match", true) ? "Eşleşme bulunamadı." : StringsKt.contains((CharSequence) searchNote, (CharSequence) "no internet", true) ? "İnternet bağlantısı yok; arama yapılamadı." : "Arama hizmetine ulaşılamadı. Lütfen bağlantınızı kontrol edip tekrar deneyin.", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(6), 0.0f, 0.0f, Dp.m7908constructorimpl(12), 6, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 24624, 0, 262120);
                                        Unit unit2 = Unit.INSTANCE;
                                        $composer3.endReplaceGroup();
                                        Unit unit3 = Unit.INSTANCE;
                                    }
                                    ImageVector qrScan = WayfindrIcons.INSTANCE.getQrScan();
                                    BtnVariant btnVariant = BtnVariant.Ghost;
                                    BtnSize btnSize = BtnSize.Sm;
                                    Modifier m820paddingqDBjuR0$default2 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(12), 7, null);
                                    ComposerKt.sourceInformationMarkerStart($composer3, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                                    changedInstance3 = $composer3.changedInstance(qrPicker3);
                                    Object rememberedValue27 = $composer3.rememberedValue();
                                    if (changedInstance3) {
                                    }
                                    rememberedValue27 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda24
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher.this);
                                        }
                                    };
                                    $composer3.updateRememberedValue(rememberedValue27);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    WayfindrComponentsKt.WayfindrBtn("QR görselinden rota yükle", (Function0) rememberedValue27, m820paddingqDBjuR0$default2, qrScan, btnVariant, btnSize, false, $composer3, 221574, 64);
                                    composer = $composer3;
                                    sharedRouteName = RouteScreen$lambda$0(routeState$delegate2).getSharedRouteName();
                                    if (sharedRouteName != null) {
                                        composer.startReplaceGroup(2014314920);
                                        composer.endReplaceGroup();
                                        showSave$delegate = showSave$delegate3;
                                    } else {
                                        composer.startReplaceGroup(2014314921);
                                        ComposerKt.sourceInformation(composer, "*380@18512L1154,380@18422L1244");
                                        showSave$delegate = showSave$delegate3;
                                        WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(14), null, ComposableLambdaKt.rememberComposableLambda(-2113600503, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda25
                                            @Override // kotlin.jvm.functions.Function3
                                            public final Object invoke(Object obj10, Object obj11, Object obj12) {
                                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$83$lambda$82(sharedRouteName, routeState$delegate2, showSave$delegate, (ColumnScope) obj10, (Composer) obj11, ((Integer) obj12).intValue());
                                            }
                                        }, composer, 54), composer, 24966, 10);
                                        Unit unit4 = Unit.INSTANCE;
                                        composer.endReplaceGroup();
                                        Unit unit5 = Unit.INSTANCE;
                                    }
                                    if (RouteScreen$lambda$0(routeState$delegate2).getWaypoints().isEmpty()) {
                                        composer.startReplaceGroup(2015763458);
                                        ComposerKt.sourceInformation(composer, "404@19828L646,418@20580L1669,418@20491L1758");
                                        Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                                        Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                                        Modifier m820paddingqDBjuR0$default3 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(6), 7, null);
                                        str5 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                                        ComposerKt.sourceInformationMarkerStart(composer, 844473419, str5);
                                        MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically2, composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                                        CompositionLocalMap currentCompositionLocalMap5 = composer.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier5 = ComposedModifierKt.materializeModifier(composer, m820paddingqDBjuR0$default3);
                                        Function0 constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                                        int i17 = ((((438 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer.startReusableNode();
                                        if (composer.getInserting()) {
                                            function018 = constructor5;
                                            composer.createNode(function018);
                                        } else {
                                            function018 = constructor5;
                                            composer.useNode();
                                        }
                                        Composer m4364constructorimpl5 = Updater.m4364constructorimpl(composer);
                                        composer2 = $composer3;
                                        Updater.m4372setimpl(m4364constructorimpl5, rowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4372setimpl(m4364constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Updater.m4368initimpl(m4364constructorimpl5, Integer.valueOf(hashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                        Updater.m4370reconcileimpl(m4364constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                        Updater.m4372setimpl(m4364constructorimpl5, materializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i18 = (i17 >> 6) & 14;
                                        str4 = "C101@5233L9:Row.kt#2w3rfo";
                                        ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str4);
                                        RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                                        int i19 = ((438 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer, -867246901, "C409@20090L56,412@20257L33,410@20167L289:RouteScreen.kt#cef2n4");
                                        WayfindrComponentsKt.Eyebrow("Rota durakları · " + RouteScreen$lambda$0(routeState$delegate2).getWaypoints().size(), null, composer, 0, 2);
                                        ComposerKt.sourceInformationMarkerStart(composer, -1551991349, "CC(remember):RouteScreen.kt#9igjgp");
                                        boolean z11 = ((($dirty5 & 896) ^ 384) > 256 && $composer3.changedInstance(routeViewModel8)) || ($dirty5 & 384) == 256;
                                        Object rememberedValue28 = composer.rememberedValue();
                                        if (z11) {
                                        }
                                        Object obj10 = (KFunction) new RouteScreenKt$RouteScreen$5$2$5$1$1(routeViewModel8);
                                        composer.updateRememberedValue(obj10);
                                        rememberedValue28 = obj10;
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        WayfindrComponentsKt.WayfindrBtn("Temizle", (Function0) ((KFunction) rememberedValue28), null, WayfindrIcons.INSTANCE.getX(), BtnVariant.Ghost, BtnSize.Sm, false, composer, 221190, 68);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        composer.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(6), null, ComposableLambdaKt.rememberComposableLambda(-1714951167, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda26
                                            @Override // kotlin.jvm.functions.Function3
                                            public final Object invoke(Object obj11, Object obj12, Object obj13) {
                                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$94(State.this, routeViewModel8, (ColumnScope) obj11, (Composer) obj12, ((Integer) obj13).intValue());
                                            }
                                        }, composer, 54), composer, 24966, 10);
                                    } else {
                                        composer2 = $composer3;
                                        str4 = "C101@5233L9:Row.kt#2w3rfo";
                                        str5 = "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo";
                                        composer.startReplaceGroup(1996034903);
                                    }
                                    composer.endReplaceGroup();
                                    if (((dest == null ? dest.getLat() : null) != null || dest.getLng() == null) && !RouteScreen$lambda$0(routeState$delegate2).isResolving()) {
                                        composer.startReplaceGroup(2018241319);
                                        ComposerKt.sourceInformation(composer, "438@22367L1104");
                                        WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(16), null, ComposableSingletons$RouteScreenKt.INSTANCE.m8364getLambda$835303176$app(), composer, 24966, 10);
                                    } else {
                                        composer.startReplaceGroup(1996034903);
                                    }
                                    composer.endReplaceGroup();
                                    WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(routeState$delegate2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(6), 7, null), composer, 48, 0);
                                    Alignment.Vertical top2 = Alignment.INSTANCE.getTop();
                                    ComposerKt.sourceInformationMarkerStart(composer, 844473419, str5);
                                    Modifier modifier = Modifier.INSTANCE;
                                    MeasurePolicy rowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top2, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                                    CompositionLocalMap currentCompositionLocalMap6 = composer.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier6 = ComposedModifierKt.materializeModifier(composer, modifier);
                                    Function0 constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i20 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer.startReusableNode();
                                    if (composer.getInserting()) {
                                        function04 = constructor6;
                                        composer.useNode();
                                    } else {
                                        function04 = constructor6;
                                        composer.createNode(function04);
                                    }
                                    Composer m4364constructorimpl6 = Updater.m4364constructorimpl(composer);
                                    Updater.m4372setimpl(m4364constructorimpl6, rowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl6, Integer.valueOf(hashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl6, materializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i21 = (i20 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str4);
                                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                                    int i22 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                                    Alignment center = Alignment.INSTANCE.getCenter();
                                    Modifier m262backgroundbw27NRU$default2 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(44)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getGoldTint(), null, 2, null);
                                    ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                                    ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                                    CompositionLocalMap currentCompositionLocalMap7 = composer.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier7 = ComposedModifierKt.materializeModifier(composer, m262backgroundbw27NRU$default2);
                                    Function0 constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i23 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer.startReusableNode();
                                    if (composer.getInserting()) {
                                        function05 = constructor7;
                                        composer.useNode();
                                    } else {
                                        function05 = constructor7;
                                        composer.createNode(function05);
                                    }
                                    Composer m4364constructorimpl7 = Updater.m4364constructorimpl(composer);
                                    Updater.m4372setimpl(m4364constructorimpl7, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl7, Integer.valueOf(hashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl7, materializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i24 = (i23 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    int i25 = ((48 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), ColorKt.getGold(), composer, 432, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    composer.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), composer, 6);
                                    ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Modifier modifier2 = Modifier.INSTANCE;
                                    MeasurePolicy columnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                                    CompositionLocalMap currentCompositionLocalMap8 = composer.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier8 = ComposedModifierKt.materializeModifier(composer, modifier2);
                                    Function0 constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i26 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer.startReusableNode();
                                    if (composer.getInserting()) {
                                        function06 = constructor8;
                                        composer.useNode();
                                    } else {
                                        function06 = constructor8;
                                        composer.createNode(function06);
                                    }
                                    Composer m4364constructorimpl8 = Updater.m4364constructorimpl(composer);
                                    Updater.m4372setimpl(m4364constructorimpl8, columnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl8, Integer.valueOf(hashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl8, materializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i27 = (i26 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                    int i28 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer, -1541055177, "C:RouteScreen.kt#cef2n4");
                                    if (RouteScreen$lambda$0(routeState$delegate2).isResolving()) {
                                        composer.startReplaceGroup(-1540849431);
                                        ComposerKt.sourceInformation(composer, "473@24415L132");
                                        String destName4 = destName;
                                        TextKt.m3069TextNvy7gAk(destName4, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(19), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.38d), null, null, 0L, 0, false, 0, 0, null, null, composer, 1597440, 0, 261674);
                                        destName2 = destName4;
                                        composer.endReplaceGroup();
                                    } else {
                                        composer.startReplaceGroup(-1541040670);
                                        ComposerKt.sourceInformation(composer, "471@24222L139");
                                        TextKt.m3069TextNvy7gAk("Çözümleniyor…", null, ColorKt.getTextLo(), null, TextUnitKt.getSp(19), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistFamily(), TextUnitKt.getSp(-0.38d), null, null, 0L, 0, false, 0, 0, null, null, composer, 1597446, 0, 261674);
                                        composer.endReplaceGroup();
                                        destName2 = destName;
                                    }
                                    if (StringsKt.isBlank(destSub2)) {
                                        composer.startReplaceGroup(-1540624650);
                                        ComposerKt.sourceInformation(composer, "476@24642L119");
                                        destName3 = destName2;
                                        TextKt.m3069TextNvy7gAk(destSub2, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.getTextMid(), null, TextUnitKt.getSp(13), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 24624, 0, 261992);
                                        destSub = destSub2;
                                    } else {
                                        destName3 = destName2;
                                        destSub = destSub2;
                                        composer.startReplaceGroup(-1565049891);
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
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), composer, 6);
                                    if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                                        composer.startReplaceGroup(2020849566);
                                        ComposerKt.sourceInformation(composer, "484@24934L3050,528@28001L30");
                                        Arrangement.Vertical m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
                                        ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                        Modifier modifier3 = Modifier.INSTANCE;
                                        Alignment.Horizontal start2 = Alignment.INSTANCE.getStart();
                                        MeasurePolicy columnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4, start2, composer, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                                        CompositionLocalMap currentCompositionLocalMap9 = composer.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier9 = ComposedModifierKt.materializeModifier(composer, modifier3);
                                        Function0 constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                                        int i29 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer.startReusableNode();
                                        if (composer.getInserting()) {
                                            function015 = constructor9;
                                            composer.createNode(function015);
                                        } else {
                                            function015 = constructor9;
                                            composer.useNode();
                                        }
                                        Composer m4364constructorimpl9 = Updater.m4364constructorimpl(composer);
                                        Updater.m4372setimpl(m4364constructorimpl9, columnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4372setimpl(m4364constructorimpl9, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Updater.m4368initimpl(m4364constructorimpl9, Integer.valueOf(hashCode9), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                        Updater.m4370reconcileimpl(m4364constructorimpl9, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                        Updater.m4372setimpl(m4364constructorimpl9, materializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i30 = (i29 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                        ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                        int i31 = ((48 >> 6) & 112) | 6;
                                        Composer composer7 = composer;
                                        ComposerKt.sourceInformationMarkerStart(composer7, 2004497286, "C485@25013L103:RouteScreen.kt#cef2n4");
                                        WayfindrComponentsKt.Eyebrow("OSM rotaları · " + RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() + " seçenek", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(2), 7, null), composer7, 48, 0);
                                        composer7.startReplaceGroup(-1736449372);
                                        ComposerKt.sourceInformation(composer7, "*493@25684L37,488@25302L2642");
                                        Iterable routeAlternatives3 = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
                                        int i32 = 0;
                                        final int i33 = 0;
                                        Iterator it2 = routeAlternatives3.iterator();
                                        while (it2.hasNext()) {
                                            Object next = it2.next();
                                            int i34 = i33 + 1;
                                            if (i33 < 0) {
                                                CollectionsKt.throwIndexOverflow();
                                            }
                                            Route route3 = (Route) next;
                                            Iterable iterable = routeAlternatives3;
                                            boolean z12 = i33 == RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
                                            int i35 = i32;
                                            Iterator it3 = it2;
                                            Alignment.Horizontal horizontal2 = start2;
                                            MutableState showSave$delegate4 = showSave$delegate;
                                            Composer composer8 = composer;
                                            Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), z12 ? ColorKt.getSurf3() : ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), z12 ? ColorKt.getGold() : ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12)));
                                            boolean z13 = !RouteScreen$lambda$0(routeState$delegate2).getRouting();
                                            ComposerKt.sourceInformationMarkerStart(composer7, -1258408201, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changed4 = (((($dirty5 & 896) ^ 384) > 256 && $composer3.changedInstance(routeViewModel8)) || ($dirty5 & 384) == 256) | $composer3.changed(i33);
                                            Composer composer9 = composer7;
                                            Object rememberedValue29 = composer9.rememberedValue();
                                            if (changed4 || rememberedValue29 == Composer.INSTANCE.getEmpty()) {
                                                Object obj11 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda27
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$103$lambda$102$lambda$99$lambda$98(RouteViewModel.this, i33);
                                                    }
                                                };
                                                composer9.updateRememberedValue(obj11);
                                                rememberedValue29 = obj11;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer7);
                                            Modifier m817paddingVpY3zN42 = PaddingKt.m817paddingVpY3zN4(ClickableKt.m297clickableoSLSa3U$default(m273borderxT4_qwU, z13, null, null, null, (Function0) rememberedValue29, 14, null), Dp.m7908constructorimpl(14), Dp.m7908constructorimpl(12));
                                            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                                            Composer composer10 = composer7;
                                            ComposerKt.sourceInformationMarkerStart(composer10, 844473419, str5);
                                            MeasurePolicy rowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composer10, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer10, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer10, 0));
                                            CompositionLocalMap currentCompositionLocalMap10 = composer10.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier10 = ComposedModifierKt.materializeModifier(composer10, m817paddingVpY3zN42);
                                            Function0 constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i36 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer10, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer10.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer10.startReusableNode();
                                            if (composer10.getInserting()) {
                                                function016 = constructor10;
                                                composer10.createNode(function016);
                                            } else {
                                                function016 = constructor10;
                                                composer10.useNode();
                                            }
                                            Composer m4364constructorimpl10 = Updater.m4364constructorimpl(composer10);
                                            Composer composer11 = composer7;
                                            Updater.m4372setimpl(m4364constructorimpl10, rowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl10, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl10, Integer.valueOf(hashCode10), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl10, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl10, materializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i37 = (i36 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer10, 1456264949, str4);
                                            int i38 = ((384 >> 6) & 112) | 6;
                                            RowScope rowScope = RowScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(composer10, 1033834161, "C497@25930L248,502@26207L29,503@26265L1387:RouteScreen.kt#cef2n4");
                                            BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(4)), Dp.m7908constructorimpl(34)), RoundedCornerShapeKt.getCircleShape()), RouteAlternativeStyle.INSTANCE.m8356composeColorvNxB06k(i33), null, 2, null), composer10, 0);
                                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), composer10, 6);
                                            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                                            ComposerKt.sourceInformationMarkerStart(composer10, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            MeasurePolicy columnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer10, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer10, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer10, 0));
                                            CompositionLocalMap currentCompositionLocalMap11 = composer10.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier11 = ComposedModifierKt.materializeModifier(composer10, weight$default);
                                            Function0 constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i39 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer10, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer10.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer10.startReusableNode();
                                            if (composer10.getInserting()) {
                                                function017 = constructor11;
                                                composer10.createNode(function017);
                                            } else {
                                                function017 = constructor11;
                                                composer10.useNode();
                                            }
                                            Composer m4364constructorimpl11 = Updater.m4364constructorimpl(composer10);
                                            Updater.m4372setimpl(m4364constructorimpl11, columnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl11, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl11, Integer.valueOf(hashCode11), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl11, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl11, materializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i40 = (i39 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer10, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                                            int i41 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer10, 1158178236, "C504@26327L321,509@26681L352:RouteScreen.kt#cef2n4");
                                            TextKt.m3069TextNvy7gAk(i33 == 0 ? "Ana rota" : "Alternatif " + i33, null, z12 ? ColorKt.getTextHi() : ColorKt.getTextMid(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer10, 1597440, 0, 261930);
                                            TextKt.m3069TextNvy7gAk(formatAlternativeDistance(route3.getTotalMeters()) + " · " + formatAlternativeDuration(route3.getTotalSeconds()), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(3), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer10, 24624, 0, 261992);
                                            Composer composer12 = composer10;
                                            if (i33 > 0) {
                                                composer12.startReplaceGroup(1158902829);
                                                ComposerKt.sourceInformation(composer12, "516@27203L385");
                                                Route route4 = (Route) CollectionsKt.first((List) RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives());
                                                TextKt.m3069TextNvy7gAk("Ana rotaya göre " + RouteAlternativeStyleKt.routeDifferenceLabel(route3.getTotalMeters(), route3.getTotalSeconds(), route4.getTotalMeters(), route4.getTotalSeconds()), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(10), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer12, 24624, 0, 261992);
                                                composer12 = composer12;
                                            } else {
                                                composer12.startReplaceGroup(1132020714);
                                            }
                                            composer12.endReplaceGroup();
                                            ComposerKt.sourceInformationMarkerEnd(composer12);
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            composer10.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            if (z12) {
                                                composer10.startReplaceGroup(1035526078);
                                                ComposerKt.sourceInformation(composer10, "523@27695L86");
                                                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCheck(), "Seçili rota", SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), ColorKt.getGold(), composer10, 432, 0);
                                                composer10.endReplaceGroup();
                                                composer6 = composer10;
                                            } else {
                                                composer10.startReplaceGroup(1141786581);
                                                ComposerKt.sourceInformation(composer10, "524@27815L103");
                                                TextKt.m3069TextNvy7gAk("Seç", null, ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer10, 1597446, 0, 261930);
                                                composer6 = composer10;
                                                composer6.endReplaceGroup();
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer6);
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            composer10.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            ComposerKt.sourceInformationMarkerEnd(composer10);
                                            i33 = i34;
                                            i32 = i35;
                                            routeAlternatives3 = iterable;
                                            it2 = it3;
                                            start2 = horizontal2;
                                            composer = composer8;
                                            showSave$delegate = showSave$delegate4;
                                            composer7 = composer11;
                                        }
                                        showSave$delegate2 = showSave$delegate;
                                        Composer composer13 = composer7;
                                        composer13.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer13);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        composer.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        ComposerKt.sourceInformationMarkerEnd(composer);
                                        composer = composer;
                                        SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), composer, 6);
                                    } else {
                                        showSave$delegate2 = showSave$delegate;
                                        composer.startReplaceGroup(1996034903);
                                    }
                                    composer.endReplaceGroup();
                                    routing = RouteScreen$lambda$0(routeState$delegate2).getRouting();
                                    Triple[] tripleArr = new Triple[3];
                                    if (routing) {
                                        distanceText = RouteScreen$lambda$0(routeState$delegate2).getDistanceText();
                                        if (distanceText == null) {
                                            distanceText = "—";
                                        }
                                    } else {
                                        distanceText = "…";
                                    }
                                    tripleArr[0] = new Triple(distanceText, "", "Mesafe");
                                    if (routing) {
                                        durationText = RouteScreen$lambda$0(routeState$delegate2).getDurationText();
                                        if (durationText == null) {
                                            durationText = "—";
                                        }
                                    } else {
                                        durationText = "…";
                                    }
                                    tripleArr[1] = new Triple(durationText, "", "Süre");
                                    if (routing) {
                                        etaText = RouteScreen$lambda$0(routeState$delegate2).getEtaText();
                                        if (etaText == null) {
                                            etaText = "—";
                                        }
                                    } else {
                                        etaText = "…";
                                    }
                                    tripleArr[2] = new Triple(etaText, "", "Varış");
                                    Iterable listOf = CollectionsKt.listOf((Object[]) tripleArr);
                                    Arrangement.Horizontal m686spacedBy0680j_42 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                                    composer3 = composer;
                                    int i42 = 0;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str5);
                                    Modifier modifier4 = Modifier.INSTANCE;
                                    MeasurePolicy rowMeasurePolicy5 = RowKt.rowMeasurePolicy(m686spacedBy0680j_42, Alignment.INSTANCE.getTop(), composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                                    CompositionLocalMap currentCompositionLocalMap12 = composer3.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier12 = ComposedModifierKt.materializeModifier(composer3, modifier4);
                                    Function0 constructor12 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i43 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        function07 = constructor12;
                                        composer3.useNode();
                                    } else {
                                        function07 = constructor12;
                                        composer3.createNode(function07);
                                    }
                                    Composer m4364constructorimpl12 = Updater.m4364constructorimpl(composer3);
                                    MeasurePolicy measurePolicy = rowMeasurePolicy5;
                                    Updater.m4372setimpl(m4364constructorimpl12, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl12, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl12, Integer.valueOf(hashCode12), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl12, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl12, materializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i44 = (i43 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str4);
                                    int i45 = ((48 >> 6) & 112) | 6;
                                    RowScope rowScope2 = RowScopeInstance.INSTANCE;
                                    Composer composer14 = composer3;
                                    ComposerKt.sourceInformationMarkerStart(composer14, 213090107, "C:RouteScreen.kt#cef2n4");
                                    composer14.startReplaceGroup(1807989502);
                                    ComposerKt.sourceInformation(composer14, "*540@28620L859");
                                    Iterable<Triple> iterable2 = listOf;
                                    int i46 = 0;
                                    for (Triple triple : iterable2) {
                                        String str8 = (String) triple.component1();
                                        String str9 = (String) triple.component2();
                                        Iterable iterable3 = iterable2;
                                        String str10 = (String) triple.component3();
                                        int i47 = i46;
                                        Composer composer15 = composer14;
                                        Modifier modifier5 = materializeModifier12;
                                        Composer composer16 = composer3;
                                        int i48 = i42;
                                        MeasurePolicy measurePolicy2 = measurePolicy;
                                        Modifier m816padding3ABfNKs = PaddingKt.m816padding3ABfNKs(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(13));
                                        ComposerKt.sourceInformationMarkerStart(composer15, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                        MeasurePolicy columnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer15, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer15, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer15, 0));
                                        CompositionLocalMap currentCompositionLocalMap13 = composer15.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier13 = ComposedModifierKt.materializeModifier(composer15, m816padding3ABfNKs);
                                        Function0 constructor13 = ComposeUiNode.INSTANCE.getConstructor();
                                        int i49 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer15, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer15.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer15.startReusableNode();
                                        if (composer15.getInserting()) {
                                            function013 = constructor13;
                                            composer15.createNode(function013);
                                        } else {
                                            function013 = constructor13;
                                            composer15.useNode();
                                        }
                                        Composer m4364constructorimpl13 = Updater.m4364constructorimpl(composer15);
                                        Updater.m4372setimpl(m4364constructorimpl13, columnMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4372setimpl(m4364constructorimpl13, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Updater.m4368initimpl(m4364constructorimpl13, Integer.valueOf(hashCode13), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                        Updater.m4370reconcileimpl(m4364constructorimpl13, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                        Updater.m4372setimpl(m4364constructorimpl13, materializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i50 = (i49 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer15, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                        ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                                        int i51 = ((0 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer15, -293938655, "C548@28988L404,553@29417L40:RouteScreen.kt#cef2n4");
                                        Alignment.Vertical bottom = Alignment.INSTANCE.getBottom();
                                        ComposerKt.sourceInformationMarkerStart(composer15, 844473419, str5);
                                        Modifier modifier6 = Modifier.INSTANCE;
                                        MeasurePolicy rowMeasurePolicy6 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), bottom, composer15, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer15, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer15, 0));
                                        CompositionLocalMap currentCompositionLocalMap14 = composer15.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier14 = ComposedModifierKt.materializeModifier(composer15, modifier6);
                                        Function0 constructor14 = ComposeUiNode.INSTANCE.getConstructor();
                                        int i52 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer15, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer15.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer15.startReusableNode();
                                        if (composer15.getInserting()) {
                                            function014 = constructor14;
                                            composer15.createNode(function014);
                                        } else {
                                            function014 = constructor14;
                                            composer15.useNode();
                                        }
                                        Composer m4364constructorimpl14 = Updater.m4364constructorimpl(composer15);
                                        Updater.m4372setimpl(m4364constructorimpl14, rowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4372setimpl(m4364constructorimpl14, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Updater.m4368initimpl(m4364constructorimpl14, Integer.valueOf(hashCode14), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                        Updater.m4370reconcileimpl(m4364constructorimpl14, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                        Updater.m4372setimpl(m4364constructorimpl14, materializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i53 = (i52 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer15, 1456264949, str4);
                                        RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                                        int i54 = ((384 >> 6) & 112) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer15, 665601096, "C549@29060L105,550@29194L28,551@29251L115:RouteScreen.kt#cef2n4");
                                        TextKt.m3069TextNvy7gAk(str8, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(22), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer15, 1597440, 0, 261930);
                                        SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(4)), composer15, 6);
                                        TextKt.m3069TextNvy7gAk(str9, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(2), 7, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(11), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer15, 24624, 0, 261992);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        composer15.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        WayfindrComponentsKt.Eyebrow(str10, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(4), 0.0f, 0.0f, 13, null), composer15, 48, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        composer15.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        ComposerKt.sourceInformationMarkerEnd(composer15);
                                        composer14 = composer15;
                                        i42 = i48;
                                        materializeModifier12 = modifier5;
                                        composer3 = composer16;
                                        iterable2 = iterable3;
                                        i46 = i47;
                                        measurePolicy = measurePolicy2;
                                    }
                                    Composer composer17 = composer14;
                                    composer17.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(composer17);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), composer, 6);
                                    collectAsState = SnapshotStateKt.collectAsState(dashConfig.getRoutingSource(), null, composer, 0, 1);
                                    State collectAsState2 = SnapshotStateKt.collectAsState(dashConfig.getMapTheme(), null, composer, 0, 1);
                                    ComposerKt.sourceInformationMarkerStart(composer, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                                    Composer composer18 = composer;
                                    rememberedValue5 = composer18.rememberedValue();
                                    if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                                        ctx3 = ctx5;
                                        Object valueOf3 = Boolean.valueOf(OfflineRouter.INSTANCE.isInstalled(ctx3));
                                        composer18.updateRememberedValue(valueOf3);
                                        rememberedValue5 = valueOf3;
                                    } else {
                                        ctx3 = ctx5;
                                    }
                                    boolean booleanValue = ((Boolean) rememberedValue5).booleanValue();
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                                        case 1:
                                            str6 = "Motosiklet · canlı trafik";
                                            break;
                                        case 2:
                                            str6 = "Ücretsiz sunucu · internet gerekir";
                                            break;
                                        case 3:
                                            if (booleanValue) {
                                                str6 = "Telefonda · internet gerekmez";
                                                break;
                                            } else {
                                                str6 = "BRouter uygulaması yüklü değil";
                                                break;
                                            }
                                        default:
                                            throw new NoWhenBranchMatchedException();
                                    }
                                    ChoiceHeader("Rota sağlayıcısı", str6, composer, 6);
                                    Iterable available = RoutingSource.INSTANCE.getAvailable();
                                    Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available, 10));
                                    it = available.iterator();
                                    while (it.hasNext()) {
                                        arrayList3.add(((RoutingSource) it.next()).getLabel());
                                        available = available;
                                    }
                                    ArrayList arrayList4 = (List) arrayList3;
                                    String label = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                                    ComposerKt.sourceInformationMarkerStart(composer, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                                    changed2 = $composer3.changed(collectAsState) | $composer3.changedInstance(dashConfig) | (((($dirty5 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel8)) || ($dirty5 & 384) == 256);
                                    Composer composer19 = composer;
                                    composer4 = composer;
                                    Object rememberedValue30 = composer19.rememberedValue();
                                    if (changed2) {
                                    }
                                    rememberedValue30 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda5
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj12) {
                                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig.this, routeViewModel8, collectAsState, (String) obj12);
                                        }
                                    };
                                    composer19.updateRememberedValue(rememberedValue30);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    WayfindrComponentsKt.WayfindrSegmented(arrayList4, label, (Function1) rememberedValue30, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), composer4, 3072, 0);
                                    ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer4, 54);
                                    Modifier m820paddingqDBjuR0$default4 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null);
                                    Arrangement.Vertical m686spacedBy0680j_43 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
                                    int i55 = 0;
                                    ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    MeasurePolicy columnMeasurePolicy7 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_43, Alignment.INSTANCE.getStart(), composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                                    CompositionLocalMap currentCompositionLocalMap15 = composer4.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier15 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default4);
                                    Function0 constructor15 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i56 = ((((54 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer4.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        function08 = constructor15;
                                        composer4.useNode();
                                    } else {
                                        function08 = constructor15;
                                        composer4.createNode(function08);
                                    }
                                    Composer m4364constructorimpl15 = Updater.m4364constructorimpl(composer4);
                                    MeasurePolicy measurePolicy3 = columnMeasurePolicy7;
                                    Updater.m4372setimpl(m4364constructorimpl15, measurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl15, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl15, Integer.valueOf(hashCode15), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl15, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl15, materializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i57 = (i56 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                                    int i58 = ((54 >> 6) & 112) | 6;
                                    Composer composer20 = composer4;
                                    ComposerKt.sourceInformationMarkerStart(composer20, -1001505061, "C:RouteScreen.kt#cef2n4");
                                    composer20.startReplaceGroup(-1140683288);
                                    ComposerKt.sourceInformation(composer20, "*585@31259L1578");
                                    Iterable<List> chunked = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                                    int i59 = 0;
                                    for (List list4 : chunked) {
                                        Iterable iterable4 = chunked;
                                        int i60 = i59;
                                        Composer composer21 = composer20;
                                        Arrangement.Horizontal m686spacedBy0680j_44 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
                                        Modifier modifier7 = materializeModifier15;
                                        Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                        ComposerKt.sourceInformationMarkerStart(composer21, 844473419, str5);
                                        MeasurePolicy rowMeasurePolicy7 = RowKt.rowMeasurePolicy(m686spacedBy0680j_44, Alignment.INSTANCE.getTop(), composer21, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                                        ComposerKt.sourceInformationMarkerStart(composer21, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                        int hashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer21, 0));
                                        CompositionLocalMap currentCompositionLocalMap16 = composer21.getCurrentCompositionLocalMap();
                                        Modifier materializeModifier16 = ComposedModifierKt.materializeModifier(composer21, fillMaxWidth$default);
                                        Function0 constructor16 = ComposeUiNode.INSTANCE.getConstructor();
                                        int i61 = ((((54 << 3) & 112) << 6) & 896) | 6;
                                        ComposerKt.sourceInformationMarkerStart(composer21, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                        if (!(composer21.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer21.startReusableNode();
                                        if (composer21.getInserting()) {
                                            function011 = constructor16;
                                            composer21.createNode(function011);
                                        } else {
                                            function011 = constructor16;
                                            composer21.useNode();
                                        }
                                        Composer m4364constructorimpl16 = Updater.m4364constructorimpl(composer21);
                                        MeasurePolicy measurePolicy4 = measurePolicy3;
                                        MeasurePolicy measurePolicy5 = rowMeasurePolicy7;
                                        Updater.m4372setimpl(m4364constructorimpl16, measurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m4372setimpl(m4364constructorimpl16, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Updater.m4368initimpl(m4364constructorimpl16, Integer.valueOf(hashCode16), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                        Updater.m4370reconcileimpl(m4364constructorimpl16, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                        Updater.m4372setimpl(m4364constructorimpl16, materializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
                                        int i62 = (i61 >> 6) & 14;
                                        ComposerKt.sourceInformationMarkerStart(composer21, 1456264949, str4);
                                        int i63 = ((54 >> 6) & 112) | 6;
                                        RowScope rowScope3 = RowScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer21, -114499861, "C:RouteScreen.kt#cef2n4");
                                        composer21.startReplaceGroup(-3693311);
                                        ComposerKt.sourceInformation(composer21, "*593@31909L33,588@31495L1217");
                                        List<MapTheme> list5 = list4;
                                        int i64 = 0;
                                        for (final MapTheme mapTheme : list5) {
                                            Iterable iterable5 = list5;
                                            int i65 = i64;
                                            boolean z14 = mapTheme == RouteScreen$lambda$144$lambda$143$lambda$109(collectAsState2);
                                            Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                                            boolean z15 = z14;
                                            Modifier modifier8 = materializeModifier16;
                                            MeasurePolicy measurePolicy6 = measurePolicy5;
                                            int $dirty6 = $dirty5;
                                            Context ctx6 = ctx3;
                                            int i66 = i55;
                                            Modifier m273borderxT4_qwU2 = BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope3, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), z15 ? ColorKt.getSurf3() : ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), z15 ? ColorKt.getGold() : ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12)));
                                            ComposerKt.sourceInformationMarkerStart(composer21, -752154161, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changedInstance6 = composer21.changedInstance(dashConfig) | composer21.changed(mapTheme.ordinal());
                                            Object rememberedValue31 = composer21.rememberedValue();
                                            if (changedInstance6 || rememberedValue31 == Composer.INSTANCE.getEmpty()) {
                                                Object obj12 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda6
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$121$lambda$120$lambda$119$lambda$118$lambda$116$lambda$115(DashConfig.this, mapTheme);
                                                    }
                                                };
                                                composer21.updateRememberedValue(obj12);
                                                rememberedValue31 = obj12;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            Modifier m817paddingVpY3zN43 = PaddingKt.m817paddingVpY3zN4(ClickableKt.m297clickableoSLSa3U$default(m273borderxT4_qwU2, false, null, null, null, (Function0) rememberedValue31, 15, null), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(11));
                                            ComposerKt.sourceInformationMarkerStart(composer21, 844473419, str5);
                                            MeasurePolicy rowMeasurePolicy8 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composer21, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer21, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode17 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer21, 0));
                                            CompositionLocalMap currentCompositionLocalMap17 = composer21.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier17 = ComposedModifierKt.materializeModifier(composer21, m817paddingVpY3zN43);
                                            Function0 constructor17 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i67 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer21, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer21.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer21.startReusableNode();
                                            if (composer21.getInserting()) {
                                                function012 = constructor17;
                                                composer21.createNode(function012);
                                            } else {
                                                function012 = constructor17;
                                                composer21.useNode();
                                            }
                                            Composer m4364constructorimpl17 = Updater.m4364constructorimpl(composer21);
                                            Updater.m4372setimpl(m4364constructorimpl17, rowMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl17, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl17, Integer.valueOf(hashCode17), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl17, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl17, materializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i68 = (i67 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer21, 1456264949, str4);
                                            RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                                            int i69 = ((384 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer21, -433335358, "C596@32091L208,600@32332L28,601@32393L289:RouteScreen.kt#cef2n4");
                                            BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), RoundedCornerShapeKt.getCircleShape()), z15 ? ColorKt.getGold() : ColorKt.getTextLo(), null, 2, null), composer21, 0);
                                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), composer21, 6);
                                            TextKt.m3069TextNvy7gAk(mapTheme.getLabel(), null, z15 ? ColorKt.getTextHi() : ColorKt.getTextMid(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, TextOverflow.INSTANCE.m7819getEllipsisgIe3tQ8(), false, 1, 0, null, null, composer21, 1597440, 24960, 241450);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            composer21.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            ComposerKt.sourceInformationMarkerEnd(composer21);
                                            list5 = iterable5;
                                            i64 = i65;
                                            materializeModifier16 = modifier8;
                                            measurePolicy5 = measurePolicy6;
                                            $dirty5 = $dirty6;
                                            ctx3 = ctx6;
                                            i55 = i66;
                                        }
                                        int $dirty7 = $dirty5;
                                        Context ctx7 = ctx3;
                                        int i70 = i55;
                                        composer21.endReplaceGroup();
                                        if (list4.size() == 1) {
                                            composer21.startReplaceGroup(-3649803);
                                            ComposerKt.sourceInformation(composer21, "606@32788L27");
                                            SpacerKt.Spacer(RowScope.weight$default(rowScope3, Modifier.INSTANCE, 1.0f, false, 2, null), composer21, 0);
                                        } else {
                                            composer21.startReplaceGroup(-145671416);
                                        }
                                        composer21.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer21);
                                        ComposerKt.sourceInformationMarkerEnd(composer21);
                                        composer21.endNode();
                                        ComposerKt.sourceInformationMarkerEnd(composer21);
                                        ComposerKt.sourceInformationMarkerEnd(composer21);
                                        ComposerKt.sourceInformationMarkerEnd(composer21);
                                        chunked = iterable4;
                                        i59 = i60;
                                        composer20 = composer21;
                                        materializeModifier15 = modifier7;
                                        measurePolicy3 = measurePolicy4;
                                        $dirty5 = $dirty7;
                                        ctx3 = ctx7;
                                        i55 = i70;
                                    }
                                    Composer composer22 = composer20;
                                    int $dirty8 = $dirty5;
                                    final Context ctx8 = ctx3;
                                    composer22.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(composer22);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    composer4.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    Arrangement.Horizontal spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
                                    Alignment.Vertical centerVertically5 = Alignment.INSTANCE.getCenterVertically();
                                    Modifier m820paddingqDBjuR0$default5 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(9), 7, null);
                                    ComposerKt.sourceInformationMarkerStart(composer4, 844473419, str5);
                                    MeasurePolicy rowMeasurePolicy9 = RowKt.rowMeasurePolicy(spaceBetween2, centerVertically5, composer4, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode18 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                                    CompositionLocalMap currentCompositionLocalMap18 = composer4.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier18 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default5);
                                    Function0 constructor18 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i71 = ((((438 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!(composer4.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer4.startReusableNode();
                                    if (composer4.getInserting()) {
                                        function09 = constructor18;
                                        composer4.useNode();
                                    } else {
                                        function09 = constructor18;
                                        composer4.createNode(function09);
                                    }
                                    Composer m4364constructorimpl18 = Updater.m4364constructorimpl(composer4);
                                    Updater.m4372setimpl(m4364constructorimpl18, rowMeasurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl18, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl18, Integer.valueOf(hashCode18), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl18, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl18, materializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i72 = (i71 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, str4);
                                    RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                                    int i73 = ((438 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart(composer4, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                                    WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", null, composer4, 6, 2);
                                    IconKt.m2517Iconww6aTOc(RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer4, 432, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    composer4.endNode();
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    List listOf2 = CollectionsKt.listOf((Object[]) new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                                    ComposerKt.sourceInformationMarkerStart(composer4, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                                    final VoiceManager voiceManager3 = voiceManager;
                                    changedInstance4 = composer4.changedInstance(voiceManager3);
                                    rememberedValue6 = composer4.rememberedValue();
                                    if (!changedInstance4 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                        Object obj13 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda7
                                            @Override // kotlin.jvm.functions.Function1
                                            public final Object invoke(Object obj14) {
                                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager.this, (String) obj14);
                                            }
                                        };
                                        composer4.updateRememberedValue(obj13);
                                        rememberedValue6 = obj13;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    WayfindrComponentsKt.WayfindrSegmented(listOf2, voice, (Function1) rememberedValue6, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null), composer4, 3078, 0);
                                    z6 = ((dest == null ? dest.getLat() : null) != null || dest.getLng() == null || RouteScreen$lambda$0(routeState$delegate2).isResolving() || RouteScreen$lambda$0(routeState$delegate2).getRouting()) ? false : true;
                                    String str11 = !RouteScreen$lambda$7(sent$delegate3) ? "Navigasyon başlatılıyor…" : RouteScreen$lambda$0(routeState$delegate2).isResolving() ? "Hedef çözümleniyor…" : RouteScreen$lambda$0(routeState$delegate2).getRouting() ? "Rota aranıyor…" : "Navigasyonu başlat";
                                    ImageVector check = !RouteScreen$lambda$7(sent$delegate3) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                                    BtnVariant btnVariant2 = !RouteScreen$lambda$7(sent$delegate3) ? BtnVariant.Secondary : BtnVariant.Primary;
                                    BtnSize btnSize2 = BtnSize.Lg;
                                    boolean z16 = RouteScreen$lambda$7(sent$delegate3) && z6;
                                    Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    ComposerKt.sourceInformationMarkerStart(composer4, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                                    changedInstance5 = composer4.changedInstance(ctx8) | composer4.changedInstance(navPermLauncher3) | composer4.changedInstance(navPermissions);
                                    Object rememberedValue32 = composer4.rememberedValue();
                                    if (!changedInstance5 && rememberedValue32 != Composer.INSTANCE.getEmpty()) {
                                        z7 = z6;
                                        sent$delegate = sent$delegate3;
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue32, fillMaxWidth$default2, check, btnVariant2, btnSize2, z16, composer4, 196992, 0);
                                        if (z7) {
                                            sent$delegate2 = showSave$delegate2;
                                            composer4.startReplaceGroup(1996034903);
                                        } else {
                                            composer4.startReplaceGroup(2030857482);
                                            ComposerKt.sourceInformation(composer4, "655@35108L30,658@35246L19,656@35155L318");
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), composer4, 6);
                                            ImageVector pin = WayfindrIcons.INSTANCE.getPin();
                                            BtnVariant btnVariant3 = BtnVariant.Ghost;
                                            BtnSize btnSize3 = BtnSize.Md;
                                            Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                            ComposerKt.sourceInformationMarkerStart(composer4, 481158142, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue33 = composer4.rememberedValue();
                                            if (rememberedValue33 == Composer.INSTANCE.getEmpty()) {
                                                sent$delegate2 = showSave$delegate2;
                                                Object obj14 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda9
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$128$lambda$127(MutableState.this);
                                                    }
                                                };
                                                composer4.updateRememberedValue(obj14);
                                                rememberedValue33 = obj14;
                                            } else {
                                                sent$delegate2 = showSave$delegate2;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                            WayfindrComponentsKt.WayfindrBtn("Bu hedefi kaydet", (Function0) rememberedValue33, fillMaxWidth$default3, pin, btnVariant3, btnSize3, false, composer4, 221622, 64);
                                        }
                                        composer4.endReplaceGroup();
                                        if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
                                            composer4.startReplaceGroup(2031417156);
                                            ComposerKt.sourceInformation(composer4, "668@35587L30,669@35634L568");
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), composer4, 6);
                                            Alignment.Vertical centerVertically6 = Alignment.INSTANCE.getCenterVertically();
                                            Arrangement.Horizontal spaceBetween3 = Arrangement.INSTANCE.getSpaceBetween();
                                            Modifier m820paddingqDBjuR0$default6 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7908constructorimpl(4), 0.0f, 0.0f, Dp.m7908constructorimpl(6), 6, null);
                                            ComposerKt.sourceInformationMarkerStart(composer4, 844473419, str5);
                                            MeasurePolicy rowMeasurePolicy10 = RowKt.rowMeasurePolicy(spaceBetween3, centerVertically6, composer4, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode19 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                                            CompositionLocalMap currentCompositionLocalMap19 = composer4.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier19 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default6);
                                            Function0 constructor19 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i74 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!(composer4.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer4.startReusableNode();
                                            if (composer4.getInserting()) {
                                                function010 = constructor19;
                                                composer4.createNode(function010);
                                            } else {
                                                function010 = constructor19;
                                                composer4.useNode();
                                            }
                                            Composer m4364constructorimpl19 = Updater.m4364constructorimpl(composer4);
                                            Updater.m4372setimpl(m4364constructorimpl19, rowMeasurePolicy10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl19, currentCompositionLocalMap19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl19, Integer.valueOf(hashCode19), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl19, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl19, materializeModifier19, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i75 = (i74 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, str4);
                                            RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
                                            int i76 = ((432 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart(composer4, 1317978541, "C674@35910L47,676@36119L50,675@35978L206:RouteScreen.kt#cef2n4");
                                            WayfindrComponentsKt.Eyebrow("Kayıtlı hedefler · " + RouteScreen$lambda$2(savedList$delegate).size(), null, composer4, 0, 2);
                                            String str12 = RouteScreen$lambda$22(showSavedDestinations$delegate2) ? "Gizle" : "Göster";
                                            long gold = ColorKt.getGold();
                                            long sp = TextUnitKt.getSp(12);
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart(composer4, 1427995222, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue34 = composer4.rememberedValue();
                                            if (rememberedValue34 == Composer.INSTANCE.getEmpty()) {
                                                composer5 = composer4;
                                                showSavedDestinations$delegate = showSavedDestinations$delegate2;
                                                Object obj15 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda10
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$131$lambda$130$lambda$129(MutableState.this);
                                                    }
                                                };
                                                composer4.updateRememberedValue(obj15);
                                                rememberedValue34 = obj15;
                                            } else {
                                                composer5 = composer4;
                                                showSavedDestinations$delegate = showSavedDestinations$delegate2;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            TextKt.m3069TextNvy7gAk(str12, PaddingKt.m816padding3ABfNKs(ClickableKt.m297clickableoSLSa3U$default(companion, false, null, null, null, (Function0) rememberedValue34, 15, null), Dp.m7908constructorimpl(6)), gold, null, sp, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer5, 24576, 0, 262120);
                                            ComposerKt.sourceInformationMarkerEnd(composer5);
                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                            composer4.endNode();
                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                            ComposerKt.sourceInformationMarkerEnd(composer4);
                                            if (RouteScreen$lambda$22(showSavedDestinations$delegate)) {
                                                composer4.startReplaceGroup(481192555);
                                                ComposerKt.sourceInformation(composer4, "678@36311L2367,678@36246L2432");
                                                z8 = true;
                                                final MutableState showSavedDestinations$delegate3 = showSavedDestinations$delegate;
                                                routeViewModel4 = routeViewModel8;
                                                sharing$delegate = sharing$delegate2;
                                                editing$delegate = editing$delegate2;
                                                WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), false, Dp.m7908constructorimpl(6), null, ComposableLambdaKt.rememberComposableLambda(-1756830890, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda11
                                                    @Override // kotlin.jvm.functions.Function3
                                                    public final Object invoke(Object obj16, Object obj17, Object obj18) {
                                                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$142(State.this, routeViewModel8, showSavedDestinations$delegate3, sharing$delegate2, editing$delegate2, (ColumnScope) obj16, (Composer) obj17, ((Integer) obj18).intValue());
                                                    }
                                                }, composer4, 54), composer4, 24966, 10);
                                            } else {
                                                routeViewModel4 = routeViewModel8;
                                                editing$delegate = editing$delegate2;
                                                sharing$delegate = sharing$delegate2;
                                                z8 = true;
                                                composer4.startReplaceGroup(1996034903);
                                            }
                                            composer4.endReplaceGroup();
                                        } else {
                                            routeViewModel4 = routeViewModel8;
                                            editing$delegate = editing$delegate2;
                                            sharing$delegate = sharing$delegate2;
                                            z8 = true;
                                            composer4.startReplaceGroup(1996034903);
                                        }
                                        composer4.endReplaceGroup();
                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        $composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer2);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        $composer3.endNode();
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        ComposerKt.sourceInformationMarkerEnd($composer3);
                                        if (RouteScreen$lambda$10(sent$delegate2)) {
                                            $dirty = $dirty8;
                                            $composer3.startReplaceGroup(-1665730781);
                                        } else {
                                            $composer3.startReplaceGroup(-2130703672);
                                            ComposerKt.sourceInformation($composer3, "723@38827L85,724@38934L20,721@38728L233");
                                            String sharedRouteName2 = RouteScreen$lambda$0(routeState$delegate2).getSharedRouteName();
                                            if (sharedRouteName2 == null) {
                                                sharedRouteName2 = destName3;
                                            }
                                            ComposerKt.sourceInformationMarkerStart($composer3, -2130700652, "CC(remember):RouteScreen.kt#9igjgp");
                                            $dirty = $dirty8;
                                            boolean z17 = (((($dirty & 896) ^ 384) <= 256 || !$composer3.changedInstance(routeViewModel4)) && ($dirty & 384) != 256) ? false : z8;
                                            Object rememberedValue35 = $composer3.rememberedValue();
                                            if (z17) {
                                            }
                                            Object obj16 = new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda12
                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(Object obj17, Object obj18) {
                                                    return RouteScreenKt.RouteScreen$lambda$146$lambda$145(RouteViewModel.this, sent$delegate2, (String) obj17, (String) obj18);
                                                }
                                            };
                                            $composer3.updateRememberedValue(obj16);
                                            rememberedValue35 = obj16;
                                            Function2 function2 = (Function2) rememberedValue35;
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            ComposerKt.sourceInformationMarkerStart($composer3, -2130697293, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue36 = $composer3.rememberedValue();
                                            if (rememberedValue36 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue36 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda13
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return RouteScreenKt.RouteScreen$lambda$148$lambda$147(MutableState.this);
                                                    }
                                                };
                                                $composer3.updateRememberedValue(rememberedValue36);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            SaveLocationDialog(sharedRouteName2, function2, (Function0) rememberedValue36, $composer3, 384);
                                        }
                                        $composer3.endReplaceGroup();
                                        RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
                                        if (RouteScreen$lambda$16 != null) {
                                            $composer3.startReplaceGroup(-1627064575);
                                            $composer3.endReplaceGroup();
                                        } else {
                                            $composer3.startReplaceGroup(-1627064574);
                                            ComposerKt.sourceInformation($composer3, "*726@39019L18,726@38988L50");
                                            ComposerKt.sourceInformationMarkerStart($composer3, 689785320, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue37 = $composer3.rememberedValue();
                                            if (rememberedValue37 == Composer.INSTANCE.getEmpty()) {
                                                rememberedValue37 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda14
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return RouteScreenKt.RouteScreen$lambda$151$lambda$150$lambda$149(MutableState.this);
                                                    }
                                                };
                                                $composer3.updateRememberedValue(rememberedValue37);
                                            }
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            RouteQrDialogKt.RouteQrDialog(RouteScreen$lambda$16, (Function0) rememberedValue37, $composer3, SavedLocation.$stable | 48);
                                            Unit unit6 = Unit.INSTANCE;
                                            $composer3.endReplaceGroup();
                                            Unit unit7 = Unit.INSTANCE;
                                        }
                                        RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
                                        if (RouteScreen$lambda$13 != null) {
                                            $composer3.startReplaceGroup(-1626978953);
                                            $composer3.endReplaceGroup();
                                            routeViewModel5 = routeViewModel4;
                                            $composer2 = $composer3;
                                        } else {
                                            $composer3.startReplaceGroup(-1626978952);
                                            ComposerKt.sourceInformation($composer3, "*730@39139L77,731@39241L51,732@39318L18,728@39075L272");
                                            ComposerKt.sourceInformationMarkerStart($composer3, -496465606, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changedInstance7 = $composer3.changedInstance(RouteScreen$lambda$13) | (((($dirty & 896) ^ 384) > 256 && $composer3.changedInstance(routeViewModel4)) || ($dirty & 384) == 256);
                                            Object rememberedValue38 = $composer3.rememberedValue();
                                            if (changedInstance7) {
                                            }
                                            Object obj17 = new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda16
                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(Object obj18, Object obj19) {
                                                    return RouteScreenKt.RouteScreen$lambda$158$lambda$153$lambda$152(RouteViewModel.this, RouteScreen$lambda$13, editing$delegate, (String) obj18, (String) obj19);
                                                }
                                            };
                                            $composer3.updateRememberedValue(obj17);
                                            rememberedValue38 = obj17;
                                            Function2 function22 = (Function2) rememberedValue38;
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            ComposerKt.sourceInformationMarkerStart($composer3, -496462368, "CC(remember):RouteScreen.kt#9igjgp");
                                            boolean changedInstance8 = (((($dirty & 896) ^ 384) > 256 && $composer3.changedInstance(routeViewModel4)) || ($dirty & 384) == 256) | $composer3.changedInstance(RouteScreen$lambda$13);
                                            Object rememberedValue39 = $composer3.rememberedValue();
                                            if (changedInstance8) {
                                                $dirty2 = $dirty;
                                            } else {
                                                $dirty2 = $dirty;
                                            }
                                            Object obj18 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda17
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    return RouteScreenKt.RouteScreen$lambda$158$lambda$155$lambda$154(RouteViewModel.this, RouteScreen$lambda$13, editing$delegate);
                                                }
                                            };
                                            $composer3.updateRememberedValue(obj18);
                                            rememberedValue39 = obj18;
                                            Function0 function019 = (Function0) rememberedValue39;
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            ComposerKt.sourceInformationMarkerStart($composer3, -496459937, "CC(remember):RouteScreen.kt#9igjgp");
                                            Object rememberedValue40 = $composer3.rememberedValue();
                                            routeViewModel5 = routeViewModel4;
                                            if (rememberedValue40 == Composer.INSTANCE.getEmpty()) {
                                                Object obj19 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda18
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return RouteScreenKt.RouteScreen$lambda$158$lambda$157$lambda$156(MutableState.this);
                                                    }
                                                };
                                                $composer3.updateRememberedValue(obj19);
                                                rememberedValue40 = obj19;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd($composer3);
                                            EditLocationDialog(RouteScreen$lambda$13, function22, function019, (Function0) rememberedValue40, $composer3, SavedLocation.$stable | 3072);
                                            $composer2 = $composer3;
                                            Unit unit8 = Unit.INSTANCE;
                                            $composer2.endReplaceGroup();
                                            Unit unit9 = Unit.INSTANCE;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                        routeViewModel2 = routeViewModel5;
                                    }
                                    z7 = z6;
                                    sent$delegate = sent$delegate3;
                                    Object obj20 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda8
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher.this, navPermissions, ctx8, sent$delegate);
                                        }
                                    };
                                    composer4.updateRememberedValue(obj20);
                                    rememberedValue32 = obj20;
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue32, fillMaxWidth$default2, check, btnVariant2, btnSize2, z16, composer4, 196992, 0);
                                    if (z7) {
                                    }
                                    composer4.endReplaceGroup();
                                    if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
                                    }
                                    composer4.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd(composer4);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    $composer3.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer3);
                                    if (RouteScreen$lambda$10(sent$delegate2)) {
                                    }
                                    $composer3.endReplaceGroup();
                                    RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
                                    if (RouteScreen$lambda$16 != null) {
                                    }
                                    RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
                                    if (RouteScreen$lambda$13 != null) {
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    routeViewModel2 = routeViewModel5;
                                }
                            }
                            Object obj21 = (Function2) new RouteScreenKt$RouteScreen$4$1(onSentToDash, sent$delegate3, routeState$delegate, null);
                            $composer3.updateRememberedValue(obj21);
                            rememberedValue24 = obj21;
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            EffectsKt.LaunchedEffect(valueOf2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue24, $composer3, 0);
                            Modifier fillMaxSize$default3 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Arrangement.Vertical top3 = Arrangement.INSTANCE.getTop();
                            Alignment.Horizontal start3 = Alignment.INSTANCE.getStart();
                            MeasurePolicy columnMeasurePolicy8 = ColumnKt.columnMeasurePolicy(top3, start3, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                            routeState$delegate2 = routeState$delegate;
                            Context ctx52 = ctx2;
                            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode20 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap20 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier20 = ComposedModifierKt.materializeModifier($composer3, fillMaxSize$default3);
                            Function0 constructor20 = ComposeUiNode.INSTANCE.getConstructor();
                            int i410 = ((((6 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            Composer m4364constructorimpl20 = Updater.m4364constructorimpl($composer3);
                            Updater.m4372setimpl(m4364constructorimpl20, columnMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl20, currentCompositionLocalMap20, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl20, Integer.valueOf(hashCode20), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl20, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl20, materializeModifier20, ComposeUiNode.INSTANCE.getSetModifier());
                            int i510 = (i410 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            int i610 = ((6 >> 6) & 112) | 6;
                            ColumnScope columnScope3 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                            Modifier m262backgroundbw27NRU$default3 = BackgroundKt.m262backgroundbw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.46f), ColorKt.getMapBase(), null, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap22 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier22 = ComposedModifierKt.materializeModifier($composer3, m262backgroundbw27NRU$default3);
                            Function0 constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                            int i77 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            Composer m4364constructorimpl22 = Updater.m4364constructorimpl($composer3);
                            Updater.m4372setimpl(m4364constructorimpl22, maybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl22, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl22, Integer.valueOf(hashCode22), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl22, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl22, materializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                            int i82 = (i77 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            int i92 = ((0 >> 6) & 112) | 6;
                            BoxScope boxScope2 = BoxScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                            Pair<Double, Double> currentLocation3 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                            if (currentLocation3 == null) {
                            }
                            Pair<Double, Double> currentLocation22 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                            if (currentLocation22 == null) {
                            }
                            float currentBearing2 = RouteScreen$lambda$0(routeState$delegate2).getCurrentBearing();
                            long locationRefreshKey2 = RouteScreen$lambda$0(routeState$delegate2).getLocationRefreshKey();
                            if (dest == null) {
                            }
                            Iterable<SharedLocation> waypoints2 = RouteScreen$lambda$0(routeState$delegate2).getWaypoints();
                            int i102 = 0;
                            Collection arrayList5 = new ArrayList();
                            while (r101.hasNext()) {
                            }
                            ArrayList arrayList22 = (List) arrayList5;
                            Route route5 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                            if (route5 == null) {
                            }
                            if (geometry == null) {
                            }
                            List<GeoPoint> list22 = geometry;
                            Route route22 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                            if (route22 == null) {
                            }
                            if (traffic == null) {
                            }
                            List<TrafficInterval> list32 = traffic;
                            routeAlternatives = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
                            list = routeAlternatives;
                            if (!(routeAlternatives.size() <= 1)) {
                            }
                            if (list == null) {
                            }
                            int selectedRouteIndex3 = RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
                            if (RouteScreen$lambda$0(routeState$delegate2).getRoute() == null) {
                            }
                            Modifier fillMaxSize$default22 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                            if ((($dirty3 & 896) ^ 384) <= 256) {
                            }
                            Object rememberedValue252 = $composer3.rememberedValue();
                            if (z4) {
                            }
                            Object obj72 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda22
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj82) {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel.this, (LatLng) obj82);
                                }
                            };
                            $composer3.updateRememberedValue(obj72);
                            rememberedValue252 = obj72;
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            FreeWayfindrMapKt.WayfindrMap(first, second, pair2, arrayList22, list22, list32, list, selectedRouteIndex3, true, fillMaxSize$default22, z9, false, currentBearing2, locationRefreshKey2, false, true, null, null, null, null, false, (Function1) rememberedValue252, $composer3, 905969664, 221184, 0, 2033664);
                            Alignment.Vertical centerVertically7 = Alignment.INSTANCE.getCenterVertically();
                            Modifier m817paddingVpY3zN44 = PaddingKt.m817paddingVpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope2.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopStart()), 0.0f, 1, null), Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(14));
                            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy11 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically7, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode32 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap32 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier32 = ComposedModifierKt.materializeModifier($composer3, m817paddingVpY3zN44);
                            Function0 constructor32 = ComposeUiNode.INSTANCE.getConstructor();
                            int i112 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            int $dirty42 = $dirty3;
                            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            Composer m4364constructorimpl32 = Updater.m4364constructorimpl($composer3);
                            Updater.m4372setimpl(m4364constructorimpl32, rowMeasurePolicy11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl32, currentCompositionLocalMap32, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl32, Integer.valueOf(hashCode32), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl32, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl32, materializeModifier32, ComposeUiNode.INSTANCE.getSetModifier());
                            int i122 = (i112 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance8 = RowScopeInstance.INSTANCE;
                            int i132 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                            final ManagedActivityResultLauncher navPermLauncher32 = navPermLauncher;
                            final ManagedActivityResultLauncher qrPicker32 = qrPicker;
                            int $dirty52 = $dirty42;
                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), onBack, BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, (($dirty42 << 3) & 112) | 3072, 48);
                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                            WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(routeState$delegate2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(routeState$delegate2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(routeState$delegate2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), $composer3, 0, 4);
                            ImageVector recenter2 = WayfindrIcons.INSTANCE.getRecenter();
                            ComposerKt.sourceInformationMarkerStart($composer3, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                            if ((($dirty52 & 896) ^ 384) <= 256) {
                            }
                            rememberedValue4 = $composer3.rememberedValue();
                            if (!z5) {
                            }
                            Object obj82 = (KFunction) new RouteScreenKt$RouteScreen$5$1$5$1$1(routeViewModel3);
                            $composer3.updateRememberedValue(obj82);
                            rememberedValue4 = obj82;
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter2, (Function0) ((KFunction) rememberedValue4), BackgroundKt.m261backgroundbw27NRU(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, 3072, 48);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                            }
                            $composer3.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            Modifier m820paddingqDBjuR0$default7 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m817paddingVpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope3, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), RoundedCornerShapeKt.m1126RoundedCornerShapea9UjIt4$default(Dp.m7908constructorimpl(22), Dp.m7908constructorimpl(22), 0.0f, 0.0f, 12, null)), ColorKt.getBg1(), null, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18), Dp.m7908constructorimpl(8)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(20), 7, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy22 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode42 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap42 = $composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier42 = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default7);
                            Function0 constructor42 = ComposeUiNode.INSTANCE.getConstructor();
                            int i142 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer3.getApplier() instanceof Applier)) {
                            }
                            $composer3.startReusableNode();
                            if ($composer3.getInserting()) {
                            }
                            Composer m4364constructorimpl42 = Updater.m4364constructorimpl($composer3);
                            Updater.m4372setimpl(m4364constructorimpl42, columnMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl42, currentCompositionLocalMap42, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl42, Integer.valueOf(hashCode42), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl42, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl42, materializeModifier42, ComposeUiNode.INSTANCE.getSetModifier());
                            int i152 = (i142 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            int i162 = ((0 >> 6) & 112) | 6;
                            ColumnScope columnScope22 = ColumnScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer3, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                            BoxKt.Box(PaddingKt.m820paddingqDBjuR0$default(columnScope22.align(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), Dp.m7908constructorimpl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), null, 2, null), Alignment.INSTANCE.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), $composer3, 0);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                            final MutableState suggestionsSuppressed$delegate52 = suggestionsSuppressed$delegate;
                            final MutableState suggestionsLoading$delegate22 = suggestionsSuppressed$delegate2;
                            final RouteViewModel routeViewModel82 = routeViewModel3;
                            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(4), null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda23
                                @Override // kotlin.jvm.functions.Function3
                                public final Object invoke(Object obj102, Object obj112, Object obj122) {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74(Function1.this, routeViewModel82, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate52, suggestionsStatus$delegate, routeState$delegate2, suggestionsLoading$delegate22, (ColumnScope) obj102, (Composer) obj112, ((Integer) obj122).intValue());
                                }
                            }, $composer3, 54), $composer3, 24966, 10);
                            searchNote = RouteScreen$lambda$0(routeState$delegate2).getSearchNote();
                            if (searchNote != null) {
                            }
                            ImageVector qrScan2 = WayfindrIcons.INSTANCE.getQrScan();
                            BtnVariant btnVariant4 = BtnVariant.Ghost;
                            BtnSize btnSize4 = BtnSize.Sm;
                            Modifier m820paddingqDBjuR0$default22 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(12), 7, null);
                            ComposerKt.sourceInformationMarkerStart($composer3, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                            changedInstance3 = $composer3.changedInstance(qrPicker32);
                            Object rememberedValue272 = $composer3.rememberedValue();
                            if (changedInstance3) {
                            }
                            rememberedValue272 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda24
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher.this);
                                }
                            };
                            $composer3.updateRememberedValue(rememberedValue272);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            WayfindrComponentsKt.WayfindrBtn("QR görselinden rota yükle", (Function0) rememberedValue272, m820paddingqDBjuR0$default22, qrScan2, btnVariant4, btnSize4, false, $composer3, 221574, 64);
                            composer = $composer3;
                            sharedRouteName = RouteScreen$lambda$0(routeState$delegate2).getSharedRouteName();
                            if (sharedRouteName != null) {
                            }
                            if (RouteScreen$lambda$0(routeState$delegate2).getWaypoints().isEmpty()) {
                            }
                            composer.endReplaceGroup();
                            if ((dest == null ? dest.getLat() : null) != null) {
                            }
                            composer.startReplaceGroup(2018241319);
                            ComposerKt.sourceInformation(composer, "438@22367L1104");
                            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(16), null, ComposableSingletons$RouteScreenKt.INSTANCE.m8364getLambda$835303176$app(), composer, 24966, 10);
                            composer.endReplaceGroup();
                            WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(routeState$delegate2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(6), 7, null), composer, 48, 0);
                            Alignment.Vertical top22 = Alignment.INSTANCE.getTop();
                            ComposerKt.sourceInformationMarkerStart(composer, 844473419, str5);
                            Modifier modifier9 = Modifier.INSTANCE;
                            MeasurePolicy rowMeasurePolicy32 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top22, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode62 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                            CompositionLocalMap currentCompositionLocalMap62 = composer.getCurrentCompositionLocalMap();
                            Modifier materializeModifier62 = ComposedModifierKt.materializeModifier(composer, modifier9);
                            Function0 constructor62 = ComposeUiNode.INSTANCE.getConstructor();
                            int i202 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer.getApplier() instanceof Applier)) {
                            }
                            composer.startReusableNode();
                            if (composer.getInserting()) {
                            }
                            Composer m4364constructorimpl62 = Updater.m4364constructorimpl(composer);
                            Updater.m4372setimpl(m4364constructorimpl62, rowMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl62, currentCompositionLocalMap62, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl62, Integer.valueOf(hashCode62), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl62, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl62, materializeModifier62, ComposeUiNode.INSTANCE.getSetModifier());
                            int i212 = (i202 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str4);
                            RowScopeInstance rowScopeInstance32 = RowScopeInstance.INSTANCE;
                            int i222 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                            Alignment center2 = Alignment.INSTANCE.getCenter();
                            Modifier m262backgroundbw27NRU$default22 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(44)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getGoldTint(), null, 2, null);
                            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy22 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode72 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                            CompositionLocalMap currentCompositionLocalMap72 = composer.getCurrentCompositionLocalMap();
                            Modifier materializeModifier72 = ComposedModifierKt.materializeModifier(composer, m262backgroundbw27NRU$default22);
                            Function0 constructor72 = ComposeUiNode.INSTANCE.getConstructor();
                            int i232 = ((((48 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer.getApplier() instanceof Applier)) {
                            }
                            composer.startReusableNode();
                            if (composer.getInserting()) {
                            }
                            Composer m4364constructorimpl72 = Updater.m4364constructorimpl(composer);
                            Updater.m4372setimpl(m4364constructorimpl72, maybeCachedBoxMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl72, currentCompositionLocalMap72, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl72, Integer.valueOf(hashCode72), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl72, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl72, materializeModifier72, ComposeUiNode.INSTANCE.getSetModifier());
                            int i242 = (i232 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                            int i252 = ((48 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), ColorKt.getGold(), composer, 432, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            composer.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), composer, 6);
                            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Modifier modifier22 = Modifier.INSTANCE;
                            MeasurePolicy columnMeasurePolicy32 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode82 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                            CompositionLocalMap currentCompositionLocalMap82 = composer.getCurrentCompositionLocalMap();
                            Modifier materializeModifier82 = ComposedModifierKt.materializeModifier(composer, modifier22);
                            Function0 constructor82 = ComposeUiNode.INSTANCE.getConstructor();
                            int i262 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer.getApplier() instanceof Applier)) {
                            }
                            composer.startReusableNode();
                            if (composer.getInserting()) {
                            }
                            Composer m4364constructorimpl82 = Updater.m4364constructorimpl(composer);
                            Updater.m4372setimpl(m4364constructorimpl82, columnMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl82, currentCompositionLocalMap82, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl82, Integer.valueOf(hashCode82), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl82, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl82, materializeModifier82, ComposeUiNode.INSTANCE.getSetModifier());
                            int i272 = (i262 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                            int i282 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer, -1541055177, "C:RouteScreen.kt#cef2n4");
                            if (RouteScreen$lambda$0(routeState$delegate2).isResolving()) {
                            }
                            if (StringsKt.isBlank(destSub2)) {
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
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), composer, 6);
                            if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                            }
                            composer.endReplaceGroup();
                            routing = RouteScreen$lambda$0(routeState$delegate2).getRouting();
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
                            Iterable listOf3 = CollectionsKt.listOf((Object[]) tripleArr2);
                            Arrangement.Horizontal m686spacedBy0680j_422 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                            composer3 = composer;
                            int i422 = 0;
                            ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str5);
                            Modifier modifier42 = Modifier.INSTANCE;
                            MeasurePolicy rowMeasurePolicy52 = RowKt.rowMeasurePolicy(m686spacedBy0680j_422, Alignment.INSTANCE.getTop(), composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode122 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                            CompositionLocalMap currentCompositionLocalMap122 = composer3.getCurrentCompositionLocalMap();
                            Modifier materializeModifier122 = ComposedModifierKt.materializeModifier(composer3, modifier42);
                            Function0 constructor122 = ComposeUiNode.INSTANCE.getConstructor();
                            int i432 = ((((48 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer3.getApplier() instanceof Applier)) {
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                            }
                            Composer m4364constructorimpl122 = Updater.m4364constructorimpl(composer3);
                            MeasurePolicy measurePolicy7 = rowMeasurePolicy52;
                            Updater.m4372setimpl(m4364constructorimpl122, measurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl122, currentCompositionLocalMap122, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl122, Integer.valueOf(hashCode122), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl122, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl122, materializeModifier122, ComposeUiNode.INSTANCE.getSetModifier());
                            int i442 = (i432 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str4);
                            int i452 = ((48 >> 6) & 112) | 6;
                            RowScope rowScope22 = RowScopeInstance.INSTANCE;
                            Composer composer142 = composer3;
                            ComposerKt.sourceInformationMarkerStart(composer142, 213090107, "C:RouteScreen.kt#cef2n4");
                            composer142.startReplaceGroup(1807989502);
                            ComposerKt.sourceInformation(composer142, "*540@28620L859");
                            Iterable<Triple> iterable22 = listOf3;
                            int i462 = 0;
                            while (r39.hasNext()) {
                            }
                            Composer composer172 = composer142;
                            composer172.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(composer172);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), composer, 6);
                            collectAsState = SnapshotStateKt.collectAsState(dashConfig.getRoutingSource(), null, composer, 0, 1);
                            State collectAsState22 = SnapshotStateKt.collectAsState(dashConfig.getMapTheme(), null, composer, 0, 1);
                            ComposerKt.sourceInformationMarkerStart(composer, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                            Composer composer182 = composer;
                            rememberedValue5 = composer182.rememberedValue();
                            if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                            }
                            boolean booleanValue2 = ((Boolean) rememberedValue5).booleanValue();
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                            }
                            ChoiceHeader("Rota sağlayıcısı", str6, composer, 6);
                            Iterable available2 = RoutingSource.INSTANCE.getAvailable();
                            Collection arrayList32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available2, 10));
                            it = available2.iterator();
                            while (it.hasNext()) {
                            }
                            ArrayList arrayList42 = (List) arrayList32;
                            String label2 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                            ComposerKt.sourceInformationMarkerStart(composer, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                            changed2 = $composer3.changed(collectAsState) | $composer3.changedInstance(dashConfig) | (((($dirty52 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel82)) || ($dirty52 & 384) == 256);
                            Composer composer192 = composer;
                            composer4 = composer;
                            Object rememberedValue302 = composer192.rememberedValue();
                            if (changed2) {
                            }
                            rememberedValue302 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj122) {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig.this, routeViewModel82, collectAsState, (String) obj122);
                                }
                            };
                            composer192.updateRememberedValue(rememberedValue302);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            WayfindrComponentsKt.WayfindrSegmented(arrayList42, label2, (Function1) rememberedValue302, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), composer4, 3072, 0);
                            ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer4, 54);
                            Modifier m820paddingqDBjuR0$default42 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null);
                            Arrangement.Vertical m686spacedBy0680j_432 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
                            int i552 = 0;
                            ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy72 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_432, Alignment.INSTANCE.getStart(), composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode152 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                            CompositionLocalMap currentCompositionLocalMap152 = composer4.getCurrentCompositionLocalMap();
                            Modifier materializeModifier152 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default42);
                            Function0 constructor152 = ComposeUiNode.INSTANCE.getConstructor();
                            int i562 = ((((54 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                            }
                            Composer m4364constructorimpl152 = Updater.m4364constructorimpl(composer4);
                            MeasurePolicy measurePolicy32 = columnMeasurePolicy72;
                            Updater.m4372setimpl(m4364constructorimpl152, measurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl152, currentCompositionLocalMap152, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl152, Integer.valueOf(hashCode152), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl152, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl152, materializeModifier152, ComposeUiNode.INSTANCE.getSetModifier());
                            int i572 = (i562 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance52 = ColumnScopeInstance.INSTANCE;
                            int i582 = ((54 >> 6) & 112) | 6;
                            Composer composer202 = composer4;
                            ComposerKt.sourceInformationMarkerStart(composer202, -1001505061, "C:RouteScreen.kt#cef2n4");
                            composer202.startReplaceGroup(-1140683288);
                            ComposerKt.sourceInformation(composer202, "*585@31259L1578");
                            Iterable<List> chunked2 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                            int i592 = 0;
                            while (r41.hasNext()) {
                            }
                            Composer composer222 = composer202;
                            int $dirty82 = $dirty52;
                            final Context ctx82 = ctx3;
                            composer222.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(composer222);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            Arrangement.Horizontal spaceBetween22 = Arrangement.INSTANCE.getSpaceBetween();
                            Alignment.Vertical centerVertically52 = Alignment.INSTANCE.getCenterVertically();
                            Modifier m820paddingqDBjuR0$default52 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(9), 7, null);
                            ComposerKt.sourceInformationMarkerStart(composer4, 844473419, str5);
                            MeasurePolicy rowMeasurePolicy92 = RowKt.rowMeasurePolicy(spaceBetween22, centerVertically52, composer4, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode182 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                            CompositionLocalMap currentCompositionLocalMap182 = composer4.getCurrentCompositionLocalMap();
                            Modifier materializeModifier182 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default52);
                            Function0 constructor182 = ComposeUiNode.INSTANCE.getConstructor();
                            int i712 = ((((438 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!(composer4.getApplier() instanceof Applier)) {
                            }
                            composer4.startReusableNode();
                            if (composer4.getInserting()) {
                            }
                            Composer m4364constructorimpl182 = Updater.m4364constructorimpl(composer4);
                            Updater.m4372setimpl(m4364constructorimpl182, rowMeasurePolicy92, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl182, currentCompositionLocalMap182, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl182, Integer.valueOf(hashCode182), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl182, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl182, materializeModifier182, ComposeUiNode.INSTANCE.getSetModifier());
                            int i722 = (i712 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, str4);
                            RowScopeInstance rowScopeInstance62 = RowScopeInstance.INSTANCE;
                            int i732 = ((438 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart(composer4, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                            WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", null, composer4, 6, 2);
                            IconKt.m2517Iconww6aTOc(RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer4, 432, 0);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            composer4.endNode();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            List listOf22 = CollectionsKt.listOf((Object[]) new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                            ComposerKt.sourceInformationMarkerStart(composer4, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                            final VoiceManager voiceManager32 = voiceManager;
                            changedInstance4 = composer4.changedInstance(voiceManager32);
                            rememberedValue6 = composer4.rememberedValue();
                            if (!changedInstance4) {
                            }
                            Object obj132 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda7
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj142) {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager.this, (String) obj142);
                                }
                            };
                            composer4.updateRememberedValue(obj132);
                            rememberedValue6 = obj132;
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            WayfindrComponentsKt.WayfindrSegmented(listOf22, voice, (Function1) rememberedValue6, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null), composer4, 3078, 0);
                            if ((dest == null ? dest.getLat() : null) != null) {
                            }
                            if (!RouteScreen$lambda$7(sent$delegate3)) {
                            }
                            ImageVector check2 = !RouteScreen$lambda$7(sent$delegate3) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                            BtnVariant btnVariant22 = !RouteScreen$lambda$7(sent$delegate3) ? BtnVariant.Secondary : BtnVariant.Primary;
                            BtnSize btnSize22 = BtnSize.Lg;
                            if (RouteScreen$lambda$7(sent$delegate3)) {
                            }
                            Modifier fillMaxWidth$default22 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            ComposerKt.sourceInformationMarkerStart(composer4, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                            changedInstance5 = composer4.changedInstance(ctx82) | composer4.changedInstance(navPermLauncher32) | composer4.changedInstance(navPermissions);
                            Object rememberedValue322 = composer4.rememberedValue();
                            if (!changedInstance5) {
                                z7 = z6;
                                sent$delegate = sent$delegate3;
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue322, fillMaxWidth$default22, check2, btnVariant22, btnSize22, z16, composer4, 196992, 0);
                                if (z7) {
                                }
                                composer4.endReplaceGroup();
                                if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
                                }
                                composer4.endReplaceGroup();
                                ComposerKt.sourceInformationMarkerEnd(composer4);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                $composer3.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                ComposerKt.sourceInformationMarkerEnd($composer3);
                                if (RouteScreen$lambda$10(sent$delegate2)) {
                                }
                                $composer3.endReplaceGroup();
                                RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
                                if (RouteScreen$lambda$16 != null) {
                                }
                                RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
                                if (RouteScreen$lambda$13 != null) {
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                }
                                routeViewModel2 = routeViewModel5;
                            }
                            z7 = z6;
                            sent$delegate = sent$delegate3;
                            Object obj202 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda8
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher.this, navPermissions, ctx82, sent$delegate);
                                }
                            };
                            composer4.updateRememberedValue(obj202);
                            rememberedValue322 = obj202;
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue322, fillMaxWidth$default22, check2, btnVariant22, btnSize22, z16, composer4, 196992, 0);
                            if (z7) {
                            }
                            composer4.endReplaceGroup();
                            if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
                            }
                            composer4.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd(composer4);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            $composer3.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer3);
                            if (RouteScreen$lambda$10(sent$delegate2)) {
                            }
                            $composer3.endReplaceGroup();
                            RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
                            if (RouteScreen$lambda$16 != null) {
                            }
                            RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
                            if (RouteScreen$lambda$13 != null) {
                            }
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            routeViewModel2 = routeViewModel5;
                        }
                        Object obj22 = (Function2) new RouteScreenKt$RouteScreen$3$1(routeViewModel3, locationPermLauncher2, ctx2, null);
                        $composer3.updateRememberedValue(obj22);
                        rememberedValue23 = obj22;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue23, $composer3, 6);
                        Boolean valueOf22 = Boolean.valueOf(RouteScreen$lambda$7(sent$delegate3));
                        ComposerKt.sourceInformationMarkerStart($composer3, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                        changed = (($dirty3 & 112) == 32) | $composer3.changed(routeState$delegate);
                        Object rememberedValue242 = $composer3.rememberedValue();
                        if (changed) {
                        }
                        Object obj212 = (Function2) new RouteScreenKt$RouteScreen$4$1(onSentToDash, sent$delegate3, routeState$delegate, null);
                        $composer3.updateRememberedValue(obj212);
                        rememberedValue242 = obj212;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        EffectsKt.LaunchedEffect(valueOf22, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue242, $composer3, 0);
                        Modifier fillMaxSize$default32 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        Arrangement.Vertical top32 = Arrangement.INSTANCE.getTop();
                        Alignment.Horizontal start32 = Alignment.INSTANCE.getStart();
                        MeasurePolicy columnMeasurePolicy82 = ColumnKt.columnMeasurePolicy(top32, start32, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                        routeState$delegate2 = routeState$delegate;
                        Context ctx522 = ctx2;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode202 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap202 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier202 = ComposedModifierKt.materializeModifier($composer3, fillMaxSize$default32);
                        Function0 constructor202 = ComposeUiNode.INSTANCE.getConstructor();
                        int i4102 = ((((6 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        Composer m4364constructorimpl202 = Updater.m4364constructorimpl($composer3);
                        Updater.m4372setimpl(m4364constructorimpl202, columnMeasurePolicy82, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl202, currentCompositionLocalMap202, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl202, Integer.valueOf(hashCode202), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl202, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl202, materializeModifier202, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5102 = (i4102 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        int i6102 = ((6 >> 6) & 112) | 6;
                        ColumnScope columnScope32 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                        Modifier m262backgroundbw27NRU$default32 = BackgroundKt.m262backgroundbw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.46f), ColorKt.getMapBase(), null, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy32 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap222 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier222 = ComposedModifierKt.materializeModifier($composer3, m262backgroundbw27NRU$default32);
                        Function0 constructor222 = ComposeUiNode.INSTANCE.getConstructor();
                        int i772 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        Composer m4364constructorimpl222 = Updater.m4364constructorimpl($composer3);
                        Updater.m4372setimpl(m4364constructorimpl222, maybeCachedBoxMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl222, currentCompositionLocalMap222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl222, Integer.valueOf(hashCode222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl222, materializeModifier222, ComposeUiNode.INSTANCE.getSetModifier());
                        int i822 = (i772 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        int i922 = ((0 >> 6) & 112) | 6;
                        BoxScope boxScope22 = BoxScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                        Pair<Double, Double> currentLocation32 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                        if (currentLocation32 == null) {
                        }
                        Pair<Double, Double> currentLocation222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                        if (currentLocation222 == null) {
                        }
                        float currentBearing22 = RouteScreen$lambda$0(routeState$delegate2).getCurrentBearing();
                        long locationRefreshKey22 = RouteScreen$lambda$0(routeState$delegate2).getLocationRefreshKey();
                        if (dest == null) {
                        }
                        Iterable<SharedLocation> waypoints22 = RouteScreen$lambda$0(routeState$delegate2).getWaypoints();
                        int i1022 = 0;
                        Collection arrayList52 = new ArrayList();
                        while (r101.hasNext()) {
                        }
                        ArrayList arrayList222 = (List) arrayList52;
                        Route route52 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                        if (route52 == null) {
                        }
                        if (geometry == null) {
                        }
                        List<GeoPoint> list222 = geometry;
                        Route route222 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                        if (route222 == null) {
                        }
                        if (traffic == null) {
                        }
                        List<TrafficInterval> list322 = traffic;
                        routeAlternatives = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
                        list = routeAlternatives;
                        if (!(routeAlternatives.size() <= 1)) {
                        }
                        if (list == null) {
                        }
                        int selectedRouteIndex32 = RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
                        if (RouteScreen$lambda$0(routeState$delegate2).getRoute() == null) {
                        }
                        Modifier fillMaxSize$default222 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                        if ((($dirty3 & 896) ^ 384) <= 256) {
                        }
                        Object rememberedValue2522 = $composer3.rememberedValue();
                        if (z4) {
                        }
                        Object obj722 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda22
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj822) {
                                return RouteScreenKt.RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel.this, (LatLng) obj822);
                            }
                        };
                        $composer3.updateRememberedValue(obj722);
                        rememberedValue2522 = obj722;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        FreeWayfindrMapKt.WayfindrMap(first, second, pair2, arrayList222, list222, list322, list, selectedRouteIndex32, true, fillMaxSize$default222, z9, false, currentBearing22, locationRefreshKey22, false, true, null, null, null, null, false, (Function1) rememberedValue2522, $composer3, 905969664, 221184, 0, 2033664);
                        Alignment.Vertical centerVertically72 = Alignment.INSTANCE.getCenterVertically();
                        Modifier m817paddingVpY3zN442 = PaddingKt.m817paddingVpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope22.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopStart()), 0.0f, 1, null), Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(14));
                        ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy rowMeasurePolicy112 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically72, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode322 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap322 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier322 = ComposedModifierKt.materializeModifier($composer3, m817paddingVpY3zN442);
                        Function0 constructor322 = ComposeUiNode.INSTANCE.getConstructor();
                        int i1122 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        int $dirty422 = $dirty3;
                        ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        Composer m4364constructorimpl322 = Updater.m4364constructorimpl($composer3);
                        Updater.m4372setimpl(m4364constructorimpl322, rowMeasurePolicy112, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl322, currentCompositionLocalMap322, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl322, Integer.valueOf(hashCode322), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl322, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl322, materializeModifier322, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1222 = (i1122 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance82 = RowScopeInstance.INSTANCE;
                        int i1322 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                        final ManagedActivityResultLauncher navPermLauncher322 = navPermLauncher;
                        final ManagedActivityResultLauncher qrPicker322 = qrPicker;
                        int $dirty522 = $dirty422;
                        WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), onBack, BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, (($dirty422 << 3) & 112) | 3072, 48);
                        SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                        WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(routeState$delegate2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(routeState$delegate2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(routeState$delegate2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), $composer3, 0, 4);
                        ImageVector recenter22 = WayfindrIcons.INSTANCE.getRecenter();
                        ComposerKt.sourceInformationMarkerStart($composer3, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                        if ((($dirty522 & 896) ^ 384) <= 256) {
                        }
                        rememberedValue4 = $composer3.rememberedValue();
                        if (!z5) {
                        }
                        Object obj822 = (KFunction) new RouteScreenKt$RouteScreen$5$1$5$1$1(routeViewModel3);
                        $composer3.updateRememberedValue(obj822);
                        rememberedValue4 = obj822;
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter22, (Function0) ((KFunction) rememberedValue4), BackgroundKt.m261backgroundbw27NRU(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, 3072, 48);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                        }
                        $composer3.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        Modifier m820paddingqDBjuR0$default72 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m817paddingVpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope32, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), RoundedCornerShapeKt.m1126RoundedCornerShapea9UjIt4$default(Dp.m7908constructorimpl(22), Dp.m7908constructorimpl(22), 0.0f, 0.0f, 12, null)), ColorKt.getBg1(), null, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18), Dp.m7908constructorimpl(8)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(20), 7, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode422 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap422 = $composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier422 = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default72);
                        Function0 constructor422 = ComposeUiNode.INSTANCE.getConstructor();
                        int i1422 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                        }
                        Composer m4364constructorimpl422 = Updater.m4364constructorimpl($composer3);
                        Updater.m4372setimpl(m4364constructorimpl422, columnMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl422, currentCompositionLocalMap422, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl422, Integer.valueOf(hashCode422), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl422, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl422, materializeModifier422, ComposeUiNode.INSTANCE.getSetModifier());
                        int i1522 = (i1422 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        int i1622 = ((0 >> 6) & 112) | 6;
                        ColumnScope columnScope222 = ColumnScopeInstance.INSTANCE;
                        ComposerKt.sourceInformationMarkerStart($composer3, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                        BoxKt.Box(PaddingKt.m820paddingqDBjuR0$default(columnScope222.align(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), Dp.m7908constructorimpl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), null, 2, null), Alignment.INSTANCE.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), $composer3, 0);
                        SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                        final MutableState suggestionsSuppressed$delegate522 = suggestionsSuppressed$delegate;
                        final MutableState suggestionsLoading$delegate222 = suggestionsSuppressed$delegate2;
                        final RouteViewModel routeViewModel822 = routeViewModel3;
                        WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(4), null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda23
                            @Override // kotlin.jvm.functions.Function3
                            public final Object invoke(Object obj102, Object obj112, Object obj122) {
                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74(Function1.this, routeViewModel822, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate522, suggestionsStatus$delegate, routeState$delegate2, suggestionsLoading$delegate222, (ColumnScope) obj102, (Composer) obj112, ((Integer) obj122).intValue());
                            }
                        }, $composer3, 54), $composer3, 24966, 10);
                        searchNote = RouteScreen$lambda$0(routeState$delegate2).getSearchNote();
                        if (searchNote != null) {
                        }
                        ImageVector qrScan22 = WayfindrIcons.INSTANCE.getQrScan();
                        BtnVariant btnVariant42 = BtnVariant.Ghost;
                        BtnSize btnSize42 = BtnSize.Sm;
                        Modifier m820paddingqDBjuR0$default222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(12), 7, null);
                        ComposerKt.sourceInformationMarkerStart($composer3, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance3 = $composer3.changedInstance(qrPicker322);
                        Object rememberedValue2722 = $composer3.rememberedValue();
                        if (changedInstance3) {
                        }
                        rememberedValue2722 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda24
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher.this);
                            }
                        };
                        $composer3.updateRememberedValue(rememberedValue2722);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        WayfindrComponentsKt.WayfindrBtn("QR görselinden rota yükle", (Function0) rememberedValue2722, m820paddingqDBjuR0$default222, qrScan22, btnVariant42, btnSize42, false, $composer3, 221574, 64);
                        composer = $composer3;
                        sharedRouteName = RouteScreen$lambda$0(routeState$delegate2).getSharedRouteName();
                        if (sharedRouteName != null) {
                        }
                        if (RouteScreen$lambda$0(routeState$delegate2).getWaypoints().isEmpty()) {
                        }
                        composer.endReplaceGroup();
                        if ((dest == null ? dest.getLat() : null) != null) {
                        }
                        composer.startReplaceGroup(2018241319);
                        ComposerKt.sourceInformation(composer, "438@22367L1104");
                        WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(16), null, ComposableSingletons$RouteScreenKt.INSTANCE.m8364getLambda$835303176$app(), composer, 24966, 10);
                        composer.endReplaceGroup();
                        WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(routeState$delegate2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(6), 7, null), composer, 48, 0);
                        Alignment.Vertical top222 = Alignment.INSTANCE.getTop();
                        ComposerKt.sourceInformationMarkerStart(composer, 844473419, str5);
                        Modifier modifier92 = Modifier.INSTANCE;
                        MeasurePolicy rowMeasurePolicy322 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top222, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode622 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                        CompositionLocalMap currentCompositionLocalMap622 = composer.getCurrentCompositionLocalMap();
                        Modifier materializeModifier622 = ComposedModifierKt.materializeModifier(composer, modifier92);
                        Function0 constructor622 = ComposeUiNode.INSTANCE.getConstructor();
                        int i2022 = ((((384 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                        }
                        Composer m4364constructorimpl622 = Updater.m4364constructorimpl(composer);
                        Updater.m4372setimpl(m4364constructorimpl622, rowMeasurePolicy322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl622, currentCompositionLocalMap622, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl622, Integer.valueOf(hashCode622), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl622, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl622, materializeModifier622, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2122 = (i2022 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str4);
                        RowScopeInstance rowScopeInstance322 = RowScopeInstance.INSTANCE;
                        int i2222 = ((384 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                        Alignment center22 = Alignment.INSTANCE.getCenter();
                        Modifier m262backgroundbw27NRU$default222 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(44)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getGoldTint(), null, 2, null);
                        ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy222 = BoxKt.maybeCachedBoxMeasurePolicy(center22, false);
                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode722 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                        CompositionLocalMap currentCompositionLocalMap722 = composer.getCurrentCompositionLocalMap();
                        Modifier materializeModifier722 = ComposedModifierKt.materializeModifier(composer, m262backgroundbw27NRU$default222);
                        Function0 constructor722 = ComposeUiNode.INSTANCE.getConstructor();
                        int i2322 = ((((48 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                        }
                        Composer m4364constructorimpl722 = Updater.m4364constructorimpl(composer);
                        Updater.m4372setimpl(m4364constructorimpl722, maybeCachedBoxMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl722, currentCompositionLocalMap722, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl722, Integer.valueOf(hashCode722), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl722, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl722, materializeModifier722, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2422 = (i2322 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                        int i2522 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                        IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), ColorKt.getGold(), composer, 432, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), composer, 6);
                        ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        Modifier modifier222 = Modifier.INSTANCE;
                        MeasurePolicy columnMeasurePolicy322 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode822 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                        CompositionLocalMap currentCompositionLocalMap822 = composer.getCurrentCompositionLocalMap();
                        Modifier materializeModifier822 = ComposedModifierKt.materializeModifier(composer, modifier222);
                        Function0 constructor822 = ComposeUiNode.INSTANCE.getConstructor();
                        int i2622 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer.getApplier() instanceof Applier)) {
                        }
                        composer.startReusableNode();
                        if (composer.getInserting()) {
                        }
                        Composer m4364constructorimpl822 = Updater.m4364constructorimpl(composer);
                        Updater.m4372setimpl(m4364constructorimpl822, columnMeasurePolicy322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl822, currentCompositionLocalMap822, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl822, Integer.valueOf(hashCode822), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl822, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl822, materializeModifier822, ComposeUiNode.INSTANCE.getSetModifier());
                        int i2722 = (i2622 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance62 = ColumnScopeInstance.INSTANCE;
                        int i2822 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer, -1541055177, "C:RouteScreen.kt#cef2n4");
                        if (RouteScreen$lambda$0(routeState$delegate2).isResolving()) {
                        }
                        if (StringsKt.isBlank(destSub2)) {
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
                        SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), composer, 6);
                        if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                        }
                        composer.endReplaceGroup();
                        routing = RouteScreen$lambda$0(routeState$delegate2).getRouting();
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
                        Iterable listOf32 = CollectionsKt.listOf((Object[]) tripleArr22);
                        Arrangement.Horizontal m686spacedBy0680j_4222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                        composer3 = composer;
                        int i4222 = 0;
                        ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str5);
                        Modifier modifier422 = Modifier.INSTANCE;
                        MeasurePolicy rowMeasurePolicy522 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4222, Alignment.INSTANCE.getTop(), composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode1222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                        CompositionLocalMap currentCompositionLocalMap1222 = composer3.getCurrentCompositionLocalMap();
                        Modifier materializeModifier1222 = ComposedModifierKt.materializeModifier(composer3, modifier422);
                        Function0 constructor1222 = ComposeUiNode.INSTANCE.getConstructor();
                        int i4322 = ((((48 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer3.getApplier() instanceof Applier)) {
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                        }
                        Composer m4364constructorimpl1222 = Updater.m4364constructorimpl(composer3);
                        MeasurePolicy measurePolicy72 = rowMeasurePolicy522;
                        Updater.m4372setimpl(m4364constructorimpl1222, measurePolicy72, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl1222, currentCompositionLocalMap1222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl1222, Integer.valueOf(hashCode1222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl1222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl1222, materializeModifier1222, ComposeUiNode.INSTANCE.getSetModifier());
                        int i4422 = (i4322 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str4);
                        int i4522 = ((48 >> 6) & 112) | 6;
                        RowScope rowScope222 = RowScopeInstance.INSTANCE;
                        Composer composer1422 = composer3;
                        ComposerKt.sourceInformationMarkerStart(composer1422, 213090107, "C:RouteScreen.kt#cef2n4");
                        composer1422.startReplaceGroup(1807989502);
                        ComposerKt.sourceInformation(composer1422, "*540@28620L859");
                        Iterable<Triple> iterable222 = listOf32;
                        int i4622 = 0;
                        while (r39.hasNext()) {
                        }
                        Composer composer1722 = composer1422;
                        composer1722.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer1722);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer3);
                        SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), composer, 6);
                        collectAsState = SnapshotStateKt.collectAsState(dashConfig.getRoutingSource(), null, composer, 0, 1);
                        State collectAsState222 = SnapshotStateKt.collectAsState(dashConfig.getMapTheme(), null, composer, 0, 1);
                        ComposerKt.sourceInformationMarkerStart(composer, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                        Composer composer1822 = composer;
                        rememberedValue5 = composer1822.rememberedValue();
                        if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                        }
                        boolean booleanValue22 = ((Boolean) rememberedValue5).booleanValue();
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                        }
                        ChoiceHeader("Rota sağlayıcısı", str6, composer, 6);
                        Iterable available22 = RoutingSource.INSTANCE.getAvailable();
                        Collection arrayList322 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available22, 10));
                        it = available22.iterator();
                        while (it.hasNext()) {
                        }
                        ArrayList arrayList422 = (List) arrayList322;
                        String label22 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                        ComposerKt.sourceInformationMarkerStart(composer, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                        changed2 = $composer3.changed(collectAsState) | $composer3.changedInstance(dashConfig) | (((($dirty522 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel822)) || ($dirty522 & 384) == 256);
                        Composer composer1922 = composer;
                        composer4 = composer;
                        Object rememberedValue3022 = composer1922.rememberedValue();
                        if (changed2) {
                        }
                        rememberedValue3022 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj122) {
                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig.this, routeViewModel822, collectAsState, (String) obj122);
                            }
                        };
                        composer1922.updateRememberedValue(rememberedValue3022);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        WayfindrComponentsKt.WayfindrSegmented(arrayList422, label22, (Function1) rememberedValue3022, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), composer4, 3072, 0);
                        ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer4, 54);
                        Modifier m820paddingqDBjuR0$default422 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null);
                        Arrangement.Vertical m686spacedBy0680j_4322 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
                        int i5522 = 0;
                        ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy722 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4322, Alignment.INSTANCE.getStart(), composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode1522 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                        CompositionLocalMap currentCompositionLocalMap1522 = composer4.getCurrentCompositionLocalMap();
                        Modifier materializeModifier1522 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default422);
                        Function0 constructor1522 = ComposeUiNode.INSTANCE.getConstructor();
                        int i5622 = ((((54 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                        }
                        Composer m4364constructorimpl1522 = Updater.m4364constructorimpl(composer4);
                        MeasurePolicy measurePolicy322 = columnMeasurePolicy722;
                        Updater.m4372setimpl(m4364constructorimpl1522, measurePolicy322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl1522, currentCompositionLocalMap1522, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl1522, Integer.valueOf(hashCode1522), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl1522, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl1522, materializeModifier1522, ComposeUiNode.INSTANCE.getSetModifier());
                        int i5722 = (i5622 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance522 = ColumnScopeInstance.INSTANCE;
                        int i5822 = ((54 >> 6) & 112) | 6;
                        Composer composer2022 = composer4;
                        ComposerKt.sourceInformationMarkerStart(composer2022, -1001505061, "C:RouteScreen.kt#cef2n4");
                        composer2022.startReplaceGroup(-1140683288);
                        ComposerKt.sourceInformation(composer2022, "*585@31259L1578");
                        Iterable<List> chunked22 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                        int i5922 = 0;
                        while (r41.hasNext()) {
                        }
                        Composer composer2222 = composer2022;
                        int $dirty822 = $dirty522;
                        final Context ctx822 = ctx3;
                        composer2222.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer2222);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        Arrangement.Horizontal spaceBetween222 = Arrangement.INSTANCE.getSpaceBetween();
                        Alignment.Vertical centerVertically522 = Alignment.INSTANCE.getCenterVertically();
                        Modifier m820paddingqDBjuR0$default522 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(9), 7, null);
                        ComposerKt.sourceInformationMarkerStart(composer4, 844473419, str5);
                        MeasurePolicy rowMeasurePolicy922 = RowKt.rowMeasurePolicy(spaceBetween222, centerVertically522, composer4, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode1822 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                        CompositionLocalMap currentCompositionLocalMap1822 = composer4.getCurrentCompositionLocalMap();
                        Modifier materializeModifier1822 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default522);
                        Function0 constructor1822 = ComposeUiNode.INSTANCE.getConstructor();
                        int i7122 = ((((438 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!(composer4.getApplier() instanceof Applier)) {
                        }
                        composer4.startReusableNode();
                        if (composer4.getInserting()) {
                        }
                        Composer m4364constructorimpl1822 = Updater.m4364constructorimpl(composer4);
                        Updater.m4372setimpl(m4364constructorimpl1822, rowMeasurePolicy922, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl1822, currentCompositionLocalMap1822, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl1822, Integer.valueOf(hashCode1822), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl1822, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl1822, materializeModifier1822, ComposeUiNode.INSTANCE.getSetModifier());
                        int i7222 = (i7122 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, str4);
                        RowScopeInstance rowScopeInstance622 = RowScopeInstance.INSTANCE;
                        int i7322 = ((438 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart(composer4, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                        WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", null, composer4, 6, 2);
                        IconKt.m2517Iconww6aTOc(RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer4, 432, 0);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        List listOf222 = CollectionsKt.listOf((Object[]) new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                        ComposerKt.sourceInformationMarkerStart(composer4, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                        final VoiceManager voiceManager322 = voiceManager;
                        changedInstance4 = composer4.changedInstance(voiceManager322);
                        rememberedValue6 = composer4.rememberedValue();
                        if (!changedInstance4) {
                        }
                        Object obj1322 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda7
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj142) {
                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager.this, (String) obj142);
                            }
                        };
                        composer4.updateRememberedValue(obj1322);
                        rememberedValue6 = obj1322;
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        WayfindrComponentsKt.WayfindrSegmented(listOf222, voice, (Function1) rememberedValue6, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null), composer4, 3078, 0);
                        if ((dest == null ? dest.getLat() : null) != null) {
                        }
                        if (!RouteScreen$lambda$7(sent$delegate3)) {
                        }
                        ImageVector check22 = !RouteScreen$lambda$7(sent$delegate3) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                        BtnVariant btnVariant222 = !RouteScreen$lambda$7(sent$delegate3) ? BtnVariant.Secondary : BtnVariant.Primary;
                        BtnSize btnSize222 = BtnSize.Lg;
                        if (RouteScreen$lambda$7(sent$delegate3)) {
                        }
                        Modifier fillMaxWidth$default222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                        ComposerKt.sourceInformationMarkerStart(composer4, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance5 = composer4.changedInstance(ctx822) | composer4.changedInstance(navPermLauncher322) | composer4.changedInstance(navPermissions);
                        Object rememberedValue3222 = composer4.rememberedValue();
                        if (!changedInstance5) {
                        }
                        z7 = z6;
                        sent$delegate = sent$delegate3;
                        Object obj2022 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda8
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher.this, navPermissions, ctx822, sent$delegate);
                            }
                        };
                        composer4.updateRememberedValue(obj2022);
                        rememberedValue3222 = obj2022;
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue3222, fillMaxWidth$default222, check22, btnVariant222, btnSize222, z16, composer4, 196992, 0);
                        if (z7) {
                        }
                        composer4.endReplaceGroup();
                        if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
                        }
                        composer4.endReplaceGroup();
                        ComposerKt.sourceInformationMarkerEnd(composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd(composer2);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (RouteScreen$lambda$10(sent$delegate2)) {
                        }
                        $composer3.endReplaceGroup();
                        RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
                        if (RouteScreen$lambda$16 != null) {
                        }
                        RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
                        if (RouteScreen$lambda$13 != null) {
                        }
                        if (ComposerKt.isTraceInProgress()) {
                        }
                        routeViewModel2 = routeViewModel5;
                    }
                } else {
                    ctx = ctx4;
                }
                suggestionsSuppressed$delegate = suggestionsSuppressed$delegate3;
                suggestionsSuppressed$delegate2 = suggestionsLoading$delegate;
                routeState$delegate = routeState$delegate3;
                destName = sourceDestName;
                ctx2 = ctx;
                rememberedValue20 = (Function2) new RouteScreenKt$RouteScreen$2$1(ctx, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate2, suggestionsStatus$delegate, suggestionsSuppressed$delegate, routeState$delegate3, null);
                $composer3.updateRememberedValue(rememberedValue20);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(RouteScreen$lambda$19, valueOf, (Function2) rememberedValue20, $composer3, 0);
                ComposerKt.sourceInformationMarkerStart($composer3, -2131771185, "CC(remember):RouteScreen.kt#9igjgp");
                if ((($dirty3 & 896) ^ 384) <= 256) {
                }
                rememberedValue = $composer3.rememberedValue();
                if (z) {
                }
                final MutableState suggestionsSuppressed$delegate42 = suggestionsSuppressed$delegate;
                final RouteViewModel routeViewModel72 = routeViewModel6;
                final MutableState suggestions$delegate22 = suggestionsSuppressed$delegate2;
                Object obj23 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj32) {
                        return RouteScreenKt.RouteScreen$lambda$39$lambda$38(RouteViewModel.this, suggestionsSuppressed$delegate42, suggestions$delegate, suggestions$delegate22, suggestionsStatus$delegate, searchQuery$delegate, showSavedDestinations$delegate2, (PlaceSearch.Hit) obj32);
                    }
                };
                routeViewModel3 = routeViewModel72;
                suggestionsSuppressed$delegate = suggestionsSuppressed$delegate42;
                suggestions$delegate = suggestions$delegate;
                $composer3.updateRememberedValue(obj23);
                rememberedValue = obj23;
                final Function1 selectSuggestion2 = (Function1) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerStart($composer3, -2131750886, "CC(remember):RouteScreen.kt#9igjgp");
                rememberedValue2 = $composer3.rememberedValue();
                if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                }
                final String[] navPermissions2 = (String[]) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions2 = new ActivityResultContracts.RequestMultiplePermissions();
                ComposerKt.sourceInformationMarkerStart($composer3, -2131727730, "CC(remember):RouteScreen.kt#9igjgp");
                rememberedValue3 = $composer3.rememberedValue();
                if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ManagedActivityResultLauncher navPermLauncher22 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions2, (Function1) rememberedValue3, $composer3, 48);
                ActivityResultContracts.PickVisualMedia pickVisualMedia2 = new ActivityResultContracts.PickVisualMedia();
                ComposerKt.sourceInformationMarkerStart($composer3, -2131722424, "CC(remember):RouteScreen.kt#9igjgp");
                if ((($dirty3 & 896) ^ 384) <= 256) {
                }
                Object rememberedValue212 = $composer3.rememberedValue();
                if (z2) {
                }
                Object obj52 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj62) {
                        return RouteScreenKt.RouteScreen$lambda$45$lambda$44(RouteViewModel.this, (Uri) obj62);
                    }
                };
                $composer3.updateRememberedValue(obj52);
                rememberedValue212 = obj52;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ManagedActivityResultLauncher qrPicker22 = ActivityResultRegistryKt.rememberLauncherForActivityResult(pickVisualMedia2, (Function1) rememberedValue212, $composer3, 0);
                ActivityResultContracts.RequestPermission requestPermission2 = new ActivityResultContracts.RequestPermission();
                ComposerKt.sourceInformationMarkerStart($composer3, -2131716088, "CC(remember):RouteScreen.kt#9igjgp");
                if ((($dirty3 & 896) ^ 384) <= 256) {
                }
                Object rememberedValue222 = $composer3.rememberedValue();
                if (z3) {
                }
                Object obj62 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda21
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj73) {
                        return RouteScreenKt.RouteScreen$lambda$47$lambda$46(RouteViewModel.this, ((Boolean) obj73).booleanValue());
                    }
                };
                $composer3.updateRememberedValue(obj62);
                rememberedValue222 = obj62;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ManagedActivityResultLauncher locationPermLauncher22 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission2, (Function1) rememberedValue222, $composer3, 0);
                Unit unit10 = Unit.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -2131713748, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance2 = $composer3.changedInstance(ctx2) | (((($dirty3 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty3 & 384) == 256) | $composer3.changedInstance(locationPermLauncher22);
                Object rememberedValue232 = $composer3.rememberedValue();
                if (changedInstance2) {
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.LaunchedEffect(unit10, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue232, $composer3, 6);
                    Boolean valueOf222 = Boolean.valueOf(RouteScreen$lambda$7(sent$delegate3));
                    ComposerKt.sourceInformationMarkerStart($composer3, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                    changed = (($dirty3 & 112) == 32) | $composer3.changed(routeState$delegate);
                    Object rememberedValue2422 = $composer3.rememberedValue();
                    if (changed) {
                    }
                    Object obj2122 = (Function2) new RouteScreenKt$RouteScreen$4$1(onSentToDash, sent$delegate3, routeState$delegate, null);
                    $composer3.updateRememberedValue(obj2122);
                    rememberedValue2422 = obj2122;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    EffectsKt.LaunchedEffect(valueOf222, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2422, $composer3, 0);
                    Modifier fillMaxSize$default322 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Arrangement.Vertical top322 = Arrangement.INSTANCE.getTop();
                    Alignment.Horizontal start322 = Alignment.INSTANCE.getStart();
                    MeasurePolicy columnMeasurePolicy822 = ColumnKt.columnMeasurePolicy(top322, start322, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                    routeState$delegate2 = routeState$delegate;
                    Context ctx5222 = ctx2;
                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode2022 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap2022 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2022 = ComposedModifierKt.materializeModifier($composer3, fillMaxSize$default322);
                    Function0 constructor2022 = ComposeUiNode.INSTANCE.getConstructor();
                    int i41022 = ((((6 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    Composer m4364constructorimpl2022 = Updater.m4364constructorimpl($composer3);
                    Updater.m4372setimpl(m4364constructorimpl2022, columnMeasurePolicy822, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl2022, currentCompositionLocalMap2022, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl2022, Integer.valueOf(hashCode2022), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl2022, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl2022, materializeModifier2022, ComposeUiNode.INSTANCE.getSetModifier());
                    int i51022 = (i41022 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    int i61022 = ((6 >> 6) & 112) | 6;
                    ColumnScope columnScope322 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                    Modifier m262backgroundbw27NRU$default322 = BackgroundKt.m262backgroundbw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.46f), ColorKt.getMapBase(), null, 2, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy322 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode2222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap2222 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2222 = ComposedModifierKt.materializeModifier($composer3, m262backgroundbw27NRU$default322);
                    Function0 constructor2222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i7722 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    Composer m4364constructorimpl2222 = Updater.m4364constructorimpl($composer3);
                    Updater.m4372setimpl(m4364constructorimpl2222, maybeCachedBoxMeasurePolicy322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl2222, currentCompositionLocalMap2222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl2222, Integer.valueOf(hashCode2222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl2222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl2222, materializeModifier2222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i8222 = (i7722 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    int i9222 = ((0 >> 6) & 112) | 6;
                    BoxScope boxScope222 = BoxScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                    Pair<Double, Double> currentLocation322 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                    if (currentLocation322 == null) {
                    }
                    Pair<Double, Double> currentLocation2222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                    if (currentLocation2222 == null) {
                    }
                    float currentBearing222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentBearing();
                    long locationRefreshKey222 = RouteScreen$lambda$0(routeState$delegate2).getLocationRefreshKey();
                    if (dest == null) {
                    }
                    Iterable<SharedLocation> waypoints222 = RouteScreen$lambda$0(routeState$delegate2).getWaypoints();
                    int i10222 = 0;
                    Collection arrayList522 = new ArrayList();
                    while (r101.hasNext()) {
                    }
                    ArrayList arrayList2222 = (List) arrayList522;
                    Route route522 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                    if (route522 == null) {
                    }
                    if (geometry == null) {
                    }
                    List<GeoPoint> list2222 = geometry;
                    Route route2222 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                    if (route2222 == null) {
                    }
                    if (traffic == null) {
                    }
                    List<TrafficInterval> list3222 = traffic;
                    routeAlternatives = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
                    list = routeAlternatives;
                    if (!(routeAlternatives.size() <= 1)) {
                    }
                    if (list == null) {
                    }
                    int selectedRouteIndex322 = RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
                    if (RouteScreen$lambda$0(routeState$delegate2).getRoute() == null) {
                    }
                    Modifier fillMaxSize$default2222 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                    if ((($dirty3 & 896) ^ 384) <= 256) {
                    }
                    Object rememberedValue25222 = $composer3.rememberedValue();
                    if (z4) {
                    }
                    Object obj7222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda22
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj8222) {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel.this, (LatLng) obj8222);
                        }
                    };
                    $composer3.updateRememberedValue(obj7222);
                    rememberedValue25222 = obj7222;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    FreeWayfindrMapKt.WayfindrMap(first, second, pair2, arrayList2222, list2222, list3222, list, selectedRouteIndex322, true, fillMaxSize$default2222, z9, false, currentBearing222, locationRefreshKey222, false, true, null, null, null, null, false, (Function1) rememberedValue25222, $composer3, 905969664, 221184, 0, 2033664);
                    Alignment.Vertical centerVertically722 = Alignment.INSTANCE.getCenterVertically();
                    Modifier m817paddingVpY3zN4422 = PaddingKt.m817paddingVpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope222.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopStart()), 0.0f, 1, null), Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(14));
                    ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy1122 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically722, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode3222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap3222 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier3222 = ComposedModifierKt.materializeModifier($composer3, m817paddingVpY3zN4422);
                    Function0 constructor3222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i11222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    int $dirty4222 = $dirty3;
                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    Composer m4364constructorimpl3222 = Updater.m4364constructorimpl($composer3);
                    Updater.m4372setimpl(m4364constructorimpl3222, rowMeasurePolicy1122, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl3222, currentCompositionLocalMap3222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl3222, Integer.valueOf(hashCode3222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl3222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl3222, materializeModifier3222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i12222 = (i11222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance822 = RowScopeInstance.INSTANCE;
                    int i13222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                    final ManagedActivityResultLauncher navPermLauncher3222 = navPermLauncher;
                    final ManagedActivityResultLauncher qrPicker3222 = qrPicker;
                    int $dirty5222 = $dirty4222;
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), onBack, BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, (($dirty4222 << 3) & 112) | 3072, 48);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                    WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(routeState$delegate2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(routeState$delegate2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(routeState$delegate2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), $composer3, 0, 4);
                    ImageVector recenter222 = WayfindrIcons.INSTANCE.getRecenter();
                    ComposerKt.sourceInformationMarkerStart($composer3, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                    if ((($dirty5222 & 896) ^ 384) <= 256) {
                    }
                    rememberedValue4 = $composer3.rememberedValue();
                    if (!z5) {
                    }
                    Object obj8222 = (KFunction) new RouteScreenKt$RouteScreen$5$1$5$1$1(routeViewModel3);
                    $composer3.updateRememberedValue(obj8222);
                    rememberedValue4 = obj8222;
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter222, (Function0) ((KFunction) rememberedValue4), BackgroundKt.m261backgroundbw27NRU(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, 3072, 48);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                    }
                    $composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Modifier m820paddingqDBjuR0$default722 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m817paddingVpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope322, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), RoundedCornerShapeKt.m1126RoundedCornerShapea9UjIt4$default(Dp.m7908constructorimpl(22), Dp.m7908constructorimpl(22), 0.0f, 0.0f, 12, null)), ColorKt.getBg1(), null, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18), Dp.m7908constructorimpl(8)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(20), 7, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy2222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode4222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap4222 = $composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier4222 = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default722);
                    Function0 constructor4222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i14222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer3.getApplier() instanceof Applier)) {
                    }
                    $composer3.startReusableNode();
                    if ($composer3.getInserting()) {
                    }
                    Composer m4364constructorimpl4222 = Updater.m4364constructorimpl($composer3);
                    Updater.m4372setimpl(m4364constructorimpl4222, columnMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl4222, currentCompositionLocalMap4222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl4222, Integer.valueOf(hashCode4222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl4222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl4222, materializeModifier4222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i15222 = (i14222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    int i16222 = ((0 >> 6) & 112) | 6;
                    ColumnScope columnScope2222 = ColumnScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                    BoxKt.Box(PaddingKt.m820paddingqDBjuR0$default(columnScope2222.align(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), Dp.m7908constructorimpl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), null, 2, null), Alignment.INSTANCE.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), $composer3, 0);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                    final MutableState suggestionsSuppressed$delegate5222 = suggestionsSuppressed$delegate;
                    final MutableState suggestionsLoading$delegate2222 = suggestionsSuppressed$delegate2;
                    final RouteViewModel routeViewModel8222 = routeViewModel3;
                    WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(4), null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda23
                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj102, Object obj112, Object obj122) {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74(Function1.this, routeViewModel8222, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate5222, suggestionsStatus$delegate, routeState$delegate2, suggestionsLoading$delegate2222, (ColumnScope) obj102, (Composer) obj112, ((Integer) obj122).intValue());
                        }
                    }, $composer3, 54), $composer3, 24966, 10);
                    searchNote = RouteScreen$lambda$0(routeState$delegate2).getSearchNote();
                    if (searchNote != null) {
                    }
                    ImageVector qrScan222 = WayfindrIcons.INSTANCE.getQrScan();
                    BtnVariant btnVariant422 = BtnVariant.Ghost;
                    BtnSize btnSize422 = BtnSize.Sm;
                    Modifier m820paddingqDBjuR0$default2222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(12), 7, null);
                    ComposerKt.sourceInformationMarkerStart($composer3, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                    changedInstance3 = $composer3.changedInstance(qrPicker3222);
                    Object rememberedValue27222 = $composer3.rememberedValue();
                    if (changedInstance3) {
                    }
                    rememberedValue27222 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda24
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher.this);
                        }
                    };
                    $composer3.updateRememberedValue(rememberedValue27222);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    WayfindrComponentsKt.WayfindrBtn("QR görselinden rota yükle", (Function0) rememberedValue27222, m820paddingqDBjuR0$default2222, qrScan222, btnVariant422, btnSize422, false, $composer3, 221574, 64);
                    composer = $composer3;
                    sharedRouteName = RouteScreen$lambda$0(routeState$delegate2).getSharedRouteName();
                    if (sharedRouteName != null) {
                    }
                    if (RouteScreen$lambda$0(routeState$delegate2).getWaypoints().isEmpty()) {
                    }
                    composer.endReplaceGroup();
                    if ((dest == null ? dest.getLat() : null) != null) {
                    }
                    composer.startReplaceGroup(2018241319);
                    ComposerKt.sourceInformation(composer, "438@22367L1104");
                    WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(16), null, ComposableSingletons$RouteScreenKt.INSTANCE.m8364getLambda$835303176$app(), composer, 24966, 10);
                    composer.endReplaceGroup();
                    WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(routeState$delegate2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(6), 7, null), composer, 48, 0);
                    Alignment.Vertical top2222 = Alignment.INSTANCE.getTop();
                    ComposerKt.sourceInformationMarkerStart(composer, 844473419, str5);
                    Modifier modifier922 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy3222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top2222, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode6222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                    CompositionLocalMap currentCompositionLocalMap6222 = composer.getCurrentCompositionLocalMap();
                    Modifier materializeModifier6222 = ComposedModifierKt.materializeModifier(composer, modifier922);
                    Function0 constructor6222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i20222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer.getApplier() instanceof Applier)) {
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                    }
                    Composer m4364constructorimpl6222 = Updater.m4364constructorimpl(composer);
                    Updater.m4372setimpl(m4364constructorimpl6222, rowMeasurePolicy3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl6222, currentCompositionLocalMap6222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl6222, Integer.valueOf(hashCode6222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl6222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl6222, materializeModifier6222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21222 = (i20222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str4);
                    RowScopeInstance rowScopeInstance3222 = RowScopeInstance.INSTANCE;
                    int i22222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                    Alignment center222 = Alignment.INSTANCE.getCenter();
                    Modifier m262backgroundbw27NRU$default2222 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(44)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getGoldTint(), null, 2, null);
                    ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy2222 = BoxKt.maybeCachedBoxMeasurePolicy(center222, false);
                    ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode7222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                    CompositionLocalMap currentCompositionLocalMap7222 = composer.getCurrentCompositionLocalMap();
                    Modifier materializeModifier7222 = ComposedModifierKt.materializeModifier(composer, m262backgroundbw27NRU$default2222);
                    Function0 constructor7222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i23222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer.getApplier() instanceof Applier)) {
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                    }
                    Composer m4364constructorimpl7222 = Updater.m4364constructorimpl(composer);
                    Updater.m4372setimpl(m4364constructorimpl7222, maybeCachedBoxMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl7222, currentCompositionLocalMap7222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl7222, Integer.valueOf(hashCode7222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl7222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl7222, materializeModifier7222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i24222 = (i23222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance222 = BoxScopeInstance.INSTANCE;
                    int i25222 = ((48 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), ColorKt.getGold(), composer, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), composer, 6);
                    ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier2222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy3222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode8222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                    CompositionLocalMap currentCompositionLocalMap8222 = composer.getCurrentCompositionLocalMap();
                    Modifier materializeModifier8222 = ComposedModifierKt.materializeModifier(composer, modifier2222);
                    Function0 constructor8222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i26222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer.getApplier() instanceof Applier)) {
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                    }
                    Composer m4364constructorimpl8222 = Updater.m4364constructorimpl(composer);
                    Updater.m4372setimpl(m4364constructorimpl8222, columnMeasurePolicy3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl8222, currentCompositionLocalMap8222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl8222, Integer.valueOf(hashCode8222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl8222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl8222, materializeModifier8222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i27222 = (i26222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance622 = ColumnScopeInstance.INSTANCE;
                    int i28222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer, -1541055177, "C:RouteScreen.kt#cef2n4");
                    if (RouteScreen$lambda$0(routeState$delegate2).isResolving()) {
                    }
                    if (StringsKt.isBlank(destSub2)) {
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
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), composer, 6);
                    if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                    }
                    composer.endReplaceGroup();
                    routing = RouteScreen$lambda$0(routeState$delegate2).getRouting();
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
                    Iterable listOf322 = CollectionsKt.listOf((Object[]) tripleArr222);
                    Arrangement.Horizontal m686spacedBy0680j_42222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    composer3 = composer;
                    int i42222 = 0;
                    ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str5);
                    Modifier modifier4222 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy5222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_42222, Alignment.INSTANCE.getTop(), composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode12222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap12222 = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier12222 = ComposedModifierKt.materializeModifier(composer3, modifier4222);
                    Function0 constructor12222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i43222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer3.getApplier() instanceof Applier)) {
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                    }
                    Composer m4364constructorimpl12222 = Updater.m4364constructorimpl(composer3);
                    MeasurePolicy measurePolicy722 = rowMeasurePolicy5222;
                    Updater.m4372setimpl(m4364constructorimpl12222, measurePolicy722, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl12222, currentCompositionLocalMap12222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl12222, Integer.valueOf(hashCode12222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl12222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl12222, materializeModifier12222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i44222 = (i43222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str4);
                    int i45222 = ((48 >> 6) & 112) | 6;
                    RowScope rowScope2222 = RowScopeInstance.INSTANCE;
                    Composer composer14222 = composer3;
                    ComposerKt.sourceInformationMarkerStart(composer14222, 213090107, "C:RouteScreen.kt#cef2n4");
                    composer14222.startReplaceGroup(1807989502);
                    ComposerKt.sourceInformation(composer14222, "*540@28620L859");
                    Iterable<Triple> iterable2222 = listOf322;
                    int i46222 = 0;
                    while (r39.hasNext()) {
                    }
                    Composer composer17222 = composer14222;
                    composer17222.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer17222);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), composer, 6);
                    collectAsState = SnapshotStateKt.collectAsState(dashConfig.getRoutingSource(), null, composer, 0, 1);
                    State collectAsState2222 = SnapshotStateKt.collectAsState(dashConfig.getMapTheme(), null, composer, 0, 1);
                    ComposerKt.sourceInformationMarkerStart(composer, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                    Composer composer18222 = composer;
                    rememberedValue5 = composer18222.rememberedValue();
                    if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                    }
                    boolean booleanValue222 = ((Boolean) rememberedValue5).booleanValue();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                    }
                    ChoiceHeader("Rota sağlayıcısı", str6, composer, 6);
                    Iterable available222 = RoutingSource.INSTANCE.getAvailable();
                    Collection arrayList3222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available222, 10));
                    it = available222.iterator();
                    while (it.hasNext()) {
                    }
                    ArrayList arrayList4222 = (List) arrayList3222;
                    String label222 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                    ComposerKt.sourceInformationMarkerStart(composer, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                    changed2 = $composer3.changed(collectAsState) | $composer3.changedInstance(dashConfig) | (((($dirty5222 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel8222)) || ($dirty5222 & 384) == 256);
                    Composer composer19222 = composer;
                    composer4 = composer;
                    Object rememberedValue30222 = composer19222.rememberedValue();
                    if (changed2) {
                    }
                    rememberedValue30222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj122) {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig.this, routeViewModel8222, collectAsState, (String) obj122);
                        }
                    };
                    composer19222.updateRememberedValue(rememberedValue30222);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    WayfindrComponentsKt.WayfindrSegmented(arrayList4222, label222, (Function1) rememberedValue30222, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), composer4, 3072, 0);
                    ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer4, 54);
                    Modifier m820paddingqDBjuR0$default4222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null);
                    Arrangement.Vertical m686spacedBy0680j_43222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
                    int i55222 = 0;
                    ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy7222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_43222, Alignment.INSTANCE.getStart(), composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode15222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                    CompositionLocalMap currentCompositionLocalMap15222 = composer4.getCurrentCompositionLocalMap();
                    Modifier materializeModifier15222 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default4222);
                    Function0 constructor15222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i56222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer4.getApplier() instanceof Applier)) {
                    }
                    composer4.startReusableNode();
                    if (composer4.getInserting()) {
                    }
                    Composer m4364constructorimpl15222 = Updater.m4364constructorimpl(composer4);
                    MeasurePolicy measurePolicy3222 = columnMeasurePolicy7222;
                    Updater.m4372setimpl(m4364constructorimpl15222, measurePolicy3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl15222, currentCompositionLocalMap15222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl15222, Integer.valueOf(hashCode15222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl15222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl15222, materializeModifier15222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i57222 = (i56222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance5222 = ColumnScopeInstance.INSTANCE;
                    int i58222 = ((54 >> 6) & 112) | 6;
                    Composer composer20222 = composer4;
                    ComposerKt.sourceInformationMarkerStart(composer20222, -1001505061, "C:RouteScreen.kt#cef2n4");
                    composer20222.startReplaceGroup(-1140683288);
                    ComposerKt.sourceInformation(composer20222, "*585@31259L1578");
                    Iterable<List> chunked222 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                    int i59222 = 0;
                    while (r41.hasNext()) {
                    }
                    Composer composer22222 = composer20222;
                    int $dirty8222 = $dirty5222;
                    final Context ctx8222 = ctx3;
                    composer22222.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer22222);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    Arrangement.Horizontal spaceBetween2222 = Arrangement.INSTANCE.getSpaceBetween();
                    Alignment.Vertical centerVertically5222 = Alignment.INSTANCE.getCenterVertically();
                    Modifier m820paddingqDBjuR0$default5222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(9), 7, null);
                    ComposerKt.sourceInformationMarkerStart(composer4, 844473419, str5);
                    MeasurePolicy rowMeasurePolicy9222 = RowKt.rowMeasurePolicy(spaceBetween2222, centerVertically5222, composer4, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode18222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                    CompositionLocalMap currentCompositionLocalMap18222 = composer4.getCurrentCompositionLocalMap();
                    Modifier materializeModifier18222 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default5222);
                    Function0 constructor18222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i71222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!(composer4.getApplier() instanceof Applier)) {
                    }
                    composer4.startReusableNode();
                    if (composer4.getInserting()) {
                    }
                    Composer m4364constructorimpl18222 = Updater.m4364constructorimpl(composer4);
                    Updater.m4372setimpl(m4364constructorimpl18222, rowMeasurePolicy9222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl18222, currentCompositionLocalMap18222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl18222, Integer.valueOf(hashCode18222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl18222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl18222, materializeModifier18222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i72222 = (i71222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, str4);
                    RowScopeInstance rowScopeInstance6222 = RowScopeInstance.INSTANCE;
                    int i73222 = ((438 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer4, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                    WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", null, composer4, 6, 2);
                    IconKt.m2517Iconww6aTOc(RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer4, 432, 0);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    composer4.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    List listOf2222 = CollectionsKt.listOf((Object[]) new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                    ComposerKt.sourceInformationMarkerStart(composer4, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                    final VoiceManager voiceManager3222 = voiceManager;
                    changedInstance4 = composer4.changedInstance(voiceManager3222);
                    rememberedValue6 = composer4.rememberedValue();
                    if (!changedInstance4) {
                    }
                    Object obj13222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda7
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj142) {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager.this, (String) obj142);
                        }
                    };
                    composer4.updateRememberedValue(obj13222);
                    rememberedValue6 = obj13222;
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    WayfindrComponentsKt.WayfindrSegmented(listOf2222, voice, (Function1) rememberedValue6, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null), composer4, 3078, 0);
                    if ((dest == null ? dest.getLat() : null) != null) {
                    }
                    if (!RouteScreen$lambda$7(sent$delegate3)) {
                    }
                    ImageVector check222 = !RouteScreen$lambda$7(sent$delegate3) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                    BtnVariant btnVariant2222 = !RouteScreen$lambda$7(sent$delegate3) ? BtnVariant.Secondary : BtnVariant.Primary;
                    BtnSize btnSize2222 = BtnSize.Lg;
                    if (RouteScreen$lambda$7(sent$delegate3)) {
                    }
                    Modifier fillMaxWidth$default2222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart(composer4, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                    changedInstance5 = composer4.changedInstance(ctx8222) | composer4.changedInstance(navPermLauncher3222) | composer4.changedInstance(navPermissions2);
                    Object rememberedValue32222 = composer4.rememberedValue();
                    if (!changedInstance5) {
                    }
                    z7 = z6;
                    sent$delegate = sent$delegate3;
                    Object obj20222 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher.this, navPermissions2, ctx8222, sent$delegate);
                        }
                    };
                    composer4.updateRememberedValue(obj20222);
                    rememberedValue32222 = obj20222;
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue32222, fillMaxWidth$default2222, check222, btnVariant2222, btnSize2222, z16, composer4, 196992, 0);
                    if (z7) {
                    }
                    composer4.endReplaceGroup();
                    if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
                    }
                    composer4.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $composer3.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    if (RouteScreen$lambda$10(sent$delegate2)) {
                    }
                    $composer3.endReplaceGroup();
                    RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
                    if (RouteScreen$lambda$16 != null) {
                    }
                    RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
                    if (RouteScreen$lambda$13 != null) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    routeViewModel2 = routeViewModel5;
                }
                Object obj222 = (Function2) new RouteScreenKt$RouteScreen$3$1(routeViewModel3, locationPermLauncher22, ctx2, null);
                $composer3.updateRememberedValue(obj222);
                rememberedValue232 = obj222;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(unit10, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue232, $composer3, 6);
                Boolean valueOf2222 = Boolean.valueOf(RouteScreen$lambda$7(sent$delegate3));
                ComposerKt.sourceInformationMarkerStart($composer3, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
                changed = (($dirty3 & 112) == 32) | $composer3.changed(routeState$delegate);
                Object rememberedValue24222 = $composer3.rememberedValue();
                if (changed) {
                }
                Object obj21222 = (Function2) new RouteScreenKt$RouteScreen$4$1(onSentToDash, sent$delegate3, routeState$delegate, null);
                $composer3.updateRememberedValue(obj21222);
                rememberedValue24222 = obj21222;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(valueOf2222, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue24222, $composer3, 0);
                Modifier fillMaxSize$default3222 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Arrangement.Vertical top3222 = Arrangement.INSTANCE.getTop();
                Alignment.Horizontal start3222 = Alignment.INSTANCE.getStart();
                MeasurePolicy columnMeasurePolicy8222 = ColumnKt.columnMeasurePolicy(top3222, start3222, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                routeState$delegate2 = routeState$delegate;
                Context ctx52222 = ctx2;
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode20222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap20222 = $composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier20222 = ComposedModifierKt.materializeModifier($composer3, fillMaxSize$default3222);
                Function0 constructor20222 = ComposeUiNode.INSTANCE.getConstructor();
                int i410222 = ((((6 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                Composer m4364constructorimpl20222 = Updater.m4364constructorimpl($composer3);
                Updater.m4372setimpl(m4364constructorimpl20222, columnMeasurePolicy8222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl20222, currentCompositionLocalMap20222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl20222, Integer.valueOf(hashCode20222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl20222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl20222, materializeModifier20222, ComposeUiNode.INSTANCE.getSetModifier());
                int i510222 = (i410222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i610222 = ((6 >> 6) & 112) | 6;
                ColumnScope columnScope3222 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
                Modifier m262backgroundbw27NRU$default3222 = BackgroundKt.m262backgroundbw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.46f), ColorKt.getMapBase(), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy3222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode22222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap22222 = $composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier22222 = ComposedModifierKt.materializeModifier($composer3, m262backgroundbw27NRU$default3222);
                Function0 constructor22222 = ComposeUiNode.INSTANCE.getConstructor();
                int i77222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                Composer m4364constructorimpl22222 = Updater.m4364constructorimpl($composer3);
                Updater.m4372setimpl(m4364constructorimpl22222, maybeCachedBoxMeasurePolicy3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl22222, currentCompositionLocalMap22222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl22222, Integer.valueOf(hashCode22222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl22222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl22222, materializeModifier22222, ComposeUiNode.INSTANCE.getSetModifier());
                int i82222 = (i77222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                int i92222 = ((0 >> 6) & 112) | 6;
                BoxScope boxScope2222 = BoxScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
                Pair<Double, Double> currentLocation3222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                if (currentLocation3222 == null) {
                }
                Pair<Double, Double> currentLocation22222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
                if (currentLocation22222 == null) {
                }
                float currentBearing2222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentBearing();
                long locationRefreshKey2222 = RouteScreen$lambda$0(routeState$delegate2).getLocationRefreshKey();
                if (dest == null) {
                }
                Iterable<SharedLocation> waypoints2222 = RouteScreen$lambda$0(routeState$delegate2).getWaypoints();
                int i102222 = 0;
                Collection arrayList5222 = new ArrayList();
                while (r101.hasNext()) {
                }
                ArrayList arrayList22222 = (List) arrayList5222;
                Route route5222 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                if (route5222 == null) {
                }
                if (geometry == null) {
                }
                List<GeoPoint> list22222 = geometry;
                Route route22222 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
                if (route22222 == null) {
                }
                if (traffic == null) {
                }
                List<TrafficInterval> list32222 = traffic;
                routeAlternatives = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
                list = routeAlternatives;
                if (!(routeAlternatives.size() <= 1)) {
                }
                if (list == null) {
                }
                int selectedRouteIndex3222 = RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
                if (RouteScreen$lambda$0(routeState$delegate2).getRoute() == null) {
                }
                Modifier fillMaxSize$default22222 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer3, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
                if ((($dirty3 & 896) ^ 384) <= 256) {
                }
                Object rememberedValue252222 = $composer3.rememberedValue();
                if (z4) {
                }
                Object obj72222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda22
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj82222) {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel.this, (LatLng) obj82222);
                    }
                };
                $composer3.updateRememberedValue(obj72222);
                rememberedValue252222 = obj72222;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                FreeWayfindrMapKt.WayfindrMap(first, second, pair2, arrayList22222, list22222, list32222, list, selectedRouteIndex3222, true, fillMaxSize$default22222, z9, false, currentBearing2222, locationRefreshKey2222, false, true, null, null, null, null, false, (Function1) rememberedValue252222, $composer3, 905969664, 221184, 0, 2033664);
                Alignment.Vertical centerVertically7222 = Alignment.INSTANCE.getCenterVertically();
                Modifier m817paddingVpY3zN44222 = PaddingKt.m817paddingVpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope2222.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopStart()), 0.0f, 1, null), Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(14));
                ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy11222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically7222, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode32222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap32222 = $composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier32222 = ComposedModifierKt.materializeModifier($composer3, m817paddingVpY3zN44222);
                Function0 constructor32222 = ComposeUiNode.INSTANCE.getConstructor();
                int i112222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                int $dirty42222 = $dirty3;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                Composer m4364constructorimpl32222 = Updater.m4364constructorimpl($composer3);
                Updater.m4372setimpl(m4364constructorimpl32222, rowMeasurePolicy11222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl32222, currentCompositionLocalMap32222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl32222, Integer.valueOf(hashCode32222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl32222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl32222, materializeModifier32222, ComposeUiNode.INSTANCE.getSetModifier());
                int i122222 = (i112222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance8222 = RowScopeInstance.INSTANCE;
                int i132222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
                final ManagedActivityResultLauncher navPermLauncher32222 = navPermLauncher;
                final ManagedActivityResultLauncher qrPicker32222 = qrPicker;
                int $dirty52222 = $dirty42222;
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), onBack, BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, (($dirty42222 << 3) & 112) | 3072, 48);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(routeState$delegate2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(routeState$delegate2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(routeState$delegate2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), $composer3, 0, 4);
                ImageVector recenter2222 = WayfindrIcons.INSTANCE.getRecenter();
                ComposerKt.sourceInformationMarkerStart($composer3, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
                if ((($dirty52222 & 896) ^ 384) <= 256) {
                }
                rememberedValue4 = $composer3.rememberedValue();
                if (!z5) {
                }
                Object obj82222 = (KFunction) new RouteScreenKt$RouteScreen$5$1$5$1$1(routeViewModel3);
                $composer3.updateRememberedValue(obj82222);
                rememberedValue4 = obj82222;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter2222, (Function0) ((KFunction) rememberedValue4), BackgroundKt.m261backgroundbw27NRU(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, 3072, 48);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                }
                $composer3.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                Modifier m820paddingqDBjuR0$default7222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m817paddingVpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope3222, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), RoundedCornerShapeKt.m1126RoundedCornerShapea9UjIt4$default(Dp.m7908constructorimpl(22), Dp.m7908constructorimpl(22), 0.0f, 0.0f, 12, null)), ColorKt.getBg1(), null, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18), Dp.m7908constructorimpl(8)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(20), 7, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy22222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode42222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap42222 = $composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier42222 = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default7222);
                Function0 constructor42222 = ComposeUiNode.INSTANCE.getConstructor();
                int i142222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                }
                Composer m4364constructorimpl42222 = Updater.m4364constructorimpl($composer3);
                Updater.m4372setimpl(m4364constructorimpl42222, columnMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl42222, currentCompositionLocalMap42222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl42222, Integer.valueOf(hashCode42222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl42222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl42222, materializeModifier42222, ComposeUiNode.INSTANCE.getSetModifier());
                int i152222 = (i142222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                int i162222 = ((0 >> 6) & 112) | 6;
                ColumnScope columnScope22222 = ColumnScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer3, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
                BoxKt.Box(PaddingKt.m820paddingqDBjuR0$default(columnScope22222.align(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), Dp.m7908constructorimpl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), null, 2, null), Alignment.INSTANCE.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), $composer3, 0);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
                final MutableState suggestionsSuppressed$delegate52222 = suggestionsSuppressed$delegate;
                final MutableState suggestionsLoading$delegate22222 = suggestionsSuppressed$delegate2;
                final RouteViewModel routeViewModel82222 = routeViewModel3;
                WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(4), null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda23
                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj102, Object obj112, Object obj122) {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74(Function1.this, routeViewModel82222, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate52222, suggestionsStatus$delegate, routeState$delegate2, suggestionsLoading$delegate22222, (ColumnScope) obj102, (Composer) obj112, ((Integer) obj122).intValue());
                    }
                }, $composer3, 54), $composer3, 24966, 10);
                searchNote = RouteScreen$lambda$0(routeState$delegate2).getSearchNote();
                if (searchNote != null) {
                }
                ImageVector qrScan2222 = WayfindrIcons.INSTANCE.getQrScan();
                BtnVariant btnVariant4222 = BtnVariant.Ghost;
                BtnSize btnSize4222 = BtnSize.Sm;
                Modifier m820paddingqDBjuR0$default22222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(12), 7, null);
                ComposerKt.sourceInformationMarkerStart($composer3, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance3 = $composer3.changedInstance(qrPicker32222);
                Object rememberedValue272222 = $composer3.rememberedValue();
                if (changedInstance3) {
                }
                rememberedValue272222 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda24
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher.this);
                    }
                };
                $composer3.updateRememberedValue(rememberedValue272222);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                WayfindrComponentsKt.WayfindrBtn("QR görselinden rota yükle", (Function0) rememberedValue272222, m820paddingqDBjuR0$default22222, qrScan2222, btnVariant4222, btnSize4222, false, $composer3, 221574, 64);
                composer = $composer3;
                sharedRouteName = RouteScreen$lambda$0(routeState$delegate2).getSharedRouteName();
                if (sharedRouteName != null) {
                }
                if (RouteScreen$lambda$0(routeState$delegate2).getWaypoints().isEmpty()) {
                }
                composer.endReplaceGroup();
                if ((dest == null ? dest.getLat() : null) != null) {
                }
                composer.startReplaceGroup(2018241319);
                ComposerKt.sourceInformation(composer, "438@22367L1104");
                WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(16), null, ComposableSingletons$RouteScreenKt.INSTANCE.m8364getLambda$835303176$app(), composer, 24966, 10);
                composer.endReplaceGroup();
                WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(routeState$delegate2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(6), 7, null), composer, 48, 0);
                Alignment.Vertical top22222 = Alignment.INSTANCE.getTop();
                ComposerKt.sourceInformationMarkerStart(composer, 844473419, str5);
                Modifier modifier9222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy32222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top22222, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode62222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap62222 = composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier62222 = ComposedModifierKt.materializeModifier(composer, modifier9222);
                Function0 constructor62222 = ComposeUiNode.INSTANCE.getConstructor();
                int i202222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                }
                Composer m4364constructorimpl62222 = Updater.m4364constructorimpl(composer);
                Updater.m4372setimpl(m4364constructorimpl62222, rowMeasurePolicy32222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl62222, currentCompositionLocalMap62222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl62222, Integer.valueOf(hashCode62222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl62222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl62222, materializeModifier62222, ComposeUiNode.INSTANCE.getSetModifier());
                int i212222 = (i202222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str4);
                RowScopeInstance rowScopeInstance32222 = RowScopeInstance.INSTANCE;
                int i222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
                Alignment center2222 = Alignment.INSTANCE.getCenter();
                Modifier m262backgroundbw27NRU$default22222 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(44)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getGoldTint(), null, 2, null);
                ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy22222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222, false);
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode72222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap72222 = composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier72222 = ComposedModifierKt.materializeModifier(composer, m262backgroundbw27NRU$default22222);
                Function0 constructor72222 = ComposeUiNode.INSTANCE.getConstructor();
                int i232222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                }
                Composer m4364constructorimpl72222 = Updater.m4364constructorimpl(composer);
                Updater.m4372setimpl(m4364constructorimpl72222, maybeCachedBoxMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl72222, currentCompositionLocalMap72222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl72222, Integer.valueOf(hashCode72222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl72222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl72222, materializeModifier72222, ComposeUiNode.INSTANCE.getSetModifier());
                int i242222 = (i232222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2222 = BoxScopeInstance.INSTANCE;
                int i252222 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), ColorKt.getGold(), composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                ComposerKt.sourceInformationMarkerEnd(composer);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), composer, 6);
                ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier22222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy32222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode82222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
                CompositionLocalMap currentCompositionLocalMap82222 = composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier82222 = ComposedModifierKt.materializeModifier(composer, modifier22222);
                Function0 constructor82222 = ComposeUiNode.INSTANCE.getConstructor();
                int i262222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer.getApplier() instanceof Applier)) {
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                }
                Composer m4364constructorimpl82222 = Updater.m4364constructorimpl(composer);
                Updater.m4372setimpl(m4364constructorimpl82222, columnMeasurePolicy32222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl82222, currentCompositionLocalMap82222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl82222, Integer.valueOf(hashCode82222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl82222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl82222, materializeModifier82222, ComposeUiNode.INSTANCE.getSetModifier());
                int i272222 = (i262222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance6222 = ColumnScopeInstance.INSTANCE;
                int i282222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer, -1541055177, "C:RouteScreen.kt#cef2n4");
                if (RouteScreen$lambda$0(routeState$delegate2).isResolving()) {
                }
                if (StringsKt.isBlank(destSub2)) {
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
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), composer, 6);
                if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
                }
                composer.endReplaceGroup();
                routing = RouteScreen$lambda$0(routeState$delegate2).getRouting();
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
                Iterable listOf3222 = CollectionsKt.listOf((Object[]) tripleArr2222);
                Arrangement.Horizontal m686spacedBy0680j_422222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                composer3 = composer;
                int i422222 = 0;
                ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str5);
                Modifier modifier42222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy52222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_422222, Alignment.INSTANCE.getTop(), composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode122222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap122222 = composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier122222 = ComposedModifierKt.materializeModifier(composer3, modifier42222);
                Function0 constructor122222 = ComposeUiNode.INSTANCE.getConstructor();
                int i432222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer3.getApplier() instanceof Applier)) {
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                }
                Composer m4364constructorimpl122222 = Updater.m4364constructorimpl(composer3);
                MeasurePolicy measurePolicy7222 = rowMeasurePolicy52222;
                Updater.m4372setimpl(m4364constructorimpl122222, measurePolicy7222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl122222, currentCompositionLocalMap122222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl122222, Integer.valueOf(hashCode122222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl122222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl122222, materializeModifier122222, ComposeUiNode.INSTANCE.getSetModifier());
                int i442222 = (i432222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str4);
                int i452222 = ((48 >> 6) & 112) | 6;
                RowScope rowScope22222 = RowScopeInstance.INSTANCE;
                Composer composer142222 = composer3;
                ComposerKt.sourceInformationMarkerStart(composer142222, 213090107, "C:RouteScreen.kt#cef2n4");
                composer142222.startReplaceGroup(1807989502);
                ComposerKt.sourceInformation(composer142222, "*540@28620L859");
                Iterable<Triple> iterable22222 = listOf3222;
                int i462222 = 0;
                while (r39.hasNext()) {
                }
                Composer composer172222 = composer142222;
                composer172222.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer172222);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), composer, 6);
                collectAsState = SnapshotStateKt.collectAsState(dashConfig.getRoutingSource(), null, composer, 0, 1);
                State collectAsState22222 = SnapshotStateKt.collectAsState(dashConfig.getMapTheme(), null, composer, 0, 1);
                ComposerKt.sourceInformationMarkerStart(composer, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
                Composer composer182222 = composer;
                rememberedValue5 = composer182222.rememberedValue();
                if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                }
                boolean booleanValue2222 = ((Boolean) rememberedValue5).booleanValue();
                ComposerKt.sourceInformationMarkerEnd(composer);
                switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
                }
                ChoiceHeader("Rota sağlayıcısı", str6, composer, 6);
                Iterable available2222 = RoutingSource.INSTANCE.getAvailable();
                Collection arrayList32222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available2222, 10));
                it = available2222.iterator();
                while (it.hasNext()) {
                }
                ArrayList arrayList42222 = (List) arrayList32222;
                String label2222 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
                ComposerKt.sourceInformationMarkerStart(composer, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
                changed2 = $composer3.changed(collectAsState) | $composer3.changedInstance(dashConfig) | (((($dirty52222 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel82222)) || ($dirty52222 & 384) == 256);
                Composer composer192222 = composer;
                composer4 = composer;
                Object rememberedValue302222 = composer192222.rememberedValue();
                if (changed2) {
                }
                rememberedValue302222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj122) {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig.this, routeViewModel82222, collectAsState, (String) obj122);
                    }
                };
                composer192222.updateRememberedValue(rememberedValue302222);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                WayfindrComponentsKt.WayfindrSegmented(arrayList42222, label2222, (Function1) rememberedValue302222, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), composer4, 3072, 0);
                ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer4, 54);
                Modifier m820paddingqDBjuR0$default42222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null);
                Arrangement.Vertical m686spacedBy0680j_432222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
                int i552222 = 0;
                ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy72222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_432222, Alignment.INSTANCE.getStart(), composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode152222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap152222 = composer4.getCurrentCompositionLocalMap();
                Modifier materializeModifier152222 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default42222);
                Function0 constructor152222 = ComposeUiNode.INSTANCE.getConstructor();
                int i562222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer4.getApplier() instanceof Applier)) {
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                }
                Composer m4364constructorimpl152222 = Updater.m4364constructorimpl(composer4);
                MeasurePolicy measurePolicy32222 = columnMeasurePolicy72222;
                Updater.m4372setimpl(m4364constructorimpl152222, measurePolicy32222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl152222, currentCompositionLocalMap152222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl152222, Integer.valueOf(hashCode152222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl152222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl152222, materializeModifier152222, ComposeUiNode.INSTANCE.getSetModifier());
                int i572222 = (i562222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance52222 = ColumnScopeInstance.INSTANCE;
                int i582222 = ((54 >> 6) & 112) | 6;
                Composer composer202222 = composer4;
                ComposerKt.sourceInformationMarkerStart(composer202222, -1001505061, "C:RouteScreen.kt#cef2n4");
                composer202222.startReplaceGroup(-1140683288);
                ComposerKt.sourceInformation(composer202222, "*585@31259L1578");
                Iterable<List> chunked2222 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
                int i592222 = 0;
                while (r41.hasNext()) {
                }
                Composer composer222222 = composer202222;
                int $dirty82222 = $dirty52222;
                final Context ctx82222 = ctx3;
                composer222222.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer222222);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                Arrangement.Horizontal spaceBetween22222 = Arrangement.INSTANCE.getSpaceBetween();
                Alignment.Vertical centerVertically52222 = Alignment.INSTANCE.getCenterVertically();
                Modifier m820paddingqDBjuR0$default52222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(9), 7, null);
                ComposerKt.sourceInformationMarkerStart(composer4, 844473419, str5);
                MeasurePolicy rowMeasurePolicy92222 = RowKt.rowMeasurePolicy(spaceBetween22222, centerVertically52222, composer4, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode182222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
                CompositionLocalMap currentCompositionLocalMap182222 = composer4.getCurrentCompositionLocalMap();
                Modifier materializeModifier182222 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default52222);
                Function0 constructor182222 = ComposeUiNode.INSTANCE.getConstructor();
                int i712222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!(composer4.getApplier() instanceof Applier)) {
                }
                composer4.startReusableNode();
                if (composer4.getInserting()) {
                }
                Composer m4364constructorimpl182222 = Updater.m4364constructorimpl(composer4);
                Updater.m4372setimpl(m4364constructorimpl182222, rowMeasurePolicy92222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl182222, currentCompositionLocalMap182222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl182222, Integer.valueOf(hashCode182222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl182222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl182222, materializeModifier182222, ComposeUiNode.INSTANCE.getSetModifier());
                int i722222 = (i712222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, str4);
                RowScopeInstance rowScopeInstance62222 = RowScopeInstance.INSTANCE;
                int i732222 = ((438 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart(composer4, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
                WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", null, composer4, 6, 2);
                IconKt.m2517Iconww6aTOc(RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer4, 432, 0);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                composer4.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd(composer4);
                List listOf22222 = CollectionsKt.listOf((Object[]) new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
                ComposerKt.sourceInformationMarkerStart(composer4, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
                final VoiceManager voiceManager32222 = voiceManager;
                changedInstance4 = composer4.changedInstance(voiceManager32222);
                rememberedValue6 = composer4.rememberedValue();
                if (!changedInstance4) {
                }
                Object obj132222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj142) {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager.this, (String) obj142);
                    }
                };
                composer4.updateRememberedValue(obj132222);
                rememberedValue6 = obj132222;
                ComposerKt.sourceInformationMarkerEnd(composer4);
                WayfindrComponentsKt.WayfindrSegmented(listOf22222, voice, (Function1) rememberedValue6, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null), composer4, 3078, 0);
                if ((dest == null ? dest.getLat() : null) != null) {
                }
                if (!RouteScreen$lambda$7(sent$delegate3)) {
                }
                ImageVector check2222 = !RouteScreen$lambda$7(sent$delegate3) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
                BtnVariant btnVariant22222 = !RouteScreen$lambda$7(sent$delegate3) ? BtnVariant.Secondary : BtnVariant.Primary;
                BtnSize btnSize22222 = BtnSize.Lg;
                if (RouteScreen$lambda$7(sent$delegate3)) {
                }
                Modifier fillMaxWidth$default22222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer4, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance5 = composer4.changedInstance(ctx82222) | composer4.changedInstance(navPermLauncher32222) | composer4.changedInstance(navPermissions2);
                Object rememberedValue322222 = composer4.rememberedValue();
                if (!changedInstance5) {
                }
                z7 = z6;
                sent$delegate = sent$delegate3;
                Object obj202222 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher.this, navPermissions2, ctx82222, sent$delegate);
                    }
                };
                composer4.updateRememberedValue(obj202222);
                rememberedValue322222 = obj202222;
                ComposerKt.sourceInformationMarkerEnd(composer4);
                WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue322222, fillMaxWidth$default22222, check2222, btnVariant22222, btnSize22222, z16, composer4, 196992, 0);
                if (z7) {
                }
                composer4.endReplaceGroup();
                if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
                }
                composer4.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (RouteScreen$lambda$10(sent$delegate2)) {
                }
                $composer3.endReplaceGroup();
                RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
                if (RouteScreen$lambda$16 != null) {
                }
                RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
                if (RouteScreen$lambda$13 != null) {
                }
                if (ComposerKt.isTraceInProgress()) {
                }
                routeViewModel2 = routeViewModel5;
            }
            Object routeScreenKt$RouteScreen$1$1 = new RouteScreenKt$RouteScreen$1$1(sourceDestName2, name3, routeState$delegate3, suggestionsSuppressed$delegate3, suggestions$delegate, searchQuery$delegate, null);
            sourceDestName = name3;
            Object obj24 = (Function2) routeScreenKt$RouteScreen$1$1;
            $composer3.updateRememberedValue(obj24);
            rememberedValue19 = obj24;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(sourceDestName, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue19, $composer3, 0);
            String RouteScreen$lambda$192 = RouteScreen$lambda$19(searchQuery$delegate);
            Boolean valueOf4 = Boolean.valueOf(RouteScreen$lambda$34(suggestionsSuppressed$delegate3));
            ComposerKt.sourceInformationMarkerStart($composer3, -2131807658, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance = $composer3.changedInstance(ctx4) | $composer3.changed(routeState$delegate3);
            Object rememberedValue202 = $composer3.rememberedValue();
            if (changedInstance) {
            }
            suggestionsSuppressed$delegate = suggestionsSuppressed$delegate3;
            suggestionsSuppressed$delegate2 = suggestionsLoading$delegate;
            routeState$delegate = routeState$delegate3;
            destName = sourceDestName;
            ctx2 = ctx;
            rememberedValue202 = (Function2) new RouteScreenKt$RouteScreen$2$1(ctx, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate2, suggestionsStatus$delegate, suggestionsSuppressed$delegate, routeState$delegate3, null);
            $composer3.updateRememberedValue(rememberedValue202);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(RouteScreen$lambda$192, valueOf4, (Function2) rememberedValue202, $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131771185, "CC(remember):RouteScreen.kt#9igjgp");
            if ((($dirty3 & 896) ^ 384) <= 256) {
            }
            rememberedValue = $composer3.rememberedValue();
            if (z) {
            }
            final MutableState suggestionsSuppressed$delegate422 = suggestionsSuppressed$delegate;
            final RouteViewModel routeViewModel722 = routeViewModel6;
            final MutableState suggestions$delegate222 = suggestionsSuppressed$delegate2;
            Object obj232 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj32) {
                    return RouteScreenKt.RouteScreen$lambda$39$lambda$38(RouteViewModel.this, suggestionsSuppressed$delegate422, suggestions$delegate, suggestions$delegate222, suggestionsStatus$delegate, searchQuery$delegate, showSavedDestinations$delegate2, (PlaceSearch.Hit) obj32);
                }
            };
            routeViewModel3 = routeViewModel722;
            suggestionsSuppressed$delegate = suggestionsSuppressed$delegate422;
            suggestions$delegate = suggestions$delegate;
            $composer3.updateRememberedValue(obj232);
            rememberedValue = obj232;
            final Function1 selectSuggestion22 = (Function1) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -2131750886, "CC(remember):RouteScreen.kt#9igjgp");
            rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            }
            final String[] navPermissions22 = (String[]) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions22 = new ActivityResultContracts.RequestMultiplePermissions();
            ComposerKt.sourceInformationMarkerStart($composer3, -2131727730, "CC(remember):RouteScreen.kt#9igjgp");
            rememberedValue3 = $composer3.rememberedValue();
            if (rememberedValue3 == Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ManagedActivityResultLauncher navPermLauncher222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions22, (Function1) rememberedValue3, $composer3, 48);
            ActivityResultContracts.PickVisualMedia pickVisualMedia22 = new ActivityResultContracts.PickVisualMedia();
            ComposerKt.sourceInformationMarkerStart($composer3, -2131722424, "CC(remember):RouteScreen.kt#9igjgp");
            if ((($dirty3 & 896) ^ 384) <= 256) {
            }
            Object rememberedValue2122 = $composer3.rememberedValue();
            if (z2) {
            }
            Object obj522 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj622) {
                    return RouteScreenKt.RouteScreen$lambda$45$lambda$44(RouteViewModel.this, (Uri) obj622);
                }
            };
            $composer3.updateRememberedValue(obj522);
            rememberedValue2122 = obj522;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ManagedActivityResultLauncher qrPicker222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(pickVisualMedia22, (Function1) rememberedValue2122, $composer3, 0);
            ActivityResultContracts.RequestPermission requestPermission22 = new ActivityResultContracts.RequestPermission();
            ComposerKt.sourceInformationMarkerStart($composer3, -2131716088, "CC(remember):RouteScreen.kt#9igjgp");
            if ((($dirty3 & 896) ^ 384) <= 256) {
            }
            Object rememberedValue2222 = $composer3.rememberedValue();
            if (z3) {
            }
            Object obj622 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj73) {
                    return RouteScreenKt.RouteScreen$lambda$47$lambda$46(RouteViewModel.this, ((Boolean) obj73).booleanValue());
                }
            };
            $composer3.updateRememberedValue(obj622);
            rememberedValue2222 = obj622;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ManagedActivityResultLauncher locationPermLauncher222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestPermission22, (Function1) rememberedValue2222, $composer3, 0);
            Unit unit102 = Unit.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -2131713748, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance2 = $composer3.changedInstance(ctx2) | (((($dirty3 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel3)) || ($dirty3 & 384) == 256) | $composer3.changedInstance(locationPermLauncher222);
            Object rememberedValue2322 = $composer3.rememberedValue();
            if (changedInstance2) {
            }
            Object obj2222 = (Function2) new RouteScreenKt$RouteScreen$3$1(routeViewModel3, locationPermLauncher222, ctx2, null);
            $composer3.updateRememberedValue(obj2222);
            rememberedValue2322 = obj2222;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(unit102, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2322, $composer3, 6);
            Boolean valueOf22222 = Boolean.valueOf(RouteScreen$lambda$7(sent$delegate3));
            ComposerKt.sourceInformationMarkerStart($composer3, -2131706385, "CC(remember):RouteScreen.kt#9igjgp");
            changed = (($dirty3 & 112) == 32) | $composer3.changed(routeState$delegate);
            Object rememberedValue242222 = $composer3.rememberedValue();
            if (changed) {
            }
            Object obj212222 = (Function2) new RouteScreenKt$RouteScreen$4$1(onSentToDash, sent$delegate3, routeState$delegate, null);
            $composer3.updateRememberedValue(obj212222);
            rememberedValue242222 = obj212222;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(valueOf22222, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue242222, $composer3, 0);
            Modifier fillMaxSize$default32222 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Arrangement.Vertical top32222 = Arrangement.INSTANCE.getTop();
            Alignment.Horizontal start32222 = Alignment.INSTANCE.getStart();
            MeasurePolicy columnMeasurePolicy82222 = ColumnKt.columnMeasurePolicy(top32222, start32222, $composer3, ((6 >> 3) & 14) | ((6 >> 3) & 112));
            routeState$delegate2 = routeState$delegate;
            Context ctx522222 = ctx2;
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode202222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap202222 = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier202222 = ComposedModifierKt.materializeModifier($composer3, fillMaxSize$default32222);
            Function0 constructor202222 = ComposeUiNode.INSTANCE.getConstructor();
            int i4102222 = ((((6 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            Composer m4364constructorimpl202222 = Updater.m4364constructorimpl($composer3);
            Updater.m4372setimpl(m4364constructorimpl202222, columnMeasurePolicy82222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl202222, currentCompositionLocalMap202222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl202222, Integer.valueOf(hashCode202222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl202222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl202222, materializeModifier202222, ComposeUiNode.INSTANCE.getSetModifier());
            int i5102222 = (i4102222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i6102222 = ((6 >> 6) & 112) | 6;
            ColumnScope columnScope32222 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 842713540, "C171@7620L3504,254@11428L21,248@11187L27515:RouteScreen.kt#cef2n4");
            Modifier m262backgroundbw27NRU$default32222 = BackgroundKt.m262backgroundbw27NRU$default(SizeKt.fillMaxHeight(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.46f), ColorKt.getMapBase(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy32222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap222222 = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier222222 = ComposedModifierKt.materializeModifier($composer3, m262backgroundbw27NRU$default32222);
            Function0 constructor222222 = ComposeUiNode.INSTANCE.getConstructor();
            int i772222 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            Composer m4364constructorimpl222222 = Updater.m4364constructorimpl($composer3);
            Updater.m4372setimpl(m4364constructorimpl222222, maybeCachedBoxMeasurePolicy32222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl222222, currentCompositionLocalMap222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl222222, Integer.valueOf(hashCode222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl222222, materializeModifier222222, ComposeUiNode.INSTANCE.getSetModifier());
            int i822222 = (i772222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            int i922222 = ((0 >> 6) & 112) | 6;
            BoxScope boxScope22222 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -747452091, "C195@8935L60,178@7847L1214,200@9106L1630:RouteScreen.kt#cef2n4");
            Pair<Double, Double> currentLocation32222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
            if (currentLocation32222 == null) {
            }
            Pair<Double, Double> currentLocation222222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentLocation();
            if (currentLocation222222 == null) {
            }
            float currentBearing22222 = RouteScreen$lambda$0(routeState$delegate2).getCurrentBearing();
            long locationRefreshKey22222 = RouteScreen$lambda$0(routeState$delegate2).getLocationRefreshKey();
            if (dest == null) {
            }
            Iterable<SharedLocation> waypoints22222 = RouteScreen$lambda$0(routeState$delegate2).getWaypoints();
            int i1022222 = 0;
            Collection arrayList52222 = new ArrayList();
            while (r101.hasNext()) {
            }
            ArrayList arrayList222222 = (List) arrayList52222;
            Route route52222 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
            if (route52222 == null) {
            }
            if (geometry == null) {
            }
            List<GeoPoint> list222222 = geometry;
            Route route222222 = RouteScreen$lambda$0(routeState$delegate2).getRoute();
            if (route222222 == null) {
            }
            if (traffic == null) {
            }
            List<TrafficInterval> list322222 = traffic;
            routeAlternatives = RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives();
            list = routeAlternatives;
            if (!(routeAlternatives.size() <= 1)) {
            }
            if (list == null) {
            }
            int selectedRouteIndex32222 = RouteScreen$lambda$0(routeState$delegate2).getSelectedRouteIndex();
            if (RouteScreen$lambda$0(routeState$delegate2).getRoute() == null) {
            }
            Modifier fillMaxSize$default222222 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -162627081, "CC(remember):RouteScreen.kt#9igjgp");
            if ((($dirty3 & 896) ^ 384) <= 256) {
            }
            Object rememberedValue2522222 = $composer3.rememberedValue();
            if (z4) {
            }
            Object obj722222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj822222) {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel.this, (LatLng) obj822222);
                }
            };
            $composer3.updateRememberedValue(obj722222);
            rememberedValue2522222 = obj722222;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            FreeWayfindrMapKt.WayfindrMap(first, second, pair2, arrayList222222, list222222, list322222, list, selectedRouteIndex32222, true, fillMaxSize$default222222, z9, false, currentBearing22222, locationRefreshKey22222, false, true, null, null, null, null, false, (Function1) rememberedValue2522222, $composer3, 905969664, 221184, 0, 2033664);
            Alignment.Vertical centerVertically72222 = Alignment.INSTANCE.getCenterVertically();
            Modifier m817paddingVpY3zN442222 = PaddingKt.m817paddingVpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(boxScope22222.align(Modifier.INSTANCE, Alignment.INSTANCE.getTopStart()), 0.0f, 1, null), Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(androidx.compose.ui.graphics.ColorKt.Color(3641183244L)), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU())}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(14));
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy112222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically72222, $composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode322222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap322222 = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier322222 = ComposedModifierKt.materializeModifier($composer3, m817paddingVpY3zN442222);
            Function0 constructor322222 = ComposeUiNode.INSTANCE.getConstructor();
            int i1122222 = ((((384 << 3) & 112) << 6) & 896) | 6;
            int $dirty422222 = $dirty3;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            Composer m4364constructorimpl322222 = Updater.m4364constructorimpl($composer3);
            Updater.m4372setimpl(m4364constructorimpl322222, rowMeasurePolicy112222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl322222, currentCompositionLocalMap322222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl322222, Integer.valueOf(hashCode322222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl322222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl322222, materializeModifier322222, ComposeUiNode.INSTANCE.getSetModifier());
            int i1222222 = (i1122222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance82222 = RowScopeInstance.INSTANCE;
            int i1322222 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, 349592008, "C210@9542L238,216@9797L29,217@9843L523,229@10474L38,227@10383L339:RouteScreen.kt#cef2n4");
            final ManagedActivityResultLauncher navPermLauncher322222 = navPermLauncher;
            final ManagedActivityResultLauncher qrPicker322222 = qrPicker;
            int $dirty522222 = $dirty422222;
            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(WayfindrIcons.INSTANCE.getChevronLeft(), onBack, BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, (($dirty422222 << 3) & 112) | 3072, 48);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
            WayfindrComponentsKt.WayfindrChip(!RouteScreen$lambda$0(routeState$delegate2).isResolving() ? "Bağlantı çözümleniyor…" : RouteScreen$lambda$0(routeState$delegate2).getFromWayfindr() ? "Wayfindr'dan paylaşıldı" : "Google Haritalar'dan paylaşıldı", !RouteScreen$lambda$0(routeState$delegate2).isResolving() ? ChipTone.Gold : ChipTone.Neutral, false, WayfindrIcons.INSTANCE.getShare(), BackgroundKt.m261backgroundbw27NRU(Modifier.INSTANCE, androidx.compose.ui.graphics.ColorKt.Color(3003977489L), RoundedCornerShapeKt.getCircleShape()), $composer3, 0, 4);
            ImageVector recenter22222 = WayfindrIcons.INSTANCE.getRecenter();
            ComposerKt.sourceInformationMarkerStart($composer3, 1673873829, "CC(remember):RouteScreen.kt#9igjgp");
            if ((($dirty522222 & 896) ^ 384) <= 256) {
            }
            rememberedValue4 = $composer3.rememberedValue();
            if (!z5) {
            }
            Object obj822222 = (KFunction) new RouteScreenKt$RouteScreen$5$1$5$1$1(routeViewModel3);
            $composer3.updateRememberedValue(obj822222);
            rememberedValue4 = obj822222;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter22222, (Function0) ((KFunction) rememberedValue4), BackgroundKt.m261backgroundbw27NRU(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 0.0f, 14, null), androidx.compose.ui.graphics.ColorKt.Color(3003977489L), WayfindrComponentsKt.getIconBtnShape()), Dp.m7908constructorimpl(40), false, null, $composer3, 3072, 48);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
            }
            $composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier m820paddingqDBjuR0$default72222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m817paddingVpY3zN4(ScrollKt.verticalScroll$default(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(ColumnScope.weight$default(columnScope32222, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 1.0f, false, 2, null), RoundedCornerShapeKt.m1126RoundedCornerShapea9UjIt4$default(Dp.m7908constructorimpl(22), Dp.m7908constructorimpl(22), 0.0f, 0.0f, 12, null)), ColorKt.getBg1(), null, 2, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18), Dp.m7908constructorimpl(8)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(20), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode422222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap422222 = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier422222 = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default72222);
            Function0 constructor422222 = ComposeUiNode.INSTANCE.getConstructor();
            int i1422222 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
            }
            Composer m4364constructorimpl422222 = Updater.m4364constructorimpl($composer3);
            Updater.m4372setimpl(m4364constructorimpl422222, columnMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl422222, currentCompositionLocalMap422222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl422222, Integer.valueOf(hashCode422222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl422222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl422222, materializeModifier422222, ComposeUiNode.INSTANCE.getSetModifier());
            int i1522222 = (i1422222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            int i1622222 = ((0 >> 6) & 112) | 6;
            ColumnScope columnScope222222 = ColumnScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, 2008388714, "C259@11606L249,267@11869L30,270@12028L5160,270@11939L5249,368@17856L180,366@17764L487,456@23499L101,458@23614L1201,481@24829L30,538@28492L1019,558@29525L30,561@29674L16,562@29739L16,563@29791L73,564@29877L443,573@30521L251,570@30333L530,579@30876L81,580@30970L1899,611@32883L666,628@33735L352,625@33563L615,646@34667L88,639@34307L755:RouteScreen.kt#cef2n4");
            BoxKt.Box(PaddingKt.m820paddingqDBjuR0$default(columnScope222222.align(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), Dp.m7908constructorimpl(4)), RoundedCornerShapeKt.getCircleShape()), ColorKt.getLine3(), null, 2, null), Alignment.INSTANCE.getCenterHorizontally()), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), $composer3, 0);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer3, 6);
            final MutableState suggestionsSuppressed$delegate522222 = suggestionsSuppressed$delegate;
            final MutableState suggestionsLoading$delegate222222 = suggestionsSuppressed$delegate2;
            final RouteViewModel routeViewModel822222 = routeViewModel3;
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(4), null, ComposableLambdaKt.rememberComposableLambda(820477990, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj102, Object obj112, Object obj122) {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74(Function1.this, routeViewModel822222, searchQuery$delegate, suggestions$delegate, suggestionsSuppressed$delegate522222, suggestionsStatus$delegate, routeState$delegate2, suggestionsLoading$delegate222222, (ColumnScope) obj102, (Composer) obj112, ((Integer) obj122).intValue());
                }
            }, $composer3, 54), $composer3, 24966, 10);
            searchNote = RouteScreen$lambda$0(routeState$delegate2).getSearchNote();
            if (searchNote != null) {
            }
            ImageVector qrScan22222 = WayfindrIcons.INSTANCE.getQrScan();
            BtnVariant btnVariant42222 = BtnVariant.Ghost;
            BtnSize btnSize42222 = BtnSize.Sm;
            Modifier m820paddingqDBjuR0$default222222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(12), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 480601823, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance3 = $composer3.changedInstance(qrPicker322222);
            Object rememberedValue2722222 = $composer3.rememberedValue();
            if (changedInstance3) {
            }
            rememberedValue2722222 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher.this);
                }
            };
            $composer3.updateRememberedValue(rememberedValue2722222);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            WayfindrComponentsKt.WayfindrBtn("QR görselinden rota yükle", (Function0) rememberedValue2722222, m820paddingqDBjuR0$default222222, qrScan22222, btnVariant42222, btnSize42222, false, $composer3, 221574, 64);
            composer = $composer3;
            sharedRouteName = RouteScreen$lambda$0(routeState$delegate2).getSharedRouteName();
            if (sharedRouteName != null) {
            }
            if (RouteScreen$lambda$0(routeState$delegate2).getWaypoints().isEmpty()) {
            }
            composer.endReplaceGroup();
            if ((dest == null ? dest.getLat() : null) != null) {
            }
            composer.startReplaceGroup(2018241319);
            ComposerKt.sourceInformation(composer, "438@22367L1104");
            WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), false, Dp.m7908constructorimpl(16), null, ComposableSingletons$RouteScreenKt.INSTANCE.m8364getLambda$835303176$app(), composer, 24966, 10);
            composer.endReplaceGroup();
            WayfindrComponentsKt.Eyebrow(RouteScreen$lambda$0(routeState$delegate2).getWaypoints().size() <= 1 ? "Son hedef" : "Hedef", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(6), 7, null), composer, 48, 0);
            Alignment.Vertical top222222 = Alignment.INSTANCE.getTop();
            ComposerKt.sourceInformationMarkerStart(composer, 844473419, str5);
            Modifier modifier92222 = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy322222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), top222222, composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode622222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap622222 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier622222 = ComposedModifierKt.materializeModifier(composer, modifier92222);
            Function0 constructor622222 = ComposeUiNode.INSTANCE.getConstructor();
            int i2022222 = ((((384 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
            }
            Composer m4364constructorimpl622222 = Updater.m4364constructorimpl(composer);
            Updater.m4372setimpl(m4364constructorimpl622222, rowMeasurePolicy322222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl622222, currentCompositionLocalMap622222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl622222, Integer.valueOf(hashCode622222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl622222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl622222, materializeModifier622222, ComposeUiNode.INSTANCE.getSetModifier());
            int i2122222 = (i2022222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, str4);
            RowScopeInstance rowScopeInstance322222 = RowScopeInstance.INSTANCE;
            int i2222222 = ((384 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, 1479686218, "C459@23671L405,468@24093L29,469@24139L662:RouteScreen.kt#cef2n4");
            Alignment center22222 = Alignment.INSTANCE.getCenter();
            Modifier m262backgroundbw27NRU$default222222 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(44)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12))), ColorKt.getGoldTint(), null, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
            MeasurePolicy maybeCachedBoxMeasurePolicy222222 = BoxKt.maybeCachedBoxMeasurePolicy(center22222, false);
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode722222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap722222 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier722222 = ComposedModifierKt.materializeModifier(composer, m262backgroundbw27NRU$default222222);
            Function0 constructor722222 = ComposeUiNode.INSTANCE.getConstructor();
            int i2322222 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
            }
            Composer m4364constructorimpl722222 = Updater.m4364constructorimpl(composer);
            Updater.m4372setimpl(m4364constructorimpl722222, maybeCachedBoxMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl722222, currentCompositionLocalMap722222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl722222, Integer.valueOf(hashCode722222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl722222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl722222, materializeModifier722222, ComposeUiNode.INSTANCE.getSetModifier());
            int i2422222 = (i2322222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance22222 = BoxScopeInstance.INSTANCE;
            int i2522222 = ((48 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -1304438330, "C466@23954L104:RouteScreen.kt#cef2n4");
            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(22)), ColorKt.getGold(), composer, 432, 0);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), composer, 6);
            ComposerKt.sourceInformationMarkerStart(composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier222222 = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy322222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode822222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap822222 = composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier822222 = ComposedModifierKt.materializeModifier(composer, modifier222222);
            Function0 constructor822222 = ComposeUiNode.INSTANCE.getConstructor();
            int i2622222 = ((((0 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
            }
            Composer m4364constructorimpl822222 = Updater.m4364constructorimpl(composer);
            Updater.m4372setimpl(m4364constructorimpl822222, columnMeasurePolicy322222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl822222, currentCompositionLocalMap822222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl822222, Integer.valueOf(hashCode822222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl822222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl822222, materializeModifier822222, ComposeUiNode.INSTANCE.getSetModifier());
            int i2722222 = (i2622222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance62222 = ColumnScopeInstance.INSTANCE;
            int i2822222 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, -1541055177, "C:RouteScreen.kt#cef2n4");
            if (RouteScreen$lambda$0(routeState$delegate2).isResolving()) {
            }
            if (StringsKt.isBlank(destSub2)) {
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
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), composer, 6);
            if (RouteScreen$lambda$0(routeState$delegate2).getRouteAlternatives().size() <= 1) {
            }
            composer.endReplaceGroup();
            routing = RouteScreen$lambda$0(routeState$delegate2).getRouting();
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
            Iterable listOf32222 = CollectionsKt.listOf((Object[]) tripleArr22222);
            Arrangement.Horizontal m686spacedBy0680j_4222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
            composer3 = composer;
            int i4222222 = 0;
            ComposerKt.sourceInformationMarkerStart(composer3, 844473419, str5);
            Modifier modifier422222 = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy522222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4222222, Alignment.INSTANCE.getTop(), composer3, ((48 >> 3) & 14) | ((48 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode1222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
            CompositionLocalMap currentCompositionLocalMap1222222 = composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier1222222 = ComposedModifierKt.materializeModifier(composer3, modifier422222);
            Function0 constructor1222222 = ComposeUiNode.INSTANCE.getConstructor();
            int i4322222 = ((((48 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer3.getApplier() instanceof Applier)) {
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
            }
            Composer m4364constructorimpl1222222 = Updater.m4364constructorimpl(composer3);
            MeasurePolicy measurePolicy72222 = rowMeasurePolicy522222;
            Updater.m4372setimpl(m4364constructorimpl1222222, measurePolicy72222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl1222222, currentCompositionLocalMap1222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl1222222, Integer.valueOf(hashCode1222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl1222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl1222222, materializeModifier1222222, ComposeUiNode.INSTANCE.getSetModifier());
            int i4422222 = (i4322222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, str4);
            int i4522222 = ((48 >> 6) & 112) | 6;
            RowScope rowScope222222 = RowScopeInstance.INSTANCE;
            Composer composer1422222 = composer3;
            ComposerKt.sourceInformationMarkerStart(composer1422222, 213090107, "C:RouteScreen.kt#cef2n4");
            composer1422222.startReplaceGroup(1807989502);
            ComposerKt.sourceInformation(composer1422222, "*540@28620L859");
            Iterable<Triple> iterable222222 = listOf32222;
            int i4622222 = 0;
            while (r39.hasNext()) {
            }
            Composer composer1722222 = composer1422222;
            composer1722222.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer1722222);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd(composer3);
            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), composer, 6);
            collectAsState = SnapshotStateKt.collectAsState(dashConfig.getRoutingSource(), null, composer, 0, 1);
            State collectAsState222222 = SnapshotStateKt.collectAsState(dashConfig.getMapTheme(), null, composer, 0, 1);
            ComposerKt.sourceInformationMarkerStart(composer, 480983636, "CC(remember):RouteScreen.kt#9igjgp");
            Composer composer1822222 = composer;
            rememberedValue5 = composer1822222.rememberedValue();
            if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
            }
            boolean booleanValue22222 = ((Boolean) rememberedValue5).booleanValue();
            ComposerKt.sourceInformationMarkerEnd(composer);
            switch (WhenMappings.$EnumSwitchMapping$1[RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).ordinal()]) {
            }
            ChoiceHeader("Rota sağlayıcısı", str6, composer, 6);
            Iterable available22222 = RoutingSource.INSTANCE.getAvailable();
            Collection arrayList322222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(available22222, 10));
            it = available22222.iterator();
            while (it.hasNext()) {
            }
            ArrayList arrayList422222 = (List) arrayList322222;
            String label22222 = RouteScreen$lambda$144$lambda$143$lambda$108(collectAsState).getLabel();
            ComposerKt.sourceInformationMarkerStart(composer, 481007174, "CC(remember):RouteScreen.kt#9igjgp");
            changed2 = $composer3.changed(collectAsState) | $composer3.changedInstance(dashConfig) | (((($dirty522222 & 896) ^ 384) <= 256 && $composer3.changedInstance(routeViewModel822222)) || ($dirty522222 & 384) == 256);
            Composer composer1922222 = composer;
            composer4 = composer;
            Object rememberedValue3022222 = composer1922222.rememberedValue();
            if (changed2) {
            }
            rememberedValue3022222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj122) {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig.this, routeViewModel822222, collectAsState, (String) obj122);
                }
            };
            composer1922222.updateRememberedValue(rememberedValue3022222);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            WayfindrComponentsKt.WayfindrSegmented(arrayList422222, label22222, (Function1) rememberedValue3022222, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(16), 7, null), composer4, 3072, 0);
            ChoiceHeader("OpenStreetMap harita temaları", "10 stil · çevrimiçi OSM haritası", composer4, 54);
            Modifier m820paddingqDBjuR0$default422222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null);
            Arrangement.Vertical m686spacedBy0680j_4322222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(8));
            int i5522222 = 0;
            ComposerKt.sourceInformationMarkerStart(composer4, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy722222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4322222, Alignment.INSTANCE.getStart(), composer4, ((54 >> 3) & 14) | ((54 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode1522222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
            CompositionLocalMap currentCompositionLocalMap1522222 = composer4.getCurrentCompositionLocalMap();
            Modifier materializeModifier1522222 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default422222);
            Function0 constructor1522222 = ComposeUiNode.INSTANCE.getConstructor();
            int i5622222 = ((((54 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer4.getApplier() instanceof Applier)) {
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
            }
            Composer m4364constructorimpl1522222 = Updater.m4364constructorimpl(composer4);
            MeasurePolicy measurePolicy322222 = columnMeasurePolicy722222;
            Updater.m4372setimpl(m4364constructorimpl1522222, measurePolicy322222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl1522222, currentCompositionLocalMap1522222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl1522222, Integer.valueOf(hashCode1522222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl1522222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl1522222, materializeModifier1522222, ComposeUiNode.INSTANCE.getSetModifier());
            int i5722222 = (i5622222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer4, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance522222 = ColumnScopeInstance.INSTANCE;
            int i5822222 = ((54 >> 6) & 112) | 6;
            Composer composer2022222 = composer4;
            ComposerKt.sourceInformationMarkerStart(composer2022222, -1001505061, "C:RouteScreen.kt#cef2n4");
            composer2022222.startReplaceGroup(-1140683288);
            ComposerKt.sourceInformation(composer2022222, "*585@31259L1578");
            Iterable<List> chunked22222 = CollectionsKt.chunked(ArraysKt.toList(MapTheme.values()), 2);
            int i5922222 = 0;
            while (r41.hasNext()) {
            }
            Composer composer2222222 = composer2022222;
            int $dirty822222 = $dirty522222;
            final Context ctx822222 = ctx3;
            composer2222222.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2222222);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            Arrangement.Horizontal spaceBetween222222 = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically522222 = Alignment.INSTANCE.getCenterVertically();
            Modifier m820paddingqDBjuR0$default522222 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(9), 7, null);
            ComposerKt.sourceInformationMarkerStart(composer4, 844473419, str5);
            MeasurePolicy rowMeasurePolicy922222 = RowKt.rowMeasurePolicy(spaceBetween222222, centerVertically522222, composer4, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart(composer4, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode1822222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer4, 0));
            CompositionLocalMap currentCompositionLocalMap1822222 = composer4.getCurrentCompositionLocalMap();
            Modifier materializeModifier1822222 = ComposedModifierKt.materializeModifier(composer4, m820paddingqDBjuR0$default522222);
            Function0 constructor1822222 = ComposeUiNode.INSTANCE.getConstructor();
            int i7122222 = ((((438 << 3) & 112) << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart(composer4, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
            if (!(composer4.getApplier() instanceof Applier)) {
            }
            composer4.startReusableNode();
            if (composer4.getInserting()) {
            }
            Composer m4364constructorimpl1822222 = Updater.m4364constructorimpl(composer4);
            Updater.m4372setimpl(m4364constructorimpl1822222, rowMeasurePolicy922222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4372setimpl(m4364constructorimpl1822222, currentCompositionLocalMap1822222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Updater.m4368initimpl(m4364constructorimpl1822222, Integer.valueOf(hashCode1822222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
            Updater.m4370reconcileimpl(m4364constructorimpl1822222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
            Updater.m4372setimpl(m4364constructorimpl1822222, materializeModifier1822222, ComposeUiNode.INSTANCE.getSetModifier());
            int i7222222 = (i7122222 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart(composer4, 1456264949, str4);
            RowScopeInstance rowScopeInstance622222 = RowScopeInstance.INSTANCE;
            int i7322222 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer4, -674963550, "C616@33125L28,617@33170L365:RouteScreen.kt#cef2n4");
            WayfindrComponentsKt.Eyebrow("Sesli yönlendirme", null, composer4, 6, 2);
            IconKt.m2517Iconww6aTOc(RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? WayfindrIcons.INSTANCE.getSpeakerOff() : WayfindrIcons.INSTANCE.getSpeaker(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18)), RouteScreen$lambda$5(voiceMode$delegate) != VoiceMode.OFF ? ColorKt.getTextLo() : ColorKt.getGold(), composer4, 432, 0);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            composer4.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            List listOf222222 = CollectionsKt.listOf((Object[]) new String[]{"Kapalı", "Yalnızca uyarı sesi", "Tam sesli yönlendirme"});
            ComposerKt.sourceInformationMarkerStart(composer4, 481110123, "CC(remember):RouteScreen.kt#9igjgp");
            final VoiceManager voiceManager322222 = voiceManager;
            changedInstance4 = composer4.changedInstance(voiceManager322222);
            rememberedValue6 = composer4.rememberedValue();
            if (!changedInstance4) {
            }
            Object obj1322222 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj142) {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager.this, (String) obj142);
                }
            };
            composer4.updateRememberedValue(obj1322222);
            rememberedValue6 = obj1322222;
            ComposerKt.sourceInformationMarkerEnd(composer4);
            WayfindrComponentsKt.WayfindrSegmented(listOf222222, voice, (Function1) rememberedValue6, PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(22), 7, null), composer4, 3078, 0);
            if ((dest == null ? dest.getLat() : null) != null) {
            }
            if (!RouteScreen$lambda$7(sent$delegate3)) {
            }
            ImageVector check22222 = !RouteScreen$lambda$7(sent$delegate3) ? WayfindrIcons.INSTANCE.getCheck() : WayfindrIcons.INSTANCE.getNavi();
            BtnVariant btnVariant222222 = !RouteScreen$lambda$7(sent$delegate3) ? BtnVariant.Secondary : BtnVariant.Primary;
            BtnSize btnSize222222 = BtnSize.Lg;
            if (RouteScreen$lambda$7(sent$delegate3)) {
            }
            Modifier fillMaxWidth$default222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composer4, 481139683, "CC(remember):RouteScreen.kt#9igjgp");
            changedInstance5 = composer4.changedInstance(ctx822222) | composer4.changedInstance(navPermLauncher322222) | composer4.changedInstance(navPermissions22);
            Object rememberedValue3222222 = composer4.rememberedValue();
            if (!changedInstance5) {
            }
            z7 = z6;
            sent$delegate = sent$delegate3;
            Object obj2022222 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher.this, navPermissions22, ctx822222, sent$delegate);
                }
            };
            composer4.updateRememberedValue(obj2022222);
            rememberedValue3222222 = obj2022222;
            ComposerKt.sourceInformationMarkerEnd(composer4);
            WayfindrComponentsKt.WayfindrBtn(str11, (Function0) rememberedValue3222222, fillMaxWidth$default222222, check22222, btnVariant222222, btnSize222222, z16, composer4, 196992, 0);
            if (z7) {
            }
            composer4.endReplaceGroup();
            if (RouteScreen$lambda$2(savedList$delegate).isEmpty()) {
            }
            composer4.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer4);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            if (RouteScreen$lambda$10(sent$delegate2)) {
            }
            $composer3.endReplaceGroup();
            RouteScreen$lambda$16 = RouteScreen$lambda$16(sharing$delegate);
            if (RouteScreen$lambda$16 != null) {
            }
            RouteScreen$lambda$13 = RouteScreen$lambda$13(editing$delegate);
            if (RouteScreen$lambda$13 != null) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
            routeViewModel2 = routeViewModel5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            routeViewModel2 = routeViewModel6;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj25, Object obj26) {
                    return RouteScreenKt.RouteScreen$lambda$159(Function0.this, onSentToDash, routeViewModel2, $changed, i, (Composer) obj25, ((Integer) obj26).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final RouteState RouteScreen$lambda$0(State<RouteState> state) {
        return (RouteState) state.getValue();
    }

    private static final List<SavedLocation> RouteScreen$lambda$2(State<? extends List<SavedLocation>> state) {
        return (List) state.getValue();
    }

    private static final VoiceMode RouteScreen$lambda$5(State<? extends VoiceMode> state) {
        return (VoiceMode) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean RouteScreen$lambda$7(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void RouteScreen$lambda$8(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean RouteScreen$lambda$10(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void RouteScreen$lambda$11(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final SavedLocation RouteScreen$lambda$13(MutableState<SavedLocation> mutableState) {
        return mutableState.getValue();
    }

    private static final SavedLocation RouteScreen$lambda$16(MutableState<SavedLocation> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String RouteScreen$lambda$19(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean RouteScreen$lambda$22(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void RouteScreen$lambda$23(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final List<PlaceSearch.Hit> RouteScreen$lambda$25(MutableState<List<PlaceSearch.Hit>> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean RouteScreen$lambda$28(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RouteScreen$lambda$29(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final String RouteScreen$lambda$31(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean RouteScreen$lambda$34(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void RouteScreen$lambda$35(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit RouteScreen$lambda$39$lambda$38(RouteViewModel $routeViewModel, MutableState $suggestionsSuppressed$delegate, MutableState $suggestions$delegate, MutableState $suggestionsLoading$delegate, MutableState $suggestionsStatus$delegate, MutableState $searchQuery$delegate, MutableState $showSavedDestinations$delegate, PlaceSearch.Hit hit) {
        Intrinsics.checkNotNullParameter(hit, "hit");
        RouteScreen$lambda$35($suggestionsSuppressed$delegate, true);
        $suggestions$delegate.setValue(CollectionsKt.emptyList());
        RouteScreen$lambda$29($suggestionsLoading$delegate, false);
        $suggestionsStatus$delegate.setValue(null);
        $searchQuery$delegate.setValue(hit.getName());
        RouteScreen$lambda$23($showSavedDestinations$delegate, false);
        $routeViewModel.selectPlaceSuggestion(hit);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean RouteScreen$hasLocationPermission(Context ctx) {
        return ContextCompat.checkSelfPermission(ctx, "android.permission.ACCESS_FINE_LOCATION") == 0;
    }

    static final Unit RouteScreen$lambda$43$lambda$42(MutableState $sent$delegate, Map it) {
        Intrinsics.checkNotNullParameter(it, "it");
        RouteScreen$lambda$8($sent$delegate, true);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$45$lambda$44(RouteViewModel $routeViewModel, Uri uri) {
        if (uri != null) {
            $routeViewModel.importQrImage(uri);
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$47$lambda$46(RouteViewModel $routeViewModel, boolean it) {
        $routeViewModel.startLocationUpdates();
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$58$lambda$54$lambda$53(RouteViewModel $routeViewModel, LatLng it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $routeViewModel.selectLocation(it.latitude, it.longitude);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:49:0x0497, code lost:
    
        if (r4 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L58;
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
    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74(Function1 $selectSuggestion, final RouteViewModel $routeViewModel, final MutableState $searchQuery$delegate, final MutableState $suggestions$delegate, final MutableState $suggestionsSuppressed$delegate, final MutableState $suggestionsStatus$delegate, final State $routeState$delegate, MutableState $suggestionsLoading$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Modifier modifier;
        Object rememberedValue;
        int i;
        String RouteScreen$lambda$31;
        Function0 function02;
        Function0 function03;
        final Function1 function1 = $selectSuggestion;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C271@12046L5128:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(820477990, $changed, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:271)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier2 = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier2);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i2 = ((((0 << 3) & 112) << 6) & 896) | 6;
            int i3 = 6;
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
            int i4 = (i2 >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            int i5 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1309771922, "C305@14026L186,310@14318L320,274@12198L183,279@12421L307,286@12951L692,272@12102L2558:RouteScreen.kt#cef2n4");
            String RouteScreen$lambda$19 = RouteScreen$lambda$19($searchQuery$delegate);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, 0, ImeAction.INSTANCE.m7518getSearcheUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 119, (DefaultConstructorMarker) null);
            ComposerKt.sourceInformationMarkerStart($composer, 234901494, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed = $composer.changed(function1) | $composer.changedInstance($routeViewModel);
            Object rememberedValue2 = $composer.rememberedValue();
            if (changed) {
                modifier = fillMaxWidth$default;
            } else {
                modifier = fillMaxWidth$default;
                if (rememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    KeyboardActions keyboardActions = new KeyboardActions(null, null, null, null, (Function1) rememberedValue2, null, 47, null);
                    TextFieldColors m3049colors0hiis_0 = TextFieldDefaults.INSTANCE.m3049colors0hiis_0(0L, 0L, 0L, 0L, Color.INSTANCE.m5129getTransparent0d7_KjU(), Color.INSTANCE.m5129getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, null, Color.INSTANCE.m5129getTransparent0d7_KjU(), Color.INSTANCE.m5129getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
                    ComposerKt.sourceInformationMarkerStart($composer, 234842995, "CC(remember):RouteScreen.kt#9igjgp");
                    rememberedValue = $composer.rememberedValue();
                    if (rememberedValue != Composer.INSTANCE.getEmpty()) {
                        rememberedValue = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda40
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$62$lambda$61(MutableState.this, $suggestionsSuppressed$delegate, $suggestionsStatus$delegate, (String) obj);
                            }
                        };
                        $composer.updateRememberedValue(rememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    TextFieldKt.TextField(RouteScreen$lambda$19, (Function1<? super String, Unit>) rememberedValue, modifier, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(607470615, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda41
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$63(State.this, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RouteScreenKt.INSTANCE.getLambda$600090294$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(592709973, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda42
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$66(MutableState.this, $suggestionsSuppressed$delegate, $suggestions$delegate, $suggestionsStatus$delegate, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, keyboardOptions, keyboardActions, true, 0, 0, (MutableInteractionSource) null, (Shape) null, m3049colors0hiis_0, $composer, 918553008, 12779520, 0, 3964024);
                    if (RouteScreen$lambda$28($suggestionsLoading$delegate) && RouteScreen$lambda$25($suggestions$delegate).isEmpty() && RouteScreen$lambda$31($suggestionsStatus$delegate) == null) {
                        $composer.startReplaceGroup(-1321908826);
                    } else {
                        $composer.startReplaceGroup(-1307189716);
                        ComposerKt.sourceInformation($composer, "318@14788L58,*337@15888L25,334@15701L1407");
                        BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(SizeKt.m848height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7908constructorimpl(1)), ColorKt.getLine(), null, 2, null), $composer, 0);
                        if (RouteScreen$lambda$28($suggestionsLoading$delegate)) {
                            i = 12;
                            $composer.startReplaceGroup(-1321908826);
                        } else {
                            $composer.startReplaceGroup(-1307121330);
                            ComposerKt.sourceInformation($composer, "320@14925L288");
                            i = 12;
                            TextKt.m3069TextNvy7gAk("OpenStreetMap'te aranıyor…", PaddingKt.m817paddingVpY3zN4(Modifier.INSTANCE, Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(10)), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24630, 0, 261992);
                        }
                        $composer.endReplaceGroup();
                        RouteScreen$lambda$31 = RouteScreen$lambda$31($suggestionsStatus$delegate);
                        if (RouteScreen$lambda$31 != null) {
                            $composer.startReplaceGroup(-1306736683);
                            $composer.endReplaceGroup();
                        } else {
                            $composer.startReplaceGroup(-1306736682);
                            ComposerKt.sourceInformation($composer, "*327@15327L266");
                            TextKt.m3069TextNvy7gAk(RouteScreen$lambda$31, PaddingKt.m817paddingVpY3zN4(Modifier.INSTANCE, Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(10)), ColorKt.getTextLo(), null, TextUnitKt.getSp(i), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24624, 0, 261992);
                            Unit unit = Unit.INSTANCE;
                            $composer.endReplaceGroup();
                            Unit unit2 = Unit.INSTANCE;
                        }
                        Iterable<PlaceSearch.Hit> RouteScreen$lambda$25 = RouteScreen$lambda$25($suggestions$delegate);
                        int i6 = 0;
                        for (final PlaceSearch.Hit hit : RouteScreen$lambda$25) {
                            Iterable iterable = RouteScreen$lambda$25;
                            int i7 = i6;
                            Modifier clip = ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(8)));
                            ComposerKt.sourceInformationMarkerStart($composer, 1657944588, "CC(remember):RouteScreen.kt#9igjgp");
                            boolean changed2 = $composer.changed(function1) | $composer.changed(hit);
                            Object rememberedValue3 = $composer.rememberedValue();
                            if (changed2) {
                            }
                            Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda43
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$72$lambda$69$lambda$68(Function1.this, hit);
                                }
                            };
                            $composer.updateRememberedValue(obj);
                            rememberedValue3 = obj;
                            ComposerKt.sourceInformationMarkerEnd($composer);
                            Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(ClickableKt.m297clickableoSLSa3U$default(clip, false, null, null, null, (Function0) rememberedValue3, 15, null), Dp.m7908constructorimpl(14), Dp.m7908constructorimpl(11));
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, m817paddingVpY3zN4);
                            Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                            int i8 = ((((384 << 3) & 112) << 6) & 896) | 6;
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
                            int i9 = (i8 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            int i10 = ((384 >> 6) & 112) | 6;
                            RowScope rowScope = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer, -1635304540, "C341@16142L83,342@16258L29,343@16320L758:RouteScreen.kt#cef2n4");
                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(19)), ColorKt.getGold(), $composer, 432, 0);
                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer, i3);
                            Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                            CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                            Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, weight$default);
                            Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                            int i11 = ((((0 << 3) & 112) << 6) & 896) | 6;
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
                            Updater.m4372setimpl(m4364constructorimpl3, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                            int i12 = (i11 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                            i3 = 6;
                            int i13 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer, 1816346692, "C344@16386L265:RouteScreen.kt#cef2n4");
                            TextKt.m3069TextNvy7gAk(hit.getName(), null, ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getMedium(), TypeKt.getGeistFamily(), 0L, null, null, 0L, TextOverflow.INSTANCE.m7819getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 1597440, 24960, 241450);
                            if (StringsKt.isBlank(hit.getSubtitle())) {
                                $composer.startReplaceGroup(1800070389);
                            } else {
                                $composer.startReplaceGroup(1816666704);
                                ComposerKt.sourceInformation($composer, "348@16761L245");
                                TextKt.m3069TextNvy7gAk(hit.getSubtitle(), null, ColorKt.getTextLo(), null, TextUnitKt.getSp(i), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, TextOverflow.INSTANCE.m7819getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 24576, 24960, 241514);
                            }
                            $composer.endReplaceGroup();
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
                            function1 = $selectSuggestion;
                            i6 = i7;
                            RouteScreen$lambda$25 = iterable;
                        }
                    }
                    $composer.endReplaceGroup();
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
            }
            rememberedValue2 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda39
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$60$lambda$59(Function1.this, $routeViewModel, $suggestions$delegate, $searchQuery$delegate, (KeyboardActionScope) obj2);
                }
            };
            $composer.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            KeyboardActions keyboardActions2 = new KeyboardActions(null, null, null, null, (Function1) rememberedValue2, null, 47, null);
            TextFieldColors m3049colors0hiis_02 = TextFieldDefaults.INSTANCE.m3049colors0hiis_0(0L, 0L, 0L, 0L, Color.INSTANCE.m5129getTransparent0d7_KjU(), Color.INSTANCE.m5129getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, null, Color.INSTANCE.m5129getTransparent0d7_KjU(), Color.INSTANCE.m5129getTransparent0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer, 221184, 432, 0, 0, 3072, 2147477455, 4095);
            ComposerKt.sourceInformationMarkerStart($composer, 234842995, "CC(remember):RouteScreen.kt#9igjgp");
            rememberedValue = $composer.rememberedValue();
            if (rememberedValue != Composer.INSTANCE.getEmpty()) {
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            TextFieldKt.TextField(RouteScreen$lambda$19, (Function1<? super String, Unit>) rememberedValue, modifier, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(607470615, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda41
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj22) {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$63(State.this, (Composer) obj2, ((Integer) obj22).intValue());
                }
            }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RouteScreenKt.INSTANCE.getLambda$600090294$app(), (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(592709973, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda42
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj22) {
                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$66(MutableState.this, $suggestionsSuppressed$delegate, $suggestions$delegate, $suggestionsStatus$delegate, (Composer) obj2, ((Integer) obj22).intValue());
                }
            }, $composer, 54), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, keyboardOptions, keyboardActions2, true, 0, 0, (MutableInteractionSource) null, (Shape) null, m3049colors0hiis_02, $composer, 918553008, 12779520, 0, 3964024);
            if (RouteScreen$lambda$28($suggestionsLoading$delegate)) {
            }
            $composer.startReplaceGroup(-1307189716);
            ComposerKt.sourceInformation($composer, "318@14788L58,*337@15888L25,334@15701L1407");
            BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(SizeKt.m848height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7908constructorimpl(1)), ColorKt.getLine(), null, 2, null), $composer, 0);
            if (RouteScreen$lambda$28($suggestionsLoading$delegate)) {
            }
            $composer.endReplaceGroup();
            RouteScreen$lambda$31 = RouteScreen$lambda$31($suggestionsStatus$delegate);
            if (RouteScreen$lambda$31 != null) {
            }
            Iterable<PlaceSearch.Hit> RouteScreen$lambda$252 = RouteScreen$lambda$25($suggestions$delegate);
            int i62 = 0;
            while (r6.hasNext()) {
            }
            $composer.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$62$lambda$61(MutableState $searchQuery$delegate, MutableState $suggestionsSuppressed$delegate, MutableState $suggestionsStatus$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $searchQuery$delegate.setValue(it);
        RouteScreen$lambda$35($suggestionsSuppressed$delegate, false);
        $suggestionsStatus$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$63(State $routeState$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C280@12451L251:RouteScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(607470615, $changed, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:280)");
            }
            TextKt.m3069TextNvy7gAk((RouteScreen$lambda$0($routeState$delegate).getWaypoints().isEmpty() ? "Yer ara" : "Durak ekle") + " · OpenStreetMap", null, ColorKt.getTextLo(), null, 0L, null, null, null, 0L, null, null, 0L, TextOverflow.INSTANCE.m7819getEllipsisgIe3tQ8(), false, 1, 0, null, null, $composer, 0, 24960, 241658);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$66(final MutableState $searchQuery$delegate, final MutableState $suggestionsSuppressed$delegate, final MutableState $suggestions$delegate, final MutableState $suggestionsStatus$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(592709973, $changed, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:287)");
            }
            if (RouteScreen$lambda$19($searchQuery$delegate).length() > 0) {
                $composer.startReplaceGroup(812330095);
                ComposerKt.sourceInformation($composer, "291@13256L297,288@13045L542");
                ImageVector cross = WayfindrIcons.INSTANCE.getCross();
                long textLo = ColorKt.getTextLo();
                Modifier m862size3ABfNKs = SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18));
                ComposerKt.sourceInformationMarkerStart($composer, -1636356258, "CC(remember):RouteScreen.kt#9igjgp");
                Object rememberedValue = $composer.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda54
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$66$lambda$65$lambda$64(MutableState.this, $suggestionsSuppressed$delegate, $suggestions$delegate, $suggestionsStatus$delegate);
                        }
                    };
                    $composer.updateRememberedValue(obj);
                    rememberedValue = obj;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconKt.m2517Iconww6aTOc(cross, "Aramayı temizle", ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs, false, null, null, null, (Function0) rememberedValue, 15, null), textLo, $composer, 48, 0);
            } else {
                $composer.startReplaceGroup(799403405);
            }
            $composer.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$66$lambda$65$lambda$64(MutableState $searchQuery$delegate, MutableState $suggestionsSuppressed$delegate, MutableState $suggestions$delegate, MutableState $suggestionsStatus$delegate) {
        $searchQuery$delegate.setValue("");
        RouteScreen$lambda$35($suggestionsSuppressed$delegate, false);
        $suggestions$delegate.setValue(CollectionsKt.emptyList());
        $suggestionsStatus$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$60$lambda$59(Function1 $selectSuggestion, RouteViewModel $routeViewModel, MutableState $suggestions$delegate, MutableState $searchQuery$delegate, KeyboardActionScope KeyboardActions) {
        Intrinsics.checkNotNullParameter(KeyboardActions, "$this$KeyboardActions");
        PlaceSearch.Hit hit = (PlaceSearch.Hit) CollectionsKt.firstOrNull((List) RouteScreen$lambda$25($suggestions$delegate));
        if (hit != null) {
            $selectSuggestion.invoke(hit);
        } else {
            $routeViewModel.search(RouteScreen$lambda$19($searchQuery$delegate));
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$74$lambda$73$lambda$72$lambda$69$lambda$68(Function1 $selectSuggestion, PlaceSearch.Hit $hit) {
        $selectSuggestion.invoke($hit);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$77$lambda$76(ManagedActivityResultLauncher $qrPicker) {
        $qrPicker.launch(PickVisualMediaRequestKt.PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE));
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$83$lambda$82(String $sharedName, State $routeState$delegate, final MutableState $showSave$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Function0 function02;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C381@18534L1114:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2113600503, $changed, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:381)");
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
            ComposerKt.sourceInformationMarkerStart($composer, -888554936, "C382@18612L98,383@18735L29,384@18789L508,393@19419L19,391@19322L304:RouteScreen.kt#cef2n4");
            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getShare(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), ColorKt.getGold(), $composer, 432, 0);
            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(12)), $composer, 6);
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
            ComposerKt.sourceInformationMarkerStart($composer, -1796672564, "C385@18847L156,387@19032L239:RouteScreen.kt#cef2n4");
            TextKt.m3069TextNvy7gAk($sharedName, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(15), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer, 1597440, 24576, 245546);
            int size = RouteScreen$lambda$0($routeState$delegate).getWaypoints().size();
            RouteScreen$lambda$0($routeState$delegate).getWaypoints().size();
            TextKt.m3069TextNvy7gAk("Paylaşılan rota · " + size + " durak", null, ColorKt.getTextMid(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 261994);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 1633929752, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79(MutableState.this);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrBtn("Kaydet", (Function0) rememberedValue, null, WayfindrIcons.INSTANCE.getPin(), BtnVariant.Secondary, BtnSize.Sm, false, $composer, 221238, 68);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$83$lambda$82$lambda$81$lambda$80$lambda$79(MutableState $showSave$delegate) {
        RouteScreen$lambda$11($showSave$delegate, true);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x02c0, code lost:
    
        if (r1 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0360, code lost:
    
        if (r1 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x03ec, code lost:
    
        if (r5 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L74;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94(State $routeState$delegate, final RouteViewModel $routeViewModel, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Composer composer;
        Composer composer2;
        Composer composer3 = $composer;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer3, "C*421@20771L1438:RouteScreen.kt#cef2n4");
        if (composer3.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1714951167, $changed, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:419)");
            }
            Iterable waypoints = RouteScreen$lambda$0($routeState$delegate).getWaypoints();
            int i = 0;
            final int i2 = 0;
            for (Object obj : waypoints) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                SharedLocation sharedLocation = (SharedLocation) obj;
                if (i2 > 0) {
                    composer3.startReplaceGroup(1413343954);
                    ComposerKt.sourceInformation(composer3, "420@20694L52");
                    WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(4), 0.0f, 2, null), composer3, 6, 0);
                } else {
                    composer3.startReplaceGroup(843458564);
                }
                composer3.endReplaceGroup();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m7908constructorimpl(8), Dp.m7908constructorimpl(9));
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, $composer, ((390 >> 3) & 14) | ((390 >> 3) & 112));
                Iterable iterable = waypoints;
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, m817paddingVpY3zN4);
                Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i4 = ((((390 << 3) & 112) << 6) & 896) | 6;
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
                int i7 = ((390 >> 6) & 112) | 6;
                RowScope rowScope = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -668558625, "C422@20933L172,423@21134L121:RouteScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk(i2 == CollectionsKt.getLastIndex(RouteScreen$lambda$0($routeState$delegate).getWaypoints()) ? "Son" : String.valueOf(i2 + 1), SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(38)), ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597488, 0, 262056);
                TextKt.m3069TextNvy7gAk(sharedLocation.getName(), RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer, 24576, 24576, 245608);
                Composer composer4 = $composer;
                if (RouteScreen$lambda$0($routeState$delegate).getWaypoints().size() > 1) {
                    composer4.startReplaceGroup(-668187680);
                    ComposerKt.sourceInformation(composer4, "426@21539L49,425@21353L236,428@21871L49,427@21622L299,430@22112L40,429@21954L199");
                    ImageVector moveUp = WayfindrIcons.INSTANCE.getMoveUp();
                    long textMid = i2 > 0 ? ColorKt.getTextMid() : ColorKt.getTextLo();
                    Modifier m862size3ABfNKs = SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(19));
                    boolean z = i2 > 0;
                    ComposerKt.sourceInformationMarkerStart(composer4, -714284877, "CC(remember):RouteScreen.kt#9igjgp");
                    boolean changedInstance = composer4.changedInstance($routeViewModel) | composer4.changed(i2);
                    Object rememberedValue = composer4.rememberedValue();
                    if (changedInstance) {
                        composer = composer4;
                    } else {
                        composer = composer4;
                    }
                    rememberedValue = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$87$lambda$86(RouteViewModel.this, i2);
                        }
                    };
                    composer4.updateRememberedValue(rememberedValue);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    Composer composer5 = composer;
                    IconKt.m2517Iconww6aTOc(moveUp, "Durağı öne al", ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs, z, null, null, null, (Function0) rememberedValue, 14, null), textMid, composer5, 48, 0);
                    ImageVector moveDown = WayfindrIcons.INSTANCE.getMoveDown();
                    long textMid2 = i2 < CollectionsKt.getLastIndex(RouteScreen$lambda$0($routeState$delegate).getWaypoints()) ? ColorKt.getTextMid() : ColorKt.getTextLo();
                    Modifier m862size3ABfNKs2 = SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(19));
                    boolean z2 = i2 < CollectionsKt.getLastIndex(RouteScreen$lambda$0($routeState$delegate).getWaypoints());
                    ComposerKt.sourceInformationMarkerStart(composer5, -714274253, "CC(remember):RouteScreen.kt#9igjgp");
                    boolean changedInstance2 = composer5.changedInstance($routeViewModel) | composer5.changed(i2);
                    Object rememberedValue2 = composer5.rememberedValue();
                    if (changedInstance2) {
                        composer2 = composer5;
                    } else {
                        composer2 = composer5;
                    }
                    rememberedValue2 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$89$lambda$88(RouteViewModel.this, i2);
                        }
                    };
                    composer5.updateRememberedValue(rememberedValue2);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    Composer composer6 = composer2;
                    IconKt.m2517Iconww6aTOc(moveDown, "Durağı geriye al", ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs2, z2, null, null, null, (Function0) rememberedValue2, 14, null), textMid2, composer6, 48, 0);
                    composer4 = composer6;
                    ImageVector cross = WayfindrIcons.INSTANCE.getCross();
                    long textLo = ColorKt.getTextLo();
                    Modifier m862size3ABfNKs3 = SizeKt.m862size3ABfNKs(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(4), 0.0f, 0.0f, 0.0f, 14, null), Dp.m7908constructorimpl(18));
                    ComposerKt.sourceInformationMarkerStart(composer4, -714266550, "CC(remember):RouteScreen.kt#9igjgp");
                    boolean changedInstance3 = composer4.changedInstance($routeViewModel) | composer4.changed(i2);
                    Object rememberedValue3 = composer4.rememberedValue();
                    if (changedInstance3) {
                    }
                    Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$91$lambda$90(RouteViewModel.this, i2);
                        }
                    };
                    composer4.updateRememberedValue(obj2);
                    rememberedValue3 = obj2;
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    IconKt.m2517Iconww6aTOc(cross, "Durağı kaldır", ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs3, false, null, null, null, (Function0) rememberedValue3, 15, null), textLo, composer4, 48, 0);
                } else {
                    composer4.startReplaceGroup(-689363904);
                }
                composer4.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer4);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                composer3 = $composer;
                i2 = i3;
                waypoints = iterable;
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

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$87$lambda$86(RouteViewModel $routeViewModel, int $index) {
        $routeViewModel.moveWaypoint($index, $index - 1);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$89$lambda$88(RouteViewModel $routeViewModel, int $index) {
        $routeViewModel.moveWaypoint($index, $index + 1);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$94$lambda$93$lambda$92$lambda$91$lambda$90(RouteViewModel $routeViewModel, int $index) {
        $routeViewModel.removeWaypoint($index);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$103$lambda$102$lambda$99$lambda$98(RouteViewModel $routeViewModel, int $index) {
        $routeViewModel.selectRoute($index);
        return Unit.INSTANCE;
    }

    private static final RoutingSource RouteScreen$lambda$144$lambda$143$lambda$108(State<? extends RoutingSource> state) {
        return (RoutingSource) state.getValue();
    }

    private static final MapTheme RouteScreen$lambda$144$lambda$143$lambda$109(State<? extends MapTheme> state) {
        return (MapTheme) state.getValue();
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$114$lambda$113(DashConfig $dashConfig, RouteViewModel $routeViewModel, State $routingSource$delegate, String label) {
        Intrinsics.checkNotNullParameter(label, "label");
        for (RoutingSource src : RoutingSource.values()) {
            if (Intrinsics.areEqual(src.getLabel(), label)) {
                if (src != RouteScreen$lambda$144$lambda$143$lambda$108($routingSource$delegate)) {
                    $dashConfig.setRoutingSource(src);
                    $routeViewModel.recomputeRoute();
                }
                return Unit.INSTANCE;
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$121$lambda$120$lambda$119$lambda$118$lambda$116$lambda$115(DashConfig $dashConfig, MapTheme $theme) {
        $dashConfig.setMapTheme($theme);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$124$lambda$123(VoiceManager $voiceManager, String it) {
        VoiceMode voiceMode;
        Intrinsics.checkNotNullParameter(it, "it");
        if (Intrinsics.areEqual(it, "Kapalı")) {
            voiceMode = VoiceMode.OFF;
        } else {
            voiceMode = Intrinsics.areEqual(it, "Tam sesli yönlendirme") ? VoiceMode.FULL : VoiceMode.CHIME;
        }
        $voiceManager.setMode(voiceMode);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$126$lambda$125(ManagedActivityResultLauncher $navPermLauncher, String[] $navPermissions, Context $ctx, MutableState $sent$delegate) {
        if (RouteScreen$hasLocationPermission($ctx)) {
            RouteScreen$lambda$8($sent$delegate, true);
        } else {
            $navPermLauncher.launch($navPermissions);
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$128$lambda$127(MutableState $showSave$delegate) {
        RouteScreen$lambda$11($showSave$delegate, true);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$131$lambda$130$lambda$129(MutableState $showSavedDestinations$delegate) {
        RouteScreen$lambda$23($showSavedDestinations$delegate, !RouteScreen$lambda$22($showSavedDestinations$delegate));
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
    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142(State $savedList$delegate, RouteViewModel $routeViewModel, final MutableState $showSavedDestinations$delegate, final MutableState $sharing$delegate, final MutableState $editing$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Alignment.Vertical vertical;
        Function0 function0;
        Function0 function02;
        Function0 function03;
        String format;
        boolean changedInstance;
        Object rememberedValue;
        boolean changedInstance2;
        Object rememberedValue2;
        final RouteViewModel routeViewModel = $routeViewModel;
        Composer composer = $composer;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer, "C*686@36767L169,681@36482L2156:RouteScreen.kt#cef2n4");
        if (composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1756830890, $changed, -1, "com.example.wayfindr.ui.screens.RouteScreen.<anonymous>.<anonymous>.<anonymous> (RouteScreen.kt:679)");
            }
            Iterable RouteScreen$lambda$2 = RouteScreen$lambda$2($savedList$delegate);
            int i = 0;
            int i2 = 0;
            for (Object obj : RouteScreen$lambda$2) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                final SavedLocation savedLocation = (SavedLocation) obj;
                if (i2 > 0) {
                    composer.startReplaceGroup(-149724587);
                    ComposerKt.sourceInformation(composer, "680@36405L52");
                    WayfindrComponentsKt.WayfindrDivider(PaddingKt.m818paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m7908constructorimpl(4), 0.0f, 2, null), composer, 6, 0);
                } else {
                    composer.startReplaceGroup(-382611263);
                }
                composer.endReplaceGroup();
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Modifier clip = ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(12)));
                ComposerKt.sourceInformationMarkerStart(composer, -149712886, "CC(remember):RouteScreen.kt#9igjgp");
                boolean changedInstance3 = composer.changedInstance(routeViewModel) | composer.changedInstance(savedLocation);
                Object rememberedValue3 = $composer.rememberedValue();
                if (changedInstance3) {
                    vertical = centerVertically;
                } else {
                    vertical = centerVertically;
                    if (rememberedValue3 != Composer.INSTANCE.getEmpty()) {
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(ClickableKt.m297clickableoSLSa3U$default(clip, false, null, null, null, (Function0) rememberedValue3, 15, null), Dp.m7908constructorimpl(6), Dp.m7908constructorimpl(12));
                        ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                        MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
                        Iterable iterable = RouteScreen$lambda$2;
                        Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, m817paddingVpY3zN4);
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
                            $composer.useNode();
                        } else {
                            function0 = constructor;
                            $composer.createNode(function0);
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
                        ComposerKt.sourceInformationMarkerStart($composer, -837086718, "C692@37072L309,696@37410L29,697@37468L595,708@38329L17,705@38092L285,712@38564L17,710@38406L206:RouteScreen.kt#cef2n4");
                        Alignment center = Alignment.INSTANCE.getCenter();
                        Modifier m262backgroundbw27NRU$default = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(11))), ColorKt.getGoldTint(), null, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                        MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
                        Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, m262backgroundbw27NRU$default);
                        Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        int i8 = ((((48 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function02 = constructor2;
                            $composer.useNode();
                        } else {
                            function02 = constructor2;
                            $composer.createNode(function02);
                        }
                        Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer);
                        Updater.m4372setimpl(m4364constructorimpl2, maybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                        int i9 = (i8 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i10 = ((48 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -270499303, "C695@37296L83:RouteScreen.kt#cef2n4");
                        IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), ColorKt.getGold(), $composer, 432, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(13)), $composer, 6);
                        Modifier weight$default = RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                        ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                        MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                        int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                        CompositionLocalMap currentCompositionLocalMap3 = $composer.getCurrentCompositionLocalMap();
                        Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer, weight$default);
                        Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                        int i11 = ((((0 << 3) & 112) << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            function03 = constructor3;
                            $composer.useNode();
                        } else {
                            function03 = constructor3;
                            $composer.createNode(function03);
                        }
                        Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer);
                        Updater.m4372setimpl(m4364constructorimpl3, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                        Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                        Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                        int i12 = (i11 >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        int i13 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer, -1754874523, "C698@37530L124,699@37687L346:RouteScreen.kt#cef2n4");
                        TextKt.m3069TextNvy7gAk(savedLocation.getName(), null, ColorKt.getTextHi(), null, TextUnitKt.getSp(14.5d), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer, 1597440, 24576, 245546);
                        if (StringsKt.isBlank(savedLocation.getNote())) {
                            format = savedLocation.getNote();
                        } else {
                            format = String.format("%.4f, %.4f", Arrays.copyOf(new Object[]{Double.valueOf(savedLocation.getLat()), Double.valueOf(savedLocation.getLng())}, 2));
                            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                        }
                        TextKt.m3069TextNvy7gAk(format, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer, 24624, 24576, 245608);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ImageVector share = WayfindrIcons.INSTANCE.getShare();
                        String str = savedLocation.getName() + " konumunu paylaş";
                        long textLo = ColorKt.getTextLo();
                        Modifier m862size3ABfNKs = SizeKt.m862size3ABfNKs(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, Dp.m7908constructorimpl(14), 0.0f, 11, null), Dp.m7908constructorimpl(18));
                        ComposerKt.sourceInformationMarkerStart($composer, -1689532082, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance = $composer.changedInstance(savedLocation);
                        rememberedValue = $composer.rememberedValue();
                        if (!changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda29
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$137$lambda$136(SavedLocation.this, $sharing$delegate);
                                }
                            };
                            $composer.updateRememberedValue(obj2);
                            rememberedValue = obj2;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        IconKt.m2517Iconww6aTOc(share, str, ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs, false, null, null, null, (Function0) rememberedValue, 15, null), textLo, $composer, 0, 0);
                        ImageVector edit = WayfindrIcons.INSTANCE.getEdit();
                        long textLo2 = ColorKt.getTextLo();
                        Modifier m862size3ABfNKs2 = SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18));
                        ComposerKt.sourceInformationMarkerStart($composer, -1689524562, "CC(remember):RouteScreen.kt#9igjgp");
                        changedInstance2 = $composer.changedInstance(savedLocation);
                        rememberedValue2 = $composer.rememberedValue();
                        if (!changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            Object obj3 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda30
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$139$lambda$138(SavedLocation.this, $editing$delegate);
                                }
                            };
                            $composer.updateRememberedValue(obj3);
                            rememberedValue2 = obj3;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        IconKt.m2517Iconww6aTOc(edit, "Düzenle", ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs2, false, null, null, null, (Function0) rememberedValue2, 15, null), textLo2, $composer, 48, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        routeViewModel = $routeViewModel;
                        composer = $composer;
                        i2 = i3;
                        RouteScreen$lambda$2 = iterable;
                        i = i5;
                    }
                }
                rememberedValue3 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda28
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$133$lambda$132(RouteViewModel.this, savedLocation, $showSavedDestinations$delegate);
                    }
                };
                $composer.updateRememberedValue(rememberedValue3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier m817paddingVpY3zN42 = PaddingKt.m817paddingVpY3zN4(ClickableKt.m297clickableoSLSa3U$default(clip, false, null, null, null, (Function0) rememberedValue3, 15, null), Dp.m7908constructorimpl(6), Dp.m7908constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), vertical, $composer, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap4 = $composer.getCurrentCompositionLocalMap();
                Iterable iterable2 = RouteScreen$lambda$2;
                Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer, m817paddingVpY3zN42);
                Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                int i42 = ((((384 << 3) & 112) << 6) & 896) | 6;
                int i52 = i;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                }
                Composer m4364constructorimpl4 = Updater.m4364constructorimpl($composer);
                Updater.m4372setimpl(m4364constructorimpl4, rowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                int i62 = (i42 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i72 = ((384 >> 6) & 112) | 6;
                RowScope rowScope2 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, -837086718, "C692@37072L309,696@37410L29,697@37468L595,708@38329L17,705@38092L285,712@38564L17,710@38406L206:RouteScreen.kt#cef2n4");
                Alignment center2 = Alignment.INSTANCE.getCenter();
                Modifier m262backgroundbw27NRU$default2 = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(40)), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(11))), ColorKt.getGoldTint(), null, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap22 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier22 = ComposedModifierKt.materializeModifier($composer, m262backgroundbw27NRU$default2);
                Function0 constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                int i82 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                }
                Composer m4364constructorimpl22 = Updater.m4364constructorimpl($composer);
                Updater.m4372setimpl(m4364constructorimpl22, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl22, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl22, Integer.valueOf(hashCode22), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl22, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl22, materializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                int i92 = (i82 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                int i102 = ((48 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -270499303, "C695@37296L83:RouteScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getLocationPin(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), ColorKt.getGold(), $composer, 432, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(13)), $composer, 6);
                Modifier weight$default2 = RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null);
                ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode32 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
                CompositionLocalMap currentCompositionLocalMap32 = $composer.getCurrentCompositionLocalMap();
                Modifier materializeModifier32 = ComposedModifierKt.materializeModifier($composer, weight$default2);
                Function0 constructor32 = ComposeUiNode.INSTANCE.getConstructor();
                int i112 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer.getApplier() instanceof Applier)) {
                }
                $composer.startReusableNode();
                if ($composer.getInserting()) {
                }
                Composer m4364constructorimpl32 = Updater.m4364constructorimpl($composer);
                Updater.m4372setimpl(m4364constructorimpl32, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl32, currentCompositionLocalMap32, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl32, Integer.valueOf(hashCode32), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl32, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl32, materializeModifier32, ComposeUiNode.INSTANCE.getSetModifier());
                int i122 = (i112 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                int i132 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer, -1754874523, "C698@37530L124,699@37687L346:RouteScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk(savedLocation.getName(), null, ColorKt.getTextHi(), null, TextUnitKt.getSp(14.5d), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer, 1597440, 24576, 245546);
                if (StringsKt.isBlank(savedLocation.getNote())) {
                }
                TextKt.m3069TextNvy7gAk(format, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(2), 0.0f, 0.0f, 13, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(12), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer, 24624, 24576, 245608);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ImageVector share2 = WayfindrIcons.INSTANCE.getShare();
                String str2 = savedLocation.getName() + " konumunu paylaş";
                long textLo3 = ColorKt.getTextLo();
                Modifier m862size3ABfNKs3 = SizeKt.m862size3ABfNKs(PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, Dp.m7908constructorimpl(14), 0.0f, 11, null), Dp.m7908constructorimpl(18));
                ComposerKt.sourceInformationMarkerStart($composer, -1689532082, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance = $composer.changedInstance(savedLocation);
                rememberedValue = $composer.rememberedValue();
                if (!changedInstance) {
                }
                Object obj22 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda29
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$137$lambda$136(SavedLocation.this, $sharing$delegate);
                    }
                };
                $composer.updateRememberedValue(obj22);
                rememberedValue = obj22;
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconKt.m2517Iconww6aTOc(share2, str2, ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs3, false, null, null, null, (Function0) rememberedValue, 15, null), textLo3, $composer, 0, 0);
                ImageVector edit2 = WayfindrIcons.INSTANCE.getEdit();
                long textLo22 = ColorKt.getTextLo();
                Modifier m862size3ABfNKs22 = SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(18));
                ComposerKt.sourceInformationMarkerStart($composer, -1689524562, "CC(remember):RouteScreen.kt#9igjgp");
                changedInstance2 = $composer.changedInstance(savedLocation);
                rememberedValue2 = $composer.rememberedValue();
                if (!changedInstance2) {
                }
                Object obj32 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda30
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$139$lambda$138(SavedLocation.this, $editing$delegate);
                    }
                };
                $composer.updateRememberedValue(obj32);
                rememberedValue2 = obj32;
                ComposerKt.sourceInformationMarkerEnd($composer);
                IconKt.m2517Iconww6aTOc(edit2, "Düzenle", ClickableKt.m297clickableoSLSa3U$default(m862size3ABfNKs22, false, null, null, null, (Function0) rememberedValue2, 15, null), textLo22, $composer, 48, 0);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                $composer.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                routeViewModel = $routeViewModel;
                composer = $composer;
                i2 = i3;
                RouteScreen$lambda$2 = iterable2;
                i = i52;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$133$lambda$132(RouteViewModel $routeViewModel, SavedLocation $loc, MutableState $showSavedDestinations$delegate) {
        $routeViewModel.selectSaved($loc);
        RouteScreen$lambda$23($showSavedDestinations$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$137$lambda$136(SavedLocation $loc, MutableState $sharing$delegate) {
        $sharing$delegate.setValue($loc);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$144$lambda$143$lambda$142$lambda$141$lambda$140$lambda$139$lambda$138(SavedLocation $loc, MutableState $editing$delegate) {
        $editing$delegate.setValue($loc);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$146$lambda$145(RouteViewModel $routeViewModel, MutableState $showSave$delegate, String name, String note) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(note, "note");
        $routeViewModel.saveCurrentDestination(name, note);
        RouteScreen$lambda$11($showSave$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$148$lambda$147(MutableState $showSave$delegate) {
        RouteScreen$lambda$11($showSave$delegate, false);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$151$lambda$150$lambda$149(MutableState $sharing$delegate) {
        $sharing$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$158$lambda$153$lambda$152(RouteViewModel $routeViewModel, SavedLocation $loc, MutableState $editing$delegate, String name, String note) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(note, "note");
        $routeViewModel.renameSaved($loc, name, note);
        $editing$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$158$lambda$155$lambda$154(RouteViewModel $routeViewModel, SavedLocation $loc, MutableState $editing$delegate) {
        $routeViewModel.deleteSaved($loc);
        $editing$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit RouteScreen$lambda$158$lambda$157$lambda$156(MutableState $editing$delegate) {
        $editing$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    private static final void SaveLocationDialog(final String defaultName, final Function2<? super String, ? super String, Unit> function2, final Function0<Unit> function0, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1163012778);
        ComposerKt.sourceInformation($composer3, "C(SaveLocationDialog)N(defaultName,onSave,onDismiss)739@39496L40,740@39553L31,743@39691L186,744@39903L97,746@40076L404,741@39616L903:RouteScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(defaultName) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (!$composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1163012778, $dirty, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog (RouteScreen.kt:738)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, -963778210, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(defaultName, null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default);
                rememberedValue = mutableStateOf$default;
            }
            final MutableState name$delegate = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -963776395, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default2);
                rememberedValue2 = mutableStateOf$default2;
            }
            final MutableState note$delegate = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.m2063AlertDialogOix01E0(function0, ComposableLambdaKt.rememberComposableLambda(-779189346, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda44
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.SaveLocationDialog$lambda$169(Function2.this, name$delegate, note$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(-1727241312, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda45
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.SaveLocationDialog$lambda$170(Function0.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ComposableSingletons$RouteScreenKt.INSTANCE.getLambda$1619674018$app(), ComposableLambdaKt.rememberComposableLambda(1145648035, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda46
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.SaveLocationDialog$lambda$176(MutableState.this, note$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ColorKt.getSurf1(), 0L, 0L, 0L, 0.0f, null, $composer2, (($dirty >> 6) & 14) | 1772592, 0, 16020);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda47
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.SaveLocationDialog$lambda$177(defaultName, function2, function0, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String SaveLocationDialog$lambda$161(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String SaveLocationDialog$lambda$164(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit SaveLocationDialog$lambda$169(final Function2 $onSave, final MutableState $name$delegate, final MutableState $note$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C743@39770L36,743@39808L67,743@39720L155:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-779189346, $changed, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous> (RouteScreen.kt:743)");
            }
            boolean z = !StringsKt.isBlank(SaveLocationDialog$lambda$161($name$delegate));
            ComposerKt.sourceInformationMarkerStart($composer, -1476649438, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed = $composer.changed($onSave);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda52
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.SaveLocationDialog$lambda$169$lambda$167$lambda$166(Function2.this, $name$delegate, $note$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) rememberedValue, null, z, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(2084718363, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda53
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return RouteScreenKt.SaveLocationDialog$lambda$169$lambda$168(MutableState.this, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$169$lambda$167$lambda$166(Function2 $onSave, MutableState $name$delegate, MutableState $note$delegate) {
        $onSave.invoke(StringsKt.trim((CharSequence) SaveLocationDialog$lambda$161($name$delegate)).toString(), StringsKt.trim((CharSequence) SaveLocationDialog$lambda$164($note$delegate)).toString());
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$169$lambda$168(MutableState $name$delegate, RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C743@39810L63:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2084718363, $changed, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous>.<anonymous> (RouteScreen.kt:743)");
            }
            TextKt.m3069TextNvy7gAk("Kaydet", null, !StringsKt.isBlank(SaveLocationDialog$lambda$161($name$delegate)) ? ColorKt.getGold() : ColorKt.getTextLo(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$170(Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C744@39932L66:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1727241312, $changed, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous> (RouteScreen.kt:744)");
            }
            ButtonKt.TextButton($onDismiss, null, false, null, null, null, null, null, null, ComposableSingletons$RouteScreenKt.INSTANCE.getLambda$1136666397$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$176(final MutableState $name$delegate, final MutableState $note$delegate, Composer $composer, int $changed) {
        Function0 function0;
        Object obj;
        Composer composer;
        ComposerKt.sourceInformation($composer, "C747@40090L380:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1145648035, $changed, -1, "com.example.wayfindr.ui.screens.SaveLocationDialog.<anonymous> (RouteScreen.kt:747)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, -1159392043, "C748@40166L13,748@40142L117,749@40327L13,749@40303L153:RouteScreen.kt#cef2n4");
            String SaveLocationDialog$lambda$161 = SaveLocationDialog$lambda$161($name$delegate);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1486622214, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda32
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return RouteScreenKt.SaveLocationDialog$lambda$176$lambda$175$lambda$172$lambda$171(MutableState.this, (String) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                obj = obj2;
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(SaveLocationDialog$lambda$161, (Function1<? super String, Unit>) obj, fillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RouteScreenKt.INSTANCE.getLambda$837491251$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
            String SaveLocationDialog$lambda$164 = SaveLocationDialog$lambda$164($note$delegate);
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 13, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1486627366, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = $composer.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                composer = $composer;
                Object obj3 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda33
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return RouteScreenKt.SaveLocationDialog$lambda$176$lambda$175$lambda$174$lambda$173(MutableState.this, (String) obj4);
                    }
                };
                $composer.updateRememberedValue(obj3);
                rememberedValue2 = obj3;
            } else {
                composer = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(SaveLocationDialog$lambda$164, (Function1<? super String, Unit>) rememberedValue2, m820paddingqDBjuR0$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RouteScreenKt.INSTANCE.m8362getLambda$1895094998$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1573296, 12582912, 0, 8257464);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$176$lambda$175$lambda$172$lambda$171(MutableState $name$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $name$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit SaveLocationDialog$lambda$176$lambda$175$lambda$174$lambda$173(MutableState $note$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $note$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final void EditLocationDialog(final SavedLocation loc, final Function2<? super String, ? super String, Unit> function2, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(1703299186);
        ComposerKt.sourceInformation($composer3, "C(EditLocationDialog)N(loc,onSave,onDelete,onDismiss)758@40709L37,759@40763L37,762@40907L186,763@41119L92,765@41288L389,760@40832L884:RouteScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer3.changed(loc) : $composer3.changedInstance(loc) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(function2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(function02) ? 2048 : 1024;
        }
        if (!$composer3.shouldExecute(($dirty & 1171) != 1170, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1703299186, $dirty, -1, "com.example.wayfindr.ui.screens.EditLocationDialog (RouteScreen.kt:757)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 487983959, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(loc.getName(), null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default);
                rememberedValue = mutableStateOf$default;
            }
            final MutableState name$delegate = (MutableState) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 487985687, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(loc.getNote(), null, 2, null);
                $composer3.updateRememberedValue(mutableStateOf$default2);
                rememberedValue2 = mutableStateOf$default2;
            }
            final MutableState note$delegate = (MutableState) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            AndroidAlertDialog_androidKt.m2063AlertDialogOix01E0(function02, ComposableLambdaKt.rememberComposableLambda(1532949290, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda48
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.EditLocationDialog$lambda$187(Function2.this, name$delegate, note$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ComposableLambdaKt.rememberComposableLambda(209770344, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda49
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.EditLocationDialog$lambda$188(Function0.this, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ComposableSingletons$RouteScreenKt.INSTANCE.m8361getLambda$1113408602$app(), ComposableLambdaKt.rememberComposableLambda(-1774998075, true, new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda50
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.EditLocationDialog$lambda$194(MutableState.this, note$delegate, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer3, 54), null, ColorKt.getSurf1(), 0L, 0L, 0L, 0.0f, null, $composer2, (($dirty >> 9) & 14) | 1772592, 0, 16020);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda51
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.EditLocationDialog$lambda$195(SavedLocation.this, function2, function0, function02, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final String EditLocationDialog$lambda$179(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    private static final String EditLocationDialog$lambda$182(MutableState<String> mutableState) {
        return mutableState.getValue();
    }

    static final Unit EditLocationDialog$lambda$187(final Function2 $onSave, final MutableState $name$delegate, final MutableState $note$delegate, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C762@40986L36,762@41024L67,762@40936L155:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1532949290, $changed, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous> (RouteScreen.kt:762)");
            }
            boolean z = !StringsKt.isBlank(EditLocationDialog$lambda$179($name$delegate));
            ComposerKt.sourceInformationMarkerStart($composer, -9512274, "CC(remember):RouteScreen.kt#9igjgp");
            boolean changed = $composer.changed($onSave);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda55
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return RouteScreenKt.EditLocationDialog$lambda$187$lambda$185$lambda$184(Function2.this, $name$delegate, $note$delegate);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ButtonKt.TextButton((Function0) rememberedValue, null, z, null, null, null, null, null, null, ComposableLambdaKt.rememberComposableLambda(663025997, true, new Function3() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda56
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj2, Object obj3, Object obj4) {
                    return RouteScreenKt.EditLocationDialog$lambda$187$lambda$186(MutableState.this, (RowScope) obj2, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 805306368, 506);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$187$lambda$185$lambda$184(Function2 $onSave, MutableState $name$delegate, MutableState $note$delegate) {
        $onSave.invoke(StringsKt.trim((CharSequence) EditLocationDialog$lambda$179($name$delegate)).toString(), StringsKt.trim((CharSequence) EditLocationDialog$lambda$182($note$delegate)).toString());
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$187$lambda$186(MutableState $name$delegate, RowScope TextButton, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(TextButton, "$this$TextButton");
        ComposerKt.sourceInformation($composer, "C762@41026L63:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(663025997, $changed, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous>.<anonymous> (RouteScreen.kt:762)");
            }
            TextKt.m3069TextNvy7gAk("Kaydet", null, !StringsKt.isBlank(EditLocationDialog$lambda$179($name$delegate)) ? ColorKt.getGold() : ColorKt.getTextLo(), null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 6, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$188(Function0 $onDelete, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C763@41148L61:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(209770344, $changed, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous> (RouteScreen.kt:763)");
            }
            ButtonKt.TextButton($onDelete, null, false, null, null, null, null, null, null, ComposableSingletons$RouteScreenKt.INSTANCE.m8363getLambda$660152949$app(), $composer, 805306368, 510);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$194(final MutableState $name$delegate, final MutableState $note$delegate, Composer $composer, int $changed) {
        Function0 function0;
        Object obj;
        Composer composer;
        ComposerKt.sourceInformation($composer, "C766@41302L365:RouteScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1774998075, $changed, -1, "com.example.wayfindr.ui.screens.EditLocationDialog.<anonymous> (RouteScreen.kt:766)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((0 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer, 563472974, "C767@41378L13,767@41354L117,768@41539L13,768@41515L138:RouteScreen.kt#cef2n4");
            String EditLocationDialog$lambda$179 = EditLocationDialog$lambda$179($name$delegate);
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1680745852, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue = $composer.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda37
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return RouteScreenKt.EditLocationDialog$lambda$194$lambda$193$lambda$190$lambda$189(MutableState.this, (String) obj3);
                    }
                };
                $composer.updateRememberedValue(obj2);
                obj = obj2;
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            OutlinedTextFieldKt.OutlinedTextField(EditLocationDialog$lambda$179, (Function1<? super String, Unit>) obj, fillMaxWidth$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RouteScreenKt.INSTANCE.getLambda$568965429$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, $composer, 1573296, 12582912, 0, 8257464);
            String EditLocationDialog$lambda$182 = EditLocationDialog$lambda$182($note$delegate);
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(8), 0.0f, 0.0f, 13, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1680751004, "CC(remember):RouteScreen.kt#9igjgp");
            Object rememberedValue2 = $composer.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                composer = $composer;
                Object obj3 = new Function1() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda38
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return RouteScreenKt.EditLocationDialog$lambda$194$lambda$193$lambda$192$lambda$191(MutableState.this, (String) obj4);
                    }
                };
                $composer.updateRememberedValue(obj3);
                rememberedValue2 = obj3;
            } else {
                composer = $composer;
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            OutlinedTextFieldKt.OutlinedTextField(EditLocationDialog$lambda$182, (Function1<? super String, Unit>) rememberedValue2, m820paddingqDBjuR0$default, false, false, (TextStyle) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableSingletons$RouteScreenKt.INSTANCE.getLambda$73407262$app(), (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, false, (VisualTransformation) null, (KeyboardOptions) null, (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, (TextFieldColors) null, composer, 1573296, 12582912, 0, 8257464);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$194$lambda$193$lambda$190$lambda$189(MutableState $name$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $name$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    static final Unit EditLocationDialog$lambda$194$lambda$193$lambda$192$lambda$191(MutableState $note$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $note$delegate.setValue(it);
        return Unit.INSTANCE;
    }

    private static final String formatAlternativeDistance(double meters) {
        if (meters < 1000.0d) {
            return ((int) meters) + " m";
        }
        String format = String.format(Locale.forLanguageTag("tr-TR"), "%.1f km", Arrays.copyOf(new Object[]{Double.valueOf(meters / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private static final String formatAlternativeDuration(double seconds) {
        StringBuilder append;
        int minutes = RangesKt.coerceAtLeast((int) (seconds / 60.0d), 0);
        if (minutes >= 60) {
            append = new StringBuilder().append(minutes / 60).append(" sa ").append(minutes % 60);
        } else {
            append = new StringBuilder().append(minutes);
        }
        return append.append(" dk").toString();
    }

    private static final void RouteMapLegend(final List<Route> list, final int selectedIndex, final Function1<? super Integer, Unit> function1, Modifier modifier, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Composer $composer2;
        final Modifier modifier3;
        final Modifier.Companion modifier4;
        Function0 function0;
        long m5092copywmQWz5c;
        Function0 function02;
        long m5092copywmQWz5c2;
        Function0 function03;
        Function0 function04;
        long m5092copywmQWz5c3;
        final int i2 = selectedIndex;
        final Function1<? super Integer, Unit> function12 = function1;
        Composer $composer3 = $composer.startRestartGroup(-444490650);
        ComposerKt.sourceInformation($composer3, "C(RouteMapLegend)N(routes,selectedIndex,onSelect,modifier)793@42358L2350:RouteScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(list) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(i2) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function12) ? 256 : 128;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int $dirty2 = $dirty;
        if (!$composer3.shouldExecute(($dirty2 & 1171) != 1170, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            if (i3 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-444490650, $dirty2, -1, "com.example.wayfindr.ui.screens.RouteMapLegend (RouteScreen.kt:790)");
            }
            if (list.size() > 1) {
                Route main = (Route) CollectionsKt.first((List) list);
                Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(BackgroundKt.background$default(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, null), Brush.Companion.m5049verticalGradient8A3gB4$default(Brush.INSTANCE, CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU()), Color.m5084boximpl(androidx.compose.ui.graphics.ColorKt.Color(3893038610L))}), 0.0f, 0.0f, 0, 14, (Object) null), null, 0.0f, 6, null), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(8));
                int i4 = 0;
                ComposerKt.sourceInformationMarkerStart($composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
                Modifier modifier5 = modifier4;
                Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, m817paddingVpY3zN4);
                Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
                int i5 = ((((0 << 3) & 112) << 6) & 896) | 6;
                String str = "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp";
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
                Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                int i6 = (i5 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                int i7 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -1213299173, "C798@42578L210,803@42797L29,805@42904L21,804@42835L1867:RouteScreen.kt#cef2n4");
                m5092copywmQWz5c = Color.m5092copywmQWz5c(r41, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r41) : 0.82f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r41) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r41) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(Color.INSTANCE.m5131getWhite0d7_KjU()) : 0.0f);
                TextKt.m3069TextNvy7gAk("ANA ROTAYA GÖRE · MESAFE / SÜRE FARKI", null, m5092copywmQWz5c, null, TextUnitKt.getSp(9), null, FontWeight.INSTANCE.getBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 1597830, 0, 261930);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(6)), $composer3, 6);
                Modifier horizontalScroll$default = ScrollKt.horizontalScroll$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer3, 0, 1), false, null, false, 14, null);
                Arrangement.Horizontal m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(7));
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m686spacedBy0680j_4, centerVertically, $composer3, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
                CompositionLocalMap currentCompositionLocalMap2 = $composer3.getCurrentCompositionLocalMap();
                Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer3, horizontalScroll$default);
                Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                int i8 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer3, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer3.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer3.startReusableNode();
                if ($composer3.getInserting()) {
                    function02 = constructor2;
                    $composer3.createNode(function02);
                } else {
                    function02 = constructor2;
                    $composer3.useNode();
                }
                Composer m4364constructorimpl2 = Updater.m4364constructorimpl($composer3);
                Updater.m4372setimpl(m4364constructorimpl2, rowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2, Integer.valueOf(hashCode2), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2, materializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                int i9 = (i8 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                int i10 = ((432 >> 6) & 112) | 6;
                Composer composer = $composer3;
                ComposerKt.sourceInformationMarkerStart(composer, -1988190157, "C:RouteScreen.kt#cef2n4");
                composer.startReplaceGroup(-1726702687);
                ComposerKt.sourceInformation(composer, "*816@43534L19,812@43248L1430");
                Iterable take = CollectionsKt.take(list, 3);
                int i11 = 0;
                int i12 = 0;
                for (Object obj : take) {
                    int i13 = i12 + 1;
                    if (i12 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    Route route = (Route) obj;
                    final int i14 = i12;
                    Iterable iterable = take;
                    boolean z = i14 == i2;
                    int i15 = i11;
                    RoundedCornerShape m1124RoundedCornerShape0680j_4 = RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(10));
                    Modifier m262backgroundbw27NRU$default = BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, m1124RoundedCornerShape0680j_4), androidx.compose.ui.graphics.ColorKt.Color(z ? 3844483890L : 3474003226L), null, 2, null);
                    Modifier modifier6 = materializeModifier2;
                    float m7908constructorimpl = Dp.m7908constructorimpl(1);
                    if (z) {
                        m5092copywmQWz5c2 = ColorKt.getGold();
                    } else {
                        m5092copywmQWz5c2 = Color.m5092copywmQWz5c(r80, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r80) : 0.18f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r80) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r80) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(Color.INSTANCE.m5131getWhite0d7_KjU()) : 0.0f);
                    }
                    int i16 = i4;
                    int i17 = hashCode;
                    Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(m262backgroundbw27NRU$default, m7908constructorimpl, m5092copywmQWz5c2, m1124RoundedCornerShape0680j_4);
                    ComposerKt.sourceInformationMarkerStart(composer, 1229478641, "CC(remember):RouteScreen.kt#9igjgp");
                    boolean changed = (($dirty2 & 896) == 256) | $composer3.changed(i14);
                    Composer composer2 = composer;
                    Object rememberedValue = composer2.rememberedValue();
                    if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda35
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return RouteScreenKt.RouteMapLegend$lambda$203$lambda$202$lambda$201$lambda$198$lambda$197(Function1.this, i14);
                            }
                        };
                        composer2.updateRememberedValue(obj2);
                        rememberedValue = obj2;
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    Modifier m817paddingVpY3zN42 = PaddingKt.m817paddingVpY3zN4(ClickableKt.m297clickableoSLSa3U$default(m273borderxT4_qwU, false, null, null, null, (Function0) rememberedValue, 15, null), Dp.m7908constructorimpl(9), Dp.m7908constructorimpl(6));
                    Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                    Composer composer3 = composer;
                    ComposerKt.sourceInformationMarkerStart(composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composer3, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                    Composer composer4 = composer;
                    Modifier materializeModifier3 = ComposedModifierKt.materializeModifier(composer3, m817paddingVpY3zN42);
                    Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int i18 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, str);
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        function03 = constructor3;
                        composer3.createNode(function03);
                    } else {
                        function03 = constructor3;
                        composer3.useNode();
                    }
                    Composer m4364constructorimpl3 = Updater.m4364constructorimpl(composer3);
                    Composer $composer4 = $composer3;
                    Updater.m4372setimpl(m4364constructorimpl3, rowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i19 = (i18 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i20 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -985842583, "C820@43732L187,824@43940L28,825@43989L671:RouteScreen.kt#cef2n4");
                    BoxKt.Box(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.m848height3ABfNKs(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(4)), Dp.m7908constructorimpl(27)), RoundedCornerShapeKt.getCircleShape()), RouteAlternativeStyle.INSTANCE.m8356composeColorvNxB06k(i14), null, 2, null), composer3, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(7)), composer3, 6);
                    ComposerKt.sourceInformationMarkerStart(composer3, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier7 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart(composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composer3.getCurrentCompositionLocalMap();
                    Modifier materializeModifier4 = ComposedModifierKt.materializeModifier(composer3, modifier7);
                    Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int i21 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, -553112988, str);
                    if (!(composer3.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        function04 = constructor4;
                        composer3.createNode(function04);
                    } else {
                        function04 = constructor4;
                        composer3.useNode();
                    }
                    Composer m4364constructorimpl4 = Updater.m4364constructorimpl(composer3);
                    String str2 = str;
                    Updater.m4372setimpl(m4364constructorimpl4, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i22 = (i21 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart(composer3, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i23 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart(composer3, 222899479, "C826@44022L276,831@44323L315:RouteScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk(i14 == 0 ? "Ana rota" : "Alternatif " + i14, null, ColorKt.getTextHi(), null, TextUnitKt.getSp(11), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, composer3, 1597440, 24576, 245546);
                    String routeDifferenceLabel = RouteAlternativeStyleKt.routeDifferenceLabel(route.getTotalMeters(), route.getTotalSeconds(), main.getTotalMeters(), main.getTotalSeconds());
                    m5092copywmQWz5c3 = Color.m5092copywmQWz5c(r126, (r12 & 1) != 0 ? Color.m5096getAlphaimpl(r126) : 0.86f, (r12 & 2) != 0 ? Color.m5100getRedimpl(r126) : 0.0f, (r12 & 4) != 0 ? Color.m5099getGreenimpl(r126) : 0.0f, (r12 & 8) != 0 ? Color.m5097getBlueimpl(Color.INSTANCE.m5131getWhite0d7_KjU()) : 0.0f);
                    TextKt.m3069TextNvy7gAk(routeDifferenceLabel, null, m5092copywmQWz5c3, null, TextUnitKt.getSp(9), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, composer3, 24960, 24576, 245610);
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
                    i2 = selectedIndex;
                    function12 = function1;
                    i12 = i13;
                    take = iterable;
                    i11 = i15;
                    hashCode = i17;
                    materializeModifier2 = modifier6;
                    i4 = i16;
                    composer = composer4;
                    $composer3 = $composer4;
                    str = str2;
                }
                Composer composer5 = composer;
                $composer2 = $composer3;
                composer5.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composer5);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier5;
            } else {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
                if (endRestartGroup != null) {
                    endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda34
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj3, Object obj4) {
                            return RouteScreenKt.RouteMapLegend$lambda$196(list, i2, function12, modifier4, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                        }
                    });
                    return;
                }
                return;
            }
        }
        ScopeUpdateScope endRestartGroup2 = $composer2.endRestartGroup();
        if (endRestartGroup2 != null) {
            endRestartGroup2.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda36
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return RouteScreenKt.RouteMapLegend$lambda$204(list, selectedIndex, function1, modifier3, $changed, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    static final Unit RouteMapLegend$lambda$203$lambda$202$lambda$201$lambda$198$lambda$197(Function1 $onSelect, int $index) {
        $onSelect.invoke(Integer.valueOf($index));
        return Unit.INSTANCE;
    }

    private static final void ChoiceHeader(final String label, String detail, Composer $composer, final int $changed) {
        Composer $composer2;
        final String str = detail;
        Composer $composer3 = $composer.startRestartGroup(392214881);
        ComposerKt.sourceInformation($composer3, "C(ChoiceHeader)N(label,detail)847@44888L375:RouteScreen.kt#cef2n4");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(label) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(str) ? 32 : 16;
        }
        if (!$composer3.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(392214881, $dirty, -1, "com.example.wayfindr.ui.screens.ChoiceHeader (RouteScreen.kt:846)");
            }
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(9), 7, null);
            ComposerKt.sourceInformationMarkerStart($composer3, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer3, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer3, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer3.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer3, m820paddingqDBjuR0$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            int i2 = (i >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer3, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((438 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -617312233, "C852@45090L14,853@45113L144:RouteScreen.kt#cef2n4");
            WayfindrComponentsKt.Eyebrow(label, null, $composer3, $dirty & 14, 2);
            $composer2 = $composer3;
            str = detail;
            TextKt.m3069TextNvy7gAk(str, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m7908constructorimpl(12), 0.0f, 0.0f, 0.0f, 14, null), ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 1, 0, null, null, $composer3, (($dirty >> 3) & 14) | 24624, 24576, 245608);
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
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.RouteScreenKt$$ExternalSyntheticLambda31
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return RouteScreenKt.ChoiceHeader$lambda$206(label, str, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
