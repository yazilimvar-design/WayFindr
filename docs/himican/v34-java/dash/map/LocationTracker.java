package com.example.wayfindr.dash.map;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.map.LocationTracker$;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.util.Dbg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: LocationTracker.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class LocationTracker {
    private static final long GPS_STALE_MS = 10000;
    public static final long MAX_NAV_FIX_AGE_MS = 10000;
    private static final String TAG = "LocationTracker";
    private final MutableStateFlow _location;
    private HandlerThread handlerThread;
    private final LocationListener listener;
    private final LocationManager lm;
    private final StateFlow location;
    private int rejectStreak;
    private boolean running;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int $stable = 8;

    public LocationTracker(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Object systemService = context.getSystemService("location");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.location.LocationManager");
        this.lm = (LocationManager) systemService;
        this._location = StateFlowKt.MutableStateFlow((Object) null);
        this.location = FlowKt.asStateFlow(this._location);
        this.listener = new LocationTracker$.ExternalSyntheticLambda0(this);
    }

    private final boolean acceptFix(Location location, Location location2) {
        boolean isPlausibleJump;
        boolean z;
        if (location == null) {
            if (!Companion.isFreshForNavigation$default(Companion, location2, 0L, 0L, 6, (Object) null)) {
                return false;
            }
            this.rejectStreak = 0;
            return true;
        }
        double access$elapsedDeltaSeconds = Companion.access$elapsedDeltaSeconds(Companion, location, location2);
        if (access$elapsedDeltaSeconds < 0.0d) {
            return false;
        }
        if (((access$elapsedDeltaSeconds == 0.0d) && location2.getTime() < location.getTime()) || LocPlus.preferReject(location, location2, access$elapsedDeltaSeconds)) {
            return false;
        }
        LocPlus.stillLock(location2);
        if (LocPlus.rank(location2.getProvider()) != 3 && !Intrinsics.areEqual(location2.getProvider(), "gps") && Intrinsics.areEqual(location.getProvider(), "gps") && Companion.isFreshForNavigation$default(Companion, location, 0L, 0L, 6, (Object) null)) {
            return false;
        }
        double distanceTo = location.distanceTo(location2);
        isPlausibleJump = NavigationReliability.INSTANCE.isPlausibleJump(distanceTo, access$elapsedDeltaSeconds, (r14 & 4) != 0 ? 85.0d : 0.0d);
        if (!isPlausibleJump) {
            return reject();
        }
        if (access$elapsedDeltaSeconds > 10.0d) {
            this.rejectStreak = 0;
            return true;
        }
        if (!(0.0d <= access$elapsedDeltaSeconds && access$elapsedDeltaSeconds <= 8.0d) || this.rejectStreak >= 3) {
            z = true;
        } else {
            z = true;
            if (distanceTo > (((location.hasAccuracy() ? location.getAccuracy() : 50.0f) + (location2.hasAccuracy() ? location2.getAccuracy() : 50.0f)) * 2.5d) + (RangesKt.coerceAtLeast(Math.max(location.hasSpeed() ? location.getSpeed() : 0.0f, location2.hasSpeed() ? location2.getSpeed() : 0.0f), 1.0f) * access$elapsedDeltaSeconds) + 25.0d && distanceTo > 40.0d) {
                return reject();
            }
        }
        this.rejectStreak = 0;
        return z;
    }

    private final Location bestKnown(boolean z) {
        NavigationReliability navigationReliability;
        Location location;
        Double d;
        Object obj;
        Object obj2;
        List<String> listOf = CollectionsKt.listOf(new String[]{"gps", "network", "passive"});
        ArrayList arrayList = new ArrayList();
        for (String str : listOf) {
            try {
                Result.Companion companion = Result.Companion;
                obj2 = Result.constructor-impl(this.lm.getLastKnownLocation(str));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj2 = Result.constructor-impl(ResultKt.createFailure(th));
            }
            Location location2 = (Location) (Result.isFailure-impl(obj2) ? null : obj2);
            if (location2 != null) {
                arrayList.add(location2);
            }
        }
        List plus = CollectionsKt.plus(arrayList, CollectionsKt.listOfNotNull(this._location.getValue()));
        long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos();
        long currentTimeMillis = System.currentTimeMillis();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj3 : plus) {
            if (!z || Companion.isFreshForNavigation((Location) obj3, elapsedRealtimeNanos, currentTimeMillis)) {
                arrayList2.add(obj3);
            }
        }
        ArrayList arrayList3 = arrayList2;
        Iterator it = arrayList3.iterator();
        if (it.hasNext()) {
            Object next = it.next();
            if (it.hasNext()) {
                Location location3 = (Location) next;
                NavigationReliability navigationReliability2 = NavigationReliability.INSTANCE;
                Double valueOf = location3.hasAccuracy() ? Double.valueOf(location3.getAccuracy()) : null;
                Long access$fixAgeMs = Companion.access$fixAgeMs(Companion, location3, elapsedRealtimeNanos, currentTimeMillis);
                double routeOriginScore = navigationReliability2.routeOriginScore(valueOf, access$fixAgeMs != null ? access$fixAgeMs.longValue() : Long.MAX_VALUE);
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
                    ArrayList arrayList4 = arrayList3;
                    NavigationReliability navigationReliability4 = navigationReliability;
                    Long access$fixAgeMs2 = Companion.access$fixAgeMs(Companion, location, elapsedRealtimeNanos, currentTimeMillis);
                    double routeOriginScore2 = navigationReliability4.routeOriginScore(d, access$fixAgeMs2 != null ? access$fixAgeMs2.longValue() : Long.MAX_VALUE);
                    if (Double.compare(routeOriginScore, routeOriginScore2) > 0) {
                        routeOriginScore = routeOriginScore2;
                        next = next2;
                    }
                    if (!it.hasNext()) {
                        break;
                    }
                    arrayList3 = arrayList4;
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

    public static final boolean isFreshForNavigation(Location location, long j, long j2) {
        return Companion.isFreshForNavigation(location, j, j2);
    }

    static final void listener$lambda$2(LocationTracker locationTracker, Location location) {
        Intrinsics.checkNotNullParameter(location, "loc");
        Location location2 = (Location) locationTracker._location.getValue();
        if (!locationTracker.acceptFix(location2, location)) {
            Log.d(TAG, "REJECT " + location.getProvider() + " acc=" + location.getAccuracy() + " dt=" + (location2 != null ? Companion.access$elapsedDeltaSeconds(Companion, location2, location) : 0.0d) + "s");
            return;
        }
        locationTracker._location.setValue(location);
        NavPlus.onFix(location);
        Dbg dbg = Dbg.INSTANCE;
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "fix " + location.getProvider() + " acc=" + location.getAccuracy() + " (" + location.getLatitude() + "," + location.getLongitude() + ")");
        }
    }

    private final boolean reject() {
        this.rejectStreak++;
        return false;
    }

    public final StateFlow getLocation() {
        return this.location;
    }

    public final boolean isRunning() {
        return this.running;
    }

    public final Location lastKnown() {
        try {
            return bestKnown(true);
        } catch (Exception e) {
            return null;
        }
    }

    public final Location lastKnownAny() {
        try {
            return bestKnown(false);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final Location lastKnownForDisplay() {
        try {
            Location location = (Location) this._location.getValue();
            return location == null ? bestKnown(false) : location;
        } catch (Exception e) {
            return null;
        }
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
            for (String str : CollectionsKt.listOf(new String[]{"gps", "network"})) {
                if (this.lm.isProviderEnabled(str)) {
                    this.lm.requestLocationUpdates(str, 500L, 0.0f, this.listener, looper);
                }
            }
            LocPlus.addFused(this.lm, this.listener, looper);
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
}
