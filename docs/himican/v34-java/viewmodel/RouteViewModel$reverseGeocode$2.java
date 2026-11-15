package com.example.wayfindr.viewmodel;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
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

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$reverseGeocode$2", f = "RouteViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class RouteViewModel$reverseGeocode$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ double $lat;
    final /* synthetic */ double $lng;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$reverseGeocode$2(RouteViewModel routeViewModel, double d, double d2, Continuation<? super RouteViewModel$reverseGeocode$2> continuation) {
        super(2, continuation);
        this.this$0 = routeViewModel;
        this.$lat = d;
        this.$lng = d2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteViewModel$reverseGeocode$2(this.this$0, this.$lat, this.$lng, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((RouteViewModel$reverseGeocode$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        String str;
        Address a;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    if (!Geocoder.isPresent()) {
                        return null;
                    }
                    ApiUsage.INSTANCE.record(ApiUsage.REVERSE_GEOCODE);
                    List results = new Geocoder(this.this$0.getApplication(), Locale.getDefault()).getFromLocation(this.$lat, this.$lng, 1);
                    if (results != null && (a = (Address) CollectionsKt.firstOrNull(results)) != null) {
                        String featureName = a.getFeatureName();
                        return featureName == null ? a.getAddressLine(0) : featureName;
                    }
                    return null;
                } catch (Exception e) {
                    str = this.this$0.TAG;
                    Log.w(str, "Reverse geocode failed: " + e.getMessage());
                    return null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
