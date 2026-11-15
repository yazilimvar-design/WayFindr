package com.example.wayfindr.viewmodel;

import android.util.Log;
import com.example.wayfindr.data.SharedLocation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$loadSharedStops$1", f = "RouteViewModel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0}, l = {419}, m = "invokeSuspend", n = {"$this$map\\1", "$this$mapTo\\2", "destination\\2", "item\\2", "stop\\3", "$i$f$map\\1\\417", "$i$f$mapTo\\2\\497", "$i$a$-map-RouteViewModel$loadSharedStops$1$resolved$1\\3\\499\\0"}, s = {"L$0", "L$2", "L$3", "L$5", "L$6", "I$0", "I$1", "I$2"})
/* loaded from: classes4.dex */
final class RouteViewModel$loadSharedStops$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<SharedLocation> $stops;
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$loadSharedStops$1(List<SharedLocation> list, RouteViewModel routeViewModel, Continuation<? super RouteViewModel$loadSharedStops$1> continuation) {
        super(2, continuation);
        this.$stops = list;
        this.this$0 = routeViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteViewModel$loadSharedStops$1(this.$stops, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteViewModel$loadSharedStops$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x00f0, code lost:
    
        if (r7 == null) goto L25;
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x00bb  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:20:0x0082 -> B:13:0x0103). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00b5 -> B:7:0x00b7). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        int i;
        ArrayList arrayList;
        Iterator it;
        Iterable iterable;
        RouteViewModel routeViewModel;
        Iterable iterable2;
        int i2;
        Object obj;
        String str;
        Object geocode;
        SharedLocation sharedLocation;
        Collection collection;
        SharedLocation sharedLocation2;
        Pair pair;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Iterable iterable3 = this.$stops;
                RouteViewModel routeViewModel2 = this.this$0;
                i = 0;
                arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
                it = iterable3.iterator();
                iterable = iterable3;
                routeViewModel = routeViewModel2;
                iterable2 = iterable3;
                i2 = 0;
                obj = coroutine_suspended;
                boolean complete = true;
                if (!it.hasNext()) {
                    Object next = it.next();
                    SharedLocation sharedLocation3 = (SharedLocation) next;
                    if (sharedLocation3.getLat() == null || sharedLocation3.getLng() == null) {
                        String name = sharedLocation3.getName();
                        this.L$0 = SpillingKt.nullOutSpilledVariable(iterable);
                        this.L$1 = routeViewModel;
                        this.L$2 = SpillingKt.nullOutSpilledVariable(iterable2);
                        this.L$3 = arrayList;
                        this.L$4 = it;
                        this.L$5 = SpillingKt.nullOutSpilledVariable(next);
                        this.L$6 = sharedLocation3;
                        this.L$7 = arrayList;
                        this.I$0 = i;
                        this.I$1 = i2;
                        this.I$2 = 0;
                        this.label = 1;
                        geocode = routeViewModel.geocode(name, this);
                        if (geocode == obj) {
                            return obj;
                        }
                        sharedLocation = sharedLocation3;
                        collection = arrayList;
                        pair = (Pair) geocode;
                        if (pair == null) {
                            pair = routeViewModel.savedByName(sharedLocation.getName());
                        }
                        if (pair != null) {
                            sharedLocation2 = SharedLocation.copy$default(sharedLocation, null, Boxing.boxDouble(((Number) pair.component1()).doubleValue()), Boxing.boxDouble(((Number) pair.component2()).doubleValue()), null, false, 9, null);
                            break;
                        }
                        sharedLocation2 = SharedLocation.copy$default(sharedLocation, null, null, null, null, false, 15, null);
                        arrayList.add(sharedLocation2);
                        arrayList = collection;
                        boolean complete2 = true;
                        if (!it.hasNext()) {
                            List resolved = (List) arrayList;
                            List list = resolved;
                            if (!(list instanceof Collection) || !list.isEmpty()) {
                                Iterator it2 = list.iterator();
                                while (true) {
                                    if (it2.hasNext()) {
                                        SharedLocation sharedLocation4 = (SharedLocation) it2.next();
                                        if (((sharedLocation4.getLat() == null || sharedLocation4.getLng() == null) ? null : 1) == null) {
                                            complete2 = false;
                                        }
                                    }
                                }
                            }
                            this.this$0._state.setValue(RouteState.copy$default((RouteState) this.this$0._state.getValue(), (SharedLocation) CollectionsKt.last(resolved), resolved, null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131036, null));
                            if (complete2) {
                                this.this$0.computeRoute(resolved);
                            } else {
                                str = this.this$0.TAG;
                                Boxing.boxInt(Log.w(str, "Could not resolve every shared route stop"));
                            }
                            return Unit.INSTANCE;
                        }
                    } else {
                        sharedLocation2 = sharedLocation3;
                        collection = arrayList;
                        arrayList.add(sharedLocation2);
                        arrayList = collection;
                        boolean complete22 = true;
                        if (!it.hasNext()) {
                        }
                    }
                }
                break;
            case 1:
                int i3 = this.I$2;
                i2 = this.I$1;
                i = this.I$0;
                arrayList = (Collection) this.L$7;
                SharedLocation sharedLocation5 = (SharedLocation) this.L$6;
                Object obj2 = this.L$5;
                it = (Iterator) this.L$4;
                collection = (Collection) this.L$3;
                iterable2 = (Iterable) this.L$2;
                routeViewModel = (RouteViewModel) this.L$1;
                iterable = (Iterable) this.L$0;
                ResultKt.throwOnFailure($result);
                sharedLocation = sharedLocation5;
                geocode = $result;
                obj = coroutine_suspended;
                pair = (Pair) geocode;
                if (pair == null) {
                }
                if (pair != null) {
                }
                sharedLocation2 = SharedLocation.copy$default(sharedLocation, null, null, null, null, false, 15, null);
                arrayList.add(sharedLocation2);
                arrayList = collection;
                boolean complete222 = true;
                if (!it.hasNext()) {
                }
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
