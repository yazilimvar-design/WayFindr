package com.example.wayfindr.dash.nav;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.location.Location;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.core.content.FileProvider;
import btools.routingapp.IBRouterService;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.map.LocPlus;
import com.example.wayfindr.dash.map.LocationTracker;
import com.example.wayfindr.dash.map.MapRenderer;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.video.DashEncoder;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.media.MediaInfoProvider;
import com.example.wayfindr.media.NowPlaying;
import com.example.wayfindr.util.InternetNetwork;
import com.example.wayfindr.viewmodel.DashViewModel;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.io.ByteStreamsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class NavPlus {
    public static volatile List ALT_MARK = null;
    public static volatile double[] altArr = null;
    public static volatile long altSince = 0;
    public static volatile Object[] altStash = null;
    public static volatile long bearAt = 0;
    public static volatile List brPending = null;
    public static volatile boolean brWant = false;
    public static volatile float fAcc = 0.0f;
    public static volatile float fBear = 0.0f;
    public static volatile float fSpeed = 0.0f;
    public static volatile float fuBest = -1.0f;
    public static volatile boolean fuBusy = false;
    public static volatile double fuCLat = 0.0d;
    public static volatile double fuCLng = 0.0d;
    public static volatile Object fuData = null;
    public static volatile double fuLat = 0.0d;
    public static volatile double fuLng = 0.0d;
    public static volatile int fuN = 0;
    public static volatile String fuName = null;
    public static volatile long fuNextAt = 0;
    public static volatile float hdg = 0.0f;
    public static volatile long hdgAt = 0;
    public static volatile double hdgLat = 0.0d;
    public static volatile double hdgLng = 0.0d;
    public static volatile boolean hdgOk = false;
    public static volatile Paint hudBg = null;
    public static volatile boolean hudOn = true;
    /** Turn card in the frame instead of the dash's own round turn widget. */
    public static volatile boolean navCard = true;
    public static volatile Paint hudTx = null;
    public static volatile long mapWarnAt = 0;
    public static volatile boolean mbBusy = false;
    public static volatile SQLiteDatabase mbDb = null;
    public static volatile String mbErr = null;
    public static volatile int mbErrN = 0;
    public static volatile int mbHit = 0;
    public static volatile String mbInfo = null;
    public static volatile int mbMaxZ = 0;
    public static volatile int mbMinZ = 0;
    public static volatile int mbMiss = 0;
    public static volatile int mbState = 0;
    public static volatile boolean mbVec = false;
    public static volatile long netAt = 0;
    public static volatile int netOff = 0;
    public static volatile Object npNow = null;
    public static volatile long offAt = 0;
    public static volatile int offMode = 0;
    public static volatile boolean onRoute = false;
    public static volatile String pend1 = null;
    public static volatile String pend2 = null;
    public static volatile boolean phAlb = false;
    public static volatile Bitmap phBmp = null;
    public static volatile boolean phCfgOk = false;
    public static volatile boolean phMus = false;
    public static volatile boolean phOn = false;
    public static volatile Paint phPaint = null;
    public static volatile boolean phTried = false;
    public static volatile float phX = 0.5f;
    public static volatile float phY = 0.5f;
    public static volatile float phZ = 1.0f;
    public static volatile int plCount;
    public static volatile String plInfo;
    public static volatile ArrayList plNorm;
    public static volatile ArrayList plRank;
    public static volatile ArrayList plRaw;
    public static volatile int plState;
    public static volatile float predHdg;
    public static volatile long quietAt;
    public static volatile boolean rdBusy;
    public static volatile double rdCLat;
    public static volatile double rdCLng;
    public static volatile Object rdData;
    public static volatile int rdAheadM = -1;   // nearest approaching speed camera (m), for the on-map pill
    public static volatile long rdAheadAt;
    public static volatile int rdN;
    public static volatile long rdNextAt;
    public static volatile boolean roadBusy;
    public static volatile double roadLat;
    public static volatile double roadLng;
    public static volatile int roadMirror;
    public static volatile int roadPoly;
    public static volatile double roadR;
    public static volatile long roadRetry;
    public static volatile ArrayList roads;
    public static volatile ByteArrayOutputStream rsBuf;
    public static volatile HashMap rsCache;
    public static volatile boolean rtForced;
    public static volatile RoutingSource rtUser;
    public static volatile double[] snapArr;
    public static volatile long snapAt;
    public static volatile double snapLat;
    public static volatile double snapLng;
    public static volatile long spdAt;
    public static volatile boolean spdBusy;
    public static volatile int spdLimit;
    public static volatile long spdShowUntil;   // sign shows 10 s after the limit changes
    public static volatile long spdOverAt;      // last time our speed was above the limit
    public static volatile long spdNextAt;
    public static volatile long spdWarnAt;
    public static volatile float turn;
    public static volatile long wxAt;
    public static volatile long wxShowUntil;
    public static volatile int wxDay = 1;
    public static volatile boolean gSpeeding;   // Google Navigation SDK: rider is over the limit
    public static volatile long gSpeedingAt;
    public static volatile boolean wxBusy;
    public static volatile double wxCLat;
    public static volatile double wxCLng;
    public static volatile int wxCode;
    public static volatile long wxNextAt;
    public static volatile int wxRain;
    public static volatile long wxStartAt;
    public static volatile float wxTemp;
    public static volatile long wxWarnAt;

    public static final class BrJob implements Runnable {
        public final Bundle b;
        public final int idx;
        public volatile Route res;
        public final List rest;
        public final IBRouterService svc;

        public BrJob(IBRouterService iBRouterService, Bundle bundle, int i, List list) {
            this.svc = iBRouterService;
            this.b = bundle;
            this.idx = i;
            this.rest = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.res = OfflineRouter.navAlt1(this.svc, this.b, this.idx, this.rest);
            } catch (Throwable unused) {
            }
        }
    }

    static final class Export implements Runnable {
        private final File dir;

        public Export(File file) {
            this.dir = file;
        }

        @Override // java.lang.Runnable
        public void run() {
            NavPlus.exportNow(this.dir);
        }
    }

    static final class Idx implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            try {
                NavPlus.plBuild();
            } catch (Throwable unused) {
            }
        }
    }

    static final class RdFetch implements Runnable {
        private final double lat;
        private final double lng;
        private final int mode;

        public RdFetch(double d, double d2, int i) {
            this.lat = d;
            this.lng = d2;
            this.mode = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            double d = this.lat;
            double d2 = this.lng;
            int i = this.mode;
            if (i == 2) {
                try {
                    NavPlus.wxFetch(d, d2);
                } catch (Throwable unused) {
                }
                NavPlus.wxBusy = false;
            } else if (i == 3) {
                try {
                    NavPlus.fuFetch(d, d2);
                } catch (Throwable unused2) {
                }
                NavPlus.fuBusy = false;
            } else {
                try {
                    NavPlus.rdFetch(d, d2);
                } catch (Throwable unused3) {
                }
                NavPlus.rdBusy = false;
            }
        }
    }

    static final class RoadFetch implements Runnable {
        private final double lat;
        private final double lng;
        private final double r;

        public RoadFetch(double d, double d2, double d3) {
            this.lat = d;
            this.lng = d2;
            this.r = d3;
        }

        @Override // java.lang.Runnable
        public void run() {
            NavPlus.fetchRoads(this.lat, this.lng, this.r);
        }
    }

    static final class SpdFetch implements Runnable {
        private final double lat;
        private final double lng;

        public SpdFetch(double d, double d2) {
            this.lat = d;
            this.lng = d2;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                NavPlus.fetchLimit(this.lat, this.lng);
            } catch (Throwable unused) {
            }
            NavPlus.spdBusy = false;
        }
    }

    static final class Vx {
        public Canvas c;
        public int ck;
        public byte[] d;
        public float k;
        public ArrayList keys;
        public int kind;
        public Paint p;
        public int pass;
        public Path path;
        public int[] pos;
        public float s;
        public StringBuilder sb;
        public int tx;
        public int ty;
        public ArrayList vals;
        public int z;
        public float zf;
    }

    public static void addGoogleHeading(JSONObject jSONObject, GeoPoint geoPoint) {
        JSONObject optJSONObject;
        if (jSONObject != null) {
            try {
                int headingFor = headingFor(geoPoint);
                if (headingFor < 0 || (optJSONObject = jSONObject.optJSONObject("location")) == null) {
                    return;
                }
                optJSONObject.put("heading", headingFor);
                logHeading(headingFor);
            } catch (Throwable unused) {
            }
        }
    }

    public static void addHeading(JSONArray jSONArray, GeoPoint geoPoint) {
        JSONObject optJSONObject;
        if (jSONArray != null) {
            try {
                int headingFor = headingFor(geoPoint);
                if (headingFor < 0 || (optJSONObject = jSONArray.optJSONObject(0)) == null) {
                    return;
                }
                optJSONObject.put("heading", headingFor);
                optJSONObject.put("heading_tolerance", 60);
                logHeading(headingFor);
            } catch (Throwable unused) {
            }
        }
    }

    public static List aheadGeometry(Route route, double d, Double d2, Double d3) {
        List<GeoPoint> geometry = route.getGeometry();
        if (geometry != null) {
            try {
                double cutPos = cutPos(route, d, d2, d3);
                if (cutPos > 0.0d) {
                    int size = geometry.size();
                    int i = (int) cutPos;
                    double d4 = cutPos - i;
                    int i2 = size - 1;
                    if (i >= i2) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(geometry.get(i2));
                        return arrayList;
                    }
                    GeoPoint geoPoint = geometry.get(i);
                    int i3 = i + 1;
                    GeoPoint geoPoint2 = geometry.get(i3);
                    double lat = geoPoint.getLat();
                    double lat2 = lat + ((geoPoint2.getLat() - lat) * d4);
                    double lng = geoPoint.getLng();
                    GeoPoint geoPoint3 = new GeoPoint(lat2, lng + ((geoPoint2.getLng() - lng) * d4));
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(geoPoint3);
                    arrayList2.addAll(geometry.subList(i3, size));
                    return arrayList2;
                }
            } catch (Throwable unused) {
                return geometry;
            }
        }
        return geometry;
    }

    public static List aheadTraffic(Route route, double d, Double d2, Double d3) {
        List<TrafficInterval> traffic = route.getTraffic();
        if (traffic != null) {
            try {
                if (!traffic.isEmpty()) {
                    double cutPos = cutPos(route, d, d2, d3);
                    if (cutPos > 0.0d) {
                        int i = (int) cutPos;
                        ArrayList arrayList = new ArrayList();
                        for (TrafficInterval trafficInterval : traffic) {
                            int startPointIndex = trafficInterval.getStartPointIndex() - i;
                            int endPointIndex = trafficInterval.getEndPointIndex() - i;
                            if (endPointIndex > 0) {
                                if (startPointIndex < 0) {
                                    startPointIndex = 0;
                                }
                                arrayList.add(new TrafficInterval(startPointIndex, endPointIndex, trafficInterval.getSpeed()));
                            }
                        }
                        return arrayList;
                    }
                }
            } catch (Throwable unused) {
                return traffic;
            }
        }
        return traffic;
    }

    public static List altMarker(List list) {
        if (list != null) {
            try {
                if (list.isEmpty()) {
                    List list2 = ALT_MARK;
                    if (list2 != null) {
                        return list2;
                    }
                    List unmodifiableList = Collections.unmodifiableList(new ArrayList());
                    ALT_MARK = unmodifiableList;
                    return unmodifiableList;
                }
            } catch (Throwable unused) {
                return list;
            }
        }
        return list;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
    
        if (r4 < (-100.0d)) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Pair arcProject(double d, double d2, double d3, double d4) {
        try {
            double d5 = fSpeed;
            if (d5 < 1.0d) {
                d5 = 1.0d;
            }
            double d6 = turn * (d4 / d5);
            if (d6 > 100.0d) d6 = 100.0d;
            if (d6 < -100.0d) d6 = -100.0d;
            predHdg = (float) (d3 + d6);
            double d8 = d4;
            double d9 = d3;
            if (Math.abs(d6) >= 0.5d) {
                double d10 = d6 / 2.0d;
                d9 += d10;
                double d11 = d10 * 0.017453292519943295d;
                d8 *= Math.sin(d11) / d11;
            }
            double d12 = d9 * 0.017453292519943295d;
            double cos = d + ((Math.cos(d12) * d8) / 111195.07973436d);
            double sin = Math.sin(d12) * d8;
            double cos2 = Math.cos(d * 0.017453292519943295d);
            if (cos2 < 0.01d) {
                cos2 = 0.01d;
            }
            return TuplesKt.to(Double.valueOf(cos), Double.valueOf(d2 + (sin / (cos2 * 111195.07973436d))));
        } catch (Throwable unused) {
            return TuplesKt.to(Double.valueOf(d), Double.valueOf(d2));
        }
    }

    public static Route asBRouter(Route route) {
        return route.withSource(RoutingSource.BROUTER);
    }

    public static Route asGoogle(Route route) {
        return route.withSource(RoutingSource.GOOGLE);
    }

    public static String avgKmh(Object obj) {
        return String.valueOf(Math.round((rowD(obj, 1) / Math.max(rowL(obj, 2), 1.0d)) * 3.6d)).concat(" km/sa");
    }

    public static void brAttach(Route route) {
        try {
            List list = brPending;
            brPending = null;
            if (list == null || route == null) {
                return;
            }
            altStash = new Object[]{route.getGeometry(), list};
        } catch (Throwable unused) {
        }
    }

    public static boolean brDistinct(Route route, List list) {
        if (route == null || list == null) {
            return false;
        }
        try {
            double totalSeconds = route.getTotalSeconds();
            double totalMeters = route.getTotalMeters();
            int size = list.size();
            if (size == 0) {
                return true;
            }
            if (totalSeconds > ((Route) list.get(0)).getTotalSeconds() * 2.0d) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                Route route2 = (Route) list.get(i);
                if (Math.abs(route2.getTotalMeters() - totalMeters) <= 0.005d * totalMeters && Math.abs(route2.getTotalSeconds() - totalSeconds) <= 0.005d * totalSeconds) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void brWantFor(List list) {
        boolean z = false;
        List list2 = ALT_MARK;
        if (list2 != null && list == list2) {
            long elapsedRealtime = SystemClock.elapsedRealtime() - quietAt;
            if (elapsedRealtime >= 2000 || elapsedRealtime < 0) {
                z = true;
            }
        }
        brWant = z;
    }

    public static double cutPos(Route route, double d, Double d2, Double d3) {
        int size;
        double d4 = 0.0d;
        double d5 = 0.0d;
        if (route == null) {
            return -1.0d;
        }
        try {
            List<GeoPoint> geometry = route.getGeometry();
            double[] cumulative = route.getCumulative();
            if (geometry == null || cumulative == null || (size = geometry.size()) < 2 || cumulative.length != size) {
                return -1.0d;
            }
            int i = -1;
            double d6 = 1.0E18d;
            if (d2 != null && d3 != null) {
                double doubleValue = d2.doubleValue();
                double doubleValue2 = d3.doubleValue();
                double cos = Math.cos(0.017453292519943295d * doubleValue) * 111320.0d;
                double d7 = d - 60.0d;
                double d8 = d + 150.0d;
                int binarySearch = Arrays.binarySearch(cumulative, d7);
                if (binarySearch < 0) {
                    binarySearch = (-1) - binarySearch;
                }
                int i2 = binarySearch - 1;
                if (i2 < 0) {
                    i2 = 0;
                }
                while (i2 < size - 1) {
                    int i3 = i2 + 1;
                    if (cumulative[i3] >= d7) {
                        if (cumulative[i2] > d8) {
                            break;
                        }
                        GeoPoint geoPoint = geometry.get(i2);
                        GeoPoint geoPoint2 = geometry.get(i3);
                        double lng = (geoPoint.getLng() - doubleValue2) * cos;
                        double lat = (geoPoint.getLat() - doubleValue) * 110540.0d;
                        double lng2 = ((geoPoint2.getLng() - doubleValue2) * cos) - lng;
                        double lat2 = ((geoPoint2.getLat() - doubleValue) * 110540.0d) - lat;
                        double d9 = (lng2 * lng2) + (lat2 * lat2);
                        double d10 = 0.0d;
                        if (d9 > 1.0E-6d) {
                            d10 = (0.0d - ((lng * lng2) + (lat * lat2))) / d9;
                            if (d10 < 0.0d) {
                                d10 = 0.0d;
                            }
                            if (d10 > 1.0d) {
                                d10 = 1.0d;
                            }
                        }
                        double d11 = (d10 * lng2) + lng;
                        double d12 = (d10 * lat2) + lat;
                        double sqrt = Math.sqrt((d11 * d11) + (d12 * d12));
                        double d13 = cumulative[i2];
                        double abs = sqrt + (Math.abs((d13 + ((cumulative[i3] - d13) * d10)) - d) * 0.05d);
                        if (abs < d6) {
                            d6 = abs;
                            d4 = d10;
                            d5 = sqrt;
                            i = i2;
                        }
                    }
                    i2++;
                }
                if (i >= 0 && d5 <= 40.0d) {
                    return i + d4;
                }
            }
            if (d <= 0.0d) {
                return -1.0d;
            }
            int i4 = size - 1;
            if (d >= cumulative[i4]) {
                return i4;
            }
            int binarySearch2 = Arrays.binarySearch(cumulative, d);
            if (binarySearch2 >= 0) {
                return binarySearch2;
            }
            int i5 = ((-1) - binarySearch2) - 1;
            if (i5 < 0) {
                i5 = 0;
            }
            double d14 = cumulative[i5];
            double d15 = cumulative[i5 + 1] - d14;
            return i5 + (d15 > 0.0d ? (d - d14) / d15 : 0.0d);
        } catch (Throwable unused) {
            return -1.0d;
        }
    }

    public static void exTick(float f, double d, double d2) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!wxBusy && (elapsedRealtime >= wxNextAt || (wxAt != 0 && LocPlus.stDist(d, d2, wxCLat, wxCLng) >= 20000.0d && elapsedRealtime - wxStartAt >= 60000))) {
                wxStartAt = elapsedRealtime;
                wxBusy = true;
                wxNextAt = 600000L + elapsedRealtime;
                Thread thread = new Thread(new RdFetch(d, d2, 2), "Weather");
                thread.setDaemon(true);
                thread.start();
            }
            if (!fuBusy && elapsedRealtime >= fuNextAt && (fuData == null || LocPlus.stDist(d, d2, fuCLat, fuCLng) >= 3000.0d)) {
                fuBusy = true;
                fuNextAt = 60000 + elapsedRealtime;
                Thread thread2 = new Thread(new RdFetch(d, d2, 3), "Fuel");
                thread2.setDaemon(true);
                thread2.start();
            }
            int i = wxRain;
            if (i >= 60 && elapsedRealtime - wxAt < 1200000 && elapsedRealtime - wxWarnAt >= 1800000 && pend1 == null) {
                wxWarnAt = elapsedRealtime;
                pend2 = "Önümüzdeki 3 saatte %" + i + " ihtimal";
                pend1 = "Yağmur olabilir";
            }
            fuAhead(d, d2);
        } catch (Throwable unused) {
        }
    }

    public static void exportLogs(File file) {
        if (file != null) {
            try {
                Thread thread = new Thread(new Export(file), "wayfindr-log-export");
                thread.setDaemon(true);
                thread.start();
            } catch (Throwable unused) {
            }
        }
    }

    public static void exportNow(File file) {
        if (file != null) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    File file2 = new File(Environment.getExternalStoragePublicDirectory("Download"), "Wayfindr");
                    file2.mkdirs();
                    CopyOption[] copyOptionArr = {StandardCopyOption.REPLACE_EXISTING};
                    for (File file3 : listFiles) {
                        try {
                            String name = file3.getName();
                            if (name.endsWith(".log")) {
                                File file4 = new File(file2, name);
                                if (file4.length() != file3.length()) {
                                    Files.copy(file3.toPath(), file4.toPath(), copyOptionArr);
                                }
                            }
                        } catch (Throwable unused) {
                        }
                    }
                }
            } catch (Throwable unused2) {
            }
        }
    }

    public static boolean fallbackOrigin(Location location) {
        if (location == null) {
            return false;
        }
        try {
            Long fixAgeMs = LocationTracker.fixAgeMs(location, SystemClock.elapsedRealtimeNanos(), System.currentTimeMillis());
            if (fixAgeMs == null) {
                return false;
            }
            long longValue = fixAgeMs.longValue();
            if (longValue < 0 || longValue > 90000) {
                return false;
            }
            if (longValue > 20000 && location.hasSpeed() && location.getSpeed() > 4.0f) {
                return false;
            }
            if (location.hasAccuracy()) {
                return location.getAccuracy() <= 150.0f;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static List fastestFirst(List list) {
        if (list == null) {
            return new ArrayList();
        }
        try {
            ArrayList arrayList = new ArrayList(list);
            int size = arrayList.size();
            for (int i = 1; i < size; i++) {
                int i2 = i;
                while (i2 > 0) {
                    int i3 = i2 - 1;
                    Object obj = arrayList.get(i3);
                    double routeSecs = routeSecs(obj);
                    Object obj2 = arrayList.get(i2);
                    if (routeSecs > routeSecs(obj2)) {
                        arrayList.set(i3, obj2);
                        arrayList.set(i2, obj);
                        i2 = i3;
                    } else {
                        break; // jadx dropped this; without it the sort never ends (ANR)
                    }
                }
            }
            while (true) {
                int size2 = arrayList.size();
                if (size2 <= 3) {
                    return arrayList;
                }
                arrayList.remove(size2 - 1);
            }
        } catch (Throwable unused) {
            return list;
        }
    }

    public static void fetchLimit(double d, double d2) {
        JSONObject optJSONObject;
        int parseLimit;
        try {
            Context context = GPlus.ctx;
            if (context != null) {
                HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(context, "https://overpass-api.de/api/interpreter?data=".concat(URLEncoder.encode("[out:json][timeout:8];way(around:15," + d + "," + d2 + ")[\"highway\"][\"maxspeed\"];out tags 6;", "UTF-8")));
                openConnection.setConnectTimeout(6000);
                openConnection.setReadTimeout(9000);
                openConnection.setRequestProperty("User-Agent", "Wayfindr-SpeedLimit/1.0");
                if (openConnection.getResponseCode() != 200) {
                    openConnection.disconnect();
                    offlineLimit(d, d2);
                    return;
                }
                InputStream inputStream = openConnection.getInputStream();
                byte[] readBytes = ByteStreamsKt.readBytes(inputStream);
                inputStream.close();
                openConnection.disconnect();
                JSONArray optJSONArray = new JSONObject(new String(readBytes, StandardCharsets.UTF_8)).optJSONArray("elements");
                int i = 0;
                if (optJSONArray != null) {
                    int length = optJSONArray.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject2 != null && (optJSONObject = optJSONObject2.optJSONObject("tags")) != null && (parseLimit = parseLimit(optJSONObject.optString("maxspeed"))) > i) {
                            i = parseLimit;
                        }
                    }
                }
                if (i != spdLimit) spdShowUntil = SystemClock.elapsedRealtime() + 10000L;
                spdLimit = i;
                spdAt = SystemClock.elapsedRealtime();
            }
        } catch (Throwable unused) {
            offlineLimit(d, d2);
        }
    }

    /** No usable online answer: use the speed limit cached along the route (OfflineSafetyCache). */
    private static void offlineLimit(double d, double d2) {
        int l = OfflineSafetyCache.INSTANCE.limitAt(d, d2);
        if (l > 0) {
            if (l != spdLimit) spdShowUntil = SystemClock.elapsedRealtime() + 10000L;
            spdLimit = l;
            spdAt = SystemClock.elapsedRealtime();
        }
    }

    public static void fetchRoads(double d, double d2, double d3) {
        String str;
        int length;
        try {
            Context context = GPlus.ctx;
            if (context != null) {
                HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(context, (roadMirror == 0 ? "https://overpass-api.de/api/interpreter?data=" : "https://overpass.kumi.systems/api/interpreter?data=").concat(URLEncoder.encode("[out:json][timeout:25];way(around:" + ((int) d3) + "," + d + "," + d2 + ")[\"highway\"~\"^(motorway|motorway_link|trunk|trunk_link|primary|primary_link|secondary|secondary_link|tertiary|tertiary_link|unclassified|residential|living_street|road)$\"]->.a;.a out skel geom qt;out count;way.a[\"oneway\"~\"^(yes|true|1)$\"];out ids qt;out count;way.a[\"oneway\"=\"-1\"];out ids qt;out count;way.a[\"junction\"~\"^(roundabout|circular)$\"][\"oneway\"!=\"no\"];out ids qt;", "UTF-8")));
                openConnection.setConnectTimeout(8000);
                openConnection.setReadTimeout(25000);
                openConnection.setRequestProperty("User-Agent", "Wayfindr-RoadSnap/1.0");
                int responseCode = openConnection.getResponseCode();
                if (responseCode != 200) {
                    openConnection.disconnect();
                    str = "HTTP ".concat(String.valueOf(responseCode));
                } else {
                    InputStream inputStream = openConnection.getInputStream();
                    byte[] readBytes = ByteStreamsKt.readBytes(inputStream);
                    inputStream.close();
                    openConnection.disconnect();
                    JSONArray optJSONArray = new JSONObject(new String(readBytes, StandardCharsets.UTF_8)).optJSONArray("elements");
                    if (optJSONArray != null) {
                        ArrayList arrayList = new ArrayList();
                        HashMap hashMap = new HashMap();
                        int i = 0;
                        int length2 = optJSONArray.length();
                        for (int i2 = 0; i2 < length2; i2++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                            if (optJSONObject != null) {
                                if ("count".equals(optJSONObject.optString("type"))) {
                                    i++;
                                } else {
                                    Long valueOf = Long.valueOf(optJSONObject.optLong("id"));
                                    if (i != 0) {
                                        Object obj = hashMap.get(valueOf);
                                        if (obj != null) {
                                            ((double[]) obj)[0] = i == 2 ? -1.0d : 1.0d;
                                        }
                                    } else {
                                        JSONArray optJSONArray2 = optJSONObject.optJSONArray("geometry");
                                        if (optJSONArray2 != null && (length = optJSONArray2.length()) >= 2) {
                                            double[] dArr = new double[length + length + 1];
                                            int i3 = 0;
                                            while (true) {
                                                if (i3 >= length) {
                                                    arrayList.add(dArr);
                                                    hashMap.put(valueOf, dArr);
                                                    break;
                                                }
                                                JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i3);
                                                if (optJSONObject2 != null) {
                                                    double optDouble = optJSONObject2.optDouble("lat", Double.NaN);
                                                    if (optDouble == optDouble) {
                                                        int i4 = i3 + i3 + 1;
                                                        dArr[i4] = optDouble;
                                                        double optDouble2 = optJSONObject2.optDouble("lon", Double.NaN);
                                                        if (optDouble2 == optDouble2) {
                                                            dArr[i4 + 1] = optDouble2;
                                                            i3++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        roadLat = d;
                        roadLng = d2;
                        roadR = d3;
                        roadPoly = 0;
                        roads = arrayList;
                        RideDiagnostics.INSTANCE.log("road", "roads loaded: ".concat(String.valueOf(arrayList.size())));
                        roadBusy = false;
                        return;
                    }
                    str = "no elements";
                }
            } else {
                str = "no context";
            }
            roadFailed(str);
        } catch (Throwable th) {
            roadFailed(th.toString());
        }
    }

    public static Route firstRoute(Object obj) {
        try {
            if (obj instanceof Route) {
                return (Route) obj;
            }
            if (!(obj instanceof List)) {
                return null;
            }
            List list = (List) obj;
            if (list.isEmpty()) {
                return null;
            }
            Object obj2 = list.get(0);
            if (obj2 instanceof Route) {
                return (Route) obj2;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static String fmtDate(long j) {
        return new SimpleDateFormat("d MMMM yyyy, EEEE HH:mm", new Locale("tr")).format(new Date(j));
    }

    public static String fmtDur(long j) {
        long j2 = j / 60;
        long j3 = j2 / 60;
        long j4 = j2 % 60;
        StringBuilder sb = new StringBuilder();
        if (j3 > 0) {
            sb.append(j3);
            sb.append(" sa ");
        }
        sb.append(j4);
        sb.append(" dk");
        return sb.toString();
    }

    public static String fmtKm(double d) {
        return String.format(new Locale("tr"), "%.1f km", Double.valueOf(d));
    }

    public static void fuAhead(double d, double d2) {
        try {
            float f = -1.0f;
            String str = null;
            Object obj = fuData;
            if (obj != null) {
                Object[] objArr = (Object[]) obj;
                double[] dArr = (double[]) objArr[0];
                double[] dArr2 = (double[]) objArr[1];
                String[] strArr = (String[]) objArr[2];
                int i = fuN;
                boolean z = LocPlus.stHasB;
                float f2 = LocPlus.stBear;
                double cos = Math.cos(Math.toRadians(d));
                double d3 = f2;
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= i) {
                        break;
                    }
                    double d4 = dArr[i3];
                    double d5 = dArr2[i3];
                    double stDist = LocPlus.stDist(d, d2, d4, d5);
                    if (stDist <= 8000.0d) {
                        if (z) {
                            if (Math.abs(Math.IEEEremainder(Math.toDegrees(Math.atan2((d5 - d2) * cos, d4 - d)) - d3, 360.0d)) > 70.0d) {
                            }
                        }
                        float f3 = (float) stDist;
                        if (f < 0.0f || f3 < f) {
                            f = f3;
                            str = strArr[i3];
                            fuLat = dArr[i3];
                            fuLng = dArr2[i3];
                        }
                    }
                    i2 = i3 + 1;
                }
            }
            fuBest = f;
            fuName = str;
        } catch (Throwable unused) {
        }
    }

    public static void fuFetch(double d, double d2) {
        JSONArray optJSONArray;
        try {
            String ovGet = ovGet("[out:json][timeout:10];node(around:8000," + d + "," + d2 + ")[\"amenity\"=\"fuel\"];out 150;");
            if (ovGet == null || (optJSONArray = new JSONObject(ovGet).optJSONArray("elements")) == null) {
                return;
            }
            int length = optJSONArray.length();
            double[] dArr = new double[length];
            double[] dArr2 = new double[length];
            String[] strArr = new String[length];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    double optDouble = optJSONObject.optDouble("lat");
                    double optDouble2 = optJSONObject.optDouble("lon");
                    if (!Double.isNaN(optDouble) && !Double.isNaN(optDouble2)) {
                        String str = "Benzinlik";
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("tags");
                        if (optJSONObject2 != null) {
                            String optString = optJSONObject2.optString("brand");
                            if (optString.length() > 0) {
                                str = optString;
                            } else {
                                String optString2 = optJSONObject2.optString("name");
                                if (optString2.length() > 0) {
                                    str = optString2;
                                }
                            }
                        }
                        dArr[i] = optDouble;
                        dArr2[i] = optDouble2;
                        strArr[i] = str;
                        i++;
                    }
                }
            }
            fuCLat = d;
            fuCLng = d2;
            fuN = i;
            fuData = new Object[]{dArr, dArr2, strArr};
        } catch (Throwable unused) {
        }
    }

    public static void googleAlts(JSONObject jSONObject, List list) {
        if (jSONObject != null) {
            try {
                List list2 = ALT_MARK;
                if (list2 == null || list != list2) {
                    return;
                }
                jSONObject.put("computeAlternativeRoutes", true);
            } catch (Throwable unused) {
            }
        }
    }

    public static List googleChoices(Object obj) {
        Object[] objArr;
        ArrayList arrayList = new ArrayList();
        if (obj != null) {
            arrayList.add(obj);
            try {
                if ((obj instanceof Route) && (objArr = altStash) != null && objArr[0] == ((Route) obj).getGeometry()) {
                    arrayList.addAll((Collection) objArr[1]);
                    altStash = null;
                }
            } catch (Throwable unused) {
                return arrayList;
            }
        }
        return arrayList;
    }

    public static int headingFor(GeoPoint geoPoint) {
        try {
            if (!hdgOk || geoPoint == null || SystemClock.elapsedRealtime() - hdgAt > 8000) {
                return -1;
            }
            float[] fArr = new float[1];
            Location.distanceBetween(geoPoint.getLat(), geoPoint.getLng(), hdgLat, hdgLng, fArr);
            if (fArr[0] <= 150.0f) {
                return ((Math.round(hdg) % 360) + 360) % 360;
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static String httpGet(String str) {
        try {
            Context context = GPlus.ctx;
            if (context == null) {
                return null;
            }
            HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(context, str);
            openConnection.setConnectTimeout(6000);
            openConnection.setReadTimeout(12000);
            openConnection.setRequestProperty("User-Agent", "Wayfindr-Weather/1.0");
            if (openConnection.getResponseCode() != 200) {
                openConnection.disconnect();
                return null;
            }
            InputStream inputStream = openConnection.getInputStream();
            byte[] readBytes = ByteStreamsKt.readBytes(inputStream);
            inputStream.close();
            openConnection.disconnect();
            return new String(readBytes, StandardCharsets.UTF_8);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void hudDraw(Canvas canvas, int i, int i2) {
        try {
            if (!hudOn) return;
            long now = SystemClock.elapsedRealtime();
            boolean wx = wxAt != 0 && now < wxShowUntil;
            String fuel = fuBest >= 0.0f ? fuelText() : null;
            if (!wx && fuel == null) return;
            Paint paint = hudTx;
            if (paint == null) {
                paint = new Paint(1);
                paint.setColor(-1);
                paint.setFakeBoldText(true);
                hudTx = paint;
                Paint bg = new Paint(1);
                bg.setColor(-1325400064);
                hudBg = bg;
            }
            float ts = i2 * 0.06f;
            paint.setTextSize(ts);
            float icon = ts * 1.35f;
            String temp = wx ? Math.round(wxTemp) + "°" : null;
            String sep = (wx && fuel != null) ? "  ·  " : "";
            float w = 0f;
            if (wx) w += icon + ts * 0.3f + paint.measureText(temp);
            w += paint.measureText(sep);
            if (fuel != null) w += paint.measureText(fuel);
            float top = i2 * 0.14f;
            float bh = ts * 1.6f;
            float cx = i * 0.5f;
            float half = (w + ts) * 0.5f;
            RectF r = new RectF(cx - half, top, cx + half, top + bh);
            canvas.drawRoundRect(r, bh * 0.5f, bh * 0.5f, hudBg);
            float x = r.left + ts * 0.5f;
            float base = top + bh * 0.5f - (paint.ascent() + paint.descent()) * 0.5f;
            if (wx) {
                wxIcon(canvas, wxCode, wxDay == 1, x + icon * 0.5f, top + bh * 0.5f, icon);
                x += icon + ts * 0.3f;
                canvas.drawText(temp, x, base, paint);
                x += paint.measureText(temp);
            }
            if (sep.length() != 0) { canvas.drawText(sep, x, base, paint); x += paint.measureText(sep); }
            if (fuel != null) canvas.drawText(fuel, x, base, paint);
        } catch (Throwable unused) {
        }
    }

    static String fuelText() {
        int round = Math.round((fuBest / 1000.0f) * 10.0f);
        return "⛽ " + (round / 10) + "," + (round % 10) + " km";
    }

    private static Paint wxP;

    /** Small weather glyph centred on (cx, cy), s = box size: sun / moon, cloud, rain, snow, fog, storm. */
    static void wxIcon(Canvas c, int code, boolean day, float cx, float cy, float s) {
        Paint p = wxP;
        if (p == null) { p = new Paint(1); wxP = p; }
        p.setStyle(Paint.Style.FILL);
        float u = s / 10f;
        if (code == 0 || code == 1) {
            float sx = code == 1 ? cx - u * 1.5f : cx, sy = code == 1 ? cy - u * 1.5f : cy;
            if (day) {
                p.setColor(0xFFFFC83D);
                p.setStrokeWidth(u * 0.8f);
                p.setStrokeCap(Paint.Cap.ROUND);
                for (int k = 0; k < 8; k++) {
                    double a = k * Math.PI / 4;
                    c.drawLine(sx + (float) Math.cos(a) * u * 3.2f, sy + (float) Math.sin(a) * u * 3.2f,
                            sx + (float) Math.cos(a) * u * 4.5f, sy + (float) Math.sin(a) * u * 4.5f, p);
                }
                c.drawCircle(sx, sy, u * 2.4f, p);
            } else {
                p.setColor(0xFFE8E4C9);
                c.drawCircle(sx, sy, u * 3.2f, p);
                p.setColor(0xFF22252A);
                c.drawCircle(sx + u * 1.6f, sy - u * 1.1f, u * 2.7f, p);
            }
            if (code == 0) return;
        }
        int cloud = code >= 61 || code == 95 || code >= 96 ? 0xFF9AA3AD : 0xFFDDE2E8;
        float by = code <= 3 ? cy + u * 1.0f : cy - u * 0.5f;
        p.setColor(cloud);
        c.drawCircle(cx - u * 2.0f, by, u * 2.0f, p);
        c.drawCircle(cx + u * 0.3f, by - u * 1.3f, u * 2.6f, p);
        c.drawCircle(cx + u * 2.6f, by, u * 1.8f, p);
        c.drawRect(cx - u * 2.0f, by, cx + u * 2.6f, by + u * 1.9f, p);
        float dy = by + u * 3.0f;
        if (code >= 45 && code <= 48) {          // fog: three bars
            p.setStrokeWidth(u * 0.8f);
            p.setStrokeCap(Paint.Cap.ROUND);
            for (int k = 0; k < 2; k++) c.drawLine(cx - u * 3.5f, dy + k * u * 1.3f, cx + u * 3.5f, dy + k * u * 1.3f, p);
        } else if ((code >= 71 && code <= 77) || code == 85 || code == 86) {   // snow: white dots
            p.setColor(-1);
            for (int k = -1; k <= 1; k++) c.drawCircle(cx + k * u * 2.2f, dy + (k == 0 ? u * 0.9f : 0f), u * 0.75f, p);
        } else if (code >= 95) {                 // storm: yellow bolt
            p.setColor(0xFFFFC83D);
            android.graphics.Path b = new android.graphics.Path();
            b.moveTo(cx + u * 0.6f, dy - u * 1.0f); b.lineTo(cx - u * 1.2f, dy + u * 1.4f); b.lineTo(cx, dy + u * 1.4f);
            b.lineTo(cx - u * 0.8f, dy + u * 3.4f); b.lineTo(cx + u * 1.6f, dy + u * 0.6f); b.lineTo(cx + u * 0.3f, dy + u * 0.6f); b.close();
            c.drawPath(b, p);
        } else if (code >= 51) {                 // drizzle / rain / showers: blue drops
            p.setColor(0xFF4FA3FF);
            p.setStrokeWidth(u * 0.8f);
            p.setStrokeCap(Paint.Cap.ROUND);
            for (int k = -1; k <= 1; k++) c.drawLine(cx + k * u * 2.2f + u * 0.5f, dy - u * 0.4f, cx + k * u * 2.2f - u * 0.3f, dy + u * 1.4f, p);
        }
    }

    public static String hudText() {
        StringBuilder sb = new StringBuilder();
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = wxAt;
        if (j != 0 && elapsedRealtime - j < 3600000) {
            sb.append(Math.round(wxTemp));
            sb.append("° ");
            sb.append(wxLabel(wxCode));
        }
        float f = fuBest;
        if (f >= 0.0f) {
            if (sb.length() != 0) {
                sb.append("  ·  ");
            }
            sb.append("⛽ ");
            int round = Math.round((f / 1000.0f) * 10.0f);
            sb.append(round / 10);
            sb.append(",");
            sb.append(round % 10);
            sb.append(" km");
        }
        return sb.toString();
    }

    public static String jmAct(DashViewModel dashViewModel, int i) {
        try {
            if (i == 0) {
                jmKey(85);
                return "Müzik: oynat / duraklat";
            }
            if (i == 1) {
                jmKey(87);
                return "Müzik: sonraki parça";
            }
            if (i == 2) {
                jmKey(88);
                return "Müzik: önceki parça";
            }
            if (i == 3) {
                dashViewModel.zoomIn();
                return "Yakınlaştırıldı";
            }
            if (i == 4) {
                dashViewModel.zoomOut();
                return "Uzaklaştırıldı";
            }
            if (i == 5) {
                dashViewModel.setMapDark(!dashViewModel.mapDark);
                return "Koyu harita değişti";
            }
            if (fuBest < 0.0f) {
                return "İleride benzinlik yok";
            }
            String str = fuName;
            if (str == null) {
                str = "Benzinlik";
            }
            dashViewModel.setDestination(str, Double.valueOf(fuLat), Double.valueOf(fuLng));
            return "Benzinliğe rota: ".concat(str);
        } catch (Throwable unused) {
            return "Menü işlemi yapılamadı";
        }
    }

    public static String jmFuel() {
        float f = fuBest;
        if (f < 0.0f) {
            return "⛽  İleride benzinlik yok";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("⛽  ");
        String str = fuName;
        if (str == null) {
            str = "Benzinlik";
        }
        sb.append(str);
        sb.append(" · ");
        sb.append(fmtKm(f / 1000.0d));
        sb.append(" — git");
        return sb.toString();
    }

    public static void jmKey(int i) {
        try {
            Context context = GPlus.ctx;
            if (context != null) {
                AudioManager audioManager = (AudioManager) context.getSystemService("audio");
                audioManager.dispatchMediaKeyEvent(new KeyEvent(0, i));
                audioManager.dispatchMediaKeyEvent(new KeyEvent(1, i));
            }
        } catch (Throwable unused) {
        }
    }

    public static void jmRow(ArrayList arrayList, String str) {
        arrayList.add(new MapRenderer.MenuRow(str, false, false, false));
    }

    public static List jmRows(DashViewModel dashViewModel) {
        ArrayList arrayList = new ArrayList();
        jmRow(arrayList, "♫  Oynat / Duraklat");
        jmRow(arrayList, "⏭  Sonraki parça");
        jmRow(arrayList, "⏮  Önceki parça");
        jmRow(arrayList, "＋  Yakınlaştır");
        jmRow(arrayList, "－  Uzaklaştır");
        jmRow(arrayList, dashViewModel.mapDark ? "◐  Koyu harita: AÇIK" : "◐  Koyu harita: KAPALI");
        jmRow(arrayList, jmFuel());
        return arrayList;
    }

    public static void joinUntil(Thread thread, long j) {
        if (thread != null) {
            try {
                long elapsedRealtime = j - SystemClock.elapsedRealtime();
                if (elapsedRealtime > 0) {
                    thread.join(elapsedRealtime);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void logHeading(int i) {
        try {
            RideDiagnostics.INSTANCE.log("route", "origin heading ".concat(String.valueOf(i)));
        } catch (Throwable unused) {
        }
    }

    public static void mapWarn(String str) {
        if (str != null) {
            try {
                int indexOf = str.indexOf(".rd5");
                if (indexOf >= 0) {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - mapWarnAt >= 60000) {
                        mapWarnAt = elapsedRealtime;
                        String substring = str.substring(str.lastIndexOf(32, indexOf) + 1, indexOf + 4);
                        pend1 = "Çevrimdışı harita parçası eksik";
                        pend2 = substring + " — BRouter'dan indirin";
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static Bitmap mbDecode(byte[] bArr, int i, int i2) {
        try {
            if (bArr.length >= 2 && bArr[0] == 31 && bArr[1] == -117) {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr2 = new byte[4096];
                while (true) {
                    int read = gZIPInputStream.read(bArr2);
                    if (read < 0) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                bArr = byteArrayOutputStream.toByteArray();
            }
            return mbVec ? vxRender(bArr, i, i2) : BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        } catch (Throwable th) {
            mbNote(th);
            return null;
        }
    }

    public static void mbDelete(Context context) {
        try {
            if (mbBusy) {
                return;
            }
            mbReload0();
            mbFile(context).delete();
            plReset(context);
            mbOpen();
        } catch (Throwable unused) {
        }
    }

    public static File mbFile(Context context) {
        return new File(context.getFilesDir(), "offline/turkey.mbtiles");
    }

    public static String mbImport(Activity activity, Uri uri) {
        mbBusy = true;
        try {
            mbReload0();
            Context applicationContext = activity.getApplicationContext();
            File mbFile = mbFile(applicationContext);
            File parentFile = mbFile.getParentFile();
            parentFile.mkdirs();
            File file = new File(parentFile, "import.tmp");
            file.delete();
            InputStream openInputStream = activity.getContentResolver().openInputStream(uri);
            int i = 0;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1048576];
            long j = 0;
            while (true) {
                int read = openInputStream.read(bArr);
                if (read < 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
                j += read;
                int i2 = ((int) j) >> 20;
                if (i2 >= i) {
                    i = i2 + 32;
                    OfflineMapActivity.post(activity, "Kopyalanıyor… " + i2 + " MB");
                }
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            openInputStream.close();
            mbFile.delete();
            if (!file.renameTo(mbFile)) {
                mbBusy = false;
                return "Dosya yerine konamadı (yer yok mu?)";
            }
            mbReload();
            mbBusy = false;
            String mbStatus = mbStatus(applicationContext);
            plReset(applicationContext);
            plKick();
            return mbStatus;
        } catch (Throwable th) {
            mbBusy = false;
            return "Kopyalama hatası: " + th.toString();
        }
    }

    public static int mbInt(String str, int i) {
        if (str != null) {
            try {
                return Integer.parseInt(str);
            } catch (Throwable unused) {
            }
        }
        return i;
    }

    public static boolean mbIsOn(Context context) {
        try {
            return context.getSharedPreferences("offlinemap", 0).getBoolean("on", true);
        } catch (Throwable unused) {
            return true;
        }
    }

    public static String mbMeta(SQLiteDatabase sQLiteDatabase, String str) {
        String r0 = null;
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT value FROM metadata WHERE name=?", new String[]{str});
            if (rawQuery != null) {
                r0 = rawQuery.moveToFirst() ? rawQuery.getString(0) : null;
                rawQuery.close();
            }
            return r0;
        } catch (Throwable unused) {
            return r0;
        }
    }

    public static void mbNote(Throwable th) {
        mbErrN++;
        if (th != null) {
            mbErr = th.toString();
        }
    }

    public static void mbOpen() {
        try {
            Context context = GPlus.ctx;
            if (context != null) {
                mbState = 3;
                mbDb = null;
                mbInfo = null;
                File mbFile = mbFile(context);
                if (!mbFile.exists()) {
                    mbState = 2;
                    mbInfo = "Dosya yok";
                    return;
                }
                if (!mbIsOn(context)) {
                    mbState = 2;
                    mbInfo = "Dosya var, çevrimdışı harita kapalı";
                    return;
                }
                SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(mbFile.getAbsolutePath(), null, 1);
                String mbMeta = mbMeta(openDatabase, "format");
                mbVec = false;
                if (mbMeta != null && mbMeta.equals("pbf")) {
                    mbVec = true;
                }
                int mbInt = mbInt(mbMeta(openDatabase, "minzoom"), 0);
                mbMinZ = mbInt;
                int mbInt2 = mbInt(mbMeta(openDatabase, "maxzoom"), 18);
                mbMaxZ = mbInt2;
                mbDb = openDatabase;
                mbState = 1;
                StringBuilder sb = new StringBuilder();
                sb.append("Hazır: ");
                if (mbMeta == null) {
                    mbMeta = "raster";
                }
                sb.append(mbMeta);
                sb.append(", zoom ");
                sb.append(mbInt);
                sb.append("–");
                sb.append(mbInt2);
                sb.append(", ");
                sb.append(mbFile.length() / 1048576);
                sb.append(" MB");
                mbInfo = sb.toString();
            }
        } catch (Throwable th) {
            mbState = 2;
            mbInfo = "Açılamadı: " + th.toString();
        }
    }

    public static Bitmap mbOverV(int i, int i2, int i3, int i4) {
        int i5 = i - i4;
        int i6 = (1 << i5) - 1;
        return mbRaw2(i4, i2 >> i5, i3 >> i5, ((i2 & i6) << 4) | i5 | ((i3 & i6) << 12));
    }

    public static Bitmap mbRaw(int i, int i2, int i3) {
        return mbRaw2(i, i2, i3, 0);
    }

    public static Bitmap mbRaw2(int i, int i2, int i3, int i4) {
        Cursor rawQuery;
        byte[] blob;
        Bitmap bitmap = null;
        try {
            SQLiteDatabase sQLiteDatabase = mbDb;
            if (sQLiteDatabase == null || (rawQuery = sQLiteDatabase.rawQuery("SELECT tile_data FROM tiles WHERE zoom_level=? AND tile_column=? AND tile_row=? LIMIT 1", new String[]{String.valueOf(i), String.valueOf(i2), String.valueOf(((1 << i) - 1) - i3)})) == null) {
                return null;
            }
            if (rawQuery.moveToFirst() && (blob = rawQuery.getBlob(0)) != null) {
                bitmap = mbDecode(blob, i, i4);
            }
            rawQuery.close();
            return (bitmap == null || bitmap.getWidth() == 256) ? bitmap : Bitmap.createScaledBitmap(bitmap, 256, 256, true);
        } catch (Throwable th) {
            mbNote(th);
            return null;
        }
    }

    public static Bitmap mbRead(int i, int i2, int i3) {
        try {
            int i4 = mbMaxZ;
            if (i < mbMinZ) {
                return null;
            }
            int i5 = i4 + 3;
            if (mbVec) {
                i5 = i4 + 8;
            }
            if (i > i5) {
                return null;
            }
            if (i <= i4) {
                return mbRaw(i, i2, i3);
            }
            int i6 = i - i4;
            int i7 = i2 >> i6;
            int i8 = i3 >> i6;
            if (mbVec) {
                return mbOverV(i, i2, i3, i4);
            }
            Bitmap mbRaw = mbRaw(i4, i7, i8);
            if (mbRaw == null) {
                return null;
            }
            int i9 = 256 >> i6;
            int i10 = (1 << i6) - 1;
            return Bitmap.createScaledBitmap(Bitmap.createBitmap(mbRaw, (i2 & i10) * i9, (i3 & i10) * i9, i9, i9), 256, 256, true);
        } catch (Throwable th) {
            mbNote(th);
            return null;
        }
    }

    public static void mbReload() {
        mbReload0();
        mbOpen();
    }

    public static void mbReload0() {
        try {
            mbState = 3;
            SQLiteDatabase sQLiteDatabase = mbDb;
            mbDb = null;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            mbState = 0;
            mbInfo = null;
        } catch (Throwable unused) {
            mbState = 0;
        }
    }

    public static String mbStatus(Context context) {
        try {
            GPlus.init(context);
        } catch (Throwable unused) {
        }
        String mbStatus0 = mbStatus0(context);
        try {
            plKick();
        } catch (Throwable unused2) {
        }
        StringBuilder sb = new StringBuilder();
        sb.append(mbStatus0);
        sb.append("\n\nBu oturumda: dosyadan verilen parça ");
        sb.append(mbHit);
        sb.append(", dosyada bulunamayan ");
        sb.append(mbMiss);
        sb.append("\nYer adı dizini: ");
        sb.append(plStatus());
        int i = mbErrN;
        if (i != 0) {
            sb.append("\nSon hata (");
            sb.append(i);
            sb.append(" kez): ");
            sb.append(mbErr);
        }
        sb.append("\n(Haritayı kullandıktan sonra bu ekrana dön; sayılar artıyorsa harita dosyadan geliyor.)");
        return sb.toString();
    }

    public static String mbStatus0(Context context) {
        try {
            if (mbState == 0) {
                mbOpen();
            }
            if (!mbFile(context).exists()) {
                return "Henüz dosya yok.\n\n'MBTiles dosyası seç' ile Türkiye .mbtiles dosyanızı seçin. Dosya uygulamanın içine kopyalanır (telefonda dosya boyu kadar yer kaplar, 1 GB için birkaç dakika sürebilir; kopyalarken ekranı kapatmayın).";
            }
            StringBuilder sb = new StringBuilder();
            String str = mbInfo;
            if (str != null) {
                sb.append(str);
            }
            sb.append("\n\nKapsam dışı bölgeler ve haritada olmayan yakınlaştırmalar eskisi gibi internetten gelir.");
            return sb.toString();
        } catch (Throwable unused) {
            return "Durum okunamadı";
        }
    }

    public static Bitmap mbTile(String str) {
        if (offNow()) {
            return mbTile1(str);
        }
        return null;
    }

    public static Bitmap mbTile0(String str) {
        if (str == null) {
            return null;
        }
        try {
            int i = mbState;
            if (i != 1) {
                if (i != 0) {
                    return null;
                }
                mbOpen();
                if (mbState != 1) {
                    return null;
                }
            }
            String[] split = str.split("/");
            if (split.length >= 4) {
                return mbRead(Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
            }
            return null;
        } catch (Throwable th) {
            mbNote(th);
            return null;
        }
    }

    public static Bitmap mbTile1(String str) {
        Bitmap mbTile0 = mbTile0(str);
        if (mbTile0 != null) {
            mbHit++;
            return mbTile0;
        }
        if (mbState == 1) {
            mbMiss++;
        }
        return mbTile0;
    }

    public static void mbToggle(Context context) {
        try {
            context.getSharedPreferences("offlinemap", 0).edit().putBoolean("on", !mbIsOn(context)).commit();
            mbReload();
        } catch (Throwable unused) {
        }
    }

    public static String mbToggleLabel(Context context) {
        return mbIsOn(context) ? "Çevrimdışı harita: AÇIK  (kapatmak için dokun)" : "Çevrimdışı harita: KAPALI  (açmak için dokun)";
    }

    public static byte[] mbUnzip(byte[] bArr) {
        try {
            if (bArr.length < 2 || bArr[0] != 31 || bArr[1] != -117) {
                return bArr;
            }
            GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[4096];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read < 0) {
                    bArr = byteArrayOutputStream.toByteArray();
                    return bArr;
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (Throwable unused) {
            return bArr;
        }
    }

    public static int offCmd(Context context, String str) {
        if (str == null || context == null) {
            return 0;
        }
        try {
            if (!"|çevrimdışı|cevrimdisi|çevrimdışı harita|cevrimdisi harita|offline|offline harita|harita yükle|harita yukle|".contains("|" + str.trim().toLowerCase(Locale.ROOT) + "|")) {
                return 0;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime - offAt <= 4000) {
                return 3;
            }
            offAt = elapsedRealtime;
            GPlus.init(context);
            Intent intent = new Intent(context, (Class<?>) OfflineMapActivity.class);
            intent.addFlags(268435456);
            context.startActivity(intent);
            return 3;
        } catch (Throwable unused) {
            return 0;
        }
    }

    public static boolean offNow() {
        NetworkInfo activeNetworkInfo;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - netAt >= 4000) {
                netAt = currentTimeMillis;
                int i = 1;
                Context context = GPlus.ctx;
                if (context != null && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.isConnected()) {
                    i = 0;
                }
                netOff = i;
            }
            return netOff != 0;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean okPos(double d, double d2) {
        return Math.abs(d) + Math.abs(d2) > 0.001d;
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x0067, code lost:
    
        if (r12 < r13) goto L26;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void onFix(Location location) {
        float f;
        if (location != null) {
            try {
                float speed = location.hasSpeed() ? location.getSpeed() : 0.0f;
                fSpeed = speed;
                fAcc = location.hasAccuracy() ? location.getAccuracy() : 30.0f;
                boolean hasBearing = location.hasBearing();
                if (!hasBearing || speed < 2.0f) {
                    turn = 0.0f;
                    bearAt = 0L;
                } else {
                    float bearing = location.getBearing();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long j = bearAt;
                    if (j <= 0) {
                        f = 0.0f;
                    } else {
                        long j2 = elapsedRealtime - j;
                        if (j2 <= 0) {
                            f = turn;
                        } else if (j2 > 3000) {
                            f = 0.0f;
                        } else {
                            float f3 = (((((bearing - fBear) + 900.0f) % 360.0f) - 180.0f) * 1000.0f) / j2;
                            if (f3 > 80.0f) f3 = 80.0f;
                            if (f3 < -80.0f) f3 = -80.0f;
                            f = (f3 * 0.6f) + (turn * 0.4f);
                            if (Math.abs(f) < 2.0f) {
                                f = 0.0f;
                            }
                        }
                    }
                    turn = f;
                    fBear = bearing;
                    bearAt = elapsedRealtime;
                }
                if (hasBearing && speed >= 2.5f) {
                    hdg = location.getBearing();
                    hdgLat = location.getLatitude();
                    hdgLng = location.getLongitude();
                    hdgAt = SystemClock.elapsedRealtime();
                    hdgOk = true;
                }
                roadTick(location.getLatitude(), location.getLongitude(), speed);
            } catch (Throwable unused) {
            }
        }
    }

    public static void openOffline() {
        try {
            Context context = GPlus.ctx;
            if (context == null) {
                context = (Context) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
            }
            if (context != null) {
                GPlus.init(context);
                Intent intent = new Intent(context, (Class<?>) OfflineMapActivity.class);
                intent.addFlags(268435456);
                context.startActivity(intent);
            }
        } catch (Throwable unused) {
        }
    }

    public static String ovGet(String str) {
        try {
            Context context = GPlus.ctx;
            if (context == null) {
                return null;
            }
            HttpURLConnection openConnection = InternetNetwork.INSTANCE.openConnection(context, "https://overpass-api.de/api/interpreter?data=".concat(URLEncoder.encode(str, "UTF-8")));
            openConnection.setConnectTimeout(6000);
            openConnection.setReadTimeout(12000);
            openConnection.setRequestProperty("User-Agent", "Wayfindr-Radar/1.0");
            if (openConnection.getResponseCode() != 200) {
                openConnection.disconnect();
                return null;
            }
            InputStream inputStream = openConnection.getInputStream();
            byte[] readBytes = ByteStreamsKt.readBytes(inputStream);
            inputStream.close();
            openConnection.disconnect();
            return new String(readBytes, StandardCharsets.UTF_8);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static double overlap(Route route, Route route2, double d) {
        int i = 0;
        int i2 = 0;
        try {
            List<GeoPoint> geometry = route.getGeometry();
            int size = geometry.size();
            if (size < 2) {
                return 0.0d;
            }
            List<GeoPoint> geometry2 = route2.getGeometry();
            double[] cumulative = route2.getCumulative();
            int size2 = geometry2.size();
            if (size2 < 2 || cumulative.length != size2) {
                return 0.0d;
            }
            GeoPoint geoPoint = geometry.get(0);
            double lat = geoPoint.getLat();
            double lng = geoPoint.getLng();
            double cos = Math.cos(0.017453292519943295d * lat) * 111195.07973436d;
            double d2 = d - 200.0d;
            double d3 = d + 30000.0d;
            int i3 = 0;
            while (true) {
                int i4 = i3 + 1;
                if (i4 >= size2 || cumulative[i4] >= d2) {
                    break;
                }
                i3++;
            }
            int i5 = i3;
            while (true) {
                int i6 = i5;
                if (i6 + 1 >= size2 || cumulative[i6] > d3) {
                    break;
                }
                i5++;
            }
            int i7 = (i5 - i3) + 1;
            if (i7 < 2) {
                return 0.0d;
            }
            double[] dArr = new double[i7];
            double[] dArr2 = new double[i7];
            int i8 = i7 - 1;
            int i9 = 0;
            while (true) {
                int i10 = i9;
                if (i10 >= i7) {
                    break;
                }
                GeoPoint geoPoint2 = geometry2.get(i3 + i10);
                dArr[i10] = (geoPoint2.getLng() - lng) * cos;
                dArr2[i10] = (geoPoint2.getLat() - lat) * 111195.07973436d;
                i9++;
            }
            double d4 = 0.0d;
            double d5 = 0.0d;
            double d6 = 0.0d;
            double d7 = 0.0d;
            int i11 = 0;
            int i12 = 1;
            loop3: while (true) {
                int i13 = i12;
                if (i13 >= size) {
                    break;
                }
                GeoPoint geoPoint3 = geometry.get(i13);
                double lng2 = (geoPoint3.getLng() - lng) * cos;
                double lat2 = (geoPoint3.getLat() - lat) * 111195.07973436d;
                double d8 = lng2 - d4;
                double d9 = lat2 - d5;
                double sqrt = Math.sqrt((d8 * d8) + (d9 * d9));
                while (d7 <= d6 + sqrt) {
                    if (d7 > 20000.0d) {
                        break loop3;
                    }
                    double d10 = sqrt > 0.0d ? (d7 - d6) / sqrt : 0.0d;
                    double d11 = ((lng2 - d4) * d10) + d4;
                    double d12 = ((lat2 - d5) * d10) + d5;
                    int i14 = i11 - 2;
                    if (i14 < 0) {
                        i14 = 0;
                    }
                    int i15 = i14;
                    boolean z = false;
                    int i16 = 0;
                    while (true) {
                        if (i16 >= i8) {
                            break;
                        }
                        int i17 = i15 + i16;
                        if (i17 >= i8) {
                            i17 -= i8;
                        }
                        double d13 = dArr[i17];
                        double d14 = dArr2[i17];
                        int i18 = i17 + 1;
                        double d15 = dArr[i18];
                        double d16 = dArr2[i18];
                        double d17 = d11 + 30.0d;
                        if (d13 <= d17 || d15 <= d17) {
                            double d18 = d11 - 30.0d;
                            if (d13 >= d18 || d15 >= d18) {
                                double d19 = d12 + 30.0d;
                                if (d14 <= d19 || d16 <= d19) {
                                    double d20 = d12 - 30.0d;
                                    if (d14 >= d20 || d16 >= d20) {
                                        double d21 = d15 - d13;
                                        double d22 = d16 - d14;
                                        double d23 = (d21 * d21) + (d22 * d22);
                                        double d24 = 0.0d;
                                        if (d23 > 1.0E-6d) {
                                            d24 = (((d11 - d13) * d21) + ((d12 - d14) * d22)) / d23;
                                            if (d24 < 0.0d) {
                                                d24 = 0.0d;
                                            }
                                            if (d24 > 1.0d) {
                                                d24 = 1.0d;
                                            }
                                        }
                                        double d25 = ((d24 * d21) + d13) - d11;
                                        double d26 = ((d24 * d22) + d14) - d12;
                                        if ((d25 * d25) + (d26 * d26) <= 900.0d) {
                                            z = true;
                                            i11 = i17;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        i16++;
                    }
                    i++;
                    if (z) {
                        i2++;
                    }
                    d7 += 200.0d;
                }
                d6 += sqrt;
                d4 = lng2;
                d5 = lat2;
                i12++;
            }
            if (i > 0) {
                return i2 / i;
            }
            return 0.0d;
        } catch (Throwable unused) {
            return 0.0d;
        }
    }

    public static int parseLimit(String str) {
        int i = 0;
        if (str != null) {
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i = (i * 10) + (charAt - '0');
            }
        }
        if (i == 0) {
            return 0;
        }
        if (str.contains("mph")) {
            i = (i * 1609) / 1000;
        }
        if (i < 5 || i > 200) {
            return 0;
        }
        return i;
    }

    public static long periodStart(boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        if (z) {
            calendar.set(5, 1);
        } else {
            calendar.add(5, -((calendar.get(7) + 5) % 7));
        }
        return calendar.getTimeInMillis();
    }

    public static String phAccess(Context context) {
        try {
            return MediaInfoProvider.INSTANCE.isAccessGranted(context) ? "Bildirim erişimi: açık. Şerit çıkmıyorsa müziği başlat ve konsola bir kez yeniden bağlan." : "Bildirim erişimi: kapalı. Müzik şeridi ve albüm kapağı için açman gerekir.";
        } catch (Throwable unused) {
            return "Bildirim erişimi: bilinmiyor";
        }
    }

    public static void phCfg(Context context) {
        if (context != null) {
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("navplus_ui", 0);
                phOn = sharedPreferences.getBoolean("ph_on", true);
                phMus = sharedPreferences.getBoolean("ph_mus", true);
                phAlb = sharedPreferences.getBoolean("ph_alb", false);
                phZ = sharedPreferences.getFloat("ph_z", 1.0f);
                phX = sharedPreferences.getFloat("ph_x", 0.5f);
                phY = sharedPreferences.getFloat("ph_y", 0.5f);
                hudOn = sharedPreferences.getBoolean("hud_on", true);
                navCard = sharedPreferences.getBoolean("nav_card", true);
                phCfgOk = true;
            } catch (Throwable unused) {
            }
        }
    }

    public static void phClear(Context context) {
        try {
            phBmp = null;
            phTried = true;
            phResetCrop(context);
            phFile(context).delete();
        } catch (Throwable unused) {
        }
    }

    public static boolean phDraw(Canvas canvas, MapRenderer.Frame frame, int i, int i2) {
        Bitmap bitmap;
        NowPlaying phNow;
        if (i <= 0 || i2 <= 0) {
            return false;
        }
        try {
            phLoad();
            List<GeoPoint> route = frame.getRoute();
            if ((route != null && !route.isEmpty()) || frame.getPickerRows() != null) {
                return false;
            }
            if (frame.getRiderLat() != null && !LocPlus.stOn) {
                return false;
            }
            // Himican: duvar kağıdı her zaman arka planda kalır; albüm kapağı yalnızca şeritte.
            if (!phOn || (bitmap = phBmp) == null) {
                return false;
            }
            phPhoto(canvas, bitmap, i, i2);
            phMusic(canvas, i, i2);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static File phFile(Context context) {
        return new File(context.getFilesDir(), "console_photo.jpg");
    }

    public static String phImport(Context context, Uri uri) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            InputStream openInputStream = contentResolver.openInputStream(uri);
            if (openInputStream == null) {
                return "Fotoğraf açılamadı";
            }
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(openInputStream, null, options);
            openInputStream.close();
            int i = options.outWidth;
            int i2 = options.outHeight;
            if (i <= 0 || i2 <= 0) {
                return "Fotoğraf açılamadı";
            }
            int min = Math.min(i, i2);
            int i3 = 1;
            while (true) {
                int i4 = i3 * 2;
                if (min / i4 < 720) {
                    break;
                }
                i3 = i4;
            }
            BitmapFactory.Options options2 = new BitmapFactory.Options();
            options2.inSampleSize = i3;
            InputStream openInputStream2 = contentResolver.openInputStream(uri);
            if (openInputStream2 == null) {
                return "Fotoğraf açılamadı";
            }
            Bitmap decodeStream = BitmapFactory.decodeStream(openInputStream2, null, options2);
            openInputStream2.close();
            if (decodeStream == null) {
                return "Fotoğraf açılamadı";
            }
            FileOutputStream fileOutputStream = new FileOutputStream(phFile(context));
            decodeStream.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
            phBmp = decodeStream;
            phTried = true;
            phResetCrop(context);
            return "Konsol fotoğrafı kaydedildi";
        } catch (Throwable th) {
            return "Fotoğraf yüklenemedi: ".concat(th.toString());
        }
    }

    public static void phLoad() {
        try {
            Context context = GPlus.ctx;
            if (context != null) {
                if (!phCfgOk) {
                    phCfg(context);
                }
                if (phBmp != null || phTried) {
                    return;
                }
                phTried = true;
                File phFile = phFile(context);
                if (phFile.exists()) {
                    phBmp = BitmapFactory.decodeFile(phFile.getAbsolutePath());
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void phMusic(Canvas canvas, int i, int i2) {
        NowPlaying phNow;
        try {
            if (!phMus || (phNow = phNow()) == null) {
                return;
            }
            phStrip(canvas, i, i2, phNow.getTitle(), phNow.getArtist(), phNow.getArt());
        } catch (Throwable unused) {
        }
    }

    public static NowPlaying phNow() {
        try {
            Object obj = npNow;
            if (obj instanceof NowPlaying) {
                NowPlaying nowPlaying = (NowPlaying) obj;
                if (nowPlaying.getPlaying()) {
                    return nowPlaying;
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void phPaintOk() {
        if (phPaint == null) {
            phPaint = new Paint(2);
        }
    }

    public static void phPan(float f, float f2) {
        float f3;
        float f4;
        try {
            Bitmap bitmap = phBmp;
            if (bitmap != null) {
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                if (width <= 0 || height <= 0) {
                    return;
                }
                float f5 = width;
                float f6 = height;
                if (f5 * 300.0f <= f6 * 526.0f) {
                    f3 = f5;
                    f4 = (f5 * 300.0f) / 526.0f;
                } else {
                    f3 = (f6 * 526.0f) / 300.0f;
                    f4 = f6;
                }
                float max = Math.max(phZ, 1.0f);
                float f7 = (f3 / max) / f5;
                float f8 = (f4 / max) / f6;
                float f9 = phX - (f * f7);
                float f10 = phY - (f2 * f8);
                float f11 = f7 * 0.5f;
                float f12 = f8 * 0.5f;
                float max2 = Math.max(Math.min(f9, 1.0f - f11), f11);
                float max3 = Math.max(Math.min(f10, 1.0f - f12), f12);
                phX = max2;
                phY = max3;
            }
        } catch (Throwable unused) {
        }
    }

    public static void phPhoto(Canvas canvas, Bitmap bitmap, int i, int i2) {
        float f;
        float f2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return;
        }
        float f3 = width;
        float f4 = height;
        float f5 = i;
        float f6 = i2;
        if (f3 * f6 <= f4 * f5) {
            f = f3;
            f2 = (f3 * f6) / f5;
        } else {
            f = (f4 * f5) / f6;
            f2 = f4;
        }
        float f7 = 1.0f;
        float f8 = 0.5f;
        float f9 = 0.5f;
        if (bitmap == phBmp) {
            f7 = phZ;
            f8 = phX;
            f9 = phY;
            if (f7 < 1.0f) {
                f7 = 1.0f;
            }
        }
        float f10 = f / f7;
        float f11 = f2 / f7;
        float max = Math.max(Math.min((f8 * f3) - (f10 * 0.5f), f3 - f10), 0.0f);
        float max2 = Math.max(Math.min((f9 * f4) - (f11 * 0.5f), f4 - f11), 0.0f);
        Rect rect = new Rect((int) max, (int) max2, (int) (max + f10), (int) (max2 + f11));
        Rect rect2 = new Rect(0, 0, i, i2);
        phPaintOk();
        Paint paint = phPaint;
        canvas.drawColor(-16777216);
        canvas.drawBitmap(bitmap, rect, rect2, paint);
    }

    public static Bitmap phPreview(int i) {
        try {
            phLoad();
            Bitmap.Config config = Bitmap.Config.ARGB_8888;
            int i2 = (i * 300) / DashEncoder.WIDTH;
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, config);
            Canvas canvas = new Canvas(createBitmap);
            Path path = new Path();
            float f = i * 0.5f;
            path.addCircle(f, f, f, Path.Direction.CW);
            canvas.clipPath(path);
            Bitmap bitmap = phBmp;
            if (bitmap == null) {
                canvas.drawColor(-14407894);
            } else {
                phPhoto(canvas, bitmap, i, i2);
                if (phMus) {
                    phStrip(canvas, i, i2, "Şarkı adı", "Sanatçı", null);
                }
            }
            Paint paint = new Paint(1);
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(-3562166);
            paint.setStrokeWidth(3.0f);
            float f2 = i * 0.5f;
            canvas.drawCircle(f2, f2, f2 - 1.5f, paint);
            return createBitmap;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void phResetCrop(Context context) {
        phZ = 1.0f;
        phX = 0.5f;
        phY = 0.5f;
        if (context != null) {
            phSaveCrop(context);
        }
    }

    public static void phSaveCrop(Context context) {
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("navplus_ui", 0).edit();
            edit.putFloat("ph_z", phZ);
            edit.putFloat("ph_x", phX);
            edit.putFloat("ph_y", phY);
            edit.commit();
        } catch (Throwable unused) {
        }
    }

    public static void phSet(Context context, String str, boolean z) {
        try {
            context.getSharedPreferences("navplus_ui", 0).edit().putBoolean(str, z).commit();
            phCfg(context);
        } catch (Throwable unused) {
        }
    }

    public static String phStatus() {
        try {
            phLoad();
            Bitmap bitmap = phBmp;
            if (bitmap == null) {
                return "Konsol fotoğrafı: yok";
            }
            return "Konsol fotoğrafı: yüklü (" + bitmap.getWidth() + "×" + bitmap.getHeight() + "). Rota yokken ve motor dururken konsolda harita yerine bu fotoğraf görünür; yola çıkınca harita geri gelir.";
        } catch (Throwable unused) {
            return "Konsol fotoğrafı: yok";
        }
    }

    /** Müzik kartı: navigasyon kartı tarzında koyu, yuvarlak köşeli; kapak solda, başlık ve sanatçı yanında. */
    public static void phStrip(Canvas canvas, int i, int i2, String str, String str2, Bitmap bitmap) {
        String title = str == null ? "" : str;
        String artist = str2 == null ? "" : str2;
        try {
            phPaintOk();
            float w = i;
            float h = i2;
            float left = w * 0.05f;
            float right = w * 0.95f;
            float top = h * 0.68f;
            float bottom = h * 0.90f;
            float ch = bottom - top;
            float pad = ch * 0.13f;
            Paint card = new Paint(1);
            card.setColor(0xE6201C18);
            canvas.drawRoundRect(new RectF(left, top, right, bottom), ch * 0.24f, ch * 0.24f, card);
            float x = left + pad;
            if (bitmap != null) {
                float size = (bottom - pad) - (top + pad);
                canvas.drawBitmap(bitmap, (Rect) null, new RectF(x, top + pad, x + size, bottom - pad), phPaint);
                x = x + size + pad;
            }
            float textW = right - pad - x;
            TextPaint tp = new TextPaint(1);
            TextUtils.TruncateAt end = TextUtils.TruncateAt.END;
            tp.setColor(-1);
            tp.setTextSize(ch * 0.34f);
            tp.setFakeBoldText(true);
            canvas.drawText(TextUtils.ellipsize(title, tp, textW, end).toString(), x, top + ch * 0.47f, tp);
            tp.setColor(0xFFB8B8B8);
            tp.setTextSize(ch * 0.25f);
            tp.setFakeBoldText(false);
            canvas.drawText(TextUtils.ellipsize(artist, tp, textW, end).toString(), x, top + ch * 0.80f, tp);
        } catch (Throwable unused) {
        }
    }

    public static void phZoomBy(float f) {
        phZ = Math.min(Math.max(phZ * f, 1.0f), 6.0f);
        phPan(0.0f, 0.0f);
    }

    public static Route pickPlanned(Object obj, DashViewModel dashViewModel, int i) {
        Object obj2;
        Route route;
        if (obj == null) {
            return null;
        }
        try {
            if (obj instanceof List) {
                obj2 = obj;
            } else {
                ArrayList arrayList = new ArrayList();
                arrayList.add(obj);
                obj2 = arrayList;
            }
            List list = (List) obj2;
            int size = list.size();
            if (size == 0) {
                return null;
            }
            Route route2 = (Route) list.get(0);
            if (dashViewModel == null || (route = dashViewModel.route) == null) {
                return route2;
            }
            double nav10Progress = DashViewModel.nav10Progress(dashViewModel);
            double totalSeconds = (route2.getTotalSeconds() * 1.1d) + 90.0d;
            Route route3 = null;
            int i2 = 0;
            double d = -1.0d;
            StringBuilder sb = new StringBuilder();
            sb.append(i == 0 ? "reroute: on planned route " : "traffic refresh: on planned route ");
            for (int i3 = 0; i3 < size; i3++) {
                Object obj3 = list.get(i3);
                if (obj3 instanceof Route) {
                    Route route4 = (Route) obj3;
                    double overlap = overlap(route4, route, nav10Progress);
                    sb.append((int) (overlap * 100.0d));
                    sb.append("% ");
                    if (i == 0 && route4.getTotalSeconds() > totalSeconds) {
                        sb.append("(slow) ");
                    } else if (overlap > d) {
                        route3 = route4;
                        i2 = i3;
                        d = overlap;
                    }
                }
            }
            if (i != 0) {
                if (route3 == null || d < 0.9d) {
                    sb.append("-> other roads suggested, planned route kept");
                    rlog(sb.toString());
                    return null;
                }
                sb.append("-> same roads, traffic and arrival time updated");
                rlog(sb.toString());
                return route3;
            }
            if (route3 == null || d < 0.15d) {
                sb.append("-> fastest (no way back to the planned roads)");
                rlog(sb.toString());
                return route2;
            }
            sb.append("-> #");
            sb.append(i2 + 1);
            sb.append(" (back to the planned roads)");
            rlog(sb.toString());
            return route3;
        } catch (Throwable unused) {
            if (i == 0) {
                return firstRoute(obj);
            }
            return null;
        }
    }

    public static int plAdd(ArrayList arrayList, String str) {
        int i = 0;
        try {
            String[] split = str.split("\t");
            double parseDouble = Double.parseDouble(split[2]);
            double parseDouble2 = Double.parseDouble(split[3]);
            if (SearchPlus.isDup(arrayList, parseDouble, parseDouble2)) {
                return 0;
            }
            arrayList.add(new PlaceSearch.Hit(split[0], parseDouble, parseDouble2, "Çevrimdışı", plLabel(split[1])));
            i = 1;
            return 1;
        } catch (Throwable unused) {
            return i;
        }
    }

    public static void plBuild() {
        try {
            plBuild0();
        } catch (Throwable th) {
            plState = 3;
            plInfo = th.toString();
            mbNote(th);
        }
    }

    /* JADX WARN: Unreachable blocks removed: 1, instructions: 1 */
    public static void plBuild0() throws Exception {
        SQLiteDatabase sQLiteDatabase;
        if (!mbVec || (sQLiteDatabase = mbDb) == null) {
            return;
        }
        plState = 1;
        plInfo = "Yer adları taranıyor…";
        int i = mbMaxZ;
        if (i > 12) {
            i = 12;
        }
        int i2 = mbMinZ;
        if (i < i2) {
            i = i2;
        }
        StringBuilder sb = new StringBuilder();
        Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT tile_column, tile_row, tile_data FROM tiles WHERE zoom_level=?", new String[]{String.valueOf(i)});
        if (rawQuery == null) {
            plState = 3;
            return;
        }
        Vx vx = new Vx();
        vx.pos = new int[1];
        vx.sb = sb;
        vx.z = i;
        int i3 = (1 << i) - 1;
        while (rawQuery.moveToNext()) {
            int i4 = rawQuery.getInt(0);
            int i5 = rawQuery.getInt(1);
            byte[] blob = rawQuery.getBlob(2);
            vx.tx = i4;
            vx.ty = i3 - i5;
            try {
                plTile(blob, vx);
            } catch (Throwable unused) {
            }
        }
        rawQuery.close();
        Context context = GPlus.ctx;
        File plFile = plFile(context);
        plFile.getParentFile().mkdirs();
        byte[] bytes = sb.toString().getBytes("UTF-8");
        FileOutputStream fileOutputStream = new FileOutputStream(plFile);
        fileOutputStream.write(bytes);
        fileOutputStream.close();
        plRaw = null;
        plLoad(context);
    }

    public static int plClassRank(String str) {
        if (vxIn(str, "|country|state|province|region|city|")) {
            return 0;
        }
        if (vxIn(str, "|town|")) {
            return 1;
        }
        if (vxIn(str, "|suburb|borough|city_district|")) {
            return 2;
        }
        if (vxIn(str, "|village|isolated_dwelling|")) {
            return 3;
        }
        return vxIn(str, "|quarter|neighbourhood|") ? 4 : 5;
    }

    public static void plEmit(StringBuilder sb, String str, String str2, double d, double d2) {
        sb.append(str.replace('\t', ' ').replace('\n', ' '));
        sb.append("\t");
        if (str2 == null) {
            str2 = "";
        }
        sb.append(str2);
        sb.append("\t");
        sb.append(d);
        sb.append("\t");
        sb.append(d2);
        sb.append("\n");
    }

    public static void plFeat(Vx vx, int i, int i2, int i3, int i4) {
        String plTag;
        int i5 = 0;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        while (vxPos(vx) < i) {
            int vxVar = vxVar(vx);
            int i9 = vxVar >>> 3;
            int i10 = vxVar & 7;
            if (i9 == 2) {
                int vxVar2 = vxVar(vx);
                i6 = vxPos(vx);
                i7 = i6 + vxVar2;
                vxSet(vx, i7);
            } else if (i9 == 3) {
                i5 = vxVar(vx);
            } else if (i9 == 4) {
                int vxVar3 = vxVar(vx);
                i8 = vxPos(vx);
                vxSet(vx, i8 + vxVar3);
            } else {
                vxSkip(vx, i10);
            }
        }
        if (i5 == 1 && i8 >= 0 && i6 >= 0 && (plTag = plTag(vx, i6, i7, i2)) != null) {
            String plTag2 = plTag(vx, i6, i7, i3);
            vxSet(vx, i8);
            vxVar(vx);
            int vxZz = vxZz(vxVar(vx));
            int vxZz2 = vxZz(vxVar(vx));
            int i11 = vx.z;
            plEmit(vx.sb, plTag, plTag2, plLat(i11, vx.ty, vxZz2, i4), plLng(i11, vx.tx, vxZz, i4));
        }
        vxSet(vx, i);
    }

    public static File plFile(Context context) {
        return new File(context.getFilesDir(), "offline/places.tsv");
    }

    public static int plIns(int[] iArr, int[] iArr2, int i, int i2, int i3) {
        int i4 = i;
        while (i4 > 0) {
            int i5 = i4 - 1;
            int i6 = iArr[i5];
            if (i6 <= i2) {
                break;
            }
            iArr[i4] = i6;
            iArr2[i4] = iArr2[i5];
            i4 = i5;
        }
        iArr[i4] = i2;
        iArr2[i4] = i3;
        return i < 6 ? i + 1 : i;
    }

    public static void plKick() {
        try {
            if (plState == 0 && mbVec && mbState == 1 && plRaw == null) {
                plState = 1;
                new Thread(new Idx(), "PlIdx").start();
            }
        } catch (Throwable unused) {
        }
    }

    public static String plLabel(String str) {
        return vxIn(str, "|country|") ? "Ülke" : vxIn(str, "|state|province|region|") ? "İl / bölge" : vxIn(str, "|city|") ? "Şehir" : vxIn(str, "|town|") ? "İlçe / kasaba" : vxIn(str, "|suburb|borough|city_district|") ? "Semt" : vxIn(str, "|village|") ? "Köy" : vxIn(str, "|quarter|neighbourhood|") ? "Mahalle" : vxIn(str, "|hamlet|isolated_dwelling|") ? "Mezra" : "Yer";
    }

    public static double plLat(int i, int i2, int i3, int i4) {
        return Math.toDegrees(Math.atan(Math.sinh((1.0d - ((((i3 / i4) + i2) / (1 << i)) * 2.0d)) * 3.141592653589793d)));
    }

    public static void plLayer(Vx vx, int i) {
        int vxPos = vxPos(vx);
        String str = null;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 4096;
        while (vxPos(vx) < i) {
            int vxVar = vxVar(vx);
            int i3 = vxVar >>> 3;
            int i4 = vxVar & 7;
            if (i3 == 1) {
                str = vxStr(vx, vxVar(vx));
            } else if (i3 == 3) {
                arrayList.add(vxStr(vx, vxVar(vx)));
            } else if (i3 == 4) {
                arrayList2.add(vxValStr(vx, vxVar(vx)));
            } else if (i3 == 5) {
                i2 = vxVar(vx);
            } else {
                vxSkip(vx, i4);
            }
        }
        if (str != null && vxIn(str, "|place|places|place_labels|place_label|") && i2 > 0) {
            vx.keys = arrayList;
            vx.vals = arrayList2;
            int indexOf = arrayList.indexOf("name");
            if (indexOf >= 0) {
                int indexOf2 = arrayList.indexOf("class");
                if (indexOf2 < 0 && (indexOf2 = arrayList.indexOf("kind")) < 0) {
                    indexOf2 = arrayList.indexOf("type");
                }
                vxSet(vx, vxPos);
                while (vxPos(vx) < i) {
                    int vxVar2 = vxVar(vx);
                    int i5 = vxVar2 >>> 3;
                    int i6 = vxVar2 & 7;
                    if (i5 == 2) {
                        plFeat(vx, vxPos(vx) + vxVar(vx), indexOf, indexOf2, i2);
                    } else {
                        vxSkip(vx, i6);
                    }
                }
            }
        }
        vxSet(vx, i);
    }

    public static double plLng(int i, int i2, int i3, int i4) {
        return ((((i3 / i4) + i2) / (1 << i)) * 360.0d) - 180.0d;
    }

    public static void plLoad(Context context) {
        if (context == null) {
            return;
        }
        try {
            if (plRaw != null) {
                return;
            }
            File plFile = plFile(context);
            if (!plFile.exists()) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(plFile), "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    bufferedReader.close();
                    plNorm = arrayList2;
                    plRank = arrayList3;
                    plCount = arrayList.size();
                    plState = 2;
                    plRaw = arrayList;
                    return;
                }
                String[] split = readLine.split("\t");
                if (split.length >= 4) {
                    arrayList2.add(plNorm(split[0]));
                    arrayList.add(readLine);
                    arrayList3.add(Integer.valueOf(plClassRank(split[1])));
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static String plNorm(String str) {
        return str.toLowerCase(Locale.ROOT).replace((char) 305, 'i').replace((char) 351, 's').replace((char) 231, 'c').replace((char) 287, 'g').replace((char) 246, 'o').replace((char) 252, 'u').replace((char) 226, 'a').replace((char) 238, 'i').replace((char) 251, 'u').replace(String.valueOf((char) 775), "");
    }

    public static void plReset(Context context) {
        try {
            plRaw = null;
            plNorm = null;
            plRank = null;
            plState = 0;
            plCount = 0;
            if (context != null) {
                plFile(context).delete();
            }
        } catch (Throwable unused) {
        }
    }

    public static int plScore(String str, String str2, String str3, int i) {
        int i2;
        if (str.startsWith(str2)) {
            i2 = 0;
        } else if (str.contains(str3)) {
            i2 = 16;
        } else {
            if (!str.contains(str2)) {
                return -1;
            }
            i2 = 32;
        }
        return i2 + i;
    }

    public static int plSearch(Context context, String str, ArrayList arrayList) {
        int i = 0;
        if (str != null && arrayList != null) {
            try {
                if (arrayList.size() < 3) {
                    String plNorm2 = plNorm(str.trim());
                    if (plNorm2.length() >= 2) {
                        if (plRaw == null) {
                            plLoad(context);
                            if (plRaw == null) {
                                plKick();
                            }
                        }
                        ArrayList arrayList2 = plNorm;
                        ArrayList arrayList3 = plRank;
                        int[] iArr = new int[7];
                        int[] iArr2 = new int[7];
                        int i2 = 0;
                        int size = arrayList2.size();
                        String concat = " ".concat(plNorm2);
                        for (int i3 = 0; i3 < size; i3++) {
                            int plScore = plScore((String) arrayList2.get(i3), plNorm2, concat, ((Integer) arrayList3.get(i3)).intValue());
                            if (plScore >= 0) {
                                i2 = plIns(iArr, iArr2, i2, plScore, i3);
                            }
                        }
                        for (int i4 = 0; i4 < i2; i4++) {
                            i += plAdd(arrayList, (String) plRaw.get(iArr2[i4]));
                        }
                    }
                }
            } catch (Throwable unused) {
                return 0;
            }
        }
        return i;
    }

    public static String plStatus() {
        int i = plState;
        if (i == 0) {
            return "yok (vektör dosya açılınca otomatik hazırlanır)";
        }
        if (i == 1) {
            return "hazırlanıyor… (birkaç dakika sürebilir)";
        }
        if (i != 2) {
            String str = plInfo;
            return str == null ? "hata" : str;
        }
        return plCount + " yer adı (şehir, ilçe, semt, köy) · çevrimdışı aramada çıkar";
    }

    public static String plTag(Vx vx, int i, int i2, int i3) {
        if (i3 < 0 || i < 0) {
            return null;
        }
        vxSet(vx, i);
        while (vxPos(vx) < i2) {
            int vxVar = vxVar(vx);
            int vxVar2 = vxVar(vx);
            if (vxVar == i3) {
                ArrayList arrayList = vx.vals;
                if (vxVar2 < arrayList.size()) {
                    return (String) arrayList.get(vxVar2);
                }
                return null;
            }
        }
        return null;
    }

    public static void plTile(byte[] bArr, Vx vx) {
        byte[] mbUnzip = mbUnzip(bArr);
        vx.d = mbUnzip;
        vxSet(vx, 0);
        int length = mbUnzip.length;
        while (vxPos(vx) < length) {
            int vxVar = vxVar(vx);
            int i = vxVar >>> 3;
            int i2 = vxVar & 7;
            if (i == 3 && i2 == 2) {
                plLayer(vx, vxPos(vx) + vxVar(vx));
            } else {
                vxSkip(vx, i2);
            }
        }
    }

    public static GeoPoint pointAt(List list, double[] dArr, double d) {
        if (dArr == null || list == null) {
            return null;
        }
        try {
            int length = dArr.length;
            if (length < 2 || list.size() != length || d != d) {
                return null;
            }
            if (d <= dArr[0]) {
                return (GeoPoint) list.get(0);
            }
            if (d >= dArr[length - 1]) {
                return (GeoPoint) list.get(length - 1);
            }
            int binarySearch = Arrays.binarySearch(dArr, d);
            if (binarySearch >= 0) {
                return (GeoPoint) list.get(binarySearch);
            }
            int i = (-binarySearch) - 2;
            if (i < 0) {
                i = 0;
            }
            int i2 = length - 2;
            if (i > i2) {
                i = i2;
            }
            double d2 = dArr[i];
            int i3 = i + 1;
            double d3 = dArr[i3] - d2;
            double d4 = d - d2;
            if (d3 <= 0.0d) {
                d4 = 0.0d;
                d3 = 1.0d;
            }
            double d5 = d4 / d3;
            GeoPoint geoPoint = (GeoPoint) list.get(i);
            GeoPoint geoPoint2 = (GeoPoint) list.get(i3);
            double lat = geoPoint.getLat();
            double lat2 = lat + ((geoPoint2.getLat() - lat) * d5);
            double lng = geoPoint.getLng();
            return new GeoPoint(lat2, lng + ((geoPoint2.getLng() - lng) * d5));
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void quietAlts() {
        quietAt = SystemClock.elapsedRealtime();
    }

    public static void rdFetch(double d, double d2) {
        JSONArray optJSONArray;
        try {
            String ovGet = ovGet("[out:json][timeout:10];(node(around:6000," + d + "," + d2 + ")[\"highway\"=\"speed_camera\"];node(around:6000," + d + "," + d2 + ")[\"traffic_calming\"];);out 400;");
            if (ovGet == null) {
                ovGet = OfflineSafetyCache.INSTANCE.cameraJson(d, d2, 6000.0d);
            }
            if (ovGet == null || (optJSONArray = new JSONObject(ovGet).optJSONArray("elements")) == null) {
                return;
            }
            int length = optJSONArray.length();
            double[] dArr = new double[length];
            double[] dArr2 = new double[length];
            int[] iArr = new int[length];
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    double optDouble = optJSONObject.optDouble("lat");
                    double optDouble2 = optJSONObject.optDouble("lon");
                    if (!Double.isNaN(optDouble) && !Double.isNaN(optDouble2)) {
                        int i3 = 2;
                        JSONObject optJSONObject2 = optJSONObject.optJSONObject("tags");
                        if (optJSONObject2 != null && "speed_camera".equals(optJSONObject2.optString("highway"))) {
                            i3 = 1;
                        }
                        dArr[i] = optDouble;
                        dArr2[i] = optDouble2;
                        iArr[i] = i3;
                        i++;
                    }
                }
            }
            rdCLat = d;
            rdCLng = d2;
            rdN = i;
            rdData = new Object[]{dArr, dArr2, iArr, new double[length], new long[length]};
        } catch (Throwable unused) {
        }
    }

    public static void rdTick(float f, double d, double d2) {
        try {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!rdBusy && elapsedRealtime >= rdNextAt && (rdData == null || LocPlus.stDist(d, d2, rdCLat, rdCLng) >= 2500.0d)) {
                rdBusy = true;
                rdNextAt = 30000 + elapsedRealtime;
                Thread thread = new Thread(new RdFetch(d, d2, 1), "Radar");
                thread.setDaemon(true);
                thread.start();
            }
            Object obj = rdData;
            if (obj != null) {
                Object[] objArr = (Object[]) obj;
                double[] dArr = (double[]) objArr[0];
                double[] dArr2 = (double[]) objArr[1];
                int[] iArr = (int[]) objArr[2];
                double[] dArr3 = (double[]) objArr[3];
                long[] jArr = (long[]) objArr[4];
                int length = iArr.length;
                int ahead = -1;
                for (int i = 0; i < length; i++) {
                    int i2 = iArr[i];
                    if (i2 != 0) {
                        double stDist = LocPlus.stDist(d, d2, dArr[i], dArr2[i]);
                        double d3 = dArr3[i];
                        dArr3[i] = stDist;
                        if (i2 == 1 && stDist <= 1000.0d && d3 > 0.0d && stDist < d3 && (ahead < 0 || stDist < ahead)) {
                            ahead = (int) stDist;
                        }
                        if (stDist <= (i2 != 1 ? 150.0d : 500.0d) && d3 > 0.0d && stDist < d3 && f >= 4.0f && elapsedRealtime - jArr[i] >= 120000 && pend1 == null) {
                            jArr[i] = elapsedRealtime;
                            String str = i2 == 1 ? "Hız kamerası" : "Kasis";
                            pend2 = ((int) stDist) + " m ileride";
                            pend1 = str;
                            if (i2 == 1) { rdAheadM = (int) stDist; rdAheadAt = elapsedRealtime; }
                            return;
                        }
                    }
                }
                if (ahead >= 0) { rdAheadM = ahead; rdAheadAt = elapsedRealtime; }
                else if (elapsedRealtime - rdAheadAt > 4000) rdAheadM = -1;
            }
        } catch (Throwable unused) {
        }
    }

    public static Bitmap rideCard(Object obj) {
        Bitmap createBitmap = Bitmap.createBitmap(1080, 1350, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-15855856);
        txt(canvas, "HIMALAYAN 450 · SÜRÜŞ", 70.0f, 110.0f, 34.0f, -3562166, true);
        txt(canvas, fmtDate(rowL(obj, 0)), 70.0f, 175.0f, 44.0f, -1, true);
        RectF rectF = new RectF(70.0f, 230.0f, 1016.0f, 932.0f);
        Paint paint = new Paint(1);
        paint.setColor(-15263461);
        canvas.drawRoundRect(rectF, 40.0f, 40.0f, paint);
        String str = (String) ((Object[]) obj)[4];
        if (str != null) {
            List<GeoPoint> decode = PolylineCodec.INSTANCE.decode(str, 5);
            canvas.save();
            canvas.translate(70.0f, 230.0f);
            trackDraw(canvas, decode, 960.0f, 700.0f, 12.0f, -3562166);
            canvas.restore();
        }
        txt(canvas, "MESAFE", 70.0f, 1020.0f, 28.0f, -6644319, true);
        txt(canvas, fmtKm(rowD(obj, 1) / 1000.0d), 70.0f, 1085.0f, 58.0f, -1, true);
        txt(canvas, "SÜRE", 560.0f, 1020.0f, 28.0f, -6644319, true);
        txt(canvas, fmtDur(rowL(obj, 2)), 560.0f, 1085.0f, 58.0f, -1, true);
        txt(canvas, "ORT. HIZ", 70.0f, 1160.0f, 28.0f, -6644319, true);
        txt(canvas, avgKmh(obj), 70.0f, 1225.0f, 58.0f, -1, true);
        txt(canvas, "EN YÜKSEK", 560.0f, 1160.0f, 28.0f, -6644319, true);
        txt(canvas, String.valueOf(Math.round(rowD(obj, 3) * 3.6d)).concat(" km/sa"), 560.0f, 1225.0f, 58.0f, -1, true);
        txt(canvas, "● 0–30   ● 30–60   ● 60–90   ● 90–120   ● 120+ km/sa", 70.0f, 1305.0f, 26.0f, -6644319, false);
        return createBitmap;
    }

    public static ArrayList rideRows(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            File databasePath = context.getDatabasePath("wayfindr.db");
            if (databasePath.exists()) {
                SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(databasePath.getPath(), null, 1);
                Cursor rawQuery = openDatabase.rawQuery("SELECT start_ms,distance_m,duration_s,max_speed,track FROM ride ORDER BY start_ms DESC", null);
                while (rawQuery.moveToNext()) {
                    arrayList.add(new Object[]{Long.valueOf(rawQuery.getLong(0)), Double.valueOf(rawQuery.getDouble(1)), Long.valueOf(rawQuery.getLong(2)), Double.valueOf(rawQuery.getDouble(3)), rawQuery.getString(4)});
                }
                rawQuery.close();
                openDatabase.close();
            }
            return arrayList;
        } catch (Throwable unused) {
            return arrayList;
        }
    }

    public static void rideShare(Activity activity, Object obj) {
        try {
            Bitmap rideCard = rideCard(obj);
            File file = new File(activity.getCacheDir(), "route_qr");
            file.mkdirs();
            File file2 = new File(file, "surus.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            rideCard.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.close();
            Uri uriForFile = FileProvider.getUriForFile(activity, "com.wayfindr.app.preview.fileprovider", file2);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("image/png");
            intent.putExtra("android.intent.extra.STREAM", uriForFile);
            intent.addFlags(1);
            activity.startActivity(Intent.createChooser(intent, "Sürüşü paylaş"));
        } catch (Throwable unused) {
        }
    }

    public static double[] rideSum(ArrayList arrayList, long j) {
        double[] dArr = new double[5];
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            if (rowL(obj, 0) >= j) {
                double rowD = rowD(obj, 1) / 1000.0d;
                dArr[0] = dArr[0] + rowD;
                dArr[4] = Math.max(dArr[4], rowD);
                dArr[1] = dArr[1] + rowL(obj, 2);
                dArr[2] = dArr[2] + 1.0d;
                dArr[3] = Math.max(dArr[3], rowD(obj, 3) * 3.6d);
            }
        }
        return dArr;
    }

    public static void rlog(String str) {
        try {
            RideDiagnostics.INSTANCE.log("route", str);
        } catch (Throwable unused) {
        }
    }

    public static void roadFailed(String str) {
        try {
            roadMirror ^= 1;
            roadRetry = SystemClock.elapsedRealtime() + 30000;
            RideDiagnostics.INSTANCE.log("road", "road fetch failed: ".concat(String.valueOf(str)));
        } catch (Throwable unused) {
        }
        roadBusy = false;
    }

    public static Pair roadSnap(Pair pair) {
        double d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        double d6 = 0.0d;
        double d7 = 0.0d;
        double[] dArr = null;
        double[] dArr2 = null;
        double[] dArr3 = null;
        int i = 0;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        if (pair != null) {
            try {
                ArrayList arrayList = roads;
                if (arrayList != null) {
                    double doubleValue = ((Number) pair.getFirst()).doubleValue();
                    double doubleValue2 = ((Number) pair.getSecond()).doubleValue();
                    boolean z3 = fSpeed >= 1.5f;
                    double d8 = predHdg;
                    double d9 = fAcc * 2.0d;
                    if (d9 < 15.0d) {
                        d9 = 15.0d;
                    }
                    if (d9 > 40.0d) {
                        d9 = 40.0d;
                    }
                    double d10 = d9;
                    double cos = Math.cos(0.017453292519943295d * doubleValue) * 111195.07973436d;
                    double d11 = 45.0d / 111195.07973436d;
                    double d12 = 45.0d / cos;
                    long j = snapAt;
                    if (j > 0 && SystemClock.elapsedRealtime() - j <= 3000) {
                        d4 = (snapLng - doubleValue2) * cos;
                        d5 = (snapLat - doubleValue) * 111195.07973436d;
                        z2 = true;
                        double[] dArr4 = snapArr;
                        if (dArr4 != null) {
                            dArr = new double[48];
                            double sqrt = Math.sqrt((d4 * d4) + (d5 * d5)) + 12.0d;
                            int length = dArr4.length;
                            int i3 = 1;
                            while (true) {
                                int i4 = i3 + 1;
                                if (i4 >= length) {
                                    break;
                                }
                                double d13 = (dArr4[i3] - doubleValue) * 111195.07973436d;
                                double d14 = (dArr4[i4] - doubleValue2) * cos;
                                double sqrt2 = Math.sqrt((d14 * d14) + (d13 * d13));
                                if (sqrt2 <= sqrt) {
                                    double d15 = d14 - d4;
                                    double d16 = d13 - d5;
                                    if (Math.sqrt((d15 * d15) + (d16 * d16)) + sqrt2 <= sqrt) {
                                        int i5 = i;
                                        if (i5 >= 24) {
                                            break;
                                        }
                                        int i6 = i5 + i5;
                                        dArr[i6] = d14;
                                        dArr[i6 + 1] = d13;
                                        i++;
                                    } else {
                                        continue;
                                    }
                                }
                                i3 += 2;
                            }
                        }
                    }
                    int i7 = 0;
                    double d17 = 1.0E9d;
                    double d18 = 1.0E9d;
                    int size = arrayList.size();
                    for (int i8 = 0; i8 < size; i8++) {
                        double[] dArr5 = (double[]) arrayList.get(i8);
                        int length2 = dArr5.length;
                        double d19 = dArr5[0];
                        for (int i9 = 1; i9 + 3 < length2; i9 += 2) {
                            double d20 = dArr5[i9];
                            double d21 = dArr5[i9 + 1];
                            double d22 = dArr5[i9 + 2];
                            double d23 = dArr5[i9 + 3];
                            double d24 = doubleValue - d11;
                            if (d20 >= d24 || d22 >= d24) {
                                double d25 = doubleValue + d11;
                                if (d20 <= d25 || d22 <= d25) {
                                    double d26 = doubleValue2 - d12;
                                    if (d21 >= d26 || d23 >= d26) {
                                        double d27 = doubleValue2 + d12;
                                        if (d21 <= d27 || d23 <= d27) {
                                            double d28 = (d21 - doubleValue2) * cos;
                                            double d29 = (d20 - doubleValue) * 111195.07973436d;
                                            double d30 = ((d23 - doubleValue2) * cos) - d28;
                                            double d31 = ((d22 - doubleValue) * 111195.07973436d) - d29;
                                            double d32 = (d30 * d30) + (d31 * d31);
                                            double d33 = 0.0d;
                                            if (d32 > 1.0E-6d) {
                                                d33 = (0.0d - ((d28 * d30) + (d29 * d31))) / d32;
                                                if (d33 < 0.0d) {
                                                    d33 = 0.0d;
                                                }
                                                if (d33 > 1.0d) {
                                                    d33 = 1.0d;
                                                }
                                            }
                                            double d34 = (d33 * d30) + d28;
                                            double d35 = (d33 * d31) + d29;
                                            double sqrt3 = Math.sqrt((d34 * d34) + (d35 * d35));
                                            if (sqrt3 <= 40.0d) {
                                                boolean z4 = false;
                                                if (z2) {
                                                    double d36 = ((d4 - d28) * d30) + ((d5 - d29) * d31);
                                                    if (d32 <= 1.0E-6d) {
                                                        d = 0.0d;
                                                    } else {
                                                        d = d36 / d32;
                                                        if (d < 0.0d) {
                                                            d = 0.0d;
                                                        }
                                                    }
                                                    if (d > 1.0d) {
                                                        d = 1.0d;
                                                    }
                                                    double d37 = ((d * d30) + d28) - d4;
                                                    double d38 = ((d * d31) + d29) - d5;
                                                    if (Math.sqrt((d38 * d38) + (d37 * d37)) > 6.0d) {
                                                        int i10 = 0;
                                                        while (true) {
                                                            int i11 = i10;
                                                            if (i11 >= i) {
                                                                break;
                                                            }
                                                            int i12 = i11 + i11;
                                                            double d39 = dArr[i12];
                                                            double d40 = dArr[i12 + 1];
                                                            double d41 = d28 - d39;
                                                            double d42 = d29 - d40;
                                                            if ((d41 * d41) + (d42 * d42) <= 2.25d) {
                                                                z4 = true;
                                                                break;
                                                            }
                                                            double d43 = (d28 + d30) - d39;
                                                            double d44 = (d29 + d31) - d40;
                                                            if ((d43 * d43) + (d44 * d44) <= 2.25d) {
                                                                z4 = true;
                                                                break;
                                                            }
                                                            i10++;
                                                        }
                                                    } else {
                                                        z4 = true;
                                                    }
                                                }
                                                if (z4 || sqrt3 <= d10) {
                                                    double d45 = sqrt3;
                                                    if (z3) {
                                                        double abs = Math.abs(((((Math.atan2(d30, d31) * 57.29577951308232d) - d8) + 900.0d) % 360.0d) - 180.0d);
                                                        if (d19 == 0.0d) {
                                                            double d46 = 180.0d - abs;
                                                            if (d46 < abs) {
                                                                abs = d46;
                                                            }
                                                        } else if (d19 <= 0.0d) {
                                                            abs = 180.0d - abs;
                                                        }
                                                        if (abs <= 50.0d) {
                                                            d45 += abs * 0.2d;
                                                        }
                                                    }
                                                    if (z4) {
                                                        d45 -= 10.0d;
                                                        if (d45 < d18) {
                                                            d18 = d45;
                                                            i2 = i8 + 1;
                                                            d6 = doubleValue + (d35 / 111195.07973436d);
                                                            d7 = doubleValue2 + (d34 / cos);
                                                            dArr3 = dArr5;
                                                        }
                                                    }
                                                    if (d45 < d17) {
                                                        d17 = d45;
                                                        i7 = i8 + 1;
                                                        d2 = doubleValue + (d35 / 111195.07973436d);
                                                        d3 = doubleValue2 + (d34 / cos);
                                                        dArr2 = dArr5;
                                                        z = z4;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    roadPoly = i7;
                    if (i7 != 0) {
                        if (z || i2 == 0) {
                            altArr = null;
                        } else {
                            long elapsedRealtime = SystemClock.elapsedRealtime();
                            double[] dArr6 = dArr2;
                            if (altArr != dArr6) {
                                altArr = dArr6;
                                altSince = elapsedRealtime;
                            } else if (elapsedRealtime - altSince < 2500) {
                            }
                            i7 = i2;
                            d2 = d6;
                            d3 = d7;
                            dArr2 = dArr3;
                        }
                        roadPoly = i7;
                        snapArr = dArr2;
                        snapLat = d2;
                        snapLng = d3;
                        snapAt = SystemClock.elapsedRealtime();
                        return TuplesKt.to(Double.valueOf(d2), Double.valueOf(d3));
                    }
                }
            } catch (Throwable unused) {
                return pair;
            }
        }
        return pair;
    }

    public static Pair roadSnapHold(Pair pair) {
        return onRoute ? pair : roadSnap(pair);
    }

    public static void roadTick(double d, double d2, float f) {
        try {
            if (roadBusy || onRoute) {
                return;
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (elapsedRealtime < roadRetry || GPlus.ctx == null) {
                return;
            }
            if (roads != null) {
                double d3 = (d - roadLat) * 111195.07973436d;
                double cos = (d2 - roadLng) * 111195.07973436d * Math.cos(d * 0.017453292519943295d);
                if (Math.sqrt((d3 * d3) + (cos * cos)) < roadR * 0.6d) {
                    return;
                }
            }
            double d4 = 350.0d;
            if (f > 12.0f) {
                d4 = 700.0d;
                if (f > 22.0f) {
                    d4 = 1300.0d;
                }
            }
            roadBusy = true;
            roadRetry = elapsedRealtime + 8000;
            Thread thread = new Thread(new RoadFetch(d, d2, d4), "RoadSnap");
            thread.setDaemon(true);
            thread.start();
        } catch (Throwable unused) {
            roadBusy = false;
        }
    }

    public static double routeSecs(Object obj) {
        if (obj instanceof Route) {
            double totalSeconds = ((Route) obj).getTotalSeconds();
            if (totalSeconds > 0.0d) {
                return totalSeconds;
            }
        }
        return Double.POSITIVE_INFINITY;
    }

    public static double rowD(Object obj, int i) {
        return ((Double) ((Object[]) obj)[i]).doubleValue();
    }

    public static long rowL(Object obj, int i) {
        return ((Long) ((Object[]) obj)[i]).longValue();
    }

    public static void rsAdd(float f) {
        ByteArrayOutputStream byteArrayOutputStream = rsBuf;
        if (byteArrayOutputStream != null) {
            byteArrayOutputStream.write(Math.min(Math.max(Math.round(f * 3.6f), 0), 250));
        }
    }

    public static void rsClear() {
        rsBuf = new ByteArrayOutputStream();
    }

    public static File rsDir() {
        Context context = GPlus.ctx;
        if (context == null) {
            return null;
        }
        return new File(context.getFilesDir(), "ride_speeds");
    }

    public static String rsKey(GeoPoint geoPoint) {
        return "sp_" + Math.round(geoPoint.getLat() * 100000.0d) + "_" + Math.round(geoPoint.getLng() * 100000.0d) + ".bin";
    }

    public static byte[] rsLoad(GeoPoint geoPoint) {
        try {
            HashMap hashMap = rsCache;
            if (hashMap == null) {
                hashMap = new HashMap();
                rsCache = hashMap;
            }
            String rsKey = rsKey(geoPoint);
            if (hashMap.containsKey(rsKey)) {
                return (byte[]) hashMap.get(rsKey);
            }
            byte[] r0 = null;
            File rsDir = rsDir();
            if (rsDir != null) {
                File file = new File(rsDir, rsKey);
                r0 = file.exists() ? Files.readAllBytes(file.toPath()) : null;
                hashMap.put(rsKey, r0);
            }
            return r0;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void rsSave(List list) {
        File rsDir;
        try {
            ByteArrayOutputStream byteArrayOutputStream = rsBuf;
            if (byteArrayOutputStream == null || list == null || list.size() < 2 || (rsDir = rsDir()) == null) {
                return;
            }
            rsDir.mkdirs();
            String rsKey = rsKey((GeoPoint) list.get(0));
            FileOutputStream fileOutputStream = new FileOutputStream(new File(rsDir, rsKey));
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.close();
            HashMap hashMap = rsCache;
            if (hashMap != null) {
                hashMap.remove(rsKey);
            }
        } catch (Throwable unused) {
        }
    }

    public static void rtPick(Context context) {
        boolean z = false;
        if (context != null) {
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
                if (connectivityManager != null) {
                    z = true;
                    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        z = false;
                    }
                }
            } catch (Throwable unused) {
                return;
            }
        }
        Router router = Router.INSTANCE;
        RoutingSource source = router.getSource();
        RoutingSource routingSource = RoutingSource.BROUTER;
        if (z) {
            if (source != routingSource) {
                rtUser = source;
                rtForced = true;
                router.setSource0(routingSource);
                return;
            }
            return;
        }
        if (rtForced) {
            rtForced = false;
            RoutingSource routingSource2 = rtUser;
            if (routingSource2 != null) {
                router.setSource0(routingSource2);
            }
        }
    }

    public static void rtUserSet() {
        rtForced = false;
    }

    public static int spColor(int i) {
        if (i < 30) {
            return -12723068;
        }
        if (i < 60) {
            return -3743670;
        }
        if (i < 90) {
            return -673483;
        }
        return i < 120 ? -689099 : -1754835;
    }

    public static void spdTick(float f, double d, double d2) {
        spdTick0(f, d, d2);
        if (okPos(d, d2)) {
            rdTick(f, d, d2);
            exTick(f, d, d2);
        }
    }

    public static void spdTick0(float f, double d, double d2) {
        try {
            float f2 = f * 3.6f;
            if (f2 >= 15.0f) {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                if (!spdBusy && elapsedRealtime >= spdNextAt) {
                    spdBusy = true;
                    spdNextAt = 20000 + elapsedRealtime;
                    Thread thread = new Thread(new SpdFetch(d, d2), "SpeedLimit");
                    thread.setDaemon(true);
                    thread.start();
                }
                int i = spdLimit;
                if (i > 0 && f2 > i + 2) spdOverAt = elapsedRealtime;
                if (i <= 0 || elapsedRealtime - spdAt > 120000 || f2 <= Math.max(i / 10, 6) + i || elapsedRealtime - spdWarnAt < 25000) {
                    return;
                }
                spdWarnAt = elapsedRealtime;
                pend1 = "Hız sınırı " + i + " km/s";
                pend2 = "Hızınız " + ((int) (f2 + 0.5f)) + " km/s";
            }
        } catch (Throwable unused) {
        }
    }

    public static void stashAlts(List<GeoPoint> intermediates, String str, Route route) {
        JSONArray optJSONArray;
        Route parse;
        if (route == null || str == null) {
            return;
        }
        try {
            List<GeoPoint> list = ALT_MARK;
            if (list == null || intermediates != list || (optJSONArray = new JSONObject(str).optJSONArray("routes")) == null) {
                return;
            }
            int length = optJSONArray.length();
            if (length > 4) {
                length = 4;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 1; i < length; i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(optJSONObject);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("routes", jSONArray);
                    parse = Router.INSTANCE.parse(jSONObject.toString());
                    if (parse != null) {
                        arrayList.add(asGoogle(parse));
                    }
                }
            }
            altStash = new Object[]{route.getGeometry(), arrayList};
        } catch (Throwable unused) {
        }
    }

    public static String sumLine(double[] dArr) {
        return ((int) dArr[2]) + " sürüş · " + fmtDur((long) dArr[1]) + " · en yüksek " + Math.round(dArr[3]) + " km/sa";
    }

    public static String[] takeNotice() {
        String str = pend1;
        if (str == null) {
            return null;
        }
        pend1 = null;
        String str2 = pend2;
        pend2 = null;
        return str2 != null ? new String[]{str, str2} : new String[]{str};
    }

    public static Bitmap trackBmp(String str, int i, int i2, float f) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawColor(-15724270);
            if (str != null) {
                trackDraw(canvas, PolylineCodec.INSTANCE.decode(str, 5), i, i2, f, -3562166);
            }
            return createBitmap;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void trackDraw(Canvas canvas, List list, float f, float f2, float f3, int i) {
        if (list != null) {
            try {
                int size = list.size();
                if (size >= 2) {
                    float[] trackXY = trackXY(list, f, f2, f3 * 1.5f);
                    byte[] rsLoad = rsLoad((GeoPoint) list.get(0));
                    if (rsLoad == null || rsLoad.length != size) {
                        rsLoad = null;
                    }
                    Paint paint = new Paint(1);
                    paint.setStrokeWidth(f3);
                    paint.setStrokeCap(Paint.Cap.ROUND);
                    paint.setColor(i);
                    for (int i2 = 1; i2 < size; i2++) {
                        if (rsLoad != null) {
                            paint.setColor(spColor(Math.max(rsLoad[i2 - 1] & 0xFF, rsLoad[i2] & 0xFF)));
                        }
                        int i3 = (i2 - 1) * 2;
                        float f4 = trackXY[i3];
                        float f5 = trackXY[i3 + 1];
                        int i4 = i2 * 2;
                        canvas.drawLine(f4, f5, trackXY[i4], trackXY[i4 + 1], paint);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void trackDrawSz(Canvas canvas, List list, long j) {
        float intBitsToFloat = Float.intBitsToFloat((int) (j >>> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) j);
        trackDraw(canvas, list, intBitsToFloat, intBitsToFloat2, Math.max(Math.min(intBitsToFloat, intBitsToFloat2) * 0.025f, 4.0f), -3562166);
    }

    public static float[] trackXY(List list, float f, float f2, float f3) {
        int size = list.size();
        float[] fArr = new float[size * 2];
        if (size < 2) {
            return fArr;
        }
        double d = Double.POSITIVE_INFINITY;
        double d2 = Double.NEGATIVE_INFINITY;
        double d3 = Double.POSITIVE_INFINITY;
        double d4 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < size; i++) {
            GeoPoint geoPoint = (GeoPoint) list.get(i);
            double lat = geoPoint.getLat();
            double lng = geoPoint.getLng();
            d = Math.min(d, lat);
            d2 = Math.max(d2, lat);
            d3 = Math.min(d3, lng);
            d4 = Math.max(d4, lng);
        }
        double cos = Math.cos(Math.toRadians((d + d2) * 0.5d));
        double d5 = (d4 - d3) * cos;
        double d6 = d2 - d;
        double d7 = f3 * 2.0d;
        double min = Math.min((f - d7) / Math.max(d5, 1.0E-7d), (f2 - d7) / Math.max(d6, 1.0E-7d));
        double d8 = (f - (d5 * min)) * 0.5d;
        double d9 = (f2 - (d6 * min)) * 0.5d;
        for (int i2 = 0; i2 < size; i2++) {
            GeoPoint geoPoint2 = (GeoPoint) list.get(i2);
            double lat2 = geoPoint2.getLat();
            double lng2 = ((geoPoint2.getLng() - d3) * cos * min) + d8;
            int i3 = i2 * 2;
            fArr[i3] = (float) lng2;
            fArr[i3 + 1] = (float) (((d2 - lat2) * min) + d9);
        }
        return fArr;
    }

    public static void txt(Canvas canvas, String str, float f, float f2, float f3, int i, boolean z) {
        Paint paint = new Paint(1);
        paint.setTextSize(f3);
        paint.setColor(i);
        paint.setFakeBoldText(z);
        canvas.drawText(str, f, f2, paint);
    }

    public static boolean vxApplies(int i, int i2) {
        return i == 1 ? i2 == 0 : (i == 2 || i == 3) ? i2 == 1 : i == 4 ? i2 == 2 : i == 5 ? i2 >= 3 : i == 6 && i2 == 0;
    }

    public static void vxFeature(Vx vx, int i) {
        int vxRoadCode;
        int vxRoadC;
        float vxRoadW;
        int i2;
        int i3;
        int i4 = 0;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        while (vxPos(vx) < i) {
            int vxVar = vxVar(vx);
            int i9 = vxVar >>> 3;
            int i10 = vxVar & 7;
            if (i9 == 2) {
                int vxVar2 = vxVar(vx);
                i5 = vxPos(vx);
                i6 = i5 + vxVar2;
                vxSet(vx, i6);
            } else if (i9 == 3) {
                i4 = vxVar(vx);
            } else if (i9 == 4) {
                int vxVar3 = vxVar(vx);
                i7 = vxPos(vx);
                i8 = i7 + vxVar3;
                vxSet(vx, i8);
            } else {
                vxSkip(vx, i10);
            }
        }
        if (i7 >= 0) {
            String str = null;
            if (i5 >= 0 && (i3 = vx.ck) >= 0) {
                vxSet(vx, i5);
                while (vxPos(vx) < i6) {
                    int vxVar4 = vxVar(vx);
                    int vxVar5 = vxVar(vx);
                    if (vxVar4 == i3) {
                        ArrayList arrayList = vx.vals;
                        if (vxVar5 < arrayList.size()) {
                            str = (String) arrayList.get(vxVar5);
                        }
                        break;
                    }
                }
            }
            int i11 = vx.kind;
            int i12 = vx.pass;
            if (i4 == 3) {
                boolean draw = true;
                i2 = 0;
                if (i11 == 1) {
                    if (vxIn(str, "|wood|forest|grass|park|garden|cemetery|pitch|golf_course|recreation_ground|village_green|meadow|scrub|wetland|nature_reserve|national_park|protected_area|playground|")) {
                        i2 = -3282000;
                    } else if (vxIn(str, "|farmland|farm|orchard|vineyard|")) {
                        i2 = -1117995;
                    } else {
                        draw = false;
                    }
                } else if (i11 == 2) {
                    i2 = -5581857;
                } else if (i11 == 4) {
                    i2 = -2502455;
                } else if (i11 == 6) {
                    i2 = -3282000;
                } else if (i11 == 5 && i12 == 3) {
                    i2 = -790036;
                } else {
                    draw = false;
                }
                if (draw) {
                    Path path = vx.path;
                    path.reset();
                    vxPath(vx, i7, i8);
                    Paint paint = vx.p;
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(i2);
                    vx.c.drawPath(path, paint);
                }
            } else if (i4 == 2) {
                boolean draw = false;
                vxRoadC = 0;
                vxRoadW = 0.0f;
                if (i11 == 3) {
                    vxRoadC = -5581857;
                    vxRoadW = 1.5f;
                    draw = true;
                } else if (i11 == 5 && (vxRoadCode = vxRoadCode(str)) != 0) {
                    if (i12 == 3) {
                        if (vxRoadCode < 6) {
                            vxRoadC = -4870752;
                            vxRoadW = vxRoadW(vxRoadCode) + 1.5f;
                            draw = true;
                        }
                    } else if (i12 == 4) {
                        vxRoadC = vxRoadC(vxRoadCode);
                        vxRoadW = vxRoadW(vxRoadCode);
                        draw = true;
                    }
                }
                if (draw) {
                    float f = (vxRoadW * vx.zf) / vx.s;
                    Path path3 = vx.path;
                    path3.reset();
                    vxPath(vx, i7, i8);
                    Paint paint3 = vx.p;
                    paint3.setStyle(Paint.Style.STROKE);
                    paint3.setStrokeWidth(f);
                    paint3.setColor(vxRoadC);
                    vx.c.drawPath(path3, paint3);
                }
            }
        }
        vxSet(vx, i);
    }

    public static boolean vxIn(String str, String str2) {
        if (str == null) {
            return false;
        }
        return str2.contains("|" + str + "|");
    }

    public static int vxKind(String str) {
        if (str == null) {
            return 0;
        }
        if (vxIn(str, "|landcover|landuse|land|natural|earth_cover|")) {
            return 1;
        }
        if (vxIn(str, "|water|ocean|water_polygons|")) {
            return 2;
        }
        if (vxIn(str, "|waterway|water_lines|waterways|")) {
            return 3;
        }
        if (vxIn(str, "|building|buildings|")) {
            return 4;
        }
        if (vxIn(str, "|transportation|road|roads|streets|")) {
            return 5;
        }
        return vxIn(str, "|park|parks|") ? 6 : 0;
    }

    public static void vxLayer(Vx vx, int i) {
        int vxPos = vxPos(vx);
        String str = null;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i2 = 4096;
        while (vxPos(vx) < i) {
            int vxVar = vxVar(vx);
            int i3 = vxVar >>> 3;
            int i4 = vxVar & 7;
            if (i3 == 1) {
                str = vxStr(vx, vxVar(vx));
            } else if (i3 == 3) {
                arrayList.add(vxStr(vx, vxVar(vx)));
            } else if (i3 == 4) {
                arrayList2.add(vxValStr(vx, vxVar(vx)));
            } else if (i3 == 5) {
                i2 = vxVar(vx);
            } else {
                vxSkip(vx, i4);
            }
        }
        int vxKind = vxKind(str);
        if (vxKind != 0 && i2 > 0 && vxApplies(vxKind, vx.pass)) {
            vx.kind = vxKind;
            vx.keys = arrayList;
            vx.vals = arrayList2;
            int indexOf = arrayList.indexOf("class");
            if (indexOf < 0 && (indexOf = arrayList.indexOf("kind")) < 0) {
                indexOf = arrayList.indexOf("type");
            }
            vx.ck = indexOf;
            vx.k = 256.0f / i2;
            vxSet(vx, vxPos);
            while (vxPos(vx) < i) {
                int vxVar2 = vxVar(vx);
                int i5 = vxVar2 >>> 3;
                int i6 = vxVar2 & 7;
                if (i5 == 2) {
                    vxFeature(vx, vxPos(vx) + vxVar(vx));
                } else {
                    vxSkip(vx, i6);
                }
            }
        }
        vxSet(vx, i);
    }

    public static void vxPass(Vx vx) {
        vxSet(vx, 0);
        int length = vx.d.length;
        while (vxPos(vx) < length) {
            int vxVar = vxVar(vx);
            int i = vxVar >>> 3;
            int i2 = vxVar & 7;
            if (i == 3 && i2 == 2) {
                vxLayer(vx, vxPos(vx) + vxVar(vx));
            } else {
                vxSkip(vx, i2);
            }
        }
    }

    public static void vxPath(Vx vx, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        float f = vx.k;
        Path path = vx.path;
        vxSet(vx, i);
        while (vxPos(vx) < i2) {
            int vxVar = vxVar(vx);
            int i5 = vxVar & 7;
            if (i5 == 7) {
                path.close();
            } else {
                for (int i6 = vxVar >>> 3; i6 > 0; i6--) {
                    i3 += vxZz(vxVar(vx));
                    i4 += vxZz(vxVar(vx));
                    float f2 = i3 * f;
                    float f3 = i4 * f;
                    if (i5 == 1) {
                        path.moveTo(f2, f3);
                    } else {
                        path.lineTo(f2, f3);
                    }
                }
            }
        }
    }

    public static int vxPos(Vx vx) {
        return vx.pos[0];
    }

    public static Bitmap vxRender(byte[] bArr, int i, int i2) {
        Vx vx = new Vx();
        vx.d = bArr;
        vx.pos = new int[1];
        Bitmap createBitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        vx.c = canvas;
        canvas.drawColor(-856087);
        Paint paint = new Paint(1);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        vx.p = paint;
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        vx.path = path;
        float f = 1 << (i2 & 15);
        vx.s = f;
        canvas.translate(-(((i2 >>> 4) & 255) * 256), -(((i2 >>> 12) & 255) * 256));
        canvas.scale(f, f);
        float f2 = (((i + (i2 & 15)) - 10) * 0.22f) + 0.5f;
        if (f2 < 0.5f) {
            f2 = 0.5f;
        }
        if (f2 > 2.2f) {
            f2 = 2.2f;
        }
        vx.zf = f2;
        int i3 = 0;
        do {
            vx.pass = i3;
            try {
                vxPass(vx);
            } catch (Throwable th) {
                mbNote(th);
            }
            i3++;
        } while (i3 < 5);
        return createBitmap;
    }

    public static int vxRoadC(int i) {
        if (i == 1) {
            return -1535328;
        }
        if (i == 2) {
            return -147601;
        }
        if (i == 3) {
            return -525633;
        }
        if (i == 4 || i == 5) {
            return -1;
        }
        if (i == 6) {
            return -5201776;
        }
        return i == 7 ? -7697782 : -1;
    }

    public static int vxRoadCode(String str) {
        if (vxIn(str, "|motorway|trunk|highway|motorway_link|trunk_link|")) {
            return 1;
        }
        if (vxIn(str, "|primary|major_road|primary_link|")) {
            return 2;
        }
        if (vxIn(str, "|secondary|medium_road|secondary_link|")) {
            return 3;
        }
        if (vxIn(str, "|tertiary|tertiary_link|")) {
            return 4;
        }
        if (vxIn(str, "|minor|service|residential|unclassified|living_street|road|busway|minor_road|street|street_limited|other|")) {
            return 5;
        }
        if (vxIn(str, "|track|path|pedestrian|footway|cycleway|bridleway|steps|")) {
            return 6;
        }
        return vxIn(str, "|rail|transit|major_rail|minor_rail|tram|light_rail|subway|narrow_gauge|") ? 7 : 0;
    }

    public static float vxRoadW(int i) {
        if (i == 1) {
            return 4.0f;
        }
        if (i == 2) {
            return 3.3999999f;
        }
        if (i == 3) {
            return 3.0f;
        }
        if (i == 4) {
            return 2.6f;
        }
        if (i == 5) {
            return 2.0f;
        }
        return (i == 6 || i == 7) ? 1.0f : 0.0f;
    }

    public static void vxSet(Vx vx, int i) {
        vx.pos[0] = i;
    }

    public static void vxSkip(Vx vx, int i) {
        if (i == 0) {
            vxVar(vx);
        } else {
            vxSet(vx, vxPos(vx) + (i == 1 ? 8 : i == 2 ? vxVar(vx) : 4));
        }
    }

    public static String vxStr(Vx vx, int i) {
        String str = null;
        try {
            str = new String(vx.d, vxPos(vx), i, "UTF-8");
        } catch (Throwable unused) {
        }
        vxSet(vx, vxPos(vx) + i);
        return str;
    }

    public static String vxValStr(Vx vx, int i) {
        String str = null;
        int vxPos = vxPos(vx) + i;
        while (vxPos(vx) < vxPos) {
            int vxVar = vxVar(vx);
            if (vxVar == 10) {
                str = vxStr(vx, vxVar(vx));
            } else {
                vxSkip(vx, vxVar & 7);
            }
        }
        vxSet(vx, vxPos);
        return str;
    }

    public static int vxVar(Vx vx) {
        byte[] bArr = vx.d;
        int[] iArr = vx.pos;
        int i = iArr[0];
        int i2 = 0;
        int i3 = 0;
        while (true) {
            byte b = bArr[i];
            i++;
            i2 |= (b & 0x7F) << i3;
            if ((b & 0x80) == 0) {
                iArr[0] = i;
                return i2;
            }
            i3 += 7;
        }
    }

    public static int vxZz(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static void wxFetch(double d, double d2) {
        JSONObject jSONObject;
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        try {
            String httpGet = httpGet("https://api.open-meteo.com/v1/forecast?latitude=" + d + "&longitude=" + d2 + "&current=temperature_2m,weather_code,is_day&hourly=precipitation_probability&forecast_hours=3&timezone=auto");
            if (httpGet == null || (optJSONObject = (jSONObject = new JSONObject(httpGet)).optJSONObject("current")) == null) {
                return;
            }
            double optDouble = optJSONObject.optDouble("temperature_2m");
            if (Double.isNaN(optDouble)) {
                return;
            }
            float f = (float) optDouble;
            int optInt = optJSONObject.optInt("weather_code");
            int i = 0;
            JSONObject optJSONObject2 = jSONObject.optJSONObject("hourly");
            if (optJSONObject2 != null && (optJSONArray = optJSONObject2.optJSONArray("precipitation_probability")) != null) {
                int min = Math.min(optJSONArray.length(), 3);
                for (int i2 = 0; i2 < min; i2++) {
                    int optInt2 = optJSONArray.optInt(i2);
                    if (optInt2 > i) {
                        i = optInt2;
                    }
                }
            }
            int day = optJSONObject.optInt("is_day", 1);
            // The weather pill shows for 10 s only when the weather is new or has changed.
            if (wxAt == 0 || optInt != wxCode || Math.round(f) != Math.round(wxTemp) || day != wxDay) {
                wxShowUntil = SystemClock.elapsedRealtime() + 10000L;
            }
            wxDay = day;
            wxTemp = f;
            wxCode = optInt;
            wxRain = i;
            wxAt = SystemClock.elapsedRealtime();
            wxCLat = d;
            wxCLng = d2;
        } catch (Throwable unused) {
        }
    }

    public static String wxLabel(int i) {
        return i == 0 ? "Açık" : i <= 3 ? "Bulutlu" : i <= 48 ? "Sisli" : i <= 57 ? "Çisenti" : i <= 67 ? "Yağmurlu" : i <= 77 ? "Karlı" : i <= 82 ? "Sağanak" : i <= 86 ? "Kar yağışlı" : "Fırtına";
    }
}
