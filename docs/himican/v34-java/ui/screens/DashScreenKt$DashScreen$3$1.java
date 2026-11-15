package com.example.wayfindr.ui.screens;

import android.content.Context;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.wayfindr.viewmodel.ConnStage;
import com.example.wayfindr.viewmodel.DashUiState;
import com.example.wayfindr.viewmodel.DashViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DashScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.screens.DashScreenKt$DashScreen$3$1", f = "DashScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class DashScreenKt$DashScreen$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ State<Boolean> $autoConnectOnStart$delegate;
    final /* synthetic */ MutableState<Boolean> $autoConnectTried$delegate;
    final /* synthetic */ Context $context;
    final /* synthetic */ String[] $essentialPermissions;
    final /* synthetic */ State<DashUiState> $ui$delegate;
    final /* synthetic */ DashViewModel $vm;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashScreenKt$DashScreen$3$1(DashViewModel dashViewModel, State<Boolean> state, MutableState<Boolean> mutableState, State<DashUiState> state2, String[] strArr, Context context, Continuation<? super DashScreenKt$DashScreen$3$1> continuation) {
        super(2, continuation);
        this.$vm = dashViewModel;
        this.$autoConnectOnStart$delegate = state;
        this.$autoConnectTried$delegate = mutableState;
        this.$ui$delegate = state2;
        this.$essentialPermissions = strArr;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashScreenKt$DashScreen$3$1(this.$vm, this.$autoConnectOnStart$delegate, this.$autoConnectTried$delegate, this.$ui$delegate, this.$essentialPermissions, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashScreenKt$DashScreen$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        boolean DashScreen$lambda$3;
        boolean DashScreen$lambda$32;
        DashUiState DashScreen$lambda$0;
        boolean DashScreen$hasEssentialPermissions;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                DashScreen$lambda$3 = DashScreenKt.DashScreen$lambda$3(this.$autoConnectOnStart$delegate);
                if (DashScreen$lambda$3) {
                    DashScreen$lambda$32 = DashScreenKt.DashScreen$lambda$32(this.$autoConnectTried$delegate);
                    if (!DashScreen$lambda$32) {
                        DashScreen$lambda$0 = DashScreenKt.DashScreen$lambda$0(this.$ui$delegate);
                        if (DashScreen$lambda$0.getStage() == ConnStage.OFFLINE) {
                            DashScreen$hasEssentialPermissions = DashScreenKt.DashScreen$hasEssentialPermissions(this.$essentialPermissions, this.$context);
                            if (DashScreen$hasEssentialPermissions) {
                                DashScreenKt.DashScreen$lambda$33(this.$autoConnectTried$delegate, true);
                                this.$vm.connect();
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
