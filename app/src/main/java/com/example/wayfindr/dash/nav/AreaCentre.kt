package com.example.wayfindr.dash.nav

import android.content.Context
import android.location.Address
import android.util.Log
import com.example.wayfindr.BuildConfig
import com.example.wayfindr.dash.offline.OfflineSearch
import com.example.wayfindr.util.InternetNetwork
import org.json.JSONArray
import org.json.JSONObject
import java.net.URLEncoder
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap

/**
 * The destination point for an area (il, ilçe, semt, mahalle, köy) is its settlement centre, not
 * the middle of its boundary. Google Places answers an administrative area with a point near the
 * polygon's centre: "Mamak" (administrative_area_level_2) came back at 39.91241, 33.05639, 13 km
 * east of Mamak itself (39.933, 32.908), out by Kutludüğün (2026-11-18).
 *
 * [fix] moves such a hit to the OSM place node of the same name within [MAX_KM]: first from the
 * offline index of the Türkiye package (no network), else from Photon (place nodes / admin_centre).
 * Hits that are not areas (POIs, streets, addresses) are never moved. Blocking: call off the main thread.
 */
object AreaCentre {
    private const val TAG = "AreaCentre"
    const val MAX_KM = 30.0

    private val TOWN = setOf("administrative_area_level_1", "administrative_area_level_2",
        "administrative_area_level_3", "locality", "postal_town", "colloquial_area")
    private val QUARTER = setOf("administrative_area_level_4", "administrative_area_level_5",
        "administrative_area_level_6", "administrative_area_level_7", "sublocality", "sublocality_level_1",
        "sublocality_level_2", "sublocality_level_3", "sublocality_level_4", "sublocality_level_5", "neighborhood")
    /** A Google result with any of these is a place to go to, not an area. */
    private val POINT = setOf("establishment", "point_of_interest", "route", "street_address", "premise",
        "subpremise", "transit_station", "intersection", "plus_code")

    /** Photon place values, most fitting first, for a town-level and a quarter-level area. */
    private val PHOTON_TOWN = listOf("city", "town", "municipality", "borough", "village", "suburb")
    private val PHOTON_QUARTER = listOf("suburb", "quarter", "neighbourhood", "village", "hamlet", "town", "city")

    /** CI only: false skips the offline index so the online (Photon) fallback is exercised. */
    @Volatile @JvmField var useOffline = true

    /** The last few decisions, for the diagnostics log and the CI search test. */
    val notes: MutableList<String> = java.util.Collections.synchronizedList(ArrayList())

    private val cache = ConcurrentHashMap<String, DoubleArray>()
    private val NONE = DoubleArray(0)

    fun clearCache() { cache.clear(); notes.clear() }

    /** true: town level (il/ilçe/şehir), false: quarter level (semt/mahalle), null: not an area. */
    fun level(types: Collection<String>): Boolean? = when {
        types.any { it in POINT } -> null
        types.any { it in TOWN } -> true
        types.any { it in QUARTER } -> false
        else -> null
    }

    /** [lat, lng] to use for a Google Places hit with these [types] (unchanged when not an area). */
    @JvmStatic
    fun fix(ctx: Context?, name: String, lat: Double, lng: Double, types: JSONArray?): DoubleArray {
        val list = types?.let { t -> (0 until t.length()).map { t.optString(it) } } ?: emptyList()
        val town = level(list) ?: return doubleArrayOf(lat, lng)
        return centre(ctx, name, lat, lng, town, list.joinToString("/"))
    }

    /** Same for a phone geocoder answer: an area when it names a district/town/quarter and no street. */
    @JvmStatic
    fun fixAddress(ctx: Context?, a: Address): DoubleArray {
        val lat = a.latitude; val lng = a.longitude
        val f = a.featureName?.trim().orEmpty()
        if (f.isEmpty() || a.thoroughfare != null || a.premises != null) return doubleArrayOf(lat, lng)
        val town = when {
            f.equals(a.subLocality, true) -> false
            f.equals(a.locality, true) || f.equals(a.subAdminArea, true) || f.equals(a.adminArea, true) -> true
            else -> return doubleArrayOf(lat, lng)
        }
        return centre(ctx, f, lat, lng, town, "geocoder")
    }

