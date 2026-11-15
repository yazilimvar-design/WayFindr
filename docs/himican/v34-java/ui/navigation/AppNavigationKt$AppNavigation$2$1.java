package com.example.wayfindr.ui.navigation;

import androidx.compose.runtime.State;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptionsBuilder;
import com.example.wayfindr.ui.navigation.Screen;
import com.example.wayfindr.viewmodel.RouteState;
import com.example.wayfindr.viewmodel.RouteViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.navigation.AppNavigationKt$AppNavigation$2$1", f = "AppNavigation.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class AppNavigationKt$AppNavigation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $currentRoute;
    final /* synthetic */ NavHostController $navController;
    final /* synthetic */ State<RouteState> $routeState$delegate;
    final /* synthetic */ RouteViewModel $routeViewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppNavigationKt$AppNavigation$2$1(String str, NavHostController navHostController, RouteViewModel routeViewModel, State<RouteState> state, Continuation<? super AppNavigationKt$AppNavigation$2$1> continuation) {
        super(2, continuation);
        this.$currentRoute = str;
        this.$navController = navHostController;
        this.$routeViewModel = routeViewModel;
        this.$routeState$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppNavigationKt$AppNavigation$2$1(this.$currentRoute, this.$navController, this.$routeViewModel, this.$routeState$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppNavigationKt$AppNavigation$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        RouteState AppNavigation$lambda$3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                AppNavigation$lambda$3 = AppNavigationKt.AppNavigation$lambda$3(this.$routeState$delegate);
                if (AppNavigation$lambda$3.getPendingNavigate() && this.$currentRoute != null) {
                    if (!Intrinsics.areEqual(this.$currentRoute, Screen.Route.INSTANCE.getRoute())) {
                        this.$navController.navigate(Screen.Route.INSTANCE.getRoute(), new Function1() { // from class: com.example.wayfindr.ui.navigation.AppNavigationKt$AppNavigation$2$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return AppNavigationKt$AppNavigation$2$1.invokeSuspend$lambda$0((NavOptionsBuilder) obj);
                            }
                        });
                    }
                    this.$routeViewModel.onNavigated();
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final Unit invokeSuspend$lambda$0(NavOptionsBuilder $this$navigate) {
        $this$navigate.setLaunchSingleTop(true);
        return Unit.INSTANCE;
    }
}
