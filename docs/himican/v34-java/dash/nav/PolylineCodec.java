package com.example.wayfindr.dash.nav;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PolylineCodec.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u001e\u0010\u000b\u001a\u00020\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\t\u001a\u00020\nJ\u001c\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013H\u0002¨\u0006\u0014"}, d2 = {"Lcom/example/wayfindr/dash/nav/PolylineCodec;", "", "<init>", "()V", "decode", "", "Lcom/example/wayfindr/dash/nav/GeoPoint;", "encoded", "", "precision", "", "encode", "points", "encodeDelta", "", "v", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class PolylineCodec {
    public static final int $stable = 0;
    public static final PolylineCodec INSTANCE = new PolylineCodec();

    private PolylineCodec() {
    }

    public static /* synthetic */ List decode$default(PolylineCodec polylineCodec, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 5;
        }
        return polylineCodec.decode(str, i);
    }

    public final List<GeoPoint> decode(String encoded, int precision) {
        int index;
        int index2;
        String encoded2 = encoded;
        Intrinsics.checkNotNullParameter(encoded2, "encoded");
        double factor = Math.pow(10.0d, precision);
        ArrayList points = new ArrayList();
        int index3 = 0;
        int lat = 0;
        int lng = 0;
        int len = encoded2.length();
        while (index3 < len) {
            int shift = 0;
            int result = 0;
            while (true) {
                index = index3 + 1;
                int b = encoded2.charAt(index3) - 63;
                result |= (b & 31) << shift;
                shift += 5;
                if (b < 32 || index >= len) {
                    break;
                }
                index3 = index;
            }
            lat += (result & 1) != 0 ? ~(result >> 1) : result >> 1;
            if (index >= len) {
                break;
            }
            int shift2 = 0;
            int result2 = 0;
            while (true) {
                index2 = index + 1;
                int b2 = encoded2.charAt(index) - 63;
                result2 |= (b2 & 31) << shift2;
                shift2 += 5;
                if (b2 < 32 || index2 >= len) {
                    break;
                }
                index = index2;
            }
            lng += (result2 & 1) != 0 ? ~(result2 >> 1) : result2 >> 1;
            double factor2 = factor;
            points.add(new GeoPoint(lat / factor, lng / factor2));
            encoded2 = encoded;
            index3 = index2;
            factor = factor2;
        }
        return points;
    }

    public static /* synthetic */ String encode$default(PolylineCodec polylineCodec, List list, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 5;
        }
        return polylineCodec.encode(list, i);
    }

    public final String encode(List<GeoPoint> points, int precision) {
        Intrinsics.checkNotNullParameter(points, "points");
        double factor = Math.pow(10.0d, precision);
        StringBuilder sb = new StringBuilder();
        long lastLat = 0;
        long lastLng = 0;
        for (GeoPoint p : points) {
            long lat = Math.round(p.getLat() * factor);
            long lng = Math.round(p.getLng() * factor);
            encodeDelta(lat - lastLat, sb);
            encodeDelta(lng - lastLng, sb);
            lastLat = lat;
            lastLng = lng;
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "toString(...)");
        return sb2;
    }

    private final void encodeDelta(long v, StringBuilder sb) {
        long value = v < 0 ? ~(v << 1) : v << 1;
        while (value >= 32) {
            sb.append((char) ((((int) (31 & value)) | 32) + 63));
            value >>= 5;
        }
        sb.append((char) (((int) value) + 63));
    }
}
