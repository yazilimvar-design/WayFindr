package com.example.wayfindr.dash.map;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TileProvider.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.dash.map.TileProvider$prefetchZoom$1", f = "TileProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class TileProvider$prefetchZoom$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $lat;
    final /* synthetic */ double $lng;
    final /* synthetic */ int $radius;
    final /* synthetic */ int $z;
    int label;
    final /* synthetic */ TileProvider this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TileProvider$prefetchZoom$1(TileProvider tileProvider, double d, double d2, int i, int i2, Continuation<? super TileProvider$prefetchZoom$1> continuation) {
        super(2, continuation);
        this.this$0 = tileProvider;
        this.$lat = d;
        this.$lng = d2;
        this.$z = i;
        this.$radius = i2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TileProvider$prefetchZoom$1(this.this$0, this.$lat, this.$lng, this.$z, this.$radius, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TileProvider$prefetchZoom$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.this$0.prefetchAround(this.$lat, this.$lng, this.$z, this.$radius);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
