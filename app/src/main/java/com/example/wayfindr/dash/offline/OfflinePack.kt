package com.example.wayfindr.dash.offline

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.StatFs
import android.util.Log
import com.example.wayfindr.util.InternetNetwork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection

/**
 * "Türkiye çevrimdışı paketi": the offline vector map of Turkey (PMTiles, published monthly by the
 * tr-map workflow of the public WayFindr repo), its place search index (SQLite FTS4 from OSM, same
 * release, "search" in the manifest) plus the 8 BRouter routing segments that cover Turkey.
 * With it the console map ([com.example.wayfindr.dash.map.VectorTiles]), routing
 * ([com.example.wayfindr.dash.nav.BRouterEngine]) and search ([OfflineSearch]) work with no internet.
 *
 * Downloads go through the system DownloadManager (resumes after network loss, keeps going with the
 * screen off or the app closed, shows its own notification) into a staging folder. Only when every
 * file of a download/update has finished are they moved over the old ones, so an interrupted update
 * leaves the working package untouched.
 */
object OfflinePack {
    private const val TAG = "OfflinePack"
    const val MANIFEST_URL =
        "https://github.com/yazilimvar-design/WayFindr/releases/download/tr-map-latest/manifest.json"
    private const val SEGMENT_URL = "https://brouter.de/brouter/segments4/"
    val SEGMENTS = listOf(
        "E25_N35", "E30_N35", "E35_N35", "E40_N35",
        "E25_N40", "E30_N40", "E35_N40", "E40_N40",
    ).map { "$it.rd5" }
    /** Rough sizes until the server has been asked (Nov 2026: map 858 MB, segments 210 MB, search ~100 MB). */
    private const val MAP_SIZE_GUESS = 860_000_000L
    private const val SEGMENTS_SIZE_GUESS = 211_000_000L
    private const val SEARCH_SIZE_GUESS = 100_000_000L
    /** Segments are rebuilt every few days; only call it an update after this long. */
    private const val SEGMENT_UPDATE_AGE_MS = 28L * 24 * 3600 * 1000
    private const val SPACE_MARGIN = 100L * 1024 * 1024

    data class Status(
        val mapInstalled: Boolean = false,
        val routingInstalled: Boolean = false,
        val searchInstalled: Boolean = false,
        val mapDate: String? = null,          // YYYYMMDD of the installed map
        val totalBytes: Long = MAP_SIZE_GUESS + SEGMENTS_SIZE_GUESS + SEARCH_SIZE_GUESS, // size of the whole package
        val downloading: Boolean = false,
        val doneBytes: Long = 0,
        val wantBytes: Long = 0,
        val updateAvailable: Boolean = false,
        val latestMapDate: String? = null,
        val busy: Boolean = false,            // asking the server before a download starts
        val message: String? = null,          // last error / info, Turkish
    ) {
        val installed get() = mapInstalled && routingInstalled
        val progress get() = if (wantBytes > 0) (doneBytes.toFloat() / wantBytes).coerceIn(0f, 1f) else 0f
    }

    private val _status = MutableStateFlow(Status())
    val status: StateFlow<Status> = _status

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val lock = Mutex()
    @Volatile private var app: Context? = null

    fun init(context: Context) {
        app = context.applicationContext
        scope.launch { refresh(context.applicationContext) }
    }

    // ── Files ────────────────────────────────────────────────────────────

    private fun base(ctx: Context) = File(ctx.getExternalFilesDir(null) ?: ctx.filesDir, "offline")
    fun segmentDir(ctx: Context) = File(base(ctx), "segments4").apply { mkdirs() }
    private fun mapDir(ctx: Context) = File(base(ctx), "map").apply { mkdirs() }
    private fun searchDir(ctx: Context) = File(base(ctx), "search").apply { mkdirs() }
    private fun stagingDir(ctx: Context) = File(base(ctx), "staging").apply { mkdirs() }
    private fun prefs(ctx: Context) = ctx.getSharedPreferences("offline_pack", Context.MODE_PRIVATE)

    /** The installed map (tr-YYYYMMDD.pmtiles), or null. */
    fun mapFile(ctx: Context? = app): File? {
        ctx ?: return null
        val name = prefs(ctx).getString("map.file", null) ?: return null
        return File(mapDir(ctx), name).takeIf { it.length() > 0 }
    }

    /** The installed search index (tr-search-YYYYMMDD.sqlite), or null. */
    fun searchFile(ctx: Context? = app): File? {
        ctx ?: return null
        val name = prefs(ctx).getString("search.file", null) ?: return null
        return File(searchDir(ctx), name).takeIf { it.length() > 0 }
    }

