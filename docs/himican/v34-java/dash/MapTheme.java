package com.example.wayfindr.dash;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DashConfig.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\b\u0086\u0081\u0002\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001 BI\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000ej\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001f¨\u0006!"}, d2 = {"Lcom/example/wayfindr/dash/MapTheme;", "", "label", "", "background", "land", "water", "parks", "buildings", "roadCasing", "roads", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "getBackground", "getLand", "getWater", "getParks", "getBuildings", "getRoadCasing", "getRoads", "HIMALAYAN", "RALLY_DUST", "JUNGLE_TRAIL", "DESERT_RAID", "NIGHT_EXPEDITION", "VOLCANIC", "STEEL_STORM", "CAMO_RAID", "ICE_STORM", "COPPER_RUSH", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public enum MapTheme {
    HIMALAYAN("Himalaya", "#dce4dc", "#c5d1c1", "#6ea6b8", "#91aa72", "#b7ad9f", "#5e554b", "#f4ead8"),
    RALLY_DUST("Ralli", "#e3c9a2", "#c8aa7c", "#5f9ba5", "#9b9b55", "#a98262", "#49382c", "#f8d9a4"),
    JUNGLE_TRAIL("Orman", "#18231d", "#243d2a", "#1e6270", "#386b37", "#4a4936", "#101410", "#a7c27a"),
    DESERT_RAID("Çöl", "#efe0bd", "#d9bc83", "#5b9ba9", "#9b9a5f", "#c29262", "#4d3422", "#fff0c5"),
    NIGHT_EXPEDITION("Gece", "#0b1118", "#17252b", "#124259", "#1d4632", "#29333a", "#050608", "#d28a28"),
    VOLCANIC("Volkan", "#211719", "#382629", "#244f63", "#4a4030", "#4a3430", "#120d0e", "#e05b32"),
    STEEL_STORM("Çelik", "#171b22", "#252c36", "#1d5270", "#284536", "#3b4149", "#080a0e", "#b8c4d0"),
    CAMO_RAID("Kamuflaj", "#22251c", "#38402a", "#285568", "#4d6335", "#555342", "#11140d", "#d2b45f"),
    ICE_STORM("Buz fırtınası", "#d9e4e8", "#c4d5dc", "#4b9dbb", "#a9c9b0", "#aebbc1", "#56636b", "#f7fbfa"),
    COPPER_RUSH("Bakır hücum", "#2a1c18", "#4a3024", "#315d6b", "#4c5630", "#634437", "#160e0b", "#d58a45");

    private final String background;
    private final String buildings;
    private final String label;
    private final String land;
    private final String parks;
    private final String roadCasing;
    private final String roads;
    private final String water;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<MapTheme> getEntries() {
        return $ENTRIES;
    }

    MapTheme(String label, String background, String land, String water, String parks, String buildings, String roadCasing, String roads) {
        this.label = label;
        this.background = background;
        this.land = land;
        this.water = water;
        this.parks = parks;
        this.buildings = buildings;
        this.roadCasing = roadCasing;
        this.roads = roads;
    }

    public final String getLabel() {
        return this.label;
    }

    public final String getBackground() {
        return this.background;
    }

    public final String getLand() {
        return this.land;
    }

    public final String getWater() {
        return this.water;
    }

    public final String getParks() {
        return this.parks;
    }

    public final String getBuildings() {
        return this.buildings;
    }

    public final String getRoadCasing() {
        return this.roadCasing;
    }

    public final String getRoads() {
        return this.roads;
    }

    /* compiled from: DashConfig.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"Lcom/example/wayfindr/dash/MapTheme$Companion;", "", "<init>", "()V", "fromStored", "Lcom/example/wayfindr/dash/MapTheme;", "value", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MapTheme fromStored(String value) {
            Object m8392constructorimpl;
            if (value != null) {
                Companion companion = MapTheme.INSTANCE;
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    m8392constructorimpl = Result.m8392constructorimpl(MapTheme.valueOf(value));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                    m8392constructorimpl = null;
                }
                MapTheme mapTheme = (MapTheme) m8392constructorimpl;
                if (mapTheme != null) {
                    return mapTheme;
                }
            }
            return MapTheme.HIMALAYAN;
        }
    }
}
