package com.example.wayfindr.dash.map;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.example.wayfindr.dash.RoutingSource;
import com.example.wayfindr.dash.nav.GPlus;
import com.example.wayfindr.dash.nav.GeoPoint;
import com.example.wayfindr.dash.nav.NavPlus;
import com.example.wayfindr.dash.nav.TrafficInterval;
import com.example.wayfindr.data.RideDiagnostics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: MapRenderer.kt */
/* loaded from: /home/claude/dec/c2in/classes2.dex */
public final class MapRenderer {
    private static final float ETA_ANGLE_DEG = 55.0f;
    private static final float ETA_MARGIN_BOTTOM = 8.0f;
    private static final float ETA_MARGIN_RIGHT = 24.0f;
    private static final float NOTICE_GAP_ABOVE_CENTRE = 22.0f;
    private static final float NOTICE_W = 340.0f;
    private static final float PICKER_LEFT = 160.0f;
    private static final int PICKER_ROWS = 4;
    private static final float PICKER_W = 380.0f;
    private static final float SAFE_RADIUS_FRAC = 0.92f;
    private final Paint attributionPaint;
    private final Paint bannerPaint;
    private final int batteryYellow;
    private final Paint boltPaint;
    private final Path boltPath;
    private final RectF childDst;
    private final int darkBg;
    private final ColorMatrixColorFilter darkTileFilter;
    private final Paint dotPaint;
    private final Paint etaBgPaint;
    private final Paint etaBigPaint;
    private final Paint etaBorderPaint;
    private final Paint etaSmallPaint;
    private final Set failedOverlays;
    private final int googleRed;
    private final Paint gpsPillText;
    private final int lightBg;
    private final ColorMatrixColorFilter lightTileFilter;
    private final Paint pickerBgPaint;
    private final Paint pickerHiPaint;
    private final Paint pickerHintPaint;
    private final Paint pickerItemPaint;
    private final Paint pickerMarkPaint;
    private final RectF pickerRow;
    private final Paint pickerSectionPaint;
    private final Paint pickerTitlePaint;
    private final RectF pillRect;
    private final Path riderPath;
    private final int routeBlue;
    private final Paint routeCasing;
    private final Paint routePaint;
    private final Path routePath;
    private final Paint routingBadgePaint;
    private final Paint standbyPaint;
    private final Paint subTextPaint;
    private final Rect textBounds;
    private final Paint textPaint;
    private final Paint tilePaint;
    private final Rect tileSrc;
    private final TileProvider tiles;
    private final float[] tiltDst;
    private final Matrix tiltMatrix;
    private final float[] tiltSrc;
    private final RectF tmpRect;
    private final int trafficAmber;
    private final int trafficRed;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int $stable = 8;
    private static final int ROUTING_GREEN = Color.rgb(30, 142, 62);
    private static final int ROUTING_ORANGE = Color.rgb(239, 108, 0);
    private static final int ROUTING_BROWN = Color.rgb(121, 85, 72);
    private static final int ACTIVE_GREEN = Color.rgb(96, 205, 128);

    public MapRenderer(TileProvider tileProvider) {
        Intrinsics.checkNotNullParameter(tileProvider, "tiles");
        this.tiles = tileProvider;
        this.lightBg = Color.rgb(229, 227, 223);
        this.darkBg = Color.rgb(22, 28, 36);
        this.routeBlue = Color.rgb(66, 133, 244);
        this.trafficAmber = Color.rgb(251, 188, 5);
        this.trafficRed = Color.rgb(234, 67, 53);
        this.googleRed = Color.rgb(234, 67, 53);
        this.batteryYellow = Color.rgb(251, 188, 5);
        this.tilePaint = new Paint(2);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(1.2f);
        this.lightTileFilter = new ColorMatrixColorFilter(colorMatrix);
        this.darkTileFilter = new ColorMatrixColorFilter(Companion.access$nightTileMatrix(Companion));
        Paint paint = new Paint(1);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(11.0f);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        this.routeCasing = paint;
        Paint paint2 = new Paint(1);
        paint2.setColor(this.routeBlue);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(6.0f);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        this.routePaint = paint2;
        this.dotPaint = new Paint(1);
        Paint paint3 = new Paint(1);
        paint3.setColor(-1);
        paint3.setTextSize(NOTICE_GAP_ABOVE_CENTRE);
        paint3.setFakeBoldText(true);
        this.textPaint = paint3;
        Paint paint4 = new Paint(1);
        paint4.setColor(this.routeBlue);
        paint4.setTextSize(19.0f);
        paint4.setFakeBoldText(true);
        this.subTextPaint = paint4;
        Paint paint5 = new Paint(1);
        paint5.setColor(Color.argb(215, 13, 15, 17));
        this.bannerPaint = paint5;
        Paint paint6 = new Paint(1);
        paint6.setColor(Color.rgb(60, 64, 67));
        paint6.setTextSize(NOTICE_GAP_ABOVE_CENTRE);
        paint6.setFakeBoldText(true);
        this.standbyPaint = paint6;
        Paint paint7 = new Paint(1);
        paint7.setColor(Color.argb(232, 20, 22, 26));
        this.etaBgPaint = paint7;
        Paint paint8 = new Paint(1);
        paint8.setColor(Color.argb(46, 255, 255, 255));
        paint8.setStyle(Paint.Style.STROKE);
        paint8.setStrokeWidth(1.5f);
        this.etaBorderPaint = paint8;
        Paint paint9 = new Paint(1);
        paint9.setColor(Color.rgb(126, 217, 87));
        paint9.setTextSize(20.4f);
        paint9.setFakeBoldText(true);
        paint9.setTextAlign(Paint.Align.CENTER);
        this.etaBigPaint = paint9;
        this.failedOverlays = Collections.synchronizedSet(new HashSet());
        Paint paint10 = new Paint(1);
        paint10.setColor(Color.rgb(230, 232, 234));
        paint10.setTextSize(11.0f);
        paint10.setTextAlign(Paint.Align.CENTER);
        this.attributionPaint = paint10;
        this.boltPath = new Path();
        Paint paint11 = new Paint(1);
        paint11.setColor(Color.rgb(126, 217, 87));
        this.boltPaint = paint11;
        Paint paint12 = new Paint(1);
        paint12.setColor(Color.rgb(196, 201, 208));
        paint12.setTextSize(16.0f);
        paint12.setTextAlign(Paint.Align.CENTER);
        this.etaSmallPaint = paint12;
        Paint paint13 = new Paint(1);
        paint13.setTextSize(13.0f);
        paint13.setFakeBoldText(true);
        paint13.setTextAlign(Paint.Align.CENTER);
        this.gpsPillText = paint13;
        this.routePath = new Path();
        this.riderPath = new Path();
        this.tmpRect = new RectF();
        this.pillRect = new RectF();
        Paint paint14 = new Paint(1);
        paint14.setColor(Color.argb(238, 20, 22, 26));
        this.pickerBgPaint = paint14;
        Paint paint15 = new Paint(1);
        paint15.setColor(Color.rgb(232, 183, 72));
        this.pickerHiPaint = paint15;
        Paint paint16 = new Paint(1);
        paint16.setColor(Color.rgb(150, 156, 164));
        paint16.setTextSize(16.0f);
        paint16.setTextAlign(Paint.Align.CENTER);
        this.pickerTitlePaint = paint16;
        Paint paint17 = new Paint(1);
        paint17.setColor(Color.rgb(236, 238, 241));
        paint17.setTextSize(26.0f);
        this.pickerItemPaint = paint17;
        Paint paint18 = new Paint(1);
        paint18.setColor(Color.rgb(150, 156, 164));
        paint18.setTextSize(16.0f);
        paint18.setTextAlign(Paint.Align.CENTER);
        this.pickerHintPaint = paint18;
        this.pickerRow = new RectF();
        this.routingBadgePaint = new Paint(1);
        Paint paint19 = new Paint(1);
        paint19.setColor(Color.rgb(150, 156, 164));
        paint19.setTextSize(17.0f);
        paint19.setFakeBoldText(true);
        this.pickerSectionPaint = paint19;
        Paint paint20 = new Paint(1);
        paint20.setTextSize(26.0f);
        paint20.setTextAlign(Paint.Align.RIGHT);
        paint20.setFakeBoldText(true);
        this.pickerMarkPaint = paint20;
        this.tileSrc = new Rect();
        this.childDst = new RectF();
        this.textBounds = new Rect();
        this.tiltMatrix = new Matrix();
        this.tiltSrc = new float[8];
        this.tiltDst = new float[8];
    }

