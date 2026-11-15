package com.example.wayfindr.ui.navigation;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: AppNavigation.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u00002\u00020\u0001:\u0005\b\t\n\u000b\fB\u0011\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0005\r\u000e\u000f\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/example/wayfindr/ui/navigation/Screen;", "", "route", "", "<init>", "(Ljava/lang/String;)V", "getRoute", "()Ljava/lang/String;", "Home", "Route", "Dash", "Rides", "Settings", "Lcom/example/wayfindr/ui/navigation/Screen$Dash;", "Lcom/example/wayfindr/ui/navigation/Screen$Home;", "Lcom/example/wayfindr/ui/navigation/Screen$Rides;", "Lcom/example/wayfindr/ui/navigation/Screen$Route;", "Lcom/example/wayfindr/ui/navigation/Screen$Settings;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public abstract class Screen {
    public static final int $stable = 0;
    private final String route;

    public /* synthetic */ Screen(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/ui/navigation/Screen$Home;", "Lcom/example/wayfindr/ui/navigation/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Home extends Screen {
        public static final int $stable = 0;
        public static final Home INSTANCE = new Home();

        private Home() {
            super("home", null);
        }
    }

    private Screen(String route) {
        this.route = route;
    }

    public final String getRoute() {
        return this.route;
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/ui/navigation/Screen$Route;", "Lcom/example/wayfindr/ui/navigation/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Route extends Screen {
        public static final int $stable = 0;
        public static final Route INSTANCE = new Route();

        private Route() {
            super("route", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/ui/navigation/Screen$Dash;", "Lcom/example/wayfindr/ui/navigation/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Dash extends Screen {
        public static final int $stable = 0;
        public static final Dash INSTANCE = new Dash();

        private Dash() {
            super("dash", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/ui/navigation/Screen$Rides;", "Lcom/example/wayfindr/ui/navigation/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Rides extends Screen {
        public static final int $stable = 0;
        public static final Rides INSTANCE = new Rides();

        private Rides() {
            super("rides", null);
        }
    }

    /* compiled from: AppNavigation.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/example/wayfindr/ui/navigation/Screen$Settings;", "Lcom/example/wayfindr/ui/navigation/Screen;", "<init>", "()V", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Settings extends Screen {
        public static final int $stable = 0;
        public static final Settings INSTANCE = new Settings();

        private Settings() {
            super("settings", null);
        }
    }
}
