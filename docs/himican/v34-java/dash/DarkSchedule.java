package com.example.wayfindr.dash;

import java.util.Calendar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: DarkSchedule.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005J\u0006\u0010\n\u001a\u00020\bJ\u0010\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eJ \u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\b2\b\b\u0002\u0010\u0012\u001a\u00020\bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/example/wayfindr/dash/DarkSchedule;", "", "<init>", "()V", "DARK_FROM_HOUR", "", "DARK_UNTIL_HOUR", "isNight", "", "hourOfDay", "isNightNow", "msUntilNextChange", "", "now", "Ljava/util/Calendar;", "effective", "auto", "manualDark", "night", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class DarkSchedule {
    public static final int $stable = 0;
    public static final int DARK_FROM_HOUR = 19;
    public static final int DARK_UNTIL_HOUR = 5;
    public static final DarkSchedule INSTANCE = new DarkSchedule();

    private DarkSchedule() {
    }

    public final boolean isNight(int hourOfDay) {
        return hourOfDay >= 19 || hourOfDay < 5;
    }

    public final boolean isNightNow() {
        return isNight(Calendar.getInstance().get(11));
    }

    public static /* synthetic */ long msUntilNextChange$default(DarkSchedule darkSchedule, Calendar calendar, int i, Object obj) {
        if ((i & 1) != 0) {
            calendar = Calendar.getInstance();
            Intrinsics.checkNotNullExpressionValue(calendar, "getInstance(...)");
        }
        return darkSchedule.msUntilNextChange(calendar);
    }

    public final long msUntilNextChange(Calendar now) {
        Intrinsics.checkNotNullParameter(now, "now");
        Object clone = now.clone();
        Intrinsics.checkNotNull(clone, "null cannot be cast to non-null type java.util.Calendar");
        Calendar next = (Calendar) clone;
        next.set(12, 0);
        next.set(13, 0);
        next.set(14, 0);
        int i = now.get(11);
        if (i < 5) {
            next.set(11, 5);
        } else if (i < 19) {
            next.set(11, 19);
        } else {
            next.add(6, 1);
            next.set(11, 5);
        }
        return RangesKt.coerceAtLeast(next.getTimeInMillis() - now.getTimeInMillis(), 0L);
    }

    public static /* synthetic */ boolean effective$default(DarkSchedule darkSchedule, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 4) != 0) {
            z3 = darkSchedule.isNightNow();
        }
        return darkSchedule.effective(z, z2, z3);
    }

    public final boolean effective(boolean auto, boolean manualDark, boolean night) {
        return auto ? night : manualDark;
    }
}
