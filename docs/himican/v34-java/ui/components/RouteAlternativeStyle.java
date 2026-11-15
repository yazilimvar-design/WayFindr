package com.example.wayfindr.ui.components;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.ranges.RangesKt;

/* compiled from: RouteAlternativeStyle.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0015\u0010\f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/example/wayfindr/ui/components/RouteAlternativeStyle;", "", "<init>", "()V", "hexColors", "", "", "composeColors", "Landroidx/compose/ui/graphics/Color;", "mapLibreHex", "index", "", "composeColor", "composeColor-vNxB06k", "(I)J", "app"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes8.dex */
public final class RouteAlternativeStyle {
    public static final RouteAlternativeStyle INSTANCE = new RouteAlternativeStyle();
    private static final List<String> hexColors = CollectionsKt.listOf((Object[]) new String[]{"#2F80ED", "#F2994A", "#9B51E0"});
    private static final List<Color> composeColors = CollectionsKt.listOf((Object[]) new Color[]{Color.m5084boximpl(ColorKt.Color(4281303277L)), Color.m5084boximpl(ColorKt.Color(4294089034L)), Color.m5084boximpl(ColorKt.Color(4288369120L))});
    public static final int $stable = 8;

    private RouteAlternativeStyle() {
    }

    public final String mapLibreHex(int index) {
        return hexColors.get(RangesKt.coerceIn(index, 0, CollectionsKt.getLastIndex(hexColors)));
    }

    /* renamed from: composeColor-vNxB06k, reason: not valid java name */
    public final long m8356composeColorvNxB06k(int index) {
        return composeColors.get(RangesKt.coerceIn(index, 0, CollectionsKt.getLastIndex(composeColors))).m5104unboximpl();
    }
}
