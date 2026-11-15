package com.example.wayfindr.ui.components;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Joystick.kt */
@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
final class JoystickKt$Joystick$1$1 implements PointerInputEventHandler {
    final /* synthetic */ MutableState<Offset> $knobOffset$delegate;
    final /* synthetic */ float $maxPx;
    final /* synthetic */ Function1<Offset, Unit> $onMove;
    final /* synthetic */ float $size;

    /* JADX WARN: Multi-variable type inference failed */
    JoystickKt$Joystick$1$1(float f, float f2, Function1<? super Offset, Unit> function1, MutableState<Offset> mutableState) {
        this.$size = f;
        this.$maxPx = f2;
        this.$onMove = function1;
        this.$knobOffset$delegate = mutableState;
    }

    @Override // androidx.compose.ui.input.pointer.PointerInputEventHandler
    public final Object invoke(final PointerInputScope $this$pointerInput, Continuation<? super Unit> continuation) {
        final float f = this.$size;
        final float f2 = this.$maxPx;
        final Function1<Offset, Unit> function1 = this.$onMove;
        final MutableState<Offset> mutableState = this.$knobOffset$delegate;
        Function1 function12 = new Function1() { // from class: com.example.wayfindr.ui.components.JoystickKt$Joystick$1$1$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JoystickKt$Joystick$1$1.invoke$lambda$0(PointerInputScope.this, f, f2, function1, mutableState, (Offset) obj);
            }
        };
        final Function1<Offset, Unit> function13 = this.$onMove;
        final MutableState<Offset> mutableState2 = this.$knobOffset$delegate;
        Function0 function0 = new Function0() { // from class: com.example.wayfindr.ui.components.JoystickKt$Joystick$1$1$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JoystickKt$Joystick$1$1.invoke$lambda$1(Function1.this, mutableState2);
            }
        };
        final Function1<Offset, Unit> function14 = this.$onMove;
        final MutableState<Offset> mutableState3 = this.$knobOffset$delegate;
        Function0 function02 = new Function0() { // from class: com.example.wayfindr.ui.components.JoystickKt$Joystick$1$1$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JoystickKt$Joystick$1$1.invoke$lambda$2(Function1.this, mutableState3);
            }
        };
        final float f3 = this.$size;
        final float f4 = this.$maxPx;
        final Function1<Offset, Unit> function15 = this.$onMove;
        final MutableState<Offset> mutableState4 = this.$knobOffset$delegate;
        Object detectDragGestures = DragGestureDetectorKt.detectDragGestures($this$pointerInput, function12, function0, function02, new Function2() { // from class: com.example.wayfindr.ui.components.JoystickKt$Joystick$1$1$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return JoystickKt$Joystick$1$1.invoke$lambda$3(PointerInputScope.this, f3, f4, function15, mutableState4, (PointerInputChange) obj, (Offset) obj2);
            }
        }, continuation);
        return detectDragGestures == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? detectDragGestures : Unit.INSTANCE;
    }

    static final Unit invoke$lambda$0(PointerInputScope $this_pointerInput, float $size, float $maxPx, Function1 $onMove, MutableState $knobOffset$delegate, Offset offset) {
        float halfPx = $this_pointerInput.mo403toPx0680j_4($size) / 2.0f;
        float dx = Float.intBitsToFloat((int) (offset.m4859unboximpl() >> 32)) - halfPx;
        float dy = Float.intBitsToFloat((int) (offset.m4859unboximpl() & 4294967295L)) - halfPx;
        float dist = (float) Math.sqrt((dx * dx) + (dy * dy));
        if (dist > $maxPx) {
            dx = (dx / dist) * $maxPx;
            dy = (dy / dist) * $maxPx;
        }
        JoystickKt.Joystick_Kz89ssw$lambda$2($knobOffset$delegate, Offset.m4841constructorimpl((Float.floatToRawIntBits(dx) << 32) | (Float.floatToRawIntBits(dy) & 4294967295L)));
        $onMove.invoke(Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(dx / $maxPx) << 32) | (4294967295L & Float.floatToRawIntBits(dy / $maxPx)))));
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$3(PointerInputScope $this_pointerInput, float $size, float $maxPx, Function1 $onMove, MutableState $knobOffset$delegate, PointerInputChange change, Offset offset) {
        Intrinsics.checkNotNullParameter(change, "change");
        float halfPx = $this_pointerInput.mo403toPx0680j_4($size) / 2.0f;
        float dx = Float.intBitsToFloat((int) (change.getPosition() >> 32)) - halfPx;
        float dy = Float.intBitsToFloat((int) (change.getPosition() & 4294967295L)) - halfPx;
        float dist = (float) Math.sqrt((dx * dx) + (dy * dy));
        if (dist > $maxPx) {
            dx = (dx / dist) * $maxPx;
            dy = (dy / dist) * $maxPx;
        }
        JoystickKt.Joystick_Kz89ssw$lambda$2($knobOffset$delegate, Offset.m4841constructorimpl((Float.floatToRawIntBits(dx) << 32) | (Float.floatToRawIntBits(dy) & 4294967295L)));
        $onMove.invoke(Offset.m4838boximpl(Offset.m4841constructorimpl((Float.floatToRawIntBits(dx / $maxPx) << 32) | (Float.floatToRawIntBits(dy / $maxPx) & 4294967295L))));
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$1(Function1 $onMove, MutableState $knobOffset$delegate) {
        JoystickKt.Joystick_Kz89ssw$lambda$2($knobOffset$delegate, Offset.INSTANCE.m4865getZeroF1C5BW0());
        $onMove.invoke(Offset.m4838boximpl(Offset.INSTANCE.m4865getZeroF1C5BW0()));
        return Unit.INSTANCE;
    }

    static final Unit invoke$lambda$2(Function1 $onMove, MutableState $knobOffset$delegate) {
        JoystickKt.Joystick_Kz89ssw$lambda$2($knobOffset$delegate, Offset.INSTANCE.m4865getZeroF1C5BW0());
        $onMove.invoke(Offset.m4838boximpl(Offset.INSTANCE.m4865getZeroF1C5BW0()));
        return Unit.INSTANCE;
    }
}
