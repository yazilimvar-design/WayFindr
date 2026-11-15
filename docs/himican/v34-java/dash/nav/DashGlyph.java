package com.example.wayfindr.dash.nav;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Route.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/example/wayfindr/dash/nav/DashGlyph;", "", "<init>", "()V", "DESTINATION", "", "STRAIGHT", "TURN_LEFT", "TURN_RIGHT", "TURN_SHARP_LEFT", "TURN_SHARP_RIGHT", "TURN_SLIGHT_LEFT", "TURN_SLIGHT_RIGHT", "U_TURN_CLOCKWISE", "U_TURN_COUNTERCLOCKWISE", "MERGE", "FORK_LEFT", "FORK_RIGHT", "OFF_RAMP_LEFT", "OFF_RAMP_RIGHT", "ROUNDABOUT_CLOCKWISE", "ROUNDABOUT_COUNTERCLOCKWISE", "DEPART", "FERRY_BOAT", "FERRY_TRAIN", "ROUNDABOUT", "fromGoogle", "maneuver", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class DashGlyph {
    public static final int $stable = 0;
    public static final int DEPART = 60;
    public static final int DESTINATION = 0;
    public static final int FERRY_BOAT = 62;
    public static final int FERRY_TRAIN = 63;
    public static final int FORK_LEFT = 6;
    public static final int FORK_RIGHT = 5;
    public static final DashGlyph INSTANCE = new DashGlyph();
    public static final int MERGE = 27;
    public static final int OFF_RAMP_LEFT = 8;
    public static final int OFF_RAMP_RIGHT = 7;
    private static final int ROUNDABOUT = 10;
    public static final int ROUNDABOUT_CLOCKWISE = 10;
    public static final int ROUNDABOUT_COUNTERCLOCKWISE = 49;
    public static final int STRAIGHT = 9;
    public static final int TURN_LEFT = 20;
    public static final int TURN_RIGHT = 21;
    public static final int TURN_SHARP_LEFT = 22;
    public static final int TURN_SHARP_RIGHT = 23;
    public static final int TURN_SLIGHT_LEFT = 24;
    public static final int TURN_SLIGHT_RIGHT = 25;
    public static final int U_TURN_CLOCKWISE = 26;
    public static final int U_TURN_COUNTERCLOCKWISE = 61;

    private DashGlyph() {
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0104 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0107 A[ORIG_RETURN, RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final int fromGoogle(String maneuver) {
        Intrinsics.checkNotNullParameter(maneuver, "maneuver");
        switch (maneuver.hashCode()) {
            case -1855790062:
                return !maneuver.equals("ROUNDABOUT_CLOCKWISE") ? 9 : 10;
            case -1603297655:
                if (maneuver.equals("TURN_LEFT")) {
                    return 20;
                }
                break;
            case -1361376074:
                if (!maneuver.equals("ROUNDABOUT_COUNTERCLOCKWISE")) {
                }
                break;
            case -1151399812:
                if (!maneuver.equals("ROUNDABOUT_RIGHT")) {
                }
                break;
            case -1127076396:
                if (maneuver.equals("UTURN_LEFT")) {
                    return 61;
                }
                break;
            case -828569467:
                if (maneuver.equals("TURN_SHARP_RIGHT")) {
                    return 23;
                }
                break;
            case -573968945:
                if (maneuver.equals("UTURN_RIGHT")) {
                    return 26;
                }
                break;
            case -108455873:
                if (maneuver.equals("FORK_RIGHT")) {
                    return 5;
                }
                break;
            case 66783482:
                if (maneuver.equals("FERRY")) {
                    return 62;
                }
                break;
            case 73247768:
                if (maneuver.equals("MERGE")) {
                    return 27;
                }
                break;
            case 101222791:
                if (!maneuver.equals("ROUNDABOUT_LEFT")) {
                }
                break;
            case 273413476:
                if (maneuver.equals("FORK_LEFT")) {
                    return 6;
                }
                break;
            case 301643137:
                if (maneuver.equals("TURN_SLIGHT_LEFT")) {
                    return 24;
                }
                break;
            case 766663618:
                if (maneuver.equals("TURN_SLIGHT_RIGHT")) {
                    return 25;
                }
                break;
            case 1081467998:
                if (maneuver.equals("TURN_SHARP_LEFT")) {
                    return 22;
                }
                break;
            case 1381342095:
                if (maneuver.equals("RAMP_RIGHT")) {
                    return 7;
                }
                break;
            case 1429850132:
                if (maneuver.equals("RAMP_LEFT")) {
                    return 8;
                }
                break;
            case 1565344355:
                if (maneuver.equals("FERRY_TRAIN")) {
                    return 63;
                }
                break;
            case 1843041210:
                if (maneuver.equals("TURN_RIGHT")) {
                    return 21;
                }
                break;
            case 2012953588:
                if (maneuver.equals("DEPART")) {
                    return 60;
                }
                break;
        }
    }
}
