package com.example.wayfindr.dash.nav;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Route.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001d\u001a\u00020\tHÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J;\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u0005HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0017HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0011\u0010\u0016\u001a\u00020\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006%"}, d2 = {"Lcom/example/wayfindr/dash/nav/Maneuver;", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Lcom/example/wayfindr/dash/nav/ManeuverType;", "instruction", "", "location", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "cumulativeMeters", "", "googleManeuver", "<init>", "(Lcom/example/wayfindr/dash/nav/ManeuverType;Ljava/lang/String;Lcom/example/wayfindr/dash/nav/GeoPoint;DLjava/lang/String;)V", "getType", "()Lcom/example/wayfindr/dash/nav/ManeuverType;", "getInstruction", "()Ljava/lang/String;", "getLocation", "()Lcom/example/wayfindr/dash/nav/GeoPoint;", "getCumulativeMeters", "()D", "getGoogleManeuver", "dashCode", "", "getDashCode", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final /* data */ class Maneuver {
    public static final int $stable = 0;
    private final double cumulativeMeters;
    private final String googleManeuver;
    private final String instruction;
    private final GeoPoint location;
    private final ManeuverType type;

    public static /* synthetic */ Maneuver copy$default(Maneuver maneuver, ManeuverType maneuverType, String str, GeoPoint geoPoint, double d, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            maneuverType = maneuver.type;
        }
        if ((i & 2) != 0) {
            str = maneuver.instruction;
        }
        if ((i & 4) != 0) {
            geoPoint = maneuver.location;
        }
        if ((i & 8) != 0) {
            d = maneuver.cumulativeMeters;
        }
        if ((i & 16) != 0) {
            str2 = maneuver.googleManeuver;
        }
        String str3 = str2;
        GeoPoint geoPoint2 = geoPoint;
        return maneuver.copy(maneuverType, str, geoPoint2, d, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final ManeuverType getType() {
        return this.type;
    }

    /* renamed from: component2, reason: from getter */
    public final String getInstruction() {
        return this.instruction;
    }

    /* renamed from: component3, reason: from getter */
    public final GeoPoint getLocation() {
        return this.location;
    }

    /* renamed from: component4, reason: from getter */
    public final double getCumulativeMeters() {
        return this.cumulativeMeters;
    }

    /* renamed from: component5, reason: from getter */
    public final String getGoogleManeuver() {
        return this.googleManeuver;
    }

    public final Maneuver copy(ManeuverType type, String instruction, GeoPoint location, double cumulativeMeters, String googleManeuver) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(instruction, "instruction");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(googleManeuver, "googleManeuver");
        return new Maneuver(type, instruction, location, cumulativeMeters, googleManeuver);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Maneuver)) {
            return false;
        }
        Maneuver maneuver = (Maneuver) other;
        return this.type == maneuver.type && Intrinsics.areEqual(this.instruction, maneuver.instruction) && Intrinsics.areEqual(this.location, maneuver.location) && Double.compare(this.cumulativeMeters, maneuver.cumulativeMeters) == 0 && Intrinsics.areEqual(this.googleManeuver, maneuver.googleManeuver);
    }

    public int hashCode() {
        return (((((((this.type.hashCode() * 31) + this.instruction.hashCode()) * 31) + this.location.hashCode()) * 31) + Double.hashCode(this.cumulativeMeters)) * 31) + this.googleManeuver.hashCode();
    }

    public String toString() {
        return "Maneuver(type=" + this.type + ", instruction=" + this.instruction + ", location=" + this.location + ", cumulativeMeters=" + this.cumulativeMeters + ", googleManeuver=" + this.googleManeuver + ")";
    }

    public Maneuver(ManeuverType type, String instruction, GeoPoint location, double cumulativeMeters, String googleManeuver) {
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(instruction, "instruction");
        Intrinsics.checkNotNullParameter(location, "location");
        Intrinsics.checkNotNullParameter(googleManeuver, "googleManeuver");
        this.type = type;
        this.instruction = instruction;
        this.location = location;
        this.cumulativeMeters = cumulativeMeters;
        this.googleManeuver = googleManeuver;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ Maneuver(ManeuverType maneuverType, String str, GeoPoint geoPoint, double d, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(maneuverType, str, geoPoint, d, r6);
        String str3;
        if ((i & 16) == 0) {
            str3 = str2;
        } else {
            str3 = "";
        }
    }

    public final ManeuverType getType() {
        return this.type;
    }

    public final String getInstruction() {
        return this.instruction;
    }

    public final GeoPoint getLocation() {
        return this.location;
    }

    public final double getCumulativeMeters() {
        return this.cumulativeMeters;
    }

    public final String getGoogleManeuver() {
        return this.googleManeuver;
    }

    public final int getDashCode() {
        return DashGlyph.INSTANCE.fromGoogle(this.googleManeuver);
    }
}