    /** Debug/CI only: use a search index already placed in offline/search/. */
    fun debugInstallSearch(ctx: Context, fileName: String, date: String) {
        if (!com.example.wayfindr.BuildConfig.DEBUG) return
        prefs(ctx).edit().putString("search.file", fileName).putString("search.date", date).commit()
        OfflineSearch.reset()
    }

    /** Debug/CI only: use a map file already placed in offline/map/ (e.g. a small Ankara extract). */
    fun debugInstallMap(ctx: Context, fileName: String, date: String) {
        if (!com.example.wayfindr.BuildConfig.DEBUG) return
        prefs(ctx).edit().putString("map.file", fileName).putString("map.date", date).commit()
        com.example.wayfindr.dash.map.VectorTiles.reset()
    }

    fun mapDate(ctx: Context): String? = prefs(ctx).getString("map.date", null)

    fun routingReady(ctx: Context) = SEGMENTS.all { File(segmentDir(ctx), it).length() > 0 }

    // ── Server side ──────────────────────────────────────────────────────

    private data class Remote(val name: String, val url: String, val size: Long, val stamp: String, val dest: File)

    private fun httpGet(ctx: Context, url: String): String {
        var u = url
        repeat(5) {
            val c = InternetNetwork.openConnection(ctx, u)
            c.instanceFollowRedirects = false
            c.connectTimeout = 10_000; c.readTimeout = 15_000
            val code = c.responseCode
            if (code in 300..399) { u = c.getHeaderField("Location"); c.disconnect(); return@repeat }
            if (code !in 200..299) { c.disconnect(); throw java.io.IOException("HTTP $code") }
            return c.inputStream.use { it.readBytes().toString(Charsets.UTF_8) }.also { c.disconnect() }
        }
        throw java.io.IOException("too many redirects")
    }

    /** Content-Length and Last-Modified (ms) of a segment. */
    private fun head(ctx: Context, url: String): Pair<Long, Long> {
        val c = InternetNetwork.openConnection(ctx, url) as HttpURLConnection
        c.requestMethod = "HEAD"; c.connectTimeout = 10_000; c.readTimeout = 10_000
        try {
            if (c.responseCode !in 200..299) throw java.io.IOException("HTTP ${c.responseCode}")
            return c.contentLengthLong to c.lastModified
        } finally { c.disconnect() }
    }

    private class Plan(val items: List<Remote>, val mapDate: String, val totalBytes: Long, val updateAvailable: Boolean)

    /** Asks the server what is newest; [items] = what has to be downloaded for [force] update semantics. */
    private fun plan(ctx: Context): Plan {
        val m = JSONObject(httpGet(ctx, MANIFEST_URL))
        val mapDate = m.getString("date")
        val mapSize = m.getLong("size")
        val p = prefs(ctx)
        val items = ArrayList<Remote>()
        var update = false
        if (mapFile(ctx) == null || (mapDate(ctx) ?: "") < mapDate) {
            if (mapFile(ctx) != null) update = true
            items += Remote("map", m.getString("url"), mapSize, mapDate, File(mapDir(ctx), m.getString("file")))
        }
        // Search index (manifests from before V57 have none).
        var searchSize = 0L
        m.optJSONObject("search")?.let { s ->
            searchSize = s.optLong("size")
            val date = s.optString("date")
            val have = searchFile(ctx)
            if (have == null || (p.getString("search.date", "") ?: "") < date) {
                // An installed package without the index (V56) or with an older one: offer "Güncelle".
                if (mapFile(ctx) != null) update = true
                items += Remote("search", s.getString("url"), searchSize, date, File(searchDir(ctx), s.getString("file")))
            }
        }
        var segTotal = 0L
        for (name in SEGMENTS) {
            val (len, lm) = runCatching { head(ctx, SEGMENT_URL + name) }.getOrElse { -1L to 0L }
            segTotal += len.coerceAtLeast(0)
            val have = File(segmentDir(ctx), name)
            val haveStamp = p.getLong("seg.$name", 0L)
            val newer = lm > 0 && lm > haveStamp
            if (have.length() <= 0 || newer) {
                if (have.length() > 0 && lm - haveStamp > SEGMENT_UPDATE_AGE_MS) update = true
                items += Remote(name, SEGMENT_URL + name, len, lm.toString(), File(segmentDir(ctx), name))
            }
        }
        return Plan(items, mapDate, mapSize + segTotal + searchSize, update)
    }

    // ── Public actions ───────────────────────────────────────────────────

