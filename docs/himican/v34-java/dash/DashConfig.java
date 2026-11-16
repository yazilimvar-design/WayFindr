package com.example.wayfindr.dash;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.wayfindr.dash.nav.GPlus;
import com.example.wayfindr.dash.nav.Router;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: DashConfig.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
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
    private final MutableStateFlow _autoConnectOnStart;
    private final MutableStateFlow _highQualityVideo;
    private final MutableStateFlow _invertZoom;
    private final MutableStateFlow _mapDark;
    private final MutableStateFlow _mapDarkAuto;
    private final MutableStateFlow _mapTheme;
    private final MutableStateFlow _pickerDoubleTap;
    private final MutableStateFlow _routingSource;
    private final MutableStateFlow _sharperMap;
    private final MutableStateFlow _showJoystickTrail;
    private final MutableStateFlow _showPhoneBattery;
    private final StateFlow autoConnectOnStart;
    private final StateFlow highQualityVideo;
    private final StateFlow invertZoom;
    private final StateFlow mapDarkAuto;
    private final StateFlow mapDarkMode;
    private final StateFlow mapTheme;
    private final StateFlow pickerDoubleTap;
    private final SharedPreferences prefs;
    private final StateFlow routingSource;
    private final StateFlow sharperMap;
    private final StateFlow showJoystickTrail;
    private final StateFlow showPhoneBattery;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int $stable = 8;

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0079, code lost:
    
        if (r5 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private DashConfig(Context context) {
        RoutingSource routingSource;
        Object obj;
        GPlus.init(context);
        this.prefs = context.getApplicationContext().getSharedPreferences("dash_config", 0);
        this._mapDark = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_MAP_DARK, false)));
        this.mapDarkMode = FlowKt.asStateFlow(this._mapDark);
        this._mapDarkAuto = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_MAP_DARK_AUTO, true)));
        this.mapDarkAuto = FlowKt.asStateFlow(this._mapDarkAuto);
        String string = this.prefs.getString(KEY_ROUTING_SOURCE, null);
        if (string != null) {
            try {
                Result.Companion companion = Result.Companion;
                DashConfig dashConfig = this;
                obj = Result.constructor-impl(RoutingSource.valueOf(string));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            routingSource = (RoutingSource) (Result.isFailure-impl(obj) ? null : obj);
        }
        RoutingSource routingSource2 = this.prefs.getBoolean(KEY_OFFLINE_ROUTING, false) ? RoutingSource.BROUTER : this.prefs.getBoolean(KEY_FREE_ROUTING, false) ? RoutingSource.OSM : RoutingSource.GOOGLE;
        routingSource = (routingSource2 != RoutingSource.GOOGLE || GoogleKey.INSTANCE.getPresent()) ? routingSource2 : RoutingSource.OSM;
        this._routingSource = StateFlowKt.MutableStateFlow(routingSource);
        this.routingSource = FlowKt.asStateFlow(this._routingSource);
        this._mapTheme = StateFlowKt.MutableStateFlow(MapTheme.Companion.fromStored(this.prefs.getString(KEY_MAP_THEME, null)));
        this.mapTheme = FlowKt.asStateFlow(this._mapTheme);
        this._autoConnectOnStart = StateFlowKt.MutableStateFlow(Boolean.valueOf(this.prefs.getBoolean(KEY_AUTO_CONNECT_ON_START, true)));
        this.autoConnectOnStart = FlowKt.asStateFlow(this._autoConnectOnStart);
        this.prefs.edit().remove(KEY_MAP_SOURCE).remove(KEY_OFFLINE_MAP).remove(KEY_OFFLINE_MAP_FILE).remove(KEY_FREE_MAPS).apply();
        Router.INSTANCE.setSource((RoutingSource) this._routingSource.getValue());
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

    public /* synthetic */ DashConfig(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    public final void forgetDash() {
        setSsid("");
    }

    public final StateFlow getAutoConnectOnStart() {
        return this.autoConnectOnStart;
    }

    public final StateFlow getHighQualityVideo() {
        return this.highQualityVideo;
    }

    public final StateFlow getInvertZoom() {
        return this.invertZoom;
    }

    public final StateFlow getMapDarkAuto() {
        return this.mapDarkAuto;
    }

    public final StateFlow getMapDarkMode() {
        return this.mapDarkMode;
    }

    public final StateFlow getMapTheme() {
        return this.mapTheme;
    }

    public final boolean getNeedsDiscovery() {
        return StringsKt.isBlank(getSsid());
    }

    public final String getPassword() {
        String string = this.prefs.getString(KEY_PASSWORD, DEFAULT_PASSWORD);
        return string == null ? DEFAULT_PASSWORD : string;
    }

    public final StateFlow getPickerDoubleTap() {
        return this.pickerDoubleTap;
    }

    public final StateFlow getRoutingSource() {
        return this.routingSource;
    }

    public final StateFlow getSharperMap() {
        return this.sharperMap;
    }

    public final StateFlow getShowJoystickTrail() {
        return this.showJoystickTrail;
    }

    public final StateFlow getShowPhoneBattery() {
        return this.showPhoneBattery;
    }

    public final String getSsid() {
        String string = this.prefs.getString(KEY_SSID, "");
        return string == null ? "" : string;
    }

    public final String getSsidPrefix() {
        String string = this.prefs.getString(KEY_PREFIX, DEFAULT_PREFIX);
        return string == null ? DEFAULT_PREFIX : string;
    }

    public final void setAutoConnectOnStart(boolean z) {
        this.prefs.edit().putBoolean(KEY_AUTO_CONNECT_ON_START, z).apply();
        this._autoConnectOnStart.setValue(Boolean.valueOf(z));
    }

    public final void setHighQualityVideo(boolean z) {
        this.prefs.edit().putBoolean(KEY_HQ_VIDEO, z).apply();
        this._highQualityVideo.setValue(Boolean.valueOf(z));
    }

    public final void setInvertZoom(boolean z) {
        this.prefs.edit().putBoolean(KEY_INVERT_ZOOM, z).apply();
        this._invertZoom.setValue(Boolean.valueOf(z));
    }

    public final void setMapDarkAuto(boolean z) {
        this.prefs.edit().putBoolean(KEY_MAP_DARK_AUTO, z).apply();
        this._mapDarkAuto.setValue(Boolean.valueOf(z));
    }

    public final void setMapDarkMode(boolean z) {
        this.prefs.edit().putBoolean(KEY_MAP_DARK, z).apply();
        this._mapDark.setValue(Boolean.valueOf(z));
    }

    public final void setMapTheme(MapTheme mapTheme) {
        Intrinsics.checkNotNullParameter(mapTheme, "theme");
        this.prefs.edit().putString(KEY_MAP_THEME, mapTheme.name()).apply();
        this._mapTheme.setValue(mapTheme);
    }

    public final void setPassword(String str) {
        Intrinsics.checkNotNullParameter(str, "v");
        this.prefs.edit().putString(KEY_PASSWORD, str).apply();
    }

    public final void setPickerDoubleTap(boolean z) {
        this.prefs.edit().putBoolean(KEY_PICKER_DOUBLE_TAP, z).apply();
        this._pickerDoubleTap.setValue(Boolean.valueOf(z));
    }

    public final void setRoutingSource(RoutingSource routingSource) {
        Intrinsics.checkNotNullParameter(routingSource, "choice");
        RoutingSource routingSource2 = (routingSource != RoutingSource.GOOGLE || GoogleKey.INSTANCE.getPresent()) ? routingSource : RoutingSource.OSM;
        this.prefs.edit().putString(KEY_ROUTING_SOURCE, routingSource2.name()).apply();
        this._routingSource.setValue(routingSource2);
        Router.INSTANCE.setSource(routingSource2);
    }

    public final void setSharperMap(boolean z) {
        this.prefs.edit().putBoolean(KEY_SHARPER_MAP, z).apply();
        this._sharperMap.setValue(Boolean.valueOf(z));
    }

    public final void setShowJoystickTrail(boolean z) {
        this.prefs.edit().putBoolean(KEY_JOY_TRAIL, z).apply();
        this._showJoystickTrail.setValue(Boolean.valueOf(z));
    }

    public final void setShowPhoneBattery(boolean z) {
        this.prefs.edit().putBoolean(KEY_PHONE_BATTERY, z).apply();
        this._showPhoneBattery.setValue(Boolean.valueOf(z));
    }

    public final void setSsid(String str) {
        Intrinsics.checkNotNullParameter(str, "v");
        this.prefs.edit().putString(KEY_SSID, str).apply();
    }

    public final void setSsidPrefix(String str) {
        Intrinsics.checkNotNullParameter(str, "v");
        this.prefs.edit().putString(KEY_PREFIX, str).apply();
    }
}
