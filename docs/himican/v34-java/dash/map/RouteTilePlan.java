package com.example.wayfindr.dash.map;

import com.example.wayfindr.dash.nav.GeoPoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* compiled from: RouteTilePlan.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÇ\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000e2\b\b\u0002\u0010\u0012\u001a\u00020\u000bJ.\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00162\u0006\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019H\u0002J(\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u001c0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eH\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/example/wayfindr/dash/map/RouteTilePlan;", "", "<init>", "()V", "RIDING_ZOOMS", "Lkotlin/ranges/IntRange;", "getRIDING_ZOOMS", "()Lkotlin/ranges/IntRange;", "OVERVIEW_ZOOMS", "getOVERVIEW_ZOOMS", "RIDING_METERS", "", "SAMPLE_M", "plan", "", "Lcom/example/wayfindr/dash/map/RouteTilePlan$Tile;", "route", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "ridingMeters", "addBand", "", "out", "", "p", "z", "", "radius", "resample", "Lkotlin/Pair;", "Tile", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class RouteTilePlan {
    public static final double RIDING_METERS = 50000.0d;
    private static final double SAMPLE_M = 30.0d;
    public static final RouteTilePlan INSTANCE = new RouteTilePlan();
    private static final IntRange RIDING_ZOOMS = new IntRange(15, 19);
    private static final IntRange OVERVIEW_ZOOMS = new IntRange(11, 14);
    public static final int $stable = 8;

    private RouteTilePlan() {
    }

    public final IntRange getRIDING_ZOOMS() {
        return RIDING_ZOOMS;
    }

    public final IntRange getOVERVIEW_ZOOMS() {
        return OVERVIEW_ZOOMS;
    }

    /* compiled from: RouteTilePlan.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/example/wayfindr/dash/map/RouteTilePlan$Tile;", "", "z", "", "x", "y", "<init>", "(III)V", "getZ", "()I", "getX", "getY", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Tile {
        public static final int $stable = 0;
        private final int x;
        private final int y;
        private final int z;

        public static /* synthetic */ Tile copy$default(Tile tile, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = tile.z;
            }
            if ((i4 & 2) != 0) {
                i2 = tile.x;
            }
            if ((i4 & 4) != 0) {
                i3 = tile.y;
            }
            return tile.copy(i, i2, i3);
        }

        /* renamed from: component1, reason: from getter */
        public final int getZ() {
            return this.z;
        }

        /* renamed from: component2, reason: from getter */
        public final int getX() {
            return this.x;
        }

        /* renamed from: component3, reason: from getter */
        public final int getY() {
            return this.y;
        }

        public final Tile copy(int z, int x, int y) {
            return new Tile(z, x, y);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Tile)) {
                return false;
            }
            Tile tile = (Tile) other;
            return this.z == tile.z && this.x == tile.x && this.y == tile.y;
        }

        public int hashCode() {
            return (((Integer.hashCode(this.z) * 31) + Integer.hashCode(this.x)) * 31) + Integer.hashCode(this.y);
        }

        public String toString() {
            return "Tile(z=" + this.z + ", x=" + this.x + ", y=" + this.y + ")";
        }

        public Tile(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }

        public final int getX() {
            return this.x;
        }

        public final int getY() {
            return this.y;
        }

        public final int getZ() {
            return this.z;
        }
    }

    public static /* synthetic */ List plan$default(RouteTilePlan routeTilePlan, List list, double d, int i, Object obj) {
        if ((i & 2) != 0) {
            d = 50000.0d;
        }
        return routeTilePlan.plan(list, d);
    }

    public final List<Tile> plan(List<GeoPoint> route, double ridingMeters) {
        Intrinsics.checkNotNullParameter(route, "route");
        if (route.size() < 2) {
            return CollectionsKt.emptyList();
        }
        LinkedHashSet out = new LinkedHashSet();
        List samples = resample(route);
        for (Pair<GeoPoint, Double> pair : samples) {
            GeoPoint p = pair.component1();
            double along = pair.component2().doubleValue();
            if (along > ridingMeters) {
                break;
            }
            IntRange intRange = RIDING_ZOOMS;
            int z = intRange.getFirst();
            int last = intRange.getLast();
            if (z <= last) {
                while (true) {
                    addBand(out, p, z, 1);
                    if (z != last) {
                        z++;
                    }
                }
            }
        }
        Iterator<Pair<GeoPoint, Double>> it = samples.iterator();
        while (it.hasNext()) {
            GeoPoint p2 = it.next().component1();
            IntRange intRange2 = OVERVIEW_ZOOMS;
            int z2 = intRange2.getFirst();
            int last2 = intRange2.getLast();
            if (z2 <= last2) {
                while (true) {
                    addBand(out, p2, z2, z2 == 14 ? 1 : 0);
                    if (z2 != last2) {
                        z2++;
                    }
                }
            }
        }
        return CollectionsKt.toList(out);
    }

    private final void addBand(Set<Tile> out, GeoPoint p, int z, int radius) {
        int cx = (int) Mercator.INSTANCE.lngToTileX(p.getLng(), z);
        int cy = (int) Mercator.INSTANCE.latToTileY(p.getLat(), z);
        int max = 1 << z;
        int dx = -radius;
        if (dx > radius) {
            return;
        }
        while (true) {
            int dy = -radius;
            if (dy <= radius) {
                while (true) {
                    int y = cy + dy;
                    boolean z2 = false;
                    if (y >= 0 && y < max) {
                        z2 = true;
                    }
                    if (z2) {
                        out.add(new Tile(z, (((cx + dx) % max) + max) % max, y));
                    }
                    if (dy == radius) {
                        break;
                    } else {
                        dy++;
                    }
                }
            }
            if (dx == radius) {
                return;
            } else {
                dx++;
            }
        }
    }

    private final List<Pair<GeoPoint, Double>> resample(List<GeoPoint> route) {
        ArrayList pts;
        double along;
        List<GeoPoint> list = route;
        ArrayList pts2 = new ArrayList();
        double along2 = 0.0d;
        pts2.add(TuplesKt.to(list.get(0), Double.valueOf(0.0d)));
        int i = 1;
        int size = list.size();
        while (i < size) {
            GeoPoint a = list.get(i - 1);
            GeoPoint b = list.get(i);
            double seg = GeoPoint.INSTANCE.distMeters(a, b);
            double d = SAMPLE_M;
            int n = (int) (seg / SAMPLE_M);
            int k = 1;
            if (1 <= n) {
                while (true) {
                    double f = (k * d) / seg;
                    double d2 = d;
                    pts = pts2;
                    double lat = a.getLat() + ((b.getLat() - a.getLat()) * f);
                    along = along2;
                    double along3 = a.getLng() + ((b.getLng() - a.getLng()) * f);
                    pts2.add(TuplesKt.to(new GeoPoint(lat, along3), Double.valueOf(along + (k * d2))));
                    if (k != n) {
                        k++;
                        d = d2;
                        pts2 = pts;
                        along2 = along;
                    }
                }
            } else {
                pts = pts2;
                along = along2;
            }
            along2 = along + seg;
            pts.add(TuplesKt.to(b, Double.valueOf(along2)));
            i++;
            list = route;
            pts2 = pts;
        }
        return pts2;
    }
}
