package com.example.wayfindr.viewmodel;

import com.example.wayfindr.data.SavedLocation;
import com.example.wayfindr.data.SyncRepository;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "Lcom/example/wayfindr/data/SavedLocation;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$openPicker$1$saved$1", f = "DashViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class DashViewModel$openPicker$1$saved$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends SavedLocation>>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$openPicker$1$saved$1(DashViewModel dashViewModel, Continuation<? super DashViewModel$openPicker$1$saved$1> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashViewModel$openPicker$1$saved$1 dashViewModel$openPicker$1$saved$1 = new DashViewModel$openPicker$1$saved$1(this.this$0, continuation);
        dashViewModel$openPicker$1$saved$1.L$0 = obj;
        return dashViewModel$openPicker$1$saved$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends SavedLocation>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<SavedLocation>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<SavedLocation>> continuation) {
        return ((DashViewModel$openPicker$1$saved$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object m8392constructorimpl;
        SyncRepository syncRepository;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                DashViewModel dashViewModel = this.this$0;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    syncRepository = dashViewModel.repo;
                    m8392constructorimpl = Result.m8392constructorimpl(syncRepository.savedLocations());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m8392constructorimpl = Result.m8392constructorimpl(ResultKt.createFailure(th));
                }
                return Result.m8398isFailureimpl(m8392constructorimpl) ? CollectionsKt.emptyList() : m8392constructorimpl;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
