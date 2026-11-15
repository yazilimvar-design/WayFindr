package com.example.wayfindr.ui.screens;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import com.example.wayfindr.viewmodel.DashUiState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DashScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.screens.DashScreenKt$DashScreen$1$1", f = "DashScreen.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class DashScreenKt$DashScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Offset> $pan$delegate;
    final /* synthetic */ State<DashUiState> $ui$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashScreenKt$DashScreen$1$1(State<DashUiState> state, MutableState<Offset> mutableState, Continuation<? super DashScreenKt$DashScreen$1$1> continuation) {
        super(2, continuation);
        this.$ui$delegate = state;
        this.$pan$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashScreenKt$DashScreen$1$1(this.$ui$delegate, this.$pan$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashScreenKt$DashScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        DashUiState DashScreen$lambda$0;
        long DashScreen$lambda$20;
        long DashScreen$lambda$202;
        long DashScreen$lambda$203;
        long DashScreen$lambda$204;
        long DashScreen$lambda$205;
        long DashScreen$lambda$206;
        long DashScreen$lambda$207;
        long DashScreen$lambda$208;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                DashScreen$lambda$0 = DashScreenKt.DashScreen$lambda$0(this.$ui$delegate);
                String b = DashScreen$lambda$0.getLastButton();
                if (b == null) {
                    return Unit.INSTANCE;
                }
                if (StringsKt.startsWith$default(b, "→", false, 2, (Object) null)) {
                    MutableState<Offset> mutableState = this.$pan$delegate;
                    DashScreen$lambda$207 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float coerceIn = RangesKt.coerceIn(Float.intBitsToFloat((int) (DashScreen$lambda$207 >> 32)) - 12.0f, -46.0f, 46.0f);
                    DashScreen$lambda$208 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float intBitsToFloat = Float.intBitsToFloat((int) (DashScreen$lambda$208 & 4294967295L));
                    DashScreenKt.DashScreen$lambda$21(mutableState, Offset.m4841constructorimpl((Float.floatToRawIntBits(coerceIn) << 32) | (Float.floatToRawIntBits(intBitsToFloat) & 4294967295L)));
                } else if (StringsKt.startsWith$default(b, "←", false, 2, (Object) null)) {
                    MutableState<Offset> mutableState2 = this.$pan$delegate;
                    DashScreen$lambda$205 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float coerceIn2 = RangesKt.coerceIn(Float.intBitsToFloat((int) (DashScreen$lambda$205 >> 32)) + 12.0f, -46.0f, 46.0f);
                    DashScreen$lambda$206 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float intBitsToFloat2 = Float.intBitsToFloat((int) (DashScreen$lambda$206 & 4294967295L));
                    DashScreenKt.DashScreen$lambda$21(mutableState2, Offset.m4841constructorimpl((Float.floatToRawIntBits(coerceIn2) << 32) | (Float.floatToRawIntBits(intBitsToFloat2) & 4294967295L)));
                } else if (StringsKt.startsWith$default(b, "↓", false, 2, (Object) null)) {
                    MutableState<Offset> mutableState3 = this.$pan$delegate;
                    DashScreen$lambda$203 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float intBitsToFloat3 = Float.intBitsToFloat((int) (DashScreen$lambda$203 >> 32));
                    DashScreen$lambda$204 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float coerceIn3 = RangesKt.coerceIn(Float.intBitsToFloat((int) (DashScreen$lambda$204 & 4294967295L)) - 12.0f, -46.0f, 46.0f);
                    DashScreenKt.DashScreen$lambda$21(mutableState3, Offset.m4841constructorimpl((Float.floatToRawIntBits(intBitsToFloat3) << 32) | (Float.floatToRawIntBits(coerceIn3) & 4294967295L)));
                } else if (StringsKt.startsWith$default(b, "↑", false, 2, (Object) null)) {
                    MutableState<Offset> mutableState4 = this.$pan$delegate;
                    DashScreen$lambda$20 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float intBitsToFloat4 = Float.intBitsToFloat((int) (DashScreen$lambda$20 >> 32));
                    DashScreen$lambda$202 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
                    float coerceIn4 = RangesKt.coerceIn(Float.intBitsToFloat((int) (DashScreen$lambda$202 & 4294967295L)) + 12.0f, -46.0f, 46.0f);
                    DashScreenKt.DashScreen$lambda$21(mutableState4, Offset.m4841constructorimpl((Float.floatToRawIntBits(intBitsToFloat4) << 32) | (Float.floatToRawIntBits(coerceIn4) & 4294967295L)));
                } else if (StringsKt.startsWith$default(b, "●", false, 2, (Object) null)) {
                    DashScreenKt.DashScreen$lambda$21(this.$pan$delegate, Offset.INSTANCE.m4865getZeroF1C5BW0());
                }
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
