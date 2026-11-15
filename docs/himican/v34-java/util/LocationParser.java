package com.example.wayfindr.util;

import android.util.Log;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.data.SharedLocation;
import com.google.android.gms.common.internal.ImagesContract;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: LocationParser.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0005J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0016\u001a\u00020\u0005J\u001c\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00150\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u001aJ2\u0010\u001b\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u001c2\u0006\u0010\u0019\u001a\u00020\u0005H\u0086@¢\u0006\u0002\u0010\u001aJ\u001c\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u0006\u0010\u001f\u001a\u00020\u0005J\u0012\u0010 \u001a\u0004\u0018\u00010\u00152\u0006\u0010!\u001a\u00020\u0005H\u0002J\u0010\u0010\"\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001f\u001a\u00020\u0005J\u0018\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001dH\u0002J\u0010\u0010(\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u001e\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u0006\u0010*\u001a\u00020\u0005H\u0002J\u001c\u0010+\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u001c2\u0006\u0010,\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/example/wayfindr/util/LocationParser;", "", "<init>", "()V", "TAG", "", "UA", "urlRegex", "Lkotlin/text/Regex;", "coord3d4d", "coordAt", "coordQ", "coordLl", "coordGeo", "coordSearch", "placePath", "placeQ", "bareCoords", "bodyPatterns", "", "parse", "Lcom/example/wayfindr/data/SharedLocation;", "text", "parseStops", "resolveStops", ImagesContract.URL, "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resolve", "Lkotlin/Pair;", "", "extractCoords", "s", "stopFromToken", "token", "extractPlaceName", "valid", "", "lat", "lng", "allowedHostSuffixes", "isAllowedHost", "scanBody", "body", "fetchFollowing", "start", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class LocationParser {
    private static final String TAG = "LocationParser";
    private static final String UA = "Mozilla/5.0 (Linux; Android 14; Nothing Phone 3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0 Mobile Safari/537.36";
    public static final LocationParser INSTANCE = new LocationParser();
    private static final Regex urlRegex = new Regex("https?://[^\\s)]+");
    private static final Regex coord3d4d = new Regex("!3d(-?\\d+\\.\\d+)!4d(-?\\d+\\.\\d+)");
    private static final Regex coordAt = new Regex("@(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)");
    private static final Regex coordQ = new Regex("[?&](?:q|query|destination|daddr)=(-?\\d+\\.\\d+),\\s*(-?\\d+\\.\\d+)");
    private static final Regex coordLl = new Regex("[?&]ll=(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)");
    private static final Regex coordGeo = new Regex("geo:(-?\\d+\\.\\d+),(-?\\d+\\.\\d+)");
    private static final Regex coordSearch = new Regex("/search/(-?\\d+\\.\\d+),\\+?(-?\\d+\\.\\d+)");
    private static final Regex placePath = new Regex("/place/([^/@?]+)");
    private static final Regex placeQ = new Regex("[?&]q=([^&0-9\\-@][^&]*)");
    private static final Regex bareCoords = new Regex("^\\s*(-?\\d+\\.\\d+)\\s*,\\s*(-?\\d+\\.\\d+)\\s*$");
    private static final List<Regex> bodyPatterns = CollectionsKt.listOf((Object[]) new Regex[]{coord3d4d, new Regex("\\[null,null,(-?\\d+\\.\\d{3,}),(-?\\d+\\.\\d{3,})\\]"), new Regex("center=(-?\\d+\\.\\d+)(?:%2C|,)(-?\\d+\\.\\d+)"), new Regex("\"latitude\":\\s*(-?\\d+\\.\\d+)[^}]{0,60}?\"longitude\":\\s*(-?\\d+\\.\\d+)"), coordAt});
    private static final List<String> allowedHostSuffixes = CollectionsKt.listOf((Object[]) new String[]{"google.com", "goo.gl", "g.co", "ggpht.com"});
    public static final int $stable = 8;

    private LocationParser() {
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0143  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SharedLocation parse(String text) {
        String str;
        String str2;
        Pair coords;
        String str3;
        String name;
        List lines;
        Object obj;
        String removeSuffix;
        String removePrefix;
        String value;
        Intrinsics.checkNotNullParameter(text, "text");
        String trimmed = StringsKt.trim((CharSequence) text).toString();
        Dbg dbg = Dbg.INSTANCE;
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "parse(): " + trimmed);
        }
        MatchResult find$default = Regex.find$default(urlRegex, trimmed, 0, 2, null);
        if (find$default == null || (value = find$default.getValue()) == null || (str = StringsKt.trimEnd(value, '.', ',', ';', '!', '?', ')', ']', Typography.quote, '\'')) == null) {
            str = StringsKt.startsWith$default(trimmed, "geo:", false, 2, (Object) null) ? trimmed : null;
        }
        String url = str;
        boolean isShort = url != null && (StringsKt.contains$default((CharSequence) url, (CharSequence) "maps.app.goo.gl", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) url, (CharSequence) "goo.gl/maps", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) url, (CharSequence) "g.co/kgs", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) url, (CharSequence) "//goo.gl/", false, 2, (Object) null));
        String textBefore = url != null ? StringsKt.trim((CharSequence) StringsKt.substringBefore$default(trimmed, url, (String) null, 2, (Object) null)).toString() : null;
        if (textBefore != null && (lines = StringsKt.lines(textBefore)) != null) {
            ListIterator listIterator = lines.listIterator(lines.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    obj = null;
                    break;
                }
                obj = listIterator.previous();
                if (!StringsKt.isBlank((String) obj)) {
                    break;
                }
            }
            String str4 = (String) obj;
            if (str4 != null && (removeSuffix = StringsKt.removeSuffix(str4, (CharSequence) ":")) != null && (removePrefix = StringsKt.removePrefix(removeSuffix, (CharSequence) "Check out")) != null) {
                str2 = StringsKt.trim((CharSequence) removePrefix).toString();
                String textName = str2;
                coords = (url != null || isShort) ? extractCoords(trimmed) : extractCoords(url);
                str3 = textName;
                if ((str3 != null || StringsKt.isBlank(str3)) && !Intrinsics.areEqual(textName, "Check out")) {
                    name = textName;
                } else if (url != null || isShort) {
                    name = coords == null ? "Dropped pin" : "Loading…";
                } else {
                    String extractPlaceName = extractPlaceName(url);
                    if (extractPlaceName == null) {
                        extractPlaceName = "Shared location";
                    }
                    name = extractPlaceName;
                }
                Dbg dbg2 = Dbg.INSTANCE;
                if (BuildConfig.DEBUG) {
                    Log.d(TAG, "parse() → name='" + name + "' coords=" + coords + " short=" + isShort + " url=" + url);
                }
                return new SharedLocation(name, coords == null ? coords.getFirst() : null, coords != null ? coords.getSecond() : null, url, (coords == null || url == null) ? false : true);
            }
        }
        str2 = null;
        String textName2 = str2;
        coords = (url != null || isShort) ? extractCoords(trimmed) : extractCoords(url);
        str3 = textName2;
        if (str3 != null || StringsKt.isBlank(str3)) {
        }
        if (url != null) {
        }
        if (coords == null) {
        }
        Dbg dbg22 = Dbg.INSTANCE;
        if (BuildConfig.DEBUG) {
        }
        return new SharedLocation(name, coords == null ? coords.getFirst() : null, coords != null ? coords.getSecond() : null, url, (coords == null || url == null) ? false : true);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:114:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0101  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0105 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0162 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02d6 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v44 */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v49 */
    /* JADX WARN: Type inference failed for: r0v75, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v83, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r24v0, types: [boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final List<SharedLocation> parseStops(String text) {
        String value;
        String trimEnd;
        Object m8392constructorimpl;
        Object obj;
        Iterator it;
        String str;
        List list;
        List list2;
        String str2;
        List list3;
        ?? r0;
        String str3;
        ArrayList arrayList;
        boolean z;
        Pair pair;
        String str4;
        String str5;
        Intrinsics.checkNotNullParameter(text, "text");
        int i = 0;
        int i2 = 2;
        String str6 = null;
        MatchResult find$default = Regex.find$default(urlRegex, text, 0, 2, null);
        if (find$default == null || (value = find$default.getValue()) == null || (trimEnd = StringsKt.trimEnd(value, '.', ',', ';', '!', '?', ')', ']', Typography.quote, '\'')) == null) {
            return CollectionsKt.emptyList();
        }
        String str7 = trimEnd;
        try {
            Result.Companion companion = Result.INSTANCE;
            LocationParser locationParser = this;
            m8392constructorimpl = Result.m8392constructorimpl(URLDecoder.decode(str7, "UTF-8"));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m8398isFailureimpl(m8392constructorimpl)) {
            m8392constructorimpl = str7;
        }
        String str8 = (String) m8392constructorimpl;
        List<String> split$default = StringsKt.split$default((CharSequence) StringsKt.substringAfter(str7, '?', ""), new char[]{Typography.amp}, false, 0, 6, (Object) null);
        ArrayList arrayList2 = new ArrayList();
        for (String str9 : split$default) {
            int i3 = i;
            String lowerCase = StringsKt.substringBefore$default(str9, '=', str6, i2, (Object) str6).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            String substringAfter = StringsKt.substringAfter(str9, '=', "");
            String str10 = !StringsKt.isBlank(substringAfter) ? substringAfter : null;
            if (str10 != null) {
                String str11 = str10;
                LocationParser locationParser2 = INSTANCE;
                try {
                    Result.Companion companion3 = Result.INSTANCE;
                    str4 = str11;
                } catch (Throwable th2) {
                    th = th2;
                    str4 = str11;
                }
                try {
                    str5 = Result.m8392constructorimpl(URLDecoder.decode(str4, "UTF-8"));
                } catch (Throwable th3) {
                    th = th3;
                    Result.Companion companion4 = Result.INSTANCE;
                    str5 = Result.m8392constructorimpl(ResultKt.createFailure(th));
                    String str12 = str5;
                    pair = TuplesKt.to(lowerCase, !Result.m8398isFailureimpl(str12) ? str4 : str12);
                    if (pair == null) {
                    }
                    i = i3;
                    i2 = 2;
                    str6 = null;
                }
                String str122 = str5;
                pair = TuplesKt.to(lowerCase, !Result.m8398isFailureimpl(str122) ? str4 : str122);
            } else {
                pair = null;
            }
            if (pair == null) {
                arrayList2.add(pair);
            }
            i = i3;
            i2 = 2;
            str6 = null;
        }
        ?? r24 = i;
        ArrayList arrayList3 = arrayList2;
        ArrayList arrayList4 = arrayList3;
        ArrayList arrayList5 = new ArrayList();
        for (Object obj2 : arrayList4) {
            String str13 = (String) ((Pair) obj2).component1();
            ArrayList arrayList6 = arrayList3;
            if (Intrinsics.areEqual(str13, "waypoints")) {
                arrayList = arrayList4;
            } else {
                arrayList = arrayList4;
                if (!new Regex("waypoint\\d+").matches(str13)) {
                    z = r24 == true ? 1 : 0;
                    if (!z) {
                        arrayList5.add(obj2);
                    }
                    arrayList3 = arrayList6;
                    arrayList4 = arrayList;
                }
            }
            z = true;
            if (!z) {
            }
            arrayList3 = arrayList6;
            arrayList4 = arrayList;
        }
        ArrayList arrayList7 = arrayList3;
        List sortedWith = CollectionsKt.sortedWith(arrayList5, new Comparator() { // from class: com.example.wayfindr.util.LocationParser$parseStops$$inlined$sortedBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int intValue;
                String str14 = (String) ((Pair) t).component1();
                int i4 = Integer.MAX_VALUE;
                if (Intrinsics.areEqual(str14, "waypoints")) {
                    intValue = -1;
                } else {
                    Integer intOrNull = StringsKt.toIntOrNull(StringsKt.removePrefix(str14, (CharSequence) "waypoint"));
                    intValue = intOrNull != null ? intOrNull.intValue() : Integer.MAX_VALUE;
                }
                Integer valueOf = Integer.valueOf(intValue);
                String str15 = (String) ((Pair) t2).component1();
                if (Intrinsics.areEqual(str15, "waypoints")) {
                    i4 = -1;
                } else {
                    Integer intOrNull2 = StringsKt.toIntOrNull(StringsKt.removePrefix(str15, (CharSequence) "waypoint"));
                    if (intOrNull2 != null) {
                        i4 = intOrNull2.intValue();
                    }
                }
                return ComparisonsKt.compareValues(valueOf, Integer.valueOf(i4));
            }
        });
        ArrayList arrayList8 = new ArrayList();
        List list4 = sortedWith;
        Iterator it2 = list4.iterator();
        while (it2.hasNext()) {
            String str14 = (String) ((Pair) it2.next()).component2();
            Intrinsics.checkNotNull(str14);
            char[] cArr = new char[1];
            cArr[r24 == true ? 1 : 0] = '|';
            Iterator it3 = StringsKt.split$default((CharSequence) str14, cArr, false, 0, 6, (Object) null).iterator();
            while (it3.hasNext()) {
                ArrayList arrayList9 = arrayList8;
                arrayList9.add(StringsKt.removePrefix((String) it3.next(), (CharSequence) "via:"));
                sortedWith = sortedWith;
                arrayList8 = arrayList9;
                list4 = list4;
            }
        }
        ArrayList arrayList10 = arrayList8;
        Iterator it4 = arrayList7.iterator();
        while (true) {
            if (!it4.hasNext()) {
                obj = null;
                break;
            }
            obj = it4.next();
            if (Intrinsics.areEqual((String) ((Pair) obj).component1(), "destination")) {
                break;
            }
        }
        Pair pair2 = (Pair) obj;
        if (pair2 != null && (str3 = (String) pair2.getSecond()) != null) {
            arrayList10.add(str3);
        }
        if (arrayList10.size() < 2) {
            Intrinsics.checkNotNull(str8);
            if (StringsKt.contains$default(str8, "/dir/", (boolean) r24, 2, (Object) null)) {
                String substringBefore$default = StringsKt.substringBefore$default(StringsKt.substringBefore$default(StringsKt.substringAfter$default(str8, "/dir/", (String) null, 2, (Object) null), "/data", (String) null, 2, (Object) null), '?', (String) null, 2, (Object) null);
                List split$default2 = StringsKt.split$default((CharSequence) substringBefore$default, new char[]{'/'}, false, 0, 6, (Object) null);
                List list5 = split$default2;
                ArrayList arrayList11 = new ArrayList();
                for (Object obj3 : list5) {
                    String str15 = (String) obj3;
                    if (StringsKt.isBlank(str15)) {
                        str = substringBefore$default;
                        list = split$default2;
                        list2 = list5;
                        str2 = str7;
                        list3 = null;
                    } else {
                        str = substringBefore$default;
                        list = split$default2;
                        list2 = list5;
                        str2 = str7;
                        list3 = null;
                        if (!StringsKt.startsWith$default(str15, "@", false, 2, (Object) null)) {
                            r0 = 1;
                            if (r0 == 0) {
                                arrayList11.add(obj3);
                            }
                            substringBefore$default = str;
                            split$default2 = list;
                            list5 = list2;
                            str7 = str2;
                        }
                    }
                    r0 = list3;
                    if (r0 == 0) {
                    }
                    substringBefore$default = str;
                    split$default2 = list;
                    list5 = list2;
                    str7 = str2;
                }
                ArrayList arrayList12 = arrayList11;
                String str16 = (String) CollectionsKt.firstOrNull(split$default2);
                List drop = StringsKt.isBlank(str16 != null ? str16 : "") ^ true ? CollectionsKt.drop(arrayList12, 1) : arrayList12;
                if (drop.size() >= 2) {
                    arrayList10.clear();
                    arrayList10.addAll(drop);
                }
                ArrayList arrayList13 = new ArrayList();
                it = arrayList10.iterator();
                while (it.hasNext()) {
                    SharedLocation stopFromToken = stopFromToken((String) it.next());
                    if (stopFromToken != null) {
                        arrayList13.add(stopFromToken);
                    }
                }
                HashSet hashSet = new HashSet();
                ArrayList arrayList14 = new ArrayList();
                for (Object obj4 : arrayList13) {
                    SharedLocation sharedLocation = (SharedLocation) obj4;
                    if (hashSet.add(sharedLocation.getLat() + "," + sharedLocation.getLng() + ":" + sharedLocation.getName())) {
                        arrayList14.add(obj4);
                    }
                }
                return CollectionsKt.take(arrayList14, 26);
            }
        }
        ArrayList arrayList132 = new ArrayList();
        it = arrayList10.iterator();
        while (it.hasNext()) {
        }
        HashSet hashSet2 = new HashSet();
        ArrayList arrayList142 = new ArrayList();
        while (r5.hasNext()) {
        }
        return CollectionsKt.take(arrayList142, 26);
    }

    public final Object resolveStops(String url, Continuation<? super List<SharedLocation>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new LocationParser$resolveStops$2(url, null), continuation);
    }

    public final Object resolve(String url, Continuation<? super Pair<Pair<Double, Double>, String>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new LocationParser$resolve$2(url, null), continuation);
    }

    public final Pair<Double, Double> extractCoords(String s) {
        Double doubleOrNull;
        Intrinsics.checkNotNullParameter(s, "s");
        Iterator it = CollectionsKt.listOf((Object[]) new Regex[]{coord3d4d, coordGeo, coordSearch, coordAt, coordQ, coordLl}).iterator();
        while (true) {
            if (!it.hasNext()) {
                return null;
            }
            Regex regex = (Regex) it.next();
            MatchResult m = Regex.find$default(regex, s, 0, 2, null);
            if (m != null && (doubleOrNull = StringsKt.toDoubleOrNull(m.getGroupValues().get(1))) != null) {
                double doubleValue = doubleOrNull.doubleValue();
                Double doubleOrNull2 = StringsKt.toDoubleOrNull(m.getGroupValues().get(2));
                Pair pair = doubleOrNull2 != null ? TuplesKt.to(Double.valueOf(doubleValue), Double.valueOf(doubleOrNull2.doubleValue())) : null;
                if (pair != null && valid(pair.getFirst().doubleValue(), pair.getSecond().doubleValue())) {
                    return pair;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x007d, code lost:
    
        if (r1 == null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final SharedLocation stopFromToken(String token) {
        String clean = StringsKt.removePrefix(StringsKt.trim((CharSequence) StringsKt.replace$default(token, '+', ' ', false, 4, (Object) null)).toString(), (CharSequence) "place/");
        Pair coords = null;
        if (StringsKt.isBlank(clean) || StringsKt.startsWith$default(clean, "place_id:", false, 2, (Object) null)) {
            return null;
        }
        MatchResult matchEntire = bareCoords.matchEntire(clean);
        if (matchEntire != null) {
            Double doubleOrNull = StringsKt.toDoubleOrNull(matchEntire.getGroupValues().get(1));
            if (doubleOrNull != null) {
                double doubleValue = doubleOrNull.doubleValue();
                Double doubleOrNull2 = StringsKt.toDoubleOrNull(matchEntire.getGroupValues().get(2));
                if (doubleOrNull2 != null) {
                    coords = TuplesKt.to(Double.valueOf(doubleValue), Double.valueOf(doubleOrNull2.doubleValue()));
                }
            }
        }
        coords = extractCoords(clean);
        if (coords != null && valid(coords.getFirst().doubleValue(), coords.getSecond().doubleValue())) {
            return new SharedLocation("Dropped pin", coords.getFirst(), coords.getSecond(), null, false, 24, null);
        }
        return new SharedLocation(StringsKt.replace$default(clean, '_', ' ', false, 4, (Object) null), null, null, null, true, 14, null);
    }

    public final String extractPlaceName(String s) {
        Intrinsics.checkNotNullParameter(s, "s");
        String str = null;
        MatchResult find$default = Regex.find$default(placePath, s, 0, 2, null);
        if (find$default != null) {
            String decode = URLDecoder.decode(StringsKt.replace$default(find$default.getGroupValues().get(1), "+", " ", false, 4, (Object) null), "UTF-8");
            Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
            String obj = StringsKt.trim((CharSequence) StringsKt.replace$default(decode, "_", " ", false, 4, (Object) null)).toString();
            if (!StringsKt.isBlank(obj)) {
                str = obj;
            }
            return str;
        }
        MatchResult find$default2 = Regex.find$default(placeQ, s, 0, 2, null);
        if (find$default2 == null) {
            return null;
        }
        String decode2 = URLDecoder.decode(StringsKt.replace$default(find$default2.getGroupValues().get(1), "+", " ", false, 4, (Object) null), "UTF-8");
        Intrinsics.checkNotNullExpressionValue(decode2, "decode(...)");
        String obj2 = StringsKt.trim((CharSequence) decode2).toString();
        if (!StringsKt.isBlank(obj2)) {
            str = obj2;
        }
        return str;
    }

    private final boolean valid(double lat, double lng) {
        if (-90.0d <= lat && lat <= 90.0d) {
            if (-180.0d <= lng && lng <= 180.0d) {
                if (!(lat == 0.0d)) {
                    return true;
                }
                if (!(lng == 0.0d)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x006e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[LOOP:0: B:22:0x0038->B:34:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean isAllowedHost(String url) {
        Object m8392constructorimpl;
        String host;
        boolean z;
        String str;
        try {
            Result.Companion companion = Result.INSTANCE;
            LocationParser locationParser = this;
            host = new URL(url).getHost();
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
        }
        if (host != null) {
            String lowerCase = host.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                Iterable iterable = allowedHostSuffixes;
                if ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) {
                    z = false;
                } else {
                    Iterator it = iterable.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            String str2 = (String) it.next();
                            z = true;
                            if (!Intrinsics.areEqual(lowerCase, str2) && !StringsKt.endsWith$default(lowerCase, "." + str2, false, 2, (Object) null)) {
                                str = null;
                                if (str == null) {
                                    break;
                                }
                            }
                            str = 1;
                            if (str == null) {
                            }
                        } else {
                            z = false;
                            break;
                        }
                    }
                }
                m8392constructorimpl = Result.m8392constructorimpl(Boolean.valueOf(z));
                if (Result.m8398isFailureimpl(m8392constructorimpl)) {
                    m8392constructorimpl = false;
                }
                return ((Boolean) m8392constructorimpl).booleanValue();
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Pair<Double, Double> scanBody(String body) {
        Double doubleOrNull;
        if (StringsKt.isBlank(body)) {
            return null;
        }
        for (Regex p : bodyPatterns) {
            MatchResult m = Regex.find$default(p, body, 0, 2, null);
            if (m != null && (doubleOrNull = StringsKt.toDoubleOrNull(m.getGroupValues().get(1))) != null) {
                double lat = doubleOrNull.doubleValue();
                Double doubleOrNull2 = StringsKt.toDoubleOrNull(m.getGroupValues().get(2));
                if (doubleOrNull2 != null) {
                    double lng = doubleOrNull2.doubleValue();
                    if (valid(lat, lng)) {
                        Dbg dbg = Dbg.INSTANCE;
                        if (BuildConfig.DEBUG) {
                            Log.d(TAG, "scanBody matched " + StringsKt.take(p.getPattern(), 24) + " → " + lat + "," + lng);
                        }
                        return TuplesKt.to(Double.valueOf(lat), Double.valueOf(lng));
                    }
                } else {
                    continue;
                }
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0159 A[Catch: Exception -> 0x01e2, TryCatch #2 {Exception -> 0x01e2, blocks: (B:6:0x0018, B:8:0x0028, B:10:0x0088, B:72:0x0090, B:12:0x00b3, B:16:0x0101, B:18:0x010a, B:25:0x0146, B:27:0x014b, B:32:0x0159, B:35:0x017c, B:37:0x0185, B:39:0x0195, B:42:0x01a5, B:46:0x01ab, B:49:0x0169, B:51:0x01b1, B:55:0x01cb, B:64:0x01de, B:65:0x01e1, B:74:0x0035, B:76:0x0045, B:78:0x004b, B:80:0x0053, B:82:0x0068, B:53:0x01b8, B:61:0x01dc), top: B:5:0x0018, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01b1 A[SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v7, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Pair<String, String> fetchFollowing(String start) {
        MatchResult find$default;
        List<String> groupValues;
        String str;
        int i;
        boolean z;
        String url;
        boolean z2;
        String str2;
        Ref.ObjectRef body = new Ref.ObjectRef();
        body.element = "";
        ?? r4 = 0;
        String str3 = start;
        int i2 = 0;
        while (i2 < 8) {
            int i3 = i2;
            try {
                if ((StringsKt.contains$default(str3, "consent.google", (boolean) r4, 2, (Object) null) || StringsKt.contains$default(str3, "/sorry/", (boolean) r4, 2, (Object) null)) && (find$default = Regex.find$default(new Regex("continue=([^&]+)"), str3, r4, 2, null)) != null && (groupValues = find$default.getGroupValues()) != null && (str = groupValues.get(1)) != null) {
                    String decode = URLDecoder.decode(str, "UTF-8");
                    Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
                    str3 = decode;
                    Dbg dbg = Dbg.INSTANCE;
                    if (BuildConfig.DEBUG) {
                        Log.d(TAG, "consent bypass → " + StringsKt.take(str3, 100));
                    }
                }
                if (!INSTANCE.isAllowedHost(str3)) {
                    Log.w(TAG, "blocked non-allowlisted host: " + StringsKt.take(str3, 80));
                    return TuplesKt.to(str3, body.element);
                }
                ApiUsage.INSTANCE.record(ApiUsage.SHORT_LINK);
                URLConnection openConnection = new URL(str3).openConnection();
                Intrinsics.checkNotNull(openConnection, "null cannot be cast to non-null type java.net.HttpURLConnection");
                HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
                httpURLConnection.setInstanceFollowRedirects(r4);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("User-Agent", UA);
                httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
                httpURLConnection.setConnectTimeout(9000);
                httpURLConnection.setReadTimeout(9000);
                int responseCode = httpURLConnection.getResponseCode();
                String headerField = httpURLConnection.getHeaderField("Location");
                Dbg dbg2 = Dbg.INSTANCE;
                if (BuildConfig.DEBUG) {
                    if (headerField == null || (str2 = StringsKt.take(headerField, 90)) == null) {
                        str2 = "";
                    }
                    i = i2;
                    Log.d(TAG, "hop " + i3 + ": " + responseCode + " " + str2);
                } else {
                    i = i2;
                }
                if (300 <= responseCode && responseCode < 400) {
                    String str4 = headerField;
                    if (str4 != null && !StringsKt.isBlank(str4)) {
                        z = false;
                        if (z) {
                            httpURLConnection.disconnect();
                            if (StringsKt.startsWith$default(headerField, "http", false, 2, (Object) null)) {
                                url = headerField;
                            } else {
                                url = new URL(new URL(str3), headerField).toString();
                                Intrinsics.checkNotNullExpressionValue(url, "toString(...)");
                            }
                            str3 = url;
                            if (INSTANCE.extractCoords(str3) == null || StringsKt.contains$default((CharSequence) str3, (CharSequence) "/dir/", false, 2, (Object) null)) {
                                z2 = false;
                            } else {
                                z2 = false;
                                if (!StringsKt.contains$default((CharSequence) str3, (CharSequence) "waypoints=", false, 2, (Object) null)) {
                                    return TuplesKt.to(str3, "");
                                }
                            }
                            i2 = i + 1;
                            r4 = z2;
                        }
                    }
                    z = true;
                    if (z) {
                    }
                }
                InputStream inputStream = httpURLConnection.getInputStream();
                try {
                    InputStream inputStream2 = inputStream;
                    Intrinsics.checkNotNull(inputStream2);
                    ?? str5 = new String(ByteStreamsKt.readBytes(inputStream2), Charsets.UTF_8);
                    CloseableKt.closeFinally(inputStream, null);
                    body.element = str5;
                    httpURLConnection.disconnect();
                    return TuplesKt.to(str3, body.element);
                } finally {
                }
            } catch (Exception e) {
                Log.w(TAG, "fetchFollowing failed: " + e.getMessage());
            }
        }
        return TuplesKt.to(str3, body.element);
    }
}
