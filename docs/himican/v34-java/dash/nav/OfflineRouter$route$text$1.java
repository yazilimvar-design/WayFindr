package com.example.wayfindr.dash.nav;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: OfflineRouter.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.nav.OfflineRouter$route$text$1", f = "OfflineRouter.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
final class OfflineRouter$route$text$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ List<GeoPoint> $points;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    OfflineRouter$route$text$1(Context context, List<GeoPoint> list, Continuation<? super OfflineRouter$route$text$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$points = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OfflineRouter$route$text$1(this.$ctx, this.$points, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((OfflineRouter$route$text$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object callService$default = OfflineRouter.callService$default(OfflineRouter.INSTANCE, this.$ctx, this.$points, null, this, 4, null);
                return callService$default == coroutine_suspended ? coroutine_suspended : callService$default;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
