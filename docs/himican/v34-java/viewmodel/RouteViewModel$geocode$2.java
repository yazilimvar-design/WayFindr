package com.example.wayfindr.viewmodel;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import com.example.wayfindr.BuildConfig;
import com.example.wayfindr.data.ApiUsage;
import com.example.wayfindr.util.Dbg;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$geocode$2", f = "RouteViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class RouteViewModel$geocode$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Double, ? extends Double>>, Object> {
    final /* synthetic */ String $query;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$geocode$2(RouteViewModel routeViewModel, String str, Continuation<? super RouteViewModel$geocode$2> continuation) {
        super(2, continuation);
        this.this$0 = routeViewModel;
        this.$query = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RouteViewModel$geocode$2 routeViewModel$geocode$2 = new RouteViewModel$geocode$2(this.this$0, this.$query, continuation);
        routeViewModel$geocode$2.L$0 = obj;
        return routeViewModel$geocode$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Double, ? extends Double>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Pair<Double, Double>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<Double, Double>> continuation) {
        return ((RouteViewModel$geocode$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        String str;
        String str2;
        Address a;
        String str3;
        String str4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                try {
                    if (!Geocoder.isPresent()) {
                        str4 = this.this$0.TAG;
                        Log.w(str4, "No geocoder backend present");
                        return null;
                    }
                    ApiUsage.INSTANCE.record(ApiUsage.GEOCODE);
                    List results = new Geocoder(this.this$0.getApplication(), Locale.getDefault()).getFromLocationName(this.$query, 1);
                    if (results != null && (a = (Address) CollectionsKt.firstOrNull(results)) != null) {
                        Dbg dbg = Dbg.INSTANCE;
                        str3 = this.this$0.TAG;
                        String str5 = this.$query;
                        if (BuildConfig.DEBUG) {
                            Log.i(str3, "Geocoded '" + str5 + "' → " + a.getLatitude() + "," + a.getLongitude());
                        }
                        return TuplesKt.to(Boxing.boxDouble(a.getLatitude()), Boxing.boxDouble(a.getLongitude()));
                    }
                    RouteViewModel routeViewModel = this.this$0;
                    String str6 = this.$query;
                    str2 = routeViewModel.TAG;
                    Log.w(str2, "Geocoder: no result for '" + str6 + "'");
                    return null;
                } catch (Exception e) {
                    str = this.this$0.TAG;
                    Log.w(str, "Geocoder failed: " + e.getMessage());
                    return null;
                }
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
