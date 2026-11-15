package com.example.wayfindr.util;

import android.util.Log;
import androidx.compose.material.MenuKt;
import com.example.wayfindr.BuildConfig;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LocationParser.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u001e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.util.LocationParser$resolve$2", f = "LocationParser.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class LocationParser$resolve$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends Pair<? extends Double, ? extends Double>, ? extends String>>, Object> {
    final /* synthetic */ String $url;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LocationParser$resolve$2(String str, Continuation<? super LocationParser$resolve$2> continuation) {
        super(2, continuation);
        this.$url = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LocationParser$resolve$2(this.$url, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends Pair<? extends Double, ? extends Double>, ? extends String>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super Pair<Pair<Double, Double>, String>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super Pair<Pair<Double, Double>, String>> continuation) {
        return ((LocationParser$resolve$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                Object coords = LocationParser.INSTANCE.extractCoords(finalUrl);
                String name = LocationParser.INSTANCE.extractPlaceName(finalUrl);
                if (coords == null) {
                    coords = LocationParser.INSTANCE.scanBody(body);
                }
                Dbg dbg = Dbg.INSTANCE;
                if (BuildConfig.DEBUG) {
                    Log.i("LocationParser", "resolve() → coords=" + coords + " name=" + name + " finalUrl=" + StringsKt.take(finalUrl, MenuKt.InTransitionDuration));
                }
                return TuplesKt.to(coords, name);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