    private fun centre(ctx: Context?, name: String, lat: Double, lng: Double, town: Boolean, why: String): DoubleArray {
        val key = "${OfflineSearch.norm(name)}|$town|${Math.round(lat * 20)}|${Math.round(lng * 20)}|$useOffline"
        cache[key]?.let { return if (it.isEmpty()) doubleArrayOf(lat, lng) else it }
        var src = "-"
        val c: DoubleArray? = try {
            (if (ctx != null && useOffline) OfflineSearch.centre(ctx, name, lat, lng, town, MAX_KM)
                ?.let { src = "çevrimdışı ${it.subtitle}"; doubleArrayOf(it.lat, it.lng) } else null)
                ?: (if (ctx != null && InternetNetwork.validated(ctx) != null)
                    (runCatching { photon(ctx, name, lat, lng, town) }.onFailure { Log.w(TAG, "Photon '$name': $it") }.getOrNull()
                        ?.also { src = "Photon" }
                        ?: nominatim(ctx, name, lat, lng, town)?.also { src = "Nominatim" })
                    else null)
        } catch (e: Exception) {
            Log.w(TAG, "centre '$name' failed: $e"); null
        }
        if (cache.size > 200) cache.clear()
        cache[key] = c ?: NONE
        val msg = if (c == null) "area '$name' [$why] %.5f,%.5f: no place node, kept".format(Locale.ROOT, lat, lng)
            else "area '$name' [$why] %.5f,%.5f → %.5f,%.5f (%s, %.1f km)".format(Locale.ROOT, lat, lng, c[0], c[1], src,
                GeoPoint.distMeters(GeoPoint(lat, lng), GeoPoint(c[0], c[1])) / 1000)
        Log.i(TAG, msg)
        notes += msg
        while (notes.size > 40) notes.removeAt(0)
        runCatching { com.example.wayfindr.data.RideDiagnostics.log("search", msg) }
        return c ?: doubleArrayOf(lat, lng)
    }

    private fun get(ctx: Context, url: String): String? {
        com.example.wayfindr.data.ApiUsage.record(com.example.wayfindr.data.ApiUsage.OSM_SEARCH)
        val conn = InternetNetwork.openConnection(ctx, url).apply {
            connectTimeout = 5_000; readTimeout = 6_000
            setRequestProperty("User-Agent", "Wayfindr/${BuildConfig.VERSION_NAME} (personal motorcycle navigation app; Android)")
            setRequestProperty("Accept", "application/json")
        }
        return try {
            if (conn.responseCode != 200) null else conn.inputStream.bufferedReader().readText()
        } finally { conn.disconnect() }
    }

    /** Pick the best same-name candidate within [MAX_KM]: most fitting kind first, then nearest. */
    private fun best(name: String, lat: Double, lng: Double, cands: List<Triple<String, Int, GeoPoint>>): DoubleArray? {
        val want = OfflineSearch.placeKey(name)
        val here = GeoPoint(lat, lng)
        return cands.mapNotNull { (n, rank, p) ->
            if (rank < 0 || OfflineSearch.placeKey(n) != want) return@mapNotNull null
            val km = GeoPoint.distMeters(here, p) / 1000
            if (km.isNaN() || km > MAX_KM) null else Triple(rank, km, p)
        }.minWithOrNull(compareBy({ it.first }, { it.second }))?.let { doubleArrayOf(it.third.lat, it.third.lng) }
    }

    /**
     * Nominatim settlements (if Photon can't be reached): a place node, or a boundary at its
     * admin_centre/label point (Nominatim's point for such a relation).
     */
    private fun nominatim(ctx: Context, name: String, lat: Double, lng: Double, town: Boolean): DoubleArray? = try {
        val url = "https://nominatim.openstreetmap.org/search?format=jsonv2&featureType=settlement&limit=10&q=" +
            URLEncoder.encode(name, "UTF-8") + "&viewbox=%.4f,%.4f,%.4f,%.4f".format(Locale.ROOT, lng - 0.5, lat + 0.5, lng + 0.5, lat - 0.5)
        val arr = get(ctx, url)?.let { JSONArray(it) }
        val prefs = if (town) PHOTON_TOWN else PHOTON_QUARTER
        arr?.let { a ->
            best(name, lat, lng, (0 until a.length()).mapNotNull { i ->
                val r = a.optJSONObject(i) ?: return@mapNotNull null
                val la = r.optString("lat").toDoubleOrNull(); val lo = r.optString("lon").toDoubleOrNull()
                if (la == null || lo == null) null
                else Triple(r.optString("name"), prefs.indexOf(r.optString("addresstype")), GeoPoint(la, lo))
            })
        }
    } catch (e: Exception) {
        Log.w(TAG, "Nominatim '$name': $e"); null
    }

    /** Photon restricted to OSM place objects: the place node (or a boundary's admin_centre/label). */
    private fun photon(ctx: Context, name: String, lat: Double, lng: Double, town: Boolean): DoubleArray? {
        val url = "https://photon.komoot.io/api/?osm_tag=place&limit=10&location_bias_scale=0.6&q=" +
            URLEncoder.encode(name, "UTF-8") + "&lat=%.5f&lon=%.5f".format(Locale.ROOT, lat, lng)
        val features = get(ctx, url)?.let { JSONObject(it).optJSONArray("features") } ?: return null
        val prefs = if (town) PHOTON_TOWN else PHOTON_QUARTER
        return best(name, lat, lng, (0 until features.length()).mapNotNull { i ->
            val f = features.optJSONObject(i) ?: return@mapNotNull null
            val pr = f.optJSONObject("properties") ?: return@mapNotNull null
            val c = f.optJSONObject("geometry")?.optJSONArray("coordinates") ?: return@mapNotNull null
            Triple(pr.optString("name"), prefs.indexOf(pr.optString("osm_value")), GeoPoint(c.optDouble(1), c.optDouble(0)))
        })
    }
}
