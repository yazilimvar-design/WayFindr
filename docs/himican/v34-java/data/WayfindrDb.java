package com.example.wayfindr.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.autofill.HintConstants;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.example.wayfindr.dash.DashSocket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: WayfindrDb.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 F2\u00020\u0001:\u0002FGB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J \u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000f\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0010\u0010\u0010\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0012J\u000e\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0012J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018J\u000e\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0018J\u000e\u0010!\u001a\u00020\u00072\u0006\u0010\"\u001a\u00020 J\u000e\u0010#\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010$\u001a\u00020%J\f\u0010&\u001a\b\u0012\u0004\u0012\u00020'0\u0018J\u000e\u0010(\u001a\u00020\u00072\u0006\u0010)\u001a\u00020'J\u000e\u0010*\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0016\u0010+\u001a\u00020\u001e2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0018H\u0002J\u0018\u0010.\u001a\b\u0012\u0004\u0012\u00020-0\u00182\b\u0010/\u001a\u0004\u0018\u00010\u001eH\u0002J\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\u0018J\u000e\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u000201J\u000e\u00104\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u0018J\u000e\u00107\u001a\u00020\u00072\u0006\u00103\u001a\u000206J\u000e\u00108\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\f\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u0018J\u000e\u0010;\u001a\u00020\u00072\u0006\u0010)\u001a\u00020:J\u000e\u0010<\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\f\u0010=\u001a\b\u0012\u0004\u0012\u00020>0\u0018J\u000e\u0010?\u001a\u00020\u00072\u0006\u0010@\u001a\u00020>J\u000e\u0010A\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010B\u001a\u00020CJ\u000e\u0010D\u001a\u00020\u00072\u0006\u0010E\u001a\u00020C¨\u0006H"}, d2 = {"Lcom/example/wayfindr/data/WayfindrDb;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "onCreate", "", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "onDowngrade", "seedScheduledServices", "seedMaintenance", "odometer", "", "setOdometer", "km", "addToOdometer", "deltaKm", "fuelFills", "", "Lcom/example/wayfindr/data/FuelFillup;", "upsertFuel", "f", "deleteFuelBySid", "sid", "", "maintenanceItems", "Lcom/example/wayfindr/data/MaintenanceItem;", "upsertMaintenance", "m", "deleteMaintenanceBySid", "dedupeMaintenance", "Lcom/example/wayfindr/data/WayfindrDb$DedupeResult;", "savedLocations", "Lcom/example/wayfindr/data/SavedLocation;", "upsertSaved", "s", "deleteSavedBySid", "encodeWaypoints", "points", "Lcom/example/wayfindr/data/SharedLocation;", "decodeWaypoints", "value", "rides", "Lcom/example/wayfindr/data/Ride;", "upsertRide", "r", "deleteRideBySid", "serviceRecords", "Lcom/example/wayfindr/data/ServiceRecord;", "upsertServiceRecord", "deleteServiceRecordBySid", "scheduledServices", "Lcom/example/wayfindr/data/ScheduledService;", "upsertScheduledService", "deleteScheduledServiceBySid", "documents", "Lcom/example/wayfindr/data/VehicleDocument;", "upsertDocument", "d", "deleteDocumentBySid", "bikeIdentity", "Lcom/example/wayfindr/data/BikeIdentity;", "setBikeIdentity", "b", "Companion", "DedupeResult", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class WayfindrDb extends SQLiteOpenHelper {
    private static final String CREATE_BIKE_IDENTITY = "CREATE TABLE IF NOT EXISTS bike_identity(\n                 id INTEGER PRIMARY KEY,\n                 vin TEXT NOT NULL DEFAULT '',\n                 engine_no TEXT NOT NULL DEFAULT '',\n                 reg_no TEXT NOT NULL DEFAULT '',\n                 purchase_ms INTEGER NOT NULL DEFAULT 0,\n                 colour TEXT NOT NULL DEFAULT '')";
    private static final String CREATE_DOCUMENT = "CREATE TABLE IF NOT EXISTS document(\n                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n                 sid TEXT NOT NULL DEFAULT '',\n                 type TEXT NOT NULL DEFAULT 'other',\n                 title TEXT NOT NULL DEFAULT '',\n                 number TEXT NOT NULL DEFAULT '',\n                 issue_ms INTEGER NOT NULL DEFAULT 0,\n                 expiry_ms INTEGER NOT NULL DEFAULT 0,\n                 file_path TEXT NOT NULL DEFAULT '',\n                 note TEXT NOT NULL DEFAULT '')";
    private static final String CREATE_RIDE = "CREATE TABLE IF NOT EXISTS ride(\n                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n                 sid TEXT NOT NULL DEFAULT '',\n                 start_ms INTEGER NOT NULL,\n                 end_ms INTEGER NOT NULL,\n                 distance_m REAL NOT NULL,\n                 duration_s INTEGER NOT NULL,\n                 avg_speed REAL NOT NULL,\n                 max_speed REAL NOT NULL,\n                 track TEXT NOT NULL DEFAULT '',\n                 start_lat REAL NOT NULL DEFAULT 0,\n                 start_lng REAL NOT NULL DEFAULT 0,\n                 end_lat REAL NOT NULL DEFAULT 0,\n                 end_lng REAL NOT NULL DEFAULT 0)";
    private static final String CREATE_SCHEDULED_SERVICE = "CREATE TABLE IF NOT EXISTS scheduled_service(\n                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n                 sid TEXT NOT NULL DEFAULT '',\n                 label TEXT NOT NULL,\n                 target_km INTEGER NOT NULL DEFAULT 0,\n                 target_months INTEGER NOT NULL DEFAULT 0,\n                 free INTEGER NOT NULL DEFAULT 0,\n                 order_idx INTEGER NOT NULL DEFAULT 0)";
    private static final String CREATE_SERVICE_RECORD = "CREATE TABLE IF NOT EXISTS service_record(\n                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n                 sid TEXT NOT NULL DEFAULT '',\n                 item_sid TEXT NOT NULL DEFAULT '',\n                 name TEXT NOT NULL DEFAULT '',\n                 odometer_km INTEGER NOT NULL DEFAULT 0,\n                 date_ms INTEGER NOT NULL,\n                 cost REAL NOT NULL DEFAULT 0,\n                 invoice_path TEXT NOT NULL DEFAULT '',\n                 note TEXT NOT NULL DEFAULT '',\n                 title TEXT NOT NULL DEFAULT '',\n                 kind TEXT NOT NULL DEFAULT 'company',\n                 scheduled_key TEXT NOT NULL DEFAULT '',\n                 item_sids TEXT NOT NULL DEFAULT '')";
    private static final String DB_NAME = "wayfindr.db";
    private static final int DB_VERSION = 9;
    public static final double DEFAULT_ODOMETER = 325.0d;
    private static final List<Companion.Seed> SEED_ITEMS;
    private static volatile WayfindrDb instance;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final List<Companion.SchedSeed> FREE_SERVICES = CollectionsKt.listOf((Object[]) new Companion.SchedSeed[]{new Companion.SchedSeed("free-1", "1st service", 500, 2), new Companion.SchedSeed("free-2", "2nd service", DashSocket.RTP_PORT, 6), new Companion.SchedSeed("free-3", "3rd service", 10000, 12), new Companion.SchedSeed("free-4", "4th service", 15000, 18)});

    public /* synthetic */ WayfindrDb(Context context, DefaultConstructorMarker defaultConstructorMarker) {
        this(context);
    }

    private WayfindrDb(Context context) {
        super(context.getApplicationContext(), DB_NAME, (SQLiteDatabase.CursorFactory) null, 9);
    }

    /* compiled from: WayfindrDb.kt */
    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\u000f\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019¨\u0006\u001f"}, d2 = {"Lcom/example/wayfindr/data/WayfindrDb$Companion;", "", "<init>", "()V", "DB_NAME", "", "DB_VERSION", "", "instance", "Lcom/example/wayfindr/data/WayfindrDb;", "get", "context", "Landroid/content/Context;", "DEFAULT_ODOMETER", "", "newSid", "CREATE_RIDE", "CREATE_SERVICE_RECORD", "CREATE_SCHEDULED_SERVICE", "CREATE_DOCUMENT", "CREATE_BIKE_IDENTITY", "FREE_SERVICES", "", "Lcom/example/wayfindr/data/WayfindrDb$Companion$SchedSeed;", "getFREE_SERVICES", "()Ljava/util/List;", "SEED_ITEMS", "Lcom/example/wayfindr/data/WayfindrDb$Companion$Seed;", "getSEED_ITEMS", "SchedSeed", "Seed", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WayfindrDb get(Context context) {
            WayfindrDb wayfindrDb;
            Intrinsics.checkNotNullParameter(context, "context");
            WayfindrDb wayfindrDb2 = WayfindrDb.instance;
            if (wayfindrDb2 == null) {
                synchronized (this) {
                    wayfindrDb = WayfindrDb.instance;
                    if (wayfindrDb == null) {
                        wayfindrDb = new WayfindrDb(context, null);
                        Companion companion = WayfindrDb.INSTANCE;
                        WayfindrDb.instance = wayfindrDb;
                    }
                }
                return wayfindrDb;
            }
            return wayfindrDb2;
        }

        public final String newSid() {
            String uuid = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
            return uuid;
        }

        /* compiled from: WayfindrDb.kt */
        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J1\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/example/wayfindr/data/WayfindrDb$Companion$SchedSeed;", "", "sid", "", "label", "km", "", "months", "<init>", "(Ljava/lang/String;Ljava/lang/String;II)V", "getSid", "()Ljava/lang/String;", "getLabel", "getKm", "()I", "getMonths", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class SchedSeed {
            public static final int $stable = 0;
            private final int km;
            private final String label;
            private final int months;
            private final String sid;

            public static /* synthetic */ SchedSeed copy$default(SchedSeed schedSeed, String str, String str2, int i, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = schedSeed.sid;
                }
                if ((i3 & 2) != 0) {
                    str2 = schedSeed.label;
                }
                if ((i3 & 4) != 0) {
                    i = schedSeed.km;
                }
                if ((i3 & 8) != 0) {
                    i2 = schedSeed.months;
                }
                return schedSeed.copy(str, str2, i, i2);
            }

            /* renamed from: component1, reason: from getter */
            public final String getSid() {
                return this.sid;
            }

            /* renamed from: component2, reason: from getter */
            public final String getLabel() {
                return this.label;
            }

            /* renamed from: component3, reason: from getter */
            public final int getKm() {
                return this.km;
            }

            /* renamed from: component4, reason: from getter */
            public final int getMonths() {
                return this.months;
            }

            public final SchedSeed copy(String sid, String label, int km, int months) {
                Intrinsics.checkNotNullParameter(sid, "sid");
                Intrinsics.checkNotNullParameter(label, "label");
                return new SchedSeed(sid, label, km, months);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SchedSeed)) {
                    return false;
                }
                SchedSeed schedSeed = (SchedSeed) other;
                return Intrinsics.areEqual(this.sid, schedSeed.sid) && Intrinsics.areEqual(this.label, schedSeed.label) && this.km == schedSeed.km && this.months == schedSeed.months;
            }

            public int hashCode() {
                return (((((this.sid.hashCode() * 31) + this.label.hashCode()) * 31) + Integer.hashCode(this.km)) * 31) + Integer.hashCode(this.months);
            }

            public String toString() {
                return "SchedSeed(sid=" + this.sid + ", label=" + this.label + ", km=" + this.km + ", months=" + this.months + ")";
            }

            public SchedSeed(String sid, String label, int km, int months) {
                Intrinsics.checkNotNullParameter(sid, "sid");
                Intrinsics.checkNotNullParameter(label, "label");
                this.sid = sid;
                this.label = label;
                this.km = km;
                this.months = months;
            }

            public final int getKm() {
                return this.km;
            }

            public final String getLabel() {
                return this.label;
            }

            public final int getMonths() {
                return this.months;
            }

            public final String getSid() {
                return this.sid;
            }
        }

        public final List<SchedSeed> getFREE_SERVICES() {
            return WayfindrDb.FREE_SERVICES;
        }

        /* compiled from: WayfindrDb.kt */
        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003JK\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020\u0007HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\""}, d2 = {"Lcom/example/wayfindr/data/WayfindrDb$Companion$Seed;", "", "sid", "", HintConstants.AUTOFILL_HINT_NAME, "icon", "km", "", "months", "aliases", "", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/util/List;)V", "getSid", "()Ljava/lang/String;", "getName", "getIcon", "getKm", "()I", "getMonths", "getAliases", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
        public static final /* data */ class Seed {
            public static final int $stable = 8;
            private final List<String> aliases;
            private final String icon;
            private final int km;
            private final int months;
            private final String name;
            private final String sid;

            public static /* synthetic */ Seed copy$default(Seed seed, String str, String str2, String str3, int i, int i2, List list, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    str = seed.sid;
                }
                if ((i3 & 2) != 0) {
                    str2 = seed.name;
                }
                if ((i3 & 4) != 0) {
                    str3 = seed.icon;
                }
                if ((i3 & 8) != 0) {
                    i = seed.km;
                }
                if ((i3 & 16) != 0) {
                    i2 = seed.months;
                }
                if ((i3 & 32) != 0) {
                    list = seed.aliases;
                }
                int i4 = i2;
                List list2 = list;
                return seed.copy(str, str2, str3, i, i4, list2);
            }

            /* renamed from: component1, reason: from getter */
            public final String getSid() {
                return this.sid;
            }

            /* renamed from: component2, reason: from getter */
            public final String getName() {
                return this.name;
            }

            /* renamed from: component3, reason: from getter */
            public final String getIcon() {
                return this.icon;
            }

            /* renamed from: component4, reason: from getter */
            public final int getKm() {
                return this.km;
            }

            /* renamed from: component5, reason: from getter */
            public final int getMonths() {
                return this.months;
            }

            public final List<String> component6() {
                return this.aliases;
            }

            public final Seed copy(String sid, String name, String icon, int km, int months, List<String> aliases) {
                Intrinsics.checkNotNullParameter(sid, "sid");
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(icon, "icon");
                Intrinsics.checkNotNullParameter(aliases, "aliases");
                return new Seed(sid, name, icon, km, months, aliases);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Seed)) {
                    return false;
                }
                Seed seed = (Seed) other;
                return Intrinsics.areEqual(this.sid, seed.sid) && Intrinsics.areEqual(this.name, seed.name) && Intrinsics.areEqual(this.icon, seed.icon) && this.km == seed.km && this.months == seed.months && Intrinsics.areEqual(this.aliases, seed.aliases);
            }

            public int hashCode() {
                return (((((((((this.sid.hashCode() * 31) + this.name.hashCode()) * 31) + this.icon.hashCode()) * 31) + Integer.hashCode(this.km)) * 31) + Integer.hashCode(this.months)) * 31) + this.aliases.hashCode();
            }

            public String toString() {
                return "Seed(sid=" + this.sid + ", name=" + this.name + ", icon=" + this.icon + ", km=" + this.km + ", months=" + this.months + ", aliases=" + this.aliases + ")";
            }

            public Seed(String sid, String name, String icon, int km, int months, List<String> aliases) {
                Intrinsics.checkNotNullParameter(sid, "sid");
                Intrinsics.checkNotNullParameter(name, "name");
                Intrinsics.checkNotNullParameter(icon, "icon");
                Intrinsics.checkNotNullParameter(aliases, "aliases");
                this.sid = sid;
                this.name = name;
                this.icon = icon;
                this.km = km;
                this.months = months;
                this.aliases = aliases;
            }

            /* JADX WARN: Illegal instructions before constructor call */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public /* synthetic */ Seed(String str, String str2, String str3, int i, int i2, List list, int i3, DefaultConstructorMarker defaultConstructorMarker) {
                this(str, str2, str3, i, i2, r6);
                List list2;
                if ((i3 & 32) == 0) {
                    list2 = list;
                } else {
                    list2 = CollectionsKt.emptyList();
                }
            }

            public final String getIcon() {
                return this.icon;
            }

            public final int getKm() {
                return this.km;
            }

            public final int getMonths() {
                return this.months;
            }

            public final String getName() {
                return this.name;
            }

            public final String getSid() {
                return this.sid;
            }

            public final List<String> getAliases() {
                return this.aliases;
            }
        }

        public final List<Seed> getSEED_ITEMS() {
            return WayfindrDb.SEED_ITEMS;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static {
        int i = 0;
        SEED_ITEMS = CollectionsKt.listOf((Object[]) new Companion.Seed[]{new Companion.Seed("seed-chain", "Chain clean & lube", "chain", 500, 0, null, 32, null), new Companion.Seed("seed-oil", "Engine oil & filter", "drop", 10000, 12, CollectionsKt.listOf("Engine oil")), new Companion.Seed("seed-airfilter", "Air filter", "wrench", 10000, 0, 0 == true ? 1 : 0, 32, null), new Companion.Seed("seed-brakes", "Brake pads (front)", "gauge", DashSocket.RTP_PORT, 0, null, 32, null), new Companion.Seed("seed-valve", "Valve clearance", "wrench", AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH, 0, null, 32, null), new Companion.Seed("seed-sparkplug", "Spark plug", "wrench", AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH, i, null, 32, null), new Companion.Seed("seed-brakefluid", "Brake fluid", "drop", i, 24, null, 32, null), new Companion.Seed("seed-coolant", "Coolant", "thermo", 40000, 48, null, 32, null), new Companion.Seed("seed-throttlebody", "Throttle body clean", "wrench", 40000, 48, null, 32, null)});
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL("CREATE TABLE fuel_fillup(\n                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n                 sid TEXT NOT NULL DEFAULT '',\n                 date_ms INTEGER NOT NULL,\n                 litres REAL NOT NULL,\n                 cost REAL NOT NULL,\n                 odometer_km INTEGER NOT NULL,\n                 location TEXT NOT NULL DEFAULT '')");
        db.execSQL("CREATE TABLE maintenance_item(\n                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n                 sid TEXT NOT NULL DEFAULT '',\n                 name TEXT NOT NULL,\n                 icon_key TEXT NOT NULL,\n                 interval_km INTEGER NOT NULL,\n                 last_done_odo_km INTEGER NOT NULL,\n                 last_done_date_ms INTEGER NOT NULL,\n                 interval_months INTEGER NOT NULL DEFAULT 0)");
        db.execSQL("CREATE TABLE bike_state(id INTEGER PRIMARY KEY, odometer_km REAL NOT NULL)");
        db.execSQL("INSERT INTO bike_state(id, odometer_km) VALUES (0, 325.0)");
        db.execSQL("CREATE TABLE saved_location(\n                 id INTEGER PRIMARY KEY AUTOINCREMENT,\n                 sid TEXT NOT NULL DEFAULT '',\n                 name TEXT NOT NULL,\n                 lat REAL NOT NULL,\n                 lng REAL NOT NULL,\n                 note TEXT NOT NULL DEFAULT '',\n                 created_ms INTEGER NOT NULL,\n                 waypoints TEXT NOT NULL DEFAULT '')");
        db.execSQL(CREATE_RIDE);
        db.execSQL(CREATE_SERVICE_RECORD);
        db.execSQL(CREATE_SCHEDULED_SERVICE);
        db.execSQL(CREATE_DOCUMENT);
        db.execSQL(CREATE_BIKE_IDENTITY);
        db.execSQL("INSERT INTO bike_identity(id) VALUES (0)");
        seedMaintenance(db);
        seedScheduledServices(db);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        char c;
        int i;
        Intrinsics.checkNotNullParameter(db, "db");
        char c2 = 2;
        if (oldVersion < 2) {
            db.execSQL("CREATE TABLE IF NOT EXISTS saved_location(\n                     id INTEGER PRIMARY KEY AUTOINCREMENT,\n                     name TEXT NOT NULL, lat REAL NOT NULL, lng REAL NOT NULL,\n                     note TEXT NOT NULL DEFAULT '', created_ms INTEGER NOT NULL)");
        }
        int i2 = 1;
        if (oldVersion < 3) {
            for (String t : CollectionsKt.listOf((Object[]) new String[]{"fuel_fillup", "maintenance_item", "saved_location"})) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    WayfindrDb wayfindrDb = this;
                    db.execSQL("ALTER TABLE " + t + " ADD COLUMN sid TEXT NOT NULL DEFAULT ''");
                    Result.m8392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    Result.m8392constructorimpl(ResultKt.createFailure(th));
                }
                db.execSQL("UPDATE " + t + " SET sid = lower(hex(randomblob(16))) WHERE sid IS NULL OR sid = ''");
            }
        }
        if (oldVersion < 4) {
            db.execSQL(CREATE_RIDE);
        }
        if (oldVersion < 5) {
            db.execSQL("ALTER TABLE bike_state RENAME TO bike_state_old");
            db.execSQL("CREATE TABLE bike_state(id INTEGER PRIMARY KEY, odometer_km REAL NOT NULL)");
            db.execSQL("INSERT INTO bike_state(id, odometer_km) SELECT id, odometer_km FROM bike_state_old");
            db.execSQL("DROP TABLE bike_state_old");
        }
        if (oldVersion < 6) {
            try {
                Result.Companion companion3 = Result.INSTANCE;
                WayfindrDb wayfindrDb2 = this;
                db.execSQL("ALTER TABLE maintenance_item ADD COLUMN interval_months INTEGER NOT NULL DEFAULT 0");
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th2));
            }
            db.execSQL(CREATE_SERVICE_RECORD);
            for (Companion.Seed s : SEED_ITEMS) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("interval_km", Integer.valueOf(s.getKm()));
                contentValues.put("interval_months", Integer.valueOf(s.getMonths()));
                Unit unit = Unit.INSTANCE;
                String[] strArr = new String[i2];
                strArr[0] = s.getSid();
                int updated = db.update("maintenance_item", contentValues, "sid=?", strArr);
                if (updated == 0) {
                    ContentValues contentValues2 = new ContentValues();
                    char c3 = c2;
                    contentValues2.put("sid", s.getSid());
                    contentValues2.put(HintConstants.AUTOFILL_HINT_NAME, s.getName());
                    contentValues2.put("icon_key", s.getIcon());
                    contentValues2.put("interval_km", Integer.valueOf(s.getKm()));
                    contentValues2.put("interval_months", Integer.valueOf(s.getMonths()));
                    contentValues2.put("last_done_odo_km", (Integer) 0);
                    contentValues2.put("last_done_date_ms", Long.valueOf(System.currentTimeMillis()));
                    Unit unit2 = Unit.INSTANCE;
                    db.insert("maintenance_item", null, contentValues2);
                    c2 = c3;
                    i2 = i2;
                }
            }
            c = c2;
            i = i2;
        } else {
            c = 2;
            i = 1;
        }
        if (oldVersion < 7) {
            String[] strArr2 = new String[4];
            strArr2[0] = "title TEXT NOT NULL DEFAULT ''";
            strArr2[i] = "kind TEXT NOT NULL DEFAULT 'company'";
            strArr2[c] = "scheduled_key TEXT NOT NULL DEFAULT ''";
            strArr2[3] = "item_sids TEXT NOT NULL DEFAULT ''";
            for (String col : CollectionsKt.listOf((Object[]) strArr2)) {
                try {
                    Result.Companion companion5 = Result.INSTANCE;
                    WayfindrDb wayfindrDb3 = this;
                    db.execSQL("ALTER TABLE service_record ADD COLUMN " + col);
                    Result.m8392constructorimpl(Unit.INSTANCE);
                } catch (Throwable th3) {
                    Result.Companion companion6 = Result.INSTANCE;
                    Result.m8392constructorimpl(ResultKt.createFailure(th3));
                }
            }
            try {
                Result.Companion companion7 = Result.INSTANCE;
                WayfindrDb wayfindrDb4 = this;
                db.execSQL("UPDATE service_record SET title = name WHERE title = '' AND name <> ''");
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th4) {
                Result.Companion companion8 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th4));
            }
            try {
                Result.Companion companion9 = Result.INSTANCE;
                WayfindrDb wayfindrDb5 = this;
                db.execSQL("UPDATE service_record SET item_sids = item_sid WHERE item_sids = '' AND item_sid <> ''");
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th5) {
                Result.Companion companion10 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th5));
            }
            db.execSQL(CREATE_SCHEDULED_SERVICE);
            db.execSQL(CREATE_DOCUMENT);
            db.execSQL(CREATE_BIKE_IDENTITY);
            try {
                Result.Companion companion11 = Result.INSTANCE;
                WayfindrDb wayfindrDb6 = this;
                db.execSQL("INSERT INTO bike_identity(id) VALUES (0)");
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th6) {
                Result.Companion companion12 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th6));
            }
            seedScheduledServices(db);
        }
        if (oldVersion < 8) {
            try {
                Result.Companion companion13 = Result.INSTANCE;
                WayfindrDb wayfindrDb7 = this;
                db.execSQL("ALTER TABLE saved_location ADD COLUMN waypoints TEXT NOT NULL DEFAULT ''");
                Result.m8392constructorimpl(Unit.INSTANCE);
            } catch (Throwable th7) {
                Result.Companion companion14 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th7));
            }
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
    }

    private final void seedScheduledServices(SQLiteDatabase db) {
        int i = 0;
        for (Companion.SchedSeed s : FREE_SERVICES) {
            int i2 = i;
            i++;
            ContentValues contentValues = new ContentValues();
            contentValues.put("sid", s.getSid());
            contentValues.put("label", s.getLabel());
            contentValues.put("target_km", Integer.valueOf(s.getKm()));
            contentValues.put("target_months", Integer.valueOf(s.getMonths()));
            contentValues.put("free", (Integer) 1);
            contentValues.put("order_idx", Integer.valueOf(i2));
            Unit unit = Unit.INSTANCE;
            db.insert("scheduled_service", null, contentValues);
        }
    }

    private final void seedMaintenance(SQLiteDatabase db) {
        long now = System.currentTimeMillis();
        for (Companion.Seed s : SEED_ITEMS) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("sid", s.getSid());
            contentValues.put(HintConstants.AUTOFILL_HINT_NAME, s.getName());
            contentValues.put("icon_key", s.getIcon());
            contentValues.put("interval_km", Integer.valueOf(s.getKm()));
            contentValues.put("interval_months", Integer.valueOf(s.getMonths()));
            contentValues.put("last_done_odo_km", (Integer) 0);
            contentValues.put("last_done_date_ms", Long.valueOf(now));
            Unit unit = Unit.INSTANCE;
            db.insert("maintenance_item", null, contentValues);
        }
    }

    public final double odometer() {
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT odometer_km FROM bike_state WHERE id=0", null);
        try {
            Cursor cursor = rawQuery;
            double d = cursor.moveToFirst() ? cursor.getDouble(0) : 325.0d;
            CloseableKt.closeFinally(rawQuery, null);
            return d;
        } finally {
        }
    }

    public final void setOdometer(double km) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("odometer_km", Double.valueOf(km));
        Unit unit = Unit.INSTANCE;
        writableDatabase.update("bike_state", contentValues, "id=0", null);
    }

    public final void addToOdometer(double deltaKm) {
        getWritableDatabase().execSQL("UPDATE bike_state SET odometer_km = odometer_km + ? WHERE id=0", new Double[]{Double.valueOf(deltaKm)});
    }

    public final List<FuelFillup> fuelFills() {
        ArrayList out = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT id,sid,date_ms,litres,cost,odometer_km,location FROM fuel_fillup ORDER BY odometer_km DESC, date_ms DESC", null);
        try {
            Cursor cursor = rawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                long j2 = cursor.getLong(2);
                double d = cursor.getDouble(3);
                double d2 = cursor.getDouble(4);
                int i = cursor.getInt(5);
                String string2 = cursor.getString(6);
                if (string2 == null) {
                    string2 = "";
                }
                out.add(new FuelFillup(j, j2, d, d2, i, string2, string));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            return out;
        } finally {
        }
    }

    public final void upsertFuel(FuelFillup f) {
        Intrinsics.checkNotNullParameter(f, "f");
        ContentValues cv = new ContentValues();
        cv.put("sid", f.getSid());
        cv.put("date_ms", Long.valueOf(f.getDateMs()));
        cv.put("litres", Double.valueOf(f.getLitres()));
        cv.put("cost", Double.valueOf(f.getCost()));
        cv.put("odometer_km", Integer.valueOf(f.getOdometerKm()));
        cv.put("location", f.getLocation());
        if (getWritableDatabase().update("fuel_fillup", cv, "sid=?", new String[]{f.getSid()}) == 0) {
            getWritableDatabase().insert("fuel_fillup", null, cv);
        }
    }

    public final int deleteFuelBySid(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return getWritableDatabase().delete("fuel_fillup", "sid=?", new String[]{sid});
    }

    public final List<MaintenanceItem> maintenanceItems() {
        ArrayList out = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT id,sid,name,icon_key,interval_km,last_done_odo_km,last_done_date_ms,interval_months FROM maintenance_item ORDER BY id ASC", null);
        try {
            Cursor cursor = rawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                out.add(new MaintenanceItem(j, string2, string3, cursor.getInt(4), cursor.getInt(5), cursor.getLong(6), cursor.getInt(7), string));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            return out;
        } finally {
        }
    }

    public final void upsertMaintenance(MaintenanceItem m) {
        Intrinsics.checkNotNullParameter(m, "m");
        ContentValues cv = new ContentValues();
        cv.put("sid", m.getSid());
        cv.put(HintConstants.AUTOFILL_HINT_NAME, m.getName());
        cv.put("icon_key", m.getIconKey());
        cv.put("interval_km", Integer.valueOf(m.getIntervalKm()));
        cv.put("last_done_odo_km", Integer.valueOf(m.getLastDoneOdoKm()));
        cv.put("last_done_date_ms", Long.valueOf(m.getLastDoneDateMs()));
        cv.put("interval_months", Integer.valueOf(m.getIntervalMonths()));
        if (getWritableDatabase().update("maintenance_item", cv, "sid=?", new String[]{m.getSid()}) == 0) {
            getWritableDatabase().insert("maintenance_item", null, cv);
        }
    }

    public final int deleteMaintenanceBySid(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return getWritableDatabase().delete("maintenance_item", "sid=?", new String[]{sid});
    }

    /* compiled from: WayfindrDb.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00062\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0004HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/data/WayfindrDb$DedupeResult;", "", "removedSids", "", "", "changed", "", "<init>", "(Ljava/util/List;Z)V", "getRemovedSids", "()Ljava/util/List;", "getChanged", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class DedupeResult {
        public static final int $stable = 8;
        private final boolean changed;
        private final List<String> removedSids;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DedupeResult copy$default(DedupeResult dedupeResult, List list, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                list = dedupeResult.removedSids;
            }
            if ((i & 2) != 0) {
                z = dedupeResult.changed;
            }
            return dedupeResult.copy(list, z);
        }

        public final List<String> component1() {
            return this.removedSids;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getChanged() {
            return this.changed;
        }

        public final DedupeResult copy(List<String> removedSids, boolean changed) {
            Intrinsics.checkNotNullParameter(removedSids, "removedSids");
            return new DedupeResult(removedSids, changed);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DedupeResult)) {
                return false;
            }
            DedupeResult dedupeResult = (DedupeResult) other;
            return Intrinsics.areEqual(this.removedSids, dedupeResult.removedSids) && this.changed == dedupeResult.changed;
        }

        public int hashCode() {
            return (this.removedSids.hashCode() * 31) + Boolean.hashCode(this.changed);
        }

        public String toString() {
            return "DedupeResult(removedSids=" + this.removedSids + ", changed=" + this.changed + ")";
        }

        public DedupeResult(List<String> removedSids, boolean changed) {
            Intrinsics.checkNotNullParameter(removedSids, "removedSids");
            this.removedSids = removedSids;
            this.changed = changed;
        }

        public final boolean getChanged() {
            return this.changed;
        }

        public final List<String> getRemovedSids() {
            return this.removedSids;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final DedupeResult dedupeMaintenance() {
        HashMap hashMap;
        MaintenanceItem maintenanceItem;
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        List<Companion.Seed> list = SEED_ITEMS;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(list, 10)), 16));
        for (Object obj : list) {
            linkedHashMap.put(((Companion.Seed) obj).getSid(), obj);
        }
        HashMap hashMap2 = new HashMap();
        for (Companion.Seed seed : SEED_ITEMS) {
            hashMap2.put(seed.getName(), seed);
            Iterator<T> it = seed.getAliases().iterator();
            while (it.hasNext()) {
                hashMap2.put((String) it.next(), seed);
            }
        }
        HashMap hashMap3 = hashMap2;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        try {
            HashMap hashMap4 = new HashMap();
            for (MaintenanceItem maintenanceItem2 : maintenanceItems()) {
                try {
                    Companion.Seed seed2 = (Companion.Seed) linkedHashMap.get(maintenanceItem2.getSid());
                    if (seed2 != null || (seed2 = (Companion.Seed) hashMap3.get(maintenanceItem2.getName())) != null) {
                        HashMap hashMap5 = hashMap4;
                        String sid = seed2.getSid();
                        Object obj2 = hashMap5.get(sid);
                        if (obj2 == null) {
                            arrayList = new ArrayList();
                            hashMap5.put(sid, arrayList);
                        } else {
                            arrayList = obj2;
                        }
                        ((List) arrayList).add(maintenanceItem2);
                    }
                } catch (Throwable th) {
                    th = th;
                    writableDatabase.endTransaction();
                    throw th;
                }
            }
            for (Map.Entry entry : hashMap4.entrySet()) {
                String str = (String) entry.getKey();
                List list2 = (List) entry.getValue();
                Companion.Seed seed3 = (Companion.Seed) MapsKt.getValue(linkedHashMap, str);
                MaintenanceItem maintenanceItem3 = null;
                MaintenanceItem maintenanceItem4 = (MaintenanceItem) CollectionsKt.maxWithOrThrow(list2, ComparisonsKt.compareBy(new Function1() { // from class: com.example.wayfindr.data.WayfindrDb$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return WayfindrDb.dedupeMaintenance$lambda$21((MaintenanceItem) obj3);
                    }
                }, new Function1() { // from class: com.example.wayfindr.data.WayfindrDb$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return WayfindrDb.dedupeMaintenance$lambda$22((MaintenanceItem) obj3);
                    }
                }, new Function1() { // from class: com.example.wayfindr.data.WayfindrDb$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj3) {
                        return WayfindrDb.dedupeMaintenance$lambda$23((MaintenanceItem) obj3);
                    }
                }));
                ArrayList arrayList3 = new ArrayList();
                for (Object obj3 : list2) {
                    if ((((MaintenanceItem) obj3).getId() != maintenanceItem4.getId() ? 1 : maintenanceItem3) != null) {
                        maintenanceItem = maintenanceItem3;
                        arrayList3.add(obj3);
                    } else {
                        maintenanceItem = maintenanceItem3;
                    }
                    maintenanceItem3 = maintenanceItem;
                }
                Object obj4 = maintenanceItem3;
                Iterator it2 = arrayList3.iterator();
                while (true) {
                    try {
                        hashMap = hashMap4;
                        if (!it2.hasNext()) {
                            break;
                        }
                        MaintenanceItem maintenanceItem5 = (MaintenanceItem) it2.next();
                        HashMap hashMap6 = hashMap3;
                        try {
                            String[] strArr = new String[1];
                            strArr[obj4 == true ? 1 : 0] = String.valueOf(maintenanceItem5.getId());
                            writableDatabase.delete("maintenance_item", "id=?", strArr);
                            arrayList2.add(maintenanceItem5.getSid());
                            z = true;
                            hashMap4 = hashMap;
                            hashMap3 = hashMap6;
                        } catch (Throwable th2) {
                            th = th2;
                            writableDatabase.endTransaction();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                }
                boolean z2 = z;
                HashMap hashMap7 = hashMap3;
                if (Intrinsics.areEqual(maintenanceItem4.getSid(), seed3.getSid()) && Intrinsics.areEqual(maintenanceItem4.getName(), seed3.getName()) && Intrinsics.areEqual(maintenanceItem4.getIconKey(), seed3.getIcon())) {
                    hashMap4 = hashMap;
                    z = z2;
                    hashMap3 = hashMap7;
                }
                if (!Intrinsics.areEqual(maintenanceItem4.getSid(), seed3.getSid())) {
                    arrayList2.add(maintenanceItem4.getSid());
                }
                ContentValues contentValues = new ContentValues();
                contentValues.put("sid", seed3.getSid());
                contentValues.put(HintConstants.AUTOFILL_HINT_NAME, seed3.getName());
                contentValues.put("icon_key", seed3.getIcon());
                Unit unit = Unit.INSTANCE;
                String[] strArr2 = new String[1];
                strArr2[obj4 == true ? 1 : 0] = String.valueOf(maintenanceItem4.getId());
                writableDatabase.update("maintenance_item", contentValues, "id=?", strArr2);
                z = true;
                hashMap4 = hashMap;
                hashMap3 = hashMap7;
            }
            try {
                writableDatabase.setTransactionSuccessful();
                writableDatabase.endTransaction();
                return new DedupeResult(arrayList2, z);
            } catch (Throwable th4) {
                th = th4;
                writableDatabase.endTransaction();
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    static final Comparable dedupeMaintenance$lambda$21(MaintenanceItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Integer.valueOf(it.getLastDoneOdoKm());
    }

    static final Comparable dedupeMaintenance$lambda$22(MaintenanceItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(it.getLastDoneDateMs());
    }

    static final Comparable dedupeMaintenance$lambda$23(MaintenanceItem it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(-it.getId());
    }

    public final List<SavedLocation> savedLocations() {
        ArrayList out = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT id,sid,name,lat,lng,note,created_ms,waypoints FROM saved_location ORDER BY created_ms DESC", null);
        try {
            Cursor cursor = rawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                double d = cursor.getDouble(3);
                double d2 = cursor.getDouble(4);
                String string3 = cursor.getString(5);
                if (string3 == null) {
                    string3 = "";
                }
                out.add(new SavedLocation(j, string2, d, d2, decodeWaypoints(cursor.getString(7)), string3, cursor.getLong(6), string));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            return out;
        } finally {
        }
    }

    public final void upsertSaved(SavedLocation s) {
        Intrinsics.checkNotNullParameter(s, "s");
        ContentValues cv = new ContentValues();
        cv.put("sid", s.getSid());
        cv.put(HintConstants.AUTOFILL_HINT_NAME, s.getName());
        cv.put("lat", Double.valueOf(s.getLat()));
        cv.put("lng", Double.valueOf(s.getLng()));
        cv.put("note", s.getNote());
        cv.put("created_ms", Long.valueOf(s.getCreatedMs()));
        cv.put("waypoints", encodeWaypoints(s.getWaypoints()));
        if (getWritableDatabase().update("saved_location", cv, "sid=?", new String[]{s.getSid()}) == 0) {
            getWritableDatabase().insert("saved_location", null, cv);
        }
    }

    public final int deleteSavedBySid(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return getWritableDatabase().delete("saved_location", "sid=?", new String[]{sid});
    }

    private final String encodeWaypoints(List<SharedLocation> points) {
        if (points.isEmpty()) {
            return "";
        }
        JSONArray json = new JSONArray();
        for (SharedLocation sharedLocation : points) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HintConstants.AUTOFILL_HINT_NAME, sharedLocation.getName());
            Double lat = sharedLocation.getLat();
            if (lat != null) {
                jSONObject.put("lat", lat.doubleValue());
            }
            Double lng = sharedLocation.getLng();
            if (lng != null) {
                jSONObject.put("lng", lng.doubleValue());
            }
            json.put(jSONObject);
        }
        String jSONArray = json.toString();
        Intrinsics.checkNotNullExpressionValue(jSONArray, "toString(...)");
        return jSONArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001e A[Catch: all -> 0x0080, TryCatch #0 {all -> 0x0080, blocks: (B:3:0x0006, B:5:0x0012, B:10:0x001e, B:11:0x007b, B:19:0x0023, B:21:0x0035, B:23:0x003f, B:25:0x0045, B:27:0x0072, B:31:0x0075), top: B:2:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0023 A[Catch: all -> 0x0080, TryCatch #0 {all -> 0x0080, blocks: (B:3:0x0006, B:5:0x0012, B:10:0x001e, B:11:0x007b, B:19:0x0023, B:21:0x0035, B:23:0x003f, B:25:0x0045, B:27:0x0072, B:31:0x0075), top: B:2:0x0006 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final List<SharedLocation> decodeWaypoints(String value) {
        Object m8392constructorimpl;
        String str;
        boolean z;
        List build;
        try {
            Result.Companion companion = Result.INSTANCE;
            WayfindrDb wayfindrDb = this;
            str = value;
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (str != null && !StringsKt.isBlank(str)) {
            z = false;
            if (z) {
                JSONArray jSONArray = new JSONArray(value);
                List createListBuilder = CollectionsKt.createListBuilder();
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject.has("lat") && jSONObject.has("lng")) {
                        String optString = jSONObject.optString(HintConstants.AUTOFILL_HINT_NAME, "Saved stop");
                        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                        createListBuilder.add(new SharedLocation(optString, Double.valueOf(jSONObject.getDouble("lat")), Double.valueOf(jSONObject.getDouble("lng")), null, false, 24, null));
                    }
                }
                build = CollectionsKt.build(createListBuilder);
            } else {
                build = CollectionsKt.emptyList();
            }
            m8392constructorimpl = Result.m8392constructorimpl(build);
            List emptyList = CollectionsKt.emptyList();
            if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                m8392constructorimpl = emptyList;
            }
            return (List) m8392constructorimpl;
        }
        z = true;
        if (z) {
        }
        m8392constructorimpl = Result.m8392constructorimpl(build);
        List emptyList2 = CollectionsKt.emptyList();
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
        }
        return (List) m8392constructorimpl;
    }

    public final List<Ride> rides() {
        ArrayList out = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT id,sid,start_ms,end_ms,distance_m,duration_s,avg_speed,max_speed,track,start_lat,start_lng,end_lat,end_lng FROM ride ORDER BY start_ms DESC", null);
        try {
            Cursor cursor = rawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                long j2 = cursor.getLong(2);
                long j3 = cursor.getLong(3);
                double d = cursor.getDouble(4);
                long j4 = cursor.getLong(5);
                double d2 = cursor.getDouble(6);
                double d3 = cursor.getDouble(7);
                String string2 = cursor.getString(8);
                if (string2 == null) {
                    string2 = "";
                }
                out.add(new Ride(j, j2, j3, d, j4, d2, d3, string2, cursor.getDouble(9), cursor.getDouble(10), cursor.getDouble(11), cursor.getDouble(12), string));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            return out;
        } finally {
        }
    }

    public final void upsertRide(Ride r) {
        Intrinsics.checkNotNullParameter(r, "r");
        ContentValues cv = new ContentValues();
        cv.put("sid", r.getSid());
        cv.put("start_ms", Long.valueOf(r.getStartMs()));
        cv.put("end_ms", Long.valueOf(r.getEndMs()));
        cv.put("distance_m", Double.valueOf(r.getDistanceMeters()));
        cv.put("duration_s", Long.valueOf(r.getDurationSec()));
        cv.put("avg_speed", Double.valueOf(r.getAvgSpeedMps()));
        cv.put("max_speed", Double.valueOf(r.getMaxSpeedMps()));
        cv.put("track", r.getTrackPolyline());
        cv.put("start_lat", Double.valueOf(r.getStartLat()));
        cv.put("start_lng", Double.valueOf(r.getStartLng()));
        cv.put("end_lat", Double.valueOf(r.getEndLat()));
        cv.put("end_lng", Double.valueOf(r.getEndLng()));
        if (getWritableDatabase().update("ride", cv, "sid=?", new String[]{r.getSid()}) == 0) {
            getWritableDatabase().insert("ride", null, cv);
        }
    }

    public final int deleteRideBySid(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return getWritableDatabase().delete("ride", "sid=?", new String[]{sid});
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x0083, code lost:
    
        if (r13 == null) goto L31;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<ServiceRecord> serviceRecords() {
        String str;
        Iterable iterable;
        ArrayList out = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT id,sid,title,name,kind,scheduled_key,item_sids,item_sid,odometer_km,date_ms,cost,invoice_path,note FROM service_record ORDER BY date_ms DESC", null);
        try {
            Cursor cursor = rawQuery;
            int i = 0;
            while (cursor.moveToNext()) {
                String string = cursor.getString(2);
                if (StringsKt.isBlank(string)) {
                    String string2 = cursor.getString(3);
                    string = string2 == null ? "" : string2;
                }
                String str2 = string;
                String string3 = cursor.getString(6);
                if (string3 == null) {
                    string3 = "";
                }
                String str3 = string3;
                if (StringsKt.isBlank(str3)) {
                    String string4 = cursor.getString(7);
                    str3 = string4 == null ? "" : string4;
                }
                String str4 = str3;
                long j = cursor.getLong(0);
                String string5 = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
                Intrinsics.checkNotNull(str2);
                String string6 = cursor.getString(4);
                if (string6 != null) {
                    String str5 = string6;
                    if (StringsKt.isBlank(str5)) {
                        str5 = "company";
                    }
                    str = str5;
                }
                str = "company";
                String string7 = cursor.getString(5);
                if (string7 == null) {
                    string7 = "";
                }
                Iterable split$default = StringsKt.split$default((CharSequence) str4, new char[]{','}, false, 0, 6, (Object) null);
                int i2 = i;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
                Iterator it = split$default.iterator();
                while (it.hasNext()) {
                    arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
                }
                Iterable iterable2 = (List) arrayList;
                Collection arrayList2 = new ArrayList();
                for (Object obj : iterable2) {
                    if (((String) obj).length() > 0) {
                        iterable = iterable2;
                        arrayList2.add(obj);
                    } else {
                        iterable = iterable2;
                    }
                    iterable2 = iterable;
                }
                ArrayList arrayList3 = (List) arrayList2;
                int i3 = cursor.getInt(8);
                long j2 = cursor.getLong(9);
                double d = cursor.getDouble(10);
                String string8 = cursor.getString(11);
                String str6 = string8 == null ? "" : string8;
                String string9 = cursor.getString(12);
                out.add(new ServiceRecord(j, string5, str2, str, string7, arrayList3, i3, j2, d, str6, string9 == null ? "" : string9));
                i = i2;
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            return out;
        } finally {
        }
    }

    public final void upsertServiceRecord(ServiceRecord r) {
        Intrinsics.checkNotNullParameter(r, "r");
        ContentValues cv = new ContentValues();
        cv.put("sid", r.getSid());
        cv.put("title", r.getTitle());
        cv.put(HintConstants.AUTOFILL_HINT_NAME, r.getTitle());
        cv.put("kind", r.getKind());
        cv.put("scheduled_key", r.getScheduledKey());
        cv.put("item_sids", CollectionsKt.joinToString$default(r.getItemSids(), ",", null, null, 0, null, null, 62, null));
        String str = (String) CollectionsKt.firstOrNull((List) r.getItemSids());
        if (str == null) {
            str = "";
        }
        cv.put("item_sid", str);
        cv.put("odometer_km", Integer.valueOf(r.getOdometerKm()));
        cv.put("date_ms", Long.valueOf(r.getDateMs()));
        cv.put("cost", Double.valueOf(r.getCost()));
        cv.put("invoice_path", r.getInvoicePath());
        cv.put("note", r.getNote());
        if (getWritableDatabase().update("service_record", cv, "sid=?", new String[]{r.getSid()}) == 0) {
            getWritableDatabase().insert("service_record", null, cv);
        }
    }

    public final int deleteServiceRecordBySid(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return getWritableDatabase().delete("service_record", "sid=?", new String[]{sid});
    }

    public final List<ScheduledService> scheduledServices() {
        ArrayList out = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT id,sid,label,target_km,target_months,free,order_idx FROM scheduled_service ORDER BY order_idx ASC, target_km ASC", null);
        try {
            Cursor cursor = rawQuery;
            while (cursor.moveToNext()) {
                boolean z = false;
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                int i = cursor.getInt(3);
                int i2 = cursor.getInt(4);
                if (cursor.getInt(5) != 0) {
                    z = true;
                }
                out.add(new ScheduledService(j, string, string2, i, i2, z, cursor.getInt(6)));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            return out;
        } finally {
        }
    }

    public final void upsertScheduledService(ScheduledService s) {
        Intrinsics.checkNotNullParameter(s, "s");
        ContentValues contentValues = new ContentValues();
        contentValues.put("sid", s.getSid());
        contentValues.put("label", s.getLabel());
        contentValues.put("target_km", Integer.valueOf(s.getTargetKm()));
        contentValues.put("target_months", Integer.valueOf(s.getTargetMonths()));
        contentValues.put("free", Integer.valueOf(s.getFree() ? 1 : 0));
        contentValues.put("order_idx", Integer.valueOf(s.getOrderIdx()));
        if (getWritableDatabase().update("scheduled_service", contentValues, "sid=?", new String[]{s.getSid()}) == 0) {
            getWritableDatabase().insert("scheduled_service", null, contentValues);
        }
    }

    public final int deleteScheduledServiceBySid(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return getWritableDatabase().delete("scheduled_service", "sid=?", new String[]{sid});
    }

    public final List<VehicleDocument> documents() {
        ArrayList out = new ArrayList();
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT id,sid,type,title,number,issue_ms,expiry_ms,file_path,note FROM document ORDER BY expiry_ms ASC, title ASC", null);
        try {
            Cursor cursor = rawQuery;
            while (cursor.moveToNext()) {
                long j = cursor.getLong(0);
                String string = cursor.getString(1);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                String string2 = cursor.getString(2);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                String string3 = cursor.getString(3);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                String string4 = cursor.getString(4);
                if (string4 == null) {
                    string4 = "";
                }
                long j2 = cursor.getLong(5);
                long j3 = cursor.getLong(6);
                String string5 = cursor.getString(7);
                String str = string5 == null ? "" : string5;
                String string6 = cursor.getString(8);
                out.add(new VehicleDocument(j, string, string2, string3, string4, j2, j3, str, string6 == null ? "" : string6));
            }
            Unit unit = Unit.INSTANCE;
            CloseableKt.closeFinally(rawQuery, null);
            return out;
        } finally {
        }
    }

    public final void upsertDocument(VehicleDocument d) {
        Intrinsics.checkNotNullParameter(d, "d");
        ContentValues cv = new ContentValues();
        cv.put("sid", d.getSid());
        cv.put(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, d.getType());
        cv.put("title", d.getTitle());
        cv.put("number", d.getNumber());
        cv.put("issue_ms", Long.valueOf(d.getIssueMs()));
        cv.put("expiry_ms", Long.valueOf(d.getExpiryMs()));
        cv.put("file_path", d.getFilePath());
        cv.put("note", d.getNote());
        if (getWritableDatabase().update("document", cv, "sid=?", new String[]{d.getSid()}) == 0) {
            getWritableDatabase().insert("document", null, cv);
        }
    }

    public final int deleteDocumentBySid(String sid) {
        Intrinsics.checkNotNullParameter(sid, "sid");
        return getWritableDatabase().delete("document", "sid=?", new String[]{sid});
    }

    public final BikeIdentity bikeIdentity() {
        BikeIdentity bikeIdentity;
        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT vin,engine_no,reg_no,purchase_ms,colour FROM bike_identity WHERE id=0", null);
        try {
            Cursor cursor = rawQuery;
            if (cursor.moveToFirst()) {
                String string = cursor.getString(0);
                if (string == null) {
                    string = "";
                }
                String string2 = cursor.getString(1);
                if (string2 == null) {
                    string2 = "";
                }
                String string3 = cursor.getString(2);
                if (string3 == null) {
                    string3 = "";
                }
                long j = cursor.getLong(3);
                String string4 = cursor.getString(4);
                if (string4 == null) {
                    string4 = "";
                }
                bikeIdentity = new BikeIdentity(string, string2, string3, j, string4);
            } else {
                bikeIdentity = new BikeIdentity(null, null, null, 0L, null, 31, null);
            }
            CloseableKt.closeFinally(rawQuery, null);
            return bikeIdentity;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(rawQuery, th);
                throw th2;
            }
        }
    }

    public final void setBikeIdentity(BikeIdentity b) {
        Intrinsics.checkNotNullParameter(b, "b");
        ContentValues cv = new ContentValues();
        cv.put("vin", b.getVin());
        cv.put("engine_no", b.getEngineNo());
        cv.put("reg_no", b.getRegNo());
        cv.put("purchase_ms", Long.valueOf(b.getPurchaseMs()));
        cv.put("colour", b.getColour());
        if (getWritableDatabase().update("bike_identity", cv, "id=0", null) == 0) {
            cv.put("id", (Integer) 0);
            getWritableDatabase().insert("bike_identity", null, cv);
        }
    }
}
