package com.example.wayfindr.viewmodel;

import android.app.Application;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.map.LocationTracker;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.data.SyncRepository;
import com.example.wayfindr.util.Dbg;
import com.example.wayfindr.util.LocationParser;
import com.example.wayfindr.util.RouteShare;
import com.example.wayfindr.viewmodel.RouteViewModel$;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: RouteViewModel.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class RouteViewModel extends AndroidViewModel {
    public static final int MAX_STOPS = 26;
    private final String TAG;
    private final MutableStateFlow _saved;
    private final MutableStateFlow _state;
    private boolean gpsBeforeSleep;
    private final LocationTracker locationTracker;
    private final SyncRepository repo;
    private long routeRequestGeneration;
    private Job routeRequestJob;
    private final StateFlow saved;
    private final StateFlow state;
    private static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RouteViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "app");
        this.TAG = "RouteViewModel";
        this._state = StateFlowKt.MutableStateFlow(new RouteState((SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 131071, (DefaultConstructorMarker) null));
        this.state = FlowKt.asStateFlow(this._state);
        this.locationTracker = new LocationTracker(application);
        this.repo = SyncRepository.Companion.get(application);
        this._saved = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.saved = FlowKt.asStateFlow(this._saved);
        reloadSaved();
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 1(this, (Continuation) null), 3, (Object) null);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 2(this, (Continuation) null), 3, (Object) null);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new 3(this, (Continuation) null), 3, (Object) null);
    }

    private final void addWaypoint(SharedLocation sharedLocation, boolean z) {
        List plus = CollectionsKt.plus(((RouteState) this._state.getValue()).getWaypoints(), sharedLocation);
        if (plus.size() > 26) {
            Log.w(this.TAG, "Maximum of 26 stops reached");
            this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 131039, (Object) null));
        } else {
            this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), sharedLocation, plus, (Pair) null, 0.0f, 0L, z, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 114460, (Object) null));
            if (z) {
                return;
            }
            computeRoute(plus);
        }
    }

    static /* synthetic */ void addWaypoint$default(RouteViewModel routeViewModel, SharedLocation sharedLocation, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        routeViewModel.addWaypoint(sharedLocation, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void computeRoute(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            SharedLocation sharedLocation = (SharedLocation) next;
            if ((sharedLocation.getLat() == null || sharedLocation.getLng() == null) ? false : true) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = arrayList;
        SharedLocation sharedLocation2 = (SharedLocation) CollectionsKt.lastOrNull(arrayList2);
        if (sharedLocation2 == null) {
            return;
        }
        this.locationTracker.start();
        this.routeRequestGeneration++;
        long j = this.routeRequestGeneration;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        List<SharedLocation> dropLast = CollectionsKt.dropLast(arrayList2, 1);
        boolean z = false;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(dropLast, 10));
        for (SharedLocation sharedLocation3 : dropLast) {
            Double lat = sharedLocation3.getLat();
            Intrinsics.checkNotNull(lat);
            long j2 = j;
            double doubleValue = lat.doubleValue();
            Double lng = sharedLocation3.getLng();
            Intrinsics.checkNotNull(lng);
            arrayList3.add(new GeoPoint(doubleValue, lng.doubleValue()));
            j = j2;
            sharedLocation2 = sharedLocation2;
            z = z;
        }
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, CollectionsKt.emptyList(), 0, true, (String) null, (String) null, (String) null, (String) null, (String) null, false, 98431, (Object) null));
        this.routeRequestJob = BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new RouteViewModel$computeRoute$1(this, j, sharedLocation2, arrayList3, null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fmtDuration(double d) {
        StringBuilder append;
        int i = (int) (d / 60.0d);
        if (i >= 60) {
            append = new StringBuilder().append(i / 60).append("h ").append(i % 60);
        } else {
            append = new StringBuilder().append(i);
        }
        return append.append("m").toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fmtEta(double d) {
        String format = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(System.currentTimeMillis() + ((long) (1000.0d * d))));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fmtKm(double d) {
        String format = String.format("%.0f km", Arrays.copyOf(new Object[]{Double.valueOf(d / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final RouteState freshState(RouteState routeState) {
        RouteState routeState2 = (RouteState) this._state.getValue();
        Pair currentLocation = routeState2.getCurrentLocation();
        if (currentLocation == null) {
            Location lastKnownForDisplay = this.locationTracker.lastKnownForDisplay();
            currentLocation = lastKnownForDisplay != null ? TuplesKt.to(Double.valueOf(lastKnownForDisplay.getLatitude()), Double.valueOf(lastKnownForDisplay.getLongitude())) : null;
        }
        return RouteState.copy$default(routeState, (SharedLocation) null, (List) null, currentLocation, routeState2.getCurrentBearing(), routeState2.getLocationRefreshKey(), false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 131043, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object geocode(String str, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new geocode.2(this, str, (Continuation) null), continuation);
    }

    static final CharSequence handleSharedText$lambda$7$lambda$6$lambda$5(SharedLocation sharedLocation) {
        Intrinsics.checkNotNullParameter(sharedLocation, "it");
        return sharedLocation.getName() + " " + sharedLocation.getLat() + "," + sharedLocation.getLng();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFallbackRouteOrigin(Location location) {
        return NavPlus.fallbackOrigin(location);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPreciseRouteOrigin(Location location) {
        return LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.Companion, location, 0L, 0L, 6, (Object) null) && location.hasAccuracy() && location.getAccuracy() <= 60.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadSharedStops(List list, boolean z) {
        this._state.setValue(freshState(new RouteState((SharedLocation) CollectionsKt.last(list), list, (Pair) null, 0.0f, 0L, true, z, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 130972, (DefaultConstructorMarker) null)));
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new loadSharedStops.1(list, this, (Continuation) null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job reloadSaved() {
        return BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new reloadSaved.1(this, (Continuation) null), 3, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object reverseGeocode(double d, double d2, Continuation continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new reverseGeocode.2(this, d, d2, (Continuation) null), continuation);
    }

    public static /* synthetic */ void saveCurrentDestination$default(RouteViewModel routeViewModel, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        routeViewModel.saveCurrentDestination(str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair savedByName(String str) {
        PlaceSearch.Hit savedMatch$app;
        String str2 = !StringsKt.isBlank(str) && !Intrinsics.areEqual(str, "Shared location") && !Intrinsics.areEqual(str, "Loading…") && !Intrinsics.areEqual(str, "Dropped pin") ? str : null;
        if (str2 == null || (savedMatch$app = PlaceSearch.INSTANCE.savedMatch$app(str2, (List) this._saved.getValue())) == null) {
            return null;
        }
        return TuplesKt.to(Double.valueOf(savedMatch$app.getLat()), Double.valueOf(savedMatch$app.getLng()));
    }

    public final void clear() {
        this.routeRequestGeneration++;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.routeRequestJob = null;
        this._state.setValue(freshState(new RouteState((SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 131071, (DefaultConstructorMarker) null)));
    }

    public final void clearDestinations() {
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, CollectionsKt.emptyList(), (Pair) null, 0.0f, 0L, false, false, (Route) null, CollectionsKt.emptyList(), 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 81980, (Object) null));
    }

    public final Job deleteSaved(SavedLocation savedLocation) {
        Intrinsics.checkNotNullParameter(savedLocation, "loc");
        return BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new deleteSaved.1(this, savedLocation, (Continuation) null), 3, (Object) null);
    }

    public final StateFlow getSaved() {
        return this.saved;
    }

    public final StateFlow getState() {
        return this.state;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00eb A[EDGE_INSN: B:31:0x00eb->B:8:0x00eb BREAK  A[LOOP:0: B:18:0x0031->B:32:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[LOOP:0: B:18:0x0031->B:32:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00de A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[LOOP:1: B:34:0x0076->B:45:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleSharedText(String str) {
        boolean z;
        boolean z2;
        boolean z3;
        Intrinsics.checkNotNullParameter(str, "text");
        RouteShare.SharedRoute decode = RouteShare.INSTANCE.decode(str);
        boolean z4 = true;
        if (decode == null) {
            List parseStops = LocationParser.INSTANCE.parseStops(str);
            if (parseStops.size() > 1) {
                loadSharedStops(parseStops, true);
                return;
            }
            SharedLocation parse = LocationParser.INSTANCE.parse(str);
            this._state.setValue(freshState(new RouteState(parse, (parse.getLat() == null || parse.getLng() == null) ? CollectionsKt.emptyList() : CollectionsKt.listOf(parse), (Pair) null, 0.0f, 0L, parse.getNeedsExpansion(), true, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 130972, (DefaultConstructorMarker) null)));
            if (parse.getLat() != null && parse.getLng() != null) {
                computeRoute(CollectionsKt.listOf(parse));
                return;
            } else {
                if (parse.getUrl() != null) {
                    BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new handleSharedText.2(parse, this, (Continuation) null), 3, (Object) null);
                    return;
                }
                return;
            }
        }
        Iterable iterable = (Iterable) this._saved.getValue();
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            Iterator it = iterable.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z4 = false;
                    break;
                }
                List stopsOf = RouteShare.INSTANCE.stopsOf((SavedLocation) it.next());
                if (stopsOf.size() == decode.getStops().size()) {
                    List zip = CollectionsKt.zip(stopsOf, decode.getStops());
                    if (!(zip instanceof Collection) || !zip.isEmpty()) {
                        Iterator it2 = zip.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                z2 = true;
                                break;
                            }
                            Pair pair = (Pair) it2.next();
                            SharedLocation sharedLocation = (SharedLocation) pair.component1();
                            SharedLocation sharedLocation2 = (SharedLocation) pair.component2();
                            Double lat = sharedLocation.getLat();
                            Intrinsics.checkNotNull(lat);
                            double doubleValue = lat.doubleValue();
                            Double lat2 = sharedLocation2.getLat();
                            Intrinsics.checkNotNull(lat2);
                            if (Math.abs(doubleValue - lat2.doubleValue()) < 1.0E-5d) {
                                Double lng = sharedLocation.getLng();
                                Intrinsics.checkNotNull(lng);
                                double doubleValue2 = lng.doubleValue();
                                Double lng2 = sharedLocation2.getLng();
                                Intrinsics.checkNotNull(lng2);
                                if (Math.abs(doubleValue2 - lng2.doubleValue()) < 1.0E-5d) {
                                    z3 = true;
                                    if (z3) {
                                        z2 = false;
                                        break;
                                    }
                                }
                            }
                            z3 = false;
                            if (z3) {
                            }
                        }
                    } else {
                        z2 = true;
                    }
                    if (z2) {
                        z = true;
                        if (!z) {
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                }
            }
        } else {
            z4 = false;
        }
        Dbg dbg = Dbg.INSTANCE;
        String str2 = this.TAG;
        if (BuildConfig.DEBUG) {
            Log.i(str2, "Wayfindr route '" + decode.getName() + "' (saved=" + z4 + "): " + CollectionsKt.joinToString$default(decode.getStops(), " → ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new RouteViewModel$.ExternalSyntheticLambda0(), 30, (Object) null));
        }
        this._state.setValue(freshState(new RouteState((SharedLocation) CollectionsKt.last(decode.getStops()), decode.getStops(), (Pair) null, 0.0f, 0L, false, true, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, z4 ? null : decode.getName(), true, 32700, (DefaultConstructorMarker) null)));
        computeRoute(decode.getStops());
    }

    public final void importQrImage(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 114687, (Object) null));
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new importQrImage.1(this, uri, (Continuation) null), 3, (Object) null);
    }

    public final void moveWaypoint(int i, int i2) {
        List mutableList = CollectionsKt.toMutableList(((RouteState) this._state.getValue()).getWaypoints());
        if (i >= 0 && i < mutableList.size()) {
            if (!(i2 >= 0 && i2 < mutableList.size()) || i == i2) {
                return;
            }
            mutableList.add(i2, (SharedLocation) mutableList.remove(i));
            this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) CollectionsKt.lastOrNull(mutableList), mutableList, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 116604, (Object) null));
            computeRoute(mutableList);
        }
    }

    protected void onCleared() {
        this.locationTracker.stop();
        super.onCleared();
    }

    public final void onNavigated() {
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 131007, (Object) null));
    }

    public final void recomputeRoute() {
        List waypoints = ((RouteState) this._state.getValue()).getWaypoints();
        if (waypoints.isEmpty()) {
            waypoints = CollectionsKt.listOfNotNull(((RouteState) this._state.getValue()).getDestination());
        }
        List list = waypoints;
        if (list.isEmpty()) {
            return;
        }
        computeRoute(list);
    }

    public final void refreshCurrentLocation() {
        this.locationTracker.start();
        Location lastKnown = this.locationTracker.lastKnown();
        if (lastKnown == null) {
            return;
        }
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, (List) null, TuplesKt.to(Double.valueOf(lastKnown.getLatitude()), Double.valueOf(lastKnown.getLongitude())), lastKnown.hasBearing() ? lastKnown.getBearing() : ((RouteState) this._state.getValue()).getCurrentBearing(), 1 + ((RouteState) this._state.getValue()).getLocationRefreshKey(), false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 131043, (Object) null));
    }

    public final void removeWaypoint(int i) {
        List mutableList = CollectionsKt.toMutableList(((RouteState) this._state.getValue()).getWaypoints());
        boolean z = false;
        if (i >= 0 && i < mutableList.size()) {
            z = true;
        }
        if (z) {
            mutableList.remove(i);
            this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) CollectionsKt.lastOrNull(mutableList), mutableList, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 116604, (Object) null));
            if (mutableList.isEmpty()) {
                return;
            }
            computeRoute(mutableList);
        }
    }

    public final Job renameSaved(SavedLocation savedLocation, String str, String str2) {
        Intrinsics.checkNotNullParameter(savedLocation, "loc");
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "note");
        return BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new renameSaved.1(this, savedLocation, str, str2, (Continuation) null), 3, (Object) null);
    }

    public final void saveCurrentDestination(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "name");
        Intrinsics.checkNotNullParameter(str2, "note");
        List waypoints = ((RouteState) this._state.getValue()).getWaypoints();
        if (waypoints.isEmpty()) {
            waypoints = CollectionsKt.listOfNotNull(((RouteState) this._state.getValue()).getDestination());
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : waypoints) {
            SharedLocation sharedLocation = (SharedLocation) obj;
            if ((sharedLocation.getLat() == null || sharedLocation.getLng() == null) ? false : true) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return;
        }
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new saveCurrentDestination.1(this, str, arrayList2, str2, (Continuation) null), 3, (Object) null);
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 98303, (Object) null));
    }

    public final void search(String str) {
        Intrinsics.checkNotNullParameter(str, "query");
        if (StringsKt.isBlank(str)) {
            return;
        }
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, true, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 113631, (Object) null));
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new search.1(this, str, (Continuation) null), 3, (Object) null);
    }

    public final void selectLocation(double d, double d2) {
        addWaypoint(new SharedLocation("Dropped pin", Double.valueOf(d), Double.valueOf(d2), (String) null, false, 24, (DefaultConstructorMarker) null), true);
        BuildersKt.launch$default(ViewModelKt.getViewModelScope((ViewModel) this), (CoroutineContext) null, (CoroutineStart) null, new selectLocation.1(this, d, d2, (Continuation) null), 3, (Object) null);
    }

    public final void selectPlaceSuggestion(PlaceSearch.Hit hit) {
        Intrinsics.checkNotNullParameter(hit, "hit");
        addWaypoint$default(this, new SharedLocation(hit.getName(), Double.valueOf(hit.getLat()), Double.valueOf(hit.getLng()), (String) null, false, 24, (DefaultConstructorMarker) null), false, 2, null);
    }

    public final void selectRoute(int i) {
        RouteState routeState = (RouteState) this._state.getValue();
        Route route = (Route) CollectionsKt.getOrNull(routeState.getRouteAlternatives(), i);
        if (route == null) {
            return;
        }
        this._state.setValue(RouteState.copy$default(routeState, (SharedLocation) null, (List) null, (Pair) null, 0.0f, 0L, false, false, route, (List) null, i, false, fmtKm(route.getTotalMeters()), fmtDuration(route.getTotalSeconds()), fmtEta(route.getTotalSeconds()), (String) null, (String) null, false, 116095, (Object) null));
    }

    public final void selectSaved(SavedLocation savedLocation) {
        Intrinsics.checkNotNullParameter(savedLocation, "loc");
        List waypoints = savedLocation.getWaypoints();
        if (waypoints.isEmpty()) {
            waypoints = CollectionsKt.listOf(new SharedLocation(savedLocation.getName(), Double.valueOf(savedLocation.getLat()), Double.valueOf(savedLocation.getLng()), (String) null, false, 24, (DefaultConstructorMarker) null));
        }
        List list = waypoints;
        this._state.setValue(RouteState.copy$default((RouteState) this._state.getValue(), (SharedLocation) CollectionsKt.last(list), list, (Pair) null, 0.0f, 0L, false, false, (Route) null, (List) null, 0, false, (String) null, (String) null, (String) null, (String) null, (String) null, false, 98076, (Object) null));
        computeRoute(list);
    }

    public final void startLocationUpdates() {
        this.locationTracker.start();
    }
}
