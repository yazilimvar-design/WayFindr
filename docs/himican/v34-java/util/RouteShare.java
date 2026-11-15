package com.example.wayfindr.util;

import androidx.autofill.HintConstants;
import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SharedLocation;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* compiled from: RouteShare.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u001eB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u001c\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u0010\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u0012\u0010\u001d\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/example/wayfindr/util/RouteShare;", "", "<init>", "()V", "SCHEME", "", "PREFIX", "VERSION", "", "MAX_STOPS", "linkRegex", "Lkotlin/text/Regex;", "stopsOf", "", "Lcom/example/wayfindr/data/SharedLocation;", "loc", "Lcom/example/wayfindr/data/SavedLocation;", "link", HintConstants.AUTOFILL_HINT_NAME, "stops", "decode", "Lcom/example/wayfindr/util/RouteShare$SharedRoute;", "text", "coord", "v", "", "oneLine", "s", "enc", "dec", "SharedRoute", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class RouteShare {
    private static final int MAX_STOPS = 26;
    private static final String PREFIX = "wayfindr://route?";
    public static final String SCHEME = "wayfindr";
    private static final int VERSION = 1;
    public static final RouteShare INSTANCE = new RouteShare();
    private static final Regex linkRegex = new Regex("wayfindr://route\\?[^\\s<>\"]+");
    public static final int $stable = 8;

    private RouteShare() {
    }

    /* compiled from: RouteShare.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/example/wayfindr/util/RouteShare$SharedRoute;", "", HintConstants.AUTOFILL_HINT_NAME, "", "stops", "", "Lcom/example/wayfindr/data/SharedLocation;", "<init>", "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getStops", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SharedRoute {
        public static final int $stable = 8;
        private final String name;
        private final List<SharedLocation> stops;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SharedRoute copy$default(SharedRoute sharedRoute, String str, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                str = sharedRoute.name;
            }
            if ((i & 2) != 0) {
                list = sharedRoute.stops;
            }
            return sharedRoute.copy(str, list);
        }

        /* renamed from: component1, reason: from getter */
        public final String getName() {
            return this.name;
        }

        public final List<SharedLocation> component2() {
            return this.stops;
        }

        public final SharedRoute copy(String name, List<SharedLocation> stops) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(stops, "stops");
            return new SharedRoute(name, stops);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SharedRoute)) {
                return false;
            }
            SharedRoute sharedRoute = (SharedRoute) other;
            return Intrinsics.areEqual(this.name, sharedRoute.name) && Intrinsics.areEqual(this.stops, sharedRoute.stops);
        }

        public int hashCode() {
            return (this.name.hashCode() * 31) + this.stops.hashCode();
        }

        public String toString() {
            return "SharedRoute(name=" + this.name + ", stops=" + this.stops + ")";
        }

        public SharedRoute(String name, List<SharedLocation> stops) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(stops, "stops");
            this.name = name;
            this.stops = stops;
        }

        public final String getName() {
            return this.name;
        }

        public final List<SharedLocation> getStops() {
            return this.stops;
        }
    }

    public final List<SharedLocation> stopsOf(SavedLocation loc) {
        Intrinsics.checkNotNullParameter(loc, "loc");
        Iterable waypoints = loc.getWaypoints();
        Collection arrayList = new ArrayList();
        for (Object obj : waypoints) {
            SharedLocation sharedLocation = (SharedLocation) obj;
            if ((sharedLocation.getLat() == null || sharedLocation.getLng() == null) ? false : true) {
                arrayList.add(obj);
            }
        }
        Collection collection = (List) arrayList;
        if (collection.isEmpty()) {
            collection = CollectionsKt.listOf(new SharedLocation(loc.getName(), Double.valueOf(loc.getLat()), Double.valueOf(loc.getLng()), null, false, 24, null));
        }
        return collection;
    }

    public final String link(String name, List<SharedLocation> stops) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(stops, "stops");
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX).append("n=").append(INSTANCE.enc(INSTANCE.oneLine(name)));
        Collection arrayList = new ArrayList();
        for (Object obj : stops) {
            SharedLocation sharedLocation = (SharedLocation) obj;
            if ((sharedLocation.getLat() == null || sharedLocation.getLng() == null) ? false : true) {
                arrayList.add(obj);
            }
        }
        for (SharedLocation sharedLocation2 : CollectionsKt.take((List) arrayList, 26)) {
            StringBuilder append = sb.append("&s=");
            RouteShare routeShare = INSTANCE;
            Double lat = sharedLocation2.getLat();
            Intrinsics.checkNotNull(lat);
            StringBuilder append2 = append.append(routeShare.coord(lat.doubleValue())).append(',');
            RouteShare routeShare2 = INSTANCE;
            Double lng = sharedLocation2.getLng();
            Intrinsics.checkNotNull(lng);
            append2.append(routeShare2.coord(lng.doubleValue()));
            String oneLine = INSTANCE.oneLine(sharedLocation2.getName());
            if (oneLine.length() > 0) {
                sb.append(',').append(INSTANCE.enc(oneLine));
            }
        }
        sb.append("&v=").append(1);
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r20v1 */
    /* JADX WARN: Type inference failed for: r20v2, types: [com.example.wayfindr.util.RouteShare$SharedRoute] */
    /* JADX WARN: Type inference failed for: r20v23 */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Type inference failed for: r20v5 */
    public final SharedRoute decode(String text) {
        String value;
        ?? r20;
        String obj;
        Double doubleOrNull;
        String obj2;
        Double doubleOrNull2;
        String str;
        Intrinsics.checkNotNullParameter(text, "text");
        int i = 0;
        int i2 = 2;
        String str2 = null;
        MatchResult find$default = Regex.find$default(linkRegex, text, 0, 2, null);
        if (find$default != null && (value = find$default.getValue()) != null) {
            String str3 = "";
            ArrayList arrayList = new ArrayList();
            int i3 = 1;
            Iterator it = StringsKt.split$default((CharSequence) StringsKt.removePrefix(value, (CharSequence) PREFIX), new char[]{Typography.amp}, false, 0, 6, (Object) null).iterator();
            while (true) {
                if (!it.hasNext()) {
                    r20 = str2;
                    break;
                }
                String str4 = (String) it.next();
                String substringBefore$default = StringsKt.substringBefore$default(str4, '=', str2, i2, (Object) str2);
                String substringAfter = StringsKt.substringAfter(str4, '=', "");
                if (Intrinsics.areEqual(substringBefore$default, "n")) {
                    String dec = dec(substringAfter);
                    r20 = str2;
                    str3 = dec != null ? dec : "";
                } else if (!Intrinsics.areEqual(substringBefore$default, "s")) {
                    r20 = str2;
                } else {
                    String dec2 = dec(substringAfter);
                    if (dec2 != null) {
                        String str5 = dec2;
                        char[] cArr = new char[i3];
                        cArr[i] = ',';
                        List split$default = StringsKt.split$default((CharSequence) str5, cArr, false, 3, 2, (Object) null);
                        String str6 = (String) CollectionsKt.getOrNull(split$default, i);
                        if (str6 != null && (obj = StringsKt.trim((CharSequence) str6).toString()) != null && (doubleOrNull = StringsKt.toDoubleOrNull(obj)) != null) {
                            double doubleValue = doubleOrNull.doubleValue();
                            String str7 = (String) CollectionsKt.getOrNull(split$default, i3);
                            if (str7 != null && (obj2 = StringsKt.trim((CharSequence) str7).toString()) != null && (doubleOrNull2 = StringsKt.toDoubleOrNull(obj2)) != null) {
                                double doubleValue2 = doubleOrNull2.doubleValue();
                                if (((-90.0d > doubleValue || doubleValue > 90.0d) ? i : i3) != 0) {
                                    if (((-180.0d > doubleValue2 || doubleValue2 > 180.0d) ? i : i3) != 0) {
                                        ArrayList arrayList2 = arrayList;
                                        String str8 = (String) CollectionsKt.getOrNull(split$default, i2);
                                        String obj3 = str8 != null ? StringsKt.trim((CharSequence) str8).toString() : str2;
                                        String str9 = obj3 != null ? obj3 : "";
                                        if ((str9.length() == 0 ? i3 : 0) != 0) {
                                            str = str2;
                                            str9 = "Stop " + (arrayList.size() + 1);
                                        } else {
                                            str = str2;
                                        }
                                        arrayList2.add(new SharedLocation(str9, Double.valueOf(doubleValue), Double.valueOf(doubleValue2), null, false, 24, null));
                                        r20 = str;
                                    }
                                }
                            }
                        }
                    }
                    str2 = str2;
                    i = 0;
                    i2 = 2;
                    i3 = 1;
                }
                if (arrayList.size() >= 26) {
                    break;
                }
                str2 = r20;
                i = 0;
                i2 = 2;
                i3 = 1;
            }
            if (arrayList.isEmpty()) {
                return r20;
            }
            String str10 = str3;
            if (StringsKt.isBlank(str10)) {
                str10 = ((SharedLocation) CollectionsKt.last((List) arrayList)).getName();
            }
            return new SharedRoute(str10, arrayList);
        }
        return null;
    }

    private final String coord(double v) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%.6f", Arrays.copyOf(new Object[]{Double.valueOf(v)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return StringsKt.trimEnd(StringsKt.trimEnd(format, '0'), '.');
    }

    private final String oneLine(String s) {
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(s, " ")).toString();
    }

    private final String enc(String s) {
        String encode = URLEncoder.encode(s, "UTF-8");
        Intrinsics.checkNotNullExpressionValue(encode, "encode(...)");
        return StringsKt.replace$default(encode, "+", "%20", false, 4, (Object) null);
    }

    private final String dec(String s) {
        Object m8392constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            RouteShare routeShare = this;
            m8392constructorimpl = Result.m8392constructorimpl(URLDecoder.decode(s, "UTF-8"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = null;
        }
        return (String) m8392constructorimpl;
    }
}
