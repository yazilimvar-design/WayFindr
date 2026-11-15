package com.example.wayfindr.data;

import android.content.Context;
import android.util.Log;
import androidx.autofill.HintConstants;
import androidx.compose.runtime.ComposerKt;
import com.example.wayfindr.data.WayfindrDb;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: SyncRepository.kt */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000 X2\u00020\u0001:\u0001XB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\u0006\u0010\u0014\u001a\u00020\u0015J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017J\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0017J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0017J\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0017J\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0015J&\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00152\u0006\u0010#\u001a\u00020\u00152\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020\u0018J0\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020'2\u0006\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020%2\b\b\u0002\u0010/\u001a\u00020%JL\u00100\u001a\u00020\u00122\u0006\u00101\u001a\u00020'2\u0006\u00102\u001a\u00020'2\u0006\u00103\u001a\u00020'2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00172\u0006\u0010$\u001a\u00020%2\u0006\u0010#\u001a\u00020\u00152\u0006\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020'J\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\u0017J\u000e\u00109\u001a\u00020\u00122\u0006\u0010:\u001a\u000208J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020<0\u0017J\u001e\u0010=\u001a\u00020\u00122\u0006\u0010>\u001a\u00020'2\u0006\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020%J\u000e\u0010A\u001a\u00020\u00122\u0006\u0010B\u001a\u00020<J\f\u0010C\u001a\b\u0012\u0004\u0012\u00020D0\u0017J\u000e\u0010E\u001a\u00020\u00122\u0006\u0010F\u001a\u00020DJ\u000e\u0010G\u001a\u00020\u00122\u0006\u0010F\u001a\u00020DJ\u0006\u0010H\u001a\u00020IJ\u000e\u0010J\u001a\u00020\u00122\u0006\u0010K\u001a\u00020IJ\u000e\u0010L\u001a\u00020\u00122\u0006\u0010M\u001a\u00020\u001aJ&\u0010N\u001a\u00020\u00122\u0006\u0010+\u001a\u00020'2\u0006\u0010O\u001a\u00020\u00152\u0006\u0010P\u001a\u00020\u00152\u0006\u00106\u001a\u00020'J$\u0010Q\u001a\u00020\u00122\u0006\u0010+\u001a\u00020'2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020S0\u00172\u0006\u00106\u001a\u00020'J\u001e\u0010T\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020'2\u0006\u00106\u001a\u00020'J\u000e\u0010U\u001a\u00020\u00122\u0006\u0010B\u001a\u00020\u001cJ\u000e\u0010V\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\u001eJ\u000e\u0010W\u001a\u00020\u00122\u0006\u0010:\u001a\u00020\u001eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006Y"}, d2 = {"Lcom/example/wayfindr/data/SyncRepository;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "db", "Lcom/example/wayfindr/data/WayfindrDb;", "io", "Lkotlinx/coroutines/CoroutineScope;", "_revision", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "revision", "Lkotlinx/coroutines/flow/StateFlow;", "getRevision", "()Lkotlinx/coroutines/flow/StateFlow;", "bump", "", "reconcileMaintenanceDuplicates", "odometer", "", "fuelFills", "", "Lcom/example/wayfindr/data/FuelFillup;", "maintenanceItems", "Lcom/example/wayfindr/data/MaintenanceItem;", "savedLocations", "Lcom/example/wayfindr/data/SavedLocation;", "rides", "Lcom/example/wayfindr/data/Ride;", "setOdometer", "km", "addFuel", "litres", "cost", "odoKm", "", "location", "", "deleteFuel", "f", "addMaintenance", HintConstants.AUTOFILL_HINT_NAME, "icon", "intervalKm", "lastDoneOdoKm", "intervalMonths", "logVisit", "title", "kind", "scheduledKey", "items", "invoicePath", "note", "serviceRecords", "Lcom/example/wayfindr/data/ServiceRecord;", "deleteServiceRecord", "r", "scheduledServices", "Lcom/example/wayfindr/data/ScheduledService;", "addScheduledService", "label", "targetKm", "targetMonths", "deleteScheduledService", "s", "documents", "Lcom/example/wayfindr/data/VehicleDocument;", "upsertDocument", "d", "deleteDocument", "bikeIdentity", "Lcom/example/wayfindr/data/BikeIdentity;", "setBikeIdentity", "b", "deleteMaintenance", "m", "addSaved", "lat", "lng", "addSavedItinerary", "waypoints", "Lcom/example/wayfindr/data/SharedLocation;", "renameSaved", "deleteSaved", "addRide", "deleteRide", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class SyncRepository {
    private static final String TAG = "SyncRepository";
    private static volatile SyncRepository instance;
    private final MutableStateFlow<Long> _revision;
    private final WayfindrDb db;
    private final CoroutineScope io;
    private final StateFlow<Long> revision;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    public /* synthetic */ SyncRepository(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    /* compiled from: SyncRepository.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/example/wayfindr/data/SyncRepository$Companion;", "", "<init>", "()V", "TAG", "", "instance", "Lcom/example/wayfindr/data/SyncRepository;", "get", "context", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SyncRepository get(Context context) {
            SyncRepository syncRepository;
            Intrinsics.checkNotNullParameter(context, "context");
            SyncRepository syncRepository2 = SyncRepository.instance;
            if (syncRepository2 != null) {
                return syncRepository2;
            }
            synchronized (this) {
                syncRepository = SyncRepository.instance;
                if (syncRepository == null) {
                    Context applicationContext = context.getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
                    syncRepository = new SyncRepository(applicationContext, null);
                    Companion companion = SyncRepository.INSTANCE;
                    SyncRepository.instance = syncRepository;
                }
            }
            return syncRepository;
        }
    }

    private SyncRepository(Context context) {
        this.db = WayfindrDb.INSTANCE.get(context);
        this.io = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
        this._revision = StateFlowKt.MutableStateFlow(0L);
        this.revision = FlowKt.asStateFlow(this._revision);
        BuildersKt__Builders_commonKt.launch$default(this.io, null, null, new AnonymousClass1(null), 3, null);
    }

    public final StateFlow<Long> getRevision() {
        return this.revision;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bump() {
        MutableStateFlow<Long> mutableStateFlow = this._revision;
        mutableStateFlow.setValue(Long.valueOf(mutableStateFlow.getValue().longValue() + 1));
    }

    /* compiled from: SyncRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.data.SyncRepository$1", f = "SyncRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.data.SyncRepository$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SyncRepository.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    SyncRepository.this.reconcileMaintenanceDuplicates();
                    return Unit.INSTANCE;
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reconcileMaintenanceDuplicates() {
        WayfindrDb.DedupeResult dedupeMaintenance = this.db.dedupeMaintenance();
        List removed = dedupeMaintenance.component1();
        boolean changed = dedupeMaintenance.getChanged();
        if (changed) {
            Log.i(TAG, "maintenance dedupe: removed " + removed.size() + " stale row(s), realigned names/sids");
            bump();
        }
    }

    public final double odometer() {
        return this.db.odometer();
    }

    public final List<FuelFillup> fuelFills() {
        return this.db.fuelFills();
    }

    public final List<MaintenanceItem> maintenanceItems() {
        return this.db.maintenanceItems();
    }

    public final List<SavedLocation> savedLocations() {
        return this.db.savedLocations();
    }

    public final List<Ride> rides() {
        return this.db.rides();
    }

    public final void setOdometer(double km) {
        this.db.setOdometer(km);
        bump();
    }

    public final void addFuel(double litres, double cost, int odoKm, String location) {
        Intrinsics.checkNotNullParameter(location, "location");
        double prevOdo = this.db.odometer();
        FuelFillup f = new FuelFillup(0L, System.currentTimeMillis(), litres, cost, odoKm, location, WayfindrDb.INSTANCE.newSid(), 1, null);
        this.db.upsertFuel(f);
        if (odoKm > prevOdo) {
            this.db.setOdometer(odoKm);
        }
        bump();
    }

    public final void deleteFuel(FuelFillup f) {
        Intrinsics.checkNotNullParameter(f, "f");
        this.db.deleteFuelBySid(f.getSid());
        bump();
    }

    public static /* synthetic */ void addMaintenance$default(SyncRepository syncRepository, String str, String str2, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 16) != 0) {
            i3 = 0;
        }
        syncRepository.addMaintenance(str, str2, i, i2, i3);
    }

    public final void addMaintenance(String name, String icon, int intervalKm, int lastDoneOdoKm, int intervalMonths) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(icon, "icon");
        MaintenanceItem m = new MaintenanceItem(0L, name, icon, intervalKm, lastDoneOdoKm, System.currentTimeMillis(), intervalMonths, WayfindrDb.INSTANCE.newSid(), 1, null);
        this.db.upsertMaintenance(m);
        bump();
    }

    public final void logVisit(String title, String kind, String scheduledKey, List<MaintenanceItem> items, int odoKm, double cost, String invoicePath, String note) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(kind, "kind");
        Intrinsics.checkNotNullParameter(scheduledKey, "scheduledKey");
        Intrinsics.checkNotNullParameter(items, "items");
        Intrinsics.checkNotNullParameter(invoicePath, "invoicePath");
        Intrinsics.checkNotNullParameter(note, "note");
        long now = System.currentTimeMillis();
        Iterator it = items.iterator();
        while (it.hasNext()) {
            long now2 = now;
            this.db.upsertMaintenance(MaintenanceItem.copy$default((MaintenanceItem) it.next(), 0L, null, null, 0, odoKm, now2, 0, null, ComposerKt.reuseKey, null));
            now = now2;
        }
        long now3 = now;
        String newSid = WayfindrDb.INSTANCE.newSid();
        List<MaintenanceItem> list = items;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(((MaintenanceItem) it2.next()).getSid());
        }
        ServiceRecord rec = new ServiceRecord(0L, newSid, title, kind, scheduledKey, (List) arrayList, odoKm, now3, cost, invoicePath, note, 1, null);
        this.db.upsertServiceRecord(rec);
        bump();
    }

    public final List<ServiceRecord> serviceRecords() {
        return this.db.serviceRecords();
    }

    public final void deleteServiceRecord(ServiceRecord r) {
        Intrinsics.checkNotNullParameter(r, "r");
        this.db.deleteServiceRecordBySid(r.getSid());
        if (!StringsKt.isBlank(r.getInvoicePath())) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncRepository syncRepository = this;
                Result.m8392constructorimpl(Boolean.valueOf(new File(r.getInvoicePath()).delete()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
        }
        bump();
    }

    public final List<ScheduledService> scheduledServices() {
        return this.db.scheduledServices();
    }

    public final void addScheduledService(String label, int targetKm, int targetMonths) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.db.upsertScheduledService(new ScheduledService(0L, WayfindrDb.INSTANCE.newSid(), label, targetKm, targetMonths, false, 100, 1, null));
        bump();
    }

    public final void deleteScheduledService(ScheduledService s) {
        Intrinsics.checkNotNullParameter(s, "s");
        this.db.deleteScheduledServiceBySid(s.getSid());
        bump();
    }

    public final List<VehicleDocument> documents() {
        return this.db.documents();
    }

    public final void upsertDocument(VehicleDocument d) {
        Intrinsics.checkNotNullParameter(d, "d");
        this.db.upsertDocument(d);
        bump();
    }

    public final void deleteDocument(VehicleDocument d) {
        Intrinsics.checkNotNullParameter(d, "d");
        this.db.deleteDocumentBySid(d.getSid());
        if (!StringsKt.isBlank(d.getFilePath())) {
            try {
                Result.Companion companion = Result.INSTANCE;
                SyncRepository syncRepository = this;
                Result.m8392constructorimpl(Boolean.valueOf(new File(d.getFilePath()).delete()));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
        }
        bump();
    }

    public final BikeIdentity bikeIdentity() {
        return this.db.bikeIdentity();
    }

    public final void setBikeIdentity(BikeIdentity b) {
        Intrinsics.checkNotNullParameter(b, "b");
        this.db.setBikeIdentity(b);
        bump();
    }

    public final void deleteMaintenance(MaintenanceItem m) {
        Intrinsics.checkNotNullParameter(m, "m");
        this.db.deleteMaintenanceBySid(m.getSid());
        bump();
    }

    public final void addSaved(String name, double lat, double lng, String note) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(note, "note");
        SavedLocation s = new SavedLocation(0L, name, lat, lng, null, note, 0L, WayfindrDb.INSTANCE.newSid(), 81, null);
        this.db.upsertSaved(s);
        bump();
    }

    public final void addSavedItinerary(String name, List<SharedLocation> waypoints, String note) {
        Object obj;
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(waypoints, "waypoints");
        Intrinsics.checkNotNullParameter(note, "note");
        ListIterator listIterator = waypoints.listIterator(waypoints.size());
        while (true) {
            if (listIterator.hasPrevious()) {
                obj = listIterator.previous();
                SharedLocation sharedLocation = (SharedLocation) obj;
                if (((sharedLocation.getLat() == null || sharedLocation.getLng() == null) ? null : 1) != null) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        SharedLocation finalStop = (SharedLocation) obj;
        if (finalStop == null) {
            return;
        }
        Collection arrayList = new ArrayList();
        for (Object obj2 : waypoints) {
            SharedLocation sharedLocation2 = (SharedLocation) obj2;
            if (((sharedLocation2.getLat() == null || sharedLocation2.getLng() == null) ? null : 1) != null) {
                arrayList.add(obj2);
            }
        }
        List points = (List) arrayList;
        String newSid = WayfindrDb.INSTANCE.newSid();
        Double lat = finalStop.getLat();
        Intrinsics.checkNotNull(lat);
        double doubleValue = lat.doubleValue();
        Double lng = finalStop.getLng();
        Intrinsics.checkNotNull(lng);
        SavedLocation s = new SavedLocation(0L, name, doubleValue, lng.doubleValue(), points, note, 0L, newSid, 65, null);
        this.db.upsertSaved(s);
        bump();
    }

    public final void renameSaved(SavedLocation s, String name, String note) {
        Intrinsics.checkNotNullParameter(s, "s");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(note, "note");
        SavedLocation u = SavedLocation.copy$default(s, 0L, name, 0.0d, 0.0d, null, note, 0L, null, 221, null);
        this.db.upsertSaved(u);
        bump();
    }

    public final void deleteSaved(SavedLocation s) {
        Intrinsics.checkNotNullParameter(s, "s");
        this.db.deleteSavedBySid(s.getSid());
        bump();
    }

    public final void addRide(Ride r) {
        Intrinsics.checkNotNullParameter(r, "r");
        BuildersKt__Builders_commonKt.launch$default(this.io, null, null, new SyncRepository$addRide$1(this, r, null), 3, null);
    }

    public final void deleteRide(Ride r) {
        Intrinsics.checkNotNullParameter(r, "r");
        this.db.deleteRideBySid(r.getSid());
        bump();
    }
}
