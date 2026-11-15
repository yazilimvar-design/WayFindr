package com.example.wayfindr.media;

import android.app.PendingIntent;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MediaModels.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0007HÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010¨\u0006\u001c"}, d2 = {"Lcom/example/wayfindr/media/IncomingCall;", "", "caller", "", "incoming", "", "answerIntent", "Landroid/app/PendingIntent;", "declineIntent", "<init>", "(Ljava/lang/String;ZLandroid/app/PendingIntent;Landroid/app/PendingIntent;)V", "getCaller", "()Ljava/lang/String;", "getIncoming", "()Z", "getAnswerIntent", "()Landroid/app/PendingIntent;", "getDeclineIntent", "component1", "component2", "component3", "component4", "copy", "equals", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class IncomingCall {
    public static final int $stable = 8;
    private final PendingIntent answerIntent;
    private final String caller;
    private final PendingIntent declineIntent;
    private final boolean incoming;

    public static /* synthetic */ IncomingCall copy$default(IncomingCall incomingCall, String str, boolean z, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = incomingCall.caller;
        }
        if ((i & 2) != 0) {
            z = incomingCall.incoming;
        }
        if ((i & 4) != 0) {
            pendingIntent = incomingCall.answerIntent;
        }
        if ((i & 8) != 0) {
            pendingIntent2 = incomingCall.declineIntent;
        }
        return incomingCall.copy(str, z, pendingIntent, pendingIntent2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getCaller() {
        return this.caller;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIncoming() {
        return this.incoming;
    }

    /* renamed from: component3, reason: from getter */
    public final PendingIntent getAnswerIntent() {
        return this.answerIntent;
    }

    /* renamed from: component4, reason: from getter */
    public final PendingIntent getDeclineIntent() {
        return this.declineIntent;
    }

    public final IncomingCall copy(String caller, boolean incoming, PendingIntent answerIntent, PendingIntent declineIntent) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        return new IncomingCall(caller, incoming, answerIntent, declineIntent);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IncomingCall)) {
            return false;
        }
        IncomingCall incomingCall = (IncomingCall) other;
        return Intrinsics.areEqual(this.caller, incomingCall.caller) && this.incoming == incomingCall.incoming && Intrinsics.areEqual(this.answerIntent, incomingCall.answerIntent) && Intrinsics.areEqual(this.declineIntent, incomingCall.declineIntent);
    }

    public int hashCode() {
        return (((((this.caller.hashCode() * 31) + Boolean.hashCode(this.incoming)) * 31) + (this.answerIntent == null ? 0 : this.answerIntent.hashCode())) * 31) + (this.declineIntent != null ? this.declineIntent.hashCode() : 0);
    }

    public String toString() {
        return "IncomingCall(caller=" + this.caller + ", incoming=" + this.incoming + ", answerIntent=" + this.answerIntent + ", declineIntent=" + this.declineIntent + ")";
    }

    public IncomingCall(String caller, boolean incoming, PendingIntent answerIntent, PendingIntent declineIntent) {
        Intrinsics.checkNotNullParameter(caller, "caller");
        this.caller = caller;
        this.incoming = incoming;
        this.answerIntent = answerIntent;
        this.declineIntent = declineIntent;
    }

    public /* synthetic */ IncomingCall(String str, boolean z, PendingIntent pendingIntent, PendingIntent pendingIntent2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? true : z, (i & 4) != 0 ? null : pendingIntent, (i & 8) != 0 ? null : pendingIntent2);
    }

    public final String getCaller() {
        return this.caller;
    }

    public final boolean getIncoming() {
        return this.incoming;
    }

    public final PendingIntent getAnswerIntent() {
        return this.answerIntent;
    }

    public final PendingIntent getDeclineIntent() {
        return this.declineIntent;
    }
}
