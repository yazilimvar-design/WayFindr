package com.example.wayfindr.dash.map;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import android.util.LruCache;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.data.RideDiagnostics;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import okhttp3.internal.ws.RealWebSocket;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: TileProvider.kt */
@Metadata(d1 = {"\u0000\u008d\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u001a\b\u0007\u0018\u0000 A2\u00020\u0001:\u0001AB\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J \u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016J \u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016J(\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010)\u001a\u00020\u0016J3\u0010*\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010'2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010'¢\u0006\u0002\u0010-J\u0014\u00100\u001a\u00020%2\f\u00101\u001a\b\u0012\u0004\u0012\u00020302J(\u00104\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010)\u001a\u00020\u0016H\u0002J\u0010\u00105\u001a\u00020\u000b2\u0006\u00106\u001a\u00020\u0014H\u0002J\u0012\u00107\u001a\u0004\u0018\u00010\"2\u0006\u00106\u001a\u00020\u0014H\u0002J*\u00108\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u0014H\u0002J*\u0010<\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u0014H\u0002J2\u0010=\u001a\u0004\u0018\u00010\"2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00142\u0006\u0010>\u001a\u00020\u0016H\u0002J\n\u0010?\u001a\u0004\u0018\u00010@H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\rX\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001bRN\u0010\u001c\u001aB\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00140\u0014\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\r0\r \u001e* \u0012\f\u0012\n \u001e*\u0004\u0018\u00010\u00140\u0014\u0012\f\u0012\n \u001e*\u0004\u0018\u00010\r0\r\u0018\u00010\u001d0\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000R&\u00109\u001a\u001a\u0012\u0004\u0012\u00020\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0;0:X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/example/wayfindr/dash/map/TileProvider;", "", "context", "Landroid/content/Context;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;)V", "cm", "Landroid/net/ConnectivityManager;", "diskDirOsm", "Ljava/io/File;", "hiDpi", "", "getHiDpi", "()Z", "setHiDpi", "(Z)V", "liveOsmOnly", "keyOf", "", "z", "", "x", "y", "memory", "com/example/wayfindr/dash/map/TileProvider$memory$1", "Lcom/example/wayfindr/dash/map/TileProvider$memory$1;", "inflight", "Ljava/util/concurrent/ConcurrentHashMap$KeySetView;", "kotlin.jvm.PlatformType", "lastFetchAt", "", "get", "Landroid/graphics/Bitmap;", "getCached", "prefetchZoom", "", "lat", "", "lng", "radius", "prefetch", "fromLat", "fromLng", "(DDLjava/lang/Double;Ljava/lang/Double;)V", "routeJob", "Lkotlinx/coroutines/Job;", "prefetchRoute", "route", "", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "prefetchAround", "diskFile", "key", "loadDisk", "fetch", "failedUntil", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlin/Pair;", "fetchKey", "download", "max", "internetNetwork", "Landroid/net/Network;", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class TileProvider {
    private static final long FAIL_BACKOFF_MAX_MS = 900000;
    private static final long FAIL_BACKOFF_MS = 60000;
    private static final int MAX_PREFETCH_TILES = 600;
    private static final long MIN_FETCH_GAP_MS = 60;
    public static final String OSM_ATTRIBUTION = "© OpenStreetMap contributors";
    private static final long OSM_REVALIDATE_MS = 604800000;
    private static final String OSM_URL_TEMPLATE = "https://tile.openstreetmap.org/%d/%d/%d.png";
    private static final String TAG = "TileProvider";
    private final ConnectivityManager cm;
    private final File diskDirOsm;
    private final ConcurrentHashMap<String, Pair<Long, Long>> failedUntil;
    private volatile boolean hiDpi;
    private final ConcurrentHashMap.KeySetView<String, Boolean> inflight;
    private volatile long lastFetchAt;
    private final boolean liveOsmOnly;
    private final TileProvider$memory$1 memory;
    private Job routeJob;
    private final CoroutineScope scope;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final String OSM_USER_AGENT = "Wayfindr/1.3.2-OSS-preview (personal motorcycle navigation app; Android)";

    /* compiled from: TileProvider.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/wayfindr/dash/map/TileProvider$Companion;", "", "<init>", "()V", "TAG", "", "OSM_URL_TEMPLATE", "OSM_USER_AGENT", "OSM_REVALIDATE_MS", "", "OSM_ATTRIBUTION", "MAX_PREFETCH_TILES", "", "FAIL_BACKOFF_MS", "FAIL_BACKOFF_MAX_MS", "MIN_FETCH_GAP_MS", "memoryCacheBytes", "ctx", "Landroid/content/Context;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final int memoryCacheBytes(Context ctx) {
            Object systemService = ctx.getSystemService("activity");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
            ActivityManager am = (ActivityManager) systemService;
            long heapBytes = am.getMemoryClass() * RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE * RealWebSocket.DEFAULT_MINIMUM_DEFLATE_SIZE;
            return (int) RangesKt.coerceIn(heapBytes / 5, 50331648L, 134217728L);
        }
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.example.wayfindr.dash.map.TileProvider$memory$1] */
    public TileProvider(Context context, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.scope = scope;
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.cm = (ConnectivityManager) systemService;
        File file = new File(context.getCacheDir(), "tiles_osm_v1");
        file.mkdirs();
        this.diskDirOsm = file;
        this.liveOsmOnly = true;
        final int memoryCacheBytes = INSTANCE.memoryCacheBytes(context);
        this.memory = new LruCache<String, Bitmap>(memoryCacheBytes) { // from class: com.example.wayfindr.dash.map.TileProvider$memory$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            public int sizeOf(String key, Bitmap value) {
                Intrinsics.checkNotNullParameter(key, "key");
                Intrinsics.checkNotNullParameter(value, "value");
                return value.getAllocationByteCount();
            }
        };
        this.inflight = ConcurrentHashMap.newKeySet();
        this.failedUntil = new ConcurrentHashMap<>();
    }

    public final boolean getHiDpi() {
        return this.hiDpi;
    }

    public final void setHiDpi(boolean z) {
        this.hiDpi = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String keyOf(int z, int x, int y) {
        return "osm/" + z + "/" + x + "/" + y;
    }

    public final Bitmap get(int z, int x, int y) {
        int max = 1 << z;
        if (y < 0 || y >= max) {
            return null;
        }
        int xw = ((x % max) + max) % max;
        String key = keyOf(z, xw, y);
        Bitmap bitmap = get(key);
        if (bitmap != null) {
            return bitmap;
        }
        if (this.inflight.add(key)) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new TileProvider$get$2(this, key, z, xw, y, null), 2, null);
        }
        return null;
    }

    public final Bitmap getCached(int z, int x, int y) {
        int max = 1 << z;
        if (y < 0 || y >= max) {
            return null;
        }
        int xw = ((x % max) + max) % max;
        return get(keyOf(z, xw, y));
    }

    public static /* synthetic */ void prefetchZoom$default(TileProvider tileProvider, double d, double d2, int i, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = 2;
        }
        tileProvider.prefetchZoom(d, d2, i, i2);
    }

    public final void prefetchZoom(double lat, double lng, int z, int radius) {
        if (z >= 0 && !this.liveOsmOnly) {
            BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new TileProvider$prefetchZoom$1(this, lat, lng, z, radius, null), 2, null);
        }
    }

    public static /* synthetic */ void prefetch$default(TileProvider tileProvider, double d, double d2, Double d3, Double d4, int i, Object obj) {
        if ((i & 4) != 0) {
            d3 = null;
        }
        if ((i & 8) != 0) {
            d4 = null;
        }
        tileProvider.prefetch(d, d2, d3, d4);
    }

    public final void prefetch(double lat, double lng, Double fromLat, Double fromLng) {
        if (this.liveOsmOnly) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new TileProvider$prefetch$1(this, lat, lng, fromLat, fromLng, null), 2, null);
    }

    public final void prefetchRoute(List<GeoPoint> route) {
        Job launch$default;
        Intrinsics.checkNotNullParameter(route, "route");
        if (route.size() < 2) {
            return;
        }
        Job job = this.routeJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (!this.liveOsmOnly) {
            launch$default = BuildersKt__Builders_commonKt.launch$default(this.scope, Dispatchers.getIO(), null, new TileProvider$prefetchRoute$1(route, this, null), 2, null);
            this.routeJob = launch$default;
        } else {
            RideDiagnostics.INSTANCE.log("tiles", "route cache skipped: OpenStreetMap tiles can't be pre-downloaded (OSM tile policy)");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int prefetchAround(double lat, double lng, int z, int radius) {
        int cx = (int) Mercator.INSTANCE.lngToTileX(lng, z);
        int cy = (int) Mercator.INSTANCE.latToTileY(lat, z);
        int n = 0;
        int dx = -radius;
        if (dx <= radius) {
            while (true) {
                int dy = -radius;
                if (dy <= radius) {
                    while (true) {
                        int x = cx + dx;
                        int y = cy + dy;
                        if (y >= 0 && y < (1 << z)) {
                            String key = keyOf(z, x, y);
                            if (!diskFile(key).exists()) {
                                fetchKey(z, x, y, key);
                                n++;
                            }
                        }
                        if (dy == radius) {
                            break;
                        }
                        dy++;
                    }
                }
                if (dx == radius) {
                    break;
                }
                dx++;
            }
        }
        return n;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File diskFile(String key) {
        return new File(this.diskDirOsm, StringsKt.replace$default(StringsKt.removePrefix(key, (CharSequence) "osm/"), '/', '_', false, 4, (Object) null) + ".png");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap loadDisk(String key) {
        File f = diskFile(key);
        if (f.exists()) {
            return BitmapFactory.decodeFile(f.getAbsolutePath());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap fetch(int z, int x, int y, String key) {
        return fetchKey(z, x, y, key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap fetchKey(int z, int x, int y, String key) {
        int max = 1 << z;
        if (y >= 0 && y < max) {
            Pair<Long, Long> pair = this.failedUntil.get(key);
            if (pair != null) {
                if (System.currentTimeMillis() < pair.component1().longValue()) {
                    return null;
                }
            }
            Bitmap bmp = download(z, x, y, key, max);
            ConcurrentHashMap<String, Pair<Long, Long>> concurrentHashMap = this.failedUntil;
            if (bmp == null) {
                Pair<Long, Long> pair2 = concurrentHashMap.get(key);
                long prev = pair2 != null ? pair2.getSecond().longValue() : 0L;
                long backoff = prev == 0 ? FAIL_BACKOFF_MS : RangesKt.coerceAtMost(2 * prev, FAIL_BACKOFF_MAX_MS);
                this.failedUntil.put(key, TuplesKt.to(Long.valueOf(System.currentTimeMillis() + backoff), Long.valueOf(backoff)));
            } else {
                concurrentHashMap.remove(key);
            }
            return bmp;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x006a, code lost:
    
        if (r0 == null) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Bitmap download(int z, int x, int y, String key, int max) {
        Bitmap bitmap;
        URLConnection openConnection;
        long now = System.currentTimeMillis();
        long wait = MIN_FETCH_GAP_MS - (now - this.lastFetchAt);
        if (wait > 0) {
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
            }
        }
        this.lastFetchAt = System.currentTimeMillis();
        Network net = internetNetwork();
        try {
            int yw = ((y % max) + max) % max;
            String format = String.format(OSM_URL_TEMPLATE, Arrays.copyOf(new Object[]{Integer.valueOf(z), Integer.valueOf(((x % max) + max) % max), Integer.valueOf(yw)}, 3));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            URL url = new URL(format);
            ApiUsage.INSTANCE.record(ApiUsage.OSM_TILES);
            if (net != null) {
                try {
                    openConnection = net.openConnection(url);
                } catch (Exception e2) {
                    e = e2;
                    bitmap = null;
                    Log.w(TAG, "Tile " + key + " fetch failed: " + e.getMessage());
                    return bitmap;
                }
            }
            openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection conn = (HttpURLConnection) openConnection;
            conn.setRequestProperty("User-Agent", OSM_USER_AGENT);
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            int responseCode = conn.getResponseCode();
            if (!(200 <= responseCode && responseCode < 300)) {
                Log.w(TAG, "Tile " + key + " HTTP " + conn.getResponseCode());
                conn.disconnect();
                return null;
            }
            InputStream inputStream = conn.getInputStream();
            try {
                try {
                    InputStream inputStream2 = inputStream;
                    bitmap = null;
                    Intrinsics.checkNotNull(inputStream2);
                    byte[] bytes = ByteStreamsKt.readBytes(inputStream2);
                    CloseableKt.closeFinally(inputStream, null);
                    conn.disconnect();
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    if (bmp != null) {
                        FilesKt.writeBytes(diskFile(key), bytes);
                        return bmp;
                    }
                    bitmap = null;
                    try {
                        Log.w(TAG, "Tile " + key + " decoded null (" + bytes.length + "B) — not caching");
                        return null;
                    } catch (Exception e3) {
                        e = e3;
                        Log.w(TAG, "Tile " + key + " fetch failed: " + e.getMessage());
                        return bitmap;
                    }
                } catch (Exception e4) {
                    e = e4;
                }
            } finally {
            }
        } catch (Exception e5) {
            e = e5;
            bitmap = null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
    
        if ((r8.hasCapability(12) && r8.hasCapability(16)) == true) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Network internetNetwork() {
        Object obj;
        boolean z;
        Object[] allNetworks = this.cm.getAllNetworks();
        Intrinsics.checkNotNullExpressionValue(allNetworks, "getAllNetworks(...)");
        Object[] objArr = allNetworks;
        int length = objArr.length;
        int i = 0;
        while (true) {
            if (i < length) {
                obj = objArr[i];
                NetworkCapabilities networkCapabilities = this.cm.getNetworkCapabilities((Network) obj);
                if (networkCapabilities != null) {
                    z = true;
                }
                z = false;
                if (z) {
                    break;
                }
                i++;
            } else {
                obj = null;
                break;
            }
        }
        return (Network) obj;
    }
}
