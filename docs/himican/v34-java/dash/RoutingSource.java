package com.example.wayfindr.dash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: DashConfig.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lcom/example/wayfindr/dash/RoutingSource;", "", "label", "", "<init>", "(Ljava/lang/String;ILjava/lang/String;)V", "getLabel", "()Ljava/lang/String;", "GOOGLE", "OSM", "BROUTER", "Companion", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public enum RoutingSource {
    GOOGLE("Google"),
    OSM("OSM"),
    BROUTER("BRouter");

    private final String label;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public static EnumEntries<RoutingSource> getEntries() {
        return $ENTRIES;
    }

    RoutingSource(String label) {
        this.label = label;
    }

    public final String getLabel() {
        return this.label;
    }

    /* compiled from: DashConfig.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/example/wayfindr/dash/RoutingSource$Companion;", "", "<init>", "()V", "available", "", "Lcom/example/wayfindr/dash/RoutingSource;", "getAvailable", "()Ljava/util/List;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<RoutingSource> getAvailable() {
            RoutingSource[] values = RoutingSource.values();
            Collection arrayList = new ArrayList();
            int length = values.length;
            for (int i = 0; i < length; i++) {
                RoutingSource routingSource = values[i];
                if (routingSource != RoutingSource.GOOGLE || GoogleKey.INSTANCE.getPresent()) {
                    arrayList.add(routingSource);
                }
            }
            return (List) arrayList;
        }
    }
}
