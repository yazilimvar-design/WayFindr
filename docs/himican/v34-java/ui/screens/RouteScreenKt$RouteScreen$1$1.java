package com.example.wayfindr.ui.screens;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.wayfindr.dash.nav.PlaceSearch;
import com.example.wayfindr.viewmodel.RouteState;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: RouteScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.screens.RouteScreenKt$RouteScreen$1$1", f = "RouteScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class RouteScreenKt$RouteScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $destName;
    final /* synthetic */ State<RouteState> $routeState$delegate;
    final /* synthetic */ MutableState<String> $searchQuery$delegate;
    final /* synthetic */ String $sourceDestName;
    final /* synthetic */ MutableState<List<PlaceSearch.Hit>> $suggestions$delegate;
    final /* synthetic */ MutableState<Boolean> $suggestionsSuppressed$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteScreenKt$RouteScreen$1$1(String str, String str2, State<RouteState> state, MutableState<Boolean> mutableState, MutableState<List<PlaceSearch.Hit>> mutableState2, MutableState<String> mutableState3, Continuation<? super RouteScreenKt$RouteScreen$1$1> continuation) {
        super(2, continuation);
        this.$sourceDestName = str;
        this.$destName = str2;
        this.$routeState$delegate = state;
        this.$suggestionsSuppressed$delegate = mutableState;
        this.$suggestions$delegate = mutableState2;
        this.$searchQuery$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RouteScreenKt$RouteScreen$1$1(this.$sourceDestName, this.$destName, this.$routeState$delegate, this.$suggestionsSuppressed$delegate, this.$suggestions$delegate, this.$searchQuery$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RouteScreenKt$RouteScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        RouteState RouteScreen$lambda$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                RouteScreen$lambda$0 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                if (!RouteScreen$lambda$0.isResolving()) {
                    String str = this.$sourceDestName;
                    if (!(str == null || StringsKt.isBlank(str)) && !Intrinsics.areEqual(this.$sourceDestName, "Shared location") && !Intrinsics.areEqual(this.$sourceDestName, "Dropped pin")) {
                        RouteScreenKt.RouteScreen$lambda$35(this.$suggestionsSuppressed$delegate, true);
                        this.$suggestions$delegate.setValue(CollectionsKt.emptyList());
                        this.$searchQuery$delegate.setValue(this.$destName);
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
