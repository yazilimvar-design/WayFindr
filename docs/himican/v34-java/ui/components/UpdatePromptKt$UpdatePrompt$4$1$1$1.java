package com.example.wayfindr.ui.components;

import android.content.Context;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import com.example.wayfindr.data.UpdateChecker;
import java.io.File;
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
@DebugMetadata(c = "com.example.wayfindr.ui.components.UpdatePromptKt$UpdatePrompt$4$1$1$1", f = "UpdatePrompt.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class UpdatePromptKt$UpdatePrompt$4$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableState<Boolean> $dismissed$delegate;
    final /* synthetic */ MutableState<Boolean> $downloading$delegate;
    final /* synthetic */ MutableState<Boolean> $failed$delegate;
    final /* synthetic */ MutableFloatState $progress$delegate;
    final /* synthetic */ UpdateChecker.ReleaseInfo $r;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdatePromptKt$UpdatePrompt$4$1$1$1(Context context, MutableState<Boolean> mutableState, MutableState<Boolean> mutableState2, MutableFloatState mutableFloatState, UpdateChecker.ReleaseInfo releaseInfo, MutableState<Boolean> mutableState3, Continuation<? super UpdatePromptKt$UpdatePrompt$4$1$1$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$downloading$delegate = mutableState;
        this.$failed$delegate = mutableState2;
        this.$progress$delegate = mutableFloatState;
        this.$r = releaseInfo;
        this.$dismissed$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdatePromptKt$UpdatePrompt$4$1$1$1(this.$context, this.$downloading$delegate, this.$failed$delegate, this.$progress$delegate, this.$r, this.$dismissed$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdatePromptKt$UpdatePrompt$4$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object withContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                UpdatePromptKt.UpdatePrompt$lambda$8(this.$downloading$delegate, true);
                UpdatePromptKt.UpdatePrompt$lambda$14(this.$failed$delegate, false);
                this.$progress$delegate.setFloatValue(0.0f);
                this.label = 1;
                withContext = BuildersKt.withContext(Dispatchers.getIO(), new UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1(this.$context, this.$r, this.$progress$delegate, null), this);
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
        File file = (File) withContext;
        UpdatePromptKt.UpdatePrompt$lambda$8(this.$downloading$delegate, false);
        if (file == null) {
            UpdatePromptKt.UpdatePrompt$lambda$14(this.$failed$delegate, true);
            UpdateChecker.INSTANCE.openReleasesPage(this.$context);
        } else if (UpdateChecker.INSTANCE.install(this.$context, file)) {
            UpdatePromptKt.UpdatePrompt$lambda$5(this.$dismissed$delegate, true);
        }
        return Unit.INSTANCE;
    }
}
