package com.example.wayfindr.ui.screens;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import com.example.wayfindr.viewmodel.DashViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: DashScreen.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.ui.screens.DashScreenKt$DashScreen$2$1", f = "DashScreen.kt", i = {}, l = {134}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes6.dex */
final class DashScreenKt$DashScreen$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $adjustMode$delegate;
    final /* synthetic */ MutableState<Offset> $joystickVelocity$delegate;
    final /* synthetic */ MutableState<Offset> $pan$delegate;
    final /* synthetic */ DashViewModel $vm;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashScreenKt$DashScreen$2$1(DashViewModel dashViewModel, MutableState<Boolean> mutableState, MutableState<Offset> mutableState2, MutableState<Offset> mutableState3, Continuation<? super DashScreenKt$DashScreen$2$1> continuation) {
        super(2, continuation);
        this.$vm = dashViewModel;
        this.$adjustMode$delegate = mutableState;
        this.$joystickVelocity$delegate = mutableState2;
        this.$pan$delegate = mutableState3;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashScreenKt$DashScreen$2$1(this.$vm, this.$adjustMode$delegate, this.$joystickVelocity$delegate, this.$pan$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashScreenKt$DashScreen$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Incorrect condition in loop: B:9:0x0027 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object $result) {
        Object obj;
        boolean DashScreen$lambda$23;
        long DashScreen$lambda$26;
        long DashScreen$lambda$20;
        long DashScreen$lambda$262;
        long DashScreen$lambda$202;
        long DashScreen$lambda$263;
        long DashScreen$lambda$264;
        long DashScreen$lambda$265;
        long DashScreen$lambda$266;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                obj = coroutine_suspended;
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (DashScreen$lambda$23) {
            DashScreen$lambda$26 = DashScreenKt.DashScreen$lambda$26(this.$joystickVelocity$delegate);
            if (Float.intBitsToFloat((int) (DashScreen$lambda$26 >> 32)) == 0.0f) {
                DashScreen$lambda$266 = DashScreenKt.DashScreen$lambda$26(this.$joystickVelocity$delegate);
                if (Float.intBitsToFloat((int) (DashScreen$lambda$266 & 4294967295L)) == 0.0f) {
                    return Unit.INSTANCE;
                }
            }
            MutableState<Offset> mutableState = this.$pan$delegate;
            DashScreen$lambda$20 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
            float intBitsToFloat = Float.intBitsToFloat((int) (DashScreen$lambda$20 >> 32));
            DashScreen$lambda$262 = DashScreenKt.DashScreen$lambda$26(this.$joystickVelocity$delegate);
            float coerceIn = RangesKt.coerceIn(intBitsToFloat - (Float.intBitsToFloat((int) (DashScreen$lambda$262 >> 32)) * 2.4f), -46.0f, 46.0f);
            DashScreen$lambda$202 = DashScreenKt.DashScreen$lambda$20(this.$pan$delegate);
            float intBitsToFloat2 = Float.intBitsToFloat((int) (DashScreen$lambda$202 & 4294967295L));
            DashScreen$lambda$263 = DashScreenKt.DashScreen$lambda$26(this.$joystickVelocity$delegate);
            DashScreenKt.DashScreen$lambda$21(mutableState, Offset.m4841constructorimpl((Float.floatToRawIntBits(coerceIn) << 32) | (Float.floatToRawIntBits(RangesKt.coerceIn(intBitsToFloat2 - (Float.intBitsToFloat((int) (DashScreen$lambda$263 & 4294967295L)) * 2.4f), -46.0f, 46.0f)) & 4294967295L)));
            DashViewModel dashViewModel = this.$vm;
            DashScreen$lambda$264 = DashScreenKt.DashScreen$lambda$26(this.$joystickVelocity$delegate);
            float intBitsToFloat3 = Float.intBitsToFloat((int) (DashScreen$lambda$264 >> 32)) * 4.0f;
            DashScreen$lambda$265 = DashScreenKt.DashScreen$lambda$26(this.$joystickVelocity$delegate);
            dashViewModel.panBy(intBitsToFloat3, Float.intBitsToFloat((int) (DashScreen$lambda$265 & 4294967295L)) * 4.0f);
            this.label = 1;
            if (DelayKt.delay(16L, this) == obj) {
                return obj;
            }
        }
        return Unit.INSTANCE;
    }
}
