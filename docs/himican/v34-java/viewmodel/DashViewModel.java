package com.example.wayfindr.viewmodel;

import android.app.Application;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Build;
import android.os.PowerManager;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.DarkSchedule;
import com.example.wayfindr.dash.DashConfig;
import com.example.wayfindr.dash.DashKeepAliveService;
import com.example.wayfindr.dash.DashSession;
import com.example.wayfindr.dash.DashState;
import com.example.wayfindr.dash.DashWifiManager;
import com.example.wayfindr.dash.GoogleKey;
import com.example.wayfindr.dash.PhoneStatus;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.WifiConnStatus;
import com.example.wayfindr.dash.WifiState;
import com.example.wayfindr.dash.map.LocationTracker;
import com.example.wayfindr.dash.map.MapRenderer;
import com.example.wayfindr.dash.map.NavigationReliability;
import com.example.wayfindr.dash.map.OffRouteConfirmation;
import com.example.wayfindr.dash.map.TileProvider;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.Maneuver;
import com.example.wayfindr.dash.nav.ManeuverType;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.dash.nav.RouteProgressMatch;
import com.example.wayfindr.dash.nav.RouteProgressMatcher;
import com.example.wayfindr.dash.nav.VoiceManager;
import com.example.wayfindr.dash.video.DashEncoder;
import com.example.wayfindr.dash.video.NalProcessor;
import com.example.wayfindr.dash.video.RtpPacketizer;
import com.example.wayfindr.data.Ride;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.data.RideRecorder;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.data.SyncRepository;
import com.example.wayfindr.media.CallController;
import com.example.wayfindr.media.CallInfoProvider;
import com.example.wayfindr.media.IncomingCall;
import com.example.wayfindr.media.MediaInfoProvider;
import com.example.wayfindr.util.BuildId;
import com.example.wayfindr.viewmodel.DashViewModel$;
import com.example.wayfindr.viewmodel.PickerGesture;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: DashViewModel.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class DashViewModel extends AndroidViewModel {
    private static final float BEARING_MIN_SPEED = 1.1f;
    private static final int BIG_FRAME_BYTES = 30000;
    private static final int BTN_ANSWER = 6;
    private static final int BTN_JOY_DOWN = 21;
    private static final int BTN_JOY_LEFT = 20;
    private static final int BTN_JOY_RIGHT = 19;
    private static final int BTN_MAP_ZOOM_IN = 20;
    private static final int BTN_MAP_ZOOM_OUT = 19;
    private static final int BTN_MEDIA_NEXT = 9;
    private static final int BTN_MEDIA_PREV = 10;
    private static final int BTN_REJECT = 7;
    private static final long CONN_LOST_ALERT_MS = 12000;
    private static final int DASH_MAX_ZOOM = 20;
    private static final int DASH_MIN_ZOOM = 11;
    private static final double DISPLAY_LEAD_S = 0.4d;
    private static final long DOUBLE_DOWN_MS = 3000;
    private static final long DOUBLE_TAP_MS = 500;
    private static final long FORCE_REDRAW_MS = 2000;
    private static final long FRAME_MS_ACTIVE = 250;
    private static final long FRAME_MS_IDLE = 250;
    private static final long GPS_LOST_AGE_MS = 12000;
    private static final long GPS_WEAK_AGE_MS = 4000;
    private static final long INITIAL_CONNECT_GIVEUP_MS = 120000;
    private static final long MANUAL_IDLE_MS = 8000;
    private static final int MAX_AUTH_ATTEMPTS = 4;
    private static final int MAX_AUTO_ROUTE_CALLS = 40;
    private static final double MAX_PREDICT_S = 3.0d;
    private static final List MENU_ROUTING_SOURCES;
    private static final long MOTION_HOLD_MS = 1500;
    private static final long NOTICE_MS = 4000;
    private static final long PICKER_TIMEOUT_MS = 30000;
    private static final long RECONNECT_GIVEUP_MS = 21600000;
    private static final long REROUTE_BASE_MS = 6000;
    private static final long REROUTE_MAX_MS = 120000;
    private static final long REROUTE_STREAK_RESET_MS = 60000;
    private static final float ROUTE_ORIGIN_FALLBACK_M = 100.0f;
    private static final float ROUTE_ORIGIN_PRECISE_M = 60.0f;
    private static final long ROUTE_ORIGIN_WAIT_MS = 12000;
    private static final double SECONDARY_TURN_MAX_GAP_M = 500.0d;
    private static final double SMOOTH_TAU = 0.2d;
    private static final long TRAFFIC_REFRESH_MS = 120000;
    private static final double WAYPOINT_ARRIVE_M = 35.0d;
    private static final double WAYPOINT_PROGRESS_TOLERANCE_M = 25.0d;
    private static final long ZOOM_BADGE_MS = 3000;
    private final MutableStateFlow _previewFrame;
    private final MutableStateFlow _ui;
    private volatile int activeStopIndex;
    private int authAttempts;
    private volatile int autoRouteCalls;
    private volatile boolean autoRouteCapLogged;
    private final CallController callController;
    private float camHdg;
    private boolean camInit;
    private double camLat;
    private double camLng;
    private Job connLostAlertJob;
    private boolean connLostAlerted;
    private final DashConfig dashConfig;
    private boolean dashWifiWasConnected;
    private final BroadcastReceiver debugButtonRx;
    private volatile boolean debugInjecting;
    private volatile Double destLat;
    private volatile Double destLng;
    private volatile Integer displayedEtaMin;
    private volatile long downHintUntilMs;
    private DashEncoder encoder;
    private volatile long etaArrivalMs;
    private Route etaRoute;
    private float fixBearing;
    private double fixLat;
    private double fixLng;
    private float fixSpeed;
    private long fixWallMs;
    private volatile boolean followMode;
    private Bitmap frameBitmap;
    private volatile Double frameRiderLat;
    private volatile Double frameRiderLng;
    private final Channel frameWake;
    private Job giveupJob;
    private double gpsFixIntervalMs;
    private volatile GpsStatus gpsStatus;
    private volatile boolean hasStreamedThisConnection;
    private volatile boolean headingUp;
    private Pair heldRider;
    private final JoystickTrail joyTrail;
    private long lastArrivalCalcMs;
    private long lastDiagMs;
    private long lastFixTime;
    private long lastLoneDownMs;
    private volatile long lastManualPanAt;
    private long lastRedrawAt;
    private volatile long lastRerouteAt;
    private String lastSignature;
    private volatile double lastSnapDist;
    private long lastTickNs;
    private volatile long lastTrafficRefreshAt;
    private final LocationTracker location;
    private volatile boolean loggedFirstFrame;
    private volatile boolean mapDark;
    private final MapRenderer mapRenderer;
    private volatile Double matchedLat;
    private volatile Double matchedLng;
    private final MediaInfoProvider mediaInfo;
    private Job mediaObserveJob;
    private volatile MenuPage menuPage;
    private volatile List notice;
    private volatile long noticeUntilMs;
    private final OffRouteConfirmation offRouteConfirmation;
    private volatile long offRouteSince;
    private volatile long offRouteStreakStart;
    private final Lazy ovAccent$delegate;
    private final Lazy ovBg$delegate;
    private final Lazy ovSub$delegate;
    private final Lazy ovTitle$delegate;
    private volatile float panX;
    private volatile float panY;
    private final Lazy pickerGesture$delegate;
    private volatile int pickerIndex;
    private volatile long pickerInputAtMs;
    private volatile List pickerItems;
    private volatile long pickerLastInputMs;
    private final PowerManager powerManager;
    private final StateFlow previewFrame;
    private volatile double progressM;
    private volatile long recalcHoldUntil;
    private Job recordJob;
    private final RideRecorder recorder;
    private final SyncRepository repo;
    private volatile int rerouteStreak;
    private volatile boolean rerouting;
    private volatile Route route;
    private volatile int routeLegOffset;
    private volatile long routeRequestGeneration;
    private Job routeRequestJob;
    private volatile List routeStops;
    private volatile List routeWaypoints;
    private long sendPaceMaxMs;
    private double sendPaceMs;
    private final DashSession session;
    private double smoothEtaSec;
    private double smoothSpeedMps;
    private Job streamJob;
    private Job tickerJob;
    private final TileProvider tiles;
    private volatile boolean trafficRefreshInFlight;
    private final StateFlow ui;
    private boolean userWantsConnection;
    private volatile long videoPtsMs;
    private final VoiceManager voice;
    private volatile boolean waitingForFreshRouteOrigin;
    private final DashWifiManager wifiManager;
    private volatile int zoom;
    private volatile long zoomBadgeUntilMs;
    private int zoomBeforeRightTap;
    private volatile long zoomPressedAtMs;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int $stable = 8;

    static {
        List listOf = CollectionsKt.listOf(new Pair[]{TuplesKt.to(RoutingSource.GOOGLE, "Google Maps"), TuplesKt.to(RoutingSource.OSM, "OSM"), TuplesKt.to(RoutingSource.BROUTER, "Offline (BRouter)")});
        ArrayList arrayList = new ArrayList();
        for (Object obj : listOf) {
            if (RoutingSource.Companion.getAvailable().contains(((Pair) obj).getFirst())) {
                arrayList.add(obj);
            }
        }
        MENU_ROUTING_SOURCES = arrayList;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DashViewModel(Application application) {
        super(application);
        Object value;
        Intrinsics.checkNotNullParameter(application, "app");
        this._ui = StateFlowKt.MutableStateFlow(new DashUiState((ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -1, (DefaultConstructorMarker) null));
        this.ui = FlowKt.asStateFlow(this._ui);
        this._previewFrame = StateFlowKt.MutableStateFlow((Object) null);
        this.previewFrame = FlowKt.asStateFlow(this._previewFrame);
        DashSession dashSession = new DashSession(ViewModelKt.getViewModelScope((ViewModel) this));
        dashSession.setPhoneStatusProvider(new DashViewModel$.ExternalSyntheticLambda0(this));
        this.session = dashSession;
        this.wifiManager = new DashWifiManager(application, ViewModelKt.getViewModelScope((ViewModel) this));
        this.dashConfig = DashConfig.Companion.get(application);
        this.voice = VoiceManager.Companion.get(application);
        this.repo = SyncRepository.Companion.get(application);
        this.recorder = new RideRecorder();
        this.tiles = new TileProvider(application, ViewModelKt.getViewModelScope((ViewModel) this));
        this.location = new LocationTracker(application);
        this.mapRenderer = new MapRenderer(this.tiles);
        this.mediaInfo = new MediaInfoProvider(application);
        this.callController = new CallController(application);
        Object systemService = application.getSystemService("power");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.os.PowerManager");
        this.powerManager = (PowerManager) systemService;
        this.menuPage = MenuPage.ROOT;
        this.pickerGesture$delegate = LazyKt.lazy(new DashViewModel$.ExternalSyntheticLambda1());
        this.routeWaypoints = CollectionsKt.emptyList();
        this.routeStops = CollectionsKt.emptyList();
        this.zoom = 19;
        this.headingUp = true;
        this.followMode = true;
        this.gpsStatus = GpsStatus.LOST;
        this.offRouteConfirmation = new OffRouteConfirmation(0, 0L, 3, null);
        this.lastSnapDist = -1.0d;
        this.lastSignature = "";
        this.frameWake = ChannelKt.Channel$default(-1, (BufferOverflow) null, (Function1) null, BTN_ANSWER, (Object) null);
        this.joyTrail = new JoystickTrail(0L, 0, 3, (DefaultConstructorMarker) null);
        this.mapDark = DarkSchedule.effective$default(DarkSchedule.INSTANCE, ((Boolean) this.dashConfig.getMapDarkAuto().getValue()).booleanValue(), ((Boolean) this.dashConfig.getMapDarkMode().getValue()).booleanValue(), false, MAX_AUTH_ATTEMPTS, (Object) null);
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, this.dashConfig.getSsid(), this.dashConfig.getPassword(), (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, this.mapDark, ((Boolean) this.dashConfig.getMapDarkAuto().getValue()).booleanValue(), false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -786457, (Object) null)));
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 2(this, (Continuation) null), 3, (Object) null);
        this.wifiManager.setOnSsidResolved(new DashViewModel$.ExternalSyntheticLambda2(this));
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 4(this, (Continuation) null), 3, (Object) null);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 5(this, (Continuation) null), 3, (Object) null);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 6(this, (Continuation) null), 3, (Object) null);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 7(this, (Continuation) null), 3, (Object) null);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 8(this, (Continuation) null), 3, (Object) null);
        this.session.setOnError(new DashViewModel$.ExternalSyntheticLambda3(this));
        this.session.setOnButton(new DashViewModel$.ExternalSyntheticLambda4(this));
        this.debugButtonRx = BuildConfig.DEBUG ? (BroadcastReceiver) new debugButtonRx.1(this) : null;
        BroadcastReceiver broadcastReceiver = this.debugButtonRx;
        if (broadcastReceiver != null) {
            IntentFilter intentFilter = new IntentFilter("com.wayfindr.DASH_BUTTON");
            Application application2 = getApplication();
            if (Build.VERSION.SDK_INT >= 33) {
                application2.registerReceiver(broadcastReceiver, intentFilter, 2);
            } else {
                application2.registerReceiver(broadcastReceiver, intentFilter);
            }
        }
        this.ovBg$delegate = LazyKt.lazy(new DashViewModel$.ExternalSyntheticLambda5());
        this.ovTitle$delegate = LazyKt.lazy(new DashViewModel$.ExternalSyntheticLambda6());
        this.ovSub$delegate = LazyKt.lazy(new DashViewModel$.ExternalSyntheticLambda7());
        this.ovAccent$delegate = LazyKt.lazy(new DashViewModel$.ExternalSyntheticLambda8());
    }

    static final Unit _init_$lambda$10(DashViewModel dashViewModel, byte b) {
        char c;
        Character valueOf;
        String str;
        Object value;
        int i = b & 255;
        switch (i) {
            case 19:
                c = 'R';
                valueOf = Character.valueOf(c);
                break;
            case 20:
                c = 'L';
                valueOf = Character.valueOf(c);
                break;
            case BTN_JOY_DOWN /* 21 */:
                c = 'D';
                valueOf = Character.valueOf(c);
                break;
            default:
                valueOf = null;
                break;
        }
        if (valueOf != null) {
            char charValue = valueOf.charValue();
            if (((Boolean) dashViewModel.dashConfig.getShowJoystickTrail().getValue()).booleanValue()) {
                dashViewModel.joyTrail.add(charValue, System.currentTimeMillis());
            }
        }
        IncomingCall incomingCall = (IncomingCall) CallInfoProvider.INSTANCE.getIncomingCall().getValue();
        boolean z = incomingCall != null && incomingCall.getIncoming();
        boolean z2 = (incomingCall == null || incomingCall.getIncoming()) ? false : true;
        String handlePickerInput = z ? null : dashViewModel.handlePickerInput(i);
        if (handlePickerInput != null) {
            str = handlePickerInput;
        } else if (z && i == BTN_ANSWER) {
            Intrinsics.checkNotNull(incomingCall);
            dashViewModel.answerCall(incomingCall);
            str = "Call answered";
        } else if (z && i == BTN_REJECT) {
            Intrinsics.checkNotNull(incomingCall);
            dashViewModel.endCall(incomingCall);
            str = "Call rejected";
        } else if (z2 && i == BTN_REJECT) {
            Intrinsics.checkNotNull(incomingCall);
            dashViewModel.endCall(incomingCall);
            str = "Call ended";
        } else if (!z && i == dashViewModel.zoomInCode()) {
            dashViewModel.zoomIn();
            str = "Zoom in (map)";
        } else if (!z && i == dashViewModel.zoomOutCode()) {
            dashViewModel.zoomOut();
            str = "Zoom out (map)";
        } else if (!z && i == BTN_MEDIA_NEXT) {
            dashViewModel.mediaInfo.skipNext();
            str = "Next track";
        } else if (!z && i == BTN_MEDIA_PREV) {
            dashViewModel.mediaInfo.skipPrevious();
            str = "Previous track";
        } else if (i == 34) {
            str = "Compass (dash)";
        } else {
            String num = Integer.toString(i, CharsKt.checkRadix(16));
            Intrinsics.checkNotNullExpressionValue(num, "toString(...)");
            String upperCase = num.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            str = "code 0x" + upperCase;
        }
        String str2 = str;
        RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
        String format = String.format("0x%02X (ringing=%b active=%b) → %s", Arrays.copyOf(new Object[]{Integer.valueOf(i), Boolean.valueOf(z), Boolean.valueOf(z2), str2}, MAX_AUTH_ATTEMPTS));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        rideDiagnostics.log("button", format);
        MutableStateFlow mutableStateFlow = dashViewModel._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, str2, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -5, (Object) null)));
        return Unit.INSTANCE;
    }

    static final Unit _init_$lambda$5(DashViewModel dashViewModel, String str) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, "learned");
        dashViewModel.dashConfig.setSsid(str2);
        MutableStateFlow mutableStateFlow = dashViewModel._ui;
        while (true) {
            Object value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, str2, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -9, (Object) null))) {
                return Unit.INSTANCE;
            }
            str2 = str;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    static final Unit _init_$lambda$7(DashViewModel dashViewModel, String str) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, "msg");
        RideDiagnostics.INSTANCE.log("error", str2);
        MutableStateFlow mutableStateFlow = dashViewModel._ui;
        while (true) {
            Object value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, str2, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -65, (Object) null))) {
                dashViewModel.refreshStage();
                return Unit.INSTANCE;
            }
            str2 = str;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00bf  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0158 A[LOOP:0: B:51:0x0152->B:53:0x0158, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0084  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean advanceWaypointIfReached(Location location, NavState navState) {
        SharedLocation sharedLocation;
        Double lat;
        Double d;
        boolean z;
        boolean z2;
        double d2;
        boolean z3;
        int i;
        boolean z4;
        boolean z5;
        boolean z6;
        Job job;
        Job job2;
        SharedLocation sharedLocation2;
        MutableStateFlow mutableStateFlow;
        Object value;
        String name;
        Double lat2;
        Double lng;
        List legEndCumulative;
        Double d3;
        List list = this.routeStops;
        int i2 = this.activeStopIndex;
        if (i2 < CollectionsKt.getLastIndex(list) && (lat = (sharedLocation = (SharedLocation) list.get(i2)).getLat()) != null) {
            double doubleValue = lat.doubleValue();
            Double lng2 = sharedLocation.getLng();
            if (lng2 == null) {
                return false;
            }
            double doubleValue2 = lng2.doubleValue();
            if (location.hasAccuracy()) {
                if (Math.abs(location.getAccuracy()) <= Float.MAX_VALUE) {
                    d = Double.valueOf(location.getAccuracy());
                    z = d != null || d.doubleValue() <= 60.0d;
                    if (z) {
                        z2 = false;
                        d2 = WAYPOINT_ARRIVE_M;
                    } else {
                        GeoPoint.Companion companion = GeoPoint.Companion;
                        z2 = false;
                        double latitude = location.getLatitude();
                        d2 = WAYPOINT_ARRIVE_M;
                        if (companion.distMeters(new GeoPoint(latitude, location.getLongitude()), new GeoPoint(doubleValue, doubleValue2)) <= WAYPOINT_ARRIVE_M) {
                            z3 = true;
                            int i3 = i2 - this.routeLegOffset;
                            if (z) {
                                i = i2;
                                z4 = z3;
                                if (navState.getSnapDist() <= Math.max(d2, (d != null ? d.doubleValue() : 0.0d) * 1.5d)) {
                                    z5 = true;
                                    if (z5) {
                                        Route route = this.route;
                                        if ((route == null || (legEndCumulative = route.getLegEndCumulative()) == null || (d3 = (Double) CollectionsKt.getOrNull(legEndCumulative, i3)) == null) ? z2 : navState.getProgressM() >= d3.doubleValue() - WAYPOINT_PROGRESS_TOLERANCE_M ? true : z2) {
                                            z6 = true;
                                            if (z4 && !z6) {
                                                return z2;
                                            }
                                            this.routeRequestGeneration++;
                                            job = this.routeRequestJob;
                                            if (job != null) {
                                                job2 = null;
                                                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                                            } else {
                                                job2 = null;
                                            }
                                            this.routeRequestJob = job2;
                                            boolean z7 = z2;
                                            this.rerouting = z7;
                                            this.trafficRefreshInFlight = z7;
                                            this.activeStopIndex = i + 1;
                                            SharedLocation sharedLocation3 = (SharedLocation) list.get(this.activeStopIndex);
                                            this.destLat = sharedLocation3.getLat();
                                            this.destLng = sharedLocation3.getLng();
                                            List<SharedLocation> subList = list.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(list));
                                            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList, BTN_MEDIA_PREV));
                                            for (SharedLocation sharedLocation4 : subList) {
                                                Double lat3 = sharedLocation4.getLat();
                                                Intrinsics.checkNotNull(lat3);
                                                boolean z8 = z6;
                                                double doubleValue3 = lat3.doubleValue();
                                                Double lng3 = sharedLocation4.getLng();
                                                Intrinsics.checkNotNull(lng3);
                                                arrayList.add(new GeoPoint(doubleValue3, lng3.doubleValue()));
                                                z5 = z5;
                                                z6 = z8;
                                                sharedLocation3 = sharedLocation3;
                                                z4 = z4;
                                                subList = subList;
                                            }
                                            sharedLocation2 = sharedLocation3;
                                            this.routeWaypoints = arrayList;
                                            this.voice.resetTrip();
                                            this.voice.announceNextStop(sharedLocation2.getName());
                                            this.session.updateRouteCard(sharedLocation2.getName());
                                            mutableStateFlow = this._ui;
                                            do {
                                                value = mutableStateFlow.getValue();
                                                name = sharedLocation2.getName();
                                                lat2 = sharedLocation2.getLat();
                                                Intrinsics.checkNotNull(lat2);
                                                lng = sharedLocation2.getLng();
                                                Intrinsics.checkNotNull(lng);
                                            } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, name, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, TuplesKt.to(lat2, lng), (List) null, (List) null, -536870945, (Object) null)));
                                            Log.i("DashViewModel", "Waypoint " + (i + 1) + "/" + list.size() + " reached; continuing to " + sharedLocation2.getName());
                                            return true;
                                        }
                                    }
                                    z6 = z2;
                                    if (z4) {
                                    }
                                    this.routeRequestGeneration++;
                                    job = this.routeRequestJob;
                                    if (job != null) {
                                    }
                                    this.routeRequestJob = job2;
                                    boolean z72 = z2;
                                    this.rerouting = z72;
                                    this.trafficRefreshInFlight = z72;
                                    this.activeStopIndex = i + 1;
                                    SharedLocation sharedLocation32 = (SharedLocation) list.get(this.activeStopIndex);
                                    this.destLat = sharedLocation32.getLat();
                                    this.destLng = sharedLocation32.getLng();
                                    List<SharedLocation> subList2 = list.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(list));
                                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList2, BTN_MEDIA_PREV));
                                    while (r19.hasNext()) {
                                    }
                                    sharedLocation2 = sharedLocation32;
                                    this.routeWaypoints = arrayList2;
                                    this.voice.resetTrip();
                                    this.voice.announceNextStop(sharedLocation2.getName());
                                    this.session.updateRouteCard(sharedLocation2.getName());
                                    mutableStateFlow = this._ui;
                                    do {
                                        value = mutableStateFlow.getValue();
                                        name = sharedLocation2.getName();
                                        lat2 = sharedLocation2.getLat();
                                        Intrinsics.checkNotNull(lat2);
                                        lng = sharedLocation2.getLng();
                                        Intrinsics.checkNotNull(lng);
                                    } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, name, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, TuplesKt.to(lat2, lng), (List) null, (List) null, -536870945, (Object) null)));
                                    Log.i("DashViewModel", "Waypoint " + (i + 1) + "/" + list.size() + " reached; continuing to " + sharedLocation2.getName());
                                    return true;
                                }
                            } else {
                                i = i2;
                                z4 = z3;
                            }
                            z5 = z2;
                            if (z5) {
                            }
                            z6 = z2;
                            if (z4) {
                            }
                            this.routeRequestGeneration++;
                            job = this.routeRequestJob;
                            if (job != null) {
                            }
                            this.routeRequestJob = job2;
                            boolean z722 = z2;
                            this.rerouting = z722;
                            this.trafficRefreshInFlight = z722;
                            this.activeStopIndex = i + 1;
                            SharedLocation sharedLocation322 = (SharedLocation) list.get(this.activeStopIndex);
                            this.destLat = sharedLocation322.getLat();
                            this.destLng = sharedLocation322.getLng();
                            List<SharedLocation> subList22 = list.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(list));
                            ArrayList arrayList22 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList22, BTN_MEDIA_PREV));
                            while (r19.hasNext()) {
                            }
                            sharedLocation2 = sharedLocation322;
                            this.routeWaypoints = arrayList22;
                            this.voice.resetTrip();
                            this.voice.announceNextStop(sharedLocation2.getName());
                            this.session.updateRouteCard(sharedLocation2.getName());
                            mutableStateFlow = this._ui;
                            do {
                                value = mutableStateFlow.getValue();
                                name = sharedLocation2.getName();
                                lat2 = sharedLocation2.getLat();
                                Intrinsics.checkNotNull(lat2);
                                lng = sharedLocation2.getLng();
                                Intrinsics.checkNotNull(lng);
                            } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, name, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, TuplesKt.to(lat2, lng), (List) null, (List) null, -536870945, (Object) null)));
                            Log.i("DashViewModel", "Waypoint " + (i + 1) + "/" + list.size() + " reached; continuing to " + sharedLocation2.getName());
                            return true;
                        }
                    }
                    z3 = z2;
                    int i32 = i2 - this.routeLegOffset;
                    if (z) {
                    }
                    z5 = z2;
                    if (z5) {
                    }
                    z6 = z2;
                    if (z4) {
                    }
                    this.routeRequestGeneration++;
                    job = this.routeRequestJob;
                    if (job != null) {
                    }
                    this.routeRequestJob = job2;
                    boolean z7222 = z2;
                    this.rerouting = z7222;
                    this.trafficRefreshInFlight = z7222;
                    this.activeStopIndex = i + 1;
                    SharedLocation sharedLocation3222 = (SharedLocation) list.get(this.activeStopIndex);
                    this.destLat = sharedLocation3222.getLat();
                    this.destLng = sharedLocation3222.getLng();
                    List<SharedLocation> subList222 = list.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(list));
                    ArrayList arrayList222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList222, BTN_MEDIA_PREV));
                    while (r19.hasNext()) {
                    }
                    sharedLocation2 = sharedLocation3222;
                    this.routeWaypoints = arrayList222;
                    this.voice.resetTrip();
                    this.voice.announceNextStop(sharedLocation2.getName());
                    this.session.updateRouteCard(sharedLocation2.getName());
                    mutableStateFlow = this._ui;
                    do {
                        value = mutableStateFlow.getValue();
                        name = sharedLocation2.getName();
                        lat2 = sharedLocation2.getLat();
                        Intrinsics.checkNotNull(lat2);
                        lng = sharedLocation2.getLng();
                        Intrinsics.checkNotNull(lng);
                    } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, name, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, TuplesKt.to(lat2, lng), (List) null, (List) null, -536870945, (Object) null)));
                    Log.i("DashViewModel", "Waypoint " + (i + 1) + "/" + list.size() + " reached; continuing to " + sharedLocation2.getName());
                    return true;
                }
            }
            d = null;
            if (d != null) {
            }
            if (z) {
            }
            z3 = z2;
            int i322 = i2 - this.routeLegOffset;
            if (z) {
            }
            z5 = z2;
            if (z5) {
            }
            z6 = z2;
            if (z4) {
            }
            this.routeRequestGeneration++;
            job = this.routeRequestJob;
            if (job != null) {
            }
            this.routeRequestJob = job2;
            boolean z72222 = z2;
            this.rerouting = z72222;
            this.trafficRefreshInFlight = z72222;
            this.activeStopIndex = i + 1;
            SharedLocation sharedLocation32222 = (SharedLocation) list.get(this.activeStopIndex);
            this.destLat = sharedLocation32222.getLat();
            this.destLng = sharedLocation32222.getLng();
            List<SharedLocation> subList2222 = list.subList(this.activeStopIndex + 1, CollectionsKt.getLastIndex(list));
            ArrayList arrayList2222 = new ArrayList(CollectionsKt.collectionSizeOrDefault(subList2222, BTN_MEDIA_PREV));
            while (r19.hasNext()) {
            }
            sharedLocation2 = sharedLocation32222;
            this.routeWaypoints = arrayList2222;
            this.voice.resetTrip();
            this.voice.announceNextStop(sharedLocation2.getName());
            this.session.updateRouteCard(sharedLocation2.getName());
            mutableStateFlow = this._ui;
            do {
                value = mutableStateFlow.getValue();
                name = sharedLocation2.getName();
                lat2 = sharedLocation2.getLat();
                Intrinsics.checkNotNull(lat2);
                lng = sharedLocation2.getLng();
                Intrinsics.checkNotNull(lng);
            } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, name, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, TuplesKt.to(lat2, lng), (List) null, (List) null, -536870945, (Object) null)));
            Log.i("DashViewModel", "Waypoint " + (i + 1) + "/" + list.size() + " reached; continuing to " + sharedLocation2.getName());
            return true;
        }
        return false;
    }

    private final float angleDelta(float f, float f2) {
        return Math.abs(((((f - f2) % 360.0f) + 540.0f) % 360.0f) - 180.0f);
    }

    private final void answerCall(IncomingCall incomingCall) {
        boolean z;
        Object obj;
        PendingIntent answerIntent = incomingCall.getAnswerIntent();
        if (answerIntent != null) {
            try {
                Result.Companion companion = Result.Companion;
                DashViewModel dashViewModel = this;
                answerIntent.send();
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            z = Result.isSuccess-impl(obj);
        } else {
            z = false;
        }
        if (!z) {
            this.callController.answer();
        }
        RideDiagnostics.INSTANCE.log("media", "answer (notifAction=" + z + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void armConnLostAlert() {
        Job job = this.connLostAlertJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (z || this.connLostAlerted) {
            return;
        }
        this.connLostAlertJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new armConnLostAlert.1(this, (Continuation) null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void armReconnectGiveup(long j) {
        Job job = this.giveupJob;
        boolean z = false;
        if (job != null && job.isActive()) {
            z = true;
        }
        if (z) {
            return;
        }
        this.giveupJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new armReconnectGiveup.1(j, this, (Continuation) null), 3, (Object) null);
    }

    private final boolean canOpenPicker() {
        return this.session.getState().getValue() == DashState.STREAMING || this.debugInjecting;
    }

    private final boolean canSkipStop() {
        return hasDestination() && stopsAhead().size() >= 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelConnLostAlert() {
        Job job = this.connLostAlertJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.connLostAlertJob = null;
        if (this.connLostAlerted) {
            this.connLostAlerted = false;
            this.voice.alert("Gösterge bağlantısı yeniden kuruldu");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelReconnectGiveup() {
        Job job = this.giveupJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.giveupJob = null;
    }

    private final List cancelRows() {
        List createListBuilder = CollectionsKt.createListBuilder();
        if (canSkipStop()) {
            createListBuilder.add(TuplesKt.to("Sonraki durağı atla: " + ((SharedLocation) CollectionsKt.first(stopsAhead())).getName(), "skip"));
        }
        if (hasDestination()) {
            createListBuilder.add(TuplesKt.to("Navigasyonu durdur", "end"));
        }
        return CollectionsKt.build(createListBuilder);
    }

    private final void closePicker(String str) {
        if (this.pickerItems == null) {
            return;
        }
        this.pickerItems = null;
        this.menuPage = MenuPage.ROOT;
        RideDiagnostics.INSTANCE.log("picker", "closed: " + str);
    }

    private final void drawDashOverlays(Canvas canvas, int i, int i2) {
        IncomingCall incomingCall = (IncomingCall) CallInfoProvider.INSTANCE.getIncomingCall().getValue();
        if (incomingCall != null && incomingCall.getIncoming()) {
            float f = i / 2.0f;
            float f2 = i2 / 2.0f;
            float f3 = i2 * 0.28f;
            canvas.drawRoundRect(f - f3, f2 - 26.0f, f + f3, f2 + 26.0f, 10.0f, 10.0f, getOvBg());
            canvas.drawText("Incoming call", f, f2 - 11.0f, getOvSub());
            canvas.drawText(ellipsizeOverlay(incomingCall.getCaller(), 16), f, 5.0f + f2, getOvTitle());
            canvas.drawText("UP answer · DOWN reject", f, 19.0f + f2, getOvAccent());
        }
    }

    private final String ellipsizeOverlay(String str, int i) {
        if (str.length() <= i) {
            return str;
        }
        return StringsKt.take(str, i - 1) + "…";
    }

    private final void endCall(IncomingCall incomingCall) {
        boolean z;
        Object obj;
        PendingIntent declineIntent = incomingCall.getDeclineIntent();
        if (declineIntent != null) {
            try {
                Result.Companion companion = Result.Companion;
                DashViewModel dashViewModel = this;
                declineIntent.send();
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            z = Result.isSuccess-impl(obj);
        } else {
            z = false;
        }
        if (!z) {
            this.callController.hangup();
        }
        RideDiagnostics.INSTANCE.log("media", "end/reject (notifAction=" + z + ")");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchRoute(double d, double d2, List list) {
        this.routeRequestGeneration++;
        long j = this.routeRequestGeneration;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.waitingForFreshRouteOrigin = false;
        this.routeRequestJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new DashViewModel$fetchRoute$1(this, j, d, d2, list, null), 3, (Object) null);
    }

    private final String fmtDist(double d) {
        if (d < 1000.0d) {
            return ((int) d) + " m";
        }
        String format = String.format("%.1f km", Arrays.copyOf(new Object[]{Double.valueOf(d / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final Paint getOvAccent() {
        return (Paint) this.ovAccent$delegate.getValue();
    }

    private final Paint getOvBg() {
        return (Paint) this.ovBg$delegate.getValue();
    }

    private final Paint getOvSub() {
        return (Paint) this.ovSub$delegate.getValue();
    }

    private final Paint getOvTitle() {
        return (Paint) this.ovTitle$delegate.getValue();
    }

    private final PickerGesture getPickerGesture() {
        return (PickerGesture) this.pickerGesture$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void giveUp(String str) {
        cancelReconnectGiveup();
        this.userWantsConnection = false;
        this.session.disconnect();
        this.wifiManager.disconnect();
        releaseBackgroundResources();
        refreshStage();
        RideDiagnostics.INSTANCE.log("error", "gave up: " + str + " — background released");
        RideDiagnostics.INSTANCE.stop(str);
    }

    private final String handlePickerInput(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        PickerGesture.Result onCode = getPickerGesture().onCode(i, currentTimeMillis, ((Boolean) this.dashConfig.getPickerDoubleTap().getValue()).booleanValue());
        if (i == BTN_JOY_DOWN && this.pickerItems == null && !onCode.getActivate()) {
            RideDiagnostics.INSTANCE.log("picker", "single DOWN (" + (this.lastLoneDownMs > 0 ? (currentTimeMillis - this.lastLoneDownMs) + " ms after the previous lone DOWN" : "first") + ") — waiting up to 3000 ms for a second press");
            this.lastLoneDownMs = currentTimeMillis;
            if (canOpenPicker()) {
                this.downHintUntilMs = 3000 + currentTimeMillis;
                wakeFrameLoop();
            }
        }
        if (i == BTN_JOY_DOWN && onCode.getActivate()) {
            this.downHintUntilMs = 0L;
        }
        if (onCode.getPlainTap()) {
            this.zoomBeforeRightTap = this.zoom;
        }
        boolean activate = onCode.getActivate();
        boolean undoPrevTap = onCode.getUndoPrevTap();
        String str = i == BTN_JOY_DOWN ? "Double DOWN" : "Double-tap";
        if (this.pickerItems == null) {
            if (!activate) {
                return null;
            }
            if (!canOpenPicker()) {
                RideDiagnostics.INSTANCE.log("picker", str + " ignored — not streaming to the dash");
                return null;
            }
            if (undoPrevTap && this.zoom != this.zoomBeforeRightTap) {
                setZoom(this.zoomBeforeRightTap);
            }
            openPicker();
            wakeFrameLoop();
            return str + " → open destination picker";
        }
        this.pickerLastInputMs = currentTimeMillis;
        this.pickerInputAtMs = currentTimeMillis;
        wakeFrameLoop();
        switch (i) {
            case BTN_REJECT /* 7 */:
            case BTN_JOY_DOWN /* 21 */:
                this.pickerIndex = nextMenuIndex(menuRows(), this.pickerIndex);
                return "Picker down → " + this.pickerIndex;
            case 19:
                return "Picker RIGHT → " + selectPickerItem();
            case 20:
                if (this.menuPage == MenuPage.ROOT) {
                    closePicker("cancelled (LEFT)");
                    return "Picker cancelled";
                }
                this.pickerIndex = (this.menuPage == MenuPage.CANCEL && hasDestination()) ? 1 : 0;
                this.menuPage = MenuPage.ROOT;
                return "Picker back → menu";
            default:
                String format = String.format("Picker: ignored 0x%02X", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                return format;
        }
    }

    private final boolean hasDestination() {
        return (this.destLat == null || this.destLng == null) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFallbackRouteOrigin(Location location) {
        return NavPlus.fallbackOrigin(location);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPreciseRouteOrigin(Location location) {
        return LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.Companion, location, 0L, 0L, BTN_ANSWER, (Object) null) && location.hasAccuracy() && location.getAccuracy() <= ROUTE_ORIGIN_PRECISE_M;
    }

    private final void manualPan(float f, float f2) {
        Object value;
        this.panX += f;
        this.panY += f2;
        this.followMode = false;
        this.lastManualPanAt = System.currentTimeMillis();
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -1048577, (Object) null)));
    }

    private final void maybeRefreshTraffic(Location location) {
        SharedLocation sharedLocation;
        Double lat;
        if (this.userWantsConnection && this.session.getState().getValue() == DashState.STREAMING && location != null && LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.Companion, location, 0L, 0L, BTN_ANSWER, (Object) null) && this.route != null && !this.rerouting && !this.trafficRefreshInFlight && this.dashConfig.getRoutingSource().getValue() == RoutingSource.GOOGLE && GoogleKey.INSTANCE.getPresent()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.lastTrafficRefreshAt < 300000 || (sharedLocation = (SharedLocation) CollectionsKt.lastOrNull(this.routeStops)) == null || (lat = sharedLocation.getLat()) == null) {
                return;
            }
            double doubleValue = lat.doubleValue();
            Double lng = sharedLocation.getLng();
            if (lng != null) {
                double doubleValue2 = lng.doubleValue();
                this.lastTrafficRefreshAt = currentTimeMillis;
                if (takeAutoRouteCall("traffic refresh")) {
                    this.trafficRefreshInFlight = true;
                    this.routeRequestGeneration++;
                    long j = this.routeRequestGeneration;
                    List list = CollectionsKt.toList(this.routeWaypoints);
                    int i = this.activeStopIndex;
                    Job job = this.routeRequestJob;
                    if (job != null) {
                        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                    }
                    this.routeRequestJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new DashViewModel$maybeRefreshTraffic$1(this, location, doubleValue, doubleValue2, list, j, i, null), 3, (Object) null);
                }
            }
        }
    }

    private final void maybeReroute(boolean z, Location location) {
        Double d;
        Double d2;
        if (!this.userWantsConnection || this.session.getState().getValue() != DashState.STREAMING) {
            this.offRouteSince = 0L;
            this.offRouteConfirmation.reset();
            return;
        }
        SharedLocation sharedLocation = (SharedLocation) CollectionsKt.lastOrNull(this.routeStops);
        if (sharedLocation == null || (d = sharedLocation.getLat()) == null) {
            d = this.destLat;
        }
        if (sharedLocation == null || (d2 = sharedLocation.getLng()) == null) {
            d2 = this.destLng;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (z && location != null && LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.Companion, location, 0L, 0L, BTN_ANSWER, (Object) null) && d != null) {
            if (d2 != null) {
                if (this.offRouteSince == 0) {
                    this.offRouteSince = currentTimeMillis;
                }
                Long valueOf = Long.valueOf(location.getElapsedRealtimeNanos());
                if (!(valueOf.longValue() > 0)) {
                    valueOf = null;
                }
                if (this.offRouteConfirmation.record(valueOf != null ? valueOf.longValue() : location.getTime(), currentTimeMillis)) {
                    if (currentTimeMillis - this.lastRerouteAt < RangesKt.coerceAtMost(3000 << RangesKt.coerceAtMost(this.rerouteStreak, 5), 20000L) || this.rerouting) {
                        return;
                    }
                    if (!this.trafficRefreshInFlight && takeAutoRouteCall("reroute")) {
                        this.rerouteStreak++;
                        this.lastRerouteAt = currentTimeMillis;
                        this.rerouting = true;
                        this.routeRequestGeneration++;
                        long j = this.routeRequestGeneration;
                        List list = CollectionsKt.toList(this.routeWaypoints);
                        int i = this.activeStopIndex;
                        long j2 = (currentTimeMillis - this.offRouteSince) / 1000;
                        Log.i("DashViewModel", "Off-route " + j2 + "s → rerouting");
                        Double d3 = d;
                        RideDiagnostics.INSTANCE.log("reroute", "off-route " + j2 + "s → requesting new route");
                        long currentTimeMillis2 = System.currentTimeMillis();
                        Job job = this.routeRequestJob;
                        if (job != null) {
                            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                        }
                        this.routeRequestJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new DashViewModel$maybeReroute$1(this, location, d3, d2, list, j, i, currentTimeMillis2, null), 3, (Object) null);
                        return;
                    }
                    return;
                }
                return;
            }
        }
        this.offRouteSince = 0L;
        this.offRouteConfirmation.reset();
        if (this.rerouteStreak <= 0 || currentTimeMillis - this.lastRerouteAt <= REROUTE_STREAK_RESET_MS) {
            return;
        }
        this.rerouteStreak = 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.Collection] */
    private List menuRows() {
        int rootFixedRows0;
        ?? menuRows0 = menuRows0();
        try {
            if (this.menuPage == MenuPage.ROOT && menuRows0.size() >= (rootFixedRows0 = rootFixedRows0())) {
                menuRows0 = (Collection) menuRows0;
                ArrayList arrayList = new ArrayList((Collection) menuRows0);
                arrayList.addAll(rootFixedRows0, NavPlus.jmRows(this));
                return arrayList;
            }
        } catch (Throwable unused) {
        }
        return menuRows0;
    }

    private final List menuRows0() {
        switch (WhenMappings.$EnumSwitchMapping$0[this.menuPage.ordinal()]) {
            case 1:
                List plus = CollectionsKt.plus(CollectionsKt.listOf(new MapRenderer.MenuRow("Rota kaynağı", false, false, true, BTN_ANSWER, (DefaultConstructorMarker) null)), hasDestination() ? CollectionsKt.listOf(new MapRenderer.MenuRow("Navigasyon", false, false, true, BTN_ANSWER, (DefaultConstructorMarker) null)) : CollectionsKt.emptyList());
                List list = this.pickerItems;
                if (list == null) {
                    list = CollectionsKt.emptyList();
                }
                List list2 = list;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, BTN_MEDIA_PREV));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add(new MapRenderer.MenuRow(((SavedLocation) it.next()).getName(), false, false, false, 14, (DefaultConstructorMarker) null));
                }
                return CollectionsKt.plus(plus, arrayList);
            case 2:
                List cancelRows = cancelRows();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(cancelRows, BTN_MEDIA_PREV));
                Iterator it2 = cancelRows.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new MapRenderer.MenuRow((String) ((Pair) it2.next()).getFirst(), false, false, false, 14, (DefaultConstructorMarker) null));
                }
                return arrayList2;
            case 3:
                RoutingSource routingSource = (RoutingSource) this.dashConfig.getRoutingSource().getValue();
                List listOf = CollectionsKt.listOf(new MapRenderer.MenuRow("Rota kaynağı", true, false, false, 12, (DefaultConstructorMarker) null));
                List<Pair> list3 = MENU_ROUTING_SOURCES;
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, BTN_MEDIA_PREV));
                for (Pair pair : list3) {
                    arrayList3.add(new MapRenderer.MenuRow((String) pair.component2(), false, ((RoutingSource) pair.component1()) == routingSource, false, BTN_MEDIA_PREV, (DefaultConstructorMarker) null));
                }
                return CollectionsKt.plus(listOf, arrayList3);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static double nav10Progress(DashViewModel dashViewModel) {
        return dashViewModel.progressM;
    }

    private void navplusNotice() {
        try {
            NavPlus.spdTick(this.fixSpeed, this.fixLat, this.fixLng);
            String[] takeNotice = NavPlus.takeNotice();
            if (takeNotice != null) {
                showNotice(takeNotice);
            }
        } catch (Throwable unused) {
        }
    }

    private Pair navplusPredict(Pair pair, double d) {
        Route route;
        List geometry;
        double[] cumulative;
        double d2;
        GeoPoint pointAt;
        GeoPoint pointAt2;
        try {
            if (this.lastSnapDist <= WAYPOINT_ARRIVE_M && (route = this.route) != null && (pointAt = NavPlus.pointAt((geometry = route.getGeometry()), (cumulative = route.getCumulative()), (d2 = this.progressM))) != null) {
                if (GeoPoint.Companion.distMeters(pointAt, new GeoPoint(this.fixLat, this.fixLng)) <= 12.0d && (pointAt2 = NavPlus.pointAt(geometry, cumulative, d2 + d)) != null) {
                    Pair pair2 = TuplesKt.to(Double.valueOf(pointAt2.getLat()), Double.valueOf(pointAt2.getLng()));
                    NavPlus.onRoute = true;
                    return pair2;
                }
            }
        } catch (Throwable unused) {
        }
        NavPlus.onRoute = false;
        return NavPlus.roadSnap(pair);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap newFrameBitmap() {
        int renderScale = renderScale();
        Bitmap createBitmap = Bitmap.createBitmap(renderScale * 526, renderScale * 300, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "let(...)");
        return createBitmap;
    }

    private final int nextMenuIndex(List list, int i) {
        int i2 = i;
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            i2 = (i2 + 1) % list.size();
            if (!((MapRenderer.MenuRow) list.get(i2)).getHeader()) {
                return i2;
            }
        }
        return i;
    }

    private final float normalizeHeading(float f) {
        float f2 = f % 360.0f;
        return f2 < 0.0f ? f2 + 360.0f : f2;
    }

    private final void openPicker() {
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new openPicker.1(this, (Continuation) null), 3, (Object) null);
    }

    static final Paint ovAccent_delegate$lambda$71() {
        Paint paint = new Paint();
        paint.setColor(-874181);
        paint.setAntiAlias(true);
        paint.setTextSize(10.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    static final Paint ovBg_delegate$lambda$65() {
        Paint paint = new Paint();
        paint.setColor(-871690994);
        paint.setAntiAlias(true);
        return paint;
    }

    static final Paint ovSub_delegate$lambda$69() {
        Paint paint = new Paint();
        paint.setColor(-3158065);
        paint.setAntiAlias(true);
        paint.setTextSize(10.0f);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    static final Paint ovTitle_delegate$lambda$67() {
        Paint paint = new Paint();
        paint.setColor(-1);
        paint.setAntiAlias(true);
        paint.setTextSize(15.0f);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setTextAlign(Paint.Align.CENTER);
        return paint;
    }

    static final PickerGesture pickerGesture_delegate$lambda$2() {
        return new PickerGesture(19, 20, BTN_JOY_DOWN, DOUBLE_TAP_MS, 3000L);
    }

    private final double predictedDistance(float f, double d) {
        return f * RangesKt.coerceIn(d, 0.0d, 2.25d);
    }

    private final Pair project(double d, double d2, double d3, double d4) {
        double radians = Math.toRadians(d3);
        double d5 = d4 / 6371000.0d;
        double radians2 = Math.toRadians(d);
        double radians3 = Math.toRadians(d2);
        double asin = Math.asin((Math.sin(radians2) * Math.cos(d5)) + (Math.cos(radians2) * Math.sin(d5) * Math.cos(radians)));
        return TuplesKt.to(Double.valueOf(Math.toDegrees(asin)), Double.valueOf(Math.toDegrees(Math.atan2(Math.sin(radians) * Math.sin(d5) * Math.cos(radians2), Math.cos(d5) - (Math.sin(radians2) * Math.sin(asin))) + radians3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:100:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x02be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void redrawFrame(double d, double d2, float f, float f2, Double d3) {
        List emptyList;
        List emptyList2;
        String str;
        String str2;
        RoutingSource routingSource;
        int i;
        List list;
        PhoneStatus latestPhoneStatus;
        List list2;
        boolean z;
        Double d4;
        Integer num;
        boolean z2;
        boolean z3;
        GeoPoint geoPoint;
        ArrayList arrayList;
        int renderScale = renderScale();
        this.tiles.setHiDpi(renderScale > 1);
        Bitmap bitmap = this.frameBitmap;
        if (bitmap == null) {
            return;
        }
        if (bitmap.getWidth() != renderScale * 526) {
            bitmap = newFrameBitmap();
            this.frameBitmap = bitmap;
        }
        Integer num2 = this.displayedEtaMin;
        boolean z4 = num2 != null && num2.intValue() <= 0;
        boolean recalculating = ((DashUiState) this._ui.getValue()).getRecalculating();
        String str3 = (!recalculating || this.route == null) ? (num2 == null || this.etaArrivalMs <= 0) ? null : z4 ? "Arriving" : num2.intValue() >= 60 ? (num2.intValue() / 60) + "h " + (num2.intValue() % 60) + "m" : num2 + " min" : "Recalculating";
        String format = (str3 == null || z4 || recalculating) ? null : new SimpleDateFormat("h:mm a", Locale.getDefault()).format(new Date(this.etaArrivalMs));
        int i2 = this.zoom;
        float f3 = this.panX;
        float f4 = this.panY;
        boolean z5 = this.headingUp;
        Double d5 = this.frameRiderLat;
        Double d6 = this.frameRiderLng;
        Double d7 = this.destLat;
        Double d8 = this.destLng;
        List<SharedLocation> list3 = this.routeStops;
        ArrayList arrayList2 = new ArrayList();
        for (SharedLocation sharedLocation : list3) {
            if (sharedLocation.getLat() == null || sharedLocation.getLng() == null) {
                d4 = d8;
                num = num2;
                z2 = z4;
                z3 = recalculating;
                geoPoint = null;
            } else {
                d4 = d8;
                num = num2;
                z2 = z4;
                z3 = recalculating;
                geoPoint = new GeoPoint(sharedLocation.getLat().doubleValue(), sharedLocation.getLng().doubleValue());
            }
            if (geoPoint != null) {
                arrayList = arrayList2;
                arrayList.add(geoPoint);
            } else {
                arrayList = arrayList2;
            }
            arrayList2 = arrayList;
            d8 = d4;
            num2 = num;
            z4 = z2;
            recalculating = z3;
        }
        Double d9 = d8;
        ArrayList arrayList3 = arrayList2;
        String destinationName = ((DashUiState) this._ui.getValue()).getDestinationName();
        double d10 = this.progressM;
        Route route = this.route;
        if (route == null || (emptyList = NavPlus.aheadGeometry(route, d10, d5, d6)) == null) {
            emptyList = CollectionsKt.emptyList();
        }
        List list4 = emptyList;
        Route route2 = this.route;
        if (route2 == null || (emptyList2 = NavPlus.aheadTraffic(route2, d10, d5, d6)) == null) {
            emptyList2 = CollectionsKt.emptyList();
        }
        List list5 = emptyList2;
        String fmtDist = d3 != null ? fmtDist(d3.doubleValue()) : null;
        List menuRows = this.pickerItems != null ? menuRows() : null;
        switch (WhenMappings.$EnumSwitchMapping$0[this.menuPage.ordinal()]) {
            case 1:
                str = "KAYITLI YERLER";
                break;
            case 2:
                str = "ROTA / DURAK";
                break;
            case 3:
                str = "ROTA KAYNAĞI";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[this.menuPage.ordinal()]) {
            case 1:
                str2 = "Sol/sağ: gez · sağ basılı: aç";
                break;
            case 2:
                str2 = "Sol/sağ: gez · sağ basılı: seç";
                break;
            case 3:
                str2 = "Sol/sağ: gez · sağ basılı: seç";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        int i3 = this.pickerIndex;
        String str4 = System.currentTimeMillis() < this.zoomBadgeUntilMs ? "Ölçek " + this.zoom : null;
        Route route3 = this.route;
        if (route3 == null || (routingSource = route3.getSource()) == null) {
            routingSource = (RoutingSource) this.dashConfig.getRoutingSource().getValue();
        }
        RoutingSource routingSource2 = routingSource;
        List list6 = this.notice;
        if (list6 != null) {
            if (this.pickerItems == null) {
                i = i3;
                list2 = list6;
                if (System.currentTimeMillis() < this.noticeUntilMs) {
                    z = true;
                    if (z) {
                        list = list2;
                        MapRenderer.Frame frame = new MapRenderer.Frame(d, d2, i2, f3, f4, z5, f, f2, d5, d6, d7, d9, arrayList3, destinationName, list4, list5, (String) null, fmtDist, false, str3, format, menuRows, str, i, str2, str4, !((Boolean) this.dashConfig.getShowJoystickTrail().getValue()).booleanValue() ? this.joyTrail.text(System.currentTimeMillis()) : null, TileProvider.OSM_ATTRIBUTION, list, routingSource2, (this.pickerItems == null || System.currentTimeMillis() >= this.downHintUntilMs) ? null : "▼ tekrar: menüyü aç", (((Boolean) this.dashConfig.getShowPhoneBattery().getValue()).booleanValue() || (latestPhoneStatus = this.session.getLatestPhoneStatus()) == null) ? null : TuplesKt.to(Integer.valueOf(latestPhoneStatus.getBatteryPct()), Boolean.valueOf(latestPhoneStatus.getCharging())), this.gpsStatus != GpsStatus.WEAK, this.gpsStatus != GpsStatus.LOST, this.mapDark);
                        Canvas canvas = new Canvas(bitmap);
                        canvas.scale(renderScale, renderScale);
                        this.mapRenderer.draw(canvas, frame, 526, 300);
                        NavPlus.hudDraw(canvas, 526, 300);
                        drawDashOverlays(canvas, 526, 300);
                        this._previewFrame.setValue(bitmap.copy(Bitmap.Config.ARGB_8888, false));
                    }
                }
            } else {
                i = i3;
                list2 = list6;
            }
            z = false;
            if (z) {
            }
        } else {
            i = i3;
        }
        list = null;
        MapRenderer.Frame frame2 = new MapRenderer.Frame(d, d2, i2, f3, f4, z5, f, f2, d5, d6, d7, d9, arrayList3, destinationName, list4, list5, (String) null, fmtDist, false, str3, format, menuRows, str, i, str2, str4, !((Boolean) this.dashConfig.getShowJoystickTrail().getValue()).booleanValue() ? this.joyTrail.text(System.currentTimeMillis()) : null, TileProvider.OSM_ATTRIBUTION, list, routingSource2, (this.pickerItems == null || System.currentTimeMillis() >= this.downHintUntilMs) ? null : "▼ tekrar: menüyü aç", (((Boolean) this.dashConfig.getShowPhoneBattery().getValue()).booleanValue() || (latestPhoneStatus = this.session.getLatestPhoneStatus()) == null) ? null : TuplesKt.to(Integer.valueOf(latestPhoneStatus.getBatteryPct()), Boolean.valueOf(latestPhoneStatus.getCharging())), this.gpsStatus != GpsStatus.WEAK, this.gpsStatus != GpsStatus.LOST, this.mapDark);
        Canvas canvas2 = new Canvas(bitmap);
        canvas2.scale(renderScale, renderScale);
        this.mapRenderer.draw(canvas2, frame2, 526, 300);
        NavPlus.hudDraw(canvas2, 526, 300);
        drawDashOverlays(canvas2, 526, 300);
        this._previewFrame.setValue(bitmap.copy(Bitmap.Config.ARGB_8888, false));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void refreshStage() {
        Object value;
        WifiConnStatus status = ((WifiState) this.wifiManager.getState().getValue()).getStatus();
        DashState dashState = (DashState) this.session.getState().getValue();
        ConnStage connStage = dashState == DashState.STREAMING ? ConnStage.STREAMING : (dashState == DashState.ERROR || status == WifiConnStatus.ERROR) ? ConnStage.ERROR : (dashState == DashState.AUTHENTICATING || dashState == DashState.CONNECTING || dashState == DashState.READY) ? ConnStage.AUTH : (status == WifiConnStatus.REQUESTING || status == WifiConnStatus.CONNECTED) ? ConnStage.WIFI : ConnStage.OFFLINE;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, connStage, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -2, (Object) null)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void releaseBackgroundResources() {
        teardown();
        this.location.stop();
        DashKeepAliveService.Companion.stop(getApplication());
    }

    private final double remainingToActiveStop(Route route, NavState navState) {
        Double d = (Double) CollectionsKt.getOrNull(route.getLegEndCumulative(), this.activeStopIndex - this.routeLegOffset);
        return RangesKt.coerceAtLeast((d != null ? d.doubleValue() : route.getTotalMeters()) - navState.getProgressM(), 0.0d);
    }

    private final int renderScale() {
        return ((Boolean) this.dashConfig.getSharperMap().getValue()).booleanValue() ? 2 : 1;
    }

    private int rootFixedRows() {
        return rootFixedRows0() + BTN_REJECT;
    }

    private final int rootFixedRows0() {
        return hasDestination() ? 2 : 1;
    }

    private String selectPickerItem() {
        int rootFixedRows0;
        if (this.pickerItems == null || this.menuPage != MenuPage.ROOT || (rootFixedRows0 = this.pickerIndex - rootFixedRows0()) < 0 || rootFixedRows0 >= BTN_REJECT) {
            return selectPickerItem0();
        }
        String jmAct = NavPlus.jmAct(this, rootFixedRows0);
        closePicker(jmAct);
        return jmAct;
    }

    private final String selectPickerItem0() {
        List list = this.pickerItems;
        if (list == null) {
            return "Picker: nothing to select";
        }
        if (this.menuPage == MenuPage.SOURCE) {
            Pair pair = (Pair) CollectionsKt.getOrNull(MENU_ROUTING_SOURCES, this.pickerIndex - 1);
            if (pair == null) {
                return "Picker: nothing to select";
            }
            RoutingSource routingSource = (RoutingSource) pair.component1();
            String str = (String) pair.component2();
            this.dashConfig.setRoutingSource(routingSource);
            RideDiagnostics.INSTANCE.log("picker", "routing source → " + str + " (from the dash; used from the next route/reroute)");
            return "Picker → routing source " + str;
        }
        if (this.menuPage == MenuPage.CANCEL) {
            Pair pair2 = (Pair) CollectionsKt.getOrNull(cancelRows(), this.pickerIndex);
            String str2 = pair2 != null ? (String) pair2.getSecond() : null;
            if (Intrinsics.areEqual(str2, "skip")) {
                List stopsAhead = stopsAhead();
                closePicker("skipped stop " + ((SharedLocation) CollectionsKt.first(stopsAhead)).getName());
                RideDiagnostics.INSTANCE.log("picker", "cancel next stop " + ((SharedLocation) CollectionsKt.first(stopsAhead)).getName() + " → routing to " + ((SharedLocation) CollectionsKt.first(CollectionsKt.drop(stopsAhead, 1))).getName() + " (from the dash)");
                setDestinations$default(this, CollectionsKt.drop(stopsAhead, 1), null, 2, null);
                showNotice("Atlandı: " + ((SharedLocation) CollectionsKt.first(stopsAhead)).getName(), "→ " + ((SharedLocation) CollectionsKt.first(CollectionsKt.drop(stopsAhead, 1))).getName());
                return "Picker → skipped " + ((SharedLocation) CollectionsKt.first(stopsAhead)).getName();
            }
            if (!Intrinsics.areEqual(str2, "end")) {
                this.menuPage = MenuPage.ROOT;
                this.pickerIndex = 0;
                return "Picker: nothing to cancel";
            }
            closePicker("navigation cancelled");
            RideDiagnostics.INSTANCE.log("picker", "navigation cancelled (from the dash)");
            exitNavigation();
            showNotice("Navigasyon durduruldu.");
            return "Picker → navigation cancelled";
        }
        if (this.pickerIndex == 0) {
            this.menuPage = MenuPage.SOURCE;
            this.pickerIndex = 1;
            return "Picker → Source page";
        }
        if (this.pickerIndex == 1 && hasDestination()) {
            this.menuPage = MenuPage.CANCEL;
            this.pickerIndex = 0;
            return "Picker → Cancel page";
        }
        SavedLocation savedLocation = (SavedLocation) CollectionsKt.getOrNull(list, this.pickerIndex - rootFixedRows());
        if (savedLocation == null) {
            closePicker("cancel selected");
            return "Picker cancelled";
        }
        closePicker("selected " + savedLocation.getName());
        List waypoints = savedLocation.getWaypoints();
        if (waypoints.isEmpty()) {
            waypoints = CollectionsKt.listOf(new SharedLocation(savedLocation.getName(), Double.valueOf(savedLocation.getLat()), Double.valueOf(savedLocation.getLng()), (String) null, false, 24, (DefaultConstructorMarker) null));
        }
        setDestinations$default(this, waypoints, null, 2, null);
        return "Picker → navigate to " + savedLocation.getName();
    }

    public static /* synthetic */ void setDestinations$default(DashViewModel dashViewModel, List list, Route route, int i, Object obj) {
        if ((i & 2) != 0) {
            route = null;
        }
        dashViewModel.setDestinations(list, route);
    }

    private final void setZoom(int i) {
        Object value;
        int coerceIn = RangesKt.coerceIn(i, zoomRange());
        if (coerceIn == this.zoom) {
            return;
        }
        this.zoom = coerceIn;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, this.zoom, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -257, (Object) null)));
        this.lastSignature = "";
        this.zoomBadgeUntilMs = System.currentTimeMillis() + 3000;
        wakeFrameLoop();
        this.zoomPressedAtMs = System.currentTimeMillis();
        if (this.camInit) {
            TileProvider.prefetchZoom$default(this.tiles, this.camLat, this.camLng, this.zoom, 0, 8, null);
        }
    }

    private final void showNotice(String... strArr) {
        this.notice = ArraysKt.toList(strArr);
        this.noticeUntilMs = System.currentTimeMillis() + 4000;
        this.lastSignature = "";
        wakeFrameLoop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sleepNow() {
        RideDiagnostics.INSTANCE.log("sleep", "app asleep — stopping background work");
        if (this.userWantsConnection) {
            giveUp("app asleep (idle 10 min)");
        } else {
            this.wifiManager.disconnect();
            releaseBackgroundResources();
        }
    }

    private final void startMediaForwarding() {
        RideDiagnostics.INSTANCE.log("media", "forwarding start — notification access=" + MediaInfoProvider.Companion.isAccessGranted(getApplication()));
        this.mediaInfo.start();
        Job job = this.mediaObserveJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.mediaObserveJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new startMediaForwarding.1(this, (Continuation) null), 3, (Object) null);
    }

    private final void startRecording() {
        if (this.recorder.isRecording()) {
            return;
        }
        this.recorder.start();
        this.recordJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new startRecording.1(this, (Continuation) null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startStream() {
        RideDiagnostics.INSTANCE.log("stream", "startStream — encoder up, RTP→dash beginning");
        this.videoPtsMs = 0L;
        DashViewModel$.ExternalSyntheticLambda12 externalSyntheticLambda12 = new DashViewModel$.ExternalSyntheticLambda12(new NalProcessor(new DashViewModel$.ExternalSyntheticLambda11(new RtpPacketizer(new DashViewModel$.ExternalSyntheticLambda10(this)), this)), this);
        DashEncoder dashEncoder = this.encoder;
        if (dashEncoder != null) {
            dashEncoder.release();
        }
        DashEncoder dashEncoder2 = new DashEncoder(externalSyntheticLambda12);
        dashEncoder2.prepare();
        this.encoder = dashEncoder2;
        Job job = this.tickerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.tickerJob = null;
        this.frameBitmap = newFrameBitmap();
        this.lastSignature = "";
        this.camInit = false;
        this.lastTickNs = 0L;
        this.lastFixTime = 0L;
        this.heldRider = null;
        this.session.startStreaming();
        startMediaForwarding();
        Location location = (Location) this.location.getLocation().getValue();
        if (location != null) {
            TileProvider.prefetch$default(this.tiles, location.getLatitude(), location.getLongitude(), null, null, 12, null);
        }
        this.streamJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), Dispatchers.getDefault(), (CoroutineStart) null, new startStream.3(this, externalSyntheticLambda12, (Continuation) null), 2, (Object) null);
    }

    static final Unit startStream$lambda$35(DashViewModel dashViewModel, byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "rtpPkt");
        dashViewModel.session.sendRtp(bArr);
        return Unit.INSTANCE;
    }

    static final Unit startStream$lambda$36(RtpPacketizer rtpPacketizer, DashViewModel dashViewModel, byte[] bArr, boolean z) {
        Intrinsics.checkNotNullParameter(bArr, "nal");
        rtpPacketizer.packetize(bArr, true, dashViewModel.videoPtsMs);
        return Unit.INSTANCE;
    }

    static final Unit startStream$lambda$38(NalProcessor nalProcessor, DashViewModel dashViewModel, byte[] bArr, boolean z) {
        long j;
        Object value;
        DashUiState dashUiState;
        Intrinsics.checkNotNullParameter(bArr, "annexB");
        nalProcessor.process(bArr);
        if (!dashViewModel.loggedFirstFrame) {
            dashViewModel.loggedFirstFrame = true;
            RideDiagnostics.INSTANCE.log("stream", "first video frame sent (key=" + z + ", " + bArr.length + "B)");
        }
        if (bArr.length > BIG_FRAME_BYTES) {
            RideDiagnostics.INSTANCE.log("stream", "big frame: " + (bArr.length / 1024) + " KB (key=" + z + ") — budget is ~6 KB/frame");
        }
        long j2 = dashViewModel.pickerInputAtMs;
        if (j2 > 0) {
            dashViewModel.pickerInputAtMs = 0L;
            j = 0;
            RideDiagnostics.INSTANCE.log("picker", "list change → frame sent in " + (System.currentTimeMillis() - j2) + " ms (key=" + z + ", " + bArr.length + "B)");
        } else {
            j = 0;
        }
        long j3 = dashViewModel.zoomPressedAtMs;
        if (j3 > j) {
            dashViewModel.zoomPressedAtMs = j;
            RideDiagnostics.INSTANCE.log("button", "zoom → first frame sent in " + (System.currentTimeMillis() - j3) + " ms (key=" + z + ", " + bArr.length + "B)");
        }
        MutableStateFlow mutableStateFlow = dashViewModel._ui;
        do {
            value = mutableStateFlow.getValue();
            dashUiState = (DashUiState) value;
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, dashUiState.getFrameCount() + 1, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -3, (Object) null)));
        return Unit.INSTANCE;
    }

    private final void stopMediaForwarding() {
        Job job = this.mediaObserveJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.mediaObserveJob = null;
        this.mediaInfo.stop();
        this.session.updateNowPlaying((String) null, "", "", false);
        this.session.updateCall((String) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stopRecording() {
        Ride stop;
        Job job = this.recordJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.recordJob = null;
        if (this.recorder.isRecording() && (stop = this.recorder.stop()) != null) {
            this.repo.addRide(stop);
            String format = String.format("%.1f", Arrays.copyOf(new Object[]{Double.valueOf(stop.getDistanceKm())}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            Log.i("DashViewModel", "Ride saved: " + format + " km, " + stop.getDurationSec() + "s");
        }
    }

    private final List stopsAhead() {
        return CollectionsKt.drop(this.routeStops, this.activeStopIndex);
    }

    private final boolean takeAutoRouteCall(String str) {
        if (this.autoRouteCalls >= 150) {
            if (this.autoRouteCapLogged) {
                return false;
            }
            this.autoRouteCapLogged = true;
            RideDiagnostics.INSTANCE.log("reroute", "auto Routes API cap (150) reached — no more " + str + "/reroutes for this destination");
            return false;
        }
        this.autoRouteCalls++;
        RideDiagnostics.INSTANCE.log("reroute", str + ": auto Routes call " + this.autoRouteCalls + "/150");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void teardown() {
        List listOfNotNull = CollectionsKt.listOfNotNull(new Job[]{this.tickerJob, this.streamJob});
        this.tickerJob = null;
        this.streamJob = null;
        Iterator it = listOfNotNull.iterator();
        while (it.hasNext()) {
            Job.DefaultImpls.cancel$default((Job) it.next(), (CancellationException) null, 1, (Object) null);
        }
        stopMediaForwarding();
        DashEncoder dashEncoder = this.encoder;
        this.encoder = null;
        this.frameBitmap = null;
        this._previewFrame.setValue((Object) null);
        if (dashEncoder != null) {
            if (listOfNotNull.isEmpty()) {
                dashEncoder.release();
                return;
            }
            AtomicInteger atomicInteger = new AtomicInteger(listOfNotNull.size());
            Iterator it2 = listOfNotNull.iterator();
            while (it2.hasNext()) {
                ((Job) it2.next()).invokeOnCompletion(new DashViewModel$.ExternalSyntheticLambda9(atomicInteger, this, dashEncoder));
            }
        }
    }

    static final Unit teardown$lambda$85$lambda$84(AtomicInteger atomicInteger, DashViewModel dashViewModel, DashEncoder dashEncoder, Throwable th) {
        if (atomicInteger.decrementAndGet() == 0) {
            try {
                Result.Companion companion = Result.Companion;
                dashEncoder.release();
                Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.Companion companion2 = Result.Companion;
                Result.constructor-impl(ResultKt.createFailure(th2));
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0578, code lost:
    
        if (r0 == null) goto L199;
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x061a, code lost:
    
        if (r8 == null) goto L206;
     */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x032a  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x06a5  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x06c1  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x06e2  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x06f1  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x06f6  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x06e7  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0712  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0725  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0758  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x083e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:199:0x0856 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x086e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:208:0x087d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0894  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x08b4  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x08c1  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x08d9  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x08ed  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0934  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x0940  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x094e  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x0957  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0968  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x09c6  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x09dc  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x09fc  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0a10  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0a2d  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0a42  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0a7d  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x0ac7  */
    /* JADX WARN: Removed duplicated region for block: B:278:0x0ade  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0ae7  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0afe  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x0b62 A[LOOP:1: B:273:0x0aba->B:286:0x0b62, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:287:0x0b5e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0b05  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0afa  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x0ae1  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0aca  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0a9e  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0a2f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x0a15  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x0a05  */
    /* JADX WARN: Removed duplicated region for block: B:302:0x09f6  */
    /* JADX WARN: Removed duplicated region for block: B:303:0x09ca  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x096b  */
    /* JADX WARN: Removed duplicated region for block: B:305:0x095e  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x0951  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0947  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x093b  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x08fd  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x08e5  */
    /* JADX WARN: Removed duplicated region for block: B:311:0x08b9  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x089a  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0859 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0841 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x075f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x071a  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0697  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x046c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:348:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0331  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:361:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0243  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean tick() {
        Double d;
        boolean z;
        Double d2;
        float normalizeHeading;
        Location location;
        String str;
        double d3;
        Route route;
        Location location2;
        DashViewModel dashViewModel;
        float f;
        boolean z2;
        boolean z3;
        long currentTimeMillis;
        Double d4;
        Double d5;
        Double d6;
        String str2;
        Location location3;
        String str3;
        float f2;
        DashViewModel dashViewModel2;
        Pair roadSnapHold;
        long j;
        double d7;
        float normalizeHeading2;
        float normalizeHeading3;
        IncomingCall incomingCall;
        String sb;
        long j2;
        double d8;
        double d9;
        MutableStateFlow mutableStateFlow;
        Object value;
        DashUiState dashUiState;
        boolean z4;
        GpsStatus gpsStatus;
        Double d10;
        Double d11;
        float f3;
        Double valueOf;
        int i;
        Integer num;
        boolean z5;
        String caller;
        String str4;
        String str5;
        String str6;
        long j3;
        long j4;
        boolean z6;
        boolean z7;
        boolean z8;
        String str7;
        String str8;
        Maneuver secondManeuver;
        Triple triple;
        Object obj;
        double d12;
        double d13;
        long j5;
        Object value2;
        navplusNotice();
        if (!this.followMode && System.currentTimeMillis() - this.lastManualPanAt > MANUAL_IDLE_MS) {
            this.panX = 0.0f;
            this.panY = 0.0f;
            this.followMode = true;
            MutableStateFlow mutableStateFlow2 = this._ui;
            do {
                value2 = mutableStateFlow2.getValue();
            } while (!mutableStateFlow2.compareAndSet(value2, DashUiState.copy$default((DashUiState) value2, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, true, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -1048577, (Object) null)));
        }
        Location location4 = (Location) this.location.getLocation().getValue();
        Route route2 = this.route;
        Double d14 = this.destLat;
        Double d15 = this.destLng;
        if (location4 != null) {
            if ((location4.hasAccuracy() ? location4 : null) != null) {
                d = Double.valueOf(r0.getAccuracy());
                Double d16 = d;
                z = location4 == null && location4.hasBearing() && location4.getSpeed() >= BEARING_MIN_SPEED && (d16 == null || d16.doubleValue() <= WAYPOINT_ARRIVE_M);
                this.matchedLat = location4 == null ? Double.valueOf(location4.getLatitude()) : null;
                this.matchedLng = location4 == null ? Double.valueOf(location4.getLongitude()) : null;
                Double d17 = null;
                d2 = null;
                if (z) {
                    normalizeHeading = this.camInit ? normalizeHeading(this.camHdg) : 0.0f;
                } else {
                    Intrinsics.checkNotNull(location4);
                    normalizeHeading = normalizeHeading(location4.getBearing());
                }
                float f4 = normalizeHeading;
                boolean z9 = !this.userWantsConnection && this.session.getState().getValue() == DashState.STREAMING;
                if (route2 != null || location4 == null) {
                    location = location4;
                    str = "format(...)";
                    d3 = 0.0d;
                    route = route2;
                } else {
                    location = location4;
                    if (LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.Companion, location4, 0L, 0L, BTN_ANSWER, (Object) null)) {
                        NavState trackProgress = trackProgress(route2, new GeoPoint(location.getLatitude(), location.getLongitude()), z ? Float.valueOf(location.getBearing()) : null, location.hasSpeed() ? location.getSpeed() : 0.0d, z9);
                        if (!z) {
                            f4 = normalizeHeading(trackProgress.getHeading());
                        }
                        if (z9 && advanceWaypointIfReached(location, trackProgress)) {
                            this.smoothEtaSec = 0.0d;
                            j3 = 0;
                            this.etaArrivalMs = 0L;
                            this.displayedEtaMin = null;
                        } else {
                            j3 = 0;
                        }
                        Double valueOf2 = Double.valueOf(trackProgress.getRemainingM());
                        if (trackProgress.getSnapDist() <= WAYPOINT_ARRIVE_M) {
                            this.matchedLat = Double.valueOf(trackProgress.getSnapped().getLat());
                            this.matchedLng = Double.valueOf(trackProgress.getSnapped().getLng());
                        }
                        this.lastSnapDist = trackProgress.getSnapDist();
                        boolean z10 = z && angleDelta(location.getBearing(), trackProgress.getHeading()) > 50.0f;
                        if (z9) {
                            d3 = 0.0d;
                            j4 = j3;
                            if (NavigationReliability.INSTANCE.isConfidentlyOffRoute(trackProgress.getSnapDist(), d16, z, z10)) {
                                z6 = true;
                                z7 = z6;
                                long currentTimeMillis2 = System.currentTimeMillis();
                                if (z7) {
                                    this.offRouteStreakStart = j4;
                                } else if (this.offRouteStreakStart == j4) {
                                    this.offRouteStreakStart = currentTimeMillis2;
                                }
                                z8 = !z9 && (this.rerouting || (this.offRouteStreakStart != j4 && currentTimeMillis2 - this.offRouteStreakStart > FORCE_REDRAW_MS));
                                if (z8) {
                                    this.recalcHoldUntil = MOTION_HOLD_MS + currentTimeMillis2;
                                }
                                boolean z11 = !z9 && (z8 || currentTimeMillis2 < this.recalcHoldUntil);
                                if (!z9 && !z11) {
                                    if (location.getSpeed() > 0.5f) {
                                        this.smoothSpeedMps = this.smoothSpeedMps <= d3 ? location.getSpeed() : this.smoothSpeedMps + ((location.getSpeed() - this.smoothSpeedMps) * 0.05d);
                                    }
                                    double remainingM = (route2.getTotalMeters() <= d3 || route2.getTotalSeconds() <= d3) ? trackProgress.getRemainingM() / RangesKt.coerceAtLeast(this.smoothSpeedMps, 8.0d) : route2.getTotalSeconds() * RangesKt.coerceIn(trackProgress.getRemainingM() / route2.getTotalMeters(), 0.0d, 1.0d);
                                    if (this.smoothEtaSec <= d3) {
                                        d12 = remainingM;
                                    } else if (route2 != this.etaRoute) {
                                        d12 = remainingM;
                                    } else {
                                        d13 = this.smoothEtaSec + ((remainingM - this.smoothEtaSec) * 0.08d);
                                        this.smoothEtaSec = d13;
                                        if (route2 == this.etaRoute) {
                                            this.etaRoute = route2;
                                            j5 = 0;
                                            this.etaArrivalMs = 0L;
                                        } else {
                                            j5 = 0;
                                        }
                                        if (this.etaArrivalMs != j5 || currentTimeMillis2 - this.lastArrivalCalcMs > 5000) {
                                            this.etaArrivalMs = ((long) (this.smoothEtaSec * 1000.0d)) + currentTimeMillis2;
                                            this.displayedEtaMin = Integer.valueOf((int) Math.round(this.smoothEtaSec / 60.0d));
                                            this.lastArrivalCalcMs = currentTimeMillis2;
                                        }
                                    }
                                    d13 = d12;
                                    this.smoothEtaSec = d13;
                                    if (route2 == this.etaRoute) {
                                    }
                                    if (this.etaArrivalMs != j5) {
                                    }
                                    this.etaArrivalMs = ((long) (this.smoothEtaSec * 1000.0d)) + currentTimeMillis2;
                                    this.displayedEtaMin = Integer.valueOf((int) Math.round(this.smoothEtaSec / 60.0d));
                                    this.lastArrivalCalcMs = currentTimeMillis2;
                                }
                                d2 = this.smoothEtaSec <= d3 ? Double.valueOf(this.smoothEtaSec) : null;
                                Pair dashDistance = toDashDistance(trackProgress.getNextTurnM());
                                int intValue = ((Number) dashDistance.component1()).intValue();
                                int intValue2 = ((Number) dashDistance.component2()).intValue();
                                Pair dashDistance2 = toDashDistance(trackProgress.getRemainingM());
                                int intValue3 = ((Number) dashDistance2.component1()).intValue();
                                int intValue4 = ((Number) dashDistance2.component2()).intValue();
                                if (d2 == null) {
                                    double doubleValue = d2.doubleValue();
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.add(13, (int) doubleValue);
                                    String format = String.format("%02d%02d", Arrays.copyOf(new Object[]{Integer.valueOf(calendar.get(DASH_MIN_ZOOM)), Integer.valueOf(calendar.get(12))}, 2));
                                    str7 = "format(...)";
                                    Intrinsics.checkNotNullExpressionValue(format, str7);
                                    str8 = format;
                                } else {
                                    str7 = "format(...)";
                                    str8 = null;
                                }
                                Maneuver nextManeuver = trackProgress.getNextManeuver();
                                int dashCode = nextManeuver == null ? nextManeuver.getDashCode() : 0;
                                Maneuver nextManeuver2 = trackProgress.getNextManeuver();
                                secondManeuver = trackProgress.getSecondManeuver();
                                Double valueOf3 = (nextManeuver2 != null || secondManeuver == null) ? null : Double.valueOf(secondManeuver.getCumulativeMeters() - nextManeuver2.getCumulativeMeters());
                                if (secondManeuver != null || valueOf3 == null || valueOf3.doubleValue() > SECONDARY_TURN_MAX_GAP_M) {
                                    route = route2;
                                    str = str7;
                                    triple = new Triple(255, 0, 48);
                                } else {
                                    str = str7;
                                    Pair dashDistance3 = toDashDistance(valueOf3.doubleValue());
                                    route = route2;
                                    triple = new Triple(Integer.valueOf(secondManeuver.getDashCode()), Integer.valueOf(((Number) dashDistance3.component1()).intValue()), Integer.valueOf(((Number) dashDistance3.component2()).intValue()));
                                }
                                int intValue5 = ((Number) triple.component1()).intValue();
                                int intValue6 = ((Number) triple.component2()).intValue();
                                int intValue7 = ((Number) triple.component3()).intValue();
                                if (z9) {
                                    this.session.updateNavInfo(dashCode, intValue, intValue2, intValue3, intValue4, str8, intValue5, intValue6, intValue7);
                                }
                                if (z9) {
                                    try {
                                        Result.Companion companion = Result.Companion;
                                        this.voice.maybeAnnounce(trackProgress.getNextManeuver(), trackProgress.getNextTurnM(), trackProgress.getRemainingM());
                                        obj = Result.constructor-impl(Unit.INSTANCE);
                                    } catch (Throwable th) {
                                        Result.Companion companion2 = Result.Companion;
                                        obj = Result.constructor-impl(ResultKt.createFailure(th));
                                    }
                                    Throwable th2 = Result.exceptionOrNull-impl(obj);
                                    if (th2 != null) {
                                        Log.w("DashViewModel", "Voice guidance failed: " + th2.getMessage());
                                    }
                                }
                                location2 = location;
                                f = f4;
                                z2 = z7;
                                z3 = z11;
                                d17 = valueOf2;
                                dashViewModel = this;
                                dashViewModel.maybeReroute(z2, location2);
                                dashViewModel.maybeRefreshTraffic(location2);
                                currentTimeMillis = System.currentTimeMillis();
                                String str9 = "";
                                if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                                    dashViewModel.lastDiagMs = currentTimeMillis;
                                    RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
                                    if (location2 != null) {
                                        Location location5 = location2;
                                        d4 = d17;
                                        str4 = String.format("acc=%.0fm spd=%.1fm/s (%.0fkm/h)", Arrays.copyOf(new Object[]{Float.valueOf(location5.getAccuracy()), Float.valueOf(location5.getSpeed()), Double.valueOf(location5.getSpeed() * 3.6d)}, 3));
                                        str2 = str;
                                        Intrinsics.checkNotNullExpressionValue(str4, str2);
                                    } else {
                                        d4 = d17;
                                        str2 = str;
                                    }
                                    str4 = "none";
                                    String format2 = String.format(" ridden=%.2fkm", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.recorder.getDistanceSoFarM() / 1000.0d)}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format2, str2);
                                    String format3 = String.format(" fixGap=%.0fms", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.gpsFixIntervalMs)}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format3, str2);
                                    int frameCount = ((DashUiState) dashViewModel._ui.getValue()).getFrameCount();
                                    String thermal = ((DashUiState) dashViewModel._ui.getValue()).getThermal();
                                    d5 = d14;
                                    d6 = d15;
                                    String format4 = String.format(" pace=%.0fms/max=%dms", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.sendPaceMs), Long.valueOf(dashViewModel.sendPaceMaxMs)}, 2));
                                    Intrinsics.checkNotNullExpressionValue(format4, str2);
                                    if (d4 != null) {
                                        location3 = location2;
                                        str5 = String.format("%.1fkm", Arrays.copyOf(new Object[]{Double.valueOf(d4.doubleValue() / 1000.0d)}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str5, str2);
                                    } else {
                                        location3 = location2;
                                    }
                                    str5 = "-";
                                    if (dashViewModel.lastSnapDist >= d3) {
                                        str6 = String.format(" snap=%.0fm", Arrays.copyOf(new Object[]{Double.valueOf(dashViewModel.lastSnapDist)}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str6, str2);
                                    } else {
                                        str6 = "";
                                    }
                                    rideDiagnostics.log("ride", "gps=" + str4 + format2 + format3 + " frames=" + frameCount + " thermal=" + thermal + format4 + " remaining=" + str5 + " offRoute=" + z2 + str6);
                                    dashViewModel.sendPaceMaxMs = 0L;
                                } else {
                                    d4 = d17;
                                    d5 = d14;
                                    d6 = d15;
                                    str2 = str;
                                    location3 = location2;
                                }
                                if (location3 != null && location3.getTime() != dashViewModel.lastFixTime) {
                                    dashViewModel.lastFixTime = location3.getTime();
                                    long currentTimeMillis3 = System.currentTimeMillis();
                                    if (dashViewModel.fixWallMs > 0) {
                                        double d18 = currentTimeMillis3 - dashViewModel.fixWallMs;
                                        dashViewModel.gpsFixIntervalMs = dashViewModel.gpsFixIntervalMs <= d3 ? d18 : dashViewModel.gpsFixIntervalMs + ((d18 - dashViewModel.gpsFixIntervalMs) * 0.3d);
                                    }
                                    Double d19 = dashViewModel.matchedLat;
                                    dashViewModel.fixLat = d19 == null ? d19.doubleValue() : location3.getLatitude();
                                    Double d20 = dashViewModel.matchedLng;
                                    dashViewModel.fixLng = d20 == null ? d20.doubleValue() : location3.getLongitude();
                                    dashViewModel.fixBearing = location3.getBearing();
                                    dashViewModel.fixSpeed = location3.getSpeed();
                                    dashViewModel.fixWallMs = currentTimeMillis3;
                                }
                                long currentTimeMillis4 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                                dashViewModel.gpsStatus = (location3 != null || currentTimeMillis4 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis4 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                                if (location3 == null) {
                                    f2 = 25.0f;
                                    str3 = str2;
                                    dashViewModel2 = dashViewModel;
                                    roadSnapHold = null;
                                } else if (dashViewModel.fixSpeed > 0.5f) {
                                    double predictedDistance = dashViewModel.predictedDistance(dashViewModel.fixSpeed, ((System.currentTimeMillis() - dashViewModel.fixWallMs) / 1000.0d) + 0.8d) * RangesKt.coerceIn((50.0f - location3.getAccuracy()) / 25.0f, 0.0d, 1.0d);
                                    dashViewModel.heldRider = NavPlus.roadSnapHold(TuplesKt.to(Double.valueOf(dashViewModel.fixLat), Double.valueOf(dashViewModel.fixLng)));
                                    String str10 = str2;
                                    dashViewModel2 = dashViewModel;
                                    str3 = str10;
                                    roadSnapHold = dashViewModel2.navplusPredict(NavPlus.arcProject(dashViewModel.fixLat, dashViewModel.fixLng, dashViewModel.fixBearing, predictedDistance), predictedDistance);
                                    f2 = 25.0f;
                                } else {
                                    str3 = str2;
                                    DashViewModel dashViewModel3 = dashViewModel;
                                    Double d21 = dashViewModel3.matchedLat;
                                    Intrinsics.checkNotNull(d21);
                                    Double d22 = dashViewModel3.matchedLng;
                                    Intrinsics.checkNotNull(d22);
                                    Pair pair = TuplesKt.to(d21, d22);
                                    Pair pair2 = dashViewModel3.heldRider;
                                    double coerceIn = RangesKt.coerceIn(location3.getAccuracy(), 8.0f, 30.0f);
                                    if (pair2 != null) {
                                        f2 = 25.0f;
                                        if (GeoPoint.Companion.distMeters(new GeoPoint(((Number) pair2.getFirst()).doubleValue(), ((Number) pair2.getSecond()).doubleValue()), new GeoPoint(((Number) pair.getFirst()).doubleValue(), ((Number) pair.getSecond()).doubleValue())) <= coerceIn) {
                                            dashViewModel2 = this;
                                            roadSnapHold = pair2;
                                        }
                                    } else {
                                        f2 = 25.0f;
                                    }
                                    dashViewModel2 = this;
                                    roadSnapHold = NavPlus.roadSnapHold(pair);
                                    dashViewModel2.heldRider = roadSnapHold;
                                }
                                Pair pair3 = roadSnapHold;
                                double doubleValue2 = (!dashViewModel2.followMode ? pair3 != null : pair3 != null) ? dashViewModel2.camLat : ((Number) pair3.getFirst()).doubleValue();
                                double doubleValue3 = (!dashViewModel2.followMode ? pair3 != null : pair3 != null) ? dashViewModel2.camLng : ((Number) pair3.getSecond()).doubleValue();
                                double doubleValue4 = (dashViewModel2.camInit && pair3 == null && d5 != null) ? d5.doubleValue() : doubleValue2;
                                double doubleValue5 = (dashViewModel2.camInit && pair3 == null && d6 != null) ? d6.doubleValue() : doubleValue3;
                                long nanoTime = System.nanoTime();
                                double coerceIn2 = dashViewModel2.lastTickNs == 0 ? 0.042d : RangesKt.coerceIn((nanoTime - dashViewModel2.lastTickNs) / 1.0E9d, 0.0d, 0.5d);
                                dashViewModel2.lastTickNs = nanoTime;
                                float accuracy = location3 != null ? location3.getAccuracy() : 12.0f;
                                double d23 = accuracy <= f2 ? accuracy > 12.0f ? 0.30000000000000004d : SMOOTH_TAU : 0.8d;
                                double d24 = 1.0d;
                                if (dashViewModel2.camInit) {
                                    j = nanoTime;
                                    d24 = 1.0d - Math.exp((-coerceIn2) / d23);
                                } else {
                                    j = nanoTime;
                                }
                                double d25 = d24;
                                if (dashViewModel2.camInit) {
                                    dashViewModel2.camLat += (doubleValue4 - dashViewModel2.camLat) * d25;
                                    dashViewModel2.camLng += (doubleValue5 - dashViewModel2.camLng) * d25;
                                    dashViewModel2.camHdg = dashViewModel2.normalizeHeading(dashViewModel2.camHdg + (((float) d25) * (((((f - dashViewModel2.camHdg) % 360.0f) + 540.0f) % 360.0f) - 180.0f)));
                                } else {
                                    dashViewModel2.camLat = doubleValue4;
                                    dashViewModel2.camLng = doubleValue5;
                                    dashViewModel2.camHdg = dashViewModel2.normalizeHeading(f);
                                    dashViewModel2.camInit = true;
                                }
                                dashViewModel2.frameRiderLat = pair3 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                                dashViewModel2.frameRiderLng = pair3 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                                double d26 = dashViewModel2.camInit ? dashViewModel2.camLat : d3;
                                if (dashViewModel2.camInit) {
                                    d7 = d26;
                                    d3 = dashViewModel2.camLng;
                                } else {
                                    d7 = d26;
                                }
                                normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                                normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                                StringBuilder sb2 = new StringBuilder();
                                String format5 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d7)}, 1));
                                Intrinsics.checkNotNullExpressionValue(format5, str3);
                                sb2.append(format5);
                                String format6 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d3)}, 1));
                                Intrinsics.checkNotNullExpressionValue(format6, str3);
                                sb2.append(format6);
                                sb2.append(dashViewModel2.zoom);
                                sb2.append((int) dashViewModel2.panX);
                                sb2.append((int) dashViewModel2.panY);
                                sb2.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                                sb2.append(dashViewModel2.mapDark ? 1 : 0);
                                sb2.append((int) (10.0f * normalizeHeading2));
                                sb2.append(d4 != null ? Integer.valueOf((int) (d4.doubleValue() / 100.0d)).intValue() : -1);
                                sb2.append(route != null ? route.getGeometry().size() : 0);
                                Integer num2 = this.displayedEtaMin;
                                sb2.append(num2 != null ? num2.intValue() : -1);
                                sb2.append(this.etaArrivalMs / 1000);
                                sb2.append(System.currentTimeMillis() / 1000);
                                sb2.append(z3 ? 1 : 0);
                                incomingCall = (IncomingCall) CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                                if (incomingCall != null) {
                                    if (!Boolean.valueOf(incomingCall.getIncoming()).booleanValue()) {
                                        incomingCall = null;
                                    }
                                    if (incomingCall != null && (caller = incomingCall.getCaller()) != null) {
                                        str9 = caller;
                                    }
                                }
                                sb2.append(str9);
                                sb = sb2.toString();
                                long currentTimeMillis5 = System.currentTimeMillis();
                                boolean z12 = !Intrinsics.areEqual(sb, this.lastSignature);
                                this.lastSignature = sb;
                                this.lastRedrawAt = currentTimeMillis5;
                                if (this.pickerItems != null) {
                                    j2 = currentTimeMillis5;
                                    if (System.currentTimeMillis() - this.pickerLastInputMs > PICKER_TIMEOUT_MS) {
                                        closePicker("timeout");
                                    } else if (!canOpenPicker()) {
                                        closePicker("destination set / not streaming");
                                    }
                                } else {
                                    j2 = currentTimeMillis5;
                                }
                                d8 = d3;
                                d9 = d7;
                                redrawFrame(d9, d8, normalizeHeading3, normalizeHeading2, d4);
                                mutableStateFlow = this._ui;
                                while (true) {
                                    value = mutableStateFlow.getValue();
                                    dashUiState = (DashUiState) value;
                                    z4 = location3 != null;
                                    String str11 = sb;
                                    gpsStatus = this.gpsStatus;
                                    d10 = this.matchedLat;
                                    d11 = this.matchedLng;
                                    f3 = this.headingUp ? normalizeHeading3 : 0.0f;
                                    int i2 = this.zoom;
                                    valueOf = d4 != null ? Double.valueOf(d4.doubleValue() / 1000.0d) : null;
                                    if (d2 != null) {
                                        i = i2;
                                        num = this.displayedEtaMin;
                                    } else {
                                        i = i2;
                                        num = null;
                                    }
                                    z5 = z2;
                                    if (mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, i, valueOf, num, (String) null, z4, gpsStatus, false, z5, z3, false, false, false, false, (String) null, false, d10, d11, normalizeHeading2, Double.valueOf(d9), Double.valueOf(d8), f3, (Pair) null, (List) null, (List) null, -528596737, (Object) null))) {
                                        updateThermal();
                                        return z12;
                                    }
                                    sb = str11;
                                    z2 = z5;
                                }
                            }
                        } else {
                            d3 = 0.0d;
                            j4 = j3;
                        }
                        z6 = false;
                        z7 = z6;
                        long currentTimeMillis22 = System.currentTimeMillis();
                        if (z7) {
                        }
                        z8 = !z9 && (this.rerouting || (this.offRouteStreakStart != j4 && currentTimeMillis22 - this.offRouteStreakStart > FORCE_REDRAW_MS));
                        if (z8) {
                        }
                        boolean z112 = !z9 && (z8 || currentTimeMillis22 < this.recalcHoldUntil);
                        if (!z9) {
                        }
                        d2 = this.smoothEtaSec <= d3 ? Double.valueOf(this.smoothEtaSec) : null;
                        Pair dashDistance4 = toDashDistance(trackProgress.getNextTurnM());
                        int intValue8 = ((Number) dashDistance4.component1()).intValue();
                        int intValue22 = ((Number) dashDistance4.component2()).intValue();
                        Pair dashDistance22 = toDashDistance(trackProgress.getRemainingM());
                        int intValue32 = ((Number) dashDistance22.component1()).intValue();
                        int intValue42 = ((Number) dashDistance22.component2()).intValue();
                        if (d2 == null) {
                        }
                        Maneuver nextManeuver3 = trackProgress.getNextManeuver();
                        if (nextManeuver3 == null) {
                        }
                        Maneuver nextManeuver22 = trackProgress.getNextManeuver();
                        secondManeuver = trackProgress.getSecondManeuver();
                        Double valueOf32 = (nextManeuver22 != null || secondManeuver == null) ? null : Double.valueOf(secondManeuver.getCumulativeMeters() - nextManeuver22.getCumulativeMeters());
                        if (secondManeuver != null) {
                        }
                        route = route2;
                        str = str7;
                        triple = new Triple(255, 0, 48);
                        int intValue52 = ((Number) triple.component1()).intValue();
                        int intValue62 = ((Number) triple.component2()).intValue();
                        int intValue72 = ((Number) triple.component3()).intValue();
                        if (z9) {
                        }
                        if (z9) {
                        }
                        location2 = location;
                        f = f4;
                        z2 = z7;
                        z3 = z112;
                        d17 = valueOf2;
                        dashViewModel = this;
                        dashViewModel.maybeReroute(z2, location2);
                        dashViewModel.maybeRefreshTraffic(location2);
                        currentTimeMillis = System.currentTimeMillis();
                        String str92 = "";
                        if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                        }
                        if (location3 != null) {
                            dashViewModel.lastFixTime = location3.getTime();
                            long currentTimeMillis32 = System.currentTimeMillis();
                            if (dashViewModel.fixWallMs > 0) {
                            }
                            Double d192 = dashViewModel.matchedLat;
                            dashViewModel.fixLat = d192 == null ? d192.doubleValue() : location3.getLatitude();
                            Double d202 = dashViewModel.matchedLng;
                            dashViewModel.fixLng = d202 == null ? d202.doubleValue() : location3.getLongitude();
                            dashViewModel.fixBearing = location3.getBearing();
                            dashViewModel.fixSpeed = location3.getSpeed();
                            dashViewModel.fixWallMs = currentTimeMillis32;
                        }
                        long currentTimeMillis42 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                        dashViewModel.gpsStatus = (location3 != null || currentTimeMillis42 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis42 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                        if (location3 == null) {
                        }
                        Pair pair32 = roadSnapHold;
                        double doubleValue22 = (!dashViewModel2.followMode ? pair32 != null : pair32 != null) ? dashViewModel2.camLat : ((Number) pair32.getFirst()).doubleValue();
                        double doubleValue32 = (!dashViewModel2.followMode ? pair32 != null : pair32 != null) ? dashViewModel2.camLng : ((Number) pair32.getSecond()).doubleValue();
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        long nanoTime2 = System.nanoTime();
                        if (dashViewModel2.lastTickNs == 0) {
                        }
                        dashViewModel2.lastTickNs = nanoTime2;
                        if (location3 != null) {
                        }
                        if (accuracy <= f2) {
                        }
                        double d242 = 1.0d;
                        if (dashViewModel2.camInit) {
                        }
                        double d252 = d242;
                        if (dashViewModel2.camInit) {
                        }
                        dashViewModel2.frameRiderLat = pair32 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                        dashViewModel2.frameRiderLng = pair32 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                        normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                        StringBuilder sb22 = new StringBuilder();
                        String format52 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d7)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format52, str3);
                        sb22.append(format52);
                        String format62 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d3)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format62, str3);
                        sb22.append(format62);
                        sb22.append(dashViewModel2.zoom);
                        sb22.append((int) dashViewModel2.panX);
                        sb22.append((int) dashViewModel2.panY);
                        sb22.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                        sb22.append(dashViewModel2.mapDark ? 1 : 0);
                        sb22.append((int) (10.0f * normalizeHeading2));
                        sb22.append(d4 != null ? Integer.valueOf((int) (d4.doubleValue() / 100.0d)).intValue() : -1);
                        sb22.append(route != null ? route.getGeometry().size() : 0);
                        Integer num22 = this.displayedEtaMin;
                        sb22.append(num22 != null ? num22.intValue() : -1);
                        sb22.append(this.etaArrivalMs / 1000);
                        sb22.append(System.currentTimeMillis() / 1000);
                        sb22.append(z3 ? 1 : 0);
                        incomingCall = (IncomingCall) CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                        if (incomingCall != null) {
                        }
                        sb22.append(str92);
                        sb = sb22.toString();
                        long currentTimeMillis52 = System.currentTimeMillis();
                        boolean z122 = !Intrinsics.areEqual(sb, this.lastSignature);
                        this.lastSignature = sb;
                        this.lastRedrawAt = currentTimeMillis52;
                        if (this.pickerItems != null) {
                        }
                        d8 = d3;
                        d9 = d7;
                        redrawFrame(d9, d8, normalizeHeading3, normalizeHeading2, d4);
                        mutableStateFlow = this._ui;
                        while (true) {
                            value = mutableStateFlow.getValue();
                            dashUiState = (DashUiState) value;
                            if (location3 != null) {
                            }
                            String str112 = sb;
                            gpsStatus = this.gpsStatus;
                            d10 = this.matchedLat;
                            d11 = this.matchedLng;
                            if (this.headingUp) {
                            }
                            int i22 = this.zoom;
                            if (d4 != null) {
                            }
                            if (d2 != null) {
                            }
                            z5 = z2;
                            if (mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, i, valueOf, num, (String) null, z4, gpsStatus, false, z5, z3, false, false, false, false, (String) null, false, d10, d11, normalizeHeading2, Double.valueOf(d9), Double.valueOf(d8), f3, (Pair) null, (List) null, (List) null, -528596737, (Object) null))) {
                            }
                            sb = str112;
                            z2 = z5;
                        }
                    } else {
                        d3 = 0.0d;
                        route = route2;
                        str = "format(...)";
                    }
                }
                if (location != null || d14 == null || d15 == null) {
                    location2 = location;
                    dashViewModel = this;
                } else {
                    Location location6 = location;
                    dashViewModel = this;
                    location2 = location6;
                    if (LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.Companion, location6, 0L, 0L, BTN_ANSWER, (Object) null)) {
                        d17 = Double.valueOf(GeoPoint.Companion.distMeters(new GeoPoint(location2.getLatitude(), location2.getLongitude()), new GeoPoint(d14.doubleValue(), d15.doubleValue())));
                        f = f4;
                        z2 = false;
                        z3 = false;
                        dashViewModel.maybeReroute(z2, location2);
                        dashViewModel.maybeRefreshTraffic(location2);
                        currentTimeMillis = System.currentTimeMillis();
                        String str922 = "";
                        if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                        }
                        if (location3 != null) {
                        }
                        long currentTimeMillis422 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                        dashViewModel.gpsStatus = (location3 != null || currentTimeMillis422 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis422 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                        if (location3 == null) {
                        }
                        Pair pair322 = roadSnapHold;
                        double doubleValue222 = (!dashViewModel2.followMode ? pair322 != null : pair322 != null) ? dashViewModel2.camLat : ((Number) pair322.getFirst()).doubleValue();
                        double doubleValue322 = (!dashViewModel2.followMode ? pair322 != null : pair322 != null) ? dashViewModel2.camLng : ((Number) pair322.getSecond()).doubleValue();
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        long nanoTime22 = System.nanoTime();
                        if (dashViewModel2.lastTickNs == 0) {
                        }
                        dashViewModel2.lastTickNs = nanoTime22;
                        if (location3 != null) {
                        }
                        if (accuracy <= f2) {
                        }
                        double d2422 = 1.0d;
                        if (dashViewModel2.camInit) {
                        }
                        double d2522 = d2422;
                        if (dashViewModel2.camInit) {
                        }
                        dashViewModel2.frameRiderLat = pair322 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                        dashViewModel2.frameRiderLng = pair322 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                        if (dashViewModel2.camInit) {
                        }
                        if (dashViewModel2.camInit) {
                        }
                        normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                        normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                        StringBuilder sb222 = new StringBuilder();
                        String format522 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d7)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format522, str3);
                        sb222.append(format522);
                        String format622 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d3)}, 1));
                        Intrinsics.checkNotNullExpressionValue(format622, str3);
                        sb222.append(format622);
                        sb222.append(dashViewModel2.zoom);
                        sb222.append((int) dashViewModel2.panX);
                        sb222.append((int) dashViewModel2.panY);
                        sb222.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                        sb222.append(dashViewModel2.mapDark ? 1 : 0);
                        sb222.append((int) (10.0f * normalizeHeading2));
                        sb222.append(d4 != null ? Integer.valueOf((int) (d4.doubleValue() / 100.0d)).intValue() : -1);
                        sb222.append(route != null ? route.getGeometry().size() : 0);
                        Integer num222 = this.displayedEtaMin;
                        sb222.append(num222 != null ? num222.intValue() : -1);
                        sb222.append(this.etaArrivalMs / 1000);
                        sb222.append(System.currentTimeMillis() / 1000);
                        sb222.append(z3 ? 1 : 0);
                        incomingCall = (IncomingCall) CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                        if (incomingCall != null) {
                        }
                        sb222.append(str922);
                        sb = sb222.toString();
                        long currentTimeMillis522 = System.currentTimeMillis();
                        boolean z1222 = !Intrinsics.areEqual(sb, this.lastSignature);
                        this.lastSignature = sb;
                        this.lastRedrawAt = currentTimeMillis522;
                        if (this.pickerItems != null) {
                        }
                        d8 = d3;
                        d9 = d7;
                        redrawFrame(d9, d8, normalizeHeading3, normalizeHeading2, d4);
                        mutableStateFlow = this._ui;
                        while (true) {
                            value = mutableStateFlow.getValue();
                            dashUiState = (DashUiState) value;
                            if (location3 != null) {
                            }
                            String str1122 = sb;
                            gpsStatus = this.gpsStatus;
                            d10 = this.matchedLat;
                            d11 = this.matchedLng;
                            if (this.headingUp) {
                            }
                            int i222 = this.zoom;
                            if (d4 != null) {
                            }
                            if (d2 != null) {
                            }
                            z5 = z2;
                            if (mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, i, valueOf, num, (String) null, z4, gpsStatus, false, z5, z3, false, false, false, false, (String) null, false, d10, d11, normalizeHeading2, Double.valueOf(d9), Double.valueOf(d8), f3, (Pair) null, (List) null, (List) null, -528596737, (Object) null))) {
                            }
                            sb = str1122;
                            z2 = z5;
                        }
                    }
                }
                f = f4;
                z2 = false;
                z3 = false;
                dashViewModel.maybeReroute(z2, location2);
                dashViewModel.maybeRefreshTraffic(location2);
                currentTimeMillis = System.currentTimeMillis();
                String str9222 = "";
                if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
                }
                if (location3 != null) {
                }
                long currentTimeMillis4222 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
                dashViewModel.gpsStatus = (location3 != null || currentTimeMillis4222 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis4222 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
                if (location3 == null) {
                }
                Pair pair3222 = roadSnapHold;
                double doubleValue2222 = (!dashViewModel2.followMode ? pair3222 != null : pair3222 != null) ? dashViewModel2.camLat : ((Number) pair3222.getFirst()).doubleValue();
                double doubleValue3222 = (!dashViewModel2.followMode ? pair3222 != null : pair3222 != null) ? dashViewModel2.camLng : ((Number) pair3222.getSecond()).doubleValue();
                if (dashViewModel2.camInit) {
                }
                if (dashViewModel2.camInit) {
                }
                long nanoTime222 = System.nanoTime();
                if (dashViewModel2.lastTickNs == 0) {
                }
                dashViewModel2.lastTickNs = nanoTime222;
                if (location3 != null) {
                }
                if (accuracy <= f2) {
                }
                double d24222 = 1.0d;
                if (dashViewModel2.camInit) {
                }
                double d25222 = d24222;
                if (dashViewModel2.camInit) {
                }
                dashViewModel2.frameRiderLat = pair3222 != null ? Double.valueOf(dashViewModel2.camLat) : null;
                dashViewModel2.frameRiderLng = pair3222 != null ? Double.valueOf(dashViewModel2.camLng) : null;
                if (dashViewModel2.camInit) {
                }
                if (dashViewModel2.camInit) {
                }
                normalizeHeading2 = dashViewModel2.normalizeHeading(f);
                normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
                StringBuilder sb2222 = new StringBuilder();
                String format5222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d7)}, 1));
                Intrinsics.checkNotNullExpressionValue(format5222, str3);
                sb2222.append(format5222);
                String format6222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d3)}, 1));
                Intrinsics.checkNotNullExpressionValue(format6222, str3);
                sb2222.append(format6222);
                sb2222.append(dashViewModel2.zoom);
                sb2222.append((int) dashViewModel2.panX);
                sb2222.append((int) dashViewModel2.panY);
                sb2222.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
                sb2222.append(dashViewModel2.mapDark ? 1 : 0);
                sb2222.append((int) (10.0f * normalizeHeading2));
                sb2222.append(d4 != null ? Integer.valueOf((int) (d4.doubleValue() / 100.0d)).intValue() : -1);
                sb2222.append(route != null ? route.getGeometry().size() : 0);
                Integer num2222 = this.displayedEtaMin;
                sb2222.append(num2222 != null ? num2222.intValue() : -1);
                sb2222.append(this.etaArrivalMs / 1000);
                sb2222.append(System.currentTimeMillis() / 1000);
                sb2222.append(z3 ? 1 : 0);
                incomingCall = (IncomingCall) CallInfoProvider.INSTANCE.getIncomingCall().getValue();
                if (incomingCall != null) {
                }
                sb2222.append(str9222);
                sb = sb2222.toString();
                long currentTimeMillis5222 = System.currentTimeMillis();
                boolean z12222 = !Intrinsics.areEqual(sb, this.lastSignature);
                this.lastSignature = sb;
                this.lastRedrawAt = currentTimeMillis5222;
                if (this.pickerItems != null) {
                }
                d8 = d3;
                d9 = d7;
                redrawFrame(d9, d8, normalizeHeading3, normalizeHeading2, d4);
                mutableStateFlow = this._ui;
                while (true) {
                    value = mutableStateFlow.getValue();
                    dashUiState = (DashUiState) value;
                    if (location3 != null) {
                    }
                    String str11222 = sb;
                    gpsStatus = this.gpsStatus;
                    d10 = this.matchedLat;
                    d11 = this.matchedLng;
                    if (this.headingUp) {
                    }
                    int i2222 = this.zoom;
                    if (d4 != null) {
                    }
                    if (d2 != null) {
                    }
                    z5 = z2;
                    if (mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, i, valueOf, num, (String) null, z4, gpsStatus, false, z5, z3, false, false, false, false, (String) null, false, d10, d11, normalizeHeading2, Double.valueOf(d9), Double.valueOf(d8), f3, (Pair) null, (List) null, (List) null, -528596737, (Object) null))) {
                    }
                    sb = str11222;
                    z2 = z5;
                }
            }
        }
        d = null;
        Double d162 = d;
        z = location4 == null && location4.hasBearing() && location4.getSpeed() >= BEARING_MIN_SPEED && (d162 == null || d162.doubleValue() <= WAYPOINT_ARRIVE_M);
        this.matchedLat = location4 == null ? Double.valueOf(location4.getLatitude()) : null;
        this.matchedLng = location4 == null ? Double.valueOf(location4.getLongitude()) : null;
        Double d172 = null;
        d2 = null;
        if (z) {
        }
        float f42 = normalizeHeading;
        boolean z92 = !this.userWantsConnection && this.session.getState().getValue() == DashState.STREAMING;
        if (route2 != null) {
        }
        location = location4;
        str = "format(...)";
        d3 = 0.0d;
        route = route2;
        if (location != null) {
        }
        location2 = location;
        dashViewModel = this;
        f = f42;
        z2 = false;
        z3 = false;
        dashViewModel.maybeReroute(z2, location2);
        dashViewModel.maybeRefreshTraffic(location2);
        currentTimeMillis = System.currentTimeMillis();
        String str92222 = "";
        if (currentTimeMillis - dashViewModel.lastDiagMs > 15000) {
        }
        if (location3 != null) {
        }
        long currentTimeMillis42222 = dashViewModel.fixWallMs > 0 ? System.currentTimeMillis() - dashViewModel.fixWallMs : Long.MAX_VALUE;
        dashViewModel.gpsStatus = (location3 != null || currentTimeMillis42222 > 12000) ? GpsStatus.LOST : (location3.getAccuracy() > 25.0f || dashViewModel.gpsFixIntervalMs > 2500.0d || currentTimeMillis42222 > 4000) ? GpsStatus.WEAK : GpsStatus.GOOD;
        if (location3 == null) {
        }
        Pair pair32222 = roadSnapHold;
        double doubleValue22222 = (!dashViewModel2.followMode ? pair32222 != null : pair32222 != null) ? dashViewModel2.camLat : ((Number) pair32222.getFirst()).doubleValue();
        double doubleValue32222 = (!dashViewModel2.followMode ? pair32222 != null : pair32222 != null) ? dashViewModel2.camLng : ((Number) pair32222.getSecond()).doubleValue();
        if (dashViewModel2.camInit) {
        }
        if (dashViewModel2.camInit) {
        }
        long nanoTime2222 = System.nanoTime();
        if (dashViewModel2.lastTickNs == 0) {
        }
        dashViewModel2.lastTickNs = nanoTime2222;
        if (location3 != null) {
        }
        if (accuracy <= f2) {
        }
        double d242222 = 1.0d;
        if (dashViewModel2.camInit) {
        }
        double d252222 = d242222;
        if (dashViewModel2.camInit) {
        }
        dashViewModel2.frameRiderLat = pair32222 != null ? Double.valueOf(dashViewModel2.camLat) : null;
        dashViewModel2.frameRiderLng = pair32222 != null ? Double.valueOf(dashViewModel2.camLng) : null;
        if (dashViewModel2.camInit) {
        }
        if (dashViewModel2.camInit) {
        }
        normalizeHeading2 = dashViewModel2.normalizeHeading(f);
        normalizeHeading3 = dashViewModel2.normalizeHeading(dashViewModel2.camInit ? dashViewModel2.camHdg : normalizeHeading2);
        StringBuilder sb22222 = new StringBuilder();
        String format52222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d7)}, 1));
        Intrinsics.checkNotNullExpressionValue(format52222, str3);
        sb22222.append(format52222);
        String format62222 = String.format("%.6f", Arrays.copyOf(new Object[]{Double.valueOf(d3)}, 1));
        Intrinsics.checkNotNullExpressionValue(format62222, str3);
        sb22222.append(format62222);
        sb22222.append(dashViewModel2.zoom);
        sb22222.append((int) dashViewModel2.panX);
        sb22222.append((int) dashViewModel2.panY);
        sb22222.append(dashViewModel2.headingUp ? (int) (normalizeHeading3 * 10.0f) : 0);
        sb22222.append(dashViewModel2.mapDark ? 1 : 0);
        sb22222.append((int) (10.0f * normalizeHeading2));
        sb22222.append(d4 != null ? Integer.valueOf((int) (d4.doubleValue() / 100.0d)).intValue() : -1);
        sb22222.append(route != null ? route.getGeometry().size() : 0);
        Integer num22222 = this.displayedEtaMin;
        sb22222.append(num22222 != null ? num22222.intValue() : -1);
        sb22222.append(this.etaArrivalMs / 1000);
        sb22222.append(System.currentTimeMillis() / 1000);
        sb22222.append(z3 ? 1 : 0);
        incomingCall = (IncomingCall) CallInfoProvider.INSTANCE.getIncomingCall().getValue();
        if (incomingCall != null) {
        }
        sb22222.append(str92222);
        sb = sb22222.toString();
        long currentTimeMillis52222 = System.currentTimeMillis();
        boolean z122222 = !Intrinsics.areEqual(sb, this.lastSignature);
        this.lastSignature = sb;
        this.lastRedrawAt = currentTimeMillis52222;
        if (this.pickerItems != null) {
        }
        d8 = d3;
        d9 = d7;
        redrawFrame(d9, d8, normalizeHeading3, normalizeHeading2, d4);
        mutableStateFlow = this._ui;
        while (true) {
            value = mutableStateFlow.getValue();
            dashUiState = (DashUiState) value;
            if (location3 != null) {
            }
            String str112222 = sb;
            gpsStatus = this.gpsStatus;
            d10 = this.matchedLat;
            d11 = this.matchedLng;
            if (this.headingUp) {
            }
            int i22222 = this.zoom;
            if (d4 != null) {
            }
            if (d2 != null) {
            }
            z5 = z2;
            if (mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, i, valueOf, num, (String) null, z4, gpsStatus, false, z5, z3, false, false, false, false, (String) null, false, d10, d11, normalizeHeading2, Double.valueOf(d9), Double.valueOf(d8), f3, (Pair) null, (List) null, (List) null, -528596737, (Object) null))) {
            }
            sb = str112222;
            z2 = z5;
        }
    }

    private final Pair toDashDistance(double d) {
        return d < 1000.0d ? TuplesKt.to(Integer.valueOf(RangesKt.coerceIn((int) d, 0, 65535)), 48) : TuplesKt.to(Integer.valueOf(RangesKt.coerceIn((int) (d / 100.0d), 0, 65535)), 16);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a1 A[EDGE_INSN: B:25:0x00a1->B:26:0x00a1 BREAK  A[LOOP:0: B:16:0x006d->B:54:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[LOOP:0: B:16:0x006d->B:54:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final NavState trackProgress(Route route, GeoPoint geoPoint, Float f, double d, boolean z) {
        Maneuver maneuver;
        double d2;
        Object obj;
        boolean z2;
        RouteProgressMatch match = RouteProgressMatcher.INSTANCE.match(route.getGeometry(), route.getCumulative(), geoPoint, this.progressM, f, d);
        if (match == null) {
            double coerceAtLeast = RangesKt.coerceAtLeast(route.getTotalMeters() - this.progressM, 0.0d);
            return new NavState(coerceAtLeast, coerceAtLeast, f != null ? f.floatValue() : 0.0f, true, geoPoint, Double.MAX_VALUE, (Maneuver) null, this.progressM, (Maneuver) null);
        }
        double d3 = this.progressM;
        if (z) {
            d3 = Math.max(d3 - WAYPOINT_PROGRESS_TOLERANCE_M, match.getProgressMeters());
        }
        if (z) {
            this.progressM = d3;
        }
        double coerceAtLeast2 = RangesKt.coerceAtLeast(route.getTotalMeters() - d3, 0.0d);
        Iterator it = route.getManeuvers().iterator();
        while (true) {
            maneuver = null;
            if (!it.hasNext()) {
                d2 = 1.0d;
                obj = null;
                break;
            }
            obj = it.next();
            Maneuver maneuver2 = (Maneuver) obj;
            if (maneuver2.getCumulativeMeters() > d3 + 1.0d) {
                d2 = 1.0d;
                if (maneuver2.getType() != ManeuverType.DEPART) {
                    z2 = true;
                    if (!z2) {
                        break;
                    }
                }
            } else {
                d2 = 1.0d;
            }
            z2 = false;
            if (!z2) {
            }
        }
        Maneuver maneuver3 = (Maneuver) obj;
        double coerceAtLeast3 = maneuver3 != null ? RangesKt.coerceAtLeast(maneuver3.getCumulativeMeters() - d3, 0.0d) : coerceAtLeast2;
        if (maneuver3 != null) {
            Iterator it2 = route.getManeuvers().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                Maneuver maneuver4 = (Maneuver) next;
                if (maneuver4.getCumulativeMeters() > maneuver3.getCumulativeMeters() + d2 && maneuver4.getType() != ManeuverType.DEPART) {
                    maneuver = next;
                    break;
                }
            }
            maneuver = maneuver;
        }
        return new NavState(coerceAtLeast2, coerceAtLeast3, match.getHeadingDegrees(), match.getLateralDistanceMeters() > 70.0d, match.getSnappedPoint(), match.getLateralDistanceMeters(), maneuver3, d3, maneuver);
    }

    private final void updateThermal() {
        Object obj;
        String str;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.constructor-impl(Integer.valueOf(this.powerManager.getCurrentThermalStatus()));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = 0;
        }
        int intValue = ((Number) obj).intValue();
        switch (intValue) {
            case 0:
            case 1:
                str = "OK";
                break;
            case 2:
                str = "Warm";
                break;
            case 3:
            case MAX_AUTH_ATTEMPTS /* 4 */:
                str = "Hot";
                break;
            default:
                str = "Throttling";
                break;
        }
        if (Intrinsics.areEqual(str, ((DashUiState) this._ui.getValue()).getThermal())) {
            return;
        }
        MutableStateFlow mutableStateFlow = this._ui;
        while (true) {
            Object value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            int i = intValue;
            String str2 = str;
            if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, str2, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -2097153, (Object) null))) {
                return;
            }
            mutableStateFlow = mutableStateFlow2;
            str = str2;
            intValue = i;
        }
    }

    private final void wakeFrameLoop() {
        this.frameWake.trySend-JP2dKIU(Unit.INSTANCE);
    }

    private final int zoomInCode() {
        return ((Boolean) this.dashConfig.getInvertZoom().getValue()).booleanValue() ? 19 : 20;
    }

    private final int zoomOutCode() {
        return ((Boolean) this.dashConfig.getInvertZoom().getValue()).booleanValue() ? 20 : 19;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IntRange zoomRange() {
        return new IntRange(DASH_MIN_ZOOM, 20);
    }

    public final void connect() {
        Object value;
        String findDashSsid;
        Object value2;
        this.userWantsConnection = true;
        this.hasStreamedThisConnection = false;
        this.authAttempts = 0;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -4194497, (Object) null)));
        if (!this.wifiManager.isWifiEnabled()) {
            this.userWantsConnection = false;
            MutableStateFlow mutableStateFlow2 = this._ui;
            do {
                value2 = mutableStateFlow2.getValue();
            } while (!mutableStateFlow2.compareAndSet(value2, DashUiState.copy$default((DashUiState) value2, ConnStage.ERROR, 0, (String) null, (String) null, (String) null, (String) null, "Göstergeye bağlanmak için Wi‑Fi'yi açın", 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, true, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -4194370, (Object) null)));
            return;
        }
        RideDiagnostics.INSTANCE.init(getApplication());
        RideDiagnostics.INSTANCE.start("connect");
        RideDiagnostics.INSTANCE.log("build", "apk=" + BuildId.INSTANCE.sha12(getApplication()) + " vc=14 name=1.3.2-OSS-preview");
        RideDiagnostics.INSTANCE.log("connect", "ssid='" + this.dashConfig.getSsid() + "' needsDiscovery=" + this.dashConfig.getNeedsDiscovery() + " dest=" + ((DashUiState) this._ui.getValue()).getDestinationName());
        this.loggedFirstFrame = false;
        DashKeepAliveService.Companion.start(getApplication());
        this.location.start();
        startRecording();
        Job job = this.tickerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.tickerJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), Dispatchers.getDefault(), (CoroutineStart) null, new connect.3(this, (Continuation) null), 2, (Object) null);
        if (this.dashConfig.getNeedsDiscovery() && (findDashSsid = this.wifiManager.findDashSsid(this.dashConfig.getSsidPrefix())) != null) {
            boolean z = false;
            this.dashConfig.setSsid(findDashSsid);
            MutableStateFlow mutableStateFlow3 = this._ui;
            while (true) {
                Object value3 = mutableStateFlow3.getValue();
                MutableStateFlow mutableStateFlow4 = mutableStateFlow3;
                boolean z2 = z;
                if (mutableStateFlow4.compareAndSet(value3, DashUiState.copy$default((DashUiState) value3, (ConnStage) null, 0, (String) null, findDashSsid, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -9, (Object) null))) {
                    break;
                }
                mutableStateFlow3 = mutableStateFlow4;
                z = z2;
            }
        }
        if (((WifiState) this.wifiManager.getState().getValue()).getStatus() == WifiConnStatus.CONNECTED) {
            this.session.connect(((DashUiState) this._ui.getValue()).getSsid(), this.wifiManager.getNetwork());
            return;
        }
        boolean isBlank = StringsKt.isBlank(this.dashConfig.getSsid());
        DashWifiManager dashWifiManager = this.wifiManager;
        if (isBlank) {
            dashWifiManager.connect(this.dashConfig.getSsidPrefix(), this.dashConfig.getPassword(), true);
        } else {
            DashWifiManager.connect$default(dashWifiManager, this.dashConfig.getSsid(), this.dashConfig.getPassword(), false, MAX_AUTH_ATTEMPTS, (Object) null);
        }
    }

    public final void disconnect() {
        this.userWantsConnection = false;
        cancelReconnectGiveup();
        RideDiagnostics.INSTANCE.log("connect", "user disconnect");
        stopRecording();
        this.session.disconnect();
        this.wifiManager.disconnect();
        releaseBackgroundResources();
        refreshStage();
        RideDiagnostics.INSTANCE.stop("disconnect");
    }

    public final void exitNavigation() {
        Object value;
        this.routeRequestGeneration++;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.routeRequestJob = null;
        this.rerouting = false;
        this.trafficRefreshInFlight = false;
        this.waitingForFreshRouteOrigin = false;
        this.destLat = null;
        this.destLng = null;
        this.routeWaypoints = CollectionsKt.emptyList();
        this.routeStops = CollectionsKt.emptyList();
        this.activeStopIndex = 0;
        this.routeLegOffset = 0;
        this.route = null;
        this.progressM = 0.0d;
        this.offRouteSince = 0L;
        this.offRouteConfirmation.reset();
        this.panX = 0.0f;
        this.panY = 0.0f;
        this.followMode = true;
        this.smoothEtaSec = 0.0d;
        this.etaArrivalMs = 0L;
        this.displayedEtaMin = null;
        this.voice.resetTrip();
        this.lastSignature = "";
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, true, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, CollectionsKt.emptyList(), CollectionsKt.emptyList(), 535769567, (Object) null)));
        this.session.updateRouteCard("WayfindR");
    }

    public final void forgetDash() {
        Object value;
        this.dashConfig.forgetDash();
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, "", (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -9, (Object) null)));
    }

    public final StateFlow getDashFields() {
        return this.session.getDashFields();
    }

    public final StateFlow getPreviewFrame() {
        return this.previewFrame;
    }

    public final StateFlow getUi() {
        return this.ui;
    }

    protected void onCleared() {
        Object obj;
        super.onCleared();
        BroadcastReceiver broadcastReceiver = this.debugButtonRx;
        if (broadcastReceiver != null) {
            try {
                Result.Companion companion = Result.Companion;
                getApplication().unregisterReceiver(broadcastReceiver);
                obj = Result.constructor-impl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Result.box-impl(obj);
        }
        stopRecording();
        this.session.disconnect();
        this.wifiManager.disconnect();
        releaseBackgroundResources();
        this.voice.shutdown();
        RideDiagnostics.INSTANCE.stop("app closed");
    }

    public final void panBy(float f, float f2) {
        manualPan(f, f2);
    }

    public final void prefetchTiles(double d, double d2) {
        Location location = (Location) this.location.getLocation().getValue();
        this.tiles.prefetch(d, d2, location != null ? Double.valueOf(location.getLatitude()) : null, location != null ? Double.valueOf(location.getLongitude()) : null);
    }

    public final void recenter() {
        Object value;
        this.panX = 0.0f;
        this.panY = 0.0f;
        this.followMode = true;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, true, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -1048577, (Object) null)));
    }

    public final void setDestination(String str, Double d, Double d2) {
        Intrinsics.checkNotNullParameter(str, "name");
        setDestinations$default(this, CollectionsKt.listOf(new SharedLocation(str, d, d2, (String) null, false, 24, (DefaultConstructorMarker) null)), null, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0130 A[LOOP:1: B:49:0x012a->B:51:0x0130, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01ad  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02c9 A[LOOP:2: B:54:0x0172->B:71:0x02c9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x022d A[EDGE_INSN: B:72:0x022d->B:73:0x022d BREAK  A[LOOP:2: B:54:0x0172->B:71:0x02c9], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0183  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void setDestinations(List list, Route route) {
        String str;
        double d;
        Route route2;
        MutableStateFlow mutableStateFlow;
        Object value;
        DashUiState dashUiState;
        boolean z;
        Pair pair;
        List geometry;
        List list2;
        List traffic;
        double d2;
        Double d3;
        SharedLocation sharedLocation;
        String str2;
        boolean z2;
        DashViewModel dashViewModel = this;
        Intrinsics.checkNotNullParameter(list, "stops");
        dashViewModel.routeRequestGeneration++;
        Job job = dashViewModel.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        dashViewModel.routeRequestJob = null;
        dashViewModel.rerouting = false;
        dashViewModel.trafficRefreshInFlight = false;
        dashViewModel.waitingForFreshRouteOrigin = false;
        dashViewModel.offRouteSince = 0L;
        dashViewModel.offRouteConfirmation.reset();
        dashViewModel.offRouteStreakStart = 0L;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            SharedLocation sharedLocation2 = (SharedLocation) obj;
            if ((sharedLocation2.getLat() == null || sharedLocation2.getLng() == null) ? false : true) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        SharedLocation sharedLocation3 = (SharedLocation) CollectionsKt.lastOrNull(arrayList2);
        if (sharedLocation3 == null || (str = sharedLocation3.getName()) == null) {
            str = "WayfindR";
        }
        String str3 = str;
        Double lat = sharedLocation3 != null ? sharedLocation3.getLat() : null;
        Double lng = sharedLocation3 != null ? sharedLocation3.getLng() : null;
        if (route != null) {
            GeoPoint geoPoint = (GeoPoint) CollectionsKt.lastOrNull(route.getGeometry());
            d = 0.0d;
            if (route.getSource() == dashViewModel.dashConfig.getRoutingSource().getValue() && route.getGeometry().size() >= 2) {
                if ((Math.abs(route.getTotalMeters()) <= Double.MAX_VALUE) && route.getTotalMeters() >= 0.0d && geoPoint != null && lat != null && lng != null && GeoPoint.Companion.distMeters(geoPoint, new GeoPoint(lat.doubleValue(), lng.doubleValue())) <= 750.0d) {
                    z2 = true;
                    if (z2) {
                        route2 = route;
                        dashViewModel.routeStops = arrayList2;
                        dashViewModel.autoRouteCalls = 0;
                        dashViewModel.autoRouteCapLogged = false;
                        dashViewModel.rerouteStreak = 0;
                        dashViewModel.lastRerouteAt = 0L;
                        dashViewModel.activeStopIndex = 0;
                        dashViewModel.routeLegOffset = 0;
                        List<SharedLocation> dropLast = CollectionsKt.dropLast(arrayList2, 1);
                        boolean z3 = false;
                        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(dropLast, BTN_MEDIA_PREV));
                        for (SharedLocation sharedLocation4 : dropLast) {
                            Double lat2 = sharedLocation4.getLat();
                            Intrinsics.checkNotNull(lat2);
                            double doubleValue = lat2.doubleValue();
                            Double lng2 = sharedLocation4.getLng();
                            Intrinsics.checkNotNull(lng2);
                            arrayList3.add(new GeoPoint(doubleValue, lng2.doubleValue()));
                            dropLast = dropLast;
                            z3 = z3;
                        }
                        dashViewModel.routeWaypoints = arrayList3;
                        mutableStateFlow = dashViewModel._ui;
                        while (true) {
                            value = mutableStateFlow.getValue();
                            dashUiState = (DashUiState) value;
                            z = route2 == null;
                            pair = (lat != null || lng == null) ? null : TuplesKt.to(lat, lng);
                            geometry = route2 == null ? route2.getGeometry() : null;
                            if (geometry == null) {
                                geometry = CollectionsKt.emptyList();
                            }
                            list2 = geometry;
                            traffic = route2 == null ? route2.getTraffic() : null;
                            if (traffic == null) {
                                traffic = CollectionsKt.emptyList();
                            }
                            d2 = d;
                            d3 = lng;
                            sharedLocation = sharedLocation3;
                            str2 = str3;
                            if (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, 0, (String) null, (String) null, (String) null, str3, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, z, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, pair, list2, traffic, 536854495, (Object) null))) {
                                break;
                            }
                            dashViewModel = this;
                            d = d2;
                            sharedLocation3 = sharedLocation;
                            str3 = str2;
                            lng = d3;
                        }
                        dashViewModel.destLat = lat;
                        dashViewModel.destLng = d3;
                        dashViewModel.route = route2;
                        if (dashViewModel.frameBitmap == null) {
                            dashViewModel.frameBitmap = dashViewModel.newFrameBitmap();
                        }
                        dashViewModel.lastSignature = "";
                        dashViewModel.progressM = d2;
                        dashViewModel.smoothEtaSec = d2;
                        dashViewModel.etaArrivalMs = 0L;
                        dashViewModel.displayedEtaMin = null;
                        dashViewModel.voice.resetTrip();
                        dashViewModel.session.updateRouteCard(str2);
                        if (lat != null || d3 == null) {
                        }
                        Location lastKnown = dashViewModel.location.lastKnown();
                        dashViewModel.tiles.prefetch(lat.doubleValue(), d3.doubleValue(), lastKnown != null ? Double.valueOf(lastKnown.getLatitude()) : null, lastKnown != null ? Double.valueOf(lastKnown.getLongitude()) : null);
                        if (route2 != null) {
                            dashViewModel.lastTrafficRefreshAt = System.currentTimeMillis();
                            dashViewModel.tiles.prefetchRoute(route2.getGeometry());
                            return;
                        }
                        Intrinsics.checkNotNull(sharedLocation);
                        Double lat3 = sharedLocation.getLat();
                        Intrinsics.checkNotNull(lat3);
                        double doubleValue2 = lat3.doubleValue();
                        Double lng3 = sharedLocation.getLng();
                        Intrinsics.checkNotNull(lng3);
                        dashViewModel.fetchRoute(doubleValue2, lng3.doubleValue(), dashViewModel.routeWaypoints);
                        return;
                    }
                }
            }
            z2 = false;
            if (z2) {
            }
        } else {
            d = 0.0d;
        }
        route2 = null;
        dashViewModel.routeStops = arrayList2;
        dashViewModel.autoRouteCalls = 0;
        dashViewModel.autoRouteCapLogged = false;
        dashViewModel.rerouteStreak = 0;
        dashViewModel.lastRerouteAt = 0L;
        dashViewModel.activeStopIndex = 0;
        dashViewModel.routeLegOffset = 0;
        List<SharedLocation> dropLast2 = CollectionsKt.dropLast(arrayList2, 1);
        boolean z32 = false;
        ArrayList arrayList32 = new ArrayList(CollectionsKt.collectionSizeOrDefault(dropLast2, BTN_MEDIA_PREV));
        while (r14.hasNext()) {
        }
        dashViewModel.routeWaypoints = arrayList32;
        mutableStateFlow = dashViewModel._ui;
        while (true) {
            value = mutableStateFlow.getValue();
            dashUiState = (DashUiState) value;
            if (route2 == null) {
            }
            if (lat != null) {
            }
            if (route2 == null) {
            }
            if (geometry == null) {
            }
            list2 = geometry;
            if (route2 == null) {
            }
            if (traffic == null) {
            }
            d2 = d;
            d3 = lng;
            sharedLocation = sharedLocation3;
            str2 = str3;
            if (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default(dashUiState, (ConnStage) null, 0, (String) null, (String) null, (String) null, str3, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, z, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, pair, list2, traffic, 536854495, (Object) null))) {
            }
            dashViewModel = this;
            d = d2;
            sharedLocation3 = sharedLocation;
            str3 = str2;
            lng = d3;
        }
        dashViewModel.destLat = lat;
        dashViewModel.destLng = d3;
        dashViewModel.route = route2;
        if (dashViewModel.frameBitmap == null) {
        }
        dashViewModel.lastSignature = "";
        dashViewModel.progressM = d2;
        dashViewModel.smoothEtaSec = d2;
        dashViewModel.etaArrivalMs = 0L;
        dashViewModel.displayedEtaMin = null;
        dashViewModel.voice.resetTrip();
        dashViewModel.session.updateRouteCard(str2);
        if (lat != null) {
        }
    }

    public final void setMapDark(boolean z) {
        this.dashConfig.setMapDarkMode(z);
        this.dashConfig.setMapDarkAuto(false);
    }

    public final void setMapDarkAuto(boolean z) {
        this.dashConfig.setMapDarkAuto(z);
    }

    public final void setSsid(String str) {
        Object value;
        Intrinsics.checkNotNullParameter(str, "s");
        this.dashConfig.setSsid(StringsKt.trim(str).toString());
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, StringsKt.trim(str).toString(), (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -9, (Object) null)));
    }

    public final void setWifiPassword(String str) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, "p");
        this.dashConfig.setPassword(str2);
        MutableStateFlow mutableStateFlow = this._ui;
        while (true) {
            Object value = mutableStateFlow.getValue();
            MutableStateFlow mutableStateFlow2 = mutableStateFlow;
            if (mutableStateFlow2.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, str2, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, false, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -17, (Object) null))) {
                return;
            }
            str2 = str;
            mutableStateFlow = mutableStateFlow2;
        }
    }

    public final void toggleHeadingUp() {
        Object value;
        this.headingUp = !this.headingUp;
        MutableStateFlow mutableStateFlow = this._ui;
        do {
            value = mutableStateFlow.getValue();
        } while (!mutableStateFlow.compareAndSet(value, DashUiState.copy$default((DashUiState) value, (ConnStage) null, 0, (String) null, (String) null, (String) null, (String) null, (String) null, 0, 0, (Double) null, (Integer) null, (String) null, false, (GpsStatus) null, false, false, false, this.headingUp, false, false, false, (String) null, false, (Double) null, (Double) null, 0.0f, (Double) null, (Double) null, 0.0f, (Pair) null, (List) null, (List) null, -131073, (Object) null)));
    }

    public final void zoomIn() {
        setZoom(this.zoom + 1);
    }

    public final void zoomOut() {
        setZoom(this.zoom - 1);
    }
}
