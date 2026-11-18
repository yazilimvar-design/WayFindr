package com.example.wayfindr.dash.nav

import android.content.Context
import android.location.Geocoder
import android.util.Log
import com.example.wayfindr.BuildConfig
import com.example.wayfindr.dash.RoutingSource
import com.example.wayfindr.data.SavedLocation
import com.example.wayfindr.util.InternetNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.net.URLEncoder
import java.util.Locale

/**
 * Route-screen search, matched to the "Routing" setting:
 * - Google: the phone's geocoder (Google Play services backend), as before.
 * - OSM: Photon (komoot), a free OpenStreetMap search that tolerates small differences
 *   ("Garden" vs "Gardens") and prefers places near the rider; Nominatim (OSM's own, exact-name)
 *   if Photon fails. Both free, no key, identifying User-Agent, one request per typed search.
 * - BRouter (offline): BRouter has no place search, so saved destinations are matched by name on
 *   the phone first; only if none match is Nominatim tried (works only with internet).
 *
 * [GPlus] / [SearchPlus] hook in: typed commands (Google key, offline maps), Google Places results
 * when a key is set, and the phone's geocoder / offline place list when OSM finds nothing.
 */
object PlaceSearch {
    private const val TAG = "PlaceSearch"
    private const val NOMINATIM = "https://nominatim.openstreetmap.org/search"
    private const val PHOTON = "https://photon.komoot.io/api/"
    private val USER_AGENT = "Wayfindr/${BuildConfig.VERSION_NAME} (personal motorcycle navigation app; Android)"

    /** [subtitle]: the area line under a suggestion (street, district, city…); empty when unknown. */
    data class Hit(
        val name: String,
        val lat: Double,
        val lng: Double,
        val provider: String,
        val subtitle: String = "",
    )

    /** Photon only knows a few result languages; anything else gets its default (local names). */
    internal fun photonLanguageCode(language: String): String =
        language.lowercase(Locale.ROOT).takeIf { it in setOf("de", "en", "fr") } ?: "default"

    /** What the search box should say it searches with. */
    fun label(source: RoutingSource): String = when (source) {
        RoutingSource.GOOGLE -> "Google"
        RoutingSource.OSM -> "OSM"
        RoutingSource.BROUTER -> "saved + OSM"
    }

    /** Last failure reason for the UI ("no match", "no internet", …). */
    @Volatile var lastError: String? = null
        private set

    /** True when the last suggestions came from the offline index (Türkiye çevrimdışı paketi). */
    @Volatile var lastOffline: Boolean = false
        private set

    private fun online(ctx: Context) = InternetNetwork.validated(ctx) != null

    suspend fun search(
        ctx: Context, query: String, source: RoutingSource,
        near: Pair<Double, Double>?, saved: List<SavedLocation>,
    ): Hit? {
        val q = query.trim()
        if (q.isEmpty()) return null
        lastError = null
        val offlineIndex = com.example.wayfindr.dash.offline.OfflineSearch.available(ctx)
        val hit = if (!online(ctx)) {
            // No internet: saved places, then the offline index of the Türkiye package.
            savedMatch(q, saved)
                ?: (if (offlineIndex) withContext(Dispatchers.IO) {
                    com.example.wayfindr.dash.offline.OfflineSearch.best(ctx, q, near)
                } else null)
                ?: run { lastError = if (offlineIndex) "no match" else "no internet"; null }
        } else when (source) {
            // Google's geocoder can fail or find nothing: the free OSM search next.
            RoutingSource.GOOGLE -> google(ctx, q) ?: osm(ctx, q, near)
            RoutingSource.OSM -> osm(ctx, q, near)
            RoutingSource.BROUTER -> savedMatch(q, saved) ?: osm(ctx, q, near)
        } ?: (if (offlineIndex) withContext(Dispatchers.IO) {
            com.example.wayfindr.dash.offline.OfflineSearch.best(ctx, q, near)
        } else null)
        val msg = hit?.let { "search '$q' (${it.provider}) → ${it.name} %.5f,%.5f".format(it.lat, it.lng) }
            ?: "search '$q' (${label(source)}) → nothing: $lastError"
        Log.i(TAG, msg)
        com.example.wayfindr.data.RideDiagnostics.log("search", msg)
        return hit
    }

