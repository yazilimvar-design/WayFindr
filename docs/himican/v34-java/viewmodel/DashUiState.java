package com.example.wayfindr.viewmodel;

import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.TrafficInterval;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\bU\b\u0087\b\u0018\u00002\u00020\u0001Bõ\u0002\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u001a\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u001e\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010!\u001a\u00020\"\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u000f\u0012\b\b\u0002\u0010%\u001a\u00020\"\u0012\u0016\b\u0002\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010'\u0012\u000e\b\u0002\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)\u0012\u000e\b\u0002\u0010+\u001a\b\u0012\u0004\u0012\u00020,0)¢\u0006\u0004\b-\u0010.J\t\u0010[\u001a\u00020\u0003HÆ\u0003J\t\u0010\\\u001a\u00020\u0005HÆ\u0003J\u000b\u0010]\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010^\u001a\u00020\u0007HÆ\u0003J\t\u0010_\u001a\u00020\u0007HÆ\u0003J\u000b\u0010`\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010a\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010b\u001a\u00020\u0005HÆ\u0003J\t\u0010c\u001a\u00020\u0005HÆ\u0003J\u0010\u0010d\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010e\u001a\u0004\u0018\u00010\u0005HÆ\u0003¢\u0006\u0002\u0010?J\u000b\u0010f\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010g\u001a\u00020\u0013HÆ\u0003J\t\u0010h\u001a\u00020\u0015HÆ\u0003J\t\u0010i\u001a\u00020\u0013HÆ\u0003J\t\u0010j\u001a\u00020\u0013HÆ\u0003J\t\u0010k\u001a\u00020\u0013HÆ\u0003J\t\u0010l\u001a\u00020\u0013HÆ\u0003J\t\u0010m\u001a\u00020\u0013HÆ\u0003J\t\u0010n\u001a\u00020\u0013HÆ\u0003J\t\u0010o\u001a\u00020\u0013HÆ\u0003J\t\u0010p\u001a\u00020\u0007HÆ\u0003J\t\u0010q\u001a\u00020\u0013HÆ\u0003J\u0010\u0010r\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010s\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010<J\t\u0010t\u001a\u00020\"HÆ\u0003J\u0010\u0010u\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010<J\u0010\u0010v\u001a\u0004\u0018\u00010\u000fHÆ\u0003¢\u0006\u0002\u0010<J\t\u0010w\u001a\u00020\"HÆ\u0003J\u0017\u0010x\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010'HÆ\u0003J\u000f\u0010y\u001a\b\u0012\u0004\u0012\u00020*0)HÆ\u0003J\u000f\u0010z\u001a\b\u0012\u0004\u0012\u00020,0)HÆ\u0003Jü\u0002\u0010{\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u00132\b\b\u0002\u0010\u0018\u001a\u00020\u00132\b\b\u0002\u0010\u0019\u001a\u00020\u00132\b\b\u0002\u0010\u001a\u001a\u00020\u00132\b\b\u0002\u0010\u001b\u001a\u00020\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u00132\b\b\u0002\u0010\u001d\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00132\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010!\u001a\u00020\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010%\u001a\u00020\"2\u0016\b\u0002\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010'2\u000e\b\u0002\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u000e\b\u0002\u0010+\u001a\b\u0012\u0004\u0012\u00020,0)HÆ\u0001¢\u0006\u0002\u0010|J\u0013\u0010}\u001a\u00020\u00132\b\u0010~\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u007f\u001a\u00020\u0005HÖ\u0001J\n\u0010\u0080\u0001\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b5\u00104R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b6\u00104R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b7\u00104R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b8\u00104R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b9\u00102R\u0011\u0010\r\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b:\u00102R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010=\u001a\u0004\b;\u0010<R\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0005¢\u0006\n\n\u0002\u0010@\u001a\u0004\b>\u0010?R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\bA\u00104R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bB\u0010CR\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\bD\u0010ER\u0011\u0010\u0016\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bF\u0010CR\u0011\u0010\u0017\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bG\u0010CR\u0011\u0010\u0018\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bH\u0010CR\u0011\u0010\u0019\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bI\u0010CR\u0011\u0010\u001a\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010CR\u0011\u0010\u001b\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bK\u0010CR\u0011\u0010\u001c\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bL\u0010CR\u0011\u0010\u001d\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\bM\u00104R\u0011\u0010\u001e\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\bN\u0010CR\u0015\u0010\u001f\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010=\u001a\u0004\bO\u0010<R\u0015\u0010 \u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010=\u001a\u0004\bP\u0010<R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0015\u0010#\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010=\u001a\u0004\bS\u0010<R\u0015\u0010$\u001a\u0004\u0018\u00010\u000f¢\u0006\n\n\u0002\u0010=\u001a\u0004\bT\u0010<R\u0011\u0010%\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\bU\u0010RR\u001f\u0010&\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0018\u00010'¢\u0006\b\n\u0000\u001a\u0004\bV\u0010WR\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)¢\u0006\b\n\u0000\u001a\u0004\bX\u0010YR\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020,0)¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010Y¨\u0006\u0081\u0001"}, d2 = {"Lcom/example/wayfindr/viewmodel/DashUiState;", "", "stage", "Lcom/example/wayfindr/viewmodel/ConnStage;", "frameCount", "", "lastButton", "", "ssid", "wifiPassword", "destinationName", "errorMessage", "retryAttempt", "mapZoom", "remainingKm", "", "etaMinutes", "maneuver", "hasGps", "", "gpsStatus", "Lcom/example/wayfindr/viewmodel/GpsStatus;", "hasRoute", "offRoute", "recalculating", "headingUp", "mapDark", "mapDarkAuto", "followMode", "thermal", "needsWifiOn", "riderLat", "riderLng", "riderBearing", "", "camLat", "camLng", "camBearing", "destLatLng", "Lkotlin/Pair;", "routePoints", "", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "routeTraffic", "Lcom/example/wayfindr/dash/nav/TrafficInterval;", "<init>", "(Lcom/example/wayfindr/viewmodel/ConnStage;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;ZLcom/example/wayfindr/viewmodel/GpsStatus;ZZZZZZZLjava/lang/String;ZLjava/lang/Double;Ljava/lang/Double;FLjava/lang/Double;Ljava/lang/Double;FLkotlin/Pair;Ljava/util/List;Ljava/util/List;)V", "getStage", "()Lcom/example/wayfindr/viewmodel/ConnStage;", "getFrameCount", "()I", "getLastButton", "()Ljava/lang/String;", "getSsid", "getWifiPassword", "getDestinationName", "getErrorMessage", "getRetryAttempt", "getMapZoom", "getRemainingKm", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getEtaMinutes", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getManeuver", "getHasGps", "()Z", "getGpsStatus", "()Lcom/example/wayfindr/viewmodel/GpsStatus;", "getHasRoute", "getOffRoute", "getRecalculating", "getHeadingUp", "getMapDark", "getMapDarkAuto", "getFollowMode", "getThermal", "getNeedsWifiOn", "getRiderLat", "getRiderLng", "getRiderBearing", "()F", "getCamLat", "getCamLng", "getCamBearing", "getDestLatLng", "()Lkotlin/Pair;", "getRoutePoints", "()Ljava/util/List;", "getRouteTraffic", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component30", "component31", "component32", "copy", "(Lcom/example/wayfindr/viewmodel/ConnStage;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/Double;Ljava/lang/Integer;Ljava/lang/String;ZLcom/example/wayfindr/viewmodel/GpsStatus;ZZZZZZZLjava/lang/String;ZLjava/lang/Double;Ljava/lang/Double;FLjava/lang/Double;Ljava/lang/Double;FLkotlin/Pair;Ljava/util/List;Ljava/util/List;)Lcom/example/wayfindr/viewmodel/DashUiState;", "equals", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class DashUiState {
    public static final int $stable = 8;
    private final float camBearing;
    private final Double camLat;
    private final Double camLng;
    private final Pair<Double, Double> destLatLng;
    private final String destinationName;
    private final String errorMessage;
    private final Integer etaMinutes;
    private final boolean followMode;
    private final int frameCount;
    private final GpsStatus gpsStatus;
    private final boolean hasGps;
    private final boolean hasRoute;
    private final boolean headingUp;
    private final String lastButton;
    private final String maneuver;
    private final boolean mapDark;
    private final boolean mapDarkAuto;
    private final int mapZoom;
    private final boolean needsWifiOn;
    private final boolean offRoute;
    private final boolean recalculating;
    private final Double remainingKm;
    private final int retryAttempt;
    private final float riderBearing;
    private final Double riderLat;
    private final Double riderLng;
    private final List<GeoPoint> routePoints;
    private final List<TrafficInterval> routeTraffic;
    private final String ssid;
    private final ConnStage stage;
    private final String thermal;
    private final String wifiPassword;

    public DashUiState() {
        this(null, 0, null, null, null, null, null, 0, 0, null, null, null, false, null, false, false, false, false, false, false, false, null, false, null, null, 0.0f, null, null, 0.0f, null, null, null, -1, null);
    }

    public static /* synthetic */ DashUiState copy$default(DashUiState dashUiState, ConnStage connStage, int i, String str, String str2, String str3, String str4, String str5, int i2, int i3, Double d, Integer num, String str6, boolean z, GpsStatus gpsStatus, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, String str7, boolean z9, Double d2, Double d3, float f, Double d4, Double d5, float f2, Pair pair, List list, List list2, int i4, Object obj) {
        List list3;
        List list4;
        boolean z10;
        boolean z11;
        boolean z12;
        boolean z13;
        boolean z14;
        boolean z15;
        String str8;
        boolean z16;
        Double d6;
        Double d7;
        float f3;
        Double d8;
        Double d9;
        float f4;
        Pair pair2;
        boolean z17;
        int i5;
        String str9;
        String str10;
        String str11;
        String str12;
        String str13;
        int i6;
        int i7;
        Double d10;
        Integer num2;
        String str14;
        boolean z18;
        GpsStatus gpsStatus2;
        ConnStage connStage2 = (i4 & 1) != 0 ? dashUiState.stage : connStage;
        int i8 = (i4 & 2) != 0 ? dashUiState.frameCount : i;
        String str15 = (i4 & 4) != 0 ? dashUiState.lastButton : str;
        String str16 = (i4 & 8) != 0 ? dashUiState.ssid : str2;
        String str17 = (i4 & 16) != 0 ? dashUiState.wifiPassword : str3;
        String str18 = (i4 & 32) != 0 ? dashUiState.destinationName : str4;
        String str19 = (i4 & 64) != 0 ? dashUiState.errorMessage : str5;
        int i9 = (i4 & 128) != 0 ? dashUiState.retryAttempt : i2;
        int i10 = (i4 & 256) != 0 ? dashUiState.mapZoom : i3;
        Double d11 = (i4 & 512) != 0 ? dashUiState.remainingKm : d;
        Integer num3 = (i4 & 1024) != 0 ? dashUiState.etaMinutes : num;
        String str20 = (i4 & 2048) != 0 ? dashUiState.maneuver : str6;
        boolean z19 = (i4 & 4096) != 0 ? dashUiState.hasGps : z;
        GpsStatus gpsStatus3 = (i4 & 8192) != 0 ? dashUiState.gpsStatus : gpsStatus;
        ConnStage connStage3 = connStage2;
        boolean z20 = (i4 & 16384) != 0 ? dashUiState.hasRoute : z2;
        boolean z21 = (i4 & 32768) != 0 ? dashUiState.offRoute : z3;
        boolean z22 = (i4 & 65536) != 0 ? dashUiState.recalculating : z4;
        boolean z23 = (i4 & 131072) != 0 ? dashUiState.headingUp : z5;
        boolean z24 = (i4 & 262144) != 0 ? dashUiState.mapDark : z6;
        boolean z25 = (i4 & 524288) != 0 ? dashUiState.mapDarkAuto : z7;
        boolean z26 = (i4 & 1048576) != 0 ? dashUiState.followMode : z8;
        String str21 = (i4 & 2097152) != 0 ? dashUiState.thermal : str7;
        boolean z27 = (i4 & 4194304) != 0 ? dashUiState.needsWifiOn : z9;
        Double d12 = (i4 & 8388608) != 0 ? dashUiState.riderLat : d2;
        Double d13 = (i4 & 16777216) != 0 ? dashUiState.riderLng : d3;
        float f5 = (i4 & 33554432) != 0 ? dashUiState.riderBearing : f;
        Double d14 = (i4 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? dashUiState.camLat : d4;
        Double d15 = (i4 & 134217728) != 0 ? dashUiState.camLng : d5;
        float f6 = (i4 & 268435456) != 0 ? dashUiState.camBearing : f2;
        Pair pair3 = (i4 & 536870912) != 0 ? dashUiState.destLatLng : pair;
        List list5 = (i4 & 1073741824) != 0 ? dashUiState.routePoints : list;
        if ((i4 & Integer.MIN_VALUE) != 0) {
            list4 = list5;
            list3 = dashUiState.routeTraffic;
            z11 = z22;
            z12 = z23;
            z13 = z24;
            z14 = z25;
            z15 = z26;
            str8 = str21;
            z16 = z27;
            d6 = d12;
            d7 = d13;
            f3 = f5;
            d8 = d14;
            d9 = d15;
            f4 = f6;
            pair2 = pair3;
            z17 = z20;
            i5 = i8;
            str9 = str15;
            str10 = str16;
            str11 = str17;
            str12 = str18;
            str13 = str19;
            i6 = i9;
            i7 = i10;
            d10 = d11;
            num2 = num3;
            str14 = str20;
            z18 = z19;
            gpsStatus2 = gpsStatus3;
            z10 = z21;
        } else {
            list3 = list2;
            list4 = list5;
            z10 = z21;
            z11 = z22;
            z12 = z23;
            z13 = z24;
            z14 = z25;
            z15 = z26;
            str8 = str21;
            z16 = z27;
            d6 = d12;
            d7 = d13;
            f3 = f5;
            d8 = d14;
            d9 = d15;
            f4 = f6;
            pair2 = pair3;
            z17 = z20;
            i5 = i8;
            str9 = str15;
            str10 = str16;
            str11 = str17;
            str12 = str18;
            str13 = str19;
            i6 = i9;
            i7 = i10;
            d10 = d11;
            num2 = num3;
            str14 = str20;
            z18 = z19;
            gpsStatus2 = gpsStatus3;
        }
        return dashUiState.copy(connStage3, i5, str9, str10, str11, str12, str13, i6, i7, d10, num2, str14, z18, gpsStatus2, z17, z10, z11, z12, z13, z14, z15, str8, z16, d6, d7, f3, d8, d9, f4, pair2, list4, list3);
    }

    /* renamed from: component1, reason: from getter */
    public final ConnStage getStage() {
        return this.stage;
    }

    /* renamed from: component10, reason: from getter */
    public final Double getRemainingKm() {
        return this.remainingKm;
    }

    /* renamed from: component11, reason: from getter */
    public final Integer getEtaMinutes() {
        return this.etaMinutes;
    }

    /* renamed from: component12, reason: from getter */
    public final String getManeuver() {
        return this.maneuver;
    }

    /* renamed from: component13, reason: from getter */
    public final boolean getHasGps() {
        return this.hasGps;
    }

    /* renamed from: component14, reason: from getter */
    public final GpsStatus getGpsStatus() {
        return this.gpsStatus;
    }

    /* renamed from: component15, reason: from getter */
    public final boolean getHasRoute() {
        return this.hasRoute;
    }

    /* renamed from: component16, reason: from getter */
    public final boolean getOffRoute() {
        return this.offRoute;
    }

    /* renamed from: component17, reason: from getter */
    public final boolean getRecalculating() {
        return this.recalculating;
    }

    /* renamed from: component18, reason: from getter */
    public final boolean getHeadingUp() {
        return this.headingUp;
    }

    /* renamed from: component19, reason: from getter */
    public final boolean getMapDark() {
        return this.mapDark;
    }

    /* renamed from: component2, reason: from getter */
    public final int getFrameCount() {
        return this.frameCount;
    }

    /* renamed from: component20, reason: from getter */
    public final boolean getMapDarkAuto() {
        return this.mapDarkAuto;
    }

    /* renamed from: component21, reason: from getter */
    public final boolean getFollowMode() {
        return this.followMode;
    }

    /* renamed from: component22, reason: from getter */
    public final String getThermal() {
        return this.thermal;
    }

    /* renamed from: component23, reason: from getter */
    public final boolean getNeedsWifiOn() {
        return this.needsWifiOn;
    }

    /* renamed from: component24, reason: from getter */
    public final Double getRiderLat() {
        return this.riderLat;
    }

    /* renamed from: component25, reason: from getter */
    public final Double getRiderLng() {
        return this.riderLng;
    }

    /* renamed from: component26, reason: from getter */
    public final float getRiderBearing() {
        return this.riderBearing;
    }

    /* renamed from: component27, reason: from getter */
    public final Double getCamLat() {
        return this.camLat;
    }

    /* renamed from: component28, reason: from getter */
    public final Double getCamLng() {
        return this.camLng;
    }

    /* renamed from: component29, reason: from getter */
    public final float getCamBearing() {
        return this.camBearing;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLastButton() {
        return this.lastButton;
    }

    public final Pair<Double, Double> component30() {
        return this.destLatLng;
    }

    public final List<GeoPoint> component31() {
        return this.routePoints;
    }

    public final List<TrafficInterval> component32() {
        return this.routeTraffic;
    }

    /* renamed from: component4, reason: from getter */
    public final String getSsid() {
        return this.ssid;
    }

    /* renamed from: component5, reason: from getter */
    public final String getWifiPassword() {
        return this.wifiPassword;
    }

    /* renamed from: component6, reason: from getter */
    public final String getDestinationName() {
        return this.destinationName;
    }

    /* renamed from: component7, reason: from getter */
    public final String getErrorMessage() {
        return this.errorMessage;
    }

    /* renamed from: component8, reason: from getter */
    public final int getRetryAttempt() {
        return this.retryAttempt;
    }

    /* renamed from: component9, reason: from getter */
    public final int getMapZoom() {
        return this.mapZoom;
    }

    public final DashUiState copy(ConnStage stage, int frameCount, String lastButton, String ssid, String wifiPassword, String destinationName, String errorMessage, int retryAttempt, int mapZoom, Double remainingKm, Integer etaMinutes, String maneuver, boolean hasGps, GpsStatus gpsStatus, boolean hasRoute, boolean offRoute, boolean recalculating, boolean headingUp, boolean mapDark, boolean mapDarkAuto, boolean followMode, String thermal, boolean needsWifiOn, Double riderLat, Double riderLng, float riderBearing, Double camLat, Double camLng, float camBearing, Pair<Double, Double> destLatLng, List<GeoPoint> routePoints, List<TrafficInterval> routeTraffic) {
        Intrinsics.checkNotNullParameter(stage, "stage");
        Intrinsics.checkNotNullParameter(ssid, "ssid");
        Intrinsics.checkNotNullParameter(wifiPassword, "wifiPassword");
        Intrinsics.checkNotNullParameter(gpsStatus, "gpsStatus");
        Intrinsics.checkNotNullParameter(thermal, "thermal");
        Intrinsics.checkNotNullParameter(routePoints, "routePoints");
        Intrinsics.checkNotNullParameter(routeTraffic, "routeTraffic");
        return new DashUiState(stage, frameCount, lastButton, ssid, wifiPassword, destinationName, errorMessage, retryAttempt, mapZoom, remainingKm, etaMinutes, maneuver, hasGps, gpsStatus, hasRoute, offRoute, recalculating, headingUp, mapDark, mapDarkAuto, followMode, thermal, needsWifiOn, riderLat, riderLng, riderBearing, camLat, camLng, camBearing, destLatLng, routePoints, routeTraffic);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DashUiState)) {
            return false;
        }
        DashUiState dashUiState = (DashUiState) other;
        return this.stage == dashUiState.stage && this.frameCount == dashUiState.frameCount && Intrinsics.areEqual(this.lastButton, dashUiState.lastButton) && Intrinsics.areEqual(this.ssid, dashUiState.ssid) && Intrinsics.areEqual(this.wifiPassword, dashUiState.wifiPassword) && Intrinsics.areEqual(this.destinationName, dashUiState.destinationName) && Intrinsics.areEqual(this.errorMessage, dashUiState.errorMessage) && this.retryAttempt == dashUiState.retryAttempt && this.mapZoom == dashUiState.mapZoom && Intrinsics.areEqual((Object) this.remainingKm, (Object) dashUiState.remainingKm) && Intrinsics.areEqual(this.etaMinutes, dashUiState.etaMinutes) && Intrinsics.areEqual(this.maneuver, dashUiState.maneuver) && this.hasGps == dashUiState.hasGps && this.gpsStatus == dashUiState.gpsStatus && this.hasRoute == dashUiState.hasRoute && this.offRoute == dashUiState.offRoute && this.recalculating == dashUiState.recalculating && this.headingUp == dashUiState.headingUp && this.mapDark == dashUiState.mapDark && this.mapDarkAuto == dashUiState.mapDarkAuto && this.followMode == dashUiState.followMode && Intrinsics.areEqual(this.thermal, dashUiState.thermal) && this.needsWifiOn == dashUiState.needsWifiOn && Intrinsics.areEqual((Object) this.riderLat, (Object) dashUiState.riderLat) && Intrinsics.areEqual((Object) this.riderLng, (Object) dashUiState.riderLng) && Float.compare(this.riderBearing, dashUiState.riderBearing) == 0 && Intrinsics.areEqual((Object) this.camLat, (Object) dashUiState.camLat) && Intrinsics.areEqual((Object) this.camLng, (Object) dashUiState.camLng) && Float.compare(this.camBearing, dashUiState.camBearing) == 0 && Intrinsics.areEqual(this.destLatLng, dashUiState.destLatLng) && Intrinsics.areEqual(this.routePoints, dashUiState.routePoints) && Intrinsics.areEqual(this.routeTraffic, dashUiState.routeTraffic);
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((this.stage.hashCode() * 31) + Integer.hashCode(this.frameCount)) * 31) + (this.lastButton == null ? 0 : this.lastButton.hashCode())) * 31) + this.ssid.hashCode()) * 31) + this.wifiPassword.hashCode()) * 31) + (this.destinationName == null ? 0 : this.destinationName.hashCode())) * 31) + (this.errorMessage == null ? 0 : this.errorMessage.hashCode())) * 31) + Integer.hashCode(this.retryAttempt)) * 31) + Integer.hashCode(this.mapZoom)) * 31) + (this.remainingKm == null ? 0 : this.remainingKm.hashCode())) * 31) + (this.etaMinutes == null ? 0 : this.etaMinutes.hashCode())) * 31) + (this.maneuver == null ? 0 : this.maneuver.hashCode())) * 31) + Boolean.hashCode(this.hasGps)) * 31) + this.gpsStatus.hashCode()) * 31) + Boolean.hashCode(this.hasRoute)) * 31) + Boolean.hashCode(this.offRoute)) * 31) + Boolean.hashCode(this.recalculating)) * 31) + Boolean.hashCode(this.headingUp)) * 31) + Boolean.hashCode(this.mapDark)) * 31) + Boolean.hashCode(this.mapDarkAuto)) * 31) + Boolean.hashCode(this.followMode)) * 31) + this.thermal.hashCode()) * 31) + Boolean.hashCode(this.needsWifiOn)) * 31) + (this.riderLat == null ? 0 : this.riderLat.hashCode())) * 31) + (this.riderLng == null ? 0 : this.riderLng.hashCode())) * 31) + Float.hashCode(this.riderBearing)) * 31) + (this.camLat == null ? 0 : this.camLat.hashCode())) * 31) + (this.camLng == null ? 0 : this.camLng.hashCode())) * 31) + Float.hashCode(this.camBearing)) * 31) + (this.destLatLng != null ? this.destLatLng.hashCode() : 0)) * 31) + this.routePoints.hashCode()) * 31) + this.routeTraffic.hashCode();
    }

    public String toString() {
        return "DashUiState(stage=" + this.stage + ", frameCount=" + this.frameCount + ", lastButton=" + this.lastButton + ", ssid=" + this.ssid + ", wifiPassword=" + this.wifiPassword + ", destinationName=" + this.destinationName + ", errorMessage=" + this.errorMessage + ", retryAttempt=" + this.retryAttempt + ", mapZoom=" + this.mapZoom + ", remainingKm=" + this.remainingKm + ", etaMinutes=" + this.etaMinutes + ", maneuver=" + this.maneuver + ", hasGps=" + this.hasGps + ", gpsStatus=" + this.gpsStatus + ", hasRoute=" + this.hasRoute + ", offRoute=" + this.offRoute + ", recalculating=" + this.recalculating + ", headingUp=" + this.headingUp + ", mapDark=" + this.mapDark + ", mapDarkAuto=" + this.mapDarkAuto + ", followMode=" + this.followMode + ", thermal=" + this.thermal + ", needsWifiOn=" + this.needsWifiOn + ", riderLat=" + this.riderLat + ", riderLng=" + this.riderLng + ", riderBearing=" + this.riderBearing + ", camLat=" + this.camLat + ", camLng=" + this.camLng + ", camBearing=" + this.camBearing + ", destLatLng=" + this.destLatLng + ", routePoints=" + this.routePoints + ", routeTraffic=" + this.routeTraffic + ")";
    }

    public DashUiState(ConnStage stage, int frameCount, String lastButton, String ssid, String wifiPassword, String destinationName, String errorMessage, int retryAttempt, int mapZoom, Double remainingKm, Integer etaMinutes, String maneuver, boolean hasGps, GpsStatus gpsStatus, boolean hasRoute, boolean offRoute, boolean recalculating, boolean headingUp, boolean mapDark, boolean mapDarkAuto, boolean followMode, String thermal, boolean needsWifiOn, Double riderLat, Double riderLng, float riderBearing, Double camLat, Double camLng, float camBearing, Pair<Double, Double> pair, List<GeoPoint> routePoints, List<TrafficInterval> routeTraffic) {
        Intrinsics.checkNotNullParameter(stage, "stage");
        Intrinsics.checkNotNullParameter(ssid, "ssid");
        Intrinsics.checkNotNullParameter(wifiPassword, "wifiPassword");
        Intrinsics.checkNotNullParameter(gpsStatus, "gpsStatus");
        Intrinsics.checkNotNullParameter(thermal, "thermal");
        Intrinsics.checkNotNullParameter(routePoints, "routePoints");
        Intrinsics.checkNotNullParameter(routeTraffic, "routeTraffic");
        this.stage = stage;
        this.frameCount = frameCount;
        this.lastButton = lastButton;
        this.ssid = ssid;
        this.wifiPassword = wifiPassword;
        this.destinationName = destinationName;
        this.errorMessage = errorMessage;
        this.retryAttempt = retryAttempt;
        this.mapZoom = mapZoom;
        this.remainingKm = remainingKm;
        this.etaMinutes = etaMinutes;
        this.maneuver = maneuver;
        this.hasGps = hasGps;
        this.gpsStatus = gpsStatus;
        this.hasRoute = hasRoute;
        this.offRoute = offRoute;
        this.recalculating = recalculating;
        this.headingUp = headingUp;
        this.mapDark = mapDark;
        this.mapDarkAuto = mapDarkAuto;
        this.followMode = followMode;
        this.thermal = thermal;
        this.needsWifiOn = needsWifiOn;
        this.riderLat = riderLat;
        this.riderLng = riderLng;
        this.riderBearing = riderBearing;
        this.camLat = camLat;
        this.camLng = camLng;
        this.camBearing = camBearing;
        this.destLatLng = pair;
        this.routePoints = routePoints;
        this.routeTraffic = routeTraffic;
    }

    public /* synthetic */ DashUiState(ConnStage connStage, int i, String str, String str2, String str3, String str4, String str5, int i2, int i3, Double d, Integer num, String str6, boolean z, GpsStatus gpsStatus, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, String str7, boolean z9, Double d2, Double d3, float f, Double d4, Double d5, float f2, Pair pair, List list, List list2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? ConnStage.OFFLINE : connStage, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? null : str, (i4 & 8) != 0 ? "" : str2, (i4 & 16) != 0 ? DashConfig.DEFAULT_PASSWORD : str3, (i4 & 32) != 0 ? null : str4, (i4 & 64) != 0 ? null : str5, (i4 & 128) != 0 ? 0 : i2, (i4 & 256) != 0 ? 19 : i3, (i4 & 512) != 0 ? null : d, (i4 & 1024) != 0 ? null : num, (i4 & 2048) != 0 ? null : str6, (i4 & 4096) != 0 ? false : z, (i4 & 8192) != 0 ? GpsStatus.LOST : gpsStatus, (i4 & 16384) != 0 ? false : z2, (i4 & 32768) != 0 ? false : z3, (i4 & 65536) != 0 ? false : z4, (i4 & 131072) != 0 ? true : z5, (i4 & 262144) != 0 ? false : z6, (i4 & 524288) != 0 ? true : z7, (i4 & 1048576) == 0 ? z8 : true, (i4 & 2097152) != 0 ? "OK" : str7, (i4 & 4194304) != 0 ? false : z9, (i4 & 8388608) != 0 ? null : d2, (i4 & 16777216) != 0 ? null : d3, (i4 & 33554432) != 0 ? 0.0f : f, (i4 & AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL) != 0 ? null : d4, (i4 & 134217728) != 0 ? null : d5, (i4 & 268435456) == 0 ? f2 : 0.0f, (i4 & 536870912) != 0 ? null : pair, (i4 & 1073741824) != 0 ? CollectionsKt.emptyList() : list, (i4 & Integer.MIN_VALUE) != 0 ? CollectionsKt.emptyList() : list2);
    }

    public final ConnStage getStage() {
        return this.stage;
    }

    public final int getFrameCount() {
        return this.frameCount;
    }

    public final String getLastButton() {
        return this.lastButton;
    }

    public final String getSsid() {
        return this.ssid;
    }

    public final String getWifiPassword() {
        return this.wifiPassword;
    }

    public final String getDestinationName() {
        return this.destinationName;
    }

    public final String getErrorMessage() {
        return this.errorMessage;
    }

    public final int getRetryAttempt() {
        return this.retryAttempt;
    }

    public final int getMapZoom() {
        return this.mapZoom;
    }

    public final Double getRemainingKm() {
        return this.remainingKm;
    }

    public final Integer getEtaMinutes() {
        return this.etaMinutes;
    }

    public final String getManeuver() {
        return this.maneuver;
    }

    public final boolean getHasGps() {
        return this.hasGps;
    }

    public final GpsStatus getGpsStatus() {
        return this.gpsStatus;
    }

    public final boolean getHasRoute() {
        return this.hasRoute;
    }

    public final boolean getOffRoute() {
        return this.offRoute;
    }

    public final boolean getRecalculating() {
        return this.recalculating;
    }

    public final boolean getHeadingUp() {
        return this.headingUp;
    }

    public final boolean getMapDark() {
        return this.mapDark;
    }

    public final boolean getMapDarkAuto() {
        return this.mapDarkAuto;
    }

    public final boolean getFollowMode() {
        return this.followMode;
    }

    public final String getThermal() {
        return this.thermal;
    }

    public final boolean getNeedsWifiOn() {
        return this.needsWifiOn;
    }

    public final Double getRiderLat() {
        return this.riderLat;
    }

    public final Double getRiderLng() {
        return this.riderLng;
    }

    public final float getRiderBearing() {
        return this.riderBearing;
    }

    public final Double getCamLat() {
        return this.camLat;
    }

    public final Double getCamLng() {
        return this.camLng;
    }

    public final float getCamBearing() {
        return this.camBearing;
    }

    public final Pair<Double, Double> getDestLatLng() {
        return this.destLatLng;
    }

    public final List<GeoPoint> getRoutePoints() {
        return this.routePoints;
    }

    public final List<TrafficInterval> getRouteTraffic() {
        return this.routeTraffic;
    }
}
