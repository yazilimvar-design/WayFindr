package com.example.wayfindr.dash.nav;

import android.content.Context;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.data.ApiUsage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PlaceSearch.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class PlaceSearch$suggestions$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Pair $near;
    final /* synthetic */ String $query;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$suggestions$2(String str, Context context, Pair pair, Continuation continuation) {
        super(2, continuation);
        this.$query = str;
        this.$ctx = context;
        this.$near = pair;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        Continuation placeSearch$suggestions$2 = new PlaceSearch$suggestions$2(this.$query, this.$ctx, this.$near, continuation);
        placeSearch$suggestions$2.L$0 = obj;
        return placeSearch$suggestions$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x02f7  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x02fc A[Catch: Exception -> 0x03c9, IOException -> 0x03cb, TryCatch #12 {IOException -> 0x03cb, Exception -> 0x03c9, blocks: (B:62:0x02e7, B:63:0x02f1, B:66:0x02f8, B:68:0x02fc, B:115:0x0309, B:116:0x0324, B:118:0x032a, B:125:0x03a2, B:128:0x03b1, B:130:0x03c0), top: B:61:0x02e7 }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0300 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ArrayList emptyList;
        CoroutineScope coroutineScope;
        String str;
        String str2;
        Object obj2;
        PlaceSearch.Hit hit;
        JSONArray jSONArray;
        List distinct;
        String str3;
        PlaceSearch.Hit hit2;
        ArrayList arrayList;
        CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                List keyCommand = GPlus.keyCommand(this.$ctx, this.$query, this.$near);
                if (keyCommand != null) {
                    return keyCommand;
                }
                String obj3 = StringsKt.trim(this.$query).toString();
                if (obj3.length() < 2) {
                    return CollectionsKt.emptyList();
                }
                PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                PlaceSearch.access$setLastError$p((String) null);
                try {
                    Pair pair = this.$near;
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://photon.komoot.io/api/").append("?limit=20&location_bias_scale=0.6&q=").append(URLEncoder.encode(obj3, "UTF-8"));
                    StringBuilder append = sb.append("&lang=");
                    PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                    String language = Locale.getDefault().getLanguage();
                    Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
                    append.append(placeSearch2.photonLanguageCode$app(language));
                    if (pair != null) {
                        try {
                            coroutineScope = coroutineScope2;
                            try {
                                String format = String.format(Locale.ROOT, "&lat=%.5f&lon=%.5f", Arrays.copyOf(new Object[]{Boxing.boxDouble(((Number) pair.component1()).doubleValue()), Boxing.boxDouble(((Number) pair.component2()).doubleValue())}, 2));
                                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                                sb.append(format);
                            } catch (IOException e) {
                                PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                                PlaceSearch.access$setLastError$p("no internet for OpenStreetMap search");
                                emptyList = CollectionsKt.emptyList();
                                return SearchPlus.augment(this.$ctx, this.$query, this.$near, emptyList);
                            } catch (Exception e2) {
                                e = e2;
                                PlaceSearch placeSearch4 = PlaceSearch.INSTANCE;
                                PlaceSearch.access$setLastError$p("OpenStreetMap suggestions failed (" + e.getMessage() + ")");
                                emptyList = CollectionsKt.emptyList();
                                return SearchPlus.augment(this.$ctx, this.$query, this.$near, emptyList);
                            }
                        } catch (IOException e3) {
                        } catch (Exception e4) {
                            e = e4;
                        }
                    } else {
                        coroutineScope = coroutineScope2;
                    }
                    try {
                        String sb2 = sb.toString();
                        ApiUsage.INSTANCE.record("OSM search (Photon / Nominatim, free)");
                        String access$httpGet = PlaceSearch.access$httpGet(PlaceSearch.INSTANCE, this.$ctx, sb2);
                        if (access$httpGet == null) {
                            emptyList = CollectionsKt.emptyList();
                        } else {
                            JSONArray optJSONArray = new JSONObject(access$httpGet).optJSONArray("features");
                            if (optJSONArray == null) {
                                optJSONArray = new JSONArray();
                            }
                            JSONArray jSONArray2 = optJSONArray;
                            int i = 0;
                            Iterable until = RangesKt.until(0, jSONArray2.length());
                            ArrayList arrayList2 = new ArrayList();
                            IntIterator it = until.iterator();
                            while (true) {
                                int i2 = i;
                                if (it.hasNext()) {
                                    int nextInt = it.nextInt();
                                    try {
                                        Result.Companion companion = Result.Companion;
                                        try {
                                            JSONObject jSONObject = jSONArray2.getJSONObject(nextInt);
                                            JSONObject jSONObject2 = jSONObject.getJSONObject("properties");
                                            str = sb2;
                                            try {
                                                jSONArray = jSONObject.getJSONObject("geometry").getJSONArray("coordinates");
                                                String[] strArr = new String[6];
                                                strArr[i2] = "street";
                                                strArr[1] = "district";
                                                strArr[2] = "city";
                                                strArr[3] = "county";
                                                strArr[4] = "state";
                                                strArr[5] = "country";
                                                List listOf = CollectionsKt.listOf(strArr);
                                                ArrayList arrayList3 = new ArrayList();
                                                Iterator it2 = listOf.iterator();
                                                while (it2.hasNext()) {
                                                    List list = listOf;
                                                    String optString = jSONObject2.optString((String) it2.next());
                                                    Intrinsics.checkNotNull(optString);
                                                    if (StringsKt.isBlank(optString)) {
                                                        optString = null;
                                                    }
                                                    if (optString != null) {
                                                        String str4 = optString;
                                                        str2 = obj3;
                                                        arrayList = arrayList3;
                                                        try {
                                                            arrayList.add(str4);
                                                        } catch (Throwable th) {
                                                            th = th;
                                                            try {
                                                                Result.Companion companion2 = Result.Companion;
                                                                obj2 = Result.constructor-impl(ResultKt.createFailure(th));
                                                                if (Result.isFailure-impl(obj2)) {
                                                                }
                                                                hit = (PlaceSearch.Hit) obj2;
                                                                if (hit != null) {
                                                                }
                                                                sb2 = str;
                                                                obj3 = str2;
                                                                i = 0;
                                                            } catch (IOException e5) {
                                                                PlaceSearch placeSearch32 = PlaceSearch.INSTANCE;
                                                                PlaceSearch.access$setLastError$p("no internet for OpenStreetMap search");
                                                                emptyList = CollectionsKt.emptyList();
                                                                return SearchPlus.augment(this.$ctx, this.$query, this.$near, emptyList);
                                                            } catch (Exception e6) {
                                                                e = e6;
                                                                PlaceSearch placeSearch42 = PlaceSearch.INSTANCE;
                                                                PlaceSearch.access$setLastError$p("OpenStreetMap suggestions failed (" + e.getMessage() + ")");
                                                                emptyList = CollectionsKt.emptyList();
                                                                return SearchPlus.augment(this.$ctx, this.$query, this.$near, emptyList);
                                                            }
                                                        }
                                                    } else {
                                                        str2 = obj3;
                                                        arrayList = arrayList3;
                                                    }
                                                    arrayList3 = arrayList;
                                                    obj3 = str2;
                                                    listOf = list;
                                                }
                                                str2 = obj3;
                                                distinct = CollectionsKt.distinct(arrayList3);
                                                String optString2 = jSONObject2.optString("name");
                                                if (StringsKt.isBlank(optString2)) {
                                                    String str5 = (String) CollectionsKt.firstOrNull(distinct);
                                                    if (str5 == null) {
                                                        str5 = "";
                                                    }
                                                    optString2 = str5;
                                                }
                                                str3 = optString2;
                                                Intrinsics.checkNotNull(str3);
                                            } catch (Throwable th2) {
                                                th = th2;
                                                str2 = obj3;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                            str = sb2;
                                            str2 = obj3;
                                        }
                                    } catch (Throwable th4) {
                                        th = th4;
                                        str = sb2;
                                        str2 = obj3;
                                    }
                                    if (!StringsKt.isBlank(str3) && jSONArray.length() >= 2) {
                                        double d = jSONArray.getDouble(1);
                                        double d2 = jSONArray.getDouble(i2);
                                        List list2 = distinct;
                                        ArrayList arrayList4 = new ArrayList();
                                        for (Object obj4 : list2) {
                                            List list3 = list2;
                                            if (!StringsKt.equals((String) obj4, str3, true)) {
                                                ArrayList arrayList5 = arrayList4;
                                                arrayList5.add(obj4);
                                                arrayList4 = arrayList5;
                                            }
                                            list2 = list3;
                                        }
                                        hit2 = new PlaceSearch.Hit(str3, d, d2, "OSM (Photon)", CollectionsKt.joinToString$default(arrayList4, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                                        obj2 = Result.constructor-impl(hit2);
                                        if (Result.isFailure-impl(obj2)) {
                                            obj2 = null;
                                        }
                                        hit = (PlaceSearch.Hit) obj2;
                                        if (hit != null) {
                                            arrayList2.add(hit);
                                        }
                                        sb2 = str;
                                        obj3 = str2;
                                        i = 0;
                                    }
                                    hit2 = null;
                                    obj2 = Result.constructor-impl(hit2);
                                    if (Result.isFailure-impl(obj2)) {
                                    }
                                    hit = (PlaceSearch.Hit) obj2;
                                    if (hit != null) {
                                    }
                                    sb2 = str;
                                    obj3 = str2;
                                    i = 0;
                                } else {
                                    ArrayList arrayList6 = arrayList2;
                                    boolean z = false;
                                    HashSet hashSet = new HashSet();
                                    ArrayList arrayList7 = new ArrayList();
                                    for (Object obj5 : arrayList6) {
                                        PlaceSearch.Hit hit3 = (PlaceSearch.Hit) obj5;
                                        String name = hit3.getName();
                                        Locale locale = Locale.ROOT;
                                        Intrinsics.checkNotNullExpressionValue(locale, "ROOT");
                                        String lowerCase = name.toLowerCase(locale);
                                        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                        ArrayList arrayList8 = arrayList6;
                                        String format2 = String.format(Locale.ROOT, "%.4f", Arrays.copyOf(new Object[]{Boxing.boxDouble(hit3.getLat())}, 1));
                                        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                                        boolean z2 = z;
                                        String format3 = String.format(Locale.ROOT, "%.4f", Arrays.copyOf(new Object[]{Boxing.boxDouble(hit3.getLng())}, 1));
                                        Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                                        if (hashSet.add(lowerCase + ":" + format2 + ":" + format3)) {
                                            arrayList7.add(obj5);
                                            arrayList6 = arrayList8;
                                            z = z2;
                                        } else {
                                            arrayList6 = arrayList8;
                                            z = z2;
                                        }
                                    }
                                    emptyList = arrayList7;
                                    if (emptyList.isEmpty()) {
                                        PlaceSearch placeSearch5 = PlaceSearch.INSTANCE;
                                        PlaceSearch.access$setLastError$p("no match");
                                    }
                                }
                            }
                        }
                    } catch (IOException e7) {
                    } catch (Exception e8) {
                        e = e8;
                    }
                } catch (IOException e9) {
                } catch (Exception e10) {
                    e = e10;
                }
                return SearchPlus.augment(this.$ctx, this.$query, this.$near, emptyList);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
