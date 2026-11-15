package com.example.wayfindr.ui.components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
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
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.profileinstaller.ProfileVerifier;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.MapTheme;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.TrafficInterval;
import com.example.wayfindr.dash.nav.TrafficSpeed;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import org.maplibre.android.MapLibre;
import org.maplibre.android.camera.CameraUpdate;
import org.maplibre.android.maps.MapLibreMap;
import org.maplibre.android.maps.MapLibreMapOptions;
import org.maplibre.android.maps.MapView;
import org.maplibre.android.plugins.annotation.LineManager;
import org.maplibre.android.plugins.annotation.LineOptions;
import org.maplibre.android.plugins.annotation.SymbolManager;

/* compiled from: FreeWayfindrMap.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a«\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\u001a\b\u0002\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\b2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00122\b\b\u0002\u0010\u0016\u001a\u00020\u00122\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00122\b\b\u0002\u0010\u001c\u001a\u00020\u00122\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00182\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00182\b\b\u0002\u0010!\u001a\u00020\u00122\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0001\u0018\u00010#H\u0007¢\u0006\u0002\u0010%\u001a\u0081\u0002\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020)2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0014\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u00032\b\u0010\u001e\u001a\u0004\u0018\u00010\u00032\b\u0010\u001f\u001a\u0004\u0018\u00010\u00182\b\u0010 \u001a\u0004\u0018\u00010\u00182\u0014\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0001\u0018\u00010#H\u0003¢\u0006\u0002\u0010-\u001a,\u0010.\u001a\u00020\u00012\u0006\u0010/\u001a\u0002002\f\u00101\u001a\b\u0012\u0004\u0012\u00020\n0\b2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\f0\bH\u0002\u001a\b\u00103\u001a\u000204H\u0002\u001a\b\u00105\u001a\u000204H\u0002\"\u000e\u0010&\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010'\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010(\u001a\u00020)X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010*\u001a\u00020)X\u0082T¢\u0006\u0002\n\u0000¨\u00066²\u0006\n\u00107\u001a\u000208X\u008a\u0084\u0002²\u0006\f\u00109\u001a\u0004\u0018\u00010:X\u008a\u008e\u0002²\u0006\f\u0010;\u001a\u0004\u0018\u000100X\u008a\u008e\u0002²\u0006\f\u0010<\u001a\u0004\u0018\u00010=X\u008a\u008e\u0002²\u0006\n\u0010>\u001a\u00020\u0012X\u008a\u008e\u0002²\u0006\u0018\u0010?\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u0001\u0018\u00010#X\u008a\u0084\u0002"}, d2 = {"WayfindrMap", "", "riderLat", "", "riderLng", "dest", "Lkotlin/Pair;", "destinations", "", "routePoints", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "trafficIntervals", "Lcom/example/wayfindr/dash/nav/TrafficInterval;", "routeAlternatives", "Lcom/example/wayfindr/dash/nav/Route;", "selectedRouteIndex", "", "hasLocationPermission", "", "modifier", "Landroidx/compose/ui/Modifier;", "fitRoute", "navMode", "riderBearing", "", "recenterKey", "", "followRider", "centerOnRiderInitially", "camLat", "camLng", "camZoom", "camBearing", "showTraffic", "onMapClick", "Lkotlin/Function1;", "Lcom/google/android/gms/maps/model/LatLng;", "(Ljava/lang/Double;Ljava/lang/Double;Lkotlin/Pair;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;IZLandroidx/compose/ui/Modifier;ZZFJZZLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Float;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;IIII)V", "ZOOM_OFFSET", "FOLLOW_ZOOM", "RIDER_ICON", "", "DEST_ICON", "FreeWayfindrMap", "styleJson", "(Ljava/lang/String;Ljava/lang/Double;Ljava/lang/Double;Lkotlin/Pair;Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;ILandroidx/compose/ui/Modifier;ZZFJZZLjava/lang/Double;Ljava/lang/Double;Ljava/lang/Float;Ljava/lang/Float;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "drawTraffic", "lineManager", "Lorg/maplibre/android/plugins/annotation/LineManager;", "points", "intervals", "freeChevronBitmap", "Landroid/graphics/Bitmap;", "freeDestPinBitmap", "app", "mapTheme", "Lcom/example/wayfindr/dash/MapTheme;", "map", "Lorg/maplibre/android/maps/MapLibreMap;", "lineMgr", "symbolMgr", "Lorg/maplibre/android/plugins/annotation/SymbolManager;", "styleReady", "clickHandler"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class FreeWayfindrMapKt {
    private static final String DEST_ICON = "dest-pin";
    private static final double FOLLOW_ZOOM = 14.5d;
    private static final String RIDER_ICON = "rider-chevron";
    private static final double ZOOM_OFFSET = 1.0d;

    /* compiled from: FreeWayfindrMap.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            try {
                iArr[Lifecycle.Event.ON_START.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Lifecycle.Event.ON_RESUME.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Lifecycle.Event.ON_PAUSE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Lifecycle.Event.ON_STOP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[TrafficSpeed.values().length];
            try {
                iArr2[TrafficSpeed.SLOW.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr2[TrafficSpeed.TRAFFIC_JAM.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    static final Unit FreeWayfindrMap$lambda$38(String str, Double d, Double d2, Pair pair, List list, List list2, List list3, List list4, int i, Modifier modifier, boolean z, boolean z2, float f, long j, boolean z3, boolean z4, Double d3, Double d4, Float f2, Float f3, Function1 function1, int i2, int i3, int i4, Composer composer, int i5) {
        FreeWayfindrMap(str, d, d2, pair, list, list2, list3, list4, i, modifier, z, z2, f, j, z3, z4, d3, d4, f2, f3, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), RecomposeScopeImplKt.updateChangedFlags(i4));
        return Unit.INSTANCE;
    }

    static final Unit WayfindrMap$lambda$3(Double d, Double d2, Pair pair, List list, List list2, List list3, List list4, int i, boolean z, Modifier modifier, boolean z2, boolean z3, float f, long j, boolean z4, boolean z5, Double d3, Double d4, Float f2, Float f3, boolean z6, Function1 function1, int i2, int i3, int i4, int i5, Composer composer, int i6) {
        WayfindrMap(d, d2, pair, list, list2, list3, list4, i, z, modifier, z2, z3, f, j, z4, z5, d3, d4, f2, f3, z6, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), RecomposeScopeImplKt.updateChangedFlags(i4), i5);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code restructure failed: missing block: B:127:0x03f7, code lost:
    
        if (r6 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L286;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final void WayfindrMap(final Double riderLat, final Double riderLng, final Pair<Double, Double> pair, List<Pair<Double, Double>> list, final List<GeoPoint> routePoints, List<TrafficInterval> list2, List<Route> list3, int selectedRouteIndex, final boolean hasLocationPermission, Modifier modifier, boolean fitRoute, boolean navMode, float riderBearing, long recenterKey, boolean followRider, boolean centerOnRiderInitially, Double camLat, Double camLng, Float camZoom, Float camBearing, boolean showTraffic, Function1<? super LatLng, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int $changed2, final int i) {
        List destinations;
        List list4;
        List list5;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int $dirty1;
        int $dirty12;
        Composer $composer2;
        final int selectedRouteIndex2;
        final boolean navMode2;
        final float riderBearing2;
        final boolean centerOnRiderInitially2;
        final boolean centerOnRiderInitially3;
        final Double camLng2;
        final Double camLng3;
        final Float camBearing2;
        final Float camBearing3;
        final boolean showTraffic2;
        final Function1 onMapClick;
        final List destinations2;
        final List routeAlternatives;
        final List trafficIntervals;
        final Modifier modifier2;
        final boolean navMode3;
        final long recenterKey2;
        List trafficIntervals2;
        Modifier modifier3;
        List destinations3;
        boolean fitRoute2;
        boolean navMode4;
        float riderBearing3;
        long recenterKey3;
        boolean followRider2;
        boolean centerOnRiderInitially4;
        Double camLat2;
        int i8;
        Double camLng4;
        Float camZoom2;
        Float camBearing4;
        boolean showTraffic3;
        Function1 onMapClick2;
        Intrinsics.checkNotNullParameter(routePoints, "routePoints");
        Composer $composer3 = $composer.startRestartGroup(1073675790);
        ComposerKt.sourceInformation($composer3, "C(WayfindrMap)N(riderLat,riderLng,dest,destinations,routePoints,trafficIntervals,routeAlternatives,selectedRouteIndex,hasLocationPermission,modifier,fitRoute,navMode,riderBearing,recenterKey,followRider,centerOnRiderInitially,camLat,camLng,camZoom,camBearing,showTraffic,onMapClick)80@3246L7,81@3271L32,82@3340L16,83@3377L58:FreeWayfindrMap.kt#7crva5");
        int $dirty = $changed;
        int $dirty13 = $changed1;
        int $dirty2 = $changed2;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(riderLat) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(riderLng) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changed(pair) ? 256 : 128;
        }
        int i9 = i & 8;
        if (i9 != 0) {
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
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            list4 = list2;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            list4 = list2;
            $dirty |= $composer3.changedInstance(list4) ? 131072 : 65536;
        } else {
            list4 = list2;
        }
        int i11 = i & 64;
        if (i11 != 0) {
            $dirty |= 1572864;
            list5 = list3;
        } else if (($changed & 1572864) == 0) {
            list5 = list3;
            $dirty |= $composer3.changedInstance(list5) ? 1048576 : 524288;
        } else {
            list5 = list3;
        }
        int i12 = i & 128;
        if (i12 != 0) {
            $dirty |= 12582912;
            i2 = i12;
        } else if (($changed & 12582912) == 0) {
            i2 = i12;
            $dirty |= $composer3.changed(selectedRouteIndex) ? 8388608 : 4194304;
        } else {
            i2 = i12;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty |= 805306368;
            i3 = i13;
        } else if (($changed & 805306368) == 0) {
            i3 = i13;
            $dirty |= $composer3.changed(modifier) ? 536870912 : 268435456;
        } else {
            i3 = i13;
        }
        int i14 = i & 1024;
        if (i14 != 0) {
            $dirty13 |= 6;
            i4 = i14;
        } else if (($changed1 & 6) == 0) {
            i4 = i14;
            $dirty13 |= $composer3.changed(fitRoute) ? 4 : 2;
        } else {
            i4 = i14;
        }
        int i15 = i & 2048;
        if (i15 != 0) {
            $dirty13 |= 48;
            i5 = i15;
        } else if (($changed1 & 48) == 0) {
            i5 = i15;
            $dirty13 |= $composer3.changed(navMode) ? 32 : 16;
        } else {
            i5 = i15;
        }
        int i16 = i & 4096;
        if (i16 != 0) {
            $dirty13 |= 384;
            i6 = i16;
        } else {
            i6 = i16;
            if (($changed1 & 384) == 0) {
                $dirty13 |= $composer3.changed(riderBearing) ? 256 : 128;
            }
        }
        int i17 = i & 8192;
        if (i17 != 0) {
            i7 = i17;
            $dirty1 = $dirty13 | 3072;
        } else {
            i7 = i17;
            if (($changed1 & 3072) == 0) {
                $dirty1 = $dirty13 | ($composer3.changed(recenterKey) ? 2048 : 1024);
            } else {
                $dirty1 = $dirty13;
            }
        }
        int i18 = i & 16384;
        if (i18 != 0) {
            $dirty1 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changed(followRider) ? 16384 : 8192;
        }
        int i19 = i & 32768;
        if (i19 != 0) {
            $dirty12 = $dirty1 | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty12 = $dirty1 | ($composer3.changed(centerOnRiderInitially) ? 131072 : 65536);
        } else {
            $dirty12 = $dirty1;
        }
        int i20 = i & 65536;
        if (i20 != 0) {
            $dirty12 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty12 |= $composer3.changed(camLat) ? 1048576 : 524288;
        }
        int i21 = i & 131072;
        if (i21 != 0) {
            $dirty12 |= 12582912;
        } else if (($changed1 & 12582912) == 0) {
            $dirty12 |= $composer3.changed(camLng) ? 8388608 : 4194304;
        }
        int i22 = i & 262144;
        if (i22 != 0) {
            $dirty12 |= 100663296;
        } else if (($changed1 & 100663296) == 0) {
            $dirty12 |= $composer3.changed(camZoom) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i23 = i & 524288;
        if (i23 != 0) {
            $dirty12 |= 805306368;
        } else if (($changed1 & 805306368) == 0) {
            $dirty12 |= $composer3.changed(camBearing) ? 536870912 : 268435456;
        }
        int $dirty14 = $dirty12;
        int i24 = i & 2097152;
        if (i24 != 0) {
            $dirty2 |= 48;
        } else if (($changed2 & 48) == 0) {
            $dirty2 |= $composer3.changedInstance(function1) ? 32 : 16;
        }
        int $dirty22 = $dirty2;
        if ($composer3.shouldExecute(((273228947 & $dirty) == 273228946 && (306783379 & $dirty14) == 306783378 && ($dirty22 & 17) == 16) ? false : true, $dirty & 1)) {
            if (i9 != 0) {
                destinations = CollectionsKt.emptyList();
            }
            if (i10 == 0) {
                trafficIntervals2 = list4;
            } else {
                trafficIntervals2 = CollectionsKt.emptyList();
            }
            if (i11 == 0) {
                routeAlternatives = list5;
            } else {
                routeAlternatives = CollectionsKt.emptyList();
            }
            if (i2 == 0) {
                selectedRouteIndex2 = selectedRouteIndex;
            } else {
                selectedRouteIndex2 = 0;
            }
            if (i3 == 0) {
                modifier3 = modifier;
            } else {
                modifier3 = Modifier.INSTANCE;
            }
            if (i4 == 0) {
                destinations3 = destinations;
                fitRoute2 = fitRoute;
            } else {
                List list6 = destinations;
                fitRoute2 = false;
                destinations3 = list6;
            }
            if (i5 == 0) {
                navMode4 = navMode;
            } else {
                navMode4 = false;
            }
            if (i6 == 0) {
                riderBearing3 = riderBearing;
            } else {
                riderBearing3 = 0.0f;
            }
            if (i7 == 0) {
                recenterKey3 = recenterKey;
            } else {
                recenterKey3 = 0;
            }
            if (i18 == 0) {
                followRider2 = followRider;
            } else {
                followRider2 = true;
            }
            if (i19 == 0) {
                centerOnRiderInitially4 = centerOnRiderInitially;
            } else {
                centerOnRiderInitially4 = false;
            }
            if (i20 == 0) {
                camLat2 = camLat;
            } else {
                camLat2 = null;
            }
            if (i21 == 0) {
                i8 = i24;
                camLng4 = camLng;
            } else {
                camLng4 = null;
                i8 = i24;
            }
            if (i22 == 0) {
                camZoom2 = camZoom;
            } else {
                camZoom2 = null;
            }
            if (i23 == 0) {
                camBearing4 = camBearing;
            } else {
                camBearing4 = null;
            }
            if ((i & 1048576) == 0) {
                showTraffic3 = showTraffic;
            } else {
                showTraffic3 = true;
            }
            if (i8 == 0) {
                onMapClick2 = function1;
            } else {
                onMapClick2 = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1073675790, $dirty, $dirty14, "com.example.wayfindr.ui.components.WayfindrMap (FreeWayfindrMap.kt:79)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            List destinations4 = destinations3;
            int $dirty3 = $dirty;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Context ctx = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer3, 1010311758, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                rememberedValue = DashConfig.INSTANCE.get(ctx);
                $composer3.updateRememberedValue(rememberedValue);
            }
            DashConfig config = (DashConfig) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            List trafficIntervals3 = trafficIntervals2;
            State mapTheme$delegate = SnapshotStateKt.collectAsState(config.getMapTheme(), null, $composer3, 0, 1);
            MapTheme WayfindrMap$lambda$1 = WayfindrMap$lambda$1(mapTheme$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, 1010315176, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            boolean changed = $composer3.changed(WayfindrMap$lambda$1.ordinal());
            Object rememberedValue2 = $composer3.rememberedValue();
            if (changed) {
            }
            Object onlineThemeStyleJson = OsmMapStyleKt.onlineThemeStyleJson(ctx, WayfindrMap$lambda$1(mapTheme$delegate));
            $composer3.updateRememberedValue(onlineThemeStyleJson);
            rememberedValue2 = onlineThemeStyleJson;
            String styleJson = (String) rememberedValue2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.startMovableGroup(1010319870, styleJson);
            ComposerKt.sourceInformation($composer3, "86@3539L337");
            trafficIntervals = trafficIntervals3;
            destinations2 = destinations4;
            FreeWayfindrMap(styleJson, riderLat, riderLng, pair, destinations2, routePoints, trafficIntervals, routeAlternatives, selectedRouteIndex2, modifier3, fitRoute2, navMode4, riderBearing3, recenterKey3, followRider2, centerOnRiderInitially4, camLat2, camLng4, camZoom2, camBearing4, onMapClick2, $composer3, (($dirty3 << 3) & 112) | (($dirty3 << 3) & 896) | (($dirty3 << 3) & 7168) | (($dirty3 << 3) & 57344) | (($dirty3 << 3) & 458752) | (($dirty3 << 3) & 3670016) | (($dirty3 << 3) & 29360128) | (($dirty3 << 3) & 234881024) | ($dirty3 & 1879048192), ($dirty14 & 14) | ($dirty14 & 112) | ($dirty14 & 896) | ($dirty14 & 7168) | (57344 & $dirty14) | (458752 & $dirty14) | (3670016 & $dirty14) | (29360128 & $dirty14) | (234881024 & $dirty14) | (1879048192 & $dirty14), ($dirty22 >> 3) & 14);
            $composer2 = $composer3;
            $composer2.endMovableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            onMapClick = onMapClick2;
            showTraffic2 = showTraffic3;
            camBearing3 = camBearing4;
            camBearing2 = camZoom2;
            camLng3 = camLng4;
            camLng2 = camLat2;
            centerOnRiderInitially3 = centerOnRiderInitially4;
            centerOnRiderInitially2 = followRider2;
            recenterKey2 = recenterKey3;
            riderBearing2 = riderBearing3;
            navMode2 = navMode4;
            navMode3 = fitRoute2;
            modifier2 = modifier3;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            selectedRouteIndex2 = selectedRouteIndex;
            navMode2 = navMode;
            riderBearing2 = riderBearing;
            centerOnRiderInitially2 = followRider;
            centerOnRiderInitially3 = centerOnRiderInitially;
            camLng2 = camLat;
            camLng3 = camLng;
            camBearing2 = camZoom;
            camBearing3 = camBearing;
            showTraffic2 = showTraffic;
            onMapClick = function1;
            destinations2 = destinations;
            routeAlternatives = list5;
            trafficIntervals = list4;
            modifier2 = modifier;
            navMode3 = fitRoute;
            recenterKey2 = recenterKey;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return FreeWayfindrMapKt.WayfindrMap$lambda$3(riderLat, riderLng, pair, destinations2, routePoints, trafficIntervals, routeAlternatives, selectedRouteIndex2, hasLocationPermission, modifier2, navMode3, navMode2, riderBearing2, recenterKey2, centerOnRiderInitially2, centerOnRiderInitially3, camLng2, camLng3, camBearing2, camBearing3, showTraffic2, onMapClick, $changed, $changed1, $changed2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final MapTheme WayfindrMap$lambda$1(State<? extends MapTheme> state) {
        return (MapTheme) state.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:170:0x03ff, code lost:
    
        if (r3 != androidx.compose.runtime.Composer.INSTANCE.getEmpty()) goto L213;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:182:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x04e6  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x050b  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x05b8  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x05cf  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0654  */
    /* JADX WARN: Removed duplicated region for block: B:226:0x065e  */
    /* JADX WARN: Removed duplicated region for block: B:229:0x0669  */
    /* JADX WARN: Removed duplicated region for block: B:232:0x0674  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x070b  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0783  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x078e  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0799  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x07a4  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x07b4  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x07bf  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0847  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x0853  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x0872  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x087d  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x088b  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0895  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x08a2  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x0933  */
    /* JADX WARN: Removed duplicated region for block: B:314:0x08a5  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0898  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x088e  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0880  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0875  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0868  */
    /* JADX WARN: Removed duplicated region for block: B:320:0x0856  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x084a  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x07c2  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x07b7  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x07a7  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x079c  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x0791  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x0786  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x070e  */
    /* JADX WARN: Removed duplicated region for block: B:332:0x0677  */
    /* JADX WARN: Removed duplicated region for block: B:333:0x066c  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0657  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x05d2  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x05bb  */
    /* JADX WARN: Removed duplicated region for block: B:342:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x050d  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x0502  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x04e8  */
    /* JADX WARN: Type inference failed for: r14v10 */
    /* JADX WARN: Type inference failed for: r14v11, types: [int] */
    /* JADX WARN: Type inference failed for: r14v14 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final void FreeWayfindrMap(final String styleJson, final Double riderLat, final Double riderLng, final Pair<Double, Double> pair, final List<Pair<Double, Double>> list, final List<GeoPoint> list2, final List<TrafficInterval> list3, final List<Route> list4, final int selectedRouteIndex, final Modifier modifier, final boolean fitRoute, final boolean navMode, final float riderBearing, final long recenterKey, final boolean followRider, final boolean centerOnRiderInitially, final Double camLat, final Double camLng, final Float camZoom, final Float camBearing, final Function1<? super LatLng, Unit> function1, Composer $composer, final int $changed, final int $changed1, final int $changed2) {
        String str;
        int $dirty;
        Composer $composer2;
        int $dirty1;
        MapView mapView;
        AtomicBoolean destroyed;
        MapView mapView2;
        List<Pair<Double, Double>> list5;
        AtomicBoolean destroyed2;
        int $dirty12;
        Object[] objArr;
        boolean changedInstance;
        Object rememberedValue;
        int $dirty13;
        String str2;
        int $dirty2;
        boolean z;
        Object[] objArr2;
        Composer $composer3;
        MutableState symbolMgr$delegate;
        AtomicBoolean destroyed3;
        MutableState lineMgr$delegate;
        Long valueOf;
        String str3;
        int $dirty14;
        int $dirty3;
        boolean changedInstance2;
        Object rememberedValue2;
        String str4;
        int $dirty4;
        int $dirty15;
        Long l;
        Boolean valueOf2;
        Boolean valueOf3;
        Boolean valueOf4;
        boolean changedInstance3;
        FreeWayfindrMapKt$FreeWayfindrMap$6$1 rememberedValue3;
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        int $dirty5;
        ?? r14;
        MutableState styleReady$delegate;
        AtomicBoolean destroyed4;
        boolean changedInstance4;
        FreeWayfindrMapKt$FreeWayfindrMap$7$1 rememberedValue4;
        MutableState styleReady$delegate2;
        AtomicBoolean destroyed5;
        Object[] objArr3;
        boolean changedInstance5;
        Object rememberedValue5;
        Object[] objArr4;
        int $dirty16;
        int $dirty6;
        int $dirty17;
        MutableState styleReady$delegate3;
        boolean changedInstance6;
        Object rememberedValue6;
        boolean changedInstance7;
        Object rememberedValue7;
        Composer $composer4 = $composer.startRestartGroup(901814749);
        ComposerKt.sourceInformation($composer4, "C(FreeWayfindrMap)N(styleJson,riderLat,riderLng,dest,destinations,routePoints,trafficIntervals,routeAlternatives,selectedRouteIndex,modifier,fitRoute,navMode,riderBearing,recenterKey,followRider,centerOnRiderInitially,camLat,camLng,camZoom,camBearing,onMapClick)126@4950L7,127@4962L42,131@5302L106,136@5591L61,137@5668L47,138@5735L47,139@5804L49,140@5876L34,141@5935L32,143@6014L7,144@6059L803,144@6026L836,165@6892L1185,165@6868L1209,198@8407L2091,197@8239L2259,242@10734L178,242@10694L218,246@11006L205,246@10917L294,250@11285L363,250@11216L432,258@11736L392,258@11653L475,267@12313L561,267@12221L653,279@12902L11,279@12880L55:FreeWayfindrMap.kt#7crva5");
        int $dirty7 = $changed;
        int $dirty18 = $changed1;
        int $dirty22 = $changed2;
        if (($changed & 6) == 0) {
            str = styleJson;
            $dirty7 |= $composer4.changed(str) ? 4 : 2;
        } else {
            str = styleJson;
        }
        if (($changed & 48) == 0) {
            $dirty7 |= $composer4.changed(riderLat) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty7 |= $composer4.changed(riderLng) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty7 |= $composer4.changed(pair) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty7 |= $composer4.changedInstance(list) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty7 |= $composer4.changedInstance(list2) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty7 |= $composer4.changedInstance(list3) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty7 |= $composer4.changedInstance(list4) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty7 |= $composer4.changed(selectedRouteIndex) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty7 |= $composer4.changed(modifier) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty18 |= $composer4.changed(fitRoute) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty18 |= $composer4.changed(navMode) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty18 |= $composer4.changed(riderBearing) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty18 |= $composer4.changed(recenterKey) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty18 |= $composer4.changed(followRider) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty18 |= $composer4.changed(centerOnRiderInitially) ? 131072 : 65536;
        }
        if ((1572864 & $changed1) == 0) {
            $dirty18 |= $composer4.changed(camLat) ? 1048576 : 524288;
        }
        if (($changed1 & 12582912) == 0) {
            $dirty18 |= $composer4.changed(camLng) ? 8388608 : 4194304;
        }
        if (($changed1 & 100663296) == 0) {
            $dirty18 |= $composer4.changed(camZoom) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed1 & 805306368) == 0) {
            $dirty18 |= $composer4.changed(camBearing) ? 536870912 : 268435456;
        }
        if (($changed2 & 6) == 0) {
            $dirty22 |= $composer4.changedInstance(function1) ? 4 : 2;
        }
        int $dirty23 = $dirty22;
        if ($composer4.shouldExecute(((306783379 & $dirty7) == 306783378 && (306783379 & $dirty18) == 306783378 && ($dirty23 & 3) == 2) ? false : true, $dirty7 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(901814749, $dirty7, $dirty18, "com.example.wayfindr.ui.components.FreeWayfindrMap (FreeWayfindrMap.kt:125)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            int $dirty8 = $dirty7;
            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume = $composer4.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            Context context = (Context) consume;
            ComposerKt.sourceInformationMarkerStart($composer4, -939394201, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue8 = $composer4.rememberedValue();
            if (rememberedValue8 == Composer.INSTANCE.getEmpty()) {
                Object mapLibre = MapLibre.getInstance(context);
                $composer4.updateRememberedValue(mapLibre);
                rememberedValue8 = mapLibre;
            }
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, -939383257, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue9 = $composer4.rememberedValue();
            if (rememberedValue9 == Composer.INSTANCE.getEmpty()) {
                MapView mapView3 = new MapView(context, new MapLibreMapOptions().textureMode(true));
                mapView3.onCreate(null);
                $composer4.updateRememberedValue(mapView3);
                rememberedValue9 = mapView3;
            }
            final MapView mapView4 = (MapView) rememberedValue9;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, -939374054, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue10 = $composer4.rememberedValue();
            if (rememberedValue10 == Composer.INSTANCE.getEmpty()) {
                $dirty1 = $dirty18;
                Object atomicBoolean = new AtomicBoolean(false);
                $composer4.updateRememberedValue(atomicBoolean);
                rememberedValue10 = atomicBoolean;
            } else {
                $dirty1 = $dirty18;
            }
            final AtomicBoolean destroyed6 = (AtomicBoolean) rememberedValue10;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, -939371604, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue11 = $composer4.rememberedValue();
            if (rememberedValue11 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer4.updateRememberedValue(mutableStateOf$default);
                rememberedValue11 = mutableStateOf$default;
            }
            MutableState map$delegate = (MutableState) rememberedValue11;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, -939369460, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue12 = $composer4.rememberedValue();
            if (rememberedValue12 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer4.updateRememberedValue(mutableStateOf$default2);
                rememberedValue12 = mutableStateOf$default2;
            }
            MutableState lineMgr$delegate2 = (MutableState) rememberedValue12;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, -939367250, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue13 = $composer4.rememberedValue();
            if (rememberedValue13 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer4.updateRememberedValue(mutableStateOf$default3);
                rememberedValue13 = mutableStateOf$default3;
            }
            MutableState symbolMgr$delegate2 = (MutableState) rememberedValue13;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            ComposerKt.sourceInformationMarkerStart($composer4, -939364961, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            Object rememberedValue14 = $composer4.rememberedValue();
            if (rememberedValue14 == Composer.INSTANCE.getEmpty()) {
                Object mutableStateOf$default4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                $composer4.updateRememberedValue(mutableStateOf$default4);
                rememberedValue14 = mutableStateOf$default4;
            }
            MutableState styleReady$delegate4 = (MutableState) rememberedValue14;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            State clickHandler$delegate = SnapshotStateKt.rememberUpdatedState(function1, $composer4, $dirty23 & 14);
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = AndroidCompositionLocals_androidKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart($composer4, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object consume2 = $composer4.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            final LifecycleOwner lifecycleOwner = (LifecycleOwner) consume2;
            ComposerKt.sourceInformationMarkerStart($composer4, -939358336, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            boolean changedInstance8 = $composer4.changedInstance(mapView4) | $composer4.changedInstance(lifecycleOwner) | $composer4.changedInstance(destroyed6);
            Object rememberedValue15 = $composer4.rememberedValue();
            if (changedInstance8) {
            }
            rememberedValue15 = new Function1() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return FreeWayfindrMapKt.FreeWayfindrMap$lambda$26$lambda$25(LifecycleOwner.this, mapView4, destroyed6, (DisposableEffectScope) obj);
                }
            };
            $composer4.updateRememberedValue(rememberedValue15);
            ComposerKt.sourceInformationMarkerEnd($composer4);
            EffectsKt.DisposableEffect(lifecycleOwner, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) rememberedValue15, $composer4, 0);
            ComposerKt.sourceInformationMarkerStart($composer4, -939331298, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            boolean changedInstance9 = $composer4.changedInstance(mapView4) | $composer4.changedInstance(destroyed6) | $composer4.changed(clickHandler$delegate) | (($dirty8 & 14) == 4);
            Object rememberedValue16 = $composer4.rememberedValue();
            if (changedInstance9) {
                mapView = mapView4;
            } else {
                mapView = mapView4;
                if (rememberedValue16 != Composer.INSTANCE.getEmpty()) {
                    destroyed = destroyed6;
                    mapView2 = mapView;
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    EffectsKt.LaunchedEffect(mapView2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue16, $composer4, 0);
                    list5 = list;
                    if (list5.isEmpty()) {
                        list5 = CollectionsKt.listOfNotNull(pair);
                    }
                    List markerDestinations = list5;
                    final MapView mapView5 = mapView2;
                    destroyed2 = destroyed;
                    $dirty12 = $dirty1;
                    objArr = new Object[]{Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate4)), list2, list3, list4, Integer.valueOf(selectedRouteIndex), markerDestinations, riderLat, riderLng, Float.valueOf(riderBearing), Boolean.valueOf(navMode)};
                    ComposerKt.sourceInformationMarkerStart($composer4, -939281912, "CC(remember):FreeWayfindrMap.kt#9igjgp");
                    changedInstance = $composer4.changedInstance(destroyed2) | $composer4.changedInstance(list4) | ((234881024 & $dirty8) != 67108864) | $composer4.changedInstance(list2) | $composer4.changedInstance(list3) | $composer4.changedInstance(markerDestinations) | (($dirty8 & 112) != 32) | (($dirty8 & 896) != 256) | (($dirty12 & 112) != 32) | (($dirty12 & 896) != 256);
                    rememberedValue = $composer4.rememberedValue();
                    if (!changedInstance || rememberedValue == Composer.INSTANCE.getEmpty()) {
                        $dirty13 = $dirty12;
                        str2 = "CC(remember):FreeWayfindrMap.kt#9igjgp";
                        $dirty2 = $dirty8;
                        z = true;
                        objArr2 = objArr;
                        $composer3 = $composer4;
                        FreeWayfindrMapKt$FreeWayfindrMap$4$1 freeWayfindrMapKt$FreeWayfindrMap$4$1 = new FreeWayfindrMapKt$FreeWayfindrMap$4$1(destroyed2, list4, selectedRouteIndex, list2, list3, markerDestinations, riderLat, riderLng, navMode, riderBearing, lineMgr$delegate2, symbolMgr$delegate2, null);
                        symbolMgr$delegate = symbolMgr$delegate2;
                        destroyed3 = destroyed2;
                        lineMgr$delegate = lineMgr$delegate2;
                        rememberedValue = freeWayfindrMapKt$FreeWayfindrMap$4$1;
                        $composer4.updateRememberedValue(rememberedValue);
                    } else {
                        destroyed3 = destroyed2;
                        $dirty2 = $dirty8;
                        $dirty13 = $dirty12;
                        str2 = "CC(remember):FreeWayfindrMap.kt#9igjgp";
                        symbolMgr$delegate = symbolMgr$delegate2;
                        z = true;
                        objArr2 = objArr;
                        $composer3 = $composer4;
                        lineMgr$delegate = lineMgr$delegate2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    Composer $composer5 = $composer3;
                    EffectsKt.LaunchedEffect(objArr2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, $composer5, 0);
                    Boolean valueOf5 = Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate4));
                    valueOf = Long.valueOf(recenterKey);
                    str3 = str2;
                    ComposerKt.sourceInformationMarkerStart($composer5, -939209361, str3);
                    $dirty14 = $dirty13;
                    $dirty3 = $dirty2;
                    changedInstance2 = (($dirty14 & 7168) != 2048 ? z : false) | (($dirty3 & 112) != 32 ? z : false) | (($dirty3 & 896) != 256 ? z : false) | $composer5.changedInstance(destroyed3);
                    rememberedValue2 = $composer5.rememberedValue();
                    if (!changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        str4 = str3;
                        $dirty4 = $dirty3;
                        AtomicBoolean destroyed7 = destroyed3;
                        $dirty15 = $dirty14;
                        l = valueOf;
                        FreeWayfindrMapKt$FreeWayfindrMap$5$1 freeWayfindrMapKt$FreeWayfindrMap$5$1 = new FreeWayfindrMapKt$FreeWayfindrMap$5$1(recenterKey, riderLat, riderLng, destroyed7, styleReady$delegate4, map$delegate, null);
                        destroyed3 = destroyed7;
                        rememberedValue2 = freeWayfindrMapKt$FreeWayfindrMap$5$1;
                        $composer5.updateRememberedValue(rememberedValue2);
                    } else {
                        str4 = str3;
                        $dirty4 = $dirty3;
                        l = valueOf;
                        $dirty15 = $dirty14;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    EffectsKt.LaunchedEffect(valueOf5, l, (Function2) rememberedValue2, $composer5, ($dirty15 >> 6) & 112);
                    valueOf2 = Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate4));
                    valueOf3 = Boolean.valueOf(centerOnRiderInitially);
                    valueOf4 = Boolean.valueOf((riderLat != null || riderLng == null) ? false : z);
                    ComposerKt.sourceInformationMarkerStart($composer5, -939200630, str4);
                    changedInstance3 = ((458752 & $dirty15) != 131072 ? z : false) | (($dirty4 & 112) != 32 ? z : false) | (($dirty4 & 896) != 256 ? z : false) | (($dirty15 & 7168) != 2048 ? z : false) | $composer5.changedInstance(destroyed3);
                    rememberedValue3 = $composer5.rememberedValue();
                    if (!changedInstance3 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        bool = valueOf2;
                        bool2 = valueOf3;
                        bool3 = valueOf4;
                        $dirty5 = $dirty4;
                        r14 = 0;
                        styleReady$delegate = styleReady$delegate4;
                        destroyed4 = destroyed3;
                        rememberedValue3 = new FreeWayfindrMapKt$FreeWayfindrMap$6$1(centerOnRiderInitially, riderLat, riderLng, recenterKey, destroyed3, styleReady$delegate4, map$delegate, null);
                        $composer5.updateRememberedValue(rememberedValue3);
                    } else {
                        destroyed4 = destroyed3;
                        bool = valueOf2;
                        bool2 = valueOf3;
                        bool3 = valueOf4;
                        styleReady$delegate = styleReady$delegate4;
                        r14 = 0;
                        $dirty5 = $dirty4;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    EffectsKt.LaunchedEffect(bool, bool2, bool3, (Function2) rememberedValue3, $composer5, ($dirty15 >> 12) & 112);
                    Object[] objArr5 = {Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate)), Boolean.valueOf(fitRoute), list2, list4};
                    ComposerKt.sourceInformationMarkerStart($composer5, -939191544, str4);
                    changedInstance4 = $composer5.changedInstance(list4) | $composer5.changedInstance(list2) | (($dirty15 & 14) != 4 ? z : r14) | $composer5.changedInstance(destroyed4);
                    rememberedValue4 = $composer5.rememberedValue();
                    if (!changedInstance4 || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                        AtomicBoolean destroyed8 = destroyed4;
                        MutableState styleReady$delegate5 = styleReady$delegate;
                        styleReady$delegate2 = styleReady$delegate5;
                        destroyed5 = destroyed8;
                        rememberedValue4 = new FreeWayfindrMapKt$FreeWayfindrMap$7$1(list4, fitRoute, list2, destroyed8, styleReady$delegate5, map$delegate, null);
                        $composer5.updateRememberedValue(rememberedValue4);
                    } else {
                        destroyed5 = destroyed4;
                        styleReady$delegate2 = styleReady$delegate;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    EffectsKt.LaunchedEffect(objArr5, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue4, $composer5, (int) r14);
                    objArr3 = new Object[]{Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate2)), riderLat, riderLng, Float.valueOf(riderBearing), Boolean.valueOf(followRider), Boolean.valueOf(navMode)};
                    ComposerKt.sourceInformationMarkerStart($composer5, -939177083, str4);
                    changedInstance5 = ((57344 & $dirty15) != 16384 ? z : r14) | ((3670016 & $dirty15) != 1048576 ? z : r14) | (($dirty5 & 112) != 32 ? z : r14) | (($dirty5 & 896) != 256 ? z : r14) | $composer5.changedInstance(destroyed5) | (($dirty15 & 112) != 32 ? z : r14) | (($dirty15 & 896) != 256 ? z : r14);
                    rememberedValue5 = $composer5.rememberedValue();
                    if (!changedInstance5 || rememberedValue5 == Composer.INSTANCE.getEmpty()) {
                        objArr4 = objArr3;
                        AtomicBoolean destroyed9 = destroyed5;
                        $dirty16 = $dirty15;
                        MutableState styleReady$delegate6 = styleReady$delegate2;
                        $dirty6 = $dirty5;
                        $dirty17 = 1048576;
                        destroyed5 = destroyed9;
                        styleReady$delegate3 = styleReady$delegate6;
                        rememberedValue5 = new FreeWayfindrMapKt$FreeWayfindrMap$8$1(followRider, camLat, riderLat, riderLng, navMode, riderBearing, destroyed9, styleReady$delegate6, map$delegate, null);
                        $composer5.updateRememberedValue(rememberedValue5);
                    } else {
                        objArr4 = objArr3;
                        $dirty6 = $dirty5;
                        $dirty16 = $dirty15;
                        styleReady$delegate3 = styleReady$delegate2;
                        $dirty17 = 1048576;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    EffectsKt.LaunchedEffect(objArr4, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue5, $composer5, 0);
                    Object[] objArr6 = {Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate3)), camLat, camLng, camZoom, camBearing, pair, Boolean.valueOf(navMode), Boolean.valueOf(followRider)};
                    ComposerKt.sourceInformationMarkerStart($composer5, -939158450, str4);
                    $dirty = $dirty6;
                    changedInstance6 = (($dirty16 & 29360128) != 8388608 ? z : false) | (($dirty16 & 3670016) != $dirty17 ? z : false) | $composer5.changedInstance(destroyed5) | (($dirty16 & 234881024) != 67108864 ? z : false) | (($dirty16 & 1879048192) != 536870912 ? z : false) | (($dirty16 & 112) != 32 ? z : false) | (($dirty16 & 57344) != 16384 ? z : false) | (($dirty16 & 14) != 4 ? z : false) | (($dirty & 7168) != 2048 ? z : false);
                    rememberedValue6 = $composer5.rememberedValue();
                    if (!changedInstance6 || rememberedValue6 == Composer.INSTANCE.getEmpty()) {
                        Object obj = (Function2) new FreeWayfindrMapKt$FreeWayfindrMap$9$1(camLat, camLng, camZoom, camBearing, navMode, followRider, fitRoute, pair, destroyed5, styleReady$delegate3, map$delegate, null);
                        $composer5.updateRememberedValue(obj);
                        rememberedValue6 = obj;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    EffectsKt.LaunchedEffect(objArr6, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue6, $composer5, 0);
                    ComposerKt.sourceInformationMarkerStart($composer5, -939140152, str4);
                    changedInstance7 = $composer5.changedInstance(mapView5);
                    rememberedValue7 = $composer5.rememberedValue();
                    if (!changedInstance7 || rememberedValue7 == Composer.INSTANCE.getEmpty()) {
                        Object obj2 = new Function1() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                return FreeWayfindrMapKt.FreeWayfindrMap$lambda$37$lambda$36(MapView.this, (Context) obj3);
                            }
                        };
                        $composer5.updateRememberedValue(obj2);
                        rememberedValue7 = obj2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer5);
                    AndroidView_androidKt.AndroidView((Function1) rememberedValue7, modifier, null, $composer5, ($dirty >> 24) & 112, 4);
                    $composer2 = $composer5;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }
            destroyed = destroyed6;
            Object freeWayfindrMapKt$FreeWayfindrMap$3$1 = new FreeWayfindrMapKt$FreeWayfindrMap$3$1(mapView, destroyed, str, map$delegate, clickHandler$delegate, lineMgr$delegate2, symbolMgr$delegate2, styleReady$delegate4, null);
            mapView2 = mapView;
            Object obj3 = (Function2) freeWayfindrMapKt$FreeWayfindrMap$3$1;
            $composer4.updateRememberedValue(obj3);
            rememberedValue16 = obj3;
            ComposerKt.sourceInformationMarkerEnd($composer4);
            EffectsKt.LaunchedEffect(mapView2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue16, $composer4, 0);
            list5 = list;
            if (list5.isEmpty()) {
            }
            List markerDestinations2 = list5;
            final MapView mapView52 = mapView2;
            destroyed2 = destroyed;
            $dirty12 = $dirty1;
            objArr = new Object[]{Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate4)), list2, list3, list4, Integer.valueOf(selectedRouteIndex), markerDestinations2, riderLat, riderLng, Float.valueOf(riderBearing), Boolean.valueOf(navMode)};
            ComposerKt.sourceInformationMarkerStart($composer4, -939281912, "CC(remember):FreeWayfindrMap.kt#9igjgp");
            changedInstance = $composer4.changedInstance(destroyed2) | $composer4.changedInstance(list4) | ((234881024 & $dirty8) != 67108864) | $composer4.changedInstance(list2) | $composer4.changedInstance(list3) | $composer4.changedInstance(markerDestinations2) | (($dirty8 & 112) != 32) | (($dirty8 & 896) != 256) | (($dirty12 & 112) != 32) | (($dirty12 & 896) != 256);
            rememberedValue = $composer4.rememberedValue();
            if (changedInstance) {
            }
            $dirty13 = $dirty12;
            str2 = "CC(remember):FreeWayfindrMap.kt#9igjgp";
            $dirty2 = $dirty8;
            z = true;
            objArr2 = objArr;
            $composer3 = $composer4;
            FreeWayfindrMapKt$FreeWayfindrMap$4$1 freeWayfindrMapKt$FreeWayfindrMap$4$12 = new FreeWayfindrMapKt$FreeWayfindrMap$4$1(destroyed2, list4, selectedRouteIndex, list2, list3, markerDestinations2, riderLat, riderLng, navMode, riderBearing, lineMgr$delegate2, symbolMgr$delegate2, null);
            symbolMgr$delegate = symbolMgr$delegate2;
            destroyed3 = destroyed2;
            lineMgr$delegate = lineMgr$delegate2;
            rememberedValue = freeWayfindrMapKt$FreeWayfindrMap$4$12;
            $composer4.updateRememberedValue(rememberedValue);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Composer $composer52 = $composer3;
            EffectsKt.LaunchedEffect(objArr2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue, $composer52, 0);
            Boolean valueOf52 = Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate4));
            valueOf = Long.valueOf(recenterKey);
            str3 = str2;
            ComposerKt.sourceInformationMarkerStart($composer52, -939209361, str3);
            $dirty14 = $dirty13;
            $dirty3 = $dirty2;
            changedInstance2 = (($dirty14 & 7168) != 2048 ? z : false) | (($dirty3 & 112) != 32 ? z : false) | (($dirty3 & 896) != 256 ? z : false) | $composer52.changedInstance(destroyed3);
            rememberedValue2 = $composer52.rememberedValue();
            if (changedInstance2) {
            }
            str4 = str3;
            $dirty4 = $dirty3;
            AtomicBoolean destroyed72 = destroyed3;
            $dirty15 = $dirty14;
            l = valueOf;
            FreeWayfindrMapKt$FreeWayfindrMap$5$1 freeWayfindrMapKt$FreeWayfindrMap$5$12 = new FreeWayfindrMapKt$FreeWayfindrMap$5$1(recenterKey, riderLat, riderLng, destroyed72, styleReady$delegate4, map$delegate, null);
            destroyed3 = destroyed72;
            rememberedValue2 = freeWayfindrMapKt$FreeWayfindrMap$5$12;
            $composer52.updateRememberedValue(rememberedValue2);
            ComposerKt.sourceInformationMarkerEnd($composer52);
            EffectsKt.LaunchedEffect(valueOf52, l, (Function2) rememberedValue2, $composer52, ($dirty15 >> 6) & 112);
            valueOf2 = Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate4));
            valueOf3 = Boolean.valueOf(centerOnRiderInitially);
            valueOf4 = Boolean.valueOf((riderLat != null || riderLng == null) ? false : z);
            ComposerKt.sourceInformationMarkerStart($composer52, -939200630, str4);
            changedInstance3 = ((458752 & $dirty15) != 131072 ? z : false) | (($dirty4 & 112) != 32 ? z : false) | (($dirty4 & 896) != 256 ? z : false) | (($dirty15 & 7168) != 2048 ? z : false) | $composer52.changedInstance(destroyed3);
            rememberedValue3 = $composer52.rememberedValue();
            if (changedInstance3) {
            }
            bool = valueOf2;
            bool2 = valueOf3;
            bool3 = valueOf4;
            $dirty5 = $dirty4;
            r14 = 0;
            styleReady$delegate = styleReady$delegate4;
            destroyed4 = destroyed3;
            rememberedValue3 = new FreeWayfindrMapKt$FreeWayfindrMap$6$1(centerOnRiderInitially, riderLat, riderLng, recenterKey, destroyed3, styleReady$delegate4, map$delegate, null);
            $composer52.updateRememberedValue(rememberedValue3);
            ComposerKt.sourceInformationMarkerEnd($composer52);
            EffectsKt.LaunchedEffect(bool, bool2, bool3, (Function2) rememberedValue3, $composer52, ($dirty15 >> 12) & 112);
            Object[] objArr52 = {Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate)), Boolean.valueOf(fitRoute), list2, list4};
            ComposerKt.sourceInformationMarkerStart($composer52, -939191544, str4);
            changedInstance4 = $composer52.changedInstance(list4) | $composer52.changedInstance(list2) | (($dirty15 & 14) != 4 ? z : r14) | $composer52.changedInstance(destroyed4);
            rememberedValue4 = $composer52.rememberedValue();
            if (changedInstance4) {
            }
            AtomicBoolean destroyed82 = destroyed4;
            MutableState styleReady$delegate52 = styleReady$delegate;
            styleReady$delegate2 = styleReady$delegate52;
            destroyed5 = destroyed82;
            rememberedValue4 = new FreeWayfindrMapKt$FreeWayfindrMap$7$1(list4, fitRoute, list2, destroyed82, styleReady$delegate52, map$delegate, null);
            $composer52.updateRememberedValue(rememberedValue4);
            ComposerKt.sourceInformationMarkerEnd($composer52);
            EffectsKt.LaunchedEffect(objArr52, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue4, $composer52, (int) r14);
            objArr3 = new Object[]{Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate2)), riderLat, riderLng, Float.valueOf(riderBearing), Boolean.valueOf(followRider), Boolean.valueOf(navMode)};
            ComposerKt.sourceInformationMarkerStart($composer52, -939177083, str4);
            changedInstance5 = ((57344 & $dirty15) != 16384 ? z : r14) | ((3670016 & $dirty15) != 1048576 ? z : r14) | (($dirty5 & 112) != 32 ? z : r14) | (($dirty5 & 896) != 256 ? z : r14) | $composer52.changedInstance(destroyed5) | (($dirty15 & 112) != 32 ? z : r14) | (($dirty15 & 896) != 256 ? z : r14);
            rememberedValue5 = $composer52.rememberedValue();
            if (changedInstance5) {
            }
            objArr4 = objArr3;
            AtomicBoolean destroyed92 = destroyed5;
            $dirty16 = $dirty15;
            MutableState styleReady$delegate62 = styleReady$delegate2;
            $dirty6 = $dirty5;
            $dirty17 = 1048576;
            destroyed5 = destroyed92;
            styleReady$delegate3 = styleReady$delegate62;
            rememberedValue5 = new FreeWayfindrMapKt$FreeWayfindrMap$8$1(followRider, camLat, riderLat, riderLng, navMode, riderBearing, destroyed92, styleReady$delegate62, map$delegate, null);
            $composer52.updateRememberedValue(rememberedValue5);
            ComposerKt.sourceInformationMarkerEnd($composer52);
            EffectsKt.LaunchedEffect(objArr4, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue5, $composer52, 0);
            Object[] objArr62 = {Boolean.valueOf(FreeWayfindrMap$lambda$18(styleReady$delegate3)), camLat, camLng, camZoom, camBearing, pair, Boolean.valueOf(navMode), Boolean.valueOf(followRider)};
            ComposerKt.sourceInformationMarkerStart($composer52, -939158450, str4);
            if (($dirty16 & 3670016) != $dirty17) {
            }
            $dirty = $dirty6;
            changedInstance6 = (($dirty16 & 29360128) != 8388608 ? z : false) | (($dirty16 & 3670016) != $dirty17 ? z : false) | $composer52.changedInstance(destroyed5) | (($dirty16 & 234881024) != 67108864 ? z : false) | (($dirty16 & 1879048192) != 536870912 ? z : false) | (($dirty16 & 112) != 32 ? z : false) | (($dirty16 & 57344) != 16384 ? z : false) | (($dirty16 & 14) != 4 ? z : false) | (($dirty & 7168) != 2048 ? z : false);
            rememberedValue6 = $composer52.rememberedValue();
            if (!changedInstance6) {
            }
            Object obj4 = (Function2) new FreeWayfindrMapKt$FreeWayfindrMap$9$1(camLat, camLng, camZoom, camBearing, navMode, followRider, fitRoute, pair, destroyed5, styleReady$delegate3, map$delegate, null);
            $composer52.updateRememberedValue(obj4);
            rememberedValue6 = obj4;
            ComposerKt.sourceInformationMarkerEnd($composer52);
            EffectsKt.LaunchedEffect(objArr62, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue6, $composer52, 0);
            ComposerKt.sourceInformationMarkerStart($composer52, -939140152, str4);
            changedInstance7 = $composer52.changedInstance(mapView52);
            rememberedValue7 = $composer52.rememberedValue();
            if (!changedInstance7) {
            }
            Object obj22 = new Function1() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj32) {
                    return FreeWayfindrMapKt.FreeWayfindrMap$lambda$37$lambda$36(MapView.this, (Context) obj32);
                }
            };
            $composer52.updateRememberedValue(obj22);
            rememberedValue7 = obj22;
            ComposerKt.sourceInformationMarkerEnd($composer52);
            AndroidView_androidKt.AndroidView((Function1) rememberedValue7, modifier, null, $composer52, ($dirty >> 24) & 112, 4);
            $composer2 = $composer52;
            if (ComposerKt.isTraceInProgress()) {
            }
        } else {
            $dirty = $dirty7;
            $composer2 = $composer4;
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    return FreeWayfindrMapKt.FreeWayfindrMap$lambda$38(styleJson, riderLat, riderLng, pair, list, list2, list3, list4, selectedRouteIndex, modifier, fitRoute, navMode, riderBearing, recenterKey, followRider, centerOnRiderInitially, camLat, camLng, camZoom, camBearing, function1, $changed, $changed1, $changed2, (Composer) obj5, ((Integer) obj6).intValue());
                }
            });
        }
    }

    private static final MapLibreMap FreeWayfindrMap$lambda$9(MutableState<MapLibreMap> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LineManager FreeWayfindrMap$lambda$12(MutableState<LineManager> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SymbolManager FreeWayfindrMap$lambda$15(MutableState<SymbolManager> mutableState) {
        return mutableState.getValue();
    }

    private static final boolean FreeWayfindrMap$lambda$18(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void FreeWayfindrMap$lambda$19(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function1<LatLng, Unit> FreeWayfindrMap$lambda$20(State<? extends Function1<? super LatLng, Unit>> state) {
        return (Function1) state.getValue();
    }

    static final DisposableEffectResult FreeWayfindrMap$lambda$26$lambda$25(final LifecycleOwner $lifecycleOwner, final MapView $mapView, final AtomicBoolean $destroyed, final DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final LifecycleEventObserver obs = new LifecycleEventObserver() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                FreeWayfindrMapKt.FreeWayfindrMap$lambda$26$lambda$25$lambda$21(MapView.this, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycleRegistry().addObserver(obs);
        return new DisposableEffectResult() { // from class: com.example.wayfindr.ui.components.FreeWayfindrMapKt$FreeWayfindrMap$lambda$26$lambda$25$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                $destroyed.set(true);
                $lifecycleOwner.getLifecycleRegistry().removeObserver(obs);
                DisposableEffectScope disposableEffectScope = DisposableEffect;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    $mapView.onStop();
                    Result.m8392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m8392constructorimpl(ResultKt.createFailure(th));
                }
                DisposableEffectScope disposableEffectScope2 = DisposableEffect;
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    $mapView.onDestroy();
                    Result.m8392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th2) {
                    Result.Companion companion4 = Result.INSTANCE;
                    Result.m8392constructorimpl(ResultKt.createFailure(th2));
                }
            }
        };
    }

    static final void FreeWayfindrMap$lambda$26$lambda$25$lambda$21(MapView $mapView, LifecycleOwner lifecycleOwner, Lifecycle.Event e) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(e, "e");
        switch (WhenMappings.$EnumSwitchMapping$0[e.ordinal()]) {
            case 1:
                $mapView.onStart();
                break;
            case 2:
                $mapView.onResume();
                break;
            case 3:
                $mapView.onPause();
                break;
            case 4:
                $mapView.onStop();
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void FreeWayfindrMap$animate(AtomicBoolean destroyed, MutableState<Boolean> mutableState, MutableState<MapLibreMap> mutableState2, CameraUpdate update, int ms) {
        Unit unit;
        if (destroyed.get() || !FreeWayfindrMap$lambda$18(mutableState)) {
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MapLibreMap FreeWayfindrMap$lambda$9 = FreeWayfindrMap$lambda$9(mutableState2);
            if (FreeWayfindrMap$lambda$9 != null) {
                FreeWayfindrMap$lambda$9.animateCamera(update, ms);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m8392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
    }

    static final MapView FreeWayfindrMap$lambda$37$lambda$36(MapView $mapView, Context it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return $mapView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void drawTraffic(LineManager lineManager, List<GeoPoint> list, List<TrafficInterval> list2) {
        String str;
        Iterable iterable;
        int i;
        Iterator it;
        if (list.size() < 2) {
            return;
        }
        List<TrafficInterval> list3 = list2;
        int i2 = 0;
        Iterator it2 = list3.iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            TrafficInterval trafficInterval = (TrafficInterval) next;
            int coerceIn = RangesKt.coerceIn(trafficInterval.getStartPointIndex(), 0, CollectionsKt.getLastIndex(list));
            int coerceIn2 = RangesKt.coerceIn(trafficInterval.getEndPointIndex(), coerceIn, CollectionsKt.getLastIndex(list));
            switch (WhenMappings.$EnumSwitchMapping$1[trafficInterval.getSpeed().ordinal()]) {
                case 1:
                    str = "#F9AB00";
                    break;
                case 2:
                    str = "#EA4335";
                    break;
                default:
                    str = null;
                    break;
            }
            if (coerceIn2 <= coerceIn || str == null) {
                iterable = list3;
                i = i2;
                it = it2;
            } else {
                LineOptions lineOptions = new LineOptions();
                Iterable<GeoPoint> subList = list.subList(coerceIn, coerceIn2 + 1);
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList, 10));
                for (GeoPoint geoPoint : subList) {
                    arrayList.add(new org.maplibre.android.geometry.LatLng(geoPoint.getLat(), geoPoint.getLng()));
                    list3 = list3;
                    i2 = i2;
                    it2 = it2;
                    next = next;
                    trafficInterval = trafficInterval;
                }
                iterable = list3;
                i = i2;
                it = it2;
                lineManager.create((LineManager) lineOptions.withLatLngs((List) arrayList).withLineColor(str).withLineWidth(Float.valueOf(6.0f)));
            }
            list3 = iterable;
            i2 = i;
            it2 = it;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap freeChevronBitmap() {
        Bitmap bmp = Bitmap.createBitmap(84, 84, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
        Canvas c = new Canvas(bmp);
        Paint p = new Paint(1);
        p.setColor(-1);
        c.drawCircle(84 / 2.0f, 84 / 2.0f, 84 * 0.34f, p);
        p.setColor(Color.rgb(66, 133, 244));
        c.drawCircle(84 / 2.0f, 84 / 2.0f, 84 * 0.3f, p);
        p.setColor(-1);
        float cx = 84 / 2.0f;
        Path path = new Path();
        path.moveTo(cx, 84 * 0.24f);
        path.lineTo(84 * 0.72f, 84 * 0.7f);
        path.lineTo(cx, 84 * 0.58f);
        path.lineTo(84 * 0.28f, 84 * 0.7f);
        path.close();
        c.drawPath(path, p);
        return bmp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap freeDestPinBitmap() {
        Bitmap bmp = Bitmap.createBitmap(72, 72, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bmp, "createBitmap(...)");
        Canvas c = new Canvas(bmp);
        Paint p = new Paint(1);
        p.setColor(-1);
        c.drawCircle(72 / 2.0f, 72 / 2.0f, 72 * 0.3f, p);
        p.setColor(Color.rgb(234, 67, 53));
        c.drawCircle(72 / 2.0f, 72 / 2.0f, 72 * 0.24f, p);
        p.setColor(-1);
        c.drawCircle(72 / 2.0f, 72 / 2.0f, 72 * 0.09f, p);
        return bmp;
    }
}
