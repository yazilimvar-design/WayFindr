package com.example.wayfindr.data;

import android.content.Context;
import android.content.SharedPreferences;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: ApiUsage.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0006\u0010\u001d\u001a\u00020\u0005J\u0010\u0010\u001e\u001a\n \u001f*\u0004\u0018\u00010\u00050\u0005H\u0002J\u000e\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u0005J\b\u0010\"\u001a\u00020\u001aH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00140\u00130\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006#"}, d2 = {"Lcom/example/wayfindr/data/ApiUsage;", "", "<init>", "()V", "ROUTES", "", "TILES", "FREE_ROUTES", "OSM_TILES", "OFFLINE_ROUTES", "GEOCODE", "OSM_SEARCH", "REVERSE_GEOCODE", "SHORT_LINK", "UPDATE_CHECK", "prefs", "Landroid/content/SharedPreferences;", "_thisMonth", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "thisMonth", "Lkotlinx/coroutines/flow/StateFlow;", "getThisMonth", "()Lkotlinx/coroutines/flow/StateFlow;", "init", "", "context", "Landroid/content/Context;", "monthLabel", "monthKey", "kotlin.jvm.PlatformType", "record", "endpoint", "publish", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ApiUsage {
    public static final String FREE_ROUTES = "Valhalla routing (OSM, free)";
    public static final String GEOCODE = "Android Geocoder (search)";
    public static final String OFFLINE_ROUTES = "BRouter (offline, on phone)";
    public static final String OSM_SEARCH = "OSM search (Photon / Nominatim, free)";
    public static final String OSM_TILES = "OpenStreetMap tiles (free)";
    public static final String REVERSE_GEOCODE = "Android Geocoder (reverse)";
    public static final String ROUTES = "Google Routes API (computeRoutes)";
    public static final String SHORT_LINK = "Maps share-link expand";
    public static final String TILES = "Google map tiles";
    public static final String UPDATE_CHECK = "GitHub update check";
    private static SharedPreferences prefs;
    public static final ApiUsage INSTANCE = new ApiUsage();
    private static final MutableStateFlow<Map<String, Integer>> _thisMonth = StateFlowKt.MutableStateFlow(MapsKt.emptyMap());
    private static final StateFlow<Map<String, Integer>> thisMonth = _thisMonth;
    public static final int $stable = 8;

    private ApiUsage() {
    }

    public final StateFlow<Map<String, Integer>> getThisMonth() {
        return thisMonth;
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (prefs != null) {
            return;
        }
        prefs = context.getApplicationContext().getSharedPreferences("api_usage", 0);
        publish();
    }

    public final String monthLabel() {
        String format = new SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(new Date());
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    private final String monthKey() {
        return new SimpleDateFormat("yyyy-MM", Locale.ROOT).format(new Date());
    }

    public final synchronized void record(String endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        SharedPreferences p = prefs;
        if (p == null) {
            return;
        }
        String key = monthKey() + "|" + endpoint;
        p.edit().putInt(key, p.getInt(key, 0) + 1).apply();
        publish();
    }

    private final void publish() {
        Pair pair;
        SharedPreferences p = prefs;
        if (p == null) {
            return;
        }
        String prefix = monthKey() + "|";
        MutableStateFlow<Map<String, Integer>> mutableStateFlow = _thisMonth;
        Map all = p.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "getAll(...)");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : all.entrySet()) {
            String key = entry.getKey();
            Intrinsics.checkNotNull(key);
            if (StringsKt.startsWith$default(key, prefix, false, 2, (Object) null)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        Collection arrayList = new ArrayList();
        for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
            String str = (String) entry2.getKey();
            SharedPreferences p2 = p;
            Object value = entry2.getValue();
            String prefix2 = prefix;
            Integer num = value instanceof Integer ? (Integer) value : null;
            if (num != null) {
                int intValue = num.intValue();
                Intrinsics.checkNotNull(str);
                pair = TuplesKt.to(StringsKt.removePrefix(str, (CharSequence) prefix2), Integer.valueOf(intValue));
            } else {
                pair = null;
            }
            if (pair != null) {
                arrayList.add(pair);
            }
            p = p2;
            prefix = prefix2;
        }
        mutableStateFlow.setValue(MapsKt.toMap((List) arrayList));
    }
}