    public static /* synthetic */ void draw$default(MapRenderer mapRenderer, Canvas canvas, Frame frame, int i, int i2, int i3, Object obj) {
        if ((i3 & PICKER_ROWS) != 0) {
            i = canvas.getWidth();
        }
        if ((i3 & 8) != 0) {
            i2 = canvas.getHeight();
        }
        mapRenderer.draw(canvas, frame, i, i2);
    }

    private static final float draw$sx(Frame frame, int i, double d, double d2) {
        return (float) ((Mercator.INSTANCE.lngToTileX(d2, frame.getZoom()) * i) - d);
    }

    private static final float draw$sy(Frame frame, int i, double d, double d2) {
        return (float) ((Mercator.INSTANCE.latToTileY(d2, frame.getZoom()) * i) - d);
    }

    private final void drawNotice(Canvas canvas, int i, int i2, List list) {
        float f;
        float f2;
        int i3;
        if (list.isEmpty()) {
            return;
        }
        float f3 = 30.0f;
        char c = 0;
        char c2 = 0;
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        int i4 = 0;
        for (Object obj : list2) {
            int i5 = i4 + 1;
            if (i4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            float f4 = f3;
            this.pickerItemPaint.setFakeBoldText(i4 == 0);
            arrayList.add(ellipsize(str, this.pickerItemPaint, 316.0f));
            i4 = i5;
            f3 = f4;
        }
        float f5 = f3;
        ArrayList arrayList2 = arrayList;
        float f6 = 0.0f;
        int i6 = 0;
        for (Object obj2 : arrayList2) {
            int i7 = i6 + 1;
            if (i6 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str2 = (String) obj2;
            this.pickerItemPaint.setFakeBoldText(i6 == 0);
            f6 = Math.max(f6, this.pickerItemPaint.measureText(str2));
            i6 = i7;
        }
        float f7 = ETA_MARGIN_RIGHT + f6;
        float size = (ETA_MARGIN_BOTTOM * 2.0f) + (arrayList2.size() * f5);
        float f8 = i / 2.0f;
        float f9 = (i2 / 2.0f) - NOTICE_GAP_ABOVE_CENTRE;
        this.pillRect.set(f8 - (f7 / 2.0f), f9 - size, f8 + (f7 / 2.0f), f9);
        canvas.drawRoundRect(this.pillRect, 10.0f, 10.0f, this.pickerBgPaint);
        canvas.drawRoundRect(this.pillRect, 10.0f, 10.0f, this.etaBorderPaint);
        Paint.Align textAlign = this.pickerItemPaint.getTextAlign();
        this.pickerItemPaint.setTextAlign(Paint.Align.CENTER);
        float f10 = this.pillRect.top + ETA_MARGIN_BOTTOM;
        int i8 = 0;
        for (Object obj3 : arrayList2) {
            int i9 = i8 + 1;
            if (i8 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ArrayList arrayList3 = arrayList2;
            String str3 = (String) obj3;
            char c3 = c;
            char c4 = c2;
            this.pickerItemPaint.setFakeBoldText(i8 == 0);
            Paint paint = this.pickerItemPaint;
            if (i8 == 0) {
                f = f6;
                f2 = f7;
                i3 = Color.rgb(236, 238, 241);
            } else {
                f = f6;
                f2 = f7;
                i3 = ACTIVE_GREEN;
            }
            paint.setColor(i3);
            canvas.drawText(str3, f8, ((f5 / 2.0f) + f10) - ((this.pickerItemPaint.descent() + this.pickerItemPaint.ascent()) / 2.0f), this.pickerItemPaint);
            f10 += f5;
            i8 = i9;
            arrayList2 = arrayList3;
            c = c3;
            c2 = c4;
            f6 = f;
            f7 = f2;
        }
        this.pickerItemPaint.setTextAlign(textAlign);
        this.pickerItemPaint.setFakeBoldText(false);
    }

    private final void drawPicker(Canvas canvas, int i, int i2, String str, List list, int i3, String str2) {
        float f;
        float f2;
        int i4;
        float f3;
        int rgb;
        int min = Math.min(PICKER_ROWS, list.size());
        float f4 = (i / 2.0f) + 190.0f;
        float f5 = f4 - PICKER_LEFT;
        float f6 = 6.0f + 28.0f + (min * 40.0f) + 26.0f + 6.0f;
        float f7 = (PICKER_LEFT + f4) / 2.0f;
        float f8 = i2 / 2.0f;
        this.pillRect.set(PICKER_LEFT, f8 - (f6 / 2.0f), f4, f8 + (f6 / 2.0f));
        canvas.drawRoundRect(this.pillRect, 12.0f, 12.0f, this.pickerBgPaint);
        canvas.drawRoundRect(this.pillRect, 12.0f, 12.0f, this.etaBorderPaint);
        float f9 = this.pillRect.top + 6.0f;
        canvas.drawText(str, f7, ((28.0f / 2.0f) + f9) - ((this.pickerTitlePaint.descent() + this.pickerTitlePaint.ascent()) / 2.0f), this.pickerTitlePaint);
        float f10 = f9 + 28.0f;
        int coerceIn = RangesKt.coerceIn(i3, 0, CollectionsKt.getLastIndex(list));
        int coerceIn2 = RangesKt.coerceIn(coerceIn - (min / 2), 0, Math.max(0, list.size() - min));
        float f11 = this.pillRect.left + 16.0f;
        float f12 = f5 - 32.0f;
        int i5 = coerceIn2 + min;
        int i6 = coerceIn2;
        while (i6 < i5) {
            int i7 = i5;
            MenuRow menuRow = (MenuRow) list.get(i6);
            if (menuRow.getHeader()) {
                float descent = (f10 + (40.0f / 2.0f)) - ((this.pickerSectionPaint.descent() + this.pickerSectionPaint.ascent()) / 2.0f);
                f2 = f4;
                String upperCase = menuRow.getLabel().toUpperCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                f = f12;
                canvas.drawText(ellipsize(upperCase, this.pickerSectionPaint, f12), f11 - 6.0f, descent, this.pickerSectionPaint);
                f10 += 40.0f;
                i4 = coerceIn;
                f3 = f11;
            } else {
                f = f12;
                f2 = f4;
                i4 = coerceIn;
                boolean z = i6 == coerceIn;
                this.pickerRow.set(this.pillRect.left + 5.0f, f10 + 1.0f, this.pillRect.right - 5.0f, (f10 + 40.0f) - 1.0f);
                if (z) {
                    canvas.drawRoundRect(this.pickerRow, 7.0f, 7.0f, this.pickerHiPaint);
                }
                this.pickerItemPaint.setColor(z ? Color.rgb(20, 22, 26) : menuRow.getActive() ? ACTIVE_GREEN : Color.rgb(236, 238, 241));
                this.pickerItemPaint.setFakeBoldText(z || menuRow.getActive());
                String str3 = menuRow.getActive() ? "✓" : menuRow.getSubmenu() ? "›" : null;
                float f13 = str3 != null ? 28.0f : 0.0f;
                float descent2 = (f10 + (40.0f / 2.0f)) - ((this.pickerItemPaint.descent() + this.pickerItemPaint.ascent()) / 2.0f);
                canvas.drawText(ellipsize(menuRow.getLabel(), this.pickerItemPaint, f - f13), f11, descent2, this.pickerItemPaint);
                if (str3 != null) {
                    Paint paint = this.pickerMarkPaint;
                    if (z) {
                        f3 = f11;
                        rgb = Color.rgb(20, 22, 26);
                    } else {
                        f3 = f11;
                        rgb = menuRow.getActive() ? ACTIVE_GREEN : Color.rgb(150, 156, 164);
                    }
                    paint.setColor(rgb);
                    canvas.drawText(str3, this.pillRect.right - 16.0f, descent2, this.pickerMarkPaint);
                } else {
                    f3 = f11;
                }
                f10 += 40.0f;
            }
            i6++;
            i5 = i7;
            f4 = f2;
            f12 = f;
            f11 = f3;
            coerceIn = i4;
        }
        canvas.drawText(ellipsize(str2, this.pickerHintPaint, f5 - 10.0f), f7, (f10 + (26.0f / 2.0f)) - ((this.pickerHintPaint.descent() + this.pickerHintPaint.ascent()) / 2.0f), this.pickerHintPaint);
    }

    private final void drawTileBestEffort(Canvas canvas, int i, int i2, int i3, RectF rectF) {
        Object obj;
        int i4 = i;
        Bitmap bitmap = this.tiles.get(i4, i2, i3);
        Object obj2 = null;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, rectF, this.tilePaint);
            return;
        }
        for (int i5 = 1; i5 <= PICKER_ROWS && i4 - i5 >= 0; i5++) {
            Bitmap cached = this.tiles.getCached(i4 - i5, i2 >> i5, i3 >> i5);
            if (cached != null) {
                int i6 = 1 << i5;
                int width = cached.getWidth() / i6;
                int i7 = ((i6 - 1) & i2) * width;
                int i8 = ((i6 - 1) & i3) * width;
                this.tileSrc.set(i7, i8, i7 + width, i8 + width);
                canvas.drawBitmap(cached, this.tileSrc, rectF, this.tilePaint);
                return;
            }
        }
        float f = (rectF.right - rectF.left) / 2.0f;
        float f2 = (rectF.bottom - rectF.top) / 2.0f;
        int i9 = 0;
        while (true) {
            if (i9 >= 2) {
                return;
            }
            int i10 = 0;
            for (int i11 = 2; i10 < i11; i11 = 2) {
                Bitmap cached2 = this.tiles.getCached(i4 + 1, (i2 * 2) + i9, (i3 * 2) + i10);
                if (cached2 == null) {
                    obj = obj2;
                } else {
                    this.childDst.set(rectF.left + (i9 * f), rectF.top + (i10 * f2), rectF.left + (i9 * f) + f, rectF.top + (i10 * f2) + f2);
                    obj = null;
                    canvas.drawBitmap(cached2, (Rect) null, this.childDst, this.tilePaint);
                }
                i10++;
                i4 = i;
                obj2 = obj;
            }
            i9++;
            i4 = i;
        }
    }

    private final String ellipsize(String str, Paint paint, float f) {
        if (paint.measureText(str) <= f) {
            return str;
        }
        int length = str.length();
        while (length > 1 && paint.measureText(str, 0, length) + paint.measureText("…") > f) {
            length--;
        }
        String substring = str.substring(0, length);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring + "…";
    }

    private final float normalizeHeading(float f) {
        float f2 = f % 360.0f;
        return f2 < 0.0f ? f2 + 360.0f : f2;
    }

    private final void overlay(String str, Function0 function0) {
        try {
            function0.invoke();
        } catch (Exception e) {
            if (this.failedOverlays.add(str)) {
                RideDiagnostics.INSTANCE.log("render", "overlay '" + str + "' failed: " + e);
            }
        }
    }

    private final void placeInSafeCircle(int i, int i2, float f, float f2, float f3, RectF rectF) {
        float f4;
        float f5 = 2.0f;
        float f6 = i / 2.0f;
        float f7 = i2 / 2.0f;
        float f8 = (i2 / 2.0f) * SAFE_RADIUS_FRAC;
        float cos = (float) Math.cos(Math.toRadians(f3));
        float sin = (float) Math.sin(Math.toRadians(f3));
        float f9 = 0.0f;
        float f10 = 0.0f;
        while (true) {
            if (f10 > f8) {
                f4 = f5;
                break;
            }
            float f11 = (cos * f10) + f6;
            float f12 = (sin * f10) + f7;
            float f13 = f11 - (f / f5);
            float f14 = f12 - (f2 / f5);
            float f15 = (f / f5) + f11;
            f4 = f5;
            float f16 = f12 + (f2 / f5);
            if (!placeInSafeCircle$inside(f6, f7, f8, f13, f14) || !placeInSafeCircle$inside(f6, f7, f8, f15, f14) || !placeInSafeCircle$inside(f6, f7, f8, f13, f16) || !placeInSafeCircle$inside(f6, f7, f8, f15, f16)) {
                break;
            }
            f9 = f10;
            f10 += 1.0f;
            f5 = f4;
        }
        float f17 = (cos * f9) + f6;
        float f18 = (sin * f9) + f7;
        rectF.set(f17 - (f / f4), f18 - (f2 / f4), (f / f4) + f17, (f2 / f4) + f18);
    }

    private static final boolean placeInSafeCircle$inside(float f, float f2, float f3, float f4, float f5) {
        return ((f4 - f) * (f4 - f)) + ((f5 - f2) * (f5 - f2)) <= f3 * f3;
    }

    /* JADX WARN: Removed duplicated region for block: B:153:0x0756 A[Catch: Exception -> 0x07c5, TRY_LEAVE, TryCatch #18 {Exception -> 0x07c5, blocks: (B:151:0x0750, B:153:0x0756), top: B:150:0x0750 }] */
    /* JADX WARN: Removed duplicated region for block: B:162:0x07fc A[Catch: Exception -> 0x0865, TRY_LEAVE, TryCatch #17 {Exception -> 0x0865, blocks: (B:160:0x07f6, B:162:0x07fc), top: B:159:0x07f6 }] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x089e A[Catch: Exception -> 0x0a17, TRY_LEAVE, TryCatch #4 {Exception -> 0x0a17, blocks: (B:168:0x0898, B:170:0x089e), top: B:167:0x0898 }] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0a51 A[Catch: Exception -> 0x0ad0, TRY_LEAVE, TryCatch #0 {Exception -> 0x0ad0, blocks: (B:198:0x0a4b, B:200:0x0a51), top: B:197:0x0a4b }] */
    /* JADX WARN: Removed duplicated region for block: B:214:0x0b08 A[Catch: Exception -> 0x0c11, TRY_LEAVE, TryCatch #5 {Exception -> 0x0c11, blocks: (B:212:0x0b02, B:214:0x0b08, B:223:0x0b4d, B:276:0x0b41), top: B:211:0x0b02 }] */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0c44  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0cbb  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x0cce  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x0cef  */
    /* JADX WARN: Removed duplicated region for block: B:251:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:252:0x0ce7  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0cc6  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x0c50  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x0c5d  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x0c60  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0c53  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x0c08  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x0ac8  */
    /* JADX WARN: Removed duplicated region for block: B:315:0x0a0d  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x085f  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x07be  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void draw(Canvas canvas, Frame frame, int i, int i2) {
        int i3;
        int i4;
        float f;
        int i5;
        int i6;
        Canvas canvas2;
        String str;
        boolean z;
        int i7;
        double d;
        double d2;
        MapRenderer mapRenderer;
        float f2;
        float f3;
        float f4;
        String str2;
        float f5;
        float f6;
        List notice;
        int i8;
        List pickerRows;
        MapRenderer mapRenderer2;
        Canvas canvas3;
        RoutingSource routingBadge;
        Pair pair;
        float f7;
        float f8;
        String zoomLabel;
        Pair phoneBattery;
        String downHint;
        String joystickTrail;
        float f9;
        float f10;
        Path path;
        List list;
        String str3;
        double d3;
        boolean z2;
        boolean z3;
        int i9;
        int i10;
        int i11;
        String str4 = "render";
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(frame, "f");
        if (NavPlus.phDraw(canvas, frame, i, i2)) {
            return;
        }
        canvas.drawColor(frame.getDark() ? this.darkBg : this.lightBg);
        this.tilePaint.setColorFilter(frame.getDark() ? this.darkTileFilter : this.lightTileFilter);
        this.routeCasing.setColor(frame.getDark() ? Color.rgb(18, 22, 28) : -1);
        this.standbyPaint.setColor(frame.getDark() ? Color.rgb(186, 192, 200) : Color.rgb(60, 64, 67));
        boolean headingUp = frame.getHeadingUp();
        boolean z4 = headingUp && frame.getTilt3d();
        float f11 = headingUp ? i2 * (z4 ? 0.74f : 0.66f) : i2 / 2.0f;
        int i12 = 256;
        int i13 = 0;
        double panX = (frame.getPanX() + (Mercator.INSTANCE.lngToTileX(frame.getCenterLng(), frame.getZoom()) * 256)) - (i / 2.0d);
        double latToTileY = ((256 * Mercator.INSTANCE.latToTileY(frame.getCenterLat(), frame.getZoom())) + frame.getPanY()) - f11;
        float normalizeHeading = normalizeHeading(frame.getCameraHeading());
        if (headingUp) {
            canvas.save();
        }
        if (headingUp) {
            if (z4) {
                float f12 = i * 0.18f;
                this.tiltSrc[0] = 0.0f;
                this.tiltSrc[1] = 0.0f;
                i3 = 2;
                this.tiltSrc[2] = i;
                this.tiltSrc[3] = 0.0f;
                this.tiltSrc[PICKER_ROWS] = i;
                this.tiltSrc[5] = i2;
                this.tiltSrc[6] = 0.0f;
                this.tiltSrc[7] = i2;
                this.tiltDst[0] = f12;
                this.tiltDst[1] = 0.0f;
                this.tiltDst[2] = i - f12;
                this.tiltDst[3] = 0.0f;
                this.tiltDst[PICKER_ROWS] = i;
                this.tiltDst[5] = i2;
                this.tiltDst[6] = 0.0f;
                this.tiltDst[7] = i2;
                this.tiltMatrix.setPolyToPoly(this.tiltSrc, 0, this.tiltDst, 0, PICKER_ROWS);
                canvas.concat(this.tiltMatrix);
            } else {
                i3 = 2;
            }
            canvas.rotate(-normalizeHeading, i / 2.0f, f11);
        } else {
            i3 = 2;
        }
        int max = headingUp ? (int) (Math.max(i, i2) * 0.45d) : 0;
        int floorDiv = Math.floorDiv((int) (panX - max), 256);
        int floorDiv2 = Math.floorDiv((int) (latToTileY - max), 256);
        int floorDiv3 = Math.floorDiv((int) (panX + i + max), 256);
        float f13 = normalizeHeading;
        int floorDiv4 = Math.floorDiv((int) (latToTileY + i2 + max), 256);
        int i14 = floorDiv;
        if (i14 <= floorDiv3) {
            while (true) {
                int i15 = floorDiv2;
                if (i15 <= floorDiv4) {
                    while (true) {
                        i10 = max;
                        float f14 = (float) ((i14 * i12) - panX);
                        float f15 = (float) ((i15 * i12) - latToTileY);
                        this.tmpRect.set(f14, f15, i12 + f14, i12 + f15);
                        f = f13;
                        i5 = i13;
                        i4 = floorDiv3;
                        i6 = i12;
                        i11 = i14;
                        drawTileBestEffort(canvas, frame.getZoom(), i11, i15, this.tmpRect);
                        if (i15 == floorDiv4) {
                            break;
                        }
                        i15++;
                        i14 = i11;
                        floorDiv3 = i4;
                        i12 = i6;
                        max = i10;
                        i13 = i5;
                        f13 = f;
                    }
                } else {
                    i10 = max;
                    i4 = floorDiv3;
                    f = f13;
                    i5 = i13;
                    i11 = i14;
                    i6 = i12;
                }
                if (i11 == i4) {
                    break;
                }
                i14 = i11 + 1;
                floorDiv3 = i4;
                i12 = i6;
                max = i10;
                i13 = i5;
                f13 = f;
            }
        } else {
            i4 = floorDiv3;
            f = f13;
            i5 = 0;
            i6 = 256;
        }
        if (frame.getRoute().size() >= i3) {
            this.routePath.reset();
            i7 = i6;
            double d4 = panX;
            d2 = latToTileY;
            this.routePath.moveTo(draw$sx(frame, i7, panX, ((GeoPoint) frame.getRoute().get(i5)).getLng()), draw$sy(frame, i7, latToTileY, ((GeoPoint) frame.getRoute().get(i5)).getLat()));
            int i16 = 1;
            int size = frame.getRoute().size();
            while (true) {
                path = this.routePath;
                if (i16 >= size) {
                    break;
                }
                path.lineTo(draw$sx(frame, i7, d4, ((GeoPoint) frame.getRoute().get(i16)).getLng()), draw$sy(frame, i7, d2, ((GeoPoint) frame.getRoute().get(i16)).getLat()));
                i16++;
                floorDiv4 = floorDiv4;
                i4 = i4;
            }
            mapRenderer = this;
            canvas2 = canvas;
            canvas2.drawPath(path, mapRenderer.routeCasing);
            canvas2.drawPath(mapRenderer.routePath, mapRenderer.routePaint);
            List<TrafficInterval> traffic = frame.getTraffic();
            boolean z5 = false;
            for (TrafficInterval trafficInterval : traffic) {
                int coerceIn = RangesKt.coerceIn(trafficInterval.getStartPointIndex(), 0, CollectionsKt.getLastIndex(frame.getRoute()));
                int coerceIn2 = RangesKt.coerceIn(trafficInterval.getEndPointIndex(), coerceIn, CollectionsKt.getLastIndex(frame.getRoute()));
                if (coerceIn2 > coerceIn) {
                    mapRenderer.routePath.reset();
                    list = traffic;
                    double d5 = d4;
                    z2 = z5;
                    z3 = headingUp;
                    d3 = d5;
                    mapRenderer.routePath.moveTo(draw$sx(frame, i7, d5, ((GeoPoint) frame.getRoute().get(coerceIn)).getLng()), draw$sy(frame, i7, d2, ((GeoPoint) frame.getRoute().get(coerceIn)).getLat()));
                    int i17 = coerceIn + 1;
                    if (i17 <= coerceIn2) {
                        int i18 = i17;
                        while (true) {
                            str3 = str4;
                            mapRenderer.routePath.lineTo(draw$sx(frame, i7, d3, ((GeoPoint) frame.getRoute().get(i18)).getLng()), draw$sy(frame, i7, d2, ((GeoPoint) frame.getRoute().get(i18)).getLat()));
                            if (i18 != coerceIn2) {
                                i18++;
                                str4 = str3;
                            }
                        }
                    } else {
                        str3 = str4;
                    }
                    Paint paint = mapRenderer.routePaint;
                    switch (WhenMappings.$EnumSwitchMapping$0[trafficInterval.getSpeed().ordinal()]) {
                        case 1:
                            i9 = mapRenderer.routeBlue;
                            break;
                        case 2:
                            i9 = mapRenderer.trafficAmber;
                            break;
                        case 3:
                            i9 = mapRenderer.trafficRed;
                            break;
                        case PICKER_ROWS /* 4 */:
                            i9 = mapRenderer.routeBlue;
                            break;
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                    paint.setColor(i9);
                    canvas2.drawPath(mapRenderer.routePath, mapRenderer.routePaint);
                } else {
                    list = traffic;
                    str3 = str4;
                    d3 = d4;
                    z2 = z5;
                    z3 = headingUp;
                }
                z5 = z2;
                headingUp = z3;
                traffic = list;
                d4 = d3;
                str4 = str3;
            }
            str = str4;
            d = d4;
            z = headingUp;
            mapRenderer.routePaint.setColor(mapRenderer.routeBlue);
        } else {
            canvas2 = canvas;
            str = "render";
            z = headingUp;
            i7 = i6;
            d = panX;
            d2 = latToTileY;
            mapRenderer = this;
        }
        List destinations = frame.getDestinations();
        if (destinations.isEmpty()) {
            Double destLat = frame.getDestLat();
            GeoPoint geoPoint = null;
            if (destLat != null) {
                double doubleValue = destLat.doubleValue();
                Double destLng = frame.getDestLng();
                if (destLng != null) {
                    geoPoint = new GeoPoint(doubleValue, destLng.doubleValue());
                }
            }
            destinations = CollectionsKt.listOfNotNull(geoPoint);
        }
        List list2 = destinations;
        List list3 = list2;
        int i19 = 0;
        for (Object obj : list3) {
            int i20 = i19 + 1;
            if (i19 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            GeoPoint geoPoint2 = (GeoPoint) obj;
            List list4 = list3;
            int i21 = i19;
            float draw$sx = draw$sx(frame, i7, d, geoPoint2.getLng());
            List list5 = list2;
            double d6 = d2;
            float draw$sy = draw$sy(frame, i7, d6, geoPoint2.getLat());
            int i22 = i7;
            mapRenderer.dotPaint.setColor(-1);
            canvas2.drawCircle(draw$sx, draw$sy, 12.0f, mapRenderer.dotPaint);
            mapRenderer.dotPaint.setColor(i21 == CollectionsKt.getLastIndex(list5) ? mapRenderer.googleRed : Color.rgb(242, 169, 59));
            canvas2.drawCircle(draw$sx, draw$sy, 9.0f, mapRenderer.dotPaint);
            mapRenderer.dotPaint.setColor(-1);
            canvas2.drawCircle(draw$sx, draw$sy, 3.5f, mapRenderer.dotPaint);
            i7 = i22;
            i19 = i20;
            list3 = list4;
            list2 = list5;
            d2 = d6;
        }
        if (z) {
            canvas2.restore();
        }
        if (frame.getRiderLat() != null && frame.getRiderLng() != null) {
            float f16 = i / 2.0f;
            int rgb = frame.getGpsLost() ? Color.rgb(150, 154, 160) : frame.getGpsWeak() ? Color.rgb(251, 188, 5) : mapRenderer.routeBlue;
            mapRenderer.dotPaint.setColor(Color.argb(60, Color.red(rgb), Color.green(rgb), Color.blue(rgb)));
            canvas2.drawCircle(f16, f11, NOTICE_GAP_ABOVE_CENTRE, mapRenderer.dotPaint);
            float normalizeHeading2 = frame.getHeadingUp() ? mapRenderer.normalizeHeading(frame.getRiderHeading() - f) : mapRenderer.normalizeHeading(frame.getRiderHeading());
            canvas2.save();
            canvas2.translate(f16, f11);
            canvas2.rotate(normalizeHeading2);
            mapRenderer.dotPaint.setColor(-1);
            canvas2.drawCircle(0.0f, 0.0f, 10.0f, mapRenderer.dotPaint);
            mapRenderer.riderPath.reset();
            mapRenderer.riderPath.moveTo(0.0f, -8.0f);
            mapRenderer.riderPath.lineTo(6.5f, 7.0f);
            mapRenderer.riderPath.lineTo(0.0f, 3.5f);
            mapRenderer.riderPath.lineTo(-6.5f, 7.0f);
            mapRenderer.riderPath.close();
            mapRenderer.dotPaint.setColor(rgb);
            canvas2.drawPath(mapRenderer.riderPath, mapRenderer.dotPaint);
            canvas2.restore();
        }
        float f17 = i2 - ETA_MARGIN_BOTTOM;
        String etaPrimary = frame.getEtaPrimary();
        if (etaPrimary != null) {
            String etaSecondary = frame.getEtaSecondary();
            f4 = ETA_MARGIN_RIGHT;
            Paint.FontMetrics fontMetrics = mapRenderer.etaBigPaint.getFontMetrics();
            Paint.FontMetrics fontMetrics2 = mapRenderer.etaSmallPaint.getFontMetrics();
            f3 = 8.0f;
            float f18 = fontMetrics.descent - fontMetrics.ascent;
            float f19 = etaSecondary != null ? fontMetrics2.descent - fontMetrics2.ascent : 0.0f;
            float measureText = mapRenderer.etaBigPaint.measureText(etaPrimary);
            if (etaSecondary != null) {
                f9 = f18;
                f10 = mapRenderer.etaSmallPaint.measureText(etaSecondary);
            } else {
                f9 = f18;
                f10 = 0.0f;
            }
            mapRenderer.pillRect.set((i - ETA_MARGIN_RIGHT) - RangesKt.coerceAtMost((9.0f * 2.0f) + Math.max(measureText, f10), i2 * 0.5f), (i2 - ETA_MARGIN_BOTTOM) - (((4.0f * 2.0f) + f9) + (etaSecondary != null ? 1.0f + f19 : 0.0f)), i - ETA_MARGIN_RIGHT, i2 - ETA_MARGIN_BOTTOM);
            float centerX = mapRenderer.pillRect.centerX();
            float f20 = mapRenderer.pillRect.top;
            canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.etaBgPaint);
            canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.etaBorderPaint);
            float f21 = (f20 + 4.0f) - fontMetrics.ascent;
            canvas2.drawText(etaPrimary, centerX, f21, mapRenderer.etaBigPaint);
            if (etaSecondary != null) {
                canvas2.drawText(etaSecondary, centerX, f21 + ((fontMetrics.descent + 1.0f) - fontMetrics2.ascent), mapRenderer.etaSmallPaint);
            }
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
            f2 = f20;
        } else {
            f2 = f17;
            f3 = 8.0f;
            f4 = ETA_MARGIN_RIGHT;
        }
        float f22 = i2 - f3;
        try {
            String attribution = GPlus.attribution(frame.getAttribution());
            if (attribution != null) {
                Paint.FontMetrics fontMetrics3 = mapRenderer.attributionPaint.getFontMetrics();
                float measureText2 = mapRenderer.attributionPaint.measureText(attribution);
                try {
                    mapRenderer.pillRect.set(((i / 2.0f) - (measureText2 / 2.0f)) - 5.0f, (i2 - 3.0f) - ((fontMetrics3.descent - fontMetrics3.ascent) + 4.0f), (i / 2.0f) + (measureText2 / 2.0f) + 5.0f, i2 - 3.0f);
                    canvas2.drawRoundRect(mapRenderer.pillRect, 4.0f, 4.0f, mapRenderer.etaBgPaint);
                    canvas2.drawText(attribution, i / 2.0f, (mapRenderer.pillRect.top + 2.0f) - fontMetrics3.ascent, mapRenderer.attributionPaint);
                    f22 = mapRenderer.pillRect.top - 3.0f;
                } catch (Exception e) {
                    e = e;
                    f22 = f22;
                }
                try {
                    Unit unit3 = Unit.INSTANCE;
                    Unit unit4 = Unit.INSTANCE;
                } catch (Exception e2) {
                    e = e2;
                    if (this.failedOverlays.add("attribution")) {
                        str2 = str;
                        RideDiagnostics.INSTANCE.log(str2, "overlay 'attribution' failed: " + e);
                    } else {
                        str2 = str;
                    }
                    joystickTrail = frame.getJoystickTrail();
                    if (joystickTrail == null) {
                    }
                    f5 = f22;
                    downHint = frame.getDownHint();
                    if (downHint != null) {
                    }
                    float f23 = f2;
                    phoneBattery = frame.getPhoneBattery();
                    if (phoneBattery == null) {
                    }
                    zoomLabel = frame.getZoomLabel();
                    if (zoomLabel != null) {
                    }
                    routingBadge = frame.getRoutingBadge();
                    if (routingBadge == null) {
                    }
                    if (!frame.getGpsLost()) {
                    }
                    if (frame.getGpsLost()) {
                    }
                    mapRenderer.gpsPillText.setColor(frame.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                    Paint.FontMetrics fontMetrics4 = mapRenderer.gpsPillText.getFontMetrics();
                    float f24 = fontMetrics4.descent - fontMetrics4.ascent;
                    float measureText3 = mapRenderer.gpsPillText.measureText(r1) + (14.0f * 2.0f);
                    float f25 = i / 2.0f;
                    float f26 = (6.0f * 2.0f) + 14.0f + f24;
                    mapRenderer.pillRect.set(f25 - (measureText3 / 2.0f), 14.0f, (measureText3 / 2.0f) + f25, f26);
                    float f27 = (f26 - 14.0f) / 2.0f;
                    canvas2.drawRoundRect(mapRenderer.pillRect, f27, f27, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, f27, f27, mapRenderer.etaBorderPaint);
                    canvas2.drawText(r1, f25, (14.0f + 6.0f) - fontMetrics4.ascent, mapRenderer.gpsPillText);
                    notice = frame.getNotice();
                    if (notice != null) {
                    }
                    pickerRows = frame.getPickerRows();
                    if (pickerRows != null) {
                    }
                    if (frame.getRiderLat() != null) {
                        return;
                    }
                }
            }
            str2 = str;
        } catch (Exception e3) {
            e = e3;
        }
        try {
            joystickTrail = frame.getJoystickTrail();
            if (joystickTrail == null) {
                Paint.FontMetrics fontMetrics5 = mapRenderer.etaSmallPaint.getFontMetrics();
                float measureText4 = mapRenderer.etaSmallPaint.measureText("WWWW") + (7.0f * 2.0f);
                float f28 = i / 2.0f;
                try {
                    mapRenderer.pillRect.set(f28 - (measureText4 / 2.0f), f22 - (((3.0f * 2.0f) + fontMetrics5.descent) - fontMetrics5.ascent), f28 + (measureText4 / 2.0f), f22);
                    canvas2.drawRoundRect(mapRenderer.pillRect, 6.0f, 6.0f, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, 6.0f, 6.0f, mapRenderer.etaBorderPaint);
                    canvas2.drawText(joystickTrail, f28, (mapRenderer.pillRect.top + 3.0f) - fontMetrics5.ascent, mapRenderer.etaSmallPaint);
                    f22 = mapRenderer.pillRect.top - 4.0f;
                    Unit unit5 = Unit.INSTANCE;
                    Unit unit6 = Unit.INSTANCE;
                } catch (Exception e4) {
                    e = e4;
                    if (this.failedOverlays.add("joystick presses")) {
                        RideDiagnostics.INSTANCE.log(str2, "overlay 'joystick presses' failed: " + e);
                    }
                    f5 = f22;
                    downHint = frame.getDownHint();
                    if (downHint != null) {
                    }
                    float f232 = f2;
                    phoneBattery = frame.getPhoneBattery();
                    if (phoneBattery == null) {
                    }
                    zoomLabel = frame.getZoomLabel();
                    if (zoomLabel != null) {
                    }
                    routingBadge = frame.getRoutingBadge();
                    if (routingBadge == null) {
                    }
                    if (!frame.getGpsLost()) {
                    }
                    if (frame.getGpsLost()) {
                    }
                    mapRenderer.gpsPillText.setColor(frame.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                    Paint.FontMetrics fontMetrics42 = mapRenderer.gpsPillText.getFontMetrics();
                    float f242 = fontMetrics42.descent - fontMetrics42.ascent;
                    float measureText32 = mapRenderer.gpsPillText.measureText(r1) + (14.0f * 2.0f);
                    float f252 = i / 2.0f;
                    float f262 = (6.0f * 2.0f) + 14.0f + f242;
                    mapRenderer.pillRect.set(f252 - (measureText32 / 2.0f), 14.0f, (measureText32 / 2.0f) + f252, f262);
                    float f272 = (f262 - 14.0f) / 2.0f;
                    canvas2.drawRoundRect(mapRenderer.pillRect, f272, f272, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, f272, f272, mapRenderer.etaBorderPaint);
                    canvas2.drawText(r1, f252, (14.0f + 6.0f) - fontMetrics42.ascent, mapRenderer.gpsPillText);
                    notice = frame.getNotice();
                    if (notice != null) {
                    }
                    pickerRows = frame.getPickerRows();
                    if (pickerRows != null) {
                    }
                    if (frame.getRiderLat() != null) {
                    }
                }
            }
            f5 = f22;
        } catch (Exception e5) {
            e = e5;
        }
        try {
            downHint = frame.getDownHint();
            if (downHint != null) {
                Paint.FontMetrics fontMetrics6 = mapRenderer.etaSmallPaint.getFontMetrics();
                float measureText5 = mapRenderer.etaSmallPaint.measureText(downHint) + (9.0f * 2.0f);
                float f29 = i / 2.0f;
                try {
                    mapRenderer.pillRect.set(f29 - (measureText5 / 2.0f), f5 - (((3.0f * 2.0f) + fontMetrics6.descent) - fontMetrics6.ascent), f29 + (measureText5 / 2.0f), f5);
                    float f30 = f3;
                    canvas2.drawRoundRect(mapRenderer.pillRect, f30, f30, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, f30, f30, mapRenderer.etaBorderPaint);
                    canvas2.drawText(downHint, f29, (mapRenderer.pillRect.top + 3.0f) - fontMetrics6.ascent, mapRenderer.etaSmallPaint);
                    Unit unit7 = Unit.INSTANCE;
                    Unit unit8 = Unit.INSTANCE;
                } catch (Exception e6) {
                    e = e6;
                    if (this.failedOverlays.add("down hint")) {
                        RideDiagnostics.INSTANCE.log(str2, "overlay 'down hint' failed: " + e);
                    }
                    float f2322 = f2;
                    phoneBattery = frame.getPhoneBattery();
                    if (phoneBattery == null) {
                    }
                    zoomLabel = frame.getZoomLabel();
                    if (zoomLabel != null) {
                    }
                    routingBadge = frame.getRoutingBadge();
                    if (routingBadge == null) {
                    }
                    if (!frame.getGpsLost()) {
                    }
                    if (frame.getGpsLost()) {
                    }
                    mapRenderer.gpsPillText.setColor(frame.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                    Paint.FontMetrics fontMetrics422 = mapRenderer.gpsPillText.getFontMetrics();
                    float f2422 = fontMetrics422.descent - fontMetrics422.ascent;
                    float measureText322 = mapRenderer.gpsPillText.measureText(r1) + (14.0f * 2.0f);
                    float f2522 = i / 2.0f;
                    float f2622 = (6.0f * 2.0f) + 14.0f + f2422;
                    mapRenderer.pillRect.set(f2522 - (measureText322 / 2.0f), 14.0f, (measureText322 / 2.0f) + f2522, f2622);
                    float f2722 = (f2622 - 14.0f) / 2.0f;
                    canvas2.drawRoundRect(mapRenderer.pillRect, f2722, f2722, mapRenderer.etaBgPaint);
                    canvas2.drawRoundRect(mapRenderer.pillRect, f2722, f2722, mapRenderer.etaBorderPaint);
                    canvas2.drawText(r1, f2522, (14.0f + 6.0f) - fontMetrics422.ascent, mapRenderer.gpsPillText);
                    notice = frame.getNotice();
                    if (notice != null) {
                    }
                    pickerRows = frame.getPickerRows();
                    if (pickerRows != null) {
                    }
                    if (frame.getRiderLat() != null) {
                    }
                }
            }
        } catch (Exception e7) {
            e = e7;
        }
        float f23222 = f2;
        try {
            phoneBattery = frame.getPhoneBattery();
            if (phoneBattery == null) {
                int intValue = ((Number) phoneBattery.component1()).intValue();
                boolean booleanValue = ((Boolean) phoneBattery.component2()).booleanValue();
                String str5 = intValue + "%";
                Paint.FontMetrics fontMetrics7 = mapRenderer.etaSmallPaint.getFontMetrics();
                try {
                    float f31 = fontMetrics7.descent - fontMetrics7.ascent;
                    float f32 = booleanValue ? (f31 * 0.55f) + 4.0f : 0.0f;
                    float f33 = (4.0f * 2.0f) + f31;
                    float f34 = f23222 - 6.0f;
                    try {
                        try {
                            mapRenderer.pillRect.set((i - f4) - ((mapRenderer.etaSmallPaint.measureText(str5) + f32) + (9.0f * 2.0f)), f34 - f33, i - f4, f34);
                            canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.etaBgPaint);
                            canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.etaBorderPaint);
                            float f35 = mapRenderer.pillRect.left + 9.0f + f32;
                            if (booleanValue) {
                                float f36 = mapRenderer.pillRect.left + 9.0f;
                                float f37 = mapRenderer.pillRect.top + 4.0f + 1.0f;
                                float f38 = f31 - 2.0f;
                                float f39 = f31 * 0.55f;
                                mapRenderer.boltPath.reset();
                                mapRenderer.boltPath.moveTo(f36 + (0.65f * f39), f37);
                                mapRenderer.boltPath.lineTo(f36, (0.56f * f38) + f37);
                                mapRenderer.boltPath.lineTo((0.45f * f39) + f36, f37 + (0.56f * f38));
                                mapRenderer.boltPath.lineTo(f36 + (0.3f * f39), f37 + f38);
                                mapRenderer.boltPath.lineTo(f36 + f39, (0.4f * f38) + f37);
                                mapRenderer.boltPath.lineTo(f36 + (0.55f * f39), (0.4f * f38) + f37);
                                mapRenderer.boltPath.close();
                                canvas2.drawPath(mapRenderer.boltPath, mapRenderer.boltPaint);
                            }
                            int color = mapRenderer.etaSmallPaint.getColor();
                            Paint.Align textAlign = mapRenderer.etaSmallPaint.getTextAlign();
                            if (!booleanValue) {
                                mapRenderer.etaSmallPaint.setColor(intValue <= 15 ? mapRenderer.googleRed : mapRenderer.batteryYellow);
                            }
                            mapRenderer.etaSmallPaint.setTextAlign(Paint.Align.LEFT);
                            canvas2.drawText(str5, f35, (mapRenderer.pillRect.top + 4.0f) - fontMetrics7.ascent, mapRenderer.etaSmallPaint);
                            mapRenderer.etaSmallPaint.setColor(color);
                            mapRenderer.etaSmallPaint.setTextAlign(textAlign);
                            f23222 = mapRenderer.pillRect.top;
                        } catch (Exception e8) {
                            e = e8;
                            f23222 = f23222;
                        }
                    } catch (Exception e9) {
                        e = e9;
                        f23222 = f23222;
                    }
                    try {
                        Unit unit9 = Unit.INSTANCE;
                        Unit unit10 = Unit.INSTANCE;
                    } catch (Exception e10) {
                        e = e10;
                        if (this.failedOverlays.add("battery")) {
                            RideDiagnostics.INSTANCE.log(str2, "overlay 'battery' failed: " + e);
                        }
                        zoomLabel = frame.getZoomLabel();
                        if (zoomLabel != null) {
                        }
                        routingBadge = frame.getRoutingBadge();
                        if (routingBadge == null) {
                        }
                        if (!frame.getGpsLost()) {
                        }
                        if (frame.getGpsLost()) {
                        }
                        mapRenderer.gpsPillText.setColor(frame.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                        Paint.FontMetrics fontMetrics4222 = mapRenderer.gpsPillText.getFontMetrics();
                        float f24222 = fontMetrics4222.descent - fontMetrics4222.ascent;
                        float measureText3222 = mapRenderer.gpsPillText.measureText(r1) + (14.0f * 2.0f);
                        float f25222 = i / 2.0f;
                        float f26222 = (6.0f * 2.0f) + 14.0f + f24222;
                        mapRenderer.pillRect.set(f25222 - (measureText3222 / 2.0f), 14.0f, (measureText3222 / 2.0f) + f25222, f26222);
                        float f27222 = (f26222 - 14.0f) / 2.0f;
                        canvas2.drawRoundRect(mapRenderer.pillRect, f27222, f27222, mapRenderer.etaBgPaint);
                        canvas2.drawRoundRect(mapRenderer.pillRect, f27222, f27222, mapRenderer.etaBorderPaint);
                        canvas2.drawText(r1, f25222, (14.0f + 6.0f) - fontMetrics4222.ascent, mapRenderer.gpsPillText);
                        notice = frame.getNotice();
                        if (notice != null) {
                        }
                        pickerRows = frame.getPickerRows();
                        if (pickerRows != null) {
                        }
                        if (frame.getRiderLat() != null) {
                        }
                    }
                } catch (Exception e11) {
                    e = e11;
                    f23222 = f23222;
                }
            }
        } catch (Exception e12) {
            e = e12;
        }
        try {
            zoomLabel = frame.getZoomLabel();
            if (zoomLabel != null) {
                Paint.FontMetrics fontMetrics8 = mapRenderer.etaSmallPaint.getFontMetrics();
                float f40 = f23222 - 6.0f;
                f6 = f23222;
                try {
                    try {
                        mapRenderer.pillRect.set((i - f4) - (mapRenderer.etaSmallPaint.measureText(zoomLabel) + (9.0f * 2.0f)), f40 - (((4.0f * 2.0f) + fontMetrics8.descent) - fontMetrics8.ascent), i - f4, f40);
                        canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.etaBgPaint);
                        canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.etaBorderPaint);
                        canvas2.drawText(zoomLabel, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics8.ascent, mapRenderer.etaSmallPaint);
                        f23222 = mapRenderer.pillRect.top;
                    } catch (Exception e13) {
                        e = e13;
                        f23222 = f6;
                    }
                    try {
                        Unit unit11 = Unit.INSTANCE;
                        Unit unit12 = Unit.INSTANCE;
                    } catch (Exception e14) {
                        e = e14;
                        if (this.failedOverlays.add("zoom")) {
                            RideDiagnostics.INSTANCE.log(str2, "overlay 'zoom' failed: " + e);
                        }
                        routingBadge = frame.getRoutingBadge();
                        if (routingBadge == null) {
                        }
                        if (!frame.getGpsLost()) {
                        }
                        if (frame.getGpsLost()) {
                        }
                        mapRenderer.gpsPillText.setColor(frame.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                        Paint.FontMetrics fontMetrics42222 = mapRenderer.gpsPillText.getFontMetrics();
                        float f242222 = fontMetrics42222.descent - fontMetrics42222.ascent;
                        float measureText32222 = mapRenderer.gpsPillText.measureText(r1) + (14.0f * 2.0f);
                        float f252222 = i / 2.0f;
                        float f262222 = (6.0f * 2.0f) + 14.0f + f242222;
                        mapRenderer.pillRect.set(f252222 - (measureText32222 / 2.0f), 14.0f, (measureText32222 / 2.0f) + f252222, f262222);
                        float f272222 = (f262222 - 14.0f) / 2.0f;
                        canvas2.drawRoundRect(mapRenderer.pillRect, f272222, f272222, mapRenderer.etaBgPaint);
                        canvas2.drawRoundRect(mapRenderer.pillRect, f272222, f272222, mapRenderer.etaBorderPaint);
                        canvas2.drawText(r1, f252222, (14.0f + 6.0f) - fontMetrics42222.ascent, mapRenderer.gpsPillText);
                        notice = frame.getNotice();
                        if (notice != null) {
                        }
                        pickerRows = frame.getPickerRows();
                        if (pickerRows != null) {
                        }
                        if (frame.getRiderLat() != null) {
                        }
                    }
                } catch (Exception e15) {
                    e = e15;
                    f23222 = f6;
                }
            } else {
                f6 = f23222;
            }
        } catch (Exception e16) {
            e = e16;
            f6 = f23222;
        }
        try {
            routingBadge = frame.getRoutingBadge();
            if (routingBadge == null) {
                try {
                    try {
                        switch (WhenMappings.$EnumSwitchMapping$1[routingBadge.ordinal()]) {
                            case 1:
                                pair = TuplesKt.to("G", Integer.valueOf(ROUTING_GREEN));
                                String str6 = (String) pair.component1();
                                int intValue2 = ((Number) pair.component2()).intValue();
                                Paint.FontMetrics fontMetrics9 = mapRenderer.etaSmallPaint.getFontMetrics();
                                f7 = f23222;
                                try {
                                    float f41 = f7 - 6.0f;
                                    mapRenderer.pillRect.set((i - f4) - (Math.max(mapRenderer.etaSmallPaint.measureText("BR"), mapRenderer.etaSmallPaint.measureText(str6)) + (9.0f * 2.0f)), f41 - (((4.0f * 2.0f) + fontMetrics9.descent) - fontMetrics9.ascent), i - f4, f41);
                                    mapRenderer.routingBadgePaint.setColor(intValue2);
                                    canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.routingBadgePaint);
                                    int color2 = mapRenderer.etaSmallPaint.getColor();
                                    boolean isFakeBoldText = mapRenderer.etaSmallPaint.isFakeBoldText();
                                    mapRenderer.etaSmallPaint.setColor(-1);
                                    mapRenderer.etaSmallPaint.setFakeBoldText(true);
                                    canvas2.drawText(str6, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics9.ascent, mapRenderer.etaSmallPaint);
                                    mapRenderer.etaSmallPaint.setColor(color2);
                                    mapRenderer.etaSmallPaint.setFakeBoldText(isFakeBoldText);
                                    f8 = mapRenderer.pillRect.top;
                                    try {
                                        Unit unit13 = Unit.INSTANCE;
                                        Unit unit14 = Unit.INSTANCE;
                                        f23222 = f8;
                                        break;
                                    } catch (Exception e17) {
                                        e = e17;
                                        f23222 = f8;
                                        if (this.failedOverlays.add("routing source")) {
                                            RideDiagnostics.INSTANCE.log(str2, "overlay 'routing source' failed: " + e);
                                        }
                                        if (!frame.getGpsLost()) {
                                        }
                                        if (frame.getGpsLost()) {
                                        }
                                        mapRenderer.gpsPillText.setColor(frame.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
                                        Paint.FontMetrics fontMetrics422222 = mapRenderer.gpsPillText.getFontMetrics();
                                        float f2422222 = fontMetrics422222.descent - fontMetrics422222.ascent;
                                        float measureText322222 = mapRenderer.gpsPillText.measureText(r1) + (14.0f * 2.0f);
                                        float f2522222 = i / 2.0f;
                                        float f2622222 = (6.0f * 2.0f) + 14.0f + f2422222;
                                        mapRenderer.pillRect.set(f2522222 - (measureText322222 / 2.0f), 14.0f, (measureText322222 / 2.0f) + f2522222, f2622222);
                                        float f2722222 = (f2622222 - 14.0f) / 2.0f;
                                        canvas2.drawRoundRect(mapRenderer.pillRect, f2722222, f2722222, mapRenderer.etaBgPaint);
                                        canvas2.drawRoundRect(mapRenderer.pillRect, f2722222, f2722222, mapRenderer.etaBorderPaint);
                                        canvas2.drawText(r1, f2522222, (14.0f + 6.0f) - fontMetrics422222.ascent, mapRenderer.gpsPillText);
                                        notice = frame.getNotice();
                                        if (notice != null) {
                                        }
                                        pickerRows = frame.getPickerRows();
                                        if (pickerRows != null) {
                                        }
                                        if (frame.getRiderLat() != null) {
                                        }
                                    }
                                } catch (Exception e18) {
                                    e = e18;
                                    f23222 = f7;
                                }
                                break;
                            case 2:
                                pair = TuplesKt.to("O", Integer.valueOf(ROUTING_ORANGE));
                                String str62 = (String) pair.component1();
                                int intValue22 = ((Number) pair.component2()).intValue();
                                Paint.FontMetrics fontMetrics92 = mapRenderer.etaSmallPaint.getFontMetrics();
                                f7 = f23222;
                                float f412 = f7 - 6.0f;
                                mapRenderer.pillRect.set((i - f4) - (Math.max(mapRenderer.etaSmallPaint.measureText("BR"), mapRenderer.etaSmallPaint.measureText(str62)) + (9.0f * 2.0f)), f412 - (((4.0f * 2.0f) + fontMetrics92.descent) - fontMetrics92.ascent), i - f4, f412);
                                mapRenderer.routingBadgePaint.setColor(intValue22);
                                canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.routingBadgePaint);
                                int color22 = mapRenderer.etaSmallPaint.getColor();
                                boolean isFakeBoldText2 = mapRenderer.etaSmallPaint.isFakeBoldText();
                                mapRenderer.etaSmallPaint.setColor(-1);
                                mapRenderer.etaSmallPaint.setFakeBoldText(true);
                                canvas2.drawText(str62, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics92.ascent, mapRenderer.etaSmallPaint);
                                mapRenderer.etaSmallPaint.setColor(color22);
                                mapRenderer.etaSmallPaint.setFakeBoldText(isFakeBoldText2);
                                f8 = mapRenderer.pillRect.top;
                                Unit unit132 = Unit.INSTANCE;
                                Unit unit142 = Unit.INSTANCE;
                                f23222 = f8;
                                break;
                            case 3:
                                pair = TuplesKt.to("BR", Integer.valueOf(ROUTING_BROWN));
                                String str622 = (String) pair.component1();
                                int intValue222 = ((Number) pair.component2()).intValue();
                                Paint.FontMetrics fontMetrics922 = mapRenderer.etaSmallPaint.getFontMetrics();
                                f7 = f23222;
                                float f4122 = f7 - 6.0f;
                                mapRenderer.pillRect.set((i - f4) - (Math.max(mapRenderer.etaSmallPaint.measureText("BR"), mapRenderer.etaSmallPaint.measureText(str622)) + (9.0f * 2.0f)), f4122 - (((4.0f * 2.0f) + fontMetrics922.descent) - fontMetrics922.ascent), i - f4, f4122);
                                mapRenderer.routingBadgePaint.setColor(intValue222);
                                canvas2.drawRoundRect(mapRenderer.pillRect, ETA_MARGIN_BOTTOM, ETA_MARGIN_BOTTOM, mapRenderer.routingBadgePaint);
                                int color222 = mapRenderer.etaSmallPaint.getColor();
                                boolean isFakeBoldText22 = mapRenderer.etaSmallPaint.isFakeBoldText();
                                mapRenderer.etaSmallPaint.setColor(-1);
                                mapRenderer.etaSmallPaint.setFakeBoldText(true);
                                canvas2.drawText(str622, mapRenderer.pillRect.centerX(), (mapRenderer.pillRect.top + 4.0f) - fontMetrics922.ascent, mapRenderer.etaSmallPaint);
                                mapRenderer.etaSmallPaint.setColor(color222);
                                mapRenderer.etaSmallPaint.setFakeBoldText(isFakeBoldText22);
                                f8 = mapRenderer.pillRect.top;
                                Unit unit1322 = Unit.INSTANCE;
                                Unit unit1422 = Unit.INSTANCE;
                                f23222 = f8;
                                break;
                            default:
                                throw new NoWhenBranchMatchedException();
                        }
                    } catch (Exception e19) {
                        e = e19;
                    }
                } catch (Exception e20) {
                    e = e20;
                    f23222 = f6;
                }
            }
        } catch (Exception e21) {
            e = e21;
        }
        if (!frame.getGpsLost() || frame.getGpsWeak()) {
            String str7 = frame.getGpsLost() ? "GPS SİNYALİ YOK" : "GPS SİNYALİ ZAYIF";
            mapRenderer.gpsPillText.setColor(frame.getGpsLost() ? mapRenderer.googleRed : Color.rgb(251, 188, 5));
            Paint.FontMetrics fontMetrics4222222 = mapRenderer.gpsPillText.getFontMetrics();
            float f24222222 = fontMetrics4222222.descent - fontMetrics4222222.ascent;
            float measureText3222222 = mapRenderer.gpsPillText.measureText(str7) + (14.0f * 2.0f);
            float f25222222 = i / 2.0f;
            float f26222222 = (6.0f * 2.0f) + 14.0f + f24222222;
            mapRenderer.pillRect.set(f25222222 - (measureText3222222 / 2.0f), 14.0f, (measureText3222222 / 2.0f) + f25222222, f26222222);
            float f27222222 = (f26222222 - 14.0f) / 2.0f;
            canvas2.drawRoundRect(mapRenderer.pillRect, f27222222, f27222222, mapRenderer.etaBgPaint);
            canvas2.drawRoundRect(mapRenderer.pillRect, f27222222, f27222222, mapRenderer.etaBorderPaint);
            canvas2.drawText(str7, f25222222, (14.0f + 6.0f) - fontMetrics4222222.ascent, mapRenderer.gpsPillText);
        }
        notice = frame.getNotice();
        if (notice != null) {
            i8 = i2;
            mapRenderer.drawNotice(canvas2, i, i8, notice);
            Unit unit15 = Unit.INSTANCE;
            Unit unit16 = Unit.INSTANCE;
        } else {
            i8 = i2;
        }
        pickerRows = frame.getPickerRows();
        if (pickerRows != null) {
            mapRenderer2 = mapRenderer;
            canvas3 = canvas2;
            mapRenderer2.drawPicker(canvas3, i, i8, frame.getPickerTitle(), pickerRows, frame.getPickerIndex(), frame.getPickerSelectHint());
            Unit unit17 = Unit.INSTANCE;
            Unit unit18 = Unit.INSTANCE;
        } else {
            mapRenderer2 = mapRenderer;
            canvas3 = canvas2;
        }
        if (frame.getRiderLat() != null && frame.getDestLat() == null) {
            mapRenderer2.standbyPaint.getTextBounds("HIMALAYAN 450 · GPS BEKLENİYOR", 0, "HIMALAYAN 450 · GPS BEKLENİYOR".length(), mapRenderer2.textBounds);
            canvas3.drawText("HIMALAYAN 450 · GPS BEKLENİYOR", (i - mapRenderer2.textBounds.width()) / 2.0f, i8 / 2.0f, mapRenderer2.standbyPaint);
        }
    }
}
