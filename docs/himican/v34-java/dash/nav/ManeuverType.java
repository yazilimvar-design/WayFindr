package com.example.wayfindr.dash.nav;

import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Route.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000f\b\u0086\u0081\u0002\u0018\u0000 \u000f2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u0010"}, d2 = {"Lcom/example/wayfindr/dash/nav/ManeuverType;", "", "<init>", "(Ljava/lang/String;I)V", "CONTINUE", "TURN_LEFT", "TURN_RIGHT", "SLIGHT_LEFT", "SLIGHT_RIGHT", "SHARP_LEFT", "SHARP_RIGHT", "UTURN", "ROUNDABOUT", "DEPART", "ARRIVE", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public enum ManeuverType {
    CONTINUE,
    TURN_LEFT,
    TURN_RIGHT,
    SLIGHT_LEFT,
    SLIGHT_RIGHT,
    SHARP_LEFT,
    SHARP_RIGHT,
    UTURN,
    ROUNDABOUT,
    DEPART,
    ARRIVE;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<ManeuverType> getEntries() {
        return $ENTRIES;
    }

    /* compiled from: Route.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007J\u000e\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0007¨\u0006\u000b"}, d2 = {"Lcom/example/wayfindr/dash/nav/ManeuverType$Companion;", "", "<init>", "()V", "fromOsrm", "Lcom/example/wayfindr/dash/nav/ManeuverType;", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "", "modifier", "fromGoogle", "maneuver", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:10:0x0091, code lost:
        
            if (r3.equals("uturn") != false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.UTURN;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x009e, code lost:
        
            if (r3.equals("right") != false) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.TURN_RIGHT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x00aa, code lost:
        
            if (r3.equals("left") != false) goto L56;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.TURN_LEFT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x00b6, code lost:
        
            if (r3.equals("sharp right") != false) goto L60;
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.SHARP_RIGHT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x00c2, code lost:
        
            if (r3.equals("slight left") != false) goto L64;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.SLIGHT_LEFT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:31:0x00ce, code lost:
        
            if (r3.equals("sharp left") != false) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.SHARP_LEFT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00da, code lost:
        
            if (r3.equals("slight right") != false) goto L72;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.SLIGHT_RIGHT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.CONTINUE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x001b, code lost:
        
            if (r2.equals("end of road") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0025, code lost:
        
            if (r2.equals("merge") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x002f, code lost:
        
            if (r2.equals("turn") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0039, code lost:
        
            if (r2.equals("fork") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0043, code lost:
        
            if (r2.equals("roundabout") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.ROUNDABOUT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x004d, code lost:
        
            if (r2.equals("continue") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0057, code lost:
        
            if (r2.equals("off ramp") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0061, code lost:
        
            if (r2.equals("rotary") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:5:0x0011, code lost:
        
            if (r2.equals("new name") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x007d, code lost:
        
            if (r2.equals("on ramp") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:6:0x0081, code lost:
        
            if (r3 == null) goto L73;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0087, code lost:
        
            switch(r3.hashCode()) {
                case -2016367553: goto L69;
                case -1531469187: goto L65;
                case -757963388: goto L61;
                case -225243546: goto L57;
                case 3317767: goto L53;
                case 108511772: goto L49;
                case 111623794: goto L45;
                default: goto L73;
            };
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final ManeuverType fromOsrm(String type, String modifier) {
            if (type != null) {
                switch (type.hashCode()) {
                    case -1409157417:
                        if (type.equals("arrive")) {
                            return ManeuverType.ARRIVE;
                        }
                        break;
                    case -1384054349:
                        break;
                    case -1335343116:
                        if (type.equals("depart")) {
                            return ManeuverType.DEPART;
                        }
                        break;
                    case -925180623:
                        break;
                    case -832267229:
                        break;
                    case -567202649:
                        break;
                    case -144622913:
                        break;
                    case 3148994:
                        break;
                    case 3571837:
                        break;
                    case 103785528:
                        break;
                    case 1015318916:
                        break;
                    case 1318962443:
                        break;
                }
            }
            return ManeuverType.CONTINUE;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x002e, code lost:
        
            if (r2.equals("FERRY_TRAIN") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.CONTINUE;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x0037, code lost:
        
            if (r2.equals("RAMP_LEFT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0040, code lost:
        
            if (r2.equals("RAMP_RIGHT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0070, code lost:
        
            if (r2.equals("FORK_LEFT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0079, code lost:
        
            if (r2.equals("ROUNDABOUT_LEFT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.ROUNDABOUT;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0082, code lost:
        
            if (r2.equals("MERGE") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x008b, code lost:
        
            if (r2.equals("FERRY") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0095, code lost:
        
            if (r2.equals("FORK_RIGHT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x009f, code lost:
        
            if (r2.equals("UTURN_RIGHT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:?, code lost:
        
            return com.example.wayfindr.dash.nav.ManeuverType.UTURN;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00b6, code lost:
        
            if (r2.equals("UTURN_LEFT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00c3, code lost:
        
            if (r2.equals("ROUNDABOUT_RIGHT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00cd, code lost:
        
            if (r2.equals("ROUNDABOUT_COUNTERCLOCKWISE") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00e4, code lost:
        
            if (r2.equals("STRAIGHT") == false) goto L78;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00f1, code lost:
        
            if (r2.equals("ROUNDABOUT_CLOCKWISE") == false) goto L78;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final ManeuverType fromGoogle(String maneuver) {
            Intrinsics.checkNotNullParameter(maneuver, "maneuver");
            switch (maneuver.hashCode()) {
                case -1855790062:
                    break;
                case -1724867846:
                    break;
                case -1603297655:
                    if (maneuver.equals("TURN_LEFT")) {
                        return ManeuverType.TURN_LEFT;
                    }
                    return ManeuverType.CONTINUE;
                case -1361376074:
                    break;
                case -1151399812:
                    break;
                case -1127076396:
                    break;
                case -828569467:
                    if (maneuver.equals("TURN_SHARP_RIGHT")) {
                        return ManeuverType.SHARP_RIGHT;
                    }
                    return ManeuverType.CONTINUE;
                case -573968945:
                    break;
                case -108455873:
                    break;
                case 66783482:
                    break;
                case 73247768:
                    break;
                case 101222791:
                    break;
                case 273413476:
                    break;
                case 301643137:
                    if (maneuver.equals("TURN_SLIGHT_LEFT")) {
                        return ManeuverType.SLIGHT_LEFT;
                    }
                    return ManeuverType.CONTINUE;
                case 766663618:
                    if (maneuver.equals("TURN_SLIGHT_RIGHT")) {
                        return ManeuverType.SLIGHT_RIGHT;
                    }
                    return ManeuverType.CONTINUE;
                case 1081467998:
                    if (maneuver.equals("TURN_SHARP_LEFT")) {
                        return ManeuverType.SHARP_LEFT;
                    }
                    return ManeuverType.CONTINUE;
                case 1381342095:
                    break;
                case 1429850132:
                    break;
                case 1565344355:
                    break;
                case 1843041210:
                    if (maneuver.equals("TURN_RIGHT")) {
                        return ManeuverType.TURN_RIGHT;
                    }
                    return ManeuverType.CONTINUE;
                case 2012953588:
                    if (maneuver.equals("DEPART")) {
                        return ManeuverType.DEPART;
                    }
                    return ManeuverType.CONTINUE;
                default:
                    return ManeuverType.CONTINUE;
            }
        }
    }
}
