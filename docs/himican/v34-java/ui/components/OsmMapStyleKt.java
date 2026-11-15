package com.example.wayfindr.ui.components;

import android.content.Context;
import android.net.Uri;
import androidx.autofill.HintConstants;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import androidx.window.reflection.WindowExtensionsConstants;
import com.example.wayfindr.dash.MapTheme;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import kotlin.Metadata;
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
import org.maplibre.android.style.layers.Property;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: OsmMapStyle.kt */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a*\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002\u001a(\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u0001H\u0002\u001a0\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\u001a8\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002\u001aH\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u0007H\u0002\u001a\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020 H\u0002\u001a\u0010\u0010#\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002¨\u0006$"}, d2 = {"onlineThemeStyleJson", "", "context", "Landroid/content/Context;", "theme", "Lcom/example/wayfindr/dash/MapTheme;", "background", "Lorg/json/JSONObject;", "style", HintConstants.AUTOFILL_HINT_NAME, "sources", "layers", "Lorg/json/JSONArray;", "glyphs", "fill", "id", Property.SYMBOL_Z_ORDER_SOURCE, "sourceLayer", "color", Property.SYMBOL_PLACEMENT_LINE, Property.ICON_TEXT_FIT_WIDTH, "", "labelLayer", "size", "", "byLine", "", "layer", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, WindowExtensionsConstants.LAYOUT_PACKAGE, "paint", "copyLatinGlyphs", "Ljava/io/File;", "glyphTemplate", "root", "labelColor", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class OsmMapStyleKt {

    /* compiled from: OsmMapStyle.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MapTheme.values().length];
            try {
                iArr[MapTheme.JUNGLE_TRAIL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[MapTheme.NIGHT_EXPEDITION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[MapTheme.VOLCANIC.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[MapTheme.STEEL_STORM.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[MapTheme.CAMO_RAID.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[MapTheme.COPPER_RUSH.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String onlineThemeStyleJson(Context context, MapTheme theme) {
        MapTheme theme2;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(theme, "theme");
        JSONObject sources = new JSONObject().put("map", new JSONObject().put(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "vector").put(ImagesContract.URL, "https://tiles.openfreemap.org/planet").put("attribution", "© OpenStreetMap contributors · OpenFreeMap"));
        JSONArray layers = new JSONArray().put(background(theme));
        layers.put(fill("landuse", "map", "landuse", theme.getLand()));
        layers.put(fill("landcover", "map", "landcover", theme.getParks()));
        layers.put(fill("water", "map", "water", theme.getWater()));
        layers.put(fill("park", "map", "park", theme.getParks()));
        layers.put(fill("building", "map", "building", theme.getBuildings()).put("minzoom", 13));
        layers.put(line("transportation-casing", "map", "transportation", theme.getRoadCasing(), 3.0d));
        layers.put(line("transportation", "map", "transportation", theme.getRoads(), 1.8d));
        layers.put(line("waterway-line", "map", "waterway", theme.getWater(), 1.5d));
        layers.put(line("boundary", "map", "boundary", theme.getRoadCasing(), 1.0d));
        File glyphs = copyLatinGlyphs(context);
        String glyphUrl = glyphs != null ? glyphTemplate(glyphs) : null;
        if (glyphUrl == null) {
            theme2 = theme;
        } else {
            theme2 = theme;
            layers.put(labelLayer("place-labels", "map", "place", theme2, 12, false));
            layers.put(labelLayer("road-labels", "map", "transportation_name", theme2, 10, true));
            layers.put(labelLayer("water-labels", "map", "water_name", theme2, 10, false));
        }
        String str = "WayFindr OSM · " + theme2.getLabel();
        Intrinsics.checkNotNull(sources);
        Intrinsics.checkNotNull(layers);
        return style(str, sources, layers, glyphUrl);
    }

    private static final JSONObject background(MapTheme theme) {
        return layer$default("background", "background", null, null, null, new JSONObject().put("background-color", theme.getBackground()), 28, null);
    }

    private static final String style(String name, JSONObject sources, JSONArray layers, String glyphs) {
        JSONObject put = new JSONObject().put("version", 8).put(HintConstants.AUTOFILL_HINT_NAME, name).put("sources", sources).put("layers", layers);
        if (glyphs != null) {
            put.put("glyphs", glyphs);
        }
        String jSONObject = put.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return jSONObject;
    }

    private static final JSONObject fill(String id, String source, String sourceLayer, String color) {
        return layer$default(id, "fill", source, sourceLayer, null, new JSONObject().put("fill-color", color).put("fill-opacity", 1.0d), 16, null);
    }

    private static final JSONObject line(String id, String source, String sourceLayer, String color, double width) {
        return layer(id, Property.SYMBOL_PLACEMENT_LINE, source, sourceLayer, new JSONObject().put("line-cap", "round").put("line-join", "round"), new JSONObject().put("line-color", color).put("line-width", width));
    }

    private static final JSONObject labelLayer(String id, String source, String sourceLayer, MapTheme theme, int size, boolean byLine) {
        return layer(id, "symbol", source, sourceLayer, new JSONObject().put("text-field", new JSONArray().put("coalesce").put(new JSONArray().put("get").put(HintConstants.AUTOFILL_HINT_NAME)).put(new JSONArray().put("get").put("name:latin")).put(new JSONArray().put("get").put("name_en"))).put("text-font", new JSONArray().put("Open Sans Semibold")).put("text-size", size).put("text-max-width", 8).put("symbol-placement", byLine ? Property.SYMBOL_PLACEMENT_LINE : Property.SYMBOL_PLACEMENT_POINT), new JSONObject().put("text-color", labelColor(theme)).put("text-halo-color", theme.getBackground()).put("text-halo-width", 1.2d).put("text-halo-blur", 0.3d));
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

    private static final JSONObject layer(String id, String type, String source, String sourceLayer, JSONObject layout, JSONObject paint) {
        JSONObject put = new JSONObject().put("id", id).put(ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, type);
        if (source != null) {
            put.put(Property.SYMBOL_Z_ORDER_SOURCE, source);
        }
        if (sourceLayer != null) {
            put.put("source-layer", sourceLayer);
        }
        if (layout != null) {
            put.put(WindowExtensionsConstants.LAYOUT_PACKAGE, layout);
        }
        if (paint != null) {
            put.put("paint", paint);
        }
        Intrinsics.checkNotNullExpressionValue(put, "apply(...)");
        return put;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x012b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static final File copyLatinGlyphs(Context context) {
        File file;
        Object obj;
        FileOutputStream open;
        Throwable th;
        Throwable th2;
        InputStream inputStream;
        int i;
        File file2;
        int i2;
        try {
            Result.Companion companion = Result.INSTANCE;
            int i3 = 0;
            File file3 = new File(context.getFilesDir(), "map-glyphs");
            File file4 = new File(file3, "Open Sans Semibold");
            file4.mkdirs();
            int i4 = 0;
            for (String str : CollectionsKt.listOf((Object[]) new String[]{"0-255", "256-511"})) {
                File file5 = new File(file4, str + ".pbf");
                try {
                    try {
                        try {
                            try {
                                try {
                                    if (file5.isFile() && file5.length() != 0) {
                                        i = i3;
                                        file2 = file3;
                                        i2 = i4;
                                        i4 = i2;
                                        i3 = i;
                                        file3 = file2;
                                    }
                                    CloseableKt.closeFinally(open, null);
                                    i4 = i2;
                                    i3 = i;
                                    file3 = file2;
                                } catch (Throwable th3) {
                                    th = th3;
                                    Result.Companion companion2 = Result.INSTANCE;
                                    obj = Result.m8392constructorimpl(ResultKt.createFailure(th));
                                    return (File) (!Result.m8398isFailureimpl(obj) ? file : obj);
                                }
                                CloseableKt.closeFinally(open, null);
                                Unit unit = Unit.INSTANCE;
                            } catch (Throwable th4) {
                                th = th4;
                                try {
                                    throw th2;
                                } finally {
                                }
                            }
                            ByteStreamsKt.copyTo$default(inputStream, open, 0, 2, null);
                            Unit unit2 = Unit.INSTANCE;
                        } catch (Throwable th5) {
                            th2 = th5;
                            try {
                                throw th2;
                            } finally {
                            }
                        }
                        Intrinsics.checkNotNull(inputStream);
                        i = i3;
                        file2 = file3;
                        i2 = 0;
                        file = null;
                    } catch (Throwable th6) {
                        file = null;
                        th2 = th6;
                    }
                    inputStream = open;
                    open = new FileOutputStream(file5);
                } catch (Throwable th7) {
                    file = null;
                    th = th7;
                }
                open = context.getAssets().open("shortbread-fonts/Open Sans Semibold/" + str);
            }
            File file6 = file3;
            file = null;
            obj = Result.m8392constructorimpl(((!new File(file4, "0-255.pbf").isFile() || !new File(file4, "256-511.pbf").isFile()) ? i4 : 1) != 0 ? file6 : null);
        } catch (Throwable th8) {
            th = th8;
            file = null;
        }
        return (File) (!Result.m8398isFailureimpl(obj) ? file : obj);
    }

    private static final String glyphTemplate(File root) {
        String uri = Uri.fromFile(root).toString();
        Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
        return StringsKt.trimEnd(uri, '/') + "/{fontstack}/{range}.pbf";
    }

    private static final String labelColor(MapTheme theme) {
        switch (WhenMappings.$EnumSwitchMapping$0[theme.ordinal()]) {
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
}
