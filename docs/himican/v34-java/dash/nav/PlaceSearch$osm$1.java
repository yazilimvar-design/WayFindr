package com.example.wayfindr.dash.nav;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: PlaceSearch.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.PlaceSearch", f = "PlaceSearch.kt", i = {0, 0, 0, 1, 1, 1}, l = {163, 163}, m = "osm", n = {"ctx", "q", "near", "ctx", "q", "near"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* loaded from: classes9.dex */
final class PlaceSearch$osm$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaceSearch this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$osm$1(PlaceSearch placeSearch, Continuation<? super PlaceSearch$osm$1> continuation) {
        super(continuation);
        this.this$0 = placeSearch;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object osm;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        osm = this.this$0.osm(null, null, null, this);
        return osm;
    }
}
