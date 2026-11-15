package com.example.wayfindr.viewmodel;

import android.app.Application;
import android.location.Location;
import android.net.Uri;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelKt;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.dash.map.LocationTracker;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.data.SyncRepository;
import com.example.wayfindr.util.Dbg;
import com.example.wayfindr.util.IdleSleep;
import com.example.wayfindr.util.LocationParser;
import com.example.wayfindr.util.RouteShare;
import com.google.android.gms.actions.SearchIntents;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0013\b\u0007\u0018\u0000 [2\u00020\u0001:\u0001[B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020\u001fH\u0007J\b\u0010!\u001a\u00020\u001fH\u0014J\b\u0010\"\u001a\u00020\u0014H\u0002J\u0018\u0010#\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u00072\b\b\u0002\u0010%\u001a\u00020\u0007J\u001e\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u0007J\u000e\u0010(\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\u0019J\u000e\u0010)\u001a\u00020\u001f2\u0006\u0010'\u001a\u00020\u0019J\u000e\u0010*\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020\u0007J\u000e\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.J$\u0010/\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0018\u0001002\u0006\u00102\u001a\u00020\u0007H\u0082@¢\u0006\u0002\u00103J\u0016\u00104\u001a\u00020\u001f2\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0018H\u0003J\u0010\u00107\u001a\u00020\u001d2\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020\u001d2\u0006\u00108\u001a\u000209H\u0002J\u0006\u0010;\u001a\u00020\u001fJ\u000e\u0010<\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020>J\u0006\u0010?\u001a\u00020\u001fJ\u0006\u0010@\u001a\u00020\u001fJ\u0010\u0010A\u001a\u00020\n2\u0006\u0010B\u001a\u00020\nH\u0003J\u001e\u0010C\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0018\u0001002\u0006\u0010$\u001a\u00020\u0007H\u0002J\u0006\u0010D\u001a\u00020\u001fJ\u000e\u0010E\u001a\u00020\u001f2\u0006\u00102\u001a\u00020\u0007J\u000e\u0010F\u001a\u00020\u001f2\u0006\u0010G\u001a\u00020HJ\u0016\u0010I\u001a\u00020\u001f2\u0006\u0010J\u001a\u0002012\u0006\u0010K\u001a\u000201J\u001e\u0010L\u001a\u00020\u001f2\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u00182\u0006\u0010M\u001a\u00020\u001dH\u0002J\u001a\u0010N\u001a\u00020\u001f2\u0006\u00108\u001a\u0002062\b\b\u0002\u0010O\u001a\u00020\u001dH\u0002J\u000e\u0010P\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020>J\u0016\u0010Q\u001a\u00020\u001f2\u0006\u0010R\u001a\u00020>2\u0006\u0010S\u001a\u00020>J \u0010T\u001a\u0004\u0018\u00010\u00072\u0006\u0010J\u001a\u0002012\u0006\u0010K\u001a\u000201H\u0082@¢\u0006\u0002\u0010UJ\u0010\u0010V\u001a\u00020\u00072\u0006\u0010W\u001a\u000201H\u0002J\u0010\u0010X\u001a\u00020\u00072\u0006\u0010Y\u001a\u000201H\u0002J\u0010\u0010Z\u001a\u00020\u00072\u0006\u0010Y\u001a\u000201H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000eR\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"Lcom/example/wayfindr/viewmodel/RouteViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "app", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "TAG", "", "_state", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/wayfindr/viewmodel/RouteState;", "state", "Lkotlinx/coroutines/flow/StateFlow;", "getState", "()Lkotlinx/coroutines/flow/StateFlow;", "locationTracker", "Lcom/example/wayfindr/dash/map/LocationTracker;", "routeRequestGeneration", "", "routeRequestJob", "Lkotlinx/coroutines/Job;", "repo", "Lcom/example/wayfindr/data/SyncRepository;", "_saved", "", "Lcom/example/wayfindr/data/SavedLocation;", "saved", "getSaved", "gpsBeforeSleep", "", "startLocationUpdates", "", "refreshCurrentLocation", "onCleared", "reloadSaved", "saveCurrentDestination", HintConstants.AUTOFILL_HINT_NAME, "note", "renameSaved", "loc", "deleteSaved", "selectSaved", "handleSharedText", "text", "importQrImage", "uri", "Landroid/net/Uri;", "geocode", "Lkotlin/Pair;", "", SearchIntents.EXTRA_QUERY, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "computeRoute", "stops", "Lcom/example/wayfindr/data/SharedLocation;", "isPreciseRouteOrigin", "location", "Landroid/location/Location;", "isFallbackRouteOrigin", "recomputeRoute", "selectRoute", "index", "", "onNavigated", "clear", "freshState", "next", "savedByName", "clearDestinations", "search", "selectPlaceSuggestion", "hit", "Lcom/example/wayfindr/dash/nav/PlaceSearch$Hit;", "selectLocation", "lat", "lng", "loadSharedStops", "pendingNavigate", "addWaypoint", "resolving", "removeWaypoint", "moveWaypoint", "fromIndex", "toIndex", "reverseGeocode", "(DDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fmtKm", "m", "fmtDuration", "sec", "fmtEta", "Companion"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class RouteViewModel extends AndroidViewModel {

    @Deprecated
    public static final int MAX_STOPS = 26;
    private final String TAG;
    private final MutableStateFlow<List<SavedLocation>> _saved;
    private final MutableStateFlow<RouteState> _state;
    private boolean gpsBeforeSleep;
    private final LocationTracker locationTracker;
    private final SyncRepository repo;
    private long routeRequestGeneration;
    private Job routeRequestJob;
    private final StateFlow<List<SavedLocation>> saved;
    private final StateFlow<RouteState> state;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RouteViewModel(Application app) {
        super(app);
        Intrinsics.checkNotNullParameter(app, "app");
        this.TAG = "RouteViewModel";
        this._state = StateFlowKt.MutableStateFlow(new RouteState(null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131071, null));
        this.state = FlowKt.asStateFlow(this._state);
        this.locationTracker = new LocationTracker(app);
        this.repo = SyncRepository.INSTANCE.get(app);
        this._saved = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.saved = FlowKt.asStateFlow(this._saved);
        reloadSaved();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass1(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass2(null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new AnonymousClass3(null), 3, null);
    }

    public final StateFlow<RouteState> getState() {
        return this.state;
    }

    public final StateFlow<List<SavedLocation>> getSaved() {
        return this.saved;
    }

    /* compiled from: RouteViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$1", f = "RouteViewModel.kt", i = {}, l = {72}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.RouteViewModel$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RouteViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<Location> location = RouteViewModel.this.locationTracker.getLocation();
                    final RouteViewModel routeViewModel = RouteViewModel.this;
                    this.label = 1;
                    if (location.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.RouteViewModel.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit((Location) value, (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(Location location2, Continuation<? super Unit> continuation) {
                            if (location2 != null) {
                                RouteViewModel.this._state.setValue(RouteState.copy$default((RouteState) RouteViewModel.this._state.getValue(), null, null, TuplesKt.to(Boxing.boxDouble(location2.getLatitude()), Boxing.boxDouble(location2.getLongitude())), location2.hasBearing() ? location2.getBearing() : ((RouteState) RouteViewModel.this._state.getValue()).getCurrentBearing(), 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131059, null));
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: RouteViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$2", f = "RouteViewModel.kt", i = {}, l = {82}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.RouteViewModel$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RouteViewModel.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<Long> revision = RouteViewModel.this.repo.getRevision();
                    final RouteViewModel routeViewModel = RouteViewModel.this;
                    this.label = 1;
                    if (revision.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.RouteViewModel.2.1
                        public final Object emit(long it, Continuation<? super Unit> continuation) {
                            RouteViewModel.this.reloadSaved();
                            return Unit.INSTANCE;
                        }

                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit(((Number) value).longValue(), (Continuation<? super Unit>) $completion);
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: RouteViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$3", f = "RouteViewModel.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.RouteViewModel$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RouteViewModel.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    StateFlow<Boolean> asleep = IdleSleep.INSTANCE.getAsleep();
                    final RouteViewModel routeViewModel = RouteViewModel.this;
                    this.label = 1;
                    if (asleep.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.RouteViewModel.3.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                            return emit(((Boolean) value).booleanValue(), (Continuation<? super Unit>) $completion);
                        }

                        public final Object emit(boolean asleep2, Continuation<? super Unit> continuation) {
                            RouteViewModel routeViewModel2 = RouteViewModel.this;
                            if (asleep2) {
                                routeViewModel2.gpsBeforeSleep = RouteViewModel.this.locationTracker.getRunning();
                                RouteViewModel.this.locationTracker.stop();
                            } else if (routeViewModel2.gpsBeforeSleep) {
                                RouteViewModel.this.gpsBeforeSleep = false;
                                RouteViewModel.this.locationTracker.start();
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case 1:
                    ResultKt.throwOnFailure($result);
                    break;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            throw new KotlinNothingValueException();
        }
    }

    public final void startLocationUpdates() {
        this.locationTracker.start();
    }

    public final void refreshCurrentLocation() {
        this.locationTracker.start();
        Location location = this.locationTracker.lastKnown();
        if (location == null) {
            return;
        }
        this._state.setValue(RouteState.copy$default(this._state.getValue(), null, null, TuplesKt.to(Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude())), location.hasBearing() ? location.getBearing() : this._state.getValue().getCurrentBearing(), 1 + this._state.getValue().getLocationRefreshKey(), false, false, null, null, 0, false, null, null, null, null, null, false, 131043, null));
    }

    @Override // androidx.lifecycle.ViewModel
    protected void onCleared() {
        this.locationTracker.stop();
        super.onCleared();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Job reloadSaved() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$reloadSaved$1(this, null), 3, null);
        return launch$default;
    }

    public static /* synthetic */ void saveCurrentDestination$default(RouteViewModel routeViewModel, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = "";
        }
        routeViewModel.saveCurrentDestination(str, str2);
    }

    public final void saveCurrentDestination(String name, String note) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(note, "note");
        List<SharedLocation> waypoints = this._state.getValue().getWaypoints();
        if (waypoints.isEmpty()) {
            waypoints = CollectionsKt.listOfNotNull(this._state.getValue().getDestination());
        }
        Collection arrayList = new ArrayList();
        for (Object obj : waypoints) {
            SharedLocation sharedLocation = (SharedLocation) obj;
            if ((sharedLocation.getLat() == null || sharedLocation.getLng() == null) ? false : true) {
                arrayList.add(obj);
            }
        }
        List stops = (List) arrayList;
        if (stops.isEmpty()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$saveCurrentDestination$1(this, name, stops, note, null), 3, null);
        this._state.setValue(RouteState.copy$default(this._state.getValue(), null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 98303, null));
    }

    public final Job renameSaved(SavedLocation loc, String name, String note) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(loc, "loc");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(note, "note");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$renameSaved$1(this, loc, name, note, null), 3, null);
        return launch$default;
    }

    public final Job deleteSaved(SavedLocation loc) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(loc, "loc");
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$deleteSaved$1(this, loc, null), 3, null);
        return launch$default;
    }

    public final void selectSaved(SavedLocation loc) {
        Intrinsics.checkNotNullParameter(loc, "loc");
        List waypoints = loc.getWaypoints();
        if (waypoints.isEmpty()) {
            waypoints = CollectionsKt.listOf(new SharedLocation(loc.getName(), Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng()), null, false, 24, null));
        }
        List stops = waypoints;
        this._state.setValue(RouteState.copy$default(this._state.getValue(), (SharedLocation) CollectionsKt.last(stops), stops, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 98076, null));
        computeRoute(stops);
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00eb A[EDGE_INSN: B:31:0x00eb->B:8:0x00eb BREAK  A[LOOP:0: B:18:0x0031->B:32:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:? A[LOOP:0: B:18:0x0031->B:32:?, LOOP_END, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00de A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:45:? A[LOOP:1: B:34:0x0076->B:45:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void handleSharedText(String text) {
        boolean z;
        boolean z2;
        SharedLocation sharedLocation;
        Intrinsics.checkNotNullParameter(text, "text");
        RouteShare.SharedRoute decode = RouteShare.INSTANCE.decode(text);
        boolean z3 = true;
        if (decode == null) {
            List directStops = LocationParser.INSTANCE.parseStops(text);
            if (directStops.size() > 1) {
                loadSharedStops(directStops, true);
                return;
            }
            SharedLocation loc = LocationParser.INSTANCE.parse(text);
            this._state.setValue(freshState(new RouteState(loc, (loc.getLat() == null || loc.getLng() == null) ? CollectionsKt.emptyList() : CollectionsKt.listOf(loc), null, 0.0f, 0L, loc.getNeedsExpansion(), true, null, null, 0, false, null, null, null, null, null, false, 130972, null)));
            if (loc.getLat() != null && loc.getLng() != null) {
                computeRoute(CollectionsKt.listOf(loc));
                return;
            } else {
                if (loc.getUrl() != null) {
                    BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$handleSharedText$2(loc, this, null), 3, null);
                    return;
                }
                return;
            }
        }
        Iterable value = this._saved.getValue();
        if (!(value instanceof Collection) || !((Collection) value).isEmpty()) {
            Iterator it = value.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z3 = false;
                    break;
                }
                List stopsOf = RouteShare.INSTANCE.stopsOf((SavedLocation) it.next());
                if (stopsOf.size() == decode.getStops().size()) {
                    Iterable zip = CollectionsKt.zip(stopsOf, decode.getStops());
                    if (!(zip instanceof Collection) || !((Collection) zip).isEmpty()) {
                        Iterator it2 = zip.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                z2 = true;
                                break;
                            }
                            Pair pair = (Pair) it2.next();
                            SharedLocation sharedLocation2 = (SharedLocation) pair.component1();
                            SharedLocation sharedLocation3 = (SharedLocation) pair.component2();
                            Double lat = sharedLocation2.getLat();
                            Intrinsics.checkNotNull(lat);
                            double doubleValue = lat.doubleValue();
                            Double lat2 = sharedLocation3.getLat();
                            Intrinsics.checkNotNull(lat2);
                            if (Math.abs(doubleValue - lat2.doubleValue()) < 1.0E-5d) {
                                Double lng = sharedLocation2.getLng();
                                Intrinsics.checkNotNull(lng);
                                double doubleValue2 = lng.doubleValue();
                                Double lng2 = sharedLocation3.getLng();
                                Intrinsics.checkNotNull(lng2);
                                if (Math.abs(doubleValue2 - lng2.doubleValue()) < 1.0E-5d) {
                                    sharedLocation = 1;
                                    if (sharedLocation != null) {
                                        z2 = false;
                                        break;
                                    }
                                }
                            }
                            sharedLocation = null;
                            if (sharedLocation != null) {
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
            z3 = false;
        }
        Dbg dbg = Dbg.INSTANCE;
        String str = this.TAG;
        if (BuildConfig.DEBUG) {
            Log.i(str, "Wayfindr route '" + decode.getName() + "' (saved=" + z3 + "): " + CollectionsKt.joinToString$default(decode.getStops(), " → ", null, null, 0, null, new Function1() { // from class: com.example.wayfindr.viewmodel.RouteViewModel$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return RouteViewModel.handleSharedText$lambda$7$lambda$6$lambda$5((SharedLocation) obj);
                }
            }, 30, null));
        }
        this._state.setValue(freshState(new RouteState((SharedLocation) CollectionsKt.last((List) decode.getStops()), decode.getStops(), null, 0.0f, 0L, false, true, null, null, 0, false, null, null, null, null, z3 ? null : decode.getName(), true, 32700, null)));
        computeRoute(decode.getStops());
    }

    static final CharSequence handleSharedText$lambda$7$lambda$6$lambda$5(SharedLocation it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getName() + " " + it.getLat() + "," + it.getLng();
    }

    public final void importQrImage(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        this._state.setValue(RouteState.copy$default(this._state.getValue(), null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 114687, null));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$importQrImage$1(this, uri, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object geocode(String query, Continuation<? super Pair<Double, Double>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new RouteViewModel$geocode$2(this, query, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void computeRoute(List<SharedLocation> stops) {
        Job launch$default;
        Collection arrayList = new ArrayList();
        Iterator it = stops.iterator();
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
        List validStops = (List) arrayList;
        SharedLocation destination = (SharedLocation) CollectionsKt.lastOrNull(validStops);
        if (destination == null) {
            return;
        }
        this.locationTracker.start();
        this.routeRequestGeneration++;
        long requestId = this.routeRequestGeneration;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        Iterable<SharedLocation> dropLast = CollectionsKt.dropLast(validStops, 1);
        int i = 0;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(dropLast, 10));
        for (SharedLocation sharedLocation2 : dropLast) {
            Double lat = sharedLocation2.getLat();
            Intrinsics.checkNotNull(lat);
            long requestId2 = requestId;
            double doubleValue = lat.doubleValue();
            Double lng = sharedLocation2.getLng();
            Intrinsics.checkNotNull(lng);
            arrayList2.add(new GeoPoint(doubleValue, lng.doubleValue()));
            requestId = requestId2;
            destination = destination;
            i = i;
        }
        List waypoints = (List) arrayList2;
        this._state.setValue(RouteState.copy$default(this._state.getValue(), null, null, null, 0.0f, 0L, false, false, null, CollectionsKt.emptyList(), 0, true, null, null, null, null, null, false, 98431, null));
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$computeRoute$1(this, requestId, destination, waypoints, null), 3, null);
        this.routeRequestJob = launch$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isPreciseRouteOrigin(Location location) {
        return LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, location, 0L, 0L, 6, null) && location.hasAccuracy() && location.getAccuracy() <= 60.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isFallbackRouteOrigin(Location location) {
        return LocationTracker.Companion.isFreshForNavigation$default(LocationTracker.INSTANCE, location, 0L, 0L, 6, null) && (!location.hasAccuracy() || location.getAccuracy() <= 100.0f);
    }

    public final void recomputeRoute() {
        List waypoints = this._state.getValue().getWaypoints();
        if (waypoints.isEmpty()) {
            waypoints = CollectionsKt.listOfNotNull(this._state.getValue().getDestination());
        }
        List stops = waypoints;
        if (!stops.isEmpty()) {
            computeRoute(stops);
        }
    }

    public final void selectRoute(int index) {
        RouteState current = this._state.getValue();
        Route selected = (Route) CollectionsKt.getOrNull(current.getRouteAlternatives(), index);
        if (selected == null) {
            return;
        }
        this._state.setValue(RouteState.copy$default(current, null, null, null, 0.0f, 0L, false, false, selected, null, index, false, fmtKm(selected.getTotalMeters()), fmtDuration(selected.getTotalSeconds()), fmtEta(selected.getTotalSeconds()), null, null, false, 116095, null));
    }

    public final void onNavigated() {
        this._state.setValue(RouteState.copy$default(this._state.getValue(), null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131007, null));
    }

    public final void clear() {
        this.routeRequestGeneration++;
        Job job = this.routeRequestJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.routeRequestJob = null;
        this._state.setValue(freshState(new RouteState(null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131071, null)));
    }

    private final RouteState freshState(RouteState next) {
        RouteState old = this._state.getValue();
        Pair currentLocation = old.getCurrentLocation();
        if (currentLocation == null) {
            Location lastKnownForDisplay = this.locationTracker.lastKnownForDisplay();
            currentLocation = lastKnownForDisplay != null ? TuplesKt.to(Double.valueOf(lastKnownForDisplay.getLatitude()), Double.valueOf(lastKnownForDisplay.getLongitude())) : null;
        }
        Pair here = currentLocation;
        return RouteState.copy$default(next, null, null, here, old.getCurrentBearing(), old.getLocationRefreshKey(), false, false, null, null, 0, false, null, null, null, null, null, false, 131043, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Double, Double> savedByName(String name) {
        PlaceSearch.Hit savedMatch$app;
        String str = !StringsKt.isBlank(name) && !Intrinsics.areEqual(name, "Shared location") && !Intrinsics.areEqual(name, "Loading…") && !Intrinsics.areEqual(name, "Dropped pin") ? name : null;
        if (str == null || (savedMatch$app = PlaceSearch.INSTANCE.savedMatch$app(str, this._saved.getValue())) == null) {
            return null;
        }
        return TuplesKt.to(Double.valueOf(savedMatch$app.getLat()), Double.valueOf(savedMatch$app.getLng()));
    }

    public final void clearDestinations() {
        this._state.setValue(RouteState.copy$default(this._state.getValue(), null, CollectionsKt.emptyList(), null, 0.0f, 0L, false, false, null, CollectionsKt.emptyList(), 0, false, null, null, null, null, null, false, 81980, null));
    }

    public final void search(String query) {
        Intrinsics.checkNotNullParameter(query, "query");
        if (StringsKt.isBlank(query)) {
            return;
        }
        this._state.setValue(RouteState.copy$default(this._state.getValue(), null, null, null, 0.0f, 0L, true, false, null, null, 0, false, null, null, null, null, null, false, 113631, null));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$search$1(this, query, null), 3, null);
    }

    public final void selectPlaceSuggestion(PlaceSearch.Hit hit) {
        Intrinsics.checkNotNullParameter(hit, "hit");
        addWaypoint$default(this, new SharedLocation(hit.getName(), Double.valueOf(hit.getLat()), Double.valueOf(hit.getLng()), null, false, 24, null), false, 2, null);
    }

    public final void selectLocation(double lat, double lng) {
        addWaypoint(new SharedLocation("Dropped pin", Double.valueOf(lat), Double.valueOf(lng), null, false, 24, null), true);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$selectLocation$1(this, lat, lng, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadSharedStops(List<SharedLocation> stops, boolean pendingNavigate) {
        this._state.setValue(freshState(new RouteState((SharedLocation) CollectionsKt.last((List) stops), stops, null, 0.0f, 0L, true, pendingNavigate, null, null, 0, false, null, null, null, null, null, false, 130972, null)));
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new RouteViewModel$loadSharedStops$1(stops, this, null), 3, null);
    }

    static /* synthetic */ void addWaypoint$default(RouteViewModel routeViewModel, SharedLocation sharedLocation, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        routeViewModel.addWaypoint(sharedLocation, z);
    }

    private final void addWaypoint(SharedLocation location, boolean resolving) {
        List stops = CollectionsKt.plus((Collection<? extends SharedLocation>) this._state.getValue().getWaypoints(), location);
        if (stops.size() > 26) {
            Log.w(this.TAG, "Maximum of 26 stops reached");
            this._state.setValue(RouteState.copy$default(this._state.getValue(), null, null, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131039, null));
        } else {
            this._state.setValue(RouteState.copy$default(this._state.getValue(), location, stops, null, 0.0f, 0L, resolving, false, null, null, 0, false, null, null, null, null, null, false, 114460, null));
            if (resolving) {
                return;
            }
            computeRoute(stops);
        }
    }

    public final void removeWaypoint(int index) {
        List stops = CollectionsKt.toMutableList((Collection) this._state.getValue().getWaypoints());
        boolean z = false;
        if (index >= 0 && index < stops.size()) {
            z = true;
        }
        if (z) {
            stops.remove(index);
            this._state.setValue(RouteState.copy$default(this._state.getValue(), (SharedLocation) CollectionsKt.lastOrNull(stops), stops, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 116604, null));
            if (stops.isEmpty()) {
                return;
            }
            computeRoute(stops);
        }
    }

    public final void moveWaypoint(int fromIndex, int toIndex) {
        List stops = CollectionsKt.toMutableList((Collection) this._state.getValue().getWaypoints());
        if (fromIndex >= 0 && fromIndex < stops.size()) {
            if (!(toIndex >= 0 && toIndex < stops.size()) || fromIndex == toIndex) {
                return;
            }
            SharedLocation stop = stops.remove(fromIndex);
            stops.add(toIndex, stop);
            this._state.setValue(RouteState.copy$default(this._state.getValue(), (SharedLocation) CollectionsKt.lastOrNull(stops), stops, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 116604, null));
            computeRoute(stops);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object reverseGeocode(double lat, double lng, Continuation<? super String> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new RouteViewModel$reverseGeocode$2(this, lat, lng, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fmtKm(double m) {
        String format = String.format("%.0f km", Arrays.copyOf(new Object[]{Double.valueOf(m / 1000.0d)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fmtDuration(double sec) {
        StringBuilder append;
        int total = (int) (sec / 60.0d);
        if (total >= 60) {
            append = new StringBuilder().append(total / 60).append("h ").append(total % 60);
        } else {
            append = new StringBuilder().append(total);
        }
        return append.append("m").toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String fmtEta(double sec) {
        String format = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date(System.currentTimeMillis() + ((long) (1000.0d * sec))));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    /* compiled from: RouteViewModel.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/wayfindr/viewmodel/RouteViewModel$Companion;", "", "<init>", "()V", "MAX_STOPS", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
