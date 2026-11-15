package com.example.wayfindr.ui.screens;

import android.content.Context;
import androidx.activity.compose.ManagedActivityResultLauncher;
import com.example.wayfindr.viewmodel.RouteViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RouteScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.screens.RouteScreenKt$RouteScreen$3$1", f = "RouteScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class RouteScreenKt$RouteScreen$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ ManagedActivityResultLauncher<String, Boolean> $locationPermLauncher;
    final /* synthetic */ RouteViewModel $routeViewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteScreenKt$RouteScreen$3$1(RouteViewModel routeViewModel, ManagedActivityResultLauncher<String, Boolean> managedActivityResultLauncher, Context context, Continuation<? super RouteScreenKt$RouteScreen$3$1> continuation) {
        super(2, continuation);
        this.$routeViewModel = routeViewModel;
        this.$locationPermLauncher = managedActivityResultLauncher;
        this.$ctx = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteScreenKt$RouteScreen$3$1(this.$routeViewModel, this.$locationPermLauncher, this.$ctx, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteScreenKt$RouteScreen$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        boolean RouteScreen$hasLocationPermission;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                RouteScreen$hasLocationPermission = RouteScreenKt.RouteScreen$hasLocationPermission(this.$ctx);
                if (RouteScreen$hasLocationPermission) {
                    this.$routeViewModel.startLocationUpdates();
                } else {
                    this.$locationPermLauncher.launch("android.permission.ACCESS_FINE_LOCATION");
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