    /** Looks for a newer map / segments (shows "Güncelleme var"); quiet when offline. */
    fun checkForUpdate(context: Context) {
        val ctx = context.applicationContext
        scope.launch {
            if (InternetNetwork.validated(ctx) == null) return@launch
            runCatching { plan(ctx) }.onSuccess { pl ->
                prefs(ctx).edit().putLong("total", pl.totalBytes).putBoolean("update", pl.updateAvailable)
                    .putString("latest", pl.mapDate).apply()
                refresh(ctx)
            }.onFailure { Log.w(TAG, "update check failed: $it") }
        }
    }

    /**
     * Downloads the whole package, or — when installed — what is newer on the server ("Güncelle").
     * New files go to staging and replace the old ones only once all of them are complete.
     */
    fun download(context: Context) {
        val ctx = context.applicationContext
        scope.launch {
            val msg = lock.withLock {
                if (activeIds(ctx).isNotEmpty()) return@withLock null
                _status.value = _status.value.copy(busy = true, message = null)
                try {
                    if (InternetNetwork.validated(ctx) == null) "İnternet bağlantısı yok"
                    else {
                        val pl = plan(ctx)
                        prefs(ctx).edit().putLong("total", pl.totalBytes).putString("latest", pl.mapDate).apply()
                        if (pl.items.isEmpty()) {
                            prefs(ctx).edit().putBoolean("update", false).apply()
                            "Paket güncel"
                        } else {
                            val need = pl.items.sumOf { it.size.coerceAtLeast(0) } + SPACE_MARGIN
                            val free = StatFs(base(ctx).apply { mkdirs() }.absolutePath).availableBytes
                            if (free < need) "Yer yetersiz: ${gb(need)} gerekli, ${gb(free)} boş"
                            else { enqueue(ctx, pl.items); null }
                        }
                    }
                } catch (e: Exception) {
                    Log.w(TAG, "download start failed", e)
                    "İndirme başlatılamadı: ${e.message}"
                } finally {
                    _status.value = _status.value.copy(busy = false)
                }
            }
            refresh(ctx, msg)
        }
    }

    private fun enqueue(ctx: Context, items: List<Remote>) {
        val dm = ctx.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val ed = prefs(ctx).edit()
        val names = ArrayList<String>()
        for (it in items) {
            val part = File(stagingDir(ctx), it.dest.name + ".part").apply { delete() }
            val req = DownloadManager.Request(Uri.parse(it.url))
                .setTitle(when (it.name) {
                    "map" -> "Türkiye çevrimdışı haritası"
                    "search" -> "Türkiye çevrimdışı arama dizini"
                    else -> "Türkiye rota verisi ${it.name}"
                })
                .setDescription("Türkiye çevrimdışı paketi")
                .setDestinationUri(Uri.fromFile(part))
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
                .setAllowedOverMetered(true)
                .setAllowedOverRoaming(false)
            val id = dm.enqueue(req)
            ed.putLong("dl.${it.name}.id", id)
                .putString("dl.${it.name}.dest", it.dest.absolutePath)
                .putString("dl.${it.name}.part", part.absolutePath)
                .putString("dl.${it.name}.stamp", it.stamp)
                .putLong("dl.${it.name}.size", it.size)
            names += it.name
        }
        ed.putStringSet("dl.names", names.toSet()).apply()
        Log.i(TAG, "enqueued ${names.size} downloads: $names")
    }

    /** Stops a running download (staged files are dropped, the installed package stays). */
    fun cancel(context: Context) {
        val ctx = context.applicationContext
        scope.launch {
            lock.withLock {
                val dm = ctx.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
                val ids = activeIds(ctx).values.toLongArray()
                if (ids.isNotEmpty()) dm.remove(*ids)
                clearJob(ctx)
                stagingDir(ctx).listFiles()?.forEach { it.delete() }
            }
            refresh(ctx, "İndirme durduruldu")
        }
    }

    private fun activeIds(ctx: Context): Map<String, Long> {
        val p = prefs(ctx)
        return p.getStringSet("dl.names", emptySet()).orEmpty()
            .associateWith { p.getLong("dl.$it.id", -1L) }.filterValues { it >= 0 }
    }

    private fun clearJob(ctx: Context) {
        val p = prefs(ctx)
        val ed = p.edit()
        for (n in p.getStringSet("dl.names", emptySet()).orEmpty())
            for (k in listOf("id", "dest", "part", "stamp", "size")) ed.remove("dl.$n.$k")
        ed.remove("dl.names").apply()
    }

