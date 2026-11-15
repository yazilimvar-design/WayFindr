package com.example.wayfindr.dash.nav;

import com.google.android.gms.actions.SearchIntents;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.maplibre.android.style.layers.Property;

/* compiled from: PlaceSearch.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.PlaceSearch", f = "PlaceSearch.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {64, 65, 66}, m = "search", n = {"ctx", SearchIntents.EXTRA_QUERY, Property.SYMBOL_Z_ORDER_SOURCE, "near", "saved", "q", "ctx", SearchIntents.EXTRA_QUERY, Property.SYMBOL_Z_ORDER_SOURCE, "near", "saved", "q", "ctx", SearchIntents.EXTRA_QUERY, Property.SYMBOL_Z_ORDER_SOURCE, "near", "saved", "q"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
/* loaded from: classes9.dex */
final class PlaceSearch$search$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaceSearch this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$search$1(PlaceSearch placeSearch, Continuation<? super PlaceSearch$search$1> continuation) {
        super(continuation);
        this.this$0 = placeSearch;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.search(null, null, null, null, null, this);
    }
}
