package com.example.wayfindr.ui.screens;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.example.wayfindr.viewmodel.RouteState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: RouteScreen.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
final class RouteScreenKt$RouteScreen$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String $destName;
    final /* synthetic */ State $routeState$delegate;
    final /* synthetic */ MutableState $searchQuery$delegate;
    final /* synthetic */ String $sourceDestName;
    final /* synthetic */ MutableState $suggestions$delegate;
    final /* synthetic */ MutableState $suggestionsSuppressed$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    RouteScreenKt$RouteScreen$1$1(String str, String str2, State state, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, Continuation continuation) {
        super(2, continuation);
        this.$sourceDestName = str;
        this.$destName = str2;
        this.$routeState$delegate = state;
        this.$suggestionsSuppressed$delegate = mutableState;
        this.$suggestions$delegate = mutableState2;
        this.$searchQuery$delegate = mutableState3;
    }

    public final Continuation create(Object obj, Continuation continuation) {
        return new RouteScreenKt$RouteScreen$1$1(this.$sourceDestName, this.$destName, this.$routeState$delegate, this.$suggestionsSuppressed$delegate, this.$suggestions$delegate, this.$searchQuery$delegate, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        RouteState RouteScreen$lambda$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure(obj);
                RouteScreen$lambda$0 = RouteScreenKt.RouteScreen$lambda$0(this.$routeState$delegate);
                if (!RouteScreen$lambda$0.isResolving()) {
                    String str = this.$sourceDestName;
                    if (!(str == null || StringsKt.isBlank(str)) && !Intrinsics.areEqual(this.$sourceDestName, "Shared location") && !Intrinsics.areEqual(this.$sourceDestName, "Dropped pin")) {
                        RouteScreenKt.RouteScreen$lambda$35(this.$suggestionsSuppressed$delegate, true);
                        this.$suggestions$delegate.setValue(CollectionsKt.emptyList());
                        this.$searchQuery$delegate.setValue("");
                    }
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
