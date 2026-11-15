package com.example.wayfindr.viewmodel;

import android.net.Uri;
import com.example.wayfindr.util.RouteQr;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RouteViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RouteViewModel$importQrImage$1$text$1", f = "RouteViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class RouteViewModel$importQrImage$1$text$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    final /* synthetic */ Uri $uri;
    int label;
    final /* synthetic */ RouteViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteViewModel$importQrImage$1$text$1(RouteViewModel routeViewModel, Uri uri, Continuation<? super RouteViewModel$importQrImage$1$text$1> continuation) {
        super(2, continuation);
        this.this$0 = routeViewModel;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteViewModel$importQrImage$1$text$1(this.this$0, this.$uri, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((RouteViewModel$importQrImage$1$text$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return RouteQr.INSTANCE.read(this.this$0.getApplication(), this.$uri);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
