package com.example.wayfindr.dash;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.TransportInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiNetworkSpecifier;
import android.os.PatternMatcher;
import android.util.Log;
import androidx.autofill.HintConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: DashWifiManager.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\b\u0007\u0018\u0000 72\u00020\u0001:\u00017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\"\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020!2\b\b\u0002\u0010.\u001a\u00020!2\b\b\u0002\u0010/\u001a\u00020\rJ\u0012\u00100\u001a\u0004\u0018\u00010!2\u0006\u00101\u001a\u00020!H\u0007J\u0006\u00102\u001a\u00020'J\b\u00103\u001a\u00020'H\u0002J\b\u00104\u001a\u00020'H\u0002J\u0010\u00105\u001a\u00020!2\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\b\u00106\u001a\u00020'H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\"\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R(\u0010%\u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020'\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+¨\u00068"}, d2 = {"Lcom/example/wayfindr/dash/DashWifiManager;", "", "context", "Landroid/content/Context;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;)V", "cm", "Landroid/net/ConnectivityManager;", "wifi", "Landroid/net/wifi/WifiManager;", "isWifiEnabled", "", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/dash/WifiState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "value", "Landroid/net/Network;", "network", "getNetwork", "()Landroid/net/Network;", "networkCallback", "Landroid/net/ConnectivityManager$NetworkCallback;", "reconnectJob", "Lkotlinx/coroutines/Job;", "everConnected", "wantConnected", "pendingSsid", "", "pendingPassword", "pendingPrefix", "resolvedSsid", "onSsidResolved", "Lkotlin/Function1;", "", "getOnSsidResolved", "()Lkotlin/jvm/functions/Function1;", "setOnSsidResolved", "(Lkotlin/jvm/functions/Function1;)V", "connect", "ssid", HintConstants.AUTOFILL_HINT_PASSWORD, "prefixMatch", "findDashSsid", "prefix", "disconnect", "requestNetwork", "scheduleReconnect", "resolveSsid", "release", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashWifiManager {
    private static final int CONNECT_TIMEOUT = 30000;
    private static final long RECONNECT_DELAY = 2000;
    private static final int RECONNECT_TIMEOUT = 10000;
    private static final String TAG = "DashWifiManager";
    private static final String WifiManagerUnknownSsid = "<unknown ssid>";
    private final MutableStateFlow<WifiState> _state;
    private final ConnectivityManager cm;
    private final Context context;
    private volatile boolean everConnected;
    private volatile Network network;
    private ConnectivityManager.NetworkCallback networkCallback;
    private Function1<? super String, Unit> onSsidResolved;
    private String pendingPassword;
    private boolean pendingPrefix;
    private String pendingSsid;
    private Job reconnectJob;
    private String resolvedSsid;
    private final CoroutineScope scope;
    private final StateFlow<WifiState> state;
    private volatile boolean wantConnected;
    private final WifiManager wifi;
    public static final int $stable = 8;

    public DashWifiManager(Context context, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.context = context;
        this.scope = scope;
        Object systemService = this.context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.cm = (ConnectivityManager) systemService;
        Object systemService2 = this.context.getApplicationContext().getSystemService("wifi");
        Intrinsics.checkNotNull(systemService2, "null cannot be cast to non-null type android.net.wifi.WifiManager");
        this.wifi = (WifiManager) systemService2;
        this._state = StateFlowKt.MutableStateFlow(new WifiState(null, null, null, 7, null));
        this.state = FlowKt.asStateFlow(this._state);
        this.pendingSsid = "";
        this.pendingPassword = "";
    }

    public final boolean isWifiEnabled() {
        return this.wifi.isWifiEnabled();
    }

    public final StateFlow<WifiState> getState() {
        return this.state;
    }

    public final Network getNetwork() {
        return this.network;
    }

    public final Function1<String, Unit> getOnSsidResolved() {
        return this.onSsidResolved;
    }

    public final void setOnSsidResolved(Function1<? super String, Unit> function1) {
        this.onSsidResolved = function1;
    }

    public static /* synthetic */ void connect$default(DashWifiManager dashWifiManager, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        if ((i & 4) != 0) {
            z = false;
        }
        dashWifiManager.connect(str, str2, z);
    }

    public final void connect(String ssid, String password, boolean prefixMatch) {
        Intrinsics.checkNotNullParameter(ssid, "ssid");
        Intrinsics.checkNotNullParameter(password, "password");
        this.wantConnected = true;
        this.everConnected = false;
        this.pendingSsid = ssid;
        this.pendingPassword = password;
        this.pendingPrefix = prefixMatch;
        this.resolvedSsid = null;
        requestNetwork();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x007a A[Catch: Exception -> 0x00e0, TryCatch #0 {Exception -> 0x00e0, blocks: (B:3:0x000c, B:4:0x003b, B:6:0x0041, B:8:0x0058, B:10:0x0067, B:14:0x007a, B:21:0x0081, B:22:0x0092, B:24:0x0098), top: B:2:0x000c }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x007e A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final String findDashSsid(String prefix) {
        String str;
        Object obj;
        WifiManager wifi;
        String str2;
        Intrinsics.checkNotNullParameter(prefix, "prefix");
        try {
            Object systemService = this.context.getApplicationContext().getSystemService("wifi");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.wifi.WifiManager");
            WifiManager wifi2 = (WifiManager) systemService;
            Iterable scanResults = wifi2.getScanResults();
            Intrinsics.checkNotNullExpressionValue(scanResults, "getScanResults(...)");
            Collection arrayList = new ArrayList();
            Iterator it = scanResults.iterator();
            while (it.hasNext()) {
                String str3 = ((ScanResult) it.next()).SSID;
                if (str3 != null) {
                    wifi = wifi2;
                    str2 = StringsKt.trim(str3, Typography.quote);
                    if (str2 != null && !StringsKt.isBlank(str2)) {
                        if (str2 == null) {
                            arrayList.add(str2);
                        }
                        wifi2 = wifi;
                    }
                } else {
                    wifi = wifi2;
                }
                str2 = null;
                if (str2 == null) {
                }
                wifi2 = wifi;
            }
            boolean z = false;
            Iterator it2 = ((List) arrayList).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    str = null;
                    obj = null;
                    break;
                }
                obj = it2.next();
                boolean z2 = z;
                str = null;
                try {
                    if (StringsKt.startsWith$default((String) obj, prefix, z2, 2, (Object) null)) {
                        break;
                    }
                    z = z2;
                } catch (Exception e) {
                    e = e;
                    Log.w(TAG, "Scan lookup failed: " + e.getMessage());
                    return str;
                }
            }
            String str4 = (String) obj;
            Log.i(TAG, "Scan lookup for '" + prefix + "*' → " + (str4 == null ? "not found" : str4));
            return (String) obj;
        } catch (Exception e2) {
            e = e2;
            str = null;
        }
    }

    public final void disconnect() {
        Log.i(TAG, "Disconnect requested");
        this.wantConnected = false;
        Job job = this.reconnectJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        release();
        this._state.setValue(new WifiState(null, null, null, 7, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestNetwork() {
        release();
        Log.i(TAG, "Requesting WiFi: '" + this.pendingSsid + "' (" + (this.pendingPrefix ? "prefix" : "exact") + ", password=" + (StringsKt.isBlank(this.pendingPassword) ? "none" : "set") + ")");
        this._state.setValue(new WifiState(WifiConnStatus.REQUESTING, this.pendingSsid, null, 4, null));
        WifiNetworkSpecifier.Builder specBuilder = new WifiNetworkSpecifier.Builder();
        if (this.pendingPrefix) {
            specBuilder.setSsidPattern(new PatternMatcher(this.pendingSsid, 1));
        } else {
            specBuilder.setSsid(this.pendingSsid);
        }
        if (!StringsKt.isBlank(this.pendingPassword)) {
            specBuilder.setWpa2Passphrase(this.pendingPassword);
        }
        NetworkRequest request = new NetworkRequest.Builder().addTransportType(1).setNetworkSpecifier(specBuilder.build()).build();
        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() { // from class: com.example.wayfindr.dash.DashWifiManager$requestNetwork$cb$1
            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onAvailable(Network network) {
                ConnectivityManager.NetworkCallback networkCallback2;
                boolean z;
                Job job;
                String resolveSsid;
                MutableStateFlow mutableStateFlow;
                String str;
                Function1<String, Unit> onSsidResolved;
                Intrinsics.checkNotNullParameter(network, "network");
                networkCallback2 = DashWifiManager.this.networkCallback;
                if (networkCallback2 == this) {
                    z = DashWifiManager.this.wantConnected;
                    if (z) {
                        DashWifiManager.this.network = network;
                        DashWifiManager.this.everConnected = true;
                        job = DashWifiManager.this.reconnectJob;
                        if (job != null) {
                            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                        }
                        resolveSsid = DashWifiManager.this.resolveSsid(network);
                        String str2 = StringsKt.isBlank(resolveSsid) ? null : resolveSsid;
                        if (str2 == null) {
                            str2 = DashWifiManager.this.pendingSsid;
                        }
                        String resolved = str2;
                        Log.i("DashWifiManager", "WiFi connected ✓  ssid='" + resolved + "' network=" + network);
                        if (!StringsKt.isBlank(resolved)) {
                            str = DashWifiManager.this.pendingSsid;
                            if (!Intrinsics.areEqual(resolved, str) && (onSsidResolved = DashWifiManager.this.getOnSsidResolved()) != null) {
                                onSsidResolved.invoke(resolved);
                            }
                        }
                        mutableStateFlow = DashWifiManager.this._state;
                        mutableStateFlow.setValue(new WifiState(WifiConnStatus.CONNECTED, resolved, null, 4, null));
                    }
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onCapabilitiesChanged(Network network, NetworkCapabilities caps) {
                ConnectivityManager.NetworkCallback networkCallback2;
                boolean z;
                String str;
                MutableStateFlow mutableStateFlow;
                Intrinsics.checkNotNullParameter(network, "network");
                Intrinsics.checkNotNullParameter(caps, "caps");
                networkCallback2 = DashWifiManager.this.networkCallback;
                if (networkCallback2 == this) {
                    z = DashWifiManager.this.wantConnected;
                    if (!z) {
                        return;
                    }
                    TransportInfo transportInfo = caps.getTransportInfo();
                    WifiInfo info = transportInfo instanceof WifiInfo ? (WifiInfo) transportInfo : null;
                    if (info == null) {
                        return;
                    }
                    String ssid = info.getSSID();
                    if (ssid == null) {
                        ssid = "";
                    }
                    String ssid2 = StringsKt.trim(ssid, Typography.quote);
                    if (!StringsKt.isBlank(ssid2) && !Intrinsics.areEqual(ssid2, "<unknown ssid>")) {
                        str = DashWifiManager.this.resolvedSsid;
                        if (Intrinsics.areEqual(ssid2, str)) {
                            return;
                        }
                        DashWifiManager.this.resolvedSsid = ssid2;
                        DashWifiManager.this.network = network;
                        Log.i("DashWifiManager", "Resolved dash SSID: '" + ssid2 + "'");
                        Function1<String, Unit> onSsidResolved = DashWifiManager.this.getOnSsidResolved();
                        if (onSsidResolved != null) {
                            onSsidResolved.invoke(ssid2);
                        }
                        mutableStateFlow = DashWifiManager.this._state;
                        mutableStateFlow.setValue(new WifiState(WifiConnStatus.CONNECTED, ssid2, null, 4, null));
                    }
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onUnavailable() {
                ConnectivityManager.NetworkCallback networkCallback2;
                boolean z;
                boolean z2;
                MutableStateFlow mutableStateFlow;
                String str;
                String str2;
                boolean z3;
                MutableStateFlow mutableStateFlow2;
                String str3;
                networkCallback2 = DashWifiManager.this.networkCallback;
                if (networkCallback2 == this) {
                    z = DashWifiManager.this.wantConnected;
                    if (z) {
                        DashWifiManager.this.network = null;
                        z2 = DashWifiManager.this.everConnected;
                        if (z2) {
                            z3 = DashWifiManager.this.wantConnected;
                            if (z3) {
                                Log.w("DashWifiManager", "WiFi unavailable mid-session — dash AP missing, retrying in 2000ms");
                                mutableStateFlow2 = DashWifiManager.this._state;
                                WifiConnStatus wifiConnStatus = WifiConnStatus.REQUESTING;
                                str3 = DashWifiManager.this.pendingSsid;
                                mutableStateFlow2.setValue(new WifiState(wifiConnStatus, str3, "Gösterge Wi‑Fi bağlantısı kesildi; yeniden bağlanılıyor…"));
                                DashWifiManager.this.scheduleReconnect();
                                return;
                            }
                        }
                        Log.w("DashWifiManager", "WiFi unavailable — SSID not found or user declined");
                        mutableStateFlow = DashWifiManager.this._state;
                        WifiConnStatus wifiConnStatus2 = WifiConnStatus.ERROR;
                        str = DashWifiManager.this.pendingSsid;
                        str2 = DashWifiManager.this.pendingSsid;
                        mutableStateFlow.setValue(new WifiState(wifiConnStatus2, str, "“" + str2 + "” ağına bağlanılamadı. Ağı ve parolayı kontrol edin."));
                        DashWifiManager.this.wantConnected = false;
                    }
                }
            }

            @Override // android.net.ConnectivityManager.NetworkCallback
            public void onLost(Network network) {
                ConnectivityManager.NetworkCallback networkCallback2;
                boolean z;
                MutableStateFlow mutableStateFlow;
                String str;
                boolean z2;
                Intrinsics.checkNotNullParameter(network, "network");
                networkCallback2 = DashWifiManager.this.networkCallback;
                if (networkCallback2 == this) {
                    z = DashWifiManager.this.wantConnected;
                    if (!z || !Intrinsics.areEqual(DashWifiManager.this.getNetwork(), network)) {
                        return;
                    }
                    Log.w("DashWifiManager", "WiFi link lost — reconnecting in 2000ms");
                    DashWifiManager.this.network = null;
                    mutableStateFlow = DashWifiManager.this._state;
                    WifiConnStatus wifiConnStatus = WifiConnStatus.REQUESTING;
                    str = DashWifiManager.this.pendingSsid;
                    mutableStateFlow.setValue(new WifiState(wifiConnStatus, str, "Bağlantı kesildi; gösterge ağı yeniden aranıyor…"));
                    z2 = DashWifiManager.this.wantConnected;
                    if (z2) {
                        DashWifiManager.this.scheduleReconnect();
                    }
                }
            }
        };
        this.networkCallback = networkCallback;
        try {
            this.cm.requestNetwork(request, networkCallback, this.everConnected ? RECONNECT_TIMEOUT : CONNECT_TIMEOUT);
        } catch (Exception e) {
            Log.e(TAG, "requestNetwork threw: " + e.getMessage(), e);
            if (this.everConnected && this.wantConnected) {
                this._state.setValue(new WifiState(WifiConnStatus.REQUESTING, this.pendingSsid, "Gösterge bağlantısı yeniden deneniyor…"));
                scheduleReconnect();
            } else {
                this._state.setValue(new WifiState(WifiConnStatus.ERROR, this.pendingSsid, "Wi‑Fi bağlantı isteği başlatılamadı. Lütfen yeniden deneyin."));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleReconnect() {
        Job launch$default;
        Job job = this.reconnectJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new DashWifiManager$scheduleReconnect$1(this, null), 3, null);
        this.reconnectJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String resolveSsid(Network network) {
        NetworkCapabilities caps = this.cm.getNetworkCapabilities(network);
        if (caps == null) {
            return "";
        }
        TransportInfo transportInfo = caps.getTransportInfo();
        WifiInfo info = transportInfo instanceof WifiInfo ? (WifiInfo) transportInfo : null;
        if (info == null) {
            return "";
        }
        String ssid = info.getSSID();
        if (ssid == null) {
            ssid = "";
        }
        String trim = StringsKt.trim(ssid, Typography.quote);
        return Intrinsics.areEqual(trim, WifiManagerUnknownSsid) ? "" : trim;
    }

    private final void release() {
        Object m8392constructorimpl;
        ConnectivityManager.NetworkCallback networkCallback = this.networkCallback;
        if (networkCallback != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                this.cm.unregisterNetworkCallback(networkCallback);
                m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            Result.m8391boximpl(m8392constructorimpl);
        }
        this.networkCallback = null;
        this.network = null;
    }
}
