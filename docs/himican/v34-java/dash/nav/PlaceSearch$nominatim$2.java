package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.util.Log;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.dash.nav.PlaceSearch$nominatim$2$;
import com.example.wayfindr.data.ApiUsage;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
final class PlaceSearch$nominatim$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Pair $near;
    final /* synthetic */ String $q;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$nominatim$2(Context context, String str, Pair pair, Continuation continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$q = str;
        this.$near = pair;
    }

    static final CharSequence invokeSuspend$lambda$3(JSONObject jSONObject) {
        String optString = jSONObject.optString("display_name");
        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
        return StringsKt.take(optString, 60);
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new PlaceSearch$nominatim$2(this.$ctx, this.$q, this.$near, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        String str3;
        String access$httpGet;
        Object next;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                if (GPlus.command(this.$ctx, this.$q) != 0) {
                    return null;
                }
                try {
                    String str4 = this.$q;
                    Pair pair = this.$near;
                    StringBuilder sb = new StringBuilder();
                    sb.append("https://nominatim.openstreetmap.org/search").append("?format=jsonv2&limit=10&q=").append(URLEncoder.encode(str4, "UTF-8"));
                    sb.append("&accept-language=").append(Locale.getDefault().getLanguage());
                    if (pair != null) {
                        try {
                            double doubleValue = ((Number) pair.component1()).doubleValue();
                            double doubleValue2 = ((Number) pair.component2()).doubleValue();
                            str3 = null;
                            try {
                                String format = String.format(Locale.ROOT, "&viewbox=%.4f,%.4f,%.4f,%.4f", Arrays.copyOf(new Object[]{Boxing.boxDouble(doubleValue2 - 1.0d), Boxing.boxDouble(doubleValue + 1.0d), Boxing.boxDouble(doubleValue2 + 1.0d), Boxing.boxDouble(doubleValue - 1.0d)}, 4));
                                Intrinsics.checkNotNullExpressionValue(format, "format(...)");
                                sb.append(format);
                            } catch (IOException e) {
                                str = str3;
                                PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                                PlaceSearch.access$setLastError$p("no internet for OpenStreetMap search");
                                str2 = str;
                                return SearchPlus.fallback(str2, this.$ctx, this.$q, this.$near);
                            } catch (Exception e2) {
                                e = e2;
                                str = str3;
                                PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                                PlaceSearch.access$setLastError$p("OpenStreetMap search failed (" + e.getMessage() + ")");
                                str2 = str;
                                return SearchPlus.fallback(str2, this.$ctx, this.$q, this.$near);
                            }
                        } catch (IOException e3) {
                            str3 = null;
                        } catch (Exception e4) {
                            e = e4;
                            str3 = null;
                        }
                    } else {
                        str3 = null;
                    }
                    String sb2 = sb.toString();
                    ApiUsage.INSTANCE.record("OSM search (Photon / Nominatim, free)");
                    access$httpGet = PlaceSearch.access$httpGet(PlaceSearch.INSTANCE, this.$ctx, sb2);
                } catch (IOException e5) {
                    str = null;
                } catch (Exception e6) {
                    e = e6;
                    str = null;
                }
                if (access$httpGet == null) {
                    return SearchPlus.fallback(str3, this.$ctx, this.$q, this.$near);
                }
                JSONArray jSONArray = new JSONArray(access$httpGet);
                Iterable until = RangesKt.until(0, jSONArray.length());
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
                IntIterator it = until.iterator();
                while (it.hasNext()) {
                    arrayList.add(jSONArray.getJSONObject(it.nextInt()));
                }
                ArrayList arrayList2 = arrayList;
                if (arrayList2.isEmpty()) {
                    PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                    PlaceSearch.access$setLastError$p("no match");
                    return SearchPlus.fallback(str3, this.$ctx, this.$q, this.$near);
                }
                Log.i("PlaceSearch", "Nominatim '" + this.$q + "': " + CollectionsKt.joinToString$default(arrayList2, " | ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new PlaceSearch$nominatim$2$.ExternalSyntheticLambda0(), 30, (Object) null));
                Iterable withIndex = CollectionsKt.withIndex(arrayList2);
                String str5 = this.$q;
                Iterator it2 = withIndex.iterator();
                if (it2.hasNext()) {
                    next = it2.next();
                    if (it2.hasNext()) {
                        IndexedValue indexedValue = (IndexedValue) next;
                        int component1 = indexedValue.component1();
                        JSONObject jSONObject = (JSONObject) indexedValue.component2();
                        PlaceSearch placeSearch4 = PlaceSearch.INSTANCE;
                        String optString = jSONObject.optString("display_name");
                        Intrinsics.checkNotNullExpressionValue(optString, "optString(...)");
                        int wordScore$app = (placeSearch4.wordScore$app(str5, optString) * 10) - component1;
                        while (true) {
                            Object next2 = it2.next();
                            IndexedValue indexedValue2 = (IndexedValue) next2;
                            int component12 = indexedValue2.component1();
                            JSONObject jSONObject2 = (JSONObject) indexedValue2.component2();
                            String str6 = access$httpGet;
                            PlaceSearch placeSearch5 = PlaceSearch.INSTANCE;
                            JSONArray jSONArray2 = jSONArray;
                            String optString2 = jSONObject2.optString("display_name");
                            Intrinsics.checkNotNullExpressionValue(optString2, "optString(...)");
                            int wordScore$app2 = (placeSearch5.wordScore$app(str5, optString2) * 10) - component12;
                            if (wordScore$app < wordScore$app2) {
                                wordScore$app = wordScore$app2;
                                next = next2;
                            }
                            if (it2.hasNext()) {
                                access$httpGet = str6;
                                jSONArray = jSONArray2;
                            }
                        }
                    }
                } else {
                    next = str3;
                }
                Intrinsics.checkNotNull(next);
                JSONObject jSONObject3 = (JSONObject) ((IndexedValue) next).getValue();
                String optString3 = jSONObject3.optString("name");
                if (StringsKt.isBlank(optString3)) {
                    String optString4 = jSONObject3.optString("display_name");
                    Intrinsics.checkNotNullExpressionValue(optString4, "optString(...)");
                    str = str3;
                    try {
                        optString3 = StringsKt.substringBefore$default(optString4, ',', str, 2, str);
                    } catch (IOException e7) {
                        PlaceSearch placeSearch6 = PlaceSearch.INSTANCE;
                        PlaceSearch.access$setLastError$p("no internet for OpenStreetMap search");
                        str2 = str;
                        return SearchPlus.fallback(str2, this.$ctx, this.$q, this.$near);
                    } catch (Exception e8) {
                        e = e8;
                        PlaceSearch placeSearch22 = PlaceSearch.INSTANCE;
                        PlaceSearch.access$setLastError$p("OpenStreetMap search failed (" + e.getMessage() + ")");
                        str2 = str;
                        return SearchPlus.fallback(str2, this.$ctx, this.$q, this.$near);
                    }
                } else {
                    str = str3;
                }
                String str7 = optString3;
                String str8 = this.$q;
                if (StringsKt.isBlank(str7)) {
                    str7 = str8;
                }
                String str9 = str7;
                Intrinsics.checkNotNull(str9);
                String string = jSONObject3.getString("lat");
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                double parseDouble = Double.parseDouble(string);
                String string2 = jSONObject3.getString("lon");
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                str2 = new PlaceSearch.Hit(str9, parseDouble, Double.parseDouble(string2), "OSM (Nominatim)", (String) null, 16, (DefaultConstructorMarker) null);
                return SearchPlus.fallback(str2, this.$ctx, this.$q, this.$near);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
