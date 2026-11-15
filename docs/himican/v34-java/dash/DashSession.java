package com.example.wayfindr.dash;

import android.net.Network;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.core.app.NotificationCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.AuthEvent;
import com.example.wayfindr.dash.protocol.DashCommands;
import com.example.wayfindr.dash.protocol.K1GPacket;
import com.example.wayfindr.dash.protocol.Tlv;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.util.Dbg;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: DashSession.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\u001e\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\b\u0007\u0018\u0000 \u0093\u00012\u00020\u0001:\u0004\u0093\u0001\u0094\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J \u0010?\u001a\u00020\u001b2\u0006\u0010@\u001a\u00020\u00142\u0006\u0010A\u001a\u00020\u00142\u0006\u0010B\u001a\u00020!H\u0002J\u0015\u0010E\u001a\u00020\u001b2\b\u0010F\u001a\u0004\u0018\u00010G¢\u0006\u0002\u0010HJ(\u0010J\u001a\u00020\u001b2\b\u0010K\u001a\u0004\u0018\u00010!2\u0006\u0010L\u001a\u00020!2\u0006\u0010M\u001a\u00020!2\u0006\u0010N\u001a\u00020\u0012J\u0010\u0010O\u001a\u00020\u001b2\b\u0010P\u001a\u0004\u0018\u00010!JX\u0010[\u001a\u00020\u001b2\u0006\u0010\\\u001a\u00020\u00142\u0006\u0010]\u001a\u00020\u00142\u0006\u0010^\u001a\u00020\u00142\u0006\u0010_\u001a\u00020\u00142\u0006\u0010`\u001a\u00020\u00142\n\b\u0002\u0010a\u001a\u0004\u0018\u00010!2\b\b\u0002\u0010b\u001a\u00020\u00142\b\b\u0002\u0010c\u001a\u00020\u00142\b\b\u0002\u0010d\u001a\u00020\u0014J\u0010\u0010e\u001a\u00020f2\u0006\u0010g\u001a\u00020\u0012H\u0002J\u001a\u0010h\u001a\u00020\u001b2\u0006\u0010i\u001a\u00020!2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010kJ\u0006\u0010l\u001a\u00020\u001bJ\u000e\u0010m\u001a\u00020\u001b2\u0006\u0010n\u001a\u00020fJ\u000e\u0010o\u001a\u00020\u001b2\u0006\u0010p\u001a\u00020!J\u0006\u0010q\u001a\u00020\u001bJ\"\u0010r\u001a\u00020\u001b2\u0006\u0010i\u001a\u00020!2\n\b\u0002\u0010j\u001a\u0004\u0018\u00010kH\u0082@¢\u0006\u0002\u0010sJ\u0016\u0010t\u001a\u00020\u001b2\u0006\u0010u\u001a\u00020\u000eH\u0082@¢\u0006\u0002\u0010vJ\u0010\u0010w\u001a\u00020\u001b2\u0006\u0010u\u001a\u00020\u000eH\u0002J\u0018\u0010x\u001a\u00020\u001b2\u0006\u0010y\u001a\u00020f2\u0006\u0010u\u001a\u00020\u000eH\u0002J\t\u0010\u0087\u0001\u001a\u00020\u001bH\u0002J\u0011\u0010\u0088\u0001\u001a\u00020\u001b2\u0006\u0010u\u001a\u00020\u000eH\u0002J\t\u0010\u0089\u0001\u001a\u00020\u001bH\u0002J\t\u0010\u008a\u0001\u001a\u00020\u001bH\u0002J\t\u0010\u008b\u0001\u001a\u00020\u001bH\u0002J\t\u0010\u008c\u0001\u001a\u00020\u001bH\u0002J\u0012\u0010\u008d\u0001\u001a\u00020\u001b2\u0007\u0010\u008e\u0001\u001a\u00020!H\u0002J\r\u0010\u008f\u0001\u001a\u00020!*\u00020fH\u0002J\u001d\u0010\u0090\u0001\u001a\u0004\u0018\u00010f2\u0007\u0010\u0091\u0001\u001a\u00020f2\u0007\u0010\u0092\u0001\u001a\u00020fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR(\u0010 \u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u001d\"\u0004\b#\u0010\u001fR\u001a\u0010$\u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010,\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010/\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u00101\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000RB\u00106\u001a6\u0012\u0004\u0012\u00020\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u00160807j\u001a\u0012\u0004\u0012\u00020\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u001608`9X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010:\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020<0;0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010=\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020<0;0\n¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\fR\u000e\u0010C\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010I\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Q\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010R\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010V\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010W\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010X\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Y\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010Z\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R#\u0010z\u001a\n\u0012\u0004\u0012\u00020|\u0018\u00010{X\u0086\u000e¢\u0006\u000f\n\u0000\u001a\u0004\b}\u0010~\"\u0005\b\u007f\u0010\u0080\u0001R!\u0010\u0081\u0001\u001a\u0004\u0018\u00010|X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0011\u0010\u0086\u0001\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0095\u0001"}, d2 = {"Lcom/example/wayfindr/dash/DashSession;", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Lkotlinx/coroutines/CoroutineScope;)V", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/dash/DashState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "socket", "Lcom/example/wayfindr/dash/DashSocket;", "auth", "Lcom/example/wayfindr/dash/DashAuth;", "authConfirmed", "", "authRetries", "", "lastRxMs", "", "loggedFirstAck", "onButton", "Lkotlin/Function1;", "", "", "getOnButton", "()Lkotlin/jvm/functions/Function1;", "setOnButton", "(Lkotlin/jvm/functions/Function1;)V", "onError", "", "getOnError", "setOnError", "destinationName", "getDestinationName", "()Ljava/lang/String;", "setDestinationName", "(Ljava/lang/String;)V", "sessionJob", "Lkotlinx/coroutines/Job;", "rxJob", "projHbJob", "routeCardJob", "heartbeatJob", "navInfoJob", "mediaInfoJob", "npTitle", "npAlbum", "npArtist", "npPlaying", "lastJoyRxMs", "dashDataLast", "Ljava/util/HashMap;", "Lkotlin/Pair;", "Lkotlin/collections/HashMap;", "_dashFields", "", "Lcom/example/wayfindr/dash/DashSession$DashField;", "dashFields", "getDashFields", "logDashData", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "sub", "value", "compassHeading", "gpsFix", "updateGps", "bearingDeg", "", "(Ljava/lang/Float;)V", "caller", "updateNowPlaying", "title", "album", "artist", "playing", "updateCall", "callerName", "navManeuver", "navPrimaryDist", "navPrimaryUnit", "navTotalDist", "navTotalUnit", "navEta", "navActive", "navSecondary", "navSecondaryDist", "navSecondaryUnit", "updateNavInfo", "maneuver", "primaryDist", "primaryUnit", "totalDist", "totalUnit", "etaHHMM", "secondary", "secondaryDist", "secondaryUnit", "liveRouteCard", "", "projectionOn", "connect", "ssid", "network", "Landroid/net/Network;", "startStreaming", "sendRtp", "packet", "updateRouteCard", HintConstants.AUTOFILL_HINT_NAME, "disconnect", "runSession", "(Ljava/lang/String;Landroid/net/Network;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enterNavMode", "sock", "(Lcom/example/wayfindr/dash/DashSocket;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchReceiveLoop", "dispatchIncoming", "pkt", "phoneStatusProvider", "Lkotlin/Function0;", "Lcom/example/wayfindr/dash/PhoneStatus;", "getPhoneStatusProvider", "()Lkotlin/jvm/functions/Function0;", "setPhoneStatusProvider", "(Lkotlin/jvm/functions/Function0;)V", "latestPhoneStatus", "getLatestPhoneStatus", "()Lcom/example/wayfindr/dash/PhoneStatus;", "setLatestPhoneStatus", "(Lcom/example/wayfindr/dash/PhoneStatus;)V", "phoneStatusJob", "launchPhoneStatus", "launchStatusHeartbeat", "launchProjectionHeartbeat", "launchRouteCardKeepAlive", "launchNavInfo", "launchMediaInfo", "fail", NotificationCompat.CATEGORY_MESSAGE, "toHexFull", "aesDecryptCbc", "ivAndCt", "key", "Companion", "DashField", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashSession {
    private static final long AUTH_TIMEOUT = 15000;
    private static final long BURST_PAUSE = 20;
    private static final long DASH_DATA_MIN_GAP_MS = 1000;
    private static final long DASH_DATA_REPEAT_MS = 30000;
    private static final String HOSTNAME = "WayfindR";
    private static final long PROJ_HB_MS = 250;
    private static final long ROUTE_CARD_MS = 1000;
    private static final long RX_WATCHDOG_MS = 6000;
    private static final String TAG = "DashSession";
    private final MutableStateFlow<Map<Integer, DashField>> _dashFields;
    private final MutableStateFlow<DashState> _state;
    private DashAuth auth;
    private volatile boolean authConfirmed;
    private volatile int authRetries;
    private volatile String caller;
    private volatile int compassHeading;
    private final HashMap<Integer, Pair<String, Long>> dashDataLast;
    private final StateFlow<Map<Integer, DashField>> dashFields;
    private volatile String destinationName;
    private volatile boolean gpsFix;
    private Job heartbeatJob;
    private volatile long lastJoyRxMs;
    private volatile long lastRxMs;
    private volatile PhoneStatus latestPhoneStatus;
    private volatile boolean loggedFirstAck;
    private Job mediaInfoJob;
    private volatile boolean navActive;
    private volatile String navEta;
    private Job navInfoJob;
    private volatile int navManeuver;
    private volatile int navPrimaryDist;
    private volatile int navPrimaryUnit;
    private volatile int navSecondary;
    private volatile int navSecondaryDist;
    private volatile int navSecondaryUnit;
    private volatile int navTotalDist;
    private volatile int navTotalUnit;
    private volatile String npAlbum;
    private volatile String npArtist;
    private volatile boolean npPlaying;
    private volatile String npTitle;
    private Function1<? super Byte, Unit> onButton;
    private Function1<? super String, Unit> onError;
    private Job phoneStatusJob;
    private volatile Function0<PhoneStatus> phoneStatusProvider;
    private Job projHbJob;
    private Job routeCardJob;
    private Job rxJob;
    private final CoroutineScope scope;
    private Job sessionJob;
    private DashSocket socket;
    private final StateFlow<DashState> state;
    public static final int $stable = 8;

    public DashSession(CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.scope = scope;
        this._state = StateFlowKt.MutableStateFlow(DashState.IDLE);
        this.state = FlowKt.asStateFlow(this._state);
        this.destinationName = HOSTNAME;
        this.npAlbum = "";
        this.npArtist = "";
        this.dashDataLast = new HashMap<>();
        this._dashFields = StateFlowKt.MutableStateFlow(MapsKt.emptyMap());
        this.dashFields = this._dashFields;
        this.navManeuver = 9;
        this.navPrimaryUnit = 48;
        this.navTotalUnit = 48;
        this.navSecondary = 255;
        this.navSecondaryUnit = 48;
    }

    public final StateFlow<DashState> getState() {
        return this.state;
    }

    public final Function1<Byte, Unit> getOnButton() {
        return this.onButton;
    }

    public final void setOnButton(Function1<? super Byte, Unit> function1) {
        this.onButton = function1;
    }

    public final Function1<String, Unit> getOnError() {
        return this.onError;
    }

    public final void setOnError(Function1<? super String, Unit> function1) {
        this.onError = function1;
    }

    public final String getDestinationName() {
        return this.destinationName;
    }

    public final void setDestinationName(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.destinationName = str;
    }

    /* compiled from: DashSession.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\bHÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0003HÖ\u0001J\t\u0010!\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000e¨\u0006\""}, d2 = {"Lcom/example/wayfindr/dash/DashSession$DashField;", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "", "sub", "value", "", "updatedMs", "", "changedMs", "changes", "<init>", "(IILjava/lang/String;JJI)V", "getType", "()I", "getSub", "getValue", "()Ljava/lang/String;", "getUpdatedMs", "()J", "getChangedMs", "getChanges", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class DashField {
        public static final int $stable = 0;
        private final long changedMs;
        private final int changes;
        private final int sub;
        private final int type;
        private final long updatedMs;
        private final String value;

        public static /* synthetic */ DashField copy$default(DashField dashField, int i, int i2, String str, long j, long j2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = dashField.type;
            }
            if ((i4 & 2) != 0) {
                i2 = dashField.sub;
            }
            if ((i4 & 4) != 0) {
                str = dashField.value;
            }
            if ((i4 & 8) != 0) {
                j = dashField.updatedMs;
            }
            if ((i4 & 16) != 0) {
                j2 = dashField.changedMs;
            }
            if ((i4 & 32) != 0) {
                i3 = dashField.changes;
            }
            int i5 = i3;
            long j3 = j2;
            String str2 = str;
            return dashField.copy(i, i2, str2, j, j3, i5);
        }

        /* renamed from: component1, reason: from getter */
        public final int getType() {
            return this.type;
        }

        /* renamed from: component2, reason: from getter */
        public final int getSub() {
            return this.sub;
        }

        /* renamed from: component3, reason: from getter */
        public final String getValue() {
            return this.value;
        }

        /* renamed from: component4, reason: from getter */
        public final long getUpdatedMs() {
            return this.updatedMs;
        }

        /* renamed from: component5, reason: from getter */
        public final long getChangedMs() {
            return this.changedMs;
        }

        /* renamed from: component6, reason: from getter */
        public final int getChanges() {
            return this.changes;
        }

        public final DashField copy(int type, int sub, String value, long updatedMs, long changedMs, int changes) {
            Intrinsics.checkNotNullParameter(value, "value");
            return new DashField(type, sub, value, updatedMs, changedMs, changes);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DashField)) {
                return false;
            }
            DashField dashField = (DashField) other;
            return this.type == dashField.type && this.sub == dashField.sub && Intrinsics.areEqual(this.value, dashField.value) && this.updatedMs == dashField.updatedMs && this.changedMs == dashField.changedMs && this.changes == dashField.changes;
        }

        public int hashCode() {
            return (((((((((Integer.hashCode(this.type) * 31) + Integer.hashCode(this.sub)) * 31) + this.value.hashCode()) * 31) + Long.hashCode(this.updatedMs)) * 31) + Long.hashCode(this.changedMs)) * 31) + Integer.hashCode(this.changes);
        }

        public String toString() {
            return "DashField(type=" + this.type + ", sub=" + this.sub + ", value=" + this.value + ", updatedMs=" + this.updatedMs + ", changedMs=" + this.changedMs + ", changes=" + this.changes + ")";
        }

        public DashField(int type, int sub, String value, long updatedMs, long changedMs, int changes) {
            Intrinsics.checkNotNullParameter(value, "value");
            this.type = type;
            this.sub = sub;
            this.value = value;
            this.updatedMs = updatedMs;
            this.changedMs = changedMs;
            this.changes = changes;
        }

        public final int getSub() {
            return this.sub;
        }

        public final int getType() {
            return this.type;
        }

        public final long getUpdatedMs() {
            return this.updatedMs;
        }

        public final String getValue() {
            return this.value;
        }

        public final long getChangedMs() {
            return this.changedMs;
        }

        public final int getChanges() {
            return this.changes;
        }
    }

    public final StateFlow<Map<Integer, DashField>> getDashFields() {
        return this.dashFields;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ca A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void logDashData(int type, int sub, String value) {
        long changedMs;
        int key;
        boolean due;
        int key2 = (type << 8) | sub;
        long now = System.currentTimeMillis();
        DashField dashField = this._dashFields.getValue().get(Integer.valueOf(key2));
        boolean z = dashField == null || !Intrinsics.areEqual(dashField.getValue(), value);
        if (dashField == null || z || now - dashField.getUpdatedMs() >= 1000) {
            MutableStateFlow<Map<Integer, DashField>> mutableStateFlow = this._dashFields;
            Map<Integer, DashField> value2 = this._dashFields.getValue();
            Integer valueOf = Integer.valueOf(key2);
            if (z) {
                changedMs = now;
            } else {
                Intrinsics.checkNotNull(dashField);
                changedMs = dashField.getChangedMs();
            }
            key = key2;
            mutableStateFlow.setValue(MapsKt.plus(value2, TuplesKt.to(valueOf, new DashField(type, sub, value, now, changedMs, (dashField != null ? dashField.getChanges() : 0) + ((!z || dashField == null) ? 0 : 1)))));
        } else {
            key = key2;
        }
        Pair prev = this.dashDataLast.get(Integer.valueOf(key));
        boolean changed = prev == null || !Intrinsics.areEqual(prev.getFirst(), value);
        if (prev != null) {
            if (now - prev.getSecond().longValue() < (changed ? 1000L : DASH_DATA_REPEAT_MS)) {
                due = false;
                if (due) {
                    return;
                }
                this.dashDataLast.put(Integer.valueOf(key), TuplesKt.to(value, Long.valueOf(now)));
                RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
                String format = String.format("%02X %02X %s%s", Arrays.copyOf(new Object[]{Integer.valueOf(type), Integer.valueOf(sub), value, changed ? "" : " (same)"}, 4));
                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                rideDiagnostics.log("dash data", format);
                return;
            }
        }
        due = true;
        if (due) {
        }
    }

    public final void updateGps(Float bearingDeg) {
        this.gpsFix = true;
        if (bearingDeg != null) {
            this.compassHeading = Math.round(bearingDeg.floatValue());
        }
    }

    public final void updateNowPlaying(String title, String album, String artist, boolean playing) {
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        String str = null;
        if (title != null && !StringsKt.isBlank(title)) {
            str = title;
        }
        this.npTitle = str;
        this.npAlbum = album;
        this.npArtist = artist;
        this.npPlaying = playing;
    }

    public final void updateCall(String callerName) {
        String str = null;
        if (callerName != null && !StringsKt.isBlank(callerName)) {
            str = callerName;
        }
        this.caller = str;
    }

    public final void updateNavInfo(int maneuver, int primaryDist, int primaryUnit, int totalDist, int totalUnit, String etaHHMM, int secondary, int secondaryDist, int secondaryUnit) {
        this.navSecondary = secondary;
        this.navSecondaryDist = secondaryDist;
        this.navSecondaryUnit = secondaryUnit;
        this.navManeuver = maneuver;
        this.navPrimaryDist = primaryDist;
        this.navPrimaryUnit = primaryUnit;
        this.navTotalDist = totalDist;
        this.navTotalUnit = totalUnit;
        this.navEta = etaHHMM;
        this.navActive = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final byte[] liveRouteCard(boolean projectionOn) {
        byte[] routeCard;
        if (this.navActive) {
            return DashCommands.INSTANCE.routeCard(this.destinationName, projectionOn, Integer.valueOf(this.navManeuver), Integer.valueOf(this.navPrimaryUnit), Integer.valueOf(this.navTotalDist), Integer.valueOf(this.navTotalUnit), this.navEta, Integer.valueOf(this.navSecondary), Integer.valueOf(this.navSecondaryDist), Integer.valueOf(this.navSecondaryUnit));
        }
        routeCard = DashCommands.INSTANCE.routeCard(this.destinationName, (r21 & 2) != 0 ? false : projectionOn, (r21 & 4) != 0 ? null : null, (r21 & 8) != 0 ? null : null, (r21 & 16) != 0 ? null : null, (r21 & 32) != 0 ? null : null, (r21 & 64) != 0 ? null : null, (r21 & 128) != 0 ? null : null, (r21 & 256) != 0 ? null : null, (r21 & 512) == 0 ? null : null);
        return routeCard;
    }

    public static /* synthetic */ void connect$default(DashSession dashSession, String str, Network network, int i, Object obj) {
        if ((i & 2) != 0) {
            network = null;
        }
        dashSession.connect(str, network);
    }

    public final void connect(String ssid, Network network) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(ssid, "ssid");
        if (this._state.getValue() == DashState.IDLE || this._state.getValue() == DashState.ERROR) {
            Log.i(TAG, "connect() — ssid='" + ssid + "' network=" + network);
            launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$connect$1(this, ssid, network, null), 2, null);
            this.sessionJob = launch$default;
        }
    }

    public final void startStreaming() {
        if (this._state.getValue() != DashState.READY) {
            return;
        }
        this._state.setValue(DashState.STREAMING);
        launchProjectionHeartbeat();
        launchRouteCardKeepAlive();
        launchNavInfo();
        launchMediaInfo();
    }

    public final void sendRtp(byte[] packet) {
        Intrinsics.checkNotNullParameter(packet, "packet");
        DashSocket dashSocket = this.socket;
        if (dashSocket != null) {
            dashSocket.sendRtp(packet);
        }
    }

    public final void updateRouteCard(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        String str = name;
        if (StringsKt.isBlank(str)) {
            str = HOSTNAME;
        }
        this.destinationName = str;
        this.navActive = false;
        if (this._state.getValue() == DashState.READY || this._state.getValue() == DashState.STREAMING) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$updateRouteCard$2(this, null), 2, null);
        }
    }

    public final void disconnect() {
        Job job = this.sessionJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.sessionJob = null;
        Job job2 = this.rxJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        Job job3 = this.projHbJob;
        if (job3 != null) {
            Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
        }
        Job job4 = this.routeCardJob;
        if (job4 != null) {
            Job.DefaultImpls.cancel$default(job4, (CancellationException) null, 1, (Object) null);
        }
        Job job5 = this.heartbeatJob;
        if (job5 != null) {
            Job.DefaultImpls.cancel$default(job5, (CancellationException) null, 1, (Object) null);
        }
        Job job6 = this.phoneStatusJob;
        if (job6 != null) {
            Job.DefaultImpls.cancel$default(job6, (CancellationException) null, 1, (Object) null);
        }
        Job job7 = this.navInfoJob;
        if (job7 != null) {
            Job.DefaultImpls.cancel$default(job7, (CancellationException) null, 1, (Object) null);
        }
        Job job8 = this.mediaInfoJob;
        if (job8 != null) {
            Job.DefaultImpls.cancel$default(job8, (CancellationException) null, 1, (Object) null);
        }
        this.navActive = false;
        DashSocket dashSocket = this.socket;
        if (dashSocket != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                DashSession dashSession = this;
                dashSocket.send(DashCommands.INSTANCE.projectionStop());
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            try {
                Result.Companion companion3 = Result.INSTANCE;
                DashSession dashSession2 = this;
                dashSocket.send(DashCommands.INSTANCE.projectionOff());
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th2));
            }
            dashSocket.close();
        }
        this.socket = null;
        this._state.setValue(DashState.IDLE);
        Log.i(TAG, "Disconnected");
    }

    /*  JADX ERROR: Types fix failed
        java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to apply debug info
    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnoreUnknown(TypeUpdate.java:74)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:137)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:133)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.searchAndApplyVarDebugInfo(DebugInfoApplyVisitor.java:75)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.lambda$applyDebugInfo$0(DebugInfoApplyVisitor.java:68)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.applyDebugInfo(DebugInfoApplyVisitor.java:68)
    	at jadx.core.dex.visitors.debuginfo.DebugInfoApplyVisitor.visit(DebugInfoApplyVisitor.java:55)
     */
    /* JADX WARN: Not initialized variable reg: 10, insn: 0x008d: MOVE (r8 I:??[OBJECT, ARRAY]) = (r10 I:??[OBJECT, ARRAY] A[D('network' android.net.Network)]), block:B:89:0x008d */
    /* JADX WARN: Not initialized variable reg: 11, insn: 0x008e: MOVE (r10 I:??[OBJECT, ARRAY] A[D('network' android.net.Network)]) = (r11 I:??[OBJECT, ARRAY] A[D('ssid' java.lang.String)]), block:B:89:0x008d */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x0153 -> B:29:0x0156). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0108 -> B:51:0x010f). Please report as a decompilation issue!!! */
    public final java.lang.Object runSession(java.lang.String r19, android.net.Network r20, kotlin.coroutines.Continuation<? super kotlin.Unit> r21) {
        /*
            Method dump skipped, instructions count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.wayfindr.dash.DashSession.runSession(java.lang.String, android.net.Network, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object runSession$default(DashSession dashSession, String str, Network network, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            network = null;
        }
        return dashSession.runSession(str, network, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0134 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x011f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x00ea -> B:23:0x00ed). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object enterNavMode(DashSocket sock, Continuation<? super Unit> continuation) {
        DashSession$enterNavMode$1 dashSession$enterNavMode$1;
        DashSession dashSession;
        int i;
        int i2;
        byte[] routeCard;
        int i3;
        byte[] routeCard2;
        DashSocket sock2 = sock;
        if (continuation instanceof DashSession$enterNavMode$1) {
            dashSession$enterNavMode$1 = (DashSession$enterNavMode$1) continuation;
            if ((dashSession$enterNavMode$1.label & Integer.MIN_VALUE) != 0) {
                dashSession$enterNavMode$1.label -= Integer.MIN_VALUE;
                dashSession = this;
                Object $result = dashSession$enterNavMode$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i4 = 1;
                switch (dashSession$enterNavMode$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        sock2.send(DashCommands.INSTANCE.navContext());
                        dashSession$enterNavMode$1.L$0 = sock2;
                        dashSession$enterNavMode$1.label = 1;
                        if (DelayKt.delay(40L, dashSession$enterNavMode$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        sock2.send(DashCommands.INSTANCE.emptyLists());
                        dashSession$enterNavMode$1.L$0 = sock2;
                        dashSession$enterNavMode$1.label = 2;
                        if (DelayKt.delay(40L, dashSession$enterNavMode$1) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        i = 0;
                        i2 = 4;
                        if (i < i2) {
                            int i5 = i;
                            routeCard = DashCommands.INSTANCE.routeCard(dashSession.destinationName, (r21 & 2) != 0 ? false : false, (r21 & 4) != 0 ? null : null, (r21 & 8) != 0 ? null : null, (r21 & 16) != 0 ? null : null, (r21 & 32) != 0 ? null : null, (r21 & 64) != 0 ? null : null, (r21 & 128) != 0 ? null : null, (r21 & 256) != 0 ? null : null, (r21 & 512) == 0 ? null : null);
                            sock2.send(routeCard);
                            long j = i5 < i4 ? 100L : 500L;
                            dashSession$enterNavMode$1.L$0 = sock2;
                            dashSession$enterNavMode$1.I$0 = i2;
                            dashSession$enterNavMode$1.I$1 = i;
                            dashSession$enterNavMode$1.I$2 = i5;
                            dashSession$enterNavMode$1.I$3 = 0;
                            i3 = i4;
                            dashSession$enterNavMode$1.label = 3;
                            if (DelayKt.delay(j, dashSession$enterNavMode$1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                            i++;
                            i4 = i3;
                            if (i < i2) {
                                sock2.send(DashCommands.INSTANCE.projectionFrame());
                                dashSession$enterNavMode$1.L$0 = sock2;
                                dashSession$enterNavMode$1.label = 4;
                                if (DelayKt.delay(60L, dashSession$enterNavMode$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                sock2.send(DashCommands.INSTANCE.navPlaceholder());
                                dashSession$enterNavMode$1.L$0 = sock2;
                                dashSession$enterNavMode$1.label = 5;
                                if (DelayKt.delay(10L, dashSession$enterNavMode$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                sock2.send(DashCommands.INSTANCE.navStart());
                                dashSession$enterNavMode$1.L$0 = sock2;
                                dashSession$enterNavMode$1.label = 6;
                                if (DelayKt.delay(40L, dashSession$enterNavMode$1) == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                routeCard2 = DashCommands.INSTANCE.routeCard(dashSession.destinationName, (r21 & 2) != 0 ? false : true, (r21 & 4) != 0 ? null : null, (r21 & 8) != 0 ? null : null, (r21 & 16) != 0 ? null : null, (r21 & 32) != 0 ? null : null, (r21 & 64) != 0 ? null : null, (r21 & 128) != 0 ? null : null, (r21 & 256) != 0 ? null : null, (r21 & 512) == 0 ? null : null);
                                sock2.send(routeCard2);
                                Log.i(TAG, "Nav mode kick sent");
                                return Unit.INSTANCE;
                            }
                        }
                    case 1:
                        sock2 = (DashSocket) dashSession$enterNavMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        sock2.send(DashCommands.INSTANCE.emptyLists());
                        dashSession$enterNavMode$1.L$0 = sock2;
                        dashSession$enterNavMode$1.label = 2;
                        if (DelayKt.delay(40L, dashSession$enterNavMode$1) == coroutine_suspended) {
                        }
                        i = 0;
                        i2 = 4;
                        if (i < i2) {
                        }
                        break;
                    case 2:
                        sock2 = (DashSocket) dashSession$enterNavMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        i = 0;
                        i2 = 4;
                        if (i < i2) {
                        }
                        break;
                    case 3:
                        int i6 = dashSession$enterNavMode$1.I$3;
                        int i7 = dashSession$enterNavMode$1.I$2;
                        i = dashSession$enterNavMode$1.I$1;
                        i2 = dashSession$enterNavMode$1.I$0;
                        sock2 = (DashSocket) dashSession$enterNavMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        i3 = 1;
                        i++;
                        i4 = i3;
                        if (i < i2) {
                        }
                        break;
                    case 4:
                        sock2 = (DashSocket) dashSession$enterNavMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        sock2.send(DashCommands.INSTANCE.navPlaceholder());
                        dashSession$enterNavMode$1.L$0 = sock2;
                        dashSession$enterNavMode$1.label = 5;
                        if (DelayKt.delay(10L, dashSession$enterNavMode$1) == coroutine_suspended) {
                        }
                        sock2.send(DashCommands.INSTANCE.navStart());
                        dashSession$enterNavMode$1.L$0 = sock2;
                        dashSession$enterNavMode$1.label = 6;
                        if (DelayKt.delay(40L, dashSession$enterNavMode$1) == coroutine_suspended) {
                        }
                        routeCard2 = DashCommands.INSTANCE.routeCard(dashSession.destinationName, (r21 & 2) != 0 ? false : true, (r21 & 4) != 0 ? null : null, (r21 & 8) != 0 ? null : null, (r21 & 16) != 0 ? null : null, (r21 & 32) != 0 ? null : null, (r21 & 64) != 0 ? null : null, (r21 & 128) != 0 ? null : null, (r21 & 256) != 0 ? null : null, (r21 & 512) == 0 ? null : null);
                        sock2.send(routeCard2);
                        Log.i(TAG, "Nav mode kick sent");
                        return Unit.INSTANCE;
                    case 5:
                        sock2 = (DashSocket) dashSession$enterNavMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        sock2.send(DashCommands.INSTANCE.navStart());
                        dashSession$enterNavMode$1.L$0 = sock2;
                        dashSession$enterNavMode$1.label = 6;
                        if (DelayKt.delay(40L, dashSession$enterNavMode$1) == coroutine_suspended) {
                        }
                        routeCard2 = DashCommands.INSTANCE.routeCard(dashSession.destinationName, (r21 & 2) != 0 ? false : true, (r21 & 4) != 0 ? null : null, (r21 & 8) != 0 ? null : null, (r21 & 16) != 0 ? null : null, (r21 & 32) != 0 ? null : null, (r21 & 64) != 0 ? null : null, (r21 & 128) != 0 ? null : null, (r21 & 256) != 0 ? null : null, (r21 & 512) == 0 ? null : null);
                        sock2.send(routeCard2);
                        Log.i(TAG, "Nav mode kick sent");
                        return Unit.INSTANCE;
                    case 6:
                        sock2 = (DashSocket) dashSession$enterNavMode$1.L$0;
                        ResultKt.throwOnFailure($result);
                        routeCard2 = DashCommands.INSTANCE.routeCard(dashSession.destinationName, (r21 & 2) != 0 ? false : true, (r21 & 4) != 0 ? null : null, (r21 & 8) != 0 ? null : null, (r21 & 16) != 0 ? null : null, (r21 & 32) != 0 ? null : null, (r21 & 64) != 0 ? null : null, (r21 & 128) != 0 ? null : null, (r21 & 256) != 0 ? null : null, (r21 & 512) == 0 ? null : null);
                        sock2.send(routeCard2);
                        Log.i(TAG, "Nav mode kick sent");
                        return Unit.INSTANCE;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        dashSession = this;
        dashSession$enterNavMode$1 = new DashSession$enterNavMode$1(dashSession, continuation);
        Object $result2 = dashSession$enterNavMode$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i42 = 1;
        switch (dashSession$enterNavMode$1.label) {
        }
    }

    private final void launchReceiveLoop(DashSocket sock) {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$launchReceiveLoop$1(sock, this, null), 2, null);
        this.rxJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0345, code lost:
    
        if (r9 == null) goto L125;
     */
    /* JADX WARN: Removed duplicated region for block: B:107:0x01a9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x006d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:176:? A[LOOP:1: B:157:0x0033->B:176:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x01b6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void dispatchIncoming(byte[] pkt, DashSocket sock) {
        boolean z;
        String str;
        String str2;
        boolean z2;
        Iterable iterable;
        boolean z3;
        List<Tlv> parseIncoming = K1GPacket.INSTANCE.parseIncoming(pkt);
        int i = 6;
        int i2 = 9;
        boolean z4 = true;
        if (!parseIncoming.isEmpty()) {
            List<Tlv> list = parseIncoming;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator<T> it = list.iterator();
                while (true) {
                    if (it.hasNext()) {
                        Tlv tlv = (Tlv) it.next();
                        if (tlv.getType() == 9 && (tlv.getSub() == 6 || tlv.getSub() == 4)) {
                            Byte firstOrNull = ArraysKt.firstOrNull(tlv.getValue());
                            if (firstOrNull != null && firstOrNull.byteValue() == 85) {
                                z3 = true;
                                if (z3) {
                                    iterable = null;
                                    break;
                                }
                            }
                        }
                        z3 = false;
                        if (z3) {
                        }
                    } else {
                        iterable = 1;
                        break;
                    }
                }
            } else {
                iterable = 1;
            }
            if (iterable != null) {
                z = true;
                if (!z) {
                    Dbg dbg = Dbg.INSTANCE;
                    if (BuildConfig.DEBUG) {
                        Log.i(TAG, "RX RAW (" + pkt.length + "B): " + toHexFull(pkt));
                    }
                }
                for (Tlv tlv2 : parseIncoming) {
                    if (tlv2.getType() == 7) {
                        DashAuth dashAuth = this.auth;
                        AuthEvent ingest = dashAuth != null ? dashAuth.ingest(tlv2) : null;
                        if (ingest instanceof AuthEvent.SendKey) {
                            Log.i(TAG, "Got RSA pubkey — sending q3c.d");
                            RideDiagnostics.INSTANCE.log("auth", "RSA pubkey received → sending session key (q3c.d)");
                            sock.send(((AuthEvent.SendKey) ingest).getPacket());
                        } else if (Intrinsics.areEqual(ingest, AuthEvent.Confirmed.INSTANCE)) {
                            this.authConfirmed = z4;
                        } else if (Intrinsics.areEqual(ingest, AuthEvent.Rejected.INSTANCE)) {
                            this.authRetries += z4 ? 1 : 0;
                            Log.w(TAG, "Auth rejected — retry #" + this.authRetries);
                            RideDiagnostics.INSTANCE.log("auth", "REJECTED — retry #" + this.authRetries);
                            DashAuth dashAuth2 = this.auth;
                            if (dashAuth2 != null) {
                                dashAuth2.reset();
                            }
                            if (this.authRetries <= 5) {
                                sock.send(DashCommands.INSTANCE.authRequest());
                            }
                        }
                    } else {
                        if (tlv2.getType() == i2 && tlv2.getSub() == i) {
                            Byte firstOrNull2 = ArraysKt.firstOrNull(tlv2.getValue());
                            if ((firstOrNull2 == null || firstOrNull2.byteValue() != 85) ? false : z4 ? 1 : 0) {
                                if (!this.loggedFirstAck) {
                                    this.loggedFirstAck = z4;
                                    RideDiagnostics.INSTANCE.log("dash", "dash DECODED first IDR (09 06 55) — video accepted ✓");
                                }
                                sock.send(DashCommands.INSTANCE.frameDecodedIdr());
                            }
                        }
                        if (tlv2.getType() == i2 && tlv2.getSub() == 4) {
                            Byte firstOrNull3 = ArraysKt.firstOrNull(tlv2.getValue());
                            if (firstOrNull3 != null && firstOrNull3.byteValue() == 85) {
                                z2 = z4 ? 1 : 0;
                                if (!z2) {
                                    sock.send(DashCommands.INSTANCE.frameDecodedP());
                                }
                            }
                            z2 = false;
                            if (!z2) {
                            }
                        }
                        if (tlv2.getType() == i2 && tlv2.getSub() == 0) {
                            if (!(tlv2.getValue().length == 0 ? z4 ? 1 : 0 : false)) {
                                byte last = ArraysKt.last(tlv2.getValue());
                                String num = Integer.toString(last & UByte.MAX_VALUE, CharsKt.checkRadix(16));
                                Intrinsics.checkNotNullExpressionValue(num, "toString(...)");
                                String upperCase = num.toUpperCase(Locale.ROOT);
                                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                                Log.i(TAG, "JOYSTICK 09 00 → code 0x" + upperCase + "  full=" + toHexFull(tlv2.getValue()));
                                this.lastJoyRxMs = System.currentTimeMillis();
                                RideDiagnostics.INSTANCE.log("joy raw", "09 00 value=" + toHexFull(tlv2.getValue()));
                                sock.send(DashCommands.INSTANCE.buttonAck(last));
                                if (last == 34) {
                                    if (this.gpsFix) {
                                        sock.send(DashCommands.INSTANCE.gpsAvailable());
                                    }
                                    RideDiagnostics.INSTANCE.log("dash", "compass opened (gpsFix=" + this.gpsFix + ", heading=" + this.compassHeading + "°)");
                                }
                                BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getMain(), null, new DashSession$dispatchIncoming$2(this, last, null), 2, null);
                                i = 6;
                                i2 = 9;
                                z4 = true;
                            }
                        }
                        if (tlv2.getType() == 15) {
                            DashAuth dashAuth3 = this.auth;
                            byte[] sessionKey = dashAuth3 != null ? dashAuth3.getSessionKey() : null;
                            byte[] aesDecryptCbc = sessionKey != null ? aesDecryptCbc(tlv2.getValue(), sessionKey) : null;
                            Integer valueOf = Integer.valueOf(tlv2.getSub());
                            Integer valueOf2 = Integer.valueOf(tlv2.getValue().length);
                            String hexFull = toHexFull(tlv2.getValue());
                            if (aesDecryptCbc == null || (str = toHexFull(aesDecryptCbc)) == null) {
                                str = "<key=" + (sessionKey != null) + "; decrypt failed>";
                            }
                            String format = String.format("DASH TELEMETRY 0F sub=0x%02X enc(%dB)=%s  dec=%s", Arrays.copyOf(new Object[]{valueOf, valueOf2, hexFull, str}, 4));
                            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                            Log.i(TAG, format);
                            int type = tlv2.getType();
                            int sub = tlv2.getSub();
                            if (aesDecryptCbc != null) {
                                str2 = "dec=" + toHexFull(aesDecryptCbc);
                            }
                            str2 = "enc(" + tlv2.getValue().length + "B)=" + toHexFull(tlv2.getValue()) + " (decrypt failed, key=" + (sessionKey != null) + ")";
                            logDashData(type, sub, str2);
                            i = 6;
                            i2 = 9;
                            z4 = true;
                        } else if (tlv2.getType() == 12) {
                            String format2 = String.format("DASH TELEMETRY 0C sub=0x%02X (%dB) val=%s", Arrays.copyOf(new Object[]{Integer.valueOf(tlv2.getSub()), Integer.valueOf(tlv2.getValue().length), toHexFull(tlv2.getValue())}, 3));
                            Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                            Log.i(TAG, format2);
                            logDashData(tlv2.getType(), tlv2.getSub(), "val=" + toHexFull(tlv2.getValue()));
                            i = 6;
                            i2 = 9;
                            z4 = true;
                        } else {
                            String format3 = String.format("DASH EVENT type=0x%02X sub=0x%02X (%dB) val=%s", Arrays.copyOf(new Object[]{Integer.valueOf(tlv2.getType()), Integer.valueOf(tlv2.getSub()), Integer.valueOf(tlv2.getValue().length), toHexFull(tlv2.getValue())}, 4));
                            Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                            Log.i(TAG, format3);
                            logDashData(tlv2.getType(), tlv2.getSub(), "val=" + toHexFull(tlv2.getValue()));
                            if (System.currentTimeMillis() - this.lastJoyRxMs < 3000) {
                                RideDiagnostics rideDiagnostics = RideDiagnostics.INSTANCE;
                                String format4 = String.format("near-joystick event %02X %02X val=%s", Arrays.copyOf(new Object[]{Integer.valueOf(tlv2.getType()), Integer.valueOf(tlv2.getSub()), toHexFull(tlv2.getValue())}, 3));
                                Intrinsics.checkNotNullExpressionValue(format4, "format(...)");
                                rideDiagnostics.log("joy raw", format4);
                                i = 6;
                                i2 = 9;
                                z4 = true;
                            } else {
                                i = 6;
                                i2 = 9;
                                z4 = true;
                            }
                        }
                    }
                }
            }
        }
        z = false;
        if (!z) {
        }
        while (r6.hasNext()) {
        }
    }

    public final Function0<PhoneStatus> getPhoneStatusProvider() {
        return this.phoneStatusProvider;
    }

    public final void setPhoneStatusProvider(Function0<PhoneStatus> function0) {
        this.phoneStatusProvider = function0;
    }

    public final PhoneStatus getLatestPhoneStatus() {
        return this.latestPhoneStatus;
    }

    public final void setLatestPhoneStatus(PhoneStatus phoneStatus) {
        this.latestPhoneStatus = phoneStatus;
    }

    private final void launchPhoneStatus() {
        Job launch$default;
        Job job = this.phoneStatusJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$launchPhoneStatus$1(this, null), 2, null);
        this.phoneStatusJob = launch$default;
    }

    private final void launchStatusHeartbeat(DashSocket sock) {
        Job launch$default;
        Job job = this.heartbeatJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launchPhoneStatus();
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$launchStatusHeartbeat$1(this, sock, null), 2, null);
        this.heartbeatJob = launch$default;
    }

    private final void launchProjectionHeartbeat() {
        Job launch$default;
        Job job = this.projHbJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$launchProjectionHeartbeat$1(this, null), 2, null);
        this.projHbJob = launch$default;
    }

    private final void launchRouteCardKeepAlive() {
        Job launch$default;
        Job job = this.routeCardJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$launchRouteCardKeepAlive$1(this, null), 2, null);
        this.routeCardJob = launch$default;
    }

    private final void launchNavInfo() {
        Job launch$default;
        Job job = this.navInfoJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$launchNavInfo$1(this, null), 2, null);
        this.navInfoJob = launch$default;
    }

    private final void launchMediaInfo() {
        Job launch$default;
        Job job = this.mediaInfoJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new DashSession$launchMediaInfo$1(this, null), 2, null);
        this.mediaInfoJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fail(String msg) {
        Log.e(TAG, "ERROR — " + msg);
        RideDiagnostics.INSTANCE.log("error", "session fail: " + msg);
        Job job = this.rxJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Job job2 = this.heartbeatJob;
        if (job2 != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        Job job3 = this.phoneStatusJob;
        if (job3 != null) {
            Job.DefaultImpls.cancel$default(job3, (CancellationException) null, 1, (Object) null);
        }
        DashSocket dashSocket = this.socket;
        if (dashSocket != null) {
            dashSocket.close();
        }
        this.socket = null;
        this._state.setValue(DashState.ERROR);
        Function1<? super String, Unit> function1 = this.onError;
        if (function1 != null) {
            function1.invoke(msg);
        }
    }

    private final String toHexFull(byte[] $this$toHexFull) {
        return ArraysKt.joinToString$default($this$toHexFull, (CharSequence) " ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.example.wayfindr.dash.DashSession$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DashSession.toHexFull$lambda$14(((Byte) obj).byteValue());
            }
        }, 30, (Object) null);
    }

    static final CharSequence toHexFull$lambda$14(byte it) {
        String format = String.format("%02X", Arrays.copyOf(new Object[]{Byte.valueOf(it)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final byte[] aesDecryptCbc(byte[] ivAndCt, byte[] key) {
        Object m8392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            DashSession dashSession = this;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (ivAndCt.length <= 16) {
            return null;
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, new SecretKeySpec(key, "AES"), new IvParameterSpec(ArraysKt.copyOfRange(ivAndCt, 0, 16)));
        m8392constructorimpl = Result.m8392constructorimpl(cipher.doFinal(ArraysKt.copyOfRange(ivAndCt, 16, ivAndCt.length)));
        return (byte[]) (Result.m8398isFailureimpl(m8392constructorimpl) ? null : m8392constructorimpl);
    }
}
