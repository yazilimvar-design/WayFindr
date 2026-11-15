package com.example.wayfindr.viewmodel;

import androidx.collection.SieveCacheKt;
import kotlin.Metadata;

/* compiled from: PickerGesture.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0014B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/viewmodel/PickerGesture;", "", "right", "", "left", "down", "doubleTapMs", "", "doubleDownMs", "<init>", "(IIIJJ)V", "lastTapCode", "lastTapMs", "lastDownMs", "onCode", "Lcom/example/wayfindr/viewmodel/PickerGesture$Result;", "code", "nowMs", "doubleTapMode", "", "Result", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class PickerGesture {
    public static final int $stable = 8;
    private final long doubleDownMs;
    private final long doubleTapMs;
    private final int down;
    private final int left;
    private final int right;
    private int lastTapCode = -1;
    private long lastTapMs = SieveCacheKt.NodeMetaMask;
    private long lastDownMs = SieveCacheKt.NodeMetaMask;

    public PickerGesture(int right, int left, int down, long doubleTapMs, long doubleDownMs) {
        this.right = right;
        this.left = left;
        this.down = down;
        this.doubleTapMs = doubleTapMs;
        this.doubleDownMs = doubleDownMs;
    }

    /* compiled from: PickerGesture.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/example/wayfindr/viewmodel/PickerGesture$Result;", "", "activate", "", "undoPrevTap", "plainTap", "<init>", "(ZZZ)V", "getActivate", "()Z", "getUndoPrevTap", "getPlainTap", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Result {
        public static final int $stable = 0;
        private final boolean activate;
        private final boolean plainTap;
        private final boolean undoPrevTap;

        public static /* synthetic */ Result copy$default(Result result, boolean z, boolean z2, boolean z3, int i, Object obj) {
            if ((i & 1) != 0) {
                z = result.activate;
            }
            if ((i & 2) != 0) {
                z2 = result.undoPrevTap;
            }
            if ((i & 4) != 0) {
                z3 = result.plainTap;
            }
            return result.copy(z, z2, z3);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getActivate() {
            return this.activate;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getUndoPrevTap() {
            return this.undoPrevTap;
        }

        /* renamed from: component3, reason: from getter */
        public final boolean getPlainTap() {
            return this.plainTap;
        }

        public final Result copy(boolean activate, boolean undoPrevTap, boolean plainTap) {
            return new Result(activate, undoPrevTap, plainTap);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return this.activate == result.activate && this.undoPrevTap == result.undoPrevTap && this.plainTap == result.plainTap;
        }

        public int hashCode() {
            return (((Boolean.hashCode(this.activate) * 31) + Boolean.hashCode(this.undoPrevTap)) * 31) + Boolean.hashCode(this.plainTap);
        }

        public String toString() {
            return "Result(activate=" + this.activate + ", undoPrevTap=" + this.undoPrevTap + ", plainTap=" + this.plainTap + ")";
        }

        public Result(boolean activate, boolean undoPrevTap, boolean plainTap) {
            this.activate = activate;
            this.undoPrevTap = undoPrevTap;
            this.plainTap = plainTap;
        }

        public final boolean getActivate() {
            return this.activate;
        }

        public final boolean getUndoPrevTap() {
            return this.undoPrevTap;
        }

        public final boolean getPlainTap() {
            return this.plainTap;
        }
    }

    public final Result onCode(int code, long nowMs, boolean doubleTapMode) {
        boolean isTap = code == this.right || code == this.left;
        if (doubleTapMode && isTap && code == this.lastTapCode && nowMs - this.lastTapMs <= this.doubleTapMs) {
            this.lastTapCode = -1;
            return new Result(true, true, false);
        }
        if (code == this.down) {
            boolean z = nowMs - this.lastDownMs <= this.doubleDownMs;
            this.lastDownMs = z ? SieveCacheKt.NodeMetaMask : nowMs;
            return new Result(z, false, false);
        }
        if (isTap) {
            this.lastTapCode = code;
            this.lastTapMs = nowMs;
            return new Result(false, false, true);
        }
        return new Result(false, false, false);
    }
}
