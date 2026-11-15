package com.example.wayfindr.viewmodel;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: JoystickTrail.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0003J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000f\u001a\u00020\u0003J\u0006\u0010\u0012\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/wayfindr/viewmodel/JoystickTrail;", "", "lifeMs", "", "maxLen", "", "<init>", "(JI)V", "presses", "Lkotlin/collections/ArrayDeque;", "Lkotlin/Pair;", "", "add", "", "c", "nowMs", "text", "", "clear", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class JoystickTrail {
    public static final int $stable = 8;
    private final long lifeMs;
    private final int maxLen;
    private final ArrayDeque<Pair<Character, Long>> presses;

    public JoystickTrail() {
        this(0L, 0, 3, null);
    }

    public JoystickTrail(long lifeMs, int maxLen) {
        this.lifeMs = lifeMs;
        this.maxLen = maxLen;
        this.presses = new ArrayDeque<>();
    }

    public /* synthetic */ JoystickTrail(long j, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 2000L : j, (i2 & 2) != 0 ? 4 : i);
    }

    public final synchronized void add(char c, long nowMs) {
        this.presses.addLast(TuplesKt.to(Character.valueOf(c), Long.valueOf(nowMs)));
        while (this.presses.size() > this.maxLen) {
            this.presses.removeFirst();
        }
    }

    public final synchronized String text(long nowMs) {
        while (!this.presses.isEmpty() && nowMs - this.presses.first().getSecond().longValue() >= this.lifeMs) {
            this.presses.removeFirst();
        }
        return this.presses.isEmpty() ? null : CollectionsKt.joinToString$default(this.presses, "", null, null, 0, null, new Function1() { // from class: com.example.wayfindr.viewmodel.JoystickTrail$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return JoystickTrail.text$lambda$0((Pair) obj);
            }
        }, 30, null);
    }

    static final CharSequence text$lambda$0(Pair it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return String.valueOf(((Character) it.getFirst()).charValue());
    }

    public final synchronized void clear() {
        this.presses.clear();
    }
}
