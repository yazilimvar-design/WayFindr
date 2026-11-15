package com.example.wayfindr.data;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SyncRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.data.SyncRepository$addRide$1", f = "SyncRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class SyncRepository$addRide$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ride $r;
    int label;
    final /* synthetic */ SyncRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SyncRepository$addRide$1(SyncRepository syncRepository, Ride ride, Continuation<? super SyncRepository$addRide$1> continuation) {
        super(2, continuation);
        this.this$0 = syncRepository;
        this.$r = ride;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SyncRepository$addRide$1(this.this$0, this.$r, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SyncRepository$addRide$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        WayfindrDb wayfindrDb;
        WayfindrDb wayfindrDb2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                wayfindrDb = this.this$0.db;
                wayfindrDb.upsertRide(this.$r);
                wayfindrDb2 = this.this$0.db;
                wayfindrDb2.addToOdometer(this.$r.getDistanceKm());
                this.this$0.bump();
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
