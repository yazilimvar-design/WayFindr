package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import androidx.autofill.HintConstants;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.data.RideDiagnostics;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.util.InternetNetwork;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.maplibre.android.style.layers.Property;

/* compiled from: PlaceSearch.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000b\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001/B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u000bJ\u000e\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000eJL\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0086@¢\u0006\u0002\u0010\u001eJ:\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00140\u001c2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00052\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H\u0086@¢\u0006\u0002\u0010 J%\u0010!\u001a\u0004\u0018\u00010\u00142\u0006\u0010\"\u001a\u00020\u00052\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0000¢\u0006\u0002\b#J\u001d\u0010$\u001a\u00020%2\u0006\u0010\"\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\u0005H\u0000¢\u0006\u0002\b'J \u0010(\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u0005H\u0082@¢\u0006\u0002\u0010)J6\u0010*\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00052\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H\u0082@¢\u0006\u0002\u0010 J6\u0010+\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00052\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H\u0082@¢\u0006\u0002\u0010 J\u001a\u0010,\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u0005H\u0002J6\u0010.\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00052\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u0019H\u0082@¢\u0006\u0002\u0010 R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u00060"}, d2 = {"Lcom/example/wayfindr/dash/nav/PlaceSearch;", "", "<init>", "()V", "TAG", "", "NOMINATIM", "PHOTON", "USER_AGENT", "photonLanguageCode", "language", "photonLanguageCode$app", "label", Property.SYMBOL_Z_ORDER_SOURCE, "Lcom/example/wayfindr/dash/RoutingSource;", "value", "lastError", "getLastError", "()Ljava/lang/String;", "search", "Lcom/example/wayfindr/dash/nav/PlaceSearch$Hit;", "ctx", "Landroid/content/Context;", SearchIntents.EXTRA_QUERY, "near", "Lkotlin/Pair;", "", "saved", "", "Lcom/example/wayfindr/data/SavedLocation;", "(Landroid/content/Context;Ljava/lang/String;Lcom/example/wayfindr/dash/RoutingSource;Lkotlin/Pair;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suggestions", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/Pair;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "savedMatch", "q", "savedMatch$app", "wordScore", "", "text", "wordScore$app", "google", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "osm", "photon", "httpGet", ImagesContract.URL, "nominatim", "Hit", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PlaceSearch {
    private static final String NOMINATIM = "https://nominatim.openstreetmap.org/search";
    private static final String PHOTON = "https://photon.komoot.io/api/";
    private static final String TAG = "PlaceSearch";
    private static volatile String lastError;
    public static final PlaceSearch INSTANCE = new PlaceSearch();
    private static final String USER_AGENT = "Wayfindr/1.3.2-OSS-preview (personal motorcycle navigation app; Android)";
    public static final int $stable = 8;

    /* compiled from: PlaceSearch.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RoutingSource.values().length];
            try {
                iArr[RoutingSource.GOOGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RoutingSource.OSM.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RoutingSource.BROUTER.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private PlaceSearch() {
    }

    public final String photonLanguageCode$app(String language) {
        Intrinsics.checkNotNullParameter(language, "language");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = language.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        if (!SetsKt.setOf((Object[]) new String[]{"de", "en", "fr"}).contains(lowerCase)) {
            lowerCase = null;
        }
        return lowerCase == null ? "default" : lowerCase;
    }

    /* compiled from: PlaceSearch.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/example/wayfindr/dash/nav/PlaceSearch$Hit;", "", HintConstants.AUTOFILL_HINT_NAME, "", "lat", "", "lng", "provider", "subtitle", "<init>", "(Ljava/lang/String;DDLjava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getLat", "()D", "getLng", "getProvider", "getSubtitle", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Hit {
        public static final int $stable = 0;
        private final double lat;
        private final double lng;
        private final String name;
        private final String provider;
        private final String subtitle;

        public static /* synthetic */ Hit copy$default(Hit hit, String str, double d, double d2, String str2, String str3, int i, Object obj) {
            if ((i & 1) != 0) {
                str = hit.name;
            }
            if ((i & 2) != 0) {
                d = hit.lat;
            }
            if ((i & 4) != 0) {
                d2 = hit.lng;
            }
            if ((i & 8) != 0) {
                str2 = hit.provider;
            }
            if ((i & 16) != 0) {
                str3 = hit.subtitle;
            }
            double d3 = d2;
            return hit.copy(str, d, d3, str2, str3);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        /* renamed from: component2, reason: from getter */
        public final double getLat() {
            return this.lat;
        }

        /* renamed from: component3, reason: from getter */
        public final double getLng() {
            return this.lng;
        }

        /* renamed from: component4, reason: from getter */
        public final String getProvider() {
            return this.provider;
        }

        /* renamed from: component5, reason: from getter */
        public final String getSubtitle() {
            return this.subtitle;
        }

        public final Hit copy(String name, double lat, double lng, String provider, String subtitle) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            return new Hit(name, lat, lng, provider, subtitle);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Hit)) {
                return false;
            }
            Hit hit = (Hit) other;
            return Intrinsics.areEqual(this.name, hit.name) && Double.compare(this.lat, hit.lat) == 0 && Double.compare(this.lng, hit.lng) == 0 && Intrinsics.areEqual(this.provider, hit.provider) && Intrinsics.areEqual(this.subtitle, hit.subtitle);
        }

        public int hashCode() {
            return (((((((this.name.hashCode() * 31) + Double.hashCode(this.lat)) * 31) + Double.hashCode(this.lng)) * 31) + this.provider.hashCode()) * 31) + this.subtitle.hashCode();
        }

        public String toString() {
            return "Hit(name=" + this.name + ", lat=" + this.lat + ", lng=" + this.lng + ", provider=" + this.provider + ", subtitle=" + this.subtitle + ")";
        }

        public Hit(String name, double lat, double lng, String provider, String subtitle) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(provider, "provider");
            Intrinsics.checkNotNullParameter(subtitle, "subtitle");
            this.name = name;
            this.lat = lat;
            this.lng = lng;
            this.provider = provider;
            this.subtitle = subtitle;
        }

        /* JADX WARN: Illegal instructions before constructor call */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public /* synthetic */ Hit(String str, double d, double d2, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, d, d2, str2, r8);
            String str4;
            if ((i & 16) == 0) {
                str4 = str3;
            } else {
                str4 = "";
            }
        }

        public final String getName() {
            return this.name;
        }

        public final double getLat() {
            return this.lat;
        }

        public final double getLng() {
            return this.lng;
        }

        public final String getProvider() {
            return this.provider;
        }

        public final String getSubtitle() {
            return this.subtitle;
        }
    }

    public final String label(RoutingSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        switch (WhenMappings.$EnumSwitchMapping$0[source.ordinal()]) {
            case 1:
                return "Google";
            case 2:
                return "OSM";
            case 3:
                return "saved + OSM";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String getLastError() {
        return lastError;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x01c6, code lost:
    
        if (r1 == null) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object search(Context ctx, String query, RoutingSource source, Pair<Double, Double> pair, List<SavedLocation> list, Continuation<? super Hit> continuation) {
        PlaceSearch$search$1 placeSearch$search$1;
        Object google;
        String q;
        Object osm;
        Hit hit;
        Object osm2;
        String msg;
        Context ctx2 = ctx;
        RoutingSource source2 = source;
        Pair<Double, Double> near = pair;
        if (continuation instanceof PlaceSearch$search$1) {
            placeSearch$search$1 = (PlaceSearch$search$1) continuation;
            if ((placeSearch$search$1.label & Integer.MIN_VALUE) != 0) {
                placeSearch$search$1.label -= Integer.MIN_VALUE;
                Object $result = placeSearch$search$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (placeSearch$search$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        String q2 = StringsKt.trim((CharSequence) query).toString();
                        if (q2.length() == 0) {
                            return null;
                        }
                        lastError = null;
                        switch (WhenMappings.$EnumSwitchMapping$0[source2.ordinal()]) {
                            case 1:
                                placeSearch$search$1.L$0 = SpillingKt.nullOutSpilledVariable(ctx2);
                                placeSearch$search$1.L$1 = SpillingKt.nullOutSpilledVariable(query);
                                placeSearch$search$1.L$2 = source2;
                                placeSearch$search$1.L$3 = SpillingKt.nullOutSpilledVariable(near);
                                placeSearch$search$1.L$4 = SpillingKt.nullOutSpilledVariable(list);
                                placeSearch$search$1.L$5 = q2;
                                placeSearch$search$1.label = 1;
                                google = google(ctx2, q2, placeSearch$search$1);
                                if (google == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                q = q2;
                                hit = (Hit) google;
                                if (hit != null) {
                                    Hit hit2 = hit;
                                    msg = String.format("search '" + q + "' (" + hit2.getProvider() + ") → " + hit2.getName() + " %.5f,%.5f", Arrays.copyOf(new Object[]{Boxing.boxDouble(hit2.getLat()), Boxing.boxDouble(hit2.getLng())}, 2));
                                    Intrinsics.checkNotNullExpressionValue(msg, "format(...)");
                                    break;
                                }
                                msg = "search '" + q + "' (" + label(source2) + ") → nothing: " + lastError;
                                Log.i(TAG, msg);
                                RideDiagnostics.INSTANCE.log("search", msg);
                                return hit;
                            case 2:
                                placeSearch$search$1.L$0 = SpillingKt.nullOutSpilledVariable(ctx2);
                                placeSearch$search$1.L$1 = SpillingKt.nullOutSpilledVariable(query);
                                placeSearch$search$1.L$2 = source2;
                                placeSearch$search$1.L$3 = SpillingKt.nullOutSpilledVariable(near);
                                placeSearch$search$1.L$4 = SpillingKt.nullOutSpilledVariable(list);
                                placeSearch$search$1.L$5 = q2;
                                placeSearch$search$1.label = 2;
                                osm = osm(ctx2, q2, near, placeSearch$search$1);
                                if (osm == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                q = q2;
                                hit = (Hit) osm;
                                if (hit != null) {
                                }
                                msg = "search '" + q + "' (" + label(source2) + ") → nothing: " + lastError;
                                Log.i(TAG, msg);
                                RideDiagnostics.INSTANCE.log("search", msg);
                                return hit;
                            case 3:
                                hit = savedMatch$app(q2, list);
                                if (hit != null) {
                                    q = q2;
                                    if (hit != null) {
                                    }
                                    msg = "search '" + q + "' (" + label(source2) + ") → nothing: " + lastError;
                                    Log.i(TAG, msg);
                                    RideDiagnostics.INSTANCE.log("search", msg);
                                    return hit;
                                }
                                placeSearch$search$1.L$0 = SpillingKt.nullOutSpilledVariable(ctx2);
                                placeSearch$search$1.L$1 = SpillingKt.nullOutSpilledVariable(query);
                                placeSearch$search$1.L$2 = source2;
                                placeSearch$search$1.L$3 = SpillingKt.nullOutSpilledVariable(near);
                                placeSearch$search$1.L$4 = SpillingKt.nullOutSpilledVariable(list);
                                placeSearch$search$1.L$5 = q2;
                                placeSearch$search$1.label = 3;
                                osm2 = osm(ctx2, q2, near, placeSearch$search$1);
                                if (osm2 == coroutine_suspended) {
                                    return coroutine_suspended;
                                }
                                q = q2;
                                hit = (Hit) osm2;
                                if (hit != null) {
                                }
                                msg = "search '" + q + "' (" + label(source2) + ") → nothing: " + lastError;
                                Log.i(TAG, msg);
                                RideDiagnostics.INSTANCE.log("search", msg);
                                return hit;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    case 1:
                        q = (String) placeSearch$search$1.L$5;
                        near = (Pair) placeSearch$search$1.L$3;
                        source2 = (RoutingSource) placeSearch$search$1.L$2;
                        ctx2 = (Context) placeSearch$search$1.L$0;
                        ResultKt.throwOnFailure($result);
                        google = $result;
                        hit = (Hit) google;
                        if (hit != null) {
                        }
                        msg = "search '" + q + "' (" + label(source2) + ") → nothing: " + lastError;
                        Log.i(TAG, msg);
                        RideDiagnostics.INSTANCE.log("search", msg);
                        return hit;
                    case 2:
                        q = (String) placeSearch$search$1.L$5;
                        near = (Pair) placeSearch$search$1.L$3;
                        source2 = (RoutingSource) placeSearch$search$1.L$2;
                        ctx2 = (Context) placeSearch$search$1.L$0;
                        ResultKt.throwOnFailure($result);
                        osm = $result;
                        hit = (Hit) osm;
                        if (hit != null) {
                        }
                        msg = "search '" + q + "' (" + label(source2) + ") → nothing: " + lastError;
                        Log.i(TAG, msg);
                        RideDiagnostics.INSTANCE.log("search", msg);
                        return hit;
                    case 3:
                        q = (String) placeSearch$search$1.L$5;
                        near = (Pair) placeSearch$search$1.L$3;
                        source2 = (RoutingSource) placeSearch$search$1.L$2;
                        ctx2 = (Context) placeSearch$search$1.L$0;
                        ResultKt.throwOnFailure($result);
                        osm2 = $result;
                        hit = (Hit) osm2;
                        if (hit != null) {
                        }
                        msg = "search '" + q + "' (" + label(source2) + ") → nothing: " + lastError;
                        Log.i(TAG, msg);
                        RideDiagnostics.INSTANCE.log("search", msg);
                        return hit;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
        }
        placeSearch$search$1 = new PlaceSearch$search$1(this, continuation);
        Object $result2 = placeSearch$search$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (placeSearch$search$1.label) {
        }
    }

    public final Object suggestions(Context ctx, String query, Pair<Double, Double> pair, Continuation<? super List<Hit>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new PlaceSearch$suggestions$2(query, ctx, pair, null), continuation);
    }

    public final Hit savedMatch$app(String q, List<SavedLocation> saved) {
        Double lng;
        Double lat;
        SharedLocation sharedLocation;
        Intrinsics.checkNotNullParameter(q, "q");
        Intrinsics.checkNotNullParameter(saved, "saved");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = q.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable split = new Regex("\\s+").split(lowerCase, 0);
        Collection arrayList = new ArrayList();
        for (Object obj : split) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        List words = (List) arrayList;
        Iterator<SavedLocation> it = saved.iterator();
        do {
            Object obj2 = null;
            if (!it.hasNext()) {
                lastError = "no saved place matches";
                return null;
            }
            SavedLocation s = it.next();
            if (savedMatch$matches(words, s.getName())) {
                SharedLocation last = (SharedLocation) CollectionsKt.lastOrNull((List) s.getWaypoints());
                return new Hit(s.getName(), (last == null || (lat = last.getLat()) == null) ? s.getLat() : lat.doubleValue(), (last == null || (lng = last.getLng()) == null) ? s.getLng() : lng.doubleValue(), "saved place", null, 16, null);
            }
            Iterator it2 = s.getWaypoints().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Object next = it2.next();
                SharedLocation sharedLocation2 = (SharedLocation) next;
                if ((!savedMatch$matches(words, sharedLocation2.getName()) || sharedLocation2.getLat() == null || sharedLocation2.getLng() == null) ? false : true) {
                    obj2 = next;
                    break;
                }
            }
            sharedLocation = (SharedLocation) obj2;
        } while (sharedLocation == null);
        String name = sharedLocation.getName();
        Double lat2 = sharedLocation.getLat();
        Intrinsics.checkNotNull(lat2);
        double doubleValue = lat2.doubleValue();
        Double lng2 = sharedLocation.getLng();
        Intrinsics.checkNotNull(lng2);
        return new Hit(name, doubleValue, lng2.doubleValue(), "saved place", null, 16, null);
    }

    private static final boolean savedMatch$matches(List<String> list, String name) {
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String lowerCase = name.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        List<String> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return true;
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            if (!StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) it.next(), false, 2, (Object) null)) {
                return false;
            }
        }
        return true;
    }

    public final int wordScore$app(String q, String text) {
        Intrinsics.checkNotNullParameter(q, "q");
        Intrinsics.checkNotNullParameter(text, "text");
        Locale ROOT = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
        String t = text.toLowerCase(ROOT);
        Intrinsics.checkNotNullExpressionValue(t, "toLowerCase(...)");
        String squashed = StringsKt.replace$default(t, " ", "", false, 4, (Object) null);
        Locale ROOT2 = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
        String lowerCase = q.toLowerCase(ROOT2);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        Iterable split = new Regex("\\s+").split(lowerCase, 0);
        Collection arrayList = new ArrayList();
        for (Object obj : split) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        Iterable<String> iterable = (List) arrayList;
        if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
            return 0;
        }
        int i = 0;
        for (String str : iterable) {
            if ((StringsKt.contains$default((CharSequence) t, (CharSequence) str, false, 2, (Object) null) || StringsKt.contains$default((CharSequence) squashed, (CharSequence) str, false, 2, (Object) null)) && (i = i + 1) < 0) {
                CollectionsKt.throwCountOverflow();
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object google(Context ctx, String q, Continuation<? super Hit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new PlaceSearch$google$2(ctx, q, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object osm(Context ctx, String q, Pair<Double, Double> pair, Continuation<? super Hit> continuation) {
        PlaceSearch$osm$1 placeSearch$osm$1;
        Object photon;
        Hit hit;
        if (continuation instanceof PlaceSearch$osm$1) {
            placeSearch$osm$1 = (PlaceSearch$osm$1) continuation;
            if ((placeSearch$osm$1.label & Integer.MIN_VALUE) != 0) {
                placeSearch$osm$1.label -= Integer.MIN_VALUE;
                Object $result = placeSearch$osm$1.result;
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (placeSearch$osm$1.label) {
                    case 0:
                        ResultKt.throwOnFailure($result);
                        placeSearch$osm$1.L$0 = ctx;
                        placeSearch$osm$1.L$1 = q;
                        placeSearch$osm$1.L$2 = pair;
                        placeSearch$osm$1.label = 1;
                        photon = photon(ctx, q, pair, placeSearch$osm$1);
                        if (photon == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        break;
                    case 1:
                        pair = (Pair) placeSearch$osm$1.L$2;
                        q = (String) placeSearch$osm$1.L$1;
                        ctx = (Context) placeSearch$osm$1.L$0;
                        ResultKt.throwOnFailure($result);
                        photon = $result;
                        break;
                    case 2:
                        ResultKt.throwOnFailure($result);
                        return $result;
                    default:
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                hit = (Hit) photon;
                if (hit == null) {
                    return hit;
                }
                placeSearch$osm$1.L$0 = SpillingKt.nullOutSpilledVariable(ctx);
                placeSearch$osm$1.L$1 = SpillingKt.nullOutSpilledVariable(q);
                placeSearch$osm$1.L$2 = SpillingKt.nullOutSpilledVariable(pair);
                placeSearch$osm$1.label = 2;
                Object nominatim = nominatim(ctx, q, pair, placeSearch$osm$1);
                if (nominatim == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return nominatim;
            }
        }
        placeSearch$osm$1 = new PlaceSearch$osm$1(this, continuation);
        Object $result2 = placeSearch$osm$1.result;
        Object coroutine_suspended2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (placeSearch$osm$1.label) {
        }
        hit = (Hit) photon;
        if (hit == null) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object photon(Context ctx, String q, Pair<Double, Double> pair, Continuation<? super Hit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new PlaceSearch$photon$2(ctx, q, pair, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String httpGet(Context ctx, String url) {
        String str = null;
        try {
            HttpURLConnection conn = InternetNetwork.INSTANCE.openConnection(ctx, url);
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(10000);
            conn.setRequestProperty("User-Agent", USER_AGENT);
            conn.setRequestProperty("Accept", "application/json");
            try {
                if (conn.getResponseCode() != 200) {
                    lastError = "OpenStreetMap search HTTP " + conn.getResponseCode();
                } else {
                    InputStream inputStream = conn.getInputStream();
                    Intrinsics.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
                    Reader inputStreamReader = new InputStreamReader(inputStream, Charsets.UTF_8);
                    str = TextStreamsKt.readText(inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192));
                }
                return str;
            } finally {
                conn.disconnect();
            }
        } catch (IOException e) {
            lastError = "no internet for OpenStreetMap search";
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object nominatim(Context ctx, String q, Pair<Double, Double> pair, Continuation<? super Hit> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new PlaceSearch$nominatim$2(ctx, q, pair, null), continuation);
    }
}
