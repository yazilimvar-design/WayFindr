package com.example.wayfindr.viewmodel;

import androidx.core.view.MotionEventCompat;
import com.example.wayfindr.data.Ride;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.MutableStateFlow;

/* compiled from: RidesViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.RidesViewModel$reload$1", f = "RidesViewModel.kt", i = {}, l = {MotionEventCompat.AXIS_RELATIVE_Y}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class RidesViewModel$reload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RidesViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RidesViewModel$reload$1(RidesViewModel ridesViewModel, Continuation<? super RidesViewModel$reload$1> continuation) {
        super(2, continuation);
        this.this$0 = ridesViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RidesViewModel$reload$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RidesViewModel$reload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: RidesViewModel.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/wayfindr/data/Ride;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "com.example.wayfindr.viewmodel.RidesViewModel$reload$1$1", f = "RidesViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.example.wayfindr.viewmodel.RidesViewModel$reload$1$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Ride>>, Object> {
        int label;
        final /* synthetic */ RidesViewModel this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(RidesViewModel ridesViewModel, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.this$0 = ridesViewModel;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Ride>> continuation) {
            return invoke2(coroutineScope, (Continuation<? super List<Ride>>) continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Ride>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    return this.this$0.repo.rides();
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutableStateFlow mutableStateFlow;
        Object withContext;
        MutableStateFlow mutableStateFlow2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                mutableStateFlow = this.this$0._rides;
                this.L$0 = mutableStateFlow;
                this.label = 1;
                withContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1(this.this$0, null), this);
                if (withContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableStateFlow2 = mutableStateFlow;
                break;
            case 1:
                mutableStateFlow2 = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure($result);
                withContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mutableStateFlow2.setValue(withContext);
        return Unit.INSTANCE;
    }
}
