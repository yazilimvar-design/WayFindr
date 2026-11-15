package com.example.wayfindr.viewmodel;

import android.location.Location;
import com.example.wayfindr.dash.DashSession;
import com.example.wayfindr.data.RideRecorder;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: DashViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.wayfindr.viewmodel.DashViewModel$startRecording$1", f = "DashViewModel.kt", i = {}, l = {796}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes4.dex */
final class DashViewModel$startRecording$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ DashViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DashViewModel$startRecording$1(DashViewModel dashViewModel, Continuation<? super DashViewModel$startRecording$1> continuation) {
        super(2, continuation);
        this.this$0 = dashViewModel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DashViewModel$startRecording$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DashViewModel$startRecording$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                StateFlow<Location> location = this.this$0.location.getLocation();
                final DashViewModel dashViewModel = this.this$0;
                this.label = 1;
                if (location.collect(new FlowCollector() { // from class: com.example.wayfindr.viewmodel.DashViewModel$startRecording$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
                        return emit((Location) value, (Continuation<? super Unit>) $completion);
                    }

                    public final Object emit(Location loc, Continuation<? super Unit> continuation) {
                        RideRecorder rideRecorder;
                        if (loc != null) {
                            rideRecorder = DashViewModel.this.recorder;
                            rideRecorder.add(loc.getLatitude(), loc.getLongitude(), loc.getSpeed(), loc.getAccuracy(), loc.getTime());
                            DashSession dashSession = DashViewModel.this.session;
                            Float boxFloat = Boxing.boxFloat(loc.getBearing());
                            boxFloat.floatValue();
                            if (!(loc.hasBearing() && loc.getSpeed() >= 1.1f)) {
                                boxFloat = null;
                            }
                            dashSession.updateGps(boxFloat);
                        }
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                break;
            case 1:
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        throw new KotlinNothingValueException();
    }
}
