package com.example.wayfindr.ui.components;

import android.content.Context;
import com.example.wayfindr.data.UpdateChecker;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UpdatePrompt.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/wayfindr/data/UpdateChecker$ReleaseInfo;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.UpdatePromptKt$UpdatePrompt$1$1$latest$1", f = "UpdatePrompt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class UpdatePromptKt$UpdatePrompt$1$1$latest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super UpdateChecker.ReleaseInfo>, Object> {
    final /* synthetic */ Context $context;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdatePromptKt$UpdatePrompt$1$1$latest$1(Context context, Continuation<? super UpdatePromptKt$UpdatePrompt$1$1$latest$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdatePromptKt$UpdatePrompt$1$1$latest$1(this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super UpdateChecker.ReleaseInfo> continuation) {
        return ((UpdatePromptKt$UpdatePrompt$1$1$latest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return UpdateChecker.INSTANCE.fetchLatest(this.$context);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
