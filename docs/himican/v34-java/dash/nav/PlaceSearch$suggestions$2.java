package com.example.wayfindr.dash.nav;

import android.content.Context;
import androidx.autofill.HintConstants;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.data.ApiUsage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PlaceSearch.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/wayfindr/dash/nav/PlaceSearch$Hit;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.PlaceSearch$suggestions$2", f = "PlaceSearch.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class PlaceSearch$suggestions$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends PlaceSearch.Hit>>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Pair<Double, Double> $near;
    final /* synthetic */ String $query;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$suggestions$2(String str, Context context, Pair<Double, Double> pair, Continuation<? super PlaceSearch$suggestions$2> continuation) {
        super(2, continuation);
        this.$query = str;
        this.$ctx = context;
        this.$near = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlaceSearch$suggestions$2 placeSearch$suggestions$2 = new PlaceSearch$suggestions$2(this.$query, this.$ctx, this.$near, continuation);
        placeSearch$suggestions$2.L$0 = obj;
        return placeSearch$suggestions$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends PlaceSearch.Hit>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<PlaceSearch.Hit>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<PlaceSearch.Hit>> continuation) {
        return ((PlaceSearch$suggestions$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02ee A[Catch: Exception -> 0x03bb, IOException -> 0x03bd, TryCatch #10 {IOException -> 0x03bd, Exception -> 0x03bb, blocks: (B:58:0x02d9, B:59:0x02e3, B:62:0x02ea, B:64:0x02ee, B:113:0x02fb, B:114:0x0316, B:116:0x031c, B:123:0x0394, B:126:0x03a3, B:128:0x03b2), top: B:57:0x02d9 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x02f2 A[SYNTHETIC] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        CoroutineScope $this$withContext;
        String body;
        String url;
        String q;
        Object obj;
        PlaceSearch.Hit hit;
        JSONArray jSONArray;
        List distinct;
        String str;
        PlaceSearch.Hit hit2;
        Collection collection;
        CoroutineScope $this$withContext2 = (CoroutineScope) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                String q2 = StringsKt.trim((CharSequence) this.$query).toString();
                if (q2.length() < 2) {
                    return CollectionsKt.emptyList();
                }
                PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                PlaceSearch.lastError = null;
                try {
                    Pair<Double, Double> pair = this.$near;
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://photon.komoot.io/api/").append("?limit=7&q=").append(URLEncoder.encode(q2, "UTF-8"));
                    StringBuilder append = sb.append("&lang=");
                    PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                    String language = Locale.getDefault().getLanguage();
                    Intrinsics.checkNotNullExpressionValue(language, "getLanguage(...)");
                    append.append(placeSearch2.photonLanguageCode$app(language));
                    if (pair != null) {
                        try {
                            $this$withContext = $this$withContext2;
                        } catch (IOException e) {
                        } catch (Exception e2) {
                            e = e2;
                        }
                        try {
                            String format = String.format(Locale.ROOT, "&lat=%.5f&lon=%.5f", Arrays.copyOf(new Object[]{Boxing.boxDouble(pair.component1().doubleValue()), Boxing.boxDouble(pair.component2().doubleValue())}, 2));
                            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                            sb.append(format);
                        } catch (IOException e3) {
                            PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                            PlaceSearch.lastError = "no internet for OpenStreetMap search";
                            return CollectionsKt.emptyList();
                        } catch (Exception e4) {
                            e = e4;
                            PlaceSearch placeSearch4 = PlaceSearch.INSTANCE;
                            PlaceSearch.lastError = "OpenStreetMap suggestions failed (" + e.getMessage() + ")";
                            return CollectionsKt.emptyList();
                        }
                    } else {
                        $this$withContext = $this$withContext2;
                    }
                    try {
                        String url2 = sb.toString();
                        ApiUsage.INSTANCE.record(ApiUsage.OSM_SEARCH);
                        body = PlaceSearch.INSTANCE.httpGet(this.$ctx, url2);
                        if (body == null) {
                            return CollectionsKt.emptyList();
                        }
                        JSONArray optJSONArray = new JSONObject(body).optJSONArray("features");
                        if (optJSONArray == null) {
                            optJSONArray = new JSONArray();
                        }
                        JSONArray features = optJSONArray;
                        int i = 0;
                        Iterable until = RangesKt.until(0, features.length());
                        Collection arrayList = new ArrayList();
                        Iterator<Integer> it = until.iterator();
                        while (true) {
                            int i2 = i;
                            if (!it.hasNext()) {
                                Iterable iterable = (List) arrayList;
                                int i3 = 0;
                                HashSet hashSet = new HashSet();
                                ArrayList arrayList2 = new ArrayList();
                                for (Object obj2 : iterable) {
                                    PlaceSearch.Hit hit3 = (PlaceSearch.Hit) obj2;
                                    String name = hit3.getName();
                                    Locale ROOT = Locale.ROOT;
                                    Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
                                    String lowerCase = name.toLowerCase(ROOT);
                                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                                    Iterable iterable2 = iterable;
                                    String format2 = String.format(Locale.ROOT, "%.4f", Arrays.copyOf(new Object[]{Boxing.boxDouble(hit3.getLat())}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
                                    int i4 = i3;
                                    String format3 = String.format(Locale.ROOT, "%.4f", Arrays.copyOf(new Object[]{Boxing.boxDouble(hit3.getLng())}, 1));
                                    Intrinsics.checkNotNullExpressionValue(format3, "format(...)");
                                    if (hashSet.add(lowerCase + ":" + format2 + ":" + format3)) {
                                        arrayList2.add(obj2);
                                        iterable = iterable2;
                                        i3 = i4;
                                    } else {
                                        iterable = iterable2;
                                        i3 = i4;
                                    }
                                }
                                ArrayList hits = arrayList2;
                                if (!hits.isEmpty()) {
                                    return hits;
                                }
                                PlaceSearch placeSearch5 = PlaceSearch.INSTANCE;
                                PlaceSearch.lastError = "no match";
                                return hits;
                            }
                            int nextInt = ((IntIterator) it).nextInt();
                            try {
                                Result.Companion companion = Result.INSTANCE;
                                try {
                                    JSONObject jSONObject = features.getJSONObject(nextInt);
                                    JSONObject jSONObject2 = jSONObject.getJSONObject("properties");
                                    url = url2;
                                    try {
                                        jSONArray = jSONObject.getJSONObject("geometry").getJSONArray("coordinates");
                                        String[] strArr = new String[6];
                                        strArr[i2] = "street";
                                        strArr[1] = "district";
                                        strArr[2] = "city";
                                        strArr[3] = "county";
                                        strArr[4] = "state";
                                        strArr[5] = "country";
                                        Iterable listOf = CollectionsKt.listOf((Object[]) strArr);
                                        Collection arrayList3 = new ArrayList();
                                        Iterator it2 = listOf.iterator();
                                        while (it2.hasNext()) {
                                            Iterable iterable3 = listOf;
                                            String optString = jSONObject2.optString((String) it2.next());
                                            Intrinsics.checkNotNull(optString);
                                            if (StringsKt.isBlank(optString)) {
                                                optString = null;
                                            }
                                            if (optString != null) {
                                                String str2 = optString;
                                                q = q2;
                                                collection = arrayList3;
                                                try {
                                                    collection.add(str2);
                                                } catch (Throwable th) {
                                                    th = th;
                                                    try {
                                                        Result.Companion companion2 = Result.INSTANCE;
                                                        obj = Result.m8392constructorimpl(ResultKt.createFailure(th));
                                                        if (Result.m8398isFailureimpl(obj)) {
                                                        }
                                                        hit = (PlaceSearch.Hit) obj;
                                                        if (hit != null) {
                                                        }
                                                        url2 = url;
                                                        q2 = q;
                                                        i = 0;
                                                    } catch (IOException e5) {
                                                        PlaceSearch placeSearch32 = PlaceSearch.INSTANCE;
                                                        PlaceSearch.lastError = "no internet for OpenStreetMap search";
                                                        return CollectionsKt.emptyList();
                                                    } catch (Exception e6) {
                                                        e = e6;
                                                        PlaceSearch placeSearch42 = PlaceSearch.INSTANCE;
                                                        PlaceSearch.lastError = "OpenStreetMap suggestions failed (" + e.getMessage() + ")";
                                                        return CollectionsKt.emptyList();
                                                    }
                                                }
                                            } else {
                                                q = q2;
                                                collection = arrayList3;
                                            }
                                            arrayList3 = collection;
                                            q2 = q;
                                            listOf = iterable3;
                                        }
                                        q = q2;
                                        distinct = CollectionsKt.distinct((List) arrayList3);
                                        String optString2 = jSONObject2.optString(HintConstants.AUTOFILL_HINT_NAME);
                                        if (StringsKt.isBlank(optString2)) {
                                            String str3 = (String) CollectionsKt.firstOrNull(distinct);
                                            if (str3 == null) {
                                                str3 = "";
                                            }
                                            optString2 = str3;
                                        }
                                        str = optString2;
                                        Intrinsics.checkNotNull(str);
                                    } catch (Throwable th2) {
                                        th = th2;
                                        q = q2;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    url = url2;
                                    q = q2;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                url = url2;
                                q = q2;
                            }
                            if (!StringsKt.isBlank(str) && jSONArray.length() >= 2) {
                                double d = jSONArray.getDouble(1);
                                double d2 = jSONArray.getDouble(i2);
                                List list = distinct;
                                Collection arrayList4 = new ArrayList();
                                for (Object obj3 : list) {
                                    Collection collection2 = list;
                                    if (!StringsKt.equals((String) obj3, str, true)) {
                                        Collection collection3 = arrayList4;
                                        collection3.add(obj3);
                                        arrayList4 = collection3;
                                    }
                                    list = collection2;
                                }
                                hit2 = new PlaceSearch.Hit(str, d, d2, "OSM (Photon)", CollectionsKt.joinToString$default((List) arrayList4, ", ", null, null, 0, null, null, 62, null));
                                obj = Result.m8392constructorimpl(hit2);
                                if (Result.m8398isFailureimpl(obj)) {
                                    obj = null;
                                }
                                hit = (PlaceSearch.Hit) obj;
                                if (hit != null) {
                                    arrayList.add(hit);
                                }
                                url2 = url;
                                q2 = q;
                                i = 0;
                            }
                            hit2 = null;
                            obj = Result.m8392constructorimpl(hit2);
                            if (Result.m8398isFailureimpl(obj)) {
                            }
                            hit = (PlaceSearch.Hit) obj;
                            if (hit != null) {
                            }
                            url2 = url;
                            q2 = q;
                            i = 0;
                        }
                    } catch (IOException e7) {
                    } catch (Exception e8) {
                        e = e8;
                    }
                } catch (IOException e9) {
                } catch (Exception e10) {
                    e = e10;
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
