package com.example.wayfindr.ui.components;

import android.content.Context;
import androidx.compose.runtime.MutableFloatState;
import com.example.wayfindr.data.UpdateChecker;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: UpdatePrompt.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Ljava/io/File;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.components.UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1", f = "UpdatePrompt.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
final class UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super File>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MutableFloatState $progress$delegate;
    final /* synthetic */ UpdateChecker.ReleaseInfo $r;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1(Context context, UpdateChecker.ReleaseInfo releaseInfo, MutableFloatState mutableFloatState, Continuation<? super UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$r = releaseInfo;
        this.$progress$delegate = mutableFloatState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1(this.$context, this.$r, this.$progress$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super File> continuation) {
        return ((UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                UpdateChecker updateChecker = UpdateChecker.INSTANCE;
                Context context = this.$context;
                String apkUrl = this.$r.getApkUrl();
                final MutableFloatState mutableFloatState = this.$progress$delegate;
                return updateChecker.download(context, apkUrl, new Function1() { // from class: com.example.wayfindr.ui.components.UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return UpdatePromptKt$UpdatePrompt$4$1$1$1$file$1.invokeSuspend$lambda$0(MutableFloatState.this, ((Float) obj).floatValue());
                    }
                });
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final Unit invokeSuspend$lambda$0(MutableFloatState $progress$delegate, float p) {
        $progress$delegate.setFloatValue(p);
        return Unit.INSTANCE;
    }
}
