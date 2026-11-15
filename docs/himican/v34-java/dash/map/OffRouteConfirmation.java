package com.example.wayfindr.dash.map;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: NavigationReliability.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u001b\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u0005J\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/example/wayfindr/dash/map/OffRouteConfirmation;", "", "requiredFixes", "", "minimumSpanMs", "", "<init>", "(IJ)V", "firstObservedAtMs", "lastFixToken", "distinctFixes", "record", "", "fixToken", "nowMs", "reset", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class OffRouteConfirmation {
    public static final int $stable = 8;
    private int distinctFixes;
    private long firstObservedAtMs;
    private long lastFixToken;
    private final long minimumSpanMs;
    private final int requiredFixes;

    public OffRouteConfirmation() {
        this(0, 0L, 3, null);
    }

    public OffRouteConfirmation(int requiredFixes, long minimumSpanMs) {
        this.requiredFixes = requiredFixes;
        this.minimumSpanMs = minimumSpanMs;
    }

    public /* synthetic */ OffRouteConfirmation(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 3 : i, (i2 & 2) != 0 ? 2000L : j);
    }

    public final boolean record(long fixToken, long nowMs) {
        if (fixToken <= 0) {
            return false;
        }
        if (fixToken > this.lastFixToken) {
            if (this.distinctFixes == 0) {
                this.firstObservedAtMs = nowMs;
            }
            this.lastFixToken = fixToken;
            this.distinctFixes++;
        }
        return this.distinctFixes >= this.requiredFixes && nowMs - this.firstObservedAtMs >= this.minimumSpanMs;
    }

    public final void reset() {
        this.firstObservedAtMs = 0L;
        this.lastFixToken = 0L;
        this.distinctFixes = 0;
    }
}
