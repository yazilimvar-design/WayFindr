package com.example.wayfindr.ui.components

import android.content.Context
import android.net.Uri
import com.example.wayfindr.dash.MapTheme
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

/**
 * MapLibre style for the online OSM map (OpenFreeMap vector tiles), coloured by [theme]. Labels are
 * added only when the bundled Latin glyphs could be copied to app storage (MapLibre needs a glyph
 * URL); [StylePlus.enhance] then adds the extra themed layers.
 */
internal fun onlineThemeStyleJson(context: Context, theme: MapTheme): String {
    val sources = JSONObject().put(
        "map",
        JSONObject()
            .put("type", "vector")
            .put("url", "https://tiles.openfreemap.org/planet")
            .put("attribution", "© OpenStreetMap contributors · OpenFreeMap"),
    )
    val layers = JSONArray().put(background(theme))
    layers.put(fill("landuse", "map", "landuse", theme.land))
    layers.put(fill("landcover", "map", "landcover", theme.parks))
    layers.put(fill("water", "map", "water", theme.water))
    layers.put(fill("park", "map", "park", theme.parks))
    layers.put(fill("building", "map", "building", theme.buildings).put("minzoom", 13))
    layers.put(line("transportation-casing", "map", "transportation", theme.roadCasing, 3.0))
    layers.put(line("transportation", "map", "transportation", theme.roads, 1.8))
    layers.put(line("waterway-line", "map", "waterway", theme.water, 1.5))
    layers.put(line("boundary", "map", "boundary", theme.roadCasing, 1.0))
    val glyphUrl = copyLatinGlyphs(context)?.let(::glyphTemplate)
    if (glyphUrl != null) {
        layers.put(labelLayer("place-labels", "map", "place", theme, 12, byLine = false))
        layers.put(labelLayer("road-labels", "map", "transportation_name", theme, 10, byLine = true))
        layers.put(labelLayer("water-labels", "map", "water_name", theme, 10, byLine = false))
    }
    StylePlus.enhance(layers, theme)
    return style("WayFindr OSM · ${theme.label}", sources, layers, glyphUrl)
}

private fun background(theme: MapTheme): JSONObject =
    layer("background", "background", paint = JSONObject().put("background-color", theme.background))

private fun style(name: String, sources: JSONObject, layers: JSONArray, glyphs: String?): String =
    JSONObject()
        .put("version", 8)
        .put("name", name)
        .put("sources", sources)
        .put("layers", layers)
        .apply { if (glyphs != null) put("glyphs", glyphs) }
        .toString()

private fun fill(id: String, source: String, sourceLayer: String, color: String): JSONObject =
    layer(id, "fill", source, sourceLayer, paint = JSONObject().put("fill-color", color).put("fill-opacity", 1.0))

private fun line(id: String, source: String, sourceLayer: String, color: String, width: Double): JSONObject =
    layer(
        id, "line", source, sourceLayer,
        layout = JSONObject().put("line-cap", "round").put("line-join", "round"),
        paint = JSONObject().put("line-color", color).put("line-width", width),
    )

private fun labelLayer(
    id: String, source: String, sourceLayer: String, theme: MapTheme, size: Int, byLine: Boolean,
): JSONObject = layer(
    id, "symbol", source, sourceLayer,
    layout = JSONObject()
        .put(
            "text-field",
            JSONArray().put("coalesce")
                .put(JSONArray().put("get").put("name"))
                .put(JSONArray().put("get").put("name:latin"))
                .put(JSONArray().put("get").put("name_en")),
        )
        .put("text-font", JSONArray().put("Open Sans Semibold"))
        .put("text-size", size)
        .put("text-max-width", 8)
        .put("symbol-placement", if (byLine) "line" else "point"),
    paint = JSONObject()
        .put("text-color", labelColor(theme))
        .put("text-halo-color", theme.background)
        .put("text-halo-width", 1.2)
        .put("text-halo-blur", 0.3),
)

private fun layer(
    id: String,
    type: String,
    source: String? = null,
    sourceLayer: String? = null,
    layout: JSONObject? = null,
    paint: JSONObject? = null,
): JSONObject = JSONObject().put("id", id).put("type", type).apply {
    if (source != null) put("source", source)
    if (sourceLayer != null) put("source-layer", sourceLayer)
    if (layout != null) put("layout", layout)
    if (paint != null) put("paint", paint)
}

/**
 * Copies the bundled "Open Sans Semibold" Latin glyph ranges to app storage once; returns the glyph
 * root folder, or null if they aren't available.
 */
private fun copyLatinGlyphs(context: Context): File? = runCatching {
    val root = File(context.filesDir, "map-glyphs")
    val font = File(root, "Open Sans Semibold").apply { mkdirs() }
    for (range in listOf("0-255", "256-511")) {
        val target = File(font, "$range.pbf")
        if (target.isFile && target.length() != 0L) continue
        context.assets.open("shortbread-fonts/Open Sans Semibold/$range").use { input ->
            java.io.FileOutputStream(target).use { output -> input.copyTo(output) }
        }
    }
    root.takeIf { File(font, "0-255.pbf").isFile && File(font, "256-511.pbf").isFile }
}.getOrNull()

private fun glyphTemplate(root: File): String =
    Uri.fromFile(root).toString().trimEnd('/') + "/{fontstack}/{range}.pbf"

/** Light labels on the dark themes, dark labels on the light ones. */
private fun labelColor(theme: MapTheme): String = when (theme) {
    MapTheme.JUNGLE_TRAIL, MapTheme.NIGHT_EXPEDITION, MapTheme.VOLCANIC,
    MapTheme.STEEL_STORM, MapTheme.CAMO_RAID, MapTheme.COPPER_RUSH -> "#F3F1E8"
    else -> "#263038"
}