    /**
     * Re-reads DownloadManager, moves a finished set into place, publishes [status]. Called by the
     * UI while it is visible, at app start and when DownloadManager reports a finished file.
     */
    suspend fun refresh(context: Context, message: String? = _status.value.message) {
        val ctx = context.applicationContext
        val ids = activeIds(ctx)
        var done = 0L; var want = 0L; var allOk = ids.isNotEmpty(); var failed: String? = null
        if (ids.isNotEmpty()) {
            val dm = ctx.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val p = prefs(ctx)
            for ((name, id) in ids) {
                var st = -1; var so = 0L; var tot = p.getLong("dl.$name.size", 0L); var reason = 0
                dm.query(DownloadManager.Query().setFilterById(id))?.use { c ->
                    if (c.moveToFirst()) {
                        st = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
                        so = c.getLong(c.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                        c.getLong(c.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)).takeIf { it > 0 }?.let { tot = it }
                        reason = c.getInt(c.getColumnIndexOrThrow(DownloadManager.COLUMN_REASON))
                    }
                }
                done += so; want += tot
                if (st != DownloadManager.STATUS_SUCCESSFUL) allOk = false
                if (st == DownloadManager.STATUS_FAILED || st == -1) failed = "$name (hata $reason)"
            }
            if (failed != null) {
                dm.remove(*ids.values.toLongArray())
                clearJob(ctx)
                stagingDir(ctx).listFiles()?.forEach { it.delete() }
                publish(ctx, null, 0, 0, "İndirme başarısız: $failed. Tekrar deneyin.")
                return
            }
            if (allOk) {
                lock.withLock { commit(ctx) }
                publish(ctx, null, 0, 0, "Türkiye çevrimdışı paketi hazır")
                return
            }
        }
        publish(ctx, ids.isNotEmpty(), done, want, message)
    }

    /** Every staged file is complete: move them over the old ones, then forget the job. */
    private fun commit(ctx: Context) {
        val p = prefs(ctx)
        val ed = p.edit()
        val names = p.getStringSet("dl.names", emptySet()).orEmpty()
        val oldMap = mapFile(ctx)
        val oldSearch = searchFile(ctx)
        for (name in names) {
            val part = File(p.getString("dl.$name.part", null) ?: continue)
            val dest = File(p.getString("dl.$name.dest", null) ?: continue)
            if (!part.exists()) continue
            dest.parentFile?.mkdirs()
            if (!part.renameTo(dest)) { part.copyTo(dest, overwrite = true); part.delete() }
            val stamp = p.getString("dl.$name.stamp", "") ?: ""
            when (name) {
                "map" -> ed.putString("map.file", dest.name).putString("map.date", stamp)
                "search" -> ed.putString("search.file", dest.name).putString("search.date", stamp)
                else -> ed.putLong("seg.$name", stamp.toLongOrNull() ?: 0L)
            }
        }
        ed.putBoolean("update", false).commit()
        oldMap?.takeIf { it.name != mapFile(ctx)?.name }?.delete()
        OfflineSearch.reset()
        oldSearch?.takeIf { it.name != searchFile(ctx)?.name }?.delete()
        clearJob(ctx)
        Log.i(TAG, "installed: $names")
        com.example.wayfindr.data.RideDiagnostics.log("offline", "Türkiye package installed/updated: $names")
        com.example.wayfindr.dash.map.VectorTiles.reset()
    }

    private fun publish(ctx: Context, downloading: Boolean?, done: Long, want: Long, message: String?) {
        val p = prefs(ctx)
        val date = mapDate(ctx)
        val latest = p.getString("latest", null)
        _status.value = _status.value.copy(
            mapInstalled = mapFile(ctx) != null,
            routingInstalled = routingReady(ctx),
            searchInstalled = searchFile(ctx) != null,
            mapDate = date,
            totalBytes = p.getLong("total", MAP_SIZE_GUESS + SEGMENTS_SIZE_GUESS + SEARCH_SIZE_GUESS),
            downloading = downloading ?: false,
            doneBytes = done, wantBytes = want,
            latestMapDate = latest,
            updateAvailable = p.getBoolean("update", false) || (date != null && latest != null && latest > date),
            message = message,
        )
    }

    fun gb(bytes: Long): String =
        if (bytes >= 1_000_000_000L) "%.1f GB".format(java.util.Locale("tr"), bytes / 1e9)
        else "${bytes / 1_000_000} MB"

    /** "20261117" → "17.11.2026". */
    fun prettyDate(d: String?): String =
        if (d == null || d.length != 8) d ?: "" else "${d.substring(6)}.${d.substring(4, 6)}.${d.substring(0, 4)}"

    /** DownloadManager finished a file (even with the app closed): check whether the set is complete. */
    class DoneReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val pending = goAsync()
            scope.launch {
                try { refresh(context) } finally { pending.finish() }
            }
        }
    }
}
