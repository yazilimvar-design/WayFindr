package com.example.wayfindr.ui.screens;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.wayfindr.dash.nav.Route;
import com.example.wayfindr.data.SharedLocation;
import com.example.wayfindr.viewmodel.RouteState;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: RouteScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.screens.RouteScreenKt$RouteScreen$4$1", f = "RouteScreen.kt", i = {}, l = {165}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class RouteScreenKt$RouteScreen$4$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<List<SharedLocation>, Route, Unit> $onSentToDash;
    final /* synthetic */ State<RouteState> $routeState$delegate;
    final /* synthetic */ MutableState<Boolean> $sent$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    RouteScreenKt$RouteScreen$4$1(Function2<? super List<SharedLocation>, ? super Route, Unit> function2, MutableState<Boolean> mutableState, State<RouteState> state, Continuation<? super RouteScreenKt$RouteScreen$4$1> continuation) {
        super(2, continuation);
        this.$onSentToDash = function2;
        this.$sent$delegate = mutableState;
        this.$routeState$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteScreenKt$RouteScreen$4$1(this.$onSentToDash, this.$sent$delegate, this.$routeState$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteScreenKt$RouteScreen$4$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        boolean RouteScreen$lambda$7;
        RouteState RouteScreen$lambda$0;
        RouteState RouteScreen$lambda$02;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                RouteScreen$lambda$7 = RouteScreenKt.RouteScreen$lambda$7(this.$sent$delegate);
                if (RouteScreen$lambda$7) {
                    this.label = 1;
                    if (DelayKt.delay(650L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    Function2<List<SharedLocation>, Route, Unit> function2 = this.$onSentToDash;
                    RouteScreen$lambda$0 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                    List<SharedLocation> waypoints = RouteScreen$lambda$0.getWaypoints();
                    RouteScreen$lambda$02 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                    function2.invoke(waypoints, RouteScreen$lambda$02.getRoute());
                }
                return Unit.INSTANCE;
            case 1:
                ResultKt.throwOnFailure($result);
                Function2<List<SharedLocation>, Route, Unit> function22 = this.$onSentToDash;
                RouteScreen$lambda$0 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                List<SharedLocation> waypoints2 = RouteScreen$lambda$0.getWaypoints();
                RouteScreen$lambda$02 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                function22.invoke(waypoints2, RouteScreen$lambda$02.getRoute());
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
