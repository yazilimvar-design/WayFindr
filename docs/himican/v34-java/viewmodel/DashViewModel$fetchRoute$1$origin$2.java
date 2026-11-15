package com.example.wayfindr.viewmodel;

import android.location.Location;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroid/location/Location;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$fetchRoute$1$origin$2", f = "DashViewModel.kt", i = {}, l = {939}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class DashViewModel$fetchRoute$1$origin$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Location>, Object> {
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$fetchRoute$1$origin$2(DashViewModel dashViewModel, Continuation<? super DashViewModel$fetchRoute$1$origin$2> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashViewModel$fetchRoute$1$origin$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Location> continuation) {
        return ((DashViewModel$fetchRoute$1$origin$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: DashViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Landroid/location/Location;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$fetchRoute$1$origin$2$1", f = "DashViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.DashViewModel$fetchRoute$1$origin$2$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Location, Continuation<? super Boolean>, Object> {
        /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ DashViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(DashViewModel dashViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = dashViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Location location, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass1) create(location, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            boolean isPreciseRouteOrigin;
            Location it = (Location) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    isPreciseRouteOrigin = this.this$0.isPreciseRouteOrigin(it);
                    return Boxing.boxBoolean(isPreciseRouteOrigin);
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                Object first = FlowKt.first(FlowKt.filterNotNull(this.this$0.location.getLocation()), new AnonymousClass1(this.this$0, null), this);
                if (first == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return first;
            case 1:
                ResultKt.throwOnFailure($result);
                return $result;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
