package com.example.wayfindr.ui.components;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.compose.material.MenuKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.ColorKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.TrafficInterval;
import com.example.wayfindr.dash.nav.TrafficSpeed;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.compose.CameraPositionState;
import com.google.maps.android.compose.GoogleMapKt;
import com.google.maps.android.compose.MapProperties;
import com.google.maps.android.compose.MapUiSettings;
import com.google.maps.android.compose.MarkerKt;
import com.google.maps.android.compose.MarkerState;
import com.google.maps.android.compose.PolylineKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: WayfindrMap.kt */
@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0091\u0002\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\u001a\b\u0002\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\b0\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00102\b\b\u0002\u0010\u0014\u001a\u00020\u00102\b\b\u0002\u0010\u0015\u001a\u00020\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00102\b\b\u0002\u0010\u0019\u001a\u00020\u00102\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u001e\u001a\u00020\u00102\u0016\b\u0002\u0010\u001f\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0003\u0018\u00010 H\u0001¢\u0006\u0002\u0010\"\u001a\b\u0010#\u001a\u00020$H\u0002\u001a\b\u0010%\u001a\u00020$H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"FOLLOW_ZOOM", "", "GoogleWayfindrMap", "", "riderLat", "", "riderLng", "dest", "Lkotlin/Pair;", "destinations", "", "routePoints", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "trafficIntervals", "Lcom/example/wayfindr/dash/nav/TrafficInterval;", "hasLocationPermission", "", "modifier", "Landroidx/compose/ui/Modifier;", "fitRoute", "navMode", "riderBearing", "recenterKey", "", "followRider", "centerOnRiderInitially", "camLat", "camLng", "camZoom", "camBearing", "showTraffic", "onMapClick", "Lkotlin/Function1;", "Lcom/google/android/gms/maps/model/LatLng;", "(Ljava/lang/Double;Ljava/lang/Double;Lkotlin/Pair;Ljava/util/List;Ljava/util/List;Ljava/util/List;ZLandroidx/compose/ui/Modifier;ZZFJZZLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Float;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "chevronBitmap", "Landroid/graphics/Bitmap;", "destPinBitmap", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class WayfindrMapKt {
    private static final float FOLLOW_ZOOM = 15.5f;

    /* compiled from: WayfindrMap.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TrafficSpeed.values().length];
            try {
                iArr[TrafficSpeed.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TrafficSpeed.SLOW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TrafficSpeed.TRAFFIC_JAM.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TrafficSpeed.UNKNOWN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit GoogleWayfindrMap$lambda$14(Double d, Double d2, Pair pair, List list, List list2, List list3, boolean z, Modifier modifier, boolean z2, boolean z3, float f, long j, boolean z4, boolean z5, Double d3, Double d4, Float f2, Float f3, boolean z6, Function1 function1, int i, int i2, int i3, Composer composer, int i4) {
        GoogleWayfindrMap(d, d2, pair, list, list2, list3, z, modifier, z2, z3, f, j, z4, z5, d3, d4, f2, f3, z6, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:133:0x03ea, code lost:
    
        if (r10 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L280;
     */
    /* JADX WARN: Code restructure failed: missing block: B:157:0x04a3, code lost:
    
        if (r15 == androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L313;
     */
    /* JADX WARN: Removed duplicated region for block: B:190:0x05ed  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x05f5  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x060a  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0613  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x068c  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0696  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x06a1  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x06ac  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x06b7  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x06c6  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x06d1  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x06db  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x07fd  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x06dd  */
    /* JADX WARN: Removed duplicated region for block: B:249:0x06d3  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x06c8  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x06b9  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x06ae  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x06a3  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0698  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x068e  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0615  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0601  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x05f7  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x05ef  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void GoogleWayfindrMap(final Double riderLat, final Double riderLng, final Pair<Double, Double> pair, List<Pair<Double, Double>> list, final List<GeoPoint> routePoints, List<TrafficInterval> list2, final boolean hasLocationPermission, Modifier modifier, boolean fitRoute, boolean navMode, float riderBearing, long recenterKey, boolean followRider, boolean centerOnRiderInitially, Double camLat, Double camLng, Float camZoom, Float camBearing, boolean showTraffic, Function1<? super LatLng, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int i) {
        List destinations;
        List trafficIntervals;
        int i2;
        Modifier modifier2;
        int i3;
        int $dirty1;
        int i4;
        int i5;
        int $dirty12;
        boolean followRider2;
        int $dirty13;
        int $dirty14;
        Composer $composer2;
        final boolean fitRoute2;
        final Double camLat2;
        final Double camLng2;
        final Float camZoom2;
        final Float camBearing2;
        final boolean showTraffic2;
        final Function1 onMapClick;
        final boolean followRider3;
        final Modifier modifier3;
        final List trafficIntervals2;
        final List destinations2;
        final boolean navMode2;
        final float riderBearing2;
        final long recenterKey2;
        final boolean centerOnRiderInitially2;
        Function1 onMapClick2;
        int i6;
        boolean navMode3;
        int i7;
        boolean followRider4;
        Modifier modifier4;
        double d;
        float riderBearing3;
        List trafficIntervals3;
        double d2;
        List markerDestinations;
        boolean centerOnRiderInitially3;
        Double d3;
        long recenterKey3;
        MarkerState riderMarkerState;
        Double d4;
        CameraPositionState cameraPositionState;
        MarkerState destMarkerState;
        boolean changedInstance;
        Object rememberedValue;
        boolean navMode4;
        CameraPositionState cameraPositionState2;
        boolean changedInstance2;
        Object rememberedValue2;
        Double camLat3;
        Double camLng3;
        Float camZoom3;
        Float camBearing3;
        Intrinsics.checkNotNullParameter(routePoints, "routePoints");
        Composer $composer3 = $composer.startRestartGroup(1526906388);
        ComposerKt.sourceInformation($composer3, "C(GoogleWayfindrMap)N(riderLat,riderLng,dest,destinations,routePoints,trafficIntervals,hasLocationPermission,modifier,fitRoute,navMode,riderBearing,recenterKey,followRider,centerOnRiderInitially,camLat,camLng,camZoom,camBearing,showTraffic,onMapClick)59@2301L29,61@2363L86,64@2476L93,69@2655L136,69@2620L171,74@2817L116,74@2796L137,83@3235L257,83@3207L285,94@3768L284,94@3691L361,105@4246L311,105@4203L354,115@4656L545,115@4585L616,132@5430L736,132@5350L816,164@6617L2739,152@6172L3184:WayfindrMap.kt#7crva5");
        int $dirty = $changed;
        int $dirty15 = $changed1;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(riderLat) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(riderLng) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(pair) ? 256 : 128;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty |= 3072;
            destinations = list;
        } else if (($changed & 3072) == 0) {
            destinations = list;
            $dirty |= $composer3.changedInstance(destinations) ? 2048 : 1024;
        } else {
            destinations = list;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(routePoints) ? 16384 : 8192;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            trafficIntervals = list2;
            i2 = 131072;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            trafficIntervals = list2;
            i2 = 131072;
            $dirty |= $composer3.changedInstance(trafficIntervals) ? 131072 : 65536;
        } else {
            trafficIntervals = list2;
            i2 = 131072;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty |= 12582912;
            modifier2 = modifier;
        } else if (($changed & 12582912) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 8388608 : 4194304;
        } else {
            modifier2 = modifier;
        }
        int i11 = i & 256;
        if (i11 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(fitRoute) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changed(navMode) ? 536870912 : 268435456;
        }
        int i13 = i & 1024;
        if (i13 != 0) {
            $dirty15 |= 6;
            i3 = i13;
        } else if (($changed1 & 6) == 0) {
            i3 = i13;
            $dirty15 |= $composer3.changed(riderBearing) ? 4 : 2;
        } else {
            i3 = i13;
        }
        int i14 = i & 2048;
        if (i14 != 0) {
            i5 = i14;
            $dirty12 = $dirty15 | 48;
            i4 = i8;
        } else {
            if (($changed1 & 48) == 0) {
                i4 = i8;
                $dirty1 = $dirty15 | ($composer3.changed(recenterKey) ? 32 : 16);
            } else {
                $dirty1 = $dirty15;
                i4 = i8;
            }
            i5 = i14;
            $dirty12 = $dirty1;
        }
        int $dirty16 = i & 4096;
        if ($dirty16 != 0) {
            $dirty12 |= 384;
            followRider2 = followRider;
        } else if (($changed1 & 384) == 0) {
            followRider2 = followRider;
            $dirty12 |= $composer3.changed(followRider2) ? 256 : 128;
        } else {
            followRider2 = followRider;
        }
        int $dirty17 = i & 8192;
        if ($dirty17 != 0) {
            $dirty13 = $dirty12 | 3072;
        } else {
            int $dirty18 = $dirty12;
            if (($changed1 & 3072) == 0) {
                $dirty18 |= $composer3.changed(centerOnRiderInitially) ? 2048 : 1024;
            }
            $dirty13 = $dirty18;
        }
        int $dirty19 = i & 16384;
        if ($dirty19 != 0) {
            $dirty14 = $dirty13 | 24576;
        } else {
            $dirty14 = $dirty13;
            if (($changed1 & 24576) == 0) {
                $dirty14 |= $composer3.changed(camLat) ? 16384 : 8192;
            }
        }
        int i15 = i & 32768;
        if (i15 != 0) {
            $dirty14 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty14 |= $composer3.changed(camLng) ? i2 : 65536;
        }
        int i16 = i & 65536;
        if (i16 != 0) {
            $dirty14 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty14 |= $composer3.changed(camZoom) ? 1048576 : 524288;
        }
        int i17 = i & i2;
        if (i17 != 0) {
            $dirty14 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty14 |= $composer3.changed(camBearing) ? 8388608 : 4194304;
        }
        int i18 = i & 262144;
        if (i18 != 0) {
            $dirty14 |= 100663296;
        } else if (($changed1 & 100663296) == 0) {
            $dirty14 |= $composer3.changed(showTraffic) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i19 = i & 524288;
        if (i19 != 0) {
            $dirty14 |= 805306368;
        } else if (($changed1 & 805306368) == 0) {
            $dirty14 |= $composer3.changedInstance(function1) ? 536870912 : 268435456;
        }
        int $dirty110 = $dirty14;
        if ($composer3.shouldExecute((($dirty & 306259091) == 306259090 && (306783379 & $dirty110) == 306783378) ? false : true, $dirty & 1)) {
            if (i4 != 0) {
                destinations = CollectionsKt.emptyList();
            }
            if (i9 != 0) {
                trafficIntervals = CollectionsKt.emptyList();
            }
            if (i10 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            boolean fitRoute3 = i11 != 0 ? false : fitRoute;
            boolean navMode5 = i12 != 0 ? false : navMode;
            float riderBearing4 = i3 != 0 ? 0.0f : riderBearing;
            long recenterKey4 = i5 != 0 ? 0L : recenterKey;
            if ($dirty16 != 0) {
                followRider2 = true;
            }
            boolean centerOnRiderInitially4 = $dirty17 != 0 ? false : centerOnRiderInitially;
            Double camLat4 = $dirty19 != 0 ? null : camLat;
            Double camLng4 = i15 != 0 ? null : camLng;
            Float camZoom4 = i16 != 0 ? null : camZoom;
            Float camBearing4 = i17 != 0 ? null : camBearing;
            boolean showTraffic3 = i18 != 0 ? true : showTraffic;
            if (i19 != 0) {
                onMapClick2 = null;
                i6 = 16384;
            } else {
                onMapClick2 = function1;
                i6 = 16384;
            }
            if (ComposerKt.isTraceInProgress()) {
                i7 = 0;
                navMode3 = navMode5;
                ComposerKt.traceEventStart(1526906388, $dirty, $dirty110, "com.example.wayfindr.ui.components.GoogleWayfindrMap (WayfindrMap.kt:58)");
            } else {
                navMode3 = navMode5;
                i7 = 0;
            }
            $composer3.startReplaceGroup(-1911106014);
            CameraPositionState cameraPositionState3 = (CameraPositionState) RememberSaveableKt.m4534rememberSaveable(new Object[i7], (Saver) CameraPositionState.INSTANCE.getSaver(), (String) null, (Function0) new Function0<CameraPositionState>() { // from class: com.example.wayfindr.ui.components.WayfindrMapKt$GoogleWayfindrMap$$inlined$rememberCameraPositionState$1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final CameraPositionState invoke() {
                    return CameraPositionState.Companion.invoke$default(CameraPositionState.INSTANCE, null, 1, null);
                }
            }, $composer3, (i7 << 6) & 896, 0);
            $composer3.endReplaceGroup();
            if (riderLat != null) {
                modifier4 = modifier2;
                followRider4 = followRider2;
                d = riderLat.doubleValue();
            } else {
                followRider4 = followRider2;
                modifier4 = modifier2;
                d = 0.0d;
            }
            if (riderLng != null) {
                riderBearing3 = riderBearing4;
                trafficIntervals3 = trafficIntervals;
                d2 = riderLng.doubleValue();
            } else {
                riderBearing3 = riderBearing4;
                trafficIntervals3 = trafficIntervals;
                d2 = 0.0d;
            }
            MarkerState riderMarkerState2 = MarkerKt.rememberMarkerState(null, new LatLng(d, d2), $composer3, 0, 1);
            boolean centerOnRiderInitially5 = centerOnRiderInitially4;
            final List destinations3 = destinations;
            MarkerState destMarkerState2 = MarkerKt.rememberMarkerState(null, new LatLng(pair != null ? pair.getFirst().doubleValue() : 0.0d, pair != null ? pair.getSecond().doubleValue() : 0.0d), $composer3, 0, 1);
            ComposerKt.sourceInformationMarkerStart($composer3, -1521735172, "CC(remember):WayfindrMap.kt#9igjgp");
            boolean changedInstance3 = (($dirty & 14) == 4) | (($dirty & 112) == 32) | $composer3.changedInstance(riderMarkerState2);
            WayfindrMapKt$GoogleWayfindrMap$1$1 rememberedValue3 = $composer3.rememberedValue();
            if (changedInstance3) {
            }
            rememberedValue3 = new WayfindrMapKt$GoogleWayfindrMap$1$1(riderLat, riderLng, riderMarkerState2, null);
            $composer3.updateRememberedValue(rememberedValue3);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(riderLat, riderLng, (Function2) rememberedValue3, $composer3, ($dirty & 14) | ($dirty & 112));
            ComposerKt.sourceInformationMarkerStart($composer3, -1521730008, "CC(remember):WayfindrMap.kt#9igjgp");
            boolean changedInstance4 = (($dirty & 896) == 256) | $composer3.changedInstance(destMarkerState2);
            Object rememberedValue4 = $composer3.rememberedValue();
            if (changedInstance4 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object obj = (Function2) new WayfindrMapKt$GoogleWayfindrMap$2$1(pair, destMarkerState2, null);
                $composer3.updateRememberedValue(obj);
                rememberedValue4 = obj;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(pair, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue4, $composer3, ($dirty >> 6) & 14);
            List list3 = destinations3;
            if (list3.isEmpty()) {
                list3 = CollectionsKt.listOfNotNull(pair);
            }
            List markerDestinations2 = list3;
            Long valueOf = Long.valueOf(recenterKey4);
            ComposerKt.sourceInformationMarkerStart($composer3, -1521716491, "CC(remember):WayfindrMap.kt#9igjgp");
            boolean changedInstance5 = (($dirty110 & 112) == 32) | (($dirty & 14) == 4) | (($dirty & 112) == 32) | $composer3.changedInstance(cameraPositionState3);
            Object rememberedValue5 = $composer3.rememberedValue();
            if (changedInstance5) {
                markerDestinations = markerDestinations2;
            } else {
                markerDestinations = markerDestinations2;
            }
            Object obj2 = (Function2) new WayfindrMapKt$GoogleWayfindrMap$3$1(recenterKey4, riderLat, riderLng, cameraPositionState3, null);
            $composer3.updateRememberedValue(obj2);
            rememberedValue5 = obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(valueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue5, $composer3, ($dirty110 >> 3) & 14);
            Boolean valueOf2 = Boolean.valueOf(centerOnRiderInitially5);
            Boolean valueOf3 = Boolean.valueOf((riderLat == null || riderLng == null) ? false : true);
            ComposerKt.sourceInformationMarkerStart($composer3, -1521699408, "CC(remember):WayfindrMap.kt#9igjgp");
            boolean changedInstance6 = (($dirty110 & 7168) == 2048) | (($dirty & 14) == 4) | (($dirty & 112) == 32) | (($dirty110 & 112) == 32) | $composer3.changedInstance(cameraPositionState3);
            Object rememberedValue6 = $composer3.rememberedValue();
            if (changedInstance6 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                long recenterKey5 = recenterKey4;
                Object wayfindrMapKt$GoogleWayfindrMap$4$1 = new WayfindrMapKt$GoogleWayfindrMap$4$1(centerOnRiderInitially5, riderLat, riderLng, recenterKey5, cameraPositionState3, null);
                centerOnRiderInitially3 = centerOnRiderInitially5;
                d3 = riderLat;
                recenterKey3 = recenterKey5;
                riderMarkerState = riderMarkerState2;
                d4 = riderLng;
                cameraPositionState = cameraPositionState3;
                Object obj3 = (Function2) wayfindrMapKt$GoogleWayfindrMap$4$1;
                $composer3.updateRememberedValue(obj3);
                rememberedValue6 = obj3;
            } else {
                d4 = riderLng;
                riderMarkerState = riderMarkerState2;
                recenterKey3 = recenterKey4;
                centerOnRiderInitially3 = centerOnRiderInitially5;
                cameraPositionState = cameraPositionState3;
                d3 = riderLat;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(valueOf2, valueOf3, (Function2) rememberedValue6, $composer3, ($dirty110 >> 9) & 14);
            Boolean valueOf4 = Boolean.valueOf(fitRoute3);
            Integer valueOf5 = Integer.valueOf(routePoints.size());
            ComposerKt.sourceInformationMarkerStart($composer3, -1521684085, "CC(remember):WayfindrMap.kt#9igjgp");
            boolean changedInstance7 = ((234881024 & $dirty) == 67108864) | $composer3.changedInstance(routePoints) | $composer3.changedInstance(cameraPositionState);
            Object rememberedValue7 = $composer3.rememberedValue();
            if (!changedInstance7 && rememberedValue7 != Composer.INSTANCE.getEmpty()) {
                destMarkerState = destMarkerState2;
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(valueOf4, valueOf5, (Function2) rememberedValue7, $composer3, ($dirty >> 24) & 14);
                Object[] objArr = {d3, d4, Float.valueOf(riderBearing3), Boolean.valueOf(followRider4), Boolean.valueOf(navMode3)};
                ComposerKt.sourceInformationMarkerStart($composer3, -1521670731, "CC(remember):WayfindrMap.kt#9igjgp");
                changedInstance = (($dirty110 & 896) != 256) | (($dirty & 14) != 4) | (($dirty & 112) != 32) | ((1879048192 & $dirty) != 536870912) | (($dirty110 & 14) != 4) | $composer3.changedInstance(cameraPositionState);
                rememberedValue = $composer3.rememberedValue();
                if (!changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                    boolean navMode6 = navMode3;
                    CameraPositionState cameraPositionState4 = cameraPositionState;
                    Object wayfindrMapKt$GoogleWayfindrMap$6$1 = new WayfindrMapKt$GoogleWayfindrMap$6$1(followRider4, d3, d4, navMode6, riderBearing3, cameraPositionState4, null);
                    navMode4 = navMode6;
                    cameraPositionState2 = cameraPositionState4;
                    Object obj4 = (Function2) wayfindrMapKt$GoogleWayfindrMap$6$1;
                    $composer3.updateRememberedValue(obj4);
                    rememberedValue = obj4;
                } else {
                    navMode4 = navMode3;
                    cameraPositionState2 = cameraPositionState;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(objArr, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, $composer3, 0);
                Object[] objArr2 = {camLat4, camLng4, camZoom4, camBearing4, pair, Boolean.valueOf(navMode4), Boolean.valueOf(followRider4)};
                ComposerKt.sourceInformationMarkerStart($composer3, -1521645772, "CC(remember):WayfindrMap.kt#9igjgp");
                changedInstance2 = ((57344 & $dirty110) != i6) | ((458752 & $dirty110) != i2) | ((3670016 & $dirty110) != 1048576) | ((29360128 & $dirty110) != 8388608) | ((1879048192 & $dirty) != 536870912) | $composer3.changedInstance(cameraPositionState2) | (($dirty110 & 896) != 256) | ((234881024 & $dirty) != 67108864) | (($dirty & 896) != 256);
                rememberedValue2 = $composer3.rememberedValue();
                if (!changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    Float camZoom5 = camZoom4;
                    Double camLat5 = camLat4;
                    Double camLng5 = camLng4;
                    Float camBearing5 = camBearing4;
                    camLat3 = camLat5;
                    camLng3 = camLng5;
                    camZoom3 = camZoom5;
                    camBearing3 = camBearing5;
                    Object obj5 = (Function2) new WayfindrMapKt$GoogleWayfindrMap$7$1(camLat5, camLng5, camZoom5, camBearing5, navMode4, cameraPositionState2, followRider4, fitRoute3, pair, null);
                    $composer3.updateRememberedValue(obj5);
                    rememberedValue2 = obj5;
                } else {
                    camZoom3 = camZoom4;
                    camLat3 = camLat4;
                    camLng3 = camLng4;
                    camBearing3 = camBearing4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer3);
                EffectsKt.LaunchedEffect(objArr2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, $composer3, 0);
                boolean showTraffic4 = showTraffic3;
                MapProperties mapProperties = new MapProperties(false, false, false, showTraffic4, null, null, null, 0.0f, 0.0f, 503, null);
                MapUiSettings mapUiSettings = new MapUiSettings(false, false, false, false, false, false, false, false, false, false, 626, null);
                final List markerDestinations3 = markerDestinations;
                final MarkerState riderMarkerState3 = riderMarkerState;
                final MarkerState destMarkerState3 = destMarkerState;
                final boolean navMode7 = navMode4;
                final List trafficIntervals4 = trafficIntervals3;
                final float riderBearing5 = riderBearing3;
                ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(45156910, true, new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrMapKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj6, Object obj7) {
                        return WayfindrMapKt.GoogleWayfindrMap$lambda$13(routePoints, trafficIntervals4, markerDestinations3, riderLat, riderLng, navMode7, riderBearing5, riderMarkerState3, destinations3, destMarkerState3, (Composer) obj6, ((Integer) obj7).intValue());
                    }
                }, $composer3, 54);
                int i20 = (($dirty >> 21) & 14) | (CameraPositionState.$stable << 6) | (MapProperties.$stable << 15) | (MapUiSettings.$stable << 21) | (1879048192 & $dirty110);
                $composer2 = $composer3;
                modifier3 = modifier4;
                GoogleMapKt.GoogleMap(modifier3, false, cameraPositionState2, null, null, mapProperties, null, mapUiSettings, null, onMapClick2, null, null, null, null, null, null, null, rememberComposableLambda, $composer2, i20, 12582912, 130394);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                fitRoute2 = fitRoute3;
                showTraffic2 = showTraffic4;
                navMode2 = navMode7;
                onMapClick = onMapClick2;
                centerOnRiderInitially2 = centerOnRiderInitially3;
                camLat2 = camLat3;
                camLng2 = camLng3;
                followRider3 = followRider4;
                trafficIntervals2 = trafficIntervals3;
                riderBearing2 = riderBearing3;
                destinations2 = destinations3;
                recenterKey2 = recenterKey3;
                camZoom2 = camZoom3;
                camBearing2 = camBearing3;
            }
            destMarkerState = destMarkerState2;
            Object obj6 = (Function2) new WayfindrMapKt$GoogleWayfindrMap$5$1(fitRoute3, routePoints, cameraPositionState, null);
            $composer3.updateRememberedValue(obj6);
            rememberedValue7 = obj6;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(valueOf4, valueOf5, (Function2) rememberedValue7, $composer3, ($dirty >> 24) & 14);
            Object[] objArr3 = {d3, d4, Float.valueOf(riderBearing3), Boolean.valueOf(followRider4), Boolean.valueOf(navMode3)};
            ComposerKt.sourceInformationMarkerStart($composer3, -1521670731, "CC(remember):WayfindrMap.kt#9igjgp");
            changedInstance = (($dirty110 & 896) != 256) | (($dirty & 14) != 4) | (($dirty & 112) != 32) | ((1879048192 & $dirty) != 536870912) | (($dirty110 & 14) != 4) | $composer3.changedInstance(cameraPositionState);
            rememberedValue = $composer3.rememberedValue();
            if (changedInstance) {
            }
            boolean navMode62 = navMode3;
            CameraPositionState cameraPositionState42 = cameraPositionState;
            Object wayfindrMapKt$GoogleWayfindrMap$6$12 = new WayfindrMapKt$GoogleWayfindrMap$6$1(followRider4, d3, d4, navMode62, riderBearing3, cameraPositionState42, null);
            navMode4 = navMode62;
            cameraPositionState2 = cameraPositionState42;
            Object obj42 = (Function2) wayfindrMapKt$GoogleWayfindrMap$6$12;
            $composer3.updateRememberedValue(obj42);
            rememberedValue = obj42;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(objArr3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, $composer3, 0);
            Object[] objArr22 = {camLat4, camLng4, camZoom4, camBearing4, pair, Boolean.valueOf(navMode4), Boolean.valueOf(followRider4)};
            ComposerKt.sourceInformationMarkerStart($composer3, -1521645772, "CC(remember):WayfindrMap.kt#9igjgp");
            changedInstance2 = ((57344 & $dirty110) != i6) | ((458752 & $dirty110) != i2) | ((3670016 & $dirty110) != 1048576) | ((29360128 & $dirty110) != 8388608) | ((1879048192 & $dirty) != 536870912) | $composer3.changedInstance(cameraPositionState2) | (($dirty110 & 896) != 256) | ((234881024 & $dirty) != 67108864) | (($dirty & 896) != 256);
            rememberedValue2 = $composer3.rememberedValue();
            if (changedInstance2) {
            }
            Float camZoom52 = camZoom4;
            Double camLat52 = camLat4;
            Double camLng52 = camLng4;
            Float camBearing52 = camBearing4;
            camLat3 = camLat52;
            camLng3 = camLng52;
            camZoom3 = camZoom52;
            camBearing3 = camBearing52;
            Object obj52 = (Function2) new WayfindrMapKt$GoogleWayfindrMap$7$1(camLat52, camLng52, camZoom52, camBearing52, navMode4, cameraPositionState2, followRider4, fitRoute3, pair, null);
            $composer3.updateRememberedValue(obj52);
            rememberedValue2 = obj52;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(objArr22, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue2, $composer3, 0);
            boolean showTraffic42 = showTraffic3;
            MapProperties mapProperties2 = new MapProperties(false, false, false, showTraffic42, null, null, null, 0.0f, 0.0f, 503, null);
            MapUiSettings mapUiSettings2 = new MapUiSettings(false, false, false, false, false, false, false, false, false, false, 626, null);
            final List markerDestinations32 = markerDestinations;
            final MarkerState riderMarkerState32 = riderMarkerState;
            final MarkerState destMarkerState32 = destMarkerState;
            final boolean navMode72 = navMode4;
            final List trafficIntervals42 = trafficIntervals3;
            final float riderBearing52 = riderBearing3;
            ComposableLambda rememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(45156910, true, new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrMapKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj62, Object obj7) {
                    return WayfindrMapKt.GoogleWayfindrMap$lambda$13(routePoints, trafficIntervals42, markerDestinations32, riderLat, riderLng, navMode72, riderBearing52, riderMarkerState32, destinations3, destMarkerState32, (Composer) obj62, ((Integer) obj7).intValue());
                }
            }, $composer3, 54);
            int i202 = (($dirty >> 21) & 14) | (CameraPositionState.$stable << 6) | (MapProperties.$stable << 15) | (MapUiSettings.$stable << 21) | (1879048192 & $dirty110);
            $composer2 = $composer3;
            modifier3 = modifier4;
            GoogleMapKt.GoogleMap(modifier3, false, cameraPositionState2, null, null, mapProperties2, null, mapUiSettings2, null, onMapClick2, null, null, null, null, null, null, null, rememberComposableLambda2, $composer2, i202, 12582912, 130394);
            if (ComposerKt.isTraceInProgress()) {
            }
            fitRoute2 = fitRoute3;
            showTraffic2 = showTraffic42;
            navMode2 = navMode72;
            onMapClick = onMapClick2;
            centerOnRiderInitially2 = centerOnRiderInitially3;
            camLat2 = camLat3;
            camLng2 = camLng3;
            followRider3 = followRider4;
            trafficIntervals2 = trafficIntervals3;
            riderBearing2 = riderBearing3;
            destinations2 = destinations3;
            recenterKey2 = recenterKey3;
            camZoom2 = camZoom3;
            camBearing2 = camBearing3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            fitRoute2 = fitRoute;
            camLat2 = camLat;
            camLng2 = camLng;
            camZoom2 = camZoom;
            camBearing2 = camBearing;
            showTraffic2 = showTraffic;
            onMapClick = function1;
            followRider3 = followRider2;
            modifier3 = modifier2;
            trafficIntervals2 = trafficIntervals;
            destinations2 = destinations;
            navMode2 = navMode;
            riderBearing2 = riderBearing;
            recenterKey2 = recenterKey;
            centerOnRiderInitially2 = centerOnRiderInitially;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.WayfindrMapKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj7, Object obj8) {
                    return WayfindrMapKt.GoogleWayfindrMap$lambda$14(riderLat, riderLng, pair, destinations2, routePoints, trafficIntervals2, hasLocationPermission, modifier3, fitRoute2, navMode2, riderBearing2, recenterKey2, followRider3, centerOnRiderInitially2, camLat2, camLng2, camZoom2, camBearing2, showTraffic2, onMapClick, $changed, $changed1, i, (Composer) obj7, ((Integer) obj8).intValue());
                }
            });
        }
    }

    static final Unit GoogleWayfindrMap$lambda$13(List $routePoints, List $trafficIntervals, List $markerDestinations, Double $riderLat, Double $riderLng, boolean $navMode, float $riderBearing, MarkerState $riderMarkerState, List $destinations, MarkerState $destMarkerState, Composer $composer, int $changed) {
        int i;
        boolean z;
        MarkerState rememberMarkerState;
        long Color;
        Composer composer = $composer;
        ComposerKt.sourceInformation(composer, "C:WayfindrMap.kt#7crva5");
        if (composer.shouldExecute(($changed & 3) != 2, $changed & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(45156910, $changed, -1, "com.example.wayfindr.ui.components.GoogleWayfindrMap.<anonymous> (WayfindrMap.kt:166)");
            }
            if ($routePoints.size() >= 2) {
                composer.startReplaceGroup(944850540);
                ComposerKt.sourceInformation(composer, "167@6695L386,*179@7374L731");
                List list = $routePoints;
                int i2 = 0;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterable<GeoPoint> iterable = list;
                for (GeoPoint geoPoint : iterable) {
                    arrayList.add(new LatLng(geoPoint.getLat(), geoPoint.getLng()));
                    i2 = i2;
                    iterable = iterable;
                }
                int i3 = 0;
                PolylineKt.m8383PolylineUt8lOTo((List) arrayList, false, ColorKt.Color(4282549748L), new RoundCap(), false, 2, null, new RoundCap(), null, false, 16.0f, 0.0f, null, composer, 196992, 6, 6994);
                Iterator it = $trafficIntervals.iterator();
                while (it.hasNext()) {
                    TrafficInterval trafficInterval = (TrafficInterval) it.next();
                    int coerceIn = RangesKt.coerceIn(trafficInterval.getStartPointIndex(), i3, CollectionsKt.getLastIndex($routePoints));
                    int coerceIn2 = RangesKt.coerceIn(trafficInterval.getEndPointIndex(), coerceIn, CollectionsKt.getLastIndex($routePoints));
                    if (coerceIn2 > coerceIn) {
                        Iterable subList = $routePoints.subList(coerceIn, coerceIn2 + 1);
                        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList, 10));
                        Iterable<GeoPoint> iterable2 = subList;
                        for (GeoPoint geoPoint2 : iterable2) {
                            arrayList2.add(new LatLng(geoPoint2.getLat(), geoPoint2.getLng()));
                            iterable2 = iterable2;
                            coerceIn = coerceIn;
                        }
                        ArrayList arrayList3 = (List) arrayList2;
                        switch (WhenMappings.$EnumSwitchMapping$0[trafficInterval.getSpeed().ordinal()]) {
                            case 1:
                                Color = ColorKt.Color(4282549748L);
                                break;
                            case 2:
                                Color = ColorKt.Color(4294552320L);
                                break;
                            case 3:
                                Color = ColorKt.Color(4293542709L);
                                break;
                            case 4:
                                Color = ColorKt.Color(4282549748L);
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                        PolylineKt.m8383PolylineUt8lOTo(arrayList3, false, Color, new RoundCap(), false, 2, null, new RoundCap(), null, false, 16.0f, 0.0f, null, $composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 6, 6994);
                    }
                    i3 = 0;
                }
                composer = $composer;
                composer.endReplaceGroup();
                i = 938177108;
            } else {
                i = 938177108;
                composer.startReplaceGroup(938177108);
                composer.endReplaceGroup();
            }
            composer.startReplaceGroup(446169474);
            ComposerKt.sourceInformation(composer, "*199@8465L60,199@8443L82,200@8538L207");
            int i4 = 0;
            for (Object obj : $markerDestinations) {
                int i5 = i4 + 1;
                if (i4 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Pair pair = (Pair) obj;
                if (i4 == 0 && $destinations.isEmpty()) {
                    composer.startReplaceGroup(-1881483718);
                    composer.endReplaceGroup();
                    rememberMarkerState = $destMarkerState;
                    z = true;
                } else {
                    composer.startReplaceGroup(-1881482484);
                    ComposerKt.sourceInformation(composer, "198@8365L65");
                    z = true;
                    rememberMarkerState = MarkerKt.rememberMarkerState(null, new LatLng(((Number) pair.getFirst()).doubleValue(), ((Number) pair.getSecond()).doubleValue()), composer, 0, 1);
                    composer.endReplaceGroup();
                }
                ComposerKt.sourceInformationMarkerStart(composer, -1881479289, "CC(remember):WayfindrMap.kt#9igjgp");
                boolean changedInstance = composer.changedInstance(rememberMarkerState) | composer.changed(pair);
                Object rememberedValue = $composer.rememberedValue();
                if (changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                    Object obj2 = (Function2) new WayfindrMapKt$GoogleWayfindrMap$8$3$1$1(rememberMarkerState, pair, null);
                    $composer.updateRememberedValue(obj2);
                    rememberedValue = obj2;
                }
                ComposerKt.sourceInformationMarkerEnd(composer);
                EffectsKt.LaunchedEffect(pair, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, composer, 0);
                MarkerKt.m8374Markerqld6geY(rememberMarkerState, null, 0.0f, Offset.m4841constructorimpl((Float.floatToRawIntBits(0.5f) << 32) | (Float.floatToRawIntBits(0.5f) & 4294967295L)), false, false, BitmapDescriptorFactory.fromBitmap(destPinBitmap()), 0L, 0.0f, null, null, null, false, 0.0f, null, null, null, null, $composer, MarkerState.$stable | 3072, 0, 262070);
                composer = $composer;
                i4 = i5;
            }
            composer.endReplaceGroup();
            if ($riderLat == null || $riderLng == null) {
                composer.startReplaceGroup(i);
            } else {
                composer.startReplaceGroup(946964151);
                ComposerKt.sourceInformation(composer, "209@8855L485");
                MarkerKt.m8374Markerqld6geY($riderMarkerState, null, 0.0f, Offset.m4841constructorimpl((Float.floatToRawIntBits(0.5f) << 32) | (Float.floatToRawIntBits(0.5f) & 4294967295L)), false, true, BitmapDescriptorFactory.fromBitmap(chevronBitmap()), 0L, $navMode ? 0.0f : $riderBearing, null, null, null, false, 0.0f, null, null, null, null, $composer, MarkerState.$stable | 199680, 0, 261782);
                composer = $composer;
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

    private static final Bitmap chevronBitmap() {
        Bitmap bmp = Bitmap.createBitmap(MenuKt.InTransitionDuration, MenuKt.InTransitionDuration, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
        Canvas c = new Canvas(bmp);
        Paint p = new Paint(1);
        float cx = MenuKt.InTransitionDuration / 2.0f;
        float cy = MenuKt.InTransitionDuration / 2.0f;
        p.setColor(Color.argb(40, 66, 133, 244));
        Path conePath = new Path();
        conePath.moveTo(cx, cy);
        conePath.lineTo(cx - 30.0f, cy - 50.0f);
        conePath.lineTo(30.0f + cx, cy - 50.0f);
        conePath.close();
        c.drawPath(conePath, p);
        p.setColor(-1);
        c.drawCircle(cx, cy, 22.0f, p);
        p.setColor(Color.rgb(66, 133, 244));
        c.drawCircle(cx, cy, 18.0f, p);
        p.setColor(-1);
        Path arrowPath = new Path();
        arrowPath.moveTo(cx, cy - 12.0f);
        arrowPath.lineTo(cx + 10.0f, cy + 10.0f);
        arrowPath.lineTo(cx, 5.0f + cy);
        arrowPath.lineTo(cx - 10.0f, 10.0f + cy);
        arrowPath.close();
        c.drawPath(arrowPath, p);
        return bmp;
    }

    private static final Bitmap destPinBitmap() {
        Bitmap bmp = Bitmap.createBitmap(72, 72, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
        Canvas c = new Canvas(bmp);
        Paint p = new Paint(1);
        float cx = 72 / 2.0f;
        float cy = 72 / 2.0f;
        p.setColor(-1);
        c.drawCircle(cx, cy, 22.0f, p);
        p.setColor(Color.rgb(234, 67, 53));
        c.drawCircle(cx, cy, 18.0f, p);
        p.setColor(-1);
        c.drawCircle(cx, cy, 6.0f, p);
        return bmp;
    }
}
