package com.example.wayfindr.ui.components;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: BarChart.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/example/wayfindr/ui/components/BarEntry;", "", "label", "", "value", "", "<init>", "(Ljava/lang/String;F)V", "getLabel", "()Ljava/lang/String;", "getValue", "()F", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final /* data */ class BarEntry {
    public static final int $stable = 0;
    private final String label;
    private final float value;

    public static /* synthetic */ BarEntry copy$default(BarEntry barEntry, String str, float f, int i, Object obj) {
        if ((i & 1) != 0) {
            str = barEntry.label;
        }
        if ((i & 2) != 0) {
            f = barEntry.value;
        }
        return barEntry.copy(str, f);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    /* renamed from: component2, reason: from getter */
    public final float getValue() {
        return this.value;
    }

    public final BarEntry copy(String label, float value) {
        Intrinsics.checkNotNullParameter(label, "label");
        return new BarEntry(label, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BarEntry)) {
            return false;
        }
        BarEntry barEntry = (BarEntry) other;
        return Intrinsics.areEqual(this.label, barEntry.label) && Float.compare(this.value, barEntry.value) == 0;
    }

    public int hashCode() {
        return (this.label.hashCode() * 31) + Float.hashCode(this.value);
    }

    public String toString() {
        return "BarEntry(label=" + this.label + ", value=" + this.value + ")";
    }

    public BarEntry(String label, float value) {
        Intrinsics.checkNotNullParameter(label, "label");
        this.label = label;
        this.value = value;
    }

    public final String getLabel() {
        return this.label;
    }

    public final float getValue() {
        return this.value;
    }
}
