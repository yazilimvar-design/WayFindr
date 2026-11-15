package com.example.wayfindr.ui.screens;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.os.Build;
import androidx.activity.compose.ActivityResultRegistryKt;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ImageKt;
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
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidImageBitmap_androidKt;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.TileMode;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.ui.WayfindrIcons;
import com.example.wayfindr.ui.components.BtnSize;
import com.example.wayfindr.ui.components.BtnVariant;
import com.example.wayfindr.ui.components.ChipTone;
import com.example.wayfindr.ui.components.FreeWayfindrMapKt;
import com.example.wayfindr.ui.components.JoystickKt;
import com.example.wayfindr.ui.components.WayfindrComponentsKt;
import com.example.wayfindr.ui.theme.TypeKt;
import com.example.wayfindr.util.DeviceReadiness;
import com.example.wayfindr.viewmodel.ConnStage;
import com.example.wayfindr.viewmodel.DashUiState;
import com.example.wayfindr.viewmodel.DashViewModel;
import com.example.wayfindr.viewmodel.GpsStatus;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DashScreen.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002\u001a\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002¨\u0006\n²\u0006\n\u0010\u000b\u001a\u00020\fX\u008a\u0084\u0002²\u0006\f\u0010\r\u001a\u0004\u0018\u00010\u000eX\u008a\u0084\u0002²\u0006\n\u0010\u000f\u001a\u00020\u0010X\u008a\u0084\u0002²\u0006\n\u0010\u0011\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0012\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010\u0014\u001a\u00020\u0010X\u008a\u008e\u0002²\u0006\n\u0010\u0015\u001a\u00020\u0013X\u008a\u008e\u0002²\u0006\n\u0010\u0016\u001a\u00020\u0010X\u008a\u008e\u0002"}, d2 = {"DashScreen", "", "vm", "Lcom/example/wayfindr/viewmodel/DashViewModel;", "(Lcom/example/wayfindr/viewmodel/DashViewModel;Landroidx/compose/runtime/Composer;II)V", "turkishManeuver", "", "instruction", "turkishConnectionError", "error", "app", "ui", "Lcom/example/wayfindr/viewmodel/DashUiState;", "previewFrame", "Landroid/graphics/Bitmap;", "autoConnectOnStart", "", "batteryOk", "pan", "Landroidx/compose/ui/geometry/Offset;", "adjustMode", "joystickVelocity", "autoConnectTried"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class DashScreenKt {

    /* compiled from: DashScreen.kt */
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
            try {
                iArr[ConnStage.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[ConnStage.OFFLINE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit DashScreen$lambda$97(DashViewModel dashViewModel, int i, int i2, Composer composer, int i3) {
        DashScreen(dashViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:166:0x110d, code lost:
    
        if (r2 == null) goto L270;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x1173, code lost:
    
        if (r2 == null) goto L282;
     */
    /* JADX WARN: Code restructure failed: missing block: B:266:0x1fc1, code lost:
    
        if (r3.changedInstance(r2) == false) goto L387;
     */
    /* JADX WARN: Code restructure failed: missing block: B:271:0x1fe2, code lost:
    
        if (r8 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L398;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x2534, code lost:
    
        if (r15 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L447;
     */
    /* JADX WARN: Code restructure failed: missing block: B:339:0x29fe, code lost:
    
        if (r0 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L488;
     */
    /* JADX WARN: Code restructure failed: missing block: B:349:0x2ac0, code lost:
    
        if (r0 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L504;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x02de, code lost:
    
        if (r9 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L91;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x050c  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0565  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0580  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x060a  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x062a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0737  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0743  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x07ee  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0955  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0961  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x09e2  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0b20  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0b2c  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0bb1  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0d11  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x1029  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x1035  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x10cb  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x111e  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x113d  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x1181  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x11dc  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x1628  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x164a  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x16f6  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x1702  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x180c  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x1818  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x18a1  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x196b  */
    /* JADX WARN: Removed duplicated region for block: B:235:0x1977  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x1ab3  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x1bdd  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x1be9  */
    /* JADX WARN: Removed duplicated region for block: B:247:0x1cf3  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x1cff  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x1d8c  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x1e56  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x1e62  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x1f26  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x1fbb  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x1fda  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x2115  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x2121  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x222b  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x2237  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x22c4  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x238e  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x239a  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x245e  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x246d  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x2514  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x252c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x2614  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x2620  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x2795  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x27a1  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x2848  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x293b  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x2947  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x29db  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x29f6  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x2a9d  */
    /* JADX WARN: Removed duplicated region for block: B:348:0x2ab8  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x2b07  */
    /* JADX WARN: Removed duplicated region for block: B:358:0x2b22  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x2b92  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x2c32  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x2ce3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x01cc  */
    /* JADX WARN: Removed duplicated region for block: B:402:0x2cbd  */
    /* JADX WARN: Removed duplicated region for block: B:409:0x2b30  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x2ac4  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x2a02  */
    /* JADX WARN: Removed duplicated region for block: B:421:0x294d  */
    /* JADX WARN: Removed duplicated region for block: B:422:0x285a  */
    /* JADX WARN: Removed duplicated region for block: B:423:0x27a7  */
    /* JADX WARN: Removed duplicated region for block: B:424:0x2626  */
    /* JADX WARN: Removed duplicated region for block: B:425:0x2538  */
    /* JADX WARN: Removed duplicated region for block: B:429:0x2470  */
    /* JADX WARN: Removed duplicated region for block: B:430:0x2461  */
    /* JADX WARN: Removed duplicated region for block: B:431:0x23a0  */
    /* JADX WARN: Removed duplicated region for block: B:432:0x22c9  */
    /* JADX WARN: Removed duplicated region for block: B:433:0x223d  */
    /* JADX WARN: Removed duplicated region for block: B:434:0x2127  */
    /* JADX WARN: Removed duplicated region for block: B:435:0x1fe6  */
    /* JADX WARN: Removed duplicated region for block: B:438:0x1fcd  */
    /* JADX WARN: Removed duplicated region for block: B:439:0x1fc4  */
    /* JADX WARN: Removed duplicated region for block: B:440:0x1f29  */
    /* JADX WARN: Removed duplicated region for block: B:441:0x1e68  */
    /* JADX WARN: Removed duplicated region for block: B:442:0x1d91  */
    /* JADX WARN: Removed duplicated region for block: B:443:0x1d05  */
    /* JADX WARN: Removed duplicated region for block: B:444:0x1bef  */
    /* JADX WARN: Removed duplicated region for block: B:445:0x197d  */
    /* JADX WARN: Removed duplicated region for block: B:446:0x18a6  */
    /* JADX WARN: Removed duplicated region for block: B:447:0x181e  */
    /* JADX WARN: Removed duplicated region for block: B:448:0x1708  */
    /* JADX WARN: Removed duplicated region for block: B:449:0x164f  */
    /* JADX WARN: Removed duplicated region for block: B:450:0x162d  */
    /* JADX WARN: Removed duplicated region for block: B:452:0x1193  */
    /* JADX WARN: Removed duplicated region for block: B:455:0x1121  */
    /* JADX WARN: Removed duplicated region for block: B:458:0x1110  */
    /* JADX WARN: Removed duplicated region for block: B:459:0x103b  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0d20  */
    /* JADX WARN: Removed duplicated region for block: B:484:0x0bf5  */
    /* JADX WARN: Removed duplicated region for block: B:491:0x0b32  */
    /* JADX WARN: Removed duplicated region for block: B:492:0x0a4e  */
    /* JADX WARN: Removed duplicated region for block: B:493:0x0967  */
    /* JADX WARN: Removed duplicated region for block: B:495:0x08ac  */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0749  */
    /* JADX WARN: Removed duplicated region for block: B:498:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0595  */
    /* JADX WARN: Removed duplicated region for block: B:507:0x04ed  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:513:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0414  */
    /* JADX WARN: Removed duplicated region for block: B:515:0x03d6  */
    /* JADX WARN: Removed duplicated region for block: B:516:0x039e  */
    /* JADX WARN: Removed duplicated region for block: B:518:0x034a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:522:0x02e2  */
    /* JADX WARN: Removed duplicated region for block: B:526:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:529:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:531:0x01ab  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02d6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0383  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x03f7  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x043c  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0485  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x049e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x04eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void DashScreen(DashViewModel vm, Composer $composer, final int $changed, final int i) {
        int i2;
        final DashViewModel vm2;
        State ui$delegate;
        final Context context;
        Object rememberedValue;
        Object rememberedValue2;
        int $dirty;
        final String[] essentialPermissions;
        Object rememberedValue3;
        State previewFrame$delegate;
        String[] requestedPermissions;
        boolean changed;
        Object rememberedValue4;
        String[] requestedPermissions2;
        Object rememberedValue5;
        boolean hasBackgroundLocation;
        final MutableState batteryOk$delegate;
        boolean changedInstance;
        final ManagedActivityResultLauncher batteryLauncher;
        boolean changedInstance2;
        String str;
        String[] essentialPermissions2;
        final MutableState batteryOk$delegate2;
        ManagedActivityResultLauncher batteryLauncher2;
        Object rememberedValue6;
        ManagedActivityResultLauncher permissionLauncher;
        Object rememberedValue7;
        Object rememberedValue8;
        MutableState adjustMode$delegate;
        boolean changed2;
        MutableState joystickVelocity$delegate;
        boolean z;
        final Context context2;
        final MutableState adjustMode$delegate2;
        String[] essentialPermissions3;
        MutableState joystickVelocity$delegate2;
        DashScreenKt$DashScreen$2$1 dashScreenKt$DashScreen$2$1;
        Object rememberedValue9;
        boolean changed3;
        State autoConnectOnStart$delegate;
        final State ui$delegate2;
        String[] essentialPermissions4;
        Object obj;
        boolean changedInstance3;
        State uiState;
        State uiState2;
        Function0 function0;
        boolean streaming;
        DashViewModel vm3;
        String[] essentialPermissions5;
        Function0 function02;
        Function0 function03;
        Bitmap DashScreen$lambda$1;
        String maneuver;
        MutableState joystickVelocity$delegate3;
        Function0 function04;
        Function0 function05;
        Double remainingKm;
        Triple[] tripleArr;
        String str2;
        Integer etaMinutes;
        String str3;
        Iterator it;
        Function0 function06;
        Function0 function07;
        Function0 function08;
        Object rememberedValue10;
        Function0 function09;
        Function0 function010;
        Function0 function011;
        final DashViewModel vm4;
        boolean z2;
        boolean z3;
        Function0 function012;
        Function0 function013;
        Function0 function014;
        boolean z4;
        Function0 function015;
        Function0 function016;
        Object rememberedValue11;
        final MutableState joystickVelocity$delegate4;
        Function0 function017;
        boolean z5;
        Composer composer;
        boolean z6;
        Composer composer2;
        boolean z7;
        Composer composer3;
        Function0 function018;
        Function0 function019;
        Brush m5056radialGradientP_VxKs;
        List list;
        int i3;
        final DashViewModel vm5 = vm;
        Composer $composer2 = $composer.startRestartGroup(-1356596528);
        ComposerKt.sourceInformation($composer2, "C(DashScreen)N(vm)40@1676L16,41@1733L16,42@1781L7,43@1810L62,44@1933L16,48@2141L251,55@2424L233,67@2853L107,74@3216L122,82@3658L130,80@3560L228,104@4719L147,102@4617L249,110@4940L40,111@5003L33,112@5065L40,115@5234L490,115@5204L520,126@5775L383,126@5730L428,144@6671L25,144@6654L42,145@6746L195,145@6701L240,154@7152L24,155@7251L7,156@7296L748,156@7263L781,176@8141L21,173@8050L20563:DashScreen.kt#cef2n4");
        int $dirty2 = $changed;
        if (($changed & 6) == 0) {
            if ((i & 1) == 0) {
                if (($changed & 8) == 0 ? $composer2.changed(vm5) : $composer2.changedInstance(vm5)) {
                    i3 = 4;
                    $dirty2 |= i3;
                }
            }
            i3 = 2;
            $dirty2 |= i3;
        }
        if ($composer2.shouldExecute(($dirty2 & 3) != 2, $dirty2 & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "39@1641L11");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 1) != 0) {
                    $dirty2 &= -15;
                    vm2 = vm5;
                    i2 = 6;
                    $composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ui$delegate = SnapshotStateKt.collectAsState(vm2.getUi(), null, $composer2, 0, 1);
                    State previewFrame$delegate2 = SnapshotStateKt.collectAsState(vm2.getPreviewFrame(), null, $composer2, 0, 1);
                    ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object consume = $composer2.consume(localContext);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    context = (Context) consume;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980801550, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue = $composer2.rememberedValue();
                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    DashConfig dashConfig = (DashConfig) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    State autoConnectOnStart$delegate2 = SnapshotStateKt.collectAsState(dashConfig.getAutoConnectOnStart(), null, $composer2, 0, 1);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980812331, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue2 = $composer2.rememberedValue();
                    if (rememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    }
                    essentialPermissions = (String[]) rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980821369, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue3 = $composer2.rememberedValue();
                    if (rememberedValue3 != Composer.INSTANCE.getEmpty()) {
                    }
                    requestedPermissions = (String[]) rememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ConnStage stage = DashScreen$lambda$0(ui$delegate).getStage();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980834971, "CC(remember):DashScreen.kt#9igjgp");
                    changed = $composer2.changed(stage.ordinal());
                    rememberedValue4 = $composer2.rememberedValue();
                    if (changed) {
                    }
                    requestedPermissions2 = requestedPermissions;
                    Object valueOf = Boolean.valueOf(DeviceReadiness.INSTANCE.hasBackgroundLocation(context));
                    $composer2.updateRememberedValue(valueOf);
                    rememberedValue4 = valueOf;
                    boolean hasBackgroundLocation2 = ((Boolean) rememberedValue4).booleanValue();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980846602, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue5 = $composer2.rememberedValue();
                    if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                    }
                    batteryOk$delegate = (MutableState) rememberedValue5;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ActivityResultContracts.StartActivityForResult startActivityForResult = new ActivityResultContracts.StartActivityForResult();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980860754, "CC(remember):DashScreen.kt#9igjgp");
                    changedInstance = $composer2.changedInstance(context) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                    Object rememberedValue12 = $composer2.rememberedValue();
                    if (changedInstance) {
                    }
                    Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return DashScreenKt.DashScreen$lambda$14$lambda$13(context, vm2, batteryOk$delegate, (ActivityResult) obj3);
                        }
                    };
                    $composer2.updateRememberedValue(obj2);
                    rememberedValue12 = obj2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    batteryLauncher = ActivityResultRegistryKt.rememberLauncherForActivityResult(startActivityForResult, (Function1) rememberedValue12, $composer2, 0);
                    ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions = new ActivityResultContracts.RequestMultiplePermissions();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980894723, "CC(remember):DashScreen.kt#9igjgp");
                    changedInstance2 = $composer2.changedInstance(essentialPermissions) | $composer2.changedInstance(context) | $composer2.changedInstance(batteryLauncher) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                    Object rememberedValue13 = $composer2.rememberedValue();
                    if (!changedInstance2) {
                    }
                    str = "CC(remember):DashScreen.kt#9igjgp";
                    Object obj3 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj4) {
                            return DashScreenKt.DashScreen$lambda$18$lambda$17(essentialPermissions, context, vm2, batteryOk$delegate, batteryLauncher, (Map) obj4);
                        }
                    };
                    essentialPermissions2 = essentialPermissions;
                    batteryOk$delegate2 = batteryOk$delegate;
                    batteryLauncher2 = batteryLauncher;
                    $composer2.updateRememberedValue(obj3);
                    rememberedValue13 = obj3;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ManagedActivityResultLauncher permissionLauncher2 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions, (Function1) rememberedValue13, $composer2, 0);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980901688, str);
                    rememberedValue6 = $composer2.rememberedValue();
                    if (rememberedValue6 != Composer.INSTANCE.getEmpty()) {
                    }
                    final MutableState pan$delegate = (MutableState) rememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980903697, str);
                    rememberedValue7 = $composer2.rememberedValue();
                    if (rememberedValue7 != Composer.INSTANCE.getEmpty()) {
                    }
                    MutableState adjustMode$delegate3 = (MutableState) rememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980905688, str);
                    rememberedValue8 = $composer2.rememberedValue();
                    if (rememberedValue8 != Composer.INSTANCE.getEmpty()) {
                    }
                    MutableState joystickVelocity$delegate5 = (MutableState) rememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    String lastButton = DashScreen$lambda$0(ui$delegate).getLastButton();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980911546, str);
                    changed2 = $composer2.changed(ui$delegate);
                    Object rememberedValue14 = $composer2.rememberedValue();
                    if (changed2) {
                    }
                    Object obj4 = (Function2) new DashScreenKt$DashScreen$1$1(ui$delegate, pan$delegate, null);
                    $composer2.updateRememberedValue(obj4);
                    rememberedValue14 = obj4;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(lastButton, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue14, $composer2, 0);
                    Offset m4838boximpl = Offset.m4838boximpl(DashScreen$lambda$26(joystickVelocity$delegate));
                    Boolean valueOf2 = Boolean.valueOf(DashScreen$lambda$23(adjustMode$delegate));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980928751, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue15 = $composer2.rememberedValue();
                    if (z) {
                    }
                    MutableState joystickVelocity$delegate6 = joystickVelocity$delegate;
                    DashViewModel vm6 = vm2;
                    context2 = context;
                    adjustMode$delegate2 = adjustMode$delegate;
                    essentialPermissions3 = essentialPermissions2;
                    joystickVelocity$delegate2 = joystickVelocity$delegate6;
                    vm2 = vm6;
                    dashScreenKt$DashScreen$2$1 = new DashScreenKt$DashScreen$2$1(vm6, adjustMode$delegate2, joystickVelocity$delegate6, pan$delegate, null);
                    $composer2.updateRememberedValue(dashScreenKt$DashScreen$2$1);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(m4838boximpl, valueOf2, (Function2) dashScreenKt$DashScreen$2$1, $composer2, 0);
                    if (DashScreen$lambda$0(ui$delegate).getStage() == ConnStage.STREAMING) {
                    }
                    Object[] objArr = new Object[0];
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980957065, str);
                    rememberedValue9 = $composer2.rememberedValue();
                    if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    MutableState autoConnectTried$delegate = (MutableState) RememberSaveableKt.rememberSaveable(objArr, (Function0) rememberedValue9, $composer2, 48);
                    Boolean valueOf3 = Boolean.valueOf(DashScreen$lambda$3(autoConnectOnStart$delegate2));
                    ConnStage stage2 = DashScreen$lambda$0(ui$delegate).getStage();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980959635, str);
                    String[] essentialPermissions6 = essentialPermissions3;
                    changed3 = (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changed(autoConnectOnStart$delegate2) | $composer2.changed(autoConnectTried$delegate) | $composer2.changed(ui$delegate) | $composer2.changedInstance(essentialPermissions3) | $composer2.changedInstance(context2);
                    Object rememberedValue16 = $composer2.rememberedValue();
                    if (changed3) {
                    }
                    Object dashScreenKt$DashScreen$3$1 = new DashScreenKt$DashScreen$3$1(vm2, autoConnectOnStart$delegate, autoConnectTried$delegate, ui$delegate, essentialPermissions6, context2, null);
                    ui$delegate2 = ui$delegate;
                    essentialPermissions4 = essentialPermissions6;
                    obj = (Function2) dashScreenKt$DashScreen$3$1;
                    $composer2.updateRememberedValue(obj);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(valueOf3, stage2, (Function2) obj, $composer2, 0);
                    State uiState3 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                    ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object consume2 = $composer2.consume(localLifecycleOwner);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    final LifecycleOwner lifecycleOwner = (LifecycleOwner) consume2;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                    changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState3) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner);
                    final String[] essentialPermissions7 = essentialPermissions4;
                    Object rememberedValue17 = $composer2.rememberedValue();
                    if (changedInstance3) {
                    }
                    final Context context3 = context2;
                    final DashViewModel vm7 = vm2;
                    final MutableState batteryOk$delegate3 = batteryOk$delegate2;
                    final State uiState4 = uiState;
                    Object obj5 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj6) {
                            return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context3, uiState4, vm7, batteryOk$delegate3, essentialPermissions7, (DisposableEffectScope) obj6);
                        }
                    };
                    uiState2 = uiState4;
                    essentialPermissions7 = essentialPermissions7;
                    rememberedValue17 = obj5;
                    $composer2.updateRememberedValue(rememberedValue17);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.DisposableEffect(lifecycleOwner, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue17, $composer2, 0);
                    Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap = $composer2.getCurrentCompositionLocalMap();
                    final DashViewModel vm8 = vm2;
                    Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default);
                    Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
                    int i4 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl, columnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl, Integer.valueOf(hashCode), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl, materializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5 = (i4 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    int i6 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                    streaming = streaming;
                    final State ui$delegate3 = ui$delegate2;
                    WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj6, Object obj7) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming, ui$delegate2, (Composer) obj6, ((Integer) obj7).intValue());
                        }
                    }, $composer2, 54), $composer2, 1576368, 49);
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Alignment center = Alignment.INSTANCE.getCenter();
                    Modifier m818paddingVpY3zN4$default = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default);
                    Function0 constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    int i7 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
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
                    int i9 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Modifier m273borderxT4_qwU = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier3 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU);
                    Function0 constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    int i10 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl3 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl3, maybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl3, Integer.valueOf(hashCode3), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl3, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl3, materializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    int i11 = (i10 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    int i12 = ((0 >> 6) & 112) | 6;
                    Composer composer4 = $composer2;
                    ComposerKt.sourceInformationMarkerStart(composer4, 2024465622, "C:DashScreen.kt#cef2n4");
                    DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                    if (DashScreen$lambda$1 != null) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                    maneuver = DashScreen$lambda$0(ui$delegate3).getManeuver();
                    if (maneuver == null) {
                    }
                    Arrangement.Horizontal m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier = Modifier.INSTANCE;
                    Alignment.Vertical top = Alignment.INSTANCE.getTop();
                    MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m686spacedBy0680j_4, top, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier4 = ComposedModifierKt.materializeModifier($composer2, modifier);
                    Function0 constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    int i13 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl4 = Updater.m4364constructorimpl($composer2);
                    MeasurePolicy measurePolicy = rowMeasurePolicy;
                    Updater.m4372setimpl(m4364constructorimpl4, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl4, Integer.valueOf(hashCode4), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl4, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl4, materializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                    int i14 = (i13 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i15 = ((48 >> 6) & 112) | 6;
                    RowScope rowScope = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                    $composer2.startReplaceGroup(-2140347244);
                    ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                    Triple[] tripleArr2 = new Triple[3];
                    remainingKm = DashScreen$lambda$0(ui$delegate3).getRemainingKm();
                    if (remainingKm != null) {
                    }
                    str2 = "—";
                    tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate3).getRemainingKm() != null ? "km" : "", "Kalan");
                    etaMinutes = DashScreen$lambda$0(ui$delegate3).getEtaMinutes();
                    if (etaMinutes != null) {
                    }
                    str3 = "—";
                    Integer etaMinutes2 = DashScreen$lambda$0(ui$delegate3).getEtaMinutes();
                    tripleArr[1] = new Triple(str3, etaMinutes2 != null ? etaMinutes2.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                    tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate3).getMapZoom(), "", "Yakınlaştırma");
                    Iterable listOf = CollectionsKt.listOf((Object[]) tripleArr);
                    int i16 = 0;
                    it = listOf.iterator();
                    while (it.hasNext()) {
                    }
                    DashViewModel vm9 = vm3;
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN4 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier5 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4);
                    Function0 constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                    int i17 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl5 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl5, rowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl5, Integer.valueOf(hashCode5), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl5, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl5, materializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    int i18 = (i17 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    int i19 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier2 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier6 = ComposedModifierKt.materializeModifier($composer2, modifier2);
                    Function0 constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                    int i20 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl6 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl6, rowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl6, Integer.valueOf(hashCode6), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl6, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl6, materializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    int i21 = (i20 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    int i22 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier3 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap7 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier7 = ComposedModifierKt.materializeModifier($composer2, modifier3);
                    Function0 constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                    int i23 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl7 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl7, columnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl7, Integer.valueOf(hashCode7), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl7, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl7, materializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                    int i24 = (i23 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    int i25 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean DashScreen$lambda$23 = DashScreen$lambda$23(adjustMode$delegate2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                    rememberedValue10 = $composer2.rememberedValue();
                    if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$23, (Function1) rememberedValue10, $composer2, 48);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween2 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN42 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy4 = RowKt.rowMeasurePolicy(spaceBetween2, centerVertically3, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap8 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier8 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN42);
                    Function0 constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                    int i26 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl8 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl8, rowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl8, Integer.valueOf(hashCode8), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl8, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl8, materializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                    int i27 = (i26 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    int i28 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier4 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap9 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier9 = ComposedModifierKt.materializeModifier($composer2, modifier4);
                    Function0 constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                    int i29 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl9 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl9, rowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl9, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl9, Integer.valueOf(hashCode9), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl9, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl9, materializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                    int i30 = (i29 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                    int i31 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate3).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier5 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap10 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier10 = ComposedModifierKt.materializeModifier($composer2, modifier5);
                    Function0 constructor10 = ComposeUiNode.INSTANCE.getConstructor();
                    int i32 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl10 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl10, columnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl10, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl10, Integer.valueOf(hashCode10), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl10, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl10, materializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                    int i33 = (i32 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    int i34 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate3).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean headingUp = DashScreen$lambda$0(ui$delegate3).getHeadingUp();
                    ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                    if ((($dirty & 14) ^ 6) > 4) {
                    }
                    if (($dirty & 6) != 4) {
                    }
                    z2 = true;
                    z3 = z2;
                    Object rememberedValue18 = $composer2.rememberedValue();
                    if (z3) {
                    }
                    rememberedValue18 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj6) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj6).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue18);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(headingUp, (Function1) rememberedValue18, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically5 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween3 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN43 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy6 = RowKt.rowMeasurePolicy(spaceBetween3, centerVertically5, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap11 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier11 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN43);
                    Function0 constructor11 = ComposeUiNode.INSTANCE.getConstructor();
                    int i35 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl11 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl11, rowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl11, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl11, Integer.valueOf(hashCode11), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl11, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl11, materializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                    int i36 = (i35 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                    int i37 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically6 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier6 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy7 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically6, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap12 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier12 = ComposedModifierKt.materializeModifier($composer2, modifier6);
                    Function0 constructor12 = ComposeUiNode.INSTANCE.getConstructor();
                    int i38 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl12 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl12, rowMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl12, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl12, Integer.valueOf(hashCode12), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl12, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl12, materializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                    int i39 = (i38 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                    int i40 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate3).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier7 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap13 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier13 = ComposedModifierKt.materializeModifier($composer2, modifier7);
                    Function0 constructor13 = ComposeUiNode.INSTANCE.getConstructor();
                    int i41 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl13 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl13, columnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl13, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl13, Integer.valueOf(hashCode13), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl13, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl13, materializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
                    int i42 = (i41 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                    int i43 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate3).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate3).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean mapDark = DashScreen$lambda$0(ui$delegate3).getMapDark();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue19 = $composer2.rememberedValue();
                    if (z4) {
                    }
                    Object obj6 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj7) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj7).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(obj6);
                    rememberedValue19 = obj6;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(mapDark, (Function1) rememberedValue19, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically7 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal m686spacedBy0680j_42 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                    Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy8 = RowKt.rowMeasurePolicy(m686spacedBy0680j_42, centerVertically7, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap14 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier14 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default);
                    Function0 constructor14 = ComposeUiNode.INSTANCE.getConstructor();
                    int i44 = ((((438 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl14 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl14, rowMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl14, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl14, Integer.valueOf(hashCode14), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl14, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl14, materializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
                    int i45 = (i44 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i46 = ((438 >> 6) & 112) | 6;
                    RowScope rowScope2 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                    Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                    Modifier m817paddingVpY3zN44 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope2, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap15 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier15 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN44);
                    Function0 constructor15 = ComposeUiNode.INSTANCE.getConstructor();
                    int i47 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl15 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl15, columnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl15, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl15, Integer.valueOf(hashCode15), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl15, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl15, materializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
                    int i48 = (i47 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                    int i49 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                    float m7908constructorimpl = Dp.m7908constructorimpl(128);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                    rememberedValue11 = $composer2.rememberedValue();
                    if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl, (Function1) rememberedValue11, null, $composer2, 54, 4);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                    WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.Vertical m686spacedBy0680j_43 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier8 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_43, centerHorizontally2, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap16 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier16 = ComposedModifierKt.materializeModifier($composer2, modifier8);
                    Function0 constructor16 = ComposeUiNode.INSTANCE.getConstructor();
                    int i50 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl16 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl16, columnMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl16, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl16, Integer.valueOf(hashCode16), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl16, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl16, materializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
                    int i51 = (i50 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                    int i52 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                    ImageVector plus = WayfindrIcons.INSTANCE.getPlus();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                    z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue20 = $composer2.rememberedValue();
                    if (z5) {
                    }
                    rememberedValue20 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue20);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus, (Function0) rememberedValue20, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                    Composer composer5 = composer;
                    TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate3).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer5, 1597440, 0, 261930);
                    ImageVector minus = WayfindrIcons.INSTANCE.getMinus();
                    ComposerKt.sourceInformationMarkerStart(composer5, 2044399839, str);
                    z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue21 = composer5.rememberedValue();
                    if (z6) {
                    }
                    rememberedValue21 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                        }
                    };
                    composer5.updateRememberedValue(rememberedValue21);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus, (Function0) rememberedValue21, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                    Composer composer6 = composer2;
                    ImageVector recenter = WayfindrIcons.INSTANCE.getRecenter();
                    ComposerKt.sourceInformationMarkerStart(composer6, 2044402995, str);
                    z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue22 = composer6.rememberedValue();
                    if (z7) {
                    }
                    rememberedValue22 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate);
                        }
                    };
                    composer6.updateRememberedValue(rememberedValue22);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter, (Function0) rememberedValue22, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (streaming) {
                    }
                    $composer2.startReplaceGroup(-1925928760);
                    $composer2.endReplaceGroup();
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    vm5 = vm4;
                } else {
                    i2 = 6;
                    vm2 = vm5;
                    $composer2.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    ui$delegate = SnapshotStateKt.collectAsState(vm2.getUi(), null, $composer2, 0, 1);
                    State previewFrame$delegate22 = SnapshotStateKt.collectAsState(vm2.getPreviewFrame(), null, $composer2, 0, 1);
                    ProvidableCompositionLocal<Context> localContext2 = AndroidCompositionLocals_androidKt.getLocalContext();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object consume3 = $composer2.consume(localContext2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    context = (Context) consume3;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980801550, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue = $composer2.rememberedValue();
                    if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    }
                    DashConfig dashConfig2 = (DashConfig) rememberedValue;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    State autoConnectOnStart$delegate22 = SnapshotStateKt.collectAsState(dashConfig2.getAutoConnectOnStart(), null, $composer2, 0, 1);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980812331, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue2 = $composer2.rememberedValue();
                    if (rememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    }
                    essentialPermissions = (String[]) rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980821369, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue3 = $composer2.rememberedValue();
                    if (rememberedValue3 != Composer.INSTANCE.getEmpty()) {
                    }
                    requestedPermissions = (String[]) rememberedValue3;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ConnStage stage3 = DashScreen$lambda$0(ui$delegate).getStage();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980834971, "CC(remember):DashScreen.kt#9igjgp");
                    changed = $composer2.changed(stage3.ordinal());
                    rememberedValue4 = $composer2.rememberedValue();
                    if (changed) {
                    }
                    requestedPermissions2 = requestedPermissions;
                    Object valueOf4 = Boolean.valueOf(DeviceReadiness.INSTANCE.hasBackgroundLocation(context));
                    $composer2.updateRememberedValue(valueOf4);
                    rememberedValue4 = valueOf4;
                    boolean hasBackgroundLocation22 = ((Boolean) rememberedValue4).booleanValue();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980846602, "CC(remember):DashScreen.kt#9igjgp");
                    rememberedValue5 = $composer2.rememberedValue();
                    if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                    }
                    batteryOk$delegate = (MutableState) rememberedValue5;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ActivityResultContracts.StartActivityForResult startActivityForResult2 = new ActivityResultContracts.StartActivityForResult();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980860754, "CC(remember):DashScreen.kt#9igjgp");
                    changedInstance = $composer2.changedInstance(context) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                    Object rememberedValue122 = $composer2.rememberedValue();
                    if (changedInstance) {
                    }
                    Object obj22 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj32) {
                            return DashScreenKt.DashScreen$lambda$14$lambda$13(context, vm2, batteryOk$delegate, (ActivityResult) obj32);
                        }
                    };
                    $composer2.updateRememberedValue(obj22);
                    rememberedValue122 = obj22;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    batteryLauncher = ActivityResultRegistryKt.rememberLauncherForActivityResult(startActivityForResult2, (Function1) rememberedValue122, $composer2, 0);
                    ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions2 = new ActivityResultContracts.RequestMultiplePermissions();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980894723, "CC(remember):DashScreen.kt#9igjgp");
                    changedInstance2 = $composer2.changedInstance(essentialPermissions) | $composer2.changedInstance(context) | $composer2.changedInstance(batteryLauncher) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                    Object rememberedValue132 = $composer2.rememberedValue();
                    if (!changedInstance2) {
                    }
                    str = "CC(remember):DashScreen.kt#9igjgp";
                    Object obj32 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda8
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj42) {
                            return DashScreenKt.DashScreen$lambda$18$lambda$17(essentialPermissions, context, vm2, batteryOk$delegate, batteryLauncher, (Map) obj42);
                        }
                    };
                    essentialPermissions2 = essentialPermissions;
                    batteryOk$delegate2 = batteryOk$delegate;
                    batteryLauncher2 = batteryLauncher;
                    $composer2.updateRememberedValue(obj32);
                    rememberedValue132 = obj32;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ManagedActivityResultLauncher permissionLauncher22 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions2, (Function1) rememberedValue132, $composer2, 0);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980901688, str);
                    rememberedValue6 = $composer2.rememberedValue();
                    if (rememberedValue6 != Composer.INSTANCE.getEmpty()) {
                    }
                    final MutableState pan$delegate2 = (MutableState) rememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980903697, str);
                    rememberedValue7 = $composer2.rememberedValue();
                    if (rememberedValue7 != Composer.INSTANCE.getEmpty()) {
                    }
                    MutableState adjustMode$delegate32 = (MutableState) rememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980905688, str);
                    rememberedValue8 = $composer2.rememberedValue();
                    if (rememberedValue8 != Composer.INSTANCE.getEmpty()) {
                    }
                    MutableState joystickVelocity$delegate52 = (MutableState) rememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    String lastButton2 = DashScreen$lambda$0(ui$delegate).getLastButton();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980911546, str);
                    changed2 = $composer2.changed(ui$delegate);
                    Object rememberedValue142 = $composer2.rememberedValue();
                    if (changed2) {
                    }
                    Object obj42 = (Function2) new DashScreenKt$DashScreen$1$1(ui$delegate, pan$delegate2, null);
                    $composer2.updateRememberedValue(obj42);
                    rememberedValue142 = obj42;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(lastButton2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue142, $composer2, 0);
                    Offset m4838boximpl2 = Offset.m4838boximpl(DashScreen$lambda$26(joystickVelocity$delegate));
                    Boolean valueOf22 = Boolean.valueOf(DashScreen$lambda$23(adjustMode$delegate));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980928751, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue152 = $composer2.rememberedValue();
                    if (z) {
                    }
                    MutableState joystickVelocity$delegate62 = joystickVelocity$delegate;
                    DashViewModel vm62 = vm2;
                    context2 = context;
                    adjustMode$delegate2 = adjustMode$delegate;
                    essentialPermissions3 = essentialPermissions2;
                    joystickVelocity$delegate2 = joystickVelocity$delegate62;
                    vm2 = vm62;
                    dashScreenKt$DashScreen$2$1 = new DashScreenKt$DashScreen$2$1(vm62, adjustMode$delegate2, joystickVelocity$delegate62, pan$delegate2, null);
                    $composer2.updateRememberedValue(dashScreenKt$DashScreen$2$1);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(m4838boximpl2, valueOf22, (Function2) dashScreenKt$DashScreen$2$1, $composer2, 0);
                    if (DashScreen$lambda$0(ui$delegate).getStage() == ConnStage.STREAMING) {
                    }
                    Object[] objArr2 = new Object[0];
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980957065, str);
                    rememberedValue9 = $composer2.rememberedValue();
                    if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    MutableState autoConnectTried$delegate2 = (MutableState) RememberSaveableKt.rememberSaveable(objArr2, (Function0) rememberedValue9, $composer2, 48);
                    Boolean valueOf32 = Boolean.valueOf(DashScreen$lambda$3(autoConnectOnStart$delegate22));
                    ConnStage stage22 = DashScreen$lambda$0(ui$delegate).getStage();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980959635, str);
                    String[] essentialPermissions62 = essentialPermissions3;
                    changed3 = (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changed(autoConnectOnStart$delegate22) | $composer2.changed(autoConnectTried$delegate2) | $composer2.changed(ui$delegate) | $composer2.changedInstance(essentialPermissions3) | $composer2.changedInstance(context2);
                    Object rememberedValue162 = $composer2.rememberedValue();
                    if (changed3) {
                    }
                    Object dashScreenKt$DashScreen$3$12 = new DashScreenKt$DashScreen$3$1(vm2, autoConnectOnStart$delegate, autoConnectTried$delegate2, ui$delegate, essentialPermissions62, context2, null);
                    ui$delegate2 = ui$delegate;
                    essentialPermissions4 = essentialPermissions62;
                    obj = (Function2) dashScreenKt$DashScreen$3$12;
                    $composer2.updateRememberedValue(obj);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(valueOf32, stage22, (Function2) obj, $composer2, 0);
                    State uiState32 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                    ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner2 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object consume22 = $composer2.consume(localLifecycleOwner2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    final LifecycleOwner lifecycleOwner2 = (LifecycleOwner) consume22;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                    changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState32) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner2);
                    final String[] essentialPermissions72 = essentialPermissions4;
                    Object rememberedValue172 = $composer2.rememberedValue();
                    if (changedInstance3) {
                    }
                    final Context context32 = context2;
                    final DashViewModel vm72 = vm2;
                    final MutableState batteryOk$delegate32 = batteryOk$delegate2;
                    final State uiState42 = uiState;
                    Object obj52 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj62) {
                            return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context32, uiState42, vm72, batteryOk$delegate32, essentialPermissions72, (DisposableEffectScope) obj62);
                        }
                    };
                    uiState2 = uiState42;
                    essentialPermissions72 = essentialPermissions72;
                    rememberedValue172 = obj52;
                    $composer2.updateRememberedValue(rememberedValue172);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.DisposableEffect(lifecycleOwner2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue172, $composer2, 0);
                    Modifier m820paddingqDBjuR0$default2 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy7 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode17 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap17 = $composer2.getCurrentCompositionLocalMap();
                    final DashViewModel vm82 = vm2;
                    Modifier materializeModifier17 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default2);
                    Function0 constructor17 = ComposeUiNode.INSTANCE.getConstructor();
                    int i410 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl17 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl17, columnMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl17, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl17, Integer.valueOf(hashCode17), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl17, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl17, materializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
                    int i53 = (i410 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                    int i62 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                    streaming = streaming;
                    final State ui$delegate32 = ui$delegate2;
                    WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj62, Object obj7) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming, ui$delegate2, (Composer) obj62, ((Integer) obj7).intValue());
                        }
                    }, $composer2, 54), $composer2, 1576368, 49);
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Alignment center2 = Alignment.INSTANCE.getCenter();
                    Modifier m818paddingVpY3zN4$default2 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(center2, false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode22 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap22 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier22 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default2);
                    Function0 constructor22 = ComposeUiNode.INSTANCE.getConstructor();
                    int i72 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl22 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl22, maybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl22, currentCompositionLocalMap22, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl22, Integer.valueOf(hashCode22), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl22, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl22, materializeModifier22, ComposeUiNode.INSTANCE.getSetModifier());
                    int i82 = (i72 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    int i92 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Modifier m273borderxT4_qwU2 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy22 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode32 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap32 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier32 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU2);
                    Function0 constructor32 = ComposeUiNode.INSTANCE.getConstructor();
                    int i102 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl32 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl32, maybeCachedBoxMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl32, currentCompositionLocalMap32, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl32, Integer.valueOf(hashCode32), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl32, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl32, materializeModifier32, ComposeUiNode.INSTANCE.getSetModifier());
                    int i112 = (i102 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance22 = BoxScopeInstance.INSTANCE;
                    int i122 = ((0 >> 6) & 112) | 6;
                    Composer composer42 = $composer2;
                    ComposerKt.sourceInformationMarkerStart(composer42, 2024465622, "C:DashScreen.kt#cef2n4");
                    DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                    if (DashScreen$lambda$1 != null) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer42);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                    maneuver = DashScreen$lambda$0(ui$delegate32).getManeuver();
                    if (maneuver == null) {
                    }
                    Arrangement.Horizontal m686spacedBy0680j_44 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier9 = Modifier.INSTANCE;
                    Alignment.Vertical top2 = Alignment.INSTANCE.getTop();
                    MeasurePolicy rowMeasurePolicy9 = RowKt.rowMeasurePolicy(m686spacedBy0680j_44, top2, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode42 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap42 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier42 = ComposedModifierKt.materializeModifier($composer2, modifier9);
                    Function0 constructor42 = ComposeUiNode.INSTANCE.getConstructor();
                    int i132 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl42 = Updater.m4364constructorimpl($composer2);
                    MeasurePolicy measurePolicy2 = rowMeasurePolicy9;
                    Updater.m4372setimpl(m4364constructorimpl42, measurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl42, currentCompositionLocalMap42, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl42, Integer.valueOf(hashCode42), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl42, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl42, materializeModifier42, ComposeUiNode.INSTANCE.getSetModifier());
                    int i142 = (i132 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i152 = ((48 >> 6) & 112) | 6;
                    RowScope rowScope3 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                    $composer2.startReplaceGroup(-2140347244);
                    ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                    Triple[] tripleArr22 = new Triple[3];
                    remainingKm = DashScreen$lambda$0(ui$delegate32).getRemainingKm();
                    if (remainingKm != null) {
                    }
                    str2 = "—";
                    tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate32).getRemainingKm() != null ? "km" : "", "Kalan");
                    etaMinutes = DashScreen$lambda$0(ui$delegate32).getEtaMinutes();
                    if (etaMinutes != null) {
                    }
                    str3 = "—";
                    Integer etaMinutes22 = DashScreen$lambda$0(ui$delegate32).getEtaMinutes();
                    tripleArr[1] = new Triple(str3, etaMinutes22 != null ? etaMinutes22.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                    tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate32).getMapZoom(), "", "Yakınlaştırma");
                    Iterable listOf2 = CollectionsKt.listOf((Object[]) tripleArr);
                    int i162 = 0;
                    it = listOf2.iterator();
                    while (it.hasNext()) {
                    }
                    DashViewModel vm92 = vm3;
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically8 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween4 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN45 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy22 = RowKt.rowMeasurePolicy(spaceBetween4, centerVertically8, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode52 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap52 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier52 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN45);
                    Function0 constructor52 = ComposeUiNode.INSTANCE.getConstructor();
                    int i172 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl52 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl52, rowMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl52, currentCompositionLocalMap52, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl52, Integer.valueOf(hashCode52), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl52, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl52, materializeModifier52, ComposeUiNode.INSTANCE.getSetModifier());
                    int i182 = (i172 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
                    int i192 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically22 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier22 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy32 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically22, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode62 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap62 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier62 = ComposedModifierKt.materializeModifier($composer2, modifier22);
                    Function0 constructor62 = ComposeUiNode.INSTANCE.getConstructor();
                    int i202 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl62 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl62, rowMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl62, currentCompositionLocalMap62, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl62, Integer.valueOf(hashCode62), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl62, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl62, materializeModifier62, ComposeUiNode.INSTANCE.getSetModifier());
                    int i212 = (i202 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance22 = RowScopeInstance.INSTANCE;
                    int i222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier32 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy22 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode72 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap72 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier72 = ComposedModifierKt.materializeModifier($composer2, modifier32);
                    Function0 constructor72 = ComposeUiNode.INSTANCE.getConstructor();
                    int i232 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl72 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl72, columnMeasurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl72, currentCompositionLocalMap72, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl72, Integer.valueOf(hashCode72), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl72, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl72, materializeModifier72, ComposeUiNode.INSTANCE.getSetModifier());
                    int i242 = (i232 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance22 = ColumnScopeInstance.INSTANCE;
                    int i252 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean DashScreen$lambda$232 = DashScreen$lambda$23(adjustMode$delegate2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                    rememberedValue10 = $composer2.rememberedValue();
                    if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$232, (Function1) rememberedValue10, $composer2, 48);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically32 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween22 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN422 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy42 = RowKt.rowMeasurePolicy(spaceBetween22, centerVertically32, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode82 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap82 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier82 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN422);
                    Function0 constructor82 = ComposeUiNode.INSTANCE.getConstructor();
                    int i262 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl82 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl82, rowMeasurePolicy42, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl82, currentCompositionLocalMap82, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl82, Integer.valueOf(hashCode82), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl82, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl82, materializeModifier82, ComposeUiNode.INSTANCE.getSetModifier());
                    int i272 = (i262 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance32 = RowScopeInstance.INSTANCE;
                    int i282 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically42 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier42 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy52 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically42, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode92 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap92 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier92 = ComposedModifierKt.materializeModifier($composer2, modifier42);
                    Function0 constructor92 = ComposeUiNode.INSTANCE.getConstructor();
                    int i292 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl92 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl92, rowMeasurePolicy52, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl92, currentCompositionLocalMap92, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl92, Integer.valueOf(hashCode92), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl92, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl92, materializeModifier92, ComposeUiNode.INSTANCE.getSetModifier());
                    int i302 = (i292 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance42 = RowScopeInstance.INSTANCE;
                    int i312 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate32).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier52 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy32 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode102 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap102 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier102 = ComposedModifierKt.materializeModifier($composer2, modifier52);
                    Function0 constructor102 = ComposeUiNode.INSTANCE.getConstructor();
                    int i322 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl102 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl102, columnMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl102, currentCompositionLocalMap102, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl102, Integer.valueOf(hashCode102), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl102, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl102, materializeModifier102, ComposeUiNode.INSTANCE.getSetModifier());
                    int i332 = (i322 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance32 = ColumnScopeInstance.INSTANCE;
                    int i342 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate32).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean headingUp2 = DashScreen$lambda$0(ui$delegate32).getHeadingUp();
                    ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                    if ((($dirty & 14) ^ 6) > 4) {
                    }
                    if (($dirty & 6) != 4) {
                    }
                    z2 = true;
                    z3 = z2;
                    Object rememberedValue182 = $composer2.rememberedValue();
                    if (z3) {
                    }
                    rememberedValue182 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj62) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj62).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue182);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(headingUp2, (Function1) rememberedValue182, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically52 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween32 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN432 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy62 = RowKt.rowMeasurePolicy(spaceBetween32, centerVertically52, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode112 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap112 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier112 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN432);
                    Function0 constructor112 = ComposeUiNode.INSTANCE.getConstructor();
                    int i352 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl112 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl112, rowMeasurePolicy62, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl112, currentCompositionLocalMap112, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl112, Integer.valueOf(hashCode112), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl112, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl112, materializeModifier112, ComposeUiNode.INSTANCE.getSetModifier());
                    int i362 = (i352 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance52 = RowScopeInstance.INSTANCE;
                    int i372 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically62 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier62 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy72 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically62, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode122 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap122 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier122 = ComposedModifierKt.materializeModifier($composer2, modifier62);
                    Function0 constructor122 = ComposeUiNode.INSTANCE.getConstructor();
                    int i382 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl122 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl122, rowMeasurePolicy72, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl122, currentCompositionLocalMap122, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl122, Integer.valueOf(hashCode122), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl122, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl122, materializeModifier122, ComposeUiNode.INSTANCE.getSetModifier());
                    int i392 = (i382 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance62 = RowScopeInstance.INSTANCE;
                    int i402 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate32).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier72 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy42 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode132 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap132 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier132 = ComposedModifierKt.materializeModifier($composer2, modifier72);
                    Function0 constructor132 = ComposeUiNode.INSTANCE.getConstructor();
                    int i412 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl132 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl132, columnMeasurePolicy42, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl132, currentCompositionLocalMap132, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl132, Integer.valueOf(hashCode132), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl132, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl132, materializeModifier132, ComposeUiNode.INSTANCE.getSetModifier());
                    int i422 = (i412 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance42 = ColumnScopeInstance.INSTANCE;
                    int i432 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate32).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate32).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean mapDark2 = DashScreen$lambda$0(ui$delegate32).getMapDark();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue192 = $composer2.rememberedValue();
                    if (z4) {
                    }
                    Object obj62 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj7) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj7).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(obj62);
                    rememberedValue192 = obj62;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(mapDark2, (Function1) rememberedValue192, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically72 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal m686spacedBy0680j_422 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                    Modifier fillMaxWidth$default2 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy82 = RowKt.rowMeasurePolicy(m686spacedBy0680j_422, centerVertically72, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode142 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap142 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier142 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default2);
                    Function0 constructor142 = ComposeUiNode.INSTANCE.getConstructor();
                    int i442 = ((((438 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl142 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl142, rowMeasurePolicy82, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl142, currentCompositionLocalMap142, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl142, Integer.valueOf(hashCode142), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl142, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl142, materializeModifier142, ComposeUiNode.INSTANCE.getSetModifier());
                    int i452 = (i442 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i462 = ((438 >> 6) & 112) | 6;
                    RowScope rowScope22 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                    Alignment.Horizontal centerHorizontally3 = Alignment.INSTANCE.getCenterHorizontally();
                    Modifier m817paddingVpY3zN442 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope22, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy52 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally3, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode152 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap152 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier152 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN442);
                    Function0 constructor152 = ComposeUiNode.INSTANCE.getConstructor();
                    int i472 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl152 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl152, columnMeasurePolicy52, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl152, currentCompositionLocalMap152, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl152, Integer.valueOf(hashCode152), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl152, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl152, materializeModifier152, ComposeUiNode.INSTANCE.getSetModifier());
                    int i482 = (i472 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance52 = ColumnScopeInstance.INSTANCE;
                    int i492 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                    float m7908constructorimpl2 = Dp.m7908constructorimpl(128);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                    rememberedValue11 = $composer2.rememberedValue();
                    if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl2, (Function1) rememberedValue11, null, $composer2, 54, 4);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                    WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Alignment.Horizontal centerHorizontally22 = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.Vertical m686spacedBy0680j_432 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier82 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy62 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_432, centerHorizontally22, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode162 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap162 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier162 = ComposedModifierKt.materializeModifier($composer2, modifier82);
                    Function0 constructor162 = ComposeUiNode.INSTANCE.getConstructor();
                    int i502 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl162 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl162, columnMeasurePolicy62, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl162, currentCompositionLocalMap162, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl162, Integer.valueOf(hashCode162), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl162, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl162, materializeModifier162, ComposeUiNode.INSTANCE.getSetModifier());
                    int i512 = (i502 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance62 = ColumnScopeInstance.INSTANCE;
                    int i522 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                    ImageVector plus2 = WayfindrIcons.INSTANCE.getPlus();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                    z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue202 = $composer2.rememberedValue();
                    if (z5) {
                    }
                    rememberedValue202 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue202);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus2, (Function0) rememberedValue202, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                    Composer composer52 = composer;
                    TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate32).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer52, 1597440, 0, 261930);
                    ImageVector minus2 = WayfindrIcons.INSTANCE.getMinus();
                    ComposerKt.sourceInformationMarkerStart(composer52, 2044399839, str);
                    z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue212 = composer52.rememberedValue();
                    if (z6) {
                    }
                    rememberedValue212 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                        }
                    };
                    composer52.updateRememberedValue(rememberedValue212);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus2, (Function0) rememberedValue212, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                    Composer composer62 = composer2;
                    ImageVector recenter2 = WayfindrIcons.INSTANCE.getRecenter();
                    ComposerKt.sourceInformationMarkerStart(composer62, 2044402995, str);
                    z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue222 = composer62.rememberedValue();
                    if (z7) {
                    }
                    rememberedValue222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate2);
                        }
                    };
                    composer62.updateRememberedValue(rememberedValue222);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter2, (Function0) rememberedValue222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (streaming) {
                    }
                    $composer2.startReplaceGroup(-1925928760);
                    $composer2.endReplaceGroup();
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    vm5 = vm4;
                }
            } else if ((i & 1) != 0) {
                $composer2.startReplaceableGroup(1729797275);
                ComposerKt.sourceInformation($composer2, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
                ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer2, 6);
                if (current == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
                }
                i2 = 6;
                ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(DashViewModel.class), current, (String) null, (ViewModelProvider.Factory) null, current instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, $composer2, ((0 << 3) & 7168) | ((0 << 3) & 112) | ((0 << 3) & 896) | ((0 << 3) & 57344), 0);
                $composer2.endReplaceableGroup();
                DashViewModel vm10 = (DashViewModel) viewModel;
                $dirty2 &= -15;
                vm2 = vm10;
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1356596528, $dirty2, -1, "com.example.wayfindr.ui.screens.DashScreen (DashScreen.kt:39)");
                }
                ui$delegate = SnapshotStateKt.collectAsState(vm2.getUi(), null, $composer2, 0, 1);
                State previewFrame$delegate222 = SnapshotStateKt.collectAsState(vm2.getPreviewFrame(), null, $composer2, 0, 1);
                ProvidableCompositionLocal<Context> localContext22 = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume32 = $composer2.consume(localContext22);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                context = (Context) consume32;
                ComposerKt.sourceInformationMarkerStart($composer2, 1980801550, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue = $composer2.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj7 = DashConfig.INSTANCE.get(context);
                    $composer2.updateRememberedValue(obj7);
                    rememberedValue = obj7;
                }
                DashConfig dashConfig22 = (DashConfig) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                State autoConnectOnStart$delegate222 = SnapshotStateKt.collectAsState(dashConfig22.getAutoConnectOnStart(), null, $composer2, 0, 1);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980812331, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue2 = $composer2.rememberedValue();
                if (rememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    List createListBuilder = CollectionsKt.createListBuilder();
                    $dirty = $dirty2;
                    if (Build.VERSION.SDK_INT >= 33) {
                        list = createListBuilder;
                        list.add("android.permission.NEARBY_WIFI_DEVICES");
                    } else {
                        list = createListBuilder;
                    }
                    list.add("android.permission.ACCESS_FINE_LOCATION");
                    Object obj8 = (String[]) CollectionsKt.build(createListBuilder).toArray(new String[0]);
                    $composer2.updateRememberedValue(obj8);
                    rememberedValue2 = obj8;
                } else {
                    $dirty = $dirty2;
                }
                essentialPermissions = (String[]) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980821369, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue3 = $composer2.rememberedValue();
                if (rememberedValue3 != Composer.INSTANCE.getEmpty()) {
                    List createListBuilder2 = CollectionsKt.createListBuilder();
                    CollectionsKt.addAll(createListBuilder2, essentialPermissions);
                    previewFrame$delegate = previewFrame$delegate222;
                    if (Build.VERSION.SDK_INT >= 33) {
                        createListBuilder2.add("android.permission.POST_NOTIFICATIONS");
                    }
                    Object obj9 = (String[]) CollectionsKt.build(createListBuilder2).toArray(new String[0]);
                    $composer2.updateRememberedValue(obj9);
                    rememberedValue3 = obj9;
                } else {
                    previewFrame$delegate = previewFrame$delegate222;
                }
                requestedPermissions = (String[]) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ConnStage stage32 = DashScreen$lambda$0(ui$delegate).getStage();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980834971, "CC(remember):DashScreen.kt#9igjgp");
                changed = $composer2.changed(stage32.ordinal());
                rememberedValue4 = $composer2.rememberedValue();
                if (!changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    requestedPermissions2 = requestedPermissions;
                    Object valueOf42 = Boolean.valueOf(DeviceReadiness.INSTANCE.hasBackgroundLocation(context));
                    $composer2.updateRememberedValue(valueOf42);
                    rememberedValue4 = valueOf42;
                } else {
                    requestedPermissions2 = requestedPermissions;
                }
                boolean hasBackgroundLocation222 = ((Boolean) rememberedValue4).booleanValue();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980846602, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue5 = $composer2.rememberedValue();
                if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                    hasBackgroundLocation = hasBackgroundLocation222;
                    Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(DeviceReadiness.INSTANCE.isIgnoringBatteryOptimizations(context)), null, 2, null);
                    $composer2.updateRememberedValue(mutableStateOf$default);
                    rememberedValue5 = mutableStateOf$default;
                } else {
                    hasBackgroundLocation = hasBackgroundLocation222;
                }
                batteryOk$delegate = (MutableState) rememberedValue5;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ActivityResultContracts.StartActivityForResult startActivityForResult22 = new ActivityResultContracts.StartActivityForResult();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980860754, "CC(remember):DashScreen.kt#9igjgp");
                changedInstance = $composer2.changedInstance(context) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                Object rememberedValue1222 = $composer2.rememberedValue();
                if (changedInstance) {
                }
                Object obj222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj322) {
                        return DashScreenKt.DashScreen$lambda$14$lambda$13(context, vm2, batteryOk$delegate, (ActivityResult) obj322);
                    }
                };
                $composer2.updateRememberedValue(obj222);
                rememberedValue1222 = obj222;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                batteryLauncher = ActivityResultRegistryKt.rememberLauncherForActivityResult(startActivityForResult22, (Function1) rememberedValue1222, $composer2, 0);
                ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions22 = new ActivityResultContracts.RequestMultiplePermissions();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980894723, "CC(remember):DashScreen.kt#9igjgp");
                changedInstance2 = $composer2.changedInstance(essentialPermissions) | $composer2.changedInstance(context) | $composer2.changedInstance(batteryLauncher) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                Object rememberedValue1322 = $composer2.rememberedValue();
                if (!changedInstance2 && rememberedValue1322 != Composer.INSTANCE.getEmpty()) {
                    str = "CC(remember):DashScreen.kt#9igjgp";
                    essentialPermissions2 = essentialPermissions;
                    batteryOk$delegate2 = batteryOk$delegate;
                    batteryLauncher2 = batteryLauncher;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ManagedActivityResultLauncher permissionLauncher222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions22, (Function1) rememberedValue1322, $composer2, 0);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980901688, str);
                    rememberedValue6 = $composer2.rememberedValue();
                    if (rememberedValue6 != Composer.INSTANCE.getEmpty()) {
                        permissionLauncher = permissionLauncher222;
                        Object mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4838boximpl(Offset.INSTANCE.m4865getZeroF1C5BW0()), null, 2, null);
                        $composer2.updateRememberedValue(mutableStateOf$default2);
                        rememberedValue6 = mutableStateOf$default2;
                    } else {
                        permissionLauncher = permissionLauncher222;
                    }
                    final MutableState pan$delegate22 = (MutableState) rememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980903697, str);
                    rememberedValue7 = $composer2.rememberedValue();
                    if (rememberedValue7 != Composer.INSTANCE.getEmpty()) {
                        Object mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(true, null, 2, null);
                        $composer2.updateRememberedValue(mutableStateOf$default3);
                        rememberedValue7 = mutableStateOf$default3;
                    }
                    MutableState adjustMode$delegate322 = (MutableState) rememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980905688, str);
                    rememberedValue8 = $composer2.rememberedValue();
                    if (rememberedValue8 != Composer.INSTANCE.getEmpty()) {
                        adjustMode$delegate = adjustMode$delegate322;
                        Object mutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Offset.m4838boximpl(Offset.INSTANCE.m4865getZeroF1C5BW0()), null, 2, null);
                        $composer2.updateRememberedValue(mutableStateOf$default4);
                        rememberedValue8 = mutableStateOf$default4;
                    } else {
                        adjustMode$delegate = adjustMode$delegate322;
                    }
                    MutableState joystickVelocity$delegate522 = (MutableState) rememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    String lastButton22 = DashScreen$lambda$0(ui$delegate).getLastButton();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980911546, str);
                    changed2 = $composer2.changed(ui$delegate);
                    Object rememberedValue1422 = $composer2.rememberedValue();
                    if (changed2) {
                        joystickVelocity$delegate = joystickVelocity$delegate522;
                        if (rememberedValue1422 != Composer.INSTANCE.getEmpty()) {
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            EffectsKt.LaunchedEffect(lastButton22, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue1422, $composer2, 0);
                            Offset m4838boximpl22 = Offset.m4838boximpl(DashScreen$lambda$26(joystickVelocity$delegate));
                            Boolean valueOf222 = Boolean.valueOf(DashScreen$lambda$23(adjustMode$delegate));
                            ComposerKt.sourceInformationMarkerStart($composer2, 1980928751, str);
                            z = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4;
                            Object rememberedValue1522 = $composer2.rememberedValue();
                            if (!z || rememberedValue1522 == Composer.INSTANCE.getEmpty()) {
                                MutableState joystickVelocity$delegate622 = joystickVelocity$delegate;
                                DashViewModel vm622 = vm2;
                                context2 = context;
                                adjustMode$delegate2 = adjustMode$delegate;
                                essentialPermissions3 = essentialPermissions2;
                                joystickVelocity$delegate2 = joystickVelocity$delegate622;
                                vm2 = vm622;
                                dashScreenKt$DashScreen$2$1 = new DashScreenKt$DashScreen$2$1(vm622, adjustMode$delegate2, joystickVelocity$delegate622, pan$delegate22, null);
                                $composer2.updateRememberedValue(dashScreenKt$DashScreen$2$1);
                            } else {
                                dashScreenKt$DashScreen$2$1 = rememberedValue1522;
                                context2 = context;
                                joystickVelocity$delegate2 = joystickVelocity$delegate;
                                adjustMode$delegate2 = adjustMode$delegate;
                                essentialPermissions3 = essentialPermissions2;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            EffectsKt.LaunchedEffect(m4838boximpl22, valueOf222, (Function2) dashScreenKt$DashScreen$2$1, $composer2, 0);
                            final boolean streaming2 = DashScreen$lambda$0(ui$delegate).getStage() == ConnStage.STREAMING;
                            Object[] objArr22 = new Object[0];
                            ComposerKt.sourceInformationMarkerStart($composer2, 1980957065, str);
                            rememberedValue9 = $composer2.rememberedValue();
                            if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                                Object obj10 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda9
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        MutableState mutableStateOf$default5;
                                        mutableStateOf$default5 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                                        return mutableStateOf$default5;
                                    }
                                };
                                $composer2.updateRememberedValue(obj10);
                                rememberedValue9 = obj10;
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            MutableState autoConnectTried$delegate22 = (MutableState) RememberSaveableKt.rememberSaveable(objArr22, (Function0) rememberedValue9, $composer2, 48);
                            Boolean valueOf322 = Boolean.valueOf(DashScreen$lambda$3(autoConnectOnStart$delegate222));
                            ConnStage stage222 = DashScreen$lambda$0(ui$delegate).getStage();
                            ComposerKt.sourceInformationMarkerStart($composer2, 1980959635, str);
                            String[] essentialPermissions622 = essentialPermissions3;
                            changed3 = (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changed(autoConnectOnStart$delegate222) | $composer2.changed(autoConnectTried$delegate22) | $composer2.changed(ui$delegate) | $composer2.changedInstance(essentialPermissions3) | $composer2.changedInstance(context2);
                            Object rememberedValue1622 = $composer2.rememberedValue();
                            if (changed3) {
                                autoConnectOnStart$delegate = autoConnectOnStart$delegate222;
                            } else {
                                autoConnectOnStart$delegate = autoConnectOnStart$delegate222;
                                if (rememberedValue1622 != Composer.INSTANCE.getEmpty()) {
                                    obj = rememberedValue1622;
                                    essentialPermissions4 = essentialPermissions622;
                                    ui$delegate2 = ui$delegate;
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    EffectsKt.LaunchedEffect(valueOf322, stage222, (Function2) obj, $composer2, 0);
                                    State uiState322 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                                    ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner22 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                                    Object consume222 = $composer2.consume(localLifecycleOwner22);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    final LifecycleOwner lifecycleOwner22 = (LifecycleOwner) consume222;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                                    changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState322) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner22);
                                    final String[] essentialPermissions722 = essentialPermissions4;
                                    Object rememberedValue1722 = $composer2.rememberedValue();
                                    if (changedInstance3) {
                                        uiState = uiState322;
                                        if (rememberedValue1722 != Composer.INSTANCE.getEmpty()) {
                                            uiState2 = uiState;
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            EffectsKt.DisposableEffect(lifecycleOwner22, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue1722, $composer2, 0);
                                            Modifier m820paddingqDBjuR0$default22 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            MeasurePolicy columnMeasurePolicy72 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode172 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap172 = $composer2.getCurrentCompositionLocalMap();
                                            final DashViewModel vm822 = vm2;
                                            Modifier materializeModifier172 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default22);
                                            Function0 constructor172 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i4102 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function0 = constructor172;
                                                $composer2.createNode(function0);
                                            } else {
                                                function0 = constructor172;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl172 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl172, columnMeasurePolicy72, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl172, currentCompositionLocalMap172, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl172, Integer.valueOf(hashCode172), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl172, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl172, materializeModifier172, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i532 = (i4102 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance72 = ColumnScopeInstance.INSTANCE;
                                            int i622 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                                            streaming = streaming2;
                                            final State ui$delegate322 = ui$delegate2;
                                            WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(Object obj622, Object obj72) {
                                                    return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming2, ui$delegate2, (Composer) obj622, ((Integer) obj72).intValue());
                                                }
                                            }, $composer2, 54), $composer2, 1576368, 49);
                                            if (streaming) {
                                                vm3 = vm822;
                                                essentialPermissions5 = essentialPermissions722;
                                                $composer2.startReplaceGroup(-1925928760);
                                            } else {
                                                $composer2.startReplaceGroup(-1915501228);
                                                ComposerKt.sourceInformation($composer2, "248@11993L4696,248@11920L4769");
                                                if (hasBackgroundLocation) {
                                                    $composer2.startReplaceGroup(-1925928760);
                                                } else {
                                                    $composer2.startReplaceGroup(-1915609418);
                                                    ComposerKt.sourceInformation($composer2, "221@10515L1378,218@10371L1522");
                                                    WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(14), 7, null), true, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(224574377, true, new Function3() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda12
                                                        @Override // kotlin.jvm.functions.Function3
                                                        public final Object invoke(Object obj11, Object obj12, Object obj13) {
                                                            return DashScreenKt.DashScreen$lambda$96$lambda$45(context2, (ColumnScope) obj11, (Composer) obj12, ((Integer) obj13).intValue());
                                                        }
                                                    }, $composer2, 54), $composer2, 24630, 12);
                                                }
                                                $composer2.endReplaceGroup();
                                                Modifier m820paddingqDBjuR0$default3 = PaddingKt.m820paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(14), 7, null);
                                                final Context context4 = context2;
                                                final String[] requestedPermissions3 = requestedPermissions2;
                                                final ManagedActivityResultLauncher batteryLauncher3 = batteryLauncher2;
                                                final ManagedActivityResultLauncher permissionLauncher3 = permissionLauncher;
                                                Function3 function3 = new Function3() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda13
                                                    @Override // kotlin.jvm.functions.Function3
                                                    public final Object invoke(Object obj11, Object obj12, Object obj13) {
                                                        return DashScreenKt.DashScreen$lambda$96$lambda$54(State.this, context4, essentialPermissions722, batteryLauncher3, vm822, permissionLauncher3, requestedPermissions3, batteryOk$delegate2, (ColumnScope) obj11, (Composer) obj12, ((Integer) obj13).intValue());
                                                    }
                                                };
                                                essentialPermissions5 = essentialPermissions722;
                                                vm3 = vm822;
                                                ui$delegate322 = ui$delegate322;
                                                WayfindrComponentsKt.m8357WayfindrCardTN_CM5M(m820paddingqDBjuR0$default3, false, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-111876380, true, function3, $composer2, 54), $composer2, 24582, 14);
                                            }
                                            $composer2.endReplaceGroup();
                                            Alignment center22 = Alignment.INSTANCE.getCenter();
                                            Modifier m818paddingVpY3zN4$default22 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                            MeasurePolicy maybeCachedBoxMeasurePolicy32 = BoxKt.maybeCachedBoxMeasurePolicy(center22, false);
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap222 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier222 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default22);
                                            Function0 constructor222 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i722 = ((((54 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function02 = constructor222;
                                                $composer2.createNode(function02);
                                            } else {
                                                function02 = constructor222;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl222 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl222, maybeCachedBoxMeasurePolicy32, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl222, currentCompositionLocalMap222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl222, Integer.valueOf(hashCode222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl222, materializeModifier222, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i822 = (i722 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance32 = BoxScopeInstance.INSTANCE;
                                            int i922 = ((54 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                                            if (streaming) {
                                                $composer2.startReplaceGroup(1395304391);
                                                ComposerKt.sourceInformation($composer2, "339@16943L263");
                                                Modifier clip = ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(300)), RoundedCornerShapeKt.RoundedCornerShape(50));
                                                m5056radialGradientP_VxKs = Brush.INSTANCE.m5056radialGradientP_VxKs((List<Color>) CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(com.example.wayfindr.ui.theme.ColorKt.getGoldGlow()), Color.m5084boximpl(Color.INSTANCE.m5129getTransparent0d7_KjU())}), (r12 & 2) != 0 ? Offset.INSTANCE.m4864getUnspecifiedF1C5BW0() : 0L, (r12 & 4) != 0 ? Float.POSITIVE_INFINITY : 200.0f, (r12 & 8) != 0 ? TileMode.INSTANCE.m5489getClamp3opZhB0() : 0);
                                                BoxKt.Box(BackgroundKt.background$default(clip, m5056radialGradientP_VxKs, null, 0.0f, 6, null), $composer2, 0);
                                            } else {
                                                $composer2.startReplaceGroup(1378504654);
                                            }
                                            $composer2.endReplaceGroup();
                                            Modifier m273borderxT4_qwU22 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                            MeasurePolicy maybeCachedBoxMeasurePolicy222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode322 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap322 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier322 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU22);
                                            Function0 constructor322 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i1022 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function03 = constructor322;
                                                $composer2.createNode(function03);
                                            } else {
                                                function03 = constructor322;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl322 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl322, maybeCachedBoxMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl322, currentCompositionLocalMap322, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl322, Integer.valueOf(hashCode322), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl322, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl322, materializeModifier322, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i1122 = (i1022 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                            BoxScopeInstance boxScopeInstance222 = BoxScopeInstance.INSTANCE;
                                            int i1222 = ((0 >> 6) & 112) | 6;
                                            Composer composer422 = $composer2;
                                            ComposerKt.sourceInformationMarkerStart(composer422, 2024465622, "C:DashScreen.kt#cef2n4");
                                            DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                                            if (DashScreen$lambda$1 != null) {
                                                composer422.startReplaceGroup(2024752929);
                                                ComposerKt.sourceInformation(composer422, "361@18167L303");
                                                ImageKt.m319Image5hnEew(AndroidImageBitmap_androidKt.asImageBitmap(DashScreen$lambda$1), "Canlı konsol görüntüsü", SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), null, ContentScale.INSTANCE.getCrop(), 0.0f, null, 0, composer422, 25008, 232);
                                                composer422 = composer422;
                                                composer422.endReplaceGroup();
                                            } else {
                                                composer422.startReplaceGroup(2025145048);
                                                ComposerKt.sourceInformation(composer422, "368@18516L916");
                                                Double camLat = DashScreen$lambda$0(ui$delegate322).getCamLat();
                                                if (camLat == null) {
                                                    camLat = DashScreen$lambda$0(ui$delegate322).getRiderLat();
                                                }
                                                Double d = camLat;
                                                Double camLng = DashScreen$lambda$0(ui$delegate322).getCamLng();
                                                if (camLng == null) {
                                                    camLng = DashScreen$lambda$0(ui$delegate322).getRiderLng();
                                                }
                                                FreeWayfindrMapKt.WayfindrMap(d, camLng, DashScreen$lambda$0(ui$delegate322).getDestLatLng(), null, DashScreen$lambda$0(ui$delegate322).getRoutePoints(), DashScreen$lambda$0(ui$delegate322).getRouteTraffic(), null, 0, DashScreen$hasEssentialPermissions(essentialPermissions5, context2), SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), false, DashScreen$lambda$0(ui$delegate322).getHeadingUp(), DashScreen$lambda$0(ui$delegate322).getRiderBearing(), 0L, false, false, DashScreen$lambda$0(ui$delegate322).getCamLat(), DashScreen$lambda$0(ui$delegate322).getCamLng(), Float.valueOf(DashScreen$lambda$0(ui$delegate322).getMapZoom()), Float.valueOf(DashScreen$lambda$0(ui$delegate322).getCamBearing()), false, null, composer422, 805306368, 0, 0, 3204296);
                                                composer422.endReplaceGroup();
                                            }
                                            ComposerKt.sourceInformationMarkerEnd(composer422);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                                            maneuver = DashScreen$lambda$0(ui$delegate322).getManeuver();
                                            if (maneuver == null) {
                                                $composer2.startReplaceGroup(-1906445850);
                                                $composer2.endReplaceGroup();
                                                joystickVelocity$delegate3 = joystickVelocity$delegate2;
                                            } else {
                                                $composer2.startReplaceGroup(-1906445849);
                                                ComposerKt.sourceInformation($composer2, "*393@19633L884,409@20530L30");
                                                Alignment.Vertical centerVertically9 = Alignment.INSTANCE.getCenterVertically();
                                                joystickVelocity$delegate3 = joystickVelocity$delegate2;
                                                Modifier m817paddingVpY3zN46 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(13))), DashScreen$lambda$0(ui$delegate322).getOffRoute() ? ColorKt.Color(869827902) : com.example.wayfindr.ui.theme.ColorKt.getGoldTint(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$0(ui$delegate322).getOffRoute() ? com.example.wayfindr.ui.theme.ColorKt.getWarn() : com.example.wayfindr.ui.theme.ColorKt.getGoldTint2(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(13))), Dp.m7908constructorimpl(14), Dp.m7908constructorimpl(11));
                                                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                                MeasurePolicy rowMeasurePolicy10 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically9, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode18 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap18 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier18 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN46);
                                                Function0 constructor18 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i54 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function04 = constructor18;
                                                    $composer2.createNode(function04);
                                                } else {
                                                    function04 = constructor18;
                                                    $composer2.useNode();
                                                }
                                                Composer m4364constructorimpl18 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl18, rowMeasurePolicy10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl18, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl18, Integer.valueOf(hashCode18), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl18, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl18, materializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i55 = (i54 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                                RowScopeInstance rowScopeInstance8 = RowScopeInstance.INSTANCE;
                                                int i56 = ((384 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -430172324, "C402@20108L103,403@20228L29,404@20274L229:DashScreen.kt#cef2n4");
                                                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate322).getOffRoute() ? com.example.wayfindr.ui.theme.ColorKt.getWarn() : com.example.wayfindr.ui.theme.ColorKt.getGold(), $composer2, 432, 0);
                                                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                                TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate322).getOffRoute() ? "Rotanın dışındasınız — yeniden rota oluşturuluyor…" : turkishManeuver(maneuver), null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(13.5d), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597440, 0, 262058);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                                                Unit unit = Unit.INSTANCE;
                                                $composer2.endReplaceGroup();
                                                Unit unit2 = Unit.INSTANCE;
                                            }
                                            Arrangement.Horizontal m686spacedBy0680j_442 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            Modifier modifier92 = Modifier.INSTANCE;
                                            Alignment.Vertical top22 = Alignment.INSTANCE.getTop();
                                            MeasurePolicy rowMeasurePolicy92 = RowKt.rowMeasurePolicy(m686spacedBy0680j_442, top22, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode422 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap422 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier422 = ComposedModifierKt.materializeModifier($composer2, modifier92);
                                            Function0 constructor422 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i1322 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function05 = constructor422;
                                                $composer2.createNode(function05);
                                            } else {
                                                function05 = constructor422;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl422 = Updater.m4364constructorimpl($composer2);
                                            MeasurePolicy measurePolicy22 = rowMeasurePolicy92;
                                            Updater.m4372setimpl(m4364constructorimpl422, measurePolicy22, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl422, currentCompositionLocalMap422, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl422, Integer.valueOf(hashCode422), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl422, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl422, materializeModifier422, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i1422 = (i1322 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            int i1522 = ((48 >> 6) & 112) | 6;
                                            RowScope rowScope32 = RowScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                                            $composer2.startReplaceGroup(-2140347244);
                                            ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                                            Triple[] tripleArr222 = new Triple[3];
                                            remainingKm = DashScreen$lambda$0(ui$delegate322).getRemainingKm();
                                            if (remainingKm != null) {
                                                double doubleValue = remainingKm.doubleValue();
                                                if (doubleValue >= 10.0d) {
                                                    tripleArr = tripleArr222;
                                                    str2 = String.format("%.0f", Arrays.copyOf(new Object[]{Double.valueOf(doubleValue)}, 1));
                                                } else {
                                                    tripleArr = tripleArr222;
                                                    str2 = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(doubleValue)}, 1));
                                                }
                                                Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                                            } else {
                                                tripleArr = tripleArr222;
                                            }
                                            str2 = "—";
                                            tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate322).getRemainingKm() != null ? "km" : "", "Kalan");
                                            etaMinutes = DashScreen$lambda$0(ui$delegate322).getEtaMinutes();
                                            if (etaMinutes != null) {
                                                int intValue = etaMinutes.intValue();
                                                str3 = intValue >= 60 ? (intValue / 60) + " sa " + (intValue % 60) + " dk" : String.valueOf(intValue);
                                            }
                                            str3 = "—";
                                            Integer etaMinutes222 = DashScreen$lambda$0(ui$delegate322).getEtaMinutes();
                                            tripleArr[1] = new Triple(str3, etaMinutes222 != null ? etaMinutes222.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                                            tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate322).getMapZoom(), "", "Yakınlaştırma");
                                            Iterable listOf22 = CollectionsKt.listOf((Object[]) tripleArr);
                                            int i1622 = 0;
                                            it = listOf22.iterator();
                                            while (it.hasNext()) {
                                                Triple triple = (Triple) it.next();
                                                String str4 = (String) triple.component1();
                                                String str5 = (String) triple.component2();
                                                Iterable iterable = listOf22;
                                                String str6 = (String) triple.component3();
                                                Alignment.Horizontal centerHorizontally4 = Alignment.INSTANCE.getCenterHorizontally();
                                                int i57 = i1622;
                                                Iterator it2 = it;
                                                MeasurePolicy measurePolicy3 = measurePolicy22;
                                                Alignment.Vertical vertical = top22;
                                                DashViewModel vm11 = vm3;
                                                Modifier m816padding3ABfNKs = PaddingKt.m816padding3ABfNKs(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope32, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(13))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(13))), Dp.m7908constructorimpl(11));
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                                MeasurePolicy columnMeasurePolicy8 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally4, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode19 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap19 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier19 = ComposedModifierKt.materializeModifier($composer2, m816padding3ABfNKs);
                                                Function0 constructor19 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i58 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function018 = constructor19;
                                                    $composer2.createNode(function018);
                                                } else {
                                                    function018 = constructor19;
                                                    $composer2.useNode();
                                                }
                                                Composer m4364constructorimpl19 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl19, columnMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl19, currentCompositionLocalMap19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl19, Integer.valueOf(hashCode19), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl19, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl19, materializeModifier19, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i59 = (i58 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
                                                int i60 = ((384 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -16649451, "C434@21710L514,441@22245L40:DashScreen.kt#cef2n4");
                                                Alignment.Vertical bottom = Alignment.INSTANCE.getBottom();
                                                Arrangement.Horizontal center3 = Arrangement.INSTANCE.getCenter();
                                                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                                Modifier modifier10 = Modifier.INSTANCE;
                                                MeasurePolicy rowMeasurePolicy11 = RowKt.rowMeasurePolicy(center3, bottom, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode20 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap20 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier20 = ComposedModifierKt.materializeModifier($composer2, modifier10);
                                                Function0 constructor20 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i61 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function019 = constructor20;
                                                    $composer2.createNode(function019);
                                                } else {
                                                    function019 = constructor20;
                                                    $composer2.useNode();
                                                }
                                                Composer m4364constructorimpl20 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl20, rowMeasurePolicy11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl20, currentCompositionLocalMap20, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl20, Integer.valueOf(hashCode20), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl20, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl20, materializeModifier20, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i63 = (i61 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                                RowScopeInstance rowScopeInstance9 = RowScopeInstance.INSTANCE;
                                                int i64 = ((432 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1093650964, "C435@21822L105:DashScreen.kt#cef2n4");
                                                TextKt.m3069TextNvy7gAk(str4, null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(18), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597440, 0, 261930);
                                                if (str5.length() > 0) {
                                                    $composer2.startReplaceGroup(1093795113);
                                                    ComposerKt.sourceInformation($composer2, "437@22002L28,438@22059L117");
                                                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(3)), $composer2, 6);
                                                    TextKt.m3069TextNvy7gAk(str5, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(2), 7, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(10.5d), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 261992);
                                                } else {
                                                    $composer2.startReplaceGroup(1071990767);
                                                }
                                                $composer2.endReplaceGroup();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                WayfindrComponentsKt.Eyebrow(str6, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(3), 0.0f, 0.0f, 13, null), $composer2, 48, 0);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                it = it2;
                                                measurePolicy22 = measurePolicy3;
                                                top22 = vertical;
                                                listOf22 = iterable;
                                                i1622 = i57;
                                                vm3 = vm11;
                                            }
                                            DashViewModel vm922 = vm3;
                                            $composer2.endReplaceGroup();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                                            Alignment.Vertical centerVertically82 = Alignment.INSTANCE.getCenterVertically();
                                            Arrangement.Horizontal spaceBetween42 = Arrangement.INSTANCE.getSpaceBetween();
                                            Modifier m817paddingVpY3zN452 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            MeasurePolicy rowMeasurePolicy222 = RowKt.rowMeasurePolicy(spaceBetween42, centerVertically82, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode522 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap522 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier522 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN452);
                                            Function0 constructor522 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i1722 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function06 = constructor522;
                                                $composer2.createNode(function06);
                                            } else {
                                                function06 = constructor522;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl522 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl522, rowMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl522, currentCompositionLocalMap522, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl522, Integer.valueOf(hashCode522), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl522, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl522, materializeModifier522, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i1822 = (i1722 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance72 = RowScopeInstance.INSTANCE;
                                            int i1922 = ((432 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                                            Alignment.Vertical centerVertically222 = Alignment.INSTANCE.getCenterVertically();
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            Modifier modifier222 = Modifier.INSTANCE;
                                            MeasurePolicy rowMeasurePolicy322 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode622 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap622 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier622 = ComposedModifierKt.materializeModifier($composer2, modifier222);
                                            Function0 constructor622 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i2022 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function07 = constructor622;
                                                $composer2.createNode(function07);
                                            } else {
                                                function07 = constructor622;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl622 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl622, rowMeasurePolicy322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl622, currentCompositionLocalMap622, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl622, Integer.valueOf(hashCode622), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl622, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl622, materializeModifier622, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i2122 = (i2022 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance222 = RowScopeInstance.INSTANCE;
                                            int i2222 = ((384 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            Modifier modifier322 = Modifier.INSTANCE;
                                            MeasurePolicy columnMeasurePolicy222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode722 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap722 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier722 = ComposedModifierKt.materializeModifier($composer2, modifier322);
                                            Function0 constructor722 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i2322 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function08 = constructor722;
                                                $composer2.createNode(function08);
                                            } else {
                                                function08 = constructor722;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl722 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl722, columnMeasurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl722, currentCompositionLocalMap722, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl722, Integer.valueOf(hashCode722), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl722, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl722, materializeModifier722, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i2422 = (i2322 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance222 = ColumnScopeInstance.INSTANCE;
                                            int i2522 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                                            TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                                            TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            boolean DashScreen$lambda$2322 = DashScreen$lambda$23(adjustMode$delegate2);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                                            rememberedValue10 = $composer2.rememberedValue();
                                            if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                                                Object obj11 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda14
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj12) {
                                                        return DashScreenKt.DashScreen$lambda$96$lambda$70$lambda$69$lambda$68(MutableState.this, ((Boolean) obj12).booleanValue());
                                                    }
                                                };
                                                $composer2.updateRememberedValue(obj11);
                                                rememberedValue10 = obj11;
                                            }
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$2322, (Function1) rememberedValue10, $composer2, 48);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                                            Alignment.Vertical centerVertically322 = Alignment.INSTANCE.getCenterVertically();
                                            Arrangement.Horizontal spaceBetween222 = Arrangement.INSTANCE.getSpaceBetween();
                                            Modifier m817paddingVpY3zN4222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            MeasurePolicy rowMeasurePolicy422 = RowKt.rowMeasurePolicy(spaceBetween222, centerVertically322, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode822 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap822 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier822 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4222);
                                            Function0 constructor822 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i2622 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function09 = constructor822;
                                                $composer2.createNode(function09);
                                            } else {
                                                function09 = constructor822;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl822 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl822, rowMeasurePolicy422, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl822, currentCompositionLocalMap822, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl822, Integer.valueOf(hashCode822), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl822, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl822, materializeModifier822, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i2722 = (i2622 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance322 = RowScopeInstance.INSTANCE;
                                            int i2822 = ((432 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                                            Alignment.Vertical centerVertically422 = Alignment.INSTANCE.getCenterVertically();
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            Modifier modifier422 = Modifier.INSTANCE;
                                            MeasurePolicy rowMeasurePolicy522 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically422, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode922 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap922 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier922 = ComposedModifierKt.materializeModifier($composer2, modifier422);
                                            Function0 constructor922 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i2922 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function010 = constructor922;
                                                $composer2.createNode(function010);
                                            } else {
                                                function010 = constructor922;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl922 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl922, rowMeasurePolicy522, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl922, currentCompositionLocalMap922, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl922, Integer.valueOf(hashCode922), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl922, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl922, materializeModifier922, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i3022 = (i2922 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance422 = RowScopeInstance.INSTANCE;
                                            int i3122 = ((384 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate322).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            Modifier modifier522 = Modifier.INSTANCE;
                                            MeasurePolicy columnMeasurePolicy322 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode1022 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap1022 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier1022 = ComposedModifierKt.materializeModifier($composer2, modifier522);
                                            Function0 constructor1022 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i3222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                                function011 = constructor1022;
                                                $composer2.createNode(function011);
                                            } else {
                                                function011 = constructor1022;
                                                $composer2.useNode();
                                            }
                                            Composer m4364constructorimpl1022 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl1022, columnMeasurePolicy322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl1022, currentCompositionLocalMap1022, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl1022, Integer.valueOf(hashCode1022), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl1022, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl1022, materializeModifier1022, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i3322 = (i3222 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance322 = ColumnScopeInstance.INSTANCE;
                                            int i3422 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                                            TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                                            TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate322).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            boolean headingUp22 = DashScreen$lambda$0(ui$delegate322).getHeadingUp();
                                            ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                                            if ((($dirty & 14) ^ 6) > 4) {
                                                vm4 = vm922;
                                            } else {
                                                vm4 = vm922;
                                            }
                                            if (($dirty & 6) != 4) {
                                                z2 = false;
                                                z3 = z2;
                                                Object rememberedValue1822 = $composer2.rememberedValue();
                                                if (z3) {
                                                }
                                                rememberedValue1822 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj622) {
                                                        return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj622).booleanValue());
                                                    }
                                                };
                                                $composer2.updateRememberedValue(rememberedValue1822);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                WayfindrComponentsKt.WayfindrToggle(headingUp22, (Function1) rememberedValue1822, $composer2, 0);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                                                Alignment.Vertical centerVertically522 = Alignment.INSTANCE.getCenterVertically();
                                                Arrangement.Horizontal spaceBetween322 = Arrangement.INSTANCE.getSpaceBetween();
                                                Modifier m817paddingVpY3zN4322 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                                                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                                MeasurePolicy rowMeasurePolicy622 = RowKt.rowMeasurePolicy(spaceBetween322, centerVertically522, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode1122 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap1122 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier1122 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4322);
                                                Function0 constructor1122 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i3522 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function012 = constructor1122;
                                                    $composer2.useNode();
                                                } else {
                                                    function012 = constructor1122;
                                                    $composer2.createNode(function012);
                                                }
                                                Composer m4364constructorimpl1122 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl1122, rowMeasurePolicy622, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl1122, currentCompositionLocalMap1122, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl1122, Integer.valueOf(hashCode1122), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl1122, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl1122, materializeModifier1122, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i3622 = (i3522 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                                RowScopeInstance rowScopeInstance522 = RowScopeInstance.INSTANCE;
                                                int i3722 = ((432 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                                                Alignment.Vertical centerVertically622 = Alignment.INSTANCE.getCenterVertically();
                                                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                                Modifier modifier622 = Modifier.INSTANCE;
                                                MeasurePolicy rowMeasurePolicy722 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically622, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode1222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap1222 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier1222 = ComposedModifierKt.materializeModifier($composer2, modifier622);
                                                Function0 constructor1222 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i3822 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function013 = constructor1222;
                                                    $composer2.useNode();
                                                } else {
                                                    function013 = constructor1222;
                                                    $composer2.createNode(function013);
                                                }
                                                Composer m4364constructorimpl1222 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl1222, rowMeasurePolicy722, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl1222, currentCompositionLocalMap1222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl1222, Integer.valueOf(hashCode1222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl1222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl1222, materializeModifier1222, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i3922 = (i3822 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                                RowScopeInstance rowScopeInstance622 = RowScopeInstance.INSTANCE;
                                                int i4022 = ((384 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                                                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate322).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                                                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                                Modifier modifier722 = Modifier.INSTANCE;
                                                MeasurePolicy columnMeasurePolicy422 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode1322 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap1322 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier1322 = ComposedModifierKt.materializeModifier($composer2, modifier722);
                                                Function0 constructor1322 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i4122 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function014 = constructor1322;
                                                    $composer2.useNode();
                                                } else {
                                                    function014 = constructor1322;
                                                    $composer2.createNode(function014);
                                                }
                                                Composer m4364constructorimpl1322 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl1322, columnMeasurePolicy422, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl1322, currentCompositionLocalMap1322, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl1322, Integer.valueOf(hashCode1322), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl1322, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl1322, materializeModifier1322, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i4222 = (i4122 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance422 = ColumnScopeInstance.INSTANCE;
                                                int i4322 = ((0 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                                                TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                                                TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate322).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate322).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                boolean mapDark22 = DashScreen$lambda$0(ui$delegate322).getMapDark();
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                                                z4 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                                Object rememberedValue1922 = $composer2.rememberedValue();
                                                if (z4) {
                                                }
                                                Object obj622 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                                                    @Override // kotlin.jvm.functions.Function1
                                                    public final Object invoke(Object obj72) {
                                                        return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                                                    }
                                                };
                                                $composer2.updateRememberedValue(obj622);
                                                rememberedValue1922 = obj622;
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                WayfindrComponentsKt.WayfindrToggle(mapDark22, (Function1) rememberedValue1922, $composer2, 0);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                                                Alignment.Vertical centerVertically722 = Alignment.INSTANCE.getCenterVertically();
                                                Arrangement.Horizontal m686spacedBy0680j_4222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                                                Modifier fillMaxWidth$default22 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                                MeasurePolicy rowMeasurePolicy822 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4222, centerVertically722, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode1422 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap1422 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier1422 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default22);
                                                Function0 constructor1422 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i4422 = ((((438 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function015 = constructor1422;
                                                    $composer2.useNode();
                                                } else {
                                                    function015 = constructor1422;
                                                    $composer2.createNode(function015);
                                                }
                                                Composer m4364constructorimpl1422 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl1422, rowMeasurePolicy822, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl1422, currentCompositionLocalMap1422, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl1422, Integer.valueOf(hashCode1422), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl1422, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl1422, materializeModifier1422, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i4522 = (i4422 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                                int i4622 = ((438 >> 6) & 112) | 6;
                                                RowScope rowScope222 = RowScopeInstance.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                                                Alignment.Horizontal centerHorizontally32 = Alignment.INSTANCE.getCenterHorizontally();
                                                Modifier m817paddingVpY3zN4422 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                                MeasurePolicy columnMeasurePolicy522 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally32, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode1522 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap1522 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier1522 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4422);
                                                Function0 constructor1522 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i4722 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function016 = constructor1522;
                                                    $composer2.useNode();
                                                } else {
                                                    function016 = constructor1522;
                                                    $composer2.createNode(function016);
                                                }
                                                Composer m4364constructorimpl1522 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl1522, columnMeasurePolicy522, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl1522, currentCompositionLocalMap1522, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl1522, Integer.valueOf(hashCode1522), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl1522, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl1522, materializeModifier1522, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i4822 = (i4722 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance522 = ColumnScopeInstance.INSTANCE;
                                                int i4922 = ((384 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                                                float m7908constructorimpl22 = Dp.m7908constructorimpl(128);
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                                                rememberedValue11 = $composer2.rememberedValue();
                                                if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                                                    joystickVelocity$delegate4 = joystickVelocity$delegate3;
                                                    rememberedValue11 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda1
                                                        @Override // kotlin.jvm.functions.Function1
                                                        public final Object invoke(Object obj12) {
                                                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$83$lambda$82$lambda$81(MutableState.this, joystickVelocity$delegate4, (Offset) obj12);
                                                        }
                                                    };
                                                    $composer2.updateRememberedValue(rememberedValue11);
                                                } else {
                                                    joystickVelocity$delegate4 = joystickVelocity$delegate3;
                                                }
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl22, (Function1) rememberedValue11, null, $composer2, 54, 4);
                                                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                                                WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                Alignment.Horizontal centerHorizontally222 = Alignment.INSTANCE.getCenterHorizontally();
                                                Arrangement.Vertical m686spacedBy0680j_4322 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                                                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                                Modifier modifier822 = Modifier.INSTANCE;
                                                MeasurePolicy columnMeasurePolicy622 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4322, centerHorizontally222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                                int hashCode1622 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                                CompositionLocalMap currentCompositionLocalMap1622 = $composer2.getCurrentCompositionLocalMap();
                                                Modifier materializeModifier1622 = ComposedModifierKt.materializeModifier($composer2, modifier822);
                                                Function0 constructor1622 = ComposeUiNode.INSTANCE.getConstructor();
                                                int i5022 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                                if (!($composer2.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                $composer2.startReusableNode();
                                                if ($composer2.getInserting()) {
                                                    function017 = constructor1622;
                                                    $composer2.useNode();
                                                } else {
                                                    function017 = constructor1622;
                                                    $composer2.createNode(function017);
                                                }
                                                Composer m4364constructorimpl1622 = Updater.m4364constructorimpl($composer2);
                                                Updater.m4372setimpl(m4364constructorimpl1622, columnMeasurePolicy622, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m4372setimpl(m4364constructorimpl1622, currentCompositionLocalMap1622, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Updater.m4368initimpl(m4364constructorimpl1622, Integer.valueOf(hashCode1622), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                                Updater.m4370reconcileimpl(m4364constructorimpl1622, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                                Updater.m4372setimpl(m4364constructorimpl1622, materializeModifier1622, ComposeUiNode.INSTANCE.getSetModifier());
                                                int i5122 = (i5022 >> 6) & 14;
                                                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance622 = ColumnScopeInstance.INSTANCE;
                                                int i5222 = ((432 >> 6) & 112) | 6;
                                                ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                                                ImageVector plus22 = WayfindrIcons.INSTANCE.getPlus();
                                                ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                                                z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                                Object rememberedValue2022 = $composer2.rememberedValue();
                                                if (z5) {
                                                    composer = $composer2;
                                                } else {
                                                    composer = $composer2;
                                                }
                                                rememberedValue2022 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                                                    }
                                                };
                                                $composer2.updateRememberedValue(rememberedValue2022);
                                                ComposerKt.sourceInformationMarkerEnd(composer);
                                                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus22, (Function0) rememberedValue2022, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                                                Composer composer522 = composer;
                                                TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate322).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer522, 1597440, 0, 261930);
                                                ImageVector minus22 = WayfindrIcons.INSTANCE.getMinus();
                                                ComposerKt.sourceInformationMarkerStart(composer522, 2044399839, str);
                                                z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                                Object rememberedValue2122 = composer522.rememberedValue();
                                                if (z6) {
                                                    composer2 = composer522;
                                                } else {
                                                    composer2 = composer522;
                                                }
                                                rememberedValue2122 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                                                    }
                                                };
                                                composer522.updateRememberedValue(rememberedValue2122);
                                                ComposerKt.sourceInformationMarkerEnd(composer2);
                                                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus22, (Function0) rememberedValue2122, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                                                Composer composer622 = composer2;
                                                ImageVector recenter22 = WayfindrIcons.INSTANCE.getRecenter();
                                                ComposerKt.sourceInformationMarkerStart(composer622, 2044402995, str);
                                                z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                                Object rememberedValue2222 = composer622.rememberedValue();
                                                if (z7) {
                                                    composer3 = composer622;
                                                    if (rememberedValue2222 != Composer.INSTANCE.getEmpty()) {
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter22, (Function0) rememberedValue2222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        $composer2.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        $composer2.endNode();
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        ComposerKt.sourceInformationMarkerEnd($composer2);
                                                        if (streaming || DashScreen$lambda$0(ui$delegate322).getDestinationName() == null) {
                                                            $composer2.startReplaceGroup(-1925928760);
                                                        } else {
                                                            $composer2.startReplaceGroup(-1898306427);
                                                            ComposerKt.sourceInformation($composer2, "559@27847L30,562@27966L23,560@27890L288");
                                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                                                            ImageVector navi = WayfindrIcons.INSTANCE.getNavi();
                                                            BtnVariant btnVariant = BtnVariant.Ghost;
                                                            BtnSize btnSize = BtnSize.Md;
                                                            Modifier fillMaxWidth$default3 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                                            ComposerKt.sourceInformationMarkerStart($composer2, 215862897, str);
                                                            boolean z8 = ((($dirty & 14) ^ 6) > 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                                            Object rememberedValue23 = $composer2.rememberedValue();
                                                            if (z8 || rememberedValue23 == Composer.INSTANCE.getEmpty()) {
                                                                Object obj12 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda5
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Object invoke() {
                                                                        return DashScreenKt.DashScreen$lambda$96$lambda$93$lambda$92(DashViewModel.this);
                                                                    }
                                                                };
                                                                $composer2.updateRememberedValue(obj12);
                                                                rememberedValue23 = obj12;
                                                            }
                                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                                            WayfindrComponentsKt.WayfindrBtn("Navigasyondan çık", (Function0) rememberedValue23, fillMaxWidth$default3, navi, btnVariant, btnSize, false, $composer2, 221574, 64);
                                                        }
                                                        $composer2.endReplaceGroup();
                                                        if (streaming) {
                                                            $composer2.startReplaceGroup(-1897885974);
                                                            ComposerKt.sourceInformation($composer2, "572@28271L30,575@28387L19,573@28314L283");
                                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), $composer2, 6);
                                                            ImageVector power = WayfindrIcons.INSTANCE.getPower();
                                                            BtnVariant btnVariant2 = BtnVariant.Danger;
                                                            BtnSize btnSize2 = BtnSize.Md;
                                                            Modifier fillMaxWidth$default4 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                                            ComposerKt.sourceInformationMarkerStart($composer2, 215876365, str);
                                                            boolean z9 = ((($dirty & 14) ^ 6) > 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                                            Object rememberedValue24 = $composer2.rememberedValue();
                                                            if (z9 || rememberedValue24 == Composer.INSTANCE.getEmpty()) {
                                                                Object obj13 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda6
                                                                    @Override // kotlin.jvm.functions.Function0
                                                                    public final Object invoke() {
                                                                        return DashScreenKt.DashScreen$lambda$96$lambda$95$lambda$94(DashViewModel.this);
                                                                    }
                                                                };
                                                                $composer2.updateRememberedValue(obj13);
                                                                rememberedValue24 = obj13;
                                                            }
                                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                                            WayfindrComponentsKt.WayfindrBtn("Bağlantıyı kes", (Function0) rememberedValue24, fillMaxWidth$default4, power, btnVariant2, btnSize2, false, $composer2, 221574, 64);
                                                        } else {
                                                            $composer2.startReplaceGroup(-1925928760);
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
                                                        vm5 = vm4;
                                                    }
                                                } else {
                                                    composer3 = composer622;
                                                }
                                                rememberedValue2222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                                                    @Override // kotlin.jvm.functions.Function0
                                                    public final Object invoke() {
                                                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate22);
                                                    }
                                                };
                                                composer622.updateRememberedValue(rememberedValue2222);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter22, (Function0) rememberedValue2222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                if (streaming) {
                                                }
                                                $composer2.startReplaceGroup(-1925928760);
                                                $composer2.endReplaceGroup();
                                                if (streaming) {
                                                }
                                                $composer2.endReplaceGroup();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                $composer2.endNode();
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                                if (ComposerKt.isTraceInProgress()) {
                                                }
                                                vm5 = vm4;
                                            }
                                            z2 = true;
                                            z3 = z2;
                                            Object rememberedValue18222 = $composer2.rememberedValue();
                                            if (z3) {
                                            }
                                            rememberedValue18222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                                                @Override // kotlin.jvm.functions.Function1
                                                public final Object invoke(Object obj6222) {
                                                    return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj6222).booleanValue());
                                                }
                                            };
                                            $composer2.updateRememberedValue(rememberedValue18222);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            WayfindrComponentsKt.WayfindrToggle(headingUp22, (Function1) rememberedValue18222, $composer2, 0);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                                            Alignment.Vertical centerVertically5222 = Alignment.INSTANCE.getCenterVertically();
                                            Arrangement.Horizontal spaceBetween3222 = Arrangement.INSTANCE.getSpaceBetween();
                                            Modifier m817paddingVpY3zN43222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            MeasurePolicy rowMeasurePolicy6222 = RowKt.rowMeasurePolicy(spaceBetween3222, centerVertically5222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode11222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap11222 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier11222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN43222);
                                            Function0 constructor11222 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i35222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                            }
                                            Composer m4364constructorimpl11222 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl11222, rowMeasurePolicy6222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl11222, currentCompositionLocalMap11222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl11222, Integer.valueOf(hashCode11222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl11222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl11222, materializeModifier11222, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i36222 = (i35222 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance5222 = RowScopeInstance.INSTANCE;
                                            int i37222 = ((432 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                                            Alignment.Vertical centerVertically6222 = Alignment.INSTANCE.getCenterVertically();
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            Modifier modifier6222 = Modifier.INSTANCE;
                                            MeasurePolicy rowMeasurePolicy7222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically6222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode12222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap12222 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier12222 = ComposedModifierKt.materializeModifier($composer2, modifier6222);
                                            Function0 constructor12222 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i38222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                            }
                                            Composer m4364constructorimpl12222 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl12222, rowMeasurePolicy7222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl12222, currentCompositionLocalMap12222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl12222, Integer.valueOf(hashCode12222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl12222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl12222, materializeModifier12222, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i39222 = (i38222 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            RowScopeInstance rowScopeInstance6222 = RowScopeInstance.INSTANCE;
                                            int i40222 = ((384 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate322).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            Modifier modifier7222 = Modifier.INSTANCE;
                                            MeasurePolicy columnMeasurePolicy4222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode13222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap13222 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier13222 = ComposedModifierKt.materializeModifier($composer2, modifier7222);
                                            Function0 constructor13222 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i41222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                            }
                                            Composer m4364constructorimpl13222 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl13222, columnMeasurePolicy4222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl13222, currentCompositionLocalMap13222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl13222, Integer.valueOf(hashCode13222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl13222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl13222, materializeModifier13222, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i42222 = (i41222 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance4222 = ColumnScopeInstance.INSTANCE;
                                            int i43222 = ((0 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                                            TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                                            TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate322).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate322).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            boolean mapDark222 = DashScreen$lambda$0(ui$delegate322).getMapDark();
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                                            if ((($dirty & 14) ^ 6) <= 4) {
                                            }
                                            Object rememberedValue19222 = $composer2.rememberedValue();
                                            if (z4) {
                                            }
                                            Object obj6222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                                                @Override // kotlin.jvm.functions.Function1
                                                public final Object invoke(Object obj72) {
                                                    return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                                                }
                                            };
                                            $composer2.updateRememberedValue(obj6222);
                                            rememberedValue19222 = obj6222;
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            WayfindrComponentsKt.WayfindrToggle(mapDark222, (Function1) rememberedValue19222, $composer2, 0);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                                            Alignment.Vertical centerVertically7222 = Alignment.INSTANCE.getCenterVertically();
                                            Arrangement.Horizontal m686spacedBy0680j_42222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                                            Modifier fillMaxWidth$default222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                            MeasurePolicy rowMeasurePolicy8222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_42222, centerVertically7222, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode14222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap14222 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier14222 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default222);
                                            Function0 constructor14222 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i44222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                            }
                                            Composer m4364constructorimpl14222 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl14222, rowMeasurePolicy8222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl14222, currentCompositionLocalMap14222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl14222, Integer.valueOf(hashCode14222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl14222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl14222, materializeModifier14222, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i45222 = (i44222 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                            int i46222 = ((438 >> 6) & 112) | 6;
                                            RowScope rowScope2222 = RowScopeInstance.INSTANCE;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                                            Alignment.Horizontal centerHorizontally322 = Alignment.INSTANCE.getCenterHorizontally();
                                            Modifier m817paddingVpY3zN44222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope2222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            MeasurePolicy columnMeasurePolicy5222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally322, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode15222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap15222 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier15222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN44222);
                                            Function0 constructor15222 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i47222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                            }
                                            Composer m4364constructorimpl15222 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl15222, columnMeasurePolicy5222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl15222, currentCompositionLocalMap15222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl15222, Integer.valueOf(hashCode15222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl15222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl15222, materializeModifier15222, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i48222 = (i47222 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance5222 = ColumnScopeInstance.INSTANCE;
                                            int i49222 = ((384 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                                            float m7908constructorimpl222 = Dp.m7908constructorimpl(128);
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                                            rememberedValue11 = $composer2.rememberedValue();
                                            if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                                            }
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl222, (Function1) rememberedValue11, null, $composer2, 54, 4);
                                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                                            WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            Alignment.Horizontal centerHorizontally2222 = Alignment.INSTANCE.getCenterHorizontally();
                                            Arrangement.Vertical m686spacedBy0680j_43222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                            Modifier modifier8222 = Modifier.INSTANCE;
                                            MeasurePolicy columnMeasurePolicy6222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_43222, centerHorizontally2222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                            int hashCode16222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                            CompositionLocalMap currentCompositionLocalMap16222 = $composer2.getCurrentCompositionLocalMap();
                                            Modifier materializeModifier16222 = ComposedModifierKt.materializeModifier($composer2, modifier8222);
                                            Function0 constructor16222 = ComposeUiNode.INSTANCE.getConstructor();
                                            int i50222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                            if (!($composer2.getApplier() instanceof Applier)) {
                                            }
                                            $composer2.startReusableNode();
                                            if ($composer2.getInserting()) {
                                            }
                                            Composer m4364constructorimpl16222 = Updater.m4364constructorimpl($composer2);
                                            Updater.m4372setimpl(m4364constructorimpl16222, columnMeasurePolicy6222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m4372setimpl(m4364constructorimpl16222, currentCompositionLocalMap16222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Updater.m4368initimpl(m4364constructorimpl16222, Integer.valueOf(hashCode16222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                            Updater.m4370reconcileimpl(m4364constructorimpl16222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                            Updater.m4372setimpl(m4364constructorimpl16222, materializeModifier16222, ComposeUiNode.INSTANCE.getSetModifier());
                                            int i51222 = (i50222 >> 6) & 14;
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                            ColumnScopeInstance columnScopeInstance6222 = ColumnScopeInstance.INSTANCE;
                                            int i52222 = ((432 >> 6) & 112) | 6;
                                            ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                                            ImageVector plus222 = WayfindrIcons.INSTANCE.getPlus();
                                            ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                                            z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                            Object rememberedValue20222 = $composer2.rememberedValue();
                                            if (z5) {
                                            }
                                            rememberedValue20222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                                                }
                                            };
                                            $composer2.updateRememberedValue(rememberedValue20222);
                                            ComposerKt.sourceInformationMarkerEnd(composer);
                                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus222, (Function0) rememberedValue20222, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                                            Composer composer5222 = composer;
                                            TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate322).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer5222, 1597440, 0, 261930);
                                            ImageVector minus222 = WayfindrIcons.INSTANCE.getMinus();
                                            ComposerKt.sourceInformationMarkerStart(composer5222, 2044399839, str);
                                            z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                            Object rememberedValue21222 = composer5222.rememberedValue();
                                            if (z6) {
                                            }
                                            rememberedValue21222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                                                }
                                            };
                                            composer5222.updateRememberedValue(rememberedValue21222);
                                            ComposerKt.sourceInformationMarkerEnd(composer2);
                                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus222, (Function0) rememberedValue21222, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                                            Composer composer6222 = composer2;
                                            ImageVector recenter222 = WayfindrIcons.INSTANCE.getRecenter();
                                            ComposerKt.sourceInformationMarkerStart(composer6222, 2044402995, str);
                                            z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                            Object rememberedValue22222 = composer6222.rememberedValue();
                                            if (z7) {
                                            }
                                            rememberedValue22222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                                                @Override // kotlin.jvm.functions.Function0
                                                public final Object invoke() {
                                                    return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate22);
                                                }
                                            };
                                            composer6222.updateRememberedValue(rememberedValue22222);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter222, (Function0) rememberedValue22222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                                            ComposerKt.sourceInformationMarkerEnd(composer3);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            if (streaming) {
                                            }
                                            $composer2.startReplaceGroup(-1925928760);
                                            $composer2.endReplaceGroup();
                                            if (streaming) {
                                            }
                                            $composer2.endReplaceGroup();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            $composer2.endNode();
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            ComposerKt.sourceInformationMarkerEnd($composer2);
                                            if (ComposerKt.isTraceInProgress()) {
                                            }
                                            vm5 = vm4;
                                        }
                                    } else {
                                        uiState = uiState322;
                                    }
                                    final Context context322 = context2;
                                    final DashViewModel vm722 = vm2;
                                    final MutableState batteryOk$delegate322 = batteryOk$delegate2;
                                    final State uiState422 = uiState;
                                    Object obj522 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj623) {
                                            return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context322, uiState422, vm722, batteryOk$delegate322, essentialPermissions722, (DisposableEffectScope) obj623);
                                        }
                                    };
                                    uiState2 = uiState422;
                                    essentialPermissions722 = essentialPermissions722;
                                    rememberedValue1722 = obj522;
                                    $composer2.updateRememberedValue(rememberedValue1722);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    EffectsKt.DisposableEffect(lifecycleOwner22, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue1722, $composer2, 0);
                                    Modifier m820paddingqDBjuR0$default222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    MeasurePolicy columnMeasurePolicy722 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode1722 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap1722 = $composer2.getCurrentCompositionLocalMap();
                                    final DashViewModel vm8222 = vm2;
                                    Modifier materializeModifier1722 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default222);
                                    Function0 constructor1722 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i41022 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl1722 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl1722, columnMeasurePolicy722, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl1722, currentCompositionLocalMap1722, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl1722, Integer.valueOf(hashCode1722), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl1722, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl1722, materializeModifier1722, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i5322 = (i41022 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance722 = ColumnScopeInstance.INSTANCE;
                                    int i6222 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                                    streaming = streaming2;
                                    final State ui$delegate3222 = ui$delegate2;
                                    WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                                        @Override // kotlin.jvm.functions.Function2
                                        public final Object invoke(Object obj6223, Object obj72) {
                                            return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming2, ui$delegate2, (Composer) obj6223, ((Integer) obj72).intValue());
                                        }
                                    }, $composer2, 54), $composer2, 1576368, 49);
                                    if (streaming) {
                                    }
                                    $composer2.endReplaceGroup();
                                    Alignment center222 = Alignment.INSTANCE.getCenter();
                                    Modifier m818paddingVpY3zN4$default222 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy322 = BoxKt.maybeCachedBoxMeasurePolicy(center222, false);
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode2222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap2222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier2222 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default222);
                                    Function0 constructor2222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i7222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl2222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl2222, maybeCachedBoxMeasurePolicy322, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl2222, currentCompositionLocalMap2222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl2222, Integer.valueOf(hashCode2222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl2222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl2222, materializeModifier2222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i8222 = (i7222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance322 = BoxScopeInstance.INSTANCE;
                                    int i9222 = ((54 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                                    if (streaming) {
                                    }
                                    $composer2.endReplaceGroup();
                                    Modifier m273borderxT4_qwU222 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                                    MeasurePolicy maybeCachedBoxMeasurePolicy2222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode3222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap3222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier3222 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU222);
                                    Function0 constructor3222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i10222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl3222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl3222, maybeCachedBoxMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl3222, currentCompositionLocalMap3222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl3222, Integer.valueOf(hashCode3222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl3222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl3222, materializeModifier3222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i11222 = (i10222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance2222 = BoxScopeInstance.INSTANCE;
                                    int i12222 = ((0 >> 6) & 112) | 6;
                                    Composer composer4222 = $composer2;
                                    ComposerKt.sourceInformationMarkerStart(composer4222, 2024465622, "C:DashScreen.kt#cef2n4");
                                    DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                                    if (DashScreen$lambda$1 != null) {
                                    }
                                    ComposerKt.sourceInformationMarkerEnd(composer4222);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                                    maneuver = DashScreen$lambda$0(ui$delegate3222).getManeuver();
                                    if (maneuver == null) {
                                    }
                                    Arrangement.Horizontal m686spacedBy0680j_4422 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    Modifier modifier922 = Modifier.INSTANCE;
                                    Alignment.Vertical top222 = Alignment.INSTANCE.getTop();
                                    MeasurePolicy rowMeasurePolicy922 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4422, top222, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode4222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap4222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier4222 = ComposedModifierKt.materializeModifier($composer2, modifier922);
                                    Function0 constructor4222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i13222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl4222 = Updater.m4364constructorimpl($composer2);
                                    MeasurePolicy measurePolicy222 = rowMeasurePolicy922;
                                    Updater.m4372setimpl(m4364constructorimpl4222, measurePolicy222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl4222, currentCompositionLocalMap4222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl4222, Integer.valueOf(hashCode4222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl4222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl4222, materializeModifier4222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i14222 = (i13222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    int i15222 = ((48 >> 6) & 112) | 6;
                                    RowScope rowScope322 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                                    $composer2.startReplaceGroup(-2140347244);
                                    ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                                    Triple[] tripleArr2222 = new Triple[3];
                                    remainingKm = DashScreen$lambda$0(ui$delegate3222).getRemainingKm();
                                    if (remainingKm != null) {
                                    }
                                    str2 = "—";
                                    tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate3222).getRemainingKm() != null ? "km" : "", "Kalan");
                                    etaMinutes = DashScreen$lambda$0(ui$delegate3222).getEtaMinutes();
                                    if (etaMinutes != null) {
                                    }
                                    str3 = "—";
                                    Integer etaMinutes2222 = DashScreen$lambda$0(ui$delegate3222).getEtaMinutes();
                                    tripleArr[1] = new Triple(str3, etaMinutes2222 != null ? etaMinutes2222.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                                    tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate3222).getMapZoom(), "", "Yakınlaştırma");
                                    Iterable listOf222 = CollectionsKt.listOf((Object[]) tripleArr);
                                    int i16222 = 0;
                                    it = listOf222.iterator();
                                    while (it.hasNext()) {
                                    }
                                    DashViewModel vm9222 = vm3;
                                    $composer2.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                                    Alignment.Vertical centerVertically822 = Alignment.INSTANCE.getCenterVertically();
                                    Arrangement.Horizontal spaceBetween422 = Arrangement.INSTANCE.getSpaceBetween();
                                    Modifier m817paddingVpY3zN4522 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    MeasurePolicy rowMeasurePolicy2222 = RowKt.rowMeasurePolicy(spaceBetween422, centerVertically822, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode5222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap5222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier5222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4522);
                                    Function0 constructor5222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i17222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl5222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl5222, rowMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl5222, currentCompositionLocalMap5222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl5222, Integer.valueOf(hashCode5222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl5222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl5222, materializeModifier5222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i18222 = (i17222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance722 = RowScopeInstance.INSTANCE;
                                    int i19222 = ((432 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                                    Alignment.Vertical centerVertically2222 = Alignment.INSTANCE.getCenterVertically();
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    Modifier modifier2222 = Modifier.INSTANCE;
                                    MeasurePolicy rowMeasurePolicy3222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode6222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap6222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier6222 = ComposedModifierKt.materializeModifier($composer2, modifier2222);
                                    Function0 constructor6222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i20222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl6222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl6222, rowMeasurePolicy3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl6222, currentCompositionLocalMap6222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl6222, Integer.valueOf(hashCode6222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl6222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl6222, materializeModifier6222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i21222 = (i20222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance2222 = RowScopeInstance.INSTANCE;
                                    int i22222 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Modifier modifier3222 = Modifier.INSTANCE;
                                    MeasurePolicy columnMeasurePolicy2222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode7222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap7222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier7222 = ComposedModifierKt.materializeModifier($composer2, modifier3222);
                                    Function0 constructor7222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i23222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl7222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl7222, columnMeasurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl7222, currentCompositionLocalMap7222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl7222, Integer.valueOf(hashCode7222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl7222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl7222, materializeModifier7222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i24222 = (i23222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance2222 = ColumnScopeInstance.INSTANCE;
                                    int i25222 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                                    TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                                    TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    boolean DashScreen$lambda$23222 = DashScreen$lambda$23(adjustMode$delegate2);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                                    rememberedValue10 = $composer2.rememberedValue();
                                    if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$23222, (Function1) rememberedValue10, $composer2, 48);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                                    Alignment.Vertical centerVertically3222 = Alignment.INSTANCE.getCenterVertically();
                                    Arrangement.Horizontal spaceBetween2222 = Arrangement.INSTANCE.getSpaceBetween();
                                    Modifier m817paddingVpY3zN42222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    MeasurePolicy rowMeasurePolicy4222 = RowKt.rowMeasurePolicy(spaceBetween2222, centerVertically3222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode8222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap8222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier8222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN42222);
                                    Function0 constructor8222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i26222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl8222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl8222, rowMeasurePolicy4222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl8222, currentCompositionLocalMap8222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl8222, Integer.valueOf(hashCode8222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl8222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl8222, materializeModifier8222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i27222 = (i26222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance3222 = RowScopeInstance.INSTANCE;
                                    int i28222 = ((432 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                                    Alignment.Vertical centerVertically4222 = Alignment.INSTANCE.getCenterVertically();
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    Modifier modifier4222 = Modifier.INSTANCE;
                                    MeasurePolicy rowMeasurePolicy5222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode9222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap9222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier9222 = ComposedModifierKt.materializeModifier($composer2, modifier4222);
                                    Function0 constructor9222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i29222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl9222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl9222, rowMeasurePolicy5222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl9222, currentCompositionLocalMap9222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl9222, Integer.valueOf(hashCode9222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl9222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl9222, materializeModifier9222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i30222 = (i29222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance4222 = RowScopeInstance.INSTANCE;
                                    int i31222 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate3222).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Modifier modifier5222 = Modifier.INSTANCE;
                                    MeasurePolicy columnMeasurePolicy3222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode10222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap10222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier10222 = ComposedModifierKt.materializeModifier($composer2, modifier5222);
                                    Function0 constructor10222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i32222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl10222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl10222, columnMeasurePolicy3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl10222, currentCompositionLocalMap10222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl10222, Integer.valueOf(hashCode10222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl10222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl10222, materializeModifier10222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i33222 = (i32222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance3222 = ColumnScopeInstance.INSTANCE;
                                    int i34222 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                                    TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                                    TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate3222).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    boolean headingUp222 = DashScreen$lambda$0(ui$delegate3222).getHeadingUp();
                                    ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                                    if ((($dirty & 14) ^ 6) > 4) {
                                    }
                                    if (($dirty & 6) != 4) {
                                    }
                                    z2 = true;
                                    z3 = z2;
                                    Object rememberedValue182222 = $composer2.rememberedValue();
                                    if (z3) {
                                    }
                                    rememberedValue182222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj62222) {
                                            return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj62222).booleanValue());
                                        }
                                    };
                                    $composer2.updateRememberedValue(rememberedValue182222);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    WayfindrComponentsKt.WayfindrToggle(headingUp222, (Function1) rememberedValue182222, $composer2, 0);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                                    Alignment.Vertical centerVertically52222 = Alignment.INSTANCE.getCenterVertically();
                                    Arrangement.Horizontal spaceBetween32222 = Arrangement.INSTANCE.getSpaceBetween();
                                    Modifier m817paddingVpY3zN432222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    MeasurePolicy rowMeasurePolicy62222 = RowKt.rowMeasurePolicy(spaceBetween32222, centerVertically52222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode112222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap112222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier112222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN432222);
                                    Function0 constructor112222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i352222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl112222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl112222, rowMeasurePolicy62222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl112222, currentCompositionLocalMap112222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl112222, Integer.valueOf(hashCode112222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl112222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl112222, materializeModifier112222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i362222 = (i352222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance52222 = RowScopeInstance.INSTANCE;
                                    int i372222 = ((432 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                                    Alignment.Vertical centerVertically62222 = Alignment.INSTANCE.getCenterVertically();
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    Modifier modifier62222 = Modifier.INSTANCE;
                                    MeasurePolicy rowMeasurePolicy72222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically62222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode122222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap122222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier122222 = ComposedModifierKt.materializeModifier($composer2, modifier62222);
                                    Function0 constructor122222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i382222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl122222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl122222, rowMeasurePolicy72222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl122222, currentCompositionLocalMap122222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl122222, Integer.valueOf(hashCode122222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl122222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl122222, materializeModifier122222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i392222 = (i382222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    RowScopeInstance rowScopeInstance62222 = RowScopeInstance.INSTANCE;
                                    int i402222 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate3222).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Modifier modifier72222 = Modifier.INSTANCE;
                                    MeasurePolicy columnMeasurePolicy42222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode132222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap132222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier132222 = ComposedModifierKt.materializeModifier($composer2, modifier72222);
                                    Function0 constructor132222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i412222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl132222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl132222, columnMeasurePolicy42222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl132222, currentCompositionLocalMap132222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl132222, Integer.valueOf(hashCode132222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl132222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl132222, materializeModifier132222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i422222 = (i412222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance42222 = ColumnScopeInstance.INSTANCE;
                                    int i432222 = ((0 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                                    TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                                    TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate3222).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate3222).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    boolean mapDark2222 = DashScreen$lambda$0(ui$delegate3222).getMapDark();
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                                    if ((($dirty & 14) ^ 6) <= 4) {
                                    }
                                    Object rememberedValue192222 = $composer2.rememberedValue();
                                    if (z4) {
                                    }
                                    Object obj62222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                                        @Override // kotlin.jvm.functions.Function1
                                        public final Object invoke(Object obj72) {
                                            return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                                        }
                                    };
                                    $composer2.updateRememberedValue(obj62222);
                                    rememberedValue192222 = obj62222;
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    WayfindrComponentsKt.WayfindrToggle(mapDark2222, (Function1) rememberedValue192222, $composer2, 0);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                                    Alignment.Vertical centerVertically72222 = Alignment.INSTANCE.getCenterVertically();
                                    Arrangement.Horizontal m686spacedBy0680j_422222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                                    Modifier fillMaxWidth$default2222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                                    MeasurePolicy rowMeasurePolicy82222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_422222, centerVertically72222, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode142222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap142222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier142222 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default2222);
                                    Function0 constructor142222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i442222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl142222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl142222, rowMeasurePolicy82222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl142222, currentCompositionLocalMap142222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl142222, Integer.valueOf(hashCode142222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl142222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl142222, materializeModifier142222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i452222 = (i442222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                                    int i462222 = ((438 >> 6) & 112) | 6;
                                    RowScope rowScope22222 = RowScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                                    Alignment.Horizontal centerHorizontally3222 = Alignment.INSTANCE.getCenterHorizontally();
                                    Modifier m817paddingVpY3zN442222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope22222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    MeasurePolicy columnMeasurePolicy52222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally3222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode152222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap152222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier152222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN442222);
                                    Function0 constructor152222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i472222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl152222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl152222, columnMeasurePolicy52222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl152222, currentCompositionLocalMap152222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl152222, Integer.valueOf(hashCode152222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl152222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl152222, materializeModifier152222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i482222 = (i472222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance52222 = ColumnScopeInstance.INSTANCE;
                                    int i492222 = ((384 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                                    float m7908constructorimpl2222 = Dp.m7908constructorimpl(128);
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                                    rememberedValue11 = $composer2.rememberedValue();
                                    if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl2222, (Function1) rememberedValue11, null, $composer2, 54, 4);
                                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                                    WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    Alignment.Horizontal centerHorizontally22222 = Alignment.INSTANCE.getCenterHorizontally();
                                    Arrangement.Vertical m686spacedBy0680j_432222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                                    Modifier modifier82222 = Modifier.INSTANCE;
                                    MeasurePolicy columnMeasurePolicy62222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_432222, centerHorizontally22222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                                    int hashCode162222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                                    CompositionLocalMap currentCompositionLocalMap162222 = $composer2.getCurrentCompositionLocalMap();
                                    Modifier materializeModifier162222 = ComposedModifierKt.materializeModifier($composer2, modifier82222);
                                    Function0 constructor162222 = ComposeUiNode.INSTANCE.getConstructor();
                                    int i502222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                                    if (!($composer2.getApplier() instanceof Applier)) {
                                    }
                                    $composer2.startReusableNode();
                                    if ($composer2.getInserting()) {
                                    }
                                    Composer m4364constructorimpl162222 = Updater.m4364constructorimpl($composer2);
                                    Updater.m4372setimpl(m4364constructorimpl162222, columnMeasurePolicy62222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m4372setimpl(m4364constructorimpl162222, currentCompositionLocalMap162222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Updater.m4368initimpl(m4364constructorimpl162222, Integer.valueOf(hashCode162222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                                    Updater.m4370reconcileimpl(m4364constructorimpl162222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                                    Updater.m4372setimpl(m4364constructorimpl162222, materializeModifier162222, ComposeUiNode.INSTANCE.getSetModifier());
                                    int i512222 = (i502222 >> 6) & 14;
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                                    ColumnScopeInstance columnScopeInstance62222 = ColumnScopeInstance.INSTANCE;
                                    int i522222 = ((432 >> 6) & 112) | 6;
                                    ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                                    ImageVector plus2222 = WayfindrIcons.INSTANCE.getPlus();
                                    ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                                    z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                    Object rememberedValue202222 = $composer2.rememberedValue();
                                    if (z5) {
                                    }
                                    rememberedValue202222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                                        }
                                    };
                                    $composer2.updateRememberedValue(rememberedValue202222);
                                    ComposerKt.sourceInformationMarkerEnd(composer);
                                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus2222, (Function0) rememberedValue202222, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                                    Composer composer52222 = composer;
                                    TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate3222).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer52222, 1597440, 0, 261930);
                                    ImageVector minus2222 = WayfindrIcons.INSTANCE.getMinus();
                                    ComposerKt.sourceInformationMarkerStart(composer52222, 2044399839, str);
                                    z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                    Object rememberedValue212222 = composer52222.rememberedValue();
                                    if (z6) {
                                    }
                                    rememberedValue212222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                                        }
                                    };
                                    composer52222.updateRememberedValue(rememberedValue212222);
                                    ComposerKt.sourceInformationMarkerEnd(composer2);
                                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus2222, (Function0) rememberedValue212222, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                                    Composer composer62222 = composer2;
                                    ImageVector recenter2222 = WayfindrIcons.INSTANCE.getRecenter();
                                    ComposerKt.sourceInformationMarkerStart(composer62222, 2044402995, str);
                                    z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                                    Object rememberedValue222222 = composer62222.rememberedValue();
                                    if (z7) {
                                    }
                                    rememberedValue222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Object invoke() {
                                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate22);
                                        }
                                    };
                                    composer62222.updateRememberedValue(rememberedValue222222);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter2222, (Function0) rememberedValue222222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    if (streaming) {
                                    }
                                    $composer2.startReplaceGroup(-1925928760);
                                    $composer2.endReplaceGroup();
                                    if (streaming) {
                                    }
                                    $composer2.endReplaceGroup();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    $composer2.endNode();
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    ComposerKt.sourceInformationMarkerEnd($composer2);
                                    if (ComposerKt.isTraceInProgress()) {
                                    }
                                    vm5 = vm4;
                                }
                            }
                            Object dashScreenKt$DashScreen$3$122 = new DashScreenKt$DashScreen$3$1(vm2, autoConnectOnStart$delegate, autoConnectTried$delegate22, ui$delegate, essentialPermissions622, context2, null);
                            ui$delegate2 = ui$delegate;
                            essentialPermissions4 = essentialPermissions622;
                            obj = (Function2) dashScreenKt$DashScreen$3$122;
                            $composer2.updateRememberedValue(obj);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            EffectsKt.LaunchedEffect(valueOf322, stage222, (Function2) obj, $composer2, 0);
                            State uiState3222 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner222 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                            Object consume2222 = $composer2.consume(localLifecycleOwner222);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            final LifecycleOwner lifecycleOwner222 = (LifecycleOwner) consume2222;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                            changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState3222) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner222);
                            final String[] essentialPermissions7222 = essentialPermissions4;
                            Object rememberedValue17222 = $composer2.rememberedValue();
                            if (changedInstance3) {
                            }
                            final Context context3222 = context2;
                            final DashViewModel vm7222 = vm2;
                            final MutableState batteryOk$delegate3222 = batteryOk$delegate2;
                            final State uiState4222 = uiState;
                            Object obj5222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj623) {
                                    return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context3222, uiState4222, vm7222, batteryOk$delegate3222, essentialPermissions7222, (DisposableEffectScope) obj623);
                                }
                            };
                            uiState2 = uiState4222;
                            essentialPermissions7222 = essentialPermissions7222;
                            rememberedValue17222 = obj5222;
                            $composer2.updateRememberedValue(rememberedValue17222);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            EffectsKt.DisposableEffect(lifecycleOwner222, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue17222, $composer2, 0);
                            Modifier m820paddingqDBjuR0$default2222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy7222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode17222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap17222 = $composer2.getCurrentCompositionLocalMap();
                            final DashViewModel vm82222 = vm2;
                            Modifier materializeModifier17222 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default2222);
                            Function0 constructor17222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i410222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl17222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl17222, columnMeasurePolicy7222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl17222, currentCompositionLocalMap17222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl17222, Integer.valueOf(hashCode17222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl17222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl17222, materializeModifier17222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i53222 = (i410222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance7222 = ColumnScopeInstance.INSTANCE;
                            int i62222 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                            streaming = streaming2;
                            final State ui$delegate32222 = ui$delegate2;
                            WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                                @Override // kotlin.jvm.functions.Function2
                                public final Object invoke(Object obj6223, Object obj72) {
                                    return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming2, ui$delegate2, (Composer) obj6223, ((Integer) obj72).intValue());
                                }
                            }, $composer2, 54), $composer2, 1576368, 49);
                            if (streaming) {
                            }
                            $composer2.endReplaceGroup();
                            Alignment center2222 = Alignment.INSTANCE.getCenter();
                            Modifier m818paddingVpY3zN4$default2222 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy3222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222, false);
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode22222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap22222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier22222 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default2222);
                            Function0 constructor22222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i72222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl22222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl22222, maybeCachedBoxMeasurePolicy3222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl22222, currentCompositionLocalMap22222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl22222, Integer.valueOf(hashCode22222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl22222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl22222, materializeModifier22222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i82222 = (i72222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance3222 = BoxScopeInstance.INSTANCE;
                            int i92222 = ((54 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                            if (streaming) {
                            }
                            $composer2.endReplaceGroup();
                            Modifier m273borderxT4_qwU2222 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                            ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                            MeasurePolicy maybeCachedBoxMeasurePolicy22222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode32222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap32222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier32222 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU2222);
                            Function0 constructor32222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i102222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl32222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl32222, maybeCachedBoxMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl32222, currentCompositionLocalMap32222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl32222, Integer.valueOf(hashCode32222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl32222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl32222, materializeModifier32222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i112222 = (i102222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                            BoxScopeInstance boxScopeInstance22222 = BoxScopeInstance.INSTANCE;
                            int i122222 = ((0 >> 6) & 112) | 6;
                            Composer composer42222 = $composer2;
                            ComposerKt.sourceInformationMarkerStart(composer42222, 2024465622, "C:DashScreen.kt#cef2n4");
                            DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                            if (DashScreen$lambda$1 != null) {
                            }
                            ComposerKt.sourceInformationMarkerEnd(composer42222);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                            maneuver = DashScreen$lambda$0(ui$delegate32222).getManeuver();
                            if (maneuver == null) {
                            }
                            Arrangement.Horizontal m686spacedBy0680j_44222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            Modifier modifier9222 = Modifier.INSTANCE;
                            Alignment.Vertical top2222 = Alignment.INSTANCE.getTop();
                            MeasurePolicy rowMeasurePolicy9222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_44222, top2222, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode42222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap42222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier42222 = ComposedModifierKt.materializeModifier($composer2, modifier9222);
                            Function0 constructor42222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i132222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl42222 = Updater.m4364constructorimpl($composer2);
                            MeasurePolicy measurePolicy2222 = rowMeasurePolicy9222;
                            Updater.m4372setimpl(m4364constructorimpl42222, measurePolicy2222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl42222, currentCompositionLocalMap42222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl42222, Integer.valueOf(hashCode42222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl42222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl42222, materializeModifier42222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i142222 = (i132222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            int i152222 = ((48 >> 6) & 112) | 6;
                            RowScope rowScope3222 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                            $composer2.startReplaceGroup(-2140347244);
                            ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                            Triple[] tripleArr22222 = new Triple[3];
                            remainingKm = DashScreen$lambda$0(ui$delegate32222).getRemainingKm();
                            if (remainingKm != null) {
                            }
                            str2 = "—";
                            tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate32222).getRemainingKm() != null ? "km" : "", "Kalan");
                            etaMinutes = DashScreen$lambda$0(ui$delegate32222).getEtaMinutes();
                            if (etaMinutes != null) {
                            }
                            str3 = "—";
                            Integer etaMinutes22222 = DashScreen$lambda$0(ui$delegate32222).getEtaMinutes();
                            tripleArr[1] = new Triple(str3, etaMinutes22222 != null ? etaMinutes22222.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                            tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate32222).getMapZoom(), "", "Yakınlaştırma");
                            Iterable listOf2222 = CollectionsKt.listOf((Object[]) tripleArr);
                            int i162222 = 0;
                            it = listOf2222.iterator();
                            while (it.hasNext()) {
                            }
                            DashViewModel vm92222 = vm3;
                            $composer2.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                            Alignment.Vertical centerVertically8222 = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.Horizontal spaceBetween4222 = Arrangement.INSTANCE.getSpaceBetween();
                            Modifier m817paddingVpY3zN45222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy22222 = RowKt.rowMeasurePolicy(spaceBetween4222, centerVertically8222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode52222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap52222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier52222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN45222);
                            Function0 constructor52222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i172222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl52222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl52222, rowMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl52222, currentCompositionLocalMap52222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl52222, Integer.valueOf(hashCode52222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl52222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl52222, materializeModifier52222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i182222 = (i172222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance7222 = RowScopeInstance.INSTANCE;
                            int i192222 = ((432 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                            Alignment.Vertical centerVertically22222 = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            Modifier modifier22222 = Modifier.INSTANCE;
                            MeasurePolicy rowMeasurePolicy32222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically22222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode62222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap62222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier62222 = ComposedModifierKt.materializeModifier($composer2, modifier22222);
                            Function0 constructor62222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i202222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl62222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl62222, rowMeasurePolicy32222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl62222, currentCompositionLocalMap62222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl62222, Integer.valueOf(hashCode62222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl62222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl62222, materializeModifier62222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i212222 = (i202222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance22222 = RowScopeInstance.INSTANCE;
                            int i222222 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Modifier modifier32222 = Modifier.INSTANCE;
                            MeasurePolicy columnMeasurePolicy22222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode72222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap72222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier72222 = ComposedModifierKt.materializeModifier($composer2, modifier32222);
                            Function0 constructor72222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i232222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl72222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl72222, columnMeasurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl72222, currentCompositionLocalMap72222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl72222, Integer.valueOf(hashCode72222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl72222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl72222, materializeModifier72222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i242222 = (i232222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance22222 = ColumnScopeInstance.INSTANCE;
                            int i252222 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                            TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                            TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            boolean DashScreen$lambda$232222 = DashScreen$lambda$23(adjustMode$delegate2);
                            ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                            rememberedValue10 = $composer2.rememberedValue();
                            if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$232222, (Function1) rememberedValue10, $composer2, 48);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                            Alignment.Vertical centerVertically32222 = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.Horizontal spaceBetween22222 = Arrangement.INSTANCE.getSpaceBetween();
                            Modifier m817paddingVpY3zN422222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy42222 = RowKt.rowMeasurePolicy(spaceBetween22222, centerVertically32222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode82222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap82222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier82222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN422222);
                            Function0 constructor82222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i262222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl82222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl82222, rowMeasurePolicy42222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl82222, currentCompositionLocalMap82222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl82222, Integer.valueOf(hashCode82222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl82222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl82222, materializeModifier82222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i272222 = (i262222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance32222 = RowScopeInstance.INSTANCE;
                            int i282222 = ((432 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                            Alignment.Vertical centerVertically42222 = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            Modifier modifier42222 = Modifier.INSTANCE;
                            MeasurePolicy rowMeasurePolicy52222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically42222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode92222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap92222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier92222 = ComposedModifierKt.materializeModifier($composer2, modifier42222);
                            Function0 constructor92222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i292222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl92222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl92222, rowMeasurePolicy52222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl92222, currentCompositionLocalMap92222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl92222, Integer.valueOf(hashCode92222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl92222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl92222, materializeModifier92222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i302222 = (i292222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance42222 = RowScopeInstance.INSTANCE;
                            int i312222 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate32222).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Modifier modifier52222 = Modifier.INSTANCE;
                            MeasurePolicy columnMeasurePolicy32222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode102222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap102222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier102222 = ComposedModifierKt.materializeModifier($composer2, modifier52222);
                            Function0 constructor102222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i322222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl102222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl102222, columnMeasurePolicy32222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl102222, currentCompositionLocalMap102222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl102222, Integer.valueOf(hashCode102222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl102222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl102222, materializeModifier102222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i332222 = (i322222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance32222 = ColumnScopeInstance.INSTANCE;
                            int i342222 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                            TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                            TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate32222).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            boolean headingUp2222 = DashScreen$lambda$0(ui$delegate32222).getHeadingUp();
                            ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                            if ((($dirty & 14) ^ 6) > 4) {
                            }
                            if (($dirty & 6) != 4) {
                            }
                            z2 = true;
                            z3 = z2;
                            Object rememberedValue1822222 = $composer2.rememberedValue();
                            if (z3) {
                            }
                            rememberedValue1822222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj622222) {
                                    return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj622222).booleanValue());
                                }
                            };
                            $composer2.updateRememberedValue(rememberedValue1822222);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            WayfindrComponentsKt.WayfindrToggle(headingUp2222, (Function1) rememberedValue1822222, $composer2, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                            Alignment.Vertical centerVertically522222 = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.Horizontal spaceBetween322222 = Arrangement.INSTANCE.getSpaceBetween();
                            Modifier m817paddingVpY3zN4322222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy622222 = RowKt.rowMeasurePolicy(spaceBetween322222, centerVertically522222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode1122222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap1122222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier1122222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4322222);
                            Function0 constructor1122222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i3522222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl1122222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl1122222, rowMeasurePolicy622222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl1122222, currentCompositionLocalMap1122222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl1122222, Integer.valueOf(hashCode1122222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl1122222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl1122222, materializeModifier1122222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i3622222 = (i3522222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance522222 = RowScopeInstance.INSTANCE;
                            int i3722222 = ((432 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                            Alignment.Vertical centerVertically622222 = Alignment.INSTANCE.getCenterVertically();
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            Modifier modifier622222 = Modifier.INSTANCE;
                            MeasurePolicy rowMeasurePolicy722222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically622222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode1222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap1222222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier1222222 = ComposedModifierKt.materializeModifier($composer2, modifier622222);
                            Function0 constructor1222222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i3822222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl1222222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl1222222, rowMeasurePolicy722222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl1222222, currentCompositionLocalMap1222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl1222222, Integer.valueOf(hashCode1222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl1222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl1222222, materializeModifier1222222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i3922222 = (i3822222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            RowScopeInstance rowScopeInstance622222 = RowScopeInstance.INSTANCE;
                            int i4022222 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                            IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate32222).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                            SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Modifier modifier722222 = Modifier.INSTANCE;
                            MeasurePolicy columnMeasurePolicy422222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode1322222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap1322222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier1322222 = ComposedModifierKt.materializeModifier($composer2, modifier722222);
                            Function0 constructor1322222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i4122222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl1322222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl1322222, columnMeasurePolicy422222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl1322222, currentCompositionLocalMap1322222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl1322222, Integer.valueOf(hashCode1322222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl1322222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl1322222, materializeModifier1322222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i4222222 = (i4122222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance422222 = ColumnScopeInstance.INSTANCE;
                            int i4322222 = ((0 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                            TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                            TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate32222).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate32222).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            boolean mapDark22222 = DashScreen$lambda$0(ui$delegate32222).getMapDark();
                            ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                            if ((($dirty & 14) ^ 6) <= 4) {
                            }
                            Object rememberedValue1922222 = $composer2.rememberedValue();
                            if (z4) {
                            }
                            Object obj622222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj72) {
                                    return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                                }
                            };
                            $composer2.updateRememberedValue(obj622222);
                            rememberedValue1922222 = obj622222;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            WayfindrComponentsKt.WayfindrToggle(mapDark22222, (Function1) rememberedValue1922222, $composer2, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                            Alignment.Vertical centerVertically722222 = Alignment.INSTANCE.getCenterVertically();
                            Arrangement.Horizontal m686spacedBy0680j_4222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                            Modifier fillMaxWidth$default22222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                            MeasurePolicy rowMeasurePolicy822222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4222222, centerVertically722222, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode1422222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap1422222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier1422222 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default22222);
                            Function0 constructor1422222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i4422222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl1422222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl1422222, rowMeasurePolicy822222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl1422222, currentCompositionLocalMap1422222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl1422222, Integer.valueOf(hashCode1422222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl1422222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl1422222, materializeModifier1422222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i4522222 = (i4422222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                            int i4622222 = ((438 >> 6) & 112) | 6;
                            RowScope rowScope222222 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                            Alignment.Horizontal centerHorizontally32222 = Alignment.INSTANCE.getCenterHorizontally();
                            Modifier m817paddingVpY3zN4422222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope222222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            MeasurePolicy columnMeasurePolicy522222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally32222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode1522222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap1522222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier1522222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4422222);
                            Function0 constructor1522222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i4722222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl1522222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl1522222, columnMeasurePolicy522222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl1522222, currentCompositionLocalMap1522222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl1522222, Integer.valueOf(hashCode1522222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl1522222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl1522222, materializeModifier1522222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i4822222 = (i4722222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance522222 = ColumnScopeInstance.INSTANCE;
                            int i4922222 = ((384 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                            float m7908constructorimpl22222 = Dp.m7908constructorimpl(128);
                            ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                            rememberedValue11 = $composer2.rememberedValue();
                            if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                            }
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl22222, (Function1) rememberedValue11, null, $composer2, 54, 4);
                            SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                            WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            Alignment.Horizontal centerHorizontally222222 = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.Vertical m686spacedBy0680j_4322222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                            ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                            Modifier modifier822222 = Modifier.INSTANCE;
                            MeasurePolicy columnMeasurePolicy622222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4322222, centerHorizontally222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                            ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                            int hashCode1622222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                            CompositionLocalMap currentCompositionLocalMap1622222 = $composer2.getCurrentCompositionLocalMap();
                            Modifier materializeModifier1622222 = ComposedModifierKt.materializeModifier($composer2, modifier822222);
                            Function0 constructor1622222 = ComposeUiNode.INSTANCE.getConstructor();
                            int i5022222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                            if (!($composer2.getApplier() instanceof Applier)) {
                            }
                            $composer2.startReusableNode();
                            if ($composer2.getInserting()) {
                            }
                            Composer m4364constructorimpl1622222 = Updater.m4364constructorimpl($composer2);
                            Updater.m4372setimpl(m4364constructorimpl1622222, columnMeasurePolicy622222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4372setimpl(m4364constructorimpl1622222, currentCompositionLocalMap1622222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Updater.m4368initimpl(m4364constructorimpl1622222, Integer.valueOf(hashCode1622222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                            Updater.m4370reconcileimpl(m4364constructorimpl1622222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                            Updater.m4372setimpl(m4364constructorimpl1622222, materializeModifier1622222, ComposeUiNode.INSTANCE.getSetModifier());
                            int i5122222 = (i5022222 >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                            ColumnScopeInstance columnScopeInstance622222 = ColumnScopeInstance.INSTANCE;
                            int i5222222 = ((432 >> 6) & 112) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                            ImageVector plus22222 = WayfindrIcons.INSTANCE.getPlus();
                            ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                            z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                            Object rememberedValue2022222 = $composer2.rememberedValue();
                            if (z5) {
                            }
                            rememberedValue2022222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                                }
                            };
                            $composer2.updateRememberedValue(rememberedValue2022222);
                            ComposerKt.sourceInformationMarkerEnd(composer);
                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus22222, (Function0) rememberedValue2022222, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                            Composer composer522222 = composer;
                            TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate32222).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer522222, 1597440, 0, 261930);
                            ImageVector minus22222 = WayfindrIcons.INSTANCE.getMinus();
                            ComposerKt.sourceInformationMarkerStart(composer522222, 2044399839, str);
                            z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                            Object rememberedValue2122222 = composer522222.rememberedValue();
                            if (z6) {
                            }
                            rememberedValue2122222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                                }
                            };
                            composer522222.updateRememberedValue(rememberedValue2122222);
                            ComposerKt.sourceInformationMarkerEnd(composer2);
                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus22222, (Function0) rememberedValue2122222, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                            Composer composer622222 = composer2;
                            ImageVector recenter22222 = WayfindrIcons.INSTANCE.getRecenter();
                            ComposerKt.sourceInformationMarkerStart(composer622222, 2044402995, str);
                            z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                            Object rememberedValue2222222 = composer622222.rememberedValue();
                            if (z7) {
                            }
                            rememberedValue2222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate22);
                                }
                            };
                            composer622222.updateRememberedValue(rememberedValue2222222);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter22222, (Function0) rememberedValue2222222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                            ComposerKt.sourceInformationMarkerEnd(composer3);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (streaming) {
                            }
                            $composer2.startReplaceGroup(-1925928760);
                            $composer2.endReplaceGroup();
                            if (streaming) {
                            }
                            $composer2.endReplaceGroup();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            $composer2.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (ComposerKt.isTraceInProgress()) {
                            }
                            vm5 = vm4;
                        }
                    } else {
                        joystickVelocity$delegate = joystickVelocity$delegate522;
                    }
                    Object obj422 = (Function2) new DashScreenKt$DashScreen$1$1(ui$delegate, pan$delegate22, null);
                    $composer2.updateRememberedValue(obj422);
                    rememberedValue1422 = obj422;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(lastButton22, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue1422, $composer2, 0);
                    Offset m4838boximpl222 = Offset.m4838boximpl(DashScreen$lambda$26(joystickVelocity$delegate));
                    Boolean valueOf2222 = Boolean.valueOf(DashScreen$lambda$23(adjustMode$delegate));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980928751, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue15222 = $composer2.rememberedValue();
                    if (z) {
                    }
                    MutableState joystickVelocity$delegate6222 = joystickVelocity$delegate;
                    DashViewModel vm6222 = vm2;
                    context2 = context;
                    adjustMode$delegate2 = adjustMode$delegate;
                    essentialPermissions3 = essentialPermissions2;
                    joystickVelocity$delegate2 = joystickVelocity$delegate6222;
                    vm2 = vm6222;
                    dashScreenKt$DashScreen$2$1 = new DashScreenKt$DashScreen$2$1(vm6222, adjustMode$delegate2, joystickVelocity$delegate6222, pan$delegate22, null);
                    $composer2.updateRememberedValue(dashScreenKt$DashScreen$2$1);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(m4838boximpl222, valueOf2222, (Function2) dashScreenKt$DashScreen$2$1, $composer2, 0);
                    if (DashScreen$lambda$0(ui$delegate).getStage() == ConnStage.STREAMING) {
                    }
                    Object[] objArr222 = new Object[0];
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980957065, str);
                    rememberedValue9 = $composer2.rememberedValue();
                    if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    MutableState autoConnectTried$delegate222 = (MutableState) RememberSaveableKt.rememberSaveable(objArr222, (Function0) rememberedValue9, $composer2, 48);
                    Boolean valueOf3222 = Boolean.valueOf(DashScreen$lambda$3(autoConnectOnStart$delegate222));
                    ConnStage stage2222 = DashScreen$lambda$0(ui$delegate).getStage();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980959635, str);
                    String[] essentialPermissions6222 = essentialPermissions3;
                    changed3 = (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changed(autoConnectOnStart$delegate222) | $composer2.changed(autoConnectTried$delegate222) | $composer2.changed(ui$delegate) | $composer2.changedInstance(essentialPermissions3) | $composer2.changedInstance(context2);
                    Object rememberedValue16222 = $composer2.rememberedValue();
                    if (changed3) {
                    }
                    Object dashScreenKt$DashScreen$3$1222 = new DashScreenKt$DashScreen$3$1(vm2, autoConnectOnStart$delegate, autoConnectTried$delegate222, ui$delegate, essentialPermissions6222, context2, null);
                    ui$delegate2 = ui$delegate;
                    essentialPermissions4 = essentialPermissions6222;
                    obj = (Function2) dashScreenKt$DashScreen$3$1222;
                    $composer2.updateRememberedValue(obj);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(valueOf3222, stage2222, (Function2) obj, $composer2, 0);
                    State uiState32222 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                    ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner2222 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object consume22222 = $composer2.consume(localLifecycleOwner2222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    final LifecycleOwner lifecycleOwner2222 = (LifecycleOwner) consume22222;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                    changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState32222) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner2222);
                    final String[] essentialPermissions72222 = essentialPermissions4;
                    Object rememberedValue172222 = $composer2.rememberedValue();
                    if (changedInstance3) {
                    }
                    final Context context32222 = context2;
                    final DashViewModel vm72222 = vm2;
                    final MutableState batteryOk$delegate32222 = batteryOk$delegate2;
                    final State uiState42222 = uiState;
                    Object obj52222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj623) {
                            return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context32222, uiState42222, vm72222, batteryOk$delegate32222, essentialPermissions72222, (DisposableEffectScope) obj623);
                        }
                    };
                    uiState2 = uiState42222;
                    essentialPermissions72222 = essentialPermissions72222;
                    rememberedValue172222 = obj52222;
                    $composer2.updateRememberedValue(rememberedValue172222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.DisposableEffect(lifecycleOwner2222, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue172222, $composer2, 0);
                    Modifier m820paddingqDBjuR0$default22222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy72222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode172222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap172222 = $composer2.getCurrentCompositionLocalMap();
                    final DashViewModel vm822222 = vm2;
                    Modifier materializeModifier172222 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default22222);
                    Function0 constructor172222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4102222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl172222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl172222, columnMeasurePolicy72222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl172222, currentCompositionLocalMap172222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl172222, Integer.valueOf(hashCode172222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl172222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl172222, materializeModifier172222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i532222 = (i4102222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance72222 = ColumnScopeInstance.INSTANCE;
                    int i622222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                    streaming = streaming2;
                    final State ui$delegate322222 = ui$delegate2;
                    WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj6223, Object obj72) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming2, ui$delegate2, (Composer) obj6223, ((Integer) obj72).intValue());
                        }
                    }, $composer2, 54), $composer2, 1576368, 49);
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Alignment center22222 = Alignment.INSTANCE.getCenter();
                    Modifier m818paddingVpY3zN4$default22222 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy32222 = BoxKt.maybeCachedBoxMeasurePolicy(center22222, false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier222222 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default22222);
                    Function0 constructor222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i722222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl222222, maybeCachedBoxMeasurePolicy32222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl222222, currentCompositionLocalMap222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl222222, Integer.valueOf(hashCode222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl222222, materializeModifier222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i822222 = (i722222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance32222 = BoxScopeInstance.INSTANCE;
                    int i922222 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Modifier m273borderxT4_qwU22222 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode322222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap322222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier322222 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU22222);
                    Function0 constructor322222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i1022222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl322222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl322222, maybeCachedBoxMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl322222, currentCompositionLocalMap322222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl322222, Integer.valueOf(hashCode322222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl322222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl322222, materializeModifier322222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i1122222 = (i1022222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance222222 = BoxScopeInstance.INSTANCE;
                    int i1222222 = ((0 >> 6) & 112) | 6;
                    Composer composer422222 = $composer2;
                    ComposerKt.sourceInformationMarkerStart(composer422222, 2024465622, "C:DashScreen.kt#cef2n4");
                    DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                    if (DashScreen$lambda$1 != null) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer422222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                    maneuver = DashScreen$lambda$0(ui$delegate322222).getManeuver();
                    if (maneuver == null) {
                    }
                    Arrangement.Horizontal m686spacedBy0680j_442222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier92222 = Modifier.INSTANCE;
                    Alignment.Vertical top22222 = Alignment.INSTANCE.getTop();
                    MeasurePolicy rowMeasurePolicy92222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_442222, top22222, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode422222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap422222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier422222 = ComposedModifierKt.materializeModifier($composer2, modifier92222);
                    Function0 constructor422222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i1322222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl422222 = Updater.m4364constructorimpl($composer2);
                    MeasurePolicy measurePolicy22222 = rowMeasurePolicy92222;
                    Updater.m4372setimpl(m4364constructorimpl422222, measurePolicy22222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl422222, currentCompositionLocalMap422222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl422222, Integer.valueOf(hashCode422222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl422222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl422222, materializeModifier422222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i1422222 = (i1322222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i1522222 = ((48 >> 6) & 112) | 6;
                    RowScope rowScope32222 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                    $composer2.startReplaceGroup(-2140347244);
                    ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                    Triple[] tripleArr222222 = new Triple[3];
                    remainingKm = DashScreen$lambda$0(ui$delegate322222).getRemainingKm();
                    if (remainingKm != null) {
                    }
                    str2 = "—";
                    tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate322222).getRemainingKm() != null ? "km" : "", "Kalan");
                    etaMinutes = DashScreen$lambda$0(ui$delegate322222).getEtaMinutes();
                    if (etaMinutes != null) {
                    }
                    str3 = "—";
                    Integer etaMinutes222222 = DashScreen$lambda$0(ui$delegate322222).getEtaMinutes();
                    tripleArr[1] = new Triple(str3, etaMinutes222222 != null ? etaMinutes222222.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                    tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate322222).getMapZoom(), "", "Yakınlaştırma");
                    Iterable listOf22222 = CollectionsKt.listOf((Object[]) tripleArr);
                    int i1622222 = 0;
                    it = listOf22222.iterator();
                    while (it.hasNext()) {
                    }
                    DashViewModel vm922222 = vm3;
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically82222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween42222 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN452222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy222222 = RowKt.rowMeasurePolicy(spaceBetween42222, centerVertically82222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode522222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap522222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier522222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN452222);
                    Function0 constructor522222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i1722222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl522222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl522222, rowMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl522222, currentCompositionLocalMap522222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl522222, Integer.valueOf(hashCode522222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl522222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl522222, materializeModifier522222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i1822222 = (i1722222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance72222 = RowScopeInstance.INSTANCE;
                    int i1922222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically222222 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier222222 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy322222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode622222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap622222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier622222 = ComposedModifierKt.materializeModifier($composer2, modifier222222);
                    Function0 constructor622222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i2022222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl622222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl622222, rowMeasurePolicy322222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl622222, currentCompositionLocalMap622222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl622222, Integer.valueOf(hashCode622222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl622222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl622222, materializeModifier622222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2122222 = (i2022222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance222222 = RowScopeInstance.INSTANCE;
                    int i2222222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier322222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode722222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap722222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier722222 = ComposedModifierKt.materializeModifier($composer2, modifier322222);
                    Function0 constructor722222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i2322222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl722222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl722222, columnMeasurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl722222, currentCompositionLocalMap722222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl722222, Integer.valueOf(hashCode722222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl722222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl722222, materializeModifier722222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2422222 = (i2322222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance222222 = ColumnScopeInstance.INSTANCE;
                    int i2522222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean DashScreen$lambda$2322222 = DashScreen$lambda$23(adjustMode$delegate2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                    rememberedValue10 = $composer2.rememberedValue();
                    if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$2322222, (Function1) rememberedValue10, $composer2, 48);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically322222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween222222 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN4222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy422222 = RowKt.rowMeasurePolicy(spaceBetween222222, centerVertically322222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode822222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap822222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier822222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4222222);
                    Function0 constructor822222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i2622222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl822222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl822222, rowMeasurePolicy422222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl822222, currentCompositionLocalMap822222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl822222, Integer.valueOf(hashCode822222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl822222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl822222, materializeModifier822222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i2722222 = (i2622222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance322222 = RowScopeInstance.INSTANCE;
                    int i2822222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically422222 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier422222 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy522222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically422222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode922222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap922222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier922222 = ComposedModifierKt.materializeModifier($composer2, modifier422222);
                    Function0 constructor922222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i2922222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl922222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl922222, rowMeasurePolicy522222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl922222, currentCompositionLocalMap922222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl922222, Integer.valueOf(hashCode922222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl922222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl922222, materializeModifier922222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3022222 = (i2922222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance422222 = RowScopeInstance.INSTANCE;
                    int i3122222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate322222).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier522222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy322222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode1022222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap1022222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier1022222 = ComposedModifierKt.materializeModifier($composer2, modifier522222);
                    Function0 constructor1022222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i3222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl1022222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl1022222, columnMeasurePolicy322222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl1022222, currentCompositionLocalMap1022222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl1022222, Integer.valueOf(hashCode1022222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl1022222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl1022222, materializeModifier1022222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3322222 = (i3222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance322222 = ColumnScopeInstance.INSTANCE;
                    int i3422222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate322222).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean headingUp22222 = DashScreen$lambda$0(ui$delegate322222).getHeadingUp();
                    ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                    if ((($dirty & 14) ^ 6) > 4) {
                    }
                    if (($dirty & 6) != 4) {
                    }
                    z2 = true;
                    z3 = z2;
                    Object rememberedValue18222222 = $composer2.rememberedValue();
                    if (z3) {
                    }
                    rememberedValue18222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj6222222) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj6222222).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue18222222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(headingUp22222, (Function1) rememberedValue18222222, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically5222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween3222222 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN43222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy6222222 = RowKt.rowMeasurePolicy(spaceBetween3222222, centerVertically5222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode11222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap11222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier11222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN43222222);
                    Function0 constructor11222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i35222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl11222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl11222222, rowMeasurePolicy6222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl11222222, currentCompositionLocalMap11222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl11222222, Integer.valueOf(hashCode11222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl11222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl11222222, materializeModifier11222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i36222222 = (i35222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance5222222 = RowScopeInstance.INSTANCE;
                    int i37222222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically6222222 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier6222222 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy7222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically6222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode12222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap12222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier12222222 = ComposedModifierKt.materializeModifier($composer2, modifier6222222);
                    Function0 constructor12222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i38222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl12222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl12222222, rowMeasurePolicy7222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl12222222, currentCompositionLocalMap12222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl12222222, Integer.valueOf(hashCode12222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl12222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl12222222, materializeModifier12222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i39222222 = (i38222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance6222222 = RowScopeInstance.INSTANCE;
                    int i40222222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate322222).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier7222222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy4222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode13222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap13222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier13222222 = ComposedModifierKt.materializeModifier($composer2, modifier7222222);
                    Function0 constructor13222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i41222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl13222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl13222222, columnMeasurePolicy4222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl13222222, currentCompositionLocalMap13222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl13222222, Integer.valueOf(hashCode13222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl13222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl13222222, materializeModifier13222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i42222222 = (i41222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance4222222 = ColumnScopeInstance.INSTANCE;
                    int i43222222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate322222).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate322222).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean mapDark222222 = DashScreen$lambda$0(ui$delegate322222).getMapDark();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue19222222 = $composer2.rememberedValue();
                    if (z4) {
                    }
                    Object obj6222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj72) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(obj6222222);
                    rememberedValue19222222 = obj6222222;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(mapDark222222, (Function1) rememberedValue19222222, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically7222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal m686spacedBy0680j_42222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                    Modifier fillMaxWidth$default222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy8222222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_42222222, centerVertically7222222, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode14222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap14222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier14222222 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default222222);
                    Function0 constructor14222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i44222222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl14222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl14222222, rowMeasurePolicy8222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl14222222, currentCompositionLocalMap14222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl14222222, Integer.valueOf(hashCode14222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl14222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl14222222, materializeModifier14222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i45222222 = (i44222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i46222222 = ((438 >> 6) & 112) | 6;
                    RowScope rowScope2222222 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                    Alignment.Horizontal centerHorizontally322222 = Alignment.INSTANCE.getCenterHorizontally();
                    Modifier m817paddingVpY3zN44222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope2222222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy5222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally322222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode15222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap15222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier15222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN44222222);
                    Function0 constructor15222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i47222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl15222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl15222222, columnMeasurePolicy5222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl15222222, currentCompositionLocalMap15222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl15222222, Integer.valueOf(hashCode15222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl15222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl15222222, materializeModifier15222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i48222222 = (i47222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance5222222 = ColumnScopeInstance.INSTANCE;
                    int i49222222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                    float m7908constructorimpl222222 = Dp.m7908constructorimpl(128);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                    rememberedValue11 = $composer2.rememberedValue();
                    if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl222222, (Function1) rememberedValue11, null, $composer2, 54, 4);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                    WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Alignment.Horizontal centerHorizontally2222222 = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.Vertical m686spacedBy0680j_43222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier8222222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy6222222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_43222222, centerHorizontally2222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode16222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap16222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier16222222 = ComposedModifierKt.materializeModifier($composer2, modifier8222222);
                    Function0 constructor16222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i50222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl16222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl16222222, columnMeasurePolicy6222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl16222222, currentCompositionLocalMap16222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl16222222, Integer.valueOf(hashCode16222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl16222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl16222222, materializeModifier16222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i51222222 = (i50222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance6222222 = ColumnScopeInstance.INSTANCE;
                    int i52222222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                    ImageVector plus222222 = WayfindrIcons.INSTANCE.getPlus();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                    z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue20222222 = $composer2.rememberedValue();
                    if (z5) {
                    }
                    rememberedValue20222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue20222222);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus222222, (Function0) rememberedValue20222222, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                    Composer composer5222222 = composer;
                    TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate322222).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer5222222, 1597440, 0, 261930);
                    ImageVector minus222222 = WayfindrIcons.INSTANCE.getMinus();
                    ComposerKt.sourceInformationMarkerStart(composer5222222, 2044399839, str);
                    z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue21222222 = composer5222222.rememberedValue();
                    if (z6) {
                    }
                    rememberedValue21222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                        }
                    };
                    composer5222222.updateRememberedValue(rememberedValue21222222);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus222222, (Function0) rememberedValue21222222, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                    Composer composer6222222 = composer2;
                    ImageVector recenter222222 = WayfindrIcons.INSTANCE.getRecenter();
                    ComposerKt.sourceInformationMarkerStart(composer6222222, 2044402995, str);
                    z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue22222222 = composer6222222.rememberedValue();
                    if (z7) {
                    }
                    rememberedValue22222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate22);
                        }
                    };
                    composer6222222.updateRememberedValue(rememberedValue22222222);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter222222, (Function0) rememberedValue22222222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (streaming) {
                    }
                    $composer2.startReplaceGroup(-1925928760);
                    $composer2.endReplaceGroup();
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    vm5 = vm4;
                }
                str = "CC(remember):DashScreen.kt#9igjgp";
                Object obj322 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj423) {
                        return DashScreenKt.DashScreen$lambda$18$lambda$17(essentialPermissions, context, vm2, batteryOk$delegate, batteryLauncher, (Map) obj423);
                    }
                };
                essentialPermissions2 = essentialPermissions;
                batteryOk$delegate2 = batteryOk$delegate;
                batteryLauncher2 = batteryLauncher;
                $composer2.updateRememberedValue(obj322);
                rememberedValue1322 = obj322;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ManagedActivityResultLauncher permissionLauncher2222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions22, (Function1) rememberedValue1322, $composer2, 0);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980901688, str);
                rememberedValue6 = $composer2.rememberedValue();
                if (rememberedValue6 != Composer.INSTANCE.getEmpty()) {
                }
                final MutableState pan$delegate222 = (MutableState) rememberedValue6;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980903697, str);
                rememberedValue7 = $composer2.rememberedValue();
                if (rememberedValue7 != Composer.INSTANCE.getEmpty()) {
                }
                MutableState adjustMode$delegate3222 = (MutableState) rememberedValue7;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980905688, str);
                rememberedValue8 = $composer2.rememberedValue();
                if (rememberedValue8 != Composer.INSTANCE.getEmpty()) {
                }
                MutableState joystickVelocity$delegate5222 = (MutableState) rememberedValue8;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                String lastButton222 = DashScreen$lambda$0(ui$delegate).getLastButton();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980911546, str);
                changed2 = $composer2.changed(ui$delegate);
                Object rememberedValue14222 = $composer2.rememberedValue();
                if (changed2) {
                }
                Object obj4222 = (Function2) new DashScreenKt$DashScreen$1$1(ui$delegate, pan$delegate222, null);
                $composer2.updateRememberedValue(obj4222);
                rememberedValue14222 = obj4222;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(lastButton222, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue14222, $composer2, 0);
                Offset m4838boximpl2222 = Offset.m4838boximpl(DashScreen$lambda$26(joystickVelocity$delegate));
                Boolean valueOf22222 = Boolean.valueOf(DashScreen$lambda$23(adjustMode$delegate));
                ComposerKt.sourceInformationMarkerStart($composer2, 1980928751, str);
                if ((($dirty & 14) ^ 6) <= 4) {
                }
                Object rememberedValue152222 = $composer2.rememberedValue();
                if (z) {
                }
                MutableState joystickVelocity$delegate62222 = joystickVelocity$delegate;
                DashViewModel vm62222 = vm2;
                context2 = context;
                adjustMode$delegate2 = adjustMode$delegate;
                essentialPermissions3 = essentialPermissions2;
                joystickVelocity$delegate2 = joystickVelocity$delegate62222;
                vm2 = vm62222;
                dashScreenKt$DashScreen$2$1 = new DashScreenKt$DashScreen$2$1(vm62222, adjustMode$delegate2, joystickVelocity$delegate62222, pan$delegate222, null);
                $composer2.updateRememberedValue(dashScreenKt$DashScreen$2$1);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(m4838boximpl2222, valueOf22222, (Function2) dashScreenKt$DashScreen$2$1, $composer2, 0);
                if (DashScreen$lambda$0(ui$delegate).getStage() == ConnStage.STREAMING) {
                }
                Object[] objArr2222 = new Object[0];
                ComposerKt.sourceInformationMarkerStart($composer2, 1980957065, str);
                rememberedValue9 = $composer2.rememberedValue();
                if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                MutableState autoConnectTried$delegate2222 = (MutableState) RememberSaveableKt.rememberSaveable(objArr2222, (Function0) rememberedValue9, $composer2, 48);
                Boolean valueOf32222 = Boolean.valueOf(DashScreen$lambda$3(autoConnectOnStart$delegate222));
                ConnStage stage22222 = DashScreen$lambda$0(ui$delegate).getStage();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980959635, str);
                String[] essentialPermissions62222 = essentialPermissions3;
                changed3 = (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changed(autoConnectOnStart$delegate222) | $composer2.changed(autoConnectTried$delegate2222) | $composer2.changed(ui$delegate) | $composer2.changedInstance(essentialPermissions3) | $composer2.changedInstance(context2);
                Object rememberedValue162222 = $composer2.rememberedValue();
                if (changed3) {
                }
                Object dashScreenKt$DashScreen$3$12222 = new DashScreenKt$DashScreen$3$1(vm2, autoConnectOnStart$delegate, autoConnectTried$delegate2222, ui$delegate, essentialPermissions62222, context2, null);
                ui$delegate2 = ui$delegate;
                essentialPermissions4 = essentialPermissions62222;
                obj = (Function2) dashScreenKt$DashScreen$3$12222;
                $composer2.updateRememberedValue(obj);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(valueOf32222, stage22222, (Function2) obj, $composer2, 0);
                State uiState322222 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner22222 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume222222 = $composer2.consume(localLifecycleOwner22222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final LifecycleOwner lifecycleOwner22222 = (LifecycleOwner) consume222222;
                ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState322222) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner22222);
                final String[] essentialPermissions722222 = essentialPermissions4;
                Object rememberedValue1722222 = $composer2.rememberedValue();
                if (changedInstance3) {
                }
                final Context context322222 = context2;
                final DashViewModel vm722222 = vm2;
                final MutableState batteryOk$delegate322222 = batteryOk$delegate2;
                final State uiState422222 = uiState;
                Object obj522222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj623) {
                        return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context322222, uiState422222, vm722222, batteryOk$delegate322222, essentialPermissions722222, (DisposableEffectScope) obj623);
                    }
                };
                uiState2 = uiState422222;
                essentialPermissions722222 = essentialPermissions722222;
                rememberedValue1722222 = obj522222;
                $composer2.updateRememberedValue(rememberedValue1722222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.DisposableEffect(lifecycleOwner22222, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue1722222, $composer2, 0);
                Modifier m820paddingqDBjuR0$default222222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy722222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode1722222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap1722222 = $composer2.getCurrentCompositionLocalMap();
                final DashViewModel vm8222222 = vm2;
                Modifier materializeModifier1722222 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default222222);
                Function0 constructor1722222 = ComposeUiNode.INSTANCE.getConstructor();
                int i41022222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl1722222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl1722222, columnMeasurePolicy722222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl1722222, currentCompositionLocalMap1722222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl1722222, Integer.valueOf(hashCode1722222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl1722222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl1722222, materializeModifier1722222, ComposeUiNode.INSTANCE.getSetModifier());
                int i5322222 = (i41022222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance722222 = ColumnScopeInstance.INSTANCE;
                int i6222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                streaming = streaming2;
                final State ui$delegate3222222 = ui$delegate2;
                WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj6223, Object obj72) {
                        return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming2, ui$delegate2, (Composer) obj6223, ((Integer) obj72).intValue());
                    }
                }, $composer2, 54), $composer2, 1576368, 49);
                if (streaming) {
                }
                $composer2.endReplaceGroup();
                Alignment center222222 = Alignment.INSTANCE.getCenter();
                Modifier m818paddingVpY3zN4$default222222 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy322222 = BoxKt.maybeCachedBoxMeasurePolicy(center222222, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode2222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap2222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier2222222 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default222222);
                Function0 constructor2222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i7222222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl2222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl2222222, maybeCachedBoxMeasurePolicy322222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl2222222, currentCompositionLocalMap2222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl2222222, Integer.valueOf(hashCode2222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl2222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl2222222, materializeModifier2222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i8222222 = (i7222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance322222 = BoxScopeInstance.INSTANCE;
                int i9222222 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                if (streaming) {
                }
                $composer2.endReplaceGroup();
                Modifier m273borderxT4_qwU222222 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy2222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode3222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap3222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier3222222 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU222222);
                Function0 constructor3222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i10222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl3222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl3222222, maybeCachedBoxMeasurePolicy2222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl3222222, currentCompositionLocalMap3222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl3222222, Integer.valueOf(hashCode3222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl3222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl3222222, materializeModifier3222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i11222222 = (i10222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2222222 = BoxScopeInstance.INSTANCE;
                int i12222222 = ((0 >> 6) & 112) | 6;
                Composer composer4222222 = $composer2;
                ComposerKt.sourceInformationMarkerStart(composer4222222, 2024465622, "C:DashScreen.kt#cef2n4");
                DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                if (DashScreen$lambda$1 != null) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer4222222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                maneuver = DashScreen$lambda$0(ui$delegate3222222).getManeuver();
                if (maneuver == null) {
                }
                Arrangement.Horizontal m686spacedBy0680j_4422222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier922222 = Modifier.INSTANCE;
                Alignment.Vertical top222222 = Alignment.INSTANCE.getTop();
                MeasurePolicy rowMeasurePolicy922222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4422222, top222222, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode4222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap4222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier4222222 = ComposedModifierKt.materializeModifier($composer2, modifier922222);
                Function0 constructor4222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i13222222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl4222222 = Updater.m4364constructorimpl($composer2);
                MeasurePolicy measurePolicy222222 = rowMeasurePolicy922222;
                Updater.m4372setimpl(m4364constructorimpl4222222, measurePolicy222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl4222222, currentCompositionLocalMap4222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl4222222, Integer.valueOf(hashCode4222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl4222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl4222222, materializeModifier4222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i14222222 = (i13222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i15222222 = ((48 >> 6) & 112) | 6;
                RowScope rowScope322222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                $composer2.startReplaceGroup(-2140347244);
                ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                Triple[] tripleArr2222222 = new Triple[3];
                remainingKm = DashScreen$lambda$0(ui$delegate3222222).getRemainingKm();
                if (remainingKm != null) {
                }
                str2 = "—";
                tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate3222222).getRemainingKm() != null ? "km" : "", "Kalan");
                etaMinutes = DashScreen$lambda$0(ui$delegate3222222).getEtaMinutes();
                if (etaMinutes != null) {
                }
                str3 = "—";
                Integer etaMinutes2222222 = DashScreen$lambda$0(ui$delegate3222222).getEtaMinutes();
                tripleArr[1] = new Triple(str3, etaMinutes2222222 != null ? etaMinutes2222222.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate3222222).getMapZoom(), "", "Yakınlaştırma");
                Iterable listOf222222 = CollectionsKt.listOf((Object[]) tripleArr);
                int i16222222 = 0;
                it = listOf222222.iterator();
                while (it.hasNext()) {
                }
                DashViewModel vm9222222 = vm3;
                $composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                Alignment.Vertical centerVertically822222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal spaceBetween422222 = Arrangement.INSTANCE.getSpaceBetween();
                Modifier m817paddingVpY3zN4522222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy2222222 = RowKt.rowMeasurePolicy(spaceBetween422222, centerVertically822222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode5222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap5222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier5222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4522222);
                Function0 constructor5222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i17222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl5222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl5222222, rowMeasurePolicy2222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl5222222, currentCompositionLocalMap5222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl5222222, Integer.valueOf(hashCode5222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl5222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl5222222, materializeModifier5222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i18222222 = (i17222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance722222 = RowScopeInstance.INSTANCE;
                int i19222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                Alignment.Vertical centerVertically2222222 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier2222222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy3222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode6222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap6222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier6222222 = ComposedModifierKt.materializeModifier($composer2, modifier2222222);
                Function0 constructor6222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i20222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl6222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl6222222, rowMeasurePolicy3222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl6222222, currentCompositionLocalMap6222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl6222222, Integer.valueOf(hashCode6222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl6222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl6222222, materializeModifier6222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i21222222 = (i20222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance2222222 = RowScopeInstance.INSTANCE;
                int i22222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier3222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy2222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode7222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap7222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier7222222 = ComposedModifierKt.materializeModifier($composer2, modifier3222222);
                Function0 constructor7222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i23222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl7222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl7222222, columnMeasurePolicy2222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl7222222, currentCompositionLocalMap7222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl7222222, Integer.valueOf(hashCode7222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl7222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl7222222, materializeModifier7222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i24222222 = (i23222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance2222222 = ColumnScopeInstance.INSTANCE;
                int i25222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean DashScreen$lambda$23222222 = DashScreen$lambda$23(adjustMode$delegate2);
                ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                rememberedValue10 = $composer2.rememberedValue();
                if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$23222222, (Function1) rememberedValue10, $composer2, 48);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                Alignment.Vertical centerVertically3222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal spaceBetween2222222 = Arrangement.INSTANCE.getSpaceBetween();
                Modifier m817paddingVpY3zN42222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy4222222 = RowKt.rowMeasurePolicy(spaceBetween2222222, centerVertically3222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode8222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap8222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier8222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN42222222);
                Function0 constructor8222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i26222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl8222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl8222222, rowMeasurePolicy4222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl8222222, currentCompositionLocalMap8222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl8222222, Integer.valueOf(hashCode8222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl8222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl8222222, materializeModifier8222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i27222222 = (i26222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3222222 = RowScopeInstance.INSTANCE;
                int i28222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                Alignment.Vertical centerVertically4222222 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier4222222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy5222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode9222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap9222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier9222222 = ComposedModifierKt.materializeModifier($composer2, modifier4222222);
                Function0 constructor9222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i29222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl9222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl9222222, rowMeasurePolicy5222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl9222222, currentCompositionLocalMap9222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl9222222, Integer.valueOf(hashCode9222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl9222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl9222222, materializeModifier9222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i30222222 = (i29222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4222222 = RowScopeInstance.INSTANCE;
                int i31222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate3222222).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier5222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy3222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode10222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap10222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier10222222 = ComposedModifierKt.materializeModifier($composer2, modifier5222222);
                Function0 constructor10222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i32222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl10222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl10222222, columnMeasurePolicy3222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl10222222, currentCompositionLocalMap10222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl10222222, Integer.valueOf(hashCode10222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl10222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl10222222, materializeModifier10222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i33222222 = (i32222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance3222222 = ColumnScopeInstance.INSTANCE;
                int i34222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate3222222).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean headingUp222222 = DashScreen$lambda$0(ui$delegate3222222).getHeadingUp();
                ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                if ((($dirty & 14) ^ 6) > 4) {
                }
                if (($dirty & 6) != 4) {
                }
                z2 = true;
                z3 = z2;
                Object rememberedValue182222222 = $composer2.rememberedValue();
                if (z3) {
                }
                rememberedValue182222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj62222222) {
                        return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj62222222).booleanValue());
                    }
                };
                $composer2.updateRememberedValue(rememberedValue182222222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                WayfindrComponentsKt.WayfindrToggle(headingUp222222, (Function1) rememberedValue182222222, $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                Alignment.Vertical centerVertically52222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal spaceBetween32222222 = Arrangement.INSTANCE.getSpaceBetween();
                Modifier m817paddingVpY3zN432222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy62222222 = RowKt.rowMeasurePolicy(spaceBetween32222222, centerVertically52222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode112222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap112222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier112222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN432222222);
                Function0 constructor112222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i352222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl112222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl112222222, rowMeasurePolicy62222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl112222222, currentCompositionLocalMap112222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl112222222, Integer.valueOf(hashCode112222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl112222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl112222222, materializeModifier112222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i362222222 = (i352222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance52222222 = RowScopeInstance.INSTANCE;
                int i372222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                Alignment.Vertical centerVertically62222222 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier62222222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy72222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically62222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode122222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap122222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier122222222 = ComposedModifierKt.materializeModifier($composer2, modifier62222222);
                Function0 constructor122222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i382222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl122222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl122222222, rowMeasurePolicy72222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl122222222, currentCompositionLocalMap122222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl122222222, Integer.valueOf(hashCode122222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl122222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl122222222, materializeModifier122222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i392222222 = (i382222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance62222222 = RowScopeInstance.INSTANCE;
                int i402222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate3222222).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier72222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy42222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode132222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap132222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier132222222 = ComposedModifierKt.materializeModifier($composer2, modifier72222222);
                Function0 constructor132222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i412222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl132222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl132222222, columnMeasurePolicy42222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl132222222, currentCompositionLocalMap132222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl132222222, Integer.valueOf(hashCode132222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl132222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl132222222, materializeModifier132222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i422222222 = (i412222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance42222222 = ColumnScopeInstance.INSTANCE;
                int i432222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate3222222).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate3222222).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean mapDark2222222 = DashScreen$lambda$0(ui$delegate3222222).getMapDark();
                ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                if ((($dirty & 14) ^ 6) <= 4) {
                }
                Object rememberedValue192222222 = $composer2.rememberedValue();
                if (z4) {
                }
                Object obj62222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj72) {
                        return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                    }
                };
                $composer2.updateRememberedValue(obj62222222);
                rememberedValue192222222 = obj62222222;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                WayfindrComponentsKt.WayfindrToggle(mapDark2222222, (Function1) rememberedValue192222222, $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                Alignment.Vertical centerVertically72222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal m686spacedBy0680j_422222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                Modifier fillMaxWidth$default2222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy82222222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_422222222, centerVertically72222222, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode142222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap142222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier142222222 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default2222222);
                Function0 constructor142222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i442222222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl142222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl142222222, rowMeasurePolicy82222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl142222222, currentCompositionLocalMap142222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl142222222, Integer.valueOf(hashCode142222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl142222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl142222222, materializeModifier142222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i452222222 = (i442222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i462222222 = ((438 >> 6) & 112) | 6;
                RowScope rowScope22222222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                Alignment.Horizontal centerHorizontally3222222 = Alignment.INSTANCE.getCenterHorizontally();
                Modifier m817paddingVpY3zN442222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope22222222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy52222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally3222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode152222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap152222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier152222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN442222222);
                Function0 constructor152222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i472222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl152222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl152222222, columnMeasurePolicy52222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl152222222, currentCompositionLocalMap152222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl152222222, Integer.valueOf(hashCode152222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl152222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl152222222, materializeModifier152222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i482222222 = (i472222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance52222222 = ColumnScopeInstance.INSTANCE;
                int i492222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                float m7908constructorimpl2222222 = Dp.m7908constructorimpl(128);
                ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                rememberedValue11 = $composer2.rememberedValue();
                if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl2222222, (Function1) rememberedValue11, null, $composer2, 54, 4);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Alignment.Horizontal centerHorizontally22222222 = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical m686spacedBy0680j_432222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier82222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy62222222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_432222222, centerHorizontally22222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode162222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap162222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier162222222 = ComposedModifierKt.materializeModifier($composer2, modifier82222222);
                Function0 constructor162222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i502222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl162222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl162222222, columnMeasurePolicy62222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl162222222, currentCompositionLocalMap162222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl162222222, Integer.valueOf(hashCode162222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl162222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl162222222, materializeModifier162222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i512222222 = (i502222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance62222222 = ColumnScopeInstance.INSTANCE;
                int i522222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                ImageVector plus2222222 = WayfindrIcons.INSTANCE.getPlus();
                ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                Object rememberedValue202222222 = $composer2.rememberedValue();
                if (z5) {
                }
                rememberedValue202222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                    }
                };
                $composer2.updateRememberedValue(rememberedValue202222222);
                ComposerKt.sourceInformationMarkerEnd(composer);
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus2222222, (Function0) rememberedValue202222222, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                Composer composer52222222 = composer;
                TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate3222222).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer52222222, 1597440, 0, 261930);
                ImageVector minus2222222 = WayfindrIcons.INSTANCE.getMinus();
                ComposerKt.sourceInformationMarkerStart(composer52222222, 2044399839, str);
                z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                Object rememberedValue212222222 = composer52222222.rememberedValue();
                if (z6) {
                }
                rememberedValue212222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                    }
                };
                composer52222222.updateRememberedValue(rememberedValue212222222);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus2222222, (Function0) rememberedValue212222222, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                Composer composer62222222 = composer2;
                ImageVector recenter2222222 = WayfindrIcons.INSTANCE.getRecenter();
                ComposerKt.sourceInformationMarkerStart(composer62222222, 2044402995, str);
                z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                Object rememberedValue222222222 = composer62222222.rememberedValue();
                if (z7) {
                }
                rememberedValue222222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate222);
                    }
                };
                composer62222222.updateRememberedValue(rememberedValue222222222);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter2222222, (Function0) rememberedValue222222222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (streaming) {
                }
                $composer2.startReplaceGroup(-1925928760);
                $composer2.endReplaceGroup();
                if (streaming) {
                }
                $composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                }
                vm5 = vm4;
            } else {
                i2 = 6;
                vm2 = vm5;
                $composer2.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                }
                ui$delegate = SnapshotStateKt.collectAsState(vm2.getUi(), null, $composer2, 0, 1);
                State previewFrame$delegate2222 = SnapshotStateKt.collectAsState(vm2.getPreviewFrame(), null, $composer2, 0, 1);
                ProvidableCompositionLocal<Context> localContext222 = AndroidCompositionLocals_androidKt.getLocalContext();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume322 = $composer2.consume(localContext222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                context = (Context) consume322;
                ComposerKt.sourceInformationMarkerStart($composer2, 1980801550, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue = $composer2.rememberedValue();
                if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                }
                DashConfig dashConfig222 = (DashConfig) rememberedValue;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                State autoConnectOnStart$delegate2222 = SnapshotStateKt.collectAsState(dashConfig222.getAutoConnectOnStart(), null, $composer2, 0, 1);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980812331, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue2 = $composer2.rememberedValue();
                if (rememberedValue2 != Composer.INSTANCE.getEmpty()) {
                }
                essentialPermissions = (String[]) rememberedValue2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980821369, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue3 = $composer2.rememberedValue();
                if (rememberedValue3 != Composer.INSTANCE.getEmpty()) {
                }
                requestedPermissions = (String[]) rememberedValue3;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ConnStage stage322 = DashScreen$lambda$0(ui$delegate).getStage();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980834971, "CC(remember):DashScreen.kt#9igjgp");
                changed = $composer2.changed(stage322.ordinal());
                rememberedValue4 = $composer2.rememberedValue();
                if (changed) {
                }
                requestedPermissions2 = requestedPermissions;
                Object valueOf422 = Boolean.valueOf(DeviceReadiness.INSTANCE.hasBackgroundLocation(context));
                $composer2.updateRememberedValue(valueOf422);
                rememberedValue4 = valueOf422;
                boolean hasBackgroundLocation2222 = ((Boolean) rememberedValue4).booleanValue();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980846602, "CC(remember):DashScreen.kt#9igjgp");
                rememberedValue5 = $composer2.rememberedValue();
                if (rememberedValue5 != Composer.INSTANCE.getEmpty()) {
                }
                batteryOk$delegate = (MutableState) rememberedValue5;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ActivityResultContracts.StartActivityForResult startActivityForResult222 = new ActivityResultContracts.StartActivityForResult();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980860754, "CC(remember):DashScreen.kt#9igjgp");
                changedInstance = $composer2.changedInstance(context) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                Object rememberedValue12222 = $composer2.rememberedValue();
                if (changedInstance) {
                }
                Object obj2222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3222) {
                        return DashScreenKt.DashScreen$lambda$14$lambda$13(context, vm2, batteryOk$delegate, (ActivityResult) obj3222);
                    }
                };
                $composer2.updateRememberedValue(obj2222);
                rememberedValue12222 = obj2222;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                batteryLauncher = ActivityResultRegistryKt.rememberLauncherForActivityResult(startActivityForResult222, (Function1) rememberedValue12222, $composer2, 0);
                ActivityResultContracts.RequestMultiplePermissions requestMultiplePermissions222 = new ActivityResultContracts.RequestMultiplePermissions();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980894723, "CC(remember):DashScreen.kt#9igjgp");
                changedInstance2 = $composer2.changedInstance(essentialPermissions) | $composer2.changedInstance(context) | $composer2.changedInstance(batteryLauncher) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4);
                Object rememberedValue13222 = $composer2.rememberedValue();
                if (!changedInstance2) {
                    str = "CC(remember):DashScreen.kt#9igjgp";
                    essentialPermissions2 = essentialPermissions;
                    batteryOk$delegate2 = batteryOk$delegate;
                    batteryLauncher2 = batteryLauncher;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ManagedActivityResultLauncher permissionLauncher22222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions222, (Function1) rememberedValue13222, $composer2, 0);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980901688, str);
                    rememberedValue6 = $composer2.rememberedValue();
                    if (rememberedValue6 != Composer.INSTANCE.getEmpty()) {
                    }
                    final MutableState pan$delegate2222 = (MutableState) rememberedValue6;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980903697, str);
                    rememberedValue7 = $composer2.rememberedValue();
                    if (rememberedValue7 != Composer.INSTANCE.getEmpty()) {
                    }
                    MutableState adjustMode$delegate32222 = (MutableState) rememberedValue7;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980905688, str);
                    rememberedValue8 = $composer2.rememberedValue();
                    if (rememberedValue8 != Composer.INSTANCE.getEmpty()) {
                    }
                    MutableState joystickVelocity$delegate52222 = (MutableState) rememberedValue8;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    String lastButton2222 = DashScreen$lambda$0(ui$delegate).getLastButton();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980911546, str);
                    changed2 = $composer2.changed(ui$delegate);
                    Object rememberedValue142222 = $composer2.rememberedValue();
                    if (changed2) {
                    }
                    Object obj42222 = (Function2) new DashScreenKt$DashScreen$1$1(ui$delegate, pan$delegate2222, null);
                    $composer2.updateRememberedValue(obj42222);
                    rememberedValue142222 = obj42222;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(lastButton2222, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue142222, $composer2, 0);
                    Offset m4838boximpl22222 = Offset.m4838boximpl(DashScreen$lambda$26(joystickVelocity$delegate));
                    Boolean valueOf222222 = Boolean.valueOf(DashScreen$lambda$23(adjustMode$delegate));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980928751, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue1522222 = $composer2.rememberedValue();
                    if (z) {
                    }
                    MutableState joystickVelocity$delegate622222 = joystickVelocity$delegate;
                    DashViewModel vm622222 = vm2;
                    context2 = context;
                    adjustMode$delegate2 = adjustMode$delegate;
                    essentialPermissions3 = essentialPermissions2;
                    joystickVelocity$delegate2 = joystickVelocity$delegate622222;
                    vm2 = vm622222;
                    dashScreenKt$DashScreen$2$1 = new DashScreenKt$DashScreen$2$1(vm622222, adjustMode$delegate2, joystickVelocity$delegate622222, pan$delegate2222, null);
                    $composer2.updateRememberedValue(dashScreenKt$DashScreen$2$1);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(m4838boximpl22222, valueOf222222, (Function2) dashScreenKt$DashScreen$2$1, $composer2, 0);
                    if (DashScreen$lambda$0(ui$delegate).getStage() == ConnStage.STREAMING) {
                    }
                    Object[] objArr22222 = new Object[0];
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980957065, str);
                    rememberedValue9 = $composer2.rememberedValue();
                    if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    MutableState autoConnectTried$delegate22222 = (MutableState) RememberSaveableKt.rememberSaveable(objArr22222, (Function0) rememberedValue9, $composer2, 48);
                    Boolean valueOf322222 = Boolean.valueOf(DashScreen$lambda$3(autoConnectOnStart$delegate2222));
                    ConnStage stage222222 = DashScreen$lambda$0(ui$delegate).getStage();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980959635, str);
                    String[] essentialPermissions622222 = essentialPermissions3;
                    changed3 = (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changed(autoConnectOnStart$delegate2222) | $composer2.changed(autoConnectTried$delegate22222) | $composer2.changed(ui$delegate) | $composer2.changedInstance(essentialPermissions3) | $composer2.changedInstance(context2);
                    Object rememberedValue1622222 = $composer2.rememberedValue();
                    if (changed3) {
                    }
                    Object dashScreenKt$DashScreen$3$122222 = new DashScreenKt$DashScreen$3$1(vm2, autoConnectOnStart$delegate, autoConnectTried$delegate22222, ui$delegate, essentialPermissions622222, context2, null);
                    ui$delegate2 = ui$delegate;
                    essentialPermissions4 = essentialPermissions622222;
                    obj = (Function2) dashScreenKt$DashScreen$3$122222;
                    $composer2.updateRememberedValue(obj);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.LaunchedEffect(valueOf322222, stage222222, (Function2) obj, $composer2, 0);
                    State uiState3222222 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                    ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner222222 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                    Object consume2222222 = $composer2.consume(localLifecycleOwner222222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    final LifecycleOwner lifecycleOwner222222 = (LifecycleOwner) consume2222222;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                    changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState3222222) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner222222);
                    final String[] essentialPermissions7222222 = essentialPermissions4;
                    Object rememberedValue17222222 = $composer2.rememberedValue();
                    if (changedInstance3) {
                    }
                    final Context context3222222 = context2;
                    final DashViewModel vm7222222 = vm2;
                    final MutableState batteryOk$delegate3222222 = batteryOk$delegate2;
                    final State uiState4222222 = uiState;
                    Object obj5222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj623) {
                            return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context3222222, uiState4222222, vm7222222, batteryOk$delegate3222222, essentialPermissions7222222, (DisposableEffectScope) obj623);
                        }
                    };
                    uiState2 = uiState4222222;
                    essentialPermissions7222222 = essentialPermissions7222222;
                    rememberedValue17222222 = obj5222222;
                    $composer2.updateRememberedValue(rememberedValue17222222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    EffectsKt.DisposableEffect(lifecycleOwner222222, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue17222222, $composer2, 0);
                    Modifier m820paddingqDBjuR0$default2222222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy7222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode17222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap17222222 = $composer2.getCurrentCompositionLocalMap();
                    final DashViewModel vm82222222 = vm2;
                    Modifier materializeModifier17222222 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default2222222);
                    Function0 constructor17222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i410222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl17222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl17222222, columnMeasurePolicy7222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl17222222, currentCompositionLocalMap17222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl17222222, Integer.valueOf(hashCode17222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl17222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl17222222, materializeModifier17222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i53222222 = (i410222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance7222222 = ColumnScopeInstance.INSTANCE;
                    int i62222222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                    streaming = streaming2;
                    final State ui$delegate32222222 = ui$delegate2;
                    WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj6223, Object obj72) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming2, ui$delegate2, (Composer) obj6223, ((Integer) obj72).intValue());
                        }
                    }, $composer2, 54), $composer2, 1576368, 49);
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Alignment center2222222 = Alignment.INSTANCE.getCenter();
                    Modifier m818paddingVpY3zN4$default2222222 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy3222222 = BoxKt.maybeCachedBoxMeasurePolicy(center2222222, false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode22222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap22222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier22222222 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default2222222);
                    Function0 constructor22222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i72222222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl22222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl22222222, maybeCachedBoxMeasurePolicy3222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl22222222, currentCompositionLocalMap22222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl22222222, Integer.valueOf(hashCode22222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl22222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl22222222, materializeModifier22222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i82222222 = (i72222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance3222222 = BoxScopeInstance.INSTANCE;
                    int i92222222 = ((54 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    Modifier m273borderxT4_qwU2222222 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                    ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                    MeasurePolicy maybeCachedBoxMeasurePolicy22222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode32222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap32222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier32222222 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU2222222);
                    Function0 constructor32222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i102222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl32222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl32222222, maybeCachedBoxMeasurePolicy22222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl32222222, currentCompositionLocalMap32222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl32222222, Integer.valueOf(hashCode32222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl32222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl32222222, materializeModifier32222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i112222222 = (i102222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance22222222 = BoxScopeInstance.INSTANCE;
                    int i122222222 = ((0 >> 6) & 112) | 6;
                    Composer composer42222222 = $composer2;
                    ComposerKt.sourceInformationMarkerStart(composer42222222, 2024465622, "C:DashScreen.kt#cef2n4");
                    DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                    if (DashScreen$lambda$1 != null) {
                    }
                    ComposerKt.sourceInformationMarkerEnd(composer42222222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                    maneuver = DashScreen$lambda$0(ui$delegate32222222).getManeuver();
                    if (maneuver == null) {
                    }
                    Arrangement.Horizontal m686spacedBy0680j_44222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier9222222 = Modifier.INSTANCE;
                    Alignment.Vertical top2222222 = Alignment.INSTANCE.getTop();
                    MeasurePolicy rowMeasurePolicy9222222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_44222222, top2222222, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode42222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap42222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier42222222 = ComposedModifierKt.materializeModifier($composer2, modifier9222222);
                    Function0 constructor42222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i132222222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl42222222 = Updater.m4364constructorimpl($composer2);
                    MeasurePolicy measurePolicy2222222 = rowMeasurePolicy9222222;
                    Updater.m4372setimpl(m4364constructorimpl42222222, measurePolicy2222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl42222222, currentCompositionLocalMap42222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl42222222, Integer.valueOf(hashCode42222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl42222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl42222222, materializeModifier42222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i142222222 = (i132222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i152222222 = ((48 >> 6) & 112) | 6;
                    RowScope rowScope3222222 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                    $composer2.startReplaceGroup(-2140347244);
                    ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                    Triple[] tripleArr22222222 = new Triple[3];
                    remainingKm = DashScreen$lambda$0(ui$delegate32222222).getRemainingKm();
                    if (remainingKm != null) {
                    }
                    str2 = "—";
                    tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate32222222).getRemainingKm() != null ? "km" : "", "Kalan");
                    etaMinutes = DashScreen$lambda$0(ui$delegate32222222).getEtaMinutes();
                    if (etaMinutes != null) {
                    }
                    str3 = "—";
                    Integer etaMinutes22222222 = DashScreen$lambda$0(ui$delegate32222222).getEtaMinutes();
                    tripleArr[1] = new Triple(str3, etaMinutes22222222 != null ? etaMinutes22222222.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                    tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate32222222).getMapZoom(), "", "Yakınlaştırma");
                    Iterable listOf2222222 = CollectionsKt.listOf((Object[]) tripleArr);
                    int i162222222 = 0;
                    it = listOf2222222.iterator();
                    while (it.hasNext()) {
                    }
                    DashViewModel vm92222222 = vm3;
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically8222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween4222222 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN45222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy22222222 = RowKt.rowMeasurePolicy(spaceBetween4222222, centerVertically8222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode52222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap52222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier52222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN45222222);
                    Function0 constructor52222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i172222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl52222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl52222222, rowMeasurePolicy22222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl52222222, currentCompositionLocalMap52222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl52222222, Integer.valueOf(hashCode52222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl52222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl52222222, materializeModifier52222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i182222222 = (i172222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance7222222 = RowScopeInstance.INSTANCE;
                    int i192222222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically22222222 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier22222222 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy32222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically22222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode62222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap62222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier62222222 = ComposedModifierKt.materializeModifier($composer2, modifier22222222);
                    Function0 constructor62222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i202222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl62222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl62222222, rowMeasurePolicy32222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl62222222, currentCompositionLocalMap62222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl62222222, Integer.valueOf(hashCode62222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl62222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl62222222, materializeModifier62222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i212222222 = (i202222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance22222222 = RowScopeInstance.INSTANCE;
                    int i222222222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier32222222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy22222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode72222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap72222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier72222222 = ComposedModifierKt.materializeModifier($composer2, modifier32222222);
                    Function0 constructor72222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i232222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl72222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl72222222, columnMeasurePolicy22222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl72222222, currentCompositionLocalMap72222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl72222222, Integer.valueOf(hashCode72222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl72222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl72222222, materializeModifier72222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i242222222 = (i232222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance22222222 = ColumnScopeInstance.INSTANCE;
                    int i252222222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean DashScreen$lambda$232222222 = DashScreen$lambda$23(adjustMode$delegate2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                    rememberedValue10 = $composer2.rememberedValue();
                    if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$232222222, (Function1) rememberedValue10, $composer2, 48);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically32222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween22222222 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN422222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy42222222 = RowKt.rowMeasurePolicy(spaceBetween22222222, centerVertically32222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode82222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap82222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier82222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN422222222);
                    Function0 constructor82222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i262222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl82222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl82222222, rowMeasurePolicy42222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl82222222, currentCompositionLocalMap82222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl82222222, Integer.valueOf(hashCode82222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl82222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl82222222, materializeModifier82222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i272222222 = (i262222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance32222222 = RowScopeInstance.INSTANCE;
                    int i282222222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically42222222 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier42222222 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy52222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically42222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode92222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap92222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier92222222 = ComposedModifierKt.materializeModifier($composer2, modifier42222222);
                    Function0 constructor92222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i292222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl92222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl92222222, rowMeasurePolicy52222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl92222222, currentCompositionLocalMap92222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl92222222, Integer.valueOf(hashCode92222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl92222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl92222222, materializeModifier92222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i302222222 = (i292222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance42222222 = RowScopeInstance.INSTANCE;
                    int i312222222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate32222222).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier52222222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy32222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode102222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap102222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier102222222 = ComposedModifierKt.materializeModifier($composer2, modifier52222222);
                    Function0 constructor102222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i322222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl102222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl102222222, columnMeasurePolicy32222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl102222222, currentCompositionLocalMap102222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl102222222, Integer.valueOf(hashCode102222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl102222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl102222222, materializeModifier102222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i332222222 = (i322222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance32222222 = ColumnScopeInstance.INSTANCE;
                    int i342222222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate32222222).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean headingUp2222222 = DashScreen$lambda$0(ui$delegate32222222).getHeadingUp();
                    ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                    if ((($dirty & 14) ^ 6) > 4) {
                    }
                    if (($dirty & 6) != 4) {
                    }
                    z2 = true;
                    z3 = z2;
                    Object rememberedValue1822222222 = $composer2.rememberedValue();
                    if (z3) {
                    }
                    rememberedValue1822222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj622222222) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj622222222).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue1822222222);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(headingUp2222222, (Function1) rememberedValue1822222222, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                    Alignment.Vertical centerVertically522222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal spaceBetween322222222 = Arrangement.INSTANCE.getSpaceBetween();
                    Modifier m817paddingVpY3zN4322222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy622222222 = RowKt.rowMeasurePolicy(spaceBetween322222222, centerVertically522222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode1122222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap1122222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier1122222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4322222222);
                    Function0 constructor1122222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i3522222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl1122222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl1122222222, rowMeasurePolicy622222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl1122222222, currentCompositionLocalMap1122222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl1122222222, Integer.valueOf(hashCode1122222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl1122222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl1122222222, materializeModifier1122222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3622222222 = (i3522222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance522222222 = RowScopeInstance.INSTANCE;
                    int i3722222222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                    Alignment.Vertical centerVertically622222222 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    Modifier modifier622222222 = Modifier.INSTANCE;
                    MeasurePolicy rowMeasurePolicy722222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically622222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode1222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap1222222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier1222222222 = ComposedModifierKt.materializeModifier($composer2, modifier622222222);
                    Function0 constructor1222222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i3822222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl1222222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl1222222222, rowMeasurePolicy722222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl1222222222, currentCompositionLocalMap1222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl1222222222, Integer.valueOf(hashCode1222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl1222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl1222222222, materializeModifier1222222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i3922222222 = (i3822222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance622222222 = RowScopeInstance.INSTANCE;
                    int i4022222222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                    IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate32222222).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                    SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier722222222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy422222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode1322222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap1322222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier1322222222 = ComposedModifierKt.materializeModifier($composer2, modifier722222222);
                    Function0 constructor1322222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4122222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl1322222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl1322222222, columnMeasurePolicy422222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl1322222222, currentCompositionLocalMap1322222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl1322222222, Integer.valueOf(hashCode1322222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl1322222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl1322222222, materializeModifier1322222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i4222222222 = (i4122222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance422222222 = ColumnScopeInstance.INSTANCE;
                    int i4322222222 = ((0 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                    TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                    TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate32222222).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate32222222).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    boolean mapDark22222222 = DashScreen$lambda$0(ui$delegate32222222).getMapDark();
                    ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                    if ((($dirty & 14) ^ 6) <= 4) {
                    }
                    Object rememberedValue1922222222 = $composer2.rememberedValue();
                    if (z4) {
                    }
                    Object obj622222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj72) {
                            return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                        }
                    };
                    $composer2.updateRememberedValue(obj622222222);
                    rememberedValue1922222222 = obj622222222;
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    WayfindrComponentsKt.WayfindrToggle(mapDark22222222, (Function1) rememberedValue1922222222, $composer2, 0);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                    Alignment.Vertical centerVertically722222222 = Alignment.INSTANCE.getCenterVertically();
                    Arrangement.Horizontal m686spacedBy0680j_4222222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                    Modifier fillMaxWidth$default22222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                    MeasurePolicy rowMeasurePolicy822222222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_4222222222, centerVertically722222222, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode1422222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap1422222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier1422222222 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default22222222);
                    Function0 constructor1422222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4422222222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl1422222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl1422222222, rowMeasurePolicy822222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl1422222222, currentCompositionLocalMap1422222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl1422222222, Integer.valueOf(hashCode1422222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl1422222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl1422222222, materializeModifier1422222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i4522222222 = (i4422222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                    int i4622222222 = ((438 >> 6) & 112) | 6;
                    RowScope rowScope222222222 = RowScopeInstance.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                    Alignment.Horizontal centerHorizontally32222222 = Alignment.INSTANCE.getCenterHorizontally();
                    Modifier m817paddingVpY3zN4422222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope222222222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    MeasurePolicy columnMeasurePolicy522222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally32222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode1522222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap1522222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier1522222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4422222222);
                    Function0 constructor1522222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i4722222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl1522222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl1522222222, columnMeasurePolicy522222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl1522222222, currentCompositionLocalMap1522222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl1522222222, Integer.valueOf(hashCode1522222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl1522222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl1522222222, materializeModifier1522222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i4822222222 = (i4722222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance522222222 = ColumnScopeInstance.INSTANCE;
                    int i4922222222 = ((384 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                    float m7908constructorimpl22222222 = Dp.m7908constructorimpl(128);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                    rememberedValue11 = $composer2.rememberedValue();
                    if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl22222222, (Function1) rememberedValue11, null, $composer2, 54, 4);
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                    WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Alignment.Horizontal centerHorizontally222222222 = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.Vertical m686spacedBy0680j_4322222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                    ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                    Modifier modifier822222222 = Modifier.INSTANCE;
                    MeasurePolicy columnMeasurePolicy622222222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_4322222222, centerHorizontally222222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                    ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                    int hashCode1622222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                    CompositionLocalMap currentCompositionLocalMap1622222222 = $composer2.getCurrentCompositionLocalMap();
                    Modifier materializeModifier1622222222 = ComposedModifierKt.materializeModifier($composer2, modifier822222222);
                    Function0 constructor1622222222 = ComposeUiNode.INSTANCE.getConstructor();
                    int i5022222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                    if (!($composer2.getApplier() instanceof Applier)) {
                    }
                    $composer2.startReusableNode();
                    if ($composer2.getInserting()) {
                    }
                    Composer m4364constructorimpl1622222222 = Updater.m4364constructorimpl($composer2);
                    Updater.m4372setimpl(m4364constructorimpl1622222222, columnMeasurePolicy622222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m4372setimpl(m4364constructorimpl1622222222, currentCompositionLocalMap1622222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Updater.m4368initimpl(m4364constructorimpl1622222222, Integer.valueOf(hashCode1622222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                    Updater.m4370reconcileimpl(m4364constructorimpl1622222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                    Updater.m4372setimpl(m4364constructorimpl1622222222, materializeModifier1622222222, ComposeUiNode.INSTANCE.getSetModifier());
                    int i5122222222 = (i5022222222 >> 6) & 14;
                    ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance622222222 = ColumnScopeInstance.INSTANCE;
                    int i5222222222 = ((432 >> 6) & 112) | 6;
                    ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                    ImageVector plus22222222 = WayfindrIcons.INSTANCE.getPlus();
                    ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                    z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue2022222222 = $composer2.rememberedValue();
                    if (z5) {
                    }
                    rememberedValue2022222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                        }
                    };
                    $composer2.updateRememberedValue(rememberedValue2022222222);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus22222222, (Function0) rememberedValue2022222222, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                    Composer composer522222222 = composer;
                    TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate32222222).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer522222222, 1597440, 0, 261930);
                    ImageVector minus22222222 = WayfindrIcons.INSTANCE.getMinus();
                    ComposerKt.sourceInformationMarkerStart(composer522222222, 2044399839, str);
                    z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue2122222222 = composer522222222.rememberedValue();
                    if (z6) {
                    }
                    rememberedValue2122222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                        }
                    };
                    composer522222222.updateRememberedValue(rememberedValue2122222222);
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus22222222, (Function0) rememberedValue2122222222, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                    Composer composer622222222 = composer2;
                    ImageVector recenter22222222 = WayfindrIcons.INSTANCE.getRecenter();
                    ComposerKt.sourceInformationMarkerStart(composer622222222, 2044402995, str);
                    z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                    Object rememberedValue2222222222 = composer622222222.rememberedValue();
                    if (z7) {
                    }
                    rememberedValue2222222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate2222);
                        }
                    };
                    composer622222222.updateRememberedValue(rememberedValue2222222222);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter22222222, (Function0) rememberedValue2222222222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (streaming) {
                    }
                    $composer2.startReplaceGroup(-1925928760);
                    $composer2.endReplaceGroup();
                    if (streaming) {
                    }
                    $composer2.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    $composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    if (ComposerKt.isTraceInProgress()) {
                    }
                    vm5 = vm4;
                }
                str = "CC(remember):DashScreen.kt#9igjgp";
                Object obj3222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj423) {
                        return DashScreenKt.DashScreen$lambda$18$lambda$17(essentialPermissions, context, vm2, batteryOk$delegate, batteryLauncher, (Map) obj423);
                    }
                };
                essentialPermissions2 = essentialPermissions;
                batteryOk$delegate2 = batteryOk$delegate;
                batteryLauncher2 = batteryLauncher;
                $composer2.updateRememberedValue(obj3222);
                rememberedValue13222 = obj3222;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ManagedActivityResultLauncher permissionLauncher222222 = ActivityResultRegistryKt.rememberLauncherForActivityResult(requestMultiplePermissions222, (Function1) rememberedValue13222, $composer2, 0);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980901688, str);
                rememberedValue6 = $composer2.rememberedValue();
                if (rememberedValue6 != Composer.INSTANCE.getEmpty()) {
                }
                final MutableState pan$delegate22222 = (MutableState) rememberedValue6;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980903697, str);
                rememberedValue7 = $composer2.rememberedValue();
                if (rememberedValue7 != Composer.INSTANCE.getEmpty()) {
                }
                MutableState adjustMode$delegate322222 = (MutableState) rememberedValue7;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, 1980905688, str);
                rememberedValue8 = $composer2.rememberedValue();
                if (rememberedValue8 != Composer.INSTANCE.getEmpty()) {
                }
                MutableState joystickVelocity$delegate522222 = (MutableState) rememberedValue8;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                String lastButton22222 = DashScreen$lambda$0(ui$delegate).getLastButton();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980911546, str);
                changed2 = $composer2.changed(ui$delegate);
                Object rememberedValue1422222 = $composer2.rememberedValue();
                if (changed2) {
                }
                Object obj422222 = (Function2) new DashScreenKt$DashScreen$1$1(ui$delegate, pan$delegate22222, null);
                $composer2.updateRememberedValue(obj422222);
                rememberedValue1422222 = obj422222;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(lastButton22222, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue1422222, $composer2, 0);
                Offset m4838boximpl222222 = Offset.m4838boximpl(DashScreen$lambda$26(joystickVelocity$delegate));
                Boolean valueOf2222222 = Boolean.valueOf(DashScreen$lambda$23(adjustMode$delegate));
                ComposerKt.sourceInformationMarkerStart($composer2, 1980928751, str);
                if ((($dirty & 14) ^ 6) <= 4) {
                }
                Object rememberedValue15222222 = $composer2.rememberedValue();
                if (z) {
                }
                MutableState joystickVelocity$delegate6222222 = joystickVelocity$delegate;
                DashViewModel vm6222222 = vm2;
                context2 = context;
                adjustMode$delegate2 = adjustMode$delegate;
                essentialPermissions3 = essentialPermissions2;
                joystickVelocity$delegate2 = joystickVelocity$delegate6222222;
                vm2 = vm6222222;
                dashScreenKt$DashScreen$2$1 = new DashScreenKt$DashScreen$2$1(vm6222222, adjustMode$delegate2, joystickVelocity$delegate6222222, pan$delegate22222, null);
                $composer2.updateRememberedValue(dashScreenKt$DashScreen$2$1);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(m4838boximpl222222, valueOf2222222, (Function2) dashScreenKt$DashScreen$2$1, $composer2, 0);
                if (DashScreen$lambda$0(ui$delegate).getStage() == ConnStage.STREAMING) {
                }
                Object[] objArr222222 = new Object[0];
                ComposerKt.sourceInformationMarkerStart($composer2, 1980957065, str);
                rememberedValue9 = $composer2.rememberedValue();
                if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                MutableState autoConnectTried$delegate222222 = (MutableState) RememberSaveableKt.rememberSaveable(objArr222222, (Function0) rememberedValue9, $composer2, 48);
                Boolean valueOf3222222 = Boolean.valueOf(DashScreen$lambda$3(autoConnectOnStart$delegate2222));
                ConnStage stage2222222 = DashScreen$lambda$0(ui$delegate).getStage();
                ComposerKt.sourceInformationMarkerStart($composer2, 1980959635, str);
                String[] essentialPermissions6222222 = essentialPermissions3;
                changed3 = (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changed(autoConnectOnStart$delegate2222) | $composer2.changed(autoConnectTried$delegate222222) | $composer2.changed(ui$delegate) | $composer2.changedInstance(essentialPermissions3) | $composer2.changedInstance(context2);
                Object rememberedValue16222222 = $composer2.rememberedValue();
                if (changed3) {
                }
                Object dashScreenKt$DashScreen$3$1222222 = new DashScreenKt$DashScreen$3$1(vm2, autoConnectOnStart$delegate, autoConnectTried$delegate222222, ui$delegate, essentialPermissions6222222, context2, null);
                ui$delegate2 = ui$delegate;
                essentialPermissions4 = essentialPermissions6222222;
                obj = (Function2) dashScreenKt$DashScreen$3$1222222;
                $composer2.updateRememberedValue(obj);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.LaunchedEffect(valueOf3222222, stage2222222, (Function2) obj, $composer2, 0);
                State uiState32222222 = SnapshotStateKt.rememberUpdatedState(DashScreen$lambda$0(ui$delegate2), $composer2, DashUiState.$stable);
                ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner2222222 = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
                ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
                Object consume22222222 = $composer2.consume(localLifecycleOwner2222222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                final LifecycleOwner lifecycleOwner2222222 = (LifecycleOwner) consume22222222;
                ComposerKt.sourceInformationMarkerStart($composer2, 1980977788, str);
                changedInstance3 = $composer2.changedInstance(context2) | $composer2.changed(uiState32222222) | $composer2.changedInstance(essentialPermissions4) | (((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm2)) || ($dirty & 6) == 4) | $composer2.changedInstance(lifecycleOwner2222222);
                final String[] essentialPermissions72222222 = essentialPermissions4;
                Object rememberedValue172222222 = $composer2.rememberedValue();
                if (changedInstance3) {
                }
                final Context context32222222 = context2;
                final DashViewModel vm72222222 = vm2;
                final MutableState batteryOk$delegate32222222 = batteryOk$delegate2;
                final State uiState42222222 = uiState;
                Object obj52222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj623) {
                        return DashScreenKt.DashScreen$lambda$38$lambda$37(LifecycleOwner.this, context32222222, uiState42222222, vm72222222, batteryOk$delegate32222222, essentialPermissions72222222, (DisposableEffectScope) obj623);
                    }
                };
                uiState2 = uiState42222222;
                essentialPermissions72222222 = essentialPermissions72222222;
                rememberedValue172222222 = obj52222222;
                $composer2.updateRememberedValue(rememberedValue172222222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                EffectsKt.DisposableEffect(lifecycleOwner2222222, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue172222222, $composer2, 0);
                Modifier m820paddingqDBjuR0$default22222222 = PaddingKt.m820paddingqDBjuR0$default(PaddingKt.m816padding3ABfNKs(ScrollKt.verticalScroll$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), ScrollKt.rememberScrollState(0, $composer2, 0, 1), false, null, false, 14, null), Dp.m7908constructorimpl(18)), 0.0f, 0.0f, 0.0f, Dp.m7908constructorimpl(24), 7, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy72222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode172222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap172222222 = $composer2.getCurrentCompositionLocalMap();
                final DashViewModel vm822222222 = vm2;
                Modifier materializeModifier172222222 = ComposedModifierKt.materializeModifier($composer2, m820paddingqDBjuR0$default22222222);
                Function0 constructor172222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i4102222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl172222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl172222222, columnMeasurePolicy72222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl172222222, currentCompositionLocalMap172222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl172222222, Integer.valueOf(hashCode172222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl172222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl172222222, materializeModifier172222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i532222222 = (i4102222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance72222222 = ColumnScopeInstance.INSTANCE;
                int i622222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1917116544, "C184@8550L1666,180@8246L1981,334@16759L2715,389@19484L30,413@20644L1683,446@22337L30,449@22411L1138,470@23559L30,473@23661L1113,494@24784L30,496@24824L1252,521@26086L30,524@26191L1508:DashScreen.kt#cef2n4");
                streaming = streaming2;
                final State ui$delegate322222222 = ui$delegate2;
                WayfindrComponentsKt.ScreenHeader(null, "Konsol ekranı", "Sürücünün gördüğü", "Konsol ekranına aktarılan haritanın canlı önizlemesi. Bisikletin Wi-Fi ağına bağlanıp aktarımı başlatmak için Bağlan'a dokunun — telefon ekranınız kapalı kalabilir.", false, null, ComposableLambdaKt.rememberComposableLambda(1559686126, true, new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj6223, Object obj72) {
                        return DashScreenKt.DashScreen$lambda$96$lambda$40(streaming2, ui$delegate2, (Composer) obj6223, ((Integer) obj72).intValue());
                    }
                }, $composer2, 54), $composer2, 1576368, 49);
                if (streaming) {
                }
                $composer2.endReplaceGroup();
                Alignment center22222222 = Alignment.INSTANCE.getCenter();
                Modifier m818paddingVpY3zN4$default22222222 = PaddingKt.m818paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, Dp.m7908constructorimpl(6), 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy32222222 = BoxKt.maybeCachedBoxMeasurePolicy(center22222222, false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap222222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier222222222 = ComposedModifierKt.materializeModifier($composer2, m818paddingVpY3zN4$default22222222);
                Function0 constructor222222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i722222222 = ((((54 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl222222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl222222222, maybeCachedBoxMeasurePolicy32222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl222222222, currentCompositionLocalMap222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl222222222, Integer.valueOf(hashCode222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl222222222, materializeModifier222222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i822222222 = (i722222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance32222222 = BoxScopeInstance.INSTANCE;
                int i922222222 = ((54 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1395359541, "C349@17520L1944:DashScreen.kt#cef2n4");
                if (streaming) {
                }
                $composer2.endReplaceGroup();
                Modifier m273borderxT4_qwU22222222 = BorderKt.m273borderxT4_qwU(BorderKt.m273borderxT4_qwU(ClipKt.clip(SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(272)), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(6), ColorKt.Color(4279045904L), RoundedCornerShapeKt.getCircleShape()), Dp.m7908constructorimpl(2), com.example.wayfindr.ui.theme.ColorKt.getLine2(), RoundedCornerShapeKt.getCircleShape());
                ComposerKt.sourceInformationMarkerStart($composer2, 1042775818, "CC(Box)N(modifier,contentAlignment,propagateMinConstraints,content)71@3424L131:Box.kt#2w3rfo");
                MeasurePolicy maybeCachedBoxMeasurePolicy222222222 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode322222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap322222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier322222222 = ComposedModifierKt.materializeModifier($composer2, m273borderxT4_qwU22222222);
                Function0 constructor322222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i1022222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl322222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl322222222, maybeCachedBoxMeasurePolicy222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl322222222, currentCompositionLocalMap322222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl322222222, Integer.valueOf(hashCode322222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl322222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl322222222, materializeModifier322222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i1122222222 = (i1022222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1833054614, "C72@3469L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance222222222 = BoxScopeInstance.INSTANCE;
                int i1222222222 = ((0 >> 6) & 112) | 6;
                Composer composer422222222 = $composer2;
                ComposerKt.sourceInformationMarkerStart(composer422222222, 2024465622, "C:DashScreen.kt#cef2n4");
                DashScreen$lambda$1 = DashScreen$lambda$1(previewFrame$delegate);
                if (DashScreen$lambda$1 != null) {
                }
                ComposerKt.sourceInformationMarkerEnd(composer422222222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(14)), $composer2, i2);
                maneuver = DashScreen$lambda$0(ui$delegate322222222).getManeuver();
                if (maneuver == null) {
                }
                Arrangement.Horizontal m686spacedBy0680j_442222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier92222222 = Modifier.INSTANCE;
                Alignment.Vertical top22222222 = Alignment.INSTANCE.getTop();
                MeasurePolicy rowMeasurePolicy92222222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_442222222, top22222222, $composer2, ((48 >> 3) & 14) | ((48 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode422222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap422222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier422222222 = ComposedModifierKt.materializeModifier($composer2, modifier92222222);
                Function0 constructor422222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i1322222222 = ((((48 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl422222222 = Updater.m4364constructorimpl($composer2);
                MeasurePolicy measurePolicy22222222 = rowMeasurePolicy92222222;
                Updater.m4372setimpl(m4364constructorimpl422222222, measurePolicy22222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl422222222, currentCompositionLocalMap422222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl422222222, Integer.valueOf(hashCode422222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl422222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl422222222, materializeModifier422222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i1422222222 = (i1322222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i1522222222 = ((48 >> 6) & 112) | 6;
                RowScope rowScope32222222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1926781749, "C:DashScreen.kt#cef2n4");
                $composer2.startReplaceGroup(-2140347244);
                ComposerKt.sourceInformation($composer2, "*425@21302L1001");
                Triple[] tripleArr222222222 = new Triple[3];
                remainingKm = DashScreen$lambda$0(ui$delegate322222222).getRemainingKm();
                if (remainingKm != null) {
                }
                str2 = "—";
                tripleArr[0] = new Triple(str2, DashScreen$lambda$0(ui$delegate322222222).getRemainingKm() != null ? "km" : "", "Kalan");
                etaMinutes = DashScreen$lambda$0(ui$delegate322222222).getEtaMinutes();
                if (etaMinutes != null) {
                }
                str3 = "—";
                Integer etaMinutes222222222 = DashScreen$lambda$0(ui$delegate322222222).getEtaMinutes();
                tripleArr[1] = new Triple(str3, etaMinutes222222222 != null ? etaMinutes222222222.intValue() >= 60 ? "" : "dk" : "", "Tahmini süre");
                tripleArr[2] = new Triple("z" + DashScreen$lambda$0(ui$delegate322222222).getMapZoom(), "", "Yakınlaştırma");
                Iterable listOf22222222 = CollectionsKt.listOf((Object[]) tripleArr);
                int i1622222222 = 0;
                it = listOf22222222.iterator();
                while (it.hasNext()) {
                }
                DashViewModel vm922222222 = vm3;
                $composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                Alignment.Vertical centerVertically82222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal spaceBetween42222222 = Arrangement.INSTANCE.getSpaceBetween();
                Modifier m817paddingVpY3zN452222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint() : com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGoldTint2() : com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy222222222 = RowKt.rowMeasurePolicy(spaceBetween42222222, centerVertically82222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode522222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap522222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier522222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN452222222);
                Function0 constructor522222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i1722222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl522222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl522222222, rowMeasurePolicy222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl522222222, currentCompositionLocalMap522222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl522222222, Integer.valueOf(hashCode522222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl522222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl522222222, materializeModifier522222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i1822222222 = (i1722222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance72222222 = RowScopeInstance.INSTANCE;
                int i1922222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 2137581795, "C459@22898L565,467@23519L19,467@23476L63:DashScreen.kt#cef2n4");
                Alignment.Vertical centerVertically222222222 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier222222222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy322222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically222222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode622222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap622222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier622222222 = ComposedModifierKt.materializeModifier($composer2, modifier222222222);
                Function0 constructor622222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i2022222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl622222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl622222222, rowMeasurePolicy322222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl622222222, currentCompositionLocalMap622222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl622222222, Integer.valueOf(hashCode622222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl622222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl622222222, materializeModifier622222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i2122222222 = (i2022222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance222222222 = RowScopeInstance.INSTANCE;
                int i2222222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1656775271, "C460@22968L106,461@23091L29,462@23137L312:DashScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getCross(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$23(adjustMode$delegate2) ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier322222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy222222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode722222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap722222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier722222222 = ComposedModifierKt.materializeModifier($composer2, modifier322222222);
                Function0 constructor722222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i2322222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl722222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl722222222, columnMeasurePolicy222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl722222222, currentCompositionLocalMap722222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl722222222, Integer.valueOf(hashCode722222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl722222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl722222222, materializeModifier722222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i2422222222 = (i2322222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance222222222 = ColumnScopeInstance.INSTANCE;
                int i2522222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -537712075, "C463@23166L122,464@23309L122:DashScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk("Harita ayarlama modu", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                TextKt.m3069TextNvy7gAk("Bisikletin kumanda kolunu taklit eder", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24630, 0, 262120);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean DashScreen$lambda$2322222222 = DashScreen$lambda$23(adjustMode$delegate2);
                ComposerKt.sourceInformationMarkerStart($composer2, 761710162, str);
                rememberedValue10 = $composer2.rememberedValue();
                if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                WayfindrComponentsKt.WayfindrToggle(DashScreen$lambda$2322222222, (Function1) rememberedValue10, $composer2, 48);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                Alignment.Vertical centerVertically322222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal spaceBetween222222222 = Arrangement.INSTANCE.getSpaceBetween();
                Modifier m817paddingVpY3zN4222222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy422222222 = RowKt.rowMeasurePolicy(spaceBetween222222222, centerVertically322222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode822222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap822222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier822222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN4222222222);
                Function0 constructor822222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i2622222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl822222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl822222222, rowMeasurePolicy422222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl822222222, currentCompositionLocalMap822222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl822222222, Integer.valueOf(hashCode822222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl822222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl822222222, materializeModifier822222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i2722222222 = (i2622222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance322222222 = RowScopeInstance.INSTANCE;
                int i2822222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1208894656, "C483@24087L594,491@24739L24,491@24694L70:DashScreen.kt#cef2n4");
                Alignment.Vertical centerVertically422222222 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier422222222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy522222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically422222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode922222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap922222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier922222222 = ComposedModifierKt.materializeModifier($composer2, modifier422222222);
                Function0 constructor922222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i2922222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl922222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl922222222, rowMeasurePolicy522222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl922222222, currentCompositionLocalMap922222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl922222222, Integer.valueOf(hashCode922222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl922222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl922222222, materializeModifier922222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i3022222222 = (i2922222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance422222222 = RowScopeInstance.INSTANCE;
                int i3122222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1689701397, "C484@24157L107,485@24281L29,486@24327L340:DashScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getNavi(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), DashScreen$lambda$0(ui$delegate322222222).getHeadingUp() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier522222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy322222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode1022222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap1022222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier1022222222 = ComposedModifierKt.materializeModifier($composer2, modifier522222222);
                Function0 constructor1022222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i3222222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl1022222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl1022222222, columnMeasurePolicy322222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl1022222222, currentCompositionLocalMap1022222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl1022222222, Integer.valueOf(hashCode1022222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl1022222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl1022222222, materializeModifier1022222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i3322222222 = (i3222222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance322222222 = ColumnScopeInstance.INSTANCE;
                int i3422222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 410778522, "C487@24356L124,488@24501L148:DashScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk("Yönü takip eden harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                TextKt.m3069TextNvy7gAk(DashScreen$lambda$0(ui$delegate322222222).getHeadingUp() ? "Sürüş yönüne göre döner" : "Kuzey yukarıda", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean headingUp22222222 = DashScreen$lambda$0(ui$delegate322222222).getHeadingUp();
                ComposerKt.sourceInformationMarkerStart($composer2, 930854934, str);
                if ((($dirty & 14) ^ 6) > 4) {
                }
                if (($dirty & 6) != 4) {
                }
                z2 = true;
                z3 = z2;
                Object rememberedValue18222222222 = $composer2.rememberedValue();
                if (z3) {
                }
                rememberedValue18222222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj6222222222) {
                        return DashScreenKt.DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel.this, ((Boolean) obj6222222222).booleanValue());
                    }
                };
                $composer2.updateRememberedValue(rememberedValue18222222222);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                WayfindrComponentsKt.WayfindrToggle(headingUp22222222, (Function1) rememberedValue18222222222, $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(10)), $composer2, 6);
                Alignment.Vertical centerVertically5222222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal spaceBetween3222222222 = Arrangement.INSTANCE.getSpaceBetween();
                Modifier m817paddingVpY3zN43222222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(14))), Dp.m7908constructorimpl(16), Dp.m7908constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy6222222222 = RowKt.rowMeasurePolicy(spaceBetween3222222222, centerVertically5222222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode11222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap11222222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier11222222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN43222222222);
                Function0 constructor11222222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i35222222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl11222222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl11222222222, rowMeasurePolicy6222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl11222222222, currentCompositionLocalMap11222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl11222222222, Integer.valueOf(hashCode11222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl11222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl11222222222, materializeModifier11222222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i36222222222 = (i35222222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance5222222222 = RowScopeInstance.INSTANCE;
                int i37222222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -260400618, "C506@25250L738,518@26044L21,518@26001L65:DashScreen.kt#cef2n4");
                Alignment.Vertical centerVertically6222222222 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                Modifier modifier6222222222 = Modifier.INSTANCE;
                MeasurePolicy rowMeasurePolicy7222222222 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically6222222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode12222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap12222222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier12222222222 = ComposedModifierKt.materializeModifier($composer2, modifier6222222222);
                Function0 constructor12222222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i38222222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl12222222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl12222222222, rowMeasurePolicy7222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl12222222222, currentCompositionLocalMap12222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl12222222222, Integer.valueOf(hashCode12222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl12222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl12222222222, materializeModifier12222222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i39222222222 = (i38222222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance6222222222 = RowScopeInstance.INSTANCE;
                int i40222222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -741207204, "C507@25320L105,508@25442L29,509@25488L486:DashScreen.kt#cef2n4");
                IconKt.m2517Iconww6aTOc(WayfindrIcons.INSTANCE.getMoon(), (String) null, SizeKt.m862size3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(20)), !DashScreen$lambda$0(ui$delegate322222222).getMapDark() ? com.example.wayfindr.ui.theme.ColorKt.getGold() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), $composer2, 432, 0);
                SpacerKt.Spacer(SizeKt.m867width3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(11)), $composer2, 6);
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier7222222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy4222222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer2, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode13222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap13222222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier13222222222 = ComposedModifierKt.materializeModifier($composer2, modifier7222222222);
                Function0 constructor13222222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i41222222222 = ((((0 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl13222222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl13222222222, columnMeasurePolicy4222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl13222222222, currentCompositionLocalMap13222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl13222222222, Integer.valueOf(hashCode13222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl13222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl13222222222, materializeModifier13222222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i42222222222 = (i41222222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance4222222222 = ColumnScopeInstance.INSTANCE;
                int i43222222222 = ((0 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, 1359272777, "C510@25517L113,511@25651L305:DashScreen.kt#cef2n4");
                TextKt.m3069TextNvy7gAk("Koyu harita", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 1597446, 0, 261930);
                TextKt.m3069TextNvy7gAk((!DashScreen$lambda$0(ui$delegate322222222).getMapDarkAuto() ? "Otomatik (19.00–05.00) · " : "Elle · ") + (!DashScreen$lambda$0(ui$delegate322222222).getMapDark() ? "gece görünümü" : "gündüz haritası"), PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(1), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11.5d), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, 24624, 0, 262120);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                boolean mapDark222222222 = DashScreen$lambda$0(ui$delegate322222222).getMapDark();
                ComposerKt.sourceInformationMarkerStart($composer2, 1100003250, str);
                if ((($dirty & 14) ^ 6) <= 4) {
                }
                Object rememberedValue19222222222 = $composer2.rememberedValue();
                if (z4) {
                }
                Object obj6222222222 = new Function1() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj72) {
                        return DashScreenKt.DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel.this, ((Boolean) obj72).booleanValue());
                    }
                };
                $composer2.updateRememberedValue(obj6222222222);
                rememberedValue19222222222 = obj6222222222;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                WayfindrComponentsKt.WayfindrToggle(mapDark222222222, (Function1) rememberedValue19222222222, $composer2, 0);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(16)), $composer2, 6);
                Alignment.Vertical centerVertically7222222222 = Alignment.INSTANCE.getCenterVertically();
                Arrangement.Horizontal m686spacedBy0680j_42222222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(16));
                Modifier fillMaxWidth$default222222222 = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart($composer2, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
                MeasurePolicy rowMeasurePolicy8222222222 = RowKt.rowMeasurePolicy(m686spacedBy0680j_42222222222, centerVertically7222222222, $composer2, ((438 >> 3) & 14) | ((438 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode14222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap14222222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier14222222222 = ComposedModifierKt.materializeModifier($composer2, fillMaxWidth$default222222222);
                Function0 constructor14222222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i44222222222 = ((((438 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl14222222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl14222222222, rowMeasurePolicy8222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl14222222222, currentCompositionLocalMap14222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl14222222222, Integer.valueOf(hashCode14222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl14222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl14222222222, materializeModifier14222222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i45222222222 = (i44222222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
                int i46222222222 = ((438 >> 6) & 112) | 6;
                RowScope rowScope2222222222 = RowScopeInstance.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, 688103991, "C529@26393L652,546@27059L630:DashScreen.kt#cef2n4");
                Alignment.Horizontal centerHorizontally322222222 = Alignment.INSTANCE.getCenterHorizontally();
                Modifier m817paddingVpY3zN44222222222 = PaddingKt.m817paddingVpY3zN4(BorderKt.m273borderxT4_qwU(BackgroundKt.m262backgroundbw27NRU$default(ClipKt.clip(RowScope.weight$default(rowScope2222222222, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), com.example.wayfindr.ui.theme.ColorKt.getSurf1(), null, 2, null), Dp.m7908constructorimpl(1), com.example.wayfindr.ui.theme.ColorKt.getLine(), RoundedCornerShapeKt.m1124RoundedCornerShape0680j_4(Dp.m7908constructorimpl(18))), Dp.m7908constructorimpl(12), Dp.m7908constructorimpl(16));
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                MeasurePolicy columnMeasurePolicy5222222222 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), centerHorizontally322222222, $composer2, ((384 >> 3) & 14) | ((384 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode15222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap15222222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier15222222222 = ComposedModifierKt.materializeModifier($composer2, m817paddingVpY3zN44222222222);
                Function0 constructor15222222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i47222222222 = ((((384 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl15222222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl15222222222, columnMeasurePolicy5222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl15222222222, currentCompositionLocalMap15222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl15222222222, Integer.valueOf(hashCode15222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl15222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl15222222222, materializeModifier15222222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i48222222222 = (i47222222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance5222222222 = ColumnScopeInstance.INSTANCE;
                int i49222222222 = ((384 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -789495246, "C540@26870L62,538@26796L155,542@26968L29,543@27014L17:DashScreen.kt#cef2n4");
                float m7908constructorimpl222222222 = Dp.m7908constructorimpl(128);
                ComposerKt.sourceInformationMarkerStart($composer2, 1775649924, str);
                rememberedValue11 = $composer2.rememberedValue();
                if (rememberedValue11 != Composer.INSTANCE.getEmpty()) {
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                JoystickKt.m8355JoystickKz89ssw(m7908constructorimpl222222222, (Function1) rememberedValue11, null, $composer2, 54, 4);
                SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(9)), $composer2, 6);
                WayfindrComponentsKt.Eyebrow("Kaydır", null, $composer2, 6, 2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Alignment.Horizontal centerHorizontally2222222222 = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.Vertical m686spacedBy0680j_43222222222 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(10));
                ComposerKt.sourceInformationMarkerStart($composer2, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
                Modifier modifier8222222222 = Modifier.INSTANCE;
                MeasurePolicy columnMeasurePolicy6222222222 = ColumnKt.columnMeasurePolicy(m686spacedBy0680j_43222222222, centerHorizontally2222222222, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
                ComposerKt.sourceInformationMarkerStart($composer2, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
                int hashCode16222222222 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer2, 0));
                CompositionLocalMap currentCompositionLocalMap16222222222 = $composer2.getCurrentCompositionLocalMap();
                Modifier materializeModifier16222222222 = ComposedModifierKt.materializeModifier($composer2, modifier8222222222);
                Function0 constructor16222222222 = ComposeUiNode.INSTANCE.getConstructor();
                int i50222222222 = ((((432 << 3) & 112) << 6) & 896) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -553112988, "CC(ReusableComposeNode)N(factory,update,content)399@15590L9:Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof Applier)) {
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                }
                Composer m4364constructorimpl16222222222 = Updater.m4364constructorimpl($composer2);
                Updater.m4372setimpl(m4364constructorimpl16222222222, columnMeasurePolicy6222222222, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m4372setimpl(m4364constructorimpl16222222222, currentCompositionLocalMap16222222222, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Updater.m4368initimpl(m4364constructorimpl16222222222, Integer.valueOf(hashCode16222222222), ComposeUiNode.INSTANCE.getSetCompositeKeyHash());
                Updater.m4370reconcileimpl(m4364constructorimpl16222222222, ComposeUiNode.INSTANCE.getApplyOnDeactivatedNodeAssertion());
                Updater.m4372setimpl(m4364constructorimpl16222222222, materializeModifier16222222222, ComposeUiNode.INSTANCE.getSetModifier());
                int i51222222222 = (i50222222222 >> 6) & 14;
                ComposerKt.sourceInformationMarkerStart($composer2, 2093002350, "C89@4557L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance6222222222 = ColumnScopeInstance.INSTANCE;
                int i52222222222 = ((432 >> 6) & 112) | 6;
                ComposerKt.sourceInformationMarkerStart($composer2, -1048376037, "C550@27281L15,550@27234L78,551@27329L118,552@27511L16,552@27464L78,553@27609L36,553@27559L116:DashScreen.kt#cef2n4");
                ImageVector plus222222222 = WayfindrIcons.INSTANCE.getPlus();
                ComposerKt.sourceInformationMarkerStart($composer2, 2044392478, str);
                z5 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                Object rememberedValue20222222222 = $composer2.rememberedValue();
                if (z5) {
                }
                rememberedValue20222222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel.this);
                    }
                };
                $composer2.updateRememberedValue(rememberedValue20222222222);
                ComposerKt.sourceInformationMarkerEnd(composer);
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(plus222222222, (Function0) rememberedValue20222222222, null, Dp.m7908constructorimpl(52), false, null, composer, 3072, 52);
                Composer composer5222222222 = composer;
                TextKt.m3069TextNvy7gAk("z" + DashScreen$lambda$0(ui$delegate322222222).getMapZoom(), null, com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(12), null, FontWeight.INSTANCE.getSemiBold(), TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, composer5222222222, 1597440, 0, 261930);
                ImageVector minus222222222 = WayfindrIcons.INSTANCE.getMinus();
                ComposerKt.sourceInformationMarkerStart(composer5222222222, 2044399839, str);
                z6 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                Object rememberedValue21222222222 = composer5222222222.rememberedValue();
                if (z6) {
                }
                rememberedValue21222222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel.this);
                    }
                };
                composer5222222222.updateRememberedValue(rememberedValue21222222222);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(minus222222222, (Function0) rememberedValue21222222222, null, Dp.m7908constructorimpl(52), false, null, composer2, 3072, 52);
                Composer composer6222222222 = composer2;
                ImageVector recenter222222222 = WayfindrIcons.INSTANCE.getRecenter();
                ComposerKt.sourceInformationMarkerStart(composer6222222222, 2044402995, str);
                z7 = ((($dirty & 14) ^ 6) <= 4 && $composer2.changedInstance(vm4)) || ($dirty & 6) == 4;
                Object rememberedValue22222222222 = composer6222222222.rememberedValue();
                if (z7) {
                }
                rememberedValue22222222222 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashScreenKt.DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel.this, pan$delegate22222);
                    }
                };
                composer6222222222.updateRememberedValue(rememberedValue22222222222);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                WayfindrComponentsKt.m8358WayfindrIconBtnN59uMVk(recenter222222222, (Function0) rememberedValue22222222222, null, Dp.m7908constructorimpl(52), true, null, composer3, 27648, 36);
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (streaming) {
                }
                $composer2.startReplaceGroup(-1925928760);
                $composer2.endReplaceGroup();
                if (streaming) {
                }
                $composer2.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerEnd($composer2);
                if (ComposerKt.isTraceInProgress()) {
                }
                vm5 = vm4;
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj14, Object obj15) {
                    return DashScreenKt.DashScreen$lambda$97(DashViewModel.this, $changed, i, (Composer) obj14, ((Integer) obj15).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DashUiState DashScreen$lambda$0(State<DashUiState> state) {
        return (DashUiState) state.getValue();
    }

    private static final Bitmap DashScreen$lambda$1(State<Bitmap> state) {
        return (Bitmap) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DashScreen$lambda$3(State<Boolean> state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DashScreen$hasEssentialPermissions(String[] essentialPermissions, Context context) {
        int length = essentialPermissions.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return true;
            }
            if (!(ContextCompat.checkSelfPermission(context, essentialPermissions[i]) == 0)) {
                return false;
            }
            i++;
        }
    }

    private static final boolean DashScreen$lambda$11(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DashScreen$lambda$12(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit DashScreen$lambda$14$lambda$13(Context $context, DashViewModel $vm, MutableState $batteryOk$delegate, ActivityResult it) {
        Intrinsics.checkNotNullParameter(it, "it");
        DashScreen$lambda$12($batteryOk$delegate, DeviceReadiness.INSTANCE.isIgnoringBatteryOptimizations($context));
        $vm.connect();
        return Unit.INSTANCE;
    }

    private static final void DashScreen$connectWithBackgroundCheck(Context context, DashViewModel $vm, MutableState<Boolean> mutableState, ManagedActivityResultLauncher<Intent, ActivityResult> managedActivityResultLauncher) {
        Object m8392constructorimpl;
        if (!DashScreen$lambda$11(mutableState) && !DeviceReadiness.INSTANCE.batteryExemptionAsked(context)) {
            DeviceReadiness.INSTANCE.markBatteryExemptionAsked(context);
            try {
                Result.Companion companion = Result.INSTANCE;
                managedActivityResultLauncher.launch(DeviceReadiness.INSTANCE.batteryExemptionIntent(context));
                m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            boolean launched = Result.m8399isSuccessimpl(m8392constructorimpl);
            if (!launched) {
                $vm.connect();
                return;
            }
            return;
        }
        $vm.connect();
    }

    static final Unit DashScreen$lambda$18$lambda$17(String[] $essentialPermissions, Context $context, DashViewModel $vm, MutableState $batteryOk$delegate, ManagedActivityResultLauncher $batteryLauncher, Map results) {
        Intrinsics.checkNotNullParameter(results, "results");
        int length = $essentialPermissions.length;
        boolean essentialOk = false;
        int i = 0;
        while (true) {
            if (i < length) {
                if (!Intrinsics.areEqual(results.get($essentialPermissions[i]), (Object) true)) {
                    break;
                }
                i++;
            } else {
                essentialOk = true;
                break;
            }
        }
        if (essentialOk) {
            DashScreen$connectWithBackgroundCheck($context, $vm, $batteryOk$delegate, $batteryLauncher);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long DashScreen$lambda$20(MutableState<Offset> mutableState) {
        return mutableState.getValue().m4859unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DashScreen$lambda$21(MutableState<Offset> mutableState, long j) {
        mutableState.setValue(Offset.m4838boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DashScreen$lambda$23(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void DashScreen$lambda$24(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long DashScreen$lambda$26(MutableState<Offset> mutableState) {
        return mutableState.getValue().m4859unboximpl();
    }

    private static final void DashScreen$lambda$27(MutableState<Offset> mutableState, long j) {
        mutableState.setValue(Offset.m4838boximpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DashScreen$lambda$32(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DashScreen$lambda$33(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final DisposableEffectResult DashScreen$lambda$38$lambda$37(final LifecycleOwner $lifecycleOwner, final Context $context, final State $uiState, final DashViewModel $vm, final MutableState $batteryOk$delegate, final String[] $essentialPermissions, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final LifecycleEventObserver obs = new LifecycleEventObserver() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda19
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                DashScreenKt.DashScreen$lambda$38$lambda$37$lambda$35($context, $uiState, $vm, $batteryOk$delegate, $essentialPermissions, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycle().addObserver(obs);
        return new DisposableEffectResult() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$DashScreen$lambda$38$lambda$37$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                LifecycleOwner.this.getLifecycle().removeObserver(obs);
            }
        };
    }

    static final void DashScreen$lambda$38$lambda$37$lambda$35(Context $context, State $uiState, DashViewModel $vm, MutableState $batteryOk$delegate, String[] $essentialPermissions, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_RESUME) {
            DashScreen$lambda$12($batteryOk$delegate, DeviceReadiness.INSTANCE.isIgnoringBatteryOptimizations($context));
            if (((DashUiState) $uiState.getValue()).getNeedsWifiOn()) {
                Object systemService = $context.getApplicationContext().getSystemService("wifi");
                Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
                if (((WifiManager) systemService).isWifiEnabled() && DashScreen$hasEssentialPermissions($essentialPermissions, $context)) {
                    $vm.connect();
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static final Unit DashScreen$lambda$96$lambda$40(boolean $streaming, State $ui$delegate, Composer $composer, int $changed) {
        Function0 function0;
        String thermal;
        ComposerKt.sourceInformation($composer, "C185@8568L1634:DashScreen.kt#cef2n4");
        if ($composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1559686126, $changed, -1, "com.example.wayfindr.ui.screens.DashScreen.<anonymous>.<anonymous> (DashScreen.kt:185)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal m686spacedBy0680j_4 = Arrangement.INSTANCE.m686spacedBy0680j_4(Dp.m7908constructorimpl(6));
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            Modifier modifier = Modifier.INSTANCE;
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(m686spacedBy0680j_4, centerVertically, $composer, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, modifier);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((432 << 3) & 112) << 6) & 896) | 6;
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
            Composer composer = $composer;
            ComposerKt.sourceInformationMarkerStart(composer, 1456264949, "C101@5233L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i3 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart(composer, 262796647, "C:DashScreen.kt#cef2n4");
            if (!$streaming || DashScreen$lambda$0($ui$delegate).getGpsStatus() == GpsStatus.GOOD) {
                composer.startReplaceGroup(254125016);
            } else {
                composer.startReplaceGroup(262840852);
                ComposerKt.sourceInformation(composer, "187@8800L308");
                WayfindrComponentsKt.WayfindrChip(DashScreen$lambda$0($ui$delegate).getGpsStatus() == GpsStatus.LOST ? "GPS sinyali yok" : "GPS sinyali zayıf", DashScreen$lambda$0($ui$delegate).getGpsStatus() == GpsStatus.LOST ? ChipTone.Alert : ChipTone.Warn, false, null, null, composer, 0, 28);
            }
            composer.endReplaceGroup();
            if (!$streaming || Intrinsics.areEqual(DashScreen$lambda$0($ui$delegate).getThermal(), "OK")) {
                composer.startReplaceGroup(254125016);
            } else {
                composer.startReplaceGroup(263257089);
                ComposerKt.sourceInformation(composer, "193@9214L487");
                String thermal2 = DashScreen$lambda$0($ui$delegate).getThermal();
                switch (thermal2.hashCode()) {
                    case 2524:
                        if (thermal2.equals("OK")) {
                            thermal = "Normal";
                            break;
                        }
                        thermal = DashScreen$lambda$0($ui$delegate).getThermal();
                        break;
                    case 72749:
                        if (thermal2.equals("Hot")) {
                            thermal = "Aşırı sıcak";
                            break;
                        }
                        thermal = DashScreen$lambda$0($ui$delegate).getThermal();
                        break;
                    case 2688677:
                        if (thermal2.equals("Warm")) {
                            thermal = "Isınıyor";
                            break;
                        }
                        thermal = DashScreen$lambda$0($ui$delegate).getThermal();
                        break;
                    case 1653039495:
                        if (thermal2.equals("Throttling")) {
                            thermal = "Performans kısıtlandı";
                            break;
                        }
                        thermal = DashScreen$lambda$0($ui$delegate).getThermal();
                        break;
                    default:
                        thermal = DashScreen$lambda$0($ui$delegate).getThermal();
                        break;
                }
                WayfindrComponentsKt.WayfindrChip(thermal, Intrinsics.areEqual(DashScreen$lambda$0($ui$delegate).getThermal(), "Warm") ? ChipTone.Warn : ChipTone.Alert, false, null, null, composer, 0, 28);
                composer = composer;
            }
            composer.endReplaceGroup();
            switch (WhenMappings.$EnumSwitchMapping$0[DashScreen$lambda$0($ui$delegate).getStage().ordinal()]) {
                case 1:
                    composer.startReplaceGroup(978342874);
                    ComposerKt.sourceInformation(composer, "205@9809L48");
                    WayfindrComponentsKt.WayfindrChip("CANLI", ChipTone.Gold, true, null, null, composer, 438, 24);
                    composer.endReplaceGroup();
                    break;
                case 2:
                case 3:
                    composer.startReplaceGroup(978347223);
                    ComposerKt.sourceInformation(composer, "207@9945L45");
                    WayfindrComponentsKt.WayfindrChip("Bağlanıyor…", ChipTone.Neutral, false, null, null, composer, 54, 28);
                    composer.endReplaceGroup();
                    break;
                case 4:
                    composer.startReplaceGroup(978350190);
                    ComposerKt.sourceInformation(composer, "208@10038L36");
                    WayfindrComponentsKt.WayfindrChip("Hata", ChipTone.Alert, false, null, null, composer, 54, 28);
                    composer.endReplaceGroup();
                    break;
                case 5:
                    composer.startReplaceGroup(978352882);
                    ComposerKt.sourceInformation(composer, "209@10122L40");
                    WayfindrComponentsKt.WayfindrChip("Çevrimdışı", ChipTone.Off, false, null, null, composer, 54, 28);
                    composer.endReplaceGroup();
                    break;
                default:
                    composer.startReplaceGroup(978341186);
                    composer.endReplaceGroup();
                    throw new NoWhenBranchMatchedException();
            }
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
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

    static final Unit DashScreen$lambda$96$lambda$45(final Context $context, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Function0 function02;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation($composer, "C222@10537L1338:DashScreen.kt#cef2n4");
        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(224574377, $changed, -1, "com.example.wayfindr.ui.screens.DashScreen.<anonymous>.<anonymous> (DashScreen.kt:222)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((438 >> 6) & 112) | 6;
            RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 981078749, "C227@10796L658,239@11568L155,237@11479L374:DashScreen.kt#cef2n4");
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 0.0f, Dp.m7908constructorimpl(12), 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, m820paddingqDBjuR0$default);
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
            ComposerKt.sourceInformationMarkerStart($composer, -1413642933, "C228@10875L221,232@11125L303:DashScreen.kt#cef2n4");
            TextKt.m3069TextNvy7gAk("Konum iznini “Her zaman izin ver” olarak ayarlayın", null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597446, 0, 262058);
            TextKt.m3069TextNvy7gAk("Ekran kapalıyken GPS konumunu güncel tutmak için gereklidir. Sistem ayarlarından “Her zaman izin ver” seçeneğini belirleyin.", PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(3), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getTextMid(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24630, 0, 262120);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerStart($composer, 585860832, "CC(remember):DashScreen.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance($context);
            Object rememberedValue = $composer.rememberedValue();
            if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                Object obj = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda20
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return DashScreenKt.DashScreen$lambda$96$lambda$45$lambda$44$lambda$43$lambda$42($context);
                    }
                };
                $composer.updateRememberedValue(obj);
                rememberedValue = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            WayfindrComponentsKt.WayfindrBtn("Ayarla", (Function0) rememberedValue, null, null, BtnVariant.Ghost, BtnSize.Sm, false, $composer, 221190, 76);
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

    static final Unit DashScreen$lambda$96$lambda$45$lambda$44$lambda$43$lambda$42(Context $context) {
        $context.startActivity(DeviceReadiness.INSTANCE.appSettingsIntent($context));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0583  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0602  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x069a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x05ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    static final Unit DashScreen$lambda$96$lambda$54(final State $ui$delegate, final Context $context, final String[] $essentialPermissions, final ManagedActivityResultLauncher $batteryLauncher, final DashViewModel $vm, final ManagedActivityResultLauncher $permissionLauncher, final String[] $requestedPermissions, final MutableState $batteryOk$delegate, ColumnScope WayfindrCard, Composer $composer, int $changed) {
        Function0 function0;
        Function0 function02;
        String str;
        String str2;
        String turkishConnectionError;
        Composer composer;
        Composer composer2;
        Composer composer3;
        String lastButton;
        String str3;
        Composer composer4;
        Object obj;
        Composer composer5 = $composer;
        Intrinsics.checkNotNullParameter(WayfindrCard, "$this$WayfindrCard");
        ComposerKt.sourceInformation(composer5, "C249@12011L4053:DashScreen.kt#cef2n4");
        if (!composer5.shouldExecute(($changed & 17) != 16, $changed & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-111876380, $changed, -1, "com.example.wayfindr.ui.screens.DashScreen.<anonymous>.<anonymous> (DashScreen.kt:249)");
            }
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.Horizontal spaceBetween = Arrangement.INSTANCE.getSpaceBetween();
            Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer, 844473419, "CC(Row)N(modifier,horizontalArrangement,verticalAlignment,content)99@5125L58,100@5188L131:Row.kt#2w3rfo");
            MeasurePolicy rowMeasurePolicy = RowKt.rowMeasurePolicy(spaceBetween, centerVertically, $composer, ((438 >> 3) & 14) | ((438 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier = ComposedModifierKt.materializeModifier($composer, fillMaxWidth$default);
            Function0 constructor = ComposeUiNode.INSTANCE.getConstructor();
            int i = ((((438 << 3) & 112) << 6) & 896) | 6;
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
            int i3 = ((438 >> 6) & 112) | 6;
            final RowScope rowScope = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer, 1822032623, "C254@12250L1949:DashScreen.kt#cef2n4");
            Modifier m820paddingqDBjuR0$default = PaddingKt.m820paddingqDBjuR0$default(RowScope.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 0.0f, Dp.m7908constructorimpl(12), 0.0f, 11, null);
            ComposerKt.sourceInformationMarkerStart($composer, 1341605231, "CC(Column)N(modifier,verticalArrangement,horizontalAlignment,content)87@4443L61,88@4509L134:Column.kt#2w3rfo");
            MeasurePolicy columnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), $composer, ((0 >> 3) & 14) | ((0 >> 3) & 112));
            ComposerKt.sourceInformationMarkerStart($composer, -1159599143, "CC(Layout)P(!1,2)81@3355L27,84@3521L416:Layout.kt#80mrfh");
            int hashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode($composer, 0));
            CompositionLocalMap currentCompositionLocalMap2 = $composer.getCurrentCompositionLocalMap();
            Modifier materializeModifier2 = ComposedModifierKt.materializeModifier($composer, m820paddingqDBjuR0$default);
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
            ComposerKt.sourceInformationMarkerStart($composer, -1531960707, "C255@12325L724,265@13074L1103:DashScreen.kt#cef2n4");
            switch (WhenMappings.$EnumSwitchMapping$0[DashScreen$lambda$0($ui$delegate).getStage().ordinal()]) {
                case 1:
                    str = "Aktarım sürüyor";
                    break;
                case 2:
                    str = !StringsKt.isBlank(DashScreen$lambda$0($ui$delegate).getSsid()) ? DashScreen$lambda$0($ui$delegate).getSsid() + " ağına katılıyor…" : "Konsol ekranı aranıyor…";
                    break;
                case 3:
                    str = DashScreen$lambda$0($ui$delegate).getRetryAttempt() > 0 ? "Bağlantı doğrulaması yeniden deneniyor…" : "Konsol ekranıyla eşleştiriliyor…";
                    break;
                case 4:
                    str = "Bağlanılamadı";
                    break;
                case 5:
                    str = "Konsol ekranına bağlan";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            TextKt.m3069TextNvy7gAk(str, null, com.example.wayfindr.ui.theme.ColorKt.getTextHi(), null, TextUnitKt.getSp(14), null, FontWeight.INSTANCE.getSemiBold(), null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 1597440, 0, 262058);
            switch (WhenMappings.$EnumSwitchMapping$0[DashScreen$lambda$0($ui$delegate).getStage().ordinal()]) {
                case 1:
                    str2 = "";
                    break;
                case 2:
                    str2 = "Sistem iletişim kutusu görünürse onaylayın";
                    break;
                case 3:
                    if (DashScreen$lambda$0($ui$delegate).getRetryAttempt() <= 0) {
                        str2 = "11.63 donanım yazılımına bağlantı güvenli hâle getiriliyor…";
                        break;
                    } else {
                        str2 = "Bağlantı doğrulama denemesi " + DashScreen$lambda$0($ui$delegate).getRetryAttempt() + "/4 — bu normal";
                        break;
                    }
                case 4:
                    String errorMessage = DashScreen$lambda$0($ui$delegate).getErrorMessage();
                    if (errorMessage != null && (turkishConnectionError = turkishConnectionError(errorMessage)) != null) {
                        str2 = turkishConnectionError;
                        break;
                    } else {
                        str2 = "Konsol ekranının açık ve bisikletin yanında olduğunuzdan emin olun, ardından yeniden deneyin.";
                        break;
                    }
                    break;
                case 5:
                    str2 = "Konsol ekranını açın, bisikletin yanına gelin ve Bağlan'a dokunun";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            TextKt.m3069TextNvy7gAk(str2, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(3), 0.0f, 0.0f, 13, null), DashScreen$lambda$0($ui$delegate).getStage() == ConnStage.ERROR ? com.example.wayfindr.ui.theme.ColorKt.getWarn() : com.example.wayfindr.ui.theme.ColorKt.getTextMid(), null, TextUnitKt.getSp(12), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24624, 0, 262120);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (DashScreen$lambda$0($ui$delegate).getStage() == ConnStage.OFFLINE) {
                composer = $composer;
            } else if (DashScreen$lambda$0($ui$delegate).getStage() == ConnStage.ERROR) {
                composer = $composer;
            } else {
                $composer.startReplaceGroup(1825406693);
                ComposerKt.sourceInformation($composer, "309@15875L19,307@15787L237");
                ComposerKt.sourceInformationMarkerStart($composer, -1880775181, "CC(remember):DashScreen.kt#9igjgp");
                boolean changedInstance = $composer.changedInstance($vm);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda18
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return DashScreenKt.DashScreen$lambda$96$lambda$54$lambda$52$lambda$51$lambda$50(DashViewModel.this);
                        }
                    };
                    $composer.updateRememberedValue(obj2);
                    rememberedValue = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                WayfindrComponentsKt.WayfindrBtn("İptal", (Function0) rememberedValue, null, null, BtnVariant.Ghost, BtnSize.Sm, false, $composer, 221190, 76);
                $composer.endReplaceGroup();
                composer2 = $composer;
                composer3 = $composer;
                ComposerKt.sourceInformationMarkerEnd(composer3);
                ComposerKt.sourceInformationMarkerEnd($composer);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerEnd($composer);
                if (DashScreen$lambda$0($ui$delegate).getFrameCount() > 0) {
                    composer5.startReplaceGroup(-10528586);
                    ComposerKt.sourceInformation(composer5, "316@16126L29,317@16176L174");
                    SpacerKt.Spacer(SizeKt.m848height3ABfNKs(Modifier.INSTANCE, Dp.m7908constructorimpl(8)), composer5, 6);
                    TextKt.m3069TextNvy7gAk("Kodlanan kare: " + DashScreen$lambda$0($ui$delegate).getFrameCount(), null, com.example.wayfindr.ui.theme.ColorKt.getTextLo(), null, TextUnitKt.getSp(11), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24576, 0, 261994);
                    composer5 = $composer;
                } else {
                    composer5.startReplaceGroup(-26512930);
                }
                composer5.endReplaceGroup();
                lastButton = DashScreen$lambda$0($ui$delegate).getLastButton();
                if (lastButton == null) {
                    composer5.startReplaceGroup(-10234614);
                    composer5.endReplaceGroup();
                } else {
                    composer5.startReplaceGroup(-10234613);
                    ComposerKt.sourceInformation(composer5, "*323@16433L224");
                    TextKt.m3069TextNvy7gAk("Kumanda kolu: " + lastButton, PaddingKt.m820paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m7908constructorimpl(4), 0.0f, 0.0f, 13, null), com.example.wayfindr.ui.theme.ColorKt.getGold(), null, TextUnitKt.getSp(11), null, null, TypeKt.getGeistMonoFamily(), 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer, 24624, 0, 261992);
                    Unit unit = Unit.INSTANCE;
                    $composer.endReplaceGroup();
                    Unit unit2 = Unit.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            composer.startReplaceGroup(1823980197);
            ComposerKt.sourceInformation(composer, "288@14620L926,282@14312L1421");
            if (DashScreen$lambda$0($ui$delegate).getNeedsWifiOn()) {
                str3 = "Wi‑Fi'yi aç";
            } else {
                str3 = DashScreen$lambda$0($ui$delegate).getStage() == ConnStage.ERROR ? "Yeniden dene" : "Bağlan";
            }
            ComposerKt.sourceInformationMarkerStart(composer, -1880814434, "CC(remember):DashScreen.kt#9igjgp");
            boolean changed = composer.changed($ui$delegate) | ((((i3 & 14) ^ 6) > 4 && composer.changed(rowScope)) || (i3 & 6) == 4) | composer.changedInstance($context) | composer.changedInstance($essentialPermissions) | composer.changedInstance($batteryLauncher) | composer.changedInstance($vm) | composer.changedInstance($permissionLauncher) | composer.changedInstance($requestedPermissions);
            Composer composer6 = composer;
            composer2 = $composer;
            Object rememberedValue2 = composer6.rememberedValue();
            if (changed) {
                composer4 = composer;
            } else {
                composer4 = composer;
                if (rememberedValue2 != Composer.INSTANCE.getEmpty()) {
                    obj = rememberedValue2;
                    ComposerKt.sourceInformationMarkerEnd(composer4);
                    WayfindrComponentsKt.WayfindrBtn(str3, (Function0) obj, null, WayfindrIcons.INSTANCE.getWifi(), BtnVariant.Primary, BtnSize.Sm, false, composer4, 221184, 68);
                    composer3 = composer4;
                    composer3.endReplaceGroup();
                    ComposerKt.sourceInformationMarkerEnd(composer3);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    composer2.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer2);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    if (DashScreen$lambda$0($ui$delegate).getFrameCount() > 0) {
                    }
                    composer5.endReplaceGroup();
                    lastButton = DashScreen$lambda$0($ui$delegate).getLastButton();
                    if (lastButton == null) {
                    }
                    if (ComposerKt.isTraceInProgress()) {
                    }
                }
            }
            obj = new Function0() { // from class: com.example.wayfindr.ui.screens.DashScreenKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return DashScreenKt.DashScreen$lambda$96$lambda$54$lambda$52$lambda$49$lambda$48(RowScope.this, $permissionLauncher, $requestedPermissions, $ui$delegate, $context, $essentialPermissions, $vm, $batteryOk$delegate, $batteryLauncher);
                }
            };
            composer6.updateRememberedValue(obj);
            ComposerKt.sourceInformationMarkerEnd(composer4);
            WayfindrComponentsKt.WayfindrBtn(str3, (Function0) obj, null, WayfindrIcons.INSTANCE.getWifi(), BtnVariant.Primary, BtnSize.Sm, false, composer4, 221184, 68);
            composer3 = composer4;
            composer3.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer3);
            ComposerKt.sourceInformationMarkerEnd($composer);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            if (DashScreen$lambda$0($ui$delegate).getFrameCount() > 0) {
            }
            composer5.endReplaceGroup();
            lastButton = DashScreen$lambda$0($ui$delegate).getLastButton();
            if (lastButton == null) {
            }
            if (ComposerKt.isTraceInProgress()) {
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$54$lambda$52$lambda$49$lambda$48(RowScope $this_Row, ManagedActivityResultLauncher $permissionLauncher, String[] $requestedPermissions, State $ui$delegate, Context $context, String[] $essentialPermissions, DashViewModel $vm, MutableState $batteryOk$delegate, ManagedActivityResultLauncher $batteryLauncher) {
        Object m8392constructorimpl;
        if (DashScreen$lambda$0($ui$delegate).getNeedsWifiOn()) {
            try {
                Result.Companion companion = Result.INSTANCE;
                $context.startActivity(new Intent("android.settings.panel.action.WIFI"));
                m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            Result.m8391boximpl(m8392constructorimpl);
        } else if (DashScreen$hasEssentialPermissions($essentialPermissions, $context)) {
            DashScreen$connectWithBackgroundCheck($context, $vm, $batteryOk$delegate, $batteryLauncher);
        } else {
            $permissionLauncher.launch($requestedPermissions);
        }
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$54$lambda$52$lambda$51$lambda$50(DashViewModel $vm) {
        $vm.disconnect();
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$70$lambda$69$lambda$68(MutableState $adjustMode$delegate, boolean it) {
        DashScreen$lambda$24($adjustMode$delegate, it);
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$75$lambda$74$lambda$73(DashViewModel $vm, boolean it) {
        $vm.toggleHeadingUp();
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$80$lambda$79$lambda$78(DashViewModel $vm, boolean it) {
        $vm.setMapDark(it);
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$91$lambda$83$lambda$82$lambda$81(MutableState $adjustMode$delegate, MutableState $joystickVelocity$delegate, Offset v) {
        DashScreen$lambda$27($joystickVelocity$delegate, DashScreen$lambda$23($adjustMode$delegate) ? v.m4859unboximpl() : Offset.INSTANCE.m4865getZeroF1C5BW0());
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$91$lambda$90$lambda$85$lambda$84(DashViewModel $vm) {
        $vm.zoomIn();
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$91$lambda$90$lambda$87$lambda$86(DashViewModel $vm) {
        $vm.zoomOut();
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$91$lambda$90$lambda$89$lambda$88(DashViewModel $vm, MutableState $pan$delegate) {
        $vm.recenter();
        DashScreen$lambda$21($pan$delegate, Offset.INSTANCE.m4865getZeroF1C5BW0());
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$93$lambda$92(DashViewModel $vm) {
        $vm.exitNavigation();
        return Unit.INSTANCE;
    }

    static final Unit DashScreen$lambda$96$lambda$95$lambda$94(DashViewModel $vm) {
        $vm.disconnect();
        return Unit.INSTANCE;
    }

    private static final String turkishManeuver(String instruction) {
        Object obj;
        Iterable translations = CollectionsKt.listOf((Object[]) new Pair[]{TuplesKt.to("Take the exit on the left", "Soldaki çıkıştan çık"), TuplesKt.to("Take the exit on the right", "Sağdaki çıkıştan çık"), TuplesKt.to("Make a U-turn", "U dönüşü yap"), TuplesKt.to("Sharp left", "Keskin sola dön"), TuplesKt.to("Sharp right", "Keskin sağa dön"), TuplesKt.to("Slight left", "Hafif sola dön"), TuplesKt.to("Slight right", "Hafif sağa dön"), TuplesKt.to("Turn left", "Sola dön"), TuplesKt.to("Turn right", "Sağa dön"), TuplesKt.to("Keep left", "Solda kal"), TuplesKt.to("Keep right", "Sağda kal"), TuplesKt.to("Head out", "Yola çık"), TuplesKt.to("Continue", "Düz devam et")});
        Iterator it = translations.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (StringsKt.startsWith(instruction, (String) ((Pair) obj).component1(), true)) {
                break;
            }
        }
        Pair match = (Pair) obj;
        if (match == null) {
            return "Rotaya devam edin";
        }
        String suffix = StringsKt.replace(StringsKt.replace(StringsKt.drop(instruction, ((String) match.getFirst()).length()), " onto ", " yoluna ", true), " on ", " üzerinde ", true);
        return match.getSecond() + suffix;
    }

    private static final String turkishConnectionError(String error) {
        return Intrinsics.areEqual(error, "Turn on Wi‑Fi to connect to the dash") ? "Konsol ekranına bağlanmak için Wi‑Fi'yi açın" : "Bağlantı sırasında bir sorun oluştu. Yeniden deneyin.";
    }
}
