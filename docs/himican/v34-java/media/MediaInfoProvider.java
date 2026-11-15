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
import android.util.Log;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.List;
import kotlin.Metadata;
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

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: MediaInfoProvider.kt */
@Metadata(d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003*\u0001\u0015\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0019\u001a\u00020\u001aJ\u0006\u0010\u001b\u001a\u00020\u001aJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dJ\u0018\u0010\u001f\u001a\u00020\u001a2\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010!H\u0002J\b\u0010\"\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/example/wayfindr/media/MediaInfoProvider;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "_nowPlaying", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/media/NowPlaying;", "nowPlaying", "Lkotlinx/coroutines/flow/StateFlow;", "getNowPlaying", "()Lkotlinx/coroutines/flow/StateFlow;", "msm", "Landroid/media/session/MediaSessionManager;", "kotlin.jvm.PlatformType", "component", "Landroid/content/ComponentName;", "controller", "Landroid/media/session/MediaController;", "controllerCb", "com/example/wayfindr/media/MediaInfoProvider$controllerCb$1", "Lcom/example/wayfindr/media/MediaInfoProvider$controllerCb$1;", "sessionsCb", "Landroid/media/session/MediaSessionManager$OnActiveSessionsChangedListener;", "start", "", "stop", "skipNext", "", "skipPrevious", "bind", "list", "", "publish", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class MediaInfoProvider {
    private static final String TAG = "MediaInfoProvider";
    private final MutableStateFlow<NowPlaying> _nowPlaying;
    private final ComponentName component;
    private final Context context;
    private MediaController controller;
    private final MediaInfoProvider$controllerCb$1 controllerCb;
    private final MediaSessionManager msm;
    private final StateFlow<NowPlaying> nowPlaying;
    private final MediaSessionManager.OnActiveSessionsChangedListener sessionsCb;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Type inference failed for: r0v9, types: [com.example.wayfindr.media.MediaInfoProvider$controllerCb$1] */
    public MediaInfoProvider(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this._nowPlaying = StateFlowKt.MutableStateFlow(null);
        this.nowPlaying = FlowKt.asStateFlow(this._nowPlaying);
        this.msm = (MediaSessionManager) this.context.getSystemService(MediaSessionManager.class);
        this.component = new ComponentName(this.context, (Class<?>) WayfindrNotificationListener.class);
        this.controllerCb = new MediaController.Callback() { // from class: com.example.wayfindr.media.MediaInfoProvider$controllerCb$1
            @Override // android.media.session.MediaController.Callback
            public void onMetadataChanged(MediaMetadata metadata) {
                MediaInfoProvider.this.publish();
            }

            @Override // android.media.session.MediaController.Callback
            public void onPlaybackStateChanged(PlaybackState state) {
                MediaInfoProvider.this.publish();
            }

            @Override // android.media.session.MediaController.Callback
            public void onSessionDestroyed() {
                MediaInfoProvider.this.bind(null);
            }
        };
        this.sessionsCb = new MediaSessionManager.OnActiveSessionsChangedListener() { // from class: com.example.wayfindr.media.MediaInfoProvider$$ExternalSyntheticLambda0
            @Override // android.media.session.MediaSessionManager.OnActiveSessionsChangedListener
            public final void onActiveSessionsChanged(List list) {
                MediaInfoProvider.this.bind(list);
            }
        };
    }

    public final StateFlow<NowPlaying> getNowPlaying() {
        return this.nowPlaying;
    }

    public final void start() {
        Object m8392constructorimpl;
        if (!INSTANCE.isAccessGranted(this.context)) {
            Log.i(TAG, "no notification access — now-playing disabled");
            RideDiagnostics.INSTANCE.log("media", "now-playing: NO notification access");
            return;
        }
        try {
            Result.Companion companion = Result.INSTANCE;
            MediaInfoProvider mediaInfoProvider = this;
            MediaSessionManager mediaSessionManager = mediaInfoProvider.msm;
            if (mediaSessionManager != null) {
                mediaSessionManager.addOnActiveSessionsChangedListener(mediaInfoProvider.sessionsCb, mediaInfoProvider.component);
            }
            MediaSessionManager mediaSessionManager2 = mediaInfoProvider.msm;
            List activeSessions = mediaSessionManager2 != null ? mediaSessionManager2.getActiveSessions(mediaInfoProvider.component) : null;
            RideDiagnostics.INSTANCE.log("media", "now-playing: " + (activeSessions != null ? activeSessions.size() : 0) + " active media session(s)");
            mediaInfoProvider.bind(activeSessions);
            m8392constructorimpl = Result.m8392constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        Throwable m8395exceptionOrNullimpl = Result.m8395exceptionOrNullimpl(m8392constructorimpl);
        if (m8395exceptionOrNullimpl != null) {
            Log.w(TAG, "start failed: " + m8395exceptionOrNullimpl.getMessage());
            RideDiagnostics.INSTANCE.log("media", "now-playing start FAILED: " + m8395exceptionOrNullimpl.getMessage());
        }
    }

    public final void stop() {
        Unit unit;
        try {
            Result.Companion companion = Result.INSTANCE;
            MediaInfoProvider mediaInfoProvider = this;
            MediaSessionManager mediaSessionManager = mediaInfoProvider.msm;
            if (mediaSessionManager != null) {
                mediaSessionManager.removeOnActiveSessionsChangedListener(mediaInfoProvider.sessionsCb);
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m8392constructorimpl(unit);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        MediaController mediaController = this.controller;
        if (mediaController != null) {
            mediaController.unregisterCallback(this.controllerCb);
        }
        this.controller = null;
        this._nowPlaying.setValue(null);
    }

    public final boolean skipNext() {
        Object m8392constructorimpl;
        Unit unit;
        MediaController.TransportControls transportControls;
        try {
            Result.Companion companion = Result.INSTANCE;
            MediaController mediaController = this.controller;
            if (mediaController == null || (transportControls = mediaController.getTransportControls()) == null) {
                unit = null;
            } else {
                transportControls.skipToNext();
                unit = Unit.INSTANCE;
            }
            m8392constructorimpl = Result.m8392constructorimpl(Boolean.valueOf(unit != null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = false;
        }
        return ((Boolean) m8392constructorimpl).booleanValue();
    }

    public final boolean skipPrevious() {
        Object m8392constructorimpl;
        Unit unit;
        MediaController.TransportControls transportControls;
        try {
            Result.Companion companion = Result.INSTANCE;
            MediaController mediaController = this.controller;
            if (mediaController == null || (transportControls = mediaController.getTransportControls()) == null) {
                unit = null;
            } else {
                transportControls.skipToPrevious();
                unit = Unit.INSTANCE;
            }
            m8392constructorimpl = Result.m8392constructorimpl(Boolean.valueOf(unit != null));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = false;
        }
        return ((Boolean) m8392constructorimpl).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bind(List<MediaController> list) {
        MediaController top = list != null ? (MediaController) CollectionsKt.firstOrNull((List) list) : null;
        MediaSession.Token sessionToken = top != null ? top.getSessionToken() : null;
        MediaController mediaController = this.controller;
        if (Intrinsics.areEqual(sessionToken, mediaController != null ? mediaController.getSessionToken() : null)) {
            publish();
            return;
        }
        MediaController mediaController2 = this.controller;
        if (mediaController2 != null) {
            mediaController2.unregisterCallback(this.controllerCb);
        }
        this.controller = top;
        MediaController mediaController3 = this.controller;
        if (mediaController3 != null) {
            mediaController3.registerCallback(this.controllerCb);
        }
        publish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void publish() {
        Bitmap art;
        PlaybackState playbackState;
        MediaController mediaController = this.controller;
        MediaMetadata md = mediaController != null ? mediaController.getMetadata() : null;
        if (md == null) {
            this._nowPlaying.setValue(null);
            return;
        }
        String string = md.getString("android.media.metadata.TITLE");
        if (string == null) {
            string = "";
        }
        String title = string;
        String string2 = md.getString("android.media.metadata.ALBUM");
        String album = string2 == null ? "" : string2;
        String string3 = md.getString("android.media.metadata.ARTIST");
        String artist = string3 == null ? "" : string3;
        if (StringsKt.isBlank(title) && StringsKt.isBlank(artist)) {
            this._nowPlaying.setValue(null);
            return;
        }
        Bitmap bitmap = md.getBitmap("android.media.metadata.ALBUM_ART");
        if (bitmap != null || (bitmap = md.getBitmap("android.media.metadata.ART")) != null) {
            art = bitmap;
        } else {
            art = md.getBitmap("android.media.metadata.DISPLAY_ICON");
        }
        MediaController mediaController2 = this.controller;
        boolean z = false;
        if (mediaController2 != null && (playbackState = mediaController2.getPlaybackState()) != null && playbackState.getState() == 3) {
            z = true;
        }
        boolean playing = z;
        this._nowPlaying.setValue(new NowPlaying(title, album, artist, art, playing));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: MediaInfoProvider.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/example/wayfindr/media/MediaInfoProvider$Companion;", "", "<init>", "()V", "TAG", "", "isAccessGranted", "", "context", "Landroid/content/Context;", "accessSettingsIntent", "Landroid/content/Intent;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isAccessGranted(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            return false;
        }

        public final Intent accessSettingsIntent() {
            Intent addFlags = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS").addFlags(268435456);
            Intrinsics.checkNotNullExpressionValue(addFlags, "addFlags(...)");
            return addFlags;
        }
    }
}
