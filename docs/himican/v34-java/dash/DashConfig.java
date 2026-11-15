package com.example.wayfindr.dash;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.wayfindr.dash.nav.Router;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DashConfig.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u0002\n\u0002\b\u0014\b\u0007\u0018\u0000 T2\u00020\u0001:\u0001TB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010@\u001a\u00020AJ\u000e\u0010B\u001a\u00020A2\u0006\u0010C\u001a\u00020\u000bJ\u000e\u0010D\u001a\u00020A2\u0006\u0010E\u001a\u00020\u0014J\u000e\u0010F\u001a\u00020A2\u0006\u0010G\u001a\u00020\u0018J\u000e\u0010H\u001a\u00020A2\u0006\u0010I\u001a\u00020\u000bJ\u000e\u0010J\u001a\u00020A2\u0006\u0010K\u001a\u00020\u000bJ\u000e\u0010L\u001a\u00020A2\u0006\u0010M\u001a\u00020\u000bJ\u000e\u0010N\u001a\u00020A2\u0006\u0010O\u001a\u00020\u000bJ\u000e\u0010P\u001a\u00020A2\u0006\u0010O\u001a\u00020\u000bJ\u000e\u0010Q\u001a\u00020A2\u0006\u0010O\u001a\u00020\u000bJ\u000e\u0010R\u001a\u00020A2\u0006\u0010O\u001a\u00020\u000bJ\u000e\u0010S\u001a\u00020A2\u0006\u0010O\u001a\u00020\u000bR\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u000fR\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000fR\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u000fR\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u000fR\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b)\u0010\u000fR\u0014\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010+\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u000fR\u0014\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000b0\r¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u000fR$\u00102\u001a\u0002012\u0006\u00100\u001a\u0002018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\u0002012\u0006\u00100\u001a\u0002018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b8\u00104\"\u0004\b9\u00106R$\u0010:\u001a\u0002012\u0006\u00100\u001a\u0002018F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b;\u00104\"\u0004\b<\u00106R\u0011\u0010=\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b>\u0010?¨\u0006U"}, d2 = {"Lcom/example/wayfindr/dash/DashConfig;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "_mapDark", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "mapDarkMode", "Lkotlinx/coroutines/flow/StateFlow;", "getMapDarkMode", "()Lkotlinx/coroutines/flow/StateFlow;", "_mapDarkAuto", "mapDarkAuto", "getMapDarkAuto", "_routingSource", "Lcom/example/wayfindr/dash/RoutingSource;", "routingSource", "getRoutingSource", "_mapTheme", "Lcom/example/wayfindr/dash/MapTheme;", "mapTheme", "getMapTheme", "_autoConnectOnStart", "autoConnectOnStart", "getAutoConnectOnStart", "_invertZoom", "invertZoom", "getInvertZoom", "_sharperMap", "sharperMap", "getSharperMap", "_highQualityVideo", "highQualityVideo", "getHighQualityVideo", "_pickerDoubleTap", "pickerDoubleTap", "getPickerDoubleTap", "_showJoystickTrail", "showJoystickTrail", "getShowJoystickTrail", "_showPhoneBattery", "showPhoneBattery", "getShowPhoneBattery", "v", "", "ssidPrefix", "getSsidPrefix", "()Ljava/lang/String;", "setSsidPrefix", "(Ljava/lang/String;)V", DashConfig.KEY_SSID, "getSsid", "setSsid", "password", "getPassword", "setPassword", "needsDiscovery", "getNeedsDiscovery", "()Z", "forgetDash", "", "setMapDarkMode", "dark", "setRoutingSource", "choice", "setMapTheme", "theme", "setAutoConnectOnStart", "enabled", "setMapDarkAuto", "auto", "setInvertZoom", "invert", "setSharperMap", DebugKt.DEBUG_PROPERTY_VALUE_ON, "setHighQualityVideo", "setPickerDoubleTap", "setShowJoystickTrail", "setShowPhoneBattery", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DashConfig {
    public static final String DEFAULT_PASSWORD = "12345678";
    public static final String DEFAULT_PREFIX = "RE_";
    private static final String KEY_AUTO_CONNECT_ON_START = "auto_connect_on_start";
    private static final String KEY_FREE_MAPS = "free_maps";
    private static final String KEY_FREE_ROUTING = "free_routing";
    private static final String KEY_HQ_VIDEO = "hq_video";
    private static final String KEY_INVERT_ZOOM = "invert_zoom";
    private static final String KEY_JOY_TRAIL = "joystick_trail";
    private static final String KEY_MAP_DARK = "map_dark";
    private static final String KEY_MAP_DARK_AUTO = "map_dark_auto";
    private static final String KEY_MAP_SOURCE = "map_source";
    private static final String KEY_MAP_THEME = "map_theme";
    private static final String KEY_OFFLINE_MAP = "offline_map_mbtiles";
    private static final String KEY_OFFLINE_MAP_FILE = "offline_map_file";
    private static final String KEY_OFFLINE_ROUTING = "offline_routing_brouter";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE_BATTERY = "phone_battery";
    private static final String KEY_PICKER_DOUBLE_TAP = "picker_double_tap";
    private static final String KEY_PREFIX = "ssid_prefix";
    private static final String KEY_ROUTING_SOURCE = "routing_source";
    private static final String KEY_SHARPER_MAP = "sharper_map";
    private static final String KEY_SSID = "ssid";
    private static volatile DashConfig instance;
    private final MutableStateFlow<Boolean> _autoConnectOnStart;
    private final MutableStateFlow<Boolean> _highQualityVideo;
    private final MutableStateFlow<Boolean> _invertZoom;
    private final MutableStateFlow<Boolean> _mapDark;
    private final MutableStateFlow<Boolean> _mapDarkAuto;
    private final MutableStateFlow<MapTheme> _mapTheme;
    private final MutableStateFlow<Boolean> _pickerDoubleTap;
    private final MutableStateFlow<RoutingSource> _routingSource;
    private final MutableStateFlow<Boolean> _sharperMap;
    private final MutableStateFlow<Boolean> _showJoystickTrail;
    private final MutableStateFlow<Boolean> _showPhoneBattery;
    private final StateFlow<Boolean> autoConnectOnStart;
    private final StateFlow<Boolean> highQualityVideo;
    private final StateFlow<Boolean> invertZoom;
    private final StateFlow<Boolean> mapDarkAuto;
    private final StateFlow<Boolean> mapDarkMode;
    private final StateFlow<MapTheme> mapTheme;
    private final StateFlow<Boolean> pickerDoubleTap;
    private final SharedPreferences prefs;
    private final StateFlow<RoutingSource> routingSource;
    private final StateFlow<Boolean> sharperMap;
    private final StateFlow<Boolean> showJoystickTrail;
    private final StateFlow<Boolean> showPhoneBattery;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ DashConfig(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0076, code lost:
    
        if (r5 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private DashConfig(Context context) {
        RoutingSource routingSource;
        RoutingSource routingSource2;
        Object m8392constructorimpl;
        this.prefs = context.getApplicationContext().getSharedPreferences("dash_config", 0);
        this._mapDark = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_MAP_DARK, false)));
        this.mapDarkMode = FlowKt.asStateFlow(this._mapDark);
        this._mapDarkAuto = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_MAP_DARK_AUTO, true)));
        this.mapDarkAuto = FlowKt.asStateFlow(this._mapDarkAuto);
        String string = this.prefs.getString(KEY_ROUTING_SOURCE, null);
        if (string != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                DashConfig dashConfig = this;
                m8392constructorimpl = Result.m8392constructorimpl(RoutingSource.valueOf(string));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            routingSource2 = (RoutingSource) (Result.m8398isFailureimpl(m8392constructorimpl) ? null : m8392constructorimpl);
        }
        if (this.prefs.getBoolean(KEY_OFFLINE_ROUTING, false)) {
            routingSource = RoutingSource.BROUTER;
        } else {
            routingSource = this.prefs.getBoolean(KEY_FREE_ROUTING, false) ? RoutingSource.OSM : RoutingSource.GOOGLE;
        }
        routingSource2 = (routingSource != RoutingSource.GOOGLE || GoogleKey.INSTANCE.getPresent()) ? routingSource : RoutingSource.OSM;
        this._routingSource = StateFlowKt.MutableStateFlow(routingSource2);
        this.routingSource = FlowKt.asStateFlow(this._routingSource);
        this._mapTheme = StateFlowKt.MutableStateFlow(MapTheme.INSTANCE.fromStored(this.prefs.getString(KEY_MAP_THEME, null)));
        this.mapTheme = FlowKt.asStateFlow(this._mapTheme);
        this._autoConnectOnStart = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_AUTO_CONNECT_ON_START, true)));
        this.autoConnectOnStart = FlowKt.asStateFlow(this._autoConnectOnStart);
        this.prefs.edit().remove(KEY_MAP_SOURCE).remove(KEY_OFFLINE_MAP).remove(KEY_OFFLINE_MAP_FILE).remove(KEY_FREE_MAPS).apply();
        Router.INSTANCE.setSource(this._routingSource.getValue());
        this._invertZoom = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_INVERT_ZOOM, false)));
        this.invertZoom = FlowKt.asStateFlow(this._invertZoom);
        this._sharperMap = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_SHARPER_MAP, false)));
        this.sharperMap = FlowKt.asStateFlow(this._sharperMap);
        this._highQualityVideo = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_HQ_VIDEO, false)));
        this.highQualityVideo = FlowKt.asStateFlow(this._highQualityVideo);
        this._pickerDoubleTap = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_PICKER_DOUBLE_TAP, false)));
        this.pickerDoubleTap = FlowKt.asStateFlow(this._pickerDoubleTap);
        this._showJoystickTrail = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_JOY_TRAIL, false)));
        this.showJoystickTrail = FlowKt.asStateFlow(this._showJoystickTrail);
        this._showPhoneBattery = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_PHONE_BATTERY, true)));
        this.showPhoneBattery = FlowKt.asStateFlow(this._showPhoneBattery);
    }

    public final StateFlow<Boolean> getMapDarkMode() {
        return this.mapDarkMode;
    }

    public final StateFlow<Boolean> getMapDarkAuto() {
        return this.mapDarkAuto;
    }

    public final StateFlow<RoutingSource> getRoutingSource() {
        return this.routingSource;
    }

    public final StateFlow<MapTheme> getMapTheme() {
        return this.mapTheme;
    }

    public final StateFlow<Boolean> getAutoConnectOnStart() {
        return this.autoConnectOnStart;
    }

    public final StateFlow<Boolean> getInvertZoom() {
        return this.invertZoom;
    }

    public final StateFlow<Boolean> getSharperMap() {
        return this.sharperMap;
    }

    public final StateFlow<Boolean> getHighQualityVideo() {
        return this.highQualityVideo;
    }

    public final StateFlow<Boolean> getPickerDoubleTap() {
        return this.pickerDoubleTap;
    }

    public final StateFlow<Boolean> getShowJoystickTrail() {
        return this.showJoystickTrail;
    }

    public final StateFlow<Boolean> getShowPhoneBattery() {
        return this.showPhoneBattery;
    }

    public final String getSsidPrefix() {
        String string = this.prefs.getString(KEY_PREFIX, DEFAULT_PREFIX);
        return string == null ? DEFAULT_PREFIX : string;
    }

    public final void setSsidPrefix(String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        this.prefs.edit().putString(KEY_PREFIX, v).apply();
    }

    public final String getSsid() {
        String string = this.prefs.getString(KEY_SSID, "");
        return string == null ? "" : string;
    }

    public final void setSsid(String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        this.prefs.edit().putString(KEY_SSID, v).apply();
    }

    public final String getPassword() {
        String string = this.prefs.getString("password", DEFAULT_PASSWORD);
        return string == null ? DEFAULT_PASSWORD : string;
    }

    public final void setPassword(String v) {
        Intrinsics.checkNotNullParameter(v, "v");
        this.prefs.edit().putString("password", v).apply();
    }

    public final boolean getNeedsDiscovery() {
        return StringsKt.isBlank(getSsid());
    }

    public final void forgetDash() {
        setSsid("");
    }

    public final void setMapDarkMode(boolean dark) {
        this.prefs.edit().putBoolean(KEY_MAP_DARK, dark).apply();
        this._mapDark.setValue(Boolean.valueOf(dark));
    }

    public final void setRoutingSource(RoutingSource choice) {
        Intrinsics.checkNotNullParameter(choice, "choice");
        RoutingSource source = (choice != RoutingSource.GOOGLE || GoogleKey.INSTANCE.getPresent()) ? choice : RoutingSource.OSM;
        this.prefs.edit().putString(KEY_ROUTING_SOURCE, source.name()).apply();
        this._routingSource.setValue(source);
        Router.INSTANCE.setSource(source);
    }

    public final void setMapTheme(MapTheme theme) {
        Intrinsics.checkNotNullParameter(theme, "theme");
        this.prefs.edit().putString(KEY_MAP_THEME, theme.name()).apply();
        this._mapTheme.setValue(theme);
    }

    public final void setAutoConnectOnStart(boolean enabled) {
        this.prefs.edit().putBoolean(KEY_AUTO_CONNECT_ON_START, enabled).apply();
        this._autoConnectOnStart.setValue(Boolean.valueOf(enabled));
    }

    public final void setMapDarkAuto(boolean auto) {
        this.prefs.edit().putBoolean(KEY_MAP_DARK_AUTO, auto).apply();
        this._mapDarkAuto.setValue(Boolean.valueOf(auto));
    }

    public final void setInvertZoom(boolean invert) {
        this.prefs.edit().putBoolean(KEY_INVERT_ZOOM, invert).apply();
        this._invertZoom.setValue(Boolean.valueOf(invert));
    }

    public final void setSharperMap(boolean on) {
        this.prefs.edit().putBoolean(KEY_SHARPER_MAP, on).apply();
        this._sharperMap.setValue(Boolean.valueOf(on));
    }

    public final void setHighQualityVideo(boolean on) {
        this.prefs.edit().putBoolean(KEY_HQ_VIDEO, on).apply();
        this._highQualityVideo.setValue(Boolean.valueOf(on));
    }

    public final void setPickerDoubleTap(boolean on) {
        this.prefs.edit().putBoolean(KEY_PICKER_DOUBLE_TAP, on).apply();
        this._pickerDoubleTap.setValue(Boolean.valueOf(on));
    }

    public final void setShowJoystickTrail(boolean on) {
        this.prefs.edit().putBoolean(KEY_JOY_TRAIL, on).apply();
        this._showJoystickTrail.setValue(Boolean.valueOf(on));
    }

    public final void setShowPhoneBattery(boolean on) {
        this.prefs.edit().putBoolean(KEY_PHONE_BATTERY, on).apply();
        this._showPhoneBattery.setValue(Boolean.valueOf(on));
    }

    /* compiled from: DashConfig.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/example/wayfindr/dash/DashConfig$Companion;", "", "<init>", "()V", "KEY_PREFIX", "", "KEY_SSID", "KEY_PASSWORD", "KEY_MAP_DARK", "KEY_MAP_DARK_AUTO", "KEY_FREE_ROUTING", "KEY_ROUTING_SOURCE", "KEY_FREE_MAPS", "KEY_OFFLINE_MAP", "KEY_MAP_SOURCE", "KEY_OFFLINE_MAP_FILE", "KEY_MAP_THEME", "KEY_AUTO_CONNECT_ON_START", "KEY_OFFLINE_ROUTING", "KEY_INVERT_ZOOM", "KEY_SHARPER_MAP", "KEY_HQ_VIDEO", "KEY_PICKER_DOUBLE_TAP", "KEY_JOY_TRAIL", "KEY_PHONE_BATTERY", "DEFAULT_PREFIX", "DEFAULT_PASSWORD", "instance", "Lcom/example/wayfindr/dash/DashConfig;", "get", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DashConfig get(Context context) {
            DashConfig dashConfig;
            Intrinsics.checkNotNullParameter(context, "context");
            DashConfig dashConfig2 = DashConfig.instance;
            if (dashConfig2 == null) {
                synchronized (this) {
                    dashConfig = DashConfig.instance;
                    if (dashConfig == null) {
                        dashConfig = new DashConfig(context, null);
                        Companion companion = DashConfig.INSTANCE;
                        DashConfig.instance = dashConfig;
                    }
                }
                return dashConfig;
            }
            return dashConfig2;
        }
    }
}
