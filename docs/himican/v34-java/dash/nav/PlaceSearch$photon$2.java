package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.nav.PlaceSearch$photon$2$;
import com.example.wayfindr.data.ApiUsage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.IntIterator;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: PlaceSearch.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class PlaceSearch$photon$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Pair $near;
    final /* synthetic */ String $q;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$photon$2(Context context, String str, Pair pair, Continuation continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$q = str;
        this.$near = pair;
    }

    private static final String invokeSuspend$describe(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("properties");
        List listOf = CollectionsKt.listOf(new String[]{"name", "street", "district", "city", "state"});
        ArrayList arrayList = new ArrayList();
        Iterator it = listOf.iterator();
        while (it.hasNext()) {
            String optString = jSONObject2.optString((String) it.next());
            Intrinsics.checkNotNull(optString);
            if (StringsKt.isBlank(optString)) {
                optString = null;
            }
            if (optString != null) {
                arrayList.add(optString);
            }
        }
        return CollectionsKt.joinToString$default(arrayList, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    static final CharSequence invokeSuspend$lambda$6(JSONObject jSONObject) {
        Intrinsics.checkNotNull(jSONObject);
        return StringsKt.take(invokeSuspend$describe(jSONObject), 60);
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new PlaceSearch$photon$2(this.$ctx, this.$q, this.$near, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object obj2;
        Object next;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                Object enterHook = GPlus.enterHook(this.$ctx, this.$q, this.$near);
                if (enterHook != null) {
                    if (enterHook instanceof PlaceSearch.Hit) {
                        return enterHook;
                    }
                    return null;
                }
                try {
                    String str = this.$q;
                    Pair pair = this.$near;
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://photon.komoot.io/api/").append("?limit=10&location_bias_scale=0.6&q=").append(URLEncoder.encode(str, "UTF-8"));
                    if (pair != null) {
                        String format = String.format(Locale.ROOT, "&lat=%.5f&lon=%.5f", Arrays.copyOf(new Object[]{Boxing.boxDouble(((Number) pair.component1()).doubleValue()), Boxing.boxDouble(((Number) pair.component2()).doubleValue())}, 2));
                        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                        sb.append(format);
                    }
                    String sb2 = sb.toString();
                    ApiUsage.INSTANCE.record("OSM search (Photon / Nominatim, free)");
                    String access$httpGet = PlaceSearch.access$httpGet(PlaceSearch.INSTANCE, this.$ctx, sb2);
                    if (access$httpGet == null) {
                        return null;
                    }
                    JSONArray optJSONArray = new JSONObject(access$httpGet).optJSONArray("features");
                    if (optJSONArray == null) {
                        optJSONArray = new JSONArray();
                    }
                    Iterable until = RangesKt.until(0, optJSONArray.length());
                    char c = '\n';
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                    IntIterator it = until.iterator();
                    while (it.hasNext()) {
                        arrayList.add(optJSONArray.getJSONObject(it.nextInt()));
                        c = c;
                    }
                    ArrayList arrayList2 = arrayList;
                    if (arrayList2.isEmpty()) {
                        PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                        PlaceSearch.access$setLastError$p("no match");
                        return null;
                    }
                    Log.i("PlaceSearch", "Photon '" + this.$q + "': " + CollectionsKt.joinToString$default(arrayList2, " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new PlaceSearch$photon$2$.ExternalSyntheticLambda0(), 30, (Object) null));
                    Iterable withIndex = CollectionsKt.withIndex(arrayList2);
                    String str2 = this.$q;
                    Iterator it2 = withIndex.iterator();
                    if (it2.hasNext()) {
                        next = it2.next();
                        if (it2.hasNext()) {
                            IndexedValue indexedValue = (IndexedValue) next;
                            int component1 = indexedValue.component1();
                            JSONObject jSONObject = (JSONObject) indexedValue.component2();
                            PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                            Intrinsics.checkNotNull(jSONObject);
                            int wordScore$app = (placeSearch2.wordScore$app(str2, invokeSuspend$describe(jSONObject)) * 10) - component1;
                            while (true) {
                                Object next2 = it2.next();
                                IndexedValue indexedValue2 = (IndexedValue) next2;
                                int component12 = indexedValue2.component1();
                                JSONObject jSONObject2 = (JSONObject) indexedValue2.component2();
                                try {
                                    PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                                    Intrinsics.checkNotNull(jSONObject2);
                                    String str3 = sb2;
                                    int wordScore$app2 = (placeSearch3.wordScore$app(str2, invokeSuspend$describe(jSONObject2)) * 10) - component12;
                                    if (wordScore$app < wordScore$app2) {
                                        wordScore$app = wordScore$app2;
                                        next = next2;
                                    }
                                    if (it2.hasNext()) {
                                        sb2 = str3;
                                    }
                                } catch (IOException e) {
                                    obj2 = null;
                                    PlaceSearch placeSearch4 = PlaceSearch.INSTANCE;
                                    PlaceSearch.access$setLastError$p("no internet for OpenStreetMap search");
                                    return obj2;
                                } catch (Exception e2) {
                                    e = e2;
                                    obj2 = null;
                                    PlaceSearch placeSearch5 = PlaceSearch.INSTANCE;
                                    PlaceSearch.access$setLastError$p("OpenStreetMap search failed (" + e.getMessage() + ")");
                                    return obj2;
                                }
                            }
                        }
                    } else {
                        next = null;
                    }
                    Intrinsics.checkNotNull(next);
                    JSONObject jSONObject3 = (JSONObject) ((IndexedValue) next).getValue();
                    JSONArray jSONArray = jSONObject3.getJSONObject("geometry").getJSONArray("coordinates");
                    String optString = jSONObject3.getJSONObject("properties").optString("name");
                    if (StringsKt.isBlank(optString)) {
                        Intrinsics.checkNotNull(jSONObject3);
                        obj2 = null;
                        try {
                            optString = StringsKt.substringBefore$default(invokeSuspend$describe(jSONObject3), ',', (String) null, 2, (Object) null);
                        } catch (IOException e3) {
                            PlaceSearch placeSearch42 = PlaceSearch.INSTANCE;
                            PlaceSearch.access$setLastError$p("no internet for OpenStreetMap search");
                            return obj2;
                        } catch (Exception e4) {
                            e = e4;
                            PlaceSearch placeSearch52 = PlaceSearch.INSTANCE;
                            PlaceSearch.access$setLastError$p("OpenStreetMap search failed (" + e.getMessage() + ")");
                            return obj2;
                        }
                    } else {
                        obj2 = null;
                    }
                    String str4 = optString;
                    String str5 = this.$q;
                    if (StringsKt.isBlank(str4)) {
                        str4 = str5;
                    }
                    String str6 = str4;
                    Intrinsics.checkNotNull(str6);
                    return new PlaceSearch.Hit(str6, jSONArray.getDouble(1), jSONArray.getDouble(0), "OSM (Photon)", (String) null, 16, (DefaultConstructorMarker) null);
                } catch (IOException e5) {
                    obj2 = null;
                } catch (Exception e6) {
                    e = e6;
                    obj2 = null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
