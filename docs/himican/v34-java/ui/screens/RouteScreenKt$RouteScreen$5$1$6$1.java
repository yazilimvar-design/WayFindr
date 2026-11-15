package com.example.wayfindr.ui.screens;

import com.example.wayfindr.viewmodel.RouteViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: RouteScreen.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
final /* synthetic */ class RouteScreenKt$RouteScreen$5$1$6$1 extends FunctionReferenceImpl implements Function1<Integer, Unit> {
    RouteScreenKt$RouteScreen$5$1$6$1(Object obj) {
        super(1, obj, RouteViewModel.class, "selectRoute", "selectRoute(I)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
        invoke(num.intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int p0) {
        ((RouteViewModel) this.receiver).selectRoute(p0);
    }
}
