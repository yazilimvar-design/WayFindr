package com.example.wayfindr.ui.components;

import android.content.Context;
import android.net.Uri;
import com.example.wayfindr.dash.MapTheme;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: OsmMapStyle.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class OsmMapStyleKt {
    private static final JSONObject background(MapTheme mapTheme) {
        return layer$default("background", "background", null, null, null, new JSONObject().put("background-color", mapTheme.getBackground()), 28, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final File copyLatinGlyphs(Context context) {
        Object obj;
        Object obj2;
        FileOutputStream open;
        Throwable th;
        Throwable th2;
        InputStream inputStream;
        boolean z;
        File file;
        boolean z2;
        try {
            Result.Companion companion = Result.Companion;
            boolean z3 = false;
            File file2 = new File(context.getFilesDir(), "map-glyphs");
            File file3 = new File(file2, "Open Sans Semibold");
            file3.mkdirs();
            boolean z4 = false;
            for (String str : CollectionsKt.listOf(new String[]{"0-255", "256-511"})) {
                File file4 = new File(file3, str + ".pbf");
                try {
                    try {
                        try {
                            try {
                                try {
                                    if (file4.isFile() && file4.length() != 0) {
                                        z = z3;
                                        file = file2;
                                        z2 = z4;
                                        z4 = z2;
                                        z3 = z;
                                        file2 = file;
                                    }
                                    CloseableKt.closeFinally(open, (Throwable) null);
                                    z4 = z2;
                                    z3 = z;
                                    file2 = file;
                                } catch (Throwable th3) {
                                    th = th3;
                                    Result.Companion companion2 = Result.Companion;
                                    obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                                    return (File) (!Result.isFailure-impl(obj2) ? obj : obj2);
                                }
                                CloseableKt.closeFinally(open, (Throwable) null);
                                Unit unit = Unit.INSTANCE;
                            } catch (Throwable th4) {
                                th = th4;
                                try {
                                    throw th2;
                                } finally {
                                }
                            }
                            ByteStreamsKt.copyTo$default(inputStream, open, 0, 2, (Object) null);
                            Unit unit2 = Unit.INSTANCE;
                        } catch (Throwable th5) {
                            th2 = th5;
                            try {
                                throw th2;
                            } finally {
                            }
                        }
                        Intrinsics.checkNotNull(inputStream);
                        z = z3;
                        file = file2;
                        z2 = false;
                        obj = null;
                    } catch (Throwable th6) {
                        obj = null;
                        th2 = th6;
                    }
                    inputStream = open;
                    open = new FileOutputStream(file4);
                } catch (Throwable th7) {
                    obj = null;
                    th = th7;
                }
                open = context.getAssets().open("shortbread-fonts/Open Sans Semibold/" + str);
            }
            File file5 = file2;
            obj = null;
            obj2 = Result.constructor-impl((!new File(file3, "0-255.pbf").isFile() || !new File(file3, "256-511.pbf").isFile()) ? z4 : true ? file5 : null);
        } catch (Throwable th8) {
            th = th8;
            obj = null;
        }
        return (File) (!Result.isFailure-impl(obj2) ? obj : obj2);
    }

    private static final JSONObject fill(String str, String str2, String str3, String str4) {
        return layer$default(str, "fill", str2, str3, null, new JSONObject().put("fill-color", str4).put("fill-opacity", 1.0d), 16, null);
    }

    private static final String glyphTemplate(File file) {
        String uri = Uri.fromFile(file).toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        return StringsKt.trimEnd(uri, new char[]{'/'}) + "/{fontstack}/{range}.pbf";
    }

    private static final String labelColor(MapTheme mapTheme) {
        switch (WhenMappings.$EnumSwitchMapping$0[mapTheme.ordinal()]) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return "#F3F1E8";
            default:
                return "#263038";
        }
    }

    private static final JSONObject labelLayer(String str, String str2, String str3, MapTheme mapTheme, int i, boolean z) {
        return layer(str, "symbol", str2, str3, new JSONObject().put("text-field", new JSONArray().put("coalesce").put(new JSONArray().put("get").put("name")).put(new JSONArray().put("get").put("name:latin")).put(new JSONArray().put("get").put("name_en"))).put("text-font", new JSONArray().put("Open Sans Semibold")).put("text-size", i).put("text-max-width", 8).put("symbol-placement", z ? "line" : "point"), new JSONObject().put("text-color", labelColor(mapTheme)).put("text-halo-color", mapTheme.getBackground()).put("text-halo-width", 1.2d).put("text-halo-blur", 0.3d));
    }

    private static final JSONObject layer(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2) {
        JSONObject put = new JSONObject().put("id", str).put("type", str2);
        if (str3 != null) {
            put.put("source", str3);
        }
        if (str4 != null) {
            put.put("source-layer", str4);
        }
        if (jSONObject != null) {
            put.put("layout", jSONObject);
        }
        if (jSONObject2 != null) {
            put.put("paint", jSONObject2);
        }
        Intrinsics.checkNotNullExpressionValue(put, "apply(...)");
        return put;
    }

    static /* synthetic */ JSONObject layer$default(String str, String str2, String str3, String str4, JSONObject jSONObject, JSONObject jSONObject2, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        if ((i & 8) != 0) {
            str4 = null;
        }
        if ((i & 16) != 0) {
            jSONObject = null;
        }
        if ((i & 32) != 0) {
            jSONObject2 = null;
        }
        return layer(str, str2, str3, str4, jSONObject, jSONObject2);
    }

    private static final JSONObject line(String str, String str2, String str3, String str4, double d) {
        return layer(str, "line", str2, str3, new JSONObject().put("line-cap", "round").put("line-join", "round"), new JSONObject().put("line-color", str4).put("line-width", d));
    }

    public static final String onlineThemeStyleJson(Context context, MapTheme mapTheme) {
        MapTheme mapTheme2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mapTheme, "theme");
        JSONObject put = new JSONObject().put("map", new JSONObject().put("type", "vector").put("url", "https://tiles.openfreemap.org/planet").put("attribution", "© OpenStreetMap contributors · OpenFreeMap"));
        JSONArray put2 = new JSONArray().put(background(mapTheme));
        put2.put(fill("landuse", "map", "landuse", mapTheme.getLand()));
        put2.put(fill("landcover", "map", "landcover", mapTheme.getParks()));
        put2.put(fill("water", "map", "water", mapTheme.getWater()));
        put2.put(fill("park", "map", "park", mapTheme.getParks()));
        put2.put(fill("building", "map", "building", mapTheme.getBuildings()).put("minzoom", 13));
        put2.put(line("transportation-casing", "map", "transportation", mapTheme.getRoadCasing(), 3.0d));
        put2.put(line("transportation", "map", "transportation", mapTheme.getRoads(), 1.8d));
        put2.put(line("waterway-line", "map", "waterway", mapTheme.getWater(), 1.5d));
        put2.put(line("boundary", "map", "boundary", mapTheme.getRoadCasing(), 1.0d));
        File copyLatinGlyphs = copyLatinGlyphs(context);
        String glyphTemplate = copyLatinGlyphs != null ? glyphTemplate(copyLatinGlyphs) : null;
        if (glyphTemplate != null) {
            mapTheme2 = mapTheme;
            put2.put(labelLayer("place-labels", "map", "place", mapTheme2, 12, false));
            put2.put(labelLayer("road-labels", "map", "transportation_name", mapTheme2, 12, true));
            put2.put(labelLayer("water-labels", "map", "water_name", mapTheme2, 10, false));
        } else {
            mapTheme2 = mapTheme;
        }
        StylePlus.enhance(put2, mapTheme2);
        String str = "WayFindr OSM · " + mapTheme2.getLabel();
        Intrinsics.checkNotNull(put);
        Intrinsics.checkNotNull(put2);
        return style(str, put, put2, glyphTemplate);
    }

    private static final String style(String str, JSONObject jSONObject, JSONArray jSONArray, String str2) {
        JSONObject put = new JSONObject().put("version", 8).put("name", str).put("sources", jSONObject).put("layers", jSONArray);
        if (str2 != null) {
            put.put("glyphs", str2);
        }
        String jSONObject2 = put.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject2, "toString(...)");
        return jSONObject2;
    }
}
