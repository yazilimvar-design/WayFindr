package com.example.wayfindr.ui.navigation;

import androidx.compose.ui.graphics.vector.ImageVector;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/example/wayfindr/ui/navigation/NavTab;", "", "screen", "Lcom/example/wayfindr/ui/navigation/Screen;", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "<init>", "(Lcom/example/wayfindr/ui/navigation/Screen;Landroidx/compose/ui/graphics/vector/ImageVector;Ljava/lang/String;)V", "getScreen", "()Lcom/example/wayfindr/ui/navigation/Screen;", "getIcon", "()Landroidx/compose/ui/graphics/vector/ImageVector;", "getLabel", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
final /* data */ class NavTab {
    private final ImageVector icon;
    private final String label;
    private final Screen screen;

    public static /* synthetic */ NavTab copy$default(NavTab navTab, Screen screen, ImageVector imageVector, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            screen = navTab.screen;
        }
        if ((i & 2) != 0) {
            imageVector = navTab.icon;
        }
        if ((i & 4) != 0) {
            str = navTab.label;
        }
        return navTab.copy(screen, imageVector, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Screen getScreen() {
        return this.screen;
    }

    /* renamed from: component2, reason: from getter */
    public final ImageVector getIcon() {
        return this.icon;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLabel() {
        return this.label;
    }

    public final NavTab copy(Screen screen, ImageVector icon, String label) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(label, "label");
        return new NavTab(screen, icon, label);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NavTab)) {
            return false;
        }
        NavTab navTab = (NavTab) other;
        return Intrinsics.areEqual(this.screen, navTab.screen) && Intrinsics.areEqual(this.icon, navTab.icon) && Intrinsics.areEqual(this.label, navTab.label);
    }

    public int hashCode() {
        return (((this.screen.hashCode() * 31) + this.icon.hashCode()) * 31) + this.label.hashCode();
    }

    public String toString() {
        return "NavTab(screen=" + this.screen + ", icon=" + this.icon + ", label=" + this.label + ")";
    }

    public NavTab(Screen screen, ImageVector icon, String label) {
        Intrinsics.checkNotNullParameter(screen, "screen");
        Intrinsics.checkNotNullParameter(icon, "icon");
        Intrinsics.checkNotNullParameter(label, "label");
        this.screen = screen;
        this.icon = icon;
        this.label = label;
    }

    public final ImageVector getIcon() {
        return this.icon;
    }

    public final String getLabel() {
        return this.label;
    }

    public final Screen getScreen() {
        return this.screen;
    }
}
