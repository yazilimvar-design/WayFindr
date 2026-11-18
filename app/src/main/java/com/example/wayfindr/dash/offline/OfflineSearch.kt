package com.example.wayfindr.dash.offline

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.wayfindr.dash.nav.GeoPoint
import com.example.wayfindr.dash.nav.PlaceSearch
import java.text.Normalizer
import java.util.Locale

/**
 * Place search with no internet, from the "Türkiye çevrimdışı paketi" search index
 * (tr-search-YYYYMMDD.sqlite, built from OpenStreetMap by the tr-map workflow's tr_search.py):
 * named POIs, il/ilçe/mahalle/köy and streets, each with its ilçe/il.
 *
 * The index holds Turkish-folded ASCII tokens in a contentless FTS4 table (columns name, area), so
 * a query is folded the same way ([norm]: İ/i, I/ı, ş/s, ğ/g, ü/u, ö/o, ç/c…) and every word is a
 * prefix ("kizilay" finds "Kızılay", "mamak devlet" finds "Mamak Devlet Hastanesi"). Words are
 * first matched against the name only; if that gives too few results, also against the area
 * ("devlet hastanesi mamak"). Results are ranked by match quality, then distance from the rider.
 */
object OfflineSearch {
    private const val TAG = "OfflineSearch"
    const val PROVIDER = "Çevrimdışı"
    private const val CANDIDATES = 300

    @Volatile private var db: SQLiteDatabase? = null
    @Volatile private var dbPath: String? = null

    /** Same folding as tr_search.py's norm(): lower case ASCII words separated by single spaces. */
    fun norm(s: String): String {
        val lower = s.replace('İ', 'i').replace('I', 'ı').lowercase(Locale.ROOT)
        val stripped = Normalizer.normalize(lower, Normalizer.Form.NFD).replace(MARKS, "")
            .replace('ı', 'i').replace("ß", "ss")
        return NON_ALNUM.replace(stripped, " ").trim()
    }

    private val MARKS = Regex("[\\u0300-\\u036f]+")
    private val NON_ALNUM = Regex("[^a-z0-9]+")

    fun available(ctx: Context): Boolean = OfflinePack.searchFile(ctx) != null

    @Synchronized
    private fun open(ctx: Context): SQLiteDatabase? {
        val f = OfflinePack.searchFile(ctx) ?: run { close(); return null }
        db?.let { if (dbPath == f.path && it.isOpen) return it }
        close()
        return try {
            SQLiteDatabase.openDatabase(f.path, null,
                SQLiteDatabase.OPEN_READONLY or SQLiteDatabase.NO_LOCALIZED_COLLATORS).also { db = it; dbPath = f.path }
        } catch (e: Exception) {
            Log.w(TAG, "cannot open ${f.path}: $e"); null
        }
    }

    /** A new index was installed (or removed): reopen on the next search. */
    @Synchronized
    fun reset() = close()

    private fun close() {
        runCatching { db?.close() }
        db = null; dbPath = null
    }

    private class Row(val name: String, val kind: String, val area: String, val lat: Double, val lng: Double, val rank: Int)

    /**
     * Up to [limit] places for [query], nearest first among equally good matches. Empty when the
     * index isn't installed or nothing matches. Blocking (call off the main thread).
     */
    fun search(ctx: Context, query: String, near: Pair<Double, Double>?, limit: Int = 10): List<PlaceSearch.Hit> {
        val t0 = System.currentTimeMillis()
        val words = norm(query).split(' ').filter { it.isNotEmpty() }
            .let { w -> if (w.size > 1) w.filter { it.length > 1 || it[0].isDigit() } else w }.take(6)
        if (words.isEmpty() || (words.size == 1 && words[0].length < 2 && !words[0][0].isDigit())) return emptyList()
        val database = open(ctx) ?: return emptyList()
        val q = words.joinToString(" ")
        val byName = run(database, words.joinToString(" ") { "name:$it*" }, near)
        val rows = if (byName.size >= limit) byName
            else (byName + run(database, words.joinToString(" ") { "$it*" }, near)).distinctBy { Triple(it.name, it.lat, it.lng) }
                .let { snapAdmin(it) }

        fun quality(r: Row): Int {
            val n = norm(r.name)
            if (n == q) return 0
            if (n.startsWith(q)) return 1
            val nw = n.split(' ')
            return if (words.all { w -> nw.any { it.startsWith(w) } }) 2 else 3
        }
        // Bigger places are worth a longer trip: il and şehir count as much nearer than a shop.
        fun importance(rank: Int) = when (rank) { 0 -> 30.0; 1 -> 15.0; 2 -> 8.0; 3, 4 -> 3.0; 5 -> 1.5; else -> 1.0 }
        val here = near?.let { GeoPoint(it.first, it.second) }
        val ranked = rows.map { r ->
            val km = here?.let { GeoPoint.distMeters(it, GeoPoint(r.lat, r.lng)) / 1000.0 } ?: 0.0
            Triple(r, quality(r), km / importance(r.rank) + (if (here == null) r.rank.toDouble() else 0.0))
        }.sortedWith(compareBy({ it.second }, { it.third }))

        val out = ArrayList<PlaceSearch.Hit>()
        for ((r, _, _) in ranked) {
            if (out.any { it.name == r.name && GeoPoint.distMeters(GeoPoint(it.lat, it.lng), GeoPoint(r.lat, r.lng)) < 300 }) continue
            out += PlaceSearch.Hit(r.name, r.lat, r.lng, PROVIDER,
                listOf(r.kind, r.area).filter { it.isNotBlank() }.joinToString(" · "))
            if (out.size >= limit) break
        }
        val msg = "offline search '$query' → ${out.size} (${rows.size} candidates) in ${System.currentTimeMillis() - t0} ms" +
            (out.firstOrNull()?.let { ": ${it.name} · ${it.subtitle}" } ?: "")
        Log.i(TAG, msg)
        com.example.wayfindr.data.RideDiagnostics.log("search", msg)
        return out
    }

