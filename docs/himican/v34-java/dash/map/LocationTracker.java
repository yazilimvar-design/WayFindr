package com.example.wayfindr.dash.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import androidx.compose.animation.core.AnimationKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.util.Dbg;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: LocationTracker.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u0000 $2\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0016\u001a\u00020\nH\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0007J\u0006\u0010\u001f\u001a\u00020\u001eJ\n\u0010 \u001a\u0004\u0018\u00010\nH\u0007J\n\u0010!\u001a\u0004\u0018\u00010\nH\u0007J\u0012\u0010\"\u001a\u0004\u0018\u00010\n2\u0006\u0010#\u001a\u00020\u0014H\u0003R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/example/wayfindr/dash/map/LocationTracker;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "lm", "Landroid/location/LocationManager;", "_location", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroid/location/Location;", "location", "Lkotlinx/coroutines/flow/StateFlow;", "getLocation", "()Lkotlinx/coroutines/flow/StateFlow;", "rejectStreak", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/location/LocationListener;", "acceptFix", "", "cur", "loc", "reject", "running", "isRunning", "()Z", "handlerThread", "Landroid/os/HandlerThread;", "start", "", "stop", "lastKnown", "lastKnownForDisplay", "bestKnown", "requireFresh", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class LocationTracker {
    private static final long GPS_STALE_MS = 10000;
    public static final long MAX_NAV_FIX_AGE_MS = 10000;
    private static final String TAG = "LocationTracker";
    private final MutableStateFlow<Location> _location;
    private HandlerThread handlerThread;
    private final LocationListener listener;
    private final LocationManager lm;
    private final StateFlow<Location> location;
    private int rejectStreak;
    private boolean running;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* compiled from: LocationTracker.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u0007H\u0007J'\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/dash/map/LocationTracker$Companion;", "", "<init>", "()V", "TAG", "", "GPS_STALE_MS", "", "MAX_NAV_FIX_AGE_MS", "isFreshForNavigation", "", "loc", "Landroid/location/Location;", "nowElapsedNanos", "nowWallMs", "fixAgeMs", "(Landroid/location/Location;JJ)Ljava/lang/Long;", "elapsedDeltaSeconds", "", "previous", "next", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ boolean isFreshForNavigation$default(Companion companion, Location location, long j, long j2, int i, Object obj) {
            long j3;
            long j4;
            if ((i & 2) == 0) {
                j3 = j;
            } else {
                j3 = SystemClock.elapsedRealtimeNanos();
            }
            if ((i & 4) == 0) {
                j4 = j2;
            } else {
                j4 = System.currentTimeMillis();
            }
            return companion.isFreshForNavigation(location, j3, j4);
        }

        @JvmStatic
        public final boolean isFreshForNavigation(Location loc, long nowElapsedNanos, long nowWallMs) {
            Intrinsics.checkNotNullParameter(loc, "loc");
            Long fixAgeMs = fixAgeMs(loc, nowElapsedNanos, nowWallMs);
            if (fixAgeMs == null) {
                return false;
            }
            long age = fixAgeMs.longValue();
            return NavigationReliability.INSTANCE.isFreshFixAge(age, 10000L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Long fixAgeMs(Location loc, long nowElapsedNanos, long nowWallMs) {
            long fixElapsed = loc.getElapsedRealtimeNanos();
            if (fixElapsed > 0 && nowElapsedNanos >= fixElapsed) {
                return Long.valueOf((nowElapsedNanos - fixElapsed) / AnimationKt.MillisToNanos);
            }
            long wallAge = nowWallMs - loc.getTime();
            Long valueOf = Long.valueOf(wallAge);
            if (valueOf.longValue() >= 0) {
                return valueOf;
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final double elapsedDeltaSeconds(Location previous, Location next) {
            long previousElapsed = previous.getElapsedRealtimeNanos();
            long nextElapsed = next.getElapsedRealtimeNanos();
            if (previousElapsed > 0 && nextElapsed > 0) {
                return (nextElapsed - previousElapsed) / 1.0E9d;
            }
            return (next.getTime() - previous.getTime()) / 1000.0d;
        }
    }

    public LocationTracker(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("location");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.lm = (LocationManager) systemService;
        this._location = StateFlowKt.MutableStateFlow(null);
        this.location = FlowKt.asStateFlow(this._location);
        this.listener = new LocationListener() { // from class: com.example.wayfindr.dash.map.LocationTracker$$ExternalSyntheticLambda0
            @Override // android.location.LocationListener
            public final void onLocationChanged(Location location) {
                LocationTracker.listener$lambda$2(LocationTracker.this, location);
            }
        };
    }

    @JvmStatic
    public static final boolean isFreshForNavigation(Location loc, long nowElapsedNanos, long nowWallMs) {
        return INSTANCE.isFreshForNavigation(loc, nowElapsedNanos, nowWallMs);
    }

    public final StateFlow<Location> getLocation() {
        return this.location;
    }

    static final void listener$lambda$2(LocationTracker this$0, Location loc) {
        Intrinsics.checkNotNullParameter(loc, "loc");
        Location cur = this$0._location.getValue();
        if (this$0.acceptFix(cur, loc)) {
            this$0._location.setValue(loc);
            Dbg dbg = Dbg.INSTANCE;
            if (BuildConfig.DEBUG) {
                Log.d(TAG, "fix " + loc.getProvider() + " acc=" + loc.getAccuracy() + " (" + loc.getLatitude() + "," + loc.getLongitude() + ")");
                return;
            }
            return;
        }
        Log.d(TAG, "REJECT " + loc.getProvider() + " acc=" + loc.getAccuracy() + " dt=" + (cur != null ? INSTANCE.elapsedDeltaSeconds(cur, loc) : 0.0d) + "s");
    }

    private final boolean acceptFix(Location cur, Location loc) {
        boolean z;
        if (cur != null) {
            double dt = INSTANCE.elapsedDeltaSeconds(cur, loc);
            if (dt < 0.0d) {
                return false;
            }
            if ((dt == 0.0d) && loc.getTime() < cur.getTime()) {
                return false;
            }
            boolean isGps = Intrinsics.areEqual(loc.getProvider(), "gps");
            if (!isGps && Intrinsics.areEqual(cur.getProvider(), "gps") && Companion.isFreshForNavigation$default(INSTANCE, cur, 0L, 0L, 6, null)) {
                return false;
            }
            double jump = cur.distanceTo(loc);
            if (!NavigationReliability.isPlausibleJump$default(NavigationReliability.INSTANCE, jump, dt, 0.0d, 4, null)) {
                return reject();
            }
            if (dt <= 10.0d) {
                if (!(0.0d <= dt && dt <= 8.0d) || this.rejectStreak >= 3) {
                    z = true;
                } else {
                    float oldSpeed = cur.hasSpeed() ? cur.getSpeed() : 0.0f;
                    float newSpeed = loc.hasSpeed() ? loc.getSpeed() : 0.0f;
                    float plausibleSpeed = RangesKt.coerceAtLeast(Math.max(oldSpeed, newSpeed), 1.0f);
                    double expected = plausibleSpeed * dt;
                    float oldAccuracy = cur.hasAccuracy() ? cur.getAccuracy() : 50.0f;
                    float newAccuracy = loc.hasAccuracy() ? loc.getAccuracy() : 50.0f;
                    float noise = oldAccuracy + newAccuracy;
                    z = true;
                    double gate = (noise * 2.5d) + expected + 25.0d;
                    if (jump > gate && jump > 40.0d) {
                        return reject();
                    }
                }
                this.rejectStreak = 0;
                return z;
            }
            this.rejectStreak = 0;
            return true;
        }
        if (!Companion.isFreshForNavigation$default(INSTANCE, loc, 0L, 0L, 6, null)) {
            return false;
        }
        this.rejectStreak = 0;
        return true;
    }

    private final boolean reject() {
        this.rejectStreak++;
        return false;
    }

    /* renamed from: isRunning, reason: from getter */
    public final boolean getRunning() {
        return this.running;
    }

    public final void start() {
        if (this.running) {
            return;
        }
        try {
            this._location.setValue(bestKnown(true));
            HandlerThread handlerThread = new HandlerThread(TAG);
            handlerThread.start();
            this.handlerThread = handlerThread;
            HandlerThread handlerThread2 = this.handlerThread;
            Intrinsics.checkNotNull(handlerThread2);
            Looper looper = handlerThread2.getLooper();
            for (String provider : CollectionsKt.listOf((Object[]) new String[]{"gps", "network"})) {
                if (this.lm.isProviderEnabled(provider)) {
                    this.lm.requestLocationUpdates(provider, 500L, 0.0f, this.listener, looper);
                }
            }
            this.running = true;
            Log.i(TAG, "Location updates started on background thread");
        } catch (SecurityException e) {
            Log.w(TAG, "Location permission missing — GPS disabled");
        } catch (Exception e2) {
            Log.w(TAG, "GPS start failed: " + e2.getMessage());
        }
    }

    public final void stop() {
        if (this.running) {
            this.lm.removeUpdates(this.listener);
            HandlerThread handlerThread = this.handlerThread;
            if (handlerThread != null) {
                handlerThread.quitSafely();
            }
            this.handlerThread = null;
            this.running = false;
        }
    }

    public final Location lastKnown() {
        try {
            return bestKnown(true);
        } catch (Exception e) {
            return null;
        }
    }

    public final Location lastKnownForDisplay() {
        try {
            Location value = this._location.getValue();
            return value == null ? bestKnown(false) : value;
        } catch (Exception e) {
            return null;
        }
    }

    private final Location bestKnown(boolean requireFresh) {
        NavigationReliability navigationReliability;
        Location location;
        Double d;
        Object obj;
        Object m8392constructorimpl;
        Iterable<String> listOf = CollectionsKt.listOf((Object[]) new String[]{"gps", "network", "passive"});
        Collection arrayList = new ArrayList();
        for (String str : listOf) {
            try {
                Result.Companion companion = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(this.lm.getLastKnownLocation(str));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            Location location2 = (Location) (Result.m8398isFailureimpl(m8392constructorimpl) ? null : m8392constructorimpl);
            if (location2 != null) {
                arrayList.add(location2);
            }
        }
        Iterable candidates = CollectionsKt.plus(arrayList, (Iterable) CollectionsKt.listOfNotNull(this._location.getValue()));
        long nowElapsed = SystemClock.elapsedRealtimeNanos();
        long nowWall = System.currentTimeMillis();
        Collection arrayList2 = new ArrayList();
        for (Object obj2 : candidates) {
            if (!requireFresh || INSTANCE.isFreshForNavigation((Location) obj2, nowElapsed, nowWall)) {
                arrayList2.add(obj2);
            }
        }
        Iterable iterable = (List) arrayList2;
        Iterator it = iterable.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                Location location3 = (Location) next;
                NavigationReliability navigationReliability2 = NavigationReliability.INSTANCE;
                Double valueOf = location3.hasAccuracy() ? Double.valueOf(location3.getAccuracy()) : null;
                Long fixAgeMs = INSTANCE.fixAgeMs(location3, nowElapsed, nowWall);
                double routeOriginScore = navigationReliability2.routeOriginScore(valueOf, fixAgeMs != null ? fixAgeMs.longValue() : Long.MAX_VALUE);
                while (true) {
                    Object next2 = it.next();
                    Location location4 = (Location) next2;
                    NavigationReliability navigationReliability3 = NavigationReliability.INSTANCE;
                    if (location4.hasAccuracy()) {
                        navigationReliability = navigationReliability3;
                        location = location4;
                        d = Double.valueOf(location4.getAccuracy());
                    } else {
                        navigationReliability = navigationReliability3;
                        location = location4;
                        d = null;
                    }
                    Iterable iterable2 = iterable;
                    NavigationReliability navigationReliability4 = navigationReliability;
                    Long fixAgeMs2 = INSTANCE.fixAgeMs(location, nowElapsed, nowWall);
                    double routeOriginScore2 = navigationReliability4.routeOriginScore(d, fixAgeMs2 != null ? fixAgeMs2.longValue() : Long.MAX_VALUE);
                    if (Double.compare(routeOriginScore, routeOriginScore2) > 0) {
                        routeOriginScore = routeOriginScore2;
                        next = next2;
                    }
                    if (!it.hasNext()) {
                        break;
                    }
                    iterable = iterable2;
                }
                obj = next;
            } else {
                obj = next;
            }
        } else {
            obj = null;
        }
        return (Location) obj;
    }
}
