package com.example.wayfindr.util;

import com.example.wayfindr.data.SharedLocation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LocationParser.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/wayfindr/data/SharedLocation;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.util.LocationParser$resolveStops$2", f = "LocationParser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class LocationParser$resolveStops$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SharedLocation>>, Object> {
    final /* synthetic */ String $url;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LocationParser$resolveStops$2(String str, Continuation<? super LocationParser$resolveStops$2> continuation) {
        super(2, continuation);
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LocationParser$resolveStops$2(this.$url, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SharedLocation>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<SharedLocation>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<SharedLocation>> continuation) {
        return ((LocationParser$resolveStops$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Pair fetchFollowing;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                fetchFollowing = LocationParser.INSTANCE.fetchFollowing(this.$url);
                String finalUrl = (String) fetchFollowing.component1();
                String body = (String) fetchFollowing.component2();
                Iterable listOf = CollectionsKt.listOf((Object[]) new String[]{this.$url, finalUrl, body});
                LocationParser locationParser = LocationParser.INSTANCE;
                Collection arrayList = new ArrayList();
                Iterator it = listOf.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList, locationParser.parseStops((String) it.next()));
                }
                Iterable iterable = (List) arrayList;
                HashSet hashSet = new HashSet();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : iterable) {
                    SharedLocation sharedLocation = (SharedLocation) obj;
                    if (hashSet.add(sharedLocation.getLat() + "," + sharedLocation.getLng() + ":" + sharedLocation.getName())) {
                        arrayList2.add(obj);
                    }
                }
                return CollectionsKt.take(arrayList2, 26);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
