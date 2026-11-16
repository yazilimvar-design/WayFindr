package com.example.wayfindr.dash.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;
import com.example.wayfindr.dash.nav.GPlus;
import com.example.wayfindr.dash.nav.NavPlus;
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
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.coroutines.Continuation;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* compiled from: TileProvider.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
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
    private final ConcurrentHashMap failedUntil;
    private volatile boolean hiDpi;
    private final ConcurrentHashMap.KeySetView inflight;
    private volatile long lastFetchAt;
    private final boolean liveOsmOnly;
    private final memory.1 memory;
    private Job routeJob;
    private final CoroutineScope scope;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int $stable = 8;
    private static final String OSM_USER_AGENT = "Wayfindr/1.3.2-OSS-preview (personal motorcycle navigation app; Android)";

    public TileProvider(Context context, CoroutineScope coroutineScope) {
        GPlus.init(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(coroutineScope, "scope");
        GPlus.init(context);
        this.scope = coroutineScope;
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        this.cm = (ConnectivityManager) systemService;
        File file = new File(context.getCacheDir(), "tiles_osm_v1");
        file.mkdirs();
        this.diskDirOsm = file;
        this.liveOsmOnly = true;
        this.memory = new memory.1(Companion.access$memoryCacheBytes(Companion, context));
        this.inflight = ConcurrentHashMap.newKeySet();
        this.failedUntil = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File diskFile(String str) {
        return new File(GPlus.tileDir(this.diskDirOsm), StringsKt.replace$default(StringsKt.removePrefix(str, "osm/"), '/', '_', false, 4, (Object) null) + ".png");
    }

    /* JADX WARN: Code restructure failed: missing block: B:55:0x007f, code lost:
    
        if (r0 == null) goto L15;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0 */
    /* JADX WARN: Type inference failed for: r16v1 */
    /* JADX WARN: Type inference failed for: r16v2, types: [int] */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* JADX WARN: Type inference failed for: r16v6 */
    /* JADX WARN: Type inference failed for: r16v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Bitmap download(int i, int i2, int i3, String str, int i4) {
        Bitmap bitmap;
        URLConnection openConnection;
        long currentTimeMillis = MIN_FETCH_GAP_MS - (System.currentTimeMillis() - this.lastFetchAt);
        if (currentTimeMillis > 0) {
            try {
                Thread.sleep(currentTimeMillis);
            } catch (InterruptedException e) {
            }
        }
        this.lastFetchAt = System.currentTimeMillis();
        Network internetNetwork = internetNetwork();
        try {
            bitmap = i3;
            String tileUrl = GPlus.tileUrl(String.format(OSM_URL_TEMPLATE, Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(((i2 % i4) + i4) % i4), Integer.valueOf(((i3 % i4) + i4) % i4)}, 3)), i, i2, bitmap, i4, getHiDpi());
            Intrinsics.checkNotNullExpressionValue(tileUrl, "format(...)");
            URL url = new URL(tileUrl);
            ApiUsage.INSTANCE.record(GPlus.usageName("OpenStreetMap tiles (free)"));
            if (internetNetwork != null) {
                try {
                    openConnection = internetNetwork.openConnection(url);
                } catch (Exception e2) {
                    e = e2;
                    bitmap = 0;
                    Log.w(TAG, "Tile " + str + " fetch failed: " + e.getMessage());
                    return bitmap;
                }
            }
            openConnection = url.openConnection();
            Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
            HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
            httpURLConnection.setRequestProperty("User-Agent", OSM_USER_AGENT);
            httpURLConnection.setConnectTimeout(8000);
            httpURLConnection.setReadTimeout(8000);
            int responseCode = httpURLConnection.getResponseCode();
            if (!(200 <= responseCode && responseCode < 300)) {
                int responseCode2 = httpURLConnection.getResponseCode();
                GPlus.tileHttp(responseCode2);
                Log.w(TAG, "Tile " + str + " HTTP " + responseCode2);
                httpURLConnection.disconnect();
                return null;
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            try {
                try {
                    InputStream inputStream2 = inputStream;
                    bitmap = 0;
                    Intrinsics.checkNotNull(inputStream2);
                    byte[] readBytes = ByteStreamsKt.readBytes(inputStream2);
                    CloseableKt.closeFinally(inputStream, (Throwable) null);
                    httpURLConnection.disconnect();
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(readBytes, 0, readBytes.length);
                    if (decodeByteArray != null) {
                        FilesKt.writeBytes(diskFile(str), readBytes);
                        return decodeByteArray;
                    }
                    bitmap = 0;
                    try {
                        Log.w(TAG, "Tile " + str + " decoded null (" + readBytes.length + "B) — not caching");
                        return null;
                    } catch (Exception e3) {
                        e = e3;
                        Log.w(TAG, "Tile " + str + " fetch failed: " + e.getMessage());
                        return bitmap;
                    }
                } catch (Exception e4) {
                    e = e4;
                    Log.w(TAG, "Tile " + str + " fetch failed: " + e.getMessage());
                    return bitmap;
                }
            } finally {
            }
        } catch (Exception e5) {
            e = e5;
            bitmap = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap fetch(int i, int i2, int i3, String str) {
        Bitmap mbTile = NavPlus.mbTile(str);
        return mbTile != null ? mbTile : fetchKey(i, i2, i3, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap fetchKey(int i, int i2, int i3, String str) {
        int i4 = 1 << i;
        if (i3 >= 0 && i3 < i4) {
            Pair pair = (Pair) this.failedUntil.get(str);
            if (pair != null) {
                if (System.currentTimeMillis() < ((Number) pair.component1()).longValue()) {
                    return null;
                }
            }
            Bitmap download = download(i, i2, i3, str, i4);
            ConcurrentHashMap concurrentHashMap = this.failedUntil;
            if (download == null) {
                Pair pair2 = (Pair) concurrentHashMap.get(str);
                long longValue = pair2 != null ? ((Number) pair2.getSecond()).longValue() : 0L;
                long coerceAtMost = longValue == 0 ? FAIL_BACKOFF_MS : RangesKt.coerceAtMost(2 * longValue, FAIL_BACKOFF_MAX_MS);
                this.failedUntil.put(str, TuplesKt.to(Long.valueOf(System.currentTimeMillis() + coerceAtMost), Long.valueOf(coerceAtMost)));
            } else {
                concurrentHashMap.remove(str);
            }
            return download;
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0038, code lost:
    
        if ((r8.hasCapability(12) && r8.hasCapability(16)) == true) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final Network internetNetwork() {
        Network network;
        boolean z;
        Network[] allNetworks = this.cm.getAllNetworks();
        Intrinsics.checkNotNullExpressionValue(allNetworks, "getAllNetworks(...)");
        Network[] networkArr = allNetworks;
        int length = networkArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                network = null;
                break;
            }
            network = networkArr[i];
            NetworkCapabilities networkCapabilities = this.cm.getNetworkCapabilities(network);
            if (networkCapabilities != null) {
                z = true;
            }
            z = false;
            if (z) {
                break;
            }
            i++;
        }
        return network;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String keyOf(int i, int i2, int i3) {
        return "osm/" + i + "/" + i2 + "/" + i3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap loadDisk(String str) {
        Bitmap mbTile = NavPlus.mbTile(str);
        if (mbTile != null) {
            return mbTile;
        }
        File diskFile = diskFile(str);
        if (diskFile.exists()) {
            return BitmapFactory.decodeFile(diskFile.getAbsolutePath());
        }
        return null;
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

    /* JADX INFO: Access modifiers changed from: private */
    public final int prefetchAround(double d, double d2, int i, int i2) {
        int lngToTileX = (int) Mercator.INSTANCE.lngToTileX(d2, i);
        int latToTileY = (int) Mercator.INSTANCE.latToTileY(d, i);
        int i3 = 0;
        int i4 = -i2;
        if (i4 <= i2) {
            while (true) {
                int i5 = -i2;
                if (i5 <= i2) {
                    while (true) {
                        int i6 = lngToTileX + i4;
                        int i7 = latToTileY + i5;
                        if (i7 >= 0 && i7 < (1 << i)) {
                            String keyOf = keyOf(i, i6, i7);
                            if (!diskFile(keyOf).exists()) {
                                fetchKey(i, i6, i7, keyOf);
                                i3++;
                            }
                        }
                        if (i5 == i2) {
                            break;
                        }
                        i5++;
                    }
                }
                if (i4 == i2) {
                    break;
                }
                i4++;
            }
        }
        return i3;
    }

    public static /* synthetic */ void prefetchZoom$default(TileProvider tileProvider, double d, double d2, int i, int i2, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            i2 = 2;
        }
        tileProvider.prefetchZoom(d, d2, i, i2);
    }

    public final Bitmap get(int i, int i2, int i3) {
        int i4 = 1 << i;
        if (i3 < 0 || i3 >= i4) {
            return null;
        }
        int i5 = ((i2 % i4) + i4) % i4;
        String keyOf = keyOf(i, i5, i3);
        Bitmap bitmap = (Bitmap) this.memory.get(keyOf);
        if (bitmap != null) {
            return bitmap;
        }
        if (this.inflight.add(keyOf)) {
            BuildersKt.launch$default(this.scope, Dispatchers.getIO(), (CoroutineStart) null, new get.2(this, keyOf, i, i5, i3, (Continuation) null), 2, (Object) null);
        }
        return null;
    }

    public final Bitmap getCached(int i, int i2, int i3) {
        int i4 = 1 << i;
        if (i3 < 0 || i3 >= i4) {
            return null;
        }
        return (Bitmap) this.memory.get(keyOf(i, ((i2 % i4) + i4) % i4, i3));
    }

    public final boolean getHiDpi() {
        return this.hiDpi;
    }

    public final void prefetch(double d, double d2, Double d3, Double d4) {
        if (this.liveOsmOnly) {
            return;
        }
        BuildersKt.launch$default(this.scope, Dispatchers.getIO(), (CoroutineStart) null, new prefetch.1(this, d, d2, d3, d4, (Continuation) null), 2, (Object) null);
    }

    public final void prefetchRoute(List list) {
        Intrinsics.checkNotNullParameter(list, "route");
        if (list.size() < 2) {
            return;
        }
        Job job = this.routeJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        if (this.liveOsmOnly) {
            RideDiagnostics.INSTANCE.log("tiles", "route cache skipped: OpenStreetMap tiles can't be pre-downloaded (OSM tile policy)");
        } else {
            this.routeJob = BuildersKt.launch$default(this.scope, Dispatchers.getIO(), (CoroutineStart) null, new prefetchRoute.1(list, this, (Continuation) null), 2, (Object) null);
        }
    }

    public final void prefetchZoom(double d, double d2, int i, int i2) {
        if (i >= 0 && !this.liveOsmOnly) {
            BuildersKt.launch$default(this.scope, Dispatchers.getIO(), (CoroutineStart) null, new prefetchZoom.1(this, d, d2, i, i2, (Continuation) null), 2, (Object) null);
        }
    }

    public final void setHiDpi(boolean z) {
        this.hiDpi = z;
    }
}
