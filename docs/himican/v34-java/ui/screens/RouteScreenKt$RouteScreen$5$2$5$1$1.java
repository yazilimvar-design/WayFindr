package com.example.wayfindr.ui.screens;

import com.example.wayfindr.viewmodel.RouteViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: RouteScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
final /* synthetic */ class RouteScreenKt$RouteScreen$5$2$5$1$1 extends FunctionReferenceImpl implements Function0<Unit> {
    RouteScreenKt$RouteScreen$5$2$5$1$1(Object obj) {
        super(0, obj, RouteViewModel.class, "clearDestinations", "clearDestinations()V", 0);
    }

    @Override // kotlin.jvm.functions.Function0
    public /* bridge */ /* synthetic */ Unit invoke() {
        invoke2();
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2() {
        ((RouteViewModel) this.receiver).clearDestinations();
    }
}
