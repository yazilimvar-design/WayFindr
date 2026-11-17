package com.example.wayfindr.viewmodel

import android.app.Application
import android.location.Geocoder
import android.net.Uri
import android.widget.Toast
import com.example.wayfindr.data.GpxImport
import com.example.wayfindr.data.RideStats
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.wayfindr.data.Ride
import com.example.wayfindr.data.SyncRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

/** Recorded rides for RidesScreen. Rides are written by [DashViewModel] on disconnect. */
class RidesViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = SyncRepository.get(app)

    private val _rides = MutableStateFlow<List<Ride>>(emptyList())
    val rides = _rides.asStateFlow()

    // Ride sid → place name of where the ride ended. Persisted so it is looked up once (online)
    // and still shows offline.
    private val namePrefs = app.getSharedPreferences("ride_names", 0)
    private val _names = MutableStateFlow<Map<String, String>>(emptyMap())
    val names = _names.asStateFlow()

    init {
        reload()
        // Reflect new rides + cross-device syncs.
        viewModelScope.launch { repo.revision.collect { reload() } }
    }

    private fun reload() = viewModelScope.launch {
        _rides.value = withContext(Dispatchers.IO) { repo.rides() }
        resolveNames()
    }

    private suspend fun resolveNames() = withContext(Dispatchers.IO) {
        val known = HashMap<String, String>()
        // Drop old door-number titles ("5A") saved by earlier versions, so they get re-resolved.
        namePrefs.all.forEach { (k, v) -> if (v is String && !(v.any(Char::isDigit) && v.length <= 6)) known[k] = v }
        _names.value = known.toMap()
        if (!Geocoder.isPresent()) return@withContext
        for (r in _rides.value) {
            if (r.sid.isEmpty() || known.containsKey(r.sid) || (r.endLat == 0.0 && r.endLng == 0.0)) continue
            val name = reverseGeocode(r.endLat, r.endLng) ?: continue   // offline: retried next time
            known[r.sid] = name
            namePrefs.edit().putString(r.sid, name).apply()
            _names.value = known.toMap()
        }
    }

    @Suppress("DEPRECATION")
    private fun reverseGeocode(lat: Double, lng: Double): String? = try {
        com.example.wayfindr.data.ApiUsage.record(com.example.wayfindr.data.ApiUsage.REVERSE_GEOCODE)
        val a = Geocoder(getApplication(), Locale.forLanguageTag("tr-TR")).getFromLocation(lat, lng, 1)?.firstOrNull()
        // featureName is often just a house number; prefer the street / district then.
        // featureName is often a door number ("5A", "12/3"): skip short codes that contain digits.
        listOf(a?.featureName, a?.thoroughfare, a?.subLocality, a?.locality)
            .firstOrNull { !it.isNullOrBlank() && !(it.any(Char::isDigit) && it.length <= 6) && !it.all { c -> c.isDigit() || c == '/' || c == '-' } }
    } catch (_: Exception) {
        null
    }

    fun deleteRide(r: Ride) = viewModelScope.launch {
        withContext(Dispatchers.IO) { repo.deleteRide(r); RideStats.delete(r.sid) }
    }

    fun deleteAll() = viewModelScope.launch {
        withContext(Dispatchers.IO) { _rides.value.forEach { repo.deleteRide(it); RideStats.delete(it.sid) } }
    }

    /** Import rides from GPX files (e.g. exported by another app); a Turkish summary is toasted. */
    fun importGpx(uris: List<Uri>) = viewModelScope.launch {
        if (uris.isEmpty()) return@launch
        val app = getApplication<Application>()
        val msg = withContext(Dispatchers.IO) {
            var added = 0; var dupes = 0; var bad = 0
            val known = ArrayList(repo.rides())
            for (uri in uris) {
                val tracks = try {
                    app.contentResolver.openInputStream(uri)?.use { GpxImport.parse(it) }.orEmpty()
                } catch (_: Exception) { emptyList() }
                if (tracks.isEmpty()) bad++
                for (t in tracks) {
                    val r = GpxImport.toRide(t)?.first
                    when {
                        r == null -> bad++
                        // Same ride imported before: same start (±1 min) and distance (±2%).
                        known.any { Math.abs(it.startMs - r.startMs) < 60_000 && Math.abs(it.distanceMeters - r.distanceMeters) <= r.distanceMeters * 0.02 } -> {
                            dupes++; RideStats.delete(r.sid)
                        }
                        else -> {
                            t.name?.let { namePrefs.edit().putString(r.sid, it).apply() }
                            repo.addRide(r, countOdometer = false)   // old rides are already on the odometer
                            known.add(r); added++
                        }
                    }
                }
            }
            listOfNotNull(
                "$added sürüş içe aktarıldı",
                "$dupes zaten vardı".takeIf { dupes > 0 },
                "$bad okunamadı".takeIf { bad > 0 },
            ).joinToString(" · ")
        }
        Toast.makeText(app, msg, Toast.LENGTH_LONG).show()
    }
}
