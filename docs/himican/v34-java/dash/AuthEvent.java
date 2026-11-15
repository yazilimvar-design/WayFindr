package com.example.wayfindr.dash;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DashAuth.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lcom/example/wayfindr/dash/AuthEvent;", "", "<init>", "()V", "SendKey", "Confirmed", "Rejected", "None", "Lcom/example/wayfindr/dash/AuthEvent$Confirmed;", "Lcom/example/wayfindr/dash/AuthEvent$None;", "Lcom/example/wayfindr/dash/AuthEvent$Rejected;", "Lcom/example/wayfindr/dash/AuthEvent$SendKey;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public abstract class AuthEvent {
    public static final int $stable = 0;

    public /* synthetic */ AuthEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AuthEvent() {
    }

    /* compiled from: DashAuth.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/example/wayfindr/dash/AuthEvent$SendKey;", "Lcom/example/wayfindr/dash/AuthEvent;", "packet", "", "<init>", "([B)V", "getPacket", "()[B", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class SendKey extends AuthEvent {
        public static final int $stable = 8;
        private final byte[] packet;

        public static /* synthetic */ SendKey copy$default(SendKey sendKey, byte[] bArr, int i, Object obj) {
            if ((i & 1) != 0) {
                bArr = sendKey.packet;
            }
            return sendKey.copy(bArr);
        }

        /* renamed from: component1, reason: from getter */
        public final byte[] getPacket() {
            return this.packet;
        }

        public final SendKey copy(byte[] packet) {
            Intrinsics.checkNotNullParameter(packet, "packet");
            return new SendKey(packet);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SendKey) && Intrinsics.areEqual(this.packet, ((SendKey) other).packet);
        }

        public int hashCode() {
            return Arrays.hashCode(this.packet);
        }

        public String toString() {
            return "SendKey(packet=" + Arrays.toString(this.packet) + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public SendKey(byte[] packet) {
            super(null);
            Intrinsics.checkNotNullParameter(packet, "packet");
            this.packet = packet;
        }

        public final byte[] getPacket() {
            return this.packet;
        }
    }

    /* compiled from: DashAuth.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/dash/AuthEvent$Confirmed;", "Lcom/example/wayfindr/dash/AuthEvent;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Confirmed extends AuthEvent {
        public static final int $stable = 0;
        public static final Confirmed INSTANCE = new Confirmed();

        private Confirmed() {
            super(null);
        }
    }

    /* compiled from: DashAuth.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/dash/AuthEvent$Rejected;", "Lcom/example/wayfindr/dash/AuthEvent;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Rejected extends AuthEvent {
        public static final int $stable = 0;
        public static final Rejected INSTANCE = new Rejected();

        private Rejected() {
            super(null);
        }
    }

    /* compiled from: DashAuth.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/dash/AuthEvent$None;", "Lcom/example/wayfindr/dash/AuthEvent;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class None extends AuthEvent {
        public static final int $stable = 0;
        public static final None INSTANCE = new None();

        private None() {
            super(null);
        }
    }
}
