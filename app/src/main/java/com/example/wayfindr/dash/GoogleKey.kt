package com.example.wayfindr.dash

import com.example.wayfindr.dash.nav.GPlus

/**
 * Whether a Google Maps API key is available (GPlus.key(): the user's own key, or
 * MAPS_API_KEY from the build). Without one, Google maps and routing are hidden and the app uses the
 * free stack: OSM / offline maps, OSM / BRouter routing.
 */
object GoogleKey {
    /** Must match mapsKeyPlaceholder in app/build.gradle.kts. */
    const val PLACEHOLDER = "add gmaps api key here"

    fun isReal(key: String): Boolean = key.isNotBlank() && key != PLACEHOLDER

    val present: Boolean get() = isReal(GPlus.key())
}
