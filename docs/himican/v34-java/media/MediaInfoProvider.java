package com.example.wayfindr.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.session.MediaController;
import android.media.session.MediaSession;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
import android.provider.Settings;
import android.util.Log;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.media.MediaInfoProvider$;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: MediaInfoProvider.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class MediaInfoProvider {
    private static final String TAG = "MediaInfoProvider";
    private final MutableStateFlow _nowPlaying;
    private final ComponentName component;
    private final Context context;
    private MediaController controller;
    private final controllerCb.1 controllerCb;
    private final MediaSessionManager msm;
    private final StateFlow nowPlaying;
    private final MediaSessionManager.OnActiveSessionsChangedListener sessionsCb;
    public static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: MediaInfoProvider.kt */
    public final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Intent accessSettingsIntent() {
            Intent addFlags = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS").addFlags(268435456);
            Intrinsics.checkNotNullExpressionValue(addFlags, "addFlags(...)");
            return addFlags;
        }

        public final boolean isAccessGranted(Context context) {
            try {
                String string = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
                if (string != null) {
                    return string.contains(context.getPackageName());
                }
                return false;
            } catch (Throwable unused) {
                return false;
            }
        }
    }

    public MediaInfoProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this._nowPlaying = StateFlowKt.MutableStateFlow((Object) null);
        this.nowPlaying = FlowKt.asStateFlow(this._nowPlaying);
        this.msm = (MediaSessionManager) this.context.getSystemService(MediaSessionManager.class);
        this.component = new ComponentName(this.context, (Class<?>) WayfindrNotificationListener.class);
        this.controllerCb = new controllerCb.1(this);
        this.sessionsCb = new MediaInfoProvider$.ExternalSyntheticLambda0(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bind(List list) {
        MediaController mediaController = list != null ? (MediaController) CollectionsKt.firstOrNull(list) : null;
        MediaSession.Token sessionToken = mediaController != null ? mediaController.getSessionToken() : null;
        MediaController mediaController2 = this.controller;
        if (Intrinsics.areEqual(sessionToken, mediaController2 != null ? mediaController2.getSessionToken() : null)) {
            publish();
            return;
        }
        MediaController mediaController3 = this.controller;
        if (mediaController3 != null) {
            mediaController3.unregisterCallback((MediaController.Callback) this.controllerCb);
        }
        this.controller = mediaController;
        MediaController mediaController4 = this.controller;
        if (mediaController4 != null) {
            mediaController4.registerCallback((MediaController.Callback) this.controllerCb);
        }
        publish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void publish() {
        PlaybackState playbackState;
        MediaController mediaController = this.controller;
        MediaMetadata metadata = mediaController != null ? mediaController.getMetadata() : null;
        if (metadata == null) {
            MutableStateFlow mutableStateFlow = this._nowPlaying;
            NavPlus.npNow = null;
            mutableStateFlow.setValue((Object) null);
            return;
        }
        String string = metadata.getString("android.media.metadata.TITLE");
        if (string == null) {
            string = "";
        }
        String str = string;
        String string2 = metadata.getString("android.media.metadata.ALBUM");
        String str2 = string2 == null ? "" : string2;
        String string3 = metadata.getString("android.media.metadata.ARTIST");
        String str3 = string3 == null ? "" : string3;
        if (StringsKt.isBlank(str) && StringsKt.isBlank(str3)) {
            MutableStateFlow mutableStateFlow2 = this._nowPlaying;
            NavPlus.npNow = null;
            mutableStateFlow2.setValue((Object) null);
            return;
        }
        Bitmap bitmap = metadata.getBitmap("android.media.metadata.ALBUM_ART");
        Bitmap bitmap2 = (bitmap == null && (bitmap = metadata.getBitmap("android.media.metadata.ART")) == null) ? metadata.getBitmap("android.media.metadata.DISPLAY_ICON") : bitmap;
        MediaController mediaController2 = this.controller;
        boolean z = false;
        if (mediaController2 != null && (playbackState = mediaController2.getPlaybackState()) != null && playbackState.getState() == 3) {
            z = true;
        }
        MutableStateFlow mutableStateFlow3 = this._nowPlaying;
        NowPlaying nowPlaying = new NowPlaying(str, str2, str3, bitmap2, z);
        NavPlus.npNow = nowPlaying;
        mutableStateFlow3.setValue(nowPlaying);
    }

    public final StateFlow getNowPlaying() {
        return this.nowPlaying;
    }

    public final boolean skipNext() {
        Object obj;
        Unit unit;
        MediaController.TransportControls transportControls;
        try {
            Result.Companion companion = Result.Companion;
            MediaController mediaController = this.controller;
            if (mediaController == null || (transportControls = mediaController.getTransportControls()) == null) {
                unit = null;
            } else {
                transportControls.skipToNext();
                unit = Unit.INSTANCE;
            }
            obj = Result.constructor-impl(Boolean.valueOf(unit != null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = false;
        }
        return ((Boolean) obj).booleanValue();
    }

    public final boolean skipPrevious() {
        Object obj;
        Unit unit;
        MediaController.TransportControls transportControls;
        try {
            Result.Companion companion = Result.Companion;
            MediaController mediaController = this.controller;
            if (mediaController == null || (transportControls = mediaController.getTransportControls()) == null) {
                unit = null;
            } else {
                transportControls.skipToPrevious();
                unit = Unit.INSTANCE;
            }
            obj = Result.constructor-impl(Boolean.valueOf(unit != null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        if (Result.isFailure-impl(obj)) {
            obj = false;
        }
        return ((Boolean) obj).booleanValue();
    }

    public final void start() {
        Object obj;
        if (!Companion.isAccessGranted(this.context)) {
            Log.i(TAG, "no notification access — now-playing disabled");
            RideDiagnostics.INSTANCE.log("media", "now-playing: NO notification access");
            return;
        }
        try {
            Result.Companion companion = Result.Companion;
            MediaInfoProvider mediaInfoProvider = this;
            MediaSessionManager mediaSessionManager = mediaInfoProvider.msm;
            if (mediaSessionManager != null) {
                mediaSessionManager.addOnActiveSessionsChangedListener(mediaInfoProvider.sessionsCb, mediaInfoProvider.component);
            }
            MediaSessionManager mediaSessionManager2 = mediaInfoProvider.msm;
            List<MediaController> activeSessions = mediaSessionManager2 != null ? mediaSessionManager2.getActiveSessions(mediaInfoProvider.component) : null;
            RideDiagnostics.INSTANCE.log("media", "now-playing: " + (activeSessions != null ? activeSessions.size() : 0) + " active media session(s)");
            mediaInfoProvider.bind(activeSessions);
            obj = Result.constructor-impl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        Throwable th2 = Result.exceptionOrNull-impl(obj);
        if (th2 != null) {
            Log.w(TAG, "start failed: " + th2.getMessage());
            RideDiagnostics.INSTANCE.log("media", "now-playing start FAILED: " + th2.getMessage());
        }
    }

    public final void stop() {
        Unit unit;
        try {
            Result.Companion companion = Result.Companion;
            MediaInfoProvider mediaInfoProvider = this;
            MediaSessionManager mediaSessionManager = mediaInfoProvider.msm;
            if (mediaSessionManager != null) {
                mediaSessionManager.removeOnActiveSessionsChangedListener(mediaInfoProvider.sessionsCb);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.constructor-impl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            Result.constructor-impl(ResultKt.createFailure(th));
        }
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.unregisterCallback((MediaController.Callback) this.controllerCb);
        }
        this.controller = null;
        this._nowPlaying.setValue((Object) null);
    }
}
