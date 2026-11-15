package com.example.wayfindr.dash.nav;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.data.ApiUsage;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: PlaceSearch.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/wayfindr/dash/nav/PlaceSearch$Hit;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.PlaceSearch$google$2", f = "PlaceSearch.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class PlaceSearch$google$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super PlaceSearch.Hit>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ String $q;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    PlaceSearch$google$2(Context context, String str, Continuation<? super PlaceSearch$google$2> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$q = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlaceSearch$google$2 placeSearch$google$2 = new PlaceSearch$google$2(this.$ctx, this.$q, continuation);
        placeSearch$google$2.L$0 = obj;
        return placeSearch$google$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super PlaceSearch.Hit> continuation) {
        return ((PlaceSearch$google$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Address a;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    if (!Geocoder.isPresent()) {
                        PlaceSearch placeSearch = PlaceSearch.INSTANCE;
                        PlaceSearch.lastError = "no geocoder on this phone";
                        return null;
                    }
                    ApiUsage.INSTANCE.record(ApiUsage.GEOCODE);
                    List<Address> fromLocationName = new Geocoder(this.$ctx, Locale.getDefault()).getFromLocationName(this.$q, 1);
                    if (fromLocationName != null && (a = (Address) CollectionsKt.firstOrNull((List) fromLocationName)) != null) {
                        return new PlaceSearch.Hit(this.$q, a.getLatitude(), a.getLongitude(), "Google", null, 16, null);
                    }
                    PlaceSearch placeSearch2 = PlaceSearch.INSTANCE;
                    PlaceSearch.lastError = "no match";
                    return null;
                } catch (Exception e) {
                    PlaceSearch placeSearch3 = PlaceSearch.INSTANCE;
                    PlaceSearch.lastError = "Google search failed (" + e.getMessage() + ")";
                    return null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