    /** Best single match (for a typed search or a shared place name that can't be resolved online). */
    fun best(ctx: Context, query: String, near: Pair<Double, Double>?): PlaceSearch.Hit? =
        search(ctx, query, near, 1).firstOrNull()

    /**
     * The index's il/ilçe rows sit at a point inside the boundary polygon (tr_search.py uses
     * shapely representative_point()), e.g. "Mamak" İlçe at 39.905, 33.025 while the town is at
     * 39.933, 32.908. Such a row takes the point of the same-name place node (Şehir/Kasaba/…)
     * within [AREA_KM], when the index has one.
     */
    private fun snapAdmin(rows: List<Row>): List<Row> = rows.map { r ->
        if (r.kind != "İl" && r.kind != "İlçe") r
        else pickCentre(rows, placeKey(r.name), r.lat, r.lng, true, AREA_KM)
            ?.let { c -> Row(r.name, r.kind, r.area, c.lat, c.lng, r.rank) } ?: r
    }

    private const val AREA_KM = 30.0
    /** Kinds that come from OSM place nodes (tr_search.py PLACE), most fitting first. İl/İlçe rows are polygons. */
    private val TOWN_KINDS = listOf("Şehir", "Kasaba", "Belde", "Semt", "Köy", "Mahalle", "Mezra")
    private val QUARTER_KINDS = listOf("Semt", "Mahalle", "Köy", "Mezra", "Belde", "Kasaba", "Şehir")

    /** Name compared for "same place": folded, without a trailing "mahallesi"/"mah.". */
    fun placeKey(s: String): String = norm(s).removeSuffix(" mahallesi").removeSuffix(" mah").removeSuffix(" mh").trim()

    private fun pickCentre(rows: List<Row>, key: String, lat: Double, lng: Double, town: Boolean, maxKm: Double): Row? {
        val kinds = if (town) TOWN_KINDS else QUARTER_KINDS
        val here = GeoPoint(lat, lng)
        return rows.mapNotNull { r ->
            val k = kinds.indexOf(r.kind)
            if (k < 0 || placeKey(r.name) != key) return@mapNotNull null
            val km = GeoPoint.distMeters(here, GeoPoint(r.lat, r.lng)) / 1000.0
            if (km > maxKm) null else Triple(k, km, r)
        }.minWithOrNull(compareBy({ it.first }, { it.second }))?.third
    }

    /**
     * The settlement centre for an area named [name] near [lat],[lng] (e.g. a Google Places
     * administrative area): the same-name place node in the index within [maxKm], or null.
     * [town]: il/ilçe/şehir level (prefers Şehir/Kasaba), else semt/mahalle level. Blocking.
     */
    fun centre(ctx: Context, name: String, lat: Double, lng: Double, town: Boolean, maxKm: Double): PlaceSearch.Hit? {
        val key = placeKey(name)
        val words = key.split(' ').filter { it.isNotEmpty() }.take(6)
        if (words.isEmpty()) return null
        val database = open(ctx) ?: return null
        val r = pickCentre(run(database, words.joinToString(" ") { "name:$it*" }, lat to lng), key, lat, lng, town, maxKm)
            ?: return null
        return PlaceSearch.Hit(r.name, r.lat, r.lng, PROVIDER, listOf(r.kind, r.area).filter { it.isNotBlank() }.joinToString(" · "))
    }

    private fun run(database: SQLiteDatabase, match: String, near: Pair<Double, Double>?): List<Row> {
        val order = near?.let { (lat, lng) ->
            val la = Math.round(lat * 1e5); val lo = Math.round(lng * 1e5)
            val c = Math.cos(Math.toRadians(lat))
            String.format(Locale.ROOT, "(p.lat - %d) * (p.lat - %d) + (p.lon - %d) * (p.lon - %d) * %.4f", la, la, lo, lo, c * c)
        } ?: "p.rank"
        val sql = "SELECT p.name, k.label, a.label, p.lat, p.lon, p.rank FROM s JOIN place p ON p.id = s.docid " +
            "JOIN kind k ON k.id = p.kind JOIN area a ON a.id = p.area WHERE s MATCH ? ORDER BY $order LIMIT $CANDIDATES"
        return try {
            database.rawQuery(sql, arrayOf(match)).use { c ->
                val list = ArrayList<Row>(c.count)
                while (c.moveToNext()) list += Row(c.getString(0), c.getString(1) ?: "", c.getString(2) ?: "",
                    c.getLong(3) / 1e5, c.getLong(4) / 1e5, c.getInt(5))
                list
            }
        } catch (e: Exception) {
            Log.w(TAG, "query '$match' failed: $e"); emptyList()
        }
    }
}