    /**
     * Type-ahead suggestions from Photon (several places, each with its area as [Hit.subtitle]),
     * merged with Google Places / the geocoder / offline places by [SearchPlus.augment]. A typed
     * command (Google key, offline maps) answers with a single confirmation row instead.
     */
    @Suppress("UNCHECKED_CAST")
    suspend fun suggestions(ctx: Context, query: String, near: Pair<Double, Double>?): List<Hit> =
        withContext(Dispatchers.IO) {
            lastOffline = false   // reset before the Google path too, or a past offline search mislabels Google hits
            (GPlus.keyCommand(ctx, query, near) as List<Hit>?)?.let { return@withContext it }
            val q = query.trim()
            if (q.length < 2) return@withContext emptyList()
            lastError = null
            lastOffline = false
            val offline = com.example.wayfindr.dash.offline.OfflineSearch
            if (!online(ctx)) {
                // No internet: the offline index answers alone (Photon, Places and the geocoder can't).
                if (!offline.available(ctx)) { lastError = "no internet for OpenStreetMap search"; return@withContext emptyList() }
                lastOffline = true
                return@withContext offline.search(ctx, q, near).also { if (it.isEmpty()) lastError = "no match" }
            }
            val hits: List<Hit> = try {
                val url = buildString {
                    append(PHOTON).append("?limit=20&location_bias_scale=0.6&q=").append(URLEncoder.encode(q, "UTF-8"))
                    append("&lang=").append(photonLanguageCode(Locale.getDefault().language))
                    near?.let { (lat, lng) -> append("&lat=%.5f&lon=%.5f".format(Locale.ROOT, lat, lng)) }
                }
                com.example.wayfindr.data.ApiUsage.record(com.example.wayfindr.data.ApiUsage.OSM_SEARCH)
                val body = httpGet(ctx, url)
                if (body == null) emptyList()
                else {
                    val features = JSONObject(body).optJSONArray("features") ?: JSONArray()
                    (0 until features.length()).mapNotNull { i ->
                        runCatching {
                            val f = features.getJSONObject(i)
                            val pr = f.getJSONObject("properties")
                            val c = f.getJSONObject("geometry").getJSONArray("coordinates")   // [lon, lat]
                            val area = listOf("street", "district", "city", "county", "state", "country")
                                .mapNotNull { k -> pr.optString(k).takeIf { it.isNotBlank() } }
                                .distinct()
                            val name = pr.optString("name").ifBlank { area.firstOrNull() ?: "" }
                            if (name.isBlank() || c.length() < 2) null
                            else Hit(name, c.getDouble(1), c.getDouble(0), "OSM (Photon)",
                                area.filterNot { it.equals(name, ignoreCase = true) }.joinToString(", "))
                        }.getOrNull()
                    }.distinctBy {
                        "${it.name.lowercase(Locale.ROOT)}:${"%.4f".format(Locale.ROOT, it.lat)}:${"%.4f".format(Locale.ROOT, it.lng)}"
                    }.also { if (it.isEmpty()) lastError = "no match" }
                }
            } catch (e: IOException) {
                lastError = "no internet for OpenStreetMap search"; emptyList()
            } catch (e: Exception) {
                lastError = "OpenStreetMap suggestions failed (${e.message})"; emptyList()
            }
            val merged = SearchPlus.augment(ctx, query, near, hits) as List<Hit>
            // Online search failed (server down, network dropped) or found nothing: the offline index.
            if (merged.isEmpty() && offline.available(ctx)) {
                offline.search(ctx, q, near).takeIf { it.isNotEmpty() }?.let { lastOffline = true; lastError = null; return@withContext it }
            }
            merged
        }

    /** Saved destinations (and their stops) whose name contains every word typed. */
    internal fun savedMatch(q: String, saved: List<SavedLocation>): Hit? {
        val words = q.lowercase(Locale.ROOT).split(Regex("\\s+")).filter { it.isNotBlank() }
        fun matches(name: String) = name.lowercase(Locale.ROOT).let { n -> words.all { it in n } }
        for (s in saved) {
            if (matches(s.name)) {
                // An itinerary's own name means its final stop.
                val last = s.waypoints.lastOrNull()
                return Hit(s.name, last?.lat ?: s.lat, last?.lng ?: s.lng, "saved place")
            }
            s.waypoints.firstOrNull { matches(it.name) && it.lat != null && it.lng != null }
                ?.let { return Hit(it.name, it.lat!!, it.lng!!, "saved place") }
        }
        lastError = "no saved place matches"
        return null
    }

    /** How many of the typed words appear in [text] (case-insensitive; "lal bagh" also matches "lalbagh"). */
    fun wordScore(q: String, text: String): Int {
        val t = text.lowercase(Locale.ROOT)
        val squashed = t.replace(" ", "")
        return q.lowercase(Locale.ROOT).split(Regex("\\s+")).filter { it.isNotBlank() }
            .count { it in t || it in squashed }
    }

    @Suppress("DEPRECATION")
    private suspend fun google(ctx: Context, q: String): Hit? = withContext(Dispatchers.IO) {
        // A typed command, or a Google Places answer when the user has a key.
        GPlus.enterHook(ctx, q, null)?.let { return@withContext it as? Hit }
        try {
            if (!Geocoder.isPresent()) { lastError = "no geocoder on this phone"; return@withContext null }
            com.example.wayfindr.data.ApiUsage.record(com.example.wayfindr.data.ApiUsage.GEOCODE)
            val a = Geocoder(ctx, Locale.getDefault()).getFromLocationName(q, 1)?.firstOrNull()
                ?: run { lastError = "no match"; return@withContext null }
            AreaCentre.fixAddress(ctx, a).let { c -> Hit(q, c[0], c[1], "Google") }   // an area: its centre
        } catch (e: Exception) {
            lastError = "Google search failed (${e.message})"; null
        }
    }

