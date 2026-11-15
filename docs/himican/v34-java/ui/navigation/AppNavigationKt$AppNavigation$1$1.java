package com.example.wayfindr.ui.navigation;

import androidx.compose.runtime.State;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.viewmodel.DashViewModel;
import com.example.wayfindr.viewmodel.RouteState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.navigation.AppNavigationKt$AppNavigation$1$1", f = "AppNavigation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class AppNavigationKt$AppNavigation$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ DashViewModel $dashViewModel;
    final /* synthetic */ State<RouteState> $routeState$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppNavigationKt$AppNavigation$1$1(DashViewModel dashViewModel, State<RouteState> state, Continuation<? super AppNavigationKt$AppNavigation$1$1> continuation) {
        super(2, continuation);
        this.$dashViewModel = dashViewModel;
        this.$routeState$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppNavigationKt$AppNavigation$1$1(this.$dashViewModel, this.$routeState$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppNavigationKt$AppNavigation$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        RouteState AppNavigation$lambda$3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AppNavigation$lambda$3 = AppNavigationKt.AppNavigation$lambda$3(this.$routeState$delegate);
                SharedLocation d = AppNavigation$lambda$3.getDestination();
                if ((d != null ? d.getLat() : null) != null && d.getLng() != null) {
                    this.$dashViewModel.prefetchTiles(d.getLat().doubleValue(), d.getLng().doubleValue());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
