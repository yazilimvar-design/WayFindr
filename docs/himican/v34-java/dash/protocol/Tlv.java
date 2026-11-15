package com.example.wayfindr.dash.protocol;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: K1GPacket.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0006HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/example/wayfindr/dash/protocol/Tlv;", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "", "sub", "value", "", "<init>", "(II[B)V", "getType", "()I", "getSub", "getValue", "()[B", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final /* data */ class Tlv {
    public static final int $stable = 8;
    private final int sub;
    private final int type;
    private final byte[] value;

    public static /* synthetic */ Tlv copy$default(Tlv tlv, int i, int i2, byte[] bArr, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = tlv.type;
        }
        if ((i3 & 2) != 0) {
            i2 = tlv.sub;
        }
        if ((i3 & 4) != 0) {
            bArr = tlv.value;
        }
        return tlv.copy(i, i2, bArr);
    }

    /* renamed from: component1, reason: from getter */
    public final int getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final int getSub() {
        return this.sub;
    }

    /* renamed from: component3, reason: from getter */
    public final byte[] getValue() {
        return this.value;
    }

    public final Tlv copy(int type, int sub, byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        return new Tlv(type, sub, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Tlv)) {
            return false;
        }
        Tlv tlv = (Tlv) other;
        return this.type == tlv.type && this.sub == tlv.sub && Intrinsics.areEqual(this.value, tlv.value);
    }

    public int hashCode() {
        return (((Integer.hashCode(this.type) * 31) + Integer.hashCode(this.sub)) * 31) + Arrays.hashCode(this.value);
    }

    public String toString() {
        return "Tlv(type=" + this.type + ", sub=" + this.sub + ", value=" + Arrays.toString(this.value) + ")";
    }

    public Tlv(int type, int sub, byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        this.type = type;
        this.sub = sub;
        this.value = value;
    }

    public /* synthetic */ Tlv(int i, int i2, byte[] bArr, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, i2, (i3 & 4) != 0 ? new byte[0] : bArr);
    }

    public final int getSub() {
        return this.sub;
    }

    public final int getType() {
        return this.type;
    }

    public final byte[] getValue() {
        return this.value;
    }
}