    /** OpenStreetMap search: Photon first, Nominatim if Photon fails or finds nothing. */
    private suspend fun osm(ctx: Context, q: String, near: Pair<Double, Double>?): Hit? =
        photon(ctx, q, near) ?: nominatim(ctx, q, near)

    private suspend fun photon(ctx: Context, q: String, near: Pair<Double, Double>?): Hit? = withContext(Dispatchers.IO) {
        GPlus.enterHook(ctx, q, near)?.let { return@withContext it as? Hit }
        try {
            val url = buildString {
                append(PHOTON).append("?limit=10&location_bias_scale=0.6&q=").append(URLEncoder.encode(q, "UTF-8"))
                near?.let { (lat, lng) -> append("&lat=%.5f&lon=%.5f".format(Locale.ROOT, lat, lng)) }
            }
            com.example.wayfindr.data.ApiUsage.record(com.example.wayfindr.data.ApiUsage.OSM_SEARCH)
            val body = httpGet(ctx, url) ?: return@withContext null
            val features = JSONObject(body).optJSONArray("features") ?: JSONArray()
            val results = (0 until features.length()).map { features.getJSONObject(it) }
            if (results.isEmpty()) { lastError = "no match"; return@withContext null }
            fun describe(f: JSONObject) = f.getJSONObject("properties").let { pr ->
                listOf("name", "street", "district", "city", "state").mapNotNull { k -> pr.optString(k).takeIf { it.isNotBlank() } }.joinToString(", ")
            }
            Log.i(TAG, "Photon '$q': " + results.joinToString(" | ") { describe(it).take(60) })
            val f = results.withIndex().maxByOrNull { (i, it) -> wordScore(q, describe(it)) * 10 - i }!!.value
            val c = f.getJSONObject("geometry").getJSONArray("coordinates")   // [lon, lat]
            val name = f.getJSONObject("properties").optString("name").ifBlank { describe(f).substringBefore(',') }.ifBlank { q }
            Hit(name, c.getDouble(1), c.getDouble(0), "OSM (Photon)")
        } catch (e: IOException) {
            lastError = "no internet for OpenStreetMap search"; null
        } catch (e: Exception) {
            lastError = "OpenStreetMap search failed (${e.message})"; null
        }
    }

    /**
     * GET over the network that has internet, with the app's User-Agent; null (and [lastError]) on
     * a non-200 answer or no connection.
     */
    private fun httpGet(ctx: Context, url: String): String? = try {
        val conn = InternetNetwork.openConnection(ctx, url).apply {
            connectTimeout = 8_000; readTimeout = 10_000
            setRequestProperty("User-Agent", USER_AGENT)
            setRequestProperty("Accept", "application/json")
        }
        try {
            if (conn.responseCode != 200) { lastError = "OpenStreetMap search HTTP ${conn.responseCode}"; null }
            else conn.inputStream.bufferedReader().readText()
        } finally { conn.disconnect() }
    } catch (e: IOException) {
        lastError = "no internet for OpenStreetMap search"; null
    }

    /** Nominatim; when it finds nothing, [SearchPlus.fallback] tries the offline places and the geocoder. */
    private suspend fun nominatim(ctx: Context, q: String, near: Pair<Double, Double>?): Hit? = withContext(Dispatchers.IO) {
        if (GPlus.command(ctx, q) != 0) return@withContext null
        val hit: Hit? = run {
            try {
                val url = buildString {
                    append(NOMINATIM).append("?format=jsonv2&limit=10&q=").append(URLEncoder.encode(q, "UTF-8"))
                    append("&accept-language=").append(Locale.getDefault().language)
                    // Prefer results near the rider (±1°, ~110 km) without excluding the rest.
                    near?.let { (lat, lng) -> append("&viewbox=%.4f,%.4f,%.4f,%.4f".format(Locale.ROOT, lng - 1, lat + 1, lng + 1, lat - 1)) }
                }
                com.example.wayfindr.data.ApiUsage.record(com.example.wayfindr.data.ApiUsage.OSM_SEARCH)
                val body = httpGet(ctx, url) ?: return@run null
                val arr = JSONArray(body)
                val results = (0 until arr.length()).map { arr.getJSONObject(it) }
                if (results.isEmpty()) { lastError = "no match"; return@run null }
                Log.i(TAG, "Nominatim '$q': " + results.joinToString(" | ") { it.optString("display_name").take(60) })
                // Nominatim's first answer can be a loose match (2026-10-17: "Lalbagh Botanical Garden"
                // gave "Atal Bihari Botanical Garden"), so take the one naming the most typed words.
                val r = results.withIndex().maxByOrNull { (i, it) -> wordScore(q, it.optString("display_name")) * 10 - i }!!.value
                val name = r.optString("name").ifBlank { r.optString("display_name").substringBefore(',') }.ifBlank { q }
                Hit(name, r.getString("lat").toDouble(), r.getString("lon").toDouble(), "OSM (Nominatim)")
            } catch (e: IOException) {
                lastError = "no internet for OpenStreetMap search"; null
            } catch (e: Exception) {
                lastError = "OpenStreetMap search failed (${e.message})"; null
            }
        }
        SearchPlus.fallback(hit, ctx, q, near) as Hit?
    }
}
