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
@DebugMetadata(c = "com.example.wayfindr.dash.nav.PlaceSearch$nominatim$2", f = "PlaceSearch.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class PlaceSearch$nominatim$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PlaceSearch.Hit>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Pair<Double, Double> $near;
    final /* synthetic */ String $q;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$nominatim$2(Context context, String str, Pair<Double, Double> pair, Continuation<? super PlaceSearch$nominatim$2> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$q = str;
        this.$near = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaceSearch$nominatim$2(this.$ctx, this.$q, this.$near, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PlaceSearch.Hit> continuation) {
        return ((PlaceSearch$nominatim$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        String str;
        String str2;
        String body;
        Object next;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    String str3 = this.$q;
                    Pair<Double, Double> pair = this.$near;
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://nominatim.openstreetmap.org/search").append("?format=jsonv2&limit=5&q=").append(URLEncoder.encode(str3, "UTF-8"));
                    sb.append("&accept-language=").append(Locale.getDefault().getLanguage());
                    if (pair != null) {
                        try {
                            double doubleValue = pair.component1().doubleValue();
                            double doubleValue2 = pair.component2().doubleValue();
                            str2 = null;
                            try {
                                String format = String.format(Locale.ROOT, "&viewbox=%.4f,%.4f,%.4f,%.4f", Arrays.copyOf(new Object[]{Boxing.boxDouble(doubleValue2 - 1.0d), Boxing.boxDouble(doubleValue + 1.0d), Boxing.boxDouble(doubleValue2 + 1.0d), Boxing.boxDouble(doubleValue - 1.0d)}, 4));
                                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                                sb.append(format);
                            } catch (IOException e) {
                                str = str2;
                                PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                                PlaceSearch.lastError = "no internet for OpenStreetMap search";
                                return str;
                            } catch (Exception e2) {
                                e = e2;
                                str = str2;
                                PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                                PlaceSearch.lastError = "OpenStreetMap search failed (" + e.getMessage() + ")";
                                return str;
                            }
                        } catch (IOException e3) {
                            str2 = null;
                        } catch (Exception e4) {
                            e = e4;
                            str2 = null;
                        }
                    } else {
                        str2 = null;
                    }
                    String url = sb.toString();
                    ApiUsage.INSTANCE.record(ApiUsage.OSM_SEARCH);
                    body = PlaceSearch.INSTANCE.httpGet(this.$ctx, url);
                    if (body == null) {
                        return str2;
                    }
                    JSONArray arr = new JSONArray(body);
                    Iterable until = RangesKt.until(0, arr.length());
                    Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                    Iterator<Integer> it = until.iterator();
                    while (it.hasNext()) {
                        arrayList.add(arr.getJSONObject(((IntIterator) it).nextInt()));
                    }
                    List results = (List) arrayList;
                    if (results.isEmpty()) {
                        PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                        PlaceSearch.lastError = "no match";
                        return str2;
                    }
                    Log.i("PlaceSearch", "Nominatim '" + this.$q + "': " + CollectionsKt.joinToString$default(results, " | ", null, null, 0, null, new Function1() { // from class: com.example.wayfindr.dash.nav.PlaceSearch$nominatim$2$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return PlaceSearch$nominatim$2.invokeSuspend$lambda$3((JSONObject) obj);
                        }
                    }, 30, null));
                    Iterable withIndex = CollectionsKt.withIndex(results);
                    String str4 = this.$q;
                    Iterator it2 = withIndex.iterator();
                    if (it2.hasNext()) {
                        next = it2.next();
                        if (it2.hasNext()) {
                            IndexedValue indexedValue = (IndexedValue) next;
                            int index = indexedValue.getIndex();
                            JSONObject jSONObject = (JSONObject) indexedValue.component2();
                            PlaceSearch placeSearch4 = PlaceSearch.INSTANCE;
                            String url2 = jSONObject.optString("display_name");
                            Intrinsics.checkNotNullExpressionValue(url2, "optString(...)");
                            int wordScore$app = (placeSearch4.wordScore$app(str4, url2) * 10) - index;
                            while (true) {
                                Object next2 = it2.next();
                                IndexedValue indexedValue2 = (IndexedValue) next2;
                                int index2 = indexedValue2.getIndex();
                                JSONObject jSONObject2 = (JSONObject) indexedValue2.component2();
                                String body2 = body;
                                PlaceSearch placeSearch5 = PlaceSearch.INSTANCE;
                                JSONArray arr2 = arr;
                                String optString = jSONObject2.optString("display_name");
                                Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                                int wordScore$app2 = (placeSearch5.wordScore$app(str4, optString) * 10) - index2;
                                if (wordScore$app < wordScore$app2) {
                                    wordScore$app = wordScore$app2;
                                    next = next2;
                                }
                                if (it2.hasNext()) {
                                    body = body2;
                                    arr = arr2;
                                }
                            }
                        }
                    } else {
                        next = str2;
                    }
                    Intrinsics.checkNotNull(next);
                    JSONObject r = (JSONObject) ((IndexedValue) next).getValue();
                    String optString2 = r.optString(HintConstants.AUTOFILL_HINT_NAME);
                    if (StringsKt.isBlank(optString2)) {
                        String optString3 = r.optString("display_name");
                        Intrinsics.checkNotNullExpressionValue(optString3, "optString(...)");
                        str = str2;
                        try {
                            optString2 = StringsKt.substringBefore$default(optString3, ',', str, 2, (Object) str);
                        } catch (IOException e5) {
                            PlaceSearch placeSearch6 = PlaceSearch.INSTANCE;
                            PlaceSearch.lastError = "no internet for OpenStreetMap search";
                            return str;
                        } catch (Exception e6) {
                            e = e6;
                            PlaceSearch placeSearch22 = PlaceSearch.INSTANCE;
                            PlaceSearch.lastError = "OpenStreetMap search failed (" + e.getMessage() + ")";
                            return str;
                        }
                    } else {
                        str = str2;
                    }
                    String str5 = optString2;
                    String str6 = this.$q;
                    if (StringsKt.isBlank(str5)) {
                        str5 = str6;
                    }
                    String name = str5;
                    Intrinsics.checkNotNull(name);
                    String string = r.getString("lat");
                    Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                    double parseDouble = Double.parseDouble(string);
                    String string2 = r.getString("lon");
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                    return new PlaceSearch.Hit(name, parseDouble, Double.parseDouble(string2), "OSM (Nominatim)", null, 16, null);
                } catch (IOException e7) {
                    str = null;
                } catch (Exception e8) {
                    e = e8;
                    str = null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final CharSequence invokeSuspend$lambda$3(JSONObject it) {
        String optString = it.optString("display_name");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        return StringsKt.take(optString, 60);
    }
}
