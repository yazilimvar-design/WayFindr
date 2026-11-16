package com.example.wayfindr.dash.map;

import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: NavigationReliability.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
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

    public OffRouteConfirmation(int i, long j) {
        this.requiredFixes = i;
        this.minimumSpanMs = j;
    }

    public /* synthetic */ OffRouteConfirmation(int i, long j, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 3 : i, (i2 & 2) != 0 ? 3000L : j);
    }

    public final boolean record(long j, long j2) {
        if (j <= 0) {
            return false;
        }
        if (j > this.lastFixToken) {
            if (this.distinctFixes == 0) {
                this.firstObservedAtMs = j2;
            }
            this.lastFixToken = j;
            this.distinctFixes++;
        }
        return this.distinctFixes >= this.requiredFixes && j2 - this.firstObservedAtMs >= this.minimumSpanMs;
    }

    public final void reset() {
        this.firstObservedAtMs = 0L;
        this.lastFixToken = 0L;
        this.distinctFixes = 0;
    }
}
