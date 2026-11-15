package com.example.wayfindr.ui.components;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.core.view.MotionEventCompat;
import com.example.wayfindr.data.UpdateChecker;
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

/* compiled from: UpdatePrompt.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.UpdatePromptKt$UpdatePrompt$1$1", f = "UpdatePrompt.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_14}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class UpdatePromptKt$UpdatePrompt$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<UpdateChecker.ReleaseInfo> $release$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdatePromptKt$UpdatePrompt$1$1(Context context, MutableState<UpdateChecker.ReleaseInfo> mutableState, Continuation<? super UpdatePromptKt$UpdatePrompt$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$release$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdatePromptKt$UpdatePrompt$1$1(this.$context, this.$release$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdatePromptKt$UpdatePrompt$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object withContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.label = 1;
                withContext = BuildersKt.withContext(Dispatchers.getIO(), new UpdatePromptKt$UpdatePrompt$1$1$latest$1(this.$context, null), this);
                if (withContext == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                withContext = $result;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        UpdateChecker.ReleaseInfo latest = (UpdateChecker.ReleaseInfo) withContext;
        if (latest != null && UpdateChecker.INSTANCE.shouldOffer(this.$context, latest)) {
            this.$release$delegate.setValue(latest);
        }
        return Unit.INSTANCE;
    }
}
