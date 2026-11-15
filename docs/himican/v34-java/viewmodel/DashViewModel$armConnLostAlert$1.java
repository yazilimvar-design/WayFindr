package com.example.wayfindr.viewmodel;

import android.os.VibrationEffect;
import android.os.Vibrator;
import com.example.wayfindr.dash.DashState;
import com.example.wayfindr.dash.nav.VoiceManager;
import com.example.wayfindr.data.RideDiagnostics;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$armConnLostAlert$1", f = "DashViewModel.kt", i = {0}, l = {770}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* loaded from: classes4.dex */
final class DashViewModel$armConnLostAlert$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$armConnLostAlert$1(DashViewModel dashViewModel, Continuation<? super DashViewModel$armConnLostAlert$1> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DashViewModel$armConnLostAlert$1 dashViewModel$armConnLostAlert$1 = new DashViewModel$armConnLostAlert$1(this.this$0, continuation);
        dashViewModel$armConnLostAlert$1.L$0 = obj;
        return dashViewModel$armConnLostAlert$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$armConnLostAlert$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        VoiceManager voiceManager;
        Unit unit;
        CoroutineScope $this$launch = (CoroutineScope) this.L$0;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                this.L$0 = $this$launch;
                this.label = 1;
                if (DelayKt.delay(12000L, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (this.this$0.session.getState().getValue() != DashState.STREAMING && this.this$0.userWantsConnection) {
            this.this$0.connLostAlerted = true;
            RideDiagnostics.INSTANCE.log("connect", "sustained link loss → rider alert (vibrate + voice)");
            DashViewModel dashViewModel = this.this$0;
            try {
                Result.Companion companion = Result.INSTANCE;
                Vibrator vibrator = (Vibrator) dashViewModel.getApplication().getSystemService(Vibrator.class);
                if (vibrator != null) {
                    vibrator.vibrate(VibrationEffect.createWaveform(new long[]{0, 300, 200, 300, 200, 300}, -1));
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                Result.m8392constructorimpl(unit);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m8392constructorimpl(ResultKt.createFailure(th));
            }
            voiceManager = this.this$0.voice;
            voiceManager.alert("Gösterge bağlantısı koptu. Yeniden bağlanmak için motoru kapatıp açın.");
        }
        return Unit.INSTANCE;
    }
}
