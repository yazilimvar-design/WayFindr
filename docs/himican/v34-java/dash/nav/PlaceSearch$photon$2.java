package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import androidx.autofill.HintConstants;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.data.ApiUsage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
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
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/wayfindr/dash/nav/PlaceSearch$Hit;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.PlaceSearch$photon$2", f = "PlaceSearch.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class PlaceSearch$photon$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PlaceSearch.Hit>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Pair<Double, Double> $near;
    final /* synthetic */ String $q;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$photon$2(Context context, String str, Pair<Double, Double> pair, Continuation<? super PlaceSearch$photon$2> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$q = str;
        this.$near = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaceSearch$photon$2(this.$ctx, this.$q, this.$near, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PlaceSearch.Hit> continuation) {
        return ((PlaceSearch$photon$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object obj;
        String body;
        Object next;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    String str = this.$q;
                    Pair<Double, Double> pair = this.$near;
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://photon.komoot.io/api/").append("?limit=5&q=").append(URLEncoder.encode(str, "UTF-8"));
                    if (pair != null) {
                        String format = String.format(Locale.ROOT, "&lat=%.5f&lon=%.5f", Arrays.copyOf(new Object[]{Boxing.boxDouble(pair.component1().doubleValue()), Boxing.boxDouble(pair.component2().doubleValue())}, 2));
                        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                        sb.append(format);
                    }
                    String url = sb.toString();
                    ApiUsage.INSTANCE.record(ApiUsage.OSM_SEARCH);
                    body = PlaceSearch.INSTANCE.httpGet(this.$ctx, url);
                    if (body == null) {
                        return null;
                    }
                    JSONArray features = new JSONObject(body).optJSONArray("features");
                    if (features == null) {
                        features = new JSONArray();
                    }
                    Iterable until = RangesKt.until(0, features.length());
                    char c = '\n';
                    Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                    Iterator<Integer> it = until.iterator();
                    while (it.hasNext()) {
                        arrayList.add(features.getJSONObject(((IntIterator) it).nextInt()));
                        c = c;
                    }
                    List results = (List) arrayList;
                    if (results.isEmpty()) {
                        PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                        PlaceSearch.lastError = "no match";
                        return null;
                    }
                    Log.i("PlaceSearch", "Photon '" + this.$q + "': " + CollectionsKt.joinToString$default(results, " | ", null, null, 0, null, new Function1() { // from class: com.example.wayfindr.dash.nav.PlaceSearch$photon$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return PlaceSearch$photon$2.invokeSuspend$lambda$6((JSONObject) obj2);
                        }
                    }, 30, null));
                    Iterable withIndex = CollectionsKt.withIndex(results);
                    String str2 = this.$q;
                    Iterator it2 = withIndex.iterator();
                    if (it2.hasNext()) {
                        next = it2.next();
                        if (it2.hasNext()) {
                            IndexedValue indexedValue = (IndexedValue) next;
                            int index = indexedValue.getIndex();
                            JSONObject jSONObject = (JSONObject) indexedValue.component2();
                            PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                            Intrinsics.checkNotNull(jSONObject);
                            int wordScore$app = (placeSearch2.wordScore$app(str2, invokeSuspend$describe(jSONObject)) * 10) - index;
                            while (true) {
                                Object next2 = it2.next();
                                IndexedValue indexedValue2 = (IndexedValue) next2;
                                int index2 = indexedValue2.getIndex();
                                JSONObject jSONObject2 = (JSONObject) indexedValue2.component2();
                                try {
                                    PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                                    Intrinsics.checkNotNull(jSONObject2);
                                    String url2 = url;
                                    int wordScore$app2 = (placeSearch3.wordScore$app(str2, invokeSuspend$describe(jSONObject2)) * 10) - index2;
                                    if (wordScore$app < wordScore$app2) {
                                        wordScore$app = wordScore$app2;
                                        next = next2;
                                    }
                                    if (it2.hasNext()) {
                                        url = url2;
                                    }
                                } catch (IOException e) {
                                    obj = null;
                                    PlaceSearch placeSearch4 = PlaceSearch.INSTANCE;
                                    PlaceSearch.lastError = "no internet for OpenStreetMap search";
                                    return obj;
                                } catch (Exception e2) {
                                    e = e2;
                                    obj = null;
                                    PlaceSearch placeSearch5 = PlaceSearch.INSTANCE;
                                    PlaceSearch.lastError = "OpenStreetMap search failed (" + e.getMessage() + ")";
                                    return obj;
                                }
                            }
                        }
                    } else {
                        next = null;
                    }
                    Intrinsics.checkNotNull(next);
                    JSONObject f = (JSONObject) ((IndexedValue) next).getValue();
                    JSONArray c2 = f.getJSONObject("geometry").getJSONArray("coordinates");
                    String optString = f.getJSONObject("properties").optString(HintConstants.AUTOFILL_HINT_NAME);
                    if (StringsKt.isBlank(optString)) {
                        Intrinsics.checkNotNull(f);
                        obj = null;
                        try {
                            optString = StringsKt.substringBefore$default(invokeSuspend$describe(f), ',', (String) null, 2, (Object) null);
                        } catch (IOException e3) {
                            PlaceSearch placeSearch42 = PlaceSearch.INSTANCE;
                            PlaceSearch.lastError = "no internet for OpenStreetMap search";
                            return obj;
                        } catch (Exception e4) {
                            e = e4;
                            PlaceSearch placeSearch52 = PlaceSearch.INSTANCE;
                            PlaceSearch.lastError = "OpenStreetMap search failed (" + e.getMessage() + ")";
                            return obj;
                        }
                    } else {
                        obj = null;
                    }
                    String str3 = optString;
                    String str4 = this.$q;
                    if (StringsKt.isBlank(str3)) {
                        str3 = str4;
                    }
                    String name = str3;
                    Intrinsics.checkNotNull(name);
                    return new PlaceSearch.Hit(name, c2.getDouble(1), c2.getDouble(0), "OSM (Photon)", null, 16, null);
                } catch (IOException e5) {
                    obj = null;
                } catch (Exception e6) {
                    e = e6;
                    obj = null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    private static final String invokeSuspend$describe(JSONObject f) {
        JSONObject jSONObject = f.getJSONObject("properties");
        Iterable listOf = CollectionsKt.listOf((Object[]) new String[]{HintConstants.AUTOFILL_HINT_NAME, "street", "district", "city", "state"});
        Collection arrayList = new ArrayList();
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            String optString = jSONObject.optString((String) it.next());
            Intrinsics.checkNotNull(optString);
            if (StringsKt.isBlank(optString)) {
                optString = null;
            }
            if (optString != null) {
                arrayList.add(optString);
            }
        }
        return CollectionsKt.joinToString$default((List) arrayList, ", ", null, null, 0, null, null, 62, null);
    }

    static final CharSequence invokeSuspend$lambda$6(JSONObject it) {
        Intrinsics.checkNotNull(it);
        return StringsKt.take(invokeSuspend$describe(it), 60);
    }
}
