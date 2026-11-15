package com.example.wayfindr.dash.nav;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TurnAnnouncer.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001aB/\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0010\u001a\u00020\u0011J \u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003J\u0010\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u0015H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/example/wayfindr/dash/nav/TurnAnnouncer;", "", "farM", "", "nearM", "arriveM", "arriveRearmM", "<init>", "(DDDD)V", "farDone", "Ljava/util/HashSet;", "", "Lkotlin/collections/HashSet;", "nearDone", "arrived", "", "reset", "", "onTick", "Lcom/example/wayfindr/dash/nav/TurnAnnouncer$Event;", "maneuver", "Lcom/example/wayfindr/dash/nav/Maneuver;", "distanceM", "remainingM", "keyOf", "m", "Event", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class TurnAnnouncer {
    public static final int $stable = 8;
    private final double arriveM;
    private final double arriveRearmM;
    private boolean arrived;
    private final HashSet<String> farDone;
    private final double farM;
    private final HashSet<String> nearDone;
    private final double nearM;

    /* compiled from: TurnAnnouncer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/example/wayfindr/dash/nav/TurnAnnouncer$Event;", "", "<init>", "(Ljava/lang/String;I)V", "NONE", "FAR", "NEAR", "ARRIVE", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum Event {
        NONE,
        FAR,
        NEAR,
        ARRIVE;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

        public static EnumEntries<Event> getEntries() {
            return $ENTRIES;
        }
    }

    public TurnAnnouncer() {
        this(0.0d, 0.0d, 0.0d, 0.0d, 15, null);
    }

    public TurnAnnouncer(double farM, double nearM, double arriveM, double arriveRearmM) {
        this.farM = farM;
        this.nearM = nearM;
        this.arriveM = arriveM;
        this.arriveRearmM = arriveRearmM;
        this.farDone = new HashSet<>();
        this.nearDone = new HashSet<>();
    }

    public /* synthetic */ TurnAnnouncer(double d, double d2, double d3, double d4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 450.0d : d, (i & 2) != 0 ? 60.0d : d2, (i & 4) != 0 ? 30.0d : d3, (i & 8) != 0 ? 250.0d : d4);
    }

    public final void reset() {
        this.farDone.clear();
        this.nearDone.clear();
        this.arrived = false;
    }

    public final Event onTick(Maneuver maneuver, double distanceM, double remainingM) {
        if (0.0d <= remainingM && remainingM <= this.arriveM) {
            if (this.arrived) {
                return Event.NONE;
            }
            this.arrived = true;
            return Event.ARRIVE;
        }
        if (remainingM > this.arriveRearmM) {
            this.arrived = false;
        }
        if (maneuver == null || maneuver.getType() == ManeuverType.ARRIVE) {
            return Event.NONE;
        }
        String key = keyOf(maneuver);
        if (distanceM < this.nearM) {
            this.farDone.add(key);
            return this.nearDone.add(key) ? Event.NEAR : Event.NONE;
        }
        if (distanceM <= this.farM) {
            return this.farDone.add(key) ? Event.FAR : Event.NONE;
        }
        return Event.NONE;
    }

    private final String keyOf(Maneuver m) {
        String format = String.format(Locale.ROOT, "%.4f,%.4f,%s", Arrays.copyOf(new Object[]{Double.valueOf(m.getLocation().getLat()), Double.valueOf(m.getLocation().getLng()), m.getType()}, 3));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }
}
