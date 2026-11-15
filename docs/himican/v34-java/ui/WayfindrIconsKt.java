package com.example.wayfindr.ui;

import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathParser;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* compiled from: WayfindrIcons.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"pathVec", "Landroidx/compose/ui/graphics/vector/ImageVector;", "paths", "", "", "strokeWidth", "", "([Ljava/lang/String;F)Landroidx/compose/ui/graphics/vector/ImageVector;", "app"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class WayfindrIconsKt {
    static /* synthetic */ ImageVector pathVec$default(String[] strArr, float f, int i, Object obj) {
        if ((i & 2) != 0) {
            f = 1.7f;
        }
        return pathVec(strArr, f);
    }

    private static final ImageVector pathVec(String[] paths, float strokeWidth) {
        ImageVector.Builder builder = new ImageVector.Builder(null, Dp.m7908constructorimpl(24), Dp.m7908constructorimpl(24), 24.0f, 24.0f, 0L, 0, false, 225, null);
        String[] strArr = paths;
        int i = 0;
        for (int length = strArr.length; i < length; length = length) {
            ImageVector.Builder.m5825addPathoIyEayM$default(builder, new PathParser().parsePathString(strArr[i]).toNodes(), 0, null, new SolidColor(Color.INSTANCE.m5129getTransparent0d7_KjU(), null), 0.0f, new SolidColor(Color.INSTANCE.m5120getBlack0d7_KjU(), null), 0.0f, strokeWidth, StrokeCap.INSTANCE.m5469getRoundKaPHkGw(), StrokeJoin.INSTANCE.m5480getRoundLxFBmk8(), 0.0f, 0.0f, 0.0f, 0.0f, 15446, null);
            i++;
            strArr = strArr;
        }
        return builder.build();
    }
}
