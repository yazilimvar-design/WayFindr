package com.example.wayfindr.viewmodel;

import android.util.Log;
import androidx.autofill.HintConstants;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.util.LocationParser;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$handleSharedText$2", f = "RouteViewModel.kt", i = {1, 2, 2, 2, 2, 2, 2}, l = {185, 190, 199}, m = "invokeSuspend", n = {"expandedStops", "expandedStops", "urlCoords", "resolvedName", HintConstants.AUTOFILL_HINT_NAME, "it\\1", "$i$a$-let-RouteViewModel$handleSharedText$2$coords$1\\1\\199\\0"}, s = {"L$0", "L$0", "L$1", "L$2", "L$3", "L$4", "I$0"})
/* loaded from: classes4.dex */
final class RouteViewModel$handleSharedText$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SharedLocation $loc;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$handleSharedText$2(SharedLocation sharedLocation, RouteViewModel routeViewModel, Continuation<? super RouteViewModel$handleSharedText$2> continuation) {
        super(2, continuation);
        this.$loc = sharedLocation;
        this.this$0 = routeViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteViewModel$handleSharedText$2(this.$loc, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteViewModel$handleSharedText$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x011b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Object resolveStops;
        List expandedStops;
        Object resolve;
        List expandedStops2;
        Pair urlCoords;
        String resolvedName;
        String str;
        String str2;
        String name;
        String name2;
        Object geocode;
        Object obj;
        String resolvedName2;
        String resolvedName3;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        boolean z = true;
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                resolveStops = LocationParser.INSTANCE.resolveStops(this.$loc.getUrl(), this);
                if (resolveStops == coroutine_suspended) {
                    return coroutine_suspended;
                }
                expandedStops = (List) resolveStops;
                if (expandedStops.size() <= 1) {
                    this.this$0.loadSharedStops(expandedStops, true);
                    return Unit.INSTANCE;
                }
                this.L$0 = SpillingKt.nullOutSpilledVariable(expandedStops);
                this.label = 2;
                resolve = LocationParser.INSTANCE.resolve(this.$loc.getUrl(), this);
                if (resolve == coroutine_suspended) {
                    return coroutine_suspended;
                }
                expandedStops2 = expandedStops;
                Pair pair = (Pair) resolve;
                urlCoords = (Pair) pair.component1();
                resolvedName = (String) pair.component2();
                if (!StringsKt.isBlank(this.$loc.getName()) || Intrinsics.areEqual(this.$loc.getName(), "Loading…")) {
                    str = resolvedName;
                    if (str != null && !StringsKt.isBlank(str)) {
                        z = false;
                    }
                    str2 = z ? resolvedName : urlCoords != null ? "Dropped pin" : "Shared location";
                } else {
                    str2 = this.$loc.getName();
                }
                name = str2;
                if (urlCoords == null) {
                    name2 = name;
                    SharedLocation resolved = SharedLocation.copy$default(this.$loc, name2, urlCoords == null ? (Double) urlCoords.getFirst() : null, urlCoords != null ? (Double) urlCoords.getSecond() : null, null, false, 8, null);
                    RouteViewModel routeViewModel = this.this$0;
                    if (urlCoords == null) {
                    }
                    return Unit.INSTANCE;
                }
                if (resolvedName == null) {
                    urlCoords = null;
                    if (urlCoords == null) {
                    }
                    name2 = name;
                    SharedLocation resolved2 = SharedLocation.copy$default(this.$loc, name2, urlCoords == null ? (Double) urlCoords.getFirst() : null, urlCoords != null ? (Double) urlCoords.getSecond() : null, null, false, 8, null);
                    RouteViewModel routeViewModel2 = this.this$0;
                    if (urlCoords == null) {
                    }
                    return Unit.INSTANCE;
                }
                RouteViewModel routeViewModel3 = this.this$0;
                this.L$0 = SpillingKt.nullOutSpilledVariable(expandedStops2);
                this.L$1 = SpillingKt.nullOutSpilledVariable(urlCoords);
                this.L$2 = SpillingKt.nullOutSpilledVariable(resolvedName);
                this.L$3 = name;
                this.L$4 = SpillingKt.nullOutSpilledVariable(resolvedName);
                this.I$0 = 0;
                this.label = 3;
                geocode = routeViewModel3.geocode(resolvedName, this);
                if (geocode == coroutine_suspended) {
                    return coroutine_suspended;
                }
                obj = geocode;
                resolvedName2 = resolvedName;
                resolvedName3 = name;
                urlCoords = (Pair) obj;
                name = resolvedName3;
                if (urlCoords == null) {
                    urlCoords = this.this$0.savedByName(name);
                }
                name2 = name;
                SharedLocation resolved22 = SharedLocation.copy$default(this.$loc, name2, urlCoords == null ? (Double) urlCoords.getFirst() : null, urlCoords != null ? (Double) urlCoords.getSecond() : null, null, false, 8, null);
                RouteViewModel routeViewModel22 = this.this$0;
                if (urlCoords == null) {
                    routeViewModel22._state.setValue(RouteState.copy$default((RouteState) this.this$0._state.getValue(), resolved22, CollectionsKt.listOf(resolved22), null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, null, null, false, 131036, null));
                    this.this$0.computeRoute(CollectionsKt.listOf(resolved22));
                } else {
                    routeViewModel22._state.setValue(RouteState.copy$default((RouteState) this.this$0._state.getValue(), resolved22, CollectionsKt.emptyList(), null, 0.0f, 0L, false, false, null, null, 0, false, null, null, null, "Google Haritalar bağlantısı açılamadı (internet yok olabilir). Kayıtlı bir yer arayın veya haritaya dokunun.", null, false, 114652, null));
                    str3 = this.this$0.TAG;
                    Boxing.boxInt(Log.w(str3, "No coords for '" + name2 + "' (url+geocode+saved all empty)"));
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                resolveStops = $result;
                expandedStops = (List) resolveStops;
                if (expandedStops.size() <= 1) {
                }
                break;
            case 2:
                List expandedStops3 = (List) this.L$0;
                ResultKt.throwOnFailure($result);
                resolve = $result;
                expandedStops2 = expandedStops3;
                Pair pair2 = (Pair) resolve;
                urlCoords = (Pair) pair2.component1();
                resolvedName = (String) pair2.component2();
                if (StringsKt.isBlank(this.$loc.getName())) {
                    break;
                }
                str = resolvedName;
                if (str != null) {
                    z = false;
                    break;
                }
                if (z) {
                }
                name = str2;
                if (urlCoords == null) {
                }
                break;
            case 3:
                int i = this.I$0;
                String name3 = (String) this.L$3;
                String resolvedName4 = (String) this.L$2;
                ResultKt.throwOnFailure($result);
                resolvedName2 = resolvedName4;
                resolvedName3 = name3;
                obj = $result;
                urlCoords = (Pair) obj;
                name = resolvedName3;
                if (urlCoords == null) {
                }
                name2 = name;
                SharedLocation resolved222 = SharedLocation.copy$default(this.$loc, name2, urlCoords == null ? (Double) urlCoords.getFirst() : null, urlCoords != null ? (Double) urlCoords.getSecond() : null, null, false, 8, null);
                RouteViewModel routeViewModel222 = this.this$0;
                if (urlCoords == null) {
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
